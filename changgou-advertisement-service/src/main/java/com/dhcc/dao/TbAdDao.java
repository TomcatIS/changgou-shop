package com.dhcc.dao;

import com.dhcc.entity.TbAd;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (TbAd)表数据库访问层
 *
 * @author makejava
 * @since 2020-05-21 22:23:00
 */
public interface TbAdDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TbAd queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TbAd> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tbAd 实例对象
     * @return 对象列表
     */
    List<TbAd> queryAll(TbAd tbAd);

    /**
     * 新增数据
     *
     * @param tbAd 实例对象
     * @return 影响行数
     */
    int insert(TbAd tbAd);

    /**
     * 修改数据
     *
     * @param tbAd 实例对象
     * @return 影响行数
     */
    int update(TbAd tbAd);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}