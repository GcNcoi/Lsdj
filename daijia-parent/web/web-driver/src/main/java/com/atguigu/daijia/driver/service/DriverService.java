package com.atguigu.daijia.driver.service;

import com.atguigu.daijia.model.form.driver.UpdateDriverAuthInfoForm;
import com.atguigu.daijia.model.vo.driver.DriverAuthInfoVo;
import com.atguigu.daijia.model.vo.driver.DriverLoginVo;

public interface DriverService {

    /**
     * 小程序授权登录
     * @param code 小程序登录凭证（code）
     * @return 司机id
     */
    String login(String code);

    /**
     * 获取司机登录信息
     * @param driverId 司机id
     * @return 司机登录信息
     */
    DriverLoginVo getDriverLoginInfo(Long driverId);

    /**
     * 获取司机认证信息
     * @param driverId 司机id
     * @return 司机认证信息
     */
    DriverAuthInfoVo getDriverAuthInfo(Long driverId);

    /**
     * 更新司机认证信息
     * @param updateDriverAuthInfoForm 司机认证信息
     * @return 是否更新成功
     */
    Boolean updateDriverAuthInfo(UpdateDriverAuthInfoForm updateDriverAuthInfoForm);
}
