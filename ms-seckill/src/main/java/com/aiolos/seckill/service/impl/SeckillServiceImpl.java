package com.aiolos.seckill.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.http.HttpStatus;
import com.aiolos.commons.enums.*;
import com.aiolos.commons.exception.CustomizedException;
import com.aiolos.commons.exception.CustomizedRuntimeException;
import com.aiolos.commons.response.CommonResponse;
import com.aiolos.commons.utils.IdGeneratorSnowflake;
import com.aiolos.food.pojo.SeckillVouchers;
import com.aiolos.food.pojo.VoucherOrder;
import com.aiolos.food.pojo.bo.SeckillVoucherInsertBO;
import com.aiolos.food.pojo.vo.SignInDinerInfo;
import com.aiolos.seckill.mapper.SeckillVouchersMapper;
import com.aiolos.seckill.mapper.VoucherOrderMapper;
import com.aiolos.seckill.model.RedisLock;
import com.aiolos.seckill.service.SeckillService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author Aiolos
 * @date 2021/8/21 2:59 上午
 */
@Service
public class SeckillServiceImpl implements SeckillService {

    private final SeckillVouchersMapper seckillVouchersMapper;
    private final VoucherOrderMapper voucherOrderMapper;
    private final RestTemplate restTemplate;
    private final RedisTemplate redisTemplate;
    private final DefaultRedisScript defaultRedisScript;
    private final RedisLock redisLock;
    private final RedissonClient redissonClient;
    private final IdGeneratorSnowflake idWorker;

    @Value("${service.name.ms-oauth-server}")
    private String oauthServerName;

    public SeckillServiceImpl(SeckillVouchersMapper seckillVouchersMapper, VoucherOrderMapper voucherOrderMapper, RestTemplate restTemplate,
                              RedisTemplate redisTemplate, DefaultRedisScript defaultRedisScript, RedisLock redisLock, RedissonClient redissonClient, IdGeneratorSnowflake idWorker) {
        this.seckillVouchersMapper = seckillVouchersMapper;
        this.voucherOrderMapper = voucherOrderMapper;
        this.restTemplate = restTemplate;
        this.redisTemplate = redisTemplate;
        this.defaultRedisScript = defaultRedisScript;
        this.redisLock = redisLock;
        this.redissonClient = redissonClient;
        this.idWorker = idWorker;
    }

    @Transactional(propagation = Propagation.NESTED, rollbackFor = CustomizedException.class)
    @Override
    public CommonResponse addSeckillVouchers(SeckillVoucherInsertBO seckillVoucherInsertBO) throws CustomizedException {
        SeckillVouchers seckillVouchers = new SeckillVouchers();
        BeanUtil.copyProperties(seckillVoucherInsertBO, seckillVouchers);
        seckillVouchers.setId(1);
        seckillVouchers.setIsValid(SeckillVoucherStatus.NORMAL.getType());
        seckillVouchers.setCreateDate(new Date());
        seckillVouchers.setUpdateDate(new Date());
        /*int resultCount = seckillVouchersMapper.insert(seckillVouchers);
        if (resultCount != 1) {
            try {
                throw new RuntimeException();
            } catch (Exception e) {
                throw new CustomizedException(ErrorEnum.ACTIVITY_RELEASE_FAILED);
            }
        }
        return CommonResponse.ok();*/

        String key = RedisKeyEnum.SECKILL_VOUCHERS.getKey() + seckillVoucherInsertBO.getFkVoucherId();
        // 查看 Redis 中是否已经存在该券的秒杀活动
        Map<String, Object> map = redisTemplate.opsForHash().entries(key);
        if (!map.isEmpty() && (int) map.get("amount") > 0)
            return CommonResponse.error(ErrorEnum.HAS_ALREADY_STARTED_A_PANIC_BUYING_ACTIVITY);

        // 保存到 Redis
        redisTemplate.opsForHash().putAll(key, BeanUtil.beanToMap(seckillVouchers));
        return CommonResponse.ok();
    }

