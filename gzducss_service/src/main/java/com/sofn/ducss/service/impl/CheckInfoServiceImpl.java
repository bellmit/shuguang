package com.sofn.ducss.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sofn.ducss.enums.AuditStatusEnum;
import com.sofn.ducss.enums.CheckColorEnum;
import com.sofn.ducss.enums.RegionLevel;
import com.sofn.ducss.enums.ThresholdEnum;
import com.sofn.ducss.exception.SofnException;
import com.sofn.ducss.mapper.CheckInfoMapper;
import com.sofn.ducss.model.CheckInfo;
import com.sofn.ducss.model.StrawUtilizeSum;
import com.sofn.ducss.model.SysOrganization;
import com.sofn.ducss.service.*;
import com.sofn.ducss.util.BigDecimalUtil;
import com.sofn.ducss.util.SysOrgUtil;
import com.sofn.ducss.util.SysRegionUtil;
import com.sofn.ducss.util.ThreadLocalUserLevelUtil;
import com.sofn.ducss.vo.*;
import com.sofn.ducss.vo.checkcolor.CheckStrawLevelingVo;
import com.sofn.ducss.vo.checkcolor.CheckStrawProduceVo;
import com.sofn.ducss.vo.checkcolor.CheckStrawUtilzeVo;
import com.sofn.ducss.vo.checkcolor.CheckStrawVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CheckInfoServiceImpl implements CheckInfoService {

    @Autowired
    private CheckInfoMapper checkInfoMapper;

    @Autowired
    private ThresholdValueManagerService thresholdValueManagerService;

    @Autowired
    private CollectFlowService collectFlowService;

    @Autowired
    private SyncSysRegionService syncSysRegionService;


    @Autowired
    private AggregateService aggregateService;

    @Autowired
    private SysRegionService sysRegionService;

    /**
     * COLOR MAP中颜色标识
     */
    private final static String COLOR_KEY = "colorState";

    /**
     * COLOR MAP中消息标识
     */
    private final static String MESSAGE_KEY = "message";


    @Override
    public List<CheckInfo> getCheckInfo(String year, String areaId, String status) {
        if (StringUtils.isBlank(year)) {
            throw new SofnException("数据年度不能为空");
        }
        String oldYear = (Integer.parseInt(year) - 1) + "";

        // 1. 判断用户的级别
        SysOrganization sysOrgInfo = SysOrgUtil.getSysOrgInfo();
        String organizationLevel = sysOrgInfo.getOrganizationLevel();
        String regionYear = syncSysRegionService.getYearByYear(year);
        // 今年的审核数据和去年的审核数据
        // List<CheckInfo> checkInfoChina = checkInfoMapper.getCheckInfoByLevel(year, areaId, regionYear, status);
        // List<CheckInfo> oldCheckInfoChina = checkInfoMapper.getCheckInfoByLevel(oldYear, areaId, regionYear, status);
        // 组装数据
        //List<String> areaIds = SysRegionUtil.getChildrenRegionIdList2(areaId,year);
        List<String> areaIds = SysRegionUtil.getChildrenRegionIdList(areaId);
        List<CheckInfo> checkInfoChina = Lists.newArrayList();
        List<CheckInfo> oldCheckInfoChina = Lists.newArrayList();
        List<CheckInfo> checkInfoChinaV2 = checkInfoMapper.getCheckInfoByLevelV2(year, areaIds, status);
        List<CheckInfo> oldCheckInfoChinaV2 = checkInfoMapper.getCheckInfoByLevelV2(oldYear, areaIds, status);
        Map<String, String> regionNameMap = SysRegionUtil.getRegionNameMapByCodes(areaIds);
        Map<String, CheckInfo> checkInfoChinaV2Map = checkInfoChinaV2.stream().collect(Collectors.toMap(CheckInfo::getAreaId, Function.identity()));
        Map<String, CheckInfo> oldCheckInfoChinaV2Map = oldCheckInfoChinaV2.stream().collect(Collectors.toMap(CheckInfo::getAreaId, Function.identity()));

        for (String id : areaIds) {
            String regionName = regionNameMap.get(id);
            CheckInfo checkInfo = checkInfoChinaV2Map.get(id);
            if (checkInfo != null) {
                checkInfo.setAreaName(regionName);
                checkInfoChina.add(checkInfo);
            } else {
                CheckInfo initCheckInfo = new CheckInfo();
                initCheckInfo.setStatus("0");
                initCheckInfo.setAreaName(regionName);
                checkInfoChina.add(initCheckInfo);
            }
            CheckInfo oldCheckInfo = oldCheckInfoChinaV2Map.get(id);
            if (oldCheckInfo != null) {
                oldCheckInfo.setAreaName(regionName);
                oldCheckInfoChina.add(oldCheckInfo);
            } else {
                CheckInfo initCheckInfo = new CheckInfo();
                initCheckInfo.setStatus("0");
                initCheckInfo.setAreaName(regionName);
                oldCheckInfoChina.add(initCheckInfo);
            }
        }

        // 今年的抽样分散户数和去年的抽样分散户数
        List<Map<String, Object>> scatteredHouseNums = Lists.newArrayList();
        List<Map<String, Object>> oldScatteredHouseNums = Lists.newArrayList();
        // 今年的市场主体规模化数量和去年的市场主体规模化数量
        List<Map<String, Object>> mainBodyNums = Lists.newArrayList();
        List<Map<String, Object>> oldMainBodyNums = Lists.newArrayList();


        // 审核等级县级3 ，市级4 ，部级 6 ，省级 5
        // 2. 根据不同用户级别获取不同的数据
        log.info("查询抽样分散户数和市场主体规模化数量");
        String columnName = "";
        if (RegionLevel.MINISTRY.getCode().equals(organizationLevel)) {
            columnName = "province_id";
            // 部级用户
        } else if (organizationLevel.equals(RegionLevel.PROVINCE.getCode())) {
            columnName = "city_id";
        } else if (RegionLevel.CITY.getCode().equals(organizationLevel)) {
            columnName = "area_id";
        } else {
            throw new SofnException("只有部级用户、省级用户、市级用户有审核权限");
        }

        if (!CollectionUtils.isEmpty(checkInfoChina)) {
            List<String> areaId1 = syncSysRegionService.getAreaIdByStatus(year, regionYear,
                    Lists.newArrayList(areaId), Lists.newArrayList("1", "5", "3", "2"));
            if (!CollectionUtils.isEmpty(areaId1)) {
                scatteredHouseNums = checkInfoMapper.getScatteredHouseNum(columnName, year, areaId1);
                oldScatteredHouseNums = checkInfoMapper.getScatteredHouseNum(columnName, oldYear, areaId1);

                mainBodyNums = checkInfoMapper.getMainBody(columnName, year, areaId1);
                oldMainBodyNums = checkInfoMapper.getMainBody(columnName, oldYear, areaId1);
            }
        }
        Map<String, Integer> scatteredHouseNumMap = getMap(scatteredHouseNums);
        Map<String, Integer> mainBodyNumsMap = getMap(mainBodyNums);
        log.info("结束查询抽样分散户数和市场主体规模化数量");


        // 3. 计算合计

        CheckInfo countInfo = getCountCheckInfo(checkInfoChina, scatteredHouseNumMap, mainBodyNumsMap);

        // Map<String, Integer> oldScatteredHouseNumMap = getMap(scatteredHouseNums);
        // Map<String, Integer> oldMainBodyNumsMap = getMap(mainBodyNums);
        // CheckInfo oldCountCheckInfo = getCountCheckInfo(oldCheckInfoChina, oldScatteredHouseNumMap, oldMainBodyNumsMap);

        Map<String, Integer> oldScatteredHouseNumMaps = getMap(oldScatteredHouseNums);
        Map<String, Integer> oldMainBodyNumsMaps = getMap(oldMainBodyNums);


        // 4. 查询该年度的阈值信息
        log.info("计算颜色");
        List<ThresholdValueManagerVo> info = thresholdValueManagerService.getInfo(year, ThresholdEnum.VALUE_MANAGER_TABLE_TYPE_SJSH.getCode());
        if (!CollectionUtils.isEmpty(info)) {

            Map<String, ThresholdValueManagerVo> thresholdValueManagerVoMap = Maps.newHashMap();
            info.forEach(item -> thresholdValueManagerVoMap.put(item.getTargetType(), item));
            // 5. 计算合计的颜色  合计不参与颜色计算
//            getColorStateByCheckInfoAndOldCheckInfo(countInfo, oldCountCheckInfo, thresholdValueManagerVoMap,
//                    null, null, null, null, false);
//
//            // 计算合计的抽样分散户数颜色标识  scatteredHouseNumColorState
//            ThresholdValueManagerVo thresholdValueManagerVo7 = thresholdValueManagerVoMap.get(ThresholdEnum.VALUE_MANAGER_TARGET_TYPE_1_CYFSHS.getCode());
//            Map<String, String> scatteredHouseNumColorMap = getColor(thresholdValueManagerVo7,
//                    new BigDecimal(oldCountCheckInfo.getScatteredHouseNum() == null ? "0" : oldCountCheckInfo.getScatteredHouseNum() + ""),
//                    new BigDecimal(countInfo.getScatteredHouseNum() == null ? "0" : countInfo.getScatteredHouseNum() + ""));
//
//            countInfo.setScatteredHouseNumColorState(scatteredHouseNumColorMap.get(COLOR_KEY));
//            countInfo.setScatteredHouseNumMessage(scatteredHouseNumColorMap.get(MESSAGE_KEY));
//
//
//            // 计算合计的市场主体规模化数量颜色标识 mainBodyNumColorState
//            ThresholdValueManagerVo thresholdValueManagerVo8 = thresholdValueManagerVoMap.get(ThresholdEnum.VALUE_MANAGER_TARGET_TYPE_1_SCZTGMHSL.getCode());
//            Map<String, String> oldMainBodyNumColorMap = getColor(thresholdValueManagerVo8,
//                    new BigDecimal(oldCountCheckInfo.getMainBodyNum() == null ? "0" : oldCountCheckInfo.getMainBodyNum() + ""),
//                    new BigDecimal(countInfo.getMainBodyNum() == null ? "0" : countInfo.getMainBodyNum() + ""));
//
//            countInfo.setMainBodyNumColorState(oldMainBodyNumColorMap.get(COLOR_KEY));
//            countInfo.setMainBodyNumMessage(oldMainBodyNumColorMap.get(MESSAGE_KEY));

            // 6. 计算其他的省市县的颜色
            // 一个指标一个指标的计算
            if (!CollectionUtils.isEmpty(checkInfoChina)) {
                // 计算颜色
                Map<String, CheckInfo> maps = Maps.newHashMap();
                oldCheckInfoChina.forEach(item -> maps.put(item.getAreaId(), item));
                for (CheckInfo item : checkInfoChina) {
                    if (item != null) {
                        // 未提交不参与颜色计算
                        if ("0".equals(item.getStatus())) {
                            continue;
                        }
                    }
                    String dataAreaId = item.getAreaId();
                    CheckInfo oldCheckInfo = maps.get(dataAreaId);
                    if (oldCheckInfo != null) {
                        getColorStateByCheckInfoAndOldCheckInfo(item, oldCheckInfo, thresholdValueManagerVoMap,
                                scatteredHouseNumMap, mainBodyNumsMap, oldScatteredHouseNumMaps, oldMainBodyNumsMaps, true);
                    }
                }

            }
        }
        log.info("结束计算颜色");
        // 添加合计的
        checkInfoChina.add(0, countInfo);
        if (!CollectionUtils.isEmpty(checkInfoChina)) {
            // 找出哪些区划的数据没有提交
            checkInfoChina.forEach(item -> {
                // 没提交或者已经退回的直接返回0  || "3".equals(item.getStatus())
                if ("0".equals(item.getStatus())) {
                    item.clearAttrValue();
                }
                // 只有部级用户才显示阈值信息

            });
        }
        return checkInfoChina;
    }

    @Override
    public List<CheckStrawProduceVo> getStrawProduceCheckInfo(String year, String areaId) throws Exception {
        // 秸秆产生量颜色
        log.info("查询开始");
        AggregateQueryVo aggregateQueryVo = new AggregateQueryVo(year, areaId);
        aggregateQueryVo.setCheckInfoFlag(true);
        List<StrawProduceResVo2> strawProduceData2 = aggregateService.getStrawProduceData2(aggregateQueryVo);
//        List<StrawProduceResVo> strawProduceData = collectFlowService.findStrawProduceData(areaId, year);
        log.info("查询结束");
        if (CollectionUtils.isEmpty(strawProduceData2)) {
            return Lists.newArrayList();
        }
        List<CheckStrawProduceVo> checkStrawProduceVos = strawProduceData2.stream().map(CheckStrawProduceVo::getCheckStrawProduceVo).collect(Collectors.toList());
        // 计算合计
//        Map<String, BigDecimal> maps = getTheoryResourceCount(checkStrawProduceVos);
//        CheckStrawProduceVo checkStrawProduceVo = new CheckStrawProduceVo();
//        checkStrawProduceVo.setStrawTypeName("合计");
//        checkStrawProduceVo.setStrawType("hj");
//        checkStrawProduceVo.setTheoryResource(maps.get("theoryResourceSum"));
//        checkStrawProduceVo.setCollectResource(maps.get("collectResourceSum"));
//        checkStrawProduceVo.setGrainYield(maps.get("grainYieldSum"));
//        checkStrawProduceVo.setSeedArea(maps.get("seedArea"));
//        checkStrawProduceVos.add(0, checkStrawProduceVo);
//        getNoun(maps, checkStrawProduceVos);

        // 2. 拉取阈值信息
        String tableType = ThresholdEnum.VALUE_MANAGER_TABLE_TYPE_CSQKHZ.getCode();
        List<ThresholdValueManagerVo> info = thresholdValueManagerService.getInfo(year, tableType);
        if (CollectionUtils.isEmpty(info)) {
            log.info("年度：【{}】，表格:{}无阈值，不进行计算", year, tableType);
            return checkStrawProduceVos;
        }
        String oldYear = (Integer.parseInt(year) - 1) + "";
        log.info("查询去年的");
        AggregateQueryVo aggregateQueryVo2 = new AggregateQueryVo(oldYear, areaId);
        aggregateQueryVo2.setCheckInfoFlag(true);
        List<StrawProduceResVo2> oldStrawProduceResVo = aggregateService.getStrawProduceData2(aggregateQueryVo2);
        log.info("结束查询去年的");
        Map<String, CheckStrawProduceVo> oldDataMap = Maps.newHashMap();
        if (!CollectionUtils.isEmpty(oldStrawProduceResVo)) {
            // 旧的数据
            List<CheckStrawProduceVo> oldCheckStrawProduceVos = oldStrawProduceResVo.stream().map(CheckStrawProduceVo::getCheckStrawProduceVo).collect(Collectors.toList());
//            // 计算合计
//            Map<String, BigDecimal> oldMaps = getTheoryResourceCount(oldCheckStrawProduceVos);
//            CheckStrawProduceVo checkStrawProduceVo2 = new CheckStrawProduceVo();
//            checkStrawProduceVo2.setStrawTypeName("合计");
//            checkStrawProduceVo2.setStrawType("hj");
//            checkStrawProduceVo2.setTheoryResource(oldMaps.get("theoryResourceSum"));
//            checkStrawProduceVo2.setCollectResource(oldMaps.get("collectResourceSum"));
//            checkStrawProduceVo2.setGrainYield(oldMaps.get("grainYieldSum"));
//            checkStrawProduceVo2.setSeedArea(oldMaps.get("seedArea"));
//            oldCheckStrawProduceVos.add(0, checkStrawProduceVo2);
//            getNoun(oldMaps, oldCheckStrawProduceVos);
            oldCheckStrawProduceVos.forEach(item -> oldDataMap.put(item.getStrawType(), item));
        }
        // 3. 计算颜色
        log.info("计算颜色");
        checkStrawProduceVos.forEach(item -> {
            CheckStrawProduceVo oldData = oldDataMap.get(item.getStrawType());
            getCheckStrawColorState(info, item, oldData);
        });
        log.info("结束计算颜色");

        return checkStrawProduceVos;
    }


    @Override
    public List<CheckStrawUtilzeVo> getUtilzeCheckInfo(String year, String areaId) throws Exception {
        // 1. 拉取之前的秸秆利用量数据
        AggregateQueryVo aggregateQueryVo = new AggregateQueryVo(year, areaId);
        aggregateQueryVo.setCheckInfoFlag(true);

        List<StrawUtilizeResVo3> strawUtilzeData2 = aggregateService.getStrawUtilzeData2(aggregateQueryVo);
//        List<StrawUtilizeSum> strawUtilzeData = collectFlowService.findStrawUtilzeData(areaId, year);
        if (CollectionUtils.isEmpty(strawUtilzeData2)) {
            return Lists.newArrayList();
        }
        List<CheckStrawUtilzeVo> checkStrawUtilzeVoList = strawUtilzeData2.stream().map(CheckStrawUtilzeVo::getCheckStrawUtilzeVo).collect(Collectors.toList());
//        checkStrawUtilzeVoList = this.order(checkStrawUtilzeVoList);
        // 2. 拉取阈值信息
        String tableType = ThresholdEnum.VALUE_MANAGER_TABLE_TYPE_LYQKHZ.getCode();
        List<ThresholdValueManagerVo> info = thresholdValueManagerService.getInfo(year, tableType);
        if (CollectionUtils.isEmpty(info)) {
            log.info("年度：【{}】，表格:{}无阈值，不进行计算", year, tableType);
            return checkStrawUtilzeVoList;
        }
        // 3. 比较值 获取颜色标识
        String oldYear = (Integer.parseInt(year) - 1) + "";
        AggregateQueryVo aggregateQueryVo2 = new AggregateQueryVo(oldYear, areaId);
        aggregateQueryVo2.setCheckInfoFlag(true);
        List<StrawUtilizeResVo3> oldStrawUtilzeData = aggregateService.getStrawUtilzeData2(aggregateQueryVo2);
        Map<String, CheckStrawUtilzeVo> maps = Maps.newHashMap();
        if (!CollectionUtils.isEmpty(oldStrawUtilzeData)) {
            List<CheckStrawUtilzeVo> oldCheckStrawUtilzeVoList = oldStrawUtilzeData.stream().map(CheckStrawUtilzeVo::getCheckStrawUtilzeVo).collect(Collectors.toList());
            oldCheckStrawUtilzeVoList.forEach(item -> maps.put(item.getStrawType(), item));
            checkStrawUtilzeVoList.forEach(item -> {
                CheckStrawUtilzeVo oldData = maps.get(item.getStrawType());
                getCheckStrawColorState(info, item, oldData);
            });
        }

        return checkStrawUtilzeVoList;
    }


    @Override
    public List<CheckStrawLevelingVo> getLevelingVo(String year, String areaId) throws Exception {
        // 1.还田离田
        // List<AreaRegionCode> regionList, String year
        AggregateQueryVo areaRegionCode = new AggregateQueryVo(year, areaId);
        areaRegionCode.setCheckInfoFlag(true);
        List<ReturnLeaveSumVo> list = aggregateService.findReturnLeaveSumData(areaRegionCode);
        List<StrawUtilizeSum> strawUtilzeData = collectFlowService.findStrawUtilzeData(areaId, year);
        if (CollectionUtils.isEmpty(strawUtilzeData)) {
            return Lists.newArrayList();
        }
        List<CheckStrawLevelingVo> checkStrawLevelingVos = list.stream()
                .map(CheckStrawLevelingVo::getCheckStrawLevelingVo).collect(Collectors.toList());

        //
//        checkStrawLevelingVos = order2(checkStrawLevelingVos);

        // 2. 拉取阈值
        String tableType = ThresholdEnum.VALUE_MANAGER_TABLE_TYPE_HTLTQK.getCode();
        List<ThresholdValueManagerVo> info = thresholdValueManagerService.getInfo(year, tableType);
        if (CollectionUtils.isEmpty(info)) {
            log.info("年度：【{}】，表格:{}无阈值，不进行计算", year, tableType);
            return checkStrawLevelingVos;
        }
        // 3. 比较颜色
        String oldYear = (Integer.parseInt(year) - 1) + "";

        AggregateQueryVo areaRegionCode2 = new AggregateQueryVo(oldYear, areaId);
        areaRegionCode2.setCheckInfoFlag(true);
        List<ReturnLeaveSumVo> oldStrawUtilzeData = aggregateService.findReturnLeaveSumData(areaRegionCode2);
        if (!CollectionUtils.isEmpty(oldStrawUtilzeData)) {
            List<CheckStrawLevelingVo> oldCheckStrawLevelingVos = oldStrawUtilzeData.stream()
                    .map(CheckStrawLevelingVo::getCheckStrawLevelingVo).collect(Collectors.toList());
            Map<String, CheckStrawVo> maps = Maps.newHashMap();
            oldCheckStrawLevelingVos.forEach(item -> maps.put(item.getStrawType(), item));
            checkStrawLevelingVos.forEach(item -> {
                CheckStrawVo oldData = maps.get(item.getStrawType());
                getCheckStrawColorState(info, item, oldData);
            });

        }
        return checkStrawLevelingVos;
    }

    // =======================================PRIVATE方法分割线=================

    /**
     * 利用情况排序
     *
     * @param strawVos 利用数据
     * @return List<CheckStrawUtilzeVo>
     */
    private List<CheckStrawUtilzeVo> order(List<CheckStrawUtilzeVo> strawVos) {
        List<CheckStrawUtilzeVo> checkStrawVos = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(strawVos)) {
            List<String> strawTypeList = checkInfoMapper.getStrawTypeList();
            if (!CollectionUtils.isEmpty(strawTypeList)) {
                strawTypeList.add(0, "hj");
                Map<String, CheckStrawUtilzeVo> maps = Maps.newHashMap();
                strawVos.forEach(item -> {
                    String key = StringUtils.isBlank(item.getStrawType()) ? "hj" : item.getStrawType();
                    maps.put(key, item);
                });
                List<CheckStrawUtilzeVo> finalCheckStrawVos = checkStrawVos;
                strawTypeList.forEach(item -> {
                    CheckStrawUtilzeVo checkStrawVo = maps.get(item);
                    if (checkStrawVo != null) {
                        finalCheckStrawVos.add(checkStrawVo);
                    }
                });
            } else {
                checkStrawVos = strawVos;
            }
        }
        return checkStrawVos;
    }


    /**
     * 还田离田情况作物排序
     *
     * @param strawVos 还田离田情况作物数据
     * @return List<CheckStrawLevelingVo>
     */
    private List<CheckStrawLevelingVo> order2(List<CheckStrawLevelingVo> strawVos) {
        List<CheckStrawLevelingVo> checkStrawVos = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(strawVos)) {
            List<String> strawTypeList = checkInfoMapper.getStrawTypeList();
            if (!CollectionUtils.isEmpty(strawTypeList)) {
                strawTypeList.add(0, "hj");
                Map<String, CheckStrawLevelingVo> maps = Maps.newHashMap();
                strawVos.forEach(item -> {
                    String key = StringUtils.isBlank(item.getStrawType()) ? "hj" : item.getStrawType();
                    maps.put(key, item);
                });
                List<CheckStrawLevelingVo> finalCheckStrawVos = checkStrawVos;
                strawTypeList.forEach(item -> {
                    CheckStrawLevelingVo checkStrawVo = maps.get(item);
                    if (checkStrawVo != null) {
                        finalCheckStrawVos.add(checkStrawVo);
                    }
                });
            } else {
                checkStrawVos = strawVos;
            }
        }
        return checkStrawVos;
    }

    /**
     * 计算比例
     *
     * @param maps 计算出来的统计信息
     * @param info 所有信息
     */
    private void getNoun(Map<String, BigDecimal> maps, List<CheckStrawProduceVo> info) {
        if (!CollectionUtils.isEmpty(info)) {
            BigDecimal theoryResourceSum = maps.get("theoryResourceSum");
            BigDecimal collectResourceSum = maps.get("collectResourceSum");
            info.forEach(item -> {
                item.setTheoryResourceNoun(getNone(item.getTheoryResource(), theoryResourceSum));
                item.setCollectResourceNoun(getNone(item.getCollectResource(), collectResourceSum));
            });
        }
    }


    /**
     * 获取前面一个数占后面一个数的比例
     *
     * @param theoryResource    前一个数
     * @param theoryResourceSum 后一个数
     * @return BigDecimal
     */
    private BigDecimal getNone(BigDecimal theoryResource, BigDecimal theoryResourceSum) {
        // theoryResourceNoun / theoryResourceSum  * 100
        if (theoryResourceSum == null || theoryResource == null) {
            return BigDecimal.ZERO;
        }
        if (theoryResourceSum.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        } else {
            return BigDecimalUtil.valueIsNull(theoryResource)
                    .divide(theoryResourceSum, 2, BigDecimal.ROUND_HALF_UP)
                    .multiply(new BigDecimal("100"));
        }
    }


    /**
     * 根据List获取里面的数据的综合
     *
     * @param strawProduceResVos List<CheckStrawProduceVo>
     * @return Map<String, BigDecimal>
     */
    private Map<String, BigDecimal> getTheoryResourceCount(List<CheckStrawProduceVo> strawProduceResVos) {
        Map<String, BigDecimal> maps = Maps.newHashMap();
        if (!CollectionUtils.isEmpty(strawProduceResVos)) {
            BigDecimal theoryResourceSum = new BigDecimal("0");
            BigDecimal collectResourceSum = new BigDecimal("0");
            BigDecimal grainYieldSum = new BigDecimal("0");
            BigDecimal seedArea = new BigDecimal("0");
            for (CheckStrawProduceVo strawProduceResVo : strawProduceResVos) {
                theoryResourceSum = theoryResourceSum.add(BigDecimalUtil.valueIsNull(strawProduceResVo.getTheoryResource()));
                collectResourceSum = collectResourceSum.add(BigDecimalUtil.valueIsNull(strawProduceResVo.getCollectResource()));
                grainYieldSum = grainYieldSum.add(BigDecimalUtil.valueIsNull(strawProduceResVo.getGrainYield()));
                seedArea = seedArea.add(BigDecimalUtil.valueIsNull(strawProduceResVo.getSeedArea()));
            }
            maps.put("theoryResourceSum", theoryResourceSum);
            maps.put("collectResourceSum", collectResourceSum);
            maps.put("grainYieldSum", grainYieldSum);
            maps.put("seedArea", seedArea);
        } else {
            maps.put("theoryResourceSum", new BigDecimal("1"));
            maps.put("collectResourceSum", new BigDecimal("1"));
            maps.put("grainYieldSum", new BigDecimal("0"));
            maps.put("seedArea", new BigDecimal("0"));
        }
        return maps;
    }

    /**
     * 比较还田离田量颜色
     *
     * @param checkStrawVo  今年的还田离田量数据
     * @param oldCheckStraw 去年的还田离田量数据
     */
    private void getCheckStrawColorState(List<ThresholdValueManagerVo> thresholdValues, CheckStrawVo checkStrawVo, CheckStrawVo oldCheckStraw) {
        Map<String, ThresholdValueManagerVo> thresholdValueManagerVoMap = Maps.newHashMap();
        thresholdValues.forEach(item -> thresholdValueManagerVoMap.put(item.getTargetType(), item));
        // 比较颜色
        if (checkStrawVo instanceof CheckStrawLevelingVo) {
            // 还田离田量    4
            CheckStrawLevelingVo checkStrawLevelingVo = (CheckStrawLevelingVo) checkStrawVo;
            CheckStrawLevelingVo old = (CheckStrawLevelingVo) oldCheckStraw;
            if (old == null) {
                return;
            }
            // 农户分散利用量
            ThresholdValueManagerVo t1 = thresholdValueManagerVoMap.get(ThresholdEnum.VALUE_MANAGER_TARGET_TYPE_4_NHFSLYL.getCode());
            Map<String, String> disperseTotalColorMap = getColor(t1, old.getDisperseTotal(), checkStrawLevelingVo.getDisperseTotal());
            checkStrawLevelingVo.setDisperseTotalColorState(disperseTotalColorMap.get(COLOR_KEY));
            checkStrawLevelingVo.setDisperseTotalMessage(disperseTotalColorMap.get(MESSAGE_KEY));
            // 主体规模化利用量
            ThresholdValueManagerVo t2 = thresholdValueManagerVoMap.get(ThresholdEnum.VALUE_MANAGER_TARGET_TYPE_4_NHFSLYL.getCode());
            Map<String, String> mainTotalColorMap = getColor(t2, old.getMainTotal(), checkStrawLevelingVo.getMainTotal());
            checkStrawLevelingVo.setMainTotalColorState(mainTotalColorMap.get(COLOR_KEY));
            checkStrawLevelingVo.setMainTotalMessage(mainTotalColorMap.get(MESSAGE_KEY));

            // 直接还田率
            ThresholdValueManagerVo t3 = thresholdValueManagerVoMap.get(ThresholdEnum.VALUE_MANAGER_TARGET_TYPE_4_ZJHTL2.getCode());
            Map<String, String> returnRatioColorMap = getColor(t3, old.getReturnRatio(), checkStrawLevelingVo.getReturnRatio(), false);
            checkStrawLevelingVo.setReturnRatioColorState(returnRatioColorMap.get(COLOR_KEY));
            checkStrawLevelingVo.setReturnRatioMessage(returnRatioColorMap.get(MESSAGE_KEY));

            // 直接还田量
            ThresholdValueManagerVo t4 = thresholdValueManagerVoMap.get(ThresholdEnum.VALUE_MANAGER_TARGET_TYPE_4_ZJHTL.getCode());
            Map<String, String> proStillFieldColorMap = getColor(t4, old.getProStillField(), checkStrawLevelingVo.getProStillField());
            checkStrawLevelingVo.setProStillFieldColorState(proStillFieldColorMap.get(COLOR_KEY));
            checkStrawLevelingVo.setProStillFieldMessage(proStillFieldColorMap.get(MESSAGE_KEY));

            // 合计
            ThresholdValueManagerVo t5 = thresholdValueManagerVoMap.get(ThresholdEnum.VALUE_MANAGER_TARGET_TYPE_4_HJ.getCode());
            Map<String, String> countColorMap = getColor(t5, old.getCount(), checkStrawLevelingVo.getCount());
            checkStrawLevelingVo.setCountColorState(countColorMap.get(COLOR_KEY));
            checkStrawLevelingVo.setCountMessage(countColorMap.get(MESSAGE_KEY));


        } else if (checkStrawVo instanceof CheckStrawProduceVo) {
            // 秸秆产生量    2
            CheckStrawProduceVo checkStrawProduceVo = (CheckStrawProduceVo) checkStrawVo;
            CheckStrawProduceVo old = (CheckStrawProduceVo) oldCheckStraw;
            if (old == null) {
                return;
            }
            // 产生量
            ThresholdValueManagerVo t1 = thresholdValueManagerVoMap.get(ThresholdEnum.VALUE_MANAGER_TARGET_TYPE_2_CSL.getCode());
            Map<String, String> theoryResourceColorMap = getColor(t1, old.getTheoryResource(), checkStrawProduceVo.getTheoryResource());
            checkStrawProduceVo.setTheoryResourceColorState(theoryResourceColorMap.get(COLOR_KEY));
            checkStrawProduceVo.setTheoryResourceMessage(theoryResourceColorMap.get(MESSAGE_KEY));
            // 产生量占比
            ThresholdValueManagerVo t2 = thresholdValueManagerVoMap.get(ThresholdEnum.VALUE_MANAGER_TARGET_TYPE_2_CSLZB.getCode());
            Map<String, String> theoryResourceNounColorMap = getColor(t2, old.getTheoryResourceNoun(), checkStrawProduceVo.getTheoryResourceNoun(), false);
            checkStrawProduceVo.setTheoryResourceNounColorState(theoryResourceNounColorMap.get(COLOR_KEY));
            checkStrawProduceVo.setTheoryResourceNounMessage(theoryResourceNounColorMap.get(MESSAGE_KEY));
            // 可收集量
            ThresholdValueManagerVo t3 = thresholdValueManagerVoMap.get(ThresholdEnum.VALUE_MANAGER_TARGET_TYPE_2_KSJL.getCode());
            Map<String, String> collectResourceColorMap = getColor(t3, old.getCollectResource(), checkStrawProduceVo.getCollectResource());
            checkStrawProduceVo.setCollectResourceColorState(collectResourceColorMap.get(COLOR_KEY));
            checkStrawProduceVo.setCollectResourceNounMessage(collectResourceColorMap.get(MESSAGE_KEY));

            // 可收集量占比
            ThresholdValueManagerVo t4 = thresholdValueManagerVoMap.get(ThresholdEnum.VALUE_MANAGER_TARGET_TYPE_2_KSJLZB.getCode());
            Map<String, String> collectResourceNounColorMap = getColor(t4, old.getCollectResourceNoun(), checkStrawProduceVo.getCollectResourceNoun(), false);
            checkStrawProduceVo.setCollectResourceNounColorState(collectResourceNounColorMap.get(COLOR_KEY));
            checkStrawProduceVo.setCollectResourceNounMessage(collectResourceNounColorMap.get(MESSAGE_KEY));
            // 粮食产量
            ThresholdValueManagerVo t5 = thresholdValueManagerVoMap.get(ThresholdEnum.VALUE_MANAGER_TARGET_TYPE_2_LSCL.getCode());
            Map<String, String> grainYieldColorMap = getColor(t5, old.getGrainYield(), checkStrawProduceVo.getGrainYield());
            checkStrawProduceVo.setGrainYieldColorState(grainYieldColorMap.get(COLOR_KEY));
            checkStrawProduceVo.setGrainYieldMessage(grainYieldColorMap.get(MESSAGE_KEY));

            // 播种面积
            ThresholdValueManagerVo t6 = thresholdValueManagerVoMap.get(ThresholdEnum.VALUE_MANAGER_TARGET_TYPE_2_BZMJ.getCode());
            Map<String, String> seedAreaColorMap = getColor(t6, old.getSeedArea(), checkStrawProduceVo.getSeedArea());
            checkStrawProduceVo.setSeedAreaColorState(seedAreaColorMap.get(COLOR_KEY));
            checkStrawProduceVo.setSeedAreaMessage(seedAreaColorMap.get(MESSAGE_KEY));

        } else if (checkStrawVo instanceof CheckStrawUtilzeVo) {
            // 秸秆利用量   3
            CheckStrawUtilzeVo newData = (CheckStrawUtilzeVo) checkStrawVo;
            CheckStrawUtilzeVo old = (CheckStrawUtilzeVo) oldCheckStraw;
            if (old == null) {
                return;
            }
            // 秸秆利用量
            ThresholdValueManagerVo t1 = thresholdValueManagerVoMap.get(ThresholdEnum.VALUE_MANAGER_TARGET_TYPE_3_JGLYL.getCode());
            Map<String, String> proStrawUtilizeColorMap = getColor(t1, old.getProStrawUtilize(), newData.getProStrawUtilize());
            newData.setProStrawUtilizeColorState(proStrawUtilizeColorMap.get(COLOR_KEY));
            newData.setProStrawUtilizeMessage(proStrawUtilizeColorMap.get(MESSAGE_KEY));

            // 综合利用率
            ThresholdValueManagerVo t2 = thresholdValueManagerVoMap.get(ThresholdEnum.VALUE_MANAGER_TARGET_TYPE_3_ZGLYL.getCode());
            Map<String, String> comprehensiveColorMap = getColor(t2, old.getComprehensive(), newData.getComprehensive(), false);
            newData.setComprehensiveColorState(comprehensiveColorMap.get(COLOR_KEY));
            newData.setComprehensiveMessage(comprehensiveColorMap.get(MESSAGE_KEY));
            // 合计
            ThresholdValueManagerVo t3 = thresholdValueManagerVoMap.get(ThresholdEnum.VALUE_MANAGER_TARGET_TYPE_3_HJ.getCode());
            Map<String, String> countColorStateMap = getColor(t3, old.getCount(), newData.getCount());
            newData.setCountColorState(countColorStateMap.get(COLOR_KEY));
            newData.setCountMessage(countColorStateMap.get(MESSAGE_KEY));
            // 肥料化
            ThresholdValueManagerVo t4 = thresholdValueManagerVoMap.get(ThresholdEnum.VALUE_MANAGER_TARGET_TYPE_3_FLH.getCode());
            Map<String, String> newFertilisingColorMap = getColor(t4, old.getNewFertilising(), newData.getNewFertilising());
            newData.setNewFertilisingColorState(newFertilisingColorMap.get(COLOR_KEY));
            newData.setNewFertilisingMessage(newFertilisingColorMap.get(MESSAGE_KEY));
            // 饲料化
            ThresholdValueManagerVo t5 = thresholdValueManagerVoMap.get(ThresholdEnum.VALUE_MANAGER_TARGET_TYPE_3_SLH.getCode());
            Map<String, String> newForageColorMap = getColor(t5, old.getNewForage(), newData.getNewForage());
            newData.setNewForageColorState(newForageColorMap.get(COLOR_KEY));
            newData.setNewForageMessage(newForageColorMap.get(MESSAGE_KEY));


            // 燃料化
            ThresholdValueManagerVo t6 = thresholdValueManagerVoMap.get(ThresholdEnum.VALUE_MANAGER_TARGET_TYPE_3_RLH.getCode());
            Map<String, String> newFuelColorMap = getColor(t6, old.getNewFuel(), newData.getNewFuel());
            newData.setNewFuelColorState(newFuelColorMap.get(COLOR_KEY));
            newData.setNewFuelMessage(newFuelColorMap.get(MESSAGE_KEY));

            // 基料化
            ThresholdValueManagerVo t7 = thresholdValueManagerVoMap.get(ThresholdEnum.VALUE_MANAGER_TARGET_TYPE_3_JLH.getCode());
            Map<String, String> newBaseColorMap = getColor(t7, old.getNewBase(), newData.getNewBase());
            newData.setNewBaseColorState(newBaseColorMap.get(COLOR_KEY));
            newData.setNewBaseMessage(newBaseColorMap.get(MESSAGE_KEY));

            // 原料化
            ThresholdValueManagerVo t8 = thresholdValueManagerVoMap.get(ThresholdEnum.VALUE_MANAGER_TARGET_TYPE_3_YLH.getCode());
            Map<String, String> newMaterialColorMap = getColor(t8, old.getNewMaterial(), newData.getNewMaterial());
            newData.setNewMaterialColorState(newMaterialColorMap.get(COLOR_KEY));
            newData.setNewMaterialMessage(newMaterialColorMap.get(MESSAGE_KEY));


            // 综合利用指数
            ThresholdValueManagerVo t9 = thresholdValueManagerVoMap.get(ThresholdEnum.VALUE_MANAGER_TARGET_TYPE_3_ZHLYZS.getCode());
            Map<String, String> comprehensiveIndexColorMap = getColor(t9, old.getComprehensiveIndex(), newData.getComprehensiveIndex(), false);
            newData.setComprehensiveIndexColorState(comprehensiveIndexColorMap.get(COLOR_KEY));
            newData.setComprehensiveIndexMessage(comprehensiveIndexColorMap.get(MESSAGE_KEY));

            // 产业化利用能力指数
            ThresholdValueManagerVo t10 = thresholdValueManagerVoMap.get(ThresholdEnum.VALUE_MANAGER_TARGET_TYPE_3_CYHLYNLZS.getCode());
            Map<String, String> industrializationIndexColorMap = getColor(t10, old.getIndustrializationIndex(), newData.getIndustrializationIndex(), false);
            newData.setIndustrializationIndexColorState(industrializationIndexColorMap.get(COLOR_KEY));
            newData.setIndustrializationIndexMessage(industrializationIndexColorMap.get(MESSAGE_KEY));
        }
    }

    /**
     * 根据StrawUtilizeSum获取还田离田量合计
     * StrawUtilizeSum 已有汇总数据暂时不使用这个方法
     *
     * @param strawUtilizeSums List<StrawUtilizeSum>
     * @return CheckStrawLevelingVo
     */
    private CheckStrawLevelingVo getCountCheckStrawLevelingVo(List<StrawUtilizeSum> strawUtilizeSums) {
        CheckStrawLevelingVo checkStrawLevelingVo = new CheckStrawLevelingVo();
        checkStrawLevelingVo.setStrawType("total");
        checkStrawLevelingVo.setStrawTypeName("合计");

        // 可收集量  用于计算直接还田率。不用于count统计
        BigDecimal collectResourceSum = new BigDecimal("0");

        // 农户分散利用量
        BigDecimal disperseTotalSum = new BigDecimal("0");

        // 主体规模化利用量
        BigDecimal mainTotalSum = new BigDecimal("0");

        //  直接还田量
        BigDecimal proStillFieldSum = new BigDecimal("0");

        // 合计
        BigDecimal countSum = new BigDecimal("0");

        //  item.getProStillField().multiply(new BigDecimal(100)).divide(item.getCollectResource(), 10, RoundingMode.HALF_UP);
        // 直接还田率   (100 /CollectResource ) *  ProStillField
        if (!CollectionUtils.isEmpty(strawUtilizeSums)) {
            for (StrawUtilizeSum strawUtilizeSum : strawUtilizeSums) {
                collectResourceSum = collectResourceSum.add(strawUtilizeSum.getCollectResource() == null ? new BigDecimal("0") : strawUtilizeSum.getCollectResource());
                disperseTotalSum = disperseTotalSum.add(strawUtilizeSum.getDisperseTotal() == null ? new BigDecimal("0") : strawUtilizeSum.getDisperseTotal());
                mainTotalSum = mainTotalSum.add(strawUtilizeSum.getMainTotal() == null ? new BigDecimal("0") : strawUtilizeSum.getMainTotal());
                proStillFieldSum = proStillFieldSum.add(strawUtilizeSum.getProStillField() == null ? new BigDecimal("0") : strawUtilizeSum.getProStillField());
                // 计算合计
                BigDecimal tempCountSum = new BigDecimal("0");
                tempCountSum = tempCountSum.add(strawUtilizeSum.getDisperseTotal() == null ? new BigDecimal("0") : strawUtilizeSum.getDisperseTotal());
                tempCountSum = tempCountSum.add(strawUtilizeSum.getMainTotal() == null ? new BigDecimal("0") : strawUtilizeSum.getMainTotal());
                countSum = countSum.add(tempCountSum);
            }
        }
        checkStrawLevelingVo.setDisperseTotal(disperseTotalSum);
        checkStrawLevelingVo.setMainTotal(mainTotalSum);
        checkStrawLevelingVo.setProStillField(proStillFieldSum);
        checkStrawLevelingVo.setCount(countSum);
        // 计算合计的直接还田率
        // 直接还田率  (100 /CollectResource ) *  ProStillField
        try {
            BigDecimal returnRatioSum = proStillFieldSum.multiply(new BigDecimal("100").divide(collectResourceSum, 10, RoundingMode.HALF_UP));
            checkStrawLevelingVo.setReturnRatio(returnRatioSum);
        } catch (Exception e) {
            e.printStackTrace();
            BigDecimal returnRatioSum = proStillFieldSum.multiply(new BigDecimal("100").divide(new BigDecimal("1"), 10, RoundingMode.HALF_UP));
            checkStrawLevelingVo.setReturnRatio(returnRatioSum);
        }

        return checkStrawLevelingVo;
    }

    /**
     * 根据旧的数据比较新的数据
     *
     * @param item                       新的数据
     * @param oldCheckInfo               旧的数据
     * @param thresholdValueManagerVoMap 阈值信息
     * @param scatteredHouseNumMap       抽样分散户数
     * @param mainBodyNumsMap            市场主体规模化数量
     * @param oldScatteredHouseNumMaps   旧的抽样分散户数
     * @param oldMainBodyNumsMaps        旧的市场主体规模化数量
     * @param checkLast                  是否检查
     */
    private void getColorStateByCheckInfoAndOldCheckInfo(CheckInfo item,
                                                         CheckInfo oldCheckInfo,
                                                         Map<String, ThresholdValueManagerVo> thresholdValueManagerVoMap,
                                                         Map<String, Integer> scatteredHouseNumMap,
                                                         Map<String, Integer> mainBodyNumsMap,
                                                         Map<String, Integer> oldScatteredHouseNumMaps,
                                                         Map<String, Integer> oldMainBodyNumsMaps,
                                                         boolean checkLast

    ) {
        String dataAreaId = item.getAreaId();
        if (oldCheckInfo != null) {
            // 理论资源量合计（吨） theoryNumSum   产生量
            ThresholdValueManagerVo thresholdValueManagerVo1 = thresholdValueManagerVoMap.get(ThresholdEnum.VALUE_MANAGER_TARGET_TYPE_1_CSL.getCode());
            Map<String, String> theoryNumSumColorMap = getColor(thresholdValueManagerVo1, oldCheckInfo.getTheoryNumSum(), item.getTheoryNumSum());
            item.setTheoryNumSumColorState(theoryNumSumColorMap.get(COLOR_KEY));
            item.setTheoryNumSumMessage(theoryNumSumColorMap.get(MESSAGE_KEY));

            // 可收集资源量合计（吨）collectNumSum   可收集量
            ThresholdValueManagerVo thresholdValueManagerVo2 = thresholdValueManagerVoMap.get(ThresholdEnum.VALUE_MANAGER_TARGET_TYPE_1_KSJL.getCode());
            Map<String, String> collectNumSumColorMap = getColor(thresholdValueManagerVo2, oldCheckInfo.getCollectNumSum(), item.getCollectNumSum());
            item.setCollectNumSumColorState(collectNumSumColorMap.get(COLOR_KEY));
            item.setCollectNumSumMessage(collectNumSumColorMap.get(MESSAGE_KEY));

            // 秸秆利用量合计（吨）  strawUtilizeNumSum    综合利用量
            ThresholdValueManagerVo thresholdValueManagerVo3 = thresholdValueManagerVoMap.get(ThresholdEnum.VALUE_MANAGER_TARGET_TYPE_1_ZHLYL.getCode());
            Map<String, String> strawUtilizeNumSumColorMap = getColor(thresholdValueManagerVo3, oldCheckInfo.getStrawUtilizeNumSum(), item.getStrawUtilizeNumSum());
            item.setStrawUtilizeNumSumColorState(strawUtilizeNumSumColorMap.get(COLOR_KEY));
            item.setStrawUtilizeNumSumMessage(strawUtilizeNumSumColorMap.get(MESSAGE_KEY));

            // 综合利用率     synUtilizeNumSum     综合利用率
            ThresholdValueManagerVo thresholdValueManagerVo4 = thresholdValueManagerVoMap.get(ThresholdEnum.VALUE_MANAGER_TARGET_TYPE_1_ZHLYL2.getCode());
            Map<String, String> synUtilizeNumSumColorMap = getColor(thresholdValueManagerVo4, oldCheckInfo.getSynUtilizeNumSum(), item.getSynUtilizeNumSum(), false);
            item.setSynUtilizeNumSumColorState(synUtilizeNumSumColorMap.get(COLOR_KEY));
            item.setSynUtilizeNumSumMessage(synUtilizeNumSumColorMap.get(MESSAGE_KEY));

            // 直接还田量合计（吨）     directReturnNumSum 直接还田量合计
            ThresholdValueManagerVo thresholdValueManagerVo5 = thresholdValueManagerVoMap.get(ThresholdEnum.VALUE_MANAGER_TARGET_TYPE_1_ZJHTL.getCode());
            Map<String, String> directReturnNumSumColorMap = getColor(thresholdValueManagerVo5, oldCheckInfo.getDirectReturnNumSum(), item.getDirectReturnNumSum());
            item.setDirectReturnNumSumColorState(directReturnNumSumColorMap.get(COLOR_KEY));
            item.setDirectReturnNumSumMessage(directReturnNumSumColorMap.get(MESSAGE_KEY));

            // 离田利用量= 市场规模化利用量 + 农户分散利用量  leavingUtilization
//            BigDecimal oldTemp = new BigDecimal("0");
//            oldTemp = oldTemp.add(checkBigDecimal(oldCheckInfo.getMainNumSum()));
//            oldTemp = oldTemp.add(checkBigDecimal(oldCheckInfo.getFarmerSplitNumSum()));

            ThresholdValueManagerVo thresholdValueManagerVo6 = thresholdValueManagerVoMap.get(ThresholdEnum.VALUE_MANAGER_TARGET_TYPE_1_LTLYL.getCode());
            Map<String, String> leavingUtilizationColorMap = getColor(thresholdValueManagerVo6, oldCheckInfo.getLeavingUtilization(), item.getLeavingUtilization());
            item.setLeavingUtilizationColorState(leavingUtilizationColorMap.get(COLOR_KEY));
            item.setLeavingUtilizationMessage(leavingUtilizationColorMap.get(MESSAGE_KEY));


            if (checkLast) {
                // 这二个是直接查询Sql根据区划分组的
                //  抽样分散户数颜色标识  scatteredHouseNumColorState
                Integer scatteredHouseNum = scatteredHouseNumMap.get(dataAreaId) == null ? 0 : scatteredHouseNumMap.get(dataAreaId);
                Integer oldScatteredHouseNum = oldScatteredHouseNumMaps.get(dataAreaId) == null ? 0 : oldScatteredHouseNumMaps.get(dataAreaId);
                item.setScatteredHouseNum(scatteredHouseNum);
                ThresholdValueManagerVo thresholdValueManagerVo7 = thresholdValueManagerVoMap.get(ThresholdEnum.VALUE_MANAGER_TARGET_TYPE_1_CYFSHS.getCode());
                Map<String, String> scatteredHouseNumColorMap = getColor(thresholdValueManagerVo7, new BigDecimal(oldScatteredHouseNum + ""), new BigDecimal(scatteredHouseNum + ""), false);
                item.setScatteredHouseNumColorState(scatteredHouseNumColorMap.get(COLOR_KEY));
                item.setScatteredHouseNumMessage(scatteredHouseNumColorMap.get(MESSAGE_KEY));

                // 市场主体规模化数量颜色标识 mainBodyNumColorState
                Integer mainBodyNum = mainBodyNumsMap.get(dataAreaId) == null ? 0 : mainBodyNumsMap.get(dataAreaId);
                Integer oldMainBodyNum = oldMainBodyNumsMaps.get(dataAreaId) == null ? 0 : oldMainBodyNumsMaps.get(dataAreaId);
                item.setMainBodyNum(mainBodyNum);
                ThresholdValueManagerVo thresholdValueManagerVo8 = thresholdValueManagerVoMap.get(ThresholdEnum.VALUE_MANAGER_TARGET_TYPE_1_SCZTGMHSL.getCode());
                Map<String, String> oldMainBodyNumColorMap = getColor(thresholdValueManagerVo8, new BigDecimal(oldMainBodyNum + ""), new BigDecimal(mainBodyNum + ""), false);
                item.setMainBodyNumColorState(oldMainBodyNumColorMap.get(COLOR_KEY));
                item.setMainBodyNumMessage(oldMainBodyNumColorMap.get(MESSAGE_KEY));
            }
        }
    }

    /**
     * 获取合计的CountInfo
     *
     * @param checkInfoChina       各个区划的审核信息
     * @param scatteredHouseNumMap 抽样分散户数
     * @param mainBodyNumsMap      市场主体规模化数量
     * @return 获取将各个属性做了累加之后的CheckInfo
     */
    private CheckInfo getCountCheckInfo(List<CheckInfo> checkInfoChina,
                                        Map<String, Integer> scatteredHouseNumMap,
                                        Map<String, Integer> mainBodyNumsMap) {
        CheckInfo checkInfo = new CheckInfo();
        checkInfo.setAreaName("合计");
        if (!CollectionUtils.isEmpty(checkInfoChina)) {
            // 合计没有审核状态
            // 农户分散利用量合计（吨） 和  市场主体规模化利用量合计（吨） 可以为空
            // farmerSplitNumSum   mainNumSum
            BigDecimal theoryNumSum = new BigDecimal("0");
            BigDecimal collectNumSum = new BigDecimal("0");
            BigDecimal strawUtilizeNumSum = new BigDecimal("0");
            BigDecimal synUtilizeNumSum = new BigDecimal("0");
            BigDecimal directReturnNumSum = new BigDecimal("0");
            BigDecimal leavingUtilization = new BigDecimal("0");
            Integer scatteredHouseNum = 0;
            Integer mainBodyNum = 0;
            List<String> status = new ArrayList<>();
            status.add(AuditStatusEnum.SAVE.getCode());
            status.add(AuditStatusEnum.RETURNED.getCode());
            for (CheckInfo item : checkInfoChina) {
                if (status.contains(item.getStatus())) {
                    // 未提及及退回不参与合计值计算
                    continue;
                }
                theoryNumSum = theoryNumSum.add(BigDecimalUtil.valueIsNull(item.getTheoryNumSum()));
                collectNumSum = collectNumSum.add(BigDecimalUtil.valueIsNull(item.getCollectNumSum()));
                strawUtilizeNumSum = strawUtilizeNumSum.add(BigDecimalUtil.valueIsNull(item.getStrawUtilizeNumSum()));
                synUtilizeNumSum = synUtilizeNumSum.add(BigDecimalUtil.valueIsNull(item.getSynUtilizeNumSum()));
                directReturnNumSum = directReturnNumSum.add(BigDecimalUtil.valueIsNull(item.getDirectReturnNumSum()));
                leavingUtilization = leavingUtilization.add(BigDecimalUtil.valueIsNull(item.getLeavingUtilization()));
                // 计算离田利用量  = 市场规模化利用量 + 农户分散利用量
                // 取出区划的抽样分散户数
                Integer areaScatteredHouseNum = scatteredHouseNumMap.get(item.getAreaId());
                if (areaScatteredHouseNum == null) {
                    areaScatteredHouseNum = 0;
                }
                item.setScatteredHouseNum(areaScatteredHouseNum);
                scatteredHouseNum += areaScatteredHouseNum;
                // 设置区划的市场主体规模化数量
                Integer areaMainBodyNum = mainBodyNumsMap.get(item.getAreaId());
                if (areaMainBodyNum == null) {
                    areaMainBodyNum = 0;
                }
                item.setMainBodyNum(areaMainBodyNum);
                mainBodyNum += areaMainBodyNum;
            }


            checkInfo.setTheoryNumSum(theoryNumSum);
            checkInfo.setCollectNumSum(collectNumSum);
            checkInfo.setStrawUtilizeNumSum(strawUtilizeNumSum);
            //checkInfo.setSynUtilizeNumSum(synUtilizeNumSum);
            checkInfo.setSynUtilizeNumSum(collectNumSum.compareTo(new BigDecimal(0)) == 0 ? new BigDecimal(0) : strawUtilizeNumSum.divide(collectNumSum, 4, RoundingMode.HALF_UP).multiply(new BigDecimal(100)));
            checkInfo.setDirectReturnNumSum(directReturnNumSum);
            checkInfo.setLeavingUtilization(leavingUtilization);
            checkInfo.setScatteredHouseNum(scatteredHouseNum);
            checkInfo.setMainBodyNum(mainBodyNum);
        }
        return checkInfo;
    }

    /**
     * 将List<Map<String,Object>> 转为Map
     *
     * @param maps List<Map<String,String>>
     * @return Map<String, Integer>
     */
    private Map<String, Integer> getMap(List<Map<String, Object>> maps) {
        Map<String, Integer> scatteredHouseNumMap = Maps.newHashMap();
        if (!CollectionUtils.isEmpty(maps)) {
            if (!CollectionUtils.isEmpty(maps)) {
                maps.forEach(item -> {
                    String tempAreaId = (String) item.get("areaid");
                    try {
                        Long tempCountNum = (Long) item.get("countnum");

                        Integer t = tempCountNum == null ? 0 : tempCountNum.intValue();
                        scatteredHouseNumMap.put(tempAreaId, t);
                    } catch (Exception e) {
                        e.printStackTrace();
                        scatteredHouseNumMap.put(tempAreaId, 0);
                    }
                });
            }
        }
        return scatteredHouseNumMap;
    }


    /**
     * 获取颜色标识
     * 如果A比B大,则A比B大百分之(A-B)/B*100
     * 如果A比B小,则A比B小百分之(B-A)/B*100
     *
     * @param thresholdValueManagerVo 阈值
     * @param oldValue                去年的值
     * @param value                   今年的值
     * @param flag                    false 除10000  true  不除10000  默认 除10000
     * @return 颜色标识   0 没颜色   1  超过阈值    2  低于阈值
     */
    private static Map<String, String> getColor(ThresholdValueManagerVo thresholdValueManagerVo, BigDecimal oldValue, BigDecimal value, boolean... flag) {
        Map<String, String> returnValue = Maps.newHashMap();
        returnValue.put(COLOR_KEY, CheckColorEnum.COLOR_UNCHANGE.getCode());
        returnValue.put(MESSAGE_KEY, "此数值正常");

        if (thresholdValueManagerVo == null) {
            return returnValue;
        }
        if (oldValue == null) {
            oldValue = new BigDecimal("0");
        }
        if (value == null) {
            value = new BigDecimal("0");
        }
        // a.compareTo(b) == -1   a小于b  ;a.compareTo(b) == 0   a等于b ; a.compareTo(b) == 1   a大于b
        // 如果 value大于oldValue 就比较大于的
        String computerType = thresholdValueManagerVo.getComputerType();
        if (StringUtils.isBlank(computerType)) {
            log.warn("计算失败，计算类型为空， 可能是该指标未添加阈值数据");
            return returnValue;
        }
        // 阈值是万吨   具体的值是吨
        // 固定阈值2是大于等于   阈值1是小于等于
        // 值为1 表示 value比 oldValue 大
        // 值为2表示  value比 oldValue 小
        String tempStr;
        if (value.compareTo(oldValue) > 0) {
            tempStr = "1";
        } else if (value.compareTo(oldValue) < 0) {
            tempStr = "2";
        } else {
            return returnValue;
        }
        BigDecimal tempValue;
        try {
            tempValue = value.subtract(oldValue).abs();  // 今年-去年
        } catch (Exception e) {
            e.printStackTrace();
            tempValue = BigDecimal.ZERO;
        }

        // 是否超过某个阈值    单位 万吨
        BigDecimal value2 = BigDecimalUtil.getBigDecimalByStr(thresholdValueManagerVo.getValue2());
        // 是否低于某个阈值
        BigDecimal value1 = BigDecimalUtil.getBigDecimalByStr(thresholdValueManagerVo.getValue1());
        // 都为0就不比较
        if (value1.compareTo(BigDecimal.ZERO) == 0 && value2.compareTo(BigDecimal.ZERO) == 0) {
            return returnValue;
        }

        BigDecimal compareValue = new BigDecimal("0");

        if (ThresholdEnum.VALUE_MANAGER_COMPUTER_TYPE_1.getCode().equals(computerType)) {
            // 数值
            // 二个值的差值除10000
            // true 除10000
            if (flag != null && flag.length > 0) {
                if (!flag[0]) {
                    compareValue = tempValue.setScale(2, BigDecimal.ROUND_HALF_UP);
                } else {
                    compareValue = BigDecimalUtil.getTenThousand(tempValue).setScale(2, BigDecimal.ROUND_HALF_UP);
                }
            } else {
                compareValue = BigDecimalUtil.getTenThousand(tempValue).setScale(2, BigDecimal.ROUND_HALF_UP);
            }

        } else if (ThresholdEnum.VALUE_MANAGER_COMPUTER_TYPE_2.getCode().equals(computerType)) {
            // 比例   要么是今年减去去年 要么是去年减去今年
            // 减了之后/ 去年的值   * 100
            if (oldValue.compareTo(BigDecimal.ZERO) == 0) {
                compareValue = BigDecimal.ZERO;
            } else {
                compareValue = tempValue.divide(oldValue, 2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100"));
            }
        }

        // 获取指标单位
        String unit1 = ThresholdEnum.getUnit(thresholdValueManagerVo.getTargetType());
        // 如果需要计算比例， 那么单位是%
        String unit = ThresholdEnum.VALUE_MANAGER_COMPUTER_TYPE_1.getCode().equals(computerType) ? unit1 : "%";
//        if (flag != null && flag.length > 0 && !flag[0]) {
//            // 如果是数值    并且没有除10000  有可能是个  有可能是%
//            if(thresholdValueManagerVo.getTargetType().equals(ThresholdEnum.VALUE_MANAGER_TARGET_TYPE_1_SCZTGMHSL.getCode()) ||
//                    ThresholdEnum.VALUE_MANAGER_TARGET_TYPE_1_CYFSHS.getCode().equals(thresholdValueManagerVo.getTargetType())){
//                unit = "个";
//            }else {
//                unit = "";
//            }
//        }
        String userLevel = ThreadLocalUserLevelUtil.getValue();
        String message = "此数值正常";
        String code = CheckColorEnum.COLOR_UNCHANGE.getCode();
        if ("1".equals(tempStr)) {
            // 是否超过某个阈值
            if (compareValue.compareTo(value2) >= 0) {
                message = "此数值比去年数值高" + compareValue + unit;
                code = CheckColorEnum.COLOR_HIGHT.getCode();
                returnValue.put(COLOR_KEY, CheckColorEnum.COLOR_HIGHT.getCode());
            }
        } else {
            // 是否低于某个阈值
            if (compareValue.compareTo(value1) >= 0) {
                message = "此数值比去年数值低" + compareValue + unit;
                code = CheckColorEnum.COLOR_LOW.getCode();
                returnValue.put(COLOR_KEY, CheckColorEnum.COLOR_LOW.getCode());
            }
        }
        if (RegionLevel.MINISTRY.getCode().equals(userLevel) && !CheckColorEnum.COLOR_UNCHANGE.getCode().equals(code)) {
            message += ",超过设置的阈值" + value2 + unit;
        }
        returnValue.put(COLOR_KEY, code);
        returnValue.put(MESSAGE_KEY, message);
        return returnValue;
    }


}
