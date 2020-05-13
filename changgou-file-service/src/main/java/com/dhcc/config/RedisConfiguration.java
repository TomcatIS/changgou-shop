package com.dhcc.config;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * @description redis序列化方式配置类
 * @author zhangqi
 * @date 2020/5/12
 */
@Configuration
public class RedisConfiguration {

    @Bean
    public RedisTemplate<String, Object> getRedisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object>  redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);
        // string方式序列化key
        redisTemplate.setKeySerializer(RedisSerializer.string());
        // json方式序列化value，默认使用jackson，这里改为fastjson
        redisTemplate.setValueSerializer(new GenericFastJsonRedisSerializer());
        return redisTemplate;
    }
}
