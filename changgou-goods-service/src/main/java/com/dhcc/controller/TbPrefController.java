package com.dhcc.controller;

import com.dhcc.entity.TbPref;
import com.dhcc.exception.BaseException;
import com.dhcc.service.TbPrefService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * (TbPref)表控制层
 *
 * @author makejava
 * @since 2020-05-07 23:00:42
 */
@RestController
@RequestMapping("tbPref")
@Validated
public class TbPrefController {
    /**
     * 服务对象
     */
    @Resource
    private TbPrefService tbPrefService;


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public TbPref selectOne(Integer id) {
        return this.tbPrefService.queryById(id);
    }

}