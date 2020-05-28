package com.dhcc.service;

import com.dhcc.entity.TbAd;
import java.util.List;

/**
 * (TbAd)表服务接口
 *
 * @author makejava
 * @since 2020-05-21 22:12:54
 */
public interface TbAdService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TbAd queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TbAd> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param tbAd 实例对象
     * @return 实例对象
     */
    TbAd insert(TbAd tbAd);

    /**
     * 修改数据
     *
     * @param tbAd 实例对象
     * @return 实例对象
     */
    TbAd update(TbAd tbAd);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}