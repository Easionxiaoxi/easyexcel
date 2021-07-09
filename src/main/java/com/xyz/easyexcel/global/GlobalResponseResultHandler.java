package com.xyz.easyexcel.global;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局统一返回体处理器，处理标注了"GLOBAL_RESPONSE_RESULT_ANN"的类或者方法
 */
@RestControllerAdvice
public class GlobalResponseResultHandler implements ResponseBodyAdvice<Object> {
    // 标注的名称
    private static final String GLOBAL_RESPONSE_RESULT_ANN = "GLOBAL_RESPONSE_RESULT_ANN";

    /**
     * 从HttpServletRequest中获取被标注了“GLOBAL_RESPONSE_RESULT_ANN“的对应值，
     * 判断类或者方法上是否被标注了
     * @return boolean
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        // 获取ServletRequestAttributes对象
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        // 获取HttpServletRequest对象
        HttpServletRequest request = requestAttributes.getRequest();
        // 获取被标注了“GLOBAL_RESPONSE_RESULT_ANN”属性对应的值
        GlobalResponseResultAnn globalResponseResultAnn = (GlobalResponseResultAnn) request.getAttribute(GLOBAL_RESPONSE_RESULT_ANN);
        // 返回是否要进行包装
        return globalResponseResultAnn != null;
    }

    /**
     * 标注了“GLOBAL_RESPONSE_RESULT_ANN“的类获取方法其返回体要进行包装
     * @return Object
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        // 判断返回体body是否存在异常，错误，存在则包装返回错误信息
        if (body instanceof GlobalErrorResult) {
            GlobalErrorResult globalErrorResult = (GlobalErrorResult) body;
            return GlobalResponseResult.failure(globalErrorResult.getSuccess(), globalErrorResult.getCode(), globalErrorResult.getMessage(), null);
        }
        return GlobalResponseResult.success(body);
    }
}
