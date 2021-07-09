package com.xyz.easyexcel.service.impl;

import com.xyz.easyexcel.entity.Order;
import com.xyz.easyexcel.service.DownloadOrderService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单下载接口实现
 */
@Service
public class DownloadOrderServiceImpl implements DownloadOrderService {
    /**
     * 获取订单列表
     *
     * @return List<Order>
     */
    @Override
    public List<Order> getOrderList() {
        Order order = new Order();
        order.setName("小明")
                .setTradeNo("10088")
                .setPaymentToolType("WXPay")
                .setOrderAmount("100")
                .setMobile("1008611")
                .setIsNeedInvoice("1")
                .setPayStatus("S")
                .setTradeTime("2020-10-27")
                .setPrincipalName("xyz")
                .setOrderStatus("S");
        ArrayList<Order> orders = new ArrayList<>();
        orders.add(order);
        return orders;
    }
}
