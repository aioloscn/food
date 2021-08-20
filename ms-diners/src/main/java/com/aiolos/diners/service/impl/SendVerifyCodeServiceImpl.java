package com.aiolos.diners.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.aiolos.commons.enums.ErrorEnum;
import com.aiolos.commons.enums.RedisKeyEnum;
import com.aiolos.commons.exception.CustomizedException;
import com.aiolos.commons.response.CommonResponse;
import com.aiolos.commons.utils.SMSUtils;
import com.aiolos.diners.service.SendVerifyCodeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author Aiolos
 * @date 2021/8/20 4:55 下午
 */
@Service
public class SendVerifyCodeServiceImpl implements SendVerifyCodeService {

    private final RedisTemplate<String, String> redisTemplate;

    private final SMSUtils smsUtils;

    public SendVerifyCodeServiceImpl(RedisTemplate<String, String> redisTemplate, SMSUtils smsUtils) {
        this.redisTemplate = redisTemplate;
        this.smsUtils = smsUtils;
    }

    @Override
    public CommonResponse send(String phone) throws CustomizedException {
        if (StringUtils.isBlank(phone))
            return CommonResponse.error(ErrorEnum.PHONE_INCORRECT);
        if (redisTemplate.hasKey(RedisKeyEnum.INTERVAL_BETWEEN_SENDING_VERIFICATION_CODE.getKey() + phone))
            return CommonResponse.error(ErrorEnum.REPEAT_SENDING_SMS_CODE);
        String code = RandomUtil.randomNumbers(6);
        // 限制用户60秒内只能获得一次验证码
        redisTemplate.opsForValue().set(RedisKeyEnum.INTERVAL_BETWEEN_SENDING_VERIFICATION_CODE.getKey() + phone, code, 60, TimeUnit.SECONDS);
//        smsUtils.sendSMS(phone, code);
        // 保存验证码用于登录时验证
        redisTemplate.opsForValue().set(RedisKeyEnum.VERIFY_CODE.getKey() + phone, code, 60 * 5, TimeUnit.SECONDS);
        return CommonResponse.ok();
    }

    /**
     * 根据手机号获取验证码
     * @param phone
     * @return
     */
    @Override
    public String getCodeByPhone(String phone) {
        String key = RedisKeyEnum.VERIFY_CODE.getKey() + phone;
        return redisTemplate.opsForValue().get(key);
    }
}
