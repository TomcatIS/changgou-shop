package com.dhcc.controller;

import com.dhcc.entity.TbCategoryBrand;
import com.dhcc.service.TbCategoryBrandService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (TbCategoryBrand)表控制层
 *
 * @author makejava
 * @since 2020-05-07 23:00:41
 */
@RestController
@RequestMapping("tbCategoryBrand")
public class TbCategoryBrandController {
    /**
     * 服务对象
     */
    @Resource
    private TbCategoryBrandService tbCategoryBrandService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public TbCategoryBrand selectOne(Integer id) {
        return this.tbCategoryBrandService.queryById(id);
    }

}