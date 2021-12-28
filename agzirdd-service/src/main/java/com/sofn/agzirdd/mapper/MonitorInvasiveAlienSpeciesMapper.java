package com.sofn.agzirdd.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.agzirdd.model.MonitorInvasiveAlienSpecies;
import com.sofn.agzirdd.model.SpeciesMonitor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Description: 物种监测模块-入侵物种Mapper
 * @Author: mcc
 * @Date: 2020\3\10 0010
 */
@Mapper
public interface MonitorInvasiveAlienSpeciesMapper extends BaseMapper<MonitorInvasiveAlienSpecies> {

    /**
     * 获取满足条件的物种监测模块-入侵物种List
     * @param params 物种监测表id
     * @return List<MonitorInvasiveAlienSpecies>
     */
    List<MonitorInvasiveAlienSpecies> getMonitorInvasiveAlienSpeciesByCondition(Map<String,Object> params);

    /**
     * 删除指定speciesMonitorId物种监测模块-入侵物种
     * @param speciesMonitorId speciesMonitorId
     * @return true or false
     */
    boolean deleteMonitorInvasiveAlienSpecies(String speciesMonitorId);
}