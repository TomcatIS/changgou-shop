package com.dhcc.entity;

import java.io.Serializable;

/**
 * (TbTemplate)实体类
 *
 * @author makejava
 * @since 2020-05-07 10:33:04
 */
public class TbTemplate implements Serializable {
    private static final long serialVersionUID = 612741432235485295L;
    /**
    * ID
    */
    private Integer id;
    /**
    * 模板名称
    */
    private String name;
    /**
    * 规格数量
    */
    private Integer specNum;
    /**
    * 参数数量
    */
    private Integer paraNum;


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

    public Integer getSpecNum() {
        return specNum;
    }

    public void setSpecNum(Integer specNum) {
        this.specNum = specNum;
    }

    public Integer getParaNum() {
        return paraNum;
    }

    public void setParaNum(Integer paraNum) {
        this.paraNum = paraNum;
    }

}