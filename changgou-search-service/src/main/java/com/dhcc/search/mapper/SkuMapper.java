package com.dhcc.search.mapper;

import com.dhcc.search.esdo.SkuDO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author zhangqi
 * @date 2020/5/27
 */
public interface SkuMapper extends ElasticsearchRepository<SkuDO, Integer> {
}
