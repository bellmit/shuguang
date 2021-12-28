package com.sofn.agzirdd.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Maps;
import com.sofn.agzirdd.mapper.MonitorCompanionSpeciesMapper;
import com.sofn.agzirdd.model.MonitorCompanionSpecies;
import com.sofn.agzirdd.service.MonitorCompanionSpeciesService;
import com.sofn.common.exception.SofnException;
import com.sofn.common.utils.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description: 物种监测模块-伴生物种ServiceImpl
 * @Author: mcc
 * @Date: 2020\3\10 0010
 */
@Service
public class MonitorCompanionSpeciesServiceImpl extends ServiceImpl<MonitorCompanionSpeciesMapper, MonitorCompanionSpecies> implements MonitorCompanionSpeciesService {

    @Autowired
    private MonitorCompanionSpeciesMapper monitorCompanionSpeciesMapper;

    @Override
    public List<MonitorCompanionSpecies> getMonitorCompanionSpeciesByCondition(Map<String, Object> params) {

        return monitorCompanionSpeciesMapper.getMonitorCompanionSpeciesByCondition(params);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchAddMonitorCompanionSpecies(List<MonitorCompanionSpecies> list, String speciesMonitorId) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("speciesMonitorId",speciesMonitorId);
        List<MonitorCompanionSpecies> monitorCompanionSpeciesList =
                monitorCompanionSpeciesMapper.getMonitorCompanionSpeciesByCondition(params);
        //判断是否存在历史数据
        if(monitorCompanionSpeciesList.size()>0){
            //存在则移除以前的历史数据
            monitorCompanionSpeciesMapper.deleteMonitorCompanionSpecies(speciesMonitorId);
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
                        throw new SofnException("外来伴生物种信息保存异常!");
                    }
                }
        );
    }

    @Override
    public void addMonitorCompanionSpecies(MonitorCompanionSpecies monitorCompanionSpecies) {
        this.save(monitorCompanionSpecies);
    }

    @Override
    public void updateMonitorCompanionSpecies(MonitorCompanionSpecies monitorCompanionSpecies) {
        this.updateById(monitorCompanionSpecies);
    }

    @Override
    public boolean removeMonitorCompanionSpecies(String speciesMonitorId) {
        return monitorCompanionSpeciesMapper.deleteMonitorCompanionSpecies(speciesMonitorId);
    }
}
