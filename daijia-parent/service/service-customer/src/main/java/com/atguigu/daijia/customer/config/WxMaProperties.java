package com.atguigu.daijia.customer.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 功能描述:微信小程序配置类
 *
 * @author: Gxf
 * @date: 2025年09月12日 9:59
 */
@Data
@Component
@ConfigurationProperties(prefix = "wx.miniapp")
public class WxMaProperties {
    /**
     * 小程序appId
     */
    private String appId;
    /**
     * 小程序appSecret
     */
    private String secret;

}
