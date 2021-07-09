package com.xyz.easyexcel.controller;

import com.alibaba.excel.EasyExcel;
import com.xyz.easyexcel.entity.Order;
import com.xyz.easyexcel.service.DownloadOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * easyexcel填充控制器
 */
@RestController
@CrossOrigin
@RequestMapping("/fill")
public class FillOrderController {
    @Autowired
    DownloadOrderService downloadOrderService;

    @GetMapping("/fillFile")
    public void fillFile(HttpServletResponse response) throws IOException {
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            String fileName = URLEncoder.encode("订单", "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
            // 获取模板路径
            String filePath = new ClassPathResource("templates/orderForm.xlsx").getFile().getPath();
            // 写excel
            EasyExcel.write(response.getOutputStream(), Order.class).withTemplate(filePath).sheet("订单").doFill(this.downloadOrderService.getOrderList());
        } catch (UnsupportedEncodingException e) {
            // 重置response
            response.reset();
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().println(e.getMessage());
            e.printStackTrace();
        }
    }
}
