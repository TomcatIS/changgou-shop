package com.dhcc.search.controller;

import com.dhcc.dto.CommonResult;
import com.dhcc.search.service.SkuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangqi
 * @date 2020/5/28
 */
@RestController
@RequestMapping("es")
@Api(tags = "搜索服务API接口文档")
public class SkuController {
    @Autowired
    private SkuService skuService;

    @GetMapping("/index/create")
    @ApiOperation(value = "创建sku索引库")
    public CommonResult<Object> createIndexAndMapping() {
        this.skuService.createMappingAndIndex();
        return CommonResult.success();
    }

    @GetMapping("/sku/importAll")
    @ApiOperation(value = "导入所有sku信息到es索引库")
    public CommonResult<Object> importAll() {
        this.skuService.importAll();
        return CommonResult.success();
    }
}
