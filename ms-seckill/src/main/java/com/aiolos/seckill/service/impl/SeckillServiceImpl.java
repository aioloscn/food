package com.aiolos.seckill.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.http.HttpStatus;
import com.aiolos.commons.enums.ErrorEnum;
import com.aiolos.commons.enums.SeckillVoucherStatus;
import com.aiolos.commons.enums.VoucherOrderStatus;
import com.aiolos.commons.enums.VoucherOrderType;
import com.aiolos.commons.exception.CustomizedException;
import com.aiolos.commons.response.CommonResponse;
import com.aiolos.commons.utils.IdGeneratorSnowflake;
import com.aiolos.food.pojo.SeckillVouchers;
import com.aiolos.food.pojo.VoucherOrder;
import com.aiolos.food.pojo.bo.SeckillVoucherInsertBO;
import com.aiolos.food.pojo.vo.SignInDinerInfo;
import com.aiolos.seckill.mapper.SeckillVouchersMapper;
import com.aiolos.seckill.mapper.VoucherOrderMapper;
import com.aiolos.seckill.service.SeckillService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.LinkedHashMap;

/**
 * @author Aiolos
 * @date 2021/8/21 2:59 上午
 */
@Service
public class SeckillServiceImpl implements SeckillService {

    private final SeckillVouchersMapper seckillVouchersMapper;
    private final VoucherOrderMapper voucherOrderMapper;
    private final RestTemplate restTemplate;
    private final IdGeneratorSnowflake idWorker;

    @Value("${service.name.ms-oauth-server}")
    private String oauthServerName;

    public SeckillServiceImpl(SeckillVouchersMapper seckillVouchersMapper, VoucherOrderMapper voucherOrderMapper, RestTemplate restTemplate, IdGeneratorSnowflake idWorker) {
        this.seckillVouchersMapper = seckillVouchersMapper;
        this.voucherOrderMapper = voucherOrderMapper;
        this.restTemplate = restTemplate;
        this.idWorker = idWorker;
    }

    @Transactional(propagation = Propagation.NESTED, rollbackFor = CustomizedException.class)
    @Override
    public CommonResponse addSeckillVouchers(SeckillVoucherInsertBO seckillVoucherInsertBO) throws CustomizedException {
        SeckillVouchers seckillVouchers = new SeckillVouchers();
        BeanUtil.copyProperties(seckillVoucherInsertBO, seckillVouchers);
        seckillVouchers.setIsValid(SeckillVoucherStatus.NORMAL.getType());
        seckillVouchers.setCreateDate(new Date());
        seckillVouchers.setUpdateDate(new Date());
        int resultCount = seckillVouchersMapper.insert(seckillVouchers);
        if (resultCount != 1) {
            try {
                throw new RuntimeException();
            } catch (Exception e) {
                throw new CustomizedException(ErrorEnum.ACTIVITY_RELEASE_FAILED);
            }
        }
        return CommonResponse.ok();
    }

    @Transactional(propagation = Propagation.NESTED, rollbackFor = CustomizedException.class)
    @Override
    public CommonResponse doSeckill(Integer voucherId, String accessToken) throws CustomizedException {
        SeckillVouchers seckillVouchers = seckillVouchersMapper.selectById(voucherId);
        if (seckillVouchers == null)
            return CommonResponse.error(ErrorEnum.NO_RELATED_ACTIVITIES);
        if (seckillVouchers.getIsValid().equals(SeckillVoucherStatus.OVER.getType()))
            return CommonResponse.error(ErrorEnum.ACTIVITY_HAS_ENDED);
        // 判断活动是否已开始/结束
        Date now = new Date();
        if (now.before(seckillVouchers.getStartTime()))
            return CommonResponse.error(ErrorEnum.ACTIVITY_HAS_NOT_YES_STARTED);
        if (now.after(seckillVouchers.getEndTime()))
            return CommonResponse.error(ErrorEnum.ACTIVITY_HAS_ENDED);
        // 判断是否已经卖完
        if (seckillVouchers.getAmount() < 1)
            return CommonResponse.error(ErrorEnum.VOUCHERS_ARE_SOLD_OUT);
        // 获取登录用户信息
        String url = oauthServerName + "user/me?access_token={accessToken}";
        CommonResponse commonResponse = restTemplate.getForObject(url, CommonResponse.class, accessToken);
        // 处理返回结果
        if (commonResponse.getCode() != HttpStatus.HTTP_OK)
            return CommonResponse.error(ErrorEnum.USER_NOT_LOGGED_IN);
        // 这里的 data 是一个 LinkedHashMap，SignInDinerInfo
        SignInDinerInfo signInDinerInfo = BeanUtil.fillBeanWithMap((LinkedHashMap) commonResponse.getData(), new SignInDinerInfo(), false);
        // 判断该用户是否已经抢购过该代金券
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("fk_seckill_id", seckillVouchers.getId());
        queryWrapper.eq("fk_diner_id", signInDinerInfo.getId());
        queryWrapper.ne("status", VoucherOrderStatus.CANCELLED.getType());
        VoucherOrder voucherOrder = voucherOrderMapper.selectOne(queryWrapper);
        if (voucherOrder != null)
            return CommonResponse.error(ErrorEnum.PURCHASED_THE_VOUCHER);

        // 扣库存
        seckillVouchers.setAmount(seckillVouchers.getAmount() - 1);
        int resultCount = seckillVouchersMapper.updateById(seckillVouchers);
        if (resultCount != 1) {
            try {
                throw new RuntimeException();
            } catch (Exception e) {
                throw new CustomizedException(ErrorEnum.VOUCHERS_ARE_SOLD_OUT);
            }
        }

        // 下单
        voucherOrder = new VoucherOrder();
        voucherOrder.setFkSeckillId(voucherId);
        voucherOrder.setFkDinerId(signInDinerInfo.getId());
        voucherOrder.setFkVoucherId(seckillVouchers.getFkVoucherId());
        voucherOrder.setOrderNo(idWorker.nextId());
        voucherOrder.setOrderType(VoucherOrderType.PANIC_BUYING_ORDER.getType());
        voucherOrder.setStatus(VoucherOrderStatus.UNPAID.getType());
        voucherOrder.setCreateDate(new Date());
        voucherOrder.setUpdateDate(new Date());
        int insertCount = voucherOrderMapper.insert(voucherOrder);
        if (insertCount != 1) {
            try {
                throw new RuntimeException();
            } catch (Exception e) {
                throw new CustomizedException(ErrorEnum.PANIC_BUYING_FAILED);
            }
        }
        return CommonResponse.ok();
    }
}
