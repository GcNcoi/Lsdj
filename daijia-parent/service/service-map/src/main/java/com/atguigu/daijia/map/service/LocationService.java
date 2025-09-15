package com.atguigu.daijia.map.service;

import com.atguigu.daijia.model.form.map.UpdateDriverLocationForm;

public interface LocationService {

    /**
     * 开启接单服务：更新司机经纬度位置
     * @param updateDriverLocationForm
     * @return
     */
    Boolean updateDriverLocation(UpdateDriverLocationForm updateDriverLocationForm);

    /**
     * 关闭接单服务：删除司机经纬度位置
     * @param driverId
     * @return
     */
    Boolean removeDriverLocation(Long driverId);
}
