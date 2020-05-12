package com.dhcc.controller;

import com.dhcc.entity.TbSpec;
import com.dhcc.service.TbSpecService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (TbSpec)表控制层
 *
 * @author makejava
 * @since 2020-05-07 23:00:42
 */
@RestController
@RequestMapping("tbSpec")
public class TbSpecController {
    /**
     * 服务对象
     */
    @Resource
    private TbSpecService tbSpecService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public TbSpec selectOne(Integer id) {
        return this.tbSpecService.queryById(id);
    }

}