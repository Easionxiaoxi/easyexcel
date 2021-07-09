package com.xyz.easyexcel.global;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 全局统一返回体拦截器，拦截标注有注解@GlobalResponseResultAnn的方法
 */
@Component
public class GlobalResponseResultInterceptor implements HandlerInterceptor {
    // 标注的名称
    private static final String GLOBAL_RESPONSE_RESULT_ANN = "GLOBAL_RESPONSE_RESULT_ANN";

    /**
     * http请求的前置，对当前处理类获取当前处理方法上标注了
     * GlobalResponseResultAnn注解的类以及方法进行标注，
     * 以便在GlobalResponseBodyAdvice接口中进行处理
     *
     * @param request
     * @param response
     * @param handler
     * @return boolean
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 请求的是方法
        if (handler instanceof HandlerMethod) {
            final HandlerMethod handlerMethod = (HandlerMethod) handler;
            // 获取当前请求的处理类
            final Class<?> beanClass = handlerMethod.getBeanType();
            // 获取当前请求的处理方法
            final Method method = handlerMethod.getMethod();
            // 判断当前请求的处理类上是否有@GlobalResponseResultAnn注解
            if (beanClass.isAnnotationPresent(GlobalResponseResultAnn.class)) {
                // 标注此请求返回体需要包装，往下传递，在GlobalResponseBodyAdvice接口中进行判断处理
                request.setAttribute(GLOBAL_RESPONSE_RESULT_ANN, beanClass.getAnnotation(GlobalResponseResultAnn.class));
            } else if (method.isAnnotationPresent(GlobalResponseResultAnn.class)) {
                request.setAttribute(GLOBAL_RESPONSE_RESULT_ANN, method.getAnnotation(GlobalResponseResultAnn.class));
            }
        }
        return true;
    }
}
