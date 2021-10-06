package com.aiolos.food.controller.restaurant;

import com.aiolos.commons.response.CommonResponse;
import com.aiolos.food.pojo.Restaurants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Aiolos
 * @date 2021/9/12 1:08 上午
 */
@Api(tags = "餐厅相关接口")
@RequestMapping
public interface RestaurantControllerApi {

    @ApiOperation(value = "根据Id查询餐厅数据", httpMethod = "GET")
    @GetMapping("{restaurantId}")
    CommonResponse<Restaurants> findById(@PathVariable Integer restaurantId);
}
