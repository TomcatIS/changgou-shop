package com.dhcc.service.impl;

import com.dhcc.entity.TbCategory;
import com.dhcc.dao.TbCategoryDao;
import com.dhcc.exception.BaseException;
import com.dhcc.service.TbCategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商品类目(TbCategory)表服务实现类
 *
 * @author makejava
 * @since 2020-05-07 23:00:41
 */
@Service("tbCategoryService")
public class TbCategoryServiceImpl implements TbCategoryService {
    @Resource
    private TbCategoryDao tbCategoryDao;

    /**
     * @description  根据父分类的id查询该父分类的子分类
     * @param id 商品id
     * @return 分类集合
     */
    @Override
    public List<TbCategory> listCategoriesByPid(Integer id) {
        return this.tbCategoryDao.listCategoriesByPid(id);
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TbCategory queryById(Integer id) {
        return this.tbCategoryDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<TbCategory> queryAllByLimit(int offset, int limit) {
        return this.tbCategoryDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tbCategory 实例对象
     * @return 实例对象
     */
    @Override
    public TbCategory insert(TbCategory tbCategory) {
        this.tbCategoryDao.insert(tbCategory);
        return tbCategory;
    }

    /**
     * 修改数据
     *
     * @param tbCategory 实例对象
     * @return 实例对象
     */
    @Override
    public TbCategory update(TbCategory tbCategory) {
        this.tbCategoryDao.update(tbCategory);
        return this.queryById(tbCategory.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.tbCategoryDao.deleteById(id) > 0;
    }
}