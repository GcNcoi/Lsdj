package com.atguigu.daijia.driver.service;

public interface OrderService {

    /**
     * 查询订单状态
     * @param orderId 订单id
     * @return 订单状态
     */
    Integer getOrderStatus(Long orderId);

}
