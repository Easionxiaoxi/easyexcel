package com.xyz.easyexcel.global;

/**
 * 全局统一返回状态码
 */
public enum GlobalResultCodeEnum {
    /**
     * 成功状态码
     */
    success(1, "成功"),

    /**
     * 参数错误 1001-1999
     */
    param_invalid(1001, "参数无效"),
    param_blank(1002, "参数为空"),
    param_type_error(1003, "参数类型错误"),
    param_incomplete(1, "参数缺失"),

    /**
     * 用户错误 2001-2999
     */
    user_not_login(2001, "用户未登入"),
    user_login_error(2002, "账号不存在或密码错误"),
    user_account_forbidden(2003, "账号已被禁用"),
    user_not_exist(2004, "用户不存在"),
    user_has_exist(2006, "用户已存在");

    /**
     * 接口异常 3001-9999
     */

    /**
     * 状态码
     */
    private Integer code;
    /**
     * 信息
     */
    private String message;

    GlobalResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
