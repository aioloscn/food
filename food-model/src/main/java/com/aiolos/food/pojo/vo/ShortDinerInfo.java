package com.aiolos.food.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Aiolos
 * @date 2021/10/5 8:26 下午
 */
@Getter
@Setter
@ApiModel("关注食客信息")
public class ShortDinerInfo {

    @ApiModelProperty("主键")
    private Integer id;
    @ApiModelProperty("昵称")
    private String nickname;
    @ApiModelProperty("头像")
    private String avatarUrl;
}
