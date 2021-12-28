package com.sofn.fdzem.mapper;


import com.sofn.fdzem.model.BiologicalResidueMonitoring;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BiologicalResidueMonitoringMapper {
    /**
     * 通过所属监测点id删除对应所有对象
     * @param monitoringPointId
     */
    void removeById(String monitoringPointId);
    /**
     * 更新批次
     * @param biologicalResidueMonitoring
     */
    void update(BiologicalResidueMonitoring biologicalResidueMonitoring);
    /**
     *添加批次
     * @param biologicalResidueMonitoring
     */
    void insert(BiologicalResidueMonitoring biologicalResidueMonitoring);


    /**
     * 通过所属监测点id获取信息
     * @param monitoringPointId
     * @return
     */
    BiologicalResidueMonitoring selectListByQuery(String monitoringPointId);


}
