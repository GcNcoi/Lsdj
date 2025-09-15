package com.atguigu.daijia.order.service;

import com.atguigu.daijia.model.entity.order.OrderInfo;
import com.atguigu.daijia.model.form.order.OrderInfoForm;
import com.baomidou.mybatisplus.extension.service.IService;

public interface OrderInfoService extends IService<OrderInfo> {

    /**
     * 保存订单信息
     * @param orderInfoForm 订单信息表单
     * @return 订单ID
     */
    Long saveOrderInfo(OrderInfoForm orderInfoForm);

    /**
     * 根据订单id获取订单状态
     * @param orderId 订单ID
     * @return 订单状态
     */
    Integer getOrderStatus(Long orderId);
}
