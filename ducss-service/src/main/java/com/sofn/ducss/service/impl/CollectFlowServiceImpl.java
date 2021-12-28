package com.sofn.ducss.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.sofn.common.exception.SofnException;
import com.sofn.common.model.Result;
import com.sofn.common.utils.IdUtil;
import com.sofn.common.utils.JsonUtils;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.UserUtil;
import com.sofn.ducss.enums.AuditStatusEnum;
import com.sofn.ducss.enums.Constants;
import com.sofn.ducss.enums.LogEnum;
import com.sofn.ducss.enums.RegionLevel;
import com.sofn.ducss.mapper.*;
import com.sofn.ducss.model.*;
import com.sofn.ducss.service.*;
import com.sofn.ducss.sysapi.SysApi;
import com.sofn.ducss.sysapi.bean.SysDict;
import com.sofn.ducss.sysapi.bean.SysOrganization;
import com.sofn.ducss.sysapi.bean.SysRegionTreeVo;
import com.sofn.ducss.util.*;
import com.sofn.ducss.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Service
@Slf4j
public class CollectFlowServiceImpl extends ServiceImpl<CollectFlowMapper, CollectFlow> implements CollectFlowService {
    @Autowired
    private CollectFlowMapper collectFlowMapper;

    @Autowired
    private CountryTaskMapper countryTaskMapper;

    @Autowired
    private StrawProduceMapper strawProduceMapper;

    @Autowired
    private StrawUtilizeSumMapper strawUtilizeSumMapper;

    @Autowired
    private StrawUtilizeSumTotalMapper strawUtilizeSumTotalMapper;

    @Autowired
    private CollectFlowLogMapper collectFlowLogMapper;

    @Autowired
    private CountryTaskService countryTaskService;

    @Autowired
    private SysApi sysApi;

    @Autowired
    private ProStillDetailMapper proStillDetailMapper;

    @Autowired
    private DisperseUtilizeDetailMapper disperseUtilizeDetailMapper;

    @Autowired
    private StrawUtilizeDetailMapper strawUtilizeDetailMapper;


    @Autowired
    private MessageService messageService;

    @Autowired
    private ProductionUsageSumMapper productionUsageSumMapper;

    @Autowired
    private StrawUsageSumMapper strawUsageSumMapper;

    @Autowired
    private ReturnLeaveSumMapper returnLeaveSumMapper;

    @Autowired
    private CountyDataAnalysisService countyDataAnalysisService;

    @Autowired
    private DataAnalysisCityService dataAnalysisCityService;


    @Override
    public PageUtils<CollectFlow> getCollectFlowByPage(Integer pageNo, Integer pageSize, List<String> years, String areaId) {
        PageHelper.offsetPage(pageNo, pageSize);

        Map<String, Object> params = Maps.newHashMap();
        params.put("years", years);
        params.put("areaId", areaId);
        List<CollectFlow> list = collectFlowMapper.getCollectFlowList(params);
        for (CollectFlow collectFlow : list) {
            collectFlow.setSynUtilizeNum(collectFlow.getCollectNum().compareTo(new BigDecimal(0)) == 0 ? new BigDecimal(0) : collectFlow.getStrawUtilizeNum().divide(collectFlow.getCollectNum(), 4).multiply(new BigDecimal(100)));
        }
        return PageUtils.getPageUtils(new PageInfo(list));
    }

    @Override
    public CollectFlowWithTotalVo getWaitForExamineDataForCity(String year, String status, String cityId) {

        CollectFlowWithTotalVo collectFlowWithTotalVo = new CollectFlowWithTotalVo();

        CollectFlowVo collectFlowVoTotal = new CollectFlowVo();
        BigDecimal theoryNumTotal = new BigDecimal(0);//理论资源量合计
        BigDecimal collectNumTotal = new BigDecimal(0);//可收集资源量合计
        BigDecimal mainNumTotal = new BigDecimal(0);//市场主体规模化利用量合计
        BigDecimal farmerSplitNumTotal = new BigDecimal(0);//农户分散利用量合计
        BigDecimal directReturnNumTotal = new BigDecimal(0);//直接还田量合计
        BigDecimal strawUtilizeNumTotal = new BigDecimal(0);//秸秆利用量合计
        BigDecimal synUtilizeNumTotal = new BigDecimal(0);//综合利用率合计
        int i = 0;

        List<CollectFlowVo> collectFlowVoList = new ArrayList<>();

        Result<List<SysRegionTreeVo>> listByParentId = sysApi.getListByParentId(cityId, Constants.APPID, "", null);
        List<SysRegionTreeVo> sysRegionTreeVoList = listByParentId.getData();

        //获取所有下级的区划ID
        String childrenIds = SysRegionUtil.getChildrenRegionIdStrByYearList(cityId, year);
        //获取已上报的数量
        Integer regionReportNum = collectFlowMapper.countReported(year, Constants.ExamineState.REPORTED.toString(), childrenIds);

        for (SysRegionTreeVo sysRegionTreeVo : sysRegionTreeVoList) {
            String areaId = sysRegionTreeVo.getRegionCode();
            String regionName = SysRegionUtil.getRegionNameByRegionCode(areaId, year);

            Map<String, Object> params = Maps.newHashMap();
            params.put("year", year);
            params.put("status", status);
            params.put("areaId", areaId);
            CollectFlow collectFlow = collectFlowMapper.getForAreaId(params);
            if (null == collectFlow) {
                CollectFlowVo collectFlowVo = new CollectFlowVo();
                collectFlowVo.setRegionName(regionName);
                if (collectFlowVo.getStatus().toString().equals(status) || status.equals("")) {
                    collectFlowVoList.add(collectFlowVo);
                }
            } else {
                CollectFlowVo collectFlowVo = CollectFlowVo.getCollectFlowVo(collectFlow);
                if (collectFlowVo.getStatus().toString().equals(status) || status.equals("")) {
                    theoryNumTotal = theoryNumTotal.add(collectFlow.getTheoryNum());
                    collectNumTotal = collectNumTotal.add(collectFlow.getCollectNum());
                    mainNumTotal = mainNumTotal.add(collectFlow.getMainNum());
                    farmerSplitNumTotal = farmerSplitNumTotal.add(collectFlow.getFarmerSplitNum());
                    directReturnNumTotal = directReturnNumTotal.add(collectFlow.getDirectReturnNum());
                    strawUtilizeNumTotal = strawUtilizeNumTotal.add(collectFlow.getStrawUtilizeNum());
                    synUtilizeNumTotal = synUtilizeNumTotal.add(collectFlow.getSynUtilizeNum());
                    collectFlowVo.setRegionName(regionName);
                    collectFlowVoList.add(collectFlowVo);
                    //统计上报和通过的数量
                    if (collectFlow.getStatus() != null) {
                        if (collectFlow.getStatus() == 1 || collectFlow.getStatus() == 2 || collectFlow.getStatus() == 5) {
                            i++;
                        }
                    }
                }
            }
        }

        collectFlowVoTotal.setRegionName("合计");
        collectFlowVoTotal.setTheoryNum(theoryNumTotal);
        collectFlowVoTotal.setCollectNum(collectNumTotal);
        collectFlowVoTotal.setMainNum(mainNumTotal);
        collectFlowVoTotal.setFarmerSplitNum(farmerSplitNumTotal);
        collectFlowVoTotal.setDirectReturnNum(directReturnNumTotal);
        collectFlowVoTotal.setStrawUtilizeNum(strawUtilizeNumTotal);
        collectFlowVoTotal.setSynUtilizeNum(synUtilizeNumTotal.divide(BigDecimal.valueOf(i), 2, BigDecimal.ROUND_HALF_UP));
        Byte statusTotal = -1;
        collectFlowVoTotal.setStatus(statusTotal);

        collectFlowWithTotalVo.setCollectFlowVoTotal(collectFlowVoTotal);
        collectFlowWithTotalVo.setCollectFlowVoList(collectFlowVoList);
        collectFlowWithTotalVo.setRegionTotalNum(sysRegionTreeVoList.size());
        collectFlowWithTotalVo.setRegionReportNum(regionReportNum);

        return collectFlowWithTotalVo;
    }

