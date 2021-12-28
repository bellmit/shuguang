package com.sofn.fdzem.mapper;


import com.sofn.fdzem.model.PlanktonMonitoring;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PlanktonMonitoringMapper {
    /**
     * 通过所属监测点id删除对应所有对象
     * @param monitoringPointId
     */
    void removeById(String monitoringPointId);
    /**
     * 更新批次
     * @param planktonMonitoring
     */
    void update(PlanktonMonitoring planktonMonitoring);
    /**
     *添加批次
     * @param planktonMonitoring
     */
    void insert(PlanktonMonitoring planktonMonitoring);


    /**
     * 通过所属监测点id获取信息
     * @param monitoringPointId
     * @return
     */
    PlanktonMonitoring selectListByQuery(String monitoringPointId);


}
