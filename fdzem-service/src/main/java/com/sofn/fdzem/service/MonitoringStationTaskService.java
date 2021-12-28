package com.sofn.fdzem.service;


import com.github.pagehelper.PageInfo;
import com.sofn.fdzem.model.MonitoringStationTask;
import com.sofn.fdzem.vo.StatusVo;
import com.sofn.fdzem.vo.TrendAnalysisVo;

import java.util.List;

public interface MonitoringStationTaskService {
    /**
     * 监测站信息任务填报列表获取+分页+条件查询
     * @param pageNo
     * @param pageSize
     * @param year
     * @param status
     * @return
     */
    PageInfo<MonitoringStationTask> selectListByQuery(Integer pageNo, Integer pageSize, String year, Integer status);

    /**
     * 根据用户信息添加监测站填报信息
     * @param year
     */
    void insertMonitoringStationTask(String year);

    /**
     * 删除
     * @param id
     */
    void remove(String id);

    /**
     * 更新
     * @param monitoringStationTask
     */
    void update(MonitoringStationTask monitoringStationTask);

    /**
     * 上报
     * @param id
     */
//    void reportAll(String id);
    void report(String id);
    /**
     * 监测站信息任务填报列表获取+分页+条件查询审核页面
     * @param pageNo
     * @param pageSize
     * @param year
     * @param status
     * @return
     */
    PageInfo<MonitoringStationTask> selectListByQueryCheck(Integer pageNo, Integer pageSize, String year, Integer status);

    /**
     * 审核通过
     * @param id
     */
    void audit(String id);
    /**
     * 审核驳回
     * @param id
     */
    void reject(String id);

    /**
     * 信息汇总查询
     * @param pageNo
     * @param pageSize
     * @param year
     * @param name
     * @param provice
     * @param city
     * @param county
     * @param seaArea
     * @return
     */
    PageInfo<MonitoringStationTask> selectListByQueryInfo(Integer pageNo, Integer pageSize, String year, String name, String provice, String city, String county, String seaArea);

    /**
     * 趋势分析查询
     * @param pageNo
     * @param pageSize
     * @param year
     * @param name
     * @param type
     * @return
     */
    PageInfo<TrendAnalysisVo> selectListByQueryTrendAnalysis(Integer pageNo, Integer pageSize, String year, String name, String type);

    /**
     * 分布图示查询
     * @param provice
     * @param city
     * @param county
     * @return
     */
    List<MonitoringStationTask> selectGraphic(String provice, String city, String county);

    /**
     * 获取监测站下拉框
     * @return
     */
    List<String> getMonitorStation();

    /**
     * 获取状态下拉框
     * @return
     */
    List<StatusVo> getStatus();
}
