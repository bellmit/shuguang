package com.sofn.agzirdd.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sofn.agzirdd.mapper.DistributionMapMapper;
import com.sofn.agzirdd.model.DistributionMap;
import com.sofn.agzirdd.service.DistributionMapService;
import com.sofn.agzirdd.sysapi.SysFileApi;
import com.sofn.agzirdd.sysapi.bean.SysFileManageVo;
import com.sofn.agzirdd.util.DateUtil;
import com.sofn.agzirdd.vo.DistributionMapVo;
import com.sofn.common.map.AdPointData;
import com.sofn.common.map.AdStatisticsData;
import com.sofn.common.map.MapConditions;
import com.sofn.common.map.MapViewData;
import com.sofn.common.model.Result;
import com.sofn.common.utils.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DistributionMapServiceImpl extends ServiceImpl<DistributionMapMapper, DistributionMap> implements DistributionMapService {
    public static final String AD_COUNTY = "ad_county";
    public static final String AD_PROVINCE = "ad_province";
    public static final String AD_CITY = "ad_city";

    @Autowired
    private DistributionMapMapper distributionMapMapper;
    /**
     * 文件图片api
     */
    @Autowired
    private SysFileApi sysFileApi;

    @Override
    public List<DistributionMapVo> selectByOneYearDuring() {
        List<DistributionMap> distributionMapList = distributionMapMapper.selectDuringOneYear();
        List<DistributionMapVo> voList = new ArrayList<>(distributionMapList.size());
        //将数据根据物种归类
        Map<String, List<DistributionMap>> map = distributionMapList.stream().collect(Collectors.groupingBy(
                DistributionMap::getSpeciesId));
        //归类后挑出需要组合的数据
        Map<String,String> valMap = new HashMap<>();
        Set valSet = new HashSet<>();    //记录分布点区划id，以便去重
        for(Map.Entry<String, List<DistributionMap>> entry : map.entrySet()){
            StringBuffer areaCombin = new StringBuffer();
            String mapKey = entry.getKey();
            List<DistributionMap> mapValue = entry.getValue();
            for(DistributionMap distributionMap:mapValue){
                //四个直辖市需要特别处理
                if("110000".equals(distributionMap.getProvinceId())||
                     "120000".equals(distributionMap.getProvinceId())||
                      "310000".equals(distributionMap.getProvinceId())||
                        "500000".equals(distributionMap.getProvinceId())){
                    if(valSet.contains(distributionMap.getCityId()))
                        continue;
                    areaCombin.append(distributionMap.getProvinceName().substring(0,2)).append(",");
                    valSet.add(distributionMap.getCityId());
                }else {
                    if(valSet.contains(distributionMap.getCityId()))
                        continue;
                    areaCombin.append(distributionMap.getCityName().substring(0,
                            distributionMap.getCityName().indexOf("市")==-1?2:distributionMap.getCityName().indexOf("市"))).append(",");
                    valSet.add(distributionMap.getCityId());
                }
                valMap.put(mapKey,areaCombin.substring(0,areaCombin.lastIndexOf(",")));
            }
        }

        //对数据组装处理返回给前端
        for (DistributionMap dibMap : distributionMapList){
            DistributionMapVo vo = new DistributionMapVo();
            BeanUtils.copyProperties(dibMap,vo);
            //将组合好的地区名重新赋值
            vo.setCombinName(valMap.get(dibMap.getSpeciesId()));
            voList.add(vo);
        }

        return voList;
    }

    @Override
    public MapViewData getMapViewData(String index, String adLevel, String adCode, Map<String, String> conditions) {
        //index没用到，前端可传可不传
        return getMapData(adLevel, adCode, conditions);
    }

    @Override
    public boolean removeBySpecInveId(String id) {
        this.baseMapper.removeBySpecInveId(id);
        return true;
    }

    @Override
    public List<MapConditions> getMapConditions(String index) {
        return null;
    }

    // 指标数据查询结果                      当前行政级别        区划编码                      查询条件
    private MapViewData getMapData(String adLevel,  String adCode, Map<String, String> conditions){
        MapViewData outputData = new MapViewData();
        outputData.setAdAreaDataList(new HashMap<>());
        outputData.setAdLevel(adLevel);
        outputData.setViewType("point");
        AdStatisticsData adStatisticsData = new AdStatisticsData();
        //表头
        Map<String, String> headMap = new HashMap<>();
        headMap.put("num", "序号");
        headMap.put("area", "行政区划");
        headMap.put("dimension", "数值");
        adStatisticsData.setHeaderMap(headMap);
        //维度
        Map<String, String> dimensionMap = new HashMap<>();
        adStatisticsData.setDimensionList(dimensionMap);
        //统计数据
        Map<String, List<Map<String, Object>>> dataMap = new HashMap<>();
        //描点信息
        List<AdPointData> adPointDataList = new ArrayList<>();
        //存放统计数据
        List<DistributionMap> mapLists = new ArrayList<>();

        if(AD_PROVINCE.equals(adLevel)){
            conditions.put("provinceId", adCode);
        }else if(AD_CITY.equals(adLevel)){
            conditions.put("cityId", adCode);
        }

        Date firstDay = DateUtil.getYearFirstDay(Calendar.getInstance().get(Calendar.YEAR));
        Date lastDay = DateUtil.getYearLastDay(Calendar.getInstance().get(Calendar.YEAR));

        //获取当前年的物种分布信息
        conditions.put("beginDate", DateUtils.format(firstDay));
        conditions.put("endDate", DateUtils.format(lastDay));
        Map<String, Object> conditionMap = new HashMap<>(conditions);
        //获取物种分布信息
        List<DistributionMap> distributionMaps = distributionMapMapper.selectByConditions(conditionMap);

        /**
        *@Author Chlf
        *@Description
        * 地图当前所在级别为国家级则将数据根据省份分类
        * 地图当前所在级别为省级则将数据根据市分类
        * 地图当前所在级别为市级则将数据根据区县分类
        **/
        Map<String, List<DistributionMap>> distributionMap =
                AD_COUNTY.equals(adLevel)?
                distributionMaps.stream().collect(Collectors.groupingBy(
                DistributionMap::getProvinceId)):
                        AD_PROVINCE.equals(adLevel)?
                                distributionMaps.stream().collect(Collectors.groupingBy(
                                        DistributionMap::getCityId)):
                                distributionMaps.stream().collect(Collectors.groupingBy(
                                        DistributionMap::getCountyId));
        //根据区划ID（key）循环distributionMap,得到其中的list，然后再处理其中的list
        for(Map.Entry<String, List<DistributionMap>> entry : distributionMap.entrySet()) {
            //描点信息装配
            AdPointData adPointData = new AdPointData();
            adPointData.setChartType("columnar");             //柱状图
            adPointData.setIndexValueLevel("0");
            adPointData.setIndexLabelList(new HashMap<>());
            //归类后挑出需要组合的数据
            Map<String,Object> indexMap = new HashMap<>();
            //String mapKey = entry.getKey();
            List<DistributionMap> mapValue = entry.getValue();
            List<DistributionMapVo> mapVoList = new ArrayList<>(mapValue.size());
            mapVoList = copyValToVo(mapValue,mapVoList);
            indexMap.put("index", mapVoList);               //重新复制，方便数据处理
            adPointData.setIndexInfo(indexMap);

            for(DistributionMap dismap : mapValue){
                adPointData.setIndexValue(
                        AD_COUNTY.equals(adLevel)?
                        dismap.getProvinceName()+":"+mapValue.size()+"个":
                           AD_PROVINCE.equals(adLevel)?
                           dismap.getCityName()+":"+mapValue.size()+"个":
                           dismap.getCountyName()+":"+mapValue.size()+"个");
                adPointData.setLatitude(dismap.getLatitude());
                adPointData.setLongitude(dismap.getLongitude());
                break;
            }
            //描点信息
            adPointDataList.add(adPointData);
        }
        //统计数据
        mapLists.add(new DistributionMap());

        Date yearFirstDay = DateUtil.getYearFirstDay(Calendar.getInstance().get(Calendar.YEAR)-1);
        Date yearLastDay = DateUtil.getYearLastDay(Calendar.getInstance().get(Calendar.YEAR)-1);

        //获取上一年的物种分布信息
        conditions.put("beginDate", DateUtils.format(yearFirstDay));
        conditions.put("endDate", DateUtils.format(yearLastDay));
        Map<String, Object> conditionMap2 = new HashMap<>(conditions);
        List<DistributionMap> distributionMapsLastYear = distributionMapMapper.selectByConditions(conditionMap2);
        //维度数据列表
        List<Map<String, Object>> dimeObjList = new ArrayList<>();
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("num", 1);
        objectMap.put("year", String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));
        objectMap.put("dimension", distributionMaps);            //当前年度的数据

        Map<String, Object> objectMap2 = new HashMap<>();
        objectMap2.put("num", 2);
        objectMap2.put("year", String.valueOf(Calendar.getInstance().get(Calendar.YEAR)-1));
        objectMap2.put("dimension", distributionMapsLastYear);   //上一年度的数据

        dimeObjList.add(objectMap);
        dimeObjList.add(objectMap2);

        String disString = "distribution";
        Map<String, List<Map<String, Object>>> dataMap1 = new HashMap<>();
        dataMap1.put(disString, dimeObjList);

        dataMap.putAll(dataMap1);
        adStatisticsData.setDataMap(dataMap);
        outputData.setAdStatisticsData(adStatisticsData);

        Map<String, List<AdPointData>> adPointMap = new HashMap<>();
        adPointMap.put(disString, adPointDataList);

        outputData.setAdPointDataList(adPointMap);
        return outputData;
    }

    //将id转为URL并赋值给新的list
    private List<DistributionMapVo>  copyValToVo(List<DistributionMap> sourceList,List<DistributionMapVo> targerList){
        if(null==sourceList||sourceList.isEmpty()) return new ArrayList<>();
        if(targerList==null){
            targerList = new ArrayList<>(sourceList.size());
        }

        //将imgId转imgUrl
        for(DistributionMap distributionMap : sourceList){
            DistributionMapVo distributionMapVo = new DistributionMapVo();
            BeanUtils.copyProperties(distributionMap, distributionMapVo);
            //判断是否存在新发物种图片
            if (StringUtils.isNotBlank(distributionMap.getSpeciesImg())) {
                //获取新数据图片String
                String picImg = distributionMap.getSpeciesImg();
                //用于存放图片url地址
                //获取图片id所对应的url
                StringBuilder picStr = getImgUrl(picImg);
                distributionMapVo.setSpeciesImgUrl(picStr.toString());
            }
            targerList.add(distributionMapVo);
        }

        return targerList;
    }

    /**
     * 获取图片id所对应的url
     * @param picImg picImg
     * @return StringBuilder
     */
    @NotNull
    private StringBuilder getImgUrl(String picImg) {
        //用于存放图片url地址
        StringBuilder picStr = new StringBuilder("");
        //获取图片信息
        Result<List<SysFileManageVo>> listResult = sysFileApi.batchGetFileInfo(picImg);
        //对获取后的图片信息进行遍历
        listResult.getData().forEach(
                baseDate -> {
                    //放入拼接后的url地址
                    picStr.append(baseDate.getFilePath()).append(",");
                }
        );
        return picStr;
    }
}
