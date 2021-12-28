package com.sofn.fdpi.service;

import com.sofn.fdpi.model.Region;

import java.util.Map;

public interface RegionService {

    /**
     * 同步支撑平台行政区域数据
     */
    void handleAreaData();

    Map<String, String> getAreaDataMap();

    /**
     * 根据编码获取区域信息
     */
    Region getByCode(String code);

    Region getById(String id);

}
