package com.sofn.ducss.service.impl;

import com.google.common.collect.Lists;
import com.sofn.common.model.Result;
import com.sofn.common.utils.JsonUtils;
import com.sofn.common.utils.UserUtil;
import com.sofn.ducss.enums.Constants;
import com.sofn.ducss.enums.RegionLevel;
import com.sofn.ducss.mapper.CollectFlowMapper;
import com.sofn.ducss.mapper.DateShowMapper;
import com.sofn.ducss.mapper.DisperseUtilizeMapper;
import com.sofn.ducss.mapper.StrawUtilizeMapper;
import com.sofn.ducss.service.HomePageService;
import com.sofn.ducss.service.SyncSysRegionService;
import com.sofn.ducss.sysapi.SysApi;
import com.sofn.ducss.sysapi.bean.SysOrganization;
import com.sofn.ducss.sysapi.bean.SysRegionTreeVo;
import com.sofn.ducss.util.SysRegionUtil;
import com.sofn.ducss.vo.homePage.DataArea;
import com.sofn.ducss.vo.homePage.ReportProgressVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 首页数据实现类
 *
 * @author jiangtao
 * @date 2020/10/29
 */

@Service
public class HomePageServiceImpl implements HomePageService {

    @Autowired
    private CollectFlowMapper collectFlowMapper;

    @Autowired
    private SysApi sysApi;

    @Autowired
    private StrawUtilizeMapper strawUtilizeMapper;

    @Autowired
    private DisperseUtilizeMapper disperseUtilizeMapper;

    @Autowired
    private DateShowMapper dateShowMapper;

    @Autowired
    private SyncSysRegionService syncSysRegionService;

    @Override
    public DataArea getDataArea(String year, String areaCode, String administrativeLevel, String type) {
        //获取当前登录用户等级
        String organizationInfo = UserUtil.getLoginUserOrganizationInfo();
        SysOrganization orgData = JsonUtils.json2obj(organizationInfo, SysOrganization.class);
        DataArea dataArea = new DataArea();
        //符合条件的县ids
        List<String> list = new ArrayList<>();
        if (type == null) {
            //获取填报县数
            if (areaCode == null) {
                //获取符合条件的县ids
                // list = this.getReportAndAuditCounty(orgData.getRegionLastCode(), orgData.getOrganizationLevel(), year);
                list = syncSysRegionService.getAreaIdByStatus(year, Lists.newArrayList(orgData.getRegionLastCode()),
                        Lists.newArrayList("1", "5"));
            } else {
                list = this.getReportAndAuditCounty(areaCode, administrativeLevel, year);
            }
        } else {
            //获取填报县数
            if (areaCode == null) {
                //获取符合条件的县ids
                list = this.getReportAndAuditCountyForDataShow(orgData.getRegionLastCode(), orgData.getOrganizationLevel(), year);
            } else {
                list = this.getReportAndAuditCountyForDataShow(areaCode, administrativeLevel, year);
            }
        }
        //查询所有满足条件县所对应市场化主体
        HashMap<String, Object> utilizeMap = new HashMap<>(12);
        utilizeMap.put("year", year);
        if (list.size() > 0) {
            dataArea.setReportCounty(String.valueOf(list.size()));
            utilizeMap.put("areaIds", list);
        } else {
            dataArea.setReportCounty("0");
            list.add(areaCode);
            utilizeMap.put("areaIds", list);
        }
        Integer utilizeNum = strawUtilizeMapper.getStrawUtilizeCountByCondition(utilizeMap);
        //查询所有满足条件县所对应抽样分散户
        Integer disperseNum = disperseUtilizeMapper.getDisperseUtilizeByIdList(utilizeMap);
        dataArea.setStrawUtilize(String.valueOf(utilizeNum));
        dataArea.setDisperseUtilize(String.valueOf(disperseNum));
        return dataArea;
    }

