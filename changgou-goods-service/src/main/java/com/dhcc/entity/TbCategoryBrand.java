package com.dhcc.entity;

import java.io.Serializable;

/**
 * (TbCategoryBrand)实体类
 *
 * @author makejava
 * @since 2020-05-07 23:00:41
 */
public class TbCategoryBrand implements Serializable {
    private static final long serialVersionUID = 700850990568458304L;
    /**
    * 分类ID
    */
    private Integer categoryId;
    /**
    * 品牌ID
    */
    private Integer brandId;


    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

}