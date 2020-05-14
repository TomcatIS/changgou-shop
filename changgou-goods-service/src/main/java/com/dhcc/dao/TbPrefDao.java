package com.dhcc.dao;

import com.dhcc.entity.TbPref;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (TbPref)表数据库访问层
 *
 * @author makejava
 * @since 2020-05-07 23:00:42
 */
public interface TbPrefDao {


    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TbPref queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TbPref> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tbPref 实例对象
     * @return 对象列表
     */
    List<TbPref> queryAll(TbPref tbPref);

    /**
     * 新增数据
     *
     * @param tbPref 实例对象
     * @return 影响行数
     */
    int insert(TbPref tbPref);

    /**
     * 修改数据
     *
     * @param tbPref 实例对象
     * @return 影响行数
     */
    int update(TbPref tbPref);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}