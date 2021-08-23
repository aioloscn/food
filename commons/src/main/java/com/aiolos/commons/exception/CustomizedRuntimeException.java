package com.aiolos.commons.exception;

/**
 * 自定义运行时异常类
 * @author Aiolos
 * @date 2020/10/7 10:03 下午
 */
public class CustomizedRuntimeException extends RuntimeException implements CommonError {

    private final CommonError commonError;

    public CustomizedRuntimeException(CommonError commonError) {
        super();
        this.commonError = commonError;
    }

    /**
     * 因为ErrorEnum实现了CommonError，所以在抛出异常的时候可以传CommonError的实现类ErrorEnum进来，在ErrorEnum中setErrMsg()
     * @param commonError   公共的异常接口
     * @param errMsg    错误信息
     */
    public CustomizedRuntimeException(CommonError commonError, String errMsg) {
        super();
        this.commonError = commonError;
        this.commonError.setErrMsg(errMsg);
    }

    @Override
    public Integer getErrCode() {
        return this.commonError.getErrCode();
    }

    @Override
    public String getErrMsg() {
        return this.commonError.getErrMsg();
    }

    /**
     * 在CommonError的被实现类（ErrorEnum）中替换message
     * @param errMsg    错误信息
     * @return  返回CommonError的实现类，也就是当前类
     */
    @Override
    public CommonError setErrMsg(String errMsg) {
        this.commonError.setErrMsg(errMsg);
        return this;
    }
}
