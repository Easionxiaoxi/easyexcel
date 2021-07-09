package com.xyz.easyexcel.service.impl;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.xyz.easyexcel.enumeration.NeedInvoiceEnum;

/**
 * 是否需要发票转换器
 */
public class NeedInvoiceConverter implements Converter<String> {
    @Override
    public Class supportJavaTypeKey() {
        return String.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public String convertToJavaData(CellData cellData, ExcelContentProperty excelContentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        return NeedInvoiceEnum.getNeedByDesc(cellData.getStringValue());
    }

    @Override
    public CellData convertToExcelData(String s, ExcelContentProperty excelContentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        CellData<Object> cellData = new CellData<>(NeedInvoiceEnum.getDescByNeed(s));
        return cellData ;
    }
}
