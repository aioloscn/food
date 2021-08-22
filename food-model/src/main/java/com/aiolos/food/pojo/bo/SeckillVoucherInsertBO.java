package com.aiolos.food.pojo.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author Aiolos
 * @date 2021/8/21 1:35 下午
 */
@ApiModel(description = "新增代金券活动对象")
@Data
public class SeckillVoucherInsertBO {

    @ApiModelProperty("代金券表主键")
    @NotNull(message = "代金券不能为空")
    private Integer fkVoucherId;

    @ApiModelProperty("数量")
    @Min(value = 1, message = "数量必须大于0")
    @NotNull(message = "数量必须大于0")
    private Integer amount;

    @ApiModelProperty("抢购开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @NotNull(message = "抢购开始时间不能为空")
    private Date startTime;

    @ApiModelProperty("抢购结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @NotNull(message = "抢购结束时间不能为空")
    private Date endTime;
}
