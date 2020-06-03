package com.dhcc.search.dto;


import org.springframework.data.domain.Sort;

import java.util.Map;

/**
 * @description 搜索信息封装类
 * @author zhangqi
 * @date 2020/6/1
 */
public class QueryDTO {
    /**
     * 第几页
     */
    private Integer page;

    /**
     * 每页数量
     */
    private Integer size;

    /**
     * 搜索关键词
     */
    private String keyword;

    /**
     * 排序信息
     */
    private Map<String, Sort.Direction> sortMap;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Map<String, Sort.Direction> getSortMap() {
        return sortMap;
    }

    public void setSortMap(Map<String, Sort.Direction> sortMap) {
        this.sortMap = sortMap;
    }

    @Override
    public String toString() {
        return "QueryDTO{" +
                "page=" + page +
                ", size=" + size +
                ", keyword='" + keyword + '\'' +
                ", sortMap=" + sortMap +
                '}';
    }
}
