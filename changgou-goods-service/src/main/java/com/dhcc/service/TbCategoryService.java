package com.dhcc.service;

import com.dhcc.entity.TbCategory;
import java.util.List;

/**
 * 商品类目(TbCategory)表服务接口
 *
 * @author makejava
 * @since 2020-05-07 23:00:41
 */
public interface TbCategoryService {

    /**
     * @description  根据父分类的id查询该父分类的子分类
     * @param id 商品id
     * @return 分类集合
     */
    List<TbCategory> listCategoriesByPid(Integer id);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TbCategory queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TbCategory> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param tbCategory 实例对象
     * @return 实例对象
     */
    TbCategory insert(TbCategory tbCategory);

    /**
     * 修改数据
     *
     * @param tbCategory 实例对象
     * @return 实例对象
     */
    TbCategory update(TbCategory tbCategory);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}