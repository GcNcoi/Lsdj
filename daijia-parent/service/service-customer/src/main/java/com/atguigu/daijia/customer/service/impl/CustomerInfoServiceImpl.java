package com.atguigu.daijia.customer.service.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import com.alibaba.fastjson.JSON;
import com.atguigu.daijia.customer.mapper.CustomerInfoMapper;
import com.atguigu.daijia.customer.mapper.CustomerLoginLogMapper;
import com.atguigu.daijia.customer.service.CustomerInfoService;
import com.atguigu.daijia.model.entity.customer.CustomerInfo;
import com.atguigu.daijia.model.entity.customer.CustomerLoginLog;
import com.atguigu.daijia.model.form.customer.UpdateWxPhoneForm;
import com.atguigu.daijia.model.vo.customer.CustomerLoginVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Slf4j
@Service
@SuppressWarnings({"unchecked", "rawtypes"})
public class CustomerInfoServiceImpl extends ServiceImpl<CustomerInfoMapper, CustomerInfo> implements CustomerInfoService {

    @Autowired
    private WxMaService wxMaService;

    @Autowired
    private CustomerInfoMapper customerInfoMapper;

    @Autowired
    private CustomerLoginLogMapper customerLoginLogMapper;

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public Long login(String code) {
        // 1. 获取code值，使用微信工具包对象，获取微信唯一标识opernId
        String openId = null;
        try {
            WxMaJscode2SessionResult sessionInfo = wxMaService.getUserService().getSessionInfo(code);
            openId = sessionInfo.getOpenid();
        } catch (WxErrorException e) {
            throw new RuntimeException(e);
        }
        // 2. 根据openId查询数据库表，判断是否第一次登录
        CustomerInfo customerInfo = customerInfoMapper.selectOne(
                new LambdaQueryWrapper<CustomerInfo>().eq(CustomerInfo::getWxOpenId, openId));
        if (customerInfo == null) {
            // 3. 如果第一次登录，添加信息到用户表
            customerInfo = new CustomerInfo();
            customerInfo.setNickname(String.valueOf(System.currentTimeMillis()));
            customerInfo.setAvatarUrl("https://oss.aliyuncs.com/aliyun_id_photo_bucket/default_handsome.jpg");
            customerInfo.setWxOpenId(openId);
            customerInfoMapper.insert(customerInfo);
        }
        // 4. 记录登录日志信息
        CustomerLoginLog customerLoginLog = new CustomerLoginLog();
        customerLoginLog.setCustomerId(customerInfo.getId());
        customerLoginLog.setMsg("小程序登录");
        customerLoginLogMapper.insert(customerLoginLog);
        // 5. 返回用户id
        return customerInfo.getId();
    }

    @Override
    public CustomerLoginVo getCustomerLoginInfo(Long customerId) {
        // 1. 根据用户id查询用户信息
        CustomerInfo customerInfo = customerInfoMapper.selectById(customerId);
        // 2. 封装到CustomerLoginVo
        CustomerLoginVo customerLoginVo = new CustomerLoginVo();
        BeanUtils.copyProperties(customerInfo, customerLoginVo);
        customerLoginVo.setIsBindPhone(StringUtils.hasText(customerInfo.getPhone()));
        // 3. 返回CustomerLoginVo
        return customerLoginVo;
    }

    @Override
    @SneakyThrows
    @Transactional(rollbackFor = {Exception.class})
    public Boolean updateWxPhoneNumber(UpdateWxPhoneForm updateWxPhoneForm) {
        // 1. 根据code值获取微信绑定手机号码
        WxMaPhoneNumberInfo phoneInfo = wxMaService.getUserService().getPhoneNoInfo(updateWxPhoneForm.getCode());
        String phoneNumber = phoneInfo.getPhoneNumber();
        log.info("phoneInfo:{}", JSON.toJSONString(phoneInfo));
        // 2. 更新用户信息
        CustomerInfo customerInfo = new CustomerInfo();
        customerInfo.setId(updateWxPhoneForm.getCustomerId());
        customerInfo.setPhone(phoneNumber);
        return this.updateById(customerInfo);
    }
}
