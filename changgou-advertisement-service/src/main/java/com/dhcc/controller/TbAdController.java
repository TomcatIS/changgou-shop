package com.dhcc.controller;

import com.dhcc.entity.TbAd;
import com.dhcc.service.TbAdService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (TbAd)表控制层
 *
 * @author makejava
 * @since 2020-05-21 22:23:09
 */
@RestController
@RequestMapping("tbAd")
public class TbAdController {
    /**
     * 服务对象
     */
    @Resource
    private TbAdService tbAdService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/selectOne/{id}")
    public TbAd selectOne(@PathVariable("id") Integer id) {
        return this.tbAdService.queryById(id);
    }
}