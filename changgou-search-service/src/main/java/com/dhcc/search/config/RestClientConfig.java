package com.dhcc.search.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhangqi
 * @date 2020/5/27
 */
@Configuration
public class RestClientConfig {
    private static final String hostName = "47.98.236.132";
    private static final Integer port = 9200;
    private static final String protocol = "http";

    @Bean
    RestHighLevelClient restHighLevelClient() {
        return new RestHighLevelClient(RestClient.builder(new HttpHost(hostName, port, protocol)));
    }
}
