package com.dhcc.service.impl;

import com.alibaba.fastjson.JSON;
import com.dhcc.dao.TbBrandDao;
import com.dhcc.dao.TbCategoryDao;
import com.dhcc.dao.TbSkuDao;
import com.dhcc.dao.TbSpuDao;
import com.dhcc.dto.Goods;
import com.dhcc.entity.TbBrand;
import com.dhcc.entity.TbCategory;
import com.dhcc.entity.TbSku;
import com.dhcc.entity.TbSpu;
import com.dhcc.enu.ProcessStatusEnum;
import com.dhcc.exception.BaseException;
import com.dhcc.service.GoodsService;
import com.dhcc.util.IdWorker;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author zhangqi
 * @date 2020/5/13
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    /**
     * id生成器
     */
    /*@Autowired
    private IdWorker idWorker;*/

    @Autowired
    private TbSpuDao tbSpuDao;

    @Autowired
    private TbSkuDao tbSkuDao;

    @Autowired
    private TbBrandDao tbBrandDao;

    @Autowired
    private TbCategoryDao tbCategoryDao;

    @Override
    public void addGoods(Goods goods) {
        addSpu(goods);
        addSku(goods);
    }

    private void addSpu(Goods goods) {
        IdWorker idWorker = new IdWorker();
        TbSpu spu = goods.getTbSpu();
        spu.setId(String.valueOf(idWorker.nextId()));
        spu.setIsDelete("0");
        spu.setIsEnableSpec("1");
        spu.setIsMarketable("1");
        int i = tbSpuDao.insert(spu);
        if (i <= 0) {
            throw new BaseException(ProcessStatusEnum.DATA_INSERT_ERROR.getCode(),
                    ProcessStatusEnum.DATA_INSERT_ERROR.getMessage());
        }
    }

    private void addSku(Goods goods) {
        IdWorker idWorker = new IdWorker();
        List<TbSku> skuList = goods.getSkuList();
        TbSpu spu = goods.getTbSpu();
        TbBrand tbBrand = tbBrandDao.queryById(spu.getBrandId());
        TbCategory tbCategory = tbCategoryDao.queryById(spu.getCategory3Id());
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = simpleDateFormat.format(date);
        try {
            date = simpleDateFormat.parse(time);
        } catch (ParseException e) {
            throw new BaseException("日期格式转换错误");
        }
        String spuName = spu.getName();
        for (TbSku sku : skuList) {
            sku.setId(String.valueOf(idWorker.nextId()));
            sku.setCreateTime(date);
            sku.setUpdateTime(date);
            sku.setSpuId(spu.getId());
            sku.setCategoryId(tbCategory.getId());
            sku.setCategoryName(tbCategory.getName());
            sku.setBrandName(tbBrand.getName());
            String spec = sku.getSpec();
            if (StringUtils.isEmpty(spec)) {
                spec = "{}";
            }
            // sku名称为spu名称加各项规格名称
            StringBuilder builder = new StringBuilder(spuName);
            Map<String, String> map = JSON.parseObject(spec, Map.class);
            Set<Map.Entry<String, String>> entries = map.entrySet();
            for (Map.Entry<String, String> entry : entries) {
                builder.append(entry);
            }
            sku.setName(builder.toString());
            int j = tbSkuDao.insert(sku);
            if (j <= 0) {
                throw new BaseException(ProcessStatusEnum.DATA_INSERT_ERROR.getCode(),
                        ProcessStatusEnum.DATA_INSERT_ERROR.getMessage());
            }
        }
    }
}
