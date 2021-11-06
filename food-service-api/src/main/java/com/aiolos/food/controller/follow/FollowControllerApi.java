package com.aiolos.food.controller.follow;

import com.aiolos.commons.exception.CustomizedException;
import com.aiolos.commons.response.CommonResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @author Aiolos
 * @date 2021/10/4 9:08 下午
 */
@Api(tags = "关注相关接口")
@RequestMapping
public interface FollowControllerApi {

    @ApiOperation(value = "关注/取关", httpMethod = "POST")
    @PostMapping("/{followDinerId}")
    CommonResponse follow(@PathVariable Integer followDinerId, @RequestParam int isFollowed, String access_token) throws CustomizedException;

    @ApiOperation(value = "查看共同关注列表", httpMethod = "GET")
    @GetMapping("commons/{dinerId}")
    CommonResponse findCommonsFriends(@PathVariable Integer dinerId, String access_token) throws CustomizedException;
}
