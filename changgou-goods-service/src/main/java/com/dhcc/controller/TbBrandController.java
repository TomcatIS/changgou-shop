package com.dhcc.controller;

import com.dhcc.entity.TbBrand;
import com.dhcc.service.TbBrandService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 品牌表(TbBrand)表控制层
 *
 * @author makejava
 * @since 2020-05-07 23:00:41
 */
@RestController
@RequestMapping("tbBrand")
public class TbBrandController {
    /**
     * 服务对象
     */
    @Resource
    private TbBrandService tbBrandService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public TbBrand selectOne(Integer id) {
        return this.tbBrandService.queryById(id);
    }

}