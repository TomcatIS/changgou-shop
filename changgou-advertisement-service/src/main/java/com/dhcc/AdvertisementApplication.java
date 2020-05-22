package com.dhcc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author zhangqi
 * @date 2020/5/21
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = "com.dhcc.dao")
public class AdvertisementApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdvertisementApplication.class, args);
    }
}
