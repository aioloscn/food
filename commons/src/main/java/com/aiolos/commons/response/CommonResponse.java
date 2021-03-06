package com.aiolos.commons.response;

import cn.hutool.http.HttpStatus;
import com.aiolos.commons.enums.ErrorEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.ToString;

import java.io.Serializable;
import java.util.Map;

/**
 * 自定义响应数据结构
 * 状态码定义在ErrorEnum类中，HttpStatus.200：成功（默认），500：错误（默认）
 * @author Aiolos
 * @date 2020/9/22 1:11 下午
 */
@ToString
public class CommonResponse<T> extends AbstractCommonResponse implements Serializable {

    /**
     * 响应业务状态码
     */
    private Integer code = HttpStatus.HTTP_OK;

    /**
     * 响应信息
     */
    private String msg = "SUCCESS";;

    /**
     * 响应中的数据
     */
    private T data;

    private Map<Object, Object> map;

    /**
     * map初始容量
     */
    @JsonIgnore
    private int capacity = 1 << 3;

    private CommonResponse() {
        super.init(this.capacity);
    }

    private CommonResponse(String msg) {
        super.init(this.capacity);
        this.msg = msg;
    }

    private CommonResponse(T data) {
        super.init(this.capacity);
        this.data = data;
    }

    private CommonResponse(String msg, T data) {
        super.init(this.capacity);
        this.msg = msg;
        this.data = data;
    }

    public static CommonResponse ok() {
        return new CommonResponse();
    }

    public static CommonResponse ok(String msg) {
        return new CommonResponse(msg);
    }

    public static CommonResponse ok(Object data) {
        return new CommonResponse(data);
    }

    public static CommonResponse ok(String msg, Object data) {
        return new CommonResponse(msg, data);
    }

    public static CommonResponse error(Integer errCode, String errMsg) {
        CommonResponse res = new CommonResponse();
        res.setCode(errCode);
        res.setMsg(errMsg);
        return res;
    }

    public static CommonResponse error(String errMsg, Object data) {
        CommonResponse res = new CommonResponse();
        res.setCode(HttpStatus.HTTP_INTERNAL_ERROR);
        res.setMsg(errMsg);
        res.setData(data);
        return res;
    }

    public static CommonResponse error(Integer errCode, String errMsg, Object data) {
        CommonResponse res = new CommonResponse();
        res.setCode(errCode);
        res.setMsg(errMsg);
        res.setData(data);
        return res;
    }

    public static CommonResponse error(ErrorEnum errorEnum) {
        CommonResponse res = new CommonResponse();
        res.setCode(errorEnum.getErrCode());
        res.setMsg(errorEnum.getErrMsg());
        return res;
    }

    @Override
    public synchronized CommonResponse put(Object key, Object value) {
        super.put(key, value);
        this.map = super.getMap();
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    @Override
    public Map<Object, Object> getMap() {
        return map;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}