package com.dhcc.service;

import com.dhcc.dto.Goods;

/**
 * @author zhangqi
 * @date 2020/5/13
 */
public interface GoodsService {
    void addGoods(Goods goods);

    Goods listGoodsBySpuId(String id);

    void updateGoods(Goods goods);

    void audit(String id);

    void put(String id);

    void pull(String id);

    void delete(String id);

    void restore(String id);

    void realDelete(String id);
}
