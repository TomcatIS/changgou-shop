package com.dhcc.dto;

import com.dhcc.entity.TbSku;
import com.dhcc.entity.TbSpu;

import java.io.Serializable;
import java.util.List;

/**
 * @description 将spu和sku集合封装成一个goods
 * @author zhangqi
 * @date 2020/5/13
 */
public class Goods implements Serializable {
    private TbSpu tbSpu;
    private List<TbSku> skuList;

    public TbSpu getTbSpu() {
        return tbSpu;
    }

    public void setTbSpu(TbSpu tbSpu) {
        this.tbSpu = tbSpu;
    }

    public List<TbSku> getSkuList() {
        return skuList;
    }

    public void setSkuList(List<TbSku> skuList) {
        this.skuList = skuList;
    }
}
