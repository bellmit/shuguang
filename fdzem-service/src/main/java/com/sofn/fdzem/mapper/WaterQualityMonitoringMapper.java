package com.sofn.fdzem.mapper;


import com.sofn.fdzem.model.WaterQualityMonitoring;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WaterQualityMonitoringMapper {
    /**
     * 通过所属监测点id删除对应所有对象
     * @param monitoringPointId
     */
    void removeById(String monitoringPointId);
    /**
     * 更新批次
     * @param waterQualityMonitoring
     */
    void update(WaterQualityMonitoring waterQualityMonitoring);
    /**
     *添加批次
     * @param waterQualityMonitoring
     */
    void insert(WaterQualityMonitoring waterQualityMonitoring);


    /**
     * 通过所属监测点id获取信息
     * @param monitoringPointId
     * @return
     */
    WaterQualityMonitoring selectListByQuery(String monitoringPointId);


}
