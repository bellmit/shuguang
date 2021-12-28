/**
 * Copyright (c) 1998-2021 SOFN Corporation
 * All rights reserved.
 * <p>
 * <p>
 * Created On 2021-01-14 17:18
 */
package com.sofn.ducss.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.sofn.ducss.enums.AuditStatusEnum;
import com.sofn.ducss.enums.Constants;
import com.sofn.ducss.enums.SixRegionEnum;
import com.sofn.ducss.mapper.CountyDataAnalysisMapper;
import com.sofn.ducss.mapper.DataAnalysisAreaMapper;
import com.sofn.ducss.mapper.SysDictionaryMapper;
import com.sofn.ducss.model.*;
import com.sofn.ducss.model.basemodel.Result;
import com.sofn.ducss.service.CountyDataAnalysisService;
import com.sofn.ducss.service.DataAnalysisCityService;
import com.sofn.ducss.service.DataAnalysisProvinceService;
import com.sofn.ducss.service.DataAnalysisSixRegionService;
import com.sofn.ducss.sysapi.SysApi;
import com.sofn.ducss.model.SysDict;
import com.sofn.ducss.util.SysDictUtil;
import com.sofn.ducss.util.SysRegionUtil;
import com.sofn.ducss.vo.AreaRegionCode;
import com.sofn.ducss.vo.DataAnalyVo;
import com.sofn.ducss.vo.StrawUsageVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 县级数据分析实现类
 *
 * @author jiangtao
 * @version 1.0
 **/
@Service
public class CountyDataAnalysisServiceImpl extends ServiceImpl<CountyDataAnalysisMapper, DataAnalysisArea> implements CountyDataAnalysisService {

    @Autowired
    private SysApi sysApi;

    @Autowired
    private CountyDataAnalysisMapper countyDataAnalysisMapper;

    @Autowired
    private DataAnalysisAreaMapper dataAnalysisAreaMapper;

    @Autowired
    private DataAnalysisCityService dataAnalysisCityService;

    @Autowired
    private DataAnalysisProvinceService dataAnalysisProvinceService;

    @Autowired
    private DataAnalysisSixRegionService dataAnalysisSixRegionService;

    @Autowired
    private SysDictionaryMapper sysDictionaryMapper;

