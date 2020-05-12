package com.dhcc.dao;

import com.dhcc.entity.TbPara;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (TbPara)表数据库访问层
 *
 * @author makejava
 * @since 2020-05-07 23:00:41
 */
public interface TbParaDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TbPara queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TbPara> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tbPara 实例对象
     * @return 对象列表
     */
    List<TbPara> queryAll(TbPara tbPara);

    /**
     * 新增数据
     *
     * @param tbPara 实例对象
     * @return 影响行数
     */
    int insert(TbPara tbPara);

    /**
     * 修改数据
     *
     * @param tbPara 实例对象
     * @return 影响行数
     */
    int update(TbPara tbPara);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}