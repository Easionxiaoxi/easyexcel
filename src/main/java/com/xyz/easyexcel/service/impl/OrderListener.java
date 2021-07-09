package com.xyz.easyexcel.service.impl;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.exception.ExcelDataConvertException;
import com.xyz.easyexcel.entity.Order;
import com.xyz.easyexcel.service.ReadOrderExcel;
import org.springframework.util.CollectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.lang.reflect.Field;
import java.util.Set;

/**
 * 读取excel订单监听器
 */
public class OrderListener extends AnalysisEventListener<Order> {
    /**
     * 每读取一行excel数据就会调用一次
     *
     * @param order
     * @param analysisContext
     * @return void
     */
    @Override
    public void invoke(Order order, AnalysisContext analysisContext) {
        // 获取校验器
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        // 进行校验
        Set<ConstraintViolation<Order>> validate = validator.validate(order, ReadOrderExcel.class);
        // 判断是否有错
        if (!CollectionUtils.isEmpty(validate)) {
            for (ConstraintViolation<Order> orderConstraintViolation : validate) {
                String propertyName = orderConstraintViolation.getPropertyPath().toString();
                try {
                    // 获取字段上对应的ExcelProperty注解的value值
                    Field declaredField = order.getClass().getDeclaredField(propertyName);
                    ExcelProperty annotation = declaredField.getAnnotation(ExcelProperty.class);
                    // 打印value值以及错误信息
                    System.out.println(annotation.value()[0] + " " + orderConstraintViolation.getMessage() + orderConstraintViolation.getRootBean());
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("解析到一条数据" + order.toString());
    }

    /**
     * 数据转换异常处理，抛出异常则停止读取。
     * 如果不抛出异常则继续读取下一行，这里不抛出，继续读取
     *
     * @param exception
     * @param context
     * @return void
     */
    @Override
    public void onException(Exception exception, AnalysisContext context) {
        System.out.println("解析失败，但是继续解析下一行" + exception.getMessage());
        // 判断是数据转换异常，打印在第几行第几列
        if (exception instanceof ExcelDataConvertException) {
            ExcelDataConvertException excelDataConvertException = (ExcelDataConvertException) exception;
            System.out.println("第" + excelDataConvertException.getRowIndex() + "行" + ",第" + excelDataConvertException.getColumnIndex() + "列 出现转换异常");
        }
    }

    /**
     * 所有数据读取完成后会调用一次
     *
     * @param analysisContext
     * @return void
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        System.out.println("所有数据解析完成");
    }
}
