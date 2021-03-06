package com.sofn.fdzem.service.impl;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.sofn.common.exception.SofnException;
import com.sofn.common.model.Result;
import com.sofn.common.utils.IdUtil;
import com.sofn.fdzem.enums.StatusEnum;
import com.sofn.fdzem.feign.OrgFeign;
import com.sofn.fdzem.feign.OrgFunctionFeign;
import com.sofn.fdzem.feign.UserFeign;
import com.sofn.fdzem.mapper.*;
import com.sofn.fdzem.model.*;
import com.sofn.fdzem.service.MonitoringStationTaskService;
import com.sofn.fdzem.util.JustGetOrganization;
import com.sofn.fdzem.vo.StatusVo;
import com.sofn.fdzem.vo.SysOrganizationForm;
import com.sofn.fdzem.vo.SysSystemFunction;
import com.sofn.fdzem.vo.TrendAnalysisVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
public class MonitoringStationTaskServiceImpl implements MonitoringStationTaskService {

    @Autowired
    private MonitoringStationTaskMapper monitoringStationTaskMapper;
    @Autowired
    private UserFeign userFeign;
    @Autowired
    private OrgFeign orgFeign;
    @Autowired
    private OrgFunctionFeign orgFunctionFeign;
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
    public PageInfo<MonitoringStationTask> selectListByQuery(Integer pageNo, Integer pageSize, String year, Integer status) {
        String organizationId = JustGetOrganization.getOrganizationId(userFeign);//???????????????id
        if(organizationId == null || organizationId.equals("")){
            throw new SofnException("????????????????????????");
        }
        Map<String,Object> params = Maps.newHashMap();
        params.put("year",year);
        params.put("organizationId",organizationId);
        if(status!=null&&status==0){
            params.put("status", null);
            List<MonitoringStationTask> monitoringStationTasks = monitoringStationTaskMapper.selectListByQuery(params);
            List<Integer> statusList = new ArrayList<>();
            statusList.add(0);
            // JDK1.8?????????lambda???????????? ?????????surveyTasks????????????????????????????????????
            // ???????????????
            List<MonitoringStationTask> list = null;
            list = monitoringStationTasks.stream()
                    .filter((MonitoringStationTask s) -> statusList.contains(s.getStatus()))
                    .collect(Collectors.toList());
            List<MonitoringStationTask> newlist = new ArrayList<>();
            if(pageNo*pageSize>list.size()){
                newlist=list.subList((pageNo-1)*pageSize, list.size());
            }else{
                newlist=list.subList((pageNo-1)*pageSize, pageNo*pageSize);
            }
            PageInfo<MonitoringStationTask> pageInfo = new PageInfo<>(newlist);
            pageInfo.setTotal(list.size());
            pageInfo.setPageSize(pageSize);
            pageInfo.setPageNum(pageNo);
            return pageInfo;
        }else {
            params.put("status", status);
            List<MonitoringStationTask> monitoringStationTasks = monitoringStationTaskMapper.selectListByQuery(params);
            List<MonitoringStationTask> newlist = new ArrayList<>();
            if (pageNo * pageSize > monitoringStationTasks.size()) {
                newlist = monitoringStationTasks.subList((pageNo - 1) * pageSize, monitoringStationTasks.size());
            } else {
                newlist = monitoringStationTasks.subList((pageNo - 1) * pageSize, pageNo * pageSize);
            }
            PageInfo<MonitoringStationTask> pageInfo = new PageInfo<>(newlist);
            pageInfo.setTotal(monitoringStationTasks.size());
            pageInfo.setPageSize(pageSize);
            pageInfo.setPageNum(pageNo);
            return pageInfo;
        }
    }

    @Override
    public PageInfo<MonitoringStationTask> selectListByQueryCheck(Integer pageNo, Integer pageSize, String year, Integer status) {
        String organizationId = JustGetOrganization.getOrganizationId(userFeign);//???????????????id
        if(organizationId == null || organizationId.equals("")){
            throw new SofnException("????????????????????????");
        }
        Map<String,Object> params = Maps.newHashMap();
        params.put("year",year);
        Result<List<SysSystemFunction>> byOrgId = orgFunctionFeign.getByOrgId(organizationId);
        //???????????????????????????
        if (byOrgId.getData()!=null&&byOrgId.getData().size()!=0&&byOrgId.getData().get(0).getFunctionDictCode().equals("monitor")){
            //???????????????????????????id
            params.put("organizationId",organizationId);
            params.put("status",status);
            List<MonitoringStationTask> monitoringStationTasks = monitoringStationTaskMapper.selectListByQuery(params);
            // ???????????????????????????surveyTasks?????????????????????1,2,3???????????????
            List<Integer> statusList = new ArrayList<>();
            statusList.add(1);
            statusList.add(2);
            statusList.add(3);
            statusList.add(5);
            statusList.add(6);

            // JDK1.8?????????lambda???????????? ?????????surveyTasks????????????????????????????????????
            // ???????????????
            List<MonitoringStationTask> list = null;
            list = monitoringStationTasks.stream()
                    .filter((MonitoringStationTask s) -> statusList.contains(s.getStatus()))
                    .collect(Collectors.toList());
            List<MonitoringStationTask> newlist = new ArrayList<>();
            if(pageNo*pageSize>list.size()){
                newlist=list.subList((pageNo-1)*pageSize, list.size());
            }else{
                newlist=list.subList((pageNo-1)*pageSize, pageNo*pageSize);
            }
            PageInfo<MonitoringStationTask> pageInfo = new PageInfo<>(newlist);
            pageInfo.setTotal(list.size());
            pageInfo.setPageSize(pageSize);
            pageInfo.setPageNum(pageNo);
            return pageInfo;
        }else {
            //???????????????????????????id????????????id
            params.put("organizationParentId", organizationId);
            params.put("status", status);
            List<MonitoringStationTask> monitoringStationTasks = monitoringStationTaskMapper.selectListByQuery(params);
            // ???????????????????????????surveyTasks?????????????????????1,2,3???????????????
            List<Integer> statusList = new ArrayList<>();
            statusList.add(2);
            statusList.add(5);
            statusList.add(6);

            // JDK1.8?????????lambda???????????? ?????????surveyTasks????????????????????????????????????
            // ???????????????
            List<MonitoringStationTask> list = null;
            list = monitoringStationTasks.stream()
                    .filter((MonitoringStationTask s) -> statusList.contains(s.getStatus()))
                    .collect(Collectors.toList());
            List<MonitoringStationTask> newlist = new ArrayList<>();
            if (pageNo * pageSize > list.size()) {
                newlist = list.subList((pageNo - 1) * pageSize, list.size());
            } else {
                newlist = list.subList((pageNo - 1) * pageSize, pageNo * pageSize);
            }
            PageInfo<MonitoringStationTask> pageInfo = new PageInfo<>(newlist);
            pageInfo.setTotal(list.size());
            pageInfo.setPageSize(pageSize);
            pageInfo.setPageNum(pageNo);
            return pageInfo;
        }

    }

