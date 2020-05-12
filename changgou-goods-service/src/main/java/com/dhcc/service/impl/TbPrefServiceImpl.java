package com.dhcc.service.impl;

import com.dhcc.entity.TbPref;
import com.dhcc.dao.TbPrefDao;
import com.dhcc.service.TbPrefService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TbPref)表服务实现类
 *
 * @author makejava
 * @since 2020-05-07 23:00:42
 */
@Service("tbPrefService")
public class TbPrefServiceImpl implements TbPrefService {
    @Resource
    private TbPrefDao tbPrefDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TbPref queryById(Integer id) {
        return this.tbPrefDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<TbPref> queryAllByLimit(int offset, int limit) {
        return this.tbPrefDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tbPref 实例对象
     * @return 实例对象
     */
    @Override
    public TbPref insert(TbPref tbPref) {
        this.tbPrefDao.insert(tbPref);
        return tbPref;
    }

    /**
     * 修改数据
     *
     * @param tbPref 实例对象
     * @return 实例对象
     */
    @Override
    public TbPref update(TbPref tbPref) {
        this.tbPrefDao.update(tbPref);
        return this.queryById(tbPref.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.tbPrefDao.deleteById(id) > 0;
    }
}