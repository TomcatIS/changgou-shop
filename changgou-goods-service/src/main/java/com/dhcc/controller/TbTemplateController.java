package com.dhcc.controller;

import com.dhcc.entity.TbTemplate;
import com.dhcc.service.TbTemplateService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (TbTemplate)表控制层
 *
 * @author makejava
 * @since 2020-05-07 23:00:42
 */
@RestController
@RequestMapping("tbTemplate")
public class TbTemplateController {
    /**
     * 服务对象
     */
    @Resource
    private TbTemplateService tbTemplateService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public TbTemplate selectOne(Integer id) {
        return this.tbTemplateService.queryById(id);
    }

}