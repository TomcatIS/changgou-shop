package com.dhcc.search.page.controller;

import com.dhcc.dto.CommonResult;
import com.dhcc.enu.ProcessStatusEnum;
import com.dhcc.exception.BaseException;
import com.dhcc.search.page.feign.SkuFeign;
import com.dhcc.search.page.service.PageService;
import com.dhcc.search.page.util.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description 商品显示控制层
 * @author zhangqi
 * @date 2020/6/6
 */
@Controller
@RequestMapping("search/page")
public class PageController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SkuFeign skuFeign;

    @Autowired
    private PageService pageService;

    /**
     * @description 商品搜索
     * @param searchMap 封装搜索条件
     */
    /*@HystrixCommand(fallbackMethod = "invokeFeignTimeOutHandler",commandProperties =
                     @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"))*/
    @GetMapping("/listSkuDetails")
    public String search(@RequestParam Map<String, String> searchMap, Model model) {
        logger.info("feign调用搜索服务查询商品信息");
        Map<String, Object> result = this.skuFeign.search(searchMap).getData();
        System.out.println(result);
        if (result == null) {
            throw new BaseException(ProcessStatusEnum.SERVICE_TRANSFER_DATA_ERROR.getCode(),
                    ProcessStatusEnum.SERVICE_TRANSFER_DATA_ERROR.getMessage());
        }
        logger.info("feign调用搜索服务查询商品信息成功");
        //  根据品牌名查询图片地址
        Map<String, String> map = this.pageService.queryBrandImg(result);
        String url = this.pageService.searchMapToUrl(searchMap);
        Page<Object> page = this.pageService.pagination(result);
        model.addAttribute("page", page);
        model.addAttribute("url", url);
        model.addAttribute("searchMap", searchMap);
        model.addAttribute("result", result);
        model.addAttribute("brandMap", map);
        return "search";
    }

    @ResponseBody
    public String invokeFeignTimeOutHandler(Map<String, String> searchMap, Model model) {
        return  "feign调用超时--->hhaocji";
    }
}
