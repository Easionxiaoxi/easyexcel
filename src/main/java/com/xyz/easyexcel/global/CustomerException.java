package com.xyz.easyexcel.global;

import lombok.Data;

/**
 * 自定义异常类
 */
@Data
public class CustomerException extends RuntimeException {

    private Integer code;

    public CustomerException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public CustomerException(GlobalResultCodeEnum globalResultCodeEnum) {
        super(globalResultCodeEnum.getMessage());
        this.code = globalResultCodeEnum.getCode();
    }

    @Override
    public String toString() {
        return "CustomerException{" + "code=" + code + ",message=" + this.getMessage() + '}';
    }
}
