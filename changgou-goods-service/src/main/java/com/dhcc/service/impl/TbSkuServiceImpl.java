package com.dhcc.service.impl;

import com.dhcc.dao.TbSkuDao;
import com.dhcc.entity.TbSku;
import com.dhcc.service.TbSkuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * 商品表(TbSku)表服务实现类
 *
 * @author makejava
 * @since 2020-05-07 23:00:42
 */
@Service("tbSkuService")
public class TbSkuServiceImpl implements TbSkuService {
    @Resource
    private TbSkuDao tbSkuDao;

    @Override
    public List<TbSku> listSkuBySpuId(String id) {
        return this.tbSkuDao.listSkuBySpuId(id);
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TbSku queryById(String id) {
        TbSku tbSku = this.tbSkuDao.queryById(id);
        Timestamp createTime = tbSku.getCreateTime();
        tbSku.setCreateTime(createTime);
        return this.tbSkuDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<TbSku> queryAllByLimit(int offset, int limit) {
        return this.tbSkuDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tbSku 实例对象
     * @return 实例对象
     */
    @Override
    public TbSku insert(TbSku tbSku) {
        this.tbSkuDao.insert(tbSku);
        return tbSku;
    }

    /**
     * 修改数据
     *
     * @param tbSku 实例对象
     * @return 实例对象
     */
    @Override
    public TbSku update(TbSku tbSku) {
        this.tbSkuDao.update(tbSku);
        return this.queryById(tbSku.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.tbSkuDao.deleteById(id) > 0;
    }

    @Override
    public List<TbSku> queryAll(TbSku tbSku) {
        return this.tbSkuDao.queryAll(tbSku);
    }
}