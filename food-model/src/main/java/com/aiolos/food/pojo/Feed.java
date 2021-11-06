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

@TableName("t_feed")
public class Feed implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 内容
     */
    private String content;

    @Column(name = "fk_diner_id")
    private Integer fkDinerId;

    /**
     * 点赞数量
     */
    @Column(name = "praise_amount")
    private Integer praiseAmount;

    /**
     * 评论数量
     */
    @Column(name = "comment_amount")
    private Integer commentAmount;

    @Column(name = "fk_restaurant_id")
    private Integer fkRestaurantId;

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
     * 获取内容
     *
     * @return content - 内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置内容
     *
     * @param content 内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
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
     * 获取点赞数量
     *
     * @return praise_amount - 点赞数量
     */
    public Integer getPraiseAmount() {
        return praiseAmount;
    }

    /**
     * 设置点赞数量
     *
     * @param praiseAmount 点赞数量
     */
    public void setPraiseAmount(Integer praiseAmount) {
        this.praiseAmount = praiseAmount;
    }

    /**
     * 获取评论数量
     *
     * @return comment_amount - 评论数量
     */
    public Integer getCommentAmount() {
        return commentAmount;
    }

    /**
     * 设置评论数量
     *
     * @param commentAmount 评论数量
     */
    public void setCommentAmount(Integer commentAmount) {
        this.commentAmount = commentAmount;
    }

    /**
     * @return fk_restaurant_id
     */
    public Integer getFkRestaurantId() {
        return fkRestaurantId;
    }

    /**
     * @param fkRestaurantId
     */
    public void setFkRestaurantId(Integer fkRestaurantId) {
        this.fkRestaurantId = fkRestaurantId;
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
        sb.append(", content=").append(content);
        sb.append(", fkDinerId=").append(fkDinerId);
        sb.append(", praiseAmount=").append(praiseAmount);
        sb.append(", commentAmount=").append(commentAmount);
        sb.append(", fkRestaurantId=").append(fkRestaurantId);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", isValid=").append(isValid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}