package com.atguigu.daijia.driver.service;

import com.atguigu.daijia.model.vo.driver.DriverLicenseOcrVo;
import com.atguigu.daijia.model.vo.driver.IdCardOcrVo;
import org.springframework.web.multipart.MultipartFile;

public interface OcrService {

    /**
     * 身份证识别
     * @param file 身份证图片
     * @return 身份证信息
     */
    IdCardOcrVo idCardOcr(MultipartFile file);

    /**
     * 驾驶证识别
     * @param file 驾驶证图片
     * @return 驾驶证信息
     */
    DriverLicenseOcrVo driverLicenseOcr(MultipartFile file);
}
