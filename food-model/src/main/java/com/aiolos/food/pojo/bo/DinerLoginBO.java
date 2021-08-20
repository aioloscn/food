package com.aiolos.food.pojo.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author Aiolos
 * @date 2021/8/18 10:48 下午
 */
@ApiModel(description = "用户登录信息")
@Data
public class DinerLoginBO {

    @ApiModelProperty("手机号/账号/用户名")
    @NotBlank(message = "账号不能为空")
    private String account;

    @ApiModelProperty("密码")
    @NotBlank(message = "密码不能为空")
    private String password;
}
