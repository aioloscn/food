package com.aiolos.food.controller.seckill;

import com.aiolos.commons.exception.CustomizedException;
import com.aiolos.commons.exception.CustomizedRuntimeException;
import com.aiolos.commons.response.CommonResponse;
import com.aiolos.food.pojo.bo.SeckillVoucherInsertBO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * @author Aiolos
 * @date 2021/8/21 1:33 下午
 */
@Api(tags = "抢购代金券相关接口")
@RequestMapping
public interface SeckillControllerApi {

    @ApiOperation(value = "新增代金券活动", httpMethod = "POST")
    @PostMapping("add")
    CommonResponse add(@Valid @RequestBody SeckillVoucherInsertBO seckillVoucherInsertBO) throws CustomizedException;

    @ApiOperation(value = "秒杀下单", httpMethod = "POST")
    @PostMapping("{seckillVoucherId}")
    CommonResponse doSeckill(@PathVariable Integer seckillVoucherId, String access_token) throws CustomizedRuntimeException;
}
