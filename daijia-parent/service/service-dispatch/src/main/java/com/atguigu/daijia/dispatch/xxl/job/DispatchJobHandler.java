package com.atguigu.daijia.dispatch.xxl.job;

import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @BelongsProject: daijia-parent
 * @BelongsPackage: com.atguigu.daijia.dispatch.xxl.job
 * @Author: GuoXiaofeng
 * @CreateTime: 2025-09-22  23:36
 * @Description: TODO
 * @Version: 1.0
 */
@Slf4j
@Component
public class DispatchJobHandler {

    @XxlJob("firstJobHandler")
    public void firstJobHandler() {
        log.info("xxl-job项目集成测试");
    }
}