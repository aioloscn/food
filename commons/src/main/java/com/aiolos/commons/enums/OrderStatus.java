package com.aiolos.commons.enums;

/**
 * @author Aiolos
 * @date 2022/1/5 9:24 下午
 */
public enum OrderStatus {

    /**
     * 创建中
     */
    ORDER_CREATING,

    /**
     * 餐厅已确认
     */
    RESTAURANT_CONFIRMED,

    /**
     * 骑手已确认
     */
    DELIVERYMAN_CONFIRMED,

    /**
     * 已结清
     */
    SETTLEMENT_CONFIRMED,

    /**
     * 订单创建中
     */
    ORDER_CREATED,

    /**
     * 订单创建失败
     */
    FAILED,
    ;
}
