package com.dhcc.controller;

import com.dhcc.dto.Goods;
import com.dhcc.exception.BaseException;
import com.dhcc.service.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * @description 商品控制层
 * @author zhangqi
 * @date 2020/5/13
 */
@RestController
@RequestMapping("goods")
@Api(tags = "商品API接口")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @PostMapping("/add")
    @ApiOperation("新增商品")
    public String addGoods(@RequestBody Goods goods) {
        if (goods.getTbSpu() == null || goods.getSkuList() == null) {
            throw new BaseException("商品不能为空！");
        }
        goodsService.addGoods(goods);
        return "商品添加成功";
    }

    @GetMapping("/list/{id}")
    @ApiOperation("根据spuId查询商品")
    @ApiImplicitParam(name = "id", value = "spuId", required = true)
    public Goods listGoodsBySpuId(@PathVariable("id") @NotNull(message = "spuId不能为空!") String id) {
        return this.goodsService.listGoodsBySpuId(id);
    }

    @PutMapping("/update")
    @ApiOperation("修改商品")
    public String updateGoods(@RequestBody Goods goods) {
        if (goods.getTbSpu() == null || goods.getSkuList() == null) {
            throw new BaseException("商品不能为空！");
        }
        this.goodsService.updateGoods(goods);
        return "修改成功！";
    }

    @PutMapping("/audit/{id}")
    @ApiOperation("商品审核")
    @ApiImplicitParam(name = "id", value = "spuId", required = true)
    public String audit(@PathVariable("id") @NotNull(message = "spuId不能为空!") String id) {
        this.goodsService.audit(id);
        return "审核通过！";
    }

    @PutMapping("/put/{id}")
    @ApiOperation("商品上架")
    @ApiImplicitParam(name = "id", value = "spuId", required = true)
    public String put(@PathVariable("id") @NotNull(message = "spuId不能为空!") String id) {
        this.goodsService.put(id);
        return "上架成功！";
    }

    @PutMapping("/pull/{id}")
    @ApiOperation("商品下架")
    @ApiImplicitParam(name = "id", value = "spuId", required = true)
    public String pull(@PathVariable("id") @NotNull(message = "spuId不能为空!") String id) {
        this.goodsService.pull(id);
        return "下架成功！";
    }

    @PutMapping("/delete/{id}")
    @ApiOperation("逻辑删除商品")
    public String delete(@PathVariable("id") @NotNull(message = "spuId不能为空!") String id) {
        this.goodsService.delete(id);
        return "商品已删除！";
    }


    @PutMapping("/restore/{id}")
    @ApiOperation("还原已删除的商品")
    public String restore(@PathVariable("id") @NotNull(message = "spuId不能为空!") String id) {
        this.goodsService.restore(id);
        return "商品已还原！";
    }

    @PutMapping("/realDelete/{id}")
    @ApiOperation("物理删除商品")
    public String realDelete(@PathVariable("id") @NotNull(message = "spuId不能为空!") String id) {
        this.goodsService.realDelete(id);
        return "商品已删除！";
    }
}
