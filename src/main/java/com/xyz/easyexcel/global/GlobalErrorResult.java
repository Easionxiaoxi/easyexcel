package com.xyz.easyexcel.global;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 全局错误异常结果
 */
@Accessors(chain = true)
@Data
public class GlobalErrorResult {
    private Boolean success;
    private Integer code;
    private String message;
    private Object data;
}
