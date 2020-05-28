package com.dhcc.search.feign;

import com.dhcc.dto.CommonResult;
import com.dhcc.entity.TbSku;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhangqi
 * @date 2020/5/27
 */
@Component
@FeignClient(value = "changgou-goods-service")
public interface SkuFeign {

    @PostMapping("tbSku/queryAll")
    CommonResult<List<TbSku>> queryAll(@RequestBody TbSku tbSku);

    @GetMapping("tbSku/querySkuBySpuId/{spuId}")
    CommonResult<List<TbSku>> querySkuBySpuId(@PathVariable("spuId") String spuId);
}
