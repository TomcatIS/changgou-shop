package com.dhcc.service.impl;

import com.dhcc.entity.TbPref;
import com.dhcc.entity.TbSpec;
import com.dhcc.dao.TbSpecDao;
import com.dhcc.exception.BaseException;
import com.dhcc.service.TbSpecService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TbSpec)表服务实现类
 *
 * @author makejava
 * @since 2020-05-07 23:00:42
 */
@Service("tbSpecService")
public class TbSpecServiceImpl implements TbSpecService {
    @Resource
    private TbSpecDao tbSpecDao;

    /**
     * @param id 分类id
     */
    @Override
    public List<TbPref> listPrecsByCategory(Integer id) {
        List<TbPref> tbPrefs = this.tbSpecDao.listPrecsByCategory(id);
        if (tbPrefs == null) {
            throw new BaseException("该分类不存在相应的规格数据!");
        }
        return tbPrefs;
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TbSpec queryById(Integer id) {
        return this.tbSpecDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<TbSpec> queryAllByLimit(int offset, int limit) {
        return this.tbSpecDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tbSpec 实例对象
     * @return 实例对象
     */
    @Override
    public TbSpec insert(TbSpec tbSpec) {
        this.tbSpecDao.insert(tbSpec);
        return tbSpec;
    }

    /**
     * 修改数据
     *
     * @param tbSpec 实例对象
     * @return 实例对象
     */
    @Override
    public TbSpec update(TbSpec tbSpec) {
        this.tbSpecDao.update(tbSpec);
        return this.queryById(tbSpec.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.tbSpecDao.deleteById(id) > 0;
    }
}