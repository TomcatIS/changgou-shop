package com.dhcc.service;

import com.dhcc.entity.TbPref;
import com.dhcc.entity.TbSpec;
import java.util.List;

/**
 * (TbSpec)表服务接口
 *
 * @author makejava
 * @since 2020-05-07 23:00:42
 */
public interface TbSpecService {

    List<TbPref> listPrecsByCategory(Integer id);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TbSpec queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TbSpec> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param tbSpec 实例对象
     * @return 实例对象
     */
    TbSpec insert(TbSpec tbSpec);

    /**
     * 修改数据
     *
     * @param tbSpec 实例对象
     * @return 实例对象
     */
    TbSpec update(TbSpec tbSpec);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}