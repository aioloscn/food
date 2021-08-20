package com.aiolos.diners.service;

import com.aiolos.commons.exception.CustomizedException;
import com.aiolos.commons.response.CommonResponse;

/**
 * 发送验证码业务逻辑层
 * @author Aiolos
 * @date 2021/8/20 4:54 下午
 */
public interface SendVerifyCodeService {

    /**
     * 发送验证码
     * @param phone
     */
    CommonResponse send(String phone) throws CustomizedException;

    String getCodeByPhone(String phone);
}
