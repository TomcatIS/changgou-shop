package com.dhcc.service.impl;

import com.dhcc.entity.TbPara;
import com.dhcc.dao.TbParaDao;
import com.dhcc.exception.BaseException;
import com.dhcc.service.TbParaService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TbPara)表服务实现类
 *
 * @author makejava
 * @since 2020-05-07 23:00:41
 */
@Service("tbParaService")
public class TbParaServiceImpl implements TbParaService {
    @Resource
    private TbParaDao tbParaDao;

    @Override
    public List<TbPara> listParamsByCategory(Integer id) {
        List<TbPara> tbParas = this.tbParaDao.listParamsByCategory(id);
        if (tbParas == null) {
            throw new BaseException("该分类没有相应的参数!");
        }
        return tbParas;
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TbPara queryById(Integer id) {
        return this.tbParaDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<TbPara> queryAllByLimit(int offset, int limit) {
        return this.tbParaDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tbPara 实例对象
     * @return 实例对象
     */
    @Override
    public TbPara insert(TbPara tbPara) {
        this.tbParaDao.insert(tbPara);
        return tbPara;
    }

    /**
     * 修改数据
     *
     * @param tbPara 实例对象
     * @return 实例对象
     */
    @Override
    public TbPara update(TbPara tbPara) {
        this.tbParaDao.update(tbPara);
        return this.queryById(tbPara.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.tbParaDao.deleteById(id) > 0;
    }
}