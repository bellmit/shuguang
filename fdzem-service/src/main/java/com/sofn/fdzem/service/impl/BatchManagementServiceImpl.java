package com.sofn.fdzem.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.sofn.common.exception.SofnException;
import com.sofn.common.model.Result;
import com.sofn.common.utils.IdUtil;
import com.sofn.fdzem.enums.StatusEnum;
import com.sofn.fdzem.feign.OrgFeign;
import com.sofn.fdzem.feign.UserFeign;
import com.sofn.fdzem.mapper.*;
import com.sofn.fdzem.model.BatchManagement;
import com.sofn.fdzem.model.MonitorStation;
import com.sofn.fdzem.model.MonitoringPoint;
import com.sofn.fdzem.model.MonitoringStationTask;
import com.sofn.fdzem.service.BatchManagementService;
import com.sofn.fdzem.service.MonitoringStationTaskService;
import com.sofn.fdzem.util.JustGetOrganization;
import com.sofn.fdzem.vo.SysOrgVo;
import com.sofn.fdzem.vo.SysOrganizationForm;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
@Slf4j
public class BatchManagementServiceImpl implements BatchManagementService {

    @Autowired
    private MonitoringStationTaskMapper monitoringStationTaskMapper;
    @Autowired
    private BatchManagementMapper batchManagementMapper;
    @Autowired
    private MonitoringPointMapper monitoringPointMapper;
    @Autowired
    private BiologicalResidueMonitoringMapper biologicalResidueMonitoringMapper;
    @Autowired
    private PlanktonMonitoringMapper planktonMonitoringMapper;
    @Autowired
    private SedimentMonitoringMapper sedimentMonitoringMapper;
    @Autowired
    private WaterQualityMonitoringMapper waterQualityMonitoringMapper;


    @Override
    public PageInfo<BatchManagement> selectListByQuery(Integer pageNo, Integer pageSize, String name, String samplingTimeLeft, String samplingTimeRight, String submittedTimeLeft, String submittedTimeRight, String monitoringStationTaskId) {
//        Map<String,Object> params = Maps.newHashMap();
//        params.put("name",name);
//        params.put("monitoringStationTaskId",monitoringStationTaskId);
//        params.put("samplingTimeLeft", StringUtils.isBlank(samplingTimeLeft) ? "1970-01-01 00:00:00" : samplingTimeLeft);
//        params.put("samplingTimeRight", StringUtils.isBlank(samplingTimeRight) ? "2080-01-01 00:00:00" : samplingTimeRight);
//        params.put("submittedTimeLeft", StringUtils.isBlank(submittedTimeLeft) ? "1970-01-01 00:00:00" : submittedTimeLeft);
//        params.put("submittedTimeRight", StringUtils.isBlank(submittedTimeRight) ? "2080-01-01 00:00:00" : submittedTimeRight);
//        List<BatchManagement> batchManagements = batchManagementMapper.selectListByQuery(params);
        Page<BatchManagement> page = new Page<>();
        page.setSize(pageSize);
        page.setCurrent(pageNo);
        QueryWrapper<BatchManagement> lambdaQueryWrapper = new QueryWrapper<>();
        lambdaQueryWrapper.like(!StringUtils.isBlank(name), "name", name);
        lambdaQueryWrapper.eq("monitoring_station_task_id", monitoringStationTaskId);
        lambdaQueryWrapper.between(!StringUtils.isBlank(samplingTimeLeft) && !StringUtils.isBlank(samplingTimeRight), "samp_ling_time", samplingTimeLeft + " 00:00:00", samplingTimeRight + " 23:59:59");
        lambdaQueryWrapper.between(!StringUtils.isBlank(submittedTimeLeft) && !StringUtils.isBlank(submittedTimeRight), "submitted_time", submittedTimeLeft + " 00:00:00", submittedTimeRight + " 23:59:59");
        IPage<BatchManagement> batchManagementPage = batchManagementMapper.selectPage(page, lambdaQueryWrapper);
//        List<BatchManagement> newlist;
//        if(pageNo*pageSize>batchManagements.size()){
//            newlist=batchManagements.subList((pageNo-1)*pageSize, batchManagements.size());
//        }else{
//            newlist=batchManagements.subList((pageNo-1)*pageSize, pageNo*pageSize);
//        }
        PageInfo<BatchManagement> pageInfo = new PageInfo<>(batchManagementPage.getRecords());
        pageInfo.setTotal(batchManagementPage.getTotal());
        pageInfo.setPageSize((int) batchManagementPage.getSize());
        pageInfo.setPageNum(pageNo);
        return pageInfo;
    }

    @Transactional(
            rollbackFor = {Exception.class}
    )
    @Override
    public void insertAll(BatchManagement batchManagement) {
        batchManagement.setId(IdUtil.getUUId());
        int count =batchManagementMapper.selectCount(new LambdaQueryWrapper<BatchManagement>().eq(BatchManagement::getMonitoringStationTaskId,batchManagement.getMonitoringStationTaskId()).eq(BatchManagement::getName,batchManagement.getName()));
        if(count>0){
            throw new SofnException("当前年度监测批次名称重复！");
        }
        monitoringStationTaskMapper.updateMonitoringStationNumber(batchManagement.getMonitoringStationTaskId(),batchManagementMapper.total(batchManagement.getMonitoringStationTaskId())+1);
        List<MonitoringPoint> monitoringPoints = batchManagement.getMonitoringPoints();
        if (monitoringPoints!=null&&monitoringPoints.size()!=0){
            for (MonitoringPoint monitoringPoint : monitoringPoints) {
                monitoringPoint.setId(IdUtil.getUUId());
                monitoringPoint.setBatchManagementId(batchManagement.getId());
                monitoringPointMapper.insert(monitoringPoint);
                if (monitoringPoint.getBiologicalResidueMonitoring()!=null||monitoringPoint.getPlanktonMonitoring()!=null||monitoringPoint.getSedimentMonitoring()!=null||monitoringPoint.getWaterQualityMonitoring()!=null){
                    switch(monitoringPoint.getType()){
                        case "1":
                            monitoringPoint.getWaterQualityMonitoring().setId(IdUtil.getUUId());
                            monitoringPoint.getWaterQualityMonitoring().setMonitoringPointId(monitoringPoint.getId());
                            waterQualityMonitoringMapper.insert(monitoringPoint.getWaterQualityMonitoring());
                            break;
                        case "2":
                            monitoringPoint.getSedimentMonitoring().setId(IdUtil.getUUId());
                            monitoringPoint.getSedimentMonitoring().setMonitoringPointId(monitoringPoint.getId());
                            sedimentMonitoringMapper.insert(monitoringPoint.getSedimentMonitoring());
                            break;
                        case "3":
                            monitoringPoint.getPlanktonMonitoring().setId(IdUtil.getUUId());
                            monitoringPoint.getPlanktonMonitoring().setMonitoringPointId(monitoringPoint.getId());
                            planktonMonitoringMapper.insert(monitoringPoint.getPlanktonMonitoring());
                            break;
                        case "4":
                            monitoringPoint.getBiologicalResidueMonitoring().setId(IdUtil.getUUId());
                            monitoringPoint.getBiologicalResidueMonitoring().setMonitoringPointId(monitoringPoint.getId());
                            biologicalResidueMonitoringMapper.insert(monitoringPoint.getBiologicalResidueMonitoring());
                            break;
                        default:
                            throw  new SofnException("监测点类型数据有误");
                    }
                }else {
                    throw  new SofnException("缺少监测点类型表数据");
                }
            }
        }else {
            throw  new SofnException("缺少监测点数据");
        }

        batchManagementMapper.insert(batchManagement);
    }

    @Transactional(
            rollbackFor = {Exception.class}
    )
    @Override
    public void remove(String id) {
        BatchManagement batchManagement = batchManagementMapper.view(id);
        monitoringStationTaskMapper.updateMonitoringStationNumber(batchManagement.getMonitoringStationTaskId(),batchManagementMapper.total(batchManagement.getMonitoringStationTaskId())-1);
        List<MonitoringPoint> monitoringPoints = monitoringPointMapper.selectListByQuery(id);
        if (monitoringPoints!=null&&monitoringPoints.size()!=0){
            for (MonitoringPoint monitoringPoint : monitoringPoints) {
                if (monitoringPoint.getType()!=null&&monitoringPoint.getType()!=""){
                    switch(monitoringPoint.getType()){
                        case "1":
                            waterQualityMonitoringMapper.removeById(monitoringPoint.getId());
                            break;
                        case "2":
                            sedimentMonitoringMapper.removeById(monitoringPoint.getId());
                            break;
                        case "3":
                            planktonMonitoringMapper.removeById(monitoringPoint.getId());
                            break;
                        case "4":
                            biologicalResidueMonitoringMapper.removeById(monitoringPoint.getId());
                            break;
                        default:
                            throw  new SofnException("监测点类型数据有误");
                    }
                }else {
                    throw new SofnException("监测点类型数据获取失败");
                }
            }
        }else {
            throw new SofnException("监测点删除失败");
        }
        monitoringPointMapper.removeById(id);
        batchManagementMapper.removeById(id);
    }

    @Transactional(
            rollbackFor = {Exception.class}
    )
    @Override
    public void update(BatchManagement batchManagement) {
        int count =batchManagementMapper.selectCount(new LambdaQueryWrapper<BatchManagement>().ne(BatchManagement::getId,batchManagement.getId()).eq(BatchManagement::getMonitoringStationTaskId,batchManagement.getMonitoringStationTaskId()).eq(BatchManagement::getName,batchManagement.getName()));
        if(count>0){
            throw new SofnException("当前年度监测批次名称重复！");
        }
        List<MonitoringPoint> monitoringPoints = batchManagement.getMonitoringPoints();
        if (monitoringPoints!=null&&monitoringPoints.size()!=0){
            for (MonitoringPoint monitoringPoint : monitoringPoints) {
                //修改监测点
                if (monitoringPoint.getId()!=null){
                    if (monitoringPoint.getBiologicalResidueMonitoring() != null || monitoringPoint.getPlanktonMonitoring() != null || monitoringPoint.getSedimentMonitoring() != null || monitoringPoint.getWaterQualityMonitoring() != null) {
                        switch(monitoringPoint.getType()){
                            case "1":
                                waterQualityMonitoringMapper.update(monitoringPoint.getWaterQualityMonitoring());
                                break;
                            case "2":
                                sedimentMonitoringMapper.update(monitoringPoint.getSedimentMonitoring());
                                break;
                            case "3":
                                planktonMonitoringMapper.update(monitoringPoint.getPlanktonMonitoring());
                                break;
                            case "4":
                                biologicalResidueMonitoringMapper.update(monitoringPoint.getBiologicalResidueMonitoring());
                                break;
                            default:
                                throw  new SofnException("监测点类型数据有误");
                        }
                    }else {
                        throw  new SofnException("缺少监测点类型表数据");
                    }
                }else {
                    //添加新的监测点
                    monitoringPoint.setId(IdUtil.getUUId());
                    monitoringPoint.setBatchManagementId(batchManagement.getId());
                    monitoringPointMapper.insert(monitoringPoint);
                    if (monitoringPoint.getBiologicalResidueMonitoring()!=null||monitoringPoint.getPlanktonMonitoring()!=null||monitoringPoint.getSedimentMonitoring()!=null||monitoringPoint.getWaterQualityMonitoring()!=null){
                        switch(monitoringPoint.getType()){
                            case "1":
                                monitoringPoint.getWaterQualityMonitoring().setId(IdUtil.getUUId());
                                monitoringPoint.getWaterQualityMonitoring().setMonitoringPointId(monitoringPoint.getId());
                                waterQualityMonitoringMapper.insert(monitoringPoint.getWaterQualityMonitoring());
                                break;
                            case "2":
                                monitoringPoint.getSedimentMonitoring().setId(IdUtil.getUUId());
                                monitoringPoint.getSedimentMonitoring().setMonitoringPointId(monitoringPoint.getId());
                                sedimentMonitoringMapper.insert(monitoringPoint.getSedimentMonitoring());
                                break;
                            case "3":
                                monitoringPoint.getPlanktonMonitoring().setId(IdUtil.getUUId());
                                monitoringPoint.getPlanktonMonitoring().setMonitoringPointId(monitoringPoint.getId());
                                planktonMonitoringMapper.insert(monitoringPoint.getPlanktonMonitoring());
                                break;
                            case "4":
                                monitoringPoint.getBiologicalResidueMonitoring().setId(IdUtil.getUUId());
                                monitoringPoint.getBiologicalResidueMonitoring().setMonitoringPointId(monitoringPoint.getId());
                                biologicalResidueMonitoringMapper.insert(monitoringPoint.getBiologicalResidueMonitoring());
                                break;
                            default:
                                throw  new SofnException("监测点类型数据有误");
                        }
                    }else {
                        throw  new SofnException("缺少监测点类型表数据");
                    }
                }
                monitoringPointMapper.update(monitoringPoint);
            }
        }else {
            throw  new SofnException("缺少监测点数据");
        }
        batchManagementMapper.update(batchManagement);
    }

    @Override
    public BatchManagement view(String id) {
        BatchManagement batchManagement = batchManagementMapper.view(id);
        if (batchManagement!=null){
            List<MonitoringPoint> monitoringPoints = monitoringPointMapper.selectListByQuery(batchManagement.getId());
            if (monitoringPoints!=null&&monitoringPoints.size()!=0){
                for (MonitoringPoint monitoringPoint : monitoringPoints) {
                    if (monitoringPoint.getType()!=null&&monitoringPoint.getType()!=""){
                        switch(monitoringPoint.getType()){
                            case "1":
                                monitoringPoint.setWaterQualityMonitoring(waterQualityMonitoringMapper.selectListByQuery(monitoringPoint.getId()));
                                break;
                            case "2":
                                monitoringPoint.setSedimentMonitoring(sedimentMonitoringMapper.selectListByQuery(monitoringPoint.getId()));
                                break;
                            case "3":
                                monitoringPoint.setPlanktonMonitoring(planktonMonitoringMapper.selectListByQuery(monitoringPoint.getId()));
                                break;
                            case "4":
                                monitoringPoint.setBiologicalResidueMonitoring(biologicalResidueMonitoringMapper.selectListByQuery(monitoringPoint.getId()));
                                break;
                            default:
                                throw  new SofnException("监测点类型数据有误");
                        }
                    }else {
                        throw new SofnException("监测点位类型数据获取失败");
                    }
                }
                batchManagement.setMonitoringPoints(monitoringPoints);
            }else {
                throw new SofnException("监测点位获取失败");
            }
        }else {
            throw new SofnException("批次详情获取失败");
        }

        return batchManagement;
    }
}
