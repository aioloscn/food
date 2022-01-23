package com.aiolos.food.pojo;

import com.baomidou.mybatisplus.annotation.TableName;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@TableName("order_detail")
public class OrderDetail implements Serializable {
    /**
     * 订单id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Integer id;

    /**
     * 状态
     */
    private String status;

    /**
     * 订单地址
     */
    private String address;

    /**
     * 用户id
     */
    @Column(name = "account_id")
    private Integer accountId;

    /**
     * 产品id
     */
    @Column(name = "product_id")
    private Integer productId;

    /**
     * 骑手id
     */
    @Column(name = "deliveryman_id")
    private Integer deliverymanId;

    /**
     * 结算id
     */
    @Column(name = "settlement_id")
    private Integer settlementId;

    /**
     * 积分奖励id
     */
    @Column(name = "reward_id")
    private Integer rewardId;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 时间
     */
    private Date date;

    private static final long serialVersionUID = 1L;

    /**
     * 获取订单id
     *
     * @return id - 订单id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置订单id
     *
     * @param id 订单id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取状态
     *
     * @return status - 状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置状态
     *
     * @param status 状态
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * 获取订单地址
     *
     * @return address - 订单地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置订单地址
     *
     * @param address 订单地址
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * 获取用户id
     *
     * @return account_id - 用户id
     */
    public Integer getAccountId() {
        return accountId;
    }

    /**
     * 设置用户id
     *
     * @param accountId 用户id
     */
    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    /**
     * 获取产品id
     *
     * @return product_id - 产品id
     */
    public Integer getProductId() {
        return productId;
    }

    /**
     * 设置产品id
     *
     * @param productId 产品id
     */
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    /**
     * 获取骑手id
     *
     * @return deliveryman_id - 骑手id
     */
    public Integer getDeliverymanId() {
        return deliverymanId;
    }

    /**
     * 设置骑手id
     *
     * @param deliverymanId 骑手id
     */
    public void setDeliverymanId(Integer deliverymanId) {
        this.deliverymanId = deliverymanId;
    }

    /**
     * 获取结算id
     *
     * @return settlement_id - 结算id
     */
    public Integer getSettlementId() {
        return settlementId;
    }

    /**
     * 设置结算id
     *
     * @param settlementId 结算id
     */
    public void setSettlementId(Integer settlementId) {
        this.settlementId = settlementId;
    }

    /**
     * 获取积分奖励id
     *
     * @return reward_id - 积分奖励id
     */
    public Integer getRewardId() {
        return rewardId;
    }

    /**
     * 设置积分奖励id
     *
     * @param rewardId 积分奖励id
     */
    public void setRewardId(Integer rewardId) {
        this.rewardId = rewardId;
    }

    /**
     * 获取价格
     *
     * @return price - 价格
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 设置价格
     *
     * @param price 价格
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 获取时间
     *
     * @return date - 时间
     */
    public Date getDate() {
        return date;
    }

    /**
     * 设置时间
     *
     * @param date 时间
     */
    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", status=").append(status);
        sb.append(", address=").append(address);
        sb.append(", accountId=").append(accountId);
        sb.append(", productId=").append(productId);
        sb.append(", deliverymanId=").append(deliverymanId);
        sb.append(", settlementId=").append(settlementId);
        sb.append(", rewardId=").append(rewardId);
        sb.append(", price=").append(price);
        sb.append(", date=").append(date);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}