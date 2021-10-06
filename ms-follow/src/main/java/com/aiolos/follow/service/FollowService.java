package com.aiolos.follow.service;

import com.aiolos.commons.exception.CustomizedException;
import com.aiolos.commons.response.CommonResponse;
import com.aiolos.food.pojo.vo.ShortDinerInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Aiolos
 * @date 2021/8/24 3:20 下午
 */
@Service
public interface FollowService {

    /**
     * 关注/取关
     * @param followDinerId     关注的食客ID
     * @param isFollowed        是否关注，1: 关注，0: 取关
     * @param accessToken       登录用户token
     * @return
     */
    CommonResponse follow(Integer followDinerId, int isFollowed, String accessToken) throws CustomizedException;

}
