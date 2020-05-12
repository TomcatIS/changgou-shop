package com.dhcc.entity;

import java.io.Serializable;

/**
 * (TbPara)实体类
 *
 * @author makejava
 * @since 2020-05-07 23:00:41
 */
public class TbPara implements Serializable {
    private static final long serialVersionUID = 618913642119020856L;
    /**
    * id
    */
    private Integer id;
    /**
    * 名称
    */
    private String name;
    /**
    * 选项
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