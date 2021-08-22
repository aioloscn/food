package com.aiolos.food.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@TableName("t_voucher")
public class Voucher implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 代金券标题
     */
    private String title;

    /**
     * 缩略图
     */
    private String thumbnail;

    /**
     * 抵扣金额
     */
    private Integer amount;

    /**
     * 售价
     */
    private BigDecimal price;

    /**
     * -1=过期 0=下架 1=上架
     */
    private Integer status;

    /**
     * 过期时间
     */
    @Column(name = "expire_time")
    private Date expireTime;

    /**
     * 验证餐厅
     */
    @Column(name = "redeem_restaurant_id")
    private Integer redeemRestaurantId;

    /**
     * 库存
     */
    private Integer stock;

    /**
     * 剩余数量
     */
    @Column(name = "stock_left")
    private Integer stockLeft;

    /**
     * 描述信息
     */
    private String description;

    /**
     * 使用条款
     */
    private String clause;

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
     * 获取代金券标题
     *
     * @return title - 代金券标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置代金券标题
     *
     * @param title 代金券标题
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 获取缩略图
     *
     * @return thumbnail - 缩略图
     */
    public String getThumbnail() {
        return thumbnail;
    }

    /**
     * 设置缩略图
     *
     * @param thumbnail 缩略图
     */
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail == null ? null : thumbnail.trim();
    }

    /**
     * 获取抵扣金额
     *
     * @return amount - 抵扣金额
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * 设置抵扣金额
     *
     * @param amount 抵扣金额
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    /**
     * 获取售价
     *
     * @return price - 售价
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 设置售价
     *
     * @param price 售价
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 获取-1=过期 0=下架 1=上架
     *
     * @return status - -1=过期 0=下架 1=上架
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置-1=过期 0=下架 1=上架
     *
     * @param status -1=过期 0=下架 1=上架
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取过期时间
     *
     * @return expire_time - 过期时间
     */
    public Date getExpireTime() {
        return expireTime;
    }

    /**
     * 设置过期时间
     *
     * @param expireTime 过期时间
     */
    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    /**
     * 获取验证餐厅
     *
     * @return redeem_restaurant_id - 验证餐厅
     */
    public Integer getRedeemRestaurantId() {
        return redeemRestaurantId;
    }

    /**
     * 设置验证餐厅
     *
     * @param redeemRestaurantId 验证餐厅
     */
    public void setRedeemRestaurantId(Integer redeemRestaurantId) {
        this.redeemRestaurantId = redeemRestaurantId;
    }

    /**
     * 获取库存
     *
     * @return stock - 库存
     */
    public Integer getStock() {
        return stock;
    }

    /**
     * 设置库存
     *
     * @param stock 库存
     */
    public void setStock(Integer stock) {
        this.stock = stock;
    }

    /**
     * 获取剩余数量
     *
     * @return stock_left - 剩余数量
     */
    public Integer getStockLeft() {
        return stockLeft;
    }

    /**
     * 设置剩余数量
     *
     * @param stockLeft 剩余数量
     */
    public void setStockLeft(Integer stockLeft) {
        this.stockLeft = stockLeft;
    }

    /**
     * 获取描述信息
     *
     * @return description - 描述信息
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置描述信息
     *
     * @param description 描述信息
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * 获取使用条款
     *
     * @return clause - 使用条款
     */
    public String getClause() {
        return clause;
    }

    /**
     * 设置使用条款
     *
     * @param clause 使用条款
     */
    public void setClause(String clause) {
        this.clause = clause == null ? null : clause.trim();
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
        sb.append(", title=").append(title);
        sb.append(", thumbnail=").append(thumbnail);
        sb.append(", amount=").append(amount);
        sb.append(", price=").append(price);
        sb.append(", status=").append(status);
        sb.append(", expireTime=").append(expireTime);
        sb.append(", redeemRestaurantId=").append(redeemRestaurantId);
        sb.append(", stock=").append(stock);
        sb.append(", stockLeft=").append(stockLeft);
        sb.append(", description=").append(description);
        sb.append(", clause=").append(clause);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", isValid=").append(isValid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}