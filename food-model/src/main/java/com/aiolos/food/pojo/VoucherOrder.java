package com.aiolos.food.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@TableName("t_voucher_order")
public class VoucherOrder implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    @TableId(type = IdType.AUTO)
    private Integer id;

    @Column(name = "order_no")
    private Long orderNo;

    @Column(name = "fk_voucher_id")
    private Integer fkVoucherId;

    @Column(name = "fk_diner_id")
    private Integer fkDinerId;

    /**
     * 图片地址
     */
    private String qrcode;

    /**
     * 0=微信支付 1=支付宝支付
     */
    private Integer payment;

    /**
     * 订单状态：-1=已取消 0=未支付 1=已支付 2=已消费 3=已过期
     */
    private Integer status;

    /**
     * 如果是抢购订单时，抢购订单的id
     */
    @Column(name = "fk_seckill_id")
    private Integer fkSeckillId;

    /**
     * 订单类型：0=正常订单 1=抢购订单
     */
    @Column(name = "order_type")
    private Integer orderType;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "update_date")
    private Date updateDate;

    @Column(name = "is_valid")
    private Integer isValid;

    private static final long serialVersionUID = 1L;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return order_no
     */
    public Long getOrderNo() {
        return orderNo;
    }

    /**
     * @param orderNo
     */
    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * @return fk_voucher_id
     */
    public Integer getFkVoucherId() {
        return fkVoucherId;
    }

    /**
     * @param fkVoucherId
     */
    public void setFkVoucherId(Integer fkVoucherId) {
        this.fkVoucherId = fkVoucherId;
    }

    /**
     * @return fk_diner_id
     */
    public Integer getFkDinerId() {
        return fkDinerId;
    }

    /**
     * @param fkDinerId
     */
    public void setFkDinerId(Integer fkDinerId) {
        this.fkDinerId = fkDinerId;
    }

    /**
     * 获取图片地址
     *
     * @return qrcode - 图片地址
     */
    public String getQrcode() {
        return qrcode;
    }

    /**
     * 设置图片地址
     *
     * @param qrcode 图片地址
     */
    public void setQrcode(String qrcode) {
        this.qrcode = qrcode == null ? null : qrcode.trim();
    }

    /**
     * 获取0=微信支付 1=支付宝支付
     *
     * @return payment - 0=微信支付 1=支付宝支付
     */
    public Integer getPayment() {
        return payment;
    }

    /**
     * 设置0=微信支付 1=支付宝支付
     *
     * @param payment 0=微信支付 1=支付宝支付
     */
    public void setPayment(Integer payment) {
        this.payment = payment;
    }

    /**
     * 获取订单状态：-1=已取消 0=未支付 1=已支付 2=已消费 3=已过期
     *
     * @return status - 订单状态：-1=已取消 0=未支付 1=已支付 2=已消费 3=已过期
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置订单状态：-1=已取消 0=未支付 1=已支付 2=已消费 3=已过期
     *
     * @param status 订单状态：-1=已取消 0=未支付 1=已支付 2=已消费 3=已过期
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取如果是抢购订单时，抢购订单的id
     *
     * @return fk_seckill_id - 如果是抢购订单时，抢购订单的id
     */
    public Integer getFkSeckillId() {
        return fkSeckillId;
    }

    /**
     * 设置如果是抢购订单时，抢购订单的id
     *
     * @param fkSeckillId 如果是抢购订单时，抢购订单的id
     */
    public void setFkSeckillId(Integer fkSeckillId) {
        this.fkSeckillId = fkSeckillId;
    }

    /**
     * 获取订单类型：0=正常订单 1=抢购订单
     *
     * @return order_type - 订单类型：0=正常订单 1=抢购订单
     */
    public Integer getOrderType() {
        return orderType;
    }

    /**
     * 设置订单类型：0=正常订单 1=抢购订单
     *
     * @param orderType 订单类型：0=正常订单 1=抢购订单
     */
    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    /**
     * @return create_date
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * @return update_date
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * @param updateDate
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * @return is_valid
     */
    public Integer getIsValid() {
        return isValid;
    }

    /**
     * @param isValid
     */
    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", orderNo=").append(orderNo);
        sb.append(", fkVoucherId=").append(fkVoucherId);
        sb.append(", fkDinerId=").append(fkDinerId);
        sb.append(", qrcode=").append(qrcode);
        sb.append(", payment=").append(payment);
        sb.append(", status=").append(status);
        sb.append(", fkSeckillId=").append(fkSeckillId);
        sb.append(", orderType=").append(orderType);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", isValid=").append(isValid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}