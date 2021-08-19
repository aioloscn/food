package com.aiolos.food.controller.diners;

import com.aiolos.commons.response.CommonResponse;
import com.aiolos.food.pojo.bo.LoginDinerBO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
    CommonResponse signIn(@Valid @RequestBody LoginDinerBO loginDinerBO);
}
