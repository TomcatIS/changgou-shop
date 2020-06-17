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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    @GetMapping("/listSkuDetails")
    public String search(@RequestParam Map<String, String> searchMap, Model model) {
        logger.info("feign调用搜索服务查询商品信息");
        CommonResult<Map<String, Object>> result = this.skuFeign.search(searchMap);
        if (result == null) {
            throw new BaseException(ProcessStatusEnum.SERVICE_TRANSFER_DATA_ERROR.getCode(),
                    ProcessStatusEnum.SERVICE_TRANSFER_DATA_ERROR.getMessage());
        }
        logger.info("feign调用搜索服务查询商品信息成功");
        String url = this.pageService.searchMapToUrl(searchMap);
        System.out.println(result.getData().toString());
        System.out.println(searchMap.toString());
        Page<Object> page = this.pageService.pagination(result.getData());
        model.addAttribute("page", page);
        model.addAttribute("url", url);
        model.addAttribute("searchMap", searchMap);
        model.addAttribute("result", result.getData());
        //<em style="color:red;">森</em><em style="color:red;">马</em>（Semir） 牛仔裤女 2018秋季新款粉红豹长裤潮流破
        //<em style="color:red;">华为</em> HUAWEI 麦芒7 6G+64G 魅海蓝 全网通  前置智慧双摄  移动联通电信4G手机 双卡双待,
        return "search";
    }
}
