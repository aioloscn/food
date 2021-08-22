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
public enum VoucherOrderStatus {

    CANCELLED(-1, "已取消"),
    UNPAID(0, "未支付"),
    PAID(1, "已支付"),
    CONSUME(2, "已消费"),
    EXPIRED(3, "已过期"),
    ;

    private Integer type;
    private String desc;
}
