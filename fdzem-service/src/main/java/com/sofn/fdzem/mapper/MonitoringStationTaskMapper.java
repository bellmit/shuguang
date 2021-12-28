package com.sofn.fdzem.mapper;

import com.sofn.fdzem.model.MonitorStation;
import com.sofn.fdzem.model.MonitoringStationTask;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface MonitoringStationTaskMapper {
    /**
     * 通过id删除该任务
     *
     * @param id
     */
    void removeById(String id);

    /**
     * 更新监测站信息任务
     *
     * @param monitoringStationTask
     */
    void update(MonitoringStationTask monitoringStationTask);

    /**
     * @param monitoringStationTask
     */
    void insert(MonitoringStationTask monitoringStationTask);

    /**
     * 状态修改
     *
     * @param id
     */
    void updateStatus(@Param("id") String id, @Param("status") Integer status);

    /**
     * 监测批次数修改
     *
     * @param id
     */
    void updateMonitoringStationNumber(@Param("id") String id, @Param("monitoringStationNumber") Integer monitoringStationNumber);

    /**
     * 分页查询+条件查询
     *
     * @param params
     * @return
     */
    List<MonitoringStationTask> selectListByQuery(Map params);

    MonitorStation selectByName(String name);

    /**
     * 信息汇总查询
     *
     * @param params
     * @return
     */
    List<MonitoringStationTask> selectListByQueryInfo(Map params);

    /**
     * 趋势分析查询近5年的5条数据
     *
     * @param params
     * @return
     */
    List<MonitoringStationTask> selectTrend(Map params);

    /**
     * 根据监测站维护更新数据
     *
     * @return
     */
    Integer updateByMonitorStation(MonitorStation monitorStation);

    /**
     * 根据名字集合批量跟新
     *
     * @param names 名字集合
     * @param id 监测中心id
     * @return
     */
    Integer updateBynames(List<String> names, String id);

    /**
     * 将organization_parent_id为id的清空
     * @param id
     * @return
     */
    Integer updateMonitroIdById(String id);
}
