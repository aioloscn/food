package com.aiolos.food.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author Aiolos
 * @date 2021/8/19 3:41 上午
 */
@Getter
@Setter
@ApiModel(description = "登录用户信息")
public class SignInDinerInfo implements Serializable {

    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("头像")
    private String avatarUrl;

    @ApiModelProperty("角色")
    private String roles;
}
