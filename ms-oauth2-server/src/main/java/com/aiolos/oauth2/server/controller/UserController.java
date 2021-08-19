package com.aiolos.oauth2.server.controller;

import cn.hutool.core.bean.BeanUtil;
import com.aiolos.commons.domain.SignInIdentity;
import com.aiolos.commons.response.CommonResponse;
import com.aiolos.food.controller.oauth.UserControllerApi;
import com.aiolos.food.pojo.vo.SignInDinerInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Aiolos
 * @date 2021/8/19 5:01 下午
 */
@RestController
public class UserController implements UserControllerApi {

    @Resource
    private RedisTokenStore redisTokenStore;

    @Override
    public CommonResponse getCurrentUser(Authentication authentication) {
        // 获取用户的登录信息
        SignInIdentity signInIdentity = (SignInIdentity) authentication.getPrincipal();
        // 转为前端可用的视图对象
        SignInDinerInfo dinerInfo = new SignInDinerInfo();
        BeanUtil.copyProperties(signInIdentity, dinerInfo);
        return CommonResponse.ok(dinerInfo);
    }

    @Override
    public CommonResponse logout(String access_token, Authentication authentication) {
        // 判断 access_token 是否为空，为空将 authorization 赋值给 access_token
        if (StringUtils.isBlank(access_token)) {
            access_token = ((OAuth2AuthenticationDetails) authentication.getDetails()).getTokenValue();
        }
        // 判断 authorization 是否为空
        if (StringUtils.isBlank(access_token))
            return CommonResponse.ok("退出成功");
        // 清除 redis token 信息
        OAuth2AccessToken oAuth2AccessToken = redisTokenStore.readAccessToken(access_token);
        if (oAuth2AccessToken != null) {
            redisTokenStore.removeAccessToken(oAuth2AccessToken);
            OAuth2RefreshToken refreshToken = oAuth2AccessToken.getRefreshToken();
            redisTokenStore.removeRefreshToken(refreshToken);
        }
        return CommonResponse.ok("退出成功");
    }
}
