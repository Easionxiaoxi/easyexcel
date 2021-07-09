package com.xyz.easyexcel.service.impl;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.xyz.easyexcel.enumeration.PayToolEnum;

/**
 * 支付类型转换
 */
public class PayToolTypeConverter implements Converter<String> {
    /**
     * 该单元格转成对应的Java字段的类型
     *
     * @return Class
     */
    @Override
    public Class supportJavaTypeKey() {
        return String.class;
    }

    /**
     * 该字段对应的单元格的格式
     *
     * @return CellDataTypeEnum
     */
    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    /**
     * 读取excel对应字段的类型转义
     *
     * @return String
     */
    @Override
    public String convertToJavaData(CellData cellData, ExcelContentProperty excelContentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        return PayToolEnum.getToolByDesc(cellData.getStringValue());
    }

    /**
     * 写excel对应字段的类型转义
     *
     * @return CellData
     */
    @Override
    public CellData convertToExcelData(String s, ExcelContentProperty excelContentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        CellData<String> cellData = new CellData<>(PayToolEnum.getDescByTool(s));
        return cellData;
    }
}
