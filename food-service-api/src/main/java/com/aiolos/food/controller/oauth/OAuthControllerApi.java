package com.aiolos.food.controller.oauth;

import com.aiolos.commons.response.CommonResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Map;

/**
 * @author Aiolos
 * @date 2021/8/19 4:34 上午
 */
@Api(tags = "登录认证相关接口")
@RequestMapping("oauth")
public interface OAuthControllerApi {

    @ApiOperation(value = "登录认证中心", notes = "重构认证授权中心增强令牌返回结果", httpMethod = "POST")
    @PostMapping("token")
    CommonResponse postAccessToken(Principal principal, @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException;
}
