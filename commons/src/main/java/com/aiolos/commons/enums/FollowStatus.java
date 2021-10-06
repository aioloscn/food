package com.aiolos.commons.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Aiolos
 * @date 2021/10/4 8:46 下午
 */
@Getter
@AllArgsConstructor
public enum FollowStatus {
    FOLLOW(1, "关注"),
    UN_FOLLOW(0, "取关"),
    ;

    private int type;
    private String desc;
}

