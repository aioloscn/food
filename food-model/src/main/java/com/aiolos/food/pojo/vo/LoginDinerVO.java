package com.aiolos.food.pojo.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author Aiolos
 * @date 2021/8/18 10:27 下午
 */
@Getter
@Setter
public class LoginDinerVO implements Serializable {

    private String nickname;
    private String avatarUrl;
    private String token;
}
