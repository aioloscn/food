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

@TableName("t_seckill_vouchers")
public class SeckillVouchers implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    @TableId(type = IdType.AUTO)
    private Integer id;

    @Column(name = "fk_voucher_id")
    private Integer fkVoucherId;

    private Integer amount;

    @Column(name = "start_time")
    private Date startTime;

    @Column(name = "end_time")
    private Date endTime;

    @Column(name = "is_valid")
    private Integer isValid;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "update_date")
    private Date updateDate;

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
     * @return amount
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * @param amount
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    /**
     * @return start_time
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * @param startTime
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * @return end_time
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * @param endTime
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", fkVoucherId=").append(fkVoucherId);
        sb.append(", amount=").append(amount);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", isValid=").append(isValid);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}