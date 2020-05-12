package com.dhcc.service;

import com.dhcc.entity.TbCategoryBrand;
import java.util.List;

/**
 * (TbCategoryBrand)表服务接口
 *
 * @author makejava
 * @since 2020-05-07 23:00:41
 */
public interface TbCategoryBrandService {

    /**
     * 通过ID查询单条数据
     *
     * @param categoryId 主键
     * @return 实例对象
     */
    TbCategoryBrand queryById(Integer categoryId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TbCategoryBrand> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param tbCategoryBrand 实例对象
     * @return 实例对象
     */
    TbCategoryBrand insert(TbCategoryBrand tbCategoryBrand);

    /**
     * 修改数据
     *
     * @param tbCategoryBrand 实例对象
     * @return 实例对象
     */
    TbCategoryBrand update(TbCategoryBrand tbCategoryBrand);

    /**
     * 通过主键删除数据
     *
     * @param categoryId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer categoryId);

}