package com.dhcc.search.repository;

import com.dhcc.search.esdo.SkuDO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author zhangqi
 * @date 2020/5/27
 */
public interface SkuRepository extends ElasticsearchRepository<SkuDO, Long> {
}
