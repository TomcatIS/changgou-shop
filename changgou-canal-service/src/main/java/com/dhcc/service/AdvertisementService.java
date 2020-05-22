package com.dhcc.service;

import com.dhcc.dto.CommonResult;
import com.dhcc.entity.TbAd;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @description 调用广告服务接口
 * @author zhangqi
 * @date 2020/5/21
 */
@Component
@FeignClient(value = "changgou-advertisement-service")
public interface AdvertisementService {

    @GetMapping("tbAd/selectOne/{id}")
    CommonResult<TbAd> selectOne(@PathVariable("id") Integer id);
}
