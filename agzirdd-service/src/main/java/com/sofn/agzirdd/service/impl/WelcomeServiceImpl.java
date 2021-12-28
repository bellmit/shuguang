package com.sofn.agzirdd.service.impl;

import com.sofn.agzirdd.enums.RegionLevelEnum;
import com.sofn.agzirdd.enums.YseOrNoEnum;
import com.sofn.agzirdd.mapper.SpeciesInvestigationMapper;
import com.sofn.agzirdd.mapper.SpeciesMonitorMapper;
import com.sofn.agzirdd.mapper.WarningMonitorMapper;
import com.sofn.agzirdd.service.WelcomeService;
import com.sofn.agzirdd.sysapi.SysRegionApi;
import com.sofn.agzirdd.vo.*;
import com.sofn.common.exception.SofnException;
import com.sofn.common.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class WelcomeServiceImpl implements WelcomeService {
    @Autowired
    WarningMonitorMapper warningMonitorMapper;
    @Autowired
    SpeciesMonitorMapper speciesMonitorMapper;
    @Autowired
    SpeciesInvestigationMapper speciesInvestigationMapper;
    @Autowired
    SysRegionApi sysRegionApi;

    @Override
    public List<WelcomeMapResVo> getMapInfo(WelcomeSearchVo vo) {
        Map<String, Object> params = new HashMap();
        params.put("speciesName",vo.getSpeciesName());
        params.put("belongYear",vo.getYear());
//        params.put("isNew", YseOrNoEnum.YES.getCode());
        //有区域查询
        if(StringUtils.hasText(vo.getAdLevel()) && StringUtils.hasText(vo.getAdCode())){
            if (RegionLevelEnum.COUNTRY.getCode().equals(vo.getAdLevel())) {
                params.put("orderByAreaIdColumn","province_id");
            } else if (RegionLevelEnum.PROVINCE.getCode().equals(vo.getAdLevel())) {
                params.put("orderByAreaIdColumn","city_id");

                params.put("whereAreaIdColumn","province_id");
                params.put("whereAreaIdVal",vo.getAdCode());
            } else if (RegionLevelEnum.CITY.getCode().equals(vo.getAdLevel())) {
                params.put("orderByAreaIdColumn","county_id");

                params.put("whereAreaIdColumn","city_id");
                params.put("whereAreaIdVal",vo.getAdCode());
            } else if (RegionLevelEnum.COUNTY.getCode().equals(vo.getAdLevel())) {
                params.put("orderByAreaIdColumn","county_id");

                params.put("whereAreaIdColumn","county_id");
                params.put("whereAreaIdVal",vo.getAdCode());
            }
        }else{
            throw new SofnException("请传入查询区域");
        }

        Map<String, WelcomeMapResVo> resMap = new HashMap();
        List<WelcomeMapVo> smList = speciesMonitorMapper.getWelcomeMapInfo(params);
        this.setResMapData(resMap,smList,true);

        List<WelcomeMapVo> siList = speciesInvestigationMapper.getWelcomeMapInfo(params);
        this.setResMapData(resMap,siList,false);

        //转换成list，并获取区域名称
        List<WelcomeMapResVo> resList = resMap.values().stream()
                .map(w -> {
                    Result<String> result = sysRegionApi.getRegionNamesByCodes(w.getAreaId());
                    if(Result.CODE.equals(result.getCode()))
                        w.setAreaName(result.getData());
                    return w;
                })
                .collect(Collectors.toList());

        return resList;
    }

    @Override
    public String getDefaultFSMJSpName(String year) {
        return speciesInvestigationMapper.getDefaultFSMJSpName(year);
    }

    @Override
    public String getDefaultZQSLSpName(String year) {
        return speciesInvestigationMapper.getDefaultZQSLSpName(year);
    }

    /**
     * 组装数据
     * @param resMap
     * @param voList
     * @param isMonitor
     */
    private void setResMapData(Map<String, WelcomeMapResVo> resMap,List<WelcomeMapVo> voList,boolean isMonitor){
        //组装数据
        WelcomeMapResVo temp = null;
        for (WelcomeMapVo w : voList) {
            temp = resMap.get(w.getAreaId());
            if (temp == null) {
                temp = new WelcomeMapResVo();
                temp.setAreaId(w.getAreaId());
            }
            temp.setData(w,isMonitor);

            resMap.put(temp.getAreaId(), temp);
        }
    }

    @Override
    public List<WelcomeTableVo> getFSMJInfo(WelcomeSearchVo vo) {
        //组装查询条件
        Map<String, Object> params = this.assemblyPrams(vo);
        params.put("classificationName", vo.getClassificationName());

        //查询
        List<WelcomeTableDBVo> ydList = warningMonitorMapper.selectListYearData(params);
        return this.assemblyResultData2(ydList,vo);
    }

    @Override
    public List<WelcomeTableVo> getDCFSMJInfo(WelcomeSearchVo vo) {
        Map<String, Object> params = this.assemblyPrams(vo);
        List<WelcomeTableDBVo> list =  speciesInvestigationMapper.selectDCFSMJInfo(params);
        return this.assemblyResultData2(list,vo);
    }

    @Override
    public List<WelcomeTableVo> getDCZQSLInfo(WelcomeSearchVo vo) {
        Map<String, Object> params = this.assemblyPrams(vo);
        List<WelcomeTableDBVo> list =  speciesInvestigationMapper.selectDCZQSLInfo(params);
        return this.assemblyResultData2(list,vo);
    }

    @Override
    public List<WelcomeTableVo> getZQSLInfo(WelcomeSearchVo vo) {
        //组装查询条件
        Map<String,Object> params = this.assemblyPrams(vo);
        List<WelcomeTableDBVo> ydList = speciesMonitorMapper.selectListYearData(params);
        //查询
        return this.assemblyResultData2(ydList,vo);
    }

    /**
     * 组装返回结果
     * @param list
     * @return
     */
    @Deprecated
    private List<WelcomeTableVo> assemblyResultData(List<WelcomeTableVo> list,WelcomeSearchVo vo){
        //转换成map
        Map<String, WelcomeTableVo> ydMap = list.stream().collect(Collectors.toMap(m -> m.getDataYear(), m -> m));

        //无数据的年份填0
        List<WelcomeTableVo> resList = new ArrayList();
        WelcomeTableVo tempWtv = null;
        Integer endYearInt = Integer.parseInt(vo.getYear());
        Integer startYearInt = endYearInt-9;
        for (; startYearInt <= endYearInt; startYearInt++) {
            tempWtv = ydMap.get(startYearInt.toString());
            if(tempWtv==null){
                tempWtv = new WelcomeTableVo();
                tempWtv.setDataYear(startYearInt.toString());
                tempWtv.setDataVal(BigDecimal.ZERO);
            }
            resList.add(tempWtv);
        }

        return resList;
    }

    /**
     * 组装返回结果
     * @param list
     * @return
     */
    private List<WelcomeTableVo> assemblyResultData2(List<WelcomeTableDBVo> list,WelcomeSearchVo vo){
        //转换成map
        Map<String, List<WelcomeTableDBVo>> yearMap = list.stream().collect(Collectors.groupingBy(WelcomeTableDBVo::getDataYear));

        //无数据的年份填0
        List<WelcomeTableVo> resList = new ArrayList<>();
        WelcomeTableVo tempWtv = null;
        Integer endYearInt = Integer.parseInt(vo.getYear());
        Integer startYearInt = endYearInt-9;
        for (; startYearInt <= endYearInt; startYearInt++) {
            String year = startYearInt.toString();

            tempWtv = new WelcomeTableVo(year);

            List<WelcomeTableDBVo> wtdList = yearMap.get(year);
            if (wtdList != null && wtdList.size()>0) {
                List<WelcomeAreaVo> areaVoList = new ArrayList();
                for (WelcomeTableDBVo wtd : wtdList) {
                    areaVoList.add(new WelcomeAreaVo(wtd.getAreaId(),wtd.getDataVal()));
                    tempWtv.setDataVal(tempWtv.getDataVal().
                            add(Objects.isNull(wtd.getDataVal())?new BigDecimal(0):wtd.getDataVal()));
                }
                tempWtv.setChilren(areaVoList);
            }
            resList.add(tempWtv);
        }

        return resList;
    }

    /**
     * 组装查询条件
     * @param vo
     * @return
     */
    private Map<String,Object> assemblyPrams(WelcomeSearchVo vo){
        String startYear = String.valueOf(Integer.parseInt(vo.getYear())-10);
        //组装查询条件
        Map<String, Object> params = new HashMap();
        params.put("startYear",startYear);
        params.put("endYear",vo.getYear());
        params.put("speciesName",vo.getSpeciesName());
        String groupCol = "";   //分组字段
        if (StringUtils.hasText(vo.getAdLevel()) && StringUtils.hasText(vo.getAdCode())) {
            if (RegionLevelEnum.COUNTRY.getCode().equals(vo.getAdLevel())) {
                //图家级，不用添加筛选条件
                groupCol = "province_id";
            } else if (RegionLevelEnum.PROVINCE.getCode().equals(vo.getAdLevel())) {
                params.put("provinceId", vo.getAdCode());
                groupCol = "city_id";
            } else if (RegionLevelEnum.CITY.getCode().equals(vo.getAdLevel())) {
                params.put("cityId", vo.getAdCode());
                groupCol = "county_id";
            } else if (RegionLevelEnum.COUNTY.getCode().equals(vo.getAdLevel())) {
                params.put("countyId", vo.getAdCode());
                groupCol = "county_id";
            }
            params.put("groupCol", groupCol);
        }

        return params;
    }
}
