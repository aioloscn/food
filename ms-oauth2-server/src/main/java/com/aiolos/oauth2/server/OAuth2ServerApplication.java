package com.aiolos.oauth2.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Aiolos
 * @date 2021/8/16 6:03 下午
 */
@SpringBootApplication
@MapperScan("com.aiolos.oauth2.server.mapper")
@ComponentScan(basePackages = "com.aiolos")    // 容器会扫描这个包下所有的@Component、@Configuration、@Bean、@Service等
public class OAuth2ServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(OAuth2ServerApplication.class, args);
    }
}
