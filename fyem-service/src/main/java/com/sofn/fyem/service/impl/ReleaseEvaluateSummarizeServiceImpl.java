package com.sofn.fyem.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sofn.common.exception.SofnException;
import com.sofn.common.model.Result;
import com.sofn.common.utils.IdUtil;
import com.sofn.common.utils.PageUtils;
import com.sofn.fyem.excelmodel.BasicProliferationReleaseExcel;
import com.sofn.fyem.model.BasicProliferationRelease;
import com.sofn.fyem.model.EvaluateStandardValue;
import com.sofn.fyem.model.EvaluateStandardValueHistory;
import com.sofn.fyem.model.ReleaseEvaluateIndicator;
import com.sofn.fyem.service.*;
import com.sofn.fyem.sysapi.SysRegionApi;
import com.sofn.fyem.sysapi.bean.SysRegionTreeVo;
import com.sofn.fyem.vo.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 放流评价汇总接口
 * @author Administrator
 */
@Service
public class ReleaseEvaluateSummarizeServiceImpl implements ReleaseEvaluateSummarizeService {

    /**
     * 水生物放流Service
     */
    @Autowired
    private BasicProliferationReleaseService basicProliferationReleaseService;

    /**
     * 放流评价指标Service
     */
    @Autowired
    private ReleaseEvaluateIndicatorService releaseEvaluateIndicatorService;

    /**
     * 评价分数Service
     */
    @Autowired
    private EvaluateStandardValueService valueService;

    /**
     * 评价分数历史Service
     */
    @Autowired
    private EvaluateStandardValueHistoryService valueHistoryService;

    @Autowired
    private SysRegionApi sysRegionApi;

    @Override
    public PageUtils<BasicProliferationReleaseVO> getBasicProliferationReleaseListByPage(Map<String, Object> params, int pageNo, int pageSize) {
        PageHelper.offsetPage(pageNo,pageSize);
        List<BasicProliferationReleaseVO> basicProliferationReleaseList = basicProliferationReleaseService.getBasicProliferationReleaseListByQuery(params);
        PageInfo<BasicProliferationReleaseVO> pageInfo = new PageInfo<>(basicProliferationReleaseList);
        return PageUtils.getPageUtils(pageInfo);
    }

    @Override
    public List<BasicProliferationReleaseVO> getBasicProliferationReleaseListByQuery(Map<String, Object> params) {
        return basicProliferationReleaseService.getBasicProliferationReleaseListByQuery(params);
    }

    @Override
    public ReleaseEvaluateSummarizeVo getReleaseEvaluateSummarizeType(String id) {

        ReleaseEvaluateSummarizeVo releaseEvaluateSummarizeVo = getReleaseEvaluateSummarizeVo(id);
        //获取一级指标信息
        List<FirstEvaluateIndicatorVo> firstList = new ArrayList<>();
        Map<String, Object> params = Maps.newHashMap();
        params.put("indicatorType","0");
        params.put("status","0");
        List<String> firstIds = new ArrayList<>();
        List<ReleaseEvaluateIndicator> evaluateIndicatorList01 =
                releaseEvaluateIndicatorService.getReleaseEvaluateIndicatorListByQuery(params);

        if(evaluateIndicatorList01.size()>0){
            evaluateIndicatorList01.forEach(
                    baseData ->{
                        //将一级指标id存放新的idsList
                        firstIds.add(baseData.getId());
                        //获取一级指标vo相关信息
                        FirstEvaluateIndicatorVo firstEvaluateIndicatorVo = new FirstEvaluateIndicatorVo();
                        firstEvaluateIndicatorVo.setFirstId(baseData.getId());
                        firstEvaluateIndicatorVo.setIndicatorName(baseData.getIndicatorName());
                        firstList.add(firstEvaluateIndicatorVo);
                    }
            );
            //获取二级指标信息
            List<SecondEvaluateIndicatorVo> secondList =
                    releaseEvaluateIndicatorService.getSecondEvaluateIndicatorType(firstIds);
            firstList.forEach(
                    firstData ->{
                        List<SecondEvaluateIndicatorVo> collect =
                                Lists.newArrayList(secondList).stream().filter(item -> firstData.getFirstId().equals(item.getParentId())).collect(Collectors.toList());
                        firstData.setSecondEvaluateIndicatorList(collect);
                    }
            );
        }
        releaseEvaluateSummarizeVo.setFirstEvaluateIndicatorList(firstList);

        return releaseEvaluateSummarizeVo;
    }

