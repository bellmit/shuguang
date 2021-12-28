package com.sofn.fyem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.sofn.common.exception.SofnException;
import com.sofn.common.model.Result;
import com.sofn.common.utils.*;
import com.sofn.fyem.constants.Constants;
import com.sofn.fyem.enums.AuditStatueEnum;
import com.sofn.fyem.enums.RoleLevelEnum;
import com.sofn.fyem.enums.YesOrNoEnum;
import com.sofn.fyem.mapper.*;
import com.sofn.fyem.model.CityAudit;
import com.sofn.fyem.model.MinisterAudit;
import com.sofn.fyem.model.ProvinceAudit;
import com.sofn.fyem.model.ReporManagement;
import com.sofn.fyem.service.ReporManagementService;
import com.sofn.fyem.sysapi.SysRegionApi;
import com.sofn.fyem.sysapi.bean.SysOrgVo;
import com.sofn.fyem.sysapi.bean.SysOrganization;
import com.sofn.fyem.sysapi.bean.SysRegionTreeVo;
import com.sofn.fyem.util.FyemAreaUtil;
import com.sofn.fyem.util.OrganizationUtil;
import com.sofn.fyem.util.RoleCodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author Administrator
 */
@Service
@Slf4j
public class ReporManagementServiceImpl extends ServiceImpl<ReporManagementMapper, ReporManagement> implements ReporManagementService, ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private ReporManagementMapper reporManagementMapper;
    @Autowired
    private CityAuditMapper cityAuditMapper;
    @Autowired
    private ProvinceAuditMapper provinceAuditMapper;
    @Autowired
    private MinisterAuditMapper ministerAuditMapper;

    @Autowired
    private BasicProliferationReleaseMapper basicProliferationReleaseMapper;
    @Autowired
    private ProliferationReleaseStatisticsMapper proliferationReleaseStatisticsMapper;

    @Autowired
    private SysRegionApi sysRegionApi;

    @Autowired
    private RedisHelper redisHelper;

    @Override
    public PageUtils<ReporManagement> getReporManagementListByPage(Map<String, Object> params, int pageNo, int pageSize) {
        PageHelper.offsetPage(pageNo,pageSize);
        List<ReporManagement> reporManagementList = reporManagementMapper.getReporManagementByCondition(params);
        PageInfo<ReporManagement> pageInfo = new PageInfo<>(reporManagementList);
        return PageUtils.getPageUtils(pageInfo);
    }

    @Override
    public List<ReporManagement> getReporManagementListByQuery(Map<String, Object> params) {

        return reporManagementMapper.getReporManagementByCondition(params);
    }

    @Override
    public ReporManagement getReporManagementById(String id) {

        return this.getById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String updateStatus(Map<String, Object> params) {
        String  belongYear = (String) params.get("belongYear");
        String  provinceId = (String) params.get("provinceId");
        String  cityId = (String) params.get("cityId");
        String  countyId = (String) params.get("countyId");

        // 允许上报标志
        boolean reportFlag = false;
        // 市退回标志
        boolean returnCityFlag = false;
        // 省退回标志
        boolean returnProvFlag = false;
        // 部退回标志
        boolean returnMiniFlag = false;

        // 获取组织相关信息
        String loginUserOrganizationInfo = UserUtil.getLoginUserOrganizationInfo();
        SysOrganization sysOrganization = JsonUtils.json2obj(loginUserOrganizationInfo, SysOrganization.class);
        // 获取组织名
        String organizationName = sysOrganization.getOrganizationName();

        // 获取当前用户id
        String loginUserId = UserUtil.getLoginUserId();

        // 获取当前机构id
        String organizationId = sysOrganization.getId();

        // 获取当前用户角色列表
        List<String> roleCodes = UserUtil.getLoginUserRoleCodeList();
        String roleCode = RoleCodeUtil.getLoginUserFyemRoleCode(roleCodes);

        // 县级上报
        if (RoleLevelEnum.LEVEL_COUNTY.getLevel().equals(roleCode)){
            // 在县级上报管理表中找到belongYear、countyId、createUserId记录，判断水生和中央是否同时填好，记录为未上报状态
            Map map = new HashMap();
            map.put("belongYear",belongYear);
            map.put("countyId",countyId);
            map.put("status", AuditStatueEnum.STATUS_NOTREPORT.getStatus());
//            map.put("createUserId",loginUserId);
            map.put("organizationId", organizationId);
            List<ReporManagement> reporManagements = reporManagementMapper.getReporManagementByCondition(map);
            ReporManagement reporManagement = reporManagements.size() > 0 ? reporManagements.get(0): null;

            // 查询市级审核表是否已存在上报记录
            map.put("status", "");
            CityAudit preCityAudit = cityAuditMapper.selectByParams(map);
            if (preCityAudit != null){
                // 处于市级已退回状态，可以继续县级上报
                if (preCityAudit.getStatus().equals(AuditStatueEnum.STATUS_RETURN_CITY.getStatus())){
                    reportFlag = true;
                    returnCityFlag = true;
                }else {
                    reportFlag = false;
                    returnCityFlag = false;
                }
            }else {
                if (YesOrNoEnum.YES.getCode().equals(reporManagement.getHasBasicProliferationRelease())
                        && YesOrNoEnum.YES.getCode().equals(reporManagement.getHasProliferationReleaseStatistics())){
                    if(AuditStatueEnum.STATUS_NOTREPORT.getStatus().equals(reporManagement.getStatus())){
                        // 水生、中央同时填好,上报管理记录处于未上报,允许上报市级
                        reportFlag = true;
                    }else {
                        return "当前已上报！";
                    }
                }else {
                    return "水生生物或中央财政表数据未填写！";
                }
            }

            if (reportFlag){
                // 获取省id、市id
                Result<List<SysRegionTreeVo>> parentNode = sysRegionApi.getParentNode(countyId);
                List<SysRegionTreeVo> data = parentNode.getData();

                CityAudit cityAudit = new CityAudit();
                cityAudit.setId(IdUtil.getUUId());
                cityAudit.setBelongYear(belongYear);
                cityAudit.setProvinceId(data.size() > 0 ? data.get(0).getRegionCode() : "");
                cityAudit.setCityId(data.size() > 0 ? data.get(1).getRegionCode() : "");
                cityAudit.setCountyId(countyId);
                cityAudit.setOrganizationId(organizationId);
                cityAudit.setOrganizationName(organizationName);
                cityAudit.setCreateTime(new Date());
                cityAudit.setRoleCode(roleCode);
                cityAudit.setCreateUserId(loginUserId);
                // 改为 市级待审核 状态
                cityAudit.setStatus(AuditStatueEnum.STATUS_REPORT_CITY.getStatus());

                Map mapOfBPR = new HashMap();
                mapOfBPR.put("belongYear",belongYear);
                mapOfBPR.put("countyId",countyId);
                mapOfBPR.put("status",AuditStatueEnum.STATUS_REPORT_CITY.getStatus());
//                mapOfBPR.put("createUserId",loginUserId);
                mapOfBPR.put("organizationId", organizationId);
                // 改变数据状态的条件(未上报、市级已退回)
                mapOfBPR.put("statusConditionFirst",AuditStatueEnum.STATUS_NOTREPORT.getStatus());
                mapOfBPR.put("statusConditionSecond",AuditStatueEnum.STATUS_RETURN_CITY.getStatus());
                try {
                    // 修改县级上报管理表记录状态、水生生物的放流记录状态、中央财政记录的状态
                    int resultOfCountyAudit = reporManagementMapper.updateStatus(mapOfBPR);
                    int resultOfBPR = basicProliferationReleaseMapper.updateStatus(mapOfBPR);
                    int resultOfPRS = proliferationReleaseStatisticsMapper.updateStatus(mapOfBPR);
                    int resultOfCityAudit = 0;
                    if (preCityAudit != null){
                        // 市级审核表修改上报数据状态
                        preCityAudit.setStatus(AuditStatueEnum.STATUS_REPORT_CITY.getStatus());
                        resultOfCityAudit = cityAuditMapper.updateById(preCityAudit);
                    }else {
                        // 市级审核表新增上报数据
                        resultOfCityAudit = cityAuditMapper.insert(cityAudit);
//                        resultOfCityAudit = cityAuditMapper.insertNotExists(cityAudit);
                    }

                    if (returnCityFlag){// 市退回，县级重新上报
                        if (resultOfCityAudit > 0){
                            this.markSubmit(belongYear);
                            return "1";
                        }else {
                            throw new SofnException("县级上报失败！");
                        }
                    }else {// 新的县级上报
                        if (resultOfCityAudit == 1 && resultOfCountyAudit > 0
                                && resultOfBPR > 0 && resultOfPRS > 0){
                            this.markSubmit(belongYear);
                            return "1";
                        } else {
                            throw new SofnException("县级上报失败！");
                        }
                    }
                } catch (Exception e){
                    log.error("县级上报报错:", e);
                    throw new SofnException("县级上报失败！");
                }
            }else {
                return "请等待上级退回后再上报";
            }
        }

        // 上报市级（市本级、市直属）
        if (RoleLevelEnum.LEVEL_CITY_ADD.getLevel().equals(roleCode)){
            returnCityFlag = false;
            // 判断状态是否可以进行上报市级
            Map map = new HashMap();
            map.put("belongYear",belongYear);
            map.put("cityId",cityId);
            map.put("status",AuditStatueEnum.STATUS_NOTREPORT.getStatus());
//            map.put("createUserId",loginUserId);
            map.put("organizationId", organizationId);
            List<ReporManagement> reporManagements = reporManagementMapper.getReporManagementByCondition(map);
            ReporManagement reporManagement = reporManagements.size() > 0 ? reporManagements.get(0): new ReporManagement();

            // 查询市级审核表是否已存在上报记录
            map.put("status","");
            CityAudit preCityAudit = cityAuditMapper.selectByParams(map);
            if (preCityAudit != null){
                // 处于市级已退回状态，可以继续上报市级
                if (preCityAudit.getStatus().equals(AuditStatueEnum.STATUS_RETURN_CITY.getStatus())){
                    reportFlag = true;
                    returnCityFlag = true;
                }else {
                    reportFlag = false;
                    returnCityFlag = false;
                }
            }else {
                if (YesOrNoEnum.YES.getCode().equals(reporManagement.getHasBasicProliferationRelease())
                        && YesOrNoEnum.YES.getCode().equals(reporManagement.getHasProliferationReleaseStatistics())){
                    if(AuditStatueEnum.STATUS_NOTREPORT.getStatus().equals(reporManagement.getStatus())){
                        // 水生、中央同时填好,上报管理记录处于未上报,允许上报市级
                        reportFlag = true;
                    }else {
                        return "当前已上报！";
                    }
                }else {
                    return "水生生物或中央财政表数据未填写！";
                }
            }

            if (reportFlag == true){
                // 获取上级地址id
                Result<List<SysRegionTreeVo>> parentNode = sysRegionApi.getParentNode(cityId);
                List<SysRegionTreeVo> data = parentNode.getData();

                CityAudit cityAudit = new CityAudit();
                cityAudit.setId(IdUtil.getUUId());
                cityAudit.setBelongYear(belongYear);
                cityAudit.setProvinceId(data.size() > 0 ? data.get(0).getRegionCode() : "");
                cityAudit.setCityId(data.size() > 0 ? data.get(1).getRegionCode() : "");
                cityAudit.setCountyId("");
                cityAudit.setOrganizationId(organizationId);
                cityAudit.setOrganizationName(organizationName);
                cityAudit.setCreateTime(new Date());
                cityAudit.setRoleCode(roleCode);
                cityAudit.setCreateUserId(loginUserId);
                // 改为 市级待审核 状态
                cityAudit.setStatus(AuditStatueEnum.STATUS_REPORT_CITY.getStatus());

                Map mapOfBPR = new HashMap();
                mapOfBPR.put("belongYear",belongYear);
                mapOfBPR.put("cityId",cityId);
                mapOfBPR.put("status",AuditStatueEnum.STATUS_REPORT_CITY.getStatus());
//                mapOfBPR.put("createUserId",loginUserId);
                mapOfBPR.put("organizationId", organizationId);
                // 改变数据状态的条件(未上报、市级已退回)
                mapOfBPR.put("statusConditionFirst",AuditStatueEnum.STATUS_NOTREPORT.getStatus());
                mapOfBPR.put("statusConditionSecond",AuditStatueEnum.STATUS_RETURN_CITY.getStatus());
                try {
                    // 修改县级上报管理表记录状态、水生生物的放流记录状态、中央财政记录的状态
                    int resultOfCountyAudit = reporManagementMapper.updateStatus(mapOfBPR);
                    int resultOfBPR = basicProliferationReleaseMapper.updateStatus(mapOfBPR);
                    int resultOfPRS = proliferationReleaseStatisticsMapper.updateStatus(mapOfBPR);
                    int resultOfCityAudit = 0;
                    if (preCityAudit != null){
                        // 市级审核表修改上报数据状态
                        preCityAudit.setStatus(AuditStatueEnum.STATUS_REPORT_CITY.getStatus());
                        resultOfCityAudit = cityAuditMapper.updateById(preCityAudit);
                    }else {
                        // 市级审核表新增上报数据
                        resultOfCityAudit = cityAuditMapper.insert(cityAudit);
//                        resultOfCityAudit = cityAuditMapper.insertNotExists(cityAudit);
                    }

                    if (returnCityFlag == true){// 市退回，市填报员重新上报
                        if (resultOfCityAudit > 0){
                            this.markSubmit(belongYear);
                            return "1";
                        }else {
                            throw new SofnException("上报市级失败！");
                        }
                    }else {// 新的上报市级
                        if (resultOfCityAudit == 1 && resultOfCountyAudit > 0
                                && resultOfBPR > 0 && resultOfPRS > 0){
                            this.markSubmit(belongYear);
                            return "1";
                        } else {
                            throw new SofnException("上报市级失败！");
                        }
                    }
                } catch (Exception e){
                    log.error("上报市级报错" + e.getMessage());
                    throw new SofnException("上报市级失败！");
                }
            }else {
                return "请等待上级退回后再上报";
            }
        }

        // 上报省级（省本级、省直属）
        if (RoleLevelEnum.LEVEL_PROVINCE_ADD.getLevel().equals(roleCode)){
            // 判断状态是否可以进行上报省级
            Map<String, Object> map = new HashMap<>();
            map.put("belongYear",belongYear);
            map.put("provinceId",provinceId);
            map.put("status",AuditStatueEnum.STATUS_NOTREPORT.getStatus());
//            map.put("createUserId",loginUserId);
            map.put("organizationId", organizationId);
            List<ReporManagement> reporManagements = reporManagementMapper.getReporManagementByCondition(map);
            ReporManagement reporManagement = reporManagements.stream().findFirst().orElseGet(ReporManagement::new);

            // 查询省级审核表是否已存在上报记录
            map.put("status","");
            ProvinceAudit preProvinceAudit = provinceAuditMapper.selectByParams(map);
            if (preProvinceAudit != null){
                // 处于省级已退回状态，可以继续上报省级
                if (preProvinceAudit.getStatus().equals(AuditStatueEnum.STATUS_RETURN_PROV.getStatus())){
                    reportFlag = true;
                    returnProvFlag = true;
                }else {
                    reportFlag = false;
                    returnProvFlag = false;
                }
            }else {
                if (YesOrNoEnum.YES.getCode().equals(reporManagement.getHasBasicProliferationRelease())
                        && YesOrNoEnum.YES.getCode().equals(reporManagement.getHasProliferationReleaseStatistics())){
                    if(AuditStatueEnum.STATUS_NOTREPORT.getStatus().equals(reporManagement.getStatus())){
                        // 水生、中央同时填好,上报管理记录处于未上报,允许上报省级
                        reportFlag = true;
                    }else {
                        return "当前已上报！";
                    }
                }else {
                    return "水生生物或中央财政表数据未填写！";
                }
            }

            if (reportFlag){
                ProvinceAudit provinceAudit = new ProvinceAudit();
                provinceAudit.setId(IdUtil.getUUId());
                provinceAudit.setBelongYear(belongYear);
                provinceAudit.setProvinceId(provinceId);
                provinceAudit.setCityId("");
                provinceAudit.setCountyId("");
                provinceAudit.setOrganizationId(organizationId);
                provinceAudit.setOrganizationName(organizationName);
                provinceAudit.setCreateTime(new Date());
                provinceAudit.setRoleCode(roleCode);
                provinceAudit.setCreateUserId(loginUserId);
                // 改为 省级待审核 状态
                provinceAudit.setStatus(AuditStatueEnum.STATUS_REPORT_PROV.getStatus());

                Map<String, Object> mapOfBPR = new HashMap<>();
                mapOfBPR.put("belongYear",belongYear);
                mapOfBPR.put("provinceId",provinceId);
                mapOfBPR.put("status",AuditStatueEnum.STATUS_REPORT_PROV.getStatus());
//                mapOfBPR.put("createUserId",loginUserId);
                mapOfBPR.put("organizationId", organizationId);
                // 改变数据状态的条件(未上报、省级已退回)
                mapOfBPR.put("statusConditionFirst",AuditStatueEnum.STATUS_NOTREPORT.getStatus());
                mapOfBPR.put("statusConditionSecond",AuditStatueEnum.STATUS_RETURN_PROV.getStatus());
                try {
                    // 修改县级上报管理表记录状态、水生生物的放流记录状态、中央财政记录的状态
                    int resultOfCountyAudit = reporManagementMapper.updateStatus(mapOfBPR);
                    int resultOfBPR = basicProliferationReleaseMapper.updateStatus(mapOfBPR);
                    int resultOfPRS = proliferationReleaseStatisticsMapper.updateStatus(mapOfBPR);
                    int resultOfProvAudit = 0;
                    if (preProvinceAudit != null){
                        // 省级审核表修改上报数据状态
                        preProvinceAudit.setStatus(AuditStatueEnum.STATUS_REPORT_PROV.getStatus());
                        resultOfProvAudit = provinceAuditMapper.updateById(preProvinceAudit);
                    }else {
                        // 省级审核表新增上报数据
                        resultOfProvAudit = provinceAuditMapper.insert(provinceAudit);
                    }

                    if (returnProvFlag){// 省退回，省填报员重新上报
                        if (resultOfProvAudit > 0){
                            this.markSubmit(belongYear);
                            return "1";
                        }else {
                            throw new SofnException("上报省级失败！");
                        }
                    }else {// 新的上报省级
                        if (resultOfProvAudit == 1 && resultOfCountyAudit > 0
                                && resultOfBPR > 0 && resultOfPRS > 0){
                            this.markSubmit(belongYear);
                            return "1";
                        } else {
                            throw new SofnException("上报省级失败！");
                        }
                    }
                } catch (Exception e){
                    log.error("上报省级报错" + e.getMessage());
                    throw new SofnException("上报省级失败！");
                }
            }else {
                return "请等待上级退回后再上报";
            }
        }

        // 上报部级（部本级、部直属）
        if (RoleLevelEnum.LEVEL_MASTER_ADD.getLevel().equals(roleCode)){
            // 判断状态是否可以进行部级上报（下级市上报数 > 0）
            Map map = new HashMap();
            map.put("belongYear",belongYear);
            map.put("status",AuditStatueEnum.STATUS_NOTREPORT.getStatus());
//            map.put("createUserId",loginUserId);
            map.put("organizationId", organizationId);
            List<ReporManagement> reporManagements = reporManagementMapper.getReporManagementByCondition(map);
            ReporManagement reporManagement = reporManagements.size() > 0 ? reporManagements.get(0): new ReporManagement();

            // 查询部级审核表是否已存在上报记录
            map.put("status","");
            MinisterAudit preMinisterAudit = ministerAuditMapper.selectByParams(map);
            if (preMinisterAudit != null){
                // 处于部级已退回状态，可以继续省级上报
                if (preMinisterAudit.getStatus().equals(AuditStatueEnum.STATUS_RETURN_MASTER.getStatus())){
                    reportFlag = true;
                    returnMiniFlag = true;
                }else {
                    returnMiniFlag = false;
                }
            }else {
                if (YesOrNoEnum.YES.getCode().equals(reporManagement.getHasBasicProliferationRelease())
                        && YesOrNoEnum.YES.getCode().equals(reporManagement.getHasProliferationReleaseStatistics())){
                    if(AuditStatueEnum.STATUS_NOTREPORT.getStatus().equals(reporManagement.getStatus())){
                        // 水生、中央同时填好,上报管理记录处于未上报,允许上报市级
                        reportFlag = true;
                    }else {
                        return "当前已上报！";
                    }
                }else {
                    return "水生生物或中央财政表数据未填写！";
                }
            }

            if (reportFlag){
                MinisterAudit ministerAudit = new MinisterAudit();
                ministerAudit.setId(IdUtil.getUUId());
                ministerAudit.setBelongYear(belongYear);
                ministerAudit.setProvinceId("");
                ministerAudit.setCityId("");
                ministerAudit.setCountyId("");
                ministerAudit.setOrganizationId(organizationId);
                ministerAudit.setOrganizationName(organizationName);
                ministerAudit.setCreateTime(new Date());
                ministerAudit.setRoleCode(roleCode);
                ministerAudit.setCreateUserId(loginUserId);
                // 改为“部级待审核”状态
                ministerAudit.setStatus(AuditStatueEnum.STATUS_REPORT_MASTER.getStatus());

                Map mapOfBPR = new HashMap();
                mapOfBPR.put("belongYear",belongYear);
                mapOfBPR.put("status",AuditStatueEnum.STATUS_REPORT_MASTER.getStatus());
//                mapOfBPR.put("createUserId",loginUserId);
                mapOfBPR.put("organizationId", organizationId);
                // 改变数据状态的条件(未上报、部级已退回)
                mapOfBPR.put("statusConditionFirst",AuditStatueEnum.STATUS_NOTREPORT.getStatus());
                mapOfBPR.put("statusConditionSecond",AuditStatueEnum.STATUS_RETURN_MASTER.getStatus());
                try {
                    // 修改县级上报管理表记录状态、水生生物的放流记录状态、中央财政记录的状态
                    int resultOfCountyAudit = reporManagementMapper.updateStatus(mapOfBPR);
                    int resultOfBPR = basicProliferationReleaseMapper.updateStatus(mapOfBPR);
                    int resultOfPRS = proliferationReleaseStatisticsMapper.updateStatus(mapOfBPR);
                    int resultOfMiniAudit = 0;
                    if (preMinisterAudit != null){
                        // 部级审核表修改上报数据状态
                        preMinisterAudit.setStatus(AuditStatueEnum.STATUS_REPORT_MASTER.getStatus());
                        resultOfMiniAudit = ministerAuditMapper.updateById(preMinisterAudit);
                    }else {
                        // 部级审核表新增上报数据
                        resultOfMiniAudit = ministerAuditMapper.insert(ministerAudit);
                    }

                    if (returnMiniFlag == true){// 部退回，部填报员重新上报
                        if (resultOfMiniAudit > 0){
                            this.markSubmit(belongYear);
                            return "1";
                        }else {
                            throw new SofnException("上报部级失败！");
                        }
                    }else {// 新的部级上报
                        if (resultOfMiniAudit == 1 && resultOfCountyAudit > 0
                                && resultOfBPR > 0 && resultOfPRS > 0){
                            this.markSubmit(belongYear);
                            return "1";
                        } else {
                            throw new SofnException("上报部级失败！");
                        }
                    }
                } catch (Exception e){
                    log.error("上报部级报错" + e.getMessage());
                    throw new SofnException("上报部级失败！");
                }
            }else {
                return "请等待上级退回后再上报";
            }
        }

        return "当前角色非法";
    }

    @Override
    public void addReporManagement(ReporManagement reporManagement) {
        this.save(reporManagement);
    }

    @Override
    public void updateReporManagement(ReporManagement reporManagement) {
        this.updateById(reporManagement);
    }

    @Override
    public void removeReporManagement(String id) {

        this.removeById(id);
    }

    @Override
    public void deleteReporManagement(Map<String, Object> params) {
        reporManagementMapper.deleteByParams(params);
    }


    /**
     * 未上报
     */
    private static final String NO_REPORT = "0";

    /**
     * 第三方机构/直属机构上报
     */
    private static final String THIRD_REPORT = "1";

    /**
     * 行政机构上报
     */
    private static final String ADMIN_REPORT = "2";

    @Override
    public boolean able2Modify(String belongYear) {
        for (String orgId : OrganizationUtil.getLoginUserOrgParentIds()) {
            if (Objects.equals(ADMIN_REPORT, this.getRedisValue(belongYear, orgId))) {
                return false;
            }
        }

        String orgId = UserUtil.getLoginUserOrganizationId();
        String val = this.getRedisValue(belongYear, orgId);

        return !Objects.equals(val, ADMIN_REPORT);
    }

    @Override
    public boolean able2Audit(String belongYear) {
        RoleLevelEnum role = RoleLevelEnum.resolve(RoleCodeUtil.getLoginUserFyemRoleCode(null)).orElseThrow(() -> new SofnException("非法角色"));
        for (String orgId : OrganizationUtil.getLoginUserOrgParentIds()) {
            if (Objects.equals(ADMIN_REPORT, this.getRedisValue(belongYear, orgId))) {
                return false;
            }
        }

        String orgId = UserUtil.getLoginUserOrganizationId();
        String val = this.getRedisValue(belongYear, orgId);

        return RoleCodeUtil.adds.contains(role) ? Objects.equals(val, NO_REPORT) : !Objects.equals(val, ADMIN_REPORT);
    }

    @Override
    public void markSubmit(String belongYear) {
        List<String> roleCodes = UserUtil.getLoginUserRoleCodeList();
        String roleCode = RoleCodeUtil.getLoginUserFyemRoleCode(roleCodes);
        RoleLevelEnum roleLevel = RoleLevelEnum.resolve(roleCode).orElseThrow(() -> new IllegalArgumentException("非法的角色"));

        String val;
        switch (roleLevel) {
            case LEVEL_MASTER_ADD:
            case LEVEL_PROVINCE_ADD:
            case LEVEL_CITY_ADD:
                val = THIRD_REPORT;
                break;
            case LEVEL_COUNTY:
            case LEVEL_CITY:
            case LEVEL_PROVINCE:
            case LEVEL_MASTER:
                val = ADMIN_REPORT;
                break;
            default:
                throw new IllegalArgumentException("非法的角色");
        }

        String orgId = UserUtil.getLoginUserOrganizationId();
        final String redisKey = orgId + ":" + belongYear;
        if (!redisHelper.hset(Constants.PERMISSION_REDIS_KEY, redisKey, val)) {
            throw new IllegalArgumentException("同步redis失败");
        }
    }

    @Override
    public void markReject(Map<String, Object> params) {
        String orgId = UserUtil.getLoginUserOrganizationId();
        Result<List<SysOrgVo>> result = sysRegionApi.listByParentId(orgId, Constants.SYSTEM_ID);
        if (!Objects.equals(result.getCode(), Result.CODE) || CollectionUtils.isEmpty(result.getData())) {
            throw new SofnException("获取子节点失败");
        }

        params = FyemAreaUtil.fillRegionCode(params);

        List<String> regionCode = Lists.newArrayListWithCapacity(3);
        if (StringUtils.isNotBlank((String) params.get("provinceId"))) {
            regionCode.add((String) params.get("provinceId"));
            if (StringUtils.isNotBlank((String) params.get("cityId"))) {
                regionCode.add((String) params.get("cityId"));
                if (StringUtils.isNotBlank((String) params.get("countyId"))) {
                    regionCode.add((String) params.get("countyId"));
                }
            }
        }

        for (SysOrgVo orgVo : result.getData()) {
            if (Objects.equals(orgVo.getRegionFullCode(), regionCode)) {
                final String redisKey = orgVo.getId() + ":" + params.get("belongYear");
                if (!redisHelper.hset(Constants.PERMISSION_REDIS_KEY, redisKey, NO_REPORT)) {
                    throw new IllegalArgumentException("同步redis失败");
                }
                break;
            }
        }
    }

    private String getRedisValue(String belongYear, String organizationId) {
        final String redisKey = organizationId + ":" + belongYear;
        return redisHelper.hHasKey(Constants.PERMISSION_REDIS_KEY, redisKey) ? String.valueOf(redisHelper.hget(Constants.PERMISSION_REDIS_KEY, redisKey))
            : NO_REPORT;
    }

    public void afterPropertiesSet() {
        if (log.isDebugEnabled()) {
            log.debug("开始同步redis数据...");
        }

        for (ReporManagement reporManagement : this.list()) {
            try {
                this.process(reporManagement);
            } catch (Exception e) {
                log.error("同步redis发生错误 ", e);
            }
        }

        if (log.isDebugEnabled()) {
            log.debug("同步redis数据完成");
        }
    }

    private void process(ReporManagement reporManagement) {
        // 数据状态
        AuditStatueEnum auditStatue = AuditStatueEnum.resolve(reporManagement.getStatus()).orElseThrow(() -> new IllegalArgumentException("非法状态"));
        RoleLevelEnum roleLevel = RoleLevelEnum.resolve(reporManagement.getRoleCode()).orElseThrow(() -> new IllegalArgumentException("非法角色"));

        if (Objects.equals(auditStatue, AuditStatueEnum.STATUS_NOTREPORT)) {
            return;
        }

        // 根据状态获取所在的等级
        String level = OrganizationUtil.getOrgInfoById(reporManagement.getOrganizationId()).getOrganizationLevel();
        switch (auditStatue) { // 上报位于低一级
            case STATUS_REPORT_CITY:
            case STATUS_APPROVE_CITY:
            case STATUS_RETURN_CITY:
                level = Objects.equals(roleLevel, RoleLevelEnum.LEVEL_CITY_ADD) ? "city" : "county"; break;
            case STATUS_REPORT_PROV:
            case STATUS_APPROVE_PROV:
            case STATUS_RETURN_PROV:
                level = Objects.equals(roleLevel, RoleLevelEnum.LEVEL_PROVINCE_ADD) ? "province" : "city"; break;
            case STATUS_REPORT_MASTER:
            case STATUS_APPROVE_MASTER:
            case STATUS_RETURN_MASTER:
                level = Objects.equals(roleLevel, RoleLevelEnum.LEVEL_MASTER_ADD) ? "ministry" : "province"; break;
            default:
        }

        // 获取数据所在的机构
        String createOrganizationId = reporManagement.getOrganizationId();
        String organizationId = OrganizationUtil.resolveLevelOrganization(createOrganizationId, level);

        // 获取数据上报状态
        String val = NO_REPORT;
        switch (auditStatue) {
            case STATUS_REPORT_CITY:
            case STATUS_APPROVE_CITY:
                val = Objects.equals(roleLevel, RoleLevelEnum.LEVEL_CITY_ADD) ? THIRD_REPORT : ADMIN_REPORT;
                break;
            case STATUS_REPORT_PROV:
            case STATUS_APPROVE_PROV:
                val = Objects.equals(roleLevel, RoleLevelEnum.LEVEL_PROVINCE_ADD) ? THIRD_REPORT : ADMIN_REPORT;
                break;
            case STATUS_REPORT_MASTER:
            case STATUS_APPROVE_MASTER:
                val = Objects.equals(roleLevel, RoleLevelEnum.LEVEL_MASTER_ADD) ? THIRD_REPORT : ADMIN_REPORT;
                break;
            default:
        }

        String redisKey = organizationId + ":" + reporManagement.getBelongYear();

        if (!redisHelper.hset(Constants.PERMISSION_REDIS_KEY, redisKey, val)) {
            throw new IllegalArgumentException("解析 ReporManagement[" + reporManagement.getId() + "] 失败");
        }
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (contextRefreshedEvent.getApplicationContext() instanceof AnnotationConfigServletWebServerApplicationContext) {
            this.afterPropertiesSet();
        }
    }
}
