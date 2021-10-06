package com.aiolos.restaurants.service;

import com.aiolos.food.pojo.Restaurants;

import java.util.List;

/**
 * @author Aiolos
 * @date 2021/9/11 10:01 下午
 */
public interface RestaurantService {

    /**
     * 查询所有餐厅
     * @return
     */
    List<Restaurants> findAll();

    /**
     * 根据Id查询餐厅
     * @return
     */
    Restaurants findById(Integer id);
}
