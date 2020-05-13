package com.dhcc.controller;

import com.dhcc.dto.CommonResult;
import com.dhcc.entity.TbBrand;
import com.dhcc.exception.BaseException;
import com.dhcc.service.TbBrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 品牌表(TbBrand)表控制层
 *
 * @author makejava
 * @since 2020-05-07 23:00:41
 */
@RestController
@RequestMapping("tbBrand")
@Api(tags = "品牌API接口")
@Validated
public class TbBrandController {
    /**
     * 服务对象
     */
    @Resource
    private TbBrandService tbBrandService;

    /**
     * @description 根据分类id查询对应的品牌名称
     */
    @ApiOperation(value = "查询品牌名称")
    @ApiImplicitParam(name = "id", value = "分类id", required = true)
    @GetMapping("list/brandsName/{id}")
    public List<String> listBrandsByCategoryId(@PathVariable("id") @NotNull(message = "分类id不能为空") Integer id) {
        return this.tbBrandService.listBrandsByCategoryId(id);
    }

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