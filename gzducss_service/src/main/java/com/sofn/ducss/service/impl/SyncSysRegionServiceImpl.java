package com.sofn.ducss.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sofn.ducss.enums.Constants;
import com.sofn.ducss.enums.RegionLevel;
import com.sofn.ducss.exception.SofnException;
import com.sofn.ducss.mapper.SyncSysRegionMapper;
import com.sofn.ducss.model.DucssRegionCopySys;
import com.sofn.ducss.model.SysOrganization;
import com.sofn.ducss.model.SysRegion;
import com.sofn.ducss.model.basemodel.Result;
import com.sofn.ducss.service.SyncSysRegionService;
import com.sofn.ducss.service.SysRegionService;
import com.sofn.ducss.sysapi.SysApi;
import com.sofn.ducss.vo.SysRegionTreeVo;
import com.sofn.ducss.util.SysRegionUtil;
import com.sofn.ducss.vo.AreaRegionCode;
import com.sofn.ducss.util.DateUtils;
import com.sofn.ducss.util.IdUtil;
import com.sofn.ducss.util.JsonUtils;
import com.sofn.ducss.util.UserUtil;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SyncSysRegionServiceImpl extends ServiceImpl<SyncSysRegionMapper, DucssRegionCopySys> implements SyncSysRegionService {

    @Autowired
    private SysApi sysApi;

    @Autowired
    private SysRegionService sysRegionService;

    @Autowired
    private SyncSysRegionMapper syncSysRegionMapper;

    @Override
    public String getYearByYear(String year) {
        Integer versionYear = null;
        if (year != null) {
            versionYear = Integer.valueOf(year);
        }
        return sysRegionService.getMaxVersionCodeByYear(versionYear).toString();
    }

/*    @Override
    public List<String> getChildrenRegion(String parentId, String year) {
        return syncSysRegionMapper.getChildrenRegion(parentId, year);
    }*/

    @Override
    public String getLevel(String areaId, String year) {
        AreaRegionCode regionCode = SysRegionUtil.getRegionCodeByLastCode2(areaId);
        if (regionCode != null) {
            return regionCode.getRegionLevel();
        }
        return null;
    }

/*    @Override
    public String getName(String areaId, String year) {
        return syncSysRegionMapper.getName(areaId, year);
    }*/

/*    @Override
    public List<String> getAreaId(String year, String regionYear, List<String> parentIds) {
        List<String> allAreaId = Lists.newArrayList();
        realGetAreaId(year, regionYear, parentIds, allAreaId, 0, RegionLevel.COUNTY.getCode(), false, null);
        return allAreaId;
    }*/

    @Override
    public List<String> getAreaId(String year, String regionYear, List<String> currentAreaIds, Boolean isAll) {
        // List<String> allAreaId = Lists.newArrayList();
        // realGetAreaId(year, regionYear, parentIds, allAreaId, 0, RegionLevel.COUNTY.getCode(), isAll, null);
        // ??????????????????code
        List<String> countyAreaIds = getAllCountyRegionCode(currentAreaIds, year);
        return getAllRegionCode(syncSysRegionMapper.getAreaId(year, countyAreaIds));
    }

    @Override
    public List<String> getAreaIdByStatus(String year, String regionYear, List<String> currentAreaIds, List<String> status) {
        // List<String> allAreaId = Lists.newArrayList();
        // realGetAreaId(year, regionYear, parentIds, allAreaId, 0, RegionLevel.COUNTY.getCode(), false, status);
        // ??????????????????code
        List<String> countyAreaIds = getAllCountyRegionCode(currentAreaIds, year);
        return getAllRegionCode(syncSysRegionMapper.getAreaIdByState(year, regionYear, countyAreaIds, status));
    }

/*    @Override
    public List<String> getAreaIdByStatus(String year, String regionYear, List<String> currentAreaIds, List<String> status) {
        List<String> allAreaId = Lists.newArrayList();
        realGetAreaId(year, regionYear, currentAreaIds, allAreaId, 0, RegionLevel.COUNTY.getCode(), false, status);
        return allAreaId;
    }*/

    @Override
    public void checkUserCanShow(List<String> areaIds, String year) {
        if (!CollectionUtils.isEmpty(areaIds)) {
            // 1. ?????????????????????
            String loginUserOrganizationInfo = UserUtil.getLoginUserOrganizationInfo();
            SysOrganization sysOrganization = JsonUtils.json2obj(loginUserOrganizationInfo, SysOrganization.class);
            String regionLastCode = sysOrganization.getRegionLastCode();
            if (StringUtils.isBlank(regionLastCode)) {
                throw new SofnException("???????????????????????????????????????");
            }
            String regionYear = this.getYearByYear(year);
            if ("100000".equals(regionLastCode)) {
                // ?????????
                return;
            }
            List<String> areaId = this.getAreaId(year, regionYear, Lists.newArrayList(regionLastCode), true);
            if (CollectionUtils.isEmpty(areaId)) {
                areaId = Lists.newArrayList();
            }
            areaId.add(regionLastCode);

            // ?????????????????????ID
            List<String> list = Lists.newArrayList();
            for (String id : areaIds) {
                if (!areaId.contains(id)) {
                    list.add(id);
                }
            }
            if (!CollectionUtils.isEmpty(list)) {
                log.error("???????????????{}????????????{}?????????????????????{}", regionLastCode, year, areaIds);
                throw new SofnException("???????????????????????????");
            }
        }
    }

    @Override
    public List<String> checkUserCanShow2(List<String> areaIds, String year) {
        if (!CollectionUtils.isEmpty(areaIds)) {
            // 1. ?????????????????????
            String loginUserOrganizationInfo = UserUtil.getLoginUserOrganizationInfo();
            SysOrganization sysOrganization = JsonUtils.json2obj(loginUserOrganizationInfo, SysOrganization.class);
            String regionLastCode = sysOrganization.getRegionLastCode();
            if (StringUtils.isBlank(regionLastCode)) {
                throw new SofnException("???????????????????????????????????????");
            }
            String regionYear = this.getYearByYear(year);


            List<String> areaId = this.getAreaId(year, regionYear, Lists.newArrayList(regionLastCode), true);
            if (CollectionUtils.isEmpty(areaId)) {
                areaId = Lists.newArrayList();
            }
            areaId.add(regionLastCode);

            // ?????????????????????ID
            List<String> list = Lists.newArrayList();
            for (String id : areaIds) {
                if (!areaId.contains(id)) {
                    list.add(id);
                }
            }
//            if (!CollectionUtils.isEmpty(list)) {
//                log.error("???????????????{}????????????{}?????????????????????{}",regionLastCode, year,  areaIds);
//                throw new SofnException("???????????????????????????");
//            }
            return list;
        }
        return null;
    }

    /*@Override
    public List<DucssRegionCopySys> getAreaIdByLevel(String level, String regionYear) {
        if (StringUtils.isBlank(level)) {
            throw new SofnException("????????????");
        }
        regionYear = this.getYearByYear(regionYear);
        return syncSysRegionMapper.getAreaIdByLevel(level, regionYear);
    }*/

    @Override
    public List<SysRegion> getAreaIdByParentId(String parentId) {
        return syncSysRegionMapper.getAreaIdByParentId(parentId);
    }

/*    @Override
    public Map<String, String> getNameMap(List<String> areaIds, String regionYear) {
        Map<String, String> maps = Maps.newHashMap();
        if (!CollectionUtils.isEmpty(areaIds)) {
            List<Map<String, String>> nameMap = syncSysRegionMapper.getNameMap(areaIds, regionYear);
            if (!CollectionUtils.isEmpty(nameMap)) {
                nameMap.forEach(item -> maps.put(item.get("regioncode"), item
                        .get("regionname")));
            }
        }
        return maps;
    }*/


    /**
     * ????????????????????????
     *
     * @param year       ??????
     * @param regionYear ????????????
     * @param parentIds  ???ID??????
     * @param allAreaId  ???????????????????????????
     * @param k          ??????????????????????????????10???
     * @param findLevel  ????????????
     * @param isAll      ???????????????
     */
    private void realGetAreaId(String year, String regionYear, List<String> parentIds, List<String> allAreaId, int k, String findLevel, boolean isAll, List<String> status) {
        k++;
        if (k < 10) {
            List<Map<String, String>> areaId;
            if (CollectionUtils.isEmpty(status)) {
                areaId = syncSysRegionMapper.getAreaId(year, parentIds);
            } else {
                areaId = syncSysRegionMapper.getAreaIdByState(year, regionYear, parentIds, status);
            }

            if (!CollectionUtils.isEmpty(areaId)) {
                boolean flag = false;
                parentIds.clear();
                for (Map<String, String> stringStringMap : areaId) {
                    String level = stringStringMap.get("level");
                    // ????????????????????????

                    flag = !findLevel.equals(level);
                    String tempAreaId = stringStringMap.get("regioncode");
                    if (!flag) {
                        // ???????????????????????????
                        allAreaId.add(tempAreaId);
                    } else {
                        // ????????????????????????????????????ID??? ??????????????????
                        if (isAll) {
                            allAreaId.add(tempAreaId);
                        }
                        parentIds.add(tempAreaId);
                    }

                }
                if (flag) {
                    realGetAreaId(year, regionYear, parentIds, allAreaId, k, findLevel, isAll, status);
                }
            }
        }
    }


    /**
     * ?????????????????????????????????
     *
     * @param data                   ????????????
     * @param ducssRegionCopySysList ?????????????????????
     * @param parentNames            ???????????????
     * @param parentIds              ?????????ID
     * @param level                  ?????? 1 ????????????
     * @param year                   ??????
     */
    private void getDucssRegionCopySysData(SysRegionTreeVo data, List<DucssRegionCopySys> ducssRegionCopySysList, String parentNames, String parentIds, Integer level, String year) {
        if (data != null) {
            DucssRegionCopySys ducssRegionCopySys = new DucssRegionCopySys();
            ducssRegionCopySys.setId(IdUtil.getUUId());
            ducssRegionCopySys.setRegionCode(data.getRegionCode());
            ducssRegionCopySys.setRegionName(data.getRegionName());
            ducssRegionCopySys.setCreateTime(new Date());
            ducssRegionCopySys.setYear(year);
            ducssRegionCopySys.setParentId(data.getParentId());
            ducssRegionCopySys.setParentIds(parentIds);
            ducssRegionCopySys.setParentNames(parentNames);

            String strLevel;
            switch (level) {
                case 1:
                    strLevel = RegionLevel.MINISTRY.getCode();
                    break;
                case 2:
                    strLevel = RegionLevel.PROVINCE.getCode();
                    break;
                case 3:
                    strLevel = RegionLevel.CITY.getCode();
                    break;
                case 4:
                    strLevel = RegionLevel.COUNTY.getCode();
                    break;
                default:
                    strLevel = "-";
            }

            ducssRegionCopySys.setLevel(strLevel);
            ducssRegionCopySysList.add(ducssRegionCopySys);
            List<SysRegionTreeVo> children = data.getChildren();
            if (!CollectionUtils.isEmpty(children)) {
                level = level + 1;
                for (SysRegionTreeVo child : children) {

                    String tempParentId;
                    if (StringUtils.isBlank(parentIds)) {
                        tempParentId = child.getParentId();
                    } else {
                        tempParentId = parentIds + "-" + child.getParentId();
                    }
                    getDucssRegionCopySysData(child, ducssRegionCopySysList,
                            parentNames + child.getRegionName(),
                            tempParentId, level, year);
                }
            }
        }
    }

    private List<String> getAllRegionCode(List<Map<String, String>> regionCodeMap) {
        List<String> allRegionCodes = Lists.newArrayList();
        for (Map<String, String> CodeMap : regionCodeMap) {
            allRegionCodes.add(CodeMap.get("regioncode"));
        }
        return allRegionCodes;
    }

    /**
     * ????????????????????? ?????????????????????????????????code, --??????????????????????????????
     */
    private List<String> getAllCountyRegionCode(List<String> currentAreaIds, String year) {
        List<String> countyAreaIds = Lists.newArrayList();
        if (CollectionUtils.isEmpty(currentAreaIds)) {
            return countyAreaIds;
        }
        for (String currentAreaId : currentAreaIds) {
            // ??????code??????
            AreaRegionCode currentRegionInfo = SysRegionUtil.getRegionCodeByLastCode(currentAreaId);
            String codeLevel = currentRegionInfo.getRegionLevel();
            if (StringUtils.isBlank(codeLevel)) {
                continue;
            }
            // ???????????????,????????????????????????????????????
            if (RegionLevel.COUNTY.getCode().equals(codeLevel)) {
                countyAreaIds.add(currentAreaId);
                continue;
            }
            // ????????????code??????code??????
            List<SysRegionTreeVo> regionList = sysApi.getListByParentId(currentAreaId, Constants.APPID, "");
            if (CollectionUtils.isNotEmpty(regionList)) {
                // ???????????????????????????Code ?????????list???
                if (RegionLevel.CITY.getCode().equals(codeLevel)) {
                    Set<String> countyChildren = regionList.stream().map(SysRegionTreeVo::getRegionCode).collect(Collectors.toSet());
                    // List<String> finalAreaIds = getAllRegionCode(syncSysRegionMapper.getAreaId(year, new ArrayList<>(countyChildren)));
                    countyAreaIds.addAll(countyChildren);
                    continue;
                }
                // ????????????????????????????????????????????????????????????
                if (RegionLevel.PROVINCE.getCode().equals(codeLevel)) {
                    // ????????????????????????????????????
                    Set<String> cityAreaIds = regionList.stream().map(SysRegionTreeVo::getRegionCode).collect(Collectors.toSet());
                    List<String> finalCityAreaIds = getAllRegionCode(syncSysRegionMapper.getAreaId(year, new ArrayList<>(cityAreaIds)));
                    for (String finalCityAreaId : finalCityAreaIds) {
                        // ?????????????????????
                        List<SysRegionTreeVo> countyRegionList = sysApi.getListByParentId(finalCityAreaId, Constants.APPID, "");
                        if (CollectionUtils.isNotEmpty(countyRegionList)) {
                            Set<String> countyChildren = countyRegionList.stream().map(SysRegionTreeVo::getRegionCode).collect(Collectors.toSet());
                            //List<String> finalAreaIds = getAllRegionCode(syncSysRegionMapper.getAreaId(year, new ArrayList<>(countyChildren)));
                            countyAreaIds.addAll(countyChildren);
                        }
                    }
                }
                // ????????????????????????????????? + ??????????????????
                if (RegionLevel.MINISTRY.getCode().equals(codeLevel)) {
                    // ??????????????? + ??????????????????
                    Set<String> provinceAreaIds = regionList.stream().map(SysRegionTreeVo::getRegionCode).collect(Collectors.toSet());
                    List<String> finalProvinceAreaIds = getAllRegionCode(syncSysRegionMapper.getAreaId(year, new ArrayList<>(provinceAreaIds)));
                    List<SysRegionTreeVo> allCityAreaIds = Lists.newArrayList();
                    // ???????????????
                    SysRegionTreeVo data = sysApi.getSysRegionTreeByYear(null, null, null, null, null, Constants.APPID, null, null);
                    if (data != null) {
                        List<SysRegionTreeVo> allProvinceIds = data.getChildren();
                        for (SysRegionTreeVo provinceId : allProvinceIds) {
                            if (finalProvinceAreaIds.contains(provinceId.getRegionCode())) {
                                allCityAreaIds.addAll(provinceId.getChildren());
                            }
                        }
                    } else {
                        throw new SofnException("???????????????????????????");
                    }

                    // ??????????????? + ??????????????????
                    Map<String, SysRegionTreeVo> cityMap = allCityAreaIds.stream().collect(Collectors.toMap(SysRegionTreeVo::getRegionCode, Function.identity()));
                    List<String> finalCityAreaIds = getAllRegionCode(syncSysRegionMapper.getAreaId(year, new ArrayList<>(cityMap.keySet())));
                    for (String finalCityAreaId : finalCityAreaIds) {
                        SysRegionTreeVo sysRegionTreeVo = cityMap.get(finalCityAreaId);
                        if (sysRegionTreeVo != null) {
                            List<SysRegionTreeVo> countyList = sysRegionTreeVo.getChildren();
                            if (CollectionUtils.isNotEmpty(countyList)) {
                                List<String> countyIds = countyList.stream().map(SysRegionTreeVo::getRegionCode).collect(Collectors.toList());
                                countyAreaIds.addAll(countyIds);
                            }
                        }
                    }
                   /* for (String finalCityAreaId : finalProvinceAreaIds) {
                        // ?????????????????????
                        Result<List<SysRegionTreeVo>> cityRegionResult = sysApi.getListByParentId(finalCityAreaId, Constants.APPID, "");
                        if (cityRegionResult != null && CollectionUtils.isNotEmpty(cityRegionResult.getData())) {
                            List<SysRegionTreeVo> cityRegionList = cityRegionResult.getData();
                            Set<String> cityChildren = cityRegionList.stream().map(SysRegionTreeVo::getRegionCode).collect(Collectors.toSet());
                            allCityAreaIds.addAll(cityChildren);
                        }
                    }

                    // ??????????????? + ??????????????????
                    List<String> finalCityChildrens = getAllRegionCode(syncSysRegionMapper.getAreaId(year, allCityAreaIds));
                    for (String finalCityChildren : finalCityChildrens) {
                        // ?????????????????????
                        Result<List<SysRegionTreeVo>> countyRegionResult = sysApi.getListByParentId(finalCityChildren, Constants.APPID, "");
                        if (countyRegionResult != null && CollectionUtils.isNotEmpty(countyRegionResult.getData())) {
                            List<SysRegionTreeVo> countyRegionList = countyRegionResult.getData();
                            Set<String> countyChildren = countyRegionList.stream().map(SysRegionTreeVo::getRegionCode).collect(Collectors.toSet());
                            countyAreaIds.addAll(countyChildren);
                        }
                    }*/
                }
            }
        }
        return countyAreaIds;
    }
}
