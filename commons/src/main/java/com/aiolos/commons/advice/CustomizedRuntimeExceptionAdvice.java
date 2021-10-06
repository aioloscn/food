package com.aiolos.commons.advice;

import com.aiolos.commons.enums.ErrorEnum;
import com.aiolos.commons.exception.CustomizedRuntimeException;
import com.aiolos.commons.response.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 所有执行的controller都会被这个切面所包含
 * @author Aiolos
 * @date 2020/10/10 4:14 下午
 */
@Order(-9)
@Slf4j
@RestControllerAdvice
public class CustomizedRuntimeExceptionAdvice {

    @ExceptionHandler(value = CustomizedRuntimeException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public CommonResponse handlerCustomizeException(HttpServletRequest req, HttpServletResponse resp, Exception e) {

        if (e instanceof CustomizedRuntimeException) {
            log.warn("自定义运行时异常捕获，异常信息：{}", ((CustomizedRuntimeException) e).getErrMsg());
            return CommonResponse.error(((CustomizedRuntimeException) e).getErrCode(), ((CustomizedRuntimeException) e).getErrMsg());
        } else {
            return CommonResponse.error(ErrorEnum.UNKNOWN_ERROR);
        }
    }
}