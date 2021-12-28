/**
 * Copyright (c) 1998-2020 SOFN Corporation
 * All rights reserved.
 * <p>
 * <p>
 * Created On 2020-07-29 10:58
 */
package com.sofn.ducss.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.sofn.ducss.enums.*;
import com.sofn.ducss.exception.SofnException;
import com.sofn.ducss.map.AdPointData;
import com.sofn.ducss.map.MapViewData;
import com.sofn.ducss.mapper.*;
import com.sofn.ducss.model.ProStillDetail;
import com.sofn.ducss.model.SysOrganization;
import com.sofn.ducss.model.basemodel.Result;
import com.sofn.ducss.model.StrawUtilizeSum;
import com.sofn.ducss.service.DateShowService;
import com.sofn.ducss.service.HomePageService;
import com.sofn.ducss.sysapi.SysApi;
import com.sofn.ducss.model.SysDict;
import com.sofn.ducss.vo.SysRegionTreeVo;
import com.sofn.ducss.util.SysDictUtil;
import com.sofn.ducss.util.SysRegionUtil;
import com.sofn.ducss.util.IdUtil;
import com.sofn.ducss.util.JsonUtils;
import com.sofn.ducss.util.PageUtils;
import com.sofn.ducss.util.UserUtil;
import com.sofn.ducss.vo.DateShow.*;
import com.sofn.ducss.vo.StrawUtilizeSumResVo;
import com.sofn.ducss.vo.homePage.DataArea;
import com.sofn.ducss.vo.param.MaterialUtilizationPageParam;
import org.apache.commons.collections4.CollectionUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 数据展示实现类
 *
 * @author jiangtao
 * @version 1.0
 **/
@Service
public class DateShowServiceImpl implements DateShowService {

    @Autowired
    private SysApi sysApi;

    @Autowired
    private DateShowMapper dateShowMapper;

    @Autowired
    private HomePageService homePageService;

    @Autowired
    private StrawUtilizeSumMapper sumMapper;

    @Autowired
    private CollectFlowMapper collectFlowMapper;

    @Autowired
    private SyncSysRegionMapper syncSysRegionMapper;

    @Autowired
    private ProStillDetailMapper proStillDetailMapper;

    @Override
    public List<ColumnLineVo> getColumnLine(String year, String type, String administrativeLevel, String areaCode) {
        Boolean b = checkOrgLevelByUser(administrativeLevel);
        //boolean bole = checkIsReport(year, administrativeLevel, areaCode);
        if (b) {
            //根据类型不同,组装不同的查询年份list
            List<String> listYear = new ArrayList<>();
            if (ColumnLineEnum.FIVEYERS.getCode().equals(type)) {
                for (int i = 0; i < 5; i++) {
                    listYear.add(Integer.toString(Integer.parseInt(year) - i));
                }
            } else {
                for (int i = 0; i < 10; i++) {
                    listYear.add(Integer.toString(Integer.parseInt(year) - i));
                }
            }
            //获取当前区域下一级areacode
            List<SysRegionTreeVo> regionTree = sysApi.getSysRegionTreeById(areaCode, "ducss");
            List<String> areaCodeList = new ArrayList<>();
            //获取特殊区域数据
            List<SysRegionTreeVo> listRegion = getSpecialAreaIds(administrativeLevel, areaCode);
            if (listRegion.size() == 0) {
                if (regionTree.size() > 0) {
                    for (SysRegionTreeVo treeDatum : regionTree) {
                        areaCodeList.add(treeDatum.getRegionCode());
                    }
                }
            } else {
                listRegion.forEach(sysRegionTreeVo -> {
                    areaCodeList.add(sysRegionTreeVo.getRegionCode());
                });
            }
            List<String> years = new ArrayList<>();
            //判断近几年哪些年满足数据审核条件
            listYear.forEach(s -> {
                boolean bole = checkIsReport(s, administrativeLevel, areaCode);
                if (bole) {
                    years.add(s);
                }
            });
            if (years.size() == 0) {
                return null;
            }
            HashMap<String, Object> map = new HashMap<>(12);
            //根据行政级别不同查询不同数据
            map.put("areaCodes", areaCodeList);
            map.put("years", years);
            map.put("status", Constants.ExamineState.PASSED);
            List<StrawUtilizeSumResVo> columnLineData = dateShowMapper.getColumnLineData(map);
            List<ColumnLineVo> list = new ArrayList<>();
            for (StrawUtilizeSumResVo columnLineDatum : columnLineData) {
                ColumnLineVo lineVo = new ColumnLineVo();
                // 综合利用率 =((本县秸秆利用量-收购外县的秸秆总量) + 调出秸秆量)/可收集量
                if (columnLineDatum.getCollectResource().compareTo(BigDecimal.ZERO) != 0) {
                    BigDecimal comprehensive = columnLineDatum.getProStrawUtilize().subtract(columnLineDatum.getMainTotalOther())
                            .add(columnLineDatum.getExportYieldTotal())
                            .multiply(new BigDecimal(100))
                            .divide(columnLineDatum.getCollectResource(), 10, RoundingMode.HALF_UP);
                    lineVo.setComprehensive(comprehensive.toString());
                } else {
                    lineVo.setComprehensive(BigDecimal.ZERO.toString());
                }
                lineVo.setYear(columnLineDatum.getYear());
                lineVo.setProStrawUtilize(columnLineDatum.getProStrawUtilize().subtract(columnLineDatum.getMainTotalOther())
                        .add(columnLineDatum.getYieldAllExport()).toString());
                list.add(lineVo);
            }
            return list;
        }
        return null;
    }

    /**
     * 校验用户是否操作的是本级别下的数据
     *
     * @param year                年份
     * @param administrativeLevel 等级
     * @param areaCode            所选区域id
     */
    private boolean checkIsReport(String year, String administrativeLevel, String areaCode) {
        //根据当前用户等级,查询下级数据是否已经审核通过
        //获取当前登录用户等级
        String organizationInfo = UserUtil.getLoginUserOrganizationInfo();
        SysOrganization orgData = JsonUtils.json2obj(organizationInfo, SysOrganization.class);
        if (RegionLevel.MINISTRY.getCode().equals(orgData.getOrganizationLevel())) {
            //如果当前查询的是省级,传的是国家的areaCode
            if ("100000".equals(areaCode)) {
                return true;
            }
            //获取当前上报完成数据
            Map<String, Object> map = new HashMap<>(12);
            map.put("year", year);
            map.put("status", Constants.ExamineState.PASSED);
            if (AdministrativeLevelEnum.CITY.getCode().equals(administrativeLevel)) {
                //查询当前省数据是否已经被审核通过
                map.put("areaCode", areaCode);
                Integer molecular = dateShowMapper.getCompleteCountByCondition(map);
                return molecular > 0;
            }
            if (AdministrativeLevelEnum.COUNTY.getCode().equals(administrativeLevel)) {
                //查询当前省数据是否已经被审核通过
                List<SysRegionTreeVo> result = sysApi.getParentNodeByRegionCode(areaCode);
                if (result.size() > 0) {
                    SysRegionTreeVo sysRegionTreeVo = result.get(0);
                    map.put("areaCode", sysRegionTreeVo.getRegionCode());
                    //分子
                    Integer molecular = dateShowMapper.getCompleteCountByCondition(map);
                    return molecular > 0;
                }
            }
        }
        if (RegionLevel.PROVINCE.getCode().equals(orgData.getOrganizationLevel())) {
            //如果当前查询的是市级,传的是国家的areaCode
            if ("100000".equals(areaCode)) {
                return false;
            }
            //获取当前上报完成数据
            Map<String, Object> map = new HashMap<>(12);
            map.put("year", year);
            map.put("status", Constants.ExamineState.PASSED);
            if (AdministrativeLevelEnum.CITY.getCode().equals(administrativeLevel)) {
                //查询当前市数据是否已经被审核通过
                List<SysRegionTreeVo> result = sysApi.getParentNodeByRegionCode(areaCode);
                if (result.size() > 0) {
                    //判断当前所选区域是否为本省
                    List<String> list = JSONArray.parseArray(orgData.getRegioncode(), String.class);
                    //判断当前所选区域是否为本省,不是直接返回false
                    if (!result.get(0).getRegionCode().equals(list.get(0))) {
                        return false;
                    }
                    // SysRegionTreeVo sysRegionTreeVo = result.get(0);
                    //map.put("areaCode", sysRegionTreeVo.getRegionCode());
                    //查看是否有市级数据审核通过
                    //获取当前区域下一级areacode
                    List<SysRegionTreeVo> regionTree = sysApi.getSysRegionTreeById(areaCode, "ducss");
                    List<String> areaCodeList = new ArrayList<>();
                    if (regionTree.size() > 0) {
                        for (SysRegionTreeVo treeDatum : regionTree) {
                            areaCodeList.add(treeDatum.getRegionCode());
                        }
                    }
                    map.put("areaCodes", areaCodeList);
                    Integer molecular = dateShowMapper.getCompleteCountByCondition(map);
                    return molecular > 0;
                }
            }
            if (AdministrativeLevelEnum.COUNTY.getCode().equals(administrativeLevel)) {
                //查询当前省数据是否已经被审核通过
                List<SysRegionTreeVo> result = sysApi.getParentNodeByRegionCode(areaCode);
                if (result.size() > 0) {
                    //判断当前所选区域是否为本省
                    List<String> list = JSONArray.parseArray(orgData.getRegioncode(), String.class);
                    if (!result.get(0).getRegionCode().equals(list.get(0))) {
                        return false;
                    }
                    // SysRegionTreeVo sysRegionTreeVo = result.get(0);
                    //查看是否有市级数据审核通过
                    //获取当前区域下一级areacode
                    List<SysRegionTreeVo> regionTree = sysApi.getSysRegionTreeById(result.get(0).getRegionCode(), "ducss");
                    List<String> areaCodeList = new ArrayList<>();
                    if (regionTree.size() > 0) {
                        for (SysRegionTreeVo treeDatum : regionTree) {
                            areaCodeList.add(treeDatum.getRegionCode());
                        }
                    }
                    map.put("areaCodes", areaCodeList);
                    Integer molecular = dateShowMapper.getCompleteCountByCondition(map);
                    return molecular > 0;
                }
            }
        }
        //登录人员是市级
        if (RegionLevel.CITY.getCode().equals(orgData.getOrganizationLevel())) {
            if (AdministrativeLevelEnum.COUNTY.getCode().equals(administrativeLevel)) {
                //查询当前区域id是否为此区域下id
                // 判断当前所选区域是否为本省
                List<String> list = JSONArray.parseArray(orgData.getRegioncode(), String.class);
                return areaCode.equals(list.get(1));
            }
            return false;
        }
        return false;
    }

    private Boolean checkOrgLevelByUser(String administrativeLevel) {
        //获取当前登录用户等级
        String organizationInfo = UserUtil.getLoginUserOrganizationInfo();
        SysOrganization orgData = JsonUtils.json2obj(organizationInfo, SysOrganization.class);
        //若根据当前等级不同查看权限不同
        List<String> list = new ArrayList<>();
        //部级查看省市县
        if (RegionLevel.MINISTRY.getCode().equals(orgData.getOrganizationLevel())) {
            list.add(AdministrativeLevelEnum.PROVINCE.getCode());
            list.add(AdministrativeLevelEnum.CITY.getCode());
            list.add(AdministrativeLevelEnum.COUNTY.getCode());
        } else if (RegionLevel.PROVINCE.getCode().equals(orgData.getOrganizationLevel())) {
            list.add(AdministrativeLevelEnum.CITY.getCode());
            list.add(AdministrativeLevelEnum.COUNTY.getCode());
        } else {
            list.add(AdministrativeLevelEnum.COUNTY.getCode());
        }
        return list.contains(administrativeLevel);
    }

    @Override
    public InstrumentAndRoketVo getInstrumentAndRoket(String year, String administrativeLevel, String areaCode) {
        Boolean b = checkOrgLevelByUser(administrativeLevel);
        boolean bole = checkIsReport(year, administrativeLevel, areaCode);
        if (b && bole) {
            InstrumentAndRoketVo roketVo = new InstrumentAndRoketVo();
            //查询当前区域下有多少个子区域作为分母
            List<SysRegionTreeVo> regionTree = sysApi.getSysRegionTreeById(areaCode, "ducss");
            if (CollectionUtils.isNotEmpty(regionTree)) {
                List<String> areaCodes = new ArrayList<>();
                //获取特殊区域数据
                List<SysRegionTreeVo> listRegion = getSpecialAreaIds(administrativeLevel, areaCode);
                if (listRegion.size() == 0) {
                    //获取当前子区域areacode集合
                    regionTree.forEach(sysRegionTreeVo -> {
                        areaCodes.add(sysRegionTreeVo.getRegionCode());
                    });
                } else {
                    listRegion.forEach(sysRegionTreeVo -> {
                        areaCodes.add(sysRegionTreeVo.getRegionCode());
                    });
                }
                //获取当前上报完成数据
                Map<String, Object> map = new HashMap<>(12);
                map.put("areaCodes", areaCodes);
                map.put("year", year);
                map.put("status", Constants.ExamineState.PASSED);
                //分子
                Integer molecular = dateShowMapper.getCompleteCountByCondition(map);
                BigDecimal decimal = new BigDecimal(Integer.toString(molecular));
                BigDecimal bigDecimal = new BigDecimal("0.00");
                if (listRegion.size() == 0) {
                    bigDecimal = new BigDecimal(Integer.toString(regionTree.size()));
                } else {
                    bigDecimal = new BigDecimal(Integer.toString(listRegion.size()));
                }
                BigDecimal num = decimal.divide(bigDecimal, 2, BigDecimal.ROUND_HALF_UP);
                roketVo.setInstrumentNum(num.toString());
                //获取今年利用率,利用量及利用可收集量
                List<String> years = new ArrayList<>();
                years.add(year);
           /* List<String> areaCodeList = new ArrayList<>();
            if (regionTree.getData().size() > 0) {
                for (SysRegionTreeVo treeDatum : regionTree.getData()) {
                    areaCodeList.add(treeDatum.getRegionCode());
                }
            }*/
                //获取今年数据
                RocketVo rocketNow = getStrawUtilizeByYear(map, years, areaCodes);
                //获取去年数据
                years.clear();
                years.add((String.valueOf(Integer.parseInt(year) - 1)));
                RocketVo rocketBefore = getStrawUtilizeByYear(map, years, areaCodes);
                List<RocketVo> list = new ArrayList<>();
                list.add(rocketBefore);
                list.add(rocketNow);
                roketVo.setList(list);
                return roketVo;
            }
        } else {
            return null;
        }
        throw new SofnException("数据异常");
    }

    /**
     * 获取历年利用率,利用量及利用可收集量
     *
     * @param years        当前起始年份
     * @param areaCodeList 区域id
     * @param map          map
     * @return boolean 布尔类型
     */
    private RocketVo getStrawUtilizeByYear(Map<String, Object> map, List<String> years, List<String> areaCodeList) {
        map.clear();
        map.put("years", years);
        map.put("areaCodes", areaCodeList);
        List<StrawUtilizeSumResVo> columnLineData = dateShowMapper.getColumnLineData(map);
        //获取去年今年利用率,利用量及利用可收集量
        RocketVo rocketVo = new RocketVo();
        columnLineData.forEach(strawUtilizeSumResVo -> {
            // 综合利用率 =((本县秸秆利用量-收购外县的秸秆总量) + 调出秸秆量)/可收集量
            if (strawUtilizeSumResVo.getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                rocketVo.setComprehensive(BigDecimal.ZERO.toString());
            } else {
                BigDecimal comprehensive = strawUtilizeSumResVo.getProStrawUtilize().subtract(strawUtilizeSumResVo.getMainTotalOther())
                        .add(strawUtilizeSumResVo.getExportYieldTotal())
                        .multiply(new BigDecimal(100))
                        .divide(strawUtilizeSumResVo.getCollectResource(), 10, RoundingMode.HALF_UP);
                rocketVo.setComprehensive(comprehensive.toString());
            }
            rocketVo.setCollectResource(strawUtilizeSumResVo.getCollectResource().toString());
            rocketVo.setProStrawUtilize(strawUtilizeSumResVo.getProStrawUtilize().subtract(strawUtilizeSumResVo.getMainTotalOther())
                    .add(strawUtilizeSumResVo.getYieldAllExport()).toString());
        });
        rocketVo.setYear(years.get(0));
        return rocketVo;
    }

    @Override
    public FiveMaterialVO getFiveMaterialVO(String year, String administrativeLevel, String areaCode, String searchStr) {
        Boolean b = checkOrgLevelByUser(administrativeLevel);
        boolean bole = checkIsReport(year, administrativeLevel, areaCode);
        if (b && bole) {
            FiveMaterialVO fiveMaterialVO = new FiveMaterialVO();
            //查询当前区域下有多少个子区域
            List<SysRegionTreeVo> regionTree = sysApi.getSysRegionTreeById(areaCode, "ducss");
            if (CollectionUtils.isNotEmpty(regionTree)) {
                List<String> areaCodes = new ArrayList<>();
                //获取当前子区域areacode集合
                //获取特殊区域数据
                List<SysRegionTreeVo> listRegion = getSpecialAreaIds(administrativeLevel, areaCode);
                if (listRegion.size() == 0) {
                    regionTree.forEach(sysRegionTreeVo -> {
                        areaCodes.add(sysRegionTreeVo.getRegionCode());
                    });
                } else {
                    listRegion.forEach(sysRegionTreeVo -> {
                        areaCodes.add(sysRegionTreeVo.getRegionCode());
                    });
                }
                //根据行政级别查询主体及五料化数据
                Map<String, Object> map = new HashMap<>(12);
                map.put("areaCodes", areaCodes);
                map.put("year", year);
                map.put("status", Constants.ExamineState.PASSED);
                if (searchStr == null) {
                    List<StrawUtilizeSumResVo> sumResVoList = dateShowMapper.getColumnLineData(map);
                    sumResVoList.forEach(strawUtilizeSumResVo -> {
                        fiveMaterialVO.setFertilising((strawUtilizeSumResVo.getMainFertilising().add(strawUtilizeSumResVo.getDisperseFertilising()).add(strawUtilizeSumResVo.getBase())));
                        fiveMaterialVO.setForage(strawUtilizeSumResVo.getForage().add(strawUtilizeSumResVo.getDisperseForage()));
                        fiveMaterialVO.setFuel(strawUtilizeSumResVo.getFuel().add(strawUtilizeSumResVo.getDisperseFuel()));
                        fiveMaterialVO.setBase(strawUtilizeSumResVo.getBase().add(strawUtilizeSumResVo.getDisperseBase()));
                        fiveMaterialVO.setMaterial(strawUtilizeSumResVo.getMaterial().add(strawUtilizeSumResVo.getDisperseMaterial()));
                    });
                } else {
                    map.put("searchStr", searchStr);
                    List<StrawUtilizeSum> otherProduction = dateShowMapper.getOtherProduction(map);
                    otherProduction.forEach(strawUtilizeSum -> {
                        fiveMaterialVO.setFertilising(strawUtilizeSum.getMainFertilising().add(strawUtilizeSum.getDisperseFertilising().add(strawUtilizeSum.getProStillField())));
                        fiveMaterialVO.setForage(strawUtilizeSum.getMainForage().add(strawUtilizeSum.getDisperseForage()));
                        fiveMaterialVO.setFuel(strawUtilizeSum.getMainFuel().add(strawUtilizeSum.getDisperseFuel()));
                        fiveMaterialVO.setMaterial(strawUtilizeSum.getMainMaterial().add(strawUtilizeSum.getDisperseMaterial()));
                        fiveMaterialVO.setBase(strawUtilizeSum.getMainBase().add(strawUtilizeSum.getDisperseBase()));
                    });
                }
                return fiveMaterialVO;
            }
        }
        return null;
    }