    @Override
    public void insertCountyDataAnalysis(String year, String areaCode) {
        //获取县级基本数据
        //秸秆类型
        List<SysDict> dictList = SysDictUtil.getDictList(Constants.DictionaryType.STRAW_TYPE);
        HashMap<String, String> strawTypeMap = new HashMap<>(14);
        for (SysDict sysDict : dictList) {
            strawTypeMap.put(sysDict.getDictcode(), sysDict.getDictname());
        }
        List<String> statues = new ArrayList<>();
        statues.add(AuditStatusEnum.PASSED.getCode());
        statues.add(AuditStatusEnum.REPORTED.getCode());
        //查询秸秆生产量与直接还田量
        List<ProStillDetail> proStillList = countyDataAnalysisMapper.getProStillDetailListByAreaId(year, areaCode, statues);
        //Map<String, ProStillDetail> proStillDetailMap = proStillList.stream().collect(Collectors.toMap(ProStillDetail::getStrawType, Function.identity()));
        // 查询分散利用量
        List<DisperseUtilizeDetail> disList = countyDataAnalysisMapper.selectDetailByAreaId(year, areaCode);
        //转换成map
        Map<String, DisperseUtilizeDetail> disUtilizeMap = disList.stream().collect(Collectors.toMap(DisperseUtilizeDetail::getStrawType, Function.identity()));
        // 市场主体规模利用量
        List<StrawUtilizeDetail> strawUtilizeList = countyDataAnalysisMapper.selectDetailSumByAreaId(year, areaCode);
        //转换成map
        Map<String, StrawUtilizeDetail> strawUtilizeDetailMap = strawUtilizeList.stream().collect(Collectors.toMap(StrawUtilizeDetail::getStrawType, Function.identity()));
        //统计数据
        List<StrawUtilizeSum> proList = new ArrayList<StrawUtilizeSum>();
        //转换为县级中间基础数据
        List<DataAnalysisArea> list = new ArrayList<>();

        //获取省市县名字并拼接
        AreaRegionCode result = SysRegionUtil.getRegionCodeByLastCode(areaCode);
        // String areaName = "/" + result.getProvinceName() + "/" + result.getCityName() + "/" + result.getCountyName();
        String areaName = "/" + result.getCityName() + "/" + result.getCountyName();

        for (ProStillDetail detail : proStillList) {
            // 计算资源量
            detail.calculateResource();
            StrawUtilizeSum sum = new StrawUtilizeSum();
            // 赋值秸秆生产量与直接还田量
            sum.setDucProStillDeatil(detail);
            sum.setYear(year);
            sum.setAreaId(areaCode);

            if (strawTypeMap.containsKey(detail.getStrawType())) {
                sum.setStrawName(strawTypeMap.get(detail.getStrawType()));
            }

            if (disUtilizeMap.containsKey(detail.getStrawType())) {
                // 赋值分散利用量
                sum.setDucDisperseUtilizeDetail(disUtilizeMap.get(detail.getStrawType()));
            }

            if (strawUtilizeDetailMap.containsKey(detail.getStrawType())) {
                // 市场主体明细
                sum.setDucStrawUtilizeDetail(strawUtilizeDetailMap.get(detail.getStrawType()));
            }
            sum.calculateNum2();
            sum.setStrawName(detail.getStrawName());
            proList.add(sum);
        }
        for (StrawUtilizeSum s : proList) {
            DataAnalysisArea area = new DataAnalysisArea();
//            area.setId(IdUtil.getUUId());
            area.setYear(year);
            area.setAreaId(areaCode);
            area.setStrawType(s.getStrawType());
            area.setGrainYield(s.getGrainYield().setScale(10));
            area.setGrassValleyRatio(s.getGrassValleyRatio().setScale(10));
            area.setCollectionRatio(s.getCollectionRatio().setScale(10));
            area.setSeedArea(s.getSeedArea().setScale(10));
            area.setReturnArea(s.getReturnArea().setScale(10));
            area.setExportYield(s.getYieldAllExport().setScale(10));
            area.setTheoryResource(s.getTheoryResource().setScale(10));
            area.setCollectResource(s.getCollectResource().setScale(10));
            area.setMarketEnt(s.getMainTotal().setScale(10));
            area.setFertilizes(s.getMainFertilising().setScale(10));
            area.setFeeds(s.getMainForage().setScale(10));
            area.setFuelleds(s.getMainFuel().setScale(10));
            area.setBaseMats(s.getMainBase().setScale(10));
            area.setMaterializations(s.getMainMaterial().setScale(10));
            area.setReuse(s.getDisperseTotal().setScale(10));
            area.setFertilisingd(s.getDisperseFertilising().setScale(10));
            area.setForaged(s.getDisperseForage().setScale(10));
            area.setFueld(s.getDisperseFuel().setScale(10));
            area.setBased(s.getDisperseBase().setScale(10));
            area.setMateriald(s.getDisperseMaterial().setScale(10));
            area.setReturnResource(s.getReturnResource().setScale(10));
            area.setOther(s.getMainTotalOther().setScale(10));
            area.setFertilize(s.getFertilize().setScale(10));
            area.setFeed(s.getFeed().setScale(10));
            area.setFuelled(s.getFuelled().setScale(10));
            area.setBaseMat(s.getBaseMat().setScale(10));
            area.setMaterialization(s.getMaterialization().setScale(10));
            area.setStrawUtilization(s.getProStrawUtilize().setScale(10));
            area.setTotolRate(s.getComprehensive().setScale(10));
            area.setComprUtilIndex(s.getComprehensiveIndex().setScale(10));
            area.setInduUtilIndex(s.getIndustrializationIndex().setScale(10));
            area.setAreaName(areaName);
            area.setStrawName(s.getStrawName());
            area.setYieldAllNum(s.getYieldAllNum().setScale(10));
            area.setLeaveNumber(area.getReuse().add(area.getMarketEnt()).setScale(10));
            area.setReturnType(s.getReturnType());
            area.setLeavingType(s.getLeavingType());
            area.setTransportAmount(s.getTransportAmount().setScale(10));
            list.add(area);
        }
        // 作物排序
        List<DataAnalysisArea> resultList = Lists.newArrayList();
        for (SysDict sysDict : dictList) {
            String dictcode = sysDict.getDictcode();
            for (DataAnalysisArea area : list) {
                if (dictcode.equals(area.getStrawType())) {
                    resultList.add(area);
                }
            }
        }
        dataAnalysisAreaMapper.insertList(resultList);
    }