    @Override
    public CollectFlowWithTotalVo getWaitForExamineDataForProvince(String year, String status, String provinceId) {

        CollectFlowWithTotalVo collectFlowWithTotalVo = new CollectFlowWithTotalVo();

        CollectFlowVo collectFlowVoTotal = new CollectFlowVo();
        BigDecimal theoryNumTotal = new BigDecimal(0);//理论资源量合计
        BigDecimal collectNumTotal = new BigDecimal(0);//可收集资源量合计
        BigDecimal mainNumTotal = new BigDecimal(0);//市场主体规模化利用量合计
        BigDecimal farmerSplitNumTotal = new BigDecimal(0);//农户分散利用量合计
        BigDecimal directReturnNumTotal = new BigDecimal(0);//直接还田量合计
        BigDecimal strawUtilizeNumTotal = new BigDecimal(0);//秸秆利用量合计
        BigDecimal synUtilizeNumTotal = new BigDecimal(0);//综合利用率合计
        int i = 0;

        List<CollectFlowVo> collectFlowVoList = new ArrayList<>();

        Result<List<SysRegionTreeVo>> listByParentId = sysApi.getListByParentId(provinceId, Constants.APPID, "", null);
        List<SysRegionTreeVo> sysRegionTreeVoList = listByParentId.getData();

        //获取所有下级的区划ID
        String childrenIds = SysRegionUtil.getChildrenRegionIdStrByYearList(provinceId, year);
        //获取已上报的数量
        Integer regionReportNum = collectFlowMapper.countReported(year, Constants.ExamineState.REPORTED.toString(), childrenIds);

        for (SysRegionTreeVo sysRegionTreeVo : sysRegionTreeVoList) {
            String areaId = sysRegionTreeVo.getRegionCode();
            String regionName = SysRegionUtil.getRegionNameByRegionCode(areaId, year);

            Map<String, Object> params = Maps.newHashMap();
            params.put("year", year);
            params.put("status", status);
            params.put("areaId", areaId);
            CollectFlow collectFlow = collectFlowMapper.getForAreaId(params);
            if (null == collectFlow) {
                CollectFlowVo collectFlowVo = new CollectFlowVo();
                collectFlowVo.setRegionName(regionName);
                if (collectFlowVo.getStatus().toString().equals(status) || status.equals("")) {
                    collectFlowVoList.add(collectFlowVo);
                }
            } else {
                CollectFlowVo collectFlowVo = CollectFlowVo.getCollectFlowVo(collectFlow);
                if (collectFlowVo.getStatus().toString().equals(status) || status.equals("")) {
                    theoryNumTotal = theoryNumTotal.add(collectFlow.getTheoryNum());
                    collectNumTotal = collectNumTotal.add(collectFlow.getCollectNum());
                    mainNumTotal = mainNumTotal.add(collectFlow.getMainNum());
                    farmerSplitNumTotal = farmerSplitNumTotal.add(collectFlow.getFarmerSplitNum());
                    directReturnNumTotal = directReturnNumTotal.add(collectFlow.getDirectReturnNum());
                    strawUtilizeNumTotal = strawUtilizeNumTotal.add(collectFlow.getStrawUtilizeNum());
                    synUtilizeNumTotal = synUtilizeNumTotal.add(collectFlow.getSynUtilizeNum());
                    collectFlowVo.setRegionName(regionName);
                    collectFlowVoList.add(collectFlowVo);
                    //统计上报和通过的数量
                    if (collectFlow.getStatus() != null) {
                        if (collectFlow.getStatus() == 1 || collectFlow.getStatus() == 2 || collectFlow.getStatus() == 5) {
                            i++;
                        }
                    }
                }
            }
        }

        collectFlowVoTotal.setRegionName("合计");
        collectFlowVoTotal.setTheoryNum(theoryNumTotal);
        collectFlowVoTotal.setCollectNum(collectNumTotal);
        collectFlowVoTotal.setMainNum(mainNumTotal);
        collectFlowVoTotal.setFarmerSplitNum(farmerSplitNumTotal);
        collectFlowVoTotal.setDirectReturnNum(directReturnNumTotal);
        collectFlowVoTotal.setStrawUtilizeNum(strawUtilizeNumTotal);
        collectFlowVoTotal.setSynUtilizeNum(synUtilizeNumTotal.divide(BigDecimal.valueOf(i), 2, BigDecimal.ROUND_HALF_UP));
        Byte statusTotal = -1;
        collectFlowVoTotal.setStatus(statusTotal);

        collectFlowWithTotalVo.setCollectFlowVoTotal(collectFlowVoTotal);
        collectFlowWithTotalVo.setCollectFlowVoList(collectFlowVoList);
        collectFlowWithTotalVo.setRegionTotalNum(sysRegionTreeVoList.size());
        collectFlowWithTotalVo.setRegionReportNum(regionReportNum);

        return collectFlowWithTotalVo;
    }

    @Override
    public CollectFlowWithTotalVo getWaitForExamineDataForMinistry(String year, String status, String regioncode) {

        CollectFlowWithTotalVo collectFlowWithTotalVo = new CollectFlowWithTotalVo();

        CollectFlowVo collectFlowVoTotal = new CollectFlowVo();
        BigDecimal theoryNumTotal = new BigDecimal(0);//理论资源量合计
        BigDecimal collectNumTotal = new BigDecimal(0);//可收集资源量合计
        BigDecimal mainNumTotal = new BigDecimal(0);//市场主体规模化利用量合计
        BigDecimal farmerSplitNumTotal = new BigDecimal(0);//农户分散利用量合计
        BigDecimal directReturnNumTotal = new BigDecimal(0);//直接还田量合计
        BigDecimal strawUtilizeNumTotal = new BigDecimal(0);//秸秆利用量合计
        BigDecimal synUtilizeNumTotal = new BigDecimal(0);//综合利用率合计
        int i = 0;

        List<CollectFlowVo> collectFlowVoList = new ArrayList<>();

        Result<List<SysRegionTreeVo>> listByParentId = sysApi.getListByParentId(regioncode, Constants.APPID, "", null);
        List<SysRegionTreeVo> sysRegionTreeVoList = listByParentId.getData();

        int regionReportNum = 0;

        for (SysRegionTreeVo sysRegionTreeVo : sysRegionTreeVoList) {
            String areaId = sysRegionTreeVo.getRegionCode();
            String regionName = SysRegionUtil.getRegionNameByRegionCode(areaId, year);

            Map<String, Object> params = Maps.newHashMap();
            params.put("year", year);
            params.put("status", status);
            params.put("areaId", areaId);
            CollectFlow collectFlow = collectFlowMapper.getForAreaId(params);
            if (null == collectFlow) {
                CollectFlowVo collectFlowVo = new CollectFlowVo();
                collectFlowVo.setRegionName(regionName);
                if (collectFlowVo.getStatus().toString().equals(status) || status.equals("")) {
                    collectFlowVoList.add(collectFlowVo);
                }
            } else {
                CollectFlowVo collectFlowVo = CollectFlowVo.getCollectFlowVo(collectFlow);
                if (collectFlowVo.getStatus().toString().equals(status) || status.equals("")) {
                    theoryNumTotal = theoryNumTotal.add(collectFlow.getTheoryNum());
                    collectNumTotal = collectNumTotal.add(collectFlow.getCollectNum());
                    mainNumTotal = mainNumTotal.add(collectFlow.getMainNum());
                    farmerSplitNumTotal = farmerSplitNumTotal.add(collectFlow.getFarmerSplitNum());
                    directReturnNumTotal = directReturnNumTotal.add(collectFlow.getDirectReturnNum());
                    strawUtilizeNumTotal = strawUtilizeNumTotal.add(collectFlow.getStrawUtilizeNum());
                    synUtilizeNumTotal = synUtilizeNumTotal.add(collectFlow.getSynUtilizeNum());
                    collectFlowVo.setRegionName(regionName);
                    collectFlowVoList.add(collectFlowVo);
                    regionReportNum++;
                    //统计上报和通过的数量
                    if (collectFlow.getStatus() != null) {
                        if (collectFlow.getStatus() == 1 || collectFlow.getStatus() == 2 || collectFlow.getStatus() == 5) {
                            i++;
                        }
                    }
                }
            }
        }

        collectFlowVoTotal.setRegionName("合计");
        collectFlowVoTotal.setTheoryNum(theoryNumTotal);
        collectFlowVoTotal.setCollectNum(collectNumTotal);
        collectFlowVoTotal.setMainNum(mainNumTotal);
        collectFlowVoTotal.setFarmerSplitNum(farmerSplitNumTotal);
        collectFlowVoTotal.setDirectReturnNum(directReturnNumTotal);
        collectFlowVoTotal.setStrawUtilizeNum(strawUtilizeNumTotal);
        collectFlowVoTotal.setSynUtilizeNum(synUtilizeNumTotal.divide(BigDecimal.valueOf(i), 2, BigDecimal.ROUND_HALF_UP));
        Byte statusTotal = -1;
        collectFlowVoTotal.setStatus(statusTotal);

        collectFlowWithTotalVo.setCollectFlowVoTotal(collectFlowVoTotal);
        collectFlowWithTotalVo.setCollectFlowVoList(collectFlowVoList);
        collectFlowWithTotalVo.setRegionTotalNum(sysRegionTreeVoList.size());
        collectFlowWithTotalVo.setRegionReportNum(regionReportNum);

        return collectFlowWithTotalVo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public synchronized String updateStatusForCity(UpdateStatusVo reqVO, String userRegionId, String level, String userId, String userName) {
        // 上报
        if (reqVO.getStatus().equals(Constants.ExamineState.REPORTED)) {
            return statusReported(reqVO, userRegionId, level, userId, userName);
        }
        // 阅读
        if (reqVO.getStatus().equals(Constants.ExamineState.READ)) {
            return statusRead(reqVO, userId, userName);
        }
        // 退回
        if (reqVO.getStatus().equals(Constants.ExamineState.RETURNED)) {
            return statusReturn(reqVO, userRegionId, level, userId, userName);
        }
        // 撤回
        if (reqVO.getStatus().equals(Constants.ExamineState.WITHDRAWN)) {
            return statusReCall(reqVO, userId, userName);
        }
        // 通过
        if (reqVO.getStatus().equals(Constants.ExamineState.PASSED)) {
            String mess = null;
            CollectFlow ducCollectFlow = null;
            if (reqVO.isMany()) {
                String[] ids = reqVO.getFlowIds().split(",");
                for (String id : ids) {
                    reqVO.setFlowId(id);
                    ducCollectFlow = collectFlowMapper.selectByPrimaryKey(reqVO.getFlowId());

                    //判断部级任务是否已经公布数据，公布了数据不允许再进行任何操作
                    String year = ducCollectFlow.getYear();
                    Map<String, Object> params3 = Maps.newHashMap();
                    params3.put("year", year);
                    params3.put("taskLevel", RegionLevel.MINISTRY.getCode());
                    List<CountryTask> tasks2 = countryTaskMapper.getCountryTaskByCondition(params3);
                    if (tasks2.size() > 0) {
                        if (tasks2.get(0).getStatus() == Constants.ExamineState.PUBLISH) {
                            throw new SofnException("部级已公布该年度数据，无法进行操作!");
                        }
                    } else {
                        throw new SofnException("未查询到部级任务!");
                    }

                    mess = statusPass(reqVO, ducCollectFlow, userRegionId, level, userId, userName);
                }
            } else {
                ducCollectFlow = collectFlowMapper.selectByPrimaryKey(reqVO.getFlowId());

                //判断部级任务是否已经公布数据，公布了数据不允许再进行任何操作
                String year = ducCollectFlow.getYear();
                Map<String, Object> params3 = Maps.newHashMap();
                params3.put("year", year);
                params3.put("taskLevel", RegionLevel.MINISTRY.getCode());
                List<CountryTask> tasks2 = countryTaskMapper.getCountryTaskByCondition(params3);
                if (tasks2.size() > 0) {
                    if (tasks2.get(0).getStatus() == Constants.ExamineState.PUBLISH) {
                        throw new SofnException("部级已公布该年度数据，无法进行操作!");
                    }
                } else {
                    throw new SofnException("未查询到部级任务!");
                }

                mess = statusPass(reqVO, ducCollectFlow, userRegionId, level, userId, userName);
            }

            if (mess != null) {
                throw new RuntimeException(mess);
            }

            // TODO: 2020-08-03  部级流程
            //更新task状态
//            countryTaskMapper.updateTaskStatus(reqVO.getYear(),reqVO.getAreaId(),reqVO.getStatus());

            //通过后，更上级区域的上报汇总Num数据
//            countryTaskService.insertOrUpdateSuperTaskNum(year, userRegionId);
            return "通过成功！";
        }

        return "无效操作！";
    }

    // 审核通过
    private String statusPass(UpdateStatusVo reqVO, CollectFlow ducCollectFlow, String userRegionId, String level, String userId, String userName) {
        if (level.equals(RegionLevel.CITY.getCode())) {
            String areaId = ducCollectFlow.getAreaId();
            Result<List<SysRegionTreeVo>> parentNode = sysApi.getParentNode(areaId, ducCollectFlow.getYear() == null ? null : Integer.valueOf(ducCollectFlow.getYear()));
            List<SysRegionTreeVo> data = parentNode.getData();
            //获取市Id
            SysRegionTreeVo city = data.get(1);
            String cityId = city.getRegionCode();
            if (!userRegionId.equals(cityId)) {
                return "无权限审核！";
            }
        } else if (level.equals(RegionLevel.PROVINCE.getCode())) {
            String areaId = ducCollectFlow.getAreaId();
            Result<List<SysRegionTreeVo>> parentNode = sysApi.getParentNode(areaId, ducCollectFlow.getYear() == null ? null : Integer.valueOf(ducCollectFlow.getYear()));
            List<SysRegionTreeVo> data = parentNode.getData();
            //获取省Id
            SysRegionTreeVo province = data.get(0);
            String provinceId = province.getRegionCode();
            if (!userRegionId.equals(provinceId)) {
                return "无权限审核！";
            }
        } else if (level.equals(RegionLevel.MINISTRY.getCode())) {

        } else {
            return "无权限审核！";
        }

        // 只有上报的数据或者已读状态才能审核
        if (ducCollectFlow.getStatus().equals(Constants.ExamineState.REPORTED)
                || ducCollectFlow.getStatus().equals(Constants.ExamineState.READ)) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("areaId", userRegionId);
            map.put("year", ducCollectFlow.getYear());
            CollectFlow thisDucCollectFlow = collectFlowMapper.selectDucCollectFlow(map);
            if (thisDucCollectFlow != null && (thisDucCollectFlow.getStatus().equals(Constants.ExamineState.REPORTED)
                    || thisDucCollectFlow.getStatus().equals(Constants.ExamineState.PASSED))) {
                return "已上报，不能再审核！";
            }
            // 更新当前状态
            collectFlowMapper.updateCollectflow(reqVO);
            if (thisDucCollectFlow == null) {
                thisDucCollectFlow = new CollectFlow();
                thisDucCollectFlow.setId(IdUtil.getUUId());
                thisDucCollectFlow.setLevel((byte) (ducCollectFlow.getLevel() + 1));
                thisDucCollectFlow.setCreateDate(new Date());
                thisDucCollectFlow.setCreateUserId(userId);
                thisDucCollectFlow.setCreateUser(userName);
                thisDucCollectFlow.setStatus(Constants.ExamineState.SAVE);
                thisDucCollectFlow.setAreaId(userRegionId);
                thisDucCollectFlow.setYear(ducCollectFlow.getYear());
                thisDucCollectFlow.setIsreport((byte) 0);
                thisDucCollectFlow.setTheoryNum(new BigDecimal(0));
                thisDucCollectFlow.setCollectNum(new BigDecimal(0));
                thisDucCollectFlow.setMainNum(new BigDecimal(0));
                thisDucCollectFlow.setFarmerSplitNum(new BigDecimal(0));
                thisDucCollectFlow.setDirectReturnNum(new BigDecimal(0));
                thisDucCollectFlow.setStrawUtilizeNum(new BigDecimal(0));
                thisDucCollectFlow.setBuyOtherNum(new BigDecimal(0));
                thisDucCollectFlow.setExportNum(new BigDecimal(0));
                thisDucCollectFlow.setCollectResourceV2(new BigDecimal(0));
                thisDucCollectFlow.setStrawUtilizationV2(new BigDecimal(0));
            }
            addNum(thisDucCollectFlow, ducCollectFlow, "add");
            collectFlowMapper.insertOrUpdate(thisDucCollectFlow);
            // 更新任务表状态
            countryTaskMapper.updateTaskStatus(ducCollectFlow.getYear(), ducCollectFlow.getAreaId(),
                    Constants.ExamineState.PASSED);
            // 查看信息写入日志
            //2020/11/2日注释掉
            writeLog(reqVO, ducCollectFlow, userId, userName);
            //新增通过站内消息
            this.addPassAndReturnedMessage(ducCollectFlow, null, AuditStatusEnum.PASSED.getCode());
            String areaName = SysRegionUtil.getFullRegionNameByLastCode(ducCollectFlow.getAreaId(), ducCollectFlow.getYear());
            LogUtil.addLog(LogEnum.LOG_TYPE_ADOPT.getCode(), "通过-" + ducCollectFlow.getYear() + "-" + areaName + "地方的数据");
        } else {
            return "该数据已经审核，不能再次审核";
        }
        return null;
    }

