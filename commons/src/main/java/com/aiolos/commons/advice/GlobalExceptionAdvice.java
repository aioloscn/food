package com.aiolos.commons.advice;

import com.aiolos.commons.enums.ErrorEnum;
import com.aiolos.commons.response.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Aiolos
 * @date 2021/5/20 12:13 上午
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public CommonResponse handlerException(HttpServletRequest req, HttpServletResponse resp, Exception e) {
        log.warn("全局异常捕获：{}", e.getMessage());
        if (e instanceof NoHandlerFoundException) {
            return CommonResponse.error(ErrorEnum.NO_HANDLER_FOUND);
        } else if (e instanceof ServletRequestBindingException) {
            return CommonResponse.error(ErrorEnum.BIND_EXCEPTION_ERROR);
        } else if (e instanceof NullPointerException) {
            return CommonResponse.error(ErrorEnum.NULL_POINT_ERROR);
        } else if (e instanceof MaxUploadSizeExceededException) {
            return CommonResponse.error(ErrorEnum.FILE_MAX_SIZE_ERROR);
        } else {
            return CommonResponse.error(ErrorEnum.UNKNOWN_ERROR);
        }
    }
}