    @Override
    public void insertCityDataAnalysis(String year, String areaCode) {
        //查询当前区域下一级区域id
        List<String> areaList = SysRegionUtil.getChildrenRegionIdList(areaCode);
        List<DataAnalysisCity> dataAnalysisCity = countyDataAnalysisMapper.getDataAnalysisCity(year, areaList);
        // String result = sysApi.getSysRegionName(areaCode);
        //获取秸秆类型map集合
        List<SysDict> dictList = SysDictUtil.getDictList(Constants.DictionaryType.STRAW_TYPE);
        HashMap<String, String> strawTypeMap = new HashMap<>(14);
        for (SysDict sysDict : dictList) {
            strawTypeMap.put(sysDict.getDictcode(), sysDict.getDictname());
        }

        //获取省市县名字并拼接
        AreaRegionCode resultName = SysRegionUtil.getRegionCodeByLastCode(areaCode);
        String areaName = "/" + resultName.getProvinceName() + "/" + resultName.getCityName();

        for (DataAnalysisCity city : dataAnalysisCity) {
            city.setCityId(areaCode);
            city.setAreaName(areaName);
            if (strawTypeMap.get(city.getStrawType()) != null) {
                city.setStrawName(strawTypeMap.get(city.getStrawType()));
            }
            city.setYear(year);
        }
        //将新增加的数据插入到city表中=
        dataAnalysisCityService.saveBatch(dataAnalysisCity);
    }

    @Override
    public void insertProvinceDataAndSixRegionAnalysis(String year, String areaCode) {
        //查询当前区域下一级区域id
        List<String> areaList = SysRegionUtil.getChildrenRegionIdList(areaCode);
        List<DataAnalysisProvice> dataAnalysisProvice = countyDataAnalysisMapper.getDataAnalysisProvice(year, areaList);
        String result = sysApi.getSysRegionName(areaCode);
        //获取秸秆类型map集合
        List<SysDict> dictList = SysDictUtil.getDictList(Constants.DictionaryType.STRAW_TYPE);
        HashMap<String, String> strawTypeMap = new HashMap<>(14);

        for (SysDict sysDict : dictList) {
            strawTypeMap.put(sysDict.getDictcode(), sysDict.getDictname());
        }
        String regionName = "";
        if (result != null) {
            regionName = result;
        }
        for (DataAnalysisProvice analysisProvice : dataAnalysisProvice) {
            if (strawTypeMap.get(analysisProvice.getStrawType()) != null) {
                analysisProvice.setStrawName(strawTypeMap.get(analysisProvice.getStrawType()));
            }
            analysisProvice.setProviceId(areaCode);
            analysisProvice.setAreaName("/" + regionName);
            analysisProvice.setYear(year);
        }
        List<DataAnalysisSixArea> sixAreaList = new ArrayList<>();
        //将数据设置到六大区表中
        for (DataAnalysisProvice provice : dataAnalysisProvice) {
            DataAnalysisSixArea sixArea = new DataAnalysisSixArea();
            BeanUtils.copyProperties(provice, sixArea);
            sixArea.setSixAreaId(provice.getProviceId());
            sixArea.setAreaName(SixRegionEnum.getByStrawTypeEnglish(provice.getProviceId()));
            sixAreaList.add(sixArea);
        }
        dataAnalysisProvinceService.saveBatch(dataAnalysisProvice);
        dataAnalysisSixRegionService.insertList(sixAreaList);
    }

    @Override
    public boolean deleCountyDataAnalysis(String year, String areaCode) {
        QueryWrapper<DataAnalysisArea> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("year", year).eq("area_id", areaCode);
        return baseMapper.delete(queryWrapper) > 0;
    }

    @Override
    public boolean deleCityDataAnalysis(String year, String areaCode) {
        QueryWrapper<DataAnalysisCity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("year", year).eq("city_id", areaCode);
        return dataAnalysisCityService.remove(queryWrapper);
    }

    @Override
    public boolean deleProvinceDataAnalysis(String year, String areaCode) {
        QueryWrapper<DataAnalysisProvice> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("year", year).eq("provice_id", areaCode);
        dataAnalysisProvinceService.remove(queryWrapper);
        QueryWrapper<DataAnalysisSixArea> sixAreaQueryWrapper = new QueryWrapper<>();
        sixAreaQueryWrapper.eq("year", year).eq("six_area_id", areaCode);
        return dataAnalysisSixRegionService.remove(sixAreaQueryWrapper);
    }

    @Override
    public List<StrawUsageVo> findDataByAreaIdsAndYears(List<String> areaIds, List<String> years) {
        return dataAnalysisAreaMapper.findDataByAreaIdsAndYears(areaIds, years);
    }

