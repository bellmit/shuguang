package com.sofn.agzirdd.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.agzirdd.model.MonitorCompanionSpecies;

import java.util.List;
import java.util.Map;

/**
 * @Description: 物种监测模块-伴生物种Service
 * @Author: mcc
 * @Date: 2020\3\10 0010
 */
public interface MonitorCompanionSpeciesService extends IService<MonitorCompanionSpecies> {

    /**
     * 获取满足条件的物种监测模块-伴生物种List
     * @param params 物种监测表id
     * @return List<MonitorCompanionSpecies>
     */
    List<MonitorCompanionSpecies> getMonitorCompanionSpeciesByCondition(Map<String,Object> params);

    /**
     * 批量新增入侵物种监测模块-伴生物种
     * @param list 物种监测模块-伴生物种list
     * @param speciesMonitorId 物种监测表id
     */
    void batchAddMonitorCompanionSpecies(List<MonitorCompanionSpecies> list, String speciesMonitorId);

    /**
     * 新增入侵物种监测模块-伴生物种
     * @param monitorCompanionSpecies monitorCompanionSpecies
     */
    void addMonitorCompanionSpecies(MonitorCompanionSpecies monitorCompanionSpecies);

    /**
     * 修改入侵物种监测模块-伴生物种
     * @param monitorCompanionSpecies monitorCompanionSpecies
     */
    void updateMonitorCompanionSpecies(MonitorCompanionSpecies monitorCompanionSpecies);

    /**
     *
     * 删除指定speciesMonitorId物种监测模块-伴生物种
     * @param speciesMonitorId speciesMonitorId
     * @return true or false
     */
    boolean removeMonitorCompanionSpecies(String speciesMonitorId);
}
