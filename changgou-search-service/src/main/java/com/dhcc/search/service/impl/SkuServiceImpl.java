package com.dhcc.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.dhcc.dto.CommonResult;
import com.dhcc.entity.TbSku;
import com.dhcc.enu.ProcessStatusEnum;
import com.dhcc.exception.BaseException;
import com.dhcc.search.esdo.SkuDO;
import com.dhcc.search.feign.SkuFeign;
import com.dhcc.search.mapper.SkuMapper;
import com.dhcc.search.service.SkuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhangqi
 * @date 2020/5/27
 */
@Service
public class SkuServiceImpl implements SkuService {

    private static final Logger logger = LoggerFactory.getLogger(SkuServiceImpl.class);

    /**
     * 索引名称，与SkuDo的索引名称保持一致
     */
    private static final String indexName = "skuinfo";

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Autowired
    private SkuFeign skuFeign;

    @Autowired
    private SkuMapper skuMapper;

    /**
     * 创建索引库结构
     */
    @Override
    public void createMappingAndIndex() {
        logger.info("----------查询sku索引库是否存在----------");
        boolean exists = this.elasticsearchRestTemplate.indexExists(indexName);
        if (exists) {
            throw new BaseException(ProcessStatusEnum.ES_CREATE_INDEX_FAIL.getCode(),
                    "该索引库已存在，创建索引库失败！");
        }
        logger.info("----------创建es索引库----------");
        boolean a = elasticsearchRestTemplate.createIndex(SkuDO.class);
        if (!a) {
            throw new BaseException(ProcessStatusEnum.ES_CREATE_INDEX_FAIL.getCode(),
                    ProcessStatusEnum.ES_CREATE_INDEX_FAIL.getMessage());
        }
        logger.info("----------创建es索引映射----------");
        boolean b = elasticsearchRestTemplate.putMapping(SkuDO.class);
        if (!b) {
            throw new BaseException(ProcessStatusEnum.ES_CREATE_MAPPING_FAIL.getCode(),
                    ProcessStatusEnum.ES_CREATE_MAPPING_FAIL.getMessage());
        }
    }

    /**
     * 导入全部数据到es索引库
     */
    @Override
    public void importAll() {
        logger.info("----------调用goods微服务查询所有sku信息----------");
        // queryAll方法必须接收一个TbSku对象作为参数，这个new一个空对象，查询没有条件，即查询全部
        CommonResult<List<TbSku>> commonResult = this.skuFeign.queryAll(new TbSku());
        List<TbSku> skuList = commonResult.getData();
        if (skuList == null || skuList.size() <= 0) {
            throw new BaseException(ProcessStatusEnum.SERVICE_TRANSFER_DATA_ERROR.getCode(),
                    ProcessStatusEnum.SERVICE_TRANSFER_DATA_ERROR.getMessage());
        }
        logger.info("----------获取sku信息成功----------");
        String jsonString = JSON.toJSONString(skuList);
        List<SkuDO> skuDOList = JSON.parseArray(jsonString, SkuDO.class);
        this.skuMapper.saveAll(skuDOList);
    }

    /**
     * 根据spuId导入数据到es索引库
     */
    @Override
    public void importBySpuId(Long spuId) {

    }


    /**
     * 根据spuId删除es索引库中相关的sku数据
     *
     */
    @Override
    public void delDataBySpuId(String spuId) {
    }
}
