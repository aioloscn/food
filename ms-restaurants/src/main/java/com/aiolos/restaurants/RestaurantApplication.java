package com.aiolos.restaurants;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Aiolos
 * @date 2021/9/12 12:05 上午
 */
@SpringBootApplication
@MapperScan("com.aiolos.restaurants.mapper")
@ComponentScan(basePackages = "com.aiolos")
public class RestaurantApplication {
    public static void main(String[] args) {
        SpringApplication.run(RestaurantApplication.class, args);
    }
}