    @Transactional(
            rollbackFor = {Exception.class}
    )
    @Override
    public void audit(String id) {
        String organizationId = JustGetOrganization.getOrganizationId(userFeign);//???????????????id
        if (organizationId == null || organizationId.equals("")) {
            throw new SofnException("????????????????????????");
        }
        Result<List<SysSystemFunction>> byOrgId = orgFunctionFeign.getByOrgId(organizationId);
        //???????????????????????????
        if (byOrgId.getData()!=null&&byOrgId.getData().size()!=0&&byOrgId.getData().get(0).getFunctionDictCode().equals("monitor")){
            monitoringStationTaskMapper.updateStatus(id, StatusEnum.STATUS_TWO.getCode());
        }else {
            monitoringStationTaskMapper.updateStatus(id, StatusEnum.STATUS_FIVE.getCode());
        }
    }

    @Transactional(
            rollbackFor = {Exception.class}
    )
    @Override
    public void reject(String id) {
        String organizationId = JustGetOrganization.getOrganizationId(userFeign);//???????????????id
        if (organizationId == null || organizationId.equals("")) {
            throw new SofnException("????????????????????????");
        }
        Result<List<SysSystemFunction>> byOrgId = orgFunctionFeign.getByOrgId(organizationId);
        //???????????????????????????
        if (byOrgId.getData()!=null&&byOrgId.getData().size()!=0&&byOrgId.getData().get(0).getFunctionDictCode().equals("monitor")){
            monitoringStationTaskMapper.updateStatus(id, StatusEnum.STATUS_THREE.getCode());
        }else {
            monitoringStationTaskMapper.updateStatus(id, StatusEnum.STATUS_SIX.getCode());
        }
    }

