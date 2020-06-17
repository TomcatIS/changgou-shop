package com.dhcc.search.page.service;

import com.dhcc.search.page.util.Page;

import java.util.Map;

/**
 * @author zhangqi
 * @date 2020/6/16
 */
public interface PageService {
    String searchMapToUrl(Map<String, String> searchMap);


    /**
     * @description 分页
     * @return 分页信息
     */
    Page<Object>  pagination(Map<String, Object> map);
}