    @Transactional(propagation = Propagation.NESTED, rollbackFor = CustomizedRuntimeException.class)
    @Override
    public CommonResponse doSeckill(Integer seckillVoucherId, String accessToken) throws CustomizedRuntimeException {
//        SeckillVouchers seckillVouchers = seckillVouchersMapper.selectById(seckillVoucherId);

        String key = RedisKeyEnum.SECKILL_VOUCHERS.getKey() + seckillVoucherId;
        Map<String, Object> map = redisTemplate.opsForHash().entries(key);
        SeckillVouchers seckillVouchers = BeanUtil.mapToBean(map, SeckillVouchers.class, true, null);
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
        // 秒杀活动不保存到数据库了，所以这个值不需要维护了
//        queryWrapper.eq("fk_seckill_id", seckillVouchers.getId());
        queryWrapper.eq("fk_voucher_id", seckillVouchers.getFkVoucherId());
        queryWrapper.eq("fk_diner_id", signInDinerInfo.getId());
        queryWrapper.between("status", VoucherOrderStatus.UNPAID.getType(), VoucherOrderStatus.PAID.getType());
        List<VoucherOrder> voucherOrders = voucherOrderMapper.selectList(queryWrapper);
        if (voucherOrders != null && voucherOrders.size() > 0)
            return CommonResponse.error(ErrorEnum.PURCHASED_THE_VOUCHER);

        // 扣库存
        /*seckillVouchers.setAmount(seckillVouchers.getAmount() - 1);
        int resultCount = seckillVouchersMapper.updateById(seckillVouchers);
        if (resultCount != 1) {
            try {
                throw new RuntimeException();
            } catch (Exception e) {
                throw new CustomizedException(ErrorEnum.VOUCHERS_ARE_SOLD_OUT);
            }
        }*/

        // 使用 Redis 锁一个账号只能购买一次
        String lockName = RedisKeyEnum.LOCK_KEY.getKey() + signInDinerInfo.getId() + ":" + seckillVoucherId;
        // 过期时间 = 活动结束时间 - 当前时间；+60s防止活动结束删除缓存的同时还有线程堆积在这里，重复消费
        long expireTime = (seckillVouchers.getEndTime().getTime() - now.getTime()) / 1000 + 60;
        // 自定义 Redis 分布式锁
//        String lockKey = redisLock.tryLock(lockName, expireTime);

        // Redisson 分布式锁
        RLock lock = redissonClient.getLock(lockName);

        try {
            // 不为空意味着拿到锁了
//            if (StringUtils.isNotBlank(lockKey)) {
            boolean isLocked = lock.tryLock(0, expireTime, TimeUnit.SECONDS);
            if (isLocked) {
                // 下单
                VoucherOrder voucherOrder = new VoucherOrder();
                // 秒杀活动不保存到数据库了，所以这个值不需要维护了
//        voucherOrder.setFkSeckillId(seckillVoucherId);
                voucherOrder.setFkDinerId(signInDinerInfo.getId());
                voucherOrder.setFkVoucherId(seckillVouchers.getFkVoucherId());
                voucherOrder.setOrderNo(idWorker.nextId());
                voucherOrder.setOrderType(VoucherOrderType.PANIC_BUYING_ORDER.getType());
                voucherOrder.setStatus(VoucherOrderStatus.UNPAID.getType());
                voucherOrder.setCreateDate(new Date());
                voucherOrder.setUpdateDate(new Date());
                int insertCount = voucherOrderMapper.insert(voucherOrder);
                if (insertCount != 1) {
                    throw new CustomizedRuntimeException(ErrorEnum.PANIC_BUYING_FAILED);
                }

                // 采用 Redis + Lua 原子操作扣库存
                List<String> keys = new ArrayList<>();
                keys.add(key);
                keys.add("amount");
                // 返回扣减前的值
                Long amount = (Long) redisTemplate.execute(defaultRedisScript, keys);
                // 回滚
                if (amount == null || amount < 1)
                    throw new CustomizedRuntimeException(ErrorEnum.VOUCHERS_ARE_SOLD_OUT);
            } else {
                return CommonResponse.error(ErrorEnum.PURCHASED_THE_VOUCHER);
            }
        } catch (Exception e) {
            // 手动回滚事务
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // 自定义 Redis 释放锁
//            redisLock.unlock(lockName, lockKey);

            // Redisson 释放锁
            lock.unlock();
            if (e instanceof CustomizedRuntimeException)
                return CommonResponse.error(ErrorEnum.VOUCHERS_ARE_SOLD_OUT);
        }
        // 正常执行完成后不释放锁，直到活动结束
        return CommonResponse.ok();
    }
}
