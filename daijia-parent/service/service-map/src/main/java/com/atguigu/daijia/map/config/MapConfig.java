package com.atguigu.daijia.map.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @BelongsProject: daijia-parent
 * @BelongsPackage: com.atguigu.daijia.map.config
 * @Author: GuoXiaofeng
 * @CreateTime: 2025-09-15  00:25
 * @Description: TODO
 * @Version: 1.0
 */
@Configuration
public class MapConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
