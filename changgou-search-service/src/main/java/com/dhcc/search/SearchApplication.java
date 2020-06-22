package com.dhcc.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * @author zhangqi
 * @date 2020/5/27
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.dhcc.search.feign")
@EnableElasticsearchRepositories(basePackages = "com.dhcc.search.repository")
@EnableHystrix
public class SearchApplication {
    public static void main(String[] args) {
        SpringApplication.run(SearchApplication.class, args);
    }
}
