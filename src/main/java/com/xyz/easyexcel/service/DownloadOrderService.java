package com.xyz.easyexcel.service;

import com.xyz.easyexcel.entity.Order;

import java.util.List;

/**
 * 文件下载接口
 */
public interface DownloadOrderService {
    /**
     * 获取订单列表
     *
     * @return List<Order>
     */
    List<Order> getOrderList();
}
