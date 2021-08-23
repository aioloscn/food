package com.aiolos.seckill.config;

import com.aiolos.seckill.model.RedisLock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author Aiolos
 * @date 2021/8/23 5:56 下午
 */
@Configuration
public class RedisLockConfiguration {

    private final RedisTemplate redisTemplate;

    public RedisLockConfiguration(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Bean
    public RedisLock redisLock() {
        return new RedisLock(redisTemplate);
    }
}
