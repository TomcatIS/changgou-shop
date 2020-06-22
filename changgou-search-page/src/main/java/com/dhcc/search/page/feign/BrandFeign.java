package com.dhcc.search.page.feign;

import com.dhcc.dto.CommonResult;
import com.dhcc.entity.TbBrand;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author zhangqi
 * @date 2020/6/17
 */
@Component
@FeignClient("changgou-goods-service")
@RequestMapping("tbBrand")
public interface BrandFeign {
    @GetMapping("/queryAll")
    CommonResult<List<TbBrand>> queryAll(TbBrand tbBrand);
}
