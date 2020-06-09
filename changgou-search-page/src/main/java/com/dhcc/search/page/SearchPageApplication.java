package com.dhcc.search.page;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author zhangqi
 * @date 2020/6/5
 */
@SpringBootApplication
@EnableFeignClients(basePackages = "com.dhcc.search.page.feign")
@EnableEurekaClient
public class SearchPageApplication {
    public static void main(String[] args) {
        SpringApplication.run(SearchPageApplication.class, args);
    }
}
