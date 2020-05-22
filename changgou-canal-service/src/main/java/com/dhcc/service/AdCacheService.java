package com.dhcc.service;

import com.alibaba.otter.canal.protocol.CanalEntry;

/**
 * @description 更新redis中的缓存数据
 * @author zhangqi
 * @date 2020/5/22
 */
public interface AdCacheService {
    void insert(CanalEntry.RowData rowData);
    void delete(CanalEntry.RowData rowData);
    void update(CanalEntry.RowData rowData);
}
