package com.sofn.agzirdd.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.agzirdd.model.WarningMonitor;
import com.sofn.agzirdd.vo.WarningMonitorVo;
import com.sofn.common.map.MapComponentImpl;
import com.sofn.common.map.MapViewData;

import java.util.List;
import java.util.Map;

/**
 *
 */
public interface WarningMonitorService extends IService<WarningMonitor>, MapComponentImpl {

    /**
     * 根据查询条件查询预警阈值列表(不分页)
     */
    List<WarningMonitorVo> getWarningMonitorVoList(Map<String, String> params);
    /**
     * 预警图信息接口
     */
    MapViewData getMapViewData(String index, String adLevel, String adCode, Map<String,String> conditions);

    /**
     * 查询有数据的日期信息列表(不分页)
     */
    List<String> getMonitorTimeList(String index, String speciesName);

    /**
     * 根据条件删除预警信息
     */
    boolean deleteByCondition(Map<String, String> params);

    /**
     * 根据关联SpeciesMonitorId删除相关数据
     * @param id
     * @return
     */
    boolean deleteBySpeciesMonitorId(String id);

}
