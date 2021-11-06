package com.aiolos.follow.controller;

import com.aiolos.commons.exception.CustomizedException;
import com.aiolos.commons.response.CommonResponse;
import com.aiolos.follow.service.FollowService;
import com.aiolos.food.controller.follow.FollowControllerApi;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Aiolos
 * @date 2021/10/4 9:12 下午
 */
@RestController
public class FollowController implements FollowControllerApi {

    private final FollowService followService;

    public FollowController(FollowService followService) {
        this.followService = followService;
    }

    @Override
    public CommonResponse follow(Integer followDinerId, int isFollowed, String access_token) throws CustomizedException {
        return followService.follow(followDinerId, isFollowed, access_token);
    }

    @Override
    public CommonResponse findCommonsFriends(Integer dinerId, String access_token) throws CustomizedException {
        return followService.findCommonsFriends(dinerId, access_token);
    }
}
