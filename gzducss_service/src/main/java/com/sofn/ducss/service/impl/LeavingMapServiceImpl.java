package com.sofn.ducss.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sofn.ducss.enums.Constants;
import com.sofn.ducss.enums.RegionEnum;
import com.sofn.ducss.enums.RegionLevel;
import com.sofn.ducss.enums.SixRegionEnum;
import com.sofn.ducss.exception.SofnException;
import com.sofn.ducss.map.AdPointData;
import com.sofn.ducss.map.MapViewData;
import com.sofn.ducss.mapper.LeavingMapMapper;
import com.sofn.ducss.mapper.ProStillDetailMapper;
import com.sofn.ducss.model.ProStillDetail;
import com.sofn.ducss.service.LeavingMapService;
import com.sofn.ducss.service.SyncSysRegionService;
import com.sofn.ducss.model.SysDict;
import com.sofn.ducss.sysapi.SysApi;
import com.sofn.ducss.util.SysDictUtil;
import com.sofn.ducss.util.SysRegionUtil;
import com.sofn.ducss.util.IdUtil;
import com.sofn.ducss.vo.DateShow.ColumnPieChartVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class LeavingMapServiceImpl implements LeavingMapService {

    @Autowired
    private LeavingMapMapper leavingMapMapper;

    @Autowired
    private SyncSysRegionService syncSysRegionService;

    @Autowired
    private SysApi sysApi;

    /**
     * 第一个值
     */
    private final String VALUE1 = "value1";

    /**
     * 第二个值
     */
    private final String VALUE2 = "value2";

    /**
     * 区划ID
     */
    private final String AREAID = "areaid";

    /**
     * 第一个值 / 第二个值 * 100
     */
    private final String RETURNRATIO = "returnratio";


    @Autowired
    private ProStillDetailMapper proStillDetailMapper;

    @Override
    public Map<String, Map<String, Object>> getValueByDataType(String year, String areaCode, String dataType, String strawType) {
        if (StringUtils.isBlank(areaCode)) {
            throw new SofnException("区域ID不能为空");
        }
        if (StringUtils.isBlank(year)) {
            throw new SofnException("年度不能为空");
        }

        String childrenRegionIds = getChildrenIds(areaCode, year);
        if (StringUtils.isBlank(childrenRegionIds)) {
            return null;
        }

        // 1 直接还田比例 2 农户分散利用比例 3 市场化主体利用比例
        List<Map<String, Object>> returnLeave;
        if ("1".equals(dataType)) {
            returnLeave = leavingMapMapper.getReturnLeave(IdUtil.getIdsByStr(childrenRegionIds), year, strawType);
        } else if ("2".equals(dataType)) {
            returnLeave = leavingMapMapper.getDisperseTotalRatio(IdUtil.getIdsByStr(childrenRegionIds), year, strawType);
        } else if ("3".equals(dataType)) {
            returnLeave = leavingMapMapper.getMainTotalRatio(IdUtil.getIdsByStr(childrenRegionIds), year, strawType);
        } else {
            throw new SofnException("不支持的数据");
        }
        Map<String, Map<String, Object>> valueMap = Maps.newHashMap();
//        mergeData(returnLeave);
        if (!CollectionUtils.isEmpty(returnLeave)) {
            returnLeave.forEach(item -> {
                String tempAreaId = (String) item.get(AREAID);
                valueMap.put(tempAreaId, item);
            });
        }
        // 将新疆建设兵团的数据放入新疆并且移除新疆建设兵团的数据
        Map<String, Object> stringObjectMap = valueMap.get(Constants.XINJIANG_CONSTRUCTION_CORPS);
        if (!CollectionUtils.isEmpty(stringObjectMap)) {
            Map<String, Object> stringObjectMap1 = valueMap.get(Constants.XINJIANG);
            if (CollectionUtils.isEmpty(stringObjectMap1)) {
                stringObjectMap1 = Maps.newHashMap();
            }
            // 取出新疆的数据
            BigDecimal value1 = stringObjectMap1.get(VALUE1) == null ? BigDecimal.ZERO : (BigDecimal) stringObjectMap1.get(VALUE1);
            BigDecimal value2 = stringObjectMap1.get(VALUE2) == null ? BigDecimal.ZERO : (BigDecimal) stringObjectMap1.get(VALUE2);
            // 取出新疆兵团的数据
            BigDecimal oldValue1 = stringObjectMap.get(VALUE1) == null ? BigDecimal.ZERO : (BigDecimal) stringObjectMap.get(VALUE1);
            BigDecimal oldValue2 = stringObjectMap.get(VALUE2) == null ? BigDecimal.ZERO : (BigDecimal) stringObjectMap.get(VALUE2);

            value1 = value1.add(oldValue1);
            value2 = value2.add(oldValue2);


            BigDecimal returnratio = value2.compareTo(BigDecimal.ZERO) == 0 ? BigDecimal.ZERO : value1.divide(value2, 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100"));
            stringObjectMap1.put(VALUE1, value1);
            stringObjectMap1.put(VALUE2, value2);
            stringObjectMap1.put(RETURNRATIO, returnratio);
            valueMap.put(Constants.XINJIANG, stringObjectMap1);
            // 新疆兵团已经加入新疆，所以直接移除
            valueMap.remove(Constants.XINJIANG_CONSTRUCTION_CORPS);
        }
        return valueMap;
    }

    @Override
    public MapViewData getLeavingRatio(String year, String administrativeLevel, String areaCode, String dataType,
                                       boolean isSixRegion, String sixRegionCodes, String strawType) {
        if (isSixRegion) {
            if (!RegionEnum.ROOT_CODE.getCode().equals(areaCode)) {
                throw new SofnException("查看六大区数据areaCode只能传入" + RegionEnum.ROOT_CODE.getCode());
            }
        }

        MapViewData mapViewData = new MapViewData();
        mapViewData.setAdLevel(administrativeLevel);
        // 获取值
        Map<String, Map<String, Object>> valueMap = this.getValueByDataType(year, areaCode, dataType, strawType);
        // 获取行政区划的代码和名字
        String regionYear = syncSysRegionService.getYearByYear(year);
        String childrenRegionIds = getChildrenIds(areaCode, year);
        if (StringUtils.isBlank(childrenRegionIds)) {
            return null;
        }
        Map<String, String> regionNameMapsByCodes = sysApi.getRegionNameMapsByCodes(childrenRegionIds, Integer.valueOf(regionYear));
        //Map<String, String> regionNameMapsByCodes = syncSysRegionService.getNameMap(IdUtil.getIdsByStr(childrenRegionIds), regionYear);
        if (!isSixRegion) {
            String yearByYear = syncSysRegionService.getYearByYear(year);
            String level = syncSysRegionService.getLevel(areaCode, yearByYear);
            Map<String, BigDecimal> returnRatioMap = new HashMap<>();
            if ((RegionLevel.CITY.getCode().equals(level) || RegionLevel.COUNTY.getCode().equals(level)) && dataType.equals("1")) {
                List<ProStillDetail> list = proStillDetailMapper.getProStillDetailListByAreaIdGroupByAreaId(IdUtil.getIdsByStr(childrenRegionIds), year, strawType);
                returnRatioMap = list.stream().collect(Collectors.toMap(ProStillDetail::getAreaId, ProStillDetail::getReturnRatio));
            }
            // 组装各省的地图数据
            List<AdPointData> pointDataArrayList = Lists.newArrayList();
            for (String areaIdKey : regionNameMapsByCodes.keySet()) {
                AdPointData adPointData = new AdPointData();
                String name = regionNameMapsByCodes.get(areaIdKey);
                Map<String, Object> value = valueMap.get(areaIdKey);
                if (RegionLevel.COUNTY.getCode().equals(level) && dataType.equals("1")) {
                    BigDecimal bigDecimalValue = returnRatioMap.get(areaIdKey);
                    if (bigDecimalValue != null) {
                        adPointData.setIndexValue(bigDecimalValue.setScale(2, RoundingMode.HALF_UP).toString());
                    } else {
                        adPointData.setIndexValue("0");
                    }
                } else if (RegionLevel.CITY.getCode().equals(level) && dataType.equals("1")) {
                    BigDecimal bigDecimalValue = returnRatioMap.get(areaIdKey);
                    if (bigDecimalValue != null) {
                        adPointData.setIndexValue(bigDecimalValue.setScale(2, RoundingMode.HALF_UP).toString());
                    } else {
                        adPointData.setIndexValue("0");
                    }
                } else {
                    if (!CollectionUtils.isEmpty(value)) {
                        BigDecimal bigDecimalValue = ((BigDecimal) value.get(RETURNRATIO)).setScale(2, BigDecimal.ROUND_HALF_UP);
                        adPointData.setIndexValue(bigDecimalValue.toString());
                    } else {
                        adPointData.setIndexValue("0");
                    }
                }
                adPointData.setAdRegionName(name);
                adPointData.setAdRegionCode(areaIdKey);
                pointDataArrayList.add(adPointData);
            }
            Map<String, List<AdPointData>> listHashMap = Maps.newHashMap();
            listHashMap.put("adPointDataList", pointDataArrayList);
            mapViewData.setAdPointDataList(listHashMap);
        } else {
            // 组装6大区的地图数据
            // 2. 如果MapViewData不等于null就组装地图数据
            List<AdPointData> pointDataArrayList = Lists.newArrayList();
            if (StringUtils.isBlank(sixRegionCodes)) {
                Map<String, BigDecimal> sixMapViewData = getSixMapViewData(valueMap);
                for (SixRegionEnum c : SixRegionEnum.values()) {
                    AdPointData adPointData = new AdPointData();
                    adPointData.setAdRegionCode(c.getCode());
                    adPointData.setAdRegionName(c.getDescription());
                    BigDecimal sixRegionValue = sixMapViewData.get(c.name());
                    adPointData.setIndexValue(sixRegionValue == null ? BigDecimal.ZERO.toString() : sixRegionValue.toString());
                    pointDataArrayList.add(adPointData);
                }
            } else {
                Map<String, String> regionNameMapsByCodes1 = sysApi.getRegionNameMapsByCodes(sixRegionCodes, Integer.valueOf(regionYear));
                // Map<String, String> regionNameMapsByCodes1 = syncSysRegionService.getNameMap(IdUtil.getIdsByStr(sixRegionCodes), regionYear);
                regionNameMapsByCodes1.forEach((k, v) -> {
                    AdPointData adPointData = new AdPointData();
                    adPointData.setAdRegionCode(k);
                    adPointData.setAdRegionName(v);
                    Map<String, Object> stringObjectMap = valueMap.get(k);
                    if (CollectionUtils.isEmpty(stringObjectMap)) {
                        adPointData.setIndexValue("0");
                    } else {
                        BigDecimal temp = (BigDecimal) stringObjectMap.get(RETURNRATIO);
                        adPointData.setIndexValue(temp.toString());
                    }
                    pointDataArrayList.add(adPointData);
                });
            }
            Map<String, List<AdPointData>> listHashMap = Maps.newHashMap();
            listHashMap.put("adPointDataList", pointDataArrayList);
            mapViewData.setAdPointDataList(listHashMap);
        }
        return mapViewData;
    }

    @Override
    public List<ColumnPieChartVo> getColumnPieChartVo(String year, String areaCode, String dataType, boolean isSixRegion, String sixRegionCodes, String strawType) {
        List<ColumnPieChartVo> returnValue = Lists.newArrayList();
        if (isSixRegion) {
            if (!RegionEnum.ROOT_CODE.getCode().equals(areaCode)) {
                throw new SofnException("查看六大区数据areaCode只能传入" + RegionEnum.ROOT_CODE.getCode());
            }
        }

        Map<String, Map<String, Object>> valueByDataType = this.getValueByDataType(year, areaCode, dataType, strawType);
        if (CollectionUtils.isEmpty(valueByDataType)) {
            return returnValue;
        }

        String childrenRegionIds = getChildrenIds(areaCode, year);
        if (StringUtils.isBlank(childrenRegionIds)) {
            return Lists.newArrayList();
        }
        String regionYear = syncSysRegionService.getYearByYear(year);

        Map<String, String> regionNameMapsByCodes = sysApi.getRegionNameMapsByCodes(childrenRegionIds, Integer.valueOf(regionYear));
        // Map<String, String> regionNameMapsByCodes = syncSysRegionService.getNameMap(IdUtil.getIdsByStr(childrenRegionIds), regionYear);
        if (!isSixRegion) {
            String yearByYear = syncSysRegionService.getYearByYear(year);
            String level = syncSysRegionService.getLevel(areaCode, yearByYear);
            if (RegionLevel.COUNTY.getCode().equals(level) && dataType.equals("1")) {
                // 查询秸秆生产量与直接还田量
                ProStillDetail detail = proStillDetailMapper.getProStillDetailListByAreaIdAndStrawType(areaCode, year, strawType);
                ColumnPieChartVo columnPieChartVo = new ColumnPieChartVo(regionNameMapsByCodes.get(areaCode), detail.getReturnRatio());
                returnValue.add(columnPieChartVo);
            } else if (RegionLevel.CITY.getCode().equals(level) && dataType.equals("1")) {
                List<ProStillDetail> list = proStillDetailMapper.getProStillDetailListByAreaIdGroupByAreaId(IdUtil.getIdsByStr(childrenRegionIds), year, strawType);
                for (ProStillDetail detail : list) {
                    ColumnPieChartVo columnPieChartVo = new ColumnPieChartVo(regionNameMapsByCodes.get(detail.getAreaId().toString()), detail.getReturnRatio());
                    returnValue.add(columnPieChartVo);
                }
            } else {
                for (Map.Entry<String, Map<String, Object>> entry : valueByDataType.entrySet()) {
                    String k = entry.getKey();
                    Map<String, Object> v = entry.getValue();
                    ColumnPieChartVo columnPieChartVo = new ColumnPieChartVo(regionNameMapsByCodes.get(k), (BigDecimal) v.get(RETURNRATIO));
                    returnValue.add(columnPieChartVo);
                }
            }
        } else {
            // 组装六大区的数据
            if (StringUtils.isBlank(sixRegionCodes)) {
                Map<String, BigDecimal> sixMapViewData = getSixMapViewData(valueByDataType);
                if (!CollectionUtils.isEmpty(sixMapViewData)) {
                    sixMapViewData.forEach((k, v) -> {
                        String name = SixRegionEnum.getSixRegionEnum(k).getDescription();
                        ColumnPieChartVo columnPieChartVo = new ColumnPieChartVo(name, v);
                        returnValue.add(columnPieChartVo);
                    });
                }
            } else {
                Map<String, String> regionNameMapsByCodes1 = sysApi.getRegionNameMapsByCodes(sixRegionCodes, Integer.valueOf(regionYear));
                // Map<String, String> regionNameMapsByCodes1 = syncSysRegionService.getNameMap(IdUtil.getIdsByStr(sixRegionCodes), regionYear);
                if (!CollectionUtils.isEmpty(regionNameMapsByCodes1)) {
                    regionNameMapsByCodes1.forEach((k, v) -> {
                        Map<String, Object> stringObjectMap = valueByDataType.get(k);
                        BigDecimal temp = BigDecimal.ZERO;
                        if (!CollectionUtils.isEmpty(stringObjectMap)) {
                            temp = (BigDecimal) stringObjectMap.get(RETURNRATIO);
                        }
                        ColumnPieChartVo columnPieChartVo = new ColumnPieChartVo(v, temp);
                        returnValue.add(columnPieChartVo);
                    });
                }
            }
        }

        // 排序
        if (!CollectionUtils.isEmpty(returnValue)) {
            returnValue.sort((o1, o2) -> {
                if (((o1 == null) || (o2 == null))) {
                    return 0;
                }
                if (o1.getValue().compareTo(o2.getValue()) > 0) {
                    return -1;
                } else if (o1.getValue().compareTo(o2.getValue()) < 0) {
                    return 1;
                }
                return 0;
            });
        }

        return returnValue;
    }

    @Override
    public List<ColumnPieChartVo> getLeavingPieChart(String year, String areaCode, String dataType, boolean isSixRegion, String sixRegionCodes) {
        if (StringUtils.isBlank(year)) {
            throw new SofnException("年度必传");
        }

        if (StringUtils.isBlank(areaCode)) {
            throw new SofnException("区域ID必传");
        }

        if (StringUtils.isBlank(dataType)) {
            throw new SofnException("数据类型必传");
        }

        try {
            syncSysRegionService.checkUserCanShow(Lists.newArrayList(areaCode), year);
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof SofnException) {
                return null;
            }
            throw new SofnException("当前区划未提交或者无权限访问该区划的信息");
        }

        // 1 直接还田比例 2 农户分散利用比例 3 市场化主体利用比例
        // 1. 组装完整Sql必要信息
        String tableName = "straw_utilize_sum";
        String value1;
        String value2 = "a.collect_resource";
        if ("1".equals(dataType)) {
            tableName = "return_leave_sum";
            value1 = "a.pro_still_field";
        } else if ("2".equals(dataType)) {
            value1 = "a.disperse_total";
        } else if ("3".equals(dataType)) {
            value1 = "a.main_total";
        } else {
            throw new SofnException("不支持的数据");
        }
        String retionYear = syncSysRegionService.getYearByYear(year);
        // 2. 区分情况
        List<Map<String, Object>> leavingPieChart;

        List<String> areaCode1 = syncSysRegionService.getAreaId(year, retionYear, Lists.newArrayList(areaCode), false);
        if (CollectionUtils.isEmpty(areaCode1)) {
            String level = syncSysRegionService.getLevel(areaCode, retionYear);
            if (RegionLevel.COUNTY.getCode().equals(level)) {
                areaCode1 = Lists.newArrayList(areaCode);
            } else {
                log.error("当前用户区域{}下无任何填报数据", areaCode);
                return Lists.newArrayList();
            }


        }

        if (!isSixRegion) {
            String yearByYear = syncSysRegionService.getYearByYear(year);
            String level = syncSysRegionService.getLevel(areaCode, yearByYear);
            //秸秆类型
            List<SysDict> dictList = SysDictUtil.getDictList(Constants.DictionaryType.STRAW_TYPE);
            HashMap<String, String> strawTypeMap = new HashMap<>();
            for (SysDict sysDict : dictList) {
                strawTypeMap.put(sysDict.getDictcode(), sysDict.getDictname());
            }
            if (RegionLevel.COUNTY.getCode().equals(level) && dataType.equals("1")) {
                // 查询秸秆生产量与直接还田量
                List<ProStillDetail> list = proStillDetailMapper.getProStillDetailListByAreaIdGroupByStrawType(areaCode, year, null);
                List<ColumnPieChartVo> columnPieChartVos = Lists.newArrayList();
                for (ProStillDetail detail : list) {
                    ColumnPieChartVo columnPieChartVo = new ColumnPieChartVo();
                    columnPieChartVo.setValue(detail.getReturnRatio());
                    columnPieChartVo.setName(strawTypeMap.get(detail.getStrawType()));
                    columnPieChartVos.add(columnPieChartVo);
                }
                columnPieChartVos.sort(new Comparator<ColumnPieChartVo>() {
                    @Override
                    public int compare(ColumnPieChartVo o1, ColumnPieChartVo o2) {
                        return o2.getValue().compareTo(o1.getValue());
                    }
                });
                return columnPieChartVos;
            }
            // 不是六大区就直接返回该区划的信息, 如果是全国就查询出省级ID，然后做SUM操作
            leavingPieChart = leavingMapMapper.getLeavingPieChart(areaCode1, year, tableName, value1, value2);
        } else {
            if (StringUtils.isBlank(sixRegionCodes)) {
                // 是六大区并且没有传入sixRegionCodes就表示全国的数据
                leavingPieChart = leavingMapMapper.getLeavingPieChart(areaCode1, year, tableName, value1, value2);
            } else {
                // 是六大区并且传入了sixRegionCodes 就只显示传入的区划的数据
                // 查可以访问的所有的县
                List<String> areaCode2 = syncSysRegionService.getAreaId(year, retionYear, IdUtil.getIdsByStr(sixRegionCodes), false);
                if (CollectionUtils.isEmpty(areaCode2)) {
                    return Lists.newArrayList();
                }
                leavingPieChart = leavingMapMapper.getLeavingPieChart(areaCode2, year, tableName, value1, value2);
            }
        }
        // 3. 组装返回数据
        if (CollectionUtils.isEmpty(leavingPieChart)) {
            return Lists.newArrayList();
        }
        List<ColumnPieChartVo> columnPieChartVos = Lists.newArrayList();
        leavingPieChart.forEach(item -> {
            String dictName = (String) item.get("dictname");
            BigDecimal tempValue1 = (BigDecimal) item.get(VALUE1);
            BigDecimal tempValue2 = (BigDecimal) item.get(VALUE2);
            // 计算比例
            BigDecimal temp;
            if (tempValue2.compareTo(BigDecimal.ZERO) == 0) {
                temp = BigDecimal.ZERO;
            } else {
                // VALUE1 / VALUE2 * 100
                temp = tempValue1.divide(tempValue2, 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100"));
            }
            ColumnPieChartVo columnPieChartVo = new ColumnPieChartVo(dictName, temp);
            columnPieChartVos.add(columnPieChartVo);
        });

        // 排序
        if (!CollectionUtils.isEmpty(columnPieChartVos)) {
            columnPieChartVos.sort((o1, o2) -> {
                if (((o1 == null) || (o2 == null))) {
                    return 0;
                }
                if (o1.getValue().compareTo(o2.getValue()) > 0) {
                    return -1;
                } else if (o1.getValue().compareTo(o2.getValue()) < 0) {
                    return 1;
                }
                return 0;
            });
        }
        return columnPieChartVos;
    }


    // ============================================================================PRIVATE方法分割线

    /**
     * 获取当前区划下面的区划ID
     *
     * @param areaCode 区划ID
     * @param year     区划年度
     * @return 当前区划下面的区划ID
     */
    private String getChildrenIds(String areaCode, String year) {
        try {
            syncSysRegionService.checkUserCanShow(Lists.newArrayList(areaCode), year);
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof SofnException) {
                return null;
            }
            throw new SofnException("当前区划未提交或者无权限访问该区划的信息");
        }
        String childrenRegionIds = SysRegionUtil.getChildrenRegionIds(areaCode);
        if (StringUtils.isBlank(childrenRegionIds)) {
            // 如果是县级就查询本身的数据
            String yearByYear = syncSysRegionService.getYearByYear(year);
            String level = syncSysRegionService.getLevel(areaCode, yearByYear);
            if (RegionLevel.COUNTY.getCode().equals(level)) {
                childrenRegionIds = areaCode;
            } else {
                throw new SofnException("当前区划没有下级区划");
            }
        }
        return childrenRegionIds;

    }

    /**
     * 计算六大区的各指标比列
     *
     * @param valueMap 数据值
     * @return Map<String, BigDecimal>  六大区的数据
     */
    private Map<String, BigDecimal> getSixMapViewData(Map<String, Map<String, Object>> valueMap) {

        // 1. 计算六大区的比例
        List<String> strings = Lists.newArrayList("NORTH_REGION", "NORTH_REGION", "NORTHEAST_REGION", "NORTHWEST_REGION", "SOUTHWEST_REGION", "SOUTH_REGION");
        Map<String, BigDecimal> sixRegionData = Maps.newHashMap();
        for (SixRegionEnum c : SixRegionEnum.values()) {
            // 计算比例
            BigDecimal temp = BigDecimal.ZERO;
            if (strings.contains(c.name())) {
                String code = c.getCode();
                List<String> idsByStr = IdUtil.getIdsByStr(code);
                // 遍历各六大区的区划数据  并且将属于当前大区的区划的数据做累加操作
                BigDecimal value1Count = new BigDecimal("0");
                BigDecimal value2Count = new BigDecimal("0");
                for (String item : idsByStr) {
                    if (code.contains(item)) {
                        Map<String, Object> value = valueMap.get(item);
                        if (!CollectionUtils.isEmpty(value)) {
                            value1Count = value1Count.add((BigDecimal) value.get(VALUE1));
                            value2Count = value2Count.add((BigDecimal) value.get(VALUE2));
                        }
                    }
                }
                if ((value2Count.compareTo(BigDecimal.ZERO) != 0)) {
                    if (value2Count.compareTo(BigDecimal.ZERO) == 0) {
                        temp = BigDecimal.ZERO;
                    } else {
                        temp = value1Count.divide(value2Count, 4, BigDecimal.ROUND_HALF_UP)
                                .multiply(new BigDecimal("100"));
                    }
                }
            }
            sixRegionData.put(c.name(), temp);
        }
        return sixRegionData;
    }


}
