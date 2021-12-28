package com.sofn.fdzem.mapper;


import com.sofn.fdzem.model.MonitoringPoint;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MonitoringPointMapper {
    /**
     * 通过批次id删除
     * @param batchManagementId
     */
    void removeById(String batchManagementId);
    /**
     * 更新批次
     * @param monitoringPoint
     */
    void update(MonitoringPoint monitoringPoint);
    /**
     *添加监测点
     * @param monitoringPoint
     */
    void insert(MonitoringPoint monitoringPoint);


    /**
     * 列表
     * @param batchManagementId
     * @return
     */
    List<MonitoringPoint> selectListByQuery(String batchManagementId);


}
