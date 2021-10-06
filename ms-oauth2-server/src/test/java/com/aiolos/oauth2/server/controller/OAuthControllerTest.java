package com.aiolos.oauth2.server.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.aiolos.commons.response.CommonResponse;
import com.aiolos.oauth2.server.OAuth2ServerApplicationTests;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.Base64Utils;


import java.nio.file.Files;
import java.nio.file.Paths;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Aiolos
 * @date 2021/8/21 8:53 下午
 */
public class OAuthControllerTest extends OAuth2ServerApplicationTests {

    @Test
    public void writeToken() throws Exception {
        String authorization = Base64Utils.encodeToString("appId:123456".getBytes());
        StringBuffer tokens = new StringBuffer();
        for (int i = 0; i < 2000; i++) {
            MvcResult mvcResult = super.mockMvc.perform(MockMvcRequestBuilders.post("/oauth/token")
                    .header("Authorization", "Basic " + authorization)
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .param("username", "test" + i)
                    .param("password", "123456")
                    .param("grant_type", "password")
                    .param("scope", "api")
            )
                    .andExpect(status().isOk())
                    // .andDo(print())
                    .andReturn();
            String contentAsString = mvcResult.getResponse().getContentAsString();
            CommonResponse resultInfo = (CommonResponse) JSONUtil.toBean(contentAsString, CommonResponse.class);
            System.out.println("resultInfo.getData() = " + resultInfo.getData());
            JSONObject result = (JSONObject) resultInfo.getData();
            String token = result.getStr("accessToken");
            tokens.append(token).append("\r\n");
        }

        Files.write(Paths.get("tokens.txt"), tokens.toString().getBytes());
    }

    @Test
    public void userme() throws Exception {
        MvcResult mvcResult = super.mockMvc.perform(MockMvcRequestBuilders.get("/user/me?access_token=00029c29-b6ee-49c3-826f-990e231f544d"))
                .andExpect(status().isOk()).andDo(print()).andReturn();
    }
}
