package com.dhcc.listener;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.dhcc.dto.CommonResult;
import com.dhcc.entity.TbAd;
import com.dhcc.service.AdvertisementService;
import com.xpand.starter.canal.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @description 监听MySQL数据库广告表的变化
 * @author zhangqi
 * @date 2020/5/20
 */
@CanalEventListener
public class MysqlDataListener {
    private static final Logger logger = LoggerFactory.getLogger(MysqlDataListener.class);

    @Autowired
    private AdvertisementService advertisementService;

    /**
     * @description 监听插入数据操作
     * @param eventType 当前操作的类型
     * @param rowData 发生变更的一行数据
     */
    @InsertListenPoint(schema = "changgou", table = "tb_ad", destination = "example")
    public void listenInsert(CanalEntry.EventType eventType, CanalEntry.RowData rowData) {
        logger.info("----------监听到数据库插入数据----------");
        logger.info("插入数据：");
        List<CanalEntry.Column> afterColumnsList = rowData.getAfterColumnsList();
        log(afterColumnsList);
    }

    /**
     * @description 监听修改数据操作
     * @param eventType 当前操作的类型
     * @param rowData 发生变更的一行数据
     */
    @UpdateListenPoint(schema = "changgou", table = "tb_ad", destination = "example")
    public void listenUpdate(CanalEntry.EventType eventType, CanalEntry.RowData rowData) {
        logger.info("----------监听到数据库修改数据----------");
        List<CanalEntry.Column> beforeColumnsList = rowData.getBeforeColumnsList();
        logger.info("修改前的数据：");
        log(beforeColumnsList);
        List<CanalEntry.Column> afterColumnsList = rowData.getAfterColumnsList();
        logger.info("修改后的数据：");
        log(afterColumnsList);
        updateRedisData(eventType, rowData);
    }

    /**
     * @description 监听删除数据操作
     * @param eventType 当前操作的类型
     * @param rowData 发生变更的一行数据
     */
    @DeleteListenPoint(schema = "changgou", table = "tb_ad", destination = "example")
    public void listenDelete(CanalEntry.EventType eventType, CanalEntry.RowData rowData) {
        logger.info("----------监听到数据库删除数据----------");
        List<CanalEntry.Column> beforeColumnsList = rowData.getBeforeColumnsList();
        logger.info("删除的数据：");
        log(beforeColumnsList);
    }


    /**
     * @description 根据mysql数据的变化，调用广告服务查询mysql数据，主要为了练习openfeign调用服务的过程
     * @param eventType 数据操作类型
     */
    public void updateRedisData(CanalEntry.EventType eventType, CanalEntry.RowData rowData) {
        if (CanalEntry.EventType.UPDATE_VALUE == eventType.getNumber()) {
            List<CanalEntry.Column> afterColumnsList = rowData.getAfterColumnsList();
            String id = afterColumnsList.get(0).getValue();
            CommonResult<TbAd> commonResult = this.advertisementService.selectOne(Integer.parseInt(id));

            System.out.println(commonResult.getData().getId());
            System.out.println(commonResult.getData().getName());
        }
    }


    /**
     * @description 记录变更的数据
     * @param columnsList 变更数据
     */
    private void  log(List<CanalEntry.Column> columnsList) {
        StringBuilder builder = new StringBuilder("");
        for (CanalEntry.Column x : columnsList) {
            builder.append(x.getName())
                    .append(":")
                    .append(x.getValue())
                    .append("   ");
        }
        logger.info(builder.toString());
    }


    /*自定义监听*/
    /*@ListenPoint(eventType = {CanalEntry.EventType.UPDATE, CanalEntry.EventType.DELETE, CanalEntry.EventType.INSERT},
            schema = "changgou", table = "tb_ad", destination = "example")
    public void listenAll(CanalEntry.EventType eventType, CanalEntry.RowData rowData) {
    }*/
}
