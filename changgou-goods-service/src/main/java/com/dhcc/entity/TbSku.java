package com.dhcc.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 商品表(TbSku)实体类
 *
 * @author makejava
 * @since 2020-05-07 23:00:42
 */
public class TbSku implements Serializable {
    private static final long serialVersionUID = -61239837998213355L;
    /**
    * 商品id
    */
    private String id;
    /**
    * 商品条码
    */
    private String sn;
    /**
    * SKU名称
    */
    private String name;
    /**
    * 价格（分）
    */
    private Integer price;
    /**
    * 库存数量
    */
    private Integer num;
    /**
    * 库存预警数量
    */
    private Integer alertNum;
    /**
    * 商品图片
    */
    private String image;
    /**
    * 商品图片列表
    */
    private String images;
    /**
    * 重量（克）
    */
    private Integer weight;
    /**
    * 创建时间
    */
    private Date createTime;
    /**
    * 更新时间
    */
    private Date updateTime;
    /**
    * SPUID
    */
    private String spuId;
    /**
    * 类目ID
    */
    private Integer categoryId;
    /**
    * 类目名称
    */
    private String categoryName;
    /**
    * 品牌名称
    */
    private String brandName;
    /**
    * 规格
    */
    private String spec;
    /**
    * 销量
    */
    private Integer saleNum;
    /**
    * 评论数
    */
    private Integer commentNum;
    /**
    * 商品状态 1-正常，2-下架，3-删除
    */
    private String status;
    
    private Integer version;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getAlertNum() {
        return alertNum;
    }

    public void setAlertNum(Integer alertNum) {
        this.alertNum = alertNum;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getSpuId() {
        return spuId;
    }

    public void setSpuId(String spuId) {
        this.spuId = spuId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
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

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public Integer getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(Integer saleNum) {
        this.saleNum = saleNum;
    }

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

}