    private void addNum(CollectFlow thisFlow, CollectFlow downFlow, String type) {
        //fix bug, old data maybe null
        if (thisFlow.getTheoryNum() == null) {
            thisFlow.setTheoryNum(BigDecimal.ZERO);
        }
        if (thisFlow.getCollectNum() == null) {
            thisFlow.setCollectNum(BigDecimal.ZERO);
        }
        if (thisFlow.getMainNum() == null) {
            thisFlow.setMainNum(BigDecimal.ZERO);
        }
        if (thisFlow.getFarmerSplitNum() == null) {
            thisFlow.setFarmerSplitNum(BigDecimal.ZERO);
        }
        if (thisFlow.getDirectReturnNum() == null) {
            thisFlow.setDirectReturnNum(BigDecimal.ZERO);
        }
        if (thisFlow.getStrawUtilizeNum() == null) {
            thisFlow.setStrawUtilizeNum(BigDecimal.ZERO);
        }
        if (thisFlow.getBuyOtherNum() == null) {
            thisFlow.setBuyOtherNum(BigDecimal.ZERO);
        }
        if (thisFlow.getExportNum() == null) {
            thisFlow.setExportNum(BigDecimal.ZERO);
        }

        if (type.equals("add")) {
            thisFlow.setTheoryNum(thisFlow.getTheoryNum().add(downFlow.getTheoryNum()));
            thisFlow.setCollectNum(thisFlow.getCollectNum().add(downFlow.getCollectNum()));
            thisFlow.setMainNum(thisFlow.getMainNum().add(downFlow.getMainNum()));
            thisFlow.setFarmerSplitNum(thisFlow.getFarmerSplitNum().add(downFlow.getFarmerSplitNum()));
            thisFlow.setDirectReturnNum(thisFlow.getDirectReturnNum().add(downFlow.getDirectReturnNum()));
            thisFlow.setStrawUtilizeNum(thisFlow.getStrawUtilizeNum().add(downFlow.getStrawUtilizeNum()));
            thisFlow.setBuyOtherNum(thisFlow.getBuyOtherNum().add(downFlow.getBuyOtherNum()));
            thisFlow.setExportNum(thisFlow.getExportNum().add(downFlow.getExportNum()));
            if (downFlow.getCollectResourceV2().compareTo(BigDecimal.ZERO) > 0 && downFlow.getStrawUtilizationV2().compareTo(BigDecimal.ZERO) > 0) {
                thisFlow.setCollectResourceV2(thisFlow.getCollectResourceV2().add(downFlow.getCollectResourceV2()));
                thisFlow.setStrawUtilizationV2(thisFlow.getStrawUtilizationV2().add(downFlow.getStrawUtilizationV2()));
            }
        } else if (type.equals("del")) {
            thisFlow.setTheoryNum(assureNonnegative(thisFlow.getTheoryNum().subtract(downFlow.getTheoryNum())));
            thisFlow.setCollectNum(assureNonnegative(thisFlow.getCollectNum().subtract(downFlow.getCollectNum())));
            thisFlow.setMainNum(assureNonnegative(thisFlow.getMainNum().subtract(downFlow.getMainNum())));
            thisFlow.setFarmerSplitNum(assureNonnegative(thisFlow.getFarmerSplitNum().subtract(downFlow.getFarmerSplitNum())));
            thisFlow.setDirectReturnNum(assureNonnegative(thisFlow.getDirectReturnNum().subtract(downFlow.getDirectReturnNum())));
            thisFlow.setStrawUtilizeNum(assureNonnegative(thisFlow.getStrawUtilizeNum().subtract(downFlow.getStrawUtilizeNum())));
            thisFlow.setBuyOtherNum(assureNonnegative(thisFlow.getBuyOtherNum().subtract(downFlow.getBuyOtherNum())));
            thisFlow.setExportNum(assureNonnegative(thisFlow.getExportNum().subtract(downFlow.getExportNum())));
        }
        // 计算综合利用率
//        if ((thisFlow.getStrawUtilizeNum().compareTo(new BigDecimal(0)) > 0 || thisFlow.getExportNum().compareTo(new BigDecimal(0)) > 0)
//                && thisFlow.getCollectNum().compareTo(new BigDecimal(0)) > 0) {
        thisFlow.setSynUtilizeNum(StrawCalculatorUtil2.calculationComprehensiveRote(thisFlow.getStrawUtilizeNum(), thisFlow.getCollectNum()));
//            //2019.4.17公式修改,（本县秸秆利用量-收购外县的秸秆总量）+调出秸秆量/可收集量
//            thisFlow.setSynUtilizeNum(thisFlow.getStrawUtilizeNum()
//                    .subtract(thisFlow.getBuyOtherNum())
//                    .add(thisFlow.getExportNum())
//                    .multiply(new BigDecimal(100))
//                    .divide(thisFlow.getCollectNum(), 10, RoundingMode.HALF_UP));
//        } else {
//            thisFlow.setSynUtilizeNum(new BigDecimal(0));
//        }
    }

