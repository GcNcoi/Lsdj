package com.atguigu.daijia.driver.service;

import com.atguigu.daijia.model.vo.driver.CosUploadVo;
import org.springframework.web.multipart.MultipartFile;

public interface CosService {

    /**
     * 文件上传
     * @param file 图片信息
     * @param path 路径
     * @return 图片地址
     */
    CosUploadVo upload(MultipartFile file, String path);
}
