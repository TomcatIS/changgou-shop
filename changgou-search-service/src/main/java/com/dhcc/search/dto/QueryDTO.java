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
     * 当前页
     */
    private Integer pageNum;

    /**
     * 每页数量
     */
    private Integer pageSize;

    /**
     * 搜索关键词
     */
    private String keyword;

    /**
     * 排序信息
     */
    private Map<String, Sort.Direction> sortMap;

    private String categoryName;

    private String brandName;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
}
