package com.dhcc.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dhcc.dto.CommonResult;
import com.dhcc.entity.TbSku;
import com.dhcc.enu.ProcessStatusEnum;
import com.dhcc.exception.BaseException;
import com.dhcc.search.dto.QueryDTO;
import com.dhcc.search.esdo.SkuDO;
import com.dhcc.search.feign.SkuFeign;
import com.dhcc.search.repository.SkuRepository;
import com.dhcc.search.service.SkuService;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.*;

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
    private static final String INDEX_NAME = "skuinfo";

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Autowired
    private SkuFeign skuFeign;

    @Autowired
    private SkuRepository skuRepository;


    /**
     * 创建索引库结构
     */
    @Override
    public void createMappingAndIndex() {
        logger.info("----------查询sku索引库是否存在----------");
        boolean exists = this.elasticsearchRestTemplate.indexExists(INDEX_NAME);
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
        int count = 0;
        for (SkuDO skuDO : skuDOList) {
            //将规格信息转换为map
            Map<String, Object> specMap = JSON.parseObject(skuDO.getSpec(), Map.class);
            skuDO.setSpecMap(specMap);
        }
        logger.info("----------sku信息导入索引库----------");
        for (int i = 0; i < 10; i++){
            this.skuRepository.save(skuDOList.get(i));
        }
        //this.skuMapper.saveAll(skuDOList);
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

    /**
     * 商品搜索功能
     */
    @Override
    public Map<String, Object> search(QueryDTO queryDTO) {
        AggregatedPage<SkuDO> aggregatedPage = null;
        List<String> categoryNameList = null;
        List<String> brandNameList = null;
        Map<String, Set<Object>> specMap = null;
        if (queryDTO != null) {
            // 构建根据关键词搜索条件
            String keyword = queryDTO.getKeyword();
            if (!StringUtils.isEmpty(keyword)) {
                logger.info("从es库搜索sku信息");
                try {
                    String field1 = "categoryName";
                    aggregatedPage = groupBy(keyword, field1);
                    categoryNameList = getAggregatedFields(aggregatedPage, field1);
                    String field2 = "brandName";
                    AggregatedPage<SkuDO> aggregatedPage1 = groupBy(keyword, field2);
                    brandNameList = getAggregatedFields(aggregatedPage1, field2);
                    String field3 = "spec.keyword";
                    AggregatedPage<SkuDO> aggregatedPage3 = groupBy(keyword, field3);
                    List<String> specList = getAggregatedFields(aggregatedPage3, field3);
                    // {颜色=[黑色, 蓝色, 紫色], 版本=[8GB+128GB, 3GB+32GB], 尺码=[L]}
                    specMap = getSpecMap(specList);
                } catch (Exception e) {
                    throw new BaseException("搜索商品信息失败！");
                }
            }
        }
        Map<String, Object> map = new HashMap<>(8);
        if (aggregatedPage != null) {
            map.put("totalPages", aggregatedPage.getTotalPages());
            map.put("totalElements", aggregatedPage.getTotalElements());
            //map.put("skuList", aggregatedPage.getContent());
        }
        map.put("categoryNameList", categoryNameList);
        map.put("brandNameList", brandNameList);
        map.put("specMap", specMap);
        logger.info("搜索sku信息成功！");
        return map;
    }

    /**
     * @description 分组查询
     * @param keyword 搜索关键词：“华为”
     * @param field 对哪个域进行分组：“品牌”
     */
    private AggregatedPage<SkuDO> groupBy(String keyword, String field) {
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        queryBuilder.withQuery(QueryBuilders.queryStringQuery(keyword).field("name"));
        queryBuilder.addAggregation(AggregationBuilders.terms(field).field(field));
        return elasticsearchRestTemplate.queryForPage(queryBuilder.build(), SkuDO.class);
    }

    /**
     * @description 获取分组查询的域
     * @param field 获取哪个域的分组结果
     * @return list {"手机”，“电子产品”}  {“华为”，“荣耀”}
     */
    private List<String> getAggregatedFields(AggregatedPage<SkuDO> aggregatedPage, String field) {
        ParsedStringTerms terms = aggregatedPage.getAggregations().get(field);
        List<String> list = new ArrayList<>();
        for (Terms.Bucket bucket : terms.getBuckets()) {
            list.add(bucket.getKeyAsString());
        }
        return list;
    }

    /**
     * @description 将规格转换成map
     * @param specList 规则字符串集合
     */
    private Map<String, Set<Object>> getSpecMap(List<String> specList) {
        Map<String, Set<Object>> setMap = new HashMap<>(16);
        for (String str : specList) {
            JSONObject jsonObject = JSONObject.parseObject(str);
            Map<String,Object> map = jsonObject;
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                if (setMap.containsKey(key)) {
                    setMap.get(key).add(value);
                    continue;
                }
                Set<Object> hashSet = new HashSet<>();
                hashSet.add(value);
                setMap.put(key, hashSet);
            }
        }
        return setMap;
    }
}
