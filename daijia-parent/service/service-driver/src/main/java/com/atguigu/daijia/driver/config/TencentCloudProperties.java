package com.atguigu.daijia.driver.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 功能描述: 腾讯云配置
 *
 * @author: Gxf
 * @date: 2025年09月12日 15:41
 */
@Data
@Component
@ConfigurationProperties(prefix = "tencent.cloud")
public class TencentCloudProperties {
    /**
     * 功能描述: 腾讯云 secretId
     */
    private String secretId;
    /**
     * 功能描述: 腾讯云 secretKey
     */
    private String secretKey;
    /**
     * 功能描述: 腾讯云 region
     */
    private String region;
    /**
     * 功能描述: 腾讯云 bucketPrivate
     */
    private String bucketPrivate;
    /**
     * 功能描述: 腾讯云 personGroupId
     */
    private String personGroupId;

}