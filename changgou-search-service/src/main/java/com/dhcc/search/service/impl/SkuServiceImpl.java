package com.dhcc.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dhcc.dto.CommonResult;
import com.dhcc.entity.TbSku;
import com.dhcc.enu.ProcessStatusEnum;
import com.dhcc.exception.BaseException;
import com.dhcc.search.constant.SearchConstant;
import com.dhcc.search.esdo.SkuDO;
import com.dhcc.search.feign.SkuFeign;
import com.dhcc.search.repository.SkuRepository;
import com.dhcc.search.service.SkuService;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
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
        logger.info("----------sku信息开始导入索引库----------");
        for (int i = 100; i < 200; i++) {
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
     */
    @Override
    public void delDataBySpuId(String spuId) {
    }

    /**
     * 商品搜索功能
     */
    @Override
    public Map<String, Object> search(Map<String, String> searchMap) {
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        Map<String, Object> map = new HashMap<>(16);
        logger.info("从es库搜索sku信息");
        // 根据关键词按sku名称(name)搜索
        String keyword = searchMap.get("keyword");
        if (!StringUtils.isEmpty(keyword)) {
            //boolQueryBuilder.must(QueryBuilders.matchQuery("name", keyword).operator(Operator.AND));
            boolQueryBuilder.must(QueryBuilders.termQuery("name", keyword));
        }
       // 分类搜索
        String categoryName = searchMap.get(SearchConstant.FIELD_CATEGORY_NAME);
        if (!StringUtils.isEmpty(categoryName)) {
            boolQueryBuilder.filter(QueryBuilders.termQuery(SearchConstant.FIELD_CATEGORY_NAME, categoryName));
        } else {
            addAggregation(queryBuilder, SearchConstant.FIELD_CATEGORY_NAME);
        }
        // 品牌搜索
        String brandName = searchMap.get(SearchConstant.FIELD_BRAND_NAME);
        if (!StringUtils.isEmpty(brandName)) {
            boolQueryBuilder.filter(QueryBuilders.termQuery(SearchConstant.FIELD_BRAND_NAME, brandName));
        } else {
            addAggregation(queryBuilder, SearchConstant.FIELD_BRAND_NAME);
        }
        // 规格搜索
        for (Map.Entry<String, String> entry : searchMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (key.startsWith("spec_")) {
                boolQueryBuilder.filter(QueryBuilders.termQuery("specMap." + key.substring(5) + ".keyword", value));
            }
        }
        addAggregation(queryBuilder, SearchConstant.FIELD_SPEC);
        // 价格搜索
        String price = searchMap.get(SearchConstant.FIELD_PRICE);
        if (!StringUtils.isEmpty(price)) {
            price = price.replace("元", "").replace("以上", "");
            String[] split = price.split("-");
            if (split.length > 0) {
                boolQueryBuilder.filter(QueryBuilders.rangeQuery(SearchConstant.FIELD_PRICE).gte(split[0]));
            }
            if (split.length == 2) {
                boolQueryBuilder.filter(QueryBuilders.rangeQuery(SearchConstant.FIELD_PRICE).lt(split[1]));
            }
        }
        // 排序
        String sortField = searchMap.get("sortField");
        String sortRule = searchMap.get("sortRule");
        if ((!StringUtils.isEmpty(sortField)) && (!StringUtils.isEmpty(sortRule))) {
            queryBuilder.withSort(new FieldSortBuilder(sortField).order(SortOrder.valueOf(sortRule)));
        }

        // 分页搜索
        String pageNum = searchMap.get("pageNum");
        int i = convertPageNum(pageNum);
        queryBuilder.withPageable(PageRequest.of(i - 1, SearchConstant.PAGE_SIZE));

        // 高亮显示
        // 高亮配置
        HighlightBuilder.Field highlightField= new HighlightBuilder.Field("name");
        highlightField.preTags("<em style=\"color:red;\">");
        highlightField.postTags("</em>");
        // 关键词长度
        highlightField.fragmentSize(50);
        queryBuilder.withHighlightFields(highlightField);
        queryBuilder.withQuery(boolQueryBuilder);
        // SearchResultMapper：封装了搜索的对象
        AggregatedPage<SkuDO> aggregatedPage = elasticsearchRestTemplate.queryForPage(queryBuilder.build(), SkuDO.class, new SearchResultMapper() {
            @Override
            public <T> AggregatedPage<T> mapResults(SearchResponse searchResponse, Class<T> aClass, Pageable pageable) {
                List<SkuDO> list = new ArrayList<>();
                // 获取结果集
                for (SearchHit hit: searchResponse.getHits()) {
                    SkuDO skuDO = JSON.parseObject(hit.getSourceAsString(), SkuDO.class);
                    // 获取高亮对象
                    HighlightField highLightName = hit.getHighlightFields().get("name");
                    if (highLightName != null && highLightName.getFragments() != null) {
                        StringBuilder builder = new StringBuilder("");
                        for (Text fragment : highLightName.getFragments()) {
                            builder.append(fragment);
                        }
                        // 高亮替换非高亮
                        skuDO.setName(builder.toString());
                    }
                    list.add(skuDO);
                }
                return new AggregatedPageImpl<>((List<T>) list, pageable, searchResponse.getHits().getTotalHits(), searchResponse.getAggregations());
            }
            @Override
            public <T> T mapSearchHit(SearchHit searchHit, Class<T> aClass) {
                return null;
            }
        });
        List<String> categoryNameList = getAggregatedFields(aggregatedPage, SearchConstant.FIELD_CATEGORY_NAME);
        List<String> brandNameList = getAggregatedFields(aggregatedPage, SearchConstant.FIELD_BRAND_NAME);
        //
        List<String> specList = getAggregatedFields(aggregatedPage, SearchConstant.FIELD_SPEC);
        // {颜色=[黑色, 蓝色, 紫色], 版本=[8GB+128GB, 3GB+32GB], 尺码=[L]}
        Map<String, Set<Object>> specMap = getSpecMap(specList);
        map.put("categoryNameList", categoryNameList);
        map.put("skuList", aggregatedPage.getContent());
        map.put("brandNameList", brandNameList);
        map.put("specMap", specMap);
        // 分页参数
        NativeSearchQuery nativeSearchQuery = queryBuilder.build();
        Pageable pageable = nativeSearchQuery.getPageable();
        map.put("pageNum", pageable.getPageNumber());
        map.put("totalPages", aggregatedPage.getTotalPages());
        map.put("totalElements", aggregatedPage.getTotalElements());
        map.put("pageSize", SearchConstant.PAGE_SIZE);
        logger.info("搜索sku信息成功！");
        return map;
    }

    private void addAggregation(NativeSearchQueryBuilder queryBuilder, String field) {
        queryBuilder.addAggregation(AggregationBuilders.terms(field).field(field).size(SearchConstant.ES_SIZE));
        //queryBuilder.addAggregation(AggregationBuilders.terms(field).field(field));
    }


    /**
     * @param field 获取哪个域的分组结果
     * @return list {"手机”，“电子产品”}  {“华为”，“荣耀”}
     * @description 获取分组查询的域
     */
    private List<String> getAggregatedFields(AggregatedPage<SkuDO> aggregatedPage, String field) {
        if (aggregatedPage == null) {
            return null;
        }
        Aggregations aggregations = aggregatedPage.getAggregations();
        if (aggregations == null) {
            return null;
        }
        ParsedStringTerms terms = aggregations.get(field);
        if (terms == null) {
            return null;
        }
        List<String> list = new ArrayList<>();
        for (Terms.Bucket bucket : terms.getBuckets()) {
            list.add(bucket.getKeyAsString());
        }
        return list;
    }

    /**
     * @param specList 规则字符串集合
     * @description 将规格转换成map
     */
    private Map<String, Set<Object>> getSpecMap(List<String> specList) {
        if (specList == null) {
            return null;
        }
        Map<String, Set<Object>> setMap = new HashMap<>(16);
        for (String str : specList) {
            JSONObject jsonObject = JSONObject.parseObject(str);
            Map<String, Object> map = jsonObject;
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

    private int convertPageNum(String pageNum) {
        int i;
        try {
            i = Integer.parseInt(pageNum);
        } catch (NumberFormatException e) {
            return 1;
        }
        if (i <= 0) {
            return 1;
        }
        return i;
    }
}
