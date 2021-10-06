package com.aiolos.follow.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.http.HttpStatus;
import com.aiolos.commons.enums.ErrorEnum;
import com.aiolos.commons.enums.FollowStatus;
import com.aiolos.commons.enums.RedisKeyEnum;
import com.aiolos.commons.exception.CustomizedException;
import com.aiolos.commons.exception.CustomizedRuntimeException;
import com.aiolos.commons.response.CommonResponse;
import com.aiolos.follow.mapper.FollowMapper;
import com.aiolos.follow.service.FollowService;
import com.aiolos.food.pojo.Follow;
import com.aiolos.food.pojo.vo.SignInDinerInfo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.LinkedHashMap;

/**
 * @author Aiolos
 * @date 2021/8/31 6:54 下午
 */
@Slf4j
@Service
public class FollowServiceImpl implements FollowService {

    @Value("${service.name.ms-oauth-server}")
    private String oauthServerName;

    private final RestTemplate restTemplate;
    private final RedisTemplate redisTemplate;
    private final FollowMapper followMapper;

    public FollowServiceImpl(RestTemplate restTemplate, RedisTemplate redisTemplate, FollowMapper followMapper) {
        this.restTemplate = restTemplate;
        this.redisTemplate = redisTemplate;
        this.followMapper = followMapper;
    }

    /**
     * 关注/取关
     * @param followDinerId     关注的食客ID
     * @param isFollowed        是否关注，1: 关注，0: 取关
     * @param accessToken       登录用户token
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public CommonResponse follow(Integer followDinerId, int isFollowed, String accessToken) throws CustomizedException {
        // 是否选择了关注对象
        if (followDinerId == null || followDinerId < 1) {
            return CommonResponse.error(ErrorEnum.LACK_OF_FOLLOWER_INFORMATION);
        }
        // 获取登录用户信息
        SignInDinerInfo signInDinerInfo = loadSignInDinerInfo(accessToken);
        // 获取当前登录用户与需要关注用户的关注信息
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("diner_id", signInDinerInfo.getId());
        queryWrapper.eq("follow_diner_id", followDinerId);
        Follow follow = followMapper.selectOne(queryWrapper);
        // 如果没有关注信息，且要进行关注操作 -- 添加关注
        if (follow == null && isFollowed == FollowStatus.FOLLOW.getType()) {
            Follow f = new Follow();
            f.setDinerId(signInDinerInfo.getId());
            f.setFollowDinerId(followDinerId);
            f.setIsValid(FollowStatus.FOLLOW.getType());
            f.setCreateDate(new Date());
            f.setUpdateDate(new Date());
            int affectedCount = followMapper.insert(f);
            if (affectedCount != 1) {
                // 抛出 un checked 异常事务回滚
                throw new CustomizedRuntimeException(ErrorEnum.FOLLOW_FAILED);
            }
            addToRedisSet(signInDinerInfo.getId(), followDinerId);
            return CommonResponse.ok();
        }
        // 如果有关注信息，且目前处于关注状态，且要进行取关操作 -- 取消关注
        if (follow != null && follow.getIsValid() == FollowStatus.FOLLOW.getType() && isFollowed == FollowStatus.UN_FOLLOW.getType()) {
            follow.setIsValid(isFollowed);
            int affectedCount = followMapper.updateById(follow);
            if (affectedCount != 1) {
                throw new CustomizedRuntimeException(ErrorEnum.UNFOLLOW_FAILED);
            }
            removeFromRedisSet(signInDinerInfo.getId(), followDinerId);
            return CommonResponse.ok();
        }
        // 如果有关注信息，且目前处于取关状态，且要进行关注操作 -- 重新关注
        if (follow != null && follow.getIsValid() == FollowStatus.UN_FOLLOW.getType() && isFollowed == FollowStatus.FOLLOW.getType()) {
            follow.setIsValid(isFollowed);
            int affectedCount = followMapper.updateById(follow);
            if (affectedCount != 1) {
                throw new CustomizedRuntimeException(ErrorEnum.FOLLOW_FAILED);
            }
            addToRedisSet(signInDinerInfo.getId(), followDinerId);
            return CommonResponse.ok();
        }
        return CommonResponse.ok();
    }

    /**
     * 添加关注列表到 Redis
     * @param dinerId                关注者
     * @param followDinerId     被关注者
     */
    private void addToRedisSet(Integer dinerId, Integer followDinerId) {
        redisTemplate.opsForSet().add(RedisKeyEnum.FOLLOWING.getKey() + dinerId, followDinerId);
        redisTemplate.opsForSet().add(RedisKeyEnum.FOLLOWERS.getKey() + followDinerId, dinerId);
    }

    /**
     * 移除 Redis 关注列表
     * @param dinerId
     * @param followDinerId
     */
    private void removeFromRedisSet(Integer dinerId, Integer followDinerId) {
        redisTemplate.opsForSet().remove(RedisKeyEnum.FOLLOWING.getKey() + dinerId, followDinerId);
        redisTemplate.opsForSet().remove(RedisKeyEnum.FOLLOWERS.getKey() + followDinerId, dinerId);
    }

    /**
     * 获取登录用户信息
     * @param accessToken
     * @return
     */
    private SignInDinerInfo loadSignInDinerInfo(String accessToken) throws CustomizedException {
        if (StringUtils.isBlank(accessToken)) {
            throw new CustomizedException(ErrorEnum.TOKEN_INVALID);
        }
        String url = oauthServerName + "user/me?access_token={accessToken}";
        CommonResponse resp = restTemplate.getForObject(url, CommonResponse.class, accessToken);
        if (resp.getCode() != HttpStatus.HTTP_OK) {
            log.info(resp.getMsg());
            throw new CustomizedException(ErrorEnum.USER_NOT_LOGGED_IN);
        }
        SignInDinerInfo dinerInfo = BeanUtil.fillBeanWithMap((LinkedHashMap) resp.getData(), new SignInDinerInfo(), false);
        return dinerInfo;
    }
}
