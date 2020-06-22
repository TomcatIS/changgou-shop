package com.dhcc.search.page.service.impl;

import com.dhcc.search.page.service.PageService;
import com.dhcc.search.page.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhangqi
 * @date 2020/6/16
 */
@Service
public class PageServiceImpl implements PageService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

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

    @Override
    public Map<String, String> queryBrandImg(Map<String, Object> result) {
        Object list = result.get("brandNameList");
        if (list == null) {
            return null;
        }
        List<String> brandNameList = (List<String>)list;
        // 将品牌和图片存入map
        Map<String, String> map = new HashMap<>(256);
        for (String brandName : brandNameList) {
            String imgUrl = (String)this.redisTemplate.opsForValue().get(brandName);
            if ("".equals(imgUrl) || imgUrl == null) {
                map.put(brandName, brandName);
                continue;
            }
            map.put(brandName, imgUrl);
        }
        return map;
    }
}
