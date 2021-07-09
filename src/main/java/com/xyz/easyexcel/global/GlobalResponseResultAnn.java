package com.xyz.easyexcel.global;

import java.lang.annotation.*;

/**
 * 全局统一返回实体需要包装的标注注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
public @interface GlobalResponseResultAnn {
}
