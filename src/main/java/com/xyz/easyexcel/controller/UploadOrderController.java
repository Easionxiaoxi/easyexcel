package com.xyz.easyexcel.controller;

import com.xyz.easyexcel.service.UploadOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


/**
 * 文件上传控制器@CrossOrigin跨域配置
 */
@RestController
@RequestMapping("/upload")
@CrossOrigin
public class UploadOrderController {
    @Autowired
    UploadOrderService uploadOrderService;

    /**
     * 读取订单excel数据
     * @param multipartFile 文件
     * @return void
     */
    @PostMapping("/uploadFile")
    public void uploadFile(@RequestParam("file") MultipartFile multipartFile) throws Exception {
        this.uploadOrderService.uploadFile(multipartFile);
    }

}
