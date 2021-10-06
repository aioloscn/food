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

@TableName("t_follow")
public class Follow implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    @TableId(type = IdType.AUTO)
    private Integer id;

    @Column(name = "diner_id")
    private Integer dinerId;

    @Column(name = "follow_diner_id")
    private Integer followDinerId;

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
     * @return diner_id
     */
    public Integer getDinerId() {
        return dinerId;
    }

    /**
     * @param dinerId
     */
    public void setDinerId(Integer dinerId) {
        this.dinerId = dinerId;
    }

    /**
     * @return follow_diner_id
     */
    public Integer getFollowDinerId() {
        return followDinerId;
    }

    /**
     * @param followDinerId
     */
    public void setFollowDinerId(Integer followDinerId) {
        this.followDinerId = followDinerId;
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
        sb.append(", dinerId=").append(dinerId);
        sb.append(", followDinerId=").append(followDinerId);
        sb.append(", isValid=").append(isValid);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}