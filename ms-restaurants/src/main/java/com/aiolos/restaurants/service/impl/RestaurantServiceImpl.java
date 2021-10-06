package com.aiolos.restaurants.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.aiolos.commons.enums.RedisKeyEnum;
import com.aiolos.food.pojo.Restaurants;
import com.aiolos.restaurants.mapper.RestaurantsMapper;
import com.aiolos.restaurants.service.RestaurantService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Aiolos
 * @date 2021/9/11 10:02 下午
 */
@Slf4j
@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantsMapper restaurantsMapper;
    private final RedisTemplate redisTemplate;

    public RestaurantServiceImpl(RestaurantsMapper restaurantsMapper, RedisTemplate redisTemplate) {
        this.restaurantsMapper = restaurantsMapper;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public List<Restaurants> findAll() {
        return restaurantsMapper.selectList(new QueryWrapper<>());
    }

    @Override
    public Restaurants findById(Integer id) {
        String key = RedisKeyEnum.RESTAURANTS.getKey() + id;
        Map restaurantMap = redisTemplate.opsForHash().entries(key);
        Restaurants restaurants;
        // 如果不存在则查询数据库
        if (restaurantMap == null || restaurantMap.isEmpty()) {
            log.info("缓存失效了，查询数据库：{}", id);
            restaurants = restaurantsMapper.selectById(id);
            if (restaurants != null) {
                // 更新缓存
                redisTemplate.opsForHash().putAll(key, BeanUtil.beanToMap(restaurants));
            } else {
                // 防止缓存穿透，缓存一个短时间的空数据，让这60s的窗口期都走缓存，到期后还没有数据继续缓存
                restaurants = new Restaurants();
                restaurants.setId(id);
                redisTemplate.opsForHash().putAll(key, BeanUtil.beanToMap(restaurants));
                redisTemplate.expire(key, 60L, TimeUnit.SECONDS);
            }
        } else {
            restaurants = BeanUtil.fillBeanWithMap(restaurantMap, new Restaurants(), false);
        }
        return restaurants;
    }
}
