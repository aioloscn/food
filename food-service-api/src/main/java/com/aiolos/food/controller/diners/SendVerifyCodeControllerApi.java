package com.aiolos.food.controller.diners;

import com.aiolos.commons.exception.CustomizedException;
import com.aiolos.commons.response.CommonResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Aiolos
 * @date 2021/8/20 5:59 下午
 */
@Api(tags = "验证码相关接口")
@RequestMapping("sms")
public interface SendVerifyCodeControllerApi {

    @ApiOperation(value = "获得短信验证码接口", httpMethod = "GET")
    @GetMapping("sendCode")
    CommonResponse send(String phone) throws CustomizedException;
}
