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

@TableName("t_restaurants")
public class Restaurants implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * the En Name of the restaurant
     */
    private String name;

    private String cnname;

    private Double x;

    private Double y;

    /**
     * En location of the restaurant
     */
    private String location;

    private String cnlocation;

    /**
     * city.district.neighbourhood
Example: Shanghai.Xuhui.Xujiahui
     */
    private String area;

    /**
     * Phone of the restaurant
     */
    private String telephone;

    private String email;

    private String website;

    private String cuisine;

    @Column(name = "average_price")
    private String averagePrice;

    /**
     * Indtroduction of the restaurant
     */
    private String introduction;

    /**
     * pics at the list, value would be:
basepath/original/picname
     */
    private String thumbnail;

    /**
     * the percentage of people like it
     */
    @Column(name = "like_votes")
    private Integer likeVotes;

    /**
     * How many people votes
     */
    @Column(name = "dislike_votes")
    private Integer dislikeVotes;

    /**
     * 城市id
     */
    @Column(name = "city_id")
    private Integer cityId;

    /**
     * 1=Valid 0=Invalid
     */
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
     * 获取the En Name of the restaurant
     *
     * @return name - the En Name of the restaurant
     */
    public String getName() {
        return name;
    }

    /**
     * 设置the En Name of the restaurant
     *
     * @param name the En Name of the restaurant
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * @return cnname
     */
    public String getCnname() {
        return cnname;
    }

    /**
     * @param cnname
     */
    public void setCnname(String cnname) {
        this.cnname = cnname == null ? null : cnname.trim();
    }

    /**
     * @return x
     */
    public Double getX() {
        return x;
    }

    /**
     * @param x
     */
    public void setX(Double x) {
        this.x = x;
    }

    /**
     * @return y
     */
    public Double getY() {
        return y;
    }

    /**
     * @param y
     */
    public void setY(Double y) {
        this.y = y;
    }

    /**
     * 获取En location of the restaurant
     *
     * @return location - En location of the restaurant
     */
    public String getLocation() {
        return location;
    }

    /**
     * 设置En location of the restaurant
     *
     * @param location En location of the restaurant
     */
    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    /**
     * @return cnlocation
     */
    public String getCnlocation() {
        return cnlocation;
    }

    /**
     * @param cnlocation
     */
    public void setCnlocation(String cnlocation) {
        this.cnlocation = cnlocation == null ? null : cnlocation.trim();
    }

    /**
     * 获取city.district.neighbourhood
Example: Shanghai.Xuhui.Xujiahui
     *
     * @return area - city.district.neighbourhood
Example: Shanghai.Xuhui.Xujiahui
     */
    public String getArea() {
        return area;
    }

    /**
     * 设置city.district.neighbourhood
Example: Shanghai.Xuhui.Xujiahui
     *
     * @param area city.district.neighbourhood
Example: Shanghai.Xuhui.Xujiahui
     */
    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    /**
     * 获取Phone of the restaurant
     *
     * @return telephone - Phone of the restaurant
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * 设置Phone of the restaurant
     *
     * @param telephone Phone of the restaurant
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    /**
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * @return website
     */
    public String getWebsite() {
        return website;
    }

    /**
     * @param website
     */
    public void setWebsite(String website) {
        this.website = website == null ? null : website.trim();
    }

    /**
     * @return cuisine
     */
    public String getCuisine() {
        return cuisine;
    }

    /**
     * @param cuisine
     */
    public void setCuisine(String cuisine) {
        this.cuisine = cuisine == null ? null : cuisine.trim();
    }

    /**
     * @return average_price
     */
    public String getAveragePrice() {
        return averagePrice;
    }

    /**
     * @param averagePrice
     */
    public void setAveragePrice(String averagePrice) {
        this.averagePrice = averagePrice == null ? null : averagePrice.trim();
    }

    /**
     * 获取Indtroduction of the restaurant
     *
     * @return introduction - Indtroduction of the restaurant
     */
    public String getIntroduction() {
        return introduction;
    }

    /**
     * 设置Indtroduction of the restaurant
     *
     * @param introduction Indtroduction of the restaurant
     */
    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }

    /**
     * 获取pics at the list, value would be:
basepath/original/picname
     *
     * @return thumbnail - pics at the list, value would be:
basepath/original/picname
     */
    public String getThumbnail() {
        return thumbnail;
    }

    /**
     * 设置pics at the list, value would be:
basepath/original/picname
     *
     * @param thumbnail pics at the list, value would be:
basepath/original/picname
     */
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail == null ? null : thumbnail.trim();
    }

    /**
     * 获取the percentage of people like it
     *
     * @return like_votes - the percentage of people like it
     */
    public Integer getLikeVotes() {
        return likeVotes;
    }

    /**
     * 设置the percentage of people like it
     *
     * @param likeVotes the percentage of people like it
     */
    public void setLikeVotes(Integer likeVotes) {
        this.likeVotes = likeVotes;
    }

    /**
     * 获取How many people votes
     *
     * @return dislike_votes - How many people votes
     */
    public Integer getDislikeVotes() {
        return dislikeVotes;
    }

    /**
     * 设置How many people votes
     *
     * @param dislikeVotes How many people votes
     */
    public void setDislikeVotes(Integer dislikeVotes) {
        this.dislikeVotes = dislikeVotes;
    }

    /**
     * 获取城市id
     *
     * @return city_id - 城市id
     */
    public Integer getCityId() {
        return cityId;
    }

    /**
     * 设置城市id
     *
     * @param cityId 城市id
     */
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    /**
     * 获取1=Valid 0=Invalid
     *
     * @return is_valid - 1=Valid 0=Invalid
     */
    public Integer getIsValid() {
        return isValid;
    }

    /**
     * 设置1=Valid 0=Invalid
     *
     * @param isValid 1=Valid 0=Invalid
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
        sb.append(", name=").append(name);
        sb.append(", cnname=").append(cnname);
        sb.append(", x=").append(x);
        sb.append(", y=").append(y);
        sb.append(", location=").append(location);
        sb.append(", cnlocation=").append(cnlocation);
        sb.append(", area=").append(area);
        sb.append(", telephone=").append(telephone);
        sb.append(", email=").append(email);
        sb.append(", website=").append(website);
        sb.append(", cuisine=").append(cuisine);
        sb.append(", averagePrice=").append(averagePrice);
        sb.append(", introduction=").append(introduction);
        sb.append(", thumbnail=").append(thumbnail);
        sb.append(", likeVotes=").append(likeVotes);
        sb.append(", dislikeVotes=").append(dislikeVotes);
        sb.append(", cityId=").append(cityId);
        sb.append(", isValid=").append(isValid);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}