package com.sofn.ducss.service.impl;

import com.sofn.common.map.AdPointData;
import com.sofn.common.map.AdStatisticsData;
import com.sofn.common.map.MapConditions;
import com.sofn.common.map.MapViewData;
import com.sofn.common.model.Result;
import com.sofn.common.utils.IdUtil;
import com.sofn.common.utils.PageUtils;
import com.sofn.ducss.enums.Constants;
import com.sofn.ducss.enums.IndexAndDimensionEnum;
import com.sofn.ducss.mapper.StrawUtilizeSumTotalMapper;
import com.sofn.ducss.model.StrawUtilizeSumTotal;
import com.sofn.ducss.service.GraphicAnalysisService;
import com.sofn.ducss.sysapi.SysApi;
import com.sofn.ducss.sysapi.bean.SysRegionTreeVo;
import com.sofn.ducss.vo.StrawUtilizeSumResVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName GraphicAnalysisImpl
 * @Description 图形分析
 * @Author Chlf
 * @Date 2020/7/28 15:15
 * Version1.0
 **/
@Service
@Slf4j
public class GraphicAnalysisImpl implements GraphicAnalysisService {
    public static final String AD_COUNTY = "ad_county";
    public static final String AD_PROVINCE = "ad_province";
    public static final String AD_CITY = "ad_city";

    @Autowired
    private StrawUtilizeSumTotalMapper strawUtilizeSumTotalMapper;
    @Autowired
    private SysApi sysApi;

    @Override
    public MapViewData getMapViewData(String index, String adLevel, String adCode, Map<String, String> conditions) {
        //根据传入条件分别查询对应数据返回
        String status = ""+ Constants.ExamineState.PASSED;       //状态，只查已通过数据
        conditions.put("status",status);

        MapViewData mapViewData = new MapViewData();
        if(index.equals(IndexAndDimensionEnum.Index.OUTPUT.toString())){//若是产生量(OUTPUT)指标
            mapViewData = getOutputData(adLevel, adCode, conditions);
        }else if(index.equals(IndexAndDimensionEnum.Index.UTILIZATION.toString())){//利用量(UTILIZATION)
            mapViewData = getUtilizationData(adLevel, adCode, conditions);
        }else if(index.equals(IndexAndDimensionEnum.Index.DIRECTRETURN.toString())){//直接还田量(DIRECTRETURN)
            mapViewData = getDirectreturnData(adLevel, adCode, conditions);
        }else if(index.equals(IndexAndDimensionEnum.Index.CALLIN.toString())){//市场主体调入量(CALLIN)
            mapViewData = getCallinData(adLevel, adCode, conditions);
        }else if(index.equals(IndexAndDimensionEnum.Index.OEE.toString())){//综合利用率(OEE)
            mapViewData = getOeeData(adLevel, adCode, conditions);
        }else if(index.equals(IndexAndDimensionEnum.Index.COMPREHENSIVEUTILIZATION.toString())){//综合利用量(COMPREHENSIVEUTILIZATION)
            mapViewData = getZhlylData(adLevel, adCode, conditions);
        }else if(index.equals(IndexAndDimensionEnum.Index.CUCI.toString())){//综合利用能力指数(CUCI)
            mapViewData = getCuciData(adLevel, adCode, conditions);
        }else if(index.equals(IndexAndDimensionEnum.Index.IUCI.toString())){//产业化利用能力指数(IUCI)
            mapViewData = getIuciData(adLevel, adCode, conditions);
        }else if(index.equals(IndexAndDimensionEnum.Index.FIVEMATERIALS.toString())){//五料化利用量(FIVEMATERIALS)指标
            mapViewData = getFivematerialsData(adLevel, adCode, conditions);
        }

        return mapViewData;
    }

