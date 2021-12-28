package com.sofn.fdpi.service.impl;

import com.google.common.collect.Maps;
import com.sofn.common.map.*;
import com.sofn.fdpi.mapper.StatisticMapper;
import com.sofn.fdpi.service.SpeService;
import com.sofn.fdpi.service.StatisticService;
import com.sofn.fdpi.vo.AssembleMapDataVo;
import com.sofn.fdpi.vo.StatisticVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service("statisticService")
public class StatisticServiceImpl implements StatisticService {
    @Autowired
    private SpeService speService;

    @Autowired
    private StatisticMapper statisticMapper;

    /**
     * 获取页面加载的下拉数据
     * wXY
     * 2020-7-30 18:28:35
     *
     * @return List<MapConditions>
     */
    @Override
    public List<MapConditions> getMapConditions() {
        List<MapConditions> list = new ArrayList<>();
        //获取有效的物种
        Map<String, String> speciesMap = speService.getMapForAllSpecies();

        MapConditions mapConditions = new MapConditions();
        mapConditions.setCode("speciesList");
        mapConditions.setValueList(speciesMap);
        list.add(mapConditions);

        return list;
    }

    /**
     * 获取地图视图数据
     *
     * @param index     指标
     * @param adLevel   行政级别{@link com.sofn.common.map.MapAdLevel}
     * @param adCode    行政区域代码或行政区域名称
     * @param speciesId 物种id
     * @param year      年度
     */
    @Override
    public MapViewData getMapViewData(String index, String adLevel, String adCode, String speciesId, String year) {
        MapViewData mapViewData = new MapViewData();
        mapViewData.setAdLevel(adLevel);
        mapViewData.setViewType(MapViewType.AREA.getCode());

        Map<String, String> whereMap = this.assembleWhereCondition(adLevel, adCode, year);
        //获取统计列表
        List<StatisticVo> statisticList = null;
        String statisticIndexName="";
        String countTitleName="";

        switch (index) {
            case "1":
                //物种分布
                statisticIndexName = "speciesCountList";
                countTitleName = "物种数量(个)";
                statisticList = statisticMapper.statisticSpeciesTypeList(whereMap.get("startDate"), whereMap.get("endDate"), whereMap.get("provinceCode"), whereMap.get("cityCode"), whereMap.get("areaCode"), speciesId, whereMap.get("groupBySqlStr"),whereMap.get("regionJoinOnSqlStr"),whereMap.get("regionType"));
                break;
            case "2":
                //标识数量
                statisticIndexName = "speciesSignboardList";
                countTitleName = "标识数量(个)";
                statisticList = statisticMapper.statisticSpeciesSignboardList(whereMap.get("startDate"), whereMap.get("endDate"), whereMap.get("provinceCode"), whereMap.get("cityCode"), whereMap.get("areaCode"), speciesId, whereMap.get("groupBySqlStr"),whereMap.get("regionJoinOnSqlStr"),whereMap.get("regionType"));
                break;
            case "3":
                //标识总数
                statisticIndexName = "signboardList";
                countTitleName = "标识总数(个)";
                statisticList =statisticMapper.statisticSpeciesSignboardList(whereMap.get("startDate"), whereMap.get("endDate"), whereMap.get("provinceCode"), whereMap.get("cityCode"), whereMap.get("areaCode"), "", whereMap.get("groupBySqlStr"),whereMap.get("regionJoinOnSqlStr"),whereMap.get("regionType"));
                break;
            case "4":
                //新注册企业数量
                statisticIndexName = "registerNewCompList";
                countTitleName = "企业数量(个)";
                statisticList = statisticMapper.statisticRegisterCompList(whereMap.get("startDate"), whereMap.get("endDate"), whereMap.get("provinceCode"), whereMap.get("cityCode"), whereMap.get("areaCode"), whereMap.get("groupBySqlStr"),whereMap.get("regionJoinOnSqlStr"),whereMap.get("regionType"));
                break;
            case "5":
                //企业总数
                statisticIndexName = "compList";
                countTitleName = "企业总数(个)";
                statisticList = statisticMapper.statisticRegisterCompList("", whereMap.get("endDate"), whereMap.get("provinceCode"), whereMap.get("cityCode"), whereMap.get("areaCode"), whereMap.get("groupBySqlStr"),whereMap.get("regionJoinOnSqlStr"),whereMap.get("regionType"));
                break;
        }
        if (CollectionUtils.isEmpty(statisticList)) {
            return mapViewData;
        }

        //获取区域视图列表和数据列表的对象
        AssembleMapDataVo assembleMapDataVo = this.assembleAdAreaDataList(statisticList, adLevel);
        //区域视图列表
        List<AdAreaData> areaList = assembleMapDataVo.getAdAreaDataList();


        Map<String, List<AdAreaData>> map = new HashMap<>();
        map.put(statisticIndexName, areaList);
        //地图区域设置值
        mapViewData.setAdAreaDataList(map);

        //组装统计对象中的数据
        AdStatisticsData abStatisticsData = new AdStatisticsData();


        Map<String, String> dimensionList = Maps.newHashMap();
        dimensionList.put(statisticIndexName, countTitleName);

        Map<String, String> headerMap = Maps.newHashMap();
        headerMap.put("regionName", "行政区划");
        headerMap.put("count", countTitleName);

        Map<String, List<Map<String, Object>>> dataMap = Maps.newHashMap();
        List<Map<String, Object>> statisticDataList = assembleMapDataVo.getStatisticDataList();
        dataMap.put(statisticIndexName, statisticDataList);

        abStatisticsData.setDimensionList(dimensionList);
        abStatisticsData.setHeaderMap(headerMap);
        abStatisticsData.setDataMap(dataMap);

        mapViewData.setAdStatisticsData(abStatisticsData);
        return mapViewData;
    }
    /**
     * 组装统计查询条件
     *
     * @param adLevel 行政级别
     * @param adCode  区域编码（51224-45555-65457）
     * @param year    年度
     * @return map
     */
    private Map<String, String> assembleWhereCondition(String adLevel, String adCode, String year) {
        Map<String, String> map = new HashMap<>();
        map.put("startDate", year + "-01-01 00:00:00");
        map.put("endDate", year + "-12-31 23:59:59");
        //统计分组条件局部变量
        String groupBySqlStr;
        String regionType;
        String regionJoinOnSqlStr;

        if (MapAdLevel.AD_PROVINCE.getCode().equals(adLevel)) {
            groupBySqlStr = "a.COMP_PROVINCE,a.COMP_CITY";
            regionJoinOnSqlStr="s.region_code=a.COMP_CITY";
            regionType="2";
        } else if (MapAdLevel.AD_CITY.getCode().equals(adLevel)) {
            groupBySqlStr = "a.COMP_PROVINCE,a.COMP_CITY,a.COMP_DISTRICT";
            regionJoinOnSqlStr="s.region_code=a.COMP_DISTRICT";
            regionType="3";
        } else {
            groupBySqlStr = "a.COMP_PROVINCE";
            regionJoinOnSqlStr="s.region_code=a.COMP_PROVINCE";
            regionType="1";
        }
        map.put("groupBySqlStr", groupBySqlStr);
        map.put("regionType", regionType);
        map.put("regionJoinOnSqlStr", regionJoinOnSqlStr);

        //分布区域
        if (StringUtils.isNotBlank(adCode)) {
            String[] regionCodeArray = adCode.split("-");
            for (int i = 0; i < regionCodeArray.length; i++) {
                if (i == 0) {
                    map.put("provinceCode", regionCodeArray[i]);
                } else if (i == 1) {
                    map.put("cityCode", regionCodeArray[i]);
                } else {
                    map.put("areaCode", regionCodeArray[i]);
                }
            }
        }else{
            map.put("provinceCode","");
            map.put("cityCode", "");
            map.put("areaCode", "");
        }
        return map;
    }

    /**
     * 组装地图和列表数据的对象
     *
     * @param statisticList List<StatisticVo>统计数据
     * @return AssembleMapDataVo
     */
    private AssembleMapDataVo assembleAdAreaDataList(List<StatisticVo> statisticList, String adLevel) {
        AssembleMapDataVo assembleMapDataVo = new AssembleMapDataVo();
        //区域视图列表
        List<AdAreaData> areaList = new ArrayList<>();
        List<Map<String, Object>> dataList = new ArrayList<>();
        final int[] number = {1};
        statisticList.forEach(v -> {
            AdAreaData adAreaData = new AdAreaData();
            Map<String, Object> dataMap = Maps.newHashMap();

            String completeRegionCode=v.getParentIds()+"/"+v.getRegionCode();
            String regionCode=completeRegionCode.substring(completeRegionCode.indexOf("/",1)+1).replace("/","-");
            String completeRegionName=v.getParentNames()+"/"+v.getRegionName();
            String regionName = completeRegionName.substring(completeRegionName.indexOf("/",1)+1).replace("/","-");

            if (MapAdLevel.AD_PROVINCE.getCode().equals(adLevel)) {
                //市
                adAreaData.setAdRegionCode(regionCode);
                adAreaData.setAdRegionName(regionName);

            } else if (MapAdLevel.AD_CITY.getCode().equals(adLevel)) {
                //区
                adAreaData.setAdRegionCode(regionCode);
                adAreaData.setAdRegionName(regionName);
            } else {
                //省
                adAreaData.setAdRegionCode(v.getRegionCode());
                adAreaData.setAdRegionName(v.getRegionName());
            }
            adAreaData.setIndexValue(String.valueOf(v.getTotalNumber()));
            adAreaData.setIndexValueLevel(String.valueOf(number[0]));
            areaList.add(adAreaData);
            dataMap.put("regionName", regionName);
            dataMap.put("count", v.getTotalNumber());
            dataList.add(dataMap);
            number[0]++;
        });
        assembleMapDataVo.setAdAreaDataList(areaList);
        assembleMapDataVo.setStatisticDataList(dataList);
        return assembleMapDataVo;
    }
}
