package com.sofn.agzirdd.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.agzirdd.model.MonitorCompanionSpecies;
import com.sofn.agzirdd.model.MonitorInvasiveAlienSpecies;

import java.util.List;
import java.util.Map;

/**
 * @Description: 物种监测模块-入侵物种Service
 * @Author: mcc
 * @Date: 2020\3\10 0010
 */
public interface MonitorInvasiveAlienSpeciesService extends IService<MonitorInvasiveAlienSpecies> {

    /**
     * 获取满足条件的物种监测模块-入侵物种List
     * @param params 物种监测表id
     * @return List<MonitorInvasiveAlienSpecies>
     */
    List<MonitorInvasiveAlienSpecies> getMonitorInvasiveAlienSpeciesByCondition(Map<String,Object> params);


    /**
     * 批量新增入侵物种监测模块-入侵物种
     * @param list 物种监测模块-入侵物种list数据
     * @param speciesMonitorId 物种监测表id
     */
    void batchAddMonitorInvasiveAlienSpecies(List<MonitorInvasiveAlienSpecies> list, String speciesMonitorId);


    /**
     * 新增入侵物种监测模块-入侵物种
     * @param monitorInvasiveAlienSpecies monitorInvasiveAlienSpecies
     */
    void addMonitorInvasiveAlienSpecies(MonitorInvasiveAlienSpecies monitorInvasiveAlienSpecies);

    /**
     * 修改入侵物种监测模块-入侵物种
     * @param monitorInvasiveAlienSpecies monitorInvasiveAlienSpecies
     */
    void updateMonitorInvasiveAlienSpecies(MonitorInvasiveAlienSpecies monitorInvasiveAlienSpecies);

    /**
     * 删除指定speciesMonitorId物种监测模块-入侵物种
     * @param speciesMonitorId speciesMonitorId
     * @return true or false
     */
    boolean removeMonitorInvasiveAlienSpecies(String speciesMonitorId);
}
