package com.aiolos.food.pojo.dto;

import com.aiolos.commons.enums.OrderStatus;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Aiolos
 * @date 2022/1/5 9:32 下午
 */
@Data
public class OrderMessage {

    private Integer orderId;
    private OrderStatus orderStatus;
    private BigDecimal price;
    private Integer deliverymanId;
    private Integer productId;
    private Integer accountId;
    private Integer settlementId;
    /**
     * 积分结算ID
     */
    private Integer rewardId;

    /**
     * 积分奖励数量
     */
    private Integer rewardAmount;
    private Boolean confirmed;
}
