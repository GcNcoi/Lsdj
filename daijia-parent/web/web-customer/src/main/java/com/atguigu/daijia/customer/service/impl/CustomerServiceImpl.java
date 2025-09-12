package com.atguigu.daijia.customer.service.impl;

import com.atguigu.daijia.common.constant.RedisConstant;
import com.atguigu.daijia.common.execption.GuiguException;
import com.atguigu.daijia.common.result.Result;
import com.atguigu.daijia.common.result.ResultCodeEnum;
import com.atguigu.daijia.customer.client.CustomerInfoFeignClient;
import com.atguigu.daijia.customer.service.CustomerService;
import com.atguigu.daijia.model.form.customer.UpdateWxPhoneForm;
import com.atguigu.daijia.model.vo.customer.CustomerLoginVo;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@SuppressWarnings({"unchecked", "rawtypes"})
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerInfoFeignClient customerInfoFeignClient;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public String login(String code) {
        // 1. 通过code进行远程调用，返回用户id
        Result<Long> loginResult = customerInfoFeignClient.login(code);
        // 2. 判断如果返回失败，则返回错误提示
        if (!loginResult.getCode().equals(200)) {
            throw new GuiguException(ResultCodeEnum.DATA_ERROR);
        }
        // 3. 获取远程调用的用户id
        Long customerId = loginResult.getData();
        // 4. 判断返回用户id是否为空，如果为空，返回错误提示
        if (customerId == null) {
            throw new GuiguException(ResultCodeEnum.DATA_ERROR);
        }
        // 5. 生成token字符串
        String token = UUID.randomUUID().toString().replace("-", "");
        // 6. 把用户id放到redis中，设置过期时间
        redisTemplate.opsForValue().set(RedisConstant.USER_LOGIN_KEY_PREFIX + token, customerId.toString(), RedisConstant.USER_LOGIN_KEY_TIMEOUT, TimeUnit.SECONDS);
        // 7. 返回token字符串
        return token;
    }

    @Override
    public CustomerLoginVo getCustomerLoginInfo(String token) {
        // 1. 从redis中获取用户id
        String customerId = (String) redisTemplate.opsForValue().get(RedisConstant.USER_LOGIN_KEY_PREFIX + token);
        // 2. 判断用户id是否为空
        if (!StringUtils.hasText(customerId)) {
            throw new GuiguException(ResultCodeEnum.DATA_ERROR);
        }
        // 3. 调用远程服务，根据用户id获取客户登录信息
        Result<CustomerLoginVo> customerLoginVoResult = customerInfoFeignClient.getCustomerLoginInfo(Long.parseLong(customerId));
        // 3.1 判断返回结果是否为空
        if (!customerLoginVoResult.getCode().equals(200)) {
            throw new GuiguException(ResultCodeEnum.DATA_ERROR);
        }
        // 4. 判断返回结果是否为空
        if (customerLoginVoResult.getData() == null) {
            throw new GuiguException(ResultCodeEnum.DATA_ERROR);
        }
        // 5. 返回客户登录信息
        return customerLoginVoResult.getData();
    }

    @Override
    public CustomerLoginVo getCustomerInfo(Long customerId) {
        // 1. 调用远程服务，根据用户id获取客户登录信息
        Result<CustomerLoginVo> customerLoginVoResult = customerInfoFeignClient.getCustomerLoginInfo(customerId);
        // 1.1 判断返回结果是否为空
        if (!customerLoginVoResult.getCode().equals(200)) {
            throw new GuiguException(ResultCodeEnum.DATA_ERROR);
        }
        // 2. 判断返回结果是否为空
        if (customerLoginVoResult.getData() == null) {
            throw new GuiguException(ResultCodeEnum.DATA_ERROR);
        }
        // 3. 返回客户登录信息
        return customerLoginVoResult.getData();
    }

    @Override
    public Boolean updateWxPhoneNumber(UpdateWxPhoneForm updateWxPhoneForm) {
        customerInfoFeignClient.updateWxPhoneNumber(updateWxPhoneForm);
        return true;
    }

}
