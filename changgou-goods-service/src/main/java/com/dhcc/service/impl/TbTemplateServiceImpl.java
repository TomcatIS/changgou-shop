package com.dhcc.service.impl;

import com.dhcc.entity.TbTemplate;
import com.dhcc.dao.TbTemplateDao;
import com.dhcc.service.TbTemplateService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TbTemplate)表服务实现类
 *
 * @author makejava
 * @since 2020-05-07 23:00:42
 */
@Service("tbTemplateService")
public class TbTemplateServiceImpl implements TbTemplateService {
    @Resource
    private TbTemplateDao tbTemplateDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TbTemplate queryById(Integer id) {
        return this.tbTemplateDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<TbTemplate> queryAllByLimit(int offset, int limit) {
        return this.tbTemplateDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tbTemplate 实例对象
     * @return 实例对象
     */
    @Override
    public TbTemplate insert(TbTemplate tbTemplate) {
        this.tbTemplateDao.insert(tbTemplate);
        return tbTemplate;
    }

    /**
     * 修改数据
     *
     * @param tbTemplate 实例对象
     * @return 实例对象
     */
    @Override
    public TbTemplate update(TbTemplate tbTemplate) {
        this.tbTemplateDao.update(tbTemplate);
        return this.queryById(tbTemplate.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.tbTemplateDao.deleteById(id) > 0;
    }
}