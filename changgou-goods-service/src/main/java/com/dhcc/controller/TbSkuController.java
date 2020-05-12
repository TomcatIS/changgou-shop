package com.dhcc.controller;

import com.dhcc.entity.TbSku;
import com.dhcc.service.TbSkuService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
    @GetMapping("selectOne")
    public TbSku selectOne(String id) {
        return this.tbSkuService.queryById(id);
    }

}