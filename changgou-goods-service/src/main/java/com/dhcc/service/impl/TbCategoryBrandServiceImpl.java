package com.dhcc.service.impl;

import com.dhcc.entity.TbCategoryBrand;
import com.dhcc.dao.TbCategoryBrandDao;
import com.dhcc.service.TbCategoryBrandService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TbCategoryBrand)表服务实现类
 *
 * @author makejava
 * @since 2020-05-07 23:00:41
 */
@Service("tbCategoryBrandService")
public class TbCategoryBrandServiceImpl implements TbCategoryBrandService {
    @Resource
    private TbCategoryBrandDao tbCategoryBrandDao;

    /**
     * 通过ID查询单条数据
     *
     * @param categoryId 主键
     * @return 实例对象
     */
    @Override
    public TbCategoryBrand queryById(Integer categoryId) {
        return this.tbCategoryBrandDao.queryById(categoryId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<TbCategoryBrand> queryAllByLimit(int offset, int limit) {
        return this.tbCategoryBrandDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tbCategoryBrand 实例对象
     * @return 实例对象
     */
    @Override
    public TbCategoryBrand insert(TbCategoryBrand tbCategoryBrand) {
        this.tbCategoryBrandDao.insert(tbCategoryBrand);
        return tbCategoryBrand;
    }

    /**
     * 修改数据
     *
     * @param tbCategoryBrand 实例对象
     * @return 实例对象
     */
    @Override
    public TbCategoryBrand update(TbCategoryBrand tbCategoryBrand) {
        this.tbCategoryBrandDao.update(tbCategoryBrand);
        return this.queryById(tbCategoryBrand.getCategoryId());
    }

    /**
     * 通过主键删除数据
     *
     * @param categoryId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer categoryId) {
        return this.tbCategoryBrandDao.deleteById(categoryId) > 0;
    }
}