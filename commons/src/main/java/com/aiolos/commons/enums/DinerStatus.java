package com.aiolos.commons.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 食客状态枚举
 * @author Aiolos
 * @date 2021/8/20 8:09 下午
 */
@Getter
@AllArgsConstructor
public enum DinerStatus {

    LOGGED_OUT(0, "已注销"),
    NORMAL(1, "正常"),
    ;

    private int type;
    private String desc;
}
