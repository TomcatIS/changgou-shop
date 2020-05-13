package com.dhcc.controller;

import com.dhcc.dto.Goods;
import com.dhcc.entity.TbSpu;
import com.dhcc.exception.BaseException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description 商品控制层
 * @author zhangqi
 * @date 2020/5/13
 */
@RestController
@RequestMapping("goods")
public class GoodsController {


    /**
     * @description 新增商品
     */
    @PostMapping("add")
    public void addGoods(@RequestBody Goods goods) {
        if (goods.getTbSpu() == null || goods.getSkuList() == null) {
            throw new BaseException("商品不能为空！");
        }
        TbSpu spu = goods.getTbSpu();

    }

}
