package com.aiolos.diners.service;

import com.aiolos.commons.response.CommonResponse;

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
}