    public List<String> getReportAndAuditCounty(String areaCode, String orgLevel, String year) {
        HashMap<String, Object> map = new HashMap<>(12);
        //根据areacode查询上报县数
        List<String> countyList = new ArrayList<>();
        //状态list
        List<Byte> list = new ArrayList<>();
        list.add(Constants.ExamineState.READ);
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
            List<String> idList = SysRegionUtil.getChildrenRegionIdByYearList(areaCode, year);
            map.put("areaIds", idList);
            return collectFlowMapper.getCountyByCondition(map);
        } else if (orgLevel.equals(RegionLevel.PROVINCE.getCode())) {
            Result<SysRegionTreeVo> treeVoResult = sysApi.getSysRegionTree(null, null, null, null, null, Constants.APPID, null);
            if (treeVoResult != null && treeVoResult.getData() != null) {
                SysRegionTreeVo Root = treeVoResult.getData();
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
            Result<SysRegionTreeVo> treeVoResult = sysApi.getSysRegionTree(null, null, null, null, null, Constants.APPID, null);
            if (treeVoResult != null && treeVoResult.getData() != null) {
                SysRegionTreeVo Root = treeVoResult.getData();
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

    public List<String> getReportAndAuditCountyForDataShow(String areaCode, String orgLevel, String year) {
        HashMap<String, Object> map = new HashMap<>(12);
        //根据areacode查询上报县数
        List<String> countyList = new ArrayList<>();
        //状态list
        List<Byte> list = new ArrayList<>();
        list.add(Constants.ExamineState.READ);
        list.add(Constants.ExamineState.REPORTED);
        list.add(Constants.ExamineState.PASSED);
        map.put("statues", list);
        map.put("year", year);
        String regionYear = SysRegionUtil.getYearByYear(year);
        // String regionYear = syncSysRegionService.getYearByYear(year);
        //县级
        if (orgLevel.equals(RegionLevel.COUNTY.getCode())) {
            //查询符合条件的县级id
            List<String> areaIds = new ArrayList<>();
            areaIds.add(areaCode);
            map.put("areaIds", areaIds);
            return collectFlowMapper.getCountyByCondition(map);
        } else if (orgLevel.equals(RegionLevel.CITY.getCode())) {
            //市级
            /*//查询当前登录用户的下级所有县id
            List<String> idList = SysRegionUtil.getChildrenRegionIdList(areaCode);*/
            List<String> arrayList = new ArrayList<>();
            arrayList.add(areaCode);
            countyList = syncSysRegionService.getAreaId(year, regionYear, arrayList, false);
            if (countyList.size() == 0) {
                //当没有数据时，默认传入一个条件使得筛选条件有效
                countyList.add("xxxxxx");
            }
            map.put("areaIds", countyList);
            return collectFlowMapper.getCountyByCondition(map);
        } else if (orgLevel.equals(RegionLevel.PROVINCE.getCode())) {
            List<String> arrayList = new ArrayList<>();
            arrayList.add(areaCode);
            countyList = syncSysRegionService.getAreaId(year, regionYear, arrayList, false);
            if (countyList.size() == 0) {
                countyList.add("xxxxxx");
            }
            map.put("areaIds", countyList);
            return collectFlowMapper.getCountyByCondition(map);
        } else if (orgLevel.equals(RegionLevel.MINISTRY.getCode())) {
            List<String> arrayList = new ArrayList<>();
            arrayList.add(areaCode);
            countyList = syncSysRegionService.getAreaId(year, regionYear, arrayList, false);
            if (countyList.size() == 0) {
                countyList.add("xxxxxx");
            }
            map.put("areaIds", countyList);
            return collectFlowMapper.getCountyByCondition(map);
        }
        return new ArrayList<>();
    }

    @Override
    public List<ReportProgressVo> getReportProgress(String year, String administrativeLevel) {
        String organizationInfo = UserUtil.getLoginUserOrganizationInfo();
        SysOrganization orgData = JsonUtils.json2obj(organizationInfo, SysOrganization.class);
        List<ReportProgressVo> progressVoList = new ArrayList<>();
        //县级
        if (orgData.getOrganizationLevel().equals(RegionLevel.COUNTY.getCode())) {
            return null;
        }
        //市级
        if (orgData.getOrganizationLevel().equals(RegionLevel.CITY.getCode())) {
            ReportProgressVo reportProgressVo = new ReportProgressVo();
            reportProgressVo.setAreaLevel(RegionLevel.COUNTY.getCode());
            getReportProgressVo(year, administrativeLevel, orgData, reportProgressVo);
            progressVoList.add(reportProgressVo);
        }
        //省级
        if (orgData.getOrganizationLevel().equals(RegionLevel.PROVINCE.getCode())) {
            ReportProgressVo cityReportProgress = new ReportProgressVo();
            ReportProgressVo countyReportProgressVo = new ReportProgressVo();
            //获取市级数据
            cityReportProgress.setAreaLevel(RegionLevel.CITY.getCode());
            getReportProgressVo(year, administrativeLevel, orgData, cityReportProgress);
            progressVoList.add(cityReportProgress);
            //获取县级数据
            List<String> countyList = new ArrayList<>();
            //查询该省下所有县级数据
            Result<SysRegionTreeVo> treeVoResult = sysApi.getSysRegionTree(null, null, null, null, null, Constants.APPID, null);
            if (treeVoResult != null && treeVoResult.getData() != null) {
                SysRegionTreeVo Root = treeVoResult.getData();
                for (SysRegionTreeVo provinceTree : Root.getChildren()) {
                    if (provinceTree.getRegionCode().equals(orgData.getRegionLastCode())) {
                        for (SysRegionTreeVo cityTree : provinceTree.getChildren()) {
                            for (SysRegionTreeVo countyTree : cityTree.getChildren()) {
                                String countyId = countyTree.getRegionCode();
                                countyList.add(countyId);
                            }
                        }
                    }
                }
            }
            HashMap<String, Object> map = new HashMap<>();
            //查询审核通过的县
            //状态list
            List<Byte> list = new ArrayList<>();
            list.add(Constants.ExamineState.PASSED);
            list.add(Constants.ExamineState.PUBLISH);
            map.put("statues", list);
            map.put("year", year);
            map.put("areaIds", countyList);
            List<String> countyPassNum = collectFlowMapper.getCountyByCondition(map);
            countyReportProgressVo.setAuditCounty(Integer.toString(countyPassNum.size()));
            //查询上报成功的县
            list.clear();
            list.add(Constants.ExamineState.REPORTED);
            list.add(Constants.ExamineState.READ);
            List<String> countyReportNum = collectFlowMapper.getCountyByCondition(map);
            countyReportProgressVo.setReportCounty(Integer.toString(countyReportNum.size()));
            countyReportProgressVo.setNoReportCounty(String.valueOf(countyList.size() - countyPassNum.size() - countyReportNum.size()));
            countyReportProgressVo.setAreaLevel(RegionLevel.COUNTY.getCode());
            progressVoList.add(countyReportProgressVo);
        }
        //部级
        if (orgData.getOrganizationLevel().equals(RegionLevel.MINISTRY.getCode())) {
            ReportProgressVo provinceReportProgress = new ReportProgressVo();
            ReportProgressVo cityReportProgress = new ReportProgressVo();
            ReportProgressVo countyReportProgressVo = new ReportProgressVo();
            provinceReportProgress.setAreaLevel(RegionLevel.PROVINCE.getCode());
            //获取省级数据
            getReportProgressVo(year, administrativeLevel, orgData, provinceReportProgress);
            progressVoList.add(provinceReportProgress);
            //获取市级数据
            List<String> cityList = new ArrayList<>();
            List<String> countyList = new ArrayList<>();
            Result<SysRegionTreeVo> treeVoResult = sysApi.getSysRegionTree(null, null, null, null, null, Constants.APPID, null);
            if (treeVoResult != null && treeVoResult.getData() != null) {
                SysRegionTreeVo Root = treeVoResult.getData();
                for (SysRegionTreeVo provinceTree : Root.getChildren()) {
                    for (SysRegionTreeVo cityTree : provinceTree.getChildren()) {
                        String cityId = cityTree.getRegionCode();
                        cityList.add(cityId);
                        for (SysRegionTreeVo countyTree : cityTree.getChildren()) {
                            String countyId = countyTree.getRegionCode();
                            countyList.add(countyId);
                        }
                    }
                }
            }
            HashMap<String, Object> map = new HashMap<>();
            //查询审核通过的市
            //状态list
            List<Byte> list = new ArrayList<>();
            list.add(Constants.ExamineState.PASSED);
            list.add(Constants.ExamineState.PUBLISH);
            map.put("statues", list);
            map.put("year", year);
            map.put("areaIds", cityList);
            List<String> cityPassNum = collectFlowMapper.getCountyByCondition(map);
            cityReportProgress.setAuditCounty(Integer.toString(cityPassNum.size()));
            //查询上报成功的市
            list.clear();
            list.add(Constants.ExamineState.REPORTED);
            list.add(Constants.ExamineState.READ);
            List<String> cityReportNum = collectFlowMapper.getCountyByCondition(map);
            cityReportProgress.setReportCounty(Integer.toString(cityReportNum.size()));
            cityReportProgress.setNoReportCounty(String.valueOf(cityList.size() - cityPassNum.size() - cityReportNum.size()));
            cityReportProgress.setAreaLevel(RegionLevel.CITY.getCode());
            progressVoList.add(cityReportProgress);
            //获取县级数据
            //查询审核通过的县
            //状态list
            list.clear();
            list.add(Constants.ExamineState.PASSED);
            list.add(Constants.ExamineState.PUBLISH);
            map.put("statues", list);
            map.put("year", year);
            map.put("areaIds", countyList);
            List<String> countyPassNum = collectFlowMapper.getCountyByCondition(map);
            countyReportProgressVo.setAuditCounty(Integer.toString(countyPassNum.size()));
            //查询上报成功的县
            list.clear();
            list.add(Constants.ExamineState.REPORTED);
            list.add(Constants.ExamineState.READ);
            List<String> countyReportNum = collectFlowMapper.getCountyByCondition(map);
            countyReportProgressVo.setReportCounty(Integer.toString(countyReportNum.size()));
            countyReportProgressVo.setNoReportCounty(String.valueOf(countyList.size() - countyPassNum.size() - countyReportNum.size()));
            countyReportProgressVo.setAreaLevel(RegionLevel.COUNTY.getCode());
            progressVoList.add(countyReportProgressVo);
            //获取县级数据
        }
        return progressVoList;
    }

    /**
     * 获取下级审核,上报,未通过量
     *
     * @param year                年份
     * @param administrativeLevel 用户等级
     * @return boolean 布尔类型
     */
    private void getReportProgressVo(String year, String administrativeLevel, SysOrganization orgData, ReportProgressVo reportProgressVo) {
        //查询当前区域下有多少个子区域
        Result<List<SysRegionTreeVo>> regionTree = sysApi.getSysRegionTreeById(orgData.getRegionLastCode(), "ducss");
        if (regionTree.getData() != null && regionTree.getData().size() > 0) {
            List<String> areaCodes = new ArrayList<>();
            //获取特殊区域数据
            List<SysRegionTreeVo> listRegion = SysRegionUtil.getSpecialAreaIds(administrativeLevel, orgData.getRegionLastCode());
            if (listRegion.size() == 0) {
                //获取当前子区域areacode集合
                regionTree.getData().forEach(sysRegionTreeVo -> {
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
            //审核通过的县
            Integer passNum = dateShowMapper.getCompleteCountByCondition(map);
            //上报通过的县
            map.put("status", Constants.ExamineState.REPORTED);
            Integer reportNum = dateShowMapper.getCompleteCountByCondition(map);
            reportProgressVo.setAuditCounty(String.valueOf(passNum));
            reportProgressVo.setReportCounty(String.valueOf(reportNum));
            //市级下县总数
            Integer totalNum;
            if (listRegion.size() == 0) {
                totalNum = regionTree.getData().size();
            } else {
                totalNum = listRegion.size();
            }
            reportProgressVo.setNoReportCounty(String.valueOf(totalNum - passNum - reportNum));
        }
    }
}