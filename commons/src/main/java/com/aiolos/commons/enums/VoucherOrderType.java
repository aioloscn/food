package com.aiolos.commons.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Aiolos
 * @date 2021/8/21 6:03 下午
 */
@Getter
@AllArgsConstructor
public enum VoucherOrderType {

    NORMAL_ORDER(0, "正常订单"),
    PANIC_BUYING_ORDER(1, "抢购订单"),
    ;

    private Integer type;
    private String desc;
}
