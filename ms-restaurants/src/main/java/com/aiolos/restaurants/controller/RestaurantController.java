package com.aiolos.restaurants.controller;

import com.aiolos.commons.enums.ErrorEnum;
import com.aiolos.commons.response.CommonResponse;
import com.aiolos.food.controller.restaurant.RestaurantControllerApi;
import com.aiolos.food.pojo.Restaurants;
import com.aiolos.restaurants.service.RestaurantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Aiolos
 * @date 2021/9/12 1:07 上午
 */
@Slf4j
@RestController
public class RestaurantController implements RestaurantControllerApi {

    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @Override
    public CommonResponse<Restaurants> findById(Integer restaurantId) {
        if (restaurantId == null)
            return CommonResponse.error(ErrorEnum.NO_RESTAURANT_ID);
        return CommonResponse.ok(restaurantService.findById(restaurantId));
    }
}