    @Override
    public ReleaseEvaluateSummarizeVo getReleaseEvaluateSummarizeVo(String id,String belongYear) {
        //获取放流水生物信息并重构结果数据
        ReleaseEvaluateSummarizeVo releaseEvaluateSummarizeVo = getReleaseEvaluateSummarizeVo(id);
        //获取一级指标信息
        List<FirstEvaluateIndicatorVo> firstList = new ArrayList<>();
        Map<String, Object> params = Maps.newHashMap();
        params.put("indicatorType","0");
        params.put("status","0");
        List<String> firstIds = new ArrayList<>();
        List<ReleaseEvaluateIndicator> evaluateIndicatorList01 =
                releaseEvaluateIndicatorService.getReleaseEvaluateIndicatorListByQuery(params);
        if(evaluateIndicatorList01.size()>0){

            evaluateIndicatorList01.forEach(
                    baseData ->{
                        //将一级指标id存放新的idsList
                        firstIds.add(baseData.getId());
                        //获取一级指标vo相关信息
                        FirstEvaluateIndicatorVo firstEvaluateIndicatorVo = new FirstEvaluateIndicatorVo();
                        firstEvaluateIndicatorVo.setFirstId(baseData.getId());
                        firstEvaluateIndicatorVo.setIndicatorName(baseData.getIndicatorName());
                        firstList.add(firstEvaluateIndicatorVo);
                    }
            );
            //获取二级指标信息
            List<SecondEvaluateIndicatorVo> secondList =
                    releaseEvaluateIndicatorService.getSecondEvaluateIndicatorVo(firstIds,belongYear,id);
            firstList.forEach(
                    firstData ->{
                        List<SecondEvaluateIndicatorVo> collect =
                                Lists.newArrayList(secondList).stream().filter(item -> firstData.getFirstId().equals(item.getParentId())).collect(Collectors.toList());
                        firstData.setSecondEvaluateIndicatorList(collect);
                    }
            );
        }
        releaseEvaluateSummarizeVo.setFirstEvaluateIndicatorList(firstList);
        return releaseEvaluateSummarizeVo;
    }

    @Override
    public ReleaseEvaluateSummarizeVo getReleaseEvaluateSummarizeHistory(String id, String belongYear) {
        //获取放流水生物信息并重构结果数据
        ReleaseEvaluateSummarizeVo releaseEvaluateSummarizeVo = getReleaseEvaluateSummarizeVo(id);
        //获取评价分数详情(历史记录)
        Map<String, Object> params = Maps.newHashMap();
        params.put("basicReleaseId",id);
        params.put("belongYear",belongYear);
        List<EvaluateStandardValueHistory> valueHistoryList = valueHistoryService.getValueHistoryByQuery(params);
        if(valueHistoryList.size()>0){

            //存放一级指标ids
            List<String> firstIds = new ArrayList();
            valueHistoryList.forEach(
                    baseData ->{
                        firstIds.add(baseData.getFirstIndicatorId());
                    }
            );
            //去掉重复的一级指标id
            HashSet ids = new HashSet(firstIds);
            firstIds.clear();
            firstIds.addAll(ids);
            //获取一级指标信息
            List<FirstEvaluateIndicatorVo> firstList = new ArrayList<>();

            firstIds.forEach(
                    baseData ->{
                        ReleaseEvaluateIndicator indicator = releaseEvaluateIndicatorService.getReleaseEvaluateIndicatorById(baseData);
                        //获取一级指标vo相关信息
                        FirstEvaluateIndicatorVo firstEvaluateIndicatorVo = new FirstEvaluateIndicatorVo();
                        firstEvaluateIndicatorVo.setFirstId(indicator.getId());
                        firstEvaluateIndicatorVo.setIndicatorName(indicator.getIndicatorName());
                        firstList.add(firstEvaluateIndicatorVo);
                    }
            );

            //获取二级指标信息
            List<SecondEvaluateIndicatorVo> secondList =
                    releaseEvaluateIndicatorService.getSecondEvaluateIndicatorHistory(firstIds,belongYear,id);
            firstList.forEach(
                    firstData ->{
                        List<SecondEvaluateIndicatorVo> collect =
                                Lists.newArrayList(secondList).stream().filter(item -> firstData.getFirstId().equals(item.getParentId())).collect(Collectors.toList());
                        firstData.setSecondEvaluateIndicatorList(collect);
                    }
            );
            releaseEvaluateSummarizeVo.setFirstEvaluateIndicatorList(firstList);
        }

        return releaseEvaluateSummarizeVo;
    }

