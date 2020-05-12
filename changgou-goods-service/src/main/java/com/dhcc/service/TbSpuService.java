package com.dhcc.service;

import com.dhcc.entity.TbSpu;
import java.util.List;

/**
 * (TbSpu)表服务接口
 *
 * @author makejava
 * @since 2020-05-07 23:00:42
 */
public interface TbSpuService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TbSpu queryById(String id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TbSpu> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param tbSpu 实例对象
     * @return 实例对象
     */
    TbSpu insert(TbSpu tbSpu);

    /**
     * 修改数据
     *
     * @param tbSpu 实例对象
     * @return 实例对象
     */
    TbSpu update(TbSpu tbSpu);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

}