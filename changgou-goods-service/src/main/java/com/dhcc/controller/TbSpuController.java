package com.dhcc.controller;

import com.dhcc.entity.TbSpu;
import com.dhcc.service.TbSpuService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (TbSpu)表控制层
 *
 * @author makejava
 * @since 2020-05-07 23:00:42
 */
@RestController
@RequestMapping("tbSpu")
public class TbSpuController {
    /**
     * 服务对象
     */
    @Resource
    private TbSpuService tbSpuService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public TbSpu selectOne(String id) {
        return this.tbSpuService.queryById(id);
    }

}