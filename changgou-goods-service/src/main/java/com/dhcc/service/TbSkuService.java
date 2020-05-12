package com.dhcc.service;

import com.dhcc.entity.TbSku;
import java.util.List;

/**
 * 商品表(TbSku)表服务接口
 *
 * @author makejava
 * @since 2020-05-07 23:00:42
 */
public interface TbSkuService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TbSku queryById(String id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TbSku> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param tbSku 实例对象
     * @return 实例对象
     */
    TbSku insert(TbSku tbSku);

    /**
     * 修改数据
     *
     * @param tbSku 实例对象
     * @return 实例对象
     */
    TbSku update(TbSku tbSku);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

}