package com.sofn.fyem.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.sofn.common.exception.SofnException;
import com.sofn.common.map.*;
import com.sofn.common.model.Result;
import com.sofn.common.utils.IdUtil;
import com.sofn.common.utils.JsonUtils;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.UserUtil;
import com.sofn.fyem.constants.Constants;
import com.sofn.fyem.enums.*;
import com.sofn.fyem.excelmodel.BasicProliferationReleaseExcel;
import com.sofn.fyem.excelmodel.ProliferationReleaseDistributionExcel;
import com.sofn.fyem.excelmodel.ProliferationReleaseExcel;
import com.sofn.fyem.mapper.*;
import com.sofn.fyem.model.*;
import com.sofn.fyem.service.BasicProliferationReleaseService;
import com.sofn.fyem.service.ReporManagementService;
import com.sofn.fyem.sysapi.SysFileApi;
import com.sofn.fyem.sysapi.SysRegionApi;
import com.sofn.fyem.sysapi.bean.*;
import com.sofn.fyem.util.FyemAreaUtil;
import com.sofn.fyem.util.RoleCodeUtil;
import com.sofn.fyem.vo.*;
import com.xiaoleilu.hutool.util.NumberUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description: 水生生物增殖放流基础数据接口实现
 * @Author: DJH
 * @Date: 2020.4.26 10.11
 */
@Service(value = "basicProliferationReleaseService")
@Slf4j
public class BasicProliferationReleaseServiceImpl extends ServiceImpl<BasicProliferationReleaseMapper, BasicProliferationRelease> implements BasicProliferationReleaseService {

    @Autowired
    private BasicProliferationReleaseMapper basicProliferationReleaseMapper;
    @Autowired
    private ProliferationReleaseStatisticsMapper proliferationReleaseStatisticsMapper;
    @Autowired
    private ReporManagementMapper reporManagementMapper;
    @Autowired
    private ProvinceAuditMapper provinceAuditMapper;
    @Autowired
    private MinisterAuditMapper ministerAuditMapper;
    @Autowired
    private CityAuditMapper cityAuditMapper;
    @Autowired
    private ReporManagementService reporManagementService;

    @Autowired
    private SysFileApi sysFileApi;
    @Autowired
    private SysRegionApi sysRegionApi;

    @Override
    public List<BasicProliferationReleaseVO> listBPRByBelongYear(Map<String, Object> params) {
        String belongYear = (String) params.get("belongYear");
        // 未填所属年度，使用当前年度
        if (StringUtils.isBlank(belongYear) || belongYear.equals("0")) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
            belongYear = dateFormat.format(new Date());
            params.put("belongYear", belongYear);
        }

        List<BasicProliferationReleaseVO> list = basicProliferationReleaseMapper.listBPRByBelongYear(params);
        // 转换省市县名称、放流级别名称
        releaseAreaAndReleaseLevelConvert(list);
        return list;
    }

    @Override
    public PageUtils<BasicProliferationReleaseVO> getBasicProliferationReleaseListByPage(Map<String, Object> params, int pageNo, int pageSize) {
        String belongYear = (String) params.get("belongYear");
        // 未填所属年度，使用当前年度
        if (StringUtils.isBlank(belongYear) || belongYear.equals("0")) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
            belongYear = dateFormat.format(new Date());
            params.put("belongYear", belongYear);
        }

        PageHelper.offsetPage(pageNo, pageSize);
        List<BasicProliferationReleaseVO> releaseEvaluateIndicatorList = basicProliferationReleaseMapper.listBPRByBelongYear(params);
        // 转换省市县名称、放流级别名称
        releaseAreaAndReleaseLevelConvert(releaseEvaluateIndicatorList);
        PageInfo<BasicProliferationReleaseVO> pageInfo = new PageInfo<>(releaseEvaluateIndicatorList);
        return PageUtils.getPageUtils(pageInfo);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, SofnException.class})
    public String insert(BasicProliferationReleaseForm form, String token) {
        // 获取当前用户角色列表
        List<String> roleCodes = UserUtil.getLoginUserRoleCodeList();
        String roleCode = RoleCodeUtil.getLoginUserFyemRoleCode(roleCodes);
        // 仅能由县级、市级填报员、省级填报员、部级填报员填报数据
        if (!(roleCode.equals(RoleLevelEnum.LEVEL_COUNTY.getLevel())
                || roleCode.equals(RoleLevelEnum.LEVEL_CITY_ADD.getLevel())
                || roleCode.equals(RoleLevelEnum.LEVEL_PROVINCE_ADD.getLevel())
                || roleCode.equals(RoleLevelEnum.LEVEL_MASTER_ADD.getLevel()))) {
            throw new SofnException("非填报员不许填报！");
        }
        //区县级操作数据
        if(roleCode.equals(RoleLevelEnum.LEVEL_COUNTY.getLevel())){
            boolean b = this.countyOperationData(form, roleCode);
            if(!b){
                throw new SofnException("当前年数据已上报或上报数据非市退回状态，不允许填报！");
            }
        }

        // form表单转换数据
        BasicProliferationRelease basicProliferationRelease = new BasicProliferationRelease();
        BasicProliferationReleaseForm.getBasicProliferationRelease(form, basicProliferationRelease);

        String loginUserId = UserUtil.getLoginUserId();
        String loginUserName = UserUtil.getLoginUserName();
        // 获取组织信息
        String loginUserOrganizationInfo = UserUtil.getLoginUserOrganizationInfo();
        SysOrganization sysOrganization = JsonUtils.json2obj(loginUserOrganizationInfo, SysOrganization.class);
        // 获取机构区划codeList
        List<String> regionCode = JsonUtils.json2List(sysOrganization.getRegioncode(), String.class);

        // 获取当前机构id
        String orgId = sysOrganization.getId();
        // 获取当前机构名称
        String orgName = sysOrganization.getOrganizationName();

        String belongYear = basicProliferationRelease.getBelongYear();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
        // 未填所属年度，使用当前年度
        if (StringUtils.isBlank(belongYear) || belongYear.equals("0")) {
            belongYear = dateFormat.format(new Date());
            basicProliferationRelease.setBelongYear(belongYear);
        }

        if (!reporManagementService.able2Modify(belongYear)) {
            throw new SofnException("存在已上报未退回的数据");
        }

        // 默认未上报状态（根据填报单位级别，设定对应级别的待审核状态）
        String status = AuditStatueEnum.STATUS_NOTREPORT.getStatus();

        basicProliferationRelease.setId(IdUtil.getUUId());
        basicProliferationRelease.setCreateUserId(loginUserId);
        basicProliferationRelease.setCreateOrganizationId(orgId);
        basicProliferationRelease.setCreateOrganizationName(orgName);
        basicProliferationRelease.setOrganizationId(orgId);
        basicProliferationRelease.setRoleCode(roleCode);
        basicProliferationRelease.setStatus(status);
        basicProliferationRelease.setCreateTime(new Date());

        // 由前端上传后，返回文件id，再用系统支持平台激活文件id即可
        SysFileManageForm sysFileManageForm = new SysFileManageForm();
        sysFileManageForm.setSystemId(Constants.SYSTEM_ID);
        sysFileManageForm.setRemark("水生生物增殖放流基础数据附件");
        sysFileManageForm.setIds(form.getAccessory());
        sysFileManageForm.setInterfaceNum("hidden");
        boolean result = false;
        try {
            result = this.save(basicProliferationRelease);
            // 激活附件
            activationFile(sysFileApi, sysFileManageForm, token);
        } catch (Exception e) {
            log.error("水生生物增殖放流基础数据新增报错:" + e.getMessage());
            throw new SofnException("新增失败！");
        }

        // 根据条件判断是否存在县级上报信息数据
        Map<String, Object> reporParams = Maps.newHashMap();
        reporParams.put("belongYear", belongYear);
        reporParams.put("organizationId", orgId);
        //reporParams.put("createUserId", loginUserId);一个区县只能有一个
        List<ReporManagement> managementList = reporManagementService.getReporManagementListByQuery(reporParams);
        ReporManagement reporManagement = new ReporManagement();
        if (managementList.size() > 0) {
            // 存在县级上报信息,更新填报状态
            reporManagement = managementList.get(0);
            reporManagement.setHasBasicProliferationRelease(YesOrNoEnum.YES.getCode());
            reporManagement.setProvinceId(basicProliferationRelease.getProvinceId());
            reporManagement.setCityId(basicProliferationRelease.getCityId());
            reporManagement.setCountyId(basicProliferationRelease.getCountyId());
            try {
                reporManagementService.updateReporManagement(reporManagement);
            }catch (Exception e){
                log.error("上报管理信息填报状态更新报错:" + e.getMessage());
                throw new SofnException("新增失败！");
            }
        } else {
            // 不存在县级上报信息,新增上报管理信息
            reporManagement.setId(IdUtil.getUUId());
            reporManagement.setBelongYear(belongYear);
            reporManagement.setProvinceId(basicProliferationRelease.getProvinceId());
            reporManagement.setCityId(basicProliferationRelease.getCityId());
            reporManagement.setCountyId(basicProliferationRelease.getCountyId());
            reporManagement.setHasBasicProliferationRelease(YesOrNoEnum.YES.getCode());
            reporManagement.setHasProliferationReleaseStatistics(YesOrNoEnum.NO.getCode());
            reporManagement.setOrganizationId(orgId);
            reporManagement.setOrganizationName(orgName);
            reporManagement.setStatus(status);
            reporManagement.setRoleCode(roleCode);
            reporManagement.setCreateUserId(loginUserId);
            reporManagement.setCreateUserName(loginUserName);
            reporManagement.setCreateTime(new Date());
            try {
                reporManagementService.addReporManagement(reporManagement);
            }catch (Exception e){
                log.error("上报管理信息新增报错:" + e.getMessage());
                throw new SofnException("新增失败！");
            }
        }

        if (!result) {
            throw new SofnException("新增失败");
        } else {
            return "1";
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String update(BasicProliferationReleaseForm form, String token) {
        // 获取当前用户角色列表
        List<String> roleCodes = UserUtil.getLoginUserRoleCodeList();
        String roleCode = RoleCodeUtil.getLoginUserFyemRoleCode(roleCodes);

        if (!reporManagementService.able2Modify(form.getBelongYear())) {
            throw new SofnException("存在已上报未退回的数据");
        }

        //区、县级修改数据判断
        if(roleCode.equals(RoleLevelEnum.LEVEL_COUNTY.getLevel())){
            boolean b = this.countyOperationData(form, roleCode);
            if(!b){
                throw new SofnException("当前年数据已上报或上报数据非市退回状态，不允许修改！");
            }
        }
        String loginUserId = UserUtil.getLoginUserId();
        // 以id获取原有数据
        BasicProliferationRelease basicProliferationRelease = basicProliferationReleaseMapper.selectByPrimaryKey(form.getId());
        if (basicProliferationRelease == null || !loginUserId.equals(basicProliferationRelease.getCreateUserId())) {
            // 原数据为null或创建者id与当前用户id不相同时，不允许修改
            throw new SofnException("非原填报员不许修改！");
        }
//        String preStatus = basicProliferationRelease.getStatus();
//        if (!(AuditStatueEnum.STATUS_NOTREPORT.getStatus().equals(preStatus)
//                ||AuditStatueEnum.STATUS_RETURN_CITY.getStatus().equals(preStatus)
//                ||AuditStatueEnum.STATUS_RETURN_PROV.getStatus().equals(preStatus)
//                ||AuditStatueEnum.STATUS_RETURN_MASTER.getStatus().equals(preStatus))){
//            // 非未上报、各级退回状态，不允许修改
//            throw new SofnException("当前状态不许修改！");
//        }
        // 将form表单转换entity数据
        BasicProliferationReleaseForm.getBasicProliferationRelease(form, basicProliferationRelease);

        // 转换放流级别
        String level = basicProliferationRelease.getReleaseLevel();
        String releaseLevel = !NumberUtils.isNumber(level) ? ReleaseLevelEnum.getStatus(level) : level;
        basicProliferationRelease.setReleaseLevel(releaseLevel);

        SysFileManageForm sysFileManageForm = new SysFileManageForm();
        sysFileManageForm.setSystemId(Constants.SYSTEM_ID);
        sysFileManageForm.setRemark("水生生物增殖放流基础数据附件");
        sysFileManageForm.setIds(form.getAccessory());
        sysFileManageForm.setInterfaceNum("hidden");

        int result = 0;
        try {
            result = basicProliferationReleaseMapper.updateById(basicProliferationRelease);
            // 激活附件
            activationFile(sysFileApi, sysFileManageForm, token);
        } catch (Exception e) {
            log.error("水生生物增殖放流基础数据修改报错:" + e.getMessage());
            throw new SofnException("修改失败！");
        }

        if (result == 0) {
            throw new SofnException("修改失败！");
        } else {
            return "1";
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String delete(String id) {
        // 获取当前用户角色列表
        List<String> roleCodes = UserUtil.getLoginUserRoleCodeList();
        String roleCode = RoleCodeUtil.getLoginUserFyemRoleCode(roleCodes);
        // 仅能由县级、市级填报员、省级填报员、部级填报员删除数据
        if (!(roleCode.equals(RoleLevelEnum.LEVEL_COUNTY.getLevel())
                || roleCode.equals(RoleLevelEnum.LEVEL_CITY_ADD.getLevel())
                || roleCode.equals(RoleLevelEnum.LEVEL_PROVINCE_ADD.getLevel())
                || roleCode.equals(RoleLevelEnum.LEVEL_MASTER_ADD.getLevel()))) {
            throw new SofnException("非填报员不许删除！");
        }

        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        BasicProliferationRelease basicProliferationRelease = basicProliferationReleaseMapper.getBPRById(params);
        String belongYear = basicProliferationRelease != null ? basicProliferationRelease.getBelongYear() : "";
        String countyId = basicProliferationRelease != null ? basicProliferationRelease.getCountyId() : "";
        assert basicProliferationRelease != null;
        if (!reporManagementService.able2Modify(belongYear)) {
            throw new SofnException("存在已上报未退回的数据");
        }
        String loginUserId = UserUtil.getLoginUserId();

        //区、县级删除数据判断
        if(roleCode.equals(RoleLevelEnum.LEVEL_COUNTY.getLevel()) && basicProliferationRelease != null){
            BasicProliferationReleaseForm basicProliferationReleaseForm = new BasicProliferationReleaseForm();
            basicProliferationReleaseForm.setProvinceId(basicProliferationRelease.getProvinceId());
            basicProliferationReleaseForm.setCityId(basicProliferationRelease.getCityId());
            basicProliferationReleaseForm.setCountyId(basicProliferationRelease.getCountyId());
            basicProliferationReleaseForm.setBelongYear(basicProliferationRelease.getBelongYear());
            boolean b = this.countyOperationData(basicProliferationReleaseForm, roleCode);
            if(!b){
                throw new SofnException("当前年数据已上报或上报数据非市退回状态，不允许删除！");
            }
        }

        int result = 0;
        if (loginUserId.equals(basicProliferationRelease.getCreateUserId())) {
            // 创建者id与当前用户id相同时，允许删除
            try {
                result = basicProliferationReleaseMapper.deleteById(id);
            } catch (Exception e) {
                log.error("水生生物增殖放流基础数据删除报错:" + e.getMessage());
                throw new SofnException("删除失败！");
            }
        } else {
            return "非原填报员不许删除！";
        }

        // 同步删除县级上报管理表记录
        Map<String, Object> bprParams = new HashMap<>();
        bprParams.put("belongYear", belongYear);
        bprParams.put("countyId", countyId);
        bprParams.put("createUserId", loginUserId);
        Map<String, Object> reporParams = new HashMap<>();
        reporParams.put("belongYear", belongYear);
        reporParams.put("createUserId", loginUserId);

        int countOfBPR = basicProliferationReleaseMapper.countBasicProliferationRelease(bprParams);
        int countOfPRS = proliferationReleaseStatisticsMapper.countProliferationReleaseStatistics(reporParams);
        try {
            if (countOfBPR == 0 && countOfPRS == 0) {
                reporManagementMapper.deleteByParams(reporParams);
            }
        } catch (Exception e) {
            log.error("县级上报管理数据删除报错:" + e.getMessage());
            throw new SofnException("县级上报管理数据删除失败！");
        }

        if (result == 0) {
            return "0";
        } else {
            return "1";
        }
    }

    @Override
    public BasicProliferationReleaseForm getBPRById(String id) {
        BasicProliferationRelease bpr = null;
        Map params = new HashMap();
        params.put("id", id);
        try {
            bpr = basicProliferationReleaseMapper.getBPRById(params);
        } catch (Exception e) {
            log.error("水生生物增殖放流基础数据获取报错:" + e.getMessage());
            throw new SofnException("获取失败！");
        }

        // 转为表单对象
        BasicProliferationReleaseForm form = BasicProliferationReleaseForm.getBasicProliferationReleaseForm(bpr);
        // 转换省市县名称、放流级别名称、附件名
        releaseAreaAndReleaseLevelAndAccessoryConvert(form);
        return form;
    }

    @Override
    public List<BasicProliferationReleaseForm> getBasicProliferationReleaseList(Map<String, Object> params) {
        List<BasicProliferationReleaseForm> basicProliferationReleaseFormList = null;
        try {
            basicProliferationReleaseFormList = basicProliferationReleaseMapper.getBasicProliferationReleaseList(params);
        } catch (Exception e) {
            log.error("水生生物增殖放流基础数据市级获取报错:" + e.getMessage());
            throw new SofnException("获取失败！");
        }
        return basicProliferationReleaseFormList;
    }

    @Override
    public List<ProliferationReleaseInfosVo> getProliferationReleaseInfos(Map<String, Object> params) {
        String provinceId = (String) params.get("provinceId");
        String cityId = (String) params.get("cityId");
        String countyId = (String) params.get("countyId");
        List<ProliferationReleaseInfosVo> proliferationReleaseInfos = null;

        if (StringUtils.isBlank(provinceId) && StringUtils.isBlank(cityId)
                && StringUtils.isBlank(countyId)) {
            // 首次进入,使用角色所属机构的省市县id
            List<FyemArea> fyemAreaList = FyemAreaUtil.getUserAreaIdByLogUser(sysRegionApi);
            FyemArea area = fyemAreaList.size() != 0 ? fyemAreaList.get(0): new FyemArea() ;
            String level = area.getLevel();
            switch (level) {
                case "county":
                    countyId = area.getCountyId();
                    break;
                case "city":
                    cityId = area.getCityId();
                    break;
                case "province":
                    provinceId = area.getProvinceId();
                    break;
                case "ministry":
                    break;
            }
            params.put("provinceId", provinceId);
            params.put("cityId", cityId);
            params.put("countyId", countyId);
        }

        if (StringUtils.isNotBlank(countyId)) {
            // 传入所属年度、省id、市id、区县id
            proliferationReleaseInfos = basicProliferationReleaseMapper.getProliferationReleaseInfosByCountyId(params);
            // 对数据统计合计
            proliferationReleaseInfos = sumProliferationReleaseInfos(proliferationReleaseInfos, countyId, 0);
            // 转换区域名称（区县）
            String regionId = proliferationReleaseInfos.get(0).getRegion();
            String regionName = sysRegionApi.getSysRegionName(regionId).getData();
            proliferationReleaseInfos.get(0).setRegion(regionName);
            return proliferationReleaseInfos;
        }
        if (StringUtils.isNotBlank(cityId)) {
            // 传入所属年度、省id、市id
            proliferationReleaseInfos = basicProliferationReleaseMapper.getProliferationReleaseInfosByCityId(params);
            // 对数据统计合计、转换区域名称
            proliferationReleaseInfos = sumProliferationReleaseInfos(proliferationReleaseInfos, cityId, 0);
            convertRegionName(proliferationReleaseInfos);
            return proliferationReleaseInfos;
        }
        if (StringUtils.isNotBlank(provinceId)) {
            // 传入所属年度、省id
            proliferationReleaseInfos = basicProliferationReleaseMapper.getProliferationReleaseInfosByProvinceId(params);
            proliferationReleaseInfos = sumProliferationReleaseInfos(proliferationReleaseInfos, provinceId, 0);
            convertRegionName(proliferationReleaseInfos);
            return proliferationReleaseInfos;
        } else {
            // 传入所属年度
            proliferationReleaseInfos = basicProliferationReleaseMapper.getProliferationReleaseInfos(params);
            proliferationReleaseInfos = sumProliferationReleaseInfos(proliferationReleaseInfos, "", 0);
            convertRegionName(proliferationReleaseInfos);
            return proliferationReleaseInfos;
        }
    }

    @Override
    public PageUtils<ProliferationReleaseInfosVo> getProliferationReleaseInfosByPage(Map<String, Object> params) {
        String provinceId = (String) params.get("provinceId");
        String cityId = (String) params.get("cityId");
        String countyId = (String) params.get("countyId");
        int pageNo = (int) params.get("pageNo");
        int pageSize = (int) params.get("pageSize");

        if (StringUtils.isBlank(provinceId) && StringUtils.isBlank(cityId)
                && StringUtils.isBlank(countyId)) {
            // 首次进入,使用角色所属机构的省市县id
            List<FyemArea> fyemAreaList = FyemAreaUtil.getUserAreaIdByLogUser(sysRegionApi);
            FyemArea area = fyemAreaList.size() != 0 ? fyemAreaList.get(0): new FyemArea() ;
            String level = area.getLevel();
            switch (level) {
                case "county":
                    countyId = area.getCountyId();
                    break;
                case "city":
                    cityId = area.getCityId();
                    break;
                case "province":
                    provinceId = area.getProvinceId();
                    break;
                case "ministry":
                    break;
            }
            params.put("provinceId", provinceId);
            params.put("cityId", cityId);
            params.put("countyId", countyId);
        }

        PageInfo<ProliferationReleaseInfosVo> pageInfo = null;
        List<ProliferationReleaseInfosVo> proliferationReleaseInfos = null;
        if (StringUtils.isNotBlank(countyId)) {
            // 传入所属年度、省id、市id、区县id
            PageHelper.offsetPage(pageNo, pageSize);
            proliferationReleaseInfos = basicProliferationReleaseMapper.getProliferationReleaseInfosByCountyId(params);
            // 对数据统计合计
            proliferationReleaseInfos = sumProliferationReleaseInfos(proliferationReleaseInfos, countyId, pageNo);
            convertRegionName(proliferationReleaseInfos);
            pageInfo = new PageInfo<>(proliferationReleaseInfos);
            return PageUtils.getPageUtils(pageInfo);
        }
        if (StringUtils.isNotBlank(cityId)) {
            // 传入所属年度、省id、市id
            PageHelper.offsetPage(pageNo, pageSize);
            proliferationReleaseInfos = basicProliferationReleaseMapper.getProliferationReleaseInfosByCityId(params);
            // 对数据统计合计、转换区域名称
            proliferationReleaseInfos = sumProliferationReleaseInfos(proliferationReleaseInfos, cityId, pageNo);
            convertRegionName(proliferationReleaseInfos);
            pageInfo = new PageInfo<>(proliferationReleaseInfos);
            return PageUtils.getPageUtils(pageInfo);
        }
        if (StringUtils.isNotBlank(provinceId)) {
            // 传入所属年度、省id
            PageHelper.offsetPage(pageNo, pageSize);
            proliferationReleaseInfos = basicProliferationReleaseMapper.getProliferationReleaseInfosByProvinceId(params);
            proliferationReleaseInfos = sumProliferationReleaseInfos(proliferationReleaseInfos, provinceId, pageNo);
            convertRegionName(proliferationReleaseInfos);
            pageInfo = new PageInfo<>(proliferationReleaseInfos);
            return PageUtils.getPageUtils(pageInfo);
        } else {
            // 传入所属年度
            PageHelper.offsetPage(pageNo, pageSize);
            proliferationReleaseInfos = basicProliferationReleaseMapper.getProliferationReleaseInfos(params);
            proliferationReleaseInfos = sumProliferationReleaseInfos(proliferationReleaseInfos, "", pageNo);
            convertRegionName(proliferationReleaseInfos);
            pageInfo = new PageInfo<>(proliferationReleaseInfos);
            return PageUtils.getPageUtils(pageInfo);
        }
    }

    @Override
    public List<BasicProliferationReleaseVO> getBasicProliferationReleaseListByQuery(Map<String, Object> params) {
        List<BasicProliferationReleaseVO> voList = new ArrayList<>();
        List<BasicProliferationRelease> basicList = basicProliferationReleaseMapper.getBasicProliferationReleaseListByQuery(params);
        basicList.forEach(
                baseData ->{
                    BasicProliferationReleaseVO vo = new BasicProliferationReleaseVO();
                    Result<List<SysRegionTreeVo>> parentNode = sysRegionApi.getParentNode(baseData.getCountyId());
                    List<SysRegionTreeVo> data = parentNode.getData();
                    //判断获取地址相关数据是否存在
                    if (data.size() > 0) {
                        //获取省名称
                        SysRegionTreeVo province = data.get(0);
                        String provinceName = province.getRegionName();
                        //获取市名称
                        SysRegionTreeVo city = data.get(1);
                        String cityName = city.getRegionName();
                        //获取区县名称
                        SysRegionTreeVo county = data.get(2);
                        String countyName = county.getRegionName();
                        //拼接市区县详细地址
                        String areaName = provinceName + cityName + countyName;
                        vo.setReleaseArea(areaName);
                    } else {
                        vo.setReleaseArea("-------");
                    }
                    BeanUtils.copyProperties(baseData, vo);
                    voList.add(vo);
                }
        );
        return voList;
    }

    @Override
    public void updateReleaseEvaluate(Map<String, Object> params) {
        basicProliferationReleaseMapper.updateReleaseEvaluate(params);
    }


    @Override
    public BasicProliferationRelease getBasicProliferationReleaseById(String id) {
        return basicProliferationReleaseMapper.getBasicProliferationReleaseById(id);
    }

    @Override
    public List<BasicProliferationReleaseExcel> getBasicProliferationReleaseExcel(Map<String, Object> params) {
        List<BasicProliferationReleaseExcel> excelList = new ArrayList<>();
        String belongYear = params.get("belongYear").toString();
        params.put("belongYear",belongYear);
        List<BasicProliferationRelease> basicProliferationReleaseList = basicProliferationReleaseMapper.getBasicProliferationReleaseListByQuery(params);
        basicProliferationReleaseList.forEach(
                baseData -> {
                    BasicProliferationReleaseExcel basicExcel = new BasicProliferationReleaseExcel();
                    Result<List<SysRegionTreeVo>> parentNode = sysRegionApi.getParentNode(baseData.getCountyId());
                    List<SysRegionTreeVo> data = parentNode.getData();
                    //判断获取地址相关数据是否存在
                    if (data.size() > 0) {
                        //获取省名称
                        SysRegionTreeVo province = data.get(0);
                        String provinceName = province.getRegionName();
                        //获取市名称
                        SysRegionTreeVo city = data.get(1);
                        String cityName = city.getRegionName();
                        //获取区县名称
                        SysRegionTreeVo county = data.get(2);
                        String countyName = county.getRegionName();
                        //拼接市区县详细地址
                        String areaName = provinceName + cityName + countyName;
                        basicExcel.setAreaName(areaName);
                    } else {
                        basicExcel.setAreaName("---");
                    }
                    BeanUtils.copyProperties(baseData, basicExcel);
                    excelList.add(basicExcel);
                }
        );
        return excelList;
    }

    @Override
    public List<ProliferationReleaseExcel> getProliferationReleaseExcel(Map<String, Object> params) {
        List<ProliferationReleaseExcel> excelList = new ArrayList<>();
        List<ProliferationReleaseInfosVo> proliferationReleaseInfosVoList = getProliferationReleaseInfos(params);
        proliferationReleaseInfosVoList.forEach(
                baseData -> {
                    ProliferationReleaseExcel proliferationReleaseExcel = new ProliferationReleaseExcel();
                    BeanUtils.copyProperties(baseData, proliferationReleaseExcel);
                    excelList.add(proliferationReleaseExcel);
                }
        );
        return excelList;
    }

    @Override
    public PageUtils<ProliferationReleaseLocationDistributionVo> getProliferationReleaseDistributionInfosByPage(Map<String, Object> params) {
        String provinceId = (String) params.get("provinceId");
        String cityId = (String) params.get("cityId");
        String countyId = (String) params.get("countyId");
        int pageNo = (int) params.get("pageNo");
        int pageSize = (int) params.get("pageSize");

        PageInfo<ProliferationReleaseLocationDistributionVo> pageInfo = null;
        List<ProliferationReleaseLocationDistributionVo> proliferationReleaseLocationDistributionVoList = null;
        if (!StringUtils.isBlank(provinceId)) {
            if (!StringUtils.isBlank(cityId)) {
                if (!StringUtils.isBlank(countyId)) {
                    // 传入所属年度、省id、市id、区县id
                    PageHelper.offsetPage(pageNo, pageSize);
                    proliferationReleaseLocationDistributionVoList =
                            basicProliferationReleaseMapper.getProliferationReleaseLocationDistributionByCountyId(params);
                    pageInfo = new PageInfo<>(proliferationReleaseLocationDistributionVoList);
                } else {
                    // 传入所属年度、省id、市id
                    PageHelper.offsetPage(pageNo, pageSize);
                    proliferationReleaseLocationDistributionVoList =
                            basicProliferationReleaseMapper.getProliferationReleaseLocationDistributionByCityId(params);
                    // 转换区域名称
                    convertDistributionRegionName(proliferationReleaseLocationDistributionVoList);
                    pageInfo = new PageInfo<>(proliferationReleaseLocationDistributionVoList);
                }
            } else {
                // 传入所属年度、省id
                PageHelper.offsetPage(pageNo, pageSize);
                proliferationReleaseLocationDistributionVoList =
                        basicProliferationReleaseMapper.getProliferationReleaseLocationDistributionByProvinceId(params);
                convertDistributionRegionName(proliferationReleaseLocationDistributionVoList);
                pageInfo = new PageInfo<>(proliferationReleaseLocationDistributionVoList);
            }
        } else {
            // 传入所属年度
            PageHelper.offsetPage(pageNo, pageSize);
            proliferationReleaseLocationDistributionVoList =
                    basicProliferationReleaseMapper.getProliferationReleaseLocationDistribution(params);
            convertDistributionRegionName(proliferationReleaseLocationDistributionVoList);
            pageInfo = new PageInfo<>(proliferationReleaseLocationDistributionVoList);
        }
        return PageUtils.getPageUtils(pageInfo);
    }

    /** 废弃 */
    @Override
    public List<ProliferationReleaseLocationDistributionVo> getProliferationReleaseDistributionInfos(Map<String, Object> params) {
        String provinceId = (String) params.get("provinceId");

        List<FyemArea> fyemAreaList = FyemAreaUtil.getUserAreaIdByLogUser(sysRegionApi);
        FyemArea area = fyemAreaList.size() != 0 ? fyemAreaList.get(0): new FyemArea() ;
        String level = area.getLevel();
        // 省市县级用户都采用所属机构的省id获取数据
        switch (level) {
            case "county":
            case "city":
            case "province": provinceId = area.getProvinceId();break;
        }
        params.put("provinceId", provinceId);

        List<ProliferationReleaseLocationDistributionVo> proliferationReleaseLocationDistributionVoList = null;
        if (StringUtils.isNotBlank(provinceId)) {
            // 传入所属年度、省id
            proliferationReleaseLocationDistributionVoList =
                    basicProliferationReleaseMapper.getProliferationReleaseLocationDistributionByProvinceId(params);
            convertDistributionRegionName(proliferationReleaseLocationDistributionVoList);
        } else {
            // 传入所属年度
            proliferationReleaseLocationDistributionVoList =
                    basicProliferationReleaseMapper.getProliferationReleaseLocationDistribution(params);
            convertDistributionRegionName(proliferationReleaseLocationDistributionVoList);
        }
        return proliferationReleaseLocationDistributionVoList;
    }

    @Override
    public MapViewData getMapViewData(String index, String adLevel, String adCode, Map<String, String> conditions) {

        // 非法指标禁止查询
        if (!IndexEnum.indexList().contains(index)){
            throw new SofnException("非法指标！");
        }

        // 仅部级审核员查看地图
        List<String> roleCodes = UserUtil.getLoginUserRoleCodeList();
        String roleCode = RoleCodeUtil.getLoginUserFyemRoleCode(roleCodes);
        if (!RoleLevelEnum.LEVEL_MASTER.getLevel().equals(roleCode)){
            throw new SofnException("仅部级审核员查看！");
        }

        // 准备参数
        Map<String, Object> params = new HashMap();
        params.put("belongYear", conditions.get("belongYear"));
        AdLevelEnum adLevelEnum = AdLevelEnum.getEnum(adLevel);
        switch (adLevelEnum){
            case LEVEL_PROVINCE : params.put("provinceId", adCode);break;
            case LEVEL_CITY : params.put("cityId", adCode);break;
            case LEVEL_COUNTY : params.put("countyId", adCode);break;
        }

        // 根据指标分别获取统计结果
        MapViewData mapViewData = null;
        IndexEnum indexEnum = IndexEnum.getEnum(index);
        switch (indexEnum){
            case RELEASES_POINTS_DTB : mapViewData = getReleasePointsDistribute(index, adLevel, params);break;
            case RELEASES_COUNT : mapViewData = getReleaseCount(index, adLevel, params);break;
            case INVEST_FUNDS : mapViewData = getInvestFunds(index, adLevel, params);break;
            case RELEASES_AC_COUNT : mapViewData = getReleaseActivitiesCount(index, adLevel, params);break;
            default : mapViewData = new MapViewData();break;
        }

        return mapViewData;
    }

    /**
     * 获取放流点位分布（分布图示，描点）
     */
    private MapViewData getReleasePointsDistribute(String index, String adLevel, Map<String, Object> params){
        MapViewData mapViewData = new MapViewData();
        List<ProliferationReleaseLocationDistributionVo> proliferationReleaseDistributionInfos = new ArrayList<>();
        mapViewData.setAdLevel(adLevel);
        mapViewData.setViewType(MapViewType.POINT.getCode());

        // 描点数据
        List<AdPointData> adPointDataList = new ArrayList<>();
        if (AdLevelEnum.LEVEL_MASTER.getLevel().equals(adLevel)
                || AdLevelEnum.LEVEL_PROVINCE.getLevel().equals(adLevel)
                || AdLevelEnum.LEVEL_CITY.getLevel().equals(adLevel)){
            // 放流次数
            proliferationReleaseDistributionInfos =
                    basicProliferationReleaseMapper.getProliferationReleaseLocationDistribution(params);
            if (proliferationReleaseDistributionInfos == null || proliferationReleaseDistributionInfos.isEmpty()){
                return new MapViewData();
            }
            convertDistributionRegionName(proliferationReleaseDistributionInfos);
            // 组装放流次数数据
            proliferationReleaseDistributionInfos.forEach(p ->{
                AdPointData adPointData = new AdPointData();
                adPointData.setLongitude(p.getLongitude().toString());
                adPointData.setLatitude(p.getLatitude().toString());
                adPointData.setChartType(MapViewType.POINT.getCode());
                adPointData.setAdRegionCode(p.getRegion());
                adPointData.setAdRegionName(p.getRegionName());

                Map<String, Object> indexInfo = new HashMap<>();
                indexInfo.put(index, IndexEnum.toMap().get(index));
                adPointData.setIndexInfo(indexInfo);

                adPointData.setIndexLabelList(new HashMap<>());
                adPointData.setIndexValueLevel("1");

                StringBuilder builder = new StringBuilder();
                builder.append("流放地点:");
                builder.append(p.getRegionName()+",");
                builder.append("流放次数:");
                builder.append(p.getReleaseCountTotal());
                String indexValue = builder.toString();

                adPointData.setIndexValue(indexValue);
                adPointDataList.add(adPointData);
            });
        }else if (AdLevelEnum.LEVEL_COUNTY.getLevel().equals(adLevel)){
            // 点位分布
            proliferationReleaseDistributionInfos =
                    basicProliferationReleaseMapper.getProliferationReleasePoints(params);

            if (proliferationReleaseDistributionInfos == null || proliferationReleaseDistributionInfos.isEmpty()){
                return new MapViewData();
            }
            convertDistributionRegionName(proliferationReleaseDistributionInfos);
            // 组装点位数据
            proliferationReleaseDistributionInfos.forEach(p -> {
                AdPointData adPointData = new AdPointData();
                adPointData.setLongitude(p.getLongitude().toString());
                adPointData.setLatitude(p.getLatitude().toString());
                adPointData.setChartType(MapViewType.POINT.getCode());
                adPointData.setAdRegionCode(p.getRegion());
                adPointData.setAdRegionName(p.getRegionName());

                Map<String, Object> indexInfo = new HashMap<>();
                indexInfo.put(index, IndexEnum.toMap().get(index));
                adPointData.setIndexInfo(indexInfo);

                adPointData.setIndexLabelList(new HashMap<>());
                adPointData.setIndexValueLevel("1");

                StringBuilder builder = new StringBuilder();
                builder.append("流放地点:");
                builder.append(p.getReleaseSite()+",");
                builder.append("流放时间:");
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate parse = p.getReleaseTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                String releaseTime = dateTimeFormatter.format(parse);
                builder.append(releaseTime+",");
                builder.append("流放资金（万元）:");
                builder.append(p.getReleaseMoney()+",");
                builder.append("组织名称:");
                builder.append(p.getOrganizationName()+",");
                builder.append("流放级别:");
                builder.append(p.getReleaseLevel()+",");
                builder.append("流放品种:");
                builder.append(p.getReleaseVarieties()+",");
                builder.append("流放数量（万尾）:");
                builder.append(p.getReleaseNumber()+",");
                builder.append("流放规格:");
                builder.append(p.getReleaseSpecification()+",");
                builder.append("供苗单位名称:");
                builder.append(p.getProvideOrganizationName());
                String indexValue = builder.toString();

                adPointData.setIndexValue(indexValue);
                adPointDataList.add(adPointData);
            });
        }

        Map<String, List<AdPointData>> adPointMap = new HashMap<>();
        adPointMap.put(IndexEnum.RELEASES_POINTS_DTB.getIndex(),adPointDataList);
        mapViewData.setAdPointDataList(adPointMap);
        mapViewData.setAdAreaDataList(Maps.newHashMap());

        // 统计数据
        AdStatisticsData adStatisticsData = new AdStatisticsData();
        // 维度
        Map<String, String> dimensionMap = new HashMap<>();
        dimensionMap.put(index, IndexEnum.toMap().get(index));
        adStatisticsData.setDimensionList(dimensionMap);
        // 数据
        if (AdLevelEnum.LEVEL_MASTER.getLevel().equals(adLevel)
                || AdLevelEnum.LEVEL_PROVINCE.getLevel().equals(adLevel)
                || AdLevelEnum.LEVEL_CITY.getLevel().equals(adLevel)){
            // 部级、省级、市级统计放流次数
            // 表头
            Map<String, String> headMap = new HashMap<>();
            headMap.put("num", "序号");
            headMap.put("area", "行政区划");
            headMap.put("dimension", "放流次数");
            adStatisticsData.setHeaderMap(headMap);
            List<Map<String, Object>> dimeObjList = new ArrayList<>();
            for (int i=0; i < proliferationReleaseDistributionInfos.size(); i++){
                Map<String, Object> map = new HashMap<>();
                map.put("num", i + 1);
                map.put("area", proliferationReleaseDistributionInfos.get(i).getRegionName());
                map.put("dimension", proliferationReleaseDistributionInfos.get(i).getReleaseCountTotal());
                dimeObjList.add(map);
            }
            Map<String, List<Map<String, Object>>> dataMap = new HashMap<>();
            dataMap.put(index,dimeObjList);
            adStatisticsData.setDataMap(dataMap);
        }else if (AdLevelEnum.LEVEL_COUNTY.getLevel().equals(adLevel)){
            // 县级统计放流活动点分布
            // 表头
            Map<String, String> headMap = new HashMap<>();
            headMap.put("num", "序号");
            headMap.put("area", "行政区划");
            headMap.put("dimension", "放流次数");
            adStatisticsData.setHeaderMap(headMap);

            List<Map<String, Object>> dimeObjList = new ArrayList<>();
            Map<String, Object> map = new HashMap<>();
            map.put("num", 1);
            map.put("area", proliferationReleaseDistributionInfos.get(0).getRegionName());
            map.put("dimension", proliferationReleaseDistributionInfos.size());
            dimeObjList.add(map);

            Map<String, List<Map<String, Object>>> dataMap = new HashMap<>();
            dataMap.put(index,dimeObjList);
            adStatisticsData.setDataMap(dataMap);
        }

        mapViewData.setAdStatisticsData(adStatisticsData);

        return mapViewData;
    }

    /**
     * 获取放流数量（分布图示，图块）
     */
    private MapViewData getReleaseCount(String index, String adLevel, Map<String, Object> params){
        MapViewData mapViewData = new MapViewData();
        List<ProliferationReleaseLocationDistributionVo> proliferationReleaseDistributionInfos = new ArrayList<>();
        mapViewData.setAdLevel(adLevel);
        mapViewData.setViewType(MapViewType.AREA.getCode());
        // 放流数量
        proliferationReleaseDistributionInfos = basicProliferationReleaseMapper.getProliferationReleaseCount(params);
        if (proliferationReleaseDistributionInfos == null || proliferationReleaseDistributionInfos.isEmpty()){
            return new MapViewData();
        }
        convertDistributionRegionName(proliferationReleaseDistributionInfos);

        Map<String,List<AdAreaData>> adAreaDataList = new HashMap<>();
        List<AdAreaData> areaDataList = new ArrayList<>();
        proliferationReleaseDistributionInfos.forEach( p -> {
            AdAreaData adAreaData = new AdAreaData();
            adAreaData.setAdRegionCode(p.getRegion());
            adAreaData.setAdRegionName(p.getRegionName());
            adAreaData.setIndexValue(p.getReleaseNumberTotal());

            Map<String, Object> indexInfo = new HashMap<>();
            indexInfo.put(index, IndexEnum.toMap().get(index));
            adAreaData.setIndexInfo(indexInfo);

            // 放入数据种类
            Map<String,String> indexLabelList = new HashMap<>();
            indexLabelList.put("1", "0-5000");
            indexLabelList.put("2", "5001-10000");
            indexLabelList.put("3", "10001-50000");
            indexLabelList.put("4", "50001-100000");
            indexLabelList.put("5", "100001-200000");
            indexLabelList.put("6", ">200000");
            adAreaData.setIndexLabelList(indexLabelList);

            // 设置颜色档位
            Double indexValue = Double.valueOf(p.getReleaseNumberTotal());
            if (indexValue >= 0 && indexValue <= 5000){
                adAreaData.setIndexValueLevel("1");
            }else if (indexValue <= 10000){
                adAreaData.setIndexValueLevel("2");
            }else if (indexValue <= 50000){
                adAreaData.setIndexValueLevel("3");
            }else if (indexValue <= 100000){
                adAreaData.setIndexValueLevel("4");
            }else if (indexValue <= 200000){
                adAreaData.setIndexValueLevel("5");
            }else if (indexValue > 200000){
                adAreaData.setIndexValueLevel("6");
            }
            areaDataList.add(adAreaData);
        });
        adAreaDataList.put(IndexEnum.RELEASES_COUNT.getIndex(),areaDataList);
        mapViewData.setAdAreaDataList(adAreaDataList);
        mapViewData.setAdPointDataList(Maps.newHashMap());

        // 统计数据
        AdStatisticsData adStatisticsData = new AdStatisticsData();
        // 表头
        Map<String, String> headMap = new HashMap<>();
        headMap.put("num", "序号");
        headMap.put("area", "行政区划");
        headMap.put("dimension", IndexEnum.toMap().get(index));
        adStatisticsData.setHeaderMap(headMap);
        // 维度
        Map<String, String> dimensionMap = new HashMap<>();
        dimensionMap.put(index, IndexEnum.toMap().get(index));
        adStatisticsData.setDimensionList(dimensionMap);
        // 数据
        List<Map<String, Object>> dimeObjList = new ArrayList<>();
        for (int i=0; i < proliferationReleaseDistributionInfos.size(); i++){
            Map<String, Object> map = new HashMap<>();
            map.put("num", i + 1);
            map.put("area", proliferationReleaseDistributionInfos.get(i).getRegionName());
            map.put("dimension", proliferationReleaseDistributionInfos.get(i).getReleaseNumberTotal());
            dimeObjList.add(map);
        }
        Map<String, List<Map<String, Object>>> dataMap = new HashMap<>();
        dataMap.put(index,dimeObjList);
        adStatisticsData.setDataMap(dataMap);

        mapViewData.setAdStatisticsData(adStatisticsData);

        return mapViewData;
    }

    /**
     * 获取投入资金（分布图示，饼图）
     */
    private MapViewData getInvestFunds(String index, String adLevel, Map<String, Object> params){
        MapViewData mapViewData = new MapViewData();
        List<ProliferationReleaseLocationDistributionVo> proliferationReleaseDistributionInfos = new ArrayList<>();

        mapViewData.setAdLevel(adLevel);
        mapViewData.setViewType(MapViewType.POINT.getCode());
        // 投入资金
        proliferationReleaseDistributionInfos =
                basicProliferationReleaseMapper.getProliferationReleaseInvestFunds(params);
        if (proliferationReleaseDistributionInfos == null || proliferationReleaseDistributionInfos.isEmpty()){
            return new MapViewData();
        }
        convertDistributionRegionName(proliferationReleaseDistributionInfos);

        // 组装描点数据（饼图）
        List<AdPointData> adPointDataList = new ArrayList<>();
        proliferationReleaseDistributionInfos.forEach(p ->{
            AdPointData adPointData = new AdPointData();
            adPointData.setLongitude(p.getLongitude());
            adPointData.setLatitude(p.getLatitude());
            adPointData.setChartType(MapViewType.PANCAKE.getCode());
            adPointData.setAdRegionCode(p.getRegion());
            adPointData.setAdRegionName(p.getRegionName());

            Map<String, Object> indexInfo = new HashMap<>();
            indexInfo.put(index, IndexEnum.toMap().get(index));
            adPointData.setIndexInfo(indexInfo);

            // 放入数据种类
            Map<String,String> indexLabelList = new HashMap<>();
            indexLabelList.put("nationInvestment", "中央投资（万元）");
            indexLabelList.put("provinceInvestment", "省级投资（万元）");
            indexLabelList.put("cityInvestment", "市县投资（万元）");
            indexLabelList.put("societyInvestment", "社会投资（万元）");
            adPointData.setIndexLabelList(indexLabelList);
            adPointData.setIndexValueLevel("1");

            StringBuilder builder = new StringBuilder();
            builder.append(p.getRegionName()+",");
            builder.append("总额（万元）:");
            builder.append(p.getInvestmentTotal()+",");
            builder.append("中央投资（万元）:");
            builder.append(p.getNationInvestment()+",");
            builder.append("省级投资（万元）:");
            builder.append(p.getProvinceInvestment()+",");
            builder.append("市县投资（万元）:");
            builder.append(p.getCityInvestment()+",");
            builder.append("社会投资（万元）:");
            builder.append(p.getSocietyInvestment());
            String indexValue = builder.toString();

            adPointData.setIndexValue(indexValue);
            adPointDataList.add(adPointData);
        });
        Map<String, List<AdPointData>> adPointMap = new HashMap<>();
        adPointMap.put(IndexEnum.INVEST_FUNDS.getIndex(),adPointDataList);
        mapViewData.setAdPointDataList(adPointMap);
        mapViewData.setAdAreaDataList(Maps.newHashMap());

        // 统计数据
        AdStatisticsData adStatisticsData = new AdStatisticsData();
        // 表头
        Map<String, String> headMap = new HashMap<>();
        headMap.put("num", "序号");
        headMap.put("area", "行政区划");
        headMap.put("dimension", IndexEnum.toMap().get(index));
        adStatisticsData.setHeaderMap(headMap);
        // 维度
        Map<String, String> dimensionMap = new HashMap<>();
        dimensionMap.put(index, IndexEnum.toMap().get(index));
        adStatisticsData.setDimensionList(dimensionMap);
        // 数据
        List<Map<String, Object>> dimeObjList = new ArrayList<>();
        for (int i=0; i < proliferationReleaseDistributionInfos.size(); i++){
            Map<String, Object> map = new HashMap<>();
            map.put("num", i + 1);
            map.put("area", proliferationReleaseDistributionInfos.get(i).getRegionName());
            map.put("dimension", proliferationReleaseDistributionInfos.get(i).getInvestmentTotal());
            dimeObjList.add(map);
        }
        Map<String, List<Map<String, Object>>> dataMap = new HashMap<>();
        dataMap.put(index,dimeObjList);
        adStatisticsData.setDataMap(dataMap);

        mapViewData.setAdStatisticsData(adStatisticsData);

        return mapViewData;
    }

    /**
     * 获取放流活动次数（分布图示，饼图）
     */
    private MapViewData getReleaseActivitiesCount(String index, String adLevel, Map<String, Object> params){
        MapViewData mapViewData = new MapViewData();
        List<ProliferationReleaseLocationDistributionVo> proliferationReleaseDistributionInfos = new ArrayList<>();

        mapViewData.setAdLevel(adLevel);
        mapViewData.setViewType(MapViewType.POINT.getCode());
        // 放流次数
        proliferationReleaseDistributionInfos =
                basicProliferationReleaseMapper.getProliferationReleaseLocationDistribution(params);
        if (proliferationReleaseDistributionInfos == null || proliferationReleaseDistributionInfos.isEmpty()){
            return new MapViewData();
        }
        convertDistributionRegionName(proliferationReleaseDistributionInfos);

        // 组装描点数据（饼图）
        List<AdPointData> adPointDataList = new ArrayList<>();
        proliferationReleaseDistributionInfos.forEach(p ->{
            AdPointData adPointData = new AdPointData();
            adPointData.setLongitude(p.getLongitude());
            adPointData.setLatitude(p.getLatitude());
            adPointData.setChartType(MapViewType.PANCAKE.getCode());
            adPointData.setAdRegionCode(p.getRegion());
            adPointData.setAdRegionName(p.getRegionName());

            Map<String, Object> indexInfo = new HashMap<>();
            indexInfo.put(index, IndexEnum.toMap().get(index));
            adPointData.setIndexInfo(indexInfo);

            // 放入数据种类
            Map<String,String> indexLabelList = new HashMap<>();
            indexLabelList.put("releaseCountry", "国家级");
            indexLabelList.put("releaseProv", "省级");
            indexLabelList.put("releaseCity", "市县级");
            indexLabelList.put("releaseOther", "其他");
            adPointData.setIndexLabelList(indexLabelList);
            adPointData.setIndexValueLevel("1");

            StringBuilder builder = new StringBuilder();
            builder.append(p.getRegionName()+",");
            builder.append("国家级:");
            builder.append(p.getReleaseCountry()+",");
            builder.append("省级:");
            builder.append(p.getReleaseProv()+",");
            builder.append("市县级:");
            builder.append(p.getReleaseCity()+",");
            builder.append("其他:");
            builder.append(p.getReleaseOther());
            String indexValue = builder.toString();

            adPointData.setIndexValue(indexValue);
            adPointDataList.add(adPointData);
        });
        Map<String, List<AdPointData>> adPointMap = new HashMap<>();
        adPointMap.put(IndexEnum.RELEASES_AC_COUNT.getIndex(),adPointDataList);
        mapViewData.setAdPointDataList(adPointMap);
        mapViewData.setAdAreaDataList(Maps.newHashMap());

        // 统计数据
        AdStatisticsData adStatisticsData = new AdStatisticsData();
        // 表头
        Map<String, String> headMap = new HashMap<>();
        headMap.put("num", "序号");
        headMap.put("area", "行政区划");
        headMap.put("dimension", IndexEnum.toMap().get(index));
        adStatisticsData.setHeaderMap(headMap);
        // 维度
        Map<String, String> dimensionMap = new HashMap<>();
        dimensionMap.put(index, IndexEnum.toMap().get(index));
        adStatisticsData.setDimensionList(dimensionMap);
        // 数据
        List<Map<String, Object>> dimeObjList = new ArrayList<>();
        for (int i=0; i < proliferationReleaseDistributionInfos.size(); i++){
            Map<String, Object> map = new HashMap<>();
            map.put("num", i + 1);
            map.put("area", proliferationReleaseDistributionInfos.get(i).getRegionName());
            map.put("dimension", proliferationReleaseDistributionInfos.get(i).getReleaseCountTotal());
            dimeObjList.add(map);
        }
        Map<String, List<Map<String, Object>>> dataMap = new HashMap<>();
        dataMap.put(index,dimeObjList);
        adStatisticsData.setDataMap(dataMap);

        mapViewData.setAdStatisticsData(adStatisticsData);

        return mapViewData;
    }

    @Override
    public List<MapIndexs> getMapIndexs(String desc) {
        List<MapIndexs> mapIndexsList = new ArrayList<>();

        // 按指标组装指标下数据种类
        if(IndexEnum.RELEASES_POINTS_DTB.getDesc().contains(desc)){
            MapIndexs mapIndex = new MapIndexs();
            mapIndex.setCode(IndexEnum.RELEASES_POINTS_DTB.getIndex());
            mapIndex.setDesc(IndexEnum.RELEASES_POINTS_DTB.getDesc());
            Map<String,String> valueList = new HashMap<>();
            mapIndex.setValueList(valueList);
            mapIndexsList.add(mapIndex);
        }
        if(IndexEnum.RELEASES_COUNT.getDesc().contains(desc)){
            MapIndexs mapIndex = new MapIndexs();
            mapIndex.setCode(IndexEnum.RELEASES_COUNT.getIndex());
            mapIndex.setDesc(IndexEnum.RELEASES_COUNT.getDesc());
            Map<String,String> valueList = new HashMap<>();
            mapIndex.setValueList(valueList);
            mapIndexsList.add(mapIndex);
        }
        if(IndexEnum.INVEST_FUNDS.getDesc().contains(desc)){
            MapIndexs mapIndex = new MapIndexs();
            mapIndex.setCode(IndexEnum.INVEST_FUNDS.getIndex());
            mapIndex.setDesc(IndexEnum.INVEST_FUNDS.getDesc());
            Map<String,String> valueList = new HashMap<>();
            // 放入数据种类
            valueList.put("nationInvestment", "中央投资（万元）");
            valueList.put("provinceInvestment", "省级投资（万元）");
            valueList.put("cityInvestment", "市县投资（万元）");
            valueList.put("societyInvestment", "社会投资（万元）");
            mapIndex.setValueList(valueList);
            mapIndexsList.add(mapIndex);
        }
        if(IndexEnum.RELEASES_AC_COUNT.getDesc().contains(desc)){
            MapIndexs mapIndex = new MapIndexs();
            mapIndex.setCode(IndexEnum.RELEASES_AC_COUNT.getIndex());
            mapIndex.setDesc(IndexEnum.RELEASES_AC_COUNT.getDesc());
            Map<String,String> valueList = new HashMap<>();
            // 放入数据种类
            valueList.put("releaseCountry", "国家级");
            valueList.put("releaseProv", "省级");
            valueList.put("releaseCity", "市县级");
            valueList.put("releaseOther", "其他");
            mapIndex.setValueList(valueList);
            mapIndexsList.add(mapIndex);
        }

        return mapIndexsList;
    }

    @Override
    public List<BasicProliferationRelease> listBPRByBelongYearAndOrgId(String belongYear, String orgId) {
        if (org.apache.commons.lang3.StringUtils.isAnyBlank(belongYear, orgId)) {
            return Collections.emptyList();
        }

        List<BasicProliferationRelease> list = this.list(Wrappers.<BasicProliferationRelease>lambdaQuery()
                .eq(BasicProliferationRelease::getBelongYear, belongYear)
                .eq(BasicProliferationRelease::getOrganizationId, orgId));
        Result<SysOrganizationForm> orgInfoById = sysRegionApi.getOrgInfoById(orgId);
        if (Result.CODE.equals(orgInfoById.getCode())) {
            list.addAll(this.listBPRByBelongYearAndOrgId(belongYear, orgInfoById.getData().getParentId()));
        }
        return list;
    }

    @Override
    public List<MapConditions> getMapConditions(String index) {
        // 假数据，不使用
        List<MapConditions> mapConditionsList = new ArrayList<>();

        // 一个下拉框
        if("year".equals(index)){
            MapConditions mapConditions = new MapConditions();
            mapConditions.setCode("year");
            Map<String,String> valueList = new HashMap<>();
            for (int i = 0; i < 5; i++) {
                valueList.put(String.valueOf(2020-i),String.valueOf(2020-i).concat("年"));
            }
            mapConditions.setValueList(valueList);
            mapConditionsList.add(mapConditions);
        }
        if (IndexEnum.RELEASES_POINTS_DTB.getIndex().equals(index)){
            MapConditions mapConditions = new MapConditions();
            mapConditions.setCode(IndexEnum.RELEASES_POINTS_DTB.getIndex());
            Map<String,String> valueList = new HashMap<>();
            valueList.put(index,IndexEnum.RELEASES_POINTS_DTB.getDesc());
            mapConditions.setValueList(valueList);
            mapConditionsList.add(mapConditions);
        }
        if (IndexEnum.RELEASES_COUNT.getIndex().equals(index)){
            MapConditions mapConditions = new MapConditions();
            mapConditions.setCode(IndexEnum.RELEASES_COUNT.getIndex());
            Map<String,String> valueList = new HashMap<>();
            valueList.put(index,IndexEnum.RELEASES_COUNT.getDesc());
            mapConditions.setValueList(valueList);
            mapConditionsList.add(mapConditions);
        }
        if (IndexEnum.INVEST_FUNDS.getIndex().equals(index)){
            MapConditions mapConditions = new MapConditions();
            mapConditions.setCode(IndexEnum.INVEST_FUNDS.getIndex());
            Map<String,String> valueList = new HashMap<>();
            valueList.put(index,IndexEnum.INVEST_FUNDS.getDesc());
            mapConditions.setValueList(valueList);
            mapConditionsList.add(mapConditions);
        }
        if (IndexEnum.RELEASES_AC_COUNT.getIndex().equals(index)){
            MapConditions mapConditions = new MapConditions();
            mapConditions.setCode(IndexEnum.RELEASES_AC_COUNT.getIndex());
            Map<String,String> valueList = new HashMap<>();
            valueList.put(index,IndexEnum.RELEASES_AC_COUNT.getDesc());
            mapConditions.setValueList(valueList);
            mapConditionsList.add(mapConditions);
        }

        return mapConditionsList;
    }

    @Override
    public List<ProliferationReleaseDistributionExcel> getProliferationReleaseDistributionExcel(Map<String, Object> params) {
        List<ProliferationReleaseDistributionExcel> excelList = new ArrayList<>();
        List<ProliferationReleaseLocationDistributionVo> proliferationReleaseLocationDistributionVoList =
                getProliferationReleaseDistributionInfos(params);
        proliferationReleaseLocationDistributionVoList.forEach(
                baseData -> {
                    ProliferationReleaseDistributionExcel proliferationReleaseDistributionExcel =
                            new ProliferationReleaseDistributionExcel();
                    BeanUtils.copyProperties(baseData, proliferationReleaseDistributionExcel);
                    excelList.add(proliferationReleaseDistributionExcel);
                }
        );
        return excelList;
    }

    /**
     * 转换省市县名称、放流级别名称
     *
     * @param list
     */
    private void releaseAreaAndReleaseLevelConvert(List<BasicProliferationReleaseVO> list) {
        // 直辖市列表
        List<String> muniList = MunicipalityEnum.adCodesList();
        for (BasicProliferationReleaseVO vo : list) {
            // 转换地址名称
            Result<List<SysRegionTreeVo>> parentNode = sysRegionApi.getParentNode(vo.getCountyId());
            List<SysRegionTreeVo> data = parentNode.getData();
            //判断获取地址相关数据是否存在
            if (data != null && data.size() > 0) {
                //获取省名称
                SysRegionTreeVo province = data.get(0);
                String provinceName = province.getRegionName();
                //获取市名称
                SysRegionTreeVo city = data.get(1);
                String cityName = city.getRegionName();
                //获取区县名称
                SysRegionTreeVo county = data.get(2);
                String countyName = county.getRegionName();

                List<String> adCodes = data.stream().map(SysRegionTreeVo::getRegionCode).collect(Collectors.toList());
                // 市看区县，隐藏直辖市的市辖区、县的显示
                if (!Collections.disjoint(adCodes, muniList)){
                    cityName = data.size()>=3 ? "" : cityName;
                }

                //拼接市区县详细地址
                StringBuilder releaseArea = new StringBuilder();
                if (StringUtils.isNotBlank(provinceName)){
                    releaseArea.append(provinceName);
                }
                if (StringUtils.isNotBlank(cityName)){
                    releaseArea.append("-").append(cityName);
                }
                if (StringUtils.isNotBlank(countyName)){
                    releaseArea.append("-").append(countyName);
                }
                vo.setReleaseArea(releaseArea.toString());
            }

            // 转换放流级别名称
            ReleaseLevelEnum levelEnum = ReleaseLevelEnum.getEnum(vo.getReleaseLevel());
            String describe = levelEnum != null ? levelEnum.getDescribe() : "";
            vo.setReleaseLevel(describe);
        }
    }

    /**
     * 转换省市县名称、放流级别名称、附件名称
     */
    private void releaseAreaAndReleaseLevelAndAccessoryConvert(BasicProliferationReleaseForm form) {
        // 转换地址名称
        Result<List<SysRegionTreeVo>> parentNode = sysRegionApi.getParentNode(form.getCountyId());
        List<SysRegionTreeVo> data = parentNode.getData();
        //判断获取地址相关数据是否存在
        if (data != null && data.size() > 0) {
            //获取省名称
            SysRegionTreeVo province = data.get(0);
            String provinceName = province.getRegionName();
            //获取市名称
            SysRegionTreeVo city = data.get(1);
            String cityName = city.getRegionName();
            //获取区县名称
            SysRegionTreeVo county = data.get(2);
            String countyName = county.getRegionName();

            List<String> adCodes = data.stream().map(SysRegionTreeVo::getRegionCode).collect(Collectors.toList());
            // 隐藏直辖市的市辖区、县的显示
            if (!Collections.disjoint(adCodes, MunicipalityEnum.adCodesList())){
                cityName = data.size()>=3 ? "" : cityName;
            }

            //拼接市区县详细地址
            StringBuilder releaseArea = new StringBuilder();
            if (StringUtils.isNotBlank(provinceName)){
                releaseArea.append(provinceName);
            }
            if (StringUtils.isNotBlank(cityName)){
                releaseArea.append("-"+cityName);
            }
            if (StringUtils.isNotBlank(countyName)){
                releaseArea.append("-"+countyName);
            }
            form.setReleaseArea(releaseArea.toString());
        }

        // 转换放流级别名称
        ReleaseLevelEnum levelEnum = ReleaseLevelEnum.getEnum(form.getReleaseLevel());
        String describe = levelEnum != null ? levelEnum.getDescribe() : "";
        form.setReleaseLevel(describe);

        // 转换附件名称
        String accessoryId = StringUtils.isNotBlank(form.getAccessory()) ? form.getAccessory() : "0";
        Result<SysFileManageVo> accessory = sysFileApi.getOne(accessoryId);
        String accessoryName = accessory == null ? "":accessory.getData() == null ? "":accessory.getData().getFileName();
        form.setAccessoryName(accessoryName);
    }

    /**
     * 对数据统计合计(各地区增殖放流关键数据)
     * @param infos
     */
    private List<ProliferationReleaseInfosVo> sumProliferationReleaseInfos(List<ProliferationReleaseInfosVo> infos, String region, int pageNo) {

        if (pageNo == 0) {
            ProliferationReleaseInfosVo proliferationReleaseInfosVo = new ProliferationReleaseInfosVo();
            BigDecimal releaseNumber = infos.stream().map(item ->
                    item.getReleaseNumber()==null ? BigDecimal.ZERO : new BigDecimal(item.getReleaseNumber()))
                    .reduce(BigDecimal.ZERO,BigDecimal::add);
            BigDecimal nationInvestment = infos.stream().map(item ->
                    item.getNationInvestment()==null ? BigDecimal.ZERO : new BigDecimal(item.getNationInvestment()))
                    .reduce(BigDecimal.ZERO,BigDecimal::add);
            BigDecimal provinceInvestment = infos.stream().map(item ->
                    item.getProvinceInvestment()==null ? BigDecimal.ZERO : new BigDecimal(item.getProvinceInvestment()))
                    .reduce(BigDecimal.ZERO,BigDecimal::add);
            BigDecimal cityInvestment = infos.stream().map(item ->
                    item.getCityInvestment()==null ? BigDecimal.ZERO : new BigDecimal(item.getCityInvestment()))
                    .reduce(BigDecimal.ZERO,BigDecimal::add);
            BigDecimal societyInvestment = infos.stream().map(item ->
                    item.getSocietyInvestment()==null ? BigDecimal.ZERO : new BigDecimal(item.getSocietyInvestment()))
                    .reduce(BigDecimal.ZERO,BigDecimal::add);
            BigDecimal investmentTotal = infos.stream().map(item ->
                    item.getInvestmentTotal()==null ? BigDecimal.ZERO : new BigDecimal(item.getInvestmentTotal()))
                    .reduce(BigDecimal.ZERO,BigDecimal::add);
            Double nationReleaseCount = infos.stream().mapToDouble(item ->
                    item.getNationReleaseCount() == null ? 0 : new Double(item.getNationReleaseCount()))
                    .sum();
            Double provinceReleaseCount = infos.stream().mapToDouble(item ->
                    item.getProvinceReleaseCount() == null ? 0 : new Double(item.getProvinceReleaseCount()))
                    .sum();
            Double cityReleaseCount = infos.stream().mapToDouble(item ->
                    item.getCityReleaseCount() == null ? 0 : new Double(item.getCityReleaseCount()))
                    .sum();
            Double otherReleaseCount = infos.stream().mapToDouble(item ->
                    item.getOtherReleaseCount() == null ? 0 : new Double(item.getOtherReleaseCount()))
                    .sum();
            Double releaseCountTotal = infos.stream().mapToDouble(item ->
                    item.getReleaseCountTotal() == null ? 0 : new Double(item.getReleaseCountTotal()))
                    .sum();
            List<ProliferationReleaseInfosVo> result = new ArrayList<>();
            // 放入上级区域id
            proliferationReleaseInfosVo.setRegion(region);
            proliferationReleaseInfosVo.setReleaseNumber(trimZero(releaseNumber.toString()));
            proliferationReleaseInfosVo.setNationInvestment(trimZero(nationInvestment.toString()));
            proliferationReleaseInfosVo.setProvinceInvestment(trimZero(provinceInvestment.toString()));
            proliferationReleaseInfosVo.setCityInvestment(trimZero(cityInvestment.toString()));
            proliferationReleaseInfosVo.setSocietyInvestment(trimZero(societyInvestment.toString()));
            proliferationReleaseInfosVo.setInvestmentTotal(trimZero(investmentTotal.toString()));
            proliferationReleaseInfosVo.setNationReleaseCount(trimZero(nationReleaseCount.toString()));
            proliferationReleaseInfosVo.setProvinceReleaseCount(trimZero(provinceReleaseCount.toString()));
            proliferationReleaseInfosVo.setCityReleaseCount(trimZero(cityReleaseCount.toString()));
            proliferationReleaseInfosVo.setOtherReleaseCount(trimZero(otherReleaseCount.toString()));
            proliferationReleaseInfosVo.setReleaseCountTotal(trimZero(releaseCountTotal.toString()));
            // 加入统计数据
            result.add(proliferationReleaseInfosVo);
            result.addAll(infos);
            return result;
        } else {
            return infos;
        }
    }

    /**
     * 转换区域名称(各地区增殖放流关键数据)
     * @param infos
     */
    private void convertRegionName(List<ProliferationReleaseInfosVo> infos) {
        infos.forEach(e -> {
            // 去除末尾0
            e.setReleaseNumber(trimZero(e.getReleaseNumber()));
            e.setNationInvestment(trimZero(e.getNationInvestment()));
            e.setProvinceInvestment(trimZero(e.getProvinceInvestment()));
            e.setCityInvestment(trimZero(e.getCityInvestment()));
            e.setSocietyInvestment(trimZero(e.getSocietyInvestment()));
            e.setInvestmentTotal(trimZero(e.getInvestmentTotal()));

            String regionName = null;
            if ("".equals(e.getRegion())) {
                regionName = "全国";
            } else {
                regionName = NumberUtil.isNumber(e.getRegion()) ? sysRegionApi.getSysRegionName(e.getRegion()).getData(): e.getRegion();
            }
            e.setRegion(regionName);
        });
    }

    /**
     * 转换区域名、放流级别、区域经纬度(分布图示)
     *
     * @param infos
     */
    private void convertDistributionRegionName(List<ProliferationReleaseLocationDistributionVo> infos) {
        if (infos == null){
            return;
        }
        // 直辖市列表
        List<String> muniList = MunicipalityEnum.adCodesList();
        infos.forEach(e -> {

            // 去除末尾0
            e.setReleaseNumberTotal(trimZero(e.getReleaseNumberTotal()));
            e.setNationInvestment(trimZero(e.getNationInvestment()));
            e.setProvinceInvestment(trimZero(e.getProvinceInvestment()));
            e.setCityInvestment(trimZero(e.getCityInvestment()));
            e.setSocietyInvestment(trimZero(e.getSocietyInvestment()));
            e.setInvestmentTotal(trimZero(e.getInvestmentTotal()));

            String[] regions = e.getRegion().split("-");
            if (StringUtils.isNotBlank(e.getRegion())) {
                // 转换地址名称
                Result<List<SysRegionTreeVo>> parentNode = sysRegionApi.getParentNode(regions[0]);
                List<SysRegionTreeVo> data = parentNode.getData();
                if (data != null && data.size() > 0) {
                    String provinceName = data.size()>=1 ? data.get(0).getRegionName():"";
                    String cityName = data.size()>=2 ? data.get(1).getRegionName():"";
                    String countyName = data.size()>=3 ? data.get(2).getRegionName():"";
                    List<String> adCodes = data.stream().map(SysRegionTreeVo::getRegionCode).collect(Collectors.toList());
                    // 隐藏直辖市的市辖区、县的显示
                    if (!Collections.disjoint(adCodes, muniList)){
                        cityName = data.size()>=3 ? "" : cityName;
                    }
                    StringBuilder releaseArea = new StringBuilder();
                    if (StringUtils.isNotBlank(provinceName)){
                        releaseArea.append(provinceName);
                    }
                    if (StringUtils.isNotBlank(cityName)){
                        releaseArea.append("-"+cityName);
                    }
                    if (StringUtils.isNotBlank(countyName)){
                        releaseArea.append("-"+countyName);
                    }

                    if (regions.length == 2 ){
                        // 处于县级查询
                        e.setRegion(regions[0]);
                        e.setRegionName(regions[1]);
                    }else {
                        // 处于部、省、市级查询，使用行政区划对应名作为行政区划名
                        e.setRegionName(releaseArea.toString());
                        // 设置对应区域的经纬度
                        e.setLongitude(data.get(data.size()-1).getLongitude());
                        e.setLatitude(data.get(data.size()-1).getLatitude());
                    }
                }
            } else {
                e.setRegionName("");
            }
            
            // 转换放流级别名称
            ReleaseLevelEnum levelEnum = ReleaseLevelEnum.getEnum(e.getReleaseLevel());
            String describe = levelEnum != null ? levelEnum.getDescribe() : "";
            e.setReleaseLevel(describe);
        });
    }


    /**
     * 去除小数后多余0
     * @param
     * @return
     */
    private String trimZero(String s) {
        if (StringUtils.isNotBlank(s) && NumberUtil.isNumber(s)){
            s = new BigDecimal(s).stripTrailingZeros().toPlainString();
        }
        return s;
    }

    /**
     * 激活附件（单个附件）
     * @param sysFileApi
     * @param form
     * @param token
     */
    private void activationFile(SysFileApi sysFileApi, SysFileManageForm form, String token){
        // 激活附件
        Result<List<SysFileVo>> listResult = null;
        if (StringUtils.isNotBlank(form.getIds())){
            listResult = sysFileApi.activationFile(form, token);
            if (listResult.getData().size() != 1){
                log.error("附件激活失败！");
                throw new SofnException("附件激活失败！");
            }
        }
    }

    /**
     * 县级是否允许操作(包括新增、修改、删除)
     * @param form
     * @param roleCode
     * @return
     */
    private boolean countyOperationData(BasicProliferationReleaseForm form,String roleCode){

        //县级操作数据条件：
        //1.所属市非上报或已退回状态
        //2.所属区县未审核(审核过了就不能再审)
        //3.所属省未审核
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("belongYear",form.getBelongYear());
        List<String> ministerStatus = new ArrayList<>();
        ministerStatus.add(AuditStatueEnum.STATUS_RETURN_MASTER.getStatus());
        paramsMap.put("statusList",ministerStatus);
        paramsMap.put("provinceId",form.getProvinceId());
        List<MinisterAudit> ministerAuditList = ministerAuditMapper.getInfoByCondition(paramsMap);
        if(ministerAuditList != null && ministerAuditList.size()>0){
            return false;
        }
        List<String> provinceStatus = new ArrayList<>();
        paramsMap.remove("statusList");
        provinceStatus.add(AuditStatueEnum.STATUS_RETURN_PROV.getStatus());
        paramsMap.put("statusList",provinceStatus);
        paramsMap.put("cityId",form.getCityId());
        List<ProvinceAudit> provinceAuditList = provinceAuditMapper.getInfoByCondition(paramsMap);
        if(provinceAuditList != null && provinceAuditList.size()>0){
            return false;
        }
        //市级待审核可以填报
        List<String> cityStatus = new ArrayList<>();
        paramsMap.remove("statusList");
        cityStatus.add(AuditStatueEnum.STATUS_RETURN_CITY.getStatus());
        cityStatus.add(AuditStatueEnum.STATUS_REPORT_CITY.getStatus());
        paramsMap.put("statusList",cityStatus);
        paramsMap.put("countyId",form.getCountyId());
        List<CityAudit> cityAuditList = cityAuditMapper.getInfoByCondition(paramsMap);
        if(cityAuditList != null && cityAuditList.size()>0){
            return false;
        }
        return true;
    }
}
