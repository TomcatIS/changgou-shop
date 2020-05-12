package com.dhcc.entity;

import java.io.Serializable;

/**
 * 品牌表(TbBrand)实体类
 *
 * @author makejava
 * @since 2020-05-07 23:00:41
 */
public class TbBrand implements Serializable {
    private static final long serialVersionUID = 393911645659828373L;
    /**
    * 品牌id
    */
    private Integer id;
    /**
    * 品牌名称
    */
    private String name;
    /**
    * 品牌图片地址
    */
    private String image;
    /**
    * 品牌的首字母
    */
    private String letter;
    /**
    * 排序
    */
    private Integer seq;


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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

}