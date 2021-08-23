package com.aiolos.seckill.service;

import com.aiolos.commons.exception.CustomizedException;
import com.aiolos.commons.exception.CustomizedRuntimeException;
import com.aiolos.commons.response.CommonResponse;
import com.aiolos.food.pojo.bo.SeckillVoucherInsertBO;

/**
 * 秒杀业务逻辑层
 * @author Aiolos
 * @date 2021/8/21 2:57 上午
 */
public interface SeckillService {

    /**
     * 添加代金券
     * @param seckillVoucherInsertBO
     * @return
     */
    CommonResponse addSeckillVouchers(SeckillVoucherInsertBO seckillVoucherInsertBO) throws CustomizedException;

    /**
     * 抢购代金券
     * @param seckillVoucherId       代金券 ID
     * @param accessToken   登录 token
     * @return
     */
    CommonResponse doSeckill(Integer seckillVoucherId, String accessToken) throws CustomizedRuntimeException;
}
