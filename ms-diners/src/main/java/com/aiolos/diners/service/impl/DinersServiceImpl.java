package com.aiolos.diners.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.aiolos.commons.domain.OAuthDinerInfo;
import com.aiolos.commons.enums.DinerStatus;
import com.aiolos.commons.enums.ErrorEnum;
import com.aiolos.commons.enums.RedisKeyEnum;
import com.aiolos.commons.exception.CustomizedException;
import com.aiolos.commons.response.CommonResponse;
import com.aiolos.diners.config.OAuth2ClientConfiguration;
import com.aiolos.diners.mapper.DinersMapper;
import com.aiolos.diners.service.DinersService;
import com.aiolos.diners.service.SendVerifyCodeService;
import com.aiolos.food.pojo.Diners;
import com.aiolos.food.pojo.bo.DinerRegisterBO;
import com.aiolos.food.pojo.vo.LoginDinerVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.*;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.LinkedHashMap;

/**
 * @author Aiolos
 * @date 2021/8/18 10:02 下午
 */
@Service
public class DinersServiceImpl implements DinersService {

    private final RestTemplate restTemplate;
    private final OAuth2ClientConfiguration oAuth2ClientConfiguration;
    private final DinersMapper dinersMapper;
    private final SendVerifyCodeService sendVerifyCodeService;
    private final RedisTemplate<String, String> redisTemplate;

    @Value("${service.name.ms-oauth-server}")
    private String oauthServerName;

    public DinersServiceImpl(RestTemplate restTemplate, OAuth2ClientConfiguration oAuth2ClientConfiguration, DinersMapper dinersMapper, SendVerifyCodeService sendVerifyCodeService, RedisTemplate<String, String> redisTemplate) {
        this.restTemplate = restTemplate;
        this.oAuth2ClientConfiguration = oAuth2ClientConfiguration;
        this.dinersMapper = dinersMapper;
        this.sendVerifyCodeService = sendVerifyCodeService;
        this.redisTemplate = redisTemplate;
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

    @Override
    public CommonResponse checkPhoneIsRegistered(String phone) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("phone", phone);
        Diners diners = dinersMapper.selectOne(queryWrapper);
        if (diners != null && diners.getIsValid() == 0)
            return CommonResponse.error(ErrorEnum.ACCOUNT_FROZEN);
        return CommonResponse.ok(diners);
    }

    @Transactional(propagation = Propagation.NESTED, rollbackFor = CustomizedException.class)
    @Override
    public CommonResponse register(DinerRegisterBO dinerRegisterBO) throws CustomizedException {
        String code = sendVerifyCodeService.getCodeByPhone(dinerRegisterBO.getPhone());
        // 验证码是否过期
        if (StringUtils.isBlank(code)) {
            return CommonResponse.error(ErrorEnum.SMS_CODE_EXPIRED);
        }
        // 验证码一致性校验
        if (!dinerRegisterBO.getVerifyCode().equals(code))
            return CommonResponse.error(ErrorEnum.SMS_CODE_INCORRECT);

        // 校验用户是否已注册
        CommonResponse commonResponse = this.checkPhoneIsRegistered(dinerRegisterBO.getPhone());
        if (commonResponse.getCode().equals(cn.hutool.http.HttpStatus.HTTP_OK) && commonResponse.getData() != null)
            return CommonResponse.error(ErrorEnum.REGISTER_DUP_FAILED);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("username", dinerRegisterBO.getUsername());
        Diners diners = dinersMapper.selectOne(queryWrapper);
        if (diners != null)
            return CommonResponse.error(ErrorEnum.USERNAME_EXIST_ERROR);

        // 密码加密
        diners = new Diners();
        BeanUtil.copyProperties(dinerRegisterBO, diners);
        diners.setPassword(DigestUtil.md5Hex(dinerRegisterBO.getPassword()));
        diners.setNickname(diners.getUsername());
        diners.setRoles("ROLE_USER");
        diners.setIsValid(DinerStatus.NORMAL.getType());
        diners.setCreateDate(new Date());
        diners.setUpdateDate(new Date());
        int resultCount = dinersMapper.insert(diners);
        if (resultCount != 1) {
            try {
                throw new RuntimeException();
            } catch (Exception e) {
                throw new CustomizedException(ErrorEnum.REGISTER_FAILED);
            }
        }

        // 删除验证码，一个验证码只能使用一次
        redisTemplate.delete(RedisKeyEnum.VERIFY_CODE.getKey() + diners.getPhone());
        // 自动登录
        return this.signIn(dinerRegisterBO.getUsername(), dinerRegisterBO.getPassword());
    }
}
