package com.xyz.easyexcel.global;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局统一异常处理器
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 统一异常处理方法
     */
    @ExceptionHandler(Exception.class)
    public GlobalErrorResult error(Exception e) {
        e.printStackTrace();
        return new GlobalErrorResult().setSuccess(false).setCode(10076).setMessage(e.getMessage());
    }

    /**
     * 指定异常处理方法
     */
    @ExceptionHandler(NullPointerException.class)
    public GlobalErrorResult error(NullPointerException e) {
        e.printStackTrace();
        return new GlobalErrorResult().setSuccess(false).setCode(10087).setMessage(e.getMessage());
    }

    /**
     * 自定义异常处理方法
     */
    @ExceptionHandler(CustomerException.class)
    public GlobalErrorResult error(CustomerException e) {
        e.printStackTrace();
        return new GlobalErrorResult().setSuccess(false).setCode(e.getCode()).setMessage(e.getMessage());
    }

}
