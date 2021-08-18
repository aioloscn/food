package com.aiolos.food.pojo.bo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author Aiolos
 * @date 2021/8/18 10:48 下午
 */
@Data
public class LoginDinerBO {

    @NotBlank(message = "账号不能为空")
    private String account;

    @NotBlank(message = "密码不能为空")
    private String password;
}
