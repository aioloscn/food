package com.aiolos.oauth2.server;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;

/**
 * @author Aiolos
 * @date 2021/8/21 8:50 下午
 */
@SpringBootTest
@AutoConfigureMockMvc
public class OAuth2ServerApplicationTests {

    @Resource
    public MockMvc mockMvc;
}