    @NotNull
    private ReleaseEvaluateSummarizeVo getReleaseEvaluateSummarizeVo(String id) {
        ReleaseEvaluateSummarizeVo releaseEvaluateSummarizeVo = new ReleaseEvaluateSummarizeVo();
        //获取水生物放流信息
        BasicProliferationRelease basicRelease = basicProliferationReleaseService.getBasicProliferationReleaseById(id);
        releaseEvaluateSummarizeVo.setBasicId(basicRelease.getId());
        releaseEvaluateSummarizeVo.setReleaseSite(basicRelease.getReleaseSite());
        releaseEvaluateSummarizeVo.setLongitude(basicRelease.getLongitude());
        releaseEvaluateSummarizeVo.setLatitude(basicRelease.getLatitude());
        releaseEvaluateSummarizeVo.setReleaseTime(basicRelease.getReleaseTime());
        return releaseEvaluateSummarizeVo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addReleaseEvaluateSummarizeVo(EvaluateStandardValueVo evaluateStandardValueVo) {
        //对水生物放流信息进行修改
        String basicId = evaluateStandardValueVo.getBasicId();
        Double releaseEvaluate = evaluateStandardValueVo.getReleaseEvaluate();
        Map<String,Object> baseParams = Maps.newHashMap();
        baseParams.put("id",basicId);
        baseParams.put("releaseEvaluate",releaseEvaluate);
        basicProliferationReleaseService.updateReleaseEvaluate(baseParams);
        //对指标评价信息进行新增
        List<EvaluateStandardValue> evaluateStandardValueList = evaluateStandardValueVo.getEvaluateStandardValueList();
        evaluateStandardValueList.forEach(
                baseData -> baseData.setId(IdUtil.getUUId())
        );
        int i = valueService.batchSaveEvaluateStandardValue(evaluateStandardValueList);
        if(i<=0){
            throw new SofnException("新增评价分数异常,请重新填写!");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateReleaseEvaluateSummarizeVo(EvaluateStandardValueVo evaluateStandardValueVo) {
        //对水生物放流信息进行修改
        String basicId = evaluateStandardValueVo.getBasicId();
        Double releaseEvaluate = evaluateStandardValueVo.getReleaseEvaluate();
        Map<String,Object> baseParams = Maps.newHashMap();
        baseParams.put("id",basicId);
        baseParams.put("releaseEvaluate",releaseEvaluate);
        basicProliferationReleaseService.updateReleaseEvaluate(baseParams);
        Map<String,Object> params = Maps.newHashMap();
        params.put("basicReleaseId",basicId);
        params.put("belongYear",evaluateStandardValueVo.getBelongYear());
        List<EvaluateStandardValue> valueListByQuery = valueService.getEvaluateStandardValueByQuery(params);
        //对指标评价信息进行新增
        //获取前端传递的指标评价分数
        List<EvaluateStandardValue> valueList = evaluateStandardValueVo.getEvaluateStandardValueList();
        //创建一个指标评价分数历史list用于存放历史
        List<EvaluateStandardValueHistory> valueHistoryList = new ArrayList<>();
        //指标评价分数存放id
        valueList.forEach(
                baseData -> {
                    EvaluateStandardValueHistory valueHistory = new EvaluateStandardValueHistory();
                    BeanUtils.copyProperties(baseData,valueHistory);
                    valueHistoryList.add(valueHistory);
                    baseData.setId(IdUtil.getUUId());
                }
        );
        //指标评价历史分数存放id
        valueHistoryList.forEach(
                baseData -> baseData.setId(IdUtil.getUUId())
        );
        if(valueListByQuery.size()>0){

            int del = valueService.batchDeleteEvaluateStandardValue(params);
            int delHistory = valueHistoryService.batchDeleteValueHistory(params);
            if(del <= 0 && delHistory <= 0){
                throw new SofnException("修改评价分数异常,请重新填写!");
            }

            int save = valueService.batchSaveEvaluateStandardValue(valueList);
            int saveHistory = valueHistoryService.batchSaveValueHistory(valueHistoryList);
            if(save <= 0 && saveHistory <= 0){
                throw new SofnException("评价指标分数异常,请重新填写!");
            }
        }else{

            int save = valueService.batchSaveEvaluateStandardValue(valueList);
            int saveHistory = valueHistoryService.batchSaveValueHistory(valueHistoryList);
            if(save <= 0 && saveHistory <= 0){
                throw new SofnException("评价指标分数异常,请重新填写!");
            }
        }
    }

    @Override
    public List<BasicProliferationReleaseExcel> getBasicProliferationReleaseExcel(Map<String, Object> params) {
        List<BasicProliferationReleaseExcel> excelList = new ArrayList<>();
        List<BasicProliferationReleaseVO> basicProliferationReleaseList = basicProliferationReleaseService.getBasicProliferationReleaseListByQuery(params);
        basicProliferationReleaseList.forEach(
                baseData -> {
                    BasicProliferationReleaseExcel basicExcel = new BasicProliferationReleaseExcel();
                    Result<List<SysRegionTreeVo>> parentNode = sysRegionApi.getParentNode(baseData.getCountyId());
                    List<SysRegionTreeVo> data = parentNode.getData();
                    //判断获取地址相关数据是否存在
                    if (data.size() > 0) {
                        //获取省名称
                        SysRegionTreeVo province = data.get(0);
                        String provinceName = province.getRegionName();
                        //获取市名称
                        SysRegionTreeVo city = data.get(1);
                        String cityName = city.getRegionName();
                        //获取区县名称
                        SysRegionTreeVo county = data.get(2);
                        String countyName = county.getRegionName();
                        //拼接市区县详细地址
                        String areaName = provinceName + cityName + countyName;
                        basicExcel.setAreaName(areaName);
                    } else {
                        basicExcel.setAreaName("---");
                    }
                    BeanUtils.copyProperties(baseData, basicExcel);
                    excelList.add(basicExcel);
                }
        );
        return excelList;
    }

}
