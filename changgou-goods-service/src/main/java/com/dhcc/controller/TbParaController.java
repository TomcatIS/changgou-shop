package com.dhcc.controller;

import com.dhcc.entity.TbPara;
import com.dhcc.exception.BaseException;
import com.dhcc.service.TbParaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * (TbPara)表控制层
 *
 * @author makejava
 * @since 2020-05-07 23:00:41
 */
@RestController
@RequestMapping("tbPara")
@Api(tags = "参数API接口")
@Validated
public class TbParaController {


    /**
     * 服务对象
     */
    @Resource
    private TbParaService tbParaService;

    @ApiOperation("根据分类查询参数")
    @GetMapping("/list/params/{id}")
    public List<TbPara> listParamsByCategory(@PathVariable("id") @NotNull(message = "分类id不能为空!") Integer id) {
        return this.tbParaService.listParamsByCategory(id);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public TbPara selectOne(Integer id) {
        return this.tbParaService.queryById(id);
    }

}