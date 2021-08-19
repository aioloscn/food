package com.aiolos.oauth2.server.config;

import com.aiolos.commons.enums.ErrorEnum;
import com.aiolos.commons.response.CommonResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 认证失败处理
 * @author Aiolos
 * @date 2021/8/19 5:41 下午
 */
@Component
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Resource
    private ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        // 返回 JSON
        response.setContentType("application/json;charset=utf-8");
        // 状态码 401
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        PrintWriter writer = response.getWriter();
        String errorMessage = authException.getMessage();
        if (StringUtils.isBlank(errorMessage))
            errorMessage = ErrorEnum.TOKEN_INVALID.getErrMsg();
        writer.write(objectMapper.writeValueAsString(CommonResponse.error(ErrorEnum.TOKEN_INVALID.getErrCode(), errorMessage)));
        writer.flush();
        writer.close();
    }
}
