package com.dhcc.entity;

import java.io.Serializable;

/**
 * (TbPref)实体类
 *
 * @author makejava
 * @since 2020-05-07 23:00:42
 */
public class TbPref implements Serializable {
    private static final long serialVersionUID = -33996463631003493L;
    /**
    * ID
    */
    private Integer id;
    /**
    * 分类ID
    */
    private Integer cateId;
    /**
    * 消费金额
    */
    private Integer buyMoney;
    /**
    * 优惠金额
    */
    private Integer preMoney;
    /**
    * 活动开始日期
    */
    private Object startTime;
    /**
    * 活动截至日期
    */
    private Object endTime;
    /**
    * 类型
    */
    private String type;
    /**
    * 状态
    */
    private String state;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCateId() {
        return cateId;
    }

    public void setCateId(Integer cateId) {
        this.cateId = cateId;
    }

    public Integer getBuyMoney() {
        return buyMoney;
    }

    public void setBuyMoney(Integer buyMoney) {
        this.buyMoney = buyMoney;
    }

    public Integer getPreMoney() {
        return preMoney;
    }

    public void setPreMoney(Integer preMoney) {
        this.preMoney = preMoney;
    }

    public Object getStartTime() {
        return startTime;
    }

    public void setStartTime(Object startTime) {
        this.startTime = startTime;
    }

    public Object getEndTime() {
        return endTime;
    }

    public void setEndTime(Object endTime) {
        this.endTime = endTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}