package com.dhcc.controller;

import com.dhcc.entity.TbSku;
import com.dhcc.service.TbSkuService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商品表(TbSku)表控制层
 *
 * @author makejava
 * @since 2020-05-07 23:00:42
 */
@RestController
@RequestMapping("tbSku")
public class TbSkuController {
    /**
     * 服务对象
     */
    @Resource
    private TbSkuService tbSkuService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne/{id}")
    public TbSku selectOne(@PathVariable("id") String id) {
        return this.tbSkuService.queryById(id);
    }


    /**
     * @description 分页查询所有sku
     * @param offset 页数
     * @param limit 每页条数
     */
    @GetMapping("querySkuByLimit/{offset}/{limit}")
    public List<TbSku> queryAllByLimit(@PathVariable("offset") Integer offset, @PathVariable("limit") Integer limit) {
        return this.tbSkuService.queryAllByLimit(offset, limit);
    }

    /**
     * @description 根据spuId查询所有sku
     * @param offset 页数
     * @param limit 每页条数
     */
    @GetMapping("querySkuBySpuId/{spuId}")
    public List<TbSku> querySkuBySpuId(@PathVariable("spuId") String spuId) {
        return this.tbSkuService.listSkuBySpuId(spuId);
    }

    @PostMapping("queryAll")
    public List<TbSku> queryAll(@RequestBody TbSku tbSku) {
        return this.tbSkuService.queryAll(tbSku);
    }

}