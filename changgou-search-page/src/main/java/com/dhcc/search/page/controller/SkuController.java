package com.dhcc.search.page.controller;

import com.dhcc.dto.CommonResult;
import com.dhcc.enu.ProcessStatusEnum;
import com.dhcc.exception.BaseException;
import com.dhcc.search.page.feign.SkuFeign;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author zhangqi
 * @date 2020/6/6
 */
@Controller
@RequestMapping("search/page")
public class SkuController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SkuFeign skuFeign;

    @GetMapping("/sku")
    public String search(@RequestParam Map<String, String> searchMap, Model model) {
        logger.info("feign调用搜索服务查询商品信息");
        CommonResult<Map<String, Object>> result = this.skuFeign.search(searchMap);
        if (result == null) {
            throw new BaseException(ProcessStatusEnum.SERVICE_TRANSFER_DATA_ERROR.getCode(),
                    ProcessStatusEnum.SERVICE_TRANSFER_DATA_ERROR.getMessage());
        }
        logger.info("feign调用搜索服务查询商品信息成功");
        System.out.println(result.getData().toString());
        model.addAttribute("searchMap", searchMap);
        model.addAttribute("result", result.getData());
        return "search";
    }
}
