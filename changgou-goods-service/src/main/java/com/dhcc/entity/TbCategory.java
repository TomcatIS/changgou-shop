package com.dhcc.entity;

import java.io.Serializable;

/**
 * 商品类目(TbCategory)实体类
 *
 * @author makejava
 * @since 2020-05-07 23:00:41
 */
public class TbCategory implements Serializable {
    private static final long serialVersionUID = 431367044856960680L;
    /**
    * 分类ID
    */
    private Integer id;
    /**
    * 分类名称
    */
    private String name;
    /**
    * 商品数量
    */
    private Integer goodsNum;
    /**
    * 是否显示
    */
    private String isShow;
    /**
    * 是否导航
    */
    private String isMenu;
    /**
    * 排序
    */
    private Integer seq;
    /**
    * 上级ID
    */
    private Integer parentId;
    /**
    * 模板ID
    */
    private Integer templateId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
    }

    public String getIsShow() {
        return isShow;
    }

    public void setIsShow(String isShow) {
        this.isShow = isShow;
    }

    public String getIsMenu() {
        return isMenu;
    }

    public void setIsMenu(String isMenu) {
        this.isMenu = isMenu;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

}