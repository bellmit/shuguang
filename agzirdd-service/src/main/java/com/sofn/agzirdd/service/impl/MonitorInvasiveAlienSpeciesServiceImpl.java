package com.sofn.agzirdd.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Maps;
import com.sofn.agzirdd.mapper.MonitorInvasiveAlienSpeciesMapper;
import com.sofn.agzirdd.model.MonitorInvasiveAlienSpecies;
import com.sofn.agzirdd.service.MonitorInvasiveAlienSpeciesService;
import com.sofn.common.exception.SofnException;
import com.sofn.common.utils.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description: 物种监测模块-入侵物种ServiceImpl
 * @Author: mcc
 * @Date: 2020\3\10 0010
 */
@Service
public class MonitorInvasiveAlienSpeciesServiceImpl extends ServiceImpl<MonitorInvasiveAlienSpeciesMapper, MonitorInvasiveAlienSpecies> implements MonitorInvasiveAlienSpeciesService {

    @Autowired
    private MonitorInvasiveAlienSpeciesMapper monitorInvasiveAlienSpeciesMapper;

    @Override
    public List<MonitorInvasiveAlienSpecies> getMonitorInvasiveAlienSpeciesByCondition(Map<String, Object> params) {

        return monitorInvasiveAlienSpeciesMapper.getMonitorInvasiveAlienSpeciesByCondition(params);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchAddMonitorInvasiveAlienSpecies(List<MonitorInvasiveAlienSpecies> list, String speciesMonitorId) {

        Map<String, Object> params = Maps.newHashMap();
        params.put("speciesMonitorId",speciesMonitorId);
        List<MonitorInvasiveAlienSpecies> monitorInvasiveAlienSpeciesList =
                monitorInvasiveAlienSpeciesMapper.getMonitorInvasiveAlienSpeciesByCondition(params);
        //判断是否存在历史数据
        if(monitorInvasiveAlienSpeciesList.size()>0){
            //存在则移除以前的历史数据
            monitorInvasiveAlienSpeciesMapper.deleteMonitorInvasiveAlienSpecies(speciesMonitorId);
        }
        //循环新增新的数据
        list.forEach(
                baseData ->{
                    //放入id
                    baseData.setId(IdUtil.getUUId());
                    //放入物种监测表id
                    baseData.setSpeciesMonitorId(speciesMonitorId);
                    //放入创建时间
                    baseData.setCreateTime(new Date());
                    boolean save = this.save(baseData);
                    if(!save){
                        throw new SofnException("外来入侵物种信息保存异常!");
                    }
                }
        );
    }

    @Override
    public void addMonitorInvasiveAlienSpecies(MonitorInvasiveAlienSpecies monitorInvasiveAlienSpecies) {

        this.save(monitorInvasiveAlienSpecies);
    }

    @Override
    public void updateMonitorInvasiveAlienSpecies(MonitorInvasiveAlienSpecies monitorInvasiveAlienSpecies) {

        this.updateById(monitorInvasiveAlienSpecies);
    }

    @Override
    public boolean removeMonitorInvasiveAlienSpecies(String speciesMonitorId) {

        return monitorInvasiveAlienSpeciesMapper.deleteMonitorInvasiveAlienSpecies(speciesMonitorId);
    }
}
