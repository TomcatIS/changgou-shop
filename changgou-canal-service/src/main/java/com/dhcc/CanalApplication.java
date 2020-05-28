package com.dhcc;

import com.xpand.starter.canal.annotation.EnableCanalClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author zhangqi
 * @date 2020/5/20
 */
@SpringBootApplication
@EnableCanalClient
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.dhcc.feign")
public class CanalApplication {
    public static void main(String[] args) {
        SpringApplication.run(CanalApplication.class, args);
    }
}
