package com.dhcc.search.esdo;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * @author zhangqi
 * @date 2020/5/27
 */
@Document(indexName = "skuinfo", //索引名
          type = "_doc"
        )
public class SkuDO implements Serializable {
    /**
     * 商品id
     */
    @Id
    @Field(index = true, store = true, type = FieldType.Keyword)
    private String id;

    /**
     * SKU名称
     * searchAnalyzer：搜索词使用的分词器
     */
    @Field(index = true, store = true, type = FieldType.Text, analyzer = "ik_smart" ,searchAnalyzer = "ik_smart")
    private String name;
    /**
     * 价格（分）
     */
    @Field(index = true, store = true, type = FieldType.Double)
    private Integer price;
    /**
     * 库存数量
     */
    @Field(index = true, store = true, type = FieldType.Integer)
    private Integer num;
    /**
     * 库存预警数量
     */
    @Field(index = true, store = true, type = FieldType.Integer)
    private Integer alertNum;
    /**
     * 商品图片
     */
    @Field(index = false, store = true, type = FieldType.Text)
    private String image;

    /**
     * 重量（克）
     */
    @Field(index = true, store = true, type = FieldType.Double)
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
    @Field(index = true, store = true, type = FieldType.Long)
    private String spuId;
    /**
     * 类目ID
     */
    @Field(index = true, store = true, type = FieldType.Long)
    private Integer categoryId;
    /**
     * 类目名称
     */
    @Field(index = true, store = true, type = FieldType.Keyword)
    private String categoryName;
    /**
     * 品牌名称
     */
    @Field(index = true, store = true, type = FieldType.Keyword)
    private String brandName;

    /**
     * 规格
     */
    private String spec;

    /**
     * 规格
     */
    private Map<String, Object> specMap;
    /**
     * 销量
     */
    @Field(index = true, store = true, type = FieldType.Integer)
    private Integer saleNum;
    /**
     * 评论数
     */
    @Field(index = true, store = true, type = FieldType.Integer)
    private Integer commentNum;
    /**
     * 商品状态 1-正常，2-下架，3-删除
     */
    @Field(index = true, store = true, type = FieldType.Keyword)
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Map<String, Object> getSpecMap() {
        return specMap;
    }

    public void setSpecMap(Map<String, Object> specMap) {
        this.specMap = specMap;
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

    @Override
    public String toString() {
        return "SkuDO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", num=" + num +
                ", alertNum=" + alertNum +
                ", image='" + image + '\'' +
                ", weight=" + weight +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", spuId='" + spuId + '\'' +
                ", categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", brandName='" + brandName + '\'' +
                ", specMap=" + specMap +
                ", saleNum=" + saleNum +
                ", commentNum=" + commentNum +
                ", status='" + status + '\'' +
                '}';
    }
}
