package com.aiolos.seckill.controller;

import com.aiolos.commons.exception.CustomizedException;
import com.aiolos.commons.exception.CustomizedRuntimeException;
import com.aiolos.commons.response.CommonResponse;
import com.aiolos.food.controller.seckill.SeckillControllerApi;
import com.aiolos.food.pojo.bo.SeckillVoucherInsertBO;
import com.aiolos.seckill.service.SeckillService;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Aiolos
 * @date 2021/8/21 1:46 下午
 */
@RestController
public class SeckillController implements SeckillControllerApi {

    private final SeckillService seckillService;

    public SeckillController(SeckillService seckillService) {
        this.seckillService = seckillService;
    }

    @Override
    public CommonResponse add(@Valid SeckillVoucherInsertBO seckillVoucherInsertBO) throws CustomizedException {
        return seckillService.addSeckillVouchers(seckillVoucherInsertBO);
    }

    @Override
    public CommonResponse doSeckill(Integer seckillVoucherId, String access_token) throws CustomizedRuntimeException {
        return seckillService.doSeckill(seckillVoucherId, access_token);
    }
}
