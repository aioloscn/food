package com.aiolos.gateway.component;

import com.aiolos.commons.enums.ErrorEnum;
import com.aiolos.commons.response.CommonResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.nio.charset.Charset;

/**
 * access_token 验证失败的集中的返回处理
 * @author Aiolos
 * @date 2021/8/20 12:56 上午
 */
@Component
public class HandleException {

    @Resource
    private ObjectMapper objectMapper;

    public Mono<Void> writeError(ServerWebExchange exchange, ErrorEnum errorEnum) {
        ServerHttpResponse response = exchange.getResponse();
        ServerHttpRequest request = exchange.getRequest();
        response.setStatusCode(HttpStatus.OK);
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        String resultInfoJson = null;
        DataBuffer buffer = null;
        try {
            resultInfoJson = objectMapper.writeValueAsString(CommonResponse.error(errorEnum));
            buffer = response.bufferFactory().wrap(resultInfoJson.getBytes(Charset.forName("UTF-8")));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return response.writeWith(Mono.just(buffer));
    }
}
