package com.sofn.fdzem.mapper;


import com.sofn.fdzem.model.SedimentMonitoring;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SedimentMonitoringMapper {
    /**
     * 通过所属监测点id删除对应所有对象
     * @param monitoringPointId
     */
    void removeById(String monitoringPointId);
    /**
     * 更新批次
     * @param sedimentMonitoring
     */
    void update(SedimentMonitoring sedimentMonitoring);
    /**
     *添加批次
     * @param sedimentMonitoring
     */
    void insert(SedimentMonitoring sedimentMonitoring);


    /**
     * 通过所属监测点id获取信息
     * @param monitoringPointId
     * @return
     */
    SedimentMonitoring selectListByQuery(String monitoringPointId);


}
