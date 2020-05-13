package com.dhcc.controller;

import com.dhcc.dto.CommonResult;
import com.dhcc.entity.TbAlbum;
import com.dhcc.service.TbAlbumService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

/**
 * (TbAlbum)表控制层
 *
 * @author makejava
 * @since 2020-05-07 10:29:46
 */
@RestController
@RequestMapping("tbAlbum")
@Validated
public class TbAlbumController {
    /**
     * 服务对象
     */
    @Resource
    private TbAlbumService tbAlbumService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public TbAlbum selectOne(@NotNull(message = "id不能为空") Long id) {
        return this.tbAlbumService.queryById(id);
    }
}