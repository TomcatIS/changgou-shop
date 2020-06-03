package com.dhcc.search.service;

import com.dhcc.dto.CommonResult;
import com.dhcc.search.dto.QueryDTO;

import java.util.Map;

/**
 * @author zhangqi
 * @date 2020/5/27
 */
public interface SkuService {

    /**
     * 创建索引库结构
     */
    void createMappingAndIndex();

    /**
     * 导入全部数据到es索引库
     */
    void importAll();

    /**
     * 根据spuId导入数据到es索引库
     */
    void importBySpuId(Long spuId);


    /**
     * 根据spuId删除es索引库中相关的sku数据
     */
    void delDataBySpuId(String spuId);

    Map<String, Object> search(QueryDTO queryDTO);
}