    @Override
    public List<StrawUsageVo> getCountyDataAnalysis(String year, String areaCode) {
        //获取县级基本数据
        //秸秆类型
        List<SysDict> dictList = SysDictUtil.getDictList(Constants.DictionaryType.STRAW_TYPE);
        HashMap<String, String> strawTypeMap = new HashMap<>();
        for (SysDict sysDict : dictList) {
            strawTypeMap.put(sysDict.getDictcode(), sysDict.getDictname());
        }
        List<String> statues = new ArrayList<>();
        /*
        statues.add(AuditStatusEnum.PASSED.getCode());
        statues.add(AuditStatusEnum.REPORTED.getCode());
        */
        //查询秸秆生产量与直接还田量
        List<ProStillDetail> proStillList = countyDataAnalysisMapper.getProStillDetailListByAreaId(year, areaCode, statues);
        //Map<String, ProStillDetail> proStillDetailMap = proStillList.stream().collect(Collectors.toMap(ProStillDetail::getStrawType, Function.identity()));
        // 查询分散利用量
        List<DisperseUtilizeDetail> disList = countyDataAnalysisMapper.selectDetailByAreaId(year, areaCode);
        //转换成map
        Map<String, DisperseUtilizeDetail> disUtilizeMap = disList.stream().collect(Collectors.toMap(DisperseUtilizeDetail::getStrawType, Function.identity()));
        // 市场主体规模利用量
        List<StrawUtilizeDetail> strawUtilizeList = countyDataAnalysisMapper.selectDetailSumByAreaId(year, areaCode);
        //转换成map
        Map<String, StrawUtilizeDetail> strawUtilizeDetailMap = strawUtilizeList.stream().collect(Collectors.toMap(StrawUtilizeDetail::getStrawType, Function.identity()));
        //统计数据
        List<StrawUtilizeSum> proList = new ArrayList<StrawUtilizeSum>();
        // 获取省市县名字并拼接
        // AreaRegionCode result = SysRegionUtil.getRegionCodeByLastCode(areaCode);
        // String areaName = "/" + result.getProvinceName() + "/" + result.getCityName() + "/" + result.getCountyName();
        // String areaName = "/" + result.getCityName() + "/" + result.getCountyName();

        for (ProStillDetail detail : proStillList) {
            // 计算资源量
            detail.calculateResource();
            StrawUtilizeSum sum = new StrawUtilizeSum();
            // 赋值秸秆生产量与直接还田量
            sum.setDucProStillDeatil(detail);
            sum.setYear(year);
            sum.setAreaId(areaCode);

            if (strawTypeMap.containsKey(detail.getStrawType())) {
                sum.setStrawName(strawTypeMap.get(detail.getStrawType()));
            }

            if (disUtilizeMap.containsKey(detail.getStrawType())) {
                // 赋值分散利用量
                sum.setDucDisperseUtilizeDetail(disUtilizeMap.get(detail.getStrawType()));
            }

            if (strawUtilizeDetailMap.containsKey(detail.getStrawType())) {
                // 市场主体明细
                sum.setDucStrawUtilizeDetail(strawUtilizeDetailMap.get(detail.getStrawType()));
            }
            sum.calculateNum2();
            sum.setStrawName(detail.getStrawName());
            proList.add(sum);
        }
        List<StrawUsageVo> usageVoList = Lists.newArrayList();
        for (StrawUtilizeSum sum : proList) {
            StrawUsageVo vo = new StrawUsageVo();
            vo.setStrawType(sum.getStrawType());
            vo.setStrawName(sum.getStrawName());
            vo.setSeedArea(sum.getSeedArea().setScale(10));
            vo.setTheoryResource(sum.getTheoryResource().setScale(10));
            vo.setReturnResource(sum.getReturnResource().setScale(10));
            vo.setLeaveNumber(sum.getDisperseTotal().add(sum.getMainTotal()).setScale(10));
            vo.setTotolRate(sum.getComprehensive().setScale(10));
            vo.setReturnType(sum.getReturnType());
            vo.setLeavingType(sum.getLeavingType());
            vo.setTransportAmount(sum.getTransportAmount().setScale(10));
            vo.setCollectResource(sum.getCollectResource().setScale(10));
            vo.setStrawUtilization(sum.getProStrawUtilize().setScale(10));
            vo.setFertilize(sum.getFertilize().setScale(10));
            vo.setFeed(sum.getFeed().setScale(10));
            vo.setFuelled(sum.getFuelled().setScale(10));
            vo.setBaseMat(sum.getBaseMat().setScale(10));
            vo.setMaterialization(sum.getMaterialization().setScale(10));
            usageVoList.add(vo);
        }
        // 作物排序
        List<StrawUsageVo> resultList = Lists.newArrayList();
        for (SysDict sysDict : dictList) {
            String dictcode = sysDict.getDictcode();
            for (StrawUsageVo vo : usageVoList) {
                if (dictcode.equals(vo.getStrawType())) {
                    resultList.add(vo);
                }
            }
        }
        return resultList;
    }
}
