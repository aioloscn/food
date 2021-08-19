package com.aiolos.diners.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.aiolos.commons.domain.OAuthDinerInfo;
import com.aiolos.commons.enums.ErrorEnum;
import com.aiolos.commons.response.CommonResponse;
import com.aiolos.diners.config.OAuth2ClientConfiguration;
import com.aiolos.diners.service.DinersService;
import com.aiolos.food.pojo.vo.LoginDinerVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;

/**
 * @author Aiolos
 * @date 2021/8/18 10:02 下午
 */
@Service
public class DinersServiceImpl implements DinersService {

    private final RestTemplate restTemplate;

    private final OAuth2ClientConfiguration oAuth2ClientConfiguration;

    @Value("${service.name.ms-oauth-server}")
    private String oauthServerName;

    public DinersServiceImpl(RestTemplate restTemplate, OAuth2ClientConfiguration oAuth2ClientConfiguration) {
        this.restTemplate = restTemplate;
        this.oAuth2ClientConfiguration = oAuth2ClientConfiguration;
    }

    @Override
    public CommonResponse signIn(String account, String password) {
        // 构建请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        // 构建请求体（请求参数）
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("username", account);
        body.add("password", password);
        body.setAll(BeanUtil.beanToMap(oAuth2ClientConfiguration));
        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(body, headers);
        // 设置 Authorization
        restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor(
                oAuth2ClientConfiguration.getClientId(), oAuth2ClientConfiguration.getSecret()
        ));
        // 发送请求
        ResponseEntity<CommonResponse> resp = restTemplate.postForEntity(oauthServerName + "oauth/token", entity, CommonResponse.class);
        // 处理返回结果
        if (resp.getStatusCode() != HttpStatus.OK) {
            return CommonResponse.error(ErrorEnum.WRONG_USERNAME_OR_PASSWORD);
        }
        CommonResponse respBody = resp.getBody();
        if (respBody.getCode() != cn.hutool.http.HttpStatus.HTTP_OK) {
            return respBody;
        }

        OAuthDinerInfo oAuthDinerInfo = BeanUtil.fillBeanWithMap((LinkedHashMap<String, Object>) respBody.getData(), new OAuthDinerInfo(), false);
        // 根据业务需求返回视图对象
        LoginDinerVO loginDinerVO = new LoginDinerVO();
        loginDinerVO.setNickname(oAuthDinerInfo.getNickname());
        loginDinerVO.setAvatarUrl(oAuthDinerInfo.getAvatarUrl());
        loginDinerVO.setToken(oAuthDinerInfo.getAccessToken());
        return CommonResponse.ok(loginDinerVO);
    }
}
