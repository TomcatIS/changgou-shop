package com.dhcc.controller;

import com.dhcc.entity.TbPara;
import com.dhcc.service.TbParaService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (TbPara)表控制层
 *
 * @author makejava
 * @since 2020-05-07 23:00:41
 */
@RestController
@RequestMapping("tbPara")
public class TbParaController {
    /**
     * 服务对象
     */
    @Resource
    private TbParaService tbParaService;

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