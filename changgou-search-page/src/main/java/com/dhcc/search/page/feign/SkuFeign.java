package com.dhcc.search.page.feign;

import com.dhcc.dto.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author zhangqi
 * @date 2020/6/6
 */
@Component
@FeignClient(value = "changgou-search-service")
@RequestMapping("es")
public interface SkuFeign {

    @GetMapping("sku/search")
    CommonResult<Map<String, Object>> search(@RequestParam Map<String, String> searchMap);
}
