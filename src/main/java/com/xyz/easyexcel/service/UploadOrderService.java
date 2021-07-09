package com.xyz.easyexcel.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传接口
 */
public interface UploadOrderService {
    void uploadFile(MultipartFile multipartFile) throws Exception;
}