    @Override
    public PageInfo<MonitoringStationTask> selectListByQueryInfo(Integer pageNo, Integer pageSize, String year, String name, String provice, String city, String county, String seaArea) {
        String organizationId = JustGetOrganization.getOrganizationId(userFeign);//???????????????id
        if(organizationId == null || organizationId.equals("")){
            throw new SofnException("????????????????????????");
        }
        Map<String,Object> params = Maps.newHashMap();
        params.put("year",year);
        params.put("name",name);
        params.put("provice",provice);
        params.put("city",city);
        params.put("county",county);
        params.put("seaArea",seaArea);
        Result<List<SysSystemFunction>> byOrgId = orgFunctionFeign.getByOrgId(organizationId);
        //???????????????????????????
        if (byOrgId.getData()!=null&&byOrgId.getData().size()!=0&&byOrgId.getData().get(0).getFunctionDictCode().equals("monitor")){
            //????????????????????????
            Result<SysOrganizationForm> info = orgFeign.getOrgInfoById(organizationId);
            //???????????????????????????id????????????id
            params.put("organizationId",organizationId);
            List<MonitoringStationTask> monitoringStationTasks = monitoringStationTaskMapper.selectListByQueryInfo(params);
            // ???????????????????????????surveyTasks?????????????????????1,2,3???????????????
            List<Integer> statusList = new ArrayList<>();
            statusList.add(2);
            statusList.add(5);
            statusList.add(6);
            // JDK1.8?????????lambda???????????? ?????????surveyTasks????????????????????????????????????
            // ???????????????
            List<MonitoringStationTask> list = null;
            list = monitoringStationTasks.stream()
                    .filter((MonitoringStationTask s) -> statusList.contains(s.getStatus()))
                    .collect(Collectors.toList());
            List<MonitoringStationTask> newlist = new ArrayList<>();
            if(pageNo*pageSize>list.size()){
                newlist=list.subList((pageNo-1)*pageSize, list.size());
            }else{
                newlist=list.subList((pageNo-1)*pageSize, pageNo*pageSize);
            }
            PageInfo<MonitoringStationTask> pageInfo = new PageInfo<>(newlist);
            pageInfo.setTotal(list.size());
            pageInfo.setPageSize(pageSize);
            pageInfo.setPageNum(pageNo);
            return pageInfo;
        }else if(byOrgId.getData()!=null&&byOrgId.getData().size()!=0&&byOrgId.getData().get(0).getFunctionDictCode().equals("area")){
            //?????????????????????????????????
            //???????????????????????????id????????????id
            params.put("organizationParentId",organizationId);
            List<MonitoringStationTask> monitoringStationTasks = monitoringStationTaskMapper.selectListByQueryInfo(params);
            // ???????????????????????????surveyTasks?????????????????????1,2,3???????????????
            List<Integer> statusList = new ArrayList<>();
            statusList.add(5);

            // JDK1.8?????????lambda???????????? ?????????surveyTasks????????????????????????????????????
            // ???????????????
            List<MonitoringStationTask> list = null;
            list = monitoringStationTasks.stream()
                    .filter((MonitoringStationTask s) -> statusList.contains(s.getStatus()))
                    .collect(Collectors.toList());
            List<MonitoringStationTask> newlist = new ArrayList<>();
            if(pageNo*pageSize>list.size()){
                newlist=list.subList((pageNo-1)*pageSize, list.size());
            }else{
                newlist=list.subList((pageNo-1)*pageSize, pageNo*pageSize);
            }
            PageInfo<MonitoringStationTask> pageInfo = new PageInfo<>(newlist);
            pageInfo.setTotal(list.size());
            pageInfo.setPageSize(pageSize);
            pageInfo.setPageNum(pageNo);
            return pageInfo;
        }else {
            //??????????????????????????????????????????????????????
            //???????????????????????????id????????????id
            params.put("organizationId",null);
            List<MonitoringStationTask> monitoringStationTasks = monitoringStationTaskMapper.selectListByQueryInfo(params);
            // ???????????????????????????surveyTasks?????????????????????1,2,3???????????????
            List<Integer> statusList = new ArrayList<>();
            statusList.add(5);
            // JDK1.8?????????lambda???????????? ?????????surveyTasks????????????????????????????????????
            // ???????????????
            List<MonitoringStationTask> list = null;
            list = monitoringStationTasks.stream()
                    .filter((MonitoringStationTask s) -> statusList.contains(s.getStatus()))
                    .collect(Collectors.toList());
            List<MonitoringStationTask> newlist;
            if(pageNo*pageSize>list.size()){
                newlist=list.subList((pageNo-1)*pageSize, list.size());
            }else{
                newlist=list.subList((pageNo-1)*pageSize, pageNo*pageSize);
            }
            PageInfo<MonitoringStationTask> pageInfo = new PageInfo<>(newlist);
            pageInfo.setTotal(list.size());
            pageInfo.setPageSize(pageSize);
            pageInfo.setPageNum(pageNo);
            return pageInfo;
        }
    }

    @Override
    public PageInfo<TrendAnalysisVo> selectListByQueryTrendAnalysis(Integer pageNo, Integer pageSize, String year, String name, String type) {
        String organizationId = JustGetOrganization.getOrganizationId(userFeign);//???????????????id
        if(organizationId == null || organizationId.equals("")){
            throw new SofnException("????????????????????????");
        }
        Map<String,Object> params = Maps.newHashMap();
        params.put("year",year);
        params.put("name",name);
        Result<List<SysSystemFunction>> byOrgId = orgFunctionFeign.getByOrgId(organizationId);
        //???????????????????????????
        if (byOrgId.getData()!=null&&byOrgId.getData().size()!=0&&byOrgId.getData().get(0).getFunctionDictCode().equals("monitor")){
            //????????????????????????
            Result<SysOrganizationForm> info = orgFeign.getOrgInfoById(organizationId);
            //???????????????????????????id????????????id
            params.put("organizationId",organizationId);
            List<MonitoringStationTask> monitoringStationTasks = monitoringStationTaskMapper.selectTrend(params);
            // ???????????????????????????surveyTasks?????????????????????1,2,3???????????????
            List<Integer> statusList = new ArrayList<>();
            statusList.add(2);
            statusList.add(5);
            statusList.add(6);
            // JDK1.8?????????lambda???????????? ?????????surveyTasks????????????????????????????????????
            // ???????????????
            List<MonitoringStationTask> listz = null;
            listz = monitoringStationTasks.stream()
                    .filter((MonitoringStationTask s) -> statusList.contains(s.getStatus()))
                    .collect(Collectors.toList());
            List<TrendAnalysisVo> list = getList(listz,type);
            List<TrendAnalysisVo> newlist = new ArrayList<>();
            if(pageNo*pageSize>list.size()){
                newlist=list.subList((pageNo-1)*pageSize, list.size());
            }else{
                newlist=list.subList((pageNo-1)*pageSize, pageNo*pageSize);
            }
            PageInfo<TrendAnalysisVo> pageInfo = new PageInfo<>(newlist);
            pageInfo.setTotal(list.size());
            pageInfo.setPageSize(pageSize);
            pageInfo.setPageNum(pageNo);
            return pageInfo;
        }else if(byOrgId.getData()!=null&&byOrgId.getData().size()!=0&&byOrgId.getData().get(0).getFunctionDictCode().equals("area")){
            //?????????????????????????????????
            //???????????????????????????id????????????id
            params.put("organizationParentId",organizationId);
            List<MonitoringStationTask> monitoringStationTasks = monitoringStationTaskMapper.selectTrend(params);
            // ???????????????????????????surveyTasks?????????????????????1,2,3???????????????
            List<Integer> statusList = new ArrayList<>();
            statusList.add(5);

            // JDK1.8?????????lambda???????????? ?????????surveyTasks????????????????????????????????????
            // ???????????????
            List<MonitoringStationTask> listz = null;
            listz = monitoringStationTasks.stream()
                    .filter((MonitoringStationTask s) -> statusList.contains(s.getStatus()))
                    .collect(Collectors.toList());
            List<TrendAnalysisVo> list = getList(listz,type);
            List<TrendAnalysisVo> newlist = new ArrayList<>();
            if(pageNo*pageSize>list.size()){
                newlist=list.subList((pageNo-1)*pageSize, list.size());
            }else{
                newlist=list.subList((pageNo-1)*pageSize, pageNo*pageSize);
            }
            PageInfo<TrendAnalysisVo> pageInfo = new PageInfo<>(newlist);
            pageInfo.setTotal(list.size());
            pageInfo.setPageSize(pageSize);
            pageInfo.setPageNum(pageNo);
            return pageInfo;
        }else {
            //??????????????????????????????????????????????????????
            //???????????????????????????id????????????id
            params.put("organizationId",null);
            List<MonitoringStationTask> monitoringStationTasks = monitoringStationTaskMapper.selectTrend(params);
            // ???????????????????????????surveyTasks?????????????????????1,2,3???????????????
            List<Integer> statusList = new ArrayList<>();
            statusList.add(5);

            // JDK1.8?????????lambda???????????? ?????????surveyTasks????????????????????????????????????
            // ???????????????
            List<MonitoringStationTask> listz = null;
            listz = monitoringStationTasks.stream()
                    .filter((MonitoringStationTask s) -> statusList.contains(s.getStatus()))
                    .collect(Collectors.toList());
            List<TrendAnalysisVo> list = getList(listz,type);
            List<TrendAnalysisVo> newlist = new ArrayList<>();
            if(pageNo*pageSize>list.size()){
                newlist=list.subList((pageNo-1)*pageSize, list.size());
            }else{
                newlist=list.subList((pageNo-1)*pageSize, pageNo*pageSize);
            }
            PageInfo<TrendAnalysisVo> pageInfo = new PageInfo<>(newlist);
            pageInfo.setTotal(list.size());
            pageInfo.setPageSize(pageSize);
            pageInfo.setPageNum(pageNo);
            return pageInfo;
        }
    }

    @Override
    public List<MonitoringStationTask> selectGraphic(String provice, String city, String county) {
        String organizationId = JustGetOrganization.getOrganizationId(userFeign);//???????????????id
        if(organizationId == null || organizationId.equals("")){
            throw new SofnException("????????????????????????");
        }
        Map<String,Object> params = Maps.newHashMap();
        params.put("provice",provice);
        params.put("city",city);
        params.put("county",county);

        Result<List<SysSystemFunction>> byOrgId = orgFunctionFeign.getByOrgId(organizationId);
        //???????????????????????????
        if (byOrgId.getData()!=null&&byOrgId.getData().size()!=0&&byOrgId.getData().get(0).getFunctionDictCode().equals("monitor")){
            //????????????????????????
            Result<SysOrganizationForm> info = orgFeign.getOrgInfoById(organizationId);
            //???????????????????????????id????????????id
            params.put("organizationId",organizationId);
            List<MonitoringStationTask> monitoringStationTasks = monitoringStationTaskMapper.selectListByQueryInfo(params);
            // ???????????????????????????surveyTasks?????????????????????1,2,3???????????????
            List<Integer> statusList = new ArrayList<>();
            statusList.add(2);
            statusList.add(5);
            statusList.add(6);
            // JDK1.8?????????lambda???????????? ?????????surveyTasks????????????????????????????????????
            // ???????????????
            List<MonitoringStationTask> list = null;
            list = monitoringStationTasks.stream()
                    .filter((MonitoringStationTask s) -> statusList.contains(s.getStatus()))
                    .collect(Collectors.toList());
            return list;
        }else if(byOrgId.getData()!=null&&byOrgId.getData().size()!=0&&byOrgId.getData().get(0).getFunctionDictCode().equals("area")){
            //?????????????????????????????????
            //???????????????????????????id????????????id
            params.put("organizationParentId", organizationId);
            List<MonitoringStationTask> monitoringStationTasks = monitoringStationTaskMapper.selectListByQueryInfo(params);
            // ???????????????????????????surveyTasks?????????????????????1,2,3???????????????
            List<Integer> statusList = new ArrayList<>();
            statusList.add(5);

            // JDK1.8?????????lambda???????????? ?????????surveyTasks????????????????????????????????????
            // ???????????????
            List<MonitoringStationTask> list = null;
            list = monitoringStationTasks.stream()
                    .filter((MonitoringStationTask s) -> statusList.contains(s.getStatus()))
                    .collect(Collectors.toList());
            return list;
        }else {
            //??????????????????????????????????????????????????????
            //???????????????????????????id????????????id
            params.put("organizationId", null);
            List<MonitoringStationTask> monitoringStationTasks = monitoringStationTaskMapper.selectListByQueryInfo(params);
            // ???????????????????????????surveyTasks?????????????????????1,2,3???????????????
            List<Integer> statusList = new ArrayList<>();
            statusList.add(5);

            // JDK1.8?????????lambda???????????? ?????????surveyTasks????????????????????????????????????
            // ???????????????
            List<MonitoringStationTask> list = null;
            list = monitoringStationTasks.stream()
                    .filter((MonitoringStationTask s) -> statusList.contains(s.getStatus()))
                    .collect(Collectors.toList());
            return list;
        }
    }

    @Override
    public List<String> getMonitorStation() {
        String organizationId = JustGetOrganization.getOrganizationId(userFeign);//???????????????id
        if(organizationId == null || organizationId.equals("")){
            throw new SofnException("????????????????????????");
        }
        Map<String,Object> params = Maps.newHashMap();
        Result<List<SysSystemFunction>> byOrgId = orgFunctionFeign.getByOrgId(organizationId);
        //???????????????????????????
        if (byOrgId.getData()!=null&&byOrgId.getData().size()!=0&&byOrgId.getData().get(0).getFunctionDictCode().equals("monitor")){
            //????????????????????????
            Result<SysOrganizationForm> info = orgFeign.getOrgInfoById(organizationId);
            //???????????????????????????id????????????id
            params.put("organizationId",organizationId);
            List<MonitoringStationTask> monitoringStationTasks = monitoringStationTaskMapper.selectTrend(params);
            List<String> list = new ArrayList<>();
            for (MonitoringStationTask monitoringStationTask : monitoringStationTasks) {
                list.add(monitoringStationTask.getName());
            }
            list=list.stream()
                    .distinct()
                    .collect(Collectors.toList());
            return list;
        }else if(byOrgId.getData()!=null&&byOrgId.getData().size()!=0&&byOrgId.getData().get(0).getFunctionDictCode().equals("area")){
            //?????????????????????????????????
            //???????????????????????????id????????????id
            params.put("organizationParentId",organizationId);
            List<MonitoringStationTask> monitoringStationTasks = monitoringStationTaskMapper.selectTrend(params);
            List<String> list = new ArrayList<>();
            for (MonitoringStationTask monitoringStationTask : monitoringStationTasks) {
                list.add(monitoringStationTask.getName());
            }
            list=list.stream()
                    .distinct()
                    .collect(Collectors.toList());
            return list;
        }else {
            //??????????????????????????????????????????????????????
            //???????????????????????????id????????????id
            params.put("organizationId",null);
            List<MonitoringStationTask> monitoringStationTasks = monitoringStationTaskMapper.selectTrend(params);
            List<String> list = new ArrayList<>();
            for (MonitoringStationTask monitoringStationTask : monitoringStationTasks) {
                list.add(monitoringStationTask.getName());
            }
            list=list.stream()
                    .distinct()
                    .collect(Collectors.toList());
            return list;
        }
    }

    @Override
    public List<StatusVo> getStatus() {
        String organizationId = JustGetOrganization.getOrganizationId(userFeign);//???????????????id
        if(organizationId == null || organizationId.equals("")){
            throw new SofnException("????????????????????????");
        }
        Result<List<SysSystemFunction>> byOrgId = orgFunctionFeign.getByOrgId(organizationId);
        //???????????????????????????
        if (byOrgId.getData()!=null&&byOrgId.getData().size()!=0&&byOrgId.getData().get(0).getFunctionDictCode().equals("monitor")){
            List<StatusVo> list = new ArrayList<>();
            StatusVo statusVo = new StatusVo();
            statusVo.setCode(1);
            statusVo.setStatus("?????????");
            list.add(statusVo);
            StatusVo statusVo1 = new StatusVo();
            statusVo1.setCode(2);
            statusVo1.setStatus("????????????");
            list.add(statusVo1);
            StatusVo statusVo2 = new StatusVo();
            statusVo2.setCode(3);
            statusVo2.setStatus("????????????");
            list.add(statusVo2);
            StatusVo statusVo4 = new StatusVo();
            statusVo4.setCode(5);
            statusVo4.setStatus("????????????");
            list.add(statusVo4);
            StatusVo statusVo5 = new StatusVo();
            statusVo5.setCode(6);
            statusVo5.setStatus("????????????");
            list.add(statusVo5);
            return list;
        }else{
            //????????????????????????????????????
            if (byOrgId.getData()!=null&&byOrgId.getData().size()!=0) {
                List<StatusVo> list = new ArrayList<>();
                StatusVo statusVo3 = new StatusVo();
                statusVo3.setCode(2);
                statusVo3.setStatus("????????????");
                list.add(statusVo3);
                StatusVo statusVo4 = new StatusVo();
                statusVo4.setCode(5);
                statusVo4.setStatus("????????????");
                list.add(statusVo4);
                StatusVo statusVo5 = new StatusVo();
                statusVo5.setCode(6);
                statusVo5.setStatus("????????????");
                list.add(statusVo5);
                return list;
            }
        }
        return null;
    }

    /**
     * ?????????????????????????????????????????????????????????????????????????????????
     * @param listz
     * @param type
     * @return
     */
    private List<TrendAnalysisVo> getList(List<MonitoringStationTask> listz, String type) {
        List<TrendAnalysisVo> trendAnalysisVoList = new ArrayList<>();
        //??????????????????????????????????????????????????? ??????????????????
        for (MonitoringStationTask monitoringStationTask : listz) {
            List<WaterQualityMonitoring> waterQualityMonitorings = new ArrayList<>();
            List<SedimentMonitoring> sedimentMonitorings = new ArrayList<>();
            List<PlanktonMonitoring> planktonMonitorings = new ArrayList<>();
            List<BiologicalResidueMonitoring> biologicalResidueMonitorings = new ArrayList<>();
            //???????????????????????? ??????????????????????????????
            TrendAnalysisVo trendAnalysisVo = new TrendAnalysisVo();
            trendAnalysisVo.setName(monitoringStationTask.getName());
            trendAnalysisVo.setYear(monitoringStationTask.getYear());
            //?????????????????????????????????????????????
            List<BatchManagement> list = batchManagementMapper.list(monitoringStationTask.getId());
            for (BatchManagement batchManagement : list) {
                //??????????????????????????????
                List<MonitoringPoint> monitoringPoints = monitoringPointMapper.selectListByQuery(batchManagement.getId());
                for (MonitoringPoint monitoringPoint : monitoringPoints) {
                    String monitoringPointType = monitoringPoint.getType();
                    if (type.equals(monitoringPointType)){
                        switch(type){
                            case "1":
                                WaterQualityMonitoring waterQualityMonitoring = waterQualityMonitoringMapper.selectListByQuery(monitoringPoint.getId());
                                if (waterQualityMonitoring != null) {
                                    waterQualityMonitorings.add(waterQualityMonitoring);
                                }
                                break;
                            case "2":
                                SedimentMonitoring sedimentMonitoring = sedimentMonitoringMapper.selectListByQuery(monitoringPoint.getId());
                                if (sedimentMonitoring != null) {
                                    sedimentMonitorings.add(sedimentMonitoring);
                                }
                                break;
                            case "3":
                                PlanktonMonitoring planktonMonitoring = planktonMonitoringMapper.selectListByQuery(monitoringPoint.getId());
                                if (planktonMonitoring != null) {
                                    planktonMonitorings.add(planktonMonitoring);
                                }
                                break;
                            case "4":
                                BiologicalResidueMonitoring biologicalResidueMonitoring = biologicalResidueMonitoringMapper.selectListByQuery(monitoringPoint.getId());
                                if (biologicalResidueMonitoring != null) {
                                    biologicalResidueMonitorings.add(biologicalResidueMonitoring);
                                }
                                break;
                            default:
                                throw  new SofnException("?????????????????????????????????");
                        }
                    }else {
                        continue;
                    }
                }
            }
            switch(type){
                case "1":
                    int p=0;
                    double ph=0;
                    double wd=0;
                    double dO=0;
                    double nitriteNitrogen=0;
                    double nitrateNitrogen=0;
                    if (waterQualityMonitorings == null || waterQualityMonitorings.isEmpty()){
                        break;
                    }
                    for (WaterQualityMonitoring t : waterQualityMonitorings) {
                        ph+=Double.valueOf(t.getPh());
                        wd+=Double.valueOf(t.getWaterDepth());
                        dO+=Double.valueOf(t.getDO());
                        nitriteNitrogen+=Double.valueOf(t.getNitriteNitrogen());
                        nitrateNitrogen+=Double.valueOf(t.getNitrateNitrogen());
                        ++p;
                    }
                    ph = ph / p;
                    wd = wd / p;
                    dO = dO / p;
                    nitriteNitrogen = nitriteNitrogen / p;
                    nitrateNitrogen = nitrateNitrogen / p;
                    trendAnalysisVo.setPh(ph);
                    trendAnalysisVo.setWaterDepth(wd);
                    trendAnalysisVo.setDO(dO);
                    trendAnalysisVo.setNitriteNitrogen(nitriteNitrogen);
                    trendAnalysisVo.setNitrateNitrogen(nitrateNitrogen);
                    if (waterQualityMonitorings!=null&&waterQualityMonitorings.size()!=0){
                        trendAnalysisVoList.add(trendAnalysisVo);
                    }
                    break;
                case "2":
                    int a=0;
                    double as=0;
                    double cd=0;
                    double cr=0;
                    double cu=0;
                    double hg=0;
                    double pb=0;
                    double pet=0;
                    double zn=0;
                    if (sedimentMonitorings == null || sedimentMonitorings.isEmpty()){
                        break;
                    }
                    for (SedimentMonitoring t : sedimentMonitorings) {
                        as+=Double.valueOf(t.getAsA());
                        cd+=Double.valueOf(t.getCd());
                        cr+=Double.valueOf(t.getCr());
                        cu+=Double.valueOf(t.getCu());
                        hg+=Double.valueOf(t.getHg());
                        pb+=Double.valueOf(t.getPb());
                        pet+=Double.valueOf(t.getPetroleum());
                        zn+=Double.valueOf(t.getZn());
                        ++a;
                    }
                    as = as / a;
                    cd = cd / a;
                    cr = cr / a;
                    cu = cu / a;
                    hg = hg / a;
                    pb = pb / a;
                    pet = pet / a;
                    zn = zn / a;
                    trendAnalysisVo.setAsA(as);
                    trendAnalysisVo.setCd(cd);
                    trendAnalysisVo.setCr(cr);
                    trendAnalysisVo.setCu(cu);
                    trendAnalysisVo.setHg(hg);
                    trendAnalysisVo.setPb(pb);
                    trendAnalysisVo.setPetroleum(pet);
                    trendAnalysisVo.setZn(zn);
                    if (sedimentMonitorings!=null&&sedimentMonitorings.size()!=0){
                        trendAnalysisVoList.add(trendAnalysisVo);
                    }
                    break;
                case "3":
                    int i=0;
                    double zoo=0;
                    double chl=0;
                    double phy=0;
                    double ind=0;
                    double num=0;
                    double sit=0;
                    double zin=0;
                    double znu=0;
                    if (planktonMonitorings == null || planktonMonitorings.isEmpty()){
                        break;
                    }
                    for (PlanktonMonitoring t : planktonMonitorings) {
                        zoo+=Double.valueOf(t.getZooplanktonBiomass());
                        chl+=Double.valueOf(t.getChlorophyll());
                        phy+=Double.valueOf(t.getPhytoplanktonDensity());
                        ind+=Double.valueOf(t.getPhytoplanktonDiversityIndex());
                        num+=Double.valueOf(t.getPhytoplanktonSpeciesNumber());
                        sit+=Double.valueOf(t.getZooplanktonDensity());
                        zin+=Double.valueOf(t.getZooplanktonDiversityIndex());
                        znu+=Double.valueOf(t.getZooplanktonSpeciesNumber());
                        ++i;
                    }
                    zoo = zoo / i;
                    chl = chl / i;
                    phy = phy / i;
                    ind = ind / i;
                    num = num / i;
                    sit = sit / i;
                    zin = zin / i;
                    znu = znu / i;
                    trendAnalysisVo.setZooplanktonBiomass(zoo);
                    trendAnalysisVo.setChlorophyll(chl);
                    trendAnalysisVo.setPhytoplanktonDensity(phy);
                    trendAnalysisVo.setPhytoplanktonDiversityIndex(ind);
                    trendAnalysisVo.setPhytoplanktonSpeciesNumber(num);
                    trendAnalysisVo.setZooplanktonDensity(sit);
                    trendAnalysisVo.setZooplanktonDiversityIndex(zin);
                    trendAnalysisVo.setZooplanktonSpeciesNumber(znu);
                    if (planktonMonitorings!=null&&planktonMonitorings.size()!=0){
                        trendAnalysisVoList.add(trendAnalysisVo);
                    }
                    break;
                case "4":
                    int f=0;
                    double cdd=0;
                    double iAs=0;
                    double col=0;
                    double crr=0;
                    double cuu=0;
                    double mhg=0;
                    double pbb=0;
                    double thg=0;
                    if (biologicalResidueMonitorings == null || biologicalResidueMonitorings.isEmpty()){
                        break;
                    }
                    for (BiologicalResidueMonitoring t : biologicalResidueMonitorings) {
                        cdd+=Double.valueOf(t.getCd());
                        iAs+=Double.valueOf(t.getIAs());
                        col+=Double.valueOf(t.getColiform());
                        crr+=Double.valueOf(t.getCr());
                        cuu+=Double.valueOf(t.getCu());
                        mhg+=Double.valueOf(t.getMeHg());
                        pbb+=Double.valueOf(t.getPb());
                        thg+=Double.valueOf(t.getTHg());
                        ++f;
                    }
                    cdd = cdd / f;
                    iAs = iAs / f;
                    col = col / f;
                    crr = crr / f;
                    cuu = cuu / f;
                    mhg = mhg / f;
                    pbb = pbb / f;
                    thg = thg / f;
                    trendAnalysisVo.setCd(cdd);
                    trendAnalysisVo.setColiform(col);
                    trendAnalysisVo.setCr(crr);
                    trendAnalysisVo.setCu(cuu);
                    trendAnalysisVo.setIAs(iAs);
                    trendAnalysisVo.setMeHg(mhg);
                    trendAnalysisVo.setPb(pbb);
                    trendAnalysisVo.setTHg(thg);
                    if (biologicalResidueMonitorings!=null&&biologicalResidueMonitorings.size()!=0){
                        trendAnalysisVoList.add(trendAnalysisVo);
                    }
                    break;
                default:
                    throw  new SofnException("?????????????????????????????????");
            }
        }
        return trendAnalysisVoList;
    }

    @Override
    @Transactional(
            rollbackFor = {Exception.class}
    )
    public void insertMonitoringStationTask(String year) {
        //1.??????????????????????????????????????????id??????
        String organizationId = JustGetOrganization.getOrganizationId(userFeign);//???????????????id
        if(organizationId == null || organizationId.equals("")){
            throw new SofnException("????????????????????????");
        }
        Map<String,Object> params = Maps.newHashMap();
        params.put("year",year);
        params.put("organizationId",organizationId);
        List<MonitoringStationTask> monitoringStationTasks = monitoringStationTaskMapper.selectListByQuery(params);
        if (monitoringStationTasks!=null&&monitoringStationTasks.size()!=0){
            throw  new  SofnException("????????????????????????????????????????????????");
        }
        SysOrganizationForm sysOrganizationForm = JustGetOrganization.getSysOrganizationForm(organizationId, orgFeign);
//        OrganizationInfo orgInfo = JsonUtils.json2obj(UserUtil.getLoginUserOrganizationInfo(), OrganizationInfo.class);
        MonitoringStationTask monitoringStationTask = new MonitoringStationTask();
        monitoringStationTask.setMonitoringStationNumber(0);
        monitoringStationTask.setStatus(StatusEnum.STATUS_ZERO.getCode());
        monitoringStationTask.setCreateTime(new Date());
        monitoringStationTask.setYear(year);
        monitoringStationTask.setId(IdUtil.getUUId());
        monitoringStationTask.setName(sysOrganizationForm.getOrganizationName());
        MonitorStation monitorStation = monitoringStationTaskMapper.selectByName(monitoringStationTask.getName());
        if(monitorStation==null){
            throw  new  SofnException("?????????????????????????????????????????????????????????????????????????????????");
        }
        monitoringStationTask.setArea(monitorStation.getProvince()+"-"+monitorStation.getProvinceCity()+"-"+monitorStation.getCountyTown());
        monitoringStationTask.setSeaArea(monitorStation.getSeaArea());
        monitoringStationTask.setProvice(monitorStation.getProvince());
        monitoringStationTask.setCity(monitorStation.getProvinceCity());
        monitoringStationTask.setCounty(monitorStation.getCountyTown());
        monitoringStationTask.setAddress(monitorStation.getAddress());
        monitoringStationTask.setLongitude(monitorStation.getLongitude());
        monitoringStationTask.setLatitude(monitorStation.getLatitude());
        monitoringStationTask.setWatersName(monitorStation.getWatersName());
        monitoringStationTask.setWatersType(monitorStation.getWatersType());
        //???????????????????????????id
        monitoringStationTask.setOrganizationId(organizationId);
        monitoringStationTask.setOrganizationParentId(monitorStation.getDistributeId());
        try {
            monitoringStationTaskMapper.insert(monitoringStationTask);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    @Transactional(
            rollbackFor = {Exception.class}
    )
    public void remove(String id) {
        List<BatchManagement> list = batchManagementMapper.list(id);
        if (list!=null&&list.size()!=0){
            for (BatchManagement batchManagement : list) {
                List<MonitoringPoint> monitoringPoints = monitoringPointMapper.selectListByQuery(batchManagement.getId());
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
                                    throw  new SofnException("???????????????????????????");
                            }
                        }else {
                            throw new SofnException("?????????????????????????????????");
                        }
                    }
                }else {
                    throw new SofnException("?????????????????????");
                }
                monitoringPointMapper.removeById(batchManagement.getId());
                batchManagementMapper.removeById(batchManagement.getId());
            }
        }else {
            monitoringStationTaskMapper.removeById(id);
        }
        monitoringStationTaskMapper.removeById(id);
    }

    @Transactional(
            rollbackFor = {Exception.class}
    )
    @Override
    public void update(MonitoringStationTask monitoringStationTask) {
        //1.??????????????????????????????????????????id??????
        String organizationId = JustGetOrganization.getOrganizationId(userFeign);//???????????????id
        if(organizationId == null || organizationId.equals("")){
            throw new SofnException("????????????????????????");
        }
        Map<String,Object> params = Maps.newHashMap();
        params.put("year",monitoringStationTask.getYear());
        params.put("organizationId",organizationId);
        List<MonitoringStationTask> monitoringStationTasks = monitoringStationTaskMapper.selectListByQuery(params);
        //monitoringStationTasks.removeIf(monitoringStationTask1 -> monitoringStationTask1.getId().equals(monitoringStationTask.getId()));
        if (!CollectionUtils.isEmpty(monitoringStationTasks)){
            throw  new  SofnException("????????????????????????????????????????????????");
        }
        MonitorStation monitorStation = monitoringStationTaskMapper.selectByName(monitoringStationTask.getName());
        if(monitorStation==null){
            throw  new  SofnException("????????????????????????????????????");
        }
        monitoringStationTask.setArea(monitorStation.getProvince()+"-"+monitorStation.getProvinceCity()+"-"+monitorStation.getCountyTown());
        monitoringStationTask.setSeaArea(monitorStation.getSeaArea());
        monitoringStationTask.setProvice(monitorStation.getProvince());
        monitoringStationTask.setCity(monitorStation.getProvinceCity());
        monitoringStationTask.setCounty(monitorStation.getCountyTown());
        monitoringStationTask.setAddress(monitorStation.getAddress());
        monitoringStationTask.setLongitude(monitorStation.getLongitude());
        monitoringStationTask.setLatitude(monitorStation.getLatitude());
        monitoringStationTask.setWatersName(monitorStation.getWatersName());
        monitoringStationTask.setWatersType(monitorStation.getWatersType());
        monitoringStationTaskMapper.update(monitoringStationTask);
    }

   /* *//**
     * ?????????????????????,????????????????????????
     * @param id
     *//*
    @Transactional(
            rollbackFor = {Exception.class}
    )
    @Override
    public void reportAll(String id) {
        *//*String organizationId = JustGetOrganization.getOrganizationId(userFeign);//???????????????id
        if (organizationId == null || organizationId.equals("")) {
            throw new SofnException("????????????????????????");
        }
        //????????????????????????????????????????????????
        Result<List<SysOrgVo>> fdzem = orgFeign.listByParentId("fdzem", organizationId, null);
        //??????????????????????????????????????????
        if (fdzem.getData().size() == 0 || fdzem.getData() == null) {
            //?????????????????????
            monitoringStationTaskMapper.updateStatus(id, StatusEnum.STATUS_FOUR.getCode());
        } else {
            Result<List<SysOrgVo>> listResult = orgFeign.listByParentId("fdzem", fdzem.getData().get(0).getId(), null);
            //???????????????????????????????????????????????????
            if (listResult.getData().size() == 0 || listResult.getData() == null) {
                //?????????????????????
                monitoringStationTaskMapper.updateStatus(id, StatusEnum.STATUS_SEVEN.getCode());
            }
        }*//*
        monitoringStationTaskMapper.updateStatus(id, StatusEnum.STATUS_FOUR.getCode());
    }*/

    /**
     * ???????????????????????????
     * @param id
     */
    @Transactional(
            rollbackFor = {Exception.class}
    )
    @Override
    public void report(String id) {
        monitoringStationTaskMapper.updateStatus(id, StatusEnum.STATUS_ONE.getCode());
    }

}
