package com.dhcc.dao;

import com.dhcc.entity.TbCategory;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 商品类目(TbCategory)表数据库访问层
 *
 * @author makejava
 * @since 2020-05-07 23:00:41
 */
public interface TbCategoryDao {


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
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TbCategory> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tbCategory 实例对象
     * @return 对象列表
     */
    List<TbCategory> queryAll(TbCategory tbCategory);

    /**
     * 新增数据
     *
     * @param tbCategory 实例对象
     * @return 影响行数
     */
    int insert(TbCategory tbCategory);

    /**
     * 修改数据
     *
     * @param tbCategory 实例对象
     * @return 影响行数
     */
    int update(TbCategory tbCategory);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}