package com.dhcc.listener;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.xpand.starter.canal.annotation.CanalEventListener;
import com.xpand.starter.canal.annotation.DeleteListenPoint;
import com.xpand.starter.canal.annotation.InsertListenPoint;
import com.xpand.starter.canal.annotation.UpdateListenPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @description 监听MySQL数据库广告表的变化
 * @author zhangqi
 * @date 2020/5/20
 */
@CanalEventListener
public class MysqlListener {
    private static final Logger logger = LoggerFactory.getLogger(MysqlListener.class);

    /**
     * @description 监听插入数据操作
     * @param eventType 当前操作的类型
     * @param rowData 当前操作数据库的数据
     */
    @InsertListenPoint
    public void listenInsert(CanalEntry.EventType eventType, CanalEntry.RowData rowData) {
        logger.info("----------监听到数据库插入数据----------");
        logger.info("插入数据：");
        List<CanalEntry.Column> afterColumnsList = rowData.getAfterColumnsList();
        for (CanalEntry.Column x : afterColumnsList) {
            logger.info(x.getName() + ":" + x.getValue());
        }
    }

    /**
     * @description 监听更新数据操作
     * @param eventType 当前操作的类型
     * @param rowData 当前操作数据库的数据
     */
    @UpdateListenPoint
    public void listenUpdate(CanalEntry.EventType eventType, CanalEntry.RowData rowData) {
        logger.info("----------监听到数据库修改数据----------");
        List<CanalEntry.Column> beforeColumnsList = rowData.getBeforeColumnsList();
        logger.info("修改前的数据：");
        for (CanalEntry.Column x : beforeColumnsList) {
            logger.info(x.getName() + ":" + x.getValue());
        }
        List<CanalEntry.Column> afterColumnsList = rowData.getAfterColumnsList();
        logger.info("修改后的数据：");
        for (CanalEntry.Column x : afterColumnsList) {
            logger.info(x.getName() + ":" + x.getValue());
        }
    }

    /**
     * @description 监听更新数据操作
     * @param eventType 当前操作的类型
     * @param rowData 当前操作数据库的数据
     */
    @DeleteListenPoint
    public void listenDelete(CanalEntry.EventType eventType, CanalEntry.RowData rowData) {
        logger.info("----------监听到数据库删除数据----------");
        List<CanalEntry.Column> beforeColumnsList = rowData.getBeforeColumnsList();
        logger.info("删除的数据：");
        for (CanalEntry.Column x : beforeColumnsList) {
            logger.info(x.getName() + ":" + x.getValue());
        }
    }
}
