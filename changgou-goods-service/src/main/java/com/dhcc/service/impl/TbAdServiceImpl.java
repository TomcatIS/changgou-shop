package com.dhcc.service.impl;

import com.dhcc.dao.TbAdDao;
import com.dhcc.entity.TbAd;
import com.dhcc.service.TbAdService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TbAd)表服务实现类
 *
 * @author makejava
 * @since 2020-05-21 22:23:08
 */
@Service("tbAdService")
public class TbAdServiceImpl implements TbAdService {
    @Resource
    private TbAdDao tbAdDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TbAd queryById(Integer id) {
        return this.tbAdDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<TbAd> queryAllByLimit(int offset, int limit) {
        return this.tbAdDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tbAd 实例对象
     * @return 实例对象
     */
    @Override
    public TbAd insert(TbAd tbAd) {
        this.tbAdDao.insert(tbAd);
        return tbAd;
    }

    /**
     * 修改数据
     *
     * @param tbAd 实例对象
     * @return 实例对象
     */
    @Override
    public TbAd update(TbAd tbAd) {
        this.tbAdDao.update(tbAd);
        return this.queryById(tbAd.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.tbAdDao.deleteById(id) > 0;
    }
}