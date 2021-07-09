package com.xyz.easyexcel.service.impl;

import com.alibaba.excel.EasyExcel;
import com.xyz.easyexcel.entity.Order;
import com.xyz.easyexcel.global.CustomerException;
import com.xyz.easyexcel.global.GlobalResultCodeEnum;
import com.xyz.easyexcel.service.UploadOrderService;
import com.xyz.easyexcel.util.FileTypeUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传接口实现类
 */
@Service
public class UploadOrderServiceImpl implements UploadOrderService {

    @Override
    public void uploadFile(MultipartFile multipartFile) throws Exception {
        // 1.非空校验
        if (multipartFile.isEmpty()) {
            throw new CustomerException(GlobalResultCodeEnum.param_blank);
        }
        // 2.特殊字符校验
        if (multipartFile.getOriginalFilename().contains(",")) {
            throw new CustomerException(GlobalResultCodeEnum.param_type_error);
        }
        // 3.文件类型校验
        String type = FileTypeUtils.getFileTypeByMagicNumber(multipartFile.getInputStream());
        System.out.println("文件类型" + type);

        // 4.文件大小校验
        double fileSize = multipartFile.getSize() / 1048576;
        System.out.println("文件大小" + fileSize);

        // 5.easyExcel读取数据headRowNumber比excel要多1
        EasyExcel.read(multipartFile.getInputStream(), Order.class, new OrderListener())
                .sheet() // 读取第几个sheet
                .headRowNumber(2) // 有几行表头
                .autoTrim(true) // 自动去掉数据头尾的空格
                .doRead();
    }
}
