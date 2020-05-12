package com.dhcc.service.impl;

import com.dhcc.entity.TbAlbum;
import com.dhcc.dao.TbAlbumDao;
import com.dhcc.service.TbAlbumService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TbAlbum)表服务实现类
 *
 * @author makejava
 * @since 2020-05-07 23:00:29
 */
@Service("tbAlbumService")
@CacheConfig(cacheNames = "goods")
public class TbAlbumServiceImpl implements TbAlbumService {
    @Resource
    private TbAlbumDao tbAlbumDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    @Cacheable(key = "#p0")
    public TbAlbum queryById(Long id) {
        return this.tbAlbumDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<TbAlbum> queryAllByLimit(int offset, int limit) {
        return this.tbAlbumDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tbAlbum 实例对象
     * @return 实例对象
     */
    @Override
    public TbAlbum insert(TbAlbum tbAlbum) {
        this.tbAlbumDao.insert(tbAlbum);
        return tbAlbum;
    }

    /**
     * 修改数据
     *
     * @param tbAlbum 实例对象
     * @return 实例对象
     */
    @Override
    public TbAlbum update(TbAlbum tbAlbum) {
        this.tbAlbumDao.update(tbAlbum);
        return this.queryById(tbAlbum.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.tbAlbumDao.deleteById(id) > 0;
    }
}