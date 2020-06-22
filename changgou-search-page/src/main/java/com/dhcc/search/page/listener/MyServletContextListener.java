package com.dhcc.search.page.listener;

import com.dhcc.dto.CommonResult;
import com.dhcc.entity.TbBrand;
import com.dhcc.enu.ProcessStatusEnum;
import com.dhcc.exception.BaseException;
import com.dhcc.search.page.feign.BrandFeign;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.List;

/**
 * @author zhangqi
 * @date 2020/6/17
 */
@WebListener
public class MyServletContextListener implements ServletContextListener {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private BrandFeign brandFeign;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("ServletContext开始初始化");
        logger.info("调用商品服务查询品牌和图片信息");
        List<TbBrand> brandList = brandFeign.queryAll(new TbBrand()).getData();
        /*List<TbBrand> brandList;
        try {
            brandList = brandFeign.queryAll(new TbBrand()).getData();
        } catch (Exception e) {
            throw new BaseException(ProcessStatusEnum.SERVICE_TRANSFER_DATA_ERROR.getCode(),
                    ProcessStatusEnum.SERVICE_TRANSFER_DATA_ERROR.getMessage());
        }*/
        logger.info("品牌名称和图片开始存入redis");
        for (TbBrand brand : brandList) {
            try {
                this.redisTemplate.opsForValue().set(brand.getName(), brand.getImage());
            } catch (Exception e) {
                throw new BaseException(ProcessStatusEnum.REDIS_INSERT_ERROR.getCode(),
                        ProcessStatusEnum.REDIS_INSERT_ERROR.getMessage());
            }
        }
        logger.info("品牌名称和图片存入redis成功");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
