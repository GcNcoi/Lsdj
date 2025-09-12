package com.atguigu.daijia.driver.service;

import com.atguigu.daijia.model.vo.driver.CosUploadVo;
import org.springframework.web.multipart.MultipartFile;

public interface CosService {

    /**
     * 上传文件到腾讯云cos
     * @param file 上传的文件
     * @param path 上传的路径
     * @return 上传后的文件信息
     */
    CosUploadVo upload(MultipartFile file, String path);

    /**
     * 获取临时签名URL
     * @param path 图片路径
     * @return 临时签名URL
     */
    String getImageUrl(String path);

}
