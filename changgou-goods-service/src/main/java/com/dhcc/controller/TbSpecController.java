package com.dhcc.controller;

import com.dhcc.entity.TbPref;
import com.dhcc.entity.TbSpec;
import com.dhcc.exception.BaseException;
import com.dhcc.service.TbSpecService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

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

    @GetMapping("/list/{id}")
    public List<TbPref> listPrecsByCategory(@PathVariable("id") @NotNull(message = "分类id不能为空!") Integer id) {
        return this.tbSpecService.listPrecsByCategory(id);
    }

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