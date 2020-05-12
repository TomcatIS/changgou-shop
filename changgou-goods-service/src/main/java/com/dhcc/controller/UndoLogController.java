package com.dhcc.controller;

import com.dhcc.entity.UndoLog;
import com.dhcc.service.UndoLogService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (UndoLog)表控制层
 *
 * @author makejava
 * @since 2020-05-07 23:00:42
 */
@RestController
@RequestMapping("undoLog")
public class UndoLogController {
    /**
     * 服务对象
     */
    @Resource
    private UndoLogService undoLogService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public UndoLog selectOne(Long id) {
        return this.undoLogService.queryById(id);
    }

}