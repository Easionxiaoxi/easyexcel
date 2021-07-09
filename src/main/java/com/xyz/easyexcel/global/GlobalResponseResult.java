package com.xyz.easyexcel.global;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 全局统一返回实体
 */
@Accessors(chain = true)
@Data
public class GlobalResponseResult implements Serializable {
    private Boolean success;
    private Integer code;
    private String message;
    private Object data;

    /**
     * 在构造器中注入全局统一返回状态码
     */
    public GlobalResponseResult(Boolean success, GlobalResultCodeEnum globalResultCodeEnum) {
        this.success = success;
        this.code = globalResultCodeEnum.getCode();
        this.message = globalResultCodeEnum.getMessage();
        this.data = null;
    }

    /**
     * 在构造器中注入全局统一返回状态码，数据，并赋值
     */
    public GlobalResponseResult(Boolean success, GlobalResultCodeEnum globalResultCodeEnum, Object data) {
        this.success = success;
        this.code = globalResultCodeEnum.getCode();
        this.message = globalResultCodeEnum.getMessage();
        this.data = data;
    }

    /**
     * 全参构造器
     */
    public GlobalResponseResult(Boolean success, Integer code, String message, Object data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 无参静态方法，返回成功
     */
    public static GlobalResponseResult success() {
        return new GlobalResponseResult(true, GlobalResultCodeEnum.success);
    }

    /**
     * 带参data静态方法，返回成功
     */
    public static GlobalResponseResult success(Object data) {
        return new GlobalResponseResult(true, GlobalResultCodeEnum.success, data);
    }

    /**
     * 带参GlobalResultCodeEnum静态方法，返回失败
     */
    public static GlobalResponseResult failure(GlobalResultCodeEnum globalResultCodeEnum) {
        return new GlobalResponseResult(false, globalResultCodeEnum);
    }

    /**
     * 带参GlobalResultCodeEnum，data，静态方法，返回失败
     */
    public static GlobalResponseResult failure(GlobalResultCodeEnum globalResultCodeEnum, Object data) {
        return new GlobalResponseResult(false, globalResultCodeEnum, data);
    }

    /**
     * 带参success，code，message，data，静态方法，返回失败
     */
    public static GlobalResponseResult failure(Boolean success, Integer code, String message, Object data) {
        return new GlobalResponseResult(success, code, message, data);
    }

}
