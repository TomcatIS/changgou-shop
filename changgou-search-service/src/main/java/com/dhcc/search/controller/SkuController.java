package com.dhcc.search.controller;

import com.dhcc.dto.CommonResult;
import com.dhcc.search.dto.QueryDTO;
import com.dhcc.search.service.SkuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author zhangqi
 * @date 2020/5/28
 */
@RestController
@RequestMapping("es")
@Api(tags = "搜索服务API接口文档")
public class SkuController {
    private static final Logger logger = LoggerFactory.getLogger(SkuController.class);

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
        logger.info("----------sku信息导入成功----------");
        return CommonResult.success();
    }

    /**
     * 根据条件搜索sku
     * @param searchMap 搜索条件
     */
    @ApiOperation(value = "商品查询")
    @GetMapping("sku/search")
    public Map<String, Object> search(QueryDTO queryDTO) {
        System.out.println(queryDTO.toString());
        return this.skuService.search(queryDTO);
    }
}
