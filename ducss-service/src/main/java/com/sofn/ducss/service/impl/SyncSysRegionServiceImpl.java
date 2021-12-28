package com.sofn.ducss.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sofn.common.exception.SofnException;
import com.sofn.common.model.Result;
import com.sofn.common.utils.DateUtils;
import com.sofn.common.utils.IdUtil;
import com.sofn.common.utils.JsonUtils;
import com.sofn.common.utils.UserUtil;
import com.sofn.ducss.enums.Constants;
import com.sofn.ducss.enums.RegionLevel;
import com.sofn.ducss.mapper.SyncSysRegionMapper;
import com.sofn.ducss.model.DucssRegionCopySys;
import com.sofn.ducss.service.SyncSysRegionService;
import com.sofn.ducss.sysapi.SysApi;
import com.sofn.ducss.sysapi.bean.SysOrganization;
import com.sofn.ducss.sysapi.bean.SysRegionForm;
import com.sofn.ducss.sysapi.bean.SysRegionTreeVo;
import com.sofn.ducss.util.SysRegionUtil;
import com.sofn.ducss.util.vo.AreaRegionCode;
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
    private SyncSysRegionMapper syncSysRegionMapper;

    /*@Override
    @Transactional(rollbackFor = Exception.class)
    public void syncSysRegion(String year) {
        if (StringUtils.isBlank(year)) {
            throw new SofnException("年度必传");
        }

        // 1. 拉取支撑平台维护的整棵树
        Result<SysRegionTreeVo> treeVoResult = sysApi.getSysRegionTreeByYear(null, null, null, null, null, Constants.APPID, Integer.parseInt(year), null);
        SysRegionTreeVo data = null;
        if (treeVoResult != null) {
            if (Result.CODE.equals(treeVoResult.getCode())) {
                data = treeVoResult.getData();
            } else {
                log.error("年度【{}】区划数据同步失败", year);
            }
        }
        List<DucssRegionCopySys> ducssRegionCopySys = Lists.newArrayList();
        // 2. 构造数据
        if (data != null) {
            getDucssRegionCopySysData(data, ducssRegionCopySys, "", "", 1, year);
        }
        if (!CollectionUtils.isEmpty(ducssRegionCopySys)) {
            // 3. 删除当前年度的数据
            Integer yearCount = syncSysRegionMapper.selectCount(new QueryWrapper<DucssRegionCopySys>().eq("year", year));
            syncSysRegionMapper.delete(new UpdateWrapper<DucssRegionCopySys>().eq("year", year));
            log.info("成功删除{}年的区划数据，数量：{}", year, yearCount);
            // 4. 添加当前年度的数据
            this.saveBatch(ducssRegionCopySys, 1000);
            log.info("成功同步{}年的行政区划数据。。。", year);
        }
    }*/

/*    @Override
    public String getYearByYear(String year) {
        if (StringUtils.isBlank(year)) {
            year = DateUtils.format(new Date(), "yyyy");
        }
        Integer yearDataNumber = syncSysRegionMapper.getYearDataNumber(year);
        if (yearDataNumber == 0) {
            year = syncSysRegionMapper.getMaxYear().toString();
        }
        return year;
    }*/

/*    @Override
    public List<String> getChildrenRegion(String parentId, String year) {
        return syncSysRegionMapper.getChildrenRegion(parentId, year);
    }*/

/*    @Override
    public String getLevel(String areaId, String year) {
        return syncSysRegionMapper.getLevel(areaId, year);
    }*/

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
        // 获取所有县级code
        List<String> countyAreaIds = getAllCountyRegionCode(currentAreaIds, year);
        if (CollectionUtils.isEmpty(countyAreaIds)) {
            return new ArrayList<>();
        }
        return syncSysRegionMapper.getAreaId(year, countyAreaIds);
    }

    @Override
    public List<String> getAreaIdByStatus(String year, List<String> currentAreaIds, List<String> status) {
        // List<String> allAreaId = Lists.newArrayList();
        // realGetAreaId(year, regionYear, parentIds, allAreaId, 0, RegionLevel.COUNTY.getCode(), false, status);
        // 获取所有县级code
        List<String> countyAreaIds = getAllCountyRegionCode(currentAreaIds, year);
        if (CollectionUtils.isEmpty(countyAreaIds)) {
            return new ArrayList<>();
        }
        log.info("查询树之后得到的areaIds：" + countyAreaIds.toString().replaceAll("(?:\\[|null|\\]| +)", ""));
        List<String> result = syncSysRegionMapper.getAreaIdByState(year, countyAreaIds, status);
        log.info("最后得到的areaIds：" + result.toString().replaceAll("(?:\\[|null|\\]| +)", ""));
        return result;
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
            // 1. 获取用户的区划
            String loginUserOrganizationInfo = UserUtil.getLoginUserOrganizationInfo();
            SysOrganization sysOrganization = JsonUtils.json2obj(loginUserOrganizationInfo, SysOrganization.class);
            String regionLastCode = sysOrganization.getRegionLastCode();
            if (StringUtils.isBlank(regionLastCode)) {
                throw new SofnException("无法找到当前用户的所属区划");
            }
            // String regionYear = this.getYearByYear(year);
            String regionYear = SysRegionUtil.getYearByYear(year);
            if ("100000".equals(regionLastCode)) {
                // 都能看
                return;
            }
            List<String> areaId = this.getAreaId(year, regionYear, Lists.newArrayList(regionLastCode), true);
            if (CollectionUtils.isEmpty(areaId)) {
                areaId = Lists.newArrayList();
            }
            areaId.add(regionLastCode);

            // 找出没有的区划ID
            List<String> list = Lists.newArrayList();
            for (String id : areaIds) {
                if (!areaId.contains(id)) {
                    list.add(id);
                }
            }
            if (!CollectionUtils.isEmpty(list)) {
                log.error("用户区划：{}，年度：{}，传入的区划：{}", regionLastCode, year, areaIds);
                throw new SofnException("该区域暂无填报数据");
            }
        }
    }

    @Override
    public List<String> checkUserCanShow2(List<String> areaIds, String year) {
        if (!CollectionUtils.isEmpty(areaIds)) {
            // 1. 获取用户的区划
            String loginUserOrganizationInfo = UserUtil.getLoginUserOrganizationInfo();
            SysOrganization sysOrganization = JsonUtils.json2obj(loginUserOrganizationInfo, SysOrganization.class);
            String regionLastCode = sysOrganization.getRegionLastCode();
            if (StringUtils.isBlank(regionLastCode)) {
                throw new SofnException("无法找到当前用户的所属区划");
            }
            // String regionYear = this.getYearByYear(year);
            String regionYear = SysRegionUtil.getYearByYear(year);


            List<String> areaId = this.getAreaId(year, regionYear, Lists.newArrayList(regionLastCode), true);
            if (CollectionUtils.isEmpty(areaId)) {
                areaId = Lists.newArrayList();
            }
            areaId.add(regionLastCode);

            // 找出没有的区划ID
            List<String> list = Lists.newArrayList();
            for (String id : areaIds) {
                if (!areaId.contains(id)) {
                    list.add(id);
                }
            }
//            if (!CollectionUtils.isEmpty(list)) {
//                log.error("用户区划：{}，年度：{}，传入的区划：{}",regionLastCode, year,  areaIds);
//                throw new SofnException("该区域暂无填报数据");
//            }
            return list;
        }
        return null;
    }

   /* @Override
    public List<DucssRegionCopySys> getAreaIdByLevel(String level, String regionYear) {
        if (StringUtils.isBlank(level)) {
            throw new SofnException("级别必传");
        }
        regionYear = this.getYearByYear(regionYear);
        return syncSysRegionMapper.getAreaIdByLevel(level, regionYear);
    }*/

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
     * 获取所有的下一级
     *
     * @param year       年度
     * @param regionYear 区划年度
     * @param parentIds  父ID集合
     * @param allAreaId  存储所有的最终区划
     * @param k          做递归检测，最多查询10次
     * @param findLevel  查找级别
     * @param isAll      是否找所有
     */
    /*private void realGetAreaId(String year, String regionYear, List<String> parentIds, List<String> allAreaId, int k, String findLevel, boolean isAll, List<String> status) {
        k++;
        if (k < 10) {
            List<Map<String, String>> areaId;
            if (CollectionUtils.isEmpty(status)) {
                areaId = syncSysRegionMapper.getAreaId(year, parentIds);
            } else {
                areaId = syncSysRegionMapper.getAreaIdByState(year, parentIds, status);
            }

            if (!CollectionUtils.isEmpty(areaId)) {
                boolean flag = false;
                parentIds.clear();
                for (Map<String, String> stringStringMap : areaId) {
                    String level = stringStringMap.get("level");
                    // 到县级就停止计算

                    flag = !findLevel.equals(level);
                    String tempAreaId = stringStringMap.get("regioncode");
                    if (!flag) {
                        // 如果为县级就添加到
                        allAreaId.add(tempAreaId);
                    } else {
                        // 如果不为县级就将他作为父ID， 直至找到县级
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
    }*/


    /**
     * 将树结构转换为列表数据
     *
     * @param data                   区划数据
     * @param ducssRegionCopySysList 存储列表数据的
     * @param parentNames            父节点名称
     * @param parentIds              父节点ID
     * @param level                  级别 1 表示部级
     * @param year                   年度
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
     * 暂时使用该方法 获取查询区域下所有县级code, --支撑平台那边没给接口
     */
    private List<String> getAllCountyRegionCode(List<String> currentAreaIds, String year) {
        List<String> countyAreaIds = Lists.newArrayList();
        if (CollectionUtils.isEmpty(currentAreaIds)) {
            return countyAreaIds;
        }
        for (String currentAreaId : currentAreaIds) {
            // 当前code级别
            AreaRegionCode currentRegionInfo = SysRegionUtil.getRegionCodeByLastCode(currentAreaId, year);
            String codeLevel = currentRegionInfo.getRegionLevel();
            if (StringUtils.isBlank(codeLevel)) {
                continue;
            }
            // 如果是县级,不需要查询下级，直接返回
            if (RegionLevel.COUNTY.getCode().equals(codeLevel)) {
                countyAreaIds.add(currentAreaId);
                continue;
            }
            List<String> currentAreaIdList = Lists.newArrayList(currentAreaIds);
            log.info("查树开始--------------------------------");
            Result<SysRegionTreeVo> treeVoResult = sysApi.getSysRegionTreeByYear(null, null, null, null, null, Constants.APPID, Integer.valueOf(year), null);
            log.info("查树结束--------------------------------");
            // 市级
            if (RegionLevel.CITY.getCode().equals(codeLevel)) {
                List<String> areaIds = SysRegionUtil.listChildrenIdsByParentIdsAndLevel(treeVoResult, currentAreaIdList, codeLevel);
                countyAreaIds.addAll(areaIds);
            }
            // 省级
            if (RegionLevel.PROVINCE.getCode().equals(codeLevel)) {
                List<String> cityIds = SysRegionUtil.listChildrenIdsByParentIdsAndLevel(treeVoResult, currentAreaIdList, codeLevel);
                // 查询已上报和已通过的市级
                List<String> finalCityAreaIds = syncSysRegionMapper.getAreaId(year, new ArrayList<>(cityIds));
                List<String> areaIds = SysRegionUtil.listChildrenIdsByParentIdsAndLevel(treeVoResult, finalCityAreaIds, RegionLevel.CITY.getCode());
                countyAreaIds.addAll(areaIds);
            }
            // 部级
            if (RegionLevel.MINISTRY.getCode().equals(codeLevel)) {
                List<String> provinceIds = SysRegionUtil.listChildrenIdsByParentIdsAndLevel(treeVoResult, currentAreaIdList, codeLevel);
                // 查出已上报 + 已通过的省级
                List<String> finalProvinceAreaIds = syncSysRegionMapper.getAreaId(year, provinceIds);
                List<String> cityIds = SysRegionUtil.listChildrenIdsByParentIdsAndLevel(treeVoResult, finalProvinceAreaIds, RegionLevel.PROVINCE.getCode());
                // 查出已上报 + 已通过的市级
                List<String> finalCityIds = syncSysRegionMapper.getAreaId(year, cityIds);
                List<String> areaIds = SysRegionUtil.listChildrenIdsByParentIdsAndLevel(treeVoResult, finalCityIds, RegionLevel.CITY.getCode());
                countyAreaIds.addAll(areaIds);
            }
        }
        return countyAreaIds;
    }

    /*
    private List<String> getAllCountyRegionCodeBAK(List<String> currentAreaIds, String year) {
        List<String> countyAreaIds = Lists.newArrayList();
        if (CollectionUtils.isEmpty(currentAreaIds)) {
            return countyAreaIds;
        }
        for (String currentAreaId : currentAreaIds) {
            // 当前code级别
            AreaRegionCode currentRegionInfo = SysRegionUtil.getRegionCodeByLastCode(currentAreaId);
            String codeLevel = currentRegionInfo.getRegionLevel();
            if (StringUtils.isBlank(codeLevel)) {
                continue;
            }
            // 如果是县级,不需要查询下级，直接返回
            if (RegionLevel.COUNTY.getCode().equals(codeLevel)) {
                countyAreaIds.add(currentAreaId);
                continue;
            }
            // 查询当前code下的code集合
            Result<List<SysRegionTreeVo>> regionResult = sysApi.getListByParentId(currentAreaId, Constants.APPID, "");
            if (regionResult != null && CollectionUtils.isNotEmpty(regionResult.getData())) {
                List<SysRegionTreeVo> regionList = regionResult.getData();
                // 如果是市级，把下级Code 添加到list里
                if (RegionLevel.CITY.getCode().equals(codeLevel)) {
                    Set<String> countyChildren = regionList.stream().map(SysRegionTreeVo::getRegionCode).collect(Collectors.toSet());
                    // List<String> finalAreaIds = getAllRegionCode(syncSysRegionMapper.getAreaId(year, new ArrayList<>(countyChildren)));
                    countyAreaIds.addAll(countyChildren);
                    continue;
                }
                // 如果是省级，需要再循环查询下面市级的县级
                if (RegionLevel.PROVINCE.getCode().equals(codeLevel)) {
                    // 查询已上报和已通过的市级
                    Set<String> cityAreaIds = regionList.stream().map(SysRegionTreeVo::getRegionCode).collect(Collectors.toSet());
                    List<String> finalCityAreaIds = syncSysRegionMapper.getAreaId(year, new ArrayList<>(cityAreaIds));
                    for (String finalCityAreaId : finalCityAreaIds) {
                        // 查询市下面的县
                        Result<List<SysRegionTreeVo>> countyRegionResult = sysApi.getListByParentId(finalCityAreaId, Constants.APPID, "");
                        if (countyRegionResult != null && CollectionUtils.isNotEmpty(countyRegionResult.getData())) {
                            List<SysRegionTreeVo> countyRegionList = countyRegionResult.getData();
                            Set<String> countyChildren = countyRegionList.stream().map(SysRegionTreeVo::getRegionCode).collect(Collectors.toSet());
                            //List<String> finalAreaIds = getAllRegionCode(syncSysRegionMapper.getAreaId(year, new ArrayList<>(countyChildren)));
                            countyAreaIds.addAll(countyChildren);
                        }
                    }
                }
                // 如果是部级，查出已上报 + 已通过的县级
                if (RegionLevel.MINISTRY.getCode().equals(codeLevel)) {
                    // 查出已上报 + 已通过的省级
                    Set<String> provinceAreaIds = regionList.stream().map(SysRegionTreeVo::getRegionCode).collect(Collectors.toSet());
                    List<String> finalProvinceAreaIds = syncSysRegionMapper.getAreaId(year, new ArrayList<>(provinceAreaIds));
                    List<SysRegionTreeVo> allCityAreaIds = Lists.newArrayList();
                    // 查询整棵树
                    Result<SysRegionTreeVo> treeVoResult = sysApi.getSysRegionTreeByYear(null, null, null, null, null, Constants.APPID, null, null);
                    SysRegionTreeVo data = treeVoResult.getData();
                    if (data != null) {
                        List<SysRegionTreeVo> allProvinceIds = data.getChildren();
                        for (SysRegionTreeVo provinceId : allProvinceIds) {
                            if (finalProvinceAreaIds.contains(provinceId.getRegionCode())) {
                                allCityAreaIds.addAll(provinceId.getChildren());
                            }
                        }
                    } else {
                        throw new SofnException("行政区划获取失败！");
                    }

                    // 查询已上报 + 已通过的市级
                    Map<String, SysRegionTreeVo> cityMap = allCityAreaIds.stream().collect(Collectors.toMap(SysRegionTreeVo::getRegionCode, Function.identity()));
                    List<String> finalCityAreaIds = syncSysRegionMapper.getAreaId(year, new ArrayList<>(cityMap.keySet()));
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
                }
            }
        }
        return countyAreaIds;
    }
    */
}
