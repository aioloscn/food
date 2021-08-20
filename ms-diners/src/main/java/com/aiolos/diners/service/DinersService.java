package com.aiolos.diners.service;

import com.aiolos.commons.exception.CustomizedException;
import com.aiolos.commons.response.CommonResponse;
import com.aiolos.food.pojo.bo.DinerRegisterBO;

/**
 * @author Aiolos
 * @date 2021/8/16 5:22 下午
 */
public interface DinersService {

    /**
     * 登录
     * @param account   账号：用户名或手机或邮箱
     * @param password  密码
     * @return
     */
    CommonResponse signIn(String account, String password);

    /**
     * 检查手机号是否已注册
     * @param phone
     * @return
     */
    CommonResponse checkPhoneIsRegistered(String phone);

    /**
     * 注册
     * @param dinerRegisterBO
     * @return
     */
    CommonResponse register(DinerRegisterBO dinerRegisterBO) throws CustomizedException;
}
