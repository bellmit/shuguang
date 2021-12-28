package com.sofn.agzirdd.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sofn.agzirdd.enums.ClassifyEnum;
import com.sofn.agzirdd.enums.RoleCodeEnum;
import com.sofn.agzirdd.enums.StatusEnum;
import com.sofn.agzirdd.enums.YseOrNoEnum;
import com.sofn.agzirdd.mapper.DistributionMapMapper;
import com.sofn.agzirdd.mapper.ThresholdValueMapper;
import com.sofn.agzirdd.mapper.WarningMonitorMapper;
import com.sofn.agzirdd.mapper.WarningThresholdMapper;
import com.sofn.agzirdd.model.DistributionMap;
import com.sofn.agzirdd.model.ThresholdValue;
import com.sofn.agzirdd.model.WarningMonitor;
import com.sofn.agzirdd.model.WarningThreshold;
import com.sofn.agzirdd.service.WarningMonitorService;
import com.sofn.agzirdd.sysapi.SysRegionApi;
import com.sofn.agzirdd.sysapi.bean.SysOrganization;
import com.sofn.agzirdd.sysapi.bean.SysRegionTreeVo;
import com.sofn.agzirdd.util.DateUtil;
import com.sofn.agzirdd.vo.DistributionMapVo;
import com.sofn.agzirdd.vo.WarningMonitorVo;
import com.sofn.agzirdd.vo.WarningMonitorVoAdd;
import com.sofn.common.map.AdPointData;
import com.sofn.common.map.AdStatisticsData;
import com.sofn.common.map.MapConditions;
import com.sofn.common.map.MapViewData;
import com.sofn.common.model.Result;
import com.sofn.common.utils.DateUtils;
import com.sofn.common.utils.JsonUtils;
import com.sofn.common.utils.UserUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class WarningMonitorServiceImpl extends ServiceImpl<WarningMonitorMapper, WarningMonitor> implements WarningMonitorService {
    public static final String AD_COUNTY = "ad_county";
    public static final String AD_PROVINCE = "ad_province";
    public static final String AD_CITY = "ad_city";
    //系统默认地图颜色,绿色
    private static final String GREEN = "#00FF00";
    private static final String RED = "#FF0000";

    @Autowired
    private WarningMonitorMapper warningMonitorMapper;
    @Autowired
    private WarningThresholdMapper warningThresholdMapper;
    @Autowired
    private ThresholdValueMapper thresholdValueMapper;
    @Autowired
    private SysRegionApi sysRegionApi;
    @Autowired
    private DistributionMapMapper distributionMapMapper;

    @Override
    public List<WarningMonitorVo> getWarningMonitorVoList(Map<String, String> params) {
        List<WarningMonitor> list = warningMonitorMapper.selectByCondition(params);
        Map<String,Object> param = new HashMap<>();
        for(String key : params.keySet()){
            param.put(key, params.get(key));
        }
        List<WarningThreshold> thresholdList = warningThresholdMapper.getWarningThresholdByCondition(param);
        //对获取的数据进行处理
        List<WarningMonitorVo> voList = new ArrayList<>();
        for(WarningMonitor warningMonitor : list){
            WarningMonitorVo vo = new WarningMonitorVo();
            BeanUtils.copyProperties(warningMonitor, vo);
            if(!thresholdList.isEmpty()){
                //一个物种的一个指标分类只有一条数据
                WarningThreshold warningThreshold = thresholdList.get(0);
                //此处为判断当前值与阈值的关系，以便给出预警颜色
                String color = getWarningColor(warningThreshold, warningMonitor.getAmount());
                vo.setColor(color);
            }
            String areaName = this.setAreaName(warningMonitor, null);
            vo.setAreaName(areaName);
            voList.add(vo);
        }

        return voList;
    }

    //通过判断给出预警颜色
    private String getWarningColor(WarningThreshold warningThreshold,String amount){
        //给定默认颜色值
        String color = GREEN;
        Long amountLong = Long.parseLong(amount);
        //阈值设置值列表
        List<ThresholdValue> thresholdValueList = thresholdValueMapper.getThresholdValueByWtId(warningThreshold.getId());
        if(thresholdValueList.isEmpty())
            return color;

        for(ThresholdValue value:thresholdValueList){
            String condition1 = value.getCondition1();
            String condition2 = value.getCondition2();
            Long value1 = value.getValue1().longValue();
            Long value2 = value.getValue2().longValue();
            //条件1:0-'>',1-'>=',2-'=',3-'!='
            //条件2:0-'<',1-'<=',2-'=',3-'!='

            //如果条件1不为空，条件2为空
            if(StringUtils.isNotBlank(condition1)&&StringUtils.isEmpty(condition2)){
                switch (condition1){
                    case "0":{
                        if(amountLong>value1){
                            color =  value.getColor();
                            break;
                        }
                    }
                    case "1":{
                        if(amountLong>=value1){
                            color =  value.getColor();
                            break;
                        }
                    }
                    case "2":{
                        if(amountLong==value1){
                            color =  value.getColor();
                            break;
                        }
                    }
                    case "3":{
                        if(amountLong!=value1){
                            color =  value.getColor();
                            break;
                        }
                    }
                }
            }else if(StringUtils.isNotBlank(condition1)&&StringUtils.isNotBlank(condition2)){
                if(condition1.equals("2")||condition2.equals("2")){
                    if(amountLong==value1||amountLong==value2){
                        color =  value.getColor();
                        break;
                    }
                }else {
                    if(condition1.equals("0")&&condition2.equals("0")
                       && amountLong>value1 && amountLong<value2){
                        color =  value.getColor();
                        break;
                    }
                    if(condition1.equals("0")&&condition2.equals("1")
                            && amountLong>value1 && amountLong<=value2){
                        color =  value.getColor();
                        break;
                    }
                    if(condition1.equals("0")&&condition2.equals("3")
                            && amountLong>value1 && amountLong!=value2){
                        color =  value.getColor();
                        break;
                    }
                    if(condition1.equals("1")&&condition2.equals("0")
                            && amountLong>=value1 && amountLong<value2){
                        color =  value.getColor();
                        break;
                    }
                    if(condition1.equals("1")&&condition2.equals("1")
                            && amountLong>=value1 && amountLong<=value2){
                        color =  value.getColor();
                        break;
                    }
                    if(condition1.equals("1")&&condition2.equals("3")
                            && amountLong>=value1 && amountLong!=value2){
                        color =  value.getColor();
                        break;
                    }
                    if(condition1.equals("3")&&condition2.equals("0")
                            && amountLong!=value1 && amountLong<value2){
                        color =  value.getColor();
                        break;
                    }
                    if(condition1.equals("3")&&condition2.equals("1")
                            && amountLong!=value1 && amountLong<=value2){
                        color =  value.getColor();
                        break;
                    }
                    if(condition1.equals("3")&&condition2.equals("3")
                            && amountLong!=value1 && amountLong!=value2){
                        color =  value.getColor();
                        break;
                    }
                }
            }
        }

        return color;
    }


    private String setAreaName(WarningMonitor warningMonitor, String adLevel){
        Result<List<SysRegionTreeVo>> result = sysRegionApi.getParentNode(warningMonitor.getCountyId());
        if(null==result||result.getData()==null)
            return "";
        List<SysRegionTreeVo> strList = result.getData();

        return assemblingInfo(strList, adLevel);
    }


    private String assemblingInfo(List<SysRegionTreeVo> strList, String adLevel){
        StringBuffer strBuf = new StringBuffer();
        int len = StringUtils.isBlank(adLevel)?
                strList.size():adLevel.equals(AD_COUNTY)?
                1:adLevel.equals(AD_PROVINCE)?
                        2:adLevel.equals(AD_CITY)?3:strList.size();
        for(int i=0;i<len;i++){
            strBuf.append(strList.get(i).getRegionName());
        }

        return strBuf.toString();
    }

    @Override
    public MapViewData getMapViewData(String index, String adLevel, String adCode, Map<String, String> conditions) {
        List<String> roleStr = UserUtil.getLoginUserRoleCodeList();
        String roleCode = roleStr.get(0);
        if ("AREA".equals(index) || "ZCJJSS".equals(index)) {//预警监测
            return getAreaMapViewData(index,adLevel, adCode, conditions, roleCode);
        }else {//新发物种
            return getNewSpeciesMapViewData(adLevel, adCode, conditions, roleCode);
        }
    }

    @Override
    public List<String> getMonitorTimeList(String index, String speciesName) {
        List<String> roleStr = UserUtil.getLoginUserRoleCodeList();
        String roleCode = roleStr.get(0);
        Map<String, Object> condition1 = new HashMap<>();
        if("agzirdd_province".equals(roleCode)){
            //获取当前用户所属机构区划信息
            String loginUserOrganizationInfo = UserUtil.getLoginUserOrganizationInfo();
            SysOrganization orgData = JsonUtils.json2obj(loginUserOrganizationInfo, SysOrganization.class);
            //判断当前用户所在机构的类型(Y-行政机构,N-代理机构)
            if(orgData.getThirdOrg().equals("Y")) {
                String address = orgData.getAddress();
                List<String> areaList = JsonUtils.json2List(address, String.class);
                condition1.put("provinceId",areaList.get(0));
            }
        }else if("agzirdd_terminal".equals(roleCode)){
            condition1.put("isSubmit","1");   //上报总部的数据
        }

        Set<String> dateStrList = new HashSet<>();
        if("AREA".equals(index) || "ZCJJSS".equals(index)) {//若查询的是发生面积或造成经济损失
            String classificationName = ClassifyEnum.AREA.getDescription();
            if("ZCJJSS".equals(index))
                classificationName = ClassifyEnum.ZCJJSS.getDescription();

            condition1.put("speciesName",speciesName);

            condition1.put("classificationName",classificationName);

            List<WarningThreshold> thresholdList = warningThresholdMapper.getWarningThresholdByCondition(condition1);
            Map<String, String> conditions = new HashMap<>();
            //从其他系统获取，此处赋值方便查询，值无实际意义
//            conditions.put("classificationId","d049f62740be40f7827827044abd205b");
            conditions.put("classificationName",classificationName);
            conditions.put("speciesName", speciesName);
            if("agzirdd_province".equals(roleCode)){
                conditions.put("provinceId",condition1.get("provinceId")+"");
            }
            conditions.put("status", StatusEnum.STATUS_7.getCode());
            conditions.put("isNew", YseOrNoEnum.YES.getCode());
            //只取县级填报数据
            conditions.put("roleCode", RoleCodeEnum.COUNTY.getCode());

            //获取物种预警信息
            List<WarningMonitor> warningMonitorsTemp = warningMonitorMapper.selectByCondition(conditions);
            if(!thresholdList.isEmpty()){
                List<String> provinceIds = new ArrayList<>(thresholdList.size());
                List<String> speciesNames = new ArrayList<>(thresholdList.size());
                for(WarningThreshold warningThreshold : thresholdList){
                    provinceIds.add(warningThreshold.getProvinceId());
                    speciesNames.add(warningThreshold.getSpeciesName());
                }
                //过滤出阈值中有的值
                warningMonitorsTemp = warningMonitorsTemp.stream().filter(s->provinceIds.contains(s.getProvinceId())
                        && speciesNames.contains(s.getSpeciesName())).collect(Collectors.toList());
            }else {//阈值为空则返回空
                return new ArrayList<>(dateStrList);
            }
            for (WarningMonitor warningMonitor: warningMonitorsTemp){
                dateStrList.add(DateUtils.format(warningMonitor.getCreateTime(),"yyyy-MM-dd"));
            }
        }else {
            condition1.put("classificationName","新发物种");
            List<WarningThreshold> thresholdList = warningThresholdMapper.getWarningThresholdByCondition(condition1);
            Map<String, Object> condition = new HashMap<>();
            condition.put("speciesName", speciesName);
            condition.put("isNew", YseOrNoEnum.YES.getCode());
            //只取县级填报数据
            condition.put("roleCode", RoleCodeEnum.COUNTY.getCode());
            if("agzirdd_province".equals(roleCode)){
                condition.put("provinceId",condition1.get("provinceId")+"");
            }
            //获取物种分布信息
            List<DistributionMap> distributionMaps = distributionMapMapper.selectByConditions(condition);
            if(!thresholdList.isEmpty()){
                List<String> provinceIds = new ArrayList<>(thresholdList.size());
                for(WarningThreshold warningThreshold : thresholdList){
                    provinceIds.add(warningThreshold.getProvinceId());
                }
                //过滤出阈值中有的值
                distributionMaps = distributionMaps.stream().filter(s->provinceIds.contains(s.getProvinceId())).collect(Collectors.toList());
            }else {//阈值为空则返回空
                return new ArrayList<>(dateStrList);
            }
            for(DistributionMap distributionMap :distributionMaps){
                dateStrList.add(DateUtils.format(distributionMap.getCreateTime(),"yyyy-MM-dd"));
            }
        }

        return new ArrayList<>(dateStrList);
    }

    @Override
    public boolean deleteByCondition(Map<String, String> params) {
        int count =  warningMonitorMapper.deleteByCondition(params);
        return count>0?true:false;
    }

    @Override
    public boolean deleteBySpeciesMonitorId(String id) {
        Map<String, String> wmParams = new HashMap();
        wmParams.put("speciesMonitorId", id);
        int count =  warningMonitorMapper.deleteByCondition(wmParams);
        return count>0?true:false;
    }

    //获取发生面积预警信息
    private MapViewData getAreaMapViewData(String index, String adLevel, String adCode, Map<String, String> conditions, String roleCode){
        //获取输入的查询年份
        int recentYear = Integer.parseInt(
                conditions.get("belongYear")!=null? conditions.get("belongYear"):
                        conditions.get("createTime")==null?String.valueOf(Calendar.getInstance().get(Calendar.YEAR)):
                        conditions.get("createTime").substring(0,4));
        MapViewData outputData = new MapViewData();
        //描点信息
        Map<String, List<AdPointData>> adPointMap = new HashMap<>();
        outputData.setAdAreaDataList(new HashMap<>());
        outputData.setAdLevel(adLevel);
        outputData.setViewType("point");   //描点
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
        //若发生面积查询未指定物种，则返回空
        if(StringUtils.isEmpty(conditions.get("speciesName"))){
            adStatisticsData.setDataMap(dataMap);
            outputData.setAdPointDataList(adPointMap);
            outputData.setAdStatisticsData(adStatisticsData);
            return outputData;
        }

        //接口传入参数处理
        if(AD_PROVINCE.equals(adLevel)){
            conditions.put("provinceId", adCode);
        }else if(AD_CITY.equals(adLevel)){
            conditions.put("cityId", adCode);
        }
        //从其他系统获取，此处赋值方便查询，值无实际意义
//        conditions.put("classificationId","d049f62740be40f7827827044abd205b");
        //只能查询状态为「总站通过」的数据
        conditions.put("status", StatusEnum.STATUS_7.getCode());
        //最新数据
        conditions.put("isNew", YseOrNoEnum.YES.getCode());

        String classificationName = ClassifyEnum.AREA.getDescription();
        if("ZCJJSS".equals(index))
            classificationName = ClassifyEnum.ZCJJSS.getDescription();

        conditions.put("classificationName",classificationName);
        //只取县级填报数据
        conditions.put("roleCode", RoleCodeEnum.COUNTY.getCode());

        if("agzirdd_province".equals(roleCode)){
            //获取当前用户所属机构区划信息
            String loginUserOrganizationInfo = UserUtil.getLoginUserOrganizationInfo();
            SysOrganization orgData = JsonUtils.json2obj(loginUserOrganizationInfo, SysOrganization.class);
            //判断当前用户所在机构的类型(Y-行政机构,N-代理机构)
            if(orgData.getThirdOrg().equals("Y")) {
                String address = orgData.getAddress();
                List<String> areaList = JsonUtils.json2List(address, String.class);
                conditions.put("provinceId",areaList.get(0));
            }
        }
        //获取物种发生面积信息
        List<WarningMonitor> warningMonitorsTemp = warningMonitorMapper.selectByCondition(conditions);
        //获取上一年的物种发生面积信息
        if(StringUtils.isNotEmpty(conditions.get("createTime"))){
            //若存在日期，则需要清空日期，以便查询上一年度数据，
            //否则，日期与年度同时存在，且上年度小于日期中的年度，则永远查询不到数据
            conditions.remove("createTime");
        }
        conditions.put("belongYear", String.valueOf(recentYear-1));
        List<WarningMonitor> warningMonitorLastYearTemp = warningMonitorMapper.selectByCondition(conditions);

        Map<String, Object> condition = new HashMap<>();
        condition.put("speciesName",conditions.get("speciesName"));
        //若是总站用户，则需要看相应物种是否上报总站,上报就能看，否则不能看
        if("agzirdd_terminal".equals(roleCode)){
            condition.put("isSubmit","1");   //去除掉未上报总部的那些数据
        }else if("agzirdd_province".equals(roleCode)){
            condition.put("provinceId",conditions.get("provinceId"));
        }
        if("AREA".equals(index)){
            condition.put("classificationName",ClassifyEnum.AREA.getDescription());
        }else if("ZCJJSS".equals(index)){
            condition.put("classificationName",ClassifyEnum.ZCJJSS.getDescription());
        }

        List<WarningThreshold> thresholdList = warningThresholdMapper.getWarningThresholdByCondition(condition);
        if(!thresholdList.isEmpty()){
            List<String> provinceIds = new ArrayList<>(thresholdList.size());
            List<String> speciesNames = new ArrayList<>(thresholdList.size());
            for (WarningThreshold threshold : thresholdList){
                provinceIds.add(threshold.getProvinceId());
                speciesNames.add(threshold.getSpeciesName());
            }
            //过滤出阈值中有的值
            warningMonitorsTemp = warningMonitorsTemp.stream().filter(s->speciesNames.contains(s.getSpeciesName())
                    && provinceIds.contains(s.getProvinceId())).collect(Collectors.toList());
            warningMonitorLastYearTemp = warningMonitorLastYearTemp.stream().filter(s->speciesNames.contains(s.getSpeciesName())
                    && provinceIds.contains(s.getProvinceId())).collect(Collectors.toList());

        }else {//若阈值不存在则返回空
            warningMonitorsTemp.clear();
            warningMonitorLastYearTemp.clear();
        }

        List<WarningMonitorVoAdd> voAdds = new ArrayList<>(warningMonitorsTemp.size());
        List<WarningMonitorVoAdd> voAddsLast = new ArrayList<>(warningMonitorLastYearTemp.size());
        for(WarningMonitor monitor : warningMonitorsTemp){
            WarningMonitorVoAdd voAdd = new WarningMonitorVoAdd();
            BeanUtils.copyProperties(monitor, voAdd);
            voAdd.setAreaName(this.setAreaName(monitor, adLevel));     //重新装配返回地区信息
            voAdd.setAdCode(adCode);
            voAdd.setAdLevel(adLevel);
            voAdds.add(voAdd);
        }
        for(WarningMonitor monitor : warningMonitorLastYearTemp){
            WarningMonitorVoAdd voAdd = new WarningMonitorVoAdd();
            BeanUtils.copyProperties(monitor, voAdd);
            voAdd.setAreaName(this.setAreaName(monitor, adLevel));     //重新装配返回地区信息
            voAdd.setAdCode(adCode);
            voAdd.setAdLevel(adLevel);
            voAddsLast.add(voAdd);
        }
        /**
         *@Author Chlf
         *@Description
         * 地图当前所在级别为国家级则将数据根据省份分类
         * 地图当前所在级别为省级则将数据根据市分类
         * 地图当前所在级别为市级则将数据根据区县分类
         **/
        //当年的数据重新分组
        Map<String, List<WarningMonitorVoAdd>> warningMonitorMap =
                AD_COUNTY.equals(adLevel)?
                        voAdds.stream().collect(Collectors.groupingBy(
                                WarningMonitorVoAdd::getProvinceId)):
                        AD_PROVINCE.equals(adLevel)?
                                voAdds.stream().collect(Collectors.groupingBy(
                                        WarningMonitorVoAdd::getCityId)):
                                voAdds.stream().collect(Collectors.groupingBy(
                                        WarningMonitorVoAdd::getCountyId));

        //上一年的数据分组
        Map<String, List<WarningMonitorVoAdd>> warningMonitorMapLastYear =
                AD_COUNTY.equals(adLevel)?
                        voAddsLast.stream().collect(Collectors.groupingBy(
                                WarningMonitor::getProvinceId)):
                        AD_PROVINCE.equals(adLevel)?
                                voAddsLast.stream().collect(Collectors.groupingBy(
                                        WarningMonitor::getCityId)):
                                voAddsLast.stream().collect(Collectors.groupingBy(
                                        WarningMonitor::getCountyId));

        //根据区划ID（key）循环distributionMap,得到其中的list，然后再处理其中的list
        for(Map.Entry<String, List<WarningMonitorVoAdd>> entry : warningMonitorMap.entrySet()) {
            //描点信息装配
            AdPointData adPointData = new AdPointData();
            adPointData.setChartType("columnar");             //柱状图
            adPointData.setIndexLabelList(new HashMap<>());
            //归类后挑出需要组合的数据
            Map<String,Object> indexMap = new HashMap<>();
            //省（市）区划编码
            String mapKey = entry.getKey();
            List<WarningMonitorVoAdd> mapValue = entry.getValue();
            //上一年度的数据
            List<WarningMonitorVoAdd> mapValLastYear = warningMonitorMapLastYear.get(mapKey);
            //比较2年度的数据，分析得出描点颜色值
            String level = compare2YearData(index,mapValue, mapValLastYear);
            adPointData.setIndexValueLevel(level);              //默认显示绿色
            if(!adLevel.equalsIgnoreCase(AD_CITY)){
                indexMap.put("adLevel", AD_COUNTY.equals(adLevel)?AD_PROVINCE:
                        AD_PROVINCE.equals(adLevel)?AD_CITY:adLevel);
                indexMap.put("adCode", entry.getKey());
            }else {
                indexMap.put("index", mapValue);
            }
            adPointData.setIndexInfo(indexMap);
            adPointData.setIndexValue(mapValue.get(0).getAreaName());
            adPointData.setLatitude(mapValue.get(0).getLatitude());
            adPointData.setLongitude(mapValue.get(0).getLongitude());

            //描点信息
            adPointDataList.add(adPointData);
        }
        //统计数据
        mapLists.add(new DistributionMap());

        //对当年的值重新整理
        this.setVal(adLevel, warningMonitorMapLastYear, warningMonitorMap);
        //维度数据列表
        List<Map<String, Object>> dimeObjList = new ArrayList<>();
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("num", 1);
        objectMap.put("year", String.valueOf(recentYear));
        objectMap.put("dimension", warningMonitorMap);            //当前年度的数据

        Map<String, Object> objectMap2 = new HashMap<>();
        objectMap2.put("num", 2);
        objectMap2.put("year", String.valueOf(recentYear-1));
        objectMap2.put("dimension", warningMonitorMapLastYear);   //上一年度的数据

        dimeObjList.add(objectMap); //查询年数据
        dimeObjList.add(objectMap2);   //前一年数据

        String disString = "distribution";
        Map<String, List<Map<String, Object>>> dataMap1 = new HashMap<>();
        dataMap1.put(disString, dimeObjList);

        dataMap.putAll(dataMap1);
        adStatisticsData.setDataMap(dataMap);
        outputData.setAdStatisticsData(adStatisticsData);
        adPointMap.put(disString, adPointDataList);

        outputData.setAdPointDataList(adPointMap);
        return outputData;
    }

    //比较2个列表数据，然后将前边的值赋给后边
    /**
     *
     * @param adLevel
     * @param source    上一年数据
     * @param target    当前查询年数据
     */
    private void setVal(String adLevel, Map<String, List<WarningMonitorVoAdd>> source, Map<String, List<WarningMonitorVoAdd>> target){
        if(target == null){
            target = new HashMap<>();
        }

        Map<String,List<WarningMonitorVoAdd>> targerMap = new HashMap<>();
        Map<String,List<WarningMonitorVoAdd>> sourceMap = new HashMap<>();
        //countyId,2019,2020,
        if (AD_CITY.equals(adLevel)) {  //县级不用汇总
            List<WarningMonitorVoAdd> targetList = new ArrayList<>();
            List<WarningMonitorVoAdd> sourceList = new ArrayList<>();

            target.values().forEach(l -> targetList.addAll(l));
            source.values().forEach(l -> sourceList.addAll(l));

            targerMap.clear();
            targerMap.put("value",targetList);
            sourceMap.clear();
            sourceMap.put("value",sourceList);
        }else{
            for(Map.Entry<String, List<WarningMonitorVoAdd>> entry : target.entrySet()) {
                String mapKey = entry.getKey();
                //当前年度的数据
                List<WarningMonitorVoAdd> mapVal = entry.getValue();
                //上一年度的数据
                List<WarningMonitorVoAdd> mapValLastYear = source.get(mapKey);

                List<WarningMonitorVoAdd> targetList = new ArrayList<>(1);
                List<WarningMonitorVoAdd> sourceList = new ArrayList<>(1);

                WarningMonitorVoAdd targetVo = new WarningMonitorVoAdd();
                BeanUtils.copyProperties(mapVal.get(0), targetVo);

                Double count = this.countVal(mapVal);
                //区划内总和
                targetVo.setAmount(count.toString());

                Double countLast = this.countVal(mapValLastYear);

                //上一年度区划内总和
                targetVo.setAmountLastYear(countLast.toString());
                //区划内发生面积与上一年度变化
                targetVo.setAmountChange(this.getValChange(count,countLast));
                targetList.add(targetVo);

                //如果有上年数据
                if(mapValLastYear!=null){
                    WarningMonitorVoAdd sourceVo = new WarningMonitorVoAdd();
                    BeanUtils.copyProperties(mapValLastYear.get(0), sourceVo);
                    sourceVo.setAmount(countLast.toString());
                    sourceList.add(sourceVo);
                }


                targerMap.put("value",targetList);
                sourceMap.put("value",sourceList);
            }
        }


        target.clear();
        target.putAll(targerMap);

        source.clear();
        source.putAll(sourceMap);
    }

    //比较前后2个值的变化情况
    private String getValChange(Double val1, Double val2){
        String changeStr = "";
        Double change = sub(val1,val2);
        changeStr = change.compareTo(0d)>0?
                "较上年增加"+change:change.compareTo(0d)==0?
                "与上年相同"+ val1:"较上年减少"+String.valueOf(change).substring(1);
        return  changeStr;

    }

    //计算list中amount之和
    private Double countVal(List<WarningMonitorVoAdd> voAdds){
        BigDecimal result = BigDecimal.ZERO;
        if(voAdds!=null && voAdds.size()>0){
            for(WarningMonitorVoAdd voAdd : voAdds){
                result = result.add(new BigDecimal(voAdd.getAmount()));
            }
        }
        return result.doubleValue();
    }

    //比较2年的数据，若其中有一个数据超过指标那么显示红色
    private String compare2YearData(String index, List<WarningMonitorVoAdd> mapValue, List<WarningMonitorVoAdd> mapValueLastYear) {
        //当前年没有数据，直接绿色
        if(mapValue == null ||  mapValue.isEmpty()){
            return GREEN;
        }
        //上一年没有数据，直接为红色
        if(mapValueLastYear == null ||  mapValueLastYear.isEmpty()){
            return RED;
        }
        String color = GREEN;   //default

        //以区县ID为key，放进map
        Map<String,WarningMonitorVoAdd> lastWmvMap = mapValueLastYear.stream().collect(Collectors.toMap(m-> m.getCountyId(),m->m));

        WarningMonitorVoAdd last = null;
        Double inc = null;
        Double incv = null;
        for (WarningMonitorVoAdd curr : mapValue) {
            last = lastWmvMap.get(curr.getCountyId());
            if(last!=null){
                inc = sub(Double.parseDouble(curr.getAmount()),Double.parseDouble(last.getAmount()));
                if(inc.compareTo(0D)>=0){
                    //区别计算方式
                    if("ZCJJSS".equals(index)){//造成经济损失
                        color = getZCJJSSColor(curr, inc);
                    }else if("AREA".equals(index)){ //发生面积
                        incv = divide(inc, Double.parseDouble(last.getAmount()),5);
                        color = getColor(curr,incv);
                    }

                    if(RED.equals(color))
                        break;
                }
            }
        }

        return color;

        /*for(WarningMonitorVoAdd monitor : mapValue){
            for(WarningMonitorVoAdd monitorLast : mapValueLastYear){
               if (monitor.getSpeciesName().equals(monitorLast.getSpeciesName()) &&
                       monitor.getProvinceId().equals(monitorLast.getProvinceId())){
                   //若前一年数据为空，或者为0；则直接显示红色
                   if(StringUtils.isEmpty(monitorLast.getAmount())||"0".equals(monitorLast.getAmount())){
                       return RED;
                   }
                   //若比上一年数据减少
                   Double inc = sub(Double.parseDouble(monitor.getAmount()),Double.parseDouble(monitorLast.getAmount()));
                   if(inc.compareTo(0d)<0){
                       return GREEN;
                   }
                   //若是比上一年增加
                   Double incv = divide(inc, Double.parseDouble(monitorLast.getAmount()),5);
                   return getColor(monitor,incv);
               }
            }
        }

        return GREEN;*/
    }

    //通过判断给出预警颜色,造成经济损失
    private String getZCJJSSColor(WarningMonitorVoAdd monitor, Double inc) {
        //给定默认颜色值, 已超出如果没有设置，统一为红色
        String color = RED;
        Map<String,Object> params = new HashMap<>();
        params.put("speciesName", monitor.getSpeciesName());
        params.put("classificationName", monitor.getClassificationName());
        params.put("provinceId", monitor.getProvinceId());
        List<WarningThreshold> thresholdList = warningThresholdMapper.getWarningThresholdByCondition(params);
        if(thresholdList.isEmpty())
            return color;
        //阈值设置值列表
        List<ThresholdValue> thresholdValueList = thresholdValueMapper.getThresholdValueByWtId(thresholdList.get(0).getId());
        if(thresholdList.isEmpty())
            return color;
        //转换BigDecimal
        BigDecimal target = BigDecimal.valueOf(inc).setScale(5);

        for(ThresholdValue tval:thresholdValueList) {
            /*
            * 20201203调整,判断条件只剩下 <=
            * 直接判断当年和上一年的数值是否在阈值区间
            * */
            if (target.compareTo(tval.getValue1()) >= 0 && target.compareTo(tval.getValue2()) <= 0) {
                color = tval.getColor();
                break;
            }
        }
        return color;
    }

    //通过判断给出预警颜色,发生面积
    private String getColor(WarningMonitorVoAdd monitor, Double inc){
        //给定默认颜色值, 已超出如果没有设置，统一为红色
        String color = RED;
        Map<String,Object> params = new HashMap<>();
        params.put("speciesName", monitor.getSpeciesName());
        params.put("classificationName", monitor.getClassificationName());
        params.put("provinceId", monitor.getProvinceId());
        List<WarningThreshold> thresholdList = warningThresholdMapper.getWarningThresholdByCondition(params);
        if(thresholdList.isEmpty())
            return color;
        //阈值设置值列表
        List<ThresholdValue> thresholdValueList = thresholdValueMapper.getThresholdValueByWtId(thresholdList.get(0).getId());
        if(thresholdList.isEmpty())
            return color;
        //转换BigDecimal, 该值已是当前年与上一年差值，然后除以上一年值，而得出的比例
        BigDecimal target = BigDecimal.valueOf(inc).setScale(5);

        for(ThresholdValue tval:thresholdValueList){
            BigDecimal val = tval.getValue1().divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP);  //将值转化为小数
            BigDecimal val2 = tval.getValue2().divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP);  //将值转化为小数
            /*20201203调整,判断条件只剩下 <= */
            if(target.compareTo(val) >=0 && target.compareTo(val2)<=0){
                color = tval.getColor();
                break;
            }

//            String condition1 = value.getCondition1();
//            Long value1 = value.getValue1().longValue();
//            BigDecimal valTemp = new BigDecimal(value1);
//            BigDecimal val = valTemp.divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP);  //将值转化为小数
//            String condition2 = value.getCondition2();
//            Long value2 = value.getValue2().longValue();
//            BigDecimal valTemp2 = new BigDecimal(value2);
//            BigDecimal val2 = valTemp2.divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP);  //将值转化为小数
//            /*20201203调整,判断条件只剩下 <= */
//            if(target.compareTo(val) >=0 && target.compareTo(val2)<=0){
//                color = value.getColor();
//                break;
//            }
        }

        return color;
    }

    /*** 提供精确的减法运算
     * @param value1 被减数
     * @param value2 减数
     * @return 两个参数的差
     */
    private static double sub(Double value1, Double value2) {
        BigDecimal b1 = new BigDecimal(Double.toString(value1));
        BigDecimal b2 = new BigDecimal(Double.toString(value2));
        return b1.subtract(b2).doubleValue();
    }

    /*** 提供精确的加法运算
     * @param value1 数1
     * @param value2 数2
     * @return 两个参数的和
     */
    private static double add(Double value1, Double value2) {
        BigDecimal b1 = new BigDecimal(Double.toString(value1));
        BigDecimal b2 = new BigDecimal(Double.toString(value2));
        return b1.add(b2).doubleValue();
    }


    /**
    * 提供（相对）精确的除法运算。 当发生除不尽的情况时，由scale参数指定精度，以后的数字四舍五入。
    *
    * @param dividend 被除数
    * @param divisor  除数
    * @param scale    表示表示需要精确到小数点以后几位。
    * @return 两个参数的商
    */
    private static Double divide(Double dividend, Double divisor, Integer scale) {
         if (scale < 0) {
                 throw new IllegalArgumentException("The scale must be a positive integer or zero");
             }
         BigDecimal b1 = new BigDecimal(Double.toString(dividend));
         BigDecimal b2 = new BigDecimal(Double.toString(divisor));
         return b1.divide(b2, scale, RoundingMode.HALF_UP).doubleValue();
     }

    //获取新物种预警信息
    private MapViewData getNewSpeciesMapViewData(String adLevel, String adCode, Map<String, String> conditions, String roleCode){
        //获取输入的查询年份
        int recentYear = Integer.parseInt(
                conditions.get("belongYear")!=null? conditions.get("belongYear"):
                        conditions.get("createTime")==null?String.valueOf(Calendar.getInstance().get(Calendar.YEAR)):
                                conditions.get("createTime").substring(0,4));
        MapViewData outputData = new MapViewData();
        outputData.setAdAreaDataList(new HashMap<>());
        outputData.setAdLevel(adLevel);
        outputData.setViewType("point");   //描点
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
        //获取查询的年度所在的第一天与最后一天
        Date firstDay = DateUtil.getYearFirstDay(recentYear);
        Date lastDay = DateUtil.getYearLastDay(recentYear);
        //省级用户需要带出所属省级机构，只能查询当前省的信息,跟地图传入的有区别
        if("agzirdd_province".equals(roleCode)){
            //获取当前用户所属机构区划信息
            String loginUserOrganizationInfo = UserUtil.getLoginUserOrganizationInfo();
            SysOrganization orgData = JsonUtils.json2obj(loginUserOrganizationInfo, SysOrganization.class);
            //判断当前用户所在机构的类型(Y-行政机构,N-代理机构)
            if(orgData.getThirdOrg().equals("Y")) {
                String address = orgData.getAddress();
                List<String> areaList = JsonUtils.json2List(address, String.class);
                conditions.put("provinceId",areaList.get(0));
            }
        }

        //获取当前年的物种分布信息
        conditions.put("beginDate", DateUtils.format(firstDay));
        conditions.put("endDate", DateUtils.format(lastDay));
        //最新数据
        conditions.put("isNew", YseOrNoEnum.YES.getCode());
        //只取县级填报数据
        conditions.put("roleCode", RoleCodeEnum.COUNTY.getCode());

        Map<String, Object> conditionMap0 = new HashMap<>(conditions);
        //获取物种分布信息
        List<DistributionMap> distributionMaps = distributionMapMapper.selectByConditions(conditionMap0);
        //获取查询的年度上一年所在的第一天与最后一天
        Date yearFirstDay = DateUtil.getYearFirstDay(recentYear-1);
        Date yearLastDay = DateUtil.getYearLastDay(recentYear-1);
        //获取上一年的物种分布信息
        conditions.put("beginDate", DateUtils.format(yearFirstDay));
        conditions.put("endDate", DateUtils.format(yearLastDay));
        Map<String, Object> conditionMap = new HashMap<>(conditions);
        List<DistributionMap> distributionMapsLastYear = distributionMapMapper.selectByConditions(conditionMap);

        Map<String, Object> condition = new HashMap<>();
        condition.put("classificationName","新发物种");
        //若是总站用户，则需要看是否上报总站,上报就能看，否则不能看
        if("agzirdd_terminal".equals(roleCode)){
            condition.put("isSubmit","1");
        }else if("agzirdd_province".equals(roleCode)){
            condition.put("provinceId",conditions.get("provinceId"));
        }
        List<WarningThreshold> thresholdList = warningThresholdMapper.getWarningThresholdByCondition(condition);
        if(!thresholdList.isEmpty()){
            List<String> provinceIds = new ArrayList<>(thresholdList.size());
            for (WarningThreshold threshold : thresholdList){
                provinceIds.add(threshold.getProvinceId());
            }
            //筛选出阈值中有的值
            distributionMaps = distributionMaps.stream().filter(s->provinceIds.contains(s.getProvinceId())).collect(Collectors.toList());
            distributionMapsLastYear = distributionMapsLastYear.stream().filter(s->provinceIds.contains(s.getProvinceId())).collect(Collectors.toList());
        }else {
            distributionMaps.clear();
            distributionMapsLastYear.clear();
        }

        List<DistributionMapVo> mapVos = new ArrayList<>(distributionMaps.size());
        for(DistributionMap map : distributionMaps){
            DistributionMapVo mapVo = new DistributionMapVo();
            BeanUtils.copyProperties(map, mapVo);
            mapVo.setAdCode(adCode);
            mapVo.setAdLevel(adLevel);

            mapVos.add(mapVo);
        }


        /**
         *@Author Chlf
         *@Description
         * 地图当前所在级别为国家级则将数据根据省份分类
         * 地图当前所在级别为省级则将数据根据市分类
         * 地图当前所在级别为市级则将数据根据区县分类
         **/
        Map<String, List<DistributionMapVo>> distributionMap =
                AD_COUNTY.equals(adLevel)?
                        mapVos.stream().collect(Collectors.groupingBy(
                                DistributionMap::getProvinceId)):
                        AD_PROVINCE.equals(adLevel)?
                                mapVos.stream().collect(Collectors.groupingBy(
                                        DistributionMap::getCityId)):
                                mapVos.stream().collect(Collectors.groupingBy(
                                        DistributionMap::getCountyId));
        //根据区划ID（key）循环distributionMap,得到其中的list，然后再处理其中的list
        for(Map.Entry<String, List<DistributionMapVo>> entry : distributionMap.entrySet()) {
            //描点信息装配
            AdPointData adPointData = new AdPointData();
            adPointData.setChartType("columnar");             //柱状图
            adPointData.setIndexValueLevel(RED);              //新发物种默认显示红色
            adPointData.setIndexLabelList(new HashMap<>());
            //归类后挑出需要组合的数据
            Map<String,Object> indexMap = new HashMap<>();
            //String mapKey = entry.getKey();
            List<DistributionMapVo> mapValue = entry.getValue();
            indexMap.put("index", mapValue);               //重新复制，方便数据处理
            adPointData.setIndexInfo(indexMap);

            for(DistributionMapVo dismap : mapValue){
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

        //维度数据列表
        List<Map<String, Object>> dimeObjList = new ArrayList<>();
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("num", 1);
        objectMap.put("year", String.valueOf(recentYear));
        objectMap.put("dimension", distributionMaps);            //当前年度的数据

        Map<String, Object> objectMap2 = new HashMap<>();
        objectMap2.put("num", 2);
        objectMap2.put("year", String.valueOf(recentYear-1));
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


    @Override
    public List<MapConditions> getMapConditions(String index) {
        return null;
    }
}
