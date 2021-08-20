package com.aiolos.diners.controller;

import com.aiolos.commons.exception.CustomizedException;
import com.aiolos.commons.response.CommonResponse;
import com.aiolos.diners.service.SendVerifyCodeService;
import com.aiolos.food.controller.diners.SendVerifyCodeControllerApi;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Aiolos
 * @date 2021/8/20 6:02 下午
 */
@RestController
public class SendVerifyCodeController implements SendVerifyCodeControllerApi {

    private final SendVerifyCodeService sendVerifyCodeService;

    public SendVerifyCodeController(SendVerifyCodeService sendVerifyCodeService) {
        this.sendVerifyCodeService = sendVerifyCodeService;
    }

    @Override
    public CommonResponse send(String phone) throws CustomizedException {
        return sendVerifyCodeService.send(phone);
    }
}
