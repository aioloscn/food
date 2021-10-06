package com.aiolos.restaurants.service;

import cn.hutool.core.bean.BeanUtil;
import com.aiolos.commons.enums.RedisKeyEnum;
import com.aiolos.food.pojo.Restaurants;
import com.aiolos.restaurants.RestaurantApplicationTest;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author Aiolos
 * @date 2021/9/11 10:05 下午
 */
@Slf4j
public class RestaurantTest extends RestaurantApplicationTest {

    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private RestaurantService restaurantService;

    /**
     * 逐行插入
     */
    @Test
    public void testSyncForHash() {
        List<Restaurants> restaurants = restaurantService.findAll();
        long start = System.currentTimeMillis();
        restaurants.forEach(restaurant -> {
            Map<String, Object> restaurantMap = BeanUtil.beanToMap(restaurant);
            String key = RedisKeyEnum.RESTAURANTS.getKey() + restaurant.getId();
            redisTemplate.opsForHash().putAll(key, restaurantMap);
        });
        long end = System.currentTimeMillis();
        log.info("执行时间：{}", end - start);
    }

    /**
     * Pipeline 管道批量插入
     */
    @Test
    public void testSyncForHashPipeline() {
        List<Restaurants> restaurants = restaurantService.findAll();
        long start = System.currentTimeMillis();
        List<Long> list = redisTemplate.executePipelined((RedisCallback<Long>) connection -> {
            for (Restaurants restaurant : restaurants) {
                String key = RedisKeyEnum.RESTAURANTS.getKey() + restaurant.getId();
                Map<String, Object> restaurantMap = BeanUtil.beanToMap(restaurant);
                StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
                Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
                Map<byte[], byte[]> restaurantStringMap = Maps.newHashMap();
                restaurantMap.forEach((k, v) -> {
                    restaurantStringMap.put(stringRedisSerializer.serialize(k), jackson2JsonRedisSerializer.serialize(v));
                });
                connection.hMSet(stringRedisSerializer.serialize(key), restaurantStringMap);
            }
            return null;
        });
        long end = System.currentTimeMillis();
        log.info("执行时间：{}", end - start);
    }
}
