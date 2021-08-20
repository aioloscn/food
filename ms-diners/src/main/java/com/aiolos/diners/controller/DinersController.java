package com.aiolos.diners.controller;

import cn.hutool.core.bean.BeanUtil;
import com.aiolos.commons.enums.ErrorEnum;
import com.aiolos.commons.exception.CustomizedException;
import com.aiolos.commons.response.CommonResponse;
import com.aiolos.diners.service.DinersService;
import com.aiolos.food.controller.diners.DinersControllerApi;
import com.aiolos.food.pojo.bo.DinerLoginBO;
import com.aiolos.food.pojo.bo.DinerRegisterBO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Aiolos
 * @date 2021/8/18 10:51 下午
 */
@Slf4j
@RestController
public class DinersController implements DinersControllerApi {

    private final DinersService dinersService;

    public DinersController(DinersService dinersService) {
        this.dinersService = dinersService;
    }

    @Override
    public CommonResponse signIn(@Valid DinerLoginBO dinerLoginBO) {
        BeanUtil.trimStrFields(dinerLoginBO);
        return dinersService.signIn(dinerLoginBO.getAccount(), dinerLoginBO.getPassword());
    }

    @Override
    public CommonResponse checkPhoneIsRegistered(String phone) {
        if (StringUtils.isBlank(phone))
            return CommonResponse.error(ErrorEnum.PHONE_INCORRECT);
        return dinersService.checkPhoneIsRegistered(phone);
    }

    @Override
    public CommonResponse register(@Valid DinerRegisterBO dinerRegisterBO) throws CustomizedException {
        BeanUtil.trimStrFields(dinerRegisterBO);
        return dinersService.register(dinerRegisterBO);
    }
}
