package com.aiolos.diners.controller;

import com.aiolos.commons.response.CommonResponse;
import com.aiolos.diners.service.DinersService;
import com.aiolos.food.controller.diners.DinersControllerApi;
import com.aiolos.food.pojo.bo.LoginDinerBO;
import lombok.extern.slf4j.Slf4j;
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
    public CommonResponse signIn(@Valid LoginDinerBO loginDinerBO) {
        return dinersService.signIn(loginDinerBO.getAccount(), loginDinerBO.getPassword());
    }
}
