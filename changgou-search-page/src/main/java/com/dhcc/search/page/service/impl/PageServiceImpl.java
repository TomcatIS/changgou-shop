package com.dhcc.search.page.service.impl;

import com.dhcc.search.page.service.PageService;
import com.dhcc.search.page.util.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author zhangqi
 * @date 2020/6/16
 */
@Service
public class PageServiceImpl implements PageService {

    @Override
    public String searchMapToUrl(Map<String, String> searchMap) {
        StringBuilder stringBuilder = new StringBuilder("listSkuDetails?");
        for (Map.Entry<String, String> entry : searchMap.entrySet()) {
            if ("pageNum".equals(entry.getKey())) {
                continue;
            }
            stringBuilder.append(entry.getKey())
                    .append("=")
                    .append(entry.getValue())
                    .append("&");
        }
        // 去掉最后一个&
        String url = stringBuilder.toString();
        if (url.lastIndexOf("&") < 0) {
            return url;
        }
        return url.substring(0,  url.lastIndexOf("&"));
    }

    /**
     * @return 分页信息
     * @description 分页
     */
    @Override
    public Page<Object> pagination(Map<String, Object> map) {
        return new Page<>(
                Long.parseLong(map.get("totalElements").toString()),
                Integer.parseInt(map.get("pageNum").toString()) + 1,
                Integer.parseInt(map.get("pageSize").toString())
        );
    }
}
