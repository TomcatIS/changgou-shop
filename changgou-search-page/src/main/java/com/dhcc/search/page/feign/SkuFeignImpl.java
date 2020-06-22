package com.dhcc.search.page.feign;

import com.dhcc.dto.CommonResult;
import com.dhcc.enu.ProcessStatusEnum;
import com.dhcc.exception.BaseException;
import com.dhcc.search.page.feign.SkuFeign;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author zhangqi
 * @date 2020/6/19
 */
@Component
public class SkuFeignImpl  implements SkuFeign {
    private final Logger logger = LoggerFactory.getLogger(getClass());


    @Override
    public CommonResult<Map<String, Object>> search(Map<String, String> searchMap) {
        logger.error("掉重构超时");
        throw new BaseException(0, "滚粗去");
    }
}
