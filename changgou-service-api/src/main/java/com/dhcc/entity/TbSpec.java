package com.dhcc.entity;

import java.io.Serializable;

/**
 * (TbSpec)实体类
 *
 * @author makejava
 * @since 2020-05-07 10:33:04
 */
public class TbSpec implements Serializable {
    private static final long serialVersionUID = 547859692928083196L;
    /**
    * ID
    */
    private Integer id;
    /**
    * 名称
    */
    private String name;
    /**
    * 规格选项
    */
    private String options;
    /**
    * 排序
    */
    private Integer seq;
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

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

}