package com.aiolos.diners;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Aiolos
 * @date 2021/8/14 9:46 下午
 */
@SpringBootApplication
@MapperScan("com.aiolos.diners.mapper")
@ComponentScan(basePackages = "com.aiolos")    // 容器会扫描这个包下所有的@Component、@Configuration、@Bean、@Service等
public class DinersApplication {
    public static void main(String[] args) {
        SpringApplication.run(DinersApplication.class, args);
    }
}

