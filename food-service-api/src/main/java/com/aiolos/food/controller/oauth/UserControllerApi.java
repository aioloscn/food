package com.aiolos.food.controller.oauth;

import com.aiolos.commons.response.CommonResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Aiolos
 * @date 2021/8/19 4:58 下午
 */
@Api(tags = "用户相关接口")
@RequestMapping("user")
public interface UserControllerApi {

    @ApiOperation(value = "获取用户信息", httpMethod = "GET")
    @GetMapping("me")
    CommonResponse getCurrentUser(Authentication authentication);

    @ApiOperation(value = "退出", httpMethod = "GET")
    @GetMapping("logout")
    CommonResponse logout(String access_token, Authentication authentication);
}
