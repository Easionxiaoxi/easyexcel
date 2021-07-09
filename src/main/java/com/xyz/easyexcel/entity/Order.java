package com.xyz.easyexcel.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.*;
import com.xyz.easyexcel.service.ReadOrderExcel;
import com.xyz.easyexcel.service.impl.NeedInvoiceConverter;
import com.xyz.easyexcel.service.impl.PayStatusConverter;
import com.xyz.easyexcel.service.impl.PayToolTypeConverter;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.apache.poi.ss.usermodel.FillPatternType;

import javax.validation.constraints.NotBlank;

/**
 * 订单，@ExcelProperty中的index要和excel表的第几列一一对应，
 * 试用index匹配，不要试用name匹配以防后期修改
 */
// 全局表头样式
@HeadStyle(fillPatternType = FillPatternType.NO_FILL)
// 全局表头字体
@HeadFontStyle(fontHeightInPoints = 20)
// 全局表头行高
@HeadRowHeight(20)
// 全局内容样式
@ContentStyle(fillPatternType = FillPatternType.NO_FILL)
// 全局内容字体
@ContentFontStyle(fontHeightInPoints = 10)
// 全局内容行高
@ContentRowHeight(20)
@Accessors(chain = true)
@ToString
@Data
public class Order {
    /**
     * 姓名
     */
    @NotBlank(message = "姓名不能为空", groups = ReadOrderExcel.class)
    // 表头样式
    @HeadStyle(fillPatternType = FillPatternType.NO_FILL)
    // 表头字体
    @HeadFontStyle(fontHeightInPoints = 14)
    @ExcelProperty(value = {"订单", "姓名"}, index = 0)
    private String name;
    /**
     * 订单号
     */
    @NotBlank(message = "订单号不能为空", groups = ReadOrderExcel.class)
    // 表头样式
    @HeadStyle(fillPatternType = FillPatternType.NO_FILL)
    // 表头字体
    @HeadFontStyle(fontHeightInPoints = 14)
    @ExcelProperty(value = {"订单", "订单号"}, index = 1)
    private String tradeNo;
    /**
     * 支付类型
     */
    // 表头样式
    @HeadStyle(fillPatternType = FillPatternType.NO_FILL)
    // 表头字体
    @HeadFontStyle(fontHeightInPoints = 14)
    @ExcelProperty(value = {"订单", "支付方式"}, converter = PayToolTypeConverter.class, index = 2)
    private String paymentToolType;
    /**
     * 订单金额
     */
    // 表头样式
    @HeadStyle(fillPatternType = FillPatternType.NO_FILL)
    // 表头字体
    @HeadFontStyle(fontHeightInPoints = 14)
    @ExcelProperty(value = {"订单", "金额"}, index = 3)
    private String orderAmount;
    /**
     * 手机号
     */
    // 表头样式
    @HeadStyle(fillPatternType = FillPatternType.NO_FILL)
    // 表头字体
    @HeadFontStyle(fontHeightInPoints = 14)
    @ExcelProperty(value = {"订单", "手机号"}, index = 4)
    private String mobile;
    /**
     * 是否有发票
     */
    // 表头样式
    @HeadStyle(fillPatternType = FillPatternType.NO_FILL)
    // 表头字体
    @HeadFontStyle(fontHeightInPoints = 14)
    @ExcelProperty(value = {"订单", "发票"}, converter = NeedInvoiceConverter.class, index = 5)
    private String isNeedInvoice;
    /**
     * 支付状态
     */
    // 表头样式
    @HeadStyle(fillPatternType = FillPatternType.NO_FILL)
    // 表头字体
    @HeadFontStyle(fontHeightInPoints = 14)
    @ExcelProperty(value = {"订单", "支付状态"}, converter = PayStatusConverter.class, index = 6)
    private String payStatus;
    /**
     * 交易时间
     */
    // 表头样式
    @HeadStyle(fillPatternType = FillPatternType.NO_FILL)
    // 表头字体
    @HeadFontStyle(fontHeightInPoints = 14)
    @ExcelProperty(value = {"订单", "支付时间"}, index = 7)
    @DateTimeFormat("yyyy年MM月dd日HH时mm分ss秒")
    private String tradeTime;
    /**
     * 企业名称
     */
    // 表头样式
    @HeadStyle(fillPatternType = FillPatternType.NO_FILL)
    // 表头字体
    @HeadFontStyle(fontHeightInPoints = 14)
    @ExcelProperty(value = {"订单", "单位"}, index = 8)
    private String principalName;
    /**
     * 订单状态
     */
    // 表头样式
    @HeadStyle(fillPatternType = FillPatternType.NO_FILL)
    // 表头字体
    @HeadFontStyle(fontHeightInPoints = 14)
    @ExcelProperty(value = {"订单", "订单状态"}, converter = PayStatusConverter.class, index = 9)
    private String orderStatus;
}
