package com.sofn.fdzem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.fdzem.model.MonitorStation;

public interface MonitorStationService extends IService<MonitorStation> {
    /**
     * 监测站信息维护查询
     * @return
     */
    Object getMonitorStationNameOrDetailInfo();

}
