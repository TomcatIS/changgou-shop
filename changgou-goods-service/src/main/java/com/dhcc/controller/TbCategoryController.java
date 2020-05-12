package com.dhcc.controller;

import com.dhcc.dto.CommonResult;
import com.dhcc.entity.TbCategory;
import com.dhcc.service.TbCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 商品类目(TbCategory)表控制层
 *
 * @author makejava
 * @since 2020-05-07 23:00:41
 */
@RestController
@RequestMapping("tbCategory")
@Validated
@Api(tags = "商品类目API接口")
public class TbCategoryController {
    /**
     * 服务对象
     */
    @Resource
    private TbCategoryService tbCategoryService;

    /**
     * @description  根据父分类的id查询该父分类的子分类
     * @param id 商品id
     * @return 分类集合
     */
    @GetMapping("/list/categories/{id}")
    @ApiOperation(value = "查询子分类")
    @ApiImplicitParam(name = "id", value = "商品id")
    public CommonResult<List<TbCategory>> listCategoriesByPid(@PathVariable("id") @NotNull(message = "id不能为空") Integer id) {
        List<TbCategory> tbCategories = this.tbCategoryService.listCategoriesByPid(id);
        return CommonResult.success(tbCategories);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public TbCategory selectOne(Integer id) {
        return this.tbCategoryService.queryById(id);
    }

}