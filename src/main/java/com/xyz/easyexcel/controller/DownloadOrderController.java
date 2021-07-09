package com.xyz.easyexcel.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.xyz.easyexcel.entity.Order;
import com.xyz.easyexcel.service.DownloadOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * 写easyexcel控制器
 */
@RestController
@CrossOrigin
@RequestMapping("/down")
public class DownloadOrderController {
    @Autowired
    DownloadOrderService downloadOrderService;

    /**
     * 导出订单excel
     * @param response 响应
     * @return void
     */
    @GetMapping("/downloadFile")
    public void downloadFile(HttpServletResponse response) throws IOException {
        ExcelWriter excelWriter = null;
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            String fileName = URLEncoder.encode("订单", "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
            // 写单个sheet，自动列宽，设置不关闭流，sheet名称
            // EasyExcel.write(response.getOutputStream(), Order.class).registerWriteHandler(new LongestMatchColumnWidthStyleStrategy()).autoCloseStream(Boolean.FALSE).sheet("订单").doWrite(this.downloadOrderService.getOrderList());
            // 写多个不同的sheet，同一个数据对象
            excelWriter = EasyExcel.write(response.getOutputStream(), Order.class).registerWriteHandler(new LongestMatchColumnWidthStyleStrategy()).build();
            // 每次创建不同的WriteSheet对象
            WriteSheet aliPayWriteSheet = EasyExcel.writerSheet(0, "支付宝").build();
            WriteSheet wXPayWriteSheet = EasyExcel.writerSheet(1, "微信").build();
            // 写入
            excelWriter.write(this.downloadOrderService.getOrderList(), aliPayWriteSheet);
            excelWriter.write(this.downloadOrderService.getOrderList(), wXPayWriteSheet);
        } catch (Exception e) {
            // 重置response
            response.reset();
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().println(e.getMessage());
            e.printStackTrace();
        } finally {
            // 千万别忘记finish 会帮忙关闭流
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
    }
}
