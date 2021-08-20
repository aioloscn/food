package com.aiolos.oauth2.server.advice;

import com.aiolos.commons.enums.ErrorEnum;
import com.aiolos.commons.response.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 捕获 UserServiceDetail 中抛出的 UsernameNotFoundException ，不在 common 中的全局异常捕获处理
 * @author Aiolos
 * @date 2021/8/20 11:48 下午
 */
@Slf4j
@Order(-2)
@RestControllerAdvice
public class InvalidGrantExceptionAdvice {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public CommonResponse handlerInvalidGrantException(HttpServletRequest req, HttpServletResponse resp, Exception e) {
        log.warn("捕获异常：{}", e.getMessage());
        if (e instanceof InvalidGrantException)
            return CommonResponse.error(ErrorEnum.WRONG_USERNAME_OR_PASSWORD.getErrCode(), e.getMessage());
        return CommonResponse.error(ErrorEnum.AUTHENTICATION_FAILED);
    }
}
