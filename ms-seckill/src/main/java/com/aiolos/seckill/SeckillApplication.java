package com.aiolos.seckill;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Aiolos
 * @date 2021/8/21 2:40 下午
 */
@SpringBootApplication
@MapperScan("com.aiolos.seckill.mapper")
@ComponentScan(basePackages = "com.aiolos")
public class SeckillApplication {
    public static void main(String[] args) {
        SpringApplication.run(SeckillApplication.class, args);
    }
}
