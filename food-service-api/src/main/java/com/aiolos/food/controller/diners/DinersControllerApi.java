package com.aiolos.food.controller.diners;

import com.aiolos.commons.exception.CustomizedException;
import com.aiolos.commons.response.CommonResponse;
import com.aiolos.food.pojo.bo.DinerLoginBO;
import com.aiolos.food.pojo.bo.DinerRegisterBO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * @author Aiolos
 * @date 2021/8/18 10:41 下午
 */
@Api(tags = "食客相关接口")
@RequestMapping
public interface DinersControllerApi {

    @ApiOperation(value = "登录功能", httpMethod = "POST")
    @PostMapping("signin")
    CommonResponse signIn(@Valid @RequestBody DinerLoginBO dinerLoginBO);

    @ApiOperation(value = "检查手机号是否已注册", httpMethod = "GET")
    @GetMapping("checkPhone")
    CommonResponse checkPhoneIsRegistered(String phone);

    @ApiOperation(value = "用户注册", httpMethod = "POST")
    @PostMapping("register")
    CommonResponse register(@Valid @RequestBody DinerRegisterBO dinerRegisterBO) throws CustomizedException;

    @ApiOperation(value = "根据ids查询食客信息", httpMethod = "GET")
    @GetMapping("findByIds")
    CommonResponse findByIds(String ids);
}