    //保证数值非负数
    private BigDecimal assureNonnegative(BigDecimal val) {
        if (val.compareTo(new BigDecimal(0)) == -1)
            return new BigDecimal(0);

        return val;
    }

    private void writeLog(UpdateStatusVo reqVO, CollectFlow ducCollectFlow, String userId, String userName) {
        CollectFlowLog ducCollectFlowLog = new CollectFlowLog();
        BeanUtils.copyProperties(ducCollectFlow, ducCollectFlowLog);
        // 激活文件
        if (StringUtils.isNotEmpty(reqVO.getFiles())) {
            if (!FileUtil.activationFiles(reqVO.getFiles())) {
                throw new SofnException("上传文件异常");
            }
            ducCollectFlowLog.setFiles(reqVO.getFiles());
        }
        ducCollectFlowLog.setId(IdUtil.getUUId());
        ducCollectFlowLog.setOperation(reqVO.getStatus() + "");
        ducCollectFlowLog.setRemark(reqVO.getRemark());
        ducCollectFlowLog.setCreateUserId(userId);
        ducCollectFlowLog.setCreateUserName(userName);
        ducCollectFlowLog.setMinhour(reqVO.getMinhour());
        ducCollectFlowLog.setCreateTime(new Date());
        collectFlowLogMapper.insertFlowLog(ducCollectFlowLog);
    }

    // 审核上报
    private String statusReported(UpdateStatusVo reqVO, String userRegionId, String level, String userId, String userName) {
        CollectFlow ducCollectFlow = collectFlowMapper.selectByPrimaryKey(reqVO.getFlowId());

        //判断部级任务是否已经公布数据，公布了数据不允许再进行任何操作
        String year = ducCollectFlow.getYear();
        Map<String, Object> params3 = Maps.newHashMap();
        params3.put("year", year);
        params3.put("taskLevel", RegionLevel.MINISTRY.getCode());
        List<CountryTask> tasks2 = countryTaskMapper.getCountryTaskByCondition(params3);
        if (tasks2.size() > 0) {
            if (tasks2.get(0).getStatus() == Constants.ExamineState.PUBLISH) {
                throw new SofnException("部级已公布该年度数据，无法进行操作!");
            }
        } else {
            throw new SofnException("未查询到部级任务!");
        }

        if ((ducCollectFlow.getStatus().equals(Constants.ExamineState.REPORTED)
                || ducCollectFlow.getStatus().equals(Constants.ExamineState.PASSED))) {
            return "已上报，不能再重复上报！";
        }
        reqVO.setAreaId(userRegionId);
        reqVO.setYear(ducCollectFlow.getYear());
        // 暂时屏蔽掉
        /*
         * List<DucCollectFlow> list =
         * collectflowDao.selectWaitListNotPage(reqVO); for (DucCollectFlow flow
         * : list) { if (flow.getStatus() == null ||
         * !flow.getStatus().equals(Constants.ExamineState.PASSED)) { return
         * flow.getAreaName() + "没有审核，不能上报！"; } }
         */
        reqVO.setStatus(Constants.ExamineState.REPORTED);

        //判断是否所有下级都是未通过状态，都是未通过则不允许上报
        Boolean flag = false;
        Result<List<SysRegionTreeVo>> listByParentId = sysApi.getListByParentId(userRegionId, Constants.APPID, "", null);
        List<SysRegionTreeVo> sysRegionTreeVoList = listByParentId.getData();
        for (SysRegionTreeVo sysRegionTreeVo : sysRegionTreeVoList) {
            String areaId = sysRegionTreeVo.getRegionCode();

            Map<String, Object> params = Maps.newHashMap();
            params.put("year", year);
            params.put("areaId", areaId);
            CollectFlow collectFlow = collectFlowMapper.getForAreaId(params);
            if (null != collectFlow) {
                if (collectFlow.getStatus() != null) {
                    if (collectFlow.getStatus() == 5) {
                        flag = true;
                    }
                    if (collectFlow.getStatus() == 1) {
                        throw new SofnException("请将待审核的数据审核完后再上报!");
                    }
                }
            }
        }
        if (flag == false) {
            throw new SofnException("没有一条数据通过不允许进行上报!");
        }

        //获取所有下级的区划ID
        List<String> childrenIds = SysRegionUtil.getChildrenRegionIdByYearList(userRegionId, year);
        // 查询所有下级的作物综合利用率有无超过100%
        StringBuilder sb = new StringBuilder();
        if (RegionLevel.CITY.getCode().equals(level)) {
            List<DataAnalysisArea> areaList = countyDataAnalysisService.listByYearAndAreaIdsAndTotolRateTooMuch(year, childrenIds);
            for (DataAnalysisArea area : areaList) {
                if (StringUtils.isNotEmpty(area.getAreaName()) && area.getAreaName().lastIndexOf("/") != -1) {
                    area.setAreaName(area.getAreaName().substring(area.getAreaName().lastIndexOf("/")).replaceAll("/", ""));
                }
                sb.append(area.getAreaName()).append(area.getStrawName()).append("综合利用率超过100%");
            }
        } else if (RegionLevel.PROVINCE.getCode().equals(level)) {
            List<DataAnalysisCity> cityList = dataAnalysisCityService.listByYearAndCityIdsAndTotolRateTooMuch(year, childrenIds);
            for (DataAnalysisCity city : cityList) {
                if (StringUtils.isNotEmpty(city.getAreaName()) && city.getAreaName().lastIndexOf("/") != -1) {
                    city.setAreaName(city.getAreaName().substring(city.getAreaName().lastIndexOf("/")).replaceAll("/", ""));
                }
                sb.append(city.getAreaName()).append(city.getStrawName()).append("综合利用率超过100%");
            }
        }
        if (sb.length() > 0) {
            throw new SofnException(sb.toString());
        }
        ArrayList<String> status = new ArrayList<>();
        status.add(AuditStatusEnum.PASSED.getCode());
        // 查询秸秆产生量数据
        List<StrawProduce> proList = strawProduceMapper.sumStrawProduce2(userRegionId, ducCollectFlow.getYear(), status, childrenIds);
        if (!proList.isEmpty()) {
            for (StrawProduce s : proList) {
                s.setId(IdUtil.getUUId());
            }
            collectFlowMapper.updateCollectflow(reqVO);
            strawProduceMapper.insertBatchStrawProduce(proList);
            //更新task状态
            countryTaskMapper.updateTaskStatus(ducCollectFlow.getYear(), ducCollectFlow.getAreaId(), reqVO.getStatus());
        } else {
            return "无数据不能上报！";
        }

        // 查询秸秆利用量数据
        List<StrawUtilizeSum> utilizeList = strawUtilizeSumMapper.sumStrawUtilizeSum(userRegionId, ducCollectFlow.getYear(), status, childrenIds);
        if (!utilizeList.isEmpty()) {
            for (StrawUtilizeSum s : utilizeList) {
                s.setId(IdUtil.getUUId());
            }
            strawUtilizeSumMapper.insertBatchStrawUtilizeSum(utilizeList);
        }
        //zy1 新增秸秆利用量新汇总
        if (!utilizeList.isEmpty()) {
            strawUsageSumMapper.insertBatchStrawUsageSum(SumUtil.strawUsageSum(utilizeList));
        }

        StrawUtilizeSum sum = strawUtilizeSumMapper.selectThanCountrySum(reqVO.getAreaId(), reqVO.getYear(), status, childrenIds);
        if (sum != null) {
            sum.setId(IdUtil.getUUId());
            strawUtilizeSumMapper.insertStrawUtilizeSumTotal(sum);
        }
        // zy1 新增产生量与利用量汇总
        productionUsageSumMapper.insertProductionUsageSum(SumUtil.getProductionUsageSum(sum));

        //zy1 新增秸秆还田离田汇总
        if (!CollectionUtils.isEmpty(utilizeList)) {
            returnLeaveSumMapper.insertBatchReturnLeaveSum(SumUtil.getReturnLeaveSum(utilizeList));
        }
        //根据登录用户等级，新增至不同的数据分析表中
        //获取当前登录用户等级
        String organizationInfo = UserUtil.getLoginUserOrganizationInfo();
        SysOrganization sysOrganization = JsonUtils.json2obj(organizationInfo, SysOrganization.class);
        if (RegionLevel.CITY.getCode().equals(sysOrganization.getOrganizationLevel())) {
            countyDataAnalysisService.insertCityDataAnalysis(year, userRegionId);
        }
        if (RegionLevel.PROVINCE.getCode().equals(sysOrganization.getOrganizationLevel())) {
            countyDataAnalysisService.insertProvinceDataAndSixRegionAnalysis(year, userRegionId);
        }
        addReportAndWithrawnMessage(ducCollectFlow, AuditStatusEnum.REPORTED.getCode());
        String areaId = ducCollectFlow.getAreaId();
        String areaName = SysRegionUtil.getFullRegionNameByLastCode(areaId, year);
        LogUtil.addLog(LogEnum.LOG_TYPE_REPORTING.getCode(), "上报-" + ducCollectFlow.getYear() + "-" + areaName + "地方的数据");
        // collectFlowLog
        writeLog(reqVO, ducCollectFlow, userId, userName);
        return "上报成功！";
    }

    // 审核阅读
    private String statusRead(UpdateStatusVo reqVO, String userId, String userName) {
        CollectFlow ducCollectFlow = collectFlowMapper.selectByPrimaryKey(reqVO.getFlowId());

        //判断部级任务是否已经公布数据，公布了数据不允许再进行任何操作
        String year = ducCollectFlow.getYear();
        Map<String, Object> params3 = Maps.newHashMap();
        params3.put("year", year);
        params3.put("taskLevel", RegionLevel.MINISTRY.getCode());
        List<CountryTask> tasks2 = countryTaskMapper.getCountryTaskByCondition(params3);
        if (tasks2.size() > 0) {
            if (tasks2.get(0).getStatus() == Constants.ExamineState.PUBLISH) {
                throw new SofnException("部级已公布该年度数据，无法进行操作!");
            }
        } else {
            throw new SofnException("未查询到部级任务!");
        }

        // 只有上报的数据才修改成已读，其他状态不能修改
        if (ducCollectFlow.getStatus().equals(Constants.ExamineState.REPORTED)) {
            collectFlowMapper.updateCollectflow(reqVO);
            // 查看信息写入日志
            writeLog(reqVO, ducCollectFlow, userId, userName);

            // 更新任务表状态为已读
            countryTaskMapper.updateTaskStatus(ducCollectFlow.getYear(), ducCollectFlow.getAreaId(),
                    Constants.ExamineState.READ);
        }
        return null;
    }

    // 退回
    private String statusReturn(UpdateStatusVo reqVO, String userRegionId, String level, String userId, String userName) {
        CollectFlow ducCollectFlow = collectFlowMapper.selectByPrimaryKey(reqVO.getFlowId());

        //判断部级任务是否已经公布数据，公布了数据不允许再进行任何操作
        String year = ducCollectFlow.getYear();
        Map<String, Object> params3 = Maps.newHashMap();
        params3.put("year", year);
        params3.put("taskLevel", RegionLevel.MINISTRY.getCode());
        List<CountryTask> tasks2 = countryTaskMapper.getCountryTaskByCondition(params3);
        if (tasks2.size() > 0) {
            if (tasks2.get(0).getStatus() == Constants.ExamineState.PUBLISH) {
                throw new SofnException("部级已公布该年度数据，无法进行操作!");
            }
        } else {
            throw new SofnException("未查询到部级任务!");
        }

        //获取所有下级的区划ID
        String childrenIds = SysRegionUtil.getChildrenRegionIdStrByYearList(userRegionId, year);
        if (level.equals(RegionLevel.CITY.getCode())) {
            String areaId = ducCollectFlow.getAreaId();
            Result<List<SysRegionTreeVo>> parentNode = sysApi.getParentNode(areaId, year == null ? null : Integer.valueOf(year));
            List<SysRegionTreeVo> data = parentNode.getData();
            //获取市Id
            SysRegionTreeVo city = data.get(1);
            String cityId = city.getRegionCode();
            if (!userRegionId.equals(cityId)) {
                return "非上级单位,无权限退回！";
            }
        } else if (level.equals(RegionLevel.PROVINCE.getCode())) {
            String areaId = ducCollectFlow.getAreaId();
            Result<List<SysRegionTreeVo>> parentNode = sysApi.getParentNode(areaId, year == null ? null : Integer.valueOf(year));
            List<SysRegionTreeVo> data = parentNode.getData();
            //获取省名称
            SysRegionTreeVo province = data.get(0);
            String provinceId = province.getRegionCode();
            if (!userRegionId.equals(provinceId)) {
                return "非上级单位,无权限退回！";
            }
        } else if (level.equals(RegionLevel.MINISTRY.getCode())) {

        } else {
            return "非上级单位,无权限退回！";
        }

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("areaId", userRegionId);
        map.put("year", ducCollectFlow.getYear());

        //查上级
        CollectFlow thisDucCollectFlow = collectFlowMapper.selectDucCollectFlow(map);

        // 退回需要本级未上报或者本级状态为已退回，已撤回状态才能退回给下级
        if (thisDucCollectFlow == null || thisDucCollectFlow.getStatus().equals(Constants.ExamineState.SAVE)
                || thisDucCollectFlow.getStatus().equals(Constants.ExamineState.RETURNED)) {
            // 下级状态为已撤回，退回状态不能退回
            if (ducCollectFlow.getStatus().equals(Constants.ExamineState.RETURNED)) {
                return "已经退回，不能再退回！";
            } else if (ducCollectFlow.getStatus().equals(Constants.ExamineState.WITHDRAWN)) {
                return "已经撤回，不能再退回！";
            } else {
                //update status
                collectFlowMapper.updateCollectflow(reqVO);
                // 更新任务表状态
                countryTaskMapper.updateTaskStatus(ducCollectFlow.getYear(), ducCollectFlow.getAreaId(),
                        Constants.ExamineState.RETURNED);
// TODO: 2020-08-04
//                SysArea ducArea = areaService.getAreaInfo(ducCollectFlow.getAreaId());
                //更上级区域的上报汇总Num数据
//                ducCountryTaskService.insertOrUpdateSuperTaskNum(ducCollectFlow.getYear(), ducArea.getParentId());

                // 退回需要填写退回原因
                //2020/11/2日注释 ,无法新增日志
                writeLog(reqVO, ducCollectFlow, userId, userName);
                //新增退回消息
                this.addPassAndReturnedMessage(ducCollectFlow, reqVO.getRemark(), AuditStatusEnum.RETURNED.getCode());
                // 审核通过需要减去数据合计
                if (thisDucCollectFlow != null && ducCollectFlow.getStatus().equals(Constants.ExamineState.PASSED)) {
		    /*addNum(thisDucCollectFlow, ducCollectFlow, "del");
		    collectflowDao.insertOrUpdate(thisDucCollectFlow);*/
                    //2019-06-18修改，每次变更时，由数据库直接更新，而不再计算后写入持久化
                    collectFlowMapper.updateRefreshSuperCollectFlowDataById(thisDucCollectFlow.getId(), thisDucCollectFlow.getAreaId(), thisDucCollectFlow.getYear(), IdUtil.getIdsByStr(childrenIds));
                }
                // 删除秸秆产生量
                StrawProduce ducStrawProduce = new StrawProduce();
                ducStrawProduce.setAreaId(ducCollectFlow.getAreaId());
                ducStrawProduce.setYear(ducCollectFlow.getYear());
                strawProduceMapper.deleteStrawProduce(ducStrawProduce);

                // 删除秸秆利用量
                StrawUtilizeSum strawUtilizeSum = new StrawUtilizeSum();
                strawUtilizeSum.setAreaId(ducCollectFlow.getAreaId());
                strawUtilizeSum.setYear(ducCollectFlow.getYear());
                strawUtilizeSumMapper.deleteStrawUtilizeSum(strawUtilizeSum);

                // 删除秸秆利用量合计
                strawUtilizeSumTotalMapper.deleteStrawUtilizeSum(strawUtilizeSum);

                //删除新的秸秆产生汇总 zy1
                productionUsageSumMapper.deleteByYearandArea(strawUtilizeSum);

                //删除新的秸秆利用汇总 zy1
                strawUsageSumMapper.deleteStrawUtilizeSum(strawUtilizeSum);

                //删除秸秆还田离田汇总 zy1
                returnLeaveSumMapper.deleteStrawUtilizeSum(strawUtilizeSum);
                //根据等级不同删除数据分析中的数据
                if (level.equals(RegionLevel.CITY.getCode())) {
                    countyDataAnalysisService.deleCountyDataAnalysis(year, ducCollectFlow.getAreaId());
                }
                if (level.equals(RegionLevel.PROVINCE.getCode())) {
                    countyDataAnalysisService.deleCityDataAnalysis(year, ducCollectFlow.getAreaId());
                }
                if (level.equals(RegionLevel.MINISTRY.getCode())) {
                    countyDataAnalysisService.deleProvinceDataAnalysis(year, ducCollectFlow.getAreaId());
                }
            }
        } else {
            return "不能退回，上级已经处理！";
        }
        // 判断机构级别   如果是市退回的是县  省退回的是市
        String areaId = ducCollectFlow.getAreaId();
        String areaName = SysRegionUtil.getFullRegionNameByLastCode(areaId, ducCollectFlow.getYear());
        LogUtil.addLog(LogEnum.LOG_TYPE_RETURN.getCode(), "退回-" + ducCollectFlow.getYear() + "-" + areaName + "数据。退回原因: " + reqVO.getRemark());
        return "退回成功！";
    }

    // 主动撤回
    private String statusReCall(UpdateStatusVo reqVO, String userId, String userName) {
        CollectFlow ducCollectFlow = collectFlowMapper.selectByPrimaryKey(reqVO.getFlowId());

        //判断部级任务是否已经公布数据，公布了数据不允许再进行任何操作
        String year = ducCollectFlow.getYear();
        Map<String, Object> params3 = Maps.newHashMap();
        params3.put("year", year);
        params3.put("taskLevel", RegionLevel.MINISTRY.getCode());
        List<CountryTask> tasks2 = countryTaskMapper.getCountryTaskByCondition(params3);
        if (tasks2.size() > 0) {
            if (tasks2.get(0).getStatus() == Constants.ExamineState.PUBLISH) {
                throw new SofnException("部级已公布该年度数据，无法进行操作!");
            }
        } else {
            throw new SofnException("未查询到部级任务!");
        }

        // 已上报的状态可以撤回
        if (ducCollectFlow.getStatus().equals(Constants.ExamineState.REPORTED)) {
            collectFlowMapper.updateCollectflow(reqVO);
            // 撤回信息写入日志
            writeLog(reqVO, ducCollectFlow, userId, userName);
            // 更新任务表状态
            countryTaskMapper.updateTaskStatus(ducCollectFlow.getYear(), ducCollectFlow.getAreaId(),
                    Constants.ExamineState.WITHDRAWN);


            // TODO: 2020-08-04
//            SysArea ducArea = areaService.getAreaInfo(ducCollectFlow.getAreaId());
            //更上级区域的上报汇总Num数据
//            ducCountryTaskService.insertOrUpdateSuperTaskNum(ducCollectFlow.getYear(), ducArea.getParentId());

            StrawProduce ducStrawProduce = new StrawProduce();
            ducStrawProduce.setAreaId(ducCollectFlow.getAreaId());
            ducStrawProduce.setYear(ducCollectFlow.getYear());
            strawProduceMapper.deleteStrawProduce(ducStrawProduce);

            // 删除秸秆利用量
            StrawUtilizeSum strawUtilizeSum = new StrawUtilizeSum();
            strawUtilizeSum.setAreaId(ducCollectFlow.getAreaId());
            strawUtilizeSum.setYear(ducCollectFlow.getYear());
            strawUtilizeSumMapper.deleteStrawUtilizeSum(strawUtilizeSum);

            //删除新的秸秆利用汇总 zy1
            strawUsageSumMapper.deleteStrawUtilizeSum(strawUtilizeSum);

            // 删除秸秆利用量合计
            strawUtilizeSumTotalMapper.deleteStrawUtilizeSum(strawUtilizeSum);

            //zy1 删除新的秸秆产生量与利用量汇总
            productionUsageSumMapper.deleteByYearandArea(strawUtilizeSum);

            //删除还田离田汇总 zy1
            returnLeaveSumMapper.deleteStrawUtilizeSum(strawUtilizeSum);

            addReportAndWithrawnMessage(ducCollectFlow, AuditStatusEnum.WITHDRAWN.getCode());
        } else { //只有状态为已上报时，才可撤回，如果变为已读或者已同意时，则不可撤回
            return "已处理不能撤回！";
        }
        String areaId = ducCollectFlow.getAreaId();
        String areaName = SysRegionUtil.getFullRegionNameByLastCode(areaId, year);
        LogUtil.addLog(LogEnum.LOG_TYPE_WITHDRAW.getCode(), "撤回-" + year + "-" + areaName + "地方数据");
        return null;
    }

    private void addReportAndWithrawnMessage(CollectFlow ducCollectFlow, String status) {
        //xx县xxxx年度数据已撤回
        //获取县名
        String regionName = SysRegionUtil.getRegionNameByRegionCode(ducCollectFlow.getAreaId(), ducCollectFlow.getYear());
        //消息内容
        String text = "";
        if (AuditStatusEnum.REPORTED.getCode().equals(status)) {
            text = regionName + "" + ducCollectFlow.getYear() + "年度数据已上报";
        }
        if (AuditStatusEnum.WITHDRAWN.getCode().equals(status)) {
            text = regionName + "" + ducCollectFlow.getYear() + "年度数据已撤回";
        }
        messageService.addMessageByProcess(String.valueOf(Constants.ExamineState.REPORTED), null, text, ducCollectFlow.getAreaId());
    }


    private void addPassAndReturnedMessage(CollectFlow ducCollectFlow, String opinion, String status) {
        //xx县xxxx年度数据已撤回
        //获取县名
        String regionName = SysRegionUtil.getRegionNameByRegionCode(ducCollectFlow.getAreaId(), ducCollectFlow.getYear());
        //消息内容
        String text = "";
        if (AuditStatusEnum.PASSED.getCode().equals(status)) {
            text = regionName + "" + ducCollectFlow.getYear() + "年度数据已通过";
        }
        if (AuditStatusEnum.RETURNED.getCode().equals(status)) {
            text = regionName + "" + ducCollectFlow.getYear() + "年度数据已退回";
        }
        if (opinion != null) {
            messageService.addMessageByProcess(String.valueOf(Constants.ExamineState.RETURNED), opinion, text, ducCollectFlow.getAreaId());
        } else {
            messageService.addMessageByProcess(String.valueOf(Constants.ExamineState.PASSED), null, text, ducCollectFlow.getAreaId());
        }

    }

    @Override
    public List<StrawUtilizeSum> selectDucStrawUtilizeSum(String areaId, String year) {
        return getProList(areaId, year);
    }

    /**
     * 查询全国任何一个县下有无  上报 ，已读，通过的数据
     *
     * @param parms
     * @return
     */
    @Override
    public Integer selectPass(HashMap<String, Object> parms) {
        int count = collectFlowMapper.selectPass(parms);
        return count;
    }

    // ===================================================================================将Controller中的方法抽取到service中

    @Autowired
    private ProStillDetailService proStillDetailService;

//    @Autowired
//    private StrawProduceMapper strawProduceMapper;

    @Override
    public List<StrawProduceResVo> findStrawProduceData(String areaId, String year) throws JsonProcessingException, IllegalAccessException, InvocationTargetException {
        // 直接复制的Controller中的方法， 没有加其他的操作
        // 增加 粮食产量和播种面积字段
        String userAreaByLogUser = UserUtil.getLoginUserOrganizationInfo();
        if (org.springframework.util.StringUtils.isEmpty(userAreaByLogUser)) {
            throw new SofnException("未获取到登录用户所属机构信息!");
        }
        SysOrganization sysOrganization = JsonUtils.json2obj(userAreaByLogUser, SysOrganization.class);
        String userAId = sysOrganization.getRegioncode();

        if (StringUtils.isBlank(areaId)) {
            areaId = userAId;
        }

        //判断是否是县级账号
        String result = areaId.substring(areaId.length() - 2, areaId.length());

        List<StrawProduce> proList = null;

        if (!result.equals("00")) {
            List<ProStillDetail> list = proStillDetailService.getProStillDetailListByAreaId(areaId,
                    year);
            proList = new ArrayList<StrawProduce>();
            for (ProStillDetail deatil : list) {
                deatil.calculateResource();
                StrawProduce ducStrawProduce = new StrawProduce();
                org.apache.commons.beanutils.BeanUtils.copyProperties(ducStrawProduce, deatil);
                proList.add(ducStrawProduce);
            }
        } else {
            //获取所有下级的区划ID
            String childrenIds = SysRegionUtil.getChildrenRegionIdStrByYearList(areaId, year);
            proList = strawProduceMapper.sumStrawProduce2(areaId, year, null, IdUtil.getIdsByStr(childrenIds));
        }

        // 产生量
        Map<String, BigDecimal> theoryMap = new HashMap<String, BigDecimal>();
        // 可收集量
        Map<String, BigDecimal> collectMap = new HashMap<String, BigDecimal>();
        //  粮食产量
        Map<String, BigDecimal> grainYieldMap = new HashMap<String, BigDecimal>();
        // 播种面积
        Map<String, BigDecimal> seedAreaMap = new HashMap<String, BigDecimal>();

        List<SysDict> list = sysApi.getDictListByType(Constants.DictionaryType.STRAW_TYPE).getData();
        for (SysDict sysDict : list) {
            theoryMap.put(sysDict.getDictcode(), new BigDecimal(0));
            collectMap.put(sysDict.getDictcode(), new BigDecimal(0));
            grainYieldMap.put(sysDict.getDictcode(), new BigDecimal(0));
            seedAreaMap.put(sysDict.getDictcode(), new BigDecimal(0));
        }

        List<StrawProduceResVo> resList = new ArrayList<StrawProduceResVo>(list.size());
        if (proList != null && proList.size() > 0) {
            for (StrawProduce deatil : proList) {
                if (theoryMap.containsKey(deatil.getStrawType())) {
                    theoryMap.put(deatil.getStrawType(),
                            theoryMap.get(deatil.getStrawType()).add(deatil.getTheoryResource()));
                }
                if (collectMap.containsKey(deatil.getStrawType())) {
                    collectMap.put(deatil.getStrawType(),
                            collectMap.get(deatil.getStrawType()).add(deatil.getCollectResource()));
                }

                if (grainYieldMap.containsKey(deatil.getStrawType())) {
                    grainYieldMap.put(deatil.getStrawType(),
                            grainYieldMap.get(deatil.getStrawType()).add(BigDecimalUtil.valueIsNull(deatil.getGrainYield())));
                }
                if (seedAreaMap.containsKey(deatil.getStrawType())) {
                    seedAreaMap.put(deatil.getStrawType(),
                            seedAreaMap.get(deatil.getStrawType()).add(BigDecimalUtil.valueIsNull(deatil.getSeedArea())));
                }


            }
        } else {
            return resList;
        }


        for (SysDict dict : list) {
            StrawProduceResVo rvo = new StrawProduceResVo();
            rvo.setStrawName(dict.getDictname());
            rvo.setStrawType(dict.getDictcode());
            rvo.setTheoryResource(theoryMap.get(dict.getDictcode()));
            rvo.setCollectResource(collectMap.get(dict.getDictcode()));
            rvo.setSeedArea(seedAreaMap.get(dict.getDictcode()));
            rvo.setGrainYield(grainYieldMap.get(dict.getDictcode()));
            resList.add(rvo);
        }
        return resList;
    }

    @Override
    public List<StrawUtilizeSum> findStrawUtilzeData(String areaId, String year) throws JsonProcessingException {

        String userAreaByLogUser = UserUtil.getLoginUserOrganizationInfo();
        if (org.springframework.util.StringUtils.isEmpty(userAreaByLogUser)) {
            throw new SofnException("未获取到登录用户所属机构信息!");
        }
        SysOrganization sysOrganization = JsonUtils.json2obj(userAreaByLogUser, SysOrganization.class);
        String userAId = sysOrganization.getRegioncode();

        if (StringUtils.isBlank(areaId)) {
            areaId = userAId;
        }
        //获取所有下级的区划ID
        String childrenIds = SysRegionUtil.getChildrenRegionIdStrByYearList(areaId, year);
        //判断是否是县级账号
        String result = areaId.substring(areaId.length() - 2, areaId.length());


        List<StrawUtilizeSum> sumList = null;

        StrawUtilizeSum sum = null;
        if (!result.equals("00")) {
            sumList = this.selectDucStrawUtilizeSum(areaId, year);
            // 计算县级合计
            sum = setCountrySum(sumList);
        } else {
            //todo 直接还田
            sumList = strawUtilizeSumMapper.sumStrawUtilizeSum(areaId, year, null, IdUtil.getIdsByStr(childrenIds));
            if (sumList != null && sumList.size() > 0) {
                sum = strawUtilizeSumMapper.selectThanCountrySum(areaId, year, null, IdUtil.getIdsByStr(childrenIds));
            }
        }
        if (sumList != null && sum != null) {
            sumList.add(sum);
        }

        if (sumList == null || sumList.isEmpty()) {
            return sumList;
        }
        List<SysDict> dictList = sysApi.getDictListByType(Constants.DictionaryType.STRAW_TYPE).getData();

        Map<String, SysDict> map = new HashMap<String, SysDict>();
        for (SysDict dict : dictList) {
            map.put(dict.getDictcode(), dict);
        }
        // 按秸秆类型的数据字典进行排序
        if (sumList != null) {
            for (StrawUtilizeSum item : sumList) {
                if (map.containsKey(item.getStrawType())) {
                    item.setStrawName(map.get(item.getStrawType()).getDictname());
                }
                //重新计算还田比例
                if (result.equals("00")) {
                    if (new BigDecimal(0).compareTo(item.getCollectResource()) == -1)
                        item.setReturnRatio(item.getProStillField().multiply(new BigDecimal(100)).divide(item.getCollectResource(), 10, RoundingMode.HALF_UP));
                }

                //保留两位小数并四舍五入
                BigDecimal mainFertilising = new BigDecimal(0);
                if (null != item.getMainFertilising()) {
                    mainFertilising = item.getMainFertilising().setScale(2, BigDecimal.ROUND_HALF_UP);
                    ;
                }
                item.setMainFertilising(mainFertilising);
                BigDecimal mainForage = new BigDecimal(0);
                if (null != item.getMainForage()) {
                    mainForage = item.getMainForage().setScale(2, BigDecimal.ROUND_HALF_UP);
                }
                item.setMainForage(mainForage);
                BigDecimal mainFuel = new BigDecimal(0);
                if (null != item.getMainFuel()) {
                    mainFuel = item.getMainFuel().setScale(2, BigDecimal.ROUND_HALF_UP);
                }
                item.setMainFuel(mainFuel);
                BigDecimal mainBase = new BigDecimal(0);
                if (null != item.getMainBase()) {
                    mainBase = item.getMainBase().setScale(2, BigDecimal.ROUND_HALF_UP);
                }
                item.setMainBase(mainBase);
                BigDecimal mainMaterial = new BigDecimal(0);
                if (null != item.getMainMaterial()) {
                    mainMaterial = item.getMainMaterial().setScale(2, BigDecimal.ROUND_HALF_UP);
                }
                item.setMainMaterial(mainMaterial);
                BigDecimal mainTotal = new BigDecimal(0);
                if (null != item.getMainTotal()) {
                    mainTotal = item.getMainTotal().setScale(2, BigDecimal.ROUND_HALF_UP);
                }
                item.setMainTotal(mainTotal);
                BigDecimal mainTotalOther = new BigDecimal(0);
                if (null != item.getMainTotalOther()) {
                    mainTotalOther = item.getMainTotalOther().setScale(2, BigDecimal.ROUND_HALF_UP);
                }
                item.setMainTotalOther(mainTotalOther);
                BigDecimal disperseFertilising = new BigDecimal(0);
                if (null != item.getDisperseFertilising()) {
                    disperseFertilising = item.getDisperseFertilising().setScale(2, BigDecimal.ROUND_HALF_UP);
                }
                item.setDisperseFertilising(disperseFertilising);
                BigDecimal disperseForage = new BigDecimal(0);
                if (null != item.getDisperseForage()) {
                    disperseForage = item.getDisperseForage().setScale(2, BigDecimal.ROUND_HALF_UP);
                }
                item.setDisperseForage(disperseForage);
                BigDecimal disperseFuel = new BigDecimal(0);
                if (null != item.getDisperseFuel()) {
                    disperseFuel = item.getDisperseFuel().setScale(2, BigDecimal.ROUND_HALF_UP);
                }
                item.setDisperseFuel(disperseFuel);
                BigDecimal disperseBase = new BigDecimal(0);
                if (null != item.getDisperseBase()) {
                    disperseBase = item.getDisperseBase().setScale(2, BigDecimal.ROUND_HALF_UP);
                }
                item.setDisperseBase(disperseBase);
                BigDecimal disperseMaterial = new BigDecimal(0);
                if (null != item.getDisperseMaterial()) {
                    disperseMaterial = item.getDisperseMaterial().setScale(2, BigDecimal.ROUND_HALF_UP);
                }
                item.setDisperseMaterial(disperseMaterial);
                BigDecimal disperseTotal = new BigDecimal(0);
                if (null != item.getDisperseTotal()) {
                    disperseTotal = item.getDisperseTotal().setScale(2, BigDecimal.ROUND_HALF_UP);
                }
                item.setDisperseTotal(disperseTotal);
                BigDecimal proStillField = new BigDecimal(0);
                if (null != item.getProStillField()) {
                    proStillField = item.getProStillField().setScale(2, BigDecimal.ROUND_HALF_UP);
                }
                item.setProStillField(proStillField);
                BigDecimal proStrawUtilize = new BigDecimal(0);
                if (null != item.getProStrawUtilize()) {
                    proStrawUtilize = item.getProStrawUtilize().setScale(2, BigDecimal.ROUND_HALF_UP);
                }
                item.setProStrawUtilize(proStrawUtilize);
                BigDecimal returnRatio = new BigDecimal(0);
                if (null != item.getReturnRatio()) {
                    returnRatio = item.getReturnRatio().setScale(2, BigDecimal.ROUND_HALF_UP);
                }
                item.setReturnRatio(returnRatio);
                BigDecimal comprehensive = new BigDecimal(0);
                if (null != item.getComprehensive()) {
                    comprehensive = item.getComprehensive().setScale(2, BigDecimal.ROUND_HALF_UP);
                }
                item.setComprehensive(comprehensive);
                BigDecimal comprehensiveIndex = new BigDecimal(0);
                if (null != item.getComprehensiveIndex()) {
                    comprehensiveIndex = item.getComprehensiveIndex().setScale(2, BigDecimal.ROUND_HALF_UP);
                }
                item.setComprehensiveIndex(comprehensiveIndex);
                BigDecimal industrializationIndex = new BigDecimal(0);
                if (null != item.getIndustrializationIndex()) {
                    industrializationIndex = item.getIndustrializationIndex().setScale(2, BigDecimal.ROUND_HALF_UP);
                }
                item.setIndustrializationIndex(industrializationIndex);
                BigDecimal collectResource = new BigDecimal(0);
                if (null != item.getCollectResource()) {
                    collectResource = item.getCollectResource().setScale(2, BigDecimal.ROUND_HALF_UP);
                }
                item.setCollectResource(collectResource);
                BigDecimal yieldAllNum = new BigDecimal(0);
                if (null != item.getYieldAllNum()) {
                    yieldAllNum = item.getYieldAllNum().setScale(2, BigDecimal.ROUND_HALF_UP);
                }
                item.setYieldAllNum(yieldAllNum);
                BigDecimal theoryResource = new BigDecimal(0);
                if (null != item.getTheoryResource()) {
                    theoryResource = item.getTheoryResource().setScale(2, BigDecimal.ROUND_HALF_UP);
                }
                item.setTheoryResource(theoryResource);
                BigDecimal yieldAllExport = new BigDecimal(0);
                if (null != item.getYieldAllExport()) {
                    yieldAllExport = item.getYieldAllExport().setScale(2, BigDecimal.ROUND_HALF_UP);
                }
                item.setYieldAllExport(yieldAllExport);
                BigDecimal fertilising = new BigDecimal(0);
                if (null != item.getFertilising()) {
                    fertilising = item.getFertilising().setScale(2, BigDecimal.ROUND_HALF_UP);
                }
                item.setFertilising(fertilising);
                BigDecimal forage = new BigDecimal(0);
                if (null != item.getForage()) {
                    forage = item.getForage().setScale(2, BigDecimal.ROUND_HALF_UP);
                }
                item.setForage(forage);
                BigDecimal fuel = new BigDecimal(0);
                if (null != item.getFuel()) {
                    fuel = item.getFuel().setScale(2, BigDecimal.ROUND_HALF_UP);
                }
                item.setFuel(fuel);
                BigDecimal base = new BigDecimal(0);
                if (null != item.getBase()) {
                    base = item.getBase().setScale(2, BigDecimal.ROUND_HALF_UP);
                }
                item.setBase(base);
                BigDecimal material = new BigDecimal(0);
                if (null != item.getMaterial()) {
                    material = item.getMaterial().setScale(2, BigDecimal.ROUND_HALF_UP);
                }
                item.setMaterial(material);
                BigDecimal grassValleyRatio = new BigDecimal(0);
                if (null != item.getGrassValleyRatio()) {
                    grassValleyRatio = item.getGrassValleyRatio().setScale(2, BigDecimal.ROUND_HALF_UP);
                }
                item.setGrassValleyRatio(grassValleyRatio);
                BigDecimal returnResource = new BigDecimal(0);
                if (null != item.getReturnResource()) {
                    returnResource = item.getReturnResource().setScale(2, BigDecimal.ROUND_HALF_UP);
                }
                item.setReturnResource(returnResource);
                BigDecimal collectionRatio = new BigDecimal(0);
                if (null != item.getCollectionRatio()) {
                    collectionRatio = item.getCollectionRatio().setScale(2, BigDecimal.ROUND_HALF_UP);
                }
                item.setCollectionRatio(collectionRatio);
                BigDecimal returnArea = new BigDecimal(0);
                if (null != item.getReturnArea()) {
                    returnArea = item.getReturnArea().setScale(2, BigDecimal.ROUND_HALF_UP);
                }
                item.setReturnArea(returnArea);
                BigDecimal seedArea = new BigDecimal(0);
                if (null != item.getSeedArea()) {
                    seedArea = item.getSeedArea().setScale(2, BigDecimal.ROUND_HALF_UP);
                }
                item.setSeedArea(seedArea);
                BigDecimal grainYield = new BigDecimal(0);
                if (null != item.getGrainYield()) {
                    grainYield = item.getGrainYield().setScale(2, BigDecimal.ROUND_HALF_UP);
                }
                item.setGrainYield(grainYield);

            }
//            Collections.sort(sumList, new Comparator<StrawUtilizeSum>() {
//
//                @Override
//                public int compare(DucStrawUtilizeSum o1, DucStrawUtilizeSum o2) {
//                    return o1.getOrderNo().compareTo(o2.getOrderNo());
//                }
//            });
        }

        return sumList;
    }

    @Override
    public List<CollectFlow> listByAreaIdsAndYears(List<String> areaIds, List<String> years) {
        return collectFlowMapper.listByAreaIdsAndYears(areaIds, years);
    }

    private StrawUtilizeSum setCountrySum(List<StrawUtilizeSum> sumList) {
        if (sumList == null || sumList.isEmpty()) {
            return null;
        }
        BigDecimal fertilising = new BigDecimal(0); // 肥料化
        BigDecimal forage = new BigDecimal(0);// 饲料化
        BigDecimal fuel = new BigDecimal(0);// 燃料化
        BigDecimal base = new BigDecimal(0);// 基料化
        BigDecimal material = new BigDecimal(0);// 原料化
        BigDecimal yieldAllNum = new BigDecimal(0); // 秸秆产量
        BigDecimal collectResource = new BigDecimal(0); // 可收集量
        BigDecimal yieldAllExport = new BigDecimal(0);// 调入量

        BigDecimal disperseFertilising = new BigDecimal(0);//分散，肥料
        BigDecimal disperseForage = new BigDecimal(0);//分散，饲料
        BigDecimal disperseFuel = new BigDecimal(0);//分散，燃料
        BigDecimal disperseBase = new BigDecimal(0);//分散，基料
        BigDecimal disperseMaterial = new BigDecimal(0);//分散，原料

        BigDecimal mainFertilising = new BigDecimal(0); // 肥料化
        BigDecimal mainForage = new BigDecimal(0);// 饲料化
        BigDecimal mainFuel = new BigDecimal(0);// 燃料化
        BigDecimal mainBase = new BigDecimal(0);// 基料化
        BigDecimal mainMaterial = new BigDecimal(0);// 原料化
        BigDecimal mainTotalOther = new BigDecimal(0);// 其他县收购
        BigDecimal proStillField = new BigDecimal(0);// 直接还田量

        BigDecimal theoryResource = new BigDecimal(0); // 产生量
        BigDecimal seedArea = new BigDecimal(0); // 播种面积
        BigDecimal returnArea = new BigDecimal(0); // 还田面积


        if (sumList != null) {
            for (StrawUtilizeSum item : sumList) {
                fertilising = item.getFertilising().add(fertilising);
                forage = item.getForage().add(forage);
                fuel = item.getFuel().add(fuel);
                base = item.getBase().add(base);
                material = item.getMaterial().add(material);
                yieldAllNum = item.getYieldAllNum().add(yieldAllNum);
                collectResource = item.getCollectResource().add(collectResource);
                yieldAllExport = item.getYieldAllExport().add(yieldAllExport);

                mainFertilising = item.getMainFertilising().add(mainFertilising);
                mainForage = item.getMainForage().add(mainForage);
                mainFuel = item.getMainFuel().add(mainFuel);
                mainBase = item.getMainBase().add(mainBase);
                mainMaterial = item.getMainMaterial().add(mainMaterial);
                mainTotalOther = item.getMainTotalOther().add(mainTotalOther);
                proStillField = item.getProStillField().add(proStillField);
                theoryResource = item.getTheoryResource().add(theoryResource);

                disperseFertilising = disperseFertilising.add(item.getDisperseFertilising());
                disperseForage = disperseForage.add(item.getDisperseForage());
                disperseFuel = disperseFuel.add(item.getDisperseFuel());
                disperseBase = disperseBase.add(item.getDisperseBase());
                disperseMaterial = disperseMaterial.add(item.getDisperseMaterial());
                seedArea = seedArea.add(item.getSeedArea());
                returnArea = returnArea.add(item.getReturnArea());
            }
        }

        StrawUtilizeSum sum = new StrawUtilizeSum();
        sum.setStrawName("合计");
        sum.setCollectResource(collectResource);
        //2019.04.15修改，在sum.calculateNum()里，已计算一次，无需再次计算
//	if (yieldAllNum.compareTo(new BigDecimal(0)) > 0) {
//	    sum.setDisperseFertilising(
//		    fertilising.multiply(collectResource).divide(yieldAllNum, 10, RoundingMode.HALF_UP));
//	    sum.setDisperseForage(forage.multiply(collectResource).divide(yieldAllNum, 10, RoundingMode.HALF_UP));
//	    sum.setDisperseFuel(fuel.multiply(collectResource).divide(yieldAllNum, 10, RoundingMode.HALF_UP));
//	    sum.setDisperseMaterial(material.multiply(collectResource).divide(yieldAllNum, 10, RoundingMode.HALF_UP));
//	    sum.setDisperseBase(base.multiply(collectResource).divide(yieldAllNum, 10, RoundingMode.HALF_UP));
//	}
        sum.setMainBase(mainBase);
        sum.setMainFertilising(mainFertilising);
        sum.setMainForage(mainForage);
        sum.setMainFuel(mainFuel);
        sum.setMainMaterial(mainMaterial);
        sum.setMainTotalOther(mainTotalOther);

        sum.setYieldAllExport(yieldAllExport);
        sum.setProStillField(proStillField);
        sum.setCollectResource(collectResource);
        sum.setTheoryResource(theoryResource);
        sum.setOrderNo(10000);

        sum.setDisperseFertilising(disperseFertilising);
        sum.setDisperseForage(disperseForage);
        sum.setDisperseFuel(disperseFuel);
        sum.setDisperseBase(disperseBase);
        sum.setDisperseMaterial(disperseMaterial);

        sum.setBase(mainBase.add(sum.getDisperseBase()));
        sum.setFertilising(mainFertilising.add(sum.getDisperseFertilising()).add(proStillField));
        sum.setForage(mainForage.add(sum.getDisperseForage()));
        sum.setFuel(mainFuel.add(sum.getDisperseFuel()));
        sum.setMaterial(mainMaterial.add(sum.getDisperseMaterial()));

        sum.setSeedArea(seedArea);
        sum.setReturnArea(returnArea);

        sum.calculateNumNoDisperse(); //计算综合利用率之类的。

        return sum;
    }

    // =====================================================================================

    private List<StrawUtilizeSum> getProList(String areaId, String year) {

        // 秸秆类型
        List<SysDict> dictList = sysApi.getDictListByType(Constants.DictionaryType.STRAW_TYPE).getData();
        Map<String, SysDict> dictMap = new HashMap<String, SysDict>();

        for (SysDict dict : dictList) {
            dictMap.put(dict.getDictcode(), dict);
        }

        List<StrawUtilizeSum> proList = new ArrayList<StrawUtilizeSum>(dictList.size());
        // 查询秸秆生产量与直接还田量
        List<ProStillDetail> list = proStillDetailMapper.getProStillDetailListByAreaId(areaId, year);
        if (list.isEmpty() && list.isEmpty()) {
            return null;
        }

        // 查询分散利用量
        List<DisperseUtilizeDetail> disList = disperseUtilizeDetailMapper.selectDetailByAreaId(areaId, year);

        Map<String, DisperseUtilizeDetail> detailMap = new HashMap<String, DisperseUtilizeDetail>();
        for (DisperseUtilizeDetail detail : disList) {
            detailMap.put(detail.getStrawType(), detail);
        }

        // 市场主体规模利用量
        List<StrawUtilizeDetail> delList = strawUtilizeDetailMapper.selectDetailSumByAreaId(areaId, year);

        if (disList.isEmpty() && delList.isEmpty()) {
            return null;
        }

        Map<String, StrawUtilizeDetail> utilizelMap = new HashMap<String, StrawUtilizeDetail>();
        for (StrawUtilizeDetail detail : delList) {
            utilizelMap.put(detail.getStrawType(), detail);
        }

        for (ProStillDetail deatil : list) {
            deatil.calculateResource();
            StrawUtilizeSum sum = new StrawUtilizeSum();
            // 赋值秸秆生产量与直接还田量
            sum.setDucProStillDeatil(deatil);
            sum.setYear(year);
            sum.setAreaId(areaId);
            if (dictMap.containsKey(deatil.getStrawType())) {
                sum.setStrawName(dictMap.get(deatil.getStrawType()).getDictname());
            }

            if (detailMap.containsKey(deatil.getStrawType())) {
                // 赋值分散利用量
                sum.setDucDisperseUtilizeDetail(detailMap.get(deatil.getStrawType()));
            }

            if (utilizelMap.containsKey(deatil.getStrawType())) {
                // 市场主体明细
                sum.setDucStrawUtilizeDetail(utilizelMap.get(deatil.getStrawType()));
            }
            sum.calculateNum();
            proList.add(sum);
        }
        return proList;
    }

}
