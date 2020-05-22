package com.dhcc.service.impl;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.dhcc.dto.CommonResult;
import com.dhcc.entity.TbAd;
import com.dhcc.enu.ProcessStatusEnum;
import com.dhcc.exception.BaseException;
import com.dhcc.service.AdCacheService;
import com.dhcc.service.AdvertisementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @description 更新redis中的缓存数据
 * @author zhangqi
 * @date 2020/5/22
 */
@Service
public class AdCacheServiceImpl implements AdCacheService {
    private static final Logger logger = LoggerFactory.getLogger(AdCacheServiceImpl.class);

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private AdvertisementService advertisementService;

    private static final String prefix = "ad_";

    @Override
    public void insert(CanalEntry.RowData rowData) {
        String id = rowData.getAfterColumns(0).getValue();
        logger.info("----------调用广告服务，获取mysql新增的数据----------");
        TbAd data = getData(id);
        logger.info("----------新增redis缓存数据----------");
        redisTemplate.opsForValue().set(prefix + data.getPosition(), data);
    }

    @Override
    public void delete(CanalEntry.RowData rowData) {
        String position = rowData.getBeforeColumns(2).getValue();
        logger.info("----------删除redis缓存中的数据");
        this.redisTemplate.delete(prefix + position);
    }

    @Override
    public void update(CanalEntry.RowData rowData) {
        String id = rowData.getAfterColumns(0).getValue();
        logger.info("----------调用广告服务，获取mysql修改的数据----------");
        TbAd data = getData(id);
        logger.info("----------更新redis缓存数据----------");
        redisTemplate.opsForValue().set(prefix + data.getPosition(), data);
    }

    /**
     * @description openfeign调用广告服务，获取Mysql中新增或修改的数据
     */
    public TbAd getData(String id) {
        CommonResult<TbAd> commonResult = advertisementService.selectOne(Integer.parseInt(id));
        TbAd data = commonResult.getData();
        if (data == null) {
            logger.info("----------获取数据失败----------");
            throw new BaseException(ProcessStatusEnum.SERVICE_TRANSFER_DATA_ERROR.getCode(),
                    ProcessStatusEnum.SERVICE_TRANSFER_DATA_ERROR.getMessage());
        }
        return data;
    }
}
