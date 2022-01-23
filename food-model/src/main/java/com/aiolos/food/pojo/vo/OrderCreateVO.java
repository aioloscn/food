package com.aiolos.food.pojo.vo;

import lombok.Data;

/**
 * @author Aiolos
 * @date 2022/1/5 9:07 下午
 */
@Data
public class OrderCreateVO {

    private Integer accountId;
    private String address;
    private Integer productId;
}
