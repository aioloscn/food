package com.aiolos.commons.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Aiolos
 * @date 2021/8/20 12:17 下午
 */
@Getter
@AllArgsConstructor
public enum RedisKeyEnum {

    VERIFY_CODE("verify_code:", "验证码"),
    INTERVAL_BETWEEN_SENDING_VERIFICATION_CODE("interval_between_sending_verification_code:", "发送验证码的间隔时间"),
    ;

    private String key;
    private String desc;
}
