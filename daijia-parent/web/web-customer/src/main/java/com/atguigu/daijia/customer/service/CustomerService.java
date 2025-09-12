package com.atguigu.daijia.customer.service;

import com.atguigu.daijia.model.form.customer.UpdateWxPhoneForm;
import com.atguigu.daijia.model.vo.customer.CustomerLoginVo;

public interface CustomerService {

    /**
     * 小程序授权登录
     * @param code
     * @return
     */
    String login(String code);

    /**
     * 获取客户登录信息
     * @param token
     * @return
     */
    CustomerLoginVo getCustomerLoginInfo(String token);

    /**
     * 获取客户信息
     * @param userId
     * @return
     */
    CustomerLoginVo getCustomerInfo(Long userId);

    /**
     * 更新客户微信手机号码
     * @param updateWxPhoneForm
     * @return
     */
    Boolean updateWxPhoneNumber(UpdateWxPhoneForm updateWxPhoneForm);
}