    @Override
    public PageUtils<MaterialUtilizationVo> getMaterialInfo(MaterialUtilizationPageParam pageParam) {
        Boolean b = checkOrgLevelByUser(pageParam.getAdministrativeLevel());
        if (b) {
            //根据当前行政等级获取数据
            MaterialUtilizationVo utilizationVo = new MaterialUtilizationVo();
            //查询当前区域下有多少个子区域
            List<SysRegionTreeVo> regionTree = sysApi.getSysRegionTreeById(pageParam.getAreaCode(), "ducss");
            if (CollectionUtils.isNotEmpty(regionTree)) {
                List<String> areaCodes = new ArrayList<>();
                //获取当前子区域areacode集合
                regionTree.forEach(sysRegionTreeVo -> {
                    areaCodes.add(sysRegionTreeVo.getRegionCode());
                });
                //根据行政级别查询主体及五料化数据
                Map<String, Object> map = new HashMap<>(12);
                map.put("status", Constants.ExamineState.PASSED);
                map.put("areaCodes", areaCodes);
                map.put("year", pageParam.getYear());
                map.put("searchStr", pageParam.getSearchStr());
                //获取符合条件数据
                PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());
                List<MaterialUtilizationVo> utilizationVoList = dateShowMapper.getMaterialInfo(map);
                //获取排序字段及排序类型
                String orderField = pageParam.getOrderField();
                String sortOrder = pageParam.getSortOrder();
                //如果排序字段是搜索字段
                if (orderField.equals(pageParam.getSearchStr())) {
                    if ("1".equals(sortOrder)) {
                        utilizationVoList.sort(new Comparator<MaterialUtilizationVo>() {
                            @Override
                            public int compare(MaterialUtilizationVo o1, MaterialUtilizationVo o2) {
                                return (o1.getMaterial().subtract(o2.getMaterial()).intValue());
                            }
                        });
                    }
                    if ("2".equals(sortOrder)) {
                        utilizationVoList.sort(new Comparator<MaterialUtilizationVo>() {
                            @Override
                            public int compare(MaterialUtilizationVo o1, MaterialUtilizationVo o2) {
                                return (o2.getMaterial().subtract(o1.getMaterial()).intValue());
                            }
                        });
                    }
                }
                //如果排序字段是调入量字段
                if ("other".equals(orderField)) {
                    if ("1".equals(sortOrder)) {
                        utilizationVoList.sort(new Comparator<MaterialUtilizationVo>() {
                            @Override
                            public int compare(MaterialUtilizationVo o1, MaterialUtilizationVo o2) {
                                return (o1.getOther().subtract(o2.getOther()).intValue());
                            }
                        });
                    }
                    if ("2".equals(sortOrder)) {
                        utilizationVoList.sort(new Comparator<MaterialUtilizationVo>() {
                            @Override
                            public int compare(MaterialUtilizationVo o1, MaterialUtilizationVo o2) {
                                return (o2.getOther().subtract(o1.getOther()).intValue());
                            }
                        });
                    }
                }
                //如果排序字段是总量
                if ("totalMaterial".equals(orderField)) {
                    if ("1".equals(sortOrder)) {
                        utilizationVoList.sort(new Comparator<MaterialUtilizationVo>() {
                            @Override
                            public int compare(MaterialUtilizationVo o1, MaterialUtilizationVo o2) {
                                return (o1.getTotalMaterial().subtract(o2.getTotalMaterial()).intValue());
                            }
                        });
                    }
                    if ("2".equals(sortOrder)) {
                        utilizationVoList.sort(new Comparator<MaterialUtilizationVo>() {
                            @Override
                            public int compare(MaterialUtilizationVo o1, MaterialUtilizationVo o2) {
                                return (o2.getTotalMaterial().subtract(o1.getTotalMaterial()).intValue());
                            }
                        });
                    }
                }
                PageInfo<MaterialUtilizationVo> pageInfo = new PageInfo<>(utilizationVoList);
                return PageUtils.getPageUtils(pageInfo);
            }
        }
        throw new SofnException("数据异常");
    }

    @Override
    public List<HistogramVo> getHistogramByCondition(String year, String areaCode, String searchStr, String administrativeLevel) {
        Boolean b = checkOrgLevelByUser(administrativeLevel);
        boolean bole = checkIsReport(year, administrativeLevel, areaCode);
        if (b && bole) {
            List<SysRegionTreeVo> regionTree = sysApi.getSysRegionTreeById(areaCode, "ducss");
            if (CollectionUtils.isNotEmpty(regionTree)) {
                //获取特殊区域数据
                List<SysRegionTreeVo> listRegion = getSpecialAreaIds(administrativeLevel, areaCode);
                List<String> areaCodes = new ArrayList<>();
                if (listRegion.size() == 0) {
                    //获取当前子区域areacode集合
                    regionTree.forEach(sysRegionTreeVo -> {
                        areaCodes.add(sysRegionTreeVo.getRegionCode());
                    });
                } else {
                    listRegion.forEach(sysRegionTreeVo -> {
                        areaCodes.add(sysRegionTreeVo.getRegionCode());
                    });
                }
                Map<String, Object> map = new HashMap<>();
                map.put("year", year);
                map.put("areaCodes", areaCodes);
                map.put("status", Constants.ExamineState.PASSED);
                //获取可收集量,利用量,利用率查询
                List<StrawUtilizeSum> otherProduction = dateShowMapper.getOtherProduction(map);
                List<HistogramVo> list = new ArrayList<>();
                otherProduction.forEach(strawUtilizeSum -> {
                    HistogramVo histogramVo = new HistogramVo();
                    histogramVo.setStrawType(strawUtilizeSum.getStrawType());
                    histogramVo.setCollectResource(strawUtilizeSum.getCollectResource());
                    histogramVo.setProStrawUtilize(strawUtilizeSum.getProStrawUtilize());
                    histogramVo.setComprehensive(strawUtilizeSum.getComprehensive());
                    histogramVo.setGrainYield(strawUtilizeSum.getGrainYield());
                    list.add(histogramVo);
                });
                return list;
            }
            return null;
        }
        return null;
    }

    @Override
    public MapViewData getMapViewData(String year, String administrativeLevel, String areaCode) {
        Boolean b = checkOrgLevelByUser(administrativeLevel);
        boolean bole = checkIsReport(year, administrativeLevel, areaCode);
        if (b && bole) {
            MapViewData mapViewData = new MapViewData();
            List<SysRegionTreeVo> regionTree = sysApi.getSysRegionTreeById(areaCode, "ducss");
            if (CollectionUtils.isNotEmpty(regionTree)) {
                //获取特殊区域数据
                List<SysRegionTreeVo> list = getSpecialAreaIds(administrativeLevel, areaCode);
                //区域map集合
                Map<String, SysRegionTreeVo> hashMap = new HashMap<>();
                ArrayList<String> areaCodes = new ArrayList<>();
                if (list.size() == 0) {
                    //将区域集合转为map集合
                    regionTree.forEach(sysRegionTreeVo -> {
                        areaCodes.add(sysRegionTreeVo.getRegionCode());
                        hashMap.put(sysRegionTreeVo.getId(), sysRegionTreeVo);
                    });
                } else {
                    list.forEach(sysRegionTreeVo -> {
                        areaCodes.add(sysRegionTreeVo.getRegionCode());
                        hashMap.put(sysRegionTreeVo.getId(), sysRegionTreeVo);
                    });
                }
                //查询信息
                Map<String, Object> map = new HashMap<>(12);
                map.put("year", year);
                map.put("areaCodes", areaCodes);
                map.put("status", Constants.ExamineState.PASSED);
                List<StrawUtilizeSumResVo> dataByAreaCode = dateShowMapper.getDataByAreaCode(map);
                //处理数据
                List<AdPointData> pointDataArrayList = new ArrayList<>();
                dataByAreaCode.forEach(strawUtilizeSumResVo -> {
                    AdPointData pointData = new AdPointData();
                    if (hashMap.get(strawUtilizeSumResVo.getAreaId()) != null) {
                        SysRegionTreeVo regionTreeVo = hashMap.get(strawUtilizeSumResVo.getAreaId());
                        pointData.setLatitude(regionTreeVo.getLatitude());
                        pointData.setLongitude(regionTreeVo.getLongitude());
                        BigDecimal comprehensive = new BigDecimal(0);
                        // 综合利用率 =((本县秸秆利用量-收购外县的秸秆总量) + 调出秸秆量)/可收集量
                        if (strawUtilizeSumResVo.getCollectResource().compareTo(BigDecimal.ZERO) != 0) {
                            comprehensive = strawUtilizeSumResVo.getProStrawUtilize().subtract(strawUtilizeSumResVo.getMainTotalOther())
                                    .add(strawUtilizeSumResVo.getExportYieldTotal())
                                    .multiply(new BigDecimal(100))
                                    .divide(strawUtilizeSumResVo.getCollectResource(), 10, RoundingMode.HALF_UP);
                        }
                        pointData.setIndexValue(comprehensive.toString());
                        Map<String, Object> indexInfo = new HashMap<>();
                        indexInfo.put("name", regionTreeVo.getRegionName());
                        pointData.setIndexInfo(indexInfo);
                    }
                    pointDataArrayList.add(pointData);
                });
                HashMap<String, List<AdPointData>> listHashMap = new HashMap<>();
                listHashMap.put("adPointDataList", pointDataArrayList);
                mapViewData.setAdPointDataList(listHashMap);
                mapViewData.setAdLevel(administrativeLevel);
                mapViewData.setViewType("point");
                return mapViewData;
            }
            return null;
        }
        return null;
    }

    public List<SysRegionTreeVo> getSpecialAreaIds(String administrativeLevel, String areaCode) {
        List<SysRegionTreeVo> list = new ArrayList<>();
        //注释掉,数据展示1.0用
       /* if (!AdministrativeLevelEnum.CITY.getCode().equals(administrativeLevel)){
            return list;
        }*/
        if (!RegionLevel.PROVINCE.getCode().equals(administrativeLevel)) {
            return list;
        }
        ArrayList<String> containsList = Lists.newArrayList("110000", "120000", "310000", "500000");
        //特殊处理直辖市
        if (containsList.contains(areaCode)) {
            SysRegionTreeVo result = sysApi.getSysRegionTree(null, areaCode, null, null, null, Constants.APPID, null);
            if (result != null) {
                if (result.getChildren().size() > 0) {
                    result.getChildren().forEach(sysRegionTreeVo -> {
                        List<SysRegionTreeVo> children = sysRegionTreeVo.getChildren();
                        children.forEach(sysRegionCode -> {
                            if (sysRegionCode.getChildren().size() > 0) {
                                for (SysRegionTreeVo child : sysRegionCode.getChildren()) {
                                    list.add(child);
                                }
                            }
                        });
                    });
                }
            }
            return list;
        }
        return list;
    }

    /**
     * 校验用户是否操作的是本级别区域下的区域
     *
     * @param year                年份
     * @param administrativeLevel 等级
     * @param areaCode            所选区域id
     */
    private boolean checkIsReportInData(String year, String administrativeLevel, String areaCode) {
        //根据当前用户等级,判断下级是否展示数据
        String organizationInfo = UserUtil.getLoginUserOrganizationInfo();
        SysOrganization orgData = JsonUtils.json2obj(organizationInfo, SysOrganization.class);
        Map<String, Object> map = new HashMap<>(12);
        ArrayList<String> statues = new ArrayList<>();
        statues.add(AuditStatusEnum.PASSED.getCode());
        statues.add(AuditStatusEnum.REPORTED.getCode());
        statues.add(AuditStatusEnum.READ.getCode());
        //登录用户为部级,所以区域均可显示
        if (RegionLevel.MINISTRY.getCode().equals(orgData.getOrganizationLevel())) {
            if (Constants.ZHONGGUO_AREA_ID.equals(areaCode) || RegionLevel.MINISTRY.getCode().equals(administrativeLevel)) {
                return true;
            }
            //获取当前上报完成数据
            map.put("year", year);
            map.put("statues", statues);
            if (RegionLevel.PROVINCE.getCode().equals(administrativeLevel)) {
                //查询当前省数据是否已经被审核通过
                map.put("areaCode", areaCode);
                Integer molecular = dateShowMapper.getAuditNumByCondition(map);
                return molecular > 0;
            }
            if (RegionLevel.CITY.getCode().equals(administrativeLevel)) {
                //查询当前省数据是否已经被审核通过
                List<SysRegionTreeVo> result = sysApi.getParentNodeByRegionCode(areaCode);
                if (CollectionUtils.isNotEmpty(result)) {
                    SysRegionTreeVo sysRegionTreeVo = result.get(0);
                    map.put("areaCode", sysRegionTreeVo.getRegionCode());
                    //分子
                    Integer molecular = dateShowMapper.getAuditNumByCondition(map);
                    return molecular > 0;
                }
            }
        }
        //省级时,只显示当前登录用户省,及该省以下区域
        if (RegionLevel.PROVINCE.getCode().equals(orgData.getOrganizationLevel())) {
            List<String> list = JSONArray.parseArray(orgData.getRegioncode(), String.class);
            //如果是本省,直接返回true
            if (list.get(0).equals(areaCode)) {
                return true;
            }
            //如果当前区域等级为市级或县级,判断该区域是否在该省下
            if (RegionLevel.CITY.getCode().equals(administrativeLevel) || RegionLevel.COUNTY.getCode().equals(administrativeLevel)) {
                //根据区域查询省级ID
                List<SysRegionTreeVo> result = sysApi.getParentNodeByRegionCode(areaCode);
                if (result.size() > 0) {
                    SysRegionTreeVo sysRegionTreeVo = result.get(0);
                    if (sysRegionTreeVo.getRegionCode().equals(orgData.getRegionLastCode())) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
            if (RegionLevel.CITY.getCode().equals(administrativeLevel)) {
                //查询当前市数据是否已经被审核通过
                List<SysRegionTreeVo> result = sysApi.getParentNodeByRegionCode(areaCode);
                if (result.size() > 0) {
                    //判断当前所选区域是否为本省
                    //判断当前所选区域是否为本省,不是直接返回false
                    if (!result.get(0).getRegionCode().equals(list.get(0))) {
                        return false;
                    }
                    // SysRegionTreeVo sysRegionTreeVo = result.get(0);
                    //map.put("areaCode", sysRegionTreeVo.getRegionCode());
                    //查看是否有市级数据审核通过
                    //获取当前区域下一级areacode
                    List<SysRegionTreeVo> regionTree = sysApi.getSysRegionTreeById(areaCode, "ducss");
                    List<String> areaCodeList = new ArrayList<>();
                    if (regionTree.size() > 0) {
                        for (SysRegionTreeVo treeDatum : regionTree) {
                            areaCodeList.add(treeDatum.getRegionCode());
                        }
                    }
                    map.put("areaCodes", areaCodeList);
                    Integer molecular = dateShowMapper.getAuditNumByCondition(map);
                    return molecular > 0;
                }
            }
        }
        //登录用户市级
        if (RegionLevel.CITY.getCode().equals(orgData.getOrganizationLevel())) {
            //如果是本市,直接返回true
            List<String> list = JSONArray.parseArray(orgData.getRegioncode(), String.class);
            if (list != null && list.get(1).equals(areaCode)) {
                return true;
            }
            //判断选择的是否是该市下级县
            if (RegionLevel.COUNTY.getCode().equals(administrativeLevel)) {
                //根据区域查询市级ID
                List<SysRegionTreeVo> result = sysApi.getParentNodeByRegionCode(areaCode);
                if (result.size() > 0) {
                    SysRegionTreeVo sysRegionTreeVo = result.get(1);
                    if (sysRegionTreeVo.getRegionCode().equals(orgData.getRegionLastCode())) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
            //查询当前县数据是否已经被审核通过
            List<SysRegionTreeVo> result = sysApi.getParentNodeByRegionCode(areaCode);
            if (result.size() > 0) {
                //判断当前所选区域是否为本省
                if (!result.get(0).getRegionCode().equals(list.get(0))) {
                    return false;
                }
                // SysRegionTreeVo sysRegionTreeVo = result.get(0);
                //查看是否有市级数据审核通过
                //获取当前区域下一级areacode
                List<SysRegionTreeVo> regionTree = sysApi.getSysRegionTreeById(result.get(0).getRegionCode(), "ducss");
                List<String> areaCodeList = new ArrayList<>();
                if (regionTree.size() > 0) {
                    for (SysRegionTreeVo treeDatum : regionTree) {
                        areaCodeList.add(treeDatum.getRegionCode());
                    }
                }
                map.put("areaCodes", areaCodeList);
                Integer molecular = dateShowMapper.getAuditNumByCondition(map);
                return molecular > 0;
            }
        }
        //是区县级用户,直接判断用户所选areacode是否是自己
        if (RegionLevel.COUNTY.getCode().equals(orgData.getOrganizationLevel())) {
            List<String> list = JSONArray.parseArray(orgData.getRegioncode(), String.class);
            if (list != null && list.size() > 0) {
                return list.get(2).equals(areaCode);
            } else {
                return false;
            }
        }
        return false;
    }

    private Boolean checkOrgLevelByUserInData(String administrativeLevel) {
        //获取当前登录用户等级
        String organizationInfo = UserUtil.getLoginUserOrganizationInfo();
        SysOrganization orgData = JsonUtils.json2obj(organizationInfo, SysOrganization.class);
        //若根据当前等级不同查看权限不同
        List<String> list = new ArrayList<>();
        //部级查看省市县
        if (RegionLevel.MINISTRY.getCode().equals(orgData.getOrganizationLevel())) {
            list.add(RegionLevel.MINISTRY.getCode());
            list.add(RegionLevel.PROVINCE.getCode());
            list.add(RegionLevel.CITY.getCode());
            list.add(RegionLevel.COUNTY.getCode());
        } else if (RegionLevel.PROVINCE.getCode().equals(orgData.getOrganizationLevel())) {
            list.add(RegionLevel.PROVINCE.getCode());
            list.add(RegionLevel.CITY.getCode());
            list.add(RegionLevel.COUNTY.getCode());
        } else if (RegionLevel.CITY.getCode().equals(orgData.getOrganizationLevel())) {
            list.add(RegionLevel.CITY.getCode());
            list.add(RegionLevel.COUNTY.getCode());
        } else if (RegionLevel.COUNTY.getCode().equals(orgData.getOrganizationLevel())) {
            list.add(RegionLevel.COUNTY.getCode());
        }
        return list.contains(administrativeLevel);
    }

    @Override
    public DataArea getDataAreaInDataShow(String year, String administrativeLevel, String areaCode) {
        //校验用户等级
        Boolean b = checkOrgLevelByUserInData(administrativeLevel);
        boolean bol = checkIsReportInData(year, administrativeLevel, areaCode);
        if (b && bol) {
            //获取数据范围接口
            DataArea dataArea = homePageService.getDataArea(year, areaCode, administrativeLevel, "1");
            return dataArea;
        }

        return null;
    }

    @Override
    public StrawResourceDataVo getStrawResourceDataVo(String year, String administrativeLevel, String areaCode) {
        //校验用户等级
        Boolean b = checkOrgLevelByUserInData(administrativeLevel);
        boolean bol = checkIsReportInData(year, administrativeLevel, areaCode);
        if (b && bol) {
            //根据区域等级和区域code用户获取秸秆资源数据
            //查询信息
            List<String> status = new ArrayList<>();
            status.add(AuditStatusEnum.PASSED.getCode());
            status.add(AuditStatusEnum.READ.getCode());
            status.add(AuditStatusEnum.REPORTED.getCode());
            //区域集合id
            ArrayList<String> areaCodes = new ArrayList<>();
            if ("100000".equals(areaCode)) {
                //查询所有省的数据
                getLowerAreaCode(administrativeLevel, areaCode, areaCodes);
            } else {
                areaCodes.add(areaCode);
            }
            //当年数据
            StrawUtilizeSumResVo strawResourceData = dateShowMapper.getStrawResourceData(year, areaCodes, status);
            //上一年数据
            int beforeYear = Integer.parseInt(year) - 1;
            StrawUtilizeSumResVo strawResourceDataBefore = dateShowMapper.getStrawResourceData(String.valueOf(beforeYear), areaCodes, status);
            StrawResourceDataVo strawResourceDataVo = new StrawResourceDataVo();
            //如果当年数据和去年数据都不为空
            if (strawResourceData != null && strawResourceDataBefore != null) {
                strawResourceDataVo.setTheoryResource(strawResourceData.getTheoryResource());
                strawResourceDataVo.setCollectResource(strawResourceData.getCollectResource());
                strawResourceDataVo.setProStrawUtilize(strawResourceData.getProStrawUtilize());
                BigDecimal comprehensive = new BigDecimal(0);
                // 综合利用率 =((本县秸秆利用量-收购外县的秸秆总量) + 调出秸秆量)/可收集量
                if (strawResourceData.getCollectResource().compareTo(BigDecimal.ZERO) != 0) {
                    comprehensive = strawResourceData.getProStrawUtilize()
                            .multiply(new BigDecimal(100))
                            .divide(strawResourceData.getCollectResource(), 10, RoundingMode.HALF_UP);
                }
                strawResourceDataVo.setComprehensive(comprehensive);
                //设置差值
                strawResourceDataVo.setTheoryResourceChange(strawResourceData.getTheoryResource().subtract(strawResourceDataBefore.getTheoryResource()));
                strawResourceDataVo.setCollectResourceChange(strawResourceData.getCollectResource().subtract(strawResourceDataBefore.getCollectResource()));
                strawResourceDataVo.setProStrawUtilizeChange(strawResourceData.getProStrawUtilize().subtract(strawResourceDataBefore.getProStrawUtilize()));
                BigDecimal beforeComprehensive = new BigDecimal(0);
                // 综合利用率 =((本县秸秆利用量-收购外县的秸秆总量) + 调出秸秆量)/可收集量
                if (strawResourceDataBefore.getCollectResource().compareTo(BigDecimal.ZERO) != 0) {
                    beforeComprehensive = strawResourceDataBefore.getProStrawUtilize()
                            .multiply(new BigDecimal(100))
                            .divide(strawResourceDataBefore.getCollectResource(), 10, RoundingMode.HALF_UP);
                }
                strawResourceDataVo.setComprehensiveChange(comprehensive.subtract(beforeComprehensive));
            }
            //如果今年不为空,去年为空
            if (strawResourceData != null && strawResourceDataBefore == null) {
                strawResourceDataVo.setTheoryResource(strawResourceData.getTheoryResource());
                strawResourceDataVo.setCollectResource(strawResourceData.getCollectResource());
                strawResourceDataVo.setProStrawUtilize(strawResourceData.getProStrawUtilize());
                BigDecimal comprehensive = new BigDecimal(0);
                // 综合利用率 =((本县秸秆利用量-收购外县的秸秆总量) + 调出秸秆量)/可收集量
                if (strawResourceData.getCollectResource().compareTo(BigDecimal.ZERO) != 0) {
                    comprehensive = strawResourceData.getProStrawUtilize()
                            .multiply(new BigDecimal(100))
                            .divide(strawResourceData.getCollectResource(), 10, RoundingMode.HALF_UP);
                }
                strawResourceDataVo.setComprehensive(comprehensive);
                //设置差值
                strawResourceDataVo.setTheoryResourceChange(strawResourceData.getTheoryResource());
                strawResourceDataVo.setCollectResourceChange(strawResourceData.getCollectResource());
                strawResourceDataVo.setProStrawUtilizeChange(strawResourceData.getProStrawUtilize());
                strawResourceDataVo.setComprehensiveChange(comprehensive);
            }
            //今年为空,去年不为空
            if (strawResourceData == null && strawResourceDataBefore != null) {
                BigDecimal num = new BigDecimal(0);
                strawResourceDataVo.setTheoryResource(num);
                strawResourceDataVo.setCollectResource(num);
                strawResourceDataVo.setProStrawUtilize(num);
                strawResourceDataVo.setComprehensive(num);
                //设置差值
                strawResourceDataVo.setTheoryResourceChange(num.subtract(strawResourceDataBefore.getTheoryResource()));
                strawResourceDataVo.setCollectResourceChange(num.subtract(strawResourceDataBefore.getCollectResource()));
                strawResourceDataVo.setProStrawUtilizeChange(num.subtract(strawResourceDataBefore.getProStrawUtilize()));
                BigDecimal beforeComprehensive = new BigDecimal(0);
                // 综合利用率 =((本县秸秆利用量-收购外县的秸秆总量) + 调出秸秆量)/可收集量
                if (strawResourceDataBefore.getCollectResource().compareTo(BigDecimal.ZERO) != 0) {
                    beforeComprehensive = strawResourceDataBefore.getProStrawUtilize().subtract(strawResourceDataBefore.getMainTotalOther())
                            .add(strawResourceDataBefore.getExportYieldTotal())
                            .multiply(new BigDecimal(100))
                            .divide(strawResourceDataBefore.getCollectResource(), 10, RoundingMode.HALF_UP);
                }
                strawResourceDataVo.setComprehensiveChange(num.subtract(beforeComprehensive));
            }
            if (strawResourceData == null && strawResourceDataBefore == null) {
                BigDecimal num = new BigDecimal(0);
                strawResourceDataVo.setTheoryResource(num);
                strawResourceDataVo.setCollectResource(num);
                strawResourceDataVo.setProStrawUtilize(num);
                strawResourceDataVo.setComprehensive(num);
                strawResourceDataVo.setTheoryResourceChange(num);
                strawResourceDataVo.setCollectResourceChange(num);
                strawResourceDataVo.setProStrawUtilizeChange(num);
                strawResourceDataVo.setComprehensiveChange(num);
            }
            return strawResourceDataVo;
        }
        return null;
    }


    //根据区域和区域id获取下级区域集合
    private void getLowerAreaCode(String administrativeLevel, String areaCode, List<String> areaCodes) {
        //获取当前区域下一级areacode
        List<SysRegionTreeVo> regionTree = sysApi.getSysRegionTreeById(areaCode, "ducss");
        //获取特殊区域数据
        List<SysRegionTreeVo> listRegion = getSpecialAreaIds(administrativeLevel, areaCode);
        if (listRegion.size() == 0) {
            if (regionTree.size() > 0) {
                for (SysRegionTreeVo treeDatum : regionTree) {
                    areaCodes.add(treeDatum.getRegionCode());
                }
            }
        } else {
            listRegion.forEach(sysRegionTreeVo -> {
                areaCodes.add(sysRegionTreeVo.getRegionCode());
            });
        }
    }

    @Override
    public MapViewData getDataAreaMapView(String year, String administrativeLevel, String areaCode, String dataAreaType) {
        //校验用户等级
        Boolean b = checkOrgLevelByUserInData(administrativeLevel);
        boolean bol = checkIsReportInData(year, administrativeLevel, areaCode);
        if (b && bol) {
            ArrayList<String> areaCodes = new ArrayList<>();
            //获取下级区域id
            getLowerAreaCode(administrativeLevel, areaCode, areaCodes);
            //获取数据
            List<String> status = new ArrayList<>();
            status.add(AuditStatusEnum.PASSED.getCode());
            status.add(AuditStatusEnum.REPORTED.getCode());
            List<ColumnPieChartVo> list = new ArrayList<>();
            Map<String, String> mapsByCodes = SysRegionUtil.getRegionNameMapsByCodes(IdUtil.getStrIdsByList(areaCodes), year);
            //查询下级已审核通过的区域
            List<String> auditList = dateShowMapper.getAuditOrReportAreaIds(year, areaCodes, status);
            if (auditList != null && auditList.size() > 0) {
                areaCodes.clear();
                areaCodes.addAll(auditList);
            } else {
                return null;
            }
            if (DataAreaTypeEnum.REPORT_COUNTY.getCode().equals(dataAreaType)) {
                if (RegionLevel.MINISTRY.getCode().equals(administrativeLevel)) {
                    list = dateShowMapper.getReportCountyNumByAdministrativeLevel(year, areaCodes, status, "province_id");
                }
                if (RegionLevel.PROVINCE.getCode().equals(administrativeLevel)) {
                    list = dateShowMapper.getReportCountyNumByAdministrativeLevel(year, areaCodes, status, "city_id");
                }
                if (RegionLevel.CITY.getCode().equals(administrativeLevel)) {
                    list = dateShowMapper.getReportCountyNumByAdministrativeLevel(year, areaCodes, status, "area_id");
                }
                if (RegionLevel.COUNTY.getCode().equals(administrativeLevel)) {
                    areaCodes.add(areaCode);
                    list = dateShowMapper.getReportCountyNumByAdministrativeLevel(year, areaCodes, status, "area_id");
                }
            }
            if (DataAreaTypeEnum.STRAW_UTILIZE.getCode().equals(dataAreaType)) {
                if (RegionLevel.MINISTRY.getCode().equals(administrativeLevel)) {
                    list = dateShowMapper.getStrawUtilizeNumByAdministrativeLevel(year, areaCodes, status, "province_id");
                }
                if (RegionLevel.PROVINCE.getCode().equals(administrativeLevel)) {
                    list = dateShowMapper.getStrawUtilizeNumByAdministrativeLevel(year, areaCodes, status, "city_id");
                }
                if (RegionLevel.CITY.getCode().equals(administrativeLevel)) {
                    list = dateShowMapper.getStrawUtilizeNumByAdministrativeLevel(year, areaCodes, status, "area_id");
                }
                if (RegionLevel.COUNTY.getCode().equals(administrativeLevel)) {
                    areaCodes.add(areaCode);
                    list = dateShowMapper.getStrawUtilizeNumByAdministrativeLevel(year, areaCodes, status, "area_id");
                }
            }
            if (DataAreaTypeEnum.DISPERSE_UTILIZE.getCode().equals(dataAreaType)) {
                if (RegionLevel.MINISTRY.getCode().equals(administrativeLevel)) {
                    list = dateShowMapper.getDisperseUtilizeNumByAdministrativeLevel(year, areaCodes, status, "province_id");
                }
                if (RegionLevel.PROVINCE.getCode().equals(administrativeLevel)) {
                    list = dateShowMapper.getDisperseUtilizeNumByAdministrativeLevel(year, areaCodes, status, "city_id");
                }
                if (RegionLevel.CITY.getCode().equals(administrativeLevel)) {
                    list = dateShowMapper.getDisperseUtilizeNumByAdministrativeLevel(year, areaCodes, status, "area_id");
                }
                if (RegionLevel.COUNTY.getCode().equals(administrativeLevel)) {
                    areaCodes.add(areaCode);
                    list = dateShowMapper.getDisperseUtilizeNumByAdministrativeLevel(year, areaCodes, status, "area_id");
                }
            }
            consolidateSpecificData(administrativeLevel, list);
            //处理数据
            List<AdPointData> pointDataArrayList = new ArrayList<>();
            MapViewData mapViewData = new MapViewData();
            //遍历数组替换areaCode为中文名
            for (ColumnPieChartVo pieChartVo : list) {
                AdPointData pointData = new AdPointData();
                pointData.setIndexValue(pieChartVo.getValue().toString());
                pointData.setAdRegionCode(pieChartVo.getName());
                //转换区域code为中文
                String name = mapsByCodes.get(pieChartVo.getName());
                pointData.setAdRegionName(name);
                pointDataArrayList.add(pointData);
            }
            HashMap<String, List<AdPointData>> listHashMap = new HashMap<>();
            listHashMap.put("adPointDataList", pointDataArrayList);
            mapViewData.setAdPointDataList(listHashMap);
            mapViewData.setAdLevel(administrativeLevel);
            mapViewData.setViewType("pie");
            return mapViewData;
        }
        return null;
    }

    /**
     * 特殊处理新疆和新疆建设兵团数据(将新疆数据整合进新疆中)
     *
     * @param administrativeLevel 区域等级
     * @param list                List<ColumnPieChartVo> 数据
     */
    private void consolidateSpecificData(String administrativeLevel, List<ColumnPieChartVo> list) {
        //当是部级用户时,将新疆和新疆建设兵团数据组合到一起
        if (RegionLevel.MINISTRY.getCode().equals(administrativeLevel)) {
            List<ColumnPieChartVo> pieChartVos = new ArrayList<>();
            ColumnPieChartVo pieChartVo = new ColumnPieChartVo();
            List<String> codeList = new ArrayList<>();
            BigDecimal decimal = BigDecimal.ZERO;
            if (list != null && list.size() > 0) {
                for (ColumnPieChartVo chartVo : list) {
                    if (Constants.XINJIANG.equals(chartVo.getName()) || Constants.XINJIANG_CONSTRUCTION_CORPS.equals(chartVo.getName())) {
                        decimal = decimal.add(chartVo.getValue());
                    } else {
                        pieChartVos.add(chartVo);
                    }
                    codeList.add(chartVo.getName());
                }
                if (codeList.contains(Constants.XINJIANG) || codeList.contains(Constants.XINJIANG_CONSTRUCTION_CORPS)) {
                    pieChartVo.setName(Constants.XINJIANG);
                    pieChartVo.setValue(decimal);
                    pieChartVos.add(pieChartVo);
                }
                list.clear();
                list.addAll(pieChartVos);
            }
        }
    }

    @Override
    public List<ColumnPieChartVo> getTheoryResource(String year, String administrativeLevel, String areaCode, String dataType, String strawType) {
        //校验用户等级
        Boolean b = checkOrgLevelByUserInData(administrativeLevel);
        boolean bol = checkIsReportInData(year, administrativeLevel, areaCode);
        if (b && bol) {
            List<String> areaCodeList = new ArrayList<>();
            //当区域等级为县级时,只需查询自身
            if (RegionLevel.COUNTY.getCode().equals(administrativeLevel)) {
                areaCodeList.add(areaCode);
            } else {
                getLowerAreaCode(administrativeLevel, areaCode, areaCodeList);
            }
            List<String> status = new ArrayList<>();
            status.add(AuditStatusEnum.PASSED.getCode());
            status.add(AuditStatusEnum.REPORTED.getCode());
            BigDecimal decimal = new BigDecimal("0.00");
            Map<String, String> mapsByCodes = SysRegionUtil.getRegionNameMapsByCodes(IdUtil.getStrIdsByList(areaCodeList), year);
            //如果未选秸秆类型,默认按区域分组
            if ("all".equals(strawType)) {
                List<ColumnPieChartVo> resourceData = dateShowMapper.getStrawResourceDataGroupByAreaId(year, areaCodeList, status, dataType);
                //遍历数组替换areaCode为中文名
                for (ColumnPieChartVo pieChartVo : resourceData) {
                    //转换成占比
                    //全部产生量或收集量
                    decimal = decimal.add(pieChartVo.getValue());
                }
                consolidateSpecificData(administrativeLevel, resourceData);
                for (ColumnPieChartVo chartVo : resourceData) {
                    String name = mapsByCodes.get(chartVo.getName());
                    chartVo.setName(name);
                    if (decimal.compareTo(BigDecimal.ZERO) == 0) {
                        chartVo.setValue(BigDecimal.ZERO);
                    } else {
                        BigDecimal divide = chartVo.getValue().divide(decimal, 5, RoundingMode.HALF_UP);
                        chartVo.setValue(divide);
                    }
                }
                resourceData.sort(new Comparator<ColumnPieChartVo>() {
                    @Override
                    public int compare(ColumnPieChartVo o1, ColumnPieChartVo o2) {
                        return o2.getValue().compareTo(o1.getValue());
                    }
                });
                return resourceData;
            } else {
                //汇总该区域该秸秆类型的产生量和可收集量
                List<ColumnPieChartVo> resource = sumMapper.getStrawResourceByStrawTypeGroupByAreaId(year, areaCodeList, status, dataType, strawType);
                //遍历数组替换areaCode为中文名
                for (ColumnPieChartVo pieChartVo : resource) {
                    decimal = decimal.add(pieChartVo.getValue());
                }
                consolidateSpecificData(administrativeLevel, resource);
                for (ColumnPieChartVo pieChartVo : resource) {
                    String name = mapsByCodes.get(pieChartVo.getName());
                    pieChartVo.setName(name);
                    if (decimal.compareTo(BigDecimal.ZERO) == 0) {
                        pieChartVo.setValue(BigDecimal.ZERO);
                    } else {
                        BigDecimal divide = pieChartVo.getValue().divide(decimal, 5, RoundingMode.HALF_UP);
                        pieChartVo.setValue(divide);
                    }
                }
                resource.sort(new Comparator<ColumnPieChartVo>() {
                    @Override
                    public int compare(ColumnPieChartVo o1, ColumnPieChartVo o2) {
                        return o2.getValue().compareTo(o1.getValue());
                    }
                });
                return resource;
            }
        }
        return null;
    }

    @Override
    public List<ColumnPieChartVo> getTheoryResourceGroupByStrawType(String year, String administrativeLevel, String areaCode, String dataType) {
        //校验用户等级
        Boolean b = checkOrgLevelByUserInData(administrativeLevel);
        boolean bol = checkIsReportInData(year, administrativeLevel, areaCode);
        //如果是全国的话,需汇总下级数据
        if (b && bol) {
            List<String> status = new ArrayList<>();
            status.add(AuditStatusEnum.PASSED.getCode());
            status.add(AuditStatusEnum.REPORTED.getCode());
            ArrayList<String> areaCodeList = new ArrayList<>();
            if (RegionLevel.MINISTRY.getCode().equals(administrativeLevel) && "100000".equals(areaCode)) {
                getLowerAreaCode(administrativeLevel, areaCode, areaCodeList);
            } else {
                areaCodeList.add(areaCode);
            }
            List<ColumnPieChartVo> dataByPie = changeStrawTypeToName(year, dataType, status, areaCodeList);
            return dataByPie;
        }
        return null;
    }

    @Override
    public MapViewData getTheoryResourceMapViewOne(String year, String administrativeLevel, String areaCode, String dataType) {
        //校验用户等级
        Boolean b = checkOrgLevelByUserInData(administrativeLevel);
        boolean bol = checkIsReportInData(year, administrativeLevel, areaCode);
        MapViewData mapViewData = new MapViewData();
        if (b && bol) {
            //获取数据
            List<String> areaCodeList = new ArrayList<>();
            //当区域等级为县级时,只需查询自身
            if (RegionLevel.COUNTY.getCode().equals(administrativeLevel)) {
                areaCodeList.add(areaCode);
            } else {
                getLowerAreaCode(administrativeLevel, areaCode, areaCodeList);
            }
            List<String> status = new ArrayList<>();
            status.add(AuditStatusEnum.PASSED.getCode());
            status.add(AuditStatusEnum.REPORTED.getCode());
            List<ColumnPieChartVo> resourceData = dateShowMapper.getStrawResourceDataGroupByAreaId(year, areaCodeList, status, dataType);
            consolidateSpecificData(administrativeLevel, resourceData);
            Map<String, String> mapsByCodes = SysRegionUtil.getRegionNameMapsByCodes(IdUtil.getStrIdsByList(areaCodeList), year);
            //处理数据
            List<AdPointData> pointDataArrayList = new ArrayList<>();
            //遍历数组替换areaCode为中文名
            for (ColumnPieChartVo pieChartVo : resourceData) {
                AdPointData pointData = new AdPointData();
                String name = mapsByCodes.get(pieChartVo.getName());
                pointData.setIndexValue(pieChartVo.getValue().toString());
                pointData.setAdRegionCode(pieChartVo.getName());
                pointData.setAdRegionName(name);
                pointDataArrayList.add(pointData);
            }
            HashMap<String, List<AdPointData>> listHashMap = new HashMap<>();
            listHashMap.put("adPointDataList", pointDataArrayList);
            mapViewData.setAdPointDataList(listHashMap);
            mapViewData.setAdLevel(administrativeLevel);
            mapViewData.setViewType("pie");
            return mapViewData;
        }
        return null;
    }

    @Override
    public List<ColumnPieChartVo> getTheoryResourceBySixRegions(String year, String areaCode, String dataType) {
        //如果区域id为空,则为全国六大区数据
        List<String> areaCodeList = new ArrayList<>();
        //获取当前区域下一级areacode
        List<SysRegionTreeVo> regionTree = sysApi.getSysRegionTreeById("100000", "ducss");
        if (CollectionUtils.isNotEmpty(regionTree)) {
            for (SysRegionTreeVo treeVo : regionTree) {
                areaCodeList.add(treeVo.getRegionCode());
            }
        }
        //获取全国秸秆产生量,或可收集量数据
        List<String> status = new ArrayList<>();
        status.add(AuditStatusEnum.PASSED.getCode());
        status.add(AuditStatusEnum.REPORTED.getCode());
        if (areaCode == null || Constants.ZHONGGUO_AREA_ID.equals(areaCode)) {
            List<ColumnPieChartVo> list = getColumnPieChartVos(year, dataType, areaCodeList, status);
            if (list != null) {
                list.sort(new Comparator<ColumnPieChartVo>() {
                    @Override
                    public int compare(ColumnPieChartVo o1, ColumnPieChartVo o2) {
                        return o2.getValue().compareTo(o1.getValue());
                    }
                });
                return list;
            }
        } else {
            //秸秆类型
            List<SysDict> dictList = SysDictUtil.getDictList(Constants.DictionaryType.STRAW_TYPE);
            HashMap<String, String> strawTypeMap = new HashMap<>();
            for (SysDict sysDict : dictList) {
                strawTypeMap.put(sysDict.getDictcode(), sysDict.getDictname());
            }
            //查询指定区域类14种秸秆产量汇总数据
            List<String> sixRegionCode = Arrays.asList(areaCode.split(","));
            List<ColumnPieChartVo> dataByPie = dateShowMapper.getStrawResourceDataByPie(year, sixRegionCode, status, dataType);
            //查询全国14种作物的产量
            List<ColumnPieChartVo> allList = dateShowMapper.getStrawResourceDataByPie(year, areaCodeList, status, dataType);
            Map<String, BigDecimal> map = allList.stream().collect(Collectors.toMap(c -> c.getName(), c -> c.getValue()));
            if (dataByPie != null && dataByPie.size() > 0) {
                for (ColumnPieChartVo pieChartVo : dataByPie) {
                    BigDecimal divide = BigDecimal.ZERO;
                    if (map.get(pieChartVo.getName()) != null && BigDecimal.ZERO.compareTo(map.get(pieChartVo.getName())) != 0) {
                        BigDecimal decimal = pieChartVo.getValue();
                        divide = decimal.divide(map.get(pieChartVo.getName()), 5, RoundingMode.HALF_UP);
                    }
                    pieChartVo.setValue(divide);
                    String name = strawTypeMap.get(pieChartVo.getName());
                    pieChartVo.setName(name);
                }
                dataByPie.sort(new Comparator<ColumnPieChartVo>() {
                    @Override
                    public int compare(ColumnPieChartVo o1, ColumnPieChartVo o2) {
                        return o2.getValue().compareTo(o1.getValue());
                    }
                });
                return dataByPie;
            }
        }
        return null;
    }

    @Override
    public List<ColumnPieChartVo> getResourcePieBySixRegions(String year, String areaCode, String dataType, String strawType) {
        //如果区域id为空,则为全国六大区数据
        List<String> areaCodeList = new ArrayList<>();
        //获取当前区域下一级areacode
        List<SysRegionTreeVo> regionTree = sysApi.getSysRegionTreeById("100000", "ducss");
        if (CollectionUtils.isNotEmpty(regionTree)) {
            for (SysRegionTreeVo treeVo : regionTree) {
                areaCodeList.add(treeVo.getRegionCode());
            }
        }
        //获取全面秸秆产生量,或可收集量数据
        List<String> status = new ArrayList<>();
        status.add(AuditStatusEnum.PASSED.getCode());
        status.add(AuditStatusEnum.REPORTED.getCode());
        //当区域id为空时,展示是全国六大区数据,并且可选秸秆数据汇总
        if (areaCode == null || Constants.ZHONGGUO_AREA_ID.equals(areaCode)) {
            //秸秆类型为空
            if ("all".equals(strawType)) {
                List<ColumnPieChartVo> list = getColumnPieChartVos(year, dataType, areaCodeList, status);
                if (list != null) {
                    return list;
                }
            } else {
                //秸秆类型不为空,查询该秸秆类型全国数据并按六大区分组
                //直接查询六次指定秸秆类型的数据
                //华北区
                List<String> north_region_list = Arrays.asList(SixRegionEnum.NORTH_REGION.getCode().split(","));
                //长江区
                List<String> chang_jiang_river_region_list = Arrays.asList(SixRegionEnum.CHANG_JIANG_RIVER_REGION.getCode().split(","));
                //东北区
                List<String> northeast_region_list = Arrays.asList(SixRegionEnum.NORTHEAST_REGION.getCode().split(","));
                //西北区
                List<String> northwest_region_list = Arrays.asList(SixRegionEnum.NORTHWEST_REGION.getCode().split(","));
                //西南区
                List<String> southwest_region_list = Arrays.asList(SixRegionEnum.SOUTHWEST_REGION.getCode().split(","));
                //华南区
                List<String> south_region_list = Arrays.asList(SixRegionEnum.SOUTH_REGION.getCode().split(","));
                //华北区
                ColumnPieChartVo north_region = dateShowMapper.getStrawResourceByPie(year, north_region_list, status, dataType, strawType);
                ColumnPieChartVo chang_jiang_river_region = dateShowMapper.getStrawResourceByPie(year, chang_jiang_river_region_list, status, dataType, strawType);
                ColumnPieChartVo northeast_region = dateShowMapper.getStrawResourceByPie(year, northeast_region_list, status, dataType, strawType);
                ColumnPieChartVo northwest_region = dateShowMapper.getStrawResourceByPie(year, northwest_region_list, status, dataType, strawType);
                ColumnPieChartVo southwest_region = dateShowMapper.getStrawResourceByPie(year, southwest_region_list, status, dataType, strawType);
                ColumnPieChartVo south_region = dateShowMapper.getStrawResourceByPie(year, south_region_list, status, dataType, strawType);
                ArrayList<ColumnPieChartVo> columnPieChartVos = new ArrayList<>();
                if (north_region != null) {
                    north_region.setName("华北区");
                    columnPieChartVos.add(north_region);
                }
                if (chang_jiang_river_region != null) {
                    chang_jiang_river_region.setName("长江区");
                    columnPieChartVos.add(chang_jiang_river_region);
                }
                if (northeast_region != null) {
                    northeast_region.setName("东北区");
                    columnPieChartVos.add(northeast_region);
                }
                if (northwest_region != null) {
                    northwest_region.setName("西北区");
                    columnPieChartVos.add(northwest_region);
                }
                if (southwest_region != null) {
                    southwest_region.setName("西南区");
                    columnPieChartVos.add(southwest_region);
                }
                if (south_region != null) {
                    south_region.setName("华南区");
                    columnPieChartVos.add(south_region);
                }
                return columnPieChartVos;
            }
            return null;
        } else {
            //点进指定六大区之一时
            //查询指定区域类14种秸秆产量汇总数据
            List<String> sixRegionCode = Arrays.asList(areaCode.split(","));
            List<ColumnPieChartVo> dataByPie = changeStrawTypeToName(year, dataType, status, sixRegionCode);
            return dataByPie;
        }
    }

    /**
     * 转换秸秆类型为中文
     */
    @NotNull
    private List<ColumnPieChartVo> changeStrawTypeToName(String year, String dataType, List<String> status, List<String> sixRegionCode) {
        List<ColumnPieChartVo> dataByPie = dateShowMapper.getStrawResourceDataByPie(year, sixRegionCode, status, dataType);
        //秸秆类型
        List<SysDict> dictList = SysDictUtil.getDictList(Constants.DictionaryType.STRAW_TYPE);
        HashMap<String, String> map = new HashMap<>();
        for (SysDict sysDict : dictList) {
            map.put(sysDict.getDictcode(), sysDict.getDictname());
        }
        //遍历数组替换秸秆类型为中文名
        for (ColumnPieChartVo pieChartVo : dataByPie) {
            String s = map.get(pieChartVo.getName());
            pieChartVo.setName(s);
        }
        return dataByPie;
    }


    @Nullable
    private List<ColumnPieChartVo> getColumnPieChartVos(String year, String dataType, List<String> areaCodeList, List<String> status) {
        List<ColumnPieChartVo> resourceData = dateShowMapper.getStrawResourceDataGroupByAreaId(year, areaCodeList, status, dataType);
        //逐条循环判断六大区.并汇总数据
        if (resourceData.size() > 0) {
            //根据六大区分条汇总
            //华北区
            List<String> north_region_list = Arrays.asList(SixRegionEnum.NORTH_REGION.getCode().split(","));
            //长江区
            List<String> chang_jiang_river_region_list = Arrays.asList(SixRegionEnum.CHANG_JIANG_RIVER_REGION.getCode().split(","));
            //东北区
            List<String> northeast_region_list = Arrays.asList(SixRegionEnum.NORTHEAST_REGION.getCode().split(","));
            //西北区
            List<String> northwest_region_list = Arrays.asList(SixRegionEnum.NORTHWEST_REGION.getCode().split(","));
            //西南区
            List<String> southwest_region_list = Arrays.asList(SixRegionEnum.SOUTHWEST_REGION.getCode().split(","));
            //华南区
            List<String> south_region_list = Arrays.asList(SixRegionEnum.SOUTH_REGION.getCode().split(","));

            //华北区汇总数据
            ColumnPieChartVo north_region = new ColumnPieChartVo();
            //长江区
            ColumnPieChartVo chang_jiang_river_region = new ColumnPieChartVo();
            //东北区
            ColumnPieChartVo northeast_region = new ColumnPieChartVo();
            //西北区
            ColumnPieChartVo northwest_region = new ColumnPieChartVo();
            //西南区
            ColumnPieChartVo southwest_region = new ColumnPieChartVo();
            //华南区
            ColumnPieChartVo south_region = new ColumnPieChartVo();

            //华北区汇总数据
            BigDecimal north_region_num = new BigDecimal("0.00");
            //长江区
            BigDecimal chang_jiang_river_region_num = new BigDecimal("0.00");
            //东北区
            BigDecimal northeast_region_num = new BigDecimal("0.00");
            //西北区
            BigDecimal northwest_region_num = new BigDecimal("0.00");
            //西南区
            BigDecimal southwest_region_num = new BigDecimal("0.00");
            //华南区
            BigDecimal south_region_num = new BigDecimal("0.00");
            for (ColumnPieChartVo pieChartVo : resourceData) {
                if (north_region_list.contains(pieChartVo.getName())) {
                    //每级叠加
                    north_region_num = north_region_num.add(pieChartVo.getValue());
                }
                if (chang_jiang_river_region_list.contains(pieChartVo.getName())) {
                    chang_jiang_river_region_num = chang_jiang_river_region_num.add(pieChartVo.getValue());
                }
                if (northeast_region_list.contains(pieChartVo.getName())) {
                    northeast_region_num = northeast_region_num.add(pieChartVo.getValue());
                }
                if (northwest_region_list.contains(pieChartVo.getName())) {
                    northwest_region_num = northwest_region_num.add(pieChartVo.getValue());
                }
                if (southwest_region_list.contains(pieChartVo.getName())) {
                    southwest_region_num = southwest_region_num.add(pieChartVo.getValue());
                }
                if (south_region_list.contains(pieChartVo.getName())) {
                    south_region_num = south_region_num.add(pieChartVo.getValue());
                }
            }
            north_region.setValue(north_region_num);
            north_region.setName(SixRegionEnum.NORTH_REGION.getDescription());
            chang_jiang_river_region.setValue(chang_jiang_river_region_num);
            chang_jiang_river_region.setName(SixRegionEnum.CHANG_JIANG_RIVER_REGION.getDescription());
            northeast_region.setValue(northeast_region_num);
            northeast_region.setName(SixRegionEnum.NORTHEAST_REGION.getDescription());
            northwest_region.setValue(northwest_region_num);
            northwest_region.setName(SixRegionEnum.NORTHWEST_REGION.getDescription());
            southwest_region.setValue(southwest_region_num);
            southwest_region.setName(SixRegionEnum.SOUTHWEST_REGION.getDescription());
            south_region.setValue(south_region_num);
            south_region.setName(SixRegionEnum.SOUTH_REGION.getDescription());
            List<ColumnPieChartVo> list = new ArrayList<>();
            list.add(north_region);
            list.add(chang_jiang_river_region);
            list.add(northeast_region);
            list.add(northwest_region);
            list.add(southwest_region);
            list.add(south_region);
            return list;
        }
        return null;
    }

    @Override
    public MapViewData getTheoryResourceMapViewTwo(String year, String administrativeLevel, String dataType) {
        //如果区域id为空,则为全国六大区数据
        List<String> areaCodeList = new ArrayList<>();
        //获取当前区域下一级areacode
        List<SysRegionTreeVo> regionTree = sysApi.getSysRegionTreeById("100000", "ducss");
        if (CollectionUtils.isNotEmpty(regionTree)) {
            for (SysRegionTreeVo treeVo : regionTree) {
                areaCodeList.add(treeVo.getRegionCode());
            }
        }
        //获取全国秸秆产生量,或可收集量数据
        List<String> status = new ArrayList<>();
        status.add(AuditStatusEnum.PASSED.getCode());
        status.add(AuditStatusEnum.REPORTED.getCode());
        List<ColumnPieChartVo> list = getColumnPieChartVos(year, dataType, areaCodeList, status);
        //处理数据
        List<AdPointData> pointDataArrayList = new ArrayList<>();
        MapViewData mapViewData = new MapViewData();
        if (list != null && list.size() > 0) {
            //遍历数组替换areaCode为中文名
            for (ColumnPieChartVo pieChartVo : list) {
                AdPointData pointData = new AdPointData();
                pointData.setIndexValue(pieChartVo.getValue().toString());
                if (SixRegionEnum.NORTH_REGION.getDescription().equals(pieChartVo.getName())) {
                    pointData.setAdRegionCode(SixRegionEnum.NORTH_REGION.getCode());
                }
                if (SixRegionEnum.CHANG_JIANG_RIVER_REGION.getDescription().equals(pieChartVo.getName())) {
                    pointData.setAdRegionCode(SixRegionEnum.CHANG_JIANG_RIVER_REGION.getCode());
                }
                if (SixRegionEnum.NORTHEAST_REGION.getDescription().equals(pieChartVo.getName())) {
                    pointData.setAdRegionCode(SixRegionEnum.NORTHEAST_REGION.getCode());
                }
                if (SixRegionEnum.NORTHWEST_REGION.getDescription().equals(pieChartVo.getName())) {
                    pointData.setAdRegionCode(SixRegionEnum.NORTHWEST_REGION.getCode());
                }
                if (SixRegionEnum.SOUTHWEST_REGION.getDescription().equals(pieChartVo.getName())) {
                    pointData.setAdRegionCode(SixRegionEnum.SOUTHWEST_REGION.getCode());
                }
                if (SixRegionEnum.SOUTH_REGION.getDescription().equals(pieChartVo.getName())) {
                    pointData.setAdRegionCode(SixRegionEnum.SOUTH_REGION.getCode());
                }
                pointData.setAdRegionName(pieChartVo.getName());
                pointDataArrayList.add(pointData);
            }
        }
        HashMap<String, List<AdPointData>> listHashMap = new HashMap<>();
        listHashMap.put("adPointDataList", pointDataArrayList);
        mapViewData.setAdPointDataList(listHashMap);
        mapViewData.setAdLevel(administrativeLevel);
        mapViewData.setViewType("pie");
        return mapViewData;
    }

    @Override
    public List<ColumnPieChartVo> getProStrawUtilize(String year, String administrativeLevel, String areaCode, String dataType, String strawType) {
        //校验用户等级
        Boolean b = checkOrgLevelByUserInData(administrativeLevel);
        boolean bol = checkIsReportInData(year, administrativeLevel, areaCode);
        if (b && bol) {
            List<String> areaCodeList = new ArrayList<>();
            //当区域等级为县级时,只需查询自身
            if (RegionLevel.COUNTY.getCode().equals(administrativeLevel)) {
                areaCodeList.add(areaCode);
            } else {
                getLowerAreaCode(administrativeLevel, areaCode, areaCodeList);
            }
            List<String> status = new ArrayList<>();
            status.add(AuditStatusEnum.PASSED.getCode());
            status.add(AuditStatusEnum.REPORTED.getCode());
            Map<String, String> mapsByCodes = SysRegionUtil.getRegionNameMapsByCodes(IdUtil.getStrIdsByList(areaCodeList), year);
            if ("all".equals(strawType)) {
                //综合利用量
                if (StrawUtilizeConditionEnum.STRAW_UTILIZE.getCode().equals(dataType)) {
                    //查询综合利用量并根据区域分组
                    List<ColumnPieChartVo> resourceData = dateShowMapper.getStrawResourceDataGroupByAreaId(year, areaCodeList, status, dataType);
                    consolidateSpecificData(administrativeLevel, resourceData);
                    for (ColumnPieChartVo chartVo : resourceData) {
                        String name = mapsByCodes.get(chartVo.getName());
                        chartVo.setName(name);
                        chartVo.setValue(chartVo.getValue());
                    }
                    resourceData.sort(new Comparator<ColumnPieChartVo>() {
                        @Override
                        public int compare(ColumnPieChartVo o1, ColumnPieChartVo o2) {
                            return o2.getValue().compareTo(o1.getValue());
                        }
                    });
                    return resourceData;
                }
                //根据区域获取数据集合
                List<StrawUtilizeSumResVo> utilizeByAreaCode = dateShowMapper.getStrawUtilizeByAreaCode(year, areaCodeList, status);
                consolidateStrawSumSpecificData(administrativeLevel, utilizeByAreaCode);
                List<ColumnPieChartVo> chartVoList = new ArrayList<>();
                //综合利用率
                if (StrawUtilizeConditionEnum.COMPREHENSIVE.getCode().equals(dataType)) {
                    //遍历数组求每个区域的综合利用率,并转换区域名称
                    for (StrawUtilizeSumResVo strawUtilizeSumResVo : utilizeByAreaCode) {
                        ColumnPieChartVo chartVo = new ColumnPieChartVo();
                        //综合利用率
                        // 综合利用率 =((本县秸秆利用量-收购外县的秸秆总量) + 调出秸秆量)/可收集量
                        if (strawUtilizeSumResVo.getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                            chartVo.setValue(BigDecimal.ZERO);
                        } else {
                            BigDecimal comprehensive = strawUtilizeSumResVo.getProStrawUtilize()
                                    .divide(strawUtilizeSumResVo.getCollectResource(), 10, RoundingMode.HALF_UP);
                            chartVo.setValue(comprehensive);
                        }
                        String name = mapsByCodes.get(strawUtilizeSumResVo.getAreaId());
                        chartVo.setName(name);
                        chartVoList.add(chartVo);
                    }
                }
                //选择是五料化之一时
                if (StrawUtilizeConditionEnum.FERTILISING.getCode().equals(dataType) || StrawUtilizeConditionEnum.FORAGE.getCode().equals(dataType)
                        || StrawUtilizeConditionEnum.FUEL.getCode().equals(dataType) || StrawUtilizeConditionEnum.BASE.getCode().equals(dataType) || StrawUtilizeConditionEnum.MATERIAL.getCode().equals(dataType)) {
                    //遍历数组求每个区域的肥料化利用比例,并转换区域名称
                    for (StrawUtilizeSumResVo strawUtilizeSumResVo : utilizeByAreaCode) {
                        ColumnPieChartVo chartVo = new ColumnPieChartVo();
                        //肥料化比例
                        if (StrawUtilizeConditionEnum.FERTILISING.getCode().equals(dataType)) {
                            BigDecimal Fertilising = strawUtilizeSumResVo.getMainFertilising().add(strawUtilizeSumResVo.getDisperseFertilising()).add(strawUtilizeSumResVo.getReturnResource());
                            // 肥料化利用比例 =(市场主体利用量（肥料）+农户分散利用量（肥料）+直接还田量)/可收集量
                            if (strawUtilizeSumResVo.getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                                chartVo.setValue(BigDecimal.ZERO);
                            } else {
                                BigDecimal fertilisingScale = Fertilising.divide(strawUtilizeSumResVo.getCollectResource(), 10, RoundingMode.HALF_UP);
                                chartVo.setValue(fertilisingScale);
                            }
                        }
                        //饲料化比例
                        if (StrawUtilizeConditionEnum.FORAGE.getCode().equals(dataType)) {
                            BigDecimal forage = strawUtilizeSumResVo.getMainForage().add(strawUtilizeSumResVo.getDisperseForage());
                            // 肥料化利用比例 =(市场主体利用量（肥料）+农户分散利用量（肥料）+直接还田量)/可收集量
                            if (strawUtilizeSumResVo.getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                                chartVo.setValue(BigDecimal.ZERO);
                            } else {
                                BigDecimal forageScale = forage.divide(strawUtilizeSumResVo.getCollectResource(), 10, RoundingMode.HALF_UP);
                                chartVo.setValue(forageScale);
                            }
                        }
                        //燃料化比例
                        if (StrawUtilizeConditionEnum.FUEL.getCode().equals(dataType)) {
                            BigDecimal fuel = strawUtilizeSumResVo.getMainFuel().add(strawUtilizeSumResVo.getDisperseFuel());
                            // 肥料化利用比例 =(市场主体利用量（肥料）+农户分散利用量（肥料）+直接还田量)/可收集量
                            if (strawUtilizeSumResVo.getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                                chartVo.setValue(BigDecimal.ZERO);
                            } else {
                                BigDecimal fuelScale = fuel.divide(strawUtilizeSumResVo.getCollectResource(), 10, RoundingMode.HALF_UP);
                                chartVo.setValue(fuelScale);
                            }
                        }
                        //基料化比例
                        if (StrawUtilizeConditionEnum.BASE.getCode().equals(dataType)) {
                            BigDecimal base = strawUtilizeSumResVo.getMainBase().add(strawUtilizeSumResVo.getDisperseBase());
                            // 肥料化利用比例 =(市场主体利用量（肥料）+农户分散利用量（肥料）+直接还田量)/可收集量
                            if (strawUtilizeSumResVo.getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                                chartVo.setValue(BigDecimal.ZERO);
                            } else {
                                BigDecimal baseScale = base.divide(strawUtilizeSumResVo.getCollectResource(), 10, RoundingMode.HALF_UP);
                                chartVo.setValue(baseScale);
                            }
                        }
                        //原料化比例
                        if (StrawUtilizeConditionEnum.MATERIAL.getCode().equals(dataType)) {
                            BigDecimal material = strawUtilizeSumResVo.getMainMaterial().add(strawUtilizeSumResVo.getDisperseMaterial());
                            // 肥料化利用比例 =(市场主体利用量（肥料）+农户分散利用量（肥料）+直接还田量)/可收集量
                            if (strawUtilizeSumResVo.getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                                chartVo.setValue(BigDecimal.ZERO);
                            } else {
                                BigDecimal materialScale = material.divide(strawUtilizeSumResVo.getCollectResource(), 10, RoundingMode.HALF_UP);
                                chartVo.setValue(materialScale);
                            }
                        }
                        String name = mapsByCodes.get(strawUtilizeSumResVo.getAreaId());
                        chartVo.setName(name);
                        chartVoList.add(chartVo);
                    }
                }
                //综合利用能力指数 = 市场主体规模化利用量+农户分散利用量+直接还田量/可收集量*100
                if (StrawUtilizeConditionEnum.COMPREHENSIVE_INDEX.getCode().equals(dataType) || StrawUtilizeConditionEnum.INDUSTRIALIZATION_INDEX.getCode().equals(dataType)) {
                    // 综合利用能力指数
                    for (StrawUtilizeSumResVo strawUtilizeSumResVo : utilizeByAreaCode) {
                        ColumnPieChartVo chartVo = new ColumnPieChartVo();
                        if (StrawUtilizeConditionEnum.COMPREHENSIVE_INDEX.getCode().equals(dataType)) {
                            if (strawUtilizeSumResVo.getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                                chartVo.setValue(BigDecimal.ZERO);
                            } else {
                                BigDecimal comprehensiveIndex = (strawUtilizeSumResVo.getMainTotal().add(strawUtilizeSumResVo.getDisperseTotal()).add(strawUtilizeSumResVo.getReturnResource()))
                                        .divide(strawUtilizeSumResVo.getCollectResource(), 10, RoundingMode.HALF_UP);
                                chartVo.setValue(comprehensiveIndex);
                            }
                        }
                        //产业化利用能力指数
                        if (StrawUtilizeConditionEnum.INDUSTRIALIZATION_INDEX.getCode().equals(dataType)) {
                            chartVo.setValue(strawUtilizeSumResVo.getIndustrializationIndex());
                        }
                        String name = mapsByCodes.get(strawUtilizeSumResVo.getAreaId());
                        chartVo.setName(name);
                        chartVoList.add(chartVo);
                    }
                }
                chartVoList.sort(new Comparator<ColumnPieChartVo>() {
                    @Override
                    public int compare(ColumnPieChartVo o1, ColumnPieChartVo o2) {
                        return o2.getValue().compareTo(o1.getValue());
                    }
                });
                return chartVoList;
            } else {
                //当秸秆类型不为空时及是14作物类型之一时
                //产生量
                List<ColumnPieChartVo> chartVoList = new ArrayList<>();
                List<StrawUtilizeSum> strawSumByStrawType = dateShowMapper.getStrawSumByStrawType(year, areaCodeList, status, strawType);
                consolidateSumSpecificData(administrativeLevel, strawSumByStrawType);
                for (StrawUtilizeSum strawUtilizeSum : strawSumByStrawType) {
                    ColumnPieChartVo chartVo = new ColumnPieChartVo();
                    //综合利用量
                    if (StrawUtilizeConditionEnum.STRAW_UTILIZE.getCode().equals(dataType)) {
                        chartVo.setValue(strawUtilizeSum.getProStrawUtilize());
                    }
                    //综合利用率
                    if (StrawUtilizeConditionEnum.COMPREHENSIVE.getCode().equals(dataType)) {
                        // 综合利用率 =((本县秸秆利用量-收购外县的秸秆总量) + 调出秸秆量)/可收集量
                        if (strawUtilizeSum.getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                            chartVo.setValue(BigDecimal.ZERO);
                        } else {
                            BigDecimal comprehensive = strawUtilizeSum.getProStrawUtilize()
                                    .divide(strawUtilizeSum.getCollectResource(), 10, RoundingMode.HALF_UP);
                            chartVo.setValue(comprehensive);
                        }
                    }
                    //肥料化比例
                    if (StrawUtilizeConditionEnum.FERTILISING.getCode().equals(dataType)) {
                        BigDecimal fertilising = strawUtilizeSum.getMainFertilising().add(strawUtilizeSum.getDisperseFertilising()).add(strawUtilizeSum.getProStillField());
                        // 肥料化利用比例 =(市场主体利用量（肥料）+农户分散利用量（肥料）+直接还田量)/可收集量
                        if (strawUtilizeSum.getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                            chartVo.setValue(BigDecimal.ZERO);
                        } else {
                            BigDecimal fertilisingScale = fertilising.divide(strawUtilizeSum.getCollectResource(), 10, RoundingMode.HALF_UP);
                            chartVo.setValue(fertilisingScale);
                        }
                    }
                    //饲料化比例
                    if (StrawUtilizeConditionEnum.FORAGE.getCode().equals(dataType)) {
                        BigDecimal forage = strawUtilizeSum.getMainForage().add(strawUtilizeSum.getDisperseForage());
                        // 肥料化利用比例 =(市场主体利用量（肥料）+农户分散利用量（肥料）+直接还田量)/可收集量
                        if (strawUtilizeSum.getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                            chartVo.setValue(BigDecimal.ZERO);
                        } else {
                            BigDecimal forageScale = forage.divide(strawUtilizeSum.getCollectResource(), 10, RoundingMode.HALF_UP);
                            chartVo.setValue(forageScale);
                        }
                    }
                    //燃料化比例
                    if (StrawUtilizeConditionEnum.FUEL.getCode().equals(dataType)) {
                        BigDecimal fuel = strawUtilizeSum.getMainFuel().add(strawUtilizeSum.getDisperseFuel());
                        // 肥料化利用比例 =(市场主体利用量（肥料）+农户分散利用量（肥料）+直接还田量)/可收集量
                        if (strawUtilizeSum.getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                            chartVo.setValue(BigDecimal.ZERO);
                        } else {
                            BigDecimal fuelScale = fuel.divide(strawUtilizeSum.getCollectResource(), 10, RoundingMode.HALF_UP);
                            chartVo.setValue(fuelScale);
                        }
                    }
                    //基料化比例
                    if (StrawUtilizeConditionEnum.BASE.getCode().equals(dataType)) {
                        BigDecimal base = strawUtilizeSum.getMainBase().add(strawUtilizeSum.getDisperseBase());
                        // 肥料化利用比例 =(市场主体利用量（肥料）+农户分散利用量（肥料）+直接还田量)/可收集量
                        if (strawUtilizeSum.getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                            chartVo.setValue(BigDecimal.ZERO);
                        } else {
                            BigDecimal baseScale = base.divide(strawUtilizeSum.getCollectResource(), 10, RoundingMode.HALF_UP);
                            chartVo.setValue(baseScale);
                        }
                    }
                    //原料化比例
                    if (StrawUtilizeConditionEnum.MATERIAL.getCode().equals(dataType)) {
                        BigDecimal material = strawUtilizeSum.getMainMaterial().add(strawUtilizeSum.getDisperseMaterial());
                        // 肥料化利用比例 =(市场主体利用量（肥料）+农户分散利用量（肥料）+直接还田量)/可收集量
                        if (strawUtilizeSum.getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                            chartVo.setValue(BigDecimal.ZERO);
                        } else {
                            BigDecimal materialScale = material.divide(strawUtilizeSum.getCollectResource(), 10, RoundingMode.HALF_UP);
                            chartVo.setValue(materialScale);
                        }
                    }
                    if (StrawUtilizeConditionEnum.COMPREHENSIVE_INDEX.getCode().equals(dataType)) {
                        if (strawUtilizeSum.getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                            chartVo.setValue(BigDecimal.ZERO);
                        } else {
                            BigDecimal comprehensiveIndex = (strawUtilizeSum.getMainTotal().add(strawUtilizeSum.getDisperseTotal()).add(strawUtilizeSum.getProStillField()))
                                    .divide(strawUtilizeSum.getCollectResource(), 10, RoundingMode.HALF_UP);
                            chartVo.setValue(comprehensiveIndex);
                        }
                    }
                    //产业化利用能力指数
                    if (StrawUtilizeConditionEnum.INDUSTRIALIZATION_INDEX.getCode().equals(dataType)) {
                        if (strawUtilizeSum.getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                            chartVo.setValue(BigDecimal.ZERO);
                        } else {
                            BigDecimal industrializationIndex = (strawUtilizeSum.getMainTotal()
                                    .divide(strawUtilizeSum.getCollectResource(), 10, RoundingMode.HALF_UP));
                            chartVo.setValue(industrializationIndex);
                        }
                    }
                    chartVo.setName(strawUtilizeSum.getAreaId());
                    String name = mapsByCodes.get(strawUtilizeSum.getAreaId());
                    chartVo.setName(name);
                    chartVoList.add(chartVo);
                }
                chartVoList.sort(new Comparator<ColumnPieChartVo>() {
                    @Override
                    public int compare(ColumnPieChartVo o1, ColumnPieChartVo o2) {
                        return o2.getValue().compareTo(o1.getValue());
                    }
                });
                return chartVoList;
            }
        }
        return null;
    }

    //根据区域分组,选择秸秆类型后特殊处理新疆及新疆建设兵团
    private void consolidateSumSpecificData(String administrativeLevel, List<StrawUtilizeSum> strawSumByStrawType) {
        //当是部级用户时,将新疆和新疆建设兵团数据组合到一起
        if (RegionLevel.MINISTRY.getCode().equals(administrativeLevel)) {
            List<StrawUtilizeSum> pieChartVos = new ArrayList<>();
            StrawUtilizeSum resVo = new StrawUtilizeSum();
            if (strawSumByStrawType != null && strawSumByStrawType.size() > 0) {
                for (StrawUtilizeSum sumResVo : strawSumByStrawType) {
                    if (Constants.XINJIANG.equals(sumResVo.getAreaId()) || Constants.XINJIANG_CONSTRUCTION_CORPS.equals(sumResVo.getAreaId())) {
                        //将所需的要的属性加起来
                        resVo.setMainFertilising(resVo.getMainFertilising().add(sumResVo.getMainFertilising()));
                        resVo.setMainForage(resVo.getMainForage().add(sumResVo.getMainForage()));
                        resVo.setMainFuel(resVo.getMainFuel().add(sumResVo.getMainFuel()));
                        resVo.setMainBase(resVo.getMainBase().add(sumResVo.getMainBase()));
                        resVo.setMainMaterial(resVo.getMainMaterial().add(sumResVo.getMainMaterial()));
                        resVo.setMainTotal(resVo.getMainTotal().add(sumResVo.getMainTotal()));
                        resVo.setMainTotalOther(resVo.getMainTotalOther().add(sumResVo.getMainTotalOther()));
                        resVo.setDisperseFertilising(resVo.getDisperseFertilising().add(sumResVo.getDisperseFertilising()));
                        resVo.setDisperseForage(resVo.getDisperseForage().add(sumResVo.getDisperseForage()));
                        resVo.setDisperseFuel(resVo.getDisperseFuel().add(sumResVo.getDisperseFuel()));
                        resVo.setDisperseBase(resVo.getDisperseBase().add(sumResVo.getDisperseBase()));
                        resVo.setDisperseMaterial(resVo.getDisperseMaterial().add(sumResVo.getDisperseMaterial()));
                        resVo.setDisperseTotal(resVo.getDisperseTotal().add(sumResVo.getDisperseTotal()));
                        resVo.setProStrawUtilize(resVo.getProStrawUtilize().add(sumResVo.getProStrawUtilize()));
                        resVo.setCollectResource(resVo.getCollectResource().add(sumResVo.getCollectResource()));
                        resVo.setTheoryResource(resVo.getTheoryResource().add(sumResVo.getTheoryResource()));
                        resVo.setProStillField(resVo.getProStillField().add(sumResVo.getProStillField()));
                        resVo.setYieldAllExport(resVo.getYieldAllExport().add(sumResVo.getYieldAllExport()));
                        resVo.setGrainYield(resVo.getGrainYield().add(sumResVo.getGrainYield()));
                        resVo.setExportYieldTotal(resVo.getExportYieldTotal().add(sumResVo.getExportYieldTotal()));
                        resVo.setComprehensive(resVo.getComprehensive().add(sumResVo.getComprehensive()));
                    } else {
                        pieChartVos.add(sumResVo);
                    }
                }
                resVo.setAreaId(Constants.XINJIANG);
                pieChartVos.add(resVo);
                strawSumByStrawType.clear();
                strawSumByStrawType.addAll(pieChartVos);
            }
        }
    }

    //根据区域分组,无秸秆类型 特殊处理新疆及新疆建设兵团
    private void consolidateStrawSumSpecificData(String administrativeLevel, List<StrawUtilizeSumResVo> utilizeByAreaCode) {
        //当是部级用户时,将新疆和新疆建设兵团数据组合到一起
        if (RegionLevel.MINISTRY.getCode().equals(administrativeLevel)) {
            List<StrawUtilizeSumResVo> pieChartVos = new ArrayList<>();
            //判断list是否含有新疆及新疆建设兵团,在添加到返回值中
            List<String> codeList = new ArrayList<>();
            StrawUtilizeSumResVo resVo = new StrawUtilizeSumResVo();
            if (utilizeByAreaCode != null && utilizeByAreaCode.size() > 0) {
                for (StrawUtilizeSumResVo sumResVo : utilizeByAreaCode) {
                    if (Constants.XINJIANG.equals(sumResVo.getAreaId()) || Constants.XINJIANG_CONSTRUCTION_CORPS.equals(sumResVo.getAreaId())) {
                        //将所需的要的属性加起来
                        resVo.setMainFertilising(resVo.getMainFertilising().add(sumResVo.getMainFertilising()));
                        resVo.setMainForage(resVo.getMainForage().add(sumResVo.getMainForage()));
                        resVo.setMainFuel(resVo.getMainFuel().add(sumResVo.getMainFuel()));
                        resVo.setMainBase(resVo.getMainBase().add(sumResVo.getMainBase()));
                        resVo.setMainMaterial(resVo.getMainMaterial().add(sumResVo.getMainMaterial()));
                        resVo.setMainTotal(resVo.getMainTotal().add(sumResVo.getMainTotal()));
                        resVo.setMainTotalOther(resVo.getMainTotalOther().add(sumResVo.getMainTotalOther()));
                        resVo.setDisperseFertilising(resVo.getDisperseFertilising().add(sumResVo.getDisperseFertilising()));
                        resVo.setDisperseForage(resVo.getDisperseForage().add(sumResVo.getDisperseForage()));
                        resVo.setDisperseFuel(resVo.getDisperseFuel().add(sumResVo.getDisperseFuel()));
                        resVo.setDisperseBase(resVo.getDisperseBase().add(sumResVo.getDisperseBase()));
                        resVo.setDisperseMaterial(resVo.getDisperseMaterial().add(sumResVo.getDisperseMaterial()));
                        resVo.setDisperseTotal(resVo.getDisperseTotal().add(sumResVo.getDisperseTotal()));
                        resVo.setProStrawUtilize(resVo.getProStrawUtilize().add(sumResVo.getProStrawUtilize()));
                        resVo.setCollectResource(resVo.getCollectResource().add(sumResVo.getCollectResource()));
                        resVo.setTheoryResource(resVo.getTheoryResource().add(sumResVo.getTheoryResource()));
                        resVo.setReturnResource(resVo.getReturnResource().add(sumResVo.getReturnResource()));
                        resVo.setFertilising(resVo.getFertilising().add(sumResVo.getFertilising()));
                        resVo.setForage(resVo.getForage().add(sumResVo.getForage()));
                        resVo.setFuel(resVo.getFuel().add(sumResVo.getFuel()));
                        resVo.setBase(resVo.getBase().add(sumResVo.getBase()));
                        resVo.setMaterial(resVo.getMaterial().add(sumResVo.getMaterial()));
                        resVo.setExportYieldTotal(resVo.getExportYieldTotal().add(sumResVo.getExportYieldTotal()));
                        resVo.setComprehensiveIndex(resVo.getComprehensiveIndex().add(sumResVo.getComprehensiveIndex()));
                        resVo.setIndustrializationIndex(resVo.getIndustrializationIndex().add(sumResVo.getIndustrializationIndex()));
                    } else {
                        pieChartVos.add(sumResVo);
                    }
                    codeList.add(sumResVo.getAreaId());
                }
                if (codeList.contains(Constants.XINJIANG) || codeList.contains(Constants.XINJIANG_CONSTRUCTION_CORPS)) {
                    resVo.setAreaId(Constants.XINJIANG);
                    pieChartVos.add(resVo);
                }
                utilizeByAreaCode.clear();
                utilizeByAreaCode.addAll(pieChartVos);
            }
        }
    }

    @Override
    public List<ColumnPieChartVo> getProStrawUtilizeGroupByStrawType(String year, String administrativeLevel, String areaCode, String dataType) {
        //校验用户等级
        Boolean b = checkOrgLevelByUserInData(administrativeLevel);
        boolean bol = checkIsReportInData(year, administrativeLevel, areaCode);
        if (b && bol) {
            List<String> areaCodeList = new ArrayList<>();
            if ("100000".equals(areaCode) && RegionLevel.MINISTRY.getCode().equals(administrativeLevel)) {
                getLowerAreaCode(administrativeLevel, areaCode, areaCodeList);
            } else {
                areaCodeList.add(areaCode);
            }
            List<String> status = new ArrayList<>();
            status.add(AuditStatusEnum.PASSED.getCode());
            status.add(AuditStatusEnum.REPORTED.getCode());
            Map<String, String> mapsByCodes = SysRegionUtil.getRegionNameMapsByCodes(IdUtil.getStrIdsByList(areaCodeList), year);
            //获取基本数据
            List<StrawUtilizeSum> sumByStrawType = dateShowMapper.getStrawSumGroupByStrawType(year, areaCodeList, status);
            //返回集合
            List<ColumnPieChartVo> chartList = new ArrayList<>();
            //秸秆类型
            List<SysDict> dictList = SysDictUtil.getDictList(Constants.DictionaryType.STRAW_TYPE);
            HashMap<String, String> strawTypeMap = new HashMap<>();
            for (SysDict sysDict : dictList) {
                strawTypeMap.put(sysDict.getDictcode(), sysDict.getDictname());
            }
            //获取五料化占比情况
            if (StrawUtilizeConditionEnum.STRAW_UTILIZE.getCode().equals(dataType)) {
                StrawUtilizeSumResVo strawResourceData = dateShowMapper.getStrawResourceData(year, areaCodeList, status);
                if (strawResourceData == null) {
                    return null;
                }
                BigDecimal fertilise = strawResourceData.getMainFertilising().add(strawResourceData.getDisperseFertilising()).add(strawResourceData.getReturnResource());
                ColumnPieChartVo fertiliseChart = new ColumnPieChartVo();
                fertiliseChart.setName("肥料化");
                fertiliseChart.setValue(fertilise);
                chartList.add(fertiliseChart);
                BigDecimal forage = strawResourceData.getMainForage().add(strawResourceData.getDisperseForage());
                ColumnPieChartVo forageChart = new ColumnPieChartVo();
                forageChart.setName("饲料化");
                forageChart.setValue(forage);
                chartList.add(forageChart);
                BigDecimal fuel = strawResourceData.getMainFuel().add(strawResourceData.getDisperseFuel());
                ColumnPieChartVo fuelChart = new ColumnPieChartVo();
                fuelChart.setName("燃料化");
                fuelChart.setValue(fuel);
                chartList.add(fuelChart);
                BigDecimal base = strawResourceData.getMainBase().add(strawResourceData.getDisperseBase());
                ColumnPieChartVo baseChart = new ColumnPieChartVo();
                baseChart.setName("基料化");
                baseChart.setValue(base);
                chartList.add(baseChart);
                BigDecimal material = strawResourceData.getMainMaterial().add(strawResourceData.getDisperseMaterial());
                ColumnPieChartVo materialChart = new ColumnPieChartVo();
                materialChart.setName("原料化");
                materialChart.setValue(material);
                chartList.add(materialChart);
                return chartList;
            }
            for (StrawUtilizeSum sum : sumByStrawType) {
                ColumnPieChartVo chartVo = new ColumnPieChartVo();
                //综合利用率
                if (StrawUtilizeConditionEnum.COMPREHENSIVE.getCode().equals(dataType)) {
                    // 综合利用率 =((本县秸秆利用量-收购外县的秸秆总量) + 调出秸秆量)/可收集量
                    if (sum.getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                        chartVo.setValue(BigDecimal.ZERO);
                    } else {
                        BigDecimal comprehensive = sum.getProStrawUtilize()
                                .divide(sum.getCollectResource(), 10, RoundingMode.HALF_UP);
                        chartVo.setValue(comprehensive);
                    }
                }
                //肥料化利用比例
                if (StrawUtilizeConditionEnum.FERTILISING.getCode().equals(dataType)) {
                    BigDecimal fertilising = sum.getMainFertilising().add(sum.getDisperseFertilising()).add(sum.getProStillField());
                    // 肥料化利用比例 =(市场主体利用量（肥料）+农户分散利用量（肥料）+直接还田量)/可收集量
                    if (sum.getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                        chartVo.setValue(BigDecimal.ZERO);
                    } else {
                        BigDecimal fertilisingScale = fertilising.divide(sum.getCollectResource(), 10, RoundingMode.HALF_UP);
                        chartVo.setValue(fertilisingScale);
                    }
                }
                //饲料化比例
                if (StrawUtilizeConditionEnum.FORAGE.getCode().equals(dataType)) {
                    BigDecimal forage = sum.getMainForage().add(sum.getDisperseForage());
                    // 肥料化利用比例 =(市场主体利用量（肥料）+农户分散利用量（肥料）+直接还田量)/可收集量
                    if (sum.getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                        chartVo.setValue(BigDecimal.ZERO);
                    } else {
                        BigDecimal forageScale = forage.divide(sum.getCollectResource(), 10, RoundingMode.HALF_UP);
                        chartVo.setValue(forageScale);
                    }
                }
                //燃料化比例
                if (StrawUtilizeConditionEnum.FUEL.getCode().equals(dataType)) {
                    BigDecimal fuel = sum.getMainFuel().add(sum.getDisperseFuel());
                    // 肥料化利用比例 =(市场主体利用量（肥料）+农户分散利用量（肥料）+直接还田量)/可收集量
                    if (sum.getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                        chartVo.setValue(BigDecimal.ZERO);
                    } else {
                        BigDecimal fuelScale = fuel.divide(sum.getCollectResource(), 10, RoundingMode.HALF_UP);
                        chartVo.setValue(fuelScale);
                    }
                }
                //基料化比例
                if (StrawUtilizeConditionEnum.BASE.getCode().equals(dataType)) {
                    BigDecimal base = sum.getMainBase().add(sum.getDisperseBase());
                    // 肥料化利用比例 =(市场主体利用量（肥料）+农户分散利用量（肥料）+直接还田量)/可收集量
                    if (sum.getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                        chartVo.setValue(BigDecimal.ZERO);
                    } else {
                        BigDecimal baseScale = base.divide(sum.getCollectResource(), 10, RoundingMode.HALF_UP);
                        chartVo.setValue(baseScale);
                    }
                }
                //原料化比例
                if (StrawUtilizeConditionEnum.MATERIAL.getCode().equals(dataType)) {
                    BigDecimal material = sum.getMainMaterial().add(sum.getDisperseMaterial());
                    // 肥料化利用比例 =(市场主体利用量（肥料）+农户分散利用量（肥料）+直接还田量)/可收集量
                    if (sum.getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                        chartVo.setValue(BigDecimal.ZERO);
                    } else {
                        BigDecimal materialScale = material.divide(sum.getCollectResource(), 10, RoundingMode.HALF_UP);
                        chartVo.setValue(materialScale);
                    }
                }
                if (StrawUtilizeConditionEnum.COMPREHENSIVE_INDEX.getCode().equals(dataType)) {
                    if (sum.getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                        chartVo.setValue(BigDecimal.ZERO);
                    } else {
                        BigDecimal comprehensiveIndex = (sum.getMainTotal().add(sum.getDisperseTotal()).add(sum.getProStillField()))
                                .divide(sum.getCollectResource(), 10, RoundingMode.HALF_UP);
                        chartVo.setValue(comprehensiveIndex);
                    }
                }
                //产业化利用能力指数
                if (StrawUtilizeConditionEnum.INDUSTRIALIZATION_INDEX.getCode().equals(dataType)) {
                    if (sum.getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                        chartVo.setValue(BigDecimal.ZERO);
                    } else {
                        BigDecimal industrializationIndex = (sum.getMainTotal()
                                .divide(sum.getCollectResource(), 10, RoundingMode.HALF_UP));
                        chartVo.setValue(industrializationIndex);
                    }
                }
                String name = strawTypeMap.get(sum.getStrawType());
                chartVo.setName(name);
                chartList.add(chartVo);
            }
            chartList.sort(new Comparator<ColumnPieChartVo>() {
                @Override
                public int compare(ColumnPieChartVo o1, ColumnPieChartVo o2) {
                    return o2.getValue().compareTo(o1.getValue());
                }
            });
            return chartList;
        }
        return null;
    }

    @Override
    public MapViewData getProStrawUtilizeMapView(String year, String administrativeLevel, String areaCode, String dataType) {
        //校验用户等级
        Boolean b = checkOrgLevelByUserInData(administrativeLevel);
        boolean bol = checkIsReportInData(year, administrativeLevel, areaCode);
        if (b && bol) {
            List<String> areaCodeList = new ArrayList<>();
            //当区域等级为县级时,只需查询自身
            if (RegionLevel.COUNTY.getCode().equals(administrativeLevel)) {
                areaCodeList.add(areaCode);
            } else {
                getLowerAreaCode(administrativeLevel, areaCode, areaCodeList);
            }
            List<String> status = new ArrayList<>();
            status.add(AuditStatusEnum.PASSED.getCode());
            status.add(AuditStatusEnum.REPORTED.getCode());
            Map<String, String> mapsByCodes = SysRegionUtil.getRegionNameMapsByCodes(IdUtil.getStrIdsByList(areaCodeList), year);
            //根据区域获取数据集合
            List<StrawUtilizeSumResVo> utilizeByAreaCode = dateShowMapper.getStrawUtilizeByAreaCode(year, areaCodeList, status);
            consolidateStrawSumSpecificData(administrativeLevel, utilizeByAreaCode);
            //处理数据
            List<AdPointData> pointDataArrayList = new ArrayList<>();
            MapViewData mapViewData = new MapViewData();
            for (StrawUtilizeSumResVo strawUtilizeSumResVo : utilizeByAreaCode) {
                AdPointData pointData = new AdPointData();
                //综合利用量
                if (StrawUtilizeConditionEnum.STRAW_UTILIZE.getCode().equals(dataType)) {
                    pointData.setIndexValue(strawUtilizeSumResVo.getProStrawUtilize().toString());
                }
                //利用率
                if (StrawUtilizeConditionEnum.COMPREHENSIVE.getCode().equals(dataType)) {
                    // 综合利用率 =((本县秸秆利用量-收购外县的秸秆总量) + 调出秸秆量)/可收集量
                    if (strawUtilizeSumResVo.getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                        pointData.setIndexValue(BigDecimal.ZERO.toString());
                    } else {
                        BigDecimal comprehensive = strawUtilizeSumResVo.getProStrawUtilize()
                                .divide(strawUtilizeSumResVo.getCollectResource(), 10, RoundingMode.HALF_UP);
                        pointData.setIndexValue(comprehensive.toString());
                    }
                }
                //肥料化利用比例
                if (StrawUtilizeConditionEnum.FERTILISING.getCode().equals(dataType)) {
                    BigDecimal fertilising = strawUtilizeSumResVo.getMainFertilising().add(strawUtilizeSumResVo.getDisperseFertilising()).add(strawUtilizeSumResVo.getReturnResource());
                    // 肥料化利用比例 =(市场主体利用量（肥料）+农户分散利用量（肥料）+直接还田量)/可收集量
                    if (strawUtilizeSumResVo.getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                        pointData.setIndexValue(BigDecimal.ZERO.toString());
                    } else {
                        BigDecimal fertilisingScale = fertilising.divide(strawUtilizeSumResVo.getCollectResource(), 10, RoundingMode.HALF_UP);
                        pointData.setIndexValue(fertilisingScale.toString());
                    }
                }
                //饲料化比例
                if (StrawUtilizeConditionEnum.FORAGE.getCode().equals(dataType)) {
                    BigDecimal forage = strawUtilizeSumResVo.getMainForage().add(strawUtilizeSumResVo.getDisperseForage());
                    // 肥料化利用比例 =(市场主体利用量（肥料）+农户分散利用量（肥料）+直接还田量)/可收集量
                    if (strawUtilizeSumResVo.getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                        pointData.setIndexValue(BigDecimal.ZERO.toString());
                    } else {
                        BigDecimal forageScale = forage.divide(strawUtilizeSumResVo.getCollectResource(), 10, RoundingMode.HALF_UP);
                        pointData.setIndexValue(forageScale.toString());
                    }
                }
                //燃料化比例
                if (StrawUtilizeConditionEnum.FUEL.getCode().equals(dataType)) {
                    BigDecimal fuel = strawUtilizeSumResVo.getMainFuel().add(strawUtilizeSumResVo.getDisperseFuel());
                    // 肥料化利用比例 =(市场主体利用量（肥料）+农户分散利用量（肥料）+直接还田量)/可收集量
                    if (strawUtilizeSumResVo.getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                        pointData.setIndexValue(BigDecimal.ZERO.toString());
                    } else {
                        BigDecimal fuelScale = fuel.divide(strawUtilizeSumResVo.getCollectResource(), 10, RoundingMode.HALF_UP);
                        pointData.setIndexValue(fuelScale.toString());
                    }
                }
                //基料化比例
                if (StrawUtilizeConditionEnum.BASE.getCode().equals(dataType)) {
                    BigDecimal base = strawUtilizeSumResVo.getMainBase().add(strawUtilizeSumResVo.getDisperseBase());
                    // 肥料化利用比例 =(市场主体利用量（肥料）+农户分散利用量（肥料）+直接还田量)/可收集量
                    if (strawUtilizeSumResVo.getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                        pointData.setIndexValue(BigDecimal.ZERO.toString());
                    } else {
                        BigDecimal baseScale = base.divide(strawUtilizeSumResVo.getCollectResource(), 10, RoundingMode.HALF_UP);
                        pointData.setIndexValue(baseScale.toString());
                    }
                }
                //原料化比例
                if (StrawUtilizeConditionEnum.MATERIAL.getCode().equals(dataType)) {
                    BigDecimal material = strawUtilizeSumResVo.getMainMaterial().add(strawUtilizeSumResVo.getDisperseMaterial());
                    // 肥料化利用比例 =(市场主体利用量（肥料）+农户分散利用量（肥料）+直接还田量)/可收集量
                    if (strawUtilizeSumResVo.getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                        pointData.setIndexValue(BigDecimal.ZERO.toString());
                    } else {
                        BigDecimal materialScale = material.divide(strawUtilizeSumResVo.getCollectResource(), 10, RoundingMode.HALF_UP);
                        pointData.setIndexValue(materialScale.toString());
                    }
                }
                if (StrawUtilizeConditionEnum.COMPREHENSIVE_INDEX.getCode().equals(dataType)) {
                    if (strawUtilizeSumResVo.getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                        pointData.setIndexValue(BigDecimal.ZERO.toString());
                    } else {
                        BigDecimal comprehensiveIndex = (strawUtilizeSumResVo.getMainTotal().add(strawUtilizeSumResVo.getDisperseTotal()).add(strawUtilizeSumResVo.getReturnResource()))
                                .divide(strawUtilizeSumResVo.getCollectResource(), 10, RoundingMode.HALF_UP);
                        pointData.setIndexValue(comprehensiveIndex.toString());
                    }
                }
                //产业化利用能力指数
                if (StrawUtilizeConditionEnum.INDUSTRIALIZATION_INDEX.getCode().equals(dataType)) {
                    if (strawUtilizeSumResVo.getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                        pointData.setIndexValue(BigDecimal.ZERO.toString());
                    } else {
                        BigDecimal industrializationIndex = (strawUtilizeSumResVo.getMainTotal()
                                .divide(strawUtilizeSumResVo.getCollectResource(), 10, RoundingMode.HALF_UP));
                        pointData.setIndexValue(industrializationIndex.toString());
                    }
                }
                pointData.setAdRegionName(mapsByCodes.get(strawUtilizeSumResVo.getAreaId()));
                pointData.setAdRegionCode(strawUtilizeSumResVo.getAreaId());
                pointDataArrayList.add(pointData);
            }
            HashMap<String, List<AdPointData>> listHashMap = new HashMap<>();
            listHashMap.put("adPointDataList", pointDataArrayList);
            mapViewData.setAdPointDataList(listHashMap);
            mapViewData.setAdLevel(administrativeLevel);
            mapViewData.setViewType("pie");
            return mapViewData;
        }
        return null;
    }

    @Override
    public List<ColumnPieChartVo> getProStrawUtilizeBySixRegions(String year, String administrativeLevel, String areaCode, String dataType, String strawType) {
        //如果区域id为空,则为全国六大区数据
        List<String> areaCodeList = new ArrayList<>();
        //获取当前区域下一级areacode
        List<SysRegionTreeVo> regionTree = sysApi.getSysRegionTreeById("100000", "ducss");
        if (CollectionUtils.isNotEmpty(regionTree)) {
            for (SysRegionTreeVo treeVo : regionTree) {
                areaCodeList.add(treeVo.getRegionCode());
            }
        }
        //获取全国秸秆产生量,或可收集量数据
        List<String> status = new ArrayList<>();
        status.add(AuditStatusEnum.PASSED.getCode());
        status.add(AuditStatusEnum.REPORTED.getCode());
        if (areaCode == null) {
            //华北区
            List<String> north_region_list = Arrays.asList(SixRegionEnum.NORTH_REGION.getCode().split(","));
            //长江区
            List<String> chang_jiang_river_region_list = Arrays.asList(SixRegionEnum.CHANG_JIANG_RIVER_REGION.getCode().split(","));
            //东北区
            List<String> northeast_region_list = Arrays.asList(SixRegionEnum.NORTHEAST_REGION.getCode().split(","));
            //西北区
            List<String> northwest_region_list = Arrays.asList(SixRegionEnum.NORTHWEST_REGION.getCode().split(","));
            //西南区
            List<String> southwest_region_list = Arrays.asList(SixRegionEnum.SOUTHWEST_REGION.getCode().split(","));
            //华南区
            List<String> south_region_list = Arrays.asList(SixRegionEnum.SOUTH_REGION.getCode().split(","));
            //返回数据集合
            List<ColumnPieChartVo> chartVoArrayList = new ArrayList<>();
            if ("all".equals(strawType)) {
                //查询全国数据
                //List<StrawUtilizeSumResVo> strawUtilize = dateShowMapper.getStrawUtilizeByAreaCode(year, areaCodeList, status);
                //获取六大区总数据
                List<SixRegionStrawUtilizeSum<StrawUtilizeSumResVo>> strawUtilizeSumList = new ArrayList<>();
                //根据六大区分条汇总
                //华北区
                StrawUtilizeSumResVo north_region_sum = dateShowMapper.getStrawResourceData(year, north_region_list, status);
                SixRegionStrawUtilizeSum<StrawUtilizeSumResVo> north_region_vo = new SixRegionStrawUtilizeSum<>();
                north_region_vo.setRegionName(SixRegionEnum.NORTH_REGION.getDescription());
                north_region_vo.setT(north_region_sum);
                strawUtilizeSumList.add(north_region_vo);
                //长江区
                StrawUtilizeSumResVo chang_jiang_river_region_sum = dateShowMapper.getStrawResourceData(year, chang_jiang_river_region_list, status);
                SixRegionStrawUtilizeSum<StrawUtilizeSumResVo> chang_jiang_river_region_vo = new SixRegionStrawUtilizeSum<>();
                chang_jiang_river_region_vo.setRegionName(SixRegionEnum.CHANG_JIANG_RIVER_REGION.getDescription());
                chang_jiang_river_region_vo.setT(chang_jiang_river_region_sum);
                strawUtilizeSumList.add(chang_jiang_river_region_vo);
                //东北区
                StrawUtilizeSumResVo northeast_region_sum = dateShowMapper.getStrawResourceData(year, northeast_region_list, status);
                SixRegionStrawUtilizeSum<StrawUtilizeSumResVo> northeast_region_vo = new SixRegionStrawUtilizeSum<>();
                northeast_region_vo.setRegionName(SixRegionEnum.NORTHEAST_REGION.getDescription());
                northeast_region_vo.setT(northeast_region_sum);
                strawUtilizeSumList.add(northeast_region_vo);
                //西北区
                StrawUtilizeSumResVo northwest_region_sum = dateShowMapper.getStrawResourceData(year, northwest_region_list, status);
                SixRegionStrawUtilizeSum<StrawUtilizeSumResVo> northwest_region_vo = new SixRegionStrawUtilizeSum<>();
                northwest_region_vo.setRegionName(SixRegionEnum.NORTHWEST_REGION.getDescription());
                northwest_region_vo.setT(northwest_region_sum);
                strawUtilizeSumList.add(northwest_region_vo);
                //西南区
                StrawUtilizeSumResVo southwest_region_sum = dateShowMapper.getStrawResourceData(year, southwest_region_list, status);
                SixRegionStrawUtilizeSum<StrawUtilizeSumResVo> southwest_region_vo = new SixRegionStrawUtilizeSum<>();
                southwest_region_vo.setRegionName(SixRegionEnum.SOUTHWEST_REGION.getDescription());
                southwest_region_vo.setT(southwest_region_sum);
                strawUtilizeSumList.add(southwest_region_vo);
                //华南区
                StrawUtilizeSumResVo south_region_sum = dateShowMapper.getStrawResourceData(year, south_region_list, status);
                SixRegionStrawUtilizeSum<StrawUtilizeSumResVo> south_region_vo = new SixRegionStrawUtilizeSum<>();
                south_region_vo.setRegionName(SixRegionEnum.SOUTH_REGION.getDescription());
                south_region_vo.setT(south_region_sum);
                strawUtilizeSumList.add(south_region_vo);

                for (SixRegionStrawUtilizeSum<StrawUtilizeSumResVo> strawUtilizeSumResVo : strawUtilizeSumList) {
                    if (strawUtilizeSumResVo.getT() != null) {
                        ColumnPieChartVo chartVo = new ColumnPieChartVo();
                        if (StrawUtilizeConditionEnum.STRAW_UTILIZE.getCode().equals(dataType)) {
                            chartVo.setValue(strawUtilizeSumResVo.getT().getProStrawUtilize());
                        }
                        if (StrawUtilizeConditionEnum.COMPREHENSIVE.getCode().equals(dataType)) {
                            //获取秸秆利用率
                            // 综合利用率 =((本县秸秆利用量-收购外县的秸秆总量) + 调出秸秆量)/可收集量
                            if (strawUtilizeSumResVo.getT().getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                                chartVo.setValue(BigDecimal.ZERO);
                            } else {
                                BigDecimal comprehensive = strawUtilizeSumResVo.getT().getProStrawUtilize()
                                        .divide(strawUtilizeSumResVo.getT().getCollectResource(), 10, RoundingMode.HALF_UP);
                                chartVo.setValue(comprehensive);
                            }
                        }
                        //肥料化利用比例
                        if (StrawUtilizeConditionEnum.FERTILISING.getCode().equals(dataType)) {
                            BigDecimal fertilising = strawUtilizeSumResVo.getT().getMainFertilising().add(strawUtilizeSumResVo.getT().getDisperseFertilising()).add(strawUtilizeSumResVo.getT().getProStillField());
                            // 肥料化利用比例 =(市场主体利用量（肥料）+农户分散利用量（肥料）+直接还田量)/可收集量
                            if (strawUtilizeSumResVo.getT().getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                                chartVo.setValue(BigDecimal.ZERO);
                            } else {
                                BigDecimal fertilisingScale = fertilising.divide(strawUtilizeSumResVo.getT().getCollectResource(), 10, RoundingMode.HALF_UP);
                                chartVo.setValue(fertilisingScale);
                            }
                        }
                        //饲料化比例
                        if (StrawUtilizeConditionEnum.FORAGE.getCode().equals(dataType)) {
                            BigDecimal forage = strawUtilizeSumResVo.getT().getMainForage().add(strawUtilizeSumResVo.getT().getDisperseForage());
                            // 肥料化利用比例 =(市场主体利用量（肥料）+农户分散利用量（肥料）+直接还田量)/可收集量
                            if (strawUtilizeSumResVo.getT().getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                                chartVo.setValue(BigDecimal.ZERO);
                            } else {
                                BigDecimal forageScale = forage.divide(strawUtilizeSumResVo.getT().getCollectResource(), 10, RoundingMode.HALF_UP);
                                chartVo.setValue(forageScale);
                            }
                        }
                        //燃料化比例
                        if (StrawUtilizeConditionEnum.FUEL.getCode().equals(dataType)) {
                            BigDecimal fuel = strawUtilizeSumResVo.getT().getMainFuel().add(strawUtilizeSumResVo.getT().getDisperseFuel());
                            // 肥料化利用比例 =(市场主体利用量（肥料）+农户分散利用量（肥料）+直接还田量)/可收集量
                            if (strawUtilizeSumResVo.getT().getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                                chartVo.setValue(BigDecimal.ZERO);
                            } else {
                                BigDecimal fuelScale = fuel.divide(strawUtilizeSumResVo.getT().getCollectResource(), 10, RoundingMode.HALF_UP);
                                chartVo.setValue(fuelScale);
                            }
                        }
                        //基料化比例
                        if (StrawUtilizeConditionEnum.BASE.getCode().equals(dataType)) {
                            BigDecimal base = strawUtilizeSumResVo.getT().getMainBase().add(strawUtilizeSumResVo.getT().getDisperseBase());
                            // 肥料化利用比例 =(市场主体利用量（肥料）+农户分散利用量（肥料）+直接还田量)/可收集量
                            if (strawUtilizeSumResVo.getT().getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                                chartVo.setValue(BigDecimal.ZERO);
                            } else {
                                BigDecimal baseScale = base.divide(strawUtilizeSumResVo.getT().getCollectResource(), 10, RoundingMode.HALF_UP);
                                chartVo.setValue(baseScale);
                            }
                        }
                        //原料化比例
                        if (StrawUtilizeConditionEnum.MATERIAL.getCode().equals(dataType)) {
                            BigDecimal material = strawUtilizeSumResVo.getT().getMainMaterial().add(strawUtilizeSumResVo.getT().getDisperseMaterial());
                            // 肥料化利用比例 =(市场主体利用量（肥料）+农户分散利用量（肥料）+直接还田量)/可收集量
                            if (strawUtilizeSumResVo.getT().getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                                chartVo.setValue(BigDecimal.ZERO);
                            } else {
                                BigDecimal materialScale = material.divide(strawUtilizeSumResVo.getT().getCollectResource(), 10, RoundingMode.HALF_UP);
                                chartVo.setValue(materialScale);
                            }
                        }
                        if (StrawUtilizeConditionEnum.COMPREHENSIVE_INDEX.getCode().equals(dataType)) {
                            if (strawUtilizeSumResVo.getT().getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                                chartVo.setValue(BigDecimal.ZERO);
                            } else {
                                BigDecimal comprehensiveIndex = (strawUtilizeSumResVo.getT().getMainTotal().add(strawUtilizeSumResVo.getT().getDisperseTotal()).add(strawUtilizeSumResVo.getT().getProStillField()))
                                        .divide(strawUtilizeSumResVo.getT().getCollectResource(), 10, RoundingMode.HALF_UP);
                                chartVo.setValue(comprehensiveIndex);
                            }
                        }
                        //产业化利用能力指数
                        if (StrawUtilizeConditionEnum.INDUSTRIALIZATION_INDEX.getCode().equals(dataType)) {
                            if (strawUtilizeSumResVo.getT().getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                                chartVo.setValue(BigDecimal.ZERO);
                            } else {
                                BigDecimal industrializationIndex = (strawUtilizeSumResVo.getT().getMainTotal()
                                        .divide(strawUtilizeSumResVo.getT().getCollectResource(), 10, RoundingMode.HALF_UP));
                                chartVo.setValue(industrializationIndex);
                            }
                        }
                        //设置名称
                        chartVo.setName(strawUtilizeSumResVo.getRegionName());
                        chartVoArrayList.add(chartVo);
                    }

                }
                chartVoArrayList.sort(new Comparator<ColumnPieChartVo>() {
                    @Override
                    public int compare(ColumnPieChartVo o1, ColumnPieChartVo o2) {
                        return o2.getValue().compareTo(o1.getValue());
                    }
                });
                return chartVoArrayList;
            } else { //获取六大区总数据
                //选定指定秸秆类型获取单个作物的8种类型数据
                //获取六大区总数据
                List<SixRegionStrawUtilizeSum<StrawUtilizeSum>> strawUtilizeSumList = new ArrayList<>();
                //根据六大区分条汇总
                //华北区
                StrawUtilizeSum north_region_sum = dateShowMapper.getStrawSum(year, north_region_list, status, strawType);
                SixRegionStrawUtilizeSum<StrawUtilizeSum> north_region_vo = new SixRegionStrawUtilizeSum<>();
                north_region_vo.setRegionName(SixRegionEnum.NORTH_REGION.getDescription());
                north_region_vo.setT(north_region_sum);
                strawUtilizeSumList.add(north_region_vo);
                //长江区
                StrawUtilizeSum chang_jiang_river_region_sum = dateShowMapper.getStrawSum(year, chang_jiang_river_region_list, status, strawType);
                SixRegionStrawUtilizeSum<StrawUtilizeSum> chang_jiang_river_region_vo = new SixRegionStrawUtilizeSum<>();
                chang_jiang_river_region_vo.setRegionName(SixRegionEnum.CHANG_JIANG_RIVER_REGION.getDescription());
                chang_jiang_river_region_vo.setT(chang_jiang_river_region_sum);
                strawUtilizeSumList.add(chang_jiang_river_region_vo);
                //东北区
                StrawUtilizeSum northeast_region_sum = dateShowMapper.getStrawSum(year, northeast_region_list, status, strawType);
                SixRegionStrawUtilizeSum<StrawUtilizeSum> northeast_region_sum_vo = new SixRegionStrawUtilizeSum<>();
                northeast_region_sum_vo.setRegionName(SixRegionEnum.NORTHEAST_REGION.getDescription());
                northeast_region_sum_vo.setT(northeast_region_sum);
                strawUtilizeSumList.add(northeast_region_sum_vo);
                //西北区
                StrawUtilizeSum northwest_region_sum = dateShowMapper.getStrawSum(year, northwest_region_list, status, strawType);
                SixRegionStrawUtilizeSum<StrawUtilizeSum> northwest_region_sum_vo = new SixRegionStrawUtilizeSum<>();
                northwest_region_sum_vo.setRegionName(SixRegionEnum.NORTHWEST_REGION.getDescription());
                northwest_region_sum_vo.setT(northwest_region_sum);
                strawUtilizeSumList.add(northwest_region_sum_vo);
                //西南区
                StrawUtilizeSum southwest_region_sum = dateShowMapper.getStrawSum(year, southwest_region_list, status, strawType);
                SixRegionStrawUtilizeSum<StrawUtilizeSum> southwest_region_sum_vo = new SixRegionStrawUtilizeSum<>();
                southwest_region_sum_vo.setRegionName(SixRegionEnum.SOUTHWEST_REGION.getDescription());
                southwest_region_sum_vo.setT(southwest_region_sum);
                strawUtilizeSumList.add(southwest_region_sum_vo);
                //华南区
                StrawUtilizeSum south_region_sum = dateShowMapper.getStrawSum(year, south_region_list, status, strawType);
                SixRegionStrawUtilizeSum<StrawUtilizeSum> south_region_sum_vo = new SixRegionStrawUtilizeSum<>();
                south_region_sum_vo.setRegionName(SixRegionEnum.SOUTH_REGION.getDescription());
                south_region_sum_vo.setT(south_region_sum);
                strawUtilizeSumList.add(south_region_sum_vo);
                for (SixRegionStrawUtilizeSum<StrawUtilizeSum> strawUtilizeSum : strawUtilizeSumList) {
                    if (strawUtilizeSum.getT() != null) {
                        ColumnPieChartVo chartVo = new ColumnPieChartVo();
                        if (StrawUtilizeConditionEnum.COMPREHENSIVE.getCode().equals(dataType)) {
                            //获取秸秆利用率
                            // 综合利用率 =((本县秸秆利用量-收购外县的秸秆总量) + 调出秸秆量)/可收集量
                            if (strawUtilizeSum.getT().getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                                chartVo.setValue(BigDecimal.ZERO);
                            } else {
                                BigDecimal comprehensive = strawUtilizeSum.getT().getProStrawUtilize()
                                        .divide(strawUtilizeSum.getT().getCollectResource(), 10, RoundingMode.HALF_UP);
                                chartVo.setValue(comprehensive);
                            }
                        }
                        //肥料化利用比例
                        if (StrawUtilizeConditionEnum.FERTILISING.getCode().equals(dataType)) {
                            BigDecimal fertilising = strawUtilizeSum.getT().getMainFertilising().add(strawUtilizeSum.getT().getDisperseFertilising()).add(strawUtilizeSum.getT().getProStillField());
                            // 肥料化利用比例 =(市场主体利用量（肥料）+农户分散利用量（肥料）+直接还田量)/可收集量
                            if (strawUtilizeSum.getT().getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                                chartVo.setValue(BigDecimal.ZERO);
                            } else {
                                BigDecimal fertilisingScale = fertilising.divide(strawUtilizeSum.getT().getCollectResource(), 10, RoundingMode.HALF_UP);
                                chartVo.setValue(fertilisingScale);
                            }
                        }
                        //饲料化比例
                        if (StrawUtilizeConditionEnum.FORAGE.getCode().equals(dataType)) {
                            BigDecimal forage = strawUtilizeSum.getT().getMainForage().add(strawUtilizeSum.getT().getDisperseForage());
                            // 肥料化利用比例 =(市场主体利用量（肥料）+农户分散利用量（肥料）+直接还田量)/可收集量
                            if (strawUtilizeSum.getT().getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                                chartVo.setValue(BigDecimal.ZERO);
                            } else {
                                BigDecimal forageScale = forage.divide(strawUtilizeSum.getT().getCollectResource(), 10, RoundingMode.HALF_UP);
                                chartVo.setValue(forageScale);
                            }
                        }
                        //燃料化比例
                        if (StrawUtilizeConditionEnum.FUEL.getCode().equals(dataType)) {
                            BigDecimal fuel = strawUtilizeSum.getT().getMainFuel().add(strawUtilizeSum.getT().getDisperseFuel());
                            // 肥料化利用比例 =(市场主体利用量（肥料）+农户分散利用量（肥料）+直接还田量)/可收集量
                            if (strawUtilizeSum.getT().getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                                chartVo.setValue(BigDecimal.ZERO);
                            } else {
                                BigDecimal fuelScale = fuel.divide(strawUtilizeSum.getT().getCollectResource(), 10, RoundingMode.HALF_UP);
                                chartVo.setValue(fuelScale);
                            }
                        }
                        //基料化比例
                        if (StrawUtilizeConditionEnum.BASE.getCode().equals(dataType)) {
                            BigDecimal base = strawUtilizeSum.getT().getMainBase().add(strawUtilizeSum.getT().getDisperseBase());
                            // 肥料化利用比例 =(市场主体利用量（肥料）+农户分散利用量（肥料）+直接还田量)/可收集量
                            if (strawUtilizeSum.getT().getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                                chartVo.setValue(BigDecimal.ZERO);
                            } else {
                                BigDecimal baseScale = base.divide(strawUtilizeSum.getT().getCollectResource(), 10, RoundingMode.HALF_UP);
                                chartVo.setValue(baseScale);
                            }
                        }
                        //原料化比例
                        if (StrawUtilizeConditionEnum.MATERIAL.getCode().equals(dataType)) {
                            BigDecimal material = strawUtilizeSum.getT().getMainMaterial().add(strawUtilizeSum.getT().getDisperseMaterial());
                            // 肥料化利用比例 =(市场主体利用量（肥料）+农户分散利用量（肥料）+直接还田量)/可收集量
                            if (strawUtilizeSum.getT().getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                                chartVo.setValue(BigDecimal.ZERO);
                            } else {
                                BigDecimal materialScale = material.divide(strawUtilizeSum.getT().getCollectResource(), 10, RoundingMode.HALF_UP);
                                chartVo.setValue(materialScale);
                            }
                        }
                        if (StrawUtilizeConditionEnum.COMPREHENSIVE_INDEX.getCode().equals(dataType)) {
                            if (strawUtilizeSum.getT().getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                                chartVo.setValue(BigDecimal.ZERO);
                            } else {
                                BigDecimal comprehensiveIndex = (strawUtilizeSum.getT().getMainTotal().add(strawUtilizeSum.getT().getDisperseTotal()).add(strawUtilizeSum.getT().getProStillField()))
                                        .divide(strawUtilizeSum.getT().getCollectResource(), 10, RoundingMode.HALF_UP);
                                chartVo.setValue(comprehensiveIndex);
                            }
                        }
                        //产业化利用能力指数
                        if (StrawUtilizeConditionEnum.INDUSTRIALIZATION_INDEX.getCode().equals(dataType)) {
                            if (strawUtilizeSum.getT().getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                                chartVo.setValue(BigDecimal.ZERO);
                            } else {
                                BigDecimal industrializationIndex = (strawUtilizeSum.getT().getMainTotal()
                                        .divide(strawUtilizeSum.getT().getCollectResource(), 10, RoundingMode.HALF_UP));
                                chartVo.setValue(industrializationIndex);
                            }
                        }
                        chartVo.setName(strawUtilizeSum.getRegionName());
                        chartVoArrayList.add(chartVo);
                    }
                }
                chartVoArrayList.sort(new Comparator<ColumnPieChartVo>() {
                    @Override
                    public int compare(ColumnPieChartVo o1, ColumnPieChartVo o2) {
                        return o2.getValue().compareTo(o1.getValue());
                    }
                });
                return chartVoArrayList;
            }
        } else {
            //返回数据集合
            List<ColumnPieChartVo> chartVoArrayList = new ArrayList<>();
            //选定六大分区之一
            areaCodeList = Arrays.asList(areaCode.split(","));
            Map<String, String> mapsByCodes = SysRegionUtil.getRegionNameMapsByCodes(IdUtil.getStrIdsByList(areaCodeList), year);
            //秸秆类型
            List<SysDict> dictList = SysDictUtil.getDictList(Constants.DictionaryType.STRAW_TYPE);
            HashMap<String, String> strawTypeMap = new HashMap<>();
            for (SysDict sysDict : dictList) {
                strawTypeMap.put(sysDict.getDictcode(), sysDict.getDictname());
            }
            //如果是综合利用量或综合利用率
            if (StrawUtilizeConditionEnum.STRAW_UTILIZE.getCode().equals(dataType) || StrawUtilizeConditionEnum.COMPREHENSIVE.getCode().equals(dataType)) {
                List<StrawUtilizeSum> sumGroupByStrawType = dateShowMapper.getStrawSumGroupByStrawType(year, areaCodeList, status);
                //处理新疆及新疆建设兵团
                this.consolidateSumSpecificData(administrativeLevel, sumGroupByStrawType);
                for (StrawUtilizeSum strawUtilizeSum : sumGroupByStrawType) {
                    ColumnPieChartVo chartVo = new ColumnPieChartVo();
                    if (StrawUtilizeConditionEnum.STRAW_UTILIZE.getCode().equals(dataType)) {
                        chartVo.setValue(strawUtilizeSum.getProStrawUtilize());
                    }
                    if (StrawUtilizeConditionEnum.COMPREHENSIVE.getCode().equals(dataType)) {
                        //获取秸秆利用率
                        // 综合利用率 =((本县秸秆利用量-收购外县的秸秆总量) + 调出秸秆量)/可收集量
                        if (strawUtilizeSum.getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                            chartVo.setValue(BigDecimal.ZERO);
                        } else {
                            BigDecimal comprehensive = strawUtilizeSum.getProStrawUtilize()
                                    .divide(strawUtilizeSum.getCollectResource(), 10, RoundingMode.HALF_UP);
                            chartVo.setValue(comprehensive);
                        }
                    }
                    chartVo.setName(strawTypeMap.get(strawUtilizeSum.getStrawType()));
                    chartVoArrayList.add(chartVo);
                }
                chartVoArrayList.sort(new Comparator<ColumnPieChartVo>() {
                    @Override
                    public int compare(ColumnPieChartVo o1, ColumnPieChartVo o2) {
                        return o2.getValue().compareTo(o1.getValue());
                    }
                });
                return chartVoArrayList;
            } else {
                //如果是肥料化利用比例（其余4料化类似）以及综合利用能力指数及产业化利用能力指数  则展示该分区下各省的数据
                List<StrawUtilizeSumResVo> strawUtilizeByAreaCode = dateShowMapper.getStrawUtilizeByAreaCode(year, areaCodeList, status);
                consolidateStrawSumSpecificData(administrativeLevel, strawUtilizeByAreaCode);
                for (StrawUtilizeSumResVo strawUtilizeSumResVo : strawUtilizeByAreaCode) {
                    ColumnPieChartVo chartVo = new ColumnPieChartVo();
                    //肥料化利用比例
                    if (StrawUtilizeConditionEnum.FERTILISING.getCode().equals(dataType)) {
                        BigDecimal fertilising = strawUtilizeSumResVo.getMainFertilising().add(strawUtilizeSumResVo.getDisperseFertilising()).add(strawUtilizeSumResVo.getProStillField());
                        // 肥料化利用比例 =(市场主体利用量（肥料）+农户分散利用量（肥料）+直接还田量)/可收集量
                        if (strawUtilizeSumResVo.getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                            chartVo.setValue(BigDecimal.ZERO);
                        } else {
                            BigDecimal fertilisingScale = fertilising.divide(strawUtilizeSumResVo.getCollectResource(), 10, RoundingMode.HALF_UP);
                            chartVo.setValue(fertilisingScale);
                        }
                    }
                    //饲料化比例
                    if (StrawUtilizeConditionEnum.FORAGE.getCode().equals(dataType)) {
                        BigDecimal forage = strawUtilizeSumResVo.getMainForage().add(strawUtilizeSumResVo.getDisperseForage());
                        // 肥料化利用比例 =(市场主体利用量（肥料）+农户分散利用量（肥料）+直接还田量)/可收集量
                        if (strawUtilizeSumResVo.getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                            chartVo.setValue(BigDecimal.ZERO);
                        } else {
                            BigDecimal forageScale = forage.divide(strawUtilizeSumResVo.getCollectResource(), 10, RoundingMode.HALF_UP);
                            chartVo.setValue(forageScale);
                        }
                    }
                    //燃料化比例
                    if (StrawUtilizeConditionEnum.FUEL.getCode().equals(dataType)) {
                        BigDecimal fuel = strawUtilizeSumResVo.getMainFuel().add(strawUtilizeSumResVo.getDisperseFuel());
                        // 肥料化利用比例 =(市场主体利用量（肥料）+农户分散利用量（肥料）+直接还田量)/可收集量
                        if (strawUtilizeSumResVo.getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                            chartVo.setValue(BigDecimal.ZERO);
                        } else {
                            BigDecimal fuelScale = fuel.divide(strawUtilizeSumResVo.getCollectResource(), 10, RoundingMode.HALF_UP);
                            chartVo.setValue(fuelScale);
                        }
                    }
                    //基料化比例
                    if (StrawUtilizeConditionEnum.BASE.getCode().equals(dataType)) {
                        BigDecimal base = strawUtilizeSumResVo.getMainBase().add(strawUtilizeSumResVo.getDisperseBase());
                        // 肥料化利用比例 =(市场主体利用量（肥料）+农户分散利用量（肥料）+直接还田量)/可收集量
                        if (strawUtilizeSumResVo.getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                            chartVo.setValue(BigDecimal.ZERO);
                        } else {
                            BigDecimal baseScale = base.divide(strawUtilizeSumResVo.getCollectResource(), 10, RoundingMode.HALF_UP);
                            chartVo.setValue(baseScale);
                        }
                    }
                    //原料化比例
                    if (StrawUtilizeConditionEnum.MATERIAL.getCode().equals(dataType)) {
                        BigDecimal material = strawUtilizeSumResVo.getMainMaterial().add(strawUtilizeSumResVo.getDisperseMaterial());
                        // 肥料化利用比例 =(市场主体利用量（肥料）+农户分散利用量（肥料）+直接还田量)/可收集量
                        if (strawUtilizeSumResVo.getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                            chartVo.setValue(BigDecimal.ZERO);
                        } else {
                            BigDecimal materialScale = material.divide(strawUtilizeSumResVo.getCollectResource(), 10, RoundingMode.HALF_UP);
                            chartVo.setValue(materialScale);
                        }
                    }
                    if (StrawUtilizeConditionEnum.COMPREHENSIVE_INDEX.getCode().equals(dataType)) {
                        if (strawUtilizeSumResVo.getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                            chartVo.setValue(BigDecimal.ZERO);
                        } else {
                            BigDecimal comprehensiveIndex = (strawUtilizeSumResVo.getMainTotal().add(strawUtilizeSumResVo.getDisperseTotal()).add(strawUtilizeSumResVo.getProStillField()))
                                    .divide(strawUtilizeSumResVo.getCollectResource(), 10, RoundingMode.HALF_UP);
                            chartVo.setValue(comprehensiveIndex);
                        }
                    }
                    //产业化利用能力指数
                    if (StrawUtilizeConditionEnum.INDUSTRIALIZATION_INDEX.getCode().equals(dataType)) {
                        if (strawUtilizeSumResVo.getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                            chartVo.setValue(BigDecimal.ZERO);
                        } else {
                            BigDecimal industrializationIndex = (strawUtilizeSumResVo.getMainTotal()
                                    .divide(strawUtilizeSumResVo.getCollectResource(), 10, RoundingMode.HALF_UP));
                            chartVo.setValue(industrializationIndex);
                        }
                    }
                    chartVo.setName(mapsByCodes.get(strawUtilizeSumResVo.getAreaId()));
                    chartVoArrayList.add(chartVo);
                }
                chartVoArrayList.sort(new Comparator<ColumnPieChartVo>() {
                    @Override
                    public int compare(ColumnPieChartVo o1, ColumnPieChartVo o2) {
                        return o2.getValue().compareTo(o1.getValue());
                    }
                });
                return chartVoArrayList;
            }
        }
    }

    @Override
    public List<ColumnPieChartVo> getProStrawUtilizeBySixRegionsTwo(String year, String administrativeLevel, String areaCode, String dataType, String strawType) {
        //校验用户等级
        Boolean b = checkOrgLevelByUserInData(administrativeLevel);
        boolean bol = checkIsReportInData(year, administrativeLevel, areaCode);
        if (b && bol) {
            List<String> areaCodeList = new ArrayList<>();
            //获取当前区域下一级areacode
            List<SysRegionTreeVo> regionTree = sysApi.getSysRegionTreeById("100000", "ducss");
            if (CollectionUtils.isNotEmpty(regionTree)) {
                for (SysRegionTreeVo treeVo : regionTree) {
                    areaCodeList.add(treeVo.getRegionCode());
                }
            }
            //秸秆类型
            List<SysDict> dictList = SysDictUtil.getDictList(Constants.DictionaryType.STRAW_TYPE);
            HashMap<String, String> strawTypeMap = new HashMap<>();
            for (SysDict sysDict : dictList) {
                strawTypeMap.put(sysDict.getDictcode(), sysDict.getDictname());
            }
            //获取全国秸秆产生量,或可收集量数据
            List<String> status = new ArrayList<>();
            status.add(AuditStatusEnum.PASSED.getCode());
            status.add(AuditStatusEnum.REPORTED.getCode());
            //当区域id为空时,展示六大区
            if (areaCode == null) {
                //华北区
                List<String> north_region_list = Arrays.asList(SixRegionEnum.NORTH_REGION.getCode().split(","));
                //长江区
                List<String> chang_jiang_river_region_list = Arrays.asList(SixRegionEnum.CHANG_JIANG_RIVER_REGION.getCode().split(","));
                //东北区
                List<String> northeast_region_list = Arrays.asList(SixRegionEnum.NORTHEAST_REGION.getCode().split(","));
                //西北区
                List<String> northwest_region_list = Arrays.asList(SixRegionEnum.NORTHWEST_REGION.getCode().split(","));
                //西南区
                List<String> southwest_region_list = Arrays.asList(SixRegionEnum.SOUTHWEST_REGION.getCode().split(","));
                //华南区
                List<String> south_region_list = Arrays.asList(SixRegionEnum.SOUTH_REGION.getCode().split(","));
                //返回数据集合
                List<ColumnPieChartVo> chartVoArrayList = new ArrayList<>();
                //当秸秆类型为空时,及strawType传all 时,展示六大区数据
                if ("all".equals(strawType)) {
                    if (StrawUtilizeConditionEnum.STRAW_UTILIZE.getCode().equals(dataType) || StrawUtilizeConditionEnum.COMPREHENSIVE.getCode().equals(dataType)) {
                        //获取六大区总数据
                        List<SixRegionStrawUtilizeSum<StrawUtilizeSumResVo>> strawUtilizeSumList = new ArrayList<>();
                        //根据六大区分条汇总
                        //华北区
                        StrawUtilizeSumResVo north_region_sum = dateShowMapper.getStrawResourceData(year, north_region_list, status);
                        SixRegionStrawUtilizeSum<StrawUtilizeSumResVo> north_region_vo = new SixRegionStrawUtilizeSum<>();
                        north_region_vo.setRegionName(SixRegionEnum.NORTH_REGION.getDescription());
                        north_region_vo.setT(north_region_sum);
                        strawUtilizeSumList.add(north_region_vo);
                        //长江区
                        StrawUtilizeSumResVo chang_jiang_river_region_sum = dateShowMapper.getStrawResourceData(year, chang_jiang_river_region_list, status);
                        SixRegionStrawUtilizeSum<StrawUtilizeSumResVo> chang_jiang_river_region_vo = new SixRegionStrawUtilizeSum<>();
                        chang_jiang_river_region_vo.setRegionName(SixRegionEnum.CHANG_JIANG_RIVER_REGION.getDescription());
                        chang_jiang_river_region_vo.setT(chang_jiang_river_region_sum);
                        strawUtilizeSumList.add(chang_jiang_river_region_vo);
                        //东北区
                        StrawUtilizeSumResVo northeast_region_sum = dateShowMapper.getStrawResourceData(year, northeast_region_list, status);
                        SixRegionStrawUtilizeSum<StrawUtilizeSumResVo> northeast_region_vo = new SixRegionStrawUtilizeSum<>();
                        northeast_region_vo.setRegionName(SixRegionEnum.NORTHEAST_REGION.getDescription());
                        northeast_region_vo.setT(northeast_region_sum);
                        strawUtilizeSumList.add(northeast_region_vo);
                        //西北区
                        StrawUtilizeSumResVo northwest_region_sum = dateShowMapper.getStrawResourceData(year, northwest_region_list, status);
                        SixRegionStrawUtilizeSum<StrawUtilizeSumResVo> northwest_region_vo = new SixRegionStrawUtilizeSum<>();
                        northwest_region_vo.setRegionName(SixRegionEnum.NORTHWEST_REGION.getDescription());
                        northwest_region_vo.setT(northwest_region_sum);
                        strawUtilizeSumList.add(northwest_region_vo);
                        //西南区
                        StrawUtilizeSumResVo southwest_region_sum = dateShowMapper.getStrawResourceData(year, southwest_region_list, status);
                        SixRegionStrawUtilizeSum<StrawUtilizeSumResVo> southwest_region_vo = new SixRegionStrawUtilizeSum<>();
                        southwest_region_vo.setRegionName(SixRegionEnum.SOUTHWEST_REGION.getDescription());
                        southwest_region_vo.setT(southwest_region_sum);
                        strawUtilizeSumList.add(southwest_region_vo);
                        //华南区
                        StrawUtilizeSumResVo south_region_sum = dateShowMapper.getStrawResourceData(year, south_region_list, status);
                        SixRegionStrawUtilizeSum<StrawUtilizeSumResVo> south_region_vo = new SixRegionStrawUtilizeSum<>();
                        south_region_vo.setRegionName(SixRegionEnum.SOUTH_REGION.getDescription());
                        south_region_vo.setT(south_region_sum);
                        strawUtilizeSumList.add(south_region_vo);
                        //综合利用量
                        if (StrawUtilizeConditionEnum.STRAW_UTILIZE.getCode().equals(dataType)) {
                            for (SixRegionStrawUtilizeSum<StrawUtilizeSumResVo> strawUtilizeSumResVo : strawUtilizeSumList) {
                                ColumnPieChartVo chartVo = new ColumnPieChartVo();
                                if (strawUtilizeSumResVo.getT() != null) {
                                    chartVo.setValue(strawUtilizeSumResVo.getT().getProStrawUtilize());
                                    chartVo.setName(strawUtilizeSumResVo.getRegionName());
                                    chartVoArrayList.add(chartVo);
                                }
                            }
                        }
                        //五料化利用比例，各自五料化除了可收集量
                        if (StrawUtilizeConditionEnum.COMPREHENSIVE.getCode().equals(dataType)) {
                            StrawUtilizeSumResVo strawResourceALL = dateShowMapper.getStrawResourceData(year, areaCodeList, status);
                            if (strawResourceALL != null) {
                                //肥料化fertilising
                                ColumnPieChartVo fertilisingChart = new ColumnPieChartVo();
                                fertilisingChart.setName("肥料化");
                                fertilisingChart.setValue((strawResourceALL.getMainFertilising().add(strawResourceALL.getDisperseFertilising()).add(strawResourceALL.getReturnResource())).divide(strawResourceALL.getCollectResource(), 10, RoundingMode.HALF_UP));
                                //饲料化forage
                                ColumnPieChartVo forageChart = new ColumnPieChartVo();
                                forageChart.setName("饲料化");
                                forageChart.setValue((strawResourceALL.getDisperseForage().add(strawResourceALL.getMainForage())).divide(strawResourceALL.getCollectResource(), 10, RoundingMode.HALF_UP));
                                //燃料化fuel
                                ColumnPieChartVo fuelChart = new ColumnPieChartVo();
                                fuelChart.setName("燃料化");
                                fuelChart.setValue((strawResourceALL.getDisperseFuel().add(strawResourceALL.getMainFuel())).divide(strawResourceALL.getCollectResource(), 10, RoundingMode.HALF_UP));
                                //基料化base
                                ColumnPieChartVo baseChart = new ColumnPieChartVo();
                                baseChart.setName("基料化");
                                baseChart.setValue((strawResourceALL.getDisperseBase().add(strawResourceALL.getMainBase())).divide(strawResourceALL.getCollectResource(), 10, RoundingMode.HALF_UP));
                                //原料化material
                                ColumnPieChartVo materialChart = new ColumnPieChartVo();
                                materialChart.setName("原料化");
                                materialChart.setValue((strawResourceALL.getDisperseMaterial().add(strawResourceALL.getMainMaterial())).divide(strawResourceALL.getCollectResource(), 10, RoundingMode.HALF_UP));
                                chartVoArrayList.add(fertilisingChart);
                                chartVoArrayList.add(forageChart);
                                chartVoArrayList.add(fuelChart);
                                chartVoArrayList.add(baseChart);
                                chartVoArrayList.add(materialChart);
                            }
                        }
                    }
                    //查询六大区数据根据14种作物分组(维度为14种作物)
                    if (StrawUtilizeConditionEnum.FERTILISING.getCode().equals(dataType) || StrawUtilizeConditionEnum.FORAGE.getCode().equals(dataType) ||
                            StrawUtilizeConditionEnum.FUEL.getCode().equals(dataType) || StrawUtilizeConditionEnum.BASE.getCode().equals(dataType)
                            || StrawUtilizeConditionEnum.MATERIAL.getCode().equals(dataType) || StrawUtilizeConditionEnum.COMPREHENSIVE_INDEX.getCode().equals(dataType)
                            || StrawUtilizeConditionEnum.INDUSTRIALIZATION_INDEX.getCode().equals(dataType)
                    ) {
                        List<StrawUtilizeSum> sumGroupByStrawType = dateShowMapper.getStrawSumGroupByStrawType(year, areaCodeList, status);
                        for (StrawUtilizeSum sum : sumGroupByStrawType) {
                            ColumnPieChartVo chartVo = new ColumnPieChartVo();
                            //肥料化利用比例
                            if (StrawUtilizeConditionEnum.FERTILISING.getCode().equals(dataType)) {
                                BigDecimal fertilising = sum.getMainFertilising().add(sum.getDisperseFertilising()).add(sum.getProStillField());
                                // 肥料化利用比例 =(市场主体利用量（肥料）+农户分散利用量（肥料）+直接还田量)/可收集量
                                if (sum.getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                                    chartVo.setValue(BigDecimal.ZERO);
                                } else {
                                    BigDecimal fertilisingScale = fertilising.divide(sum.getCollectResource(), 10, RoundingMode.HALF_UP);
                                    chartVo.setValue(fertilisingScale);
                                }
                            }
                            //饲料化比例
                            if (StrawUtilizeConditionEnum.FORAGE.getCode().equals(dataType)) {
                                BigDecimal forage = sum.getMainForage().add(sum.getDisperseForage());
                                // 肥料化利用比例 =(市场主体利用量（肥料）+农户分散利用量（肥料）+直接还田量)/可收集量
                                if (sum.getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                                    chartVo.setValue(BigDecimal.ZERO);
                                } else {
                                    BigDecimal forageScale = forage.divide(sum.getCollectResource(), 10, RoundingMode.HALF_UP);
                                    chartVo.setValue(forageScale);
                                }
                            }
                            //燃料化比例
                            if (StrawUtilizeConditionEnum.FUEL.getCode().equals(dataType)) {
                                BigDecimal fuel = sum.getMainFuel().add(sum.getDisperseFuel());
                                // 肥料化利用比例 =(市场主体利用量（肥料）+农户分散利用量（肥料）+直接还田量)/可收集量
                                if (sum.getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                                    chartVo.setValue(BigDecimal.ZERO);
                                } else {
                                    BigDecimal fuelScale = fuel.divide(sum.getCollectResource(), 10, RoundingMode.HALF_UP);
                                    chartVo.setValue(fuelScale);
                                }
                            }
                            //基料化比例
                            if (StrawUtilizeConditionEnum.BASE.getCode().equals(dataType)) {
                                BigDecimal base = sum.getMainBase().add(sum.getDisperseBase());
                                // 肥料化利用比例 =(市场主体利用量（肥料）+农户分散利用量（肥料）+直接还田量)/可收集量
                                if (sum.getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                                    chartVo.setValue(BigDecimal.ZERO);
                                } else {
                                    BigDecimal baseScale = base.divide(sum.getCollectResource(), 10, RoundingMode.HALF_UP);
                                    chartVo.setValue(baseScale);
                                }
                            }
                            //原料化比例
                            if (StrawUtilizeConditionEnum.MATERIAL.getCode().equals(dataType)) {
                                BigDecimal material = sum.getMainMaterial().add(sum.getDisperseMaterial());
                                // 肥料化利用比例 =(市场主体利用量（肥料）+农户分散利用量（肥料）+直接还田量)/可收集量
                                if (sum.getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                                    chartVo.setValue(BigDecimal.ZERO);
                                } else {
                                    BigDecimal materialScale = material.divide(sum.getCollectResource(), 10, RoundingMode.HALF_UP);
                                    chartVo.setValue(materialScale);
                                }
                            }
                            if (StrawUtilizeConditionEnum.COMPREHENSIVE_INDEX.getCode().equals(dataType)) {
                                if (sum.getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                                    chartVo.setValue(BigDecimal.ZERO);
                                } else {
                                    BigDecimal comprehensiveIndex = (sum.getMainTotal().add(sum.getDisperseTotal()).add(sum.getProStillField()))
                                            .divide(sum.getCollectResource(), 10, RoundingMode.HALF_UP);
                                    chartVo.setValue(comprehensiveIndex);
                                }
                            }
                            //产业化利用能力指数
                            if (StrawUtilizeConditionEnum.INDUSTRIALIZATION_INDEX.getCode().equals(dataType)) {
                                if (sum.getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                                    chartVo.setValue(BigDecimal.ZERO);
                                } else {
                                    BigDecimal industrializationIndex = (sum.getMainTotal()
                                            .divide(sum.getCollectResource(), 10, RoundingMode.HALF_UP));
                                    chartVo.setValue(industrializationIndex);
                                }
                            }
                            String name = strawTypeMap.get(sum.getStrawType());
                            chartVo.setName(name);
                            chartVoArrayList.add(chartVo);
                        }
                    }
                    chartVoArrayList.sort(new Comparator<ColumnPieChartVo>() {
                        @Override
                        public int compare(ColumnPieChartVo o1, ColumnPieChartVo o2) {
                            return o2.getValue().compareTo(o1.getValue());
                        }
                    });
                    return chartVoArrayList;
                } else {
                    //单个作物的六大区的综合利用量,单个作物的六大区五料化利用比例
                    if (StrawUtilizeConditionEnum.STRAW_UTILIZE.getCode().equals(dataType)) {
                        //获取六大区总数据
                        List<SixRegionStrawUtilizeSum<StrawUtilizeSum>> strawUtilizeSumList = new ArrayList<>();
                        //根据六大区分条汇总
                        //华北区
                        StrawUtilizeSum north_region_sum = dateShowMapper.getStrawSum(year, north_region_list, status, strawType);
                        SixRegionStrawUtilizeSum<StrawUtilizeSum> north_region_vo = new SixRegionStrawUtilizeSum<>();
                        north_region_vo.setRegionName(SixRegionEnum.NORTH_REGION.getDescription());
                        north_region_vo.setT(north_region_sum);
                        strawUtilizeSumList.add(north_region_vo);
                        //长江区
                        StrawUtilizeSum chang_jiang_river_region_sum = dateShowMapper.getStrawSum(year, chang_jiang_river_region_list, status, strawType);
                        SixRegionStrawUtilizeSum<StrawUtilizeSum> chang_jiang_river_region_vo = new SixRegionStrawUtilizeSum<>();
                        chang_jiang_river_region_vo.setRegionName(SixRegionEnum.CHANG_JIANG_RIVER_REGION.getDescription());
                        chang_jiang_river_region_vo.setT(chang_jiang_river_region_sum);
                        strawUtilizeSumList.add(chang_jiang_river_region_vo);
                        //东北区
                        StrawUtilizeSum northeast_region_sum = dateShowMapper.getStrawSum(year, northeast_region_list, status, strawType);
                        SixRegionStrawUtilizeSum<StrawUtilizeSum> northeast_region_vo = new SixRegionStrawUtilizeSum<>();
                        northeast_region_vo.setRegionName(SixRegionEnum.NORTHEAST_REGION.getDescription());
                        northeast_region_vo.setT(northeast_region_sum);
                        strawUtilizeSumList.add(northeast_region_vo);
                        //西北区
                        StrawUtilizeSum northwest_region_sum = dateShowMapper.getStrawSum(year, northwest_region_list, status, strawType);
                        SixRegionStrawUtilizeSum<StrawUtilizeSum> northwest_region_vo = new SixRegionStrawUtilizeSum<>();
                        northwest_region_vo.setRegionName(SixRegionEnum.NORTHWEST_REGION.getDescription());
                        northwest_region_vo.setT(northwest_region_sum);
                        strawUtilizeSumList.add(northwest_region_vo);
                        //西南区
                        StrawUtilizeSum southwest_region_sum = dateShowMapper.getStrawSum(year, southwest_region_list, status, strawType);
                        SixRegionStrawUtilizeSum<StrawUtilizeSum> southwest_region_vo = new SixRegionStrawUtilizeSum<>();
                        southwest_region_vo.setRegionName(SixRegionEnum.SOUTHWEST_REGION.getDescription());
                        southwest_region_vo.setT(southwest_region_sum);
                        strawUtilizeSumList.add(southwest_region_vo);
                        //华南区
                        StrawUtilizeSum south_region_sum = dateShowMapper.getStrawSum(year, south_region_list, status, strawType);
                        SixRegionStrawUtilizeSum<StrawUtilizeSum> south_region_vo = new SixRegionStrawUtilizeSum<>();
                        south_region_vo.setRegionName(SixRegionEnum.SOUTH_REGION.getDescription());
                        south_region_vo.setT(south_region_sum);
                        strawUtilizeSumList.add(south_region_vo);
                        for (SixRegionStrawUtilizeSum<StrawUtilizeSum> strawUtilizeSum : strawUtilizeSumList) {
                            //单个作物的六大区的综合利用量
                            ColumnPieChartVo chartVo = new ColumnPieChartVo();
                            if (strawUtilizeSum.getT() != null) {
                                chartVo.setValue(strawUtilizeSum.getT().getProStrawUtilize());
                                chartVo.setName(strawUtilizeSum.getRegionName());
                                chartVoArrayList.add(chartVo);
                            }
                        }
                    }
                    if (StrawUtilizeConditionEnum.COMPREHENSIVE.getCode().equals(dataType)) {
                        //单个作物的六大区五料化利用比例(即全国单个作物的五料化饼图)
                        StrawUtilizeSum strawSum = dateShowMapper.getStrawSum(year, areaCodeList, status, strawType);
                        if (strawSum != null) {
                            //肥料化fertilising
                            ColumnPieChartVo fertilisingChart = new ColumnPieChartVo();
                            fertilisingChart.setName("肥料化");
                            fertilisingChart.setValue((strawSum.getMainFertilising().add(strawSum.getDisperseFertilising()).add(strawSum.getProStillField())).divide(strawSum.getCollectResource(), 10, RoundingMode.HALF_UP));
                            //饲料化forage
                            ColumnPieChartVo forageChart = new ColumnPieChartVo();
                            forageChart.setName("饲料化");
                            forageChart.setValue((strawSum.getDisperseForage().add(strawSum.getMainForage())).divide(strawSum.getCollectResource(), 10, RoundingMode.HALF_UP));
                            //燃料化fuel
                            ColumnPieChartVo fuelChart = new ColumnPieChartVo();
                            fuelChart.setName("燃料化");
                            fuelChart.setValue((strawSum.getDisperseFuel().add(strawSum.getMainFuel())).divide(strawSum.getCollectResource(), 10, RoundingMode.HALF_UP));
                            //基料化base
                            ColumnPieChartVo baseChart = new ColumnPieChartVo();
                            baseChart.setName("基料化");
                            baseChart.setValue((strawSum.getDisperseBase().add(strawSum.getMainBase())).divide(strawSum.getCollectResource(), 10, RoundingMode.HALF_UP));
                            //原料化material
                            ColumnPieChartVo materialChart = new ColumnPieChartVo();
                            materialChart.setName("原料化");
                            materialChart.setValue((strawSum.getDisperseMaterial().add(strawSum.getMainMaterial())).divide(strawSum.getCollectResource(), 10, RoundingMode.HALF_UP));
                            chartVoArrayList.add(fertilisingChart);
                            chartVoArrayList.add(forageChart);
                            chartVoArrayList.add(fuelChart);
                            chartVoArrayList.add(baseChart);
                            chartVoArrayList.add(materialChart);
                        }
                    }
                    chartVoArrayList.sort(new Comparator<ColumnPieChartVo>() {
                        @Override
                        public int compare(ColumnPieChartVo o1, ColumnPieChartVo o2) {
                            return o2.getValue().compareTo(o1.getValue());
                        }
                    });
                    return chartVoArrayList;
                }
            } else {
                //当区域不为空时,展示指定秸秆类型六大区数据
                //返回数据集合
                List<ColumnPieChartVo> chartVoArrayList = new ArrayList<>();
                //选定六大分区之一
                areaCodeList = Arrays.asList(areaCode.split(","));
                List<StrawUtilizeSum> sumGroupByStrawType = dateShowMapper.getStrawSumGroupByStrawType(year, areaCodeList, status);
                if (StrawUtilizeConditionEnum.COMPREHENSIVE.getCode().equals(dataType)) {
                    //指定区域下的五料化
                    StrawUtilizeSumResVo strawResourceALL = dateShowMapper.getStrawResourceData(year, areaCodeList, status);
                    //肥料化fertilising
                    ColumnPieChartVo fertilisingChart = new ColumnPieChartVo();
                    fertilisingChart.setName("肥料化");
                    fertilisingChart.setValue(strawResourceALL.getMainFertilising().add(strawResourceALL.getDisperseFertilising()).add(strawResourceALL.getProStillField()));
                    //饲料化forage
                    ColumnPieChartVo forageChart = new ColumnPieChartVo();
                    forageChart.setName("饲料化");
                    forageChart.setValue(strawResourceALL.getDisperseForage().add(strawResourceALL.getMainForage()));
                    //燃料化fuel
                    ColumnPieChartVo fuelChart = new ColumnPieChartVo();
                    fuelChart.setName("燃料化");
                    fuelChart.setValue(strawResourceALL.getDisperseFuel().add(strawResourceALL.getMainFuel()));
                    //基料化base
                    ColumnPieChartVo baseChart = new ColumnPieChartVo();
                    baseChart.setName("基料化");
                    baseChart.setValue(strawResourceALL.getDisperseBase().add(strawResourceALL.getMainBase()));
                    //原料化material
                    ColumnPieChartVo materialChart = new ColumnPieChartVo();
                    materialChart.setName("原料化");
                    materialChart.setValue(strawResourceALL.getDisperseMaterial().add(strawResourceALL.getMainMaterial()));
                    chartVoArrayList.add(fertilisingChart);
                    chartVoArrayList.add(forageChart);
                    chartVoArrayList.add(fuelChart);
                    chartVoArrayList.add(baseChart);
                    chartVoArrayList.add(materialChart);
                } else {
                    for (StrawUtilizeSum sum : sumGroupByStrawType) {
                        ColumnPieChartVo chartVo = new ColumnPieChartVo();
                        //综合利用量
                        if (StrawUtilizeConditionEnum.STRAW_UTILIZE.getCode().equals(dataType)) {
                            chartVo.setValue(sum.getProStrawUtilize());
                        }
                        //肥料化利用比例
                        if (StrawUtilizeConditionEnum.FERTILISING.getCode().equals(dataType)) {
                            BigDecimal fertilising = sum.getMainFertilising().add(sum.getDisperseFertilising()).add(sum.getProStillField());
                            // 肥料化利用比例 =(市场主体利用量（肥料）+农户分散利用量（肥料）+直接还田量)/可收集量
                            if (sum.getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                                chartVo.setValue(BigDecimal.ZERO);
                            } else {
                                BigDecimal fertilisingScale = fertilising.divide(sum.getCollectResource(), 10, RoundingMode.HALF_UP);
                                chartVo.setValue(fertilisingScale);
                            }
                        }
                        //饲料化比例
                        if (StrawUtilizeConditionEnum.FORAGE.getCode().equals(dataType)) {
                            BigDecimal forage = sum.getMainForage().add(sum.getDisperseForage());
                            // 肥料化利用比例 =(市场主体利用量（肥料）+农户分散利用量（肥料）+直接还田量)/可收集量
                            if (sum.getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                                chartVo.setValue(BigDecimal.ZERO);
                            } else {
                                BigDecimal forageScale = forage.divide(sum.getCollectResource(), 10, RoundingMode.HALF_UP);
                                chartVo.setValue(forageScale);
                            }
                        }
                        //燃料化比例
                        if (StrawUtilizeConditionEnum.FUEL.getCode().equals(dataType)) {
                            BigDecimal fuel = sum.getMainFuel().add(sum.getDisperseFuel());
                            // 肥料化利用比例 =(市场主体利用量（肥料）+农户分散利用量（肥料）+直接还田量)/可收集量
                            if (sum.getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                                chartVo.setValue(BigDecimal.ZERO);
                            } else {
                                BigDecimal fuelScale = fuel.divide(sum.getCollectResource(), 10, RoundingMode.HALF_UP);
                                chartVo.setValue(fuelScale);
                            }
                        }
                        //基料化比例
                        if (StrawUtilizeConditionEnum.BASE.getCode().equals(dataType)) {
                            BigDecimal base = sum.getMainBase().add(sum.getDisperseBase());
                            // 肥料化利用比例 =(市场主体利用量（肥料）+农户分散利用量（肥料）+直接还田量)/可收集量
                            if (sum.getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                                chartVo.setValue(BigDecimal.ZERO);
                            } else {
                                BigDecimal baseScale = base.divide(sum.getCollectResource(), 10, RoundingMode.HALF_UP);
                                chartVo.setValue(baseScale);
                            }
                        }
                        //原料化比例
                        if (StrawUtilizeConditionEnum.MATERIAL.getCode().equals(dataType)) {
                            BigDecimal material = sum.getMainMaterial().add(sum.getDisperseMaterial());
                            // 肥料化利用比例 =(市场主体利用量（肥料）+农户分散利用量（肥料）+直接还田量)/可收集量
                            if (sum.getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                                chartVo.setValue(BigDecimal.ZERO);
                            } else {
                                BigDecimal materialScale = material.divide(sum.getCollectResource(), 10, RoundingMode.HALF_UP);
                                chartVo.setValue(materialScale);
                            }
                        }
                        if (StrawUtilizeConditionEnum.COMPREHENSIVE_INDEX.getCode().equals(dataType)) {
                            if (sum.getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                                chartVo.setValue(BigDecimal.ZERO);
                            } else {
                                BigDecimal comprehensiveIndex = (sum.getMainTotal().add(sum.getDisperseTotal()).add(sum.getProStillField()))
                                        .divide(sum.getCollectResource(), 10, RoundingMode.HALF_UP);
                                chartVo.setValue(comprehensiveIndex);
                            }
                        }
                        //产业化利用能力指数
                        if (StrawUtilizeConditionEnum.INDUSTRIALIZATION_INDEX.getCode().equals(dataType)) {
                            if (sum.getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                                chartVo.setValue(BigDecimal.ZERO);
                            } else {
                                BigDecimal industrializationIndex = (sum.getMainTotal()
                                        .divide(sum.getCollectResource(), 10, RoundingMode.HALF_UP));
                                chartVo.setValue(industrializationIndex);
                            }
                        }
                        String name = strawTypeMap.get(sum.getStrawType());
                        chartVo.setName(name);
                        chartVoArrayList.add(chartVo);
                    }
                }
                chartVoArrayList.sort(new Comparator<ColumnPieChartVo>() {
                    @Override
                    public int compare(ColumnPieChartVo o1, ColumnPieChartVo o2) {
                        return o2.getValue().compareTo(o1.getValue());
                    }
                });
                return chartVoArrayList;
            }
        }
        return null;
    }

    @Override
    public MapViewData getProStrawUtilizeMapViewTwo(String year, String administrativeLevel, String areaCode, String dataType) {
        //校验用户等级
        Boolean b = checkOrgLevelByUserInData(administrativeLevel);
        boolean bol = checkIsReportInData(year, administrativeLevel, areaCode);
        if (b && bol) {
            List<String> status = new ArrayList<>();
            status.add(AuditStatusEnum.PASSED.getCode());
            status.add(AuditStatusEnum.REPORTED.getCode());
            //华北区
            List<String> north_region_list = Arrays.asList(SixRegionEnum.NORTH_REGION.getCode().split(","));
            //长江区
            List<String> chang_jiang_river_region_list = Arrays.asList(SixRegionEnum.CHANG_JIANG_RIVER_REGION.getCode().split(","));
            //东北区
            List<String> northeast_region_list = Arrays.asList(SixRegionEnum.NORTHEAST_REGION.getCode().split(","));
            //西北区
            List<String> northwest_region_list = Arrays.asList(SixRegionEnum.NORTHWEST_REGION.getCode().split(","));
            //西南区
            List<String> southwest_region_list = Arrays.asList(SixRegionEnum.SOUTHWEST_REGION.getCode().split(","));
            //华南区
            List<String> south_region_list = Arrays.asList(SixRegionEnum.SOUTH_REGION.getCode().split(","));
            //获取六大区总数据
            List<SixRegionStrawUtilizeSum<StrawUtilizeSumResVo>> strawUtilizeSumList = new ArrayList<>();
            //根据六大区分条汇总
            //华北区
            StrawUtilizeSumResVo north_region_sum = dateShowMapper.getStrawResourceData(year, north_region_list, status);
            SixRegionStrawUtilizeSum<StrawUtilizeSumResVo> north_region_vo = new SixRegionStrawUtilizeSum<>();
            north_region_vo.setRegionName(SixRegionEnum.NORTH_REGION.getDescription());
            north_region_vo.setRegionCodes(SixRegionEnum.NORTH_REGION.getCode());
            north_region_vo.setT(north_region_sum);
            strawUtilizeSumList.add(north_region_vo);
            //长江区
            StrawUtilizeSumResVo chang_jiang_river_region_sum = dateShowMapper.getStrawResourceData(year, chang_jiang_river_region_list, status);
            SixRegionStrawUtilizeSum<StrawUtilizeSumResVo> chang_jiang_river_region_vo = new SixRegionStrawUtilizeSum<>();
            chang_jiang_river_region_vo.setRegionName(SixRegionEnum.CHANG_JIANG_RIVER_REGION.getDescription());
            chang_jiang_river_region_vo.setRegionCodes(SixRegionEnum.CHANG_JIANG_RIVER_REGION.getCode());
            chang_jiang_river_region_vo.setT(chang_jiang_river_region_sum);
            strawUtilizeSumList.add(chang_jiang_river_region_vo);
            //东北区
            StrawUtilizeSumResVo northeast_region_sum = dateShowMapper.getStrawResourceData(year, northeast_region_list, status);
            SixRegionStrawUtilizeSum<StrawUtilizeSumResVo> northeast_region_vo = new SixRegionStrawUtilizeSum<>();
            northeast_region_vo.setRegionName(SixRegionEnum.NORTHEAST_REGION.getDescription());
            northeast_region_vo.setRegionCodes(SixRegionEnum.NORTHEAST_REGION.getCode());
            northeast_region_vo.setT(northeast_region_sum);
            strawUtilizeSumList.add(northeast_region_vo);
            //西北区
            StrawUtilizeSumResVo northwest_region_sum = dateShowMapper.getStrawResourceData(year, northwest_region_list, status);
            SixRegionStrawUtilizeSum<StrawUtilizeSumResVo> northwest_region_vo = new SixRegionStrawUtilizeSum<>();
            northwest_region_vo.setRegionName(SixRegionEnum.NORTHWEST_REGION.getDescription());
            northwest_region_vo.setRegionCodes(SixRegionEnum.NORTHWEST_REGION.getCode());
            northwest_region_vo.setT(northwest_region_sum);
            strawUtilizeSumList.add(northwest_region_vo);
            //西南区
            StrawUtilizeSumResVo southwest_region_sum = dateShowMapper.getStrawResourceData(year, southwest_region_list, status);
            SixRegionStrawUtilizeSum<StrawUtilizeSumResVo> southwest_region_vo = new SixRegionStrawUtilizeSum<>();
            southwest_region_vo.setRegionName(SixRegionEnum.SOUTHWEST_REGION.getDescription());
            southwest_region_vo.setRegionCodes(SixRegionEnum.SOUTHWEST_REGION.getCode());
            southwest_region_vo.setT(southwest_region_sum);
            strawUtilizeSumList.add(southwest_region_vo);
            //华南区
            StrawUtilizeSumResVo south_region_sum = dateShowMapper.getStrawResourceData(year, south_region_list, status);
            SixRegionStrawUtilizeSum<StrawUtilizeSumResVo> south_region_vo = new SixRegionStrawUtilizeSum<>();
            south_region_vo.setRegionName(SixRegionEnum.SOUTH_REGION.getDescription());
            south_region_vo.setRegionCodes(SixRegionEnum.SOUTH_REGION.getCode());
            south_region_vo.setT(south_region_sum);
            strawUtilizeSumList.add(south_region_vo);
            //处理数据
            List<AdPointData> pointDataArrayList = new ArrayList<>();
            MapViewData mapViewData = new MapViewData();
            for (SixRegionStrawUtilizeSum<StrawUtilizeSumResVo> strawUtilizeSumResVo : strawUtilizeSumList) {
                AdPointData pointData = new AdPointData();
                if (strawUtilizeSumResVo.getT() != null) {
                    if (StrawUtilizeConditionEnum.STRAW_UTILIZE.getCode().equals(dataType)) {
                        pointData.setIndexValue(strawUtilizeSumResVo.getT().getProStrawUtilize().toString());
                    }
                    if (StrawUtilizeConditionEnum.COMPREHENSIVE.getCode().equals(dataType)) {
                        //获取秸秆利用率
                        // 综合利用率 =((本县秸秆利用量-收购外县的秸秆总量) + 调出秸秆量)/可收集量
                        if (strawUtilizeSumResVo.getT().getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                            pointData.setIndexValue(BigDecimal.ZERO.toString());
                        } else {
                            BigDecimal comprehensive = strawUtilizeSumResVo.getT().getProStrawUtilize()
                                    .divide(strawUtilizeSumResVo.getT().getCollectResource(), 10, RoundingMode.HALF_UP);
                            pointData.setIndexValue(comprehensive.toString());
                        }
                    }
                    //肥料化利用比例
                    if (StrawUtilizeConditionEnum.FERTILISING.getCode().equals(dataType)) {
                        BigDecimal fertilising = strawUtilizeSumResVo.getT().getMainFertilising().add(strawUtilizeSumResVo.getT().getDisperseFertilising()).add(strawUtilizeSumResVo.getT().getProStillField());
                        // 肥料化利用比例 =(市场主体利用量（肥料）+农户分散利用量（肥料）+直接还田量)/可收集量
                        if (strawUtilizeSumResVo.getT().getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                            pointData.setIndexValue(BigDecimal.ZERO.toString());
                        } else {
                            BigDecimal fertilisingScale = fertilising.divide(strawUtilizeSumResVo.getT().getCollectResource(), 10, RoundingMode.HALF_UP);
                            pointData.setIndexValue(fertilisingScale.toString());
                        }
                    }
                    //饲料化比例
                    if (StrawUtilizeConditionEnum.FORAGE.getCode().equals(dataType)) {
                        BigDecimal forage = strawUtilizeSumResVo.getT().getMainForage().add(strawUtilizeSumResVo.getT().getDisperseForage());
                        // 肥料化利用比例 =(市场主体利用量（肥料）+农户分散利用量（肥料）+直接还田量)/可收集量
                        if (strawUtilizeSumResVo.getT().getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                            pointData.setIndexValue(BigDecimal.ZERO.toString());
                        } else {
                            BigDecimal forageScale = forage.divide(strawUtilizeSumResVo.getT().getCollectResource(), 10, RoundingMode.HALF_UP);
                            pointData.setIndexValue(forageScale.toString());
                        }
                    }
                    //燃料化比例
                    if (StrawUtilizeConditionEnum.FUEL.getCode().equals(dataType)) {
                        BigDecimal fuel = strawUtilizeSumResVo.getT().getMainFuel().add(strawUtilizeSumResVo.getT().getDisperseFuel());
                        // 肥料化利用比例 =(市场主体利用量（肥料）+农户分散利用量（肥料）+直接还田量)/可收集量
                        if (strawUtilizeSumResVo.getT().getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                            pointData.setIndexValue(BigDecimal.ZERO.toString());
                        } else {
                            BigDecimal fuelScale = fuel.divide(strawUtilizeSumResVo.getT().getCollectResource(), 10, RoundingMode.HALF_UP);
                            pointData.setIndexValue(fuelScale.toString());
                        }
                    }
                    //基料化比例
                    if (StrawUtilizeConditionEnum.BASE.getCode().equals(dataType)) {
                        BigDecimal base = strawUtilizeSumResVo.getT().getMainBase().add(strawUtilizeSumResVo.getT().getDisperseBase());
                        // 肥料化利用比例 =(市场主体利用量（肥料）+农户分散利用量（肥料）+直接还田量)/可收集量
                        if (strawUtilizeSumResVo.getT().getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                            pointData.setIndexValue(BigDecimal.ZERO.toString());
                        } else {
                            BigDecimal baseScale = base.divide(strawUtilizeSumResVo.getT().getCollectResource(), 10, RoundingMode.HALF_UP);
                            pointData.setIndexValue(baseScale.toString());
                        }
                    }
                    //原料化比例
                    if (StrawUtilizeConditionEnum.MATERIAL.getCode().equals(dataType)) {
                        BigDecimal material = strawUtilizeSumResVo.getT().getMainMaterial().add(strawUtilizeSumResVo.getT().getDisperseMaterial());
                        // 肥料化利用比例 =(市场主体利用量（肥料）+农户分散利用量（肥料）+直接还田量)/可收集量
                        if (strawUtilizeSumResVo.getT().getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                            pointData.setIndexValue(BigDecimal.ZERO.toString());
                        } else {
                            BigDecimal materialScale = material.divide(strawUtilizeSumResVo.getT().getCollectResource(), 10, RoundingMode.HALF_UP);
                            pointData.setIndexValue(materialScale.toString());
                        }
                    }
                    if (StrawUtilizeConditionEnum.COMPREHENSIVE_INDEX.getCode().equals(dataType)) {
                        if (strawUtilizeSumResVo.getT().getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                            pointData.setIndexValue(BigDecimal.ZERO.toString());
                        } else {
                            BigDecimal comprehensiveIndex = (strawUtilizeSumResVo.getT().getMainTotal().add(strawUtilizeSumResVo.getT().getDisperseTotal()).add(strawUtilizeSumResVo.getT().getProStillField()))
                                    .divide(strawUtilizeSumResVo.getT().getCollectResource(), 10, RoundingMode.HALF_UP);
                            pointData.setIndexValue(comprehensiveIndex.toString());
                        }
                    }
                    //产业化利用能力指数
                    if (StrawUtilizeConditionEnum.INDUSTRIALIZATION_INDEX.getCode().equals(dataType)) {
                        if (strawUtilizeSumResVo.getT().getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                            pointData.setIndexValue(BigDecimal.ZERO.toString());
                        } else {
                            BigDecimal industrializationIndex = (strawUtilizeSumResVo.getT().getMainTotal()
                                    .divide(strawUtilizeSumResVo.getT().getCollectResource(), 10, RoundingMode.HALF_UP));
                            pointData.setIndexValue(industrializationIndex.toString());
                        }
                    }
                }
                //设置名称
                pointData.setAdRegionCode(strawUtilizeSumResVo.getRegionCodes());
                pointData.setAdRegionName(strawUtilizeSumResVo.getRegionName());
                pointDataArrayList.add(pointData);
            }
            HashMap<String, List<AdPointData>> listHashMap = new HashMap<>();
            listHashMap.put("adPointDataList", pointDataArrayList);
            mapViewData.setAdPointDataList(listHashMap);
            mapViewData.setAdLevel(administrativeLevel);
            mapViewData.setViewType("pie");
            return mapViewData;
        }
        return null;
    }


    @Override
    public DataCompareVo getDataCompare(String year, String administrativeLevel, String areaCode, String strawType) {
        List<String> areaCodeList = new ArrayList<>();
        //特殊处理新疆和新疆建设兵团
        if (Constants.XINJIANG.equals(areaCode)) {
            areaCodeList.add(areaCode);
            areaCodeList.add(Constants.XINJIANG_CONSTRUCTION_CORPS);
        } else {
            areaCodeList.add(areaCode);
        }
        //获取全国秸秆产生量,或可收集量数据
        List<String> status = new ArrayList<>();
        status.add(AuditStatusEnum.PASSED.getCode());
        status.add(AuditStatusEnum.READ.getCode());
        status.add(AuditStatusEnum.REPORTED.getCode());
        DataCompareVo compareVo = new DataCompareVo();
        //获取数据范围接口
        DataArea dataArea = homePageService.getDataArea(year, areaCode, administrativeLevel, "1");
        if (dataArea != null) {
            compareVo.setReportCountyNum(new BigDecimal(dataArea.getReportCounty()));
            compareVo.setStrawUtilizeNum(new BigDecimal(dataArea.getStrawUtilize()));
            compareVo.setDisperseUtilizeNum(new BigDecimal(dataArea.getDisperseUtilize()));
        }
        StrawUtilizeSumResVo strawResourceData = dateShowMapper.getStrawResourceData(year, areaCodeList, status);
        //未选秸秆类型时
        if ("all".equals(strawType)) {
            if (strawResourceData == null) {
                return new DataCompareVo();
            } else {
                //获取产生情况数据
                StrawUtilizeSum strawSum = dateShowMapper.getStrawSum(year, areaCodeList, status, null);
                compareVo.setTheoryResource(strawResourceData.getTheoryResource());
                compareVo.setTheoryResourceProportion(BigDecimal.ONE);
                compareVo.setCollectResource(strawResourceData.getCollectResource());
                compareVo.setCollectResourceProportion(BigDecimal.ONE);
                if (strawSum != null) {
                    compareVo.setGrainYield(strawSum.getGrainYield());
                }
                //获取符合条件的县级
                List<String> reportAndAuditCounty = this.getReportAndAuditCounty(areaCode, administrativeLevel, year);
                //获取播种面积
                ColumnPieChartVo seedArea = dateShowMapper.getSeedArea(year, reportAndAuditCounty, null);
                if (seedArea == null) {
                    compareVo.setSeedArea(BigDecimal.ZERO);
                } else {
                    compareVo.setSeedArea(seedArea.getValue());
                }
                //获取利用情况数据
                compareVo.setProStrawUtilize(strawResourceData.getProStrawUtilize());
                // 综合利用率 =((本县秸秆利用量-收购外县的秸秆总量) + 调出秸秆量)/可收集量
                if (strawResourceData.getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                    compareVo.setComprehensive(BigDecimal.ZERO);
                } else {
                    BigDecimal comprehensive = strawResourceData.getProStrawUtilize()
                            .divide(strawResourceData.getCollectResource(), 10, RoundingMode.HALF_UP);
                    compareVo.setComprehensive(comprehensive);
                }
                compareVo.setAllTotal(strawResourceData.getMainTotal().add(strawResourceData.getDisperseTotal()).add(strawResourceData.getReturnResource()));
                //肥料化利用比例
                BigDecimal fertilising = strawResourceData.getMainFertilising().add(strawResourceData.getDisperseFertilising()).add(strawResourceData.getReturnResource());
                compareVo.setFertilising(fertilising);
                //饲料化比例
                BigDecimal forage = strawResourceData.getMainForage().add(strawResourceData.getDisperseForage());
                compareVo.setForage(forage);
                //燃料化比例
                BigDecimal fuel = strawResourceData.getMainFuel().add(strawResourceData.getDisperseFuel());
                compareVo.setFuel(fuel);
                BigDecimal base = strawResourceData.getMainBase().add(strawResourceData.getDisperseBase());
                compareVo.setBase(base);
                BigDecimal material = strawResourceData.getMainMaterial().add(strawResourceData.getDisperseMaterial());
                compareVo.setMaterial(material);
                //综合利用能力指数
                if (strawResourceData.getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                    compareVo.setComprehensiveIndex(BigDecimal.ZERO);
                } else {
                    BigDecimal comprehensiveIndex = (strawResourceData.getMainTotal().add(strawResourceData.getDisperseTotal()).add(strawResourceData.getReturnResource()))
                            .divide(strawResourceData.getCollectResource(), 10, RoundingMode.HALF_UP);
                    compareVo.setComprehensiveIndex(comprehensiveIndex);
                }
                //产业化利用能力指数
                if (strawResourceData.getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                    compareVo.setIndustrializationIndex(BigDecimal.ZERO);
                } else {
                    BigDecimal industrializationIndex = (strawResourceData.getMainTotal()
                            .divide(strawResourceData.getCollectResource(), 10, RoundingMode.HALF_UP));
                    compareVo.setIndustrializationIndex(industrializationIndex);
                }
                //获取还田离田情况
                //直接还田量
                compareVo.setReturnResource(strawResourceData.getReturnResource());
                if (administrativeLevel.equals(RegionLevel.COUNTY.getCode())) {
                    // 查询秸秆生产量与直接还田量
                    ProStillDetail detail = proStillDetailMapper.getProStillDetailListByAreaIdAndStrawType(areaCode, year, null);
                    compareVo.setReturnRatio(detail.getReturnRatio().divide(new BigDecimal("100"), RoundingMode.HALF_UP));
                } else {
                    //直接还田率
                    if (strawResourceData.getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                        compareVo.setReturnRatio(BigDecimal.ZERO);
                    } else {
                        BigDecimal returnRatio = strawResourceData.getReturnResource().divide(strawResourceData.getCollectResource(), 10, RoundingMode.HALF_UP);
                        compareVo.setReturnRatio(returnRatio);
                    }
                }
                //离田利用量
                compareVo.setLeavingUtilization(strawResourceData.getDisperseTotal().add(strawResourceData.getMainTotal()));
                //分散利用量
                compareVo.setDisperseUtilize(strawResourceData.getDisperseTotal());
                //主体利用量
                compareVo.setMainUtilize(strawResourceData.getMainTotal());
            }
        } else {
            //选择了秸秆类型之后
            StrawUtilizeSum strawSum = dateShowMapper.getStrawSum(year, areaCodeList, status, strawType);
            if (strawSum == null) {
                return new DataCompareVo();
            }
            //获取产生情况数据
            compareVo.setTheoryResource(strawSum.getTheoryResource());
            //产生量占比
            if (strawResourceData == null || strawResourceData.getTheoryResource().compareTo(BigDecimal.ZERO) == 0) {
                compareVo.setTheoryResourceProportion(BigDecimal.ZERO);
            } else {
                compareVo.setTheoryResourceProportion(strawSum.getTheoryResource().divide(strawResourceData.getTheoryResource(), 10, RoundingMode.HALF_UP));
            }
            compareVo.setCollectResource(strawSum.getCollectResource());
            //可收集量占比
            if (strawResourceData == null || strawResourceData.getTheoryResource().compareTo(BigDecimal.ZERO) == 0) {
                compareVo.setCollectResourceProportion(BigDecimal.ZERO);
            } else {
                compareVo.setCollectResourceProportion(strawSum.getCollectResource().divide(strawResourceData.getCollectResource(), 10, RoundingMode.HALF_UP));
            }
            //粮食产量
            compareVo.setGrainYield(strawSum.getGrainYield());
            //获取符合条件的县级
            List<String> reportAndAuditCounty = this.getReportAndAuditCounty(areaCode, administrativeLevel, year);
            //获取播种面积
            ColumnPieChartVo seedArea = dateShowMapper.getSeedArea(year, reportAndAuditCounty, strawType);
            if (seedArea == null) {
                compareVo.setSeedArea(BigDecimal.ZERO);
            } else {
                compareVo.setSeedArea(seedArea.getValue());
            }
            //获取利用情况数据
            compareVo.setProStrawUtilize(strawSum.getProStrawUtilize());
            // 综合利用率 =((本县秸秆利用量-收购外县的秸秆总量) + 调出秸秆量)/可收集量
            if (strawSum.getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                compareVo.setComprehensive(BigDecimal.ZERO);
            } else {
                BigDecimal comprehensive = strawSum.getProStrawUtilize()
                        .divide(strawSum.getCollectResource(), 10, RoundingMode.HALF_UP);
                compareVo.setComprehensive(comprehensive);
            }
            compareVo.setAllTotal(strawSum.getMainTotal().add(strawSum.getDisperseTotal()));
            //肥料化利用比例
            BigDecimal fertilising = strawSum.getMainFertilising().add(strawSum.getDisperseFertilising()).add(strawSum.getProStillField());
            compareVo.setFertilising(fertilising);
            //饲料化比例
            BigDecimal forage = strawSum.getMainForage().add(strawSum.getDisperseForage());
            compareVo.setForage(forage);
            //燃料化比例
            BigDecimal fuel = strawSum.getMainFuel().add(strawSum.getDisperseFuel());
            compareVo.setFuel(fuel);
            BigDecimal base = strawSum.getMainBase().add(strawSum.getDisperseBase());
            compareVo.setBase(base);
            BigDecimal material = strawSum.getMainMaterial().add(strawSum.getDisperseMaterial());
            compareVo.setMaterial(material);
            //综合利用能力指数
            if (strawSum.getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                compareVo.setComprehensiveIndex(BigDecimal.ZERO);
            } else {
                BigDecimal comprehensiveIndex = (strawSum.getMainTotal().add(strawSum.getDisperseTotal()).add(strawSum.getProStillField()))
                        .divide(strawSum.getCollectResource(), 10, RoundingMode.HALF_UP);
                compareVo.setComprehensiveIndex(comprehensiveIndex);
            }
            //产业化利用能力指数
            if (strawSum.getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                compareVo.setIndustrializationIndex(BigDecimal.ZERO);
            } else {
                BigDecimal industrializationIndex = (strawSum.getMainTotal()
                        .divide(strawSum.getCollectResource(), 10, RoundingMode.HALF_UP));
                compareVo.setIndustrializationIndex(industrializationIndex);
            }
            //获取还田离田情况
            compareVo.setReturnResource(strawSum.getProStillField());
            if (RegionLevel.COUNTY.getCode().equals(administrativeLevel)) {
                // 查询秸秆生产量与直接还田量
                ProStillDetail detail = proStillDetailMapper.getProStillDetailListByAreaIdAndStrawType(areaCode, year, strawType);
                compareVo.setReturnRatio(detail.getReturnRatio().divide(new BigDecimal("100"), RoundingMode.HALF_UP));
            } else {
                //直接还田率
                if (strawSum.getCollectResource().compareTo(BigDecimal.ZERO) == 0) {
                    compareVo.setReturnRatio(BigDecimal.ZERO);
                } else {
                    BigDecimal returnRatio = strawSum.getProStillField().divide(strawSum.getCollectResource(), 10, RoundingMode.HALF_UP);
                    compareVo.setReturnRatio(returnRatio);
                }
            }
            //离田利用量
            compareVo.setLeavingUtilization(strawSum.getDisperseTotal().add(strawSum.getMainTotal()));
            //分散利用量
            compareVo.setDisperseUtilize(strawSum.getDisperseTotal());
            //主体利用量
            compareVo.setMainUtilize(strawSum.getMainTotal());
        }
        return compareVo;
    }

    public List<String> getReportAndAuditCounty(String areaCode, String orgLevel, String year) {
        HashMap<String, Object> map = new HashMap<>(12);
        //根据areacode查询上报县数
        ArrayList<String> countyList = new ArrayList<>();
        //状态list
        List<Byte> list = new ArrayList<>();
        list.add(Constants.ExamineState.REPORTED);
        list.add(Constants.ExamineState.PASSED);
        map.put("statues", list);
        map.put("year", year);
        //县级
        if (orgLevel.equals(RegionLevel.COUNTY.getCode())) {
            //查询符合条件的县级id
            List<String> areaIds = new ArrayList<>();
            areaIds.add(areaCode);
            map.put("areaIds", areaIds);
            return collectFlowMapper.getCountyByCondition(map);
        } else if (orgLevel.equals(RegionLevel.CITY.getCode())) {
            //市级
            //查询当前登录用户的下级所有县id
            List<String> idList = SysRegionUtil.getChildrenRegionIdList(areaCode);
            map.put("areaIds", idList);
            return collectFlowMapper.getCountyByCondition(map);
        } else if (orgLevel.equals(RegionLevel.PROVINCE.getCode())) {
            SysRegionTreeVo Root = sysApi.getSysRegionTree(null, null, null, null, null, Constants.APPID, null);
            if (Root != null) {
                for (SysRegionTreeVo provinceTree : Root.getChildren()) {
                    if (provinceTree.getRegionCode().equals(areaCode)) {
                        for (SysRegionTreeVo cityTree : provinceTree.getChildren()) {
                            for (SysRegionTreeVo countyTree : cityTree.getChildren()) {
                                String countyId = countyTree.getRegionCode();
                                countyList.add(countyId);
                            }
                        }
                    }
                }
            }
            map.put("areaIds", countyList);
            return collectFlowMapper.getCountyByCondition(map);
        } else if (orgLevel.equals(RegionLevel.MINISTRY.getCode())) {
            SysRegionTreeVo Root = sysApi.getSysRegionTree(null, null, null, null, null, Constants.APPID, null);
            if (Root != null) {
                for (SysRegionTreeVo provinceTree : Root.getChildren()) {
                    for (SysRegionTreeVo cityTree : provinceTree.getChildren()) {
                        for (SysRegionTreeVo countyTree : cityTree.getChildren()) {
                            String countyId = countyTree.getRegionCode();
                            countyList.add(countyId);
                        }
                    }
                }
            }
            map.put("areaIds", countyList);
            return collectFlowMapper.getCountyByCondition(map);
        }
        return new ArrayList<>();
    }
}