    //产生量(OUTPUT)指标数据查询结果             当前行政级别        区划编码                      查询条件
    private MapViewData getOutputData(String adLevel,  String adCode, Map<String, String> conditions){
        MapViewData outputData = new MapViewData();
        outputData.setAdAreaDataList(new HashMap<>());
        outputData.setAdLevel(adLevel);    //当前地图级别，如全国、省级等
        outputData.setViewType("point");   //描点
        AdStatisticsData adStatisticsData = new AdStatisticsData();
        //表头
        Map<String, String> headMap = new HashMap<>();
        headMap.put("num", "序号");
        headMap.put("area", "行政区划");
        headMap.put("dimension", IndexAndDimensionEnum.Dimension.LSCL.getName());
        adStatisticsData.setHeaderMap(headMap);
        //维度
        Map<String, String> dimensionMap = new HashMap<>();
        for (IndexAndDimensionEnum.Dimension dimensionEnum : IndexAndDimensionEnum.Dimension.values()) {
            dimensionMap.put(dimensionEnum.toString(), dimensionEnum.getName());
        }
        adStatisticsData.setDimensionList(dimensionMap);

        //统计数据
        Map<String, List<Map<String, Object>>> dataMap = new HashMap<>();
        //描点信息
        List<AdPointData> adPointDataList = new ArrayList<>();   //粮食产量
        List<AdPointData> adPointDataList2 = new ArrayList<>();  //产生量
        List<AdPointData> adPointDataList3 = new ArrayList<>();  //可收集量
        List<AdPointData> adPointDataList4 = new ArrayList<>();  //调出量
        //存放统计数据
        List<StrawUtilizeSumResVo> sumTotals = new ArrayList<>();

        /**
        *@Description //查询条件重新组合
        **/
        String parentId = "100000";//国家级别，查询各个省的数据统计
        if(!AD_COUNTY.equals(adLevel)){
            conditions.put("areaId", adCode);
            parentId = adCode;
        }

        //获取下一级列表
        Result<PageUtils<SysRegionTreeVo>> result = sysApi.getSysRegionFormByParentId(null,parentId,null,null,null,null);
        List<SysRegionTreeVo> voList = result.getData().getList();
        StringBuffer areaIds = new StringBuffer();
        for(SysRegionTreeVo treeVo : voList){//将符合条件的区划编码用逗号重新组合成字符串
            areaIds.append(treeVo.getRegionCode()).append(",");
        }
        //若获取不到区划信息则系统已经报错
        if(areaIds.length()==0){
            return outputData;
        }

        String areaIdStr = areaIds.substring(0,areaIds.length()-1);
        List<StrawUtilizeSumTotal> sumTotalList = strawUtilizeSumTotalMapper.selectProduceUtilizeByAreaIds(IdUtil.getIdsByStr(areaIdStr),conditions.get("year"),conditions.get("status"));
//        // 获取粮食产量,此时的为某个地区的十四种作物的列表，并非粮食产量总和
//        List<StrawUtilizeSumResVo> voSum = strawUtilizeSumMapper.selectStrawUtilizeExamineSum(areaIdStr,conditions.get("year"),conditions.get("status"));

        Map<String, Object> sumTotalMap = new HashMap<>(sumTotalList.size());
        for(StrawUtilizeSumTotal sumTotal : sumTotalList){
            sumTotalMap.put(sumTotal.getAreaId(),sumTotal);
        }

        for(SysRegionTreeVo vo:voList){
            StrawUtilizeSumResVo sumTotal = new StrawUtilizeSumResVo();
            //描点信息装配
            AdPointData adPointData = new AdPointData();   //粮食产量
            AdPointData adPointData2 = new AdPointData();  //产生量
            AdPointData adPointData3 = new AdPointData();  //可收集量
            AdPointData adPointData4 = new AdPointData();  //调出量

            sumTotal.setAreaName(vo.getRegionName());      //区划名称
            sumTotal.setAreaId(vo.getRegionCode());        //区划编码
            //描点中的区划信息
            Map<String, Object> areaMap = new HashMap<>();
            areaMap.put("name",vo.getRegionName());
            adPointData.setIndexInfo(areaMap);
            adPointData.setIndexLabelList(new HashMap<>());
            adPointData.setLatitude(vo.getLatitude());
            adPointData.setLongitude(vo.getLongitude());
            adPointData.setIndexValueLevel("1");
            adPointData.setChartType("columnar");   //柱状图
            adPointData.setIndexValue("0");         //赋默认值
            adPointData.setAdRegionCode(vo.getRegionCode());
            adPointData.setAdRegionName(vo.getRegionName());
            BeanUtils.copyProperties(adPointData,adPointData2);
            BeanUtils.copyProperties(adPointData,adPointData3);
            BeanUtils.copyProperties(adPointData,adPointData4);

            if(sumTotalMap.containsKey(vo.getRegionCode())){//此时查询中有这个地区的数据
                StrawUtilizeSumTotal st = (StrawUtilizeSumTotal)sumTotalMap.get(vo.getRegionCode());
                BeanUtils.copyProperties(st, sumTotal);
                adPointData.setIndexValue(st.getTheoryResource().compareTo(new BigDecimal(1000))==-1?
                        st.getTheoryResource().setScale(0, RoundingMode.HALF_UP)+"吨":
                        st.getTheoryResource().divide(new BigDecimal(10000.00)).setScale(2, RoundingMode.HALF_UP)+"万吨");
                adPointData2.setIndexValue(st.getYieldAllNum().compareTo(new BigDecimal(1000))==-1?
                        st.getYieldAllNum().setScale(0, RoundingMode.HALF_UP)+"吨":
                        st.getYieldAllNum().divide(new BigDecimal(10000.00)).setScale(2, RoundingMode.HALF_UP)+"万吨");
                adPointData3.setIndexValue(st.getCollectResource().compareTo(new BigDecimal(1000))==-1?
                        st.getCollectResource().setScale(0, RoundingMode.HALF_UP)+"吨":
                        st.getCollectResource().divide(new BigDecimal(10000.00)).setScale(2, RoundingMode.HALF_UP)+"万吨");
                adPointData4.setIndexValue(st.getExportYieldTotal().compareTo(new BigDecimal(1000))==-1?
                        st.getExportYieldTotal().setScale(0, RoundingMode.HALF_UP)+"吨":
                        st.getExportYieldTotal().divide(new BigDecimal(10000.00)).setScale(2, RoundingMode.HALF_UP)+"万吨");
            }

            //统计数据
            sumTotals.add(sumTotal);
            //描点信息
            adPointDataList.add(adPointData);
            adPointDataList2.add(adPointData2);
            adPointDataList3.add(adPointData3);
            adPointDataList4.add(adPointData4);
        }

        //维度数据列表
        List<Map<String, Object>> dimeObjList = new ArrayList<>();
        for (int i = 0; i < sumTotals.size(); i++) {
            Map<String, Object> objectMap = new HashMap<>();
            objectMap.put("num", i + 1);
            objectMap.put("area", sumTotals.get(i).getAreaName());
            objectMap.put("dimension", sumTotals.get(i).getTheoryResource().compareTo(new BigDecimal(1000))==-1?
                    sumTotals.get(i).getTheoryResource().divide(new BigDecimal(10000.00)).setScale(4, RoundingMode.HALF_UP):
                    sumTotals.get(i).getTheoryResource().divide(new BigDecimal(10000.00)).setScale(2, RoundingMode.HALF_UP));

            dimeObjList.add(objectMap);
        }
        List<Map<String, Object>> dimeObjList2 = new ArrayList<>();
        for (int i = 0; i < sumTotals.size(); i++) {
            Map<String, Object> objectMap = new HashMap<>();
            objectMap.put("num", i + 1);
            objectMap.put("area", sumTotals.get(i).getAreaName());
            objectMap.put("dimension", sumTotals.get(i).getYieldAllNum().compareTo(new BigDecimal(1000))==-1?
                    sumTotals.get(i).getYieldAllNum().divide(new BigDecimal(10000.00)).setScale(4, RoundingMode.HALF_UP):
                    sumTotals.get(i).getYieldAllNum().divide(new BigDecimal(10000.00)).setScale(2, RoundingMode.HALF_UP));

            dimeObjList2.add(objectMap);
        }
        List<Map<String, Object>> dimeObjList3 = new ArrayList<>();
        for (int i = 0; i < sumTotals.size(); i++) {
            Map<String, Object> objectMap = new HashMap<>();
            objectMap.put("num", i + 1);
            objectMap.put("area", sumTotals.get(i).getAreaName());
            objectMap.put("dimension",sumTotals.get(i).getCollectResource().compareTo(new BigDecimal(1000))==-1?
                    sumTotals.get(i).getCollectResource().divide(new BigDecimal(10000.00)).setScale(4, RoundingMode.HALF_UP):
                    sumTotals.get(i).getCollectResource().divide(new BigDecimal(10000.00)).setScale(2, RoundingMode.HALF_UP));

            dimeObjList3.add(objectMap);
        }
        List<Map<String, Object>> dimeObjList4 = new ArrayList<>();
        for (int i = 0; i < sumTotals.size(); i++) {
            Map<String, Object> objectMap = new HashMap<>();
            objectMap.put("num", i + 1);
            objectMap.put("area", sumTotals.get(i).getAreaName());
            objectMap.put("dimension", sumTotals.get(i).getExportYieldTotal().compareTo(new BigDecimal(1000))==-1?
                    sumTotals.get(i).getExportYieldTotal().divide(new BigDecimal(10000.00)).setScale(4, RoundingMode.HALF_UP):
                    sumTotals.get(i).getExportYieldTotal().divide(new BigDecimal(10000.00)).setScale(2, RoundingMode.HALF_UP));

            dimeObjList4.add(objectMap);
        }
        //按不同维度重新包装成map数据
        String lsclString = IndexAndDimensionEnum.Dimension.LSCL.toString();
        String cslString = IndexAndDimensionEnum.Dimension.CSL.toString();
        String ksjlString = IndexAndDimensionEnum.Dimension.KSJL.toString();
        String dclString = IndexAndDimensionEnum.Dimension.DCL.toString();
        Map<String, List<Map<String, Object>>> dataMap1 = new HashMap<>();
        dataMap1.put(lsclString, dimeObjList);
        Map<String, List<Map<String, Object>>> dataMap2 = new HashMap<>();
        dataMap2.put(cslString, dimeObjList2);
        Map<String, List<Map<String, Object>>> dataMap3 = new HashMap<>();
        dataMap3.put(ksjlString, dimeObjList3);
        Map<String, List<Map<String, Object>>> dataMap4 = new HashMap<>();
        dataMap4.put(dclString, dimeObjList4);
        dataMap.putAll(dataMap1);
        dataMap.putAll(dataMap2);
        dataMap.putAll(dataMap3);
        dataMap.putAll(dataMap4);
        adStatisticsData.setDataMap(dataMap);
        outputData.setAdStatisticsData(adStatisticsData);

        Map<String, List<AdPointData>> adPointMap = new HashMap<>();
        adPointMap.put(lsclString, adPointDataList);
        adPointMap.put(cslString, adPointDataList2);
        adPointMap.put(ksjlString, adPointDataList3);
        adPointMap.put(dclString, adPointDataList4);

        outputData.setAdPointDataList(adPointMap);
        return outputData;

    }

    //利用量(UTILIZATION)指标数据查询结果            当前行政级别        区划编码                      查询条件
    private MapViewData getUtilizationData(String adLevel,  String adCode, Map<String, String> conditions){
        MapViewData outputData = new MapViewData();
        outputData.setAdAreaDataList(new HashMap<>());
        outputData.setAdLevel(adLevel);
        outputData.setViewType("point");   //描点
        AdStatisticsData adStatisticsData = new AdStatisticsData();
        //表头
        Map<String, String> headMap = new HashMap<>();
        headMap.put("num", "序号");
        headMap.put("area", "行政区划");
        headMap.put("dimension", IndexAndDimensionEnum.Dimension.SCZTLYL.getName());
        adStatisticsData.setHeaderMap(headMap);
        //维度
        Map<String, String> dimensionMap = new HashMap<>();
        for (IndexAndDimensionEnum.Dimension dimensionEnum : IndexAndDimensionEnum.Dimension.values()) {
            dimensionMap.put(dimensionEnum.toString(), dimensionEnum.getName());
        }
        adStatisticsData.setDimensionList(dimensionMap);

        //统计数据
        Map<String, List<Map<String, Object>>> dataMap = new HashMap<>();
        //描点信息
        List<AdPointData> adPointDataList = new ArrayList<>();
        List<AdPointData> adPointDataList2 = new ArrayList<>();

        //存放统计数据
        //粮食产量(理论资源量/草谷比)
        List<StrawUtilizeSumResVo> sumTotals = new ArrayList<>();

        /**
         *@Description //查询条件重新组合
         **/
        String parentId = "100000";//国家级别，查询各个省的数据统计
        if(!AD_COUNTY.equals(adLevel)){
            conditions.put("areaId", adCode);
            parentId = adCode;
        }

        //获取下一级列表
        Result<PageUtils<SysRegionTreeVo>> result = sysApi.getSysRegionFormByParentId(null,parentId,null,null,null,null);
        List<SysRegionTreeVo> voList = result.getData().getList();
        StringBuffer areaIds = new StringBuffer();
        for(SysRegionTreeVo treeVo : voList){
            areaIds.append(treeVo.getRegionCode()).append(",");
        }
        //若获取不到区划信息则系统已经报错
        String areaIdStr = areaIds.substring(0,areaIds.length()-1);
        List<StrawUtilizeSumTotal> sumTotalList = strawUtilizeSumTotalMapper.selectProduceUtilizeByAreaIds(IdUtil.getIdsByStr(areaIdStr),conditions.get("year"),conditions.get("status"));
        Map<String, Object> sumTotalMap = new HashMap<>(sumTotalList.size());
        for(StrawUtilizeSumTotal sumTotal : sumTotalList){
            sumTotalMap.put(sumTotal.getAreaId(),sumTotal);
        }

        for(SysRegionTreeVo vo:voList){
            StrawUtilizeSumResVo sumTotal = new StrawUtilizeSumResVo();
            //描点信息装配
            AdPointData adPointData = new AdPointData();   //市场主体利用量
            AdPointData adPointData2 = new AdPointData();  //分散利用量

            sumTotal.setAreaName(vo.getRegionName());
            sumTotal.setAreaId(vo.getRegionCode());
            //描点中的区划信息
            Map<String, Object> areaMap = new HashMap<>();
            areaMap.put("name",vo.getRegionName());
            adPointData.setIndexInfo(areaMap);
            adPointData.setIndexLabelList(new HashMap<>());
            adPointData.setLatitude(vo.getLatitude());
            adPointData.setLongitude(vo.getLongitude());
            adPointData.setIndexValueLevel("1");
            adPointData.setChartType("columnar");   //柱状图
            adPointData.setIndexValue("0");
            adPointData.setAdRegionCode(vo.getRegionCode());
            adPointData.setAdRegionName(vo.getRegionName());
            BeanUtils.copyProperties(adPointData,adPointData2);

            if(sumTotalMap.containsKey(vo.getRegionCode())){//此时查询中有这个地区的数据
                StrawUtilizeSumTotal st = (StrawUtilizeSumTotal)sumTotalMap.get(vo.getRegionCode());
                BeanUtils.copyProperties(st, sumTotal);
                adPointData.setIndexValue(st.getMainTotal().compareTo(new BigDecimal(1000))==-1?
                        st.getMainTotal().setScale(0, RoundingMode.HALF_UP)+"吨":
                        st.getMainTotal().divide(new BigDecimal(10000.00)).setScale(2, RoundingMode.HALF_UP)+"万吨");
                adPointData2.setIndexValue(st.getDisperseTotal().compareTo(new BigDecimal(1000))==-1?
                        st.getDisperseTotal().setScale(0, RoundingMode.HALF_UP)+"吨":
                        st.getDisperseTotal().divide(new BigDecimal(10000.00)).setScale(2, RoundingMode.HALF_UP)+"万吨");
           }
            adPointData.setIndexInfo(areaMap);
            adPointData2.setIndexInfo(areaMap);

            //统计数据
            sumTotals.add(sumTotal);
            //描点信息
            adPointDataList.add(adPointData);
            adPointDataList2.add(adPointData2);

        }

        //维度数据列表
        List<Map<String, Object>> dimeObjList = new ArrayList<>();
        for (int i = 0; i < sumTotals.size(); i++) {
            Map<String, Object> objectMap = new HashMap<>();
            objectMap.put("num", i + 1);
            objectMap.put("area", sumTotals.get(i).getAreaName());
            objectMap.put("dimension", sumTotals.get(i).getMainTotal().compareTo(new BigDecimal(1000))==-1?
                    sumTotals.get(i).getMainTotal().divide(new BigDecimal(10000.00)).setScale(4, RoundingMode.HALF_UP):
                    sumTotals.get(i).getMainTotal().divide(new BigDecimal(10000.00)).setScale(2, RoundingMode.HALF_UP)); //市场主体利用量

            dimeObjList.add(objectMap);
        }
        List<Map<String, Object>> dimeObjList2 = new ArrayList<>();
        for (int i = 0; i < sumTotals.size(); i++) {
            Map<String, Object> objectMap = new HashMap<>();
            objectMap.put("num", i + 1);
            objectMap.put("area", sumTotals.get(i).getAreaName());
            objectMap.put("dimension", sumTotals.get(i).getDisperseTotal().compareTo(new BigDecimal(1000))==-1?
                    sumTotals.get(i).getDisperseTotal().divide(new BigDecimal(10000.00)).setScale(4, RoundingMode.HALF_UP):
                    sumTotals.get(i).getDisperseTotal().divide(new BigDecimal(10000.00)).setScale(2, RoundingMode.HALF_UP)); //分散利用量

            dimeObjList2.add(objectMap);
        }

        String scztlylString = IndexAndDimensionEnum.Dimension.SCZTLYL.toString();
        String fslylString = IndexAndDimensionEnum.Dimension.FSLYL.toString();
        Map<String, List<Map<String, Object>>> dataMap1 = new HashMap<>();
        dataMap1.put(scztlylString, dimeObjList);
        Map<String, List<Map<String, Object>>> dataMap2 = new HashMap<>();
        dataMap2.put(fslylString, dimeObjList2);

        dataMap.putAll(dataMap1);
        dataMap.putAll(dataMap2);
        adStatisticsData.setDataMap(dataMap);
        outputData.setAdStatisticsData(adStatisticsData);

        Map<String, List<AdPointData>> adPointMap = new HashMap<>();
        adPointMap.put(scztlylString, adPointDataList);
        adPointMap.put(fslylString, adPointDataList2);


        outputData.setAdPointDataList(adPointMap);
        return outputData;

    }

    //直接还田量(DIRECTRETURN)指标数据查询结果          当前行政级别        区划编码                      查询条件
    private MapViewData getDirectreturnData(String adLevel,  String adCode, Map<String, String> conditions){
        return getDataByDimension(adLevel, adCode, conditions, IndexAndDimensionEnum.Dimension.DIRECTRETURN);
    }

    //市场主体调入量(CALLIN)指标数据查询结果       当前行政级别        区划编码                      查询条件
    private MapViewData getCallinData(String adLevel,  String adCode, Map<String, String> conditions){
       return getDataByDimension(adLevel, adCode, conditions, IndexAndDimensionEnum.Dimension.CALLIN);
    }

    //综合利用率(OEE)指标数据查询结果        当前行政级别        区划编码                      查询条件
    private MapViewData getOeeData(String adLevel,  String adCode, Map<String, String> conditions){
        return getDataByDimension(adLevel, adCode, conditions, IndexAndDimensionEnum.Dimension.OEE);
    }

    //综合利用量(COMPREHENSIVEUTILIZATION)指标数据查询结果
    //                                     当前行政级别        区划编码                      查询条件
    private MapViewData getZhlylData(String adLevel,  String adCode, Map<String, String> conditions){
        return getDataByDimension(adLevel, adCode, conditions, IndexAndDimensionEnum.Dimension.COMPREHENSIVEUTILIZATION);
    }

    //综合利用能力指数(CUCI)指标数据查询结果     当前行政级别        区划编码                      查询条件
    private MapViewData getCuciData(String adLevel,  String adCode, Map<String, String> conditions){
        return getDataByDimension(adLevel,  adCode, conditions,IndexAndDimensionEnum.Dimension.CUCI);
    }

    //产业化利用能力指数(IUCI)指标数据查询结果    当前行政级别        区划编码                      查询条件
    private MapViewData getIuciData(String adLevel,  String adCode, Map<String, String> conditions){
        return getDataByDimension(adLevel,  adCode, conditions,IndexAndDimensionEnum.Dimension.IUCI);
    }

    //五料化利用量(FIVEMATERIALS)指标             当前行政级别        区划编码                      查询条件
    private MapViewData getFivematerialsData(String adLevel,  String adCode, Map<String, String> conditions){
        MapViewData outputData = new MapViewData();
        outputData.setAdAreaDataList(new HashMap<>());
        outputData.setAdLevel(adLevel);
        outputData.setViewType("point");   //描点
        AdStatisticsData adStatisticsData = new AdStatisticsData();
        //表头
        Map<String, String> headMap = new HashMap<>();
        headMap.put("num", "序号");
        headMap.put("area", "行政区划");
        headMap.put("dimension", IndexAndDimensionEnum.Dimension.FLHLYL.getName());
        adStatisticsData.setHeaderMap(headMap);

        //维度
        Map<String, String> dimensionMap = new HashMap<>();
        for (IndexAndDimensionEnum.Dimension dimensionEnum : IndexAndDimensionEnum.Dimension.values()) {
            dimensionMap.put(dimensionEnum.toString(), dimensionEnum.getName());
        }
        adStatisticsData.setDimensionList(dimensionMap);

        //统计数据
        Map<String, List<Map<String, Object>>> dataMap = new HashMap<>();
        //描点信息
        List<AdPointData> adPointDataList = new ArrayList<>();
        List<AdPointData> adPointDataList2 = new ArrayList<>();
        List<AdPointData> adPointDataList3 = new ArrayList<>();
        List<AdPointData> adPointDataList4 = new ArrayList<>();
        List<AdPointData> adPointDataList5 = new ArrayList<>();
        //存放统计数据
        List<StrawUtilizeSumResVo> sumTotals = new ArrayList<>();

        /**
         *@Description //查询条件重新组合
         **/
        String parentId = "100000";//国家级别，查询各个省的数据统计
        if(!AD_COUNTY.equals(adLevel)){
            conditions.put("areaId", adCode);
            parentId = adCode;
        }

        //获取下一级列表
        Result<PageUtils<SysRegionTreeVo>> result = sysApi.getSysRegionFormByParentId(null,parentId,null,null,null,null);
        List<SysRegionTreeVo> voList = result.getData().getList();
        StringBuffer areaIds = new StringBuffer();
        for(SysRegionTreeVo treeVo : voList){
            areaIds.append(treeVo.getRegionCode()).append(",");
        }
        //若获取不到区划信息则系统已经报错
        if(areaIds.length()==0){
            return outputData;
        }
        String areaIdStr = areaIds.substring(0,areaIds.length()-1);
        List<StrawUtilizeSumTotal> sumTotalList = strawUtilizeSumTotalMapper.selectProduceUtilizeByAreaIds(IdUtil.getIdsByStr(areaIdStr),conditions.get("year"),conditions.get("status"));
        Map<String, Object> sumTotalMap = new HashMap<>(sumTotalList.size());
        for(StrawUtilizeSumTotal sumTotal : sumTotalList){
            sumTotalMap.put(sumTotal.getAreaId(),sumTotal);
        }

        for(SysRegionTreeVo vo:voList){
            StrawUtilizeSumResVo sumTotal = new StrawUtilizeSumResVo();
            //描点信息装配
            AdPointData adPointData = new AdPointData();   //肥料化利用量
            AdPointData adPointData2 = new AdPointData();  //饲料化利用量
            AdPointData adPointData3 = new AdPointData();  //燃料化利用量
            AdPointData adPointData4 = new AdPointData();  //基料化利用量
            AdPointData adPointData5 = new AdPointData();  //原料化利用量
            sumTotal.setAreaName(vo.getRegionName());
            sumTotal.setAreaId(vo.getRegionCode());
            //描点中的区划信息
            Map<String, Object> areaMap = new HashMap<>();
            areaMap.put("name",vo.getRegionName());
            adPointData.setIndexInfo(areaMap);
            adPointData.setIndexLabelList(new HashMap<>());
            adPointData.setLatitude(vo.getLatitude());
            adPointData.setLongitude(vo.getLongitude());
            adPointData.setIndexValueLevel("1");
            adPointData.setChartType("columnar");   //柱状图
            adPointData.setIndexValue("0");
            adPointData.setAdRegionCode(vo.getRegionCode());
            adPointData.setAdRegionName(vo.getRegionName());
            BeanUtils.copyProperties(adPointData,adPointData2);
            BeanUtils.copyProperties(adPointData,adPointData3);
            BeanUtils.copyProperties(adPointData,adPointData4);
            BeanUtils.copyProperties(adPointData,adPointData5);

            if(sumTotalMap.containsKey(vo.getRegionCode())){//此时查询中有这个地区的数据
                StrawUtilizeSumTotal st = (StrawUtilizeSumTotal)sumTotalMap.get(vo.getRegionCode());
                BeanUtils.copyProperties(st, sumTotal);
                adPointData.setIndexValue(st.getMainFertilising().compareTo(new BigDecimal(1000))==-1?
                        st.getMainFertilising().setScale(0, RoundingMode.HALF_UP)+"吨":
                        st.getMainFertilising().setScale(2, RoundingMode.HALF_UP)+"万吨");
                adPointData2.setIndexValue(st.getMainForage().compareTo(new BigDecimal(1000))==-1?
                        st.getMainForage().setScale(0, RoundingMode.HALF_UP)+"吨":
                        st.getMainForage().divide(new BigDecimal(10000.00)).setScale(2, RoundingMode.HALF_UP)+"万吨");
                adPointData3.setIndexValue(st.getMainFuel().compareTo(new BigDecimal(1000))==-1?
                        st.getMainFuel().setScale(0, RoundingMode.HALF_UP)+"吨":
                        st.getMainFuel().divide(new BigDecimal(10000.00)).setScale(2, RoundingMode.HALF_UP)+"万吨");
                adPointData4.setIndexValue(st.getMainBase().compareTo(new BigDecimal(1000))==-1?
                        st.getMainBase().setScale(0, RoundingMode.HALF_UP)+"吨":
                        st.getMainBase().divide(new BigDecimal(10000.00)).setScale(2, RoundingMode.HALF_UP)+"万吨");
                adPointData5.setIndexValue(st.getMainMaterial().compareTo(new BigDecimal(1000))==-1?
                        st.getMainMaterial().setScale(0, RoundingMode.HALF_UP)+"吨":
                        st.getMainMaterial().divide(new BigDecimal(10000.00)).setScale(2, RoundingMode.HALF_UP)+"万吨");
            }
            //统计数据
            sumTotals.add(sumTotal);
            //描点信息
            adPointDataList.add(adPointData);
            adPointDataList2.add(adPointData2);
            adPointDataList3.add(adPointData3);
            adPointDataList4.add(adPointData4);
            adPointDataList5.add(adPointData5);
        }

        //维度数据列表
        List<Map<String, Object>> dimeObjList = new ArrayList<>();
        for (int i = 0; i < sumTotals.size(); i++) {
            Map<String, Object> objectMap = new HashMap<>();
            objectMap.put("num", i + 1);
            objectMap.put("area", sumTotals.get(i).getAreaName());
            objectMap.put("dimension",sumTotals.get(i).getMainFertilising().compareTo(new BigDecimal(1000))==-1?
                    sumTotals.get(i).getMainFertilising().divide(new BigDecimal(10000.00)).setScale(4, RoundingMode.CEILING):
                    sumTotals.get(i).getMainFertilising().divide(new BigDecimal(10000.00)).setScale(2, RoundingMode.CEILING));

            dimeObjList.add(objectMap);
        }
        List<Map<String, Object>> dimeObjList2 = new ArrayList<>();
        for (int i = 0; i < sumTotals.size(); i++) {
            Map<String, Object> objectMap = new HashMap<>();
            objectMap.put("num", i + 1);
            objectMap.put("area", sumTotals.get(i).getAreaName());
            objectMap.put("dimension", sumTotals.get(i).getMainForage().compareTo(new BigDecimal(1000))==-1?
                            sumTotals.get(i).getMainForage().divide(new BigDecimal(10000.00)).setScale(4, RoundingMode.CEILING):
                    sumTotals.get(i).getMainForage().divide(new BigDecimal(10000.00)).setScale(2, RoundingMode.CEILING));

            dimeObjList2.add(objectMap);
        }
        List<Map<String, Object>> dimeObjList3 = new ArrayList<>();
        for (int i = 0; i < sumTotals.size(); i++) {
            Map<String, Object> objectMap = new HashMap<>();
            objectMap.put("num", i + 1);
            objectMap.put("area", sumTotals.get(i).getAreaName());
            objectMap.put("dimension",sumTotals.get(i).getMainFuel().compareTo(new BigDecimal(1000))==-1?
                            sumTotals.get(i).getMainFuel().divide(new BigDecimal(10000.00)).setScale(4, RoundingMode.CEILING):
                    sumTotals.get(i).getMainFuel().divide(new BigDecimal(10000.00)).setScale(2, RoundingMode.CEILING));

            dimeObjList3.add(objectMap);
        }
        List<Map<String, Object>> dimeObjList4 = new ArrayList<>();
        for (int i = 0; i < sumTotals.size(); i++) {
            Map<String, Object> objectMap = new HashMap<>();
            objectMap.put("num", i + 1);
            objectMap.put("area", sumTotals.get(i).getAreaName());
            objectMap.put("dimension", sumTotals.get(i).getMainBase().compareTo(new BigDecimal(1000))==-1?
                    sumTotals.get(i).getMainBase().divide(new BigDecimal(10000.00)).setScale(4, RoundingMode.CEILING):
                    sumTotals.get(i).getMainBase().divide(new BigDecimal(10000.00)).setScale(2, RoundingMode.CEILING));

            dimeObjList4.add(objectMap);
        }
        List<Map<String, Object>> dimeObjList5 = new ArrayList<>();

        for (int i = 0; i < sumTotals.size(); i++) {
            Map<String, Object> objectMap = new HashMap<>();
            objectMap.put("num", i + 1);
            objectMap.put("area", sumTotals.get(i).getAreaName());
            objectMap.put("dimension", sumTotals.get(i).getMainMaterial().compareTo(new BigDecimal(1000))==-1?
                    sumTotals.get(i).getMainMaterial().divide(new BigDecimal(10000.00)).setScale(4, RoundingMode.CEILING):
                    sumTotals.get(i).getMainMaterial().divide(new BigDecimal(10000.00)).setScale(2, RoundingMode.CEILING));

            dimeObjList5.add(objectMap);
        }

        String flhlylstr = IndexAndDimensionEnum.Dimension.FLHLYL.toString();
        String slhlylStr = IndexAndDimensionEnum.Dimension.SLHLYL.toString();
        String rlhlylStr = IndexAndDimensionEnum.Dimension.RLHLYL.toString();
        String jlhlylStr = IndexAndDimensionEnum.Dimension.JLHLYL.toString();
        String ylhlylStr = IndexAndDimensionEnum.Dimension.YLHLYL.toString();
        Map<String, List<Map<String, Object>>> dataMap1 = new HashMap<>();
        dataMap1.put(flhlylstr, dimeObjList);
        Map<String, List<Map<String, Object>>> dataMap2 = new HashMap<>();
        dataMap2.put(slhlylStr, dimeObjList2);
        Map<String, List<Map<String, Object>>> dataMap3 = new HashMap<>();
        dataMap3.put(rlhlylStr, dimeObjList3);
        Map<String, List<Map<String, Object>>> dataMap4 = new HashMap<>();
        dataMap4.put(jlhlylStr, dimeObjList4);
        Map<String, List<Map<String, Object>>> dataMap5 = new HashMap<>();
        dataMap5.put(ylhlylStr, dimeObjList4);
        dataMap.putAll(dataMap1);
        dataMap.putAll(dataMap2);
        dataMap.putAll(dataMap3);
        dataMap.putAll(dataMap4);
        dataMap.putAll(dataMap5);
        adStatisticsData.setDataMap(dataMap);
        outputData.setAdStatisticsData(adStatisticsData);

        Map<String, List<AdPointData>> adPointMap = new HashMap<>();
        adPointMap.put(flhlylstr, adPointDataList);
        adPointMap.put(slhlylStr, adPointDataList2);
        adPointMap.put(rlhlylStr, adPointDataList3);
        adPointMap.put(jlhlylStr, adPointDataList4);
        adPointMap.put(ylhlylStr, adPointDataList5);

        outputData.setAdPointDataList(adPointMap);
        return outputData;

    }

    //维度下仅有一个指标的，合并方法来处理
    private MapViewData getDataByDimension(String adLevel,  String adCode, Map<String, String> conditions, IndexAndDimensionEnum.Dimension dimension){
        MapViewData outputData = new MapViewData();
        outputData.setAdAreaDataList(new HashMap<>());
        outputData.setAdLevel(adLevel);
        outputData.setViewType("point");   //描点
        AdStatisticsData adStatisticsData = new AdStatisticsData();
        //表头
        Map<String, String> headMap = new HashMap<>();
        headMap.put("num", "序号");
        headMap.put("area", "行政区划");
        headMap.put("dimension", dimension.getName());
        adStatisticsData.setHeaderMap(headMap);
        //维度
        Map<String, String> dimensionMap = new HashMap<>();
        for (IndexAndDimensionEnum.Dimension dimensionEnum : IndexAndDimensionEnum.Dimension.values()) {
            dimensionMap.put(dimensionEnum.toString(), dimensionEnum.getName());
        }
        adStatisticsData.setDimensionList(dimensionMap);
        //统计数据
        Map<String, List<Map<String, Object>>> dataMap = new HashMap<>();
        //描点信息
        List<AdPointData> adPointDataList = new ArrayList<>();
        //存放统计数据
        List<StrawUtilizeSumResVo> sumTotals = new ArrayList<>();
        /**
         *@Description //查询条件重新组合
         **/
        String parentId = "100000";     //国家级别，查询各个省的数据统计
        if(!AD_COUNTY.equals(adLevel)){
            conditions.put("areaId", adCode);
            parentId = adCode;
        }
        //获取下一级列表
        Result<PageUtils<SysRegionTreeVo>> result = sysApi.getSysRegionFormByParentId(null,parentId,null,null,null,null);
        List<SysRegionTreeVo> voList = result.getData().getList();
        StringBuffer areaIds = new StringBuffer();
        for(SysRegionTreeVo treeVo : voList){
            areaIds.append(treeVo.getRegionCode()).append(",");
        }
        //若获取不到区划信息则系统已经报错
        if(areaIds.length()==0){
            return outputData;
        }
        String areaIdStr = areaIds.substring(0,areaIds.length()-1);
        List<StrawUtilizeSumTotal> sumTotalList = strawUtilizeSumTotalMapper.selectProduceUtilizeByAreaIds(IdUtil.getIdsByStr(areaIdStr),conditions.get("year"),conditions.get("status"));
        Map<String, Object> sumTotalMap = new HashMap<>(sumTotalList.size());
        for(StrawUtilizeSumTotal sumTotal : sumTotalList){
            sumTotalMap.put(sumTotal.getAreaId(),sumTotal);
        }
        for(SysRegionTreeVo vo:voList){
            StrawUtilizeSumResVo sumTotal = new StrawUtilizeSumResVo();
            //描点信息装配
            AdPointData adPointData = new AdPointData();
            sumTotal.setAreaName(vo.getRegionName());
            sumTotal.setAreaId(vo.getRegionCode());
            //描点中的区划信息
            Map<String, Object> areaMap = new HashMap<>();
            areaMap.put("name",vo.getRegionName());
            adPointData.setIndexInfo(areaMap);
            adPointData.setIndexLabelList(new HashMap<>());
            adPointData.setLatitude(vo.getLatitude());
            adPointData.setLongitude(vo.getLongitude());
            adPointData.setIndexValueLevel("1");
            adPointData.setChartType("columnar");   //柱状图
            adPointData.setIndexValue("0");         //给个默认值
            adPointData.setAdRegionCode(vo.getRegionCode());
            adPointData.setAdRegionName(vo.getRegionName());

            if(sumTotalMap.containsKey(vo.getRegionCode())){//此时查询中有这个地区的数据
                StrawUtilizeSumTotal st = (StrawUtilizeSumTotal)sumTotalMap.get(vo.getRegionCode());
                BeanUtils.copyProperties(st, sumTotal);

                if(dimension.equals(IndexAndDimensionEnum.Dimension.DIRECTRETURN)){
                    adPointData.setIndexValue(st.getReturnResource().compareTo(new BigDecimal(1000))==-1?
                            st.getReturnResource().setScale(0, RoundingMode.HALF_UP)+"吨":
                            st.getReturnResource().divide(new BigDecimal(10000.00)).setScale(2, RoundingMode.HALF_UP)+"万吨");
                }else if(dimension.equals(IndexAndDimensionEnum.Dimension.CALLIN)){
                    adPointData.setIndexValue(st.getMainTotalOther().compareTo(new BigDecimal(1000))==-1?
                            st.getMainTotalOther().setScale(0, RoundingMode.HALF_UP)+"吨":
                            st.getMainTotalOther().divide(new BigDecimal(10000.00)).setScale(2, RoundingMode.HALF_UP)+"万吨");
                }else if(dimension.equals(IndexAndDimensionEnum.Dimension.OEE)) {
                    adPointData.setIndexValue(st.getComprehensive().setScale(2, RoundingMode.HALF_UP)+"");
                }else if(dimension.equals(IndexAndDimensionEnum.Dimension.COMPREHENSIVEUTILIZATION)){
                    adPointData.setIndexValue(st.getCollectResource().multiply(st.getComprehensive()).divide(new BigDecimal(100)).compareTo(new BigDecimal(1000))==-1?
                            st.getCollectResource().multiply(st.getComprehensive()).divide(new BigDecimal(100)).setScale(0, RoundingMode.HALF_UP)+"吨":
                            st.getCollectResource().multiply(st.getComprehensive()).divide(new BigDecimal(100))
                                    .divide(new BigDecimal(10000.00))
                                    .setScale(2, RoundingMode.HALF_UP)+"万吨");
                }else if(dimension.equals(IndexAndDimensionEnum.Dimension.CUCI)){
                    adPointData.setIndexValue(st.getComprehensiveIndex().setScale(2, RoundingMode.HALF_UP)+"");
                }else if(dimension.equals(IndexAndDimensionEnum.Dimension.IUCI)){
                    adPointData.setIndexValue(st.getIndustrializationIndex().setScale(2, RoundingMode.HALF_UP)+"");
                }

            }
            adPointData.setIndexInfo(areaMap);
            //统计数据
            sumTotals.add(sumTotal);
            //描点信息
            adPointDataList.add(adPointData);
        }
        //维度数据列表
        List<Map<String, Object>> dimeObjList = new ArrayList<>();
        for (int i = 0; i < sumTotals.size(); i++) {
            Map<String, Object> objectMap = new HashMap<>();
            objectMap.put("num", i + 1);
            objectMap.put("area", sumTotals.get(i).getAreaName());

            if(dimension.equals(IndexAndDimensionEnum.Dimension.DIRECTRETURN)){
                objectMap.put("dimension", sumTotals.get(i).getReturnResource().compareTo(new BigDecimal(1000))==-1?
                        sumTotals.get(i).getReturnResource().divide(new BigDecimal(10000)).setScale(4, RoundingMode.HALF_UP):
                        sumTotals.get(i).getReturnResource().divide(new BigDecimal(10000)).setScale(2, RoundingMode.HALF_UP)); //直接还田量
            }else if(dimension.equals(IndexAndDimensionEnum.Dimension.CALLIN)){
                objectMap.put("dimension",sumTotals.get(i).getMainTotalOther().compareTo(new BigDecimal(1000))==-1?
                        sumTotals.get(i).getMainTotalOther().divide(new BigDecimal(10000)).setScale(4, RoundingMode.HALF_UP):
                        sumTotals.get(i).getMainTotalOther().divide(new BigDecimal(10000)).setScale(2, RoundingMode.HALF_UP)); //市场主体调入量
            }else if(dimension.equals(IndexAndDimensionEnum.Dimension.OEE)){
                objectMap.put("dimension", sumTotals.get(i).getComprehensive().setScale(2, RoundingMode.HALF_UP));
            }else if(dimension.equals(IndexAndDimensionEnum.Dimension.COMPREHENSIVEUTILIZATION)){
                objectMap.put("dimension",sumTotals.get(i).getCollectResource().multiply(sumTotals.get(i).getComprehensive()).divide(new BigDecimal(100)).compareTo(new BigDecimal(1000))==-1?
                        sumTotals.get(i).getCollectResource().multiply(sumTotals.get(i).getComprehensive()).divide(new BigDecimal(100))
                        .divide(new BigDecimal(10000)).setScale(4, RoundingMode.HALF_UP):
                        sumTotals.get(i).getCollectResource().multiply(sumTotals.get(i).getComprehensive()).divide(new BigDecimal(100))
                        .divide(new BigDecimal(10000)).setScale(2, RoundingMode.HALF_UP)); //综合利用量
            }else if(dimension.equals(IndexAndDimensionEnum.Dimension.CUCI)){
                objectMap.put("dimension", sumTotals.get(i).getComprehensiveIndex().setScale(2, RoundingMode.HALF_UP));
            }else if(dimension.equals(IndexAndDimensionEnum.Dimension.IUCI)){
                objectMap.put("dimension", sumTotals.get(i).getIndustrializationIndex().setScale(2, RoundingMode.HALF_UP));
            }

            dimeObjList.add(objectMap);
        }

        String dimensionString = dimension.toString();
        Map<String, List<Map<String, Object>>> dataMap1 = new HashMap<>();
        dataMap1.put(dimensionString, dimeObjList);

        dataMap.putAll(dataMap1);
        adStatisticsData.setDataMap(dataMap);
        outputData.setAdStatisticsData(adStatisticsData);

        Map<String, List<AdPointData>> adPointMap = new HashMap<>();
        adPointMap.put(dimensionString, adPointDataList);

        outputData.setAdPointDataList(adPointMap);
        return outputData;
    }

    @Override
    public List<MapConditions> getMapConditions(String index) {
        return null;
    }
}