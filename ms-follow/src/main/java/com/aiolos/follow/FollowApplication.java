package com.aiolos.follow;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Aiolos
 * @date 2021/8/23 11:46 下午
 */
@SpringBootApplication
@MapperScan("com.aiolos.follow.mapper")
@ComponentScan("com.aiolos")
public class FollowApplication {
    public static void main(String[] args) {
        SpringApplication.run(FollowApplication.class, args);
    }
}
