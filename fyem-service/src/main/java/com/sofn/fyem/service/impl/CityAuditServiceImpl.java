package com.sofn.fyem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sofn.common.exception.SofnException;
import com.sofn.common.model.Result;
import com.sofn.common.utils.IdUtil;
import com.sofn.common.utils.JsonUtils;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.UserUtil;
import com.sofn.fyem.enums.AuditStatueEnum;
import com.sofn.fyem.enums.ReleaseLevelEnum;
import com.sofn.fyem.enums.RoleLevelEnum;
import com.sofn.fyem.mapper.*;
import com.sofn.fyem.model.CityAudit;
import com.sofn.fyem.model.ProvinceAudit;
import com.sofn.fyem.service.BasicProliferationReleaseService;
import com.sofn.fyem.service.CityAuditService;
import com.sofn.fyem.service.ProliferationReleaseStatisticsService;
import com.sofn.fyem.service.ReporManagementService;
import com.sofn.fyem.sysapi.SysFileApi;
import com.sofn.fyem.sysapi.SysRegionApi;
import com.sofn.fyem.sysapi.bean.SysFileManageVo;
import com.sofn.fyem.sysapi.bean.SysOrganization;
import com.sofn.fyem.sysapi.bean.SysRegionTreeVo;
import com.sofn.fyem.util.FyemAreaUtil;
import com.sofn.fyem.util.RoleCodeUtil;
import com.sofn.fyem.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description: 市级审核接口实现
 * @Author: DJH
 * @Date: 2020/4/27 14:45
 */
@Service(value = "cityAuditService")
@Slf4j
public class CityAuditServiceImpl extends ServiceImpl<CityAuditMapper, CityAudit> implements CityAuditService {
    @Autowired
    private ReporManagementMapper reporManagementMapper;
    @Autowired
    private CityAuditMapper cityAuditMapper;
    @Autowired
    private ProvinceAuditMapper provinceAuditMapper;
    @Autowired
    private BasicProliferationReleaseMapper basicProliferationReleaseMapper;
    @Autowired
    private ProliferationReleaseStatisticsMapper proliferationReleaseStatisticsMapper;
    @Autowired
    private ReporManagementService reporManagementService;
    @Autowired
    private BasicProliferationReleaseService basicProliferationReleaseService;
    @Autowired
    private ProliferationReleaseStatisticsService proliferationReleaseStatisticsService;

    @Autowired
    private SysRegionApi sysRegionApi;
    @Autowired
    private SysFileApi sysFileApi;

    @Override
    public List<CityAuditVo> listCityAuditsByBelongYear(Map<String, Object> params) {
        String belongYear = (String) params.get("belongYear");
//        String orgName = (String) params.get("orgName");
//        String cityId = (String) params.get("cityId");
        // 未填所属年度，使用当前年度
        if(StringUtils.isBlank(belongYear) || belongYear.equals("0")){
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
            belongYear = dateFormat.format( new Date() );
            params.put("belongYear", belongYear);
        }
//        Map map = new HashMap();
//        map.put("belongYear",belongYear);
//        map.put("orgName",orgName);
        // 根据城市id查询下级区县(从当前用户获取所属cityId)
//        map.put("cityId",cityId);
        List<CityAuditVo> list = cityAuditMapper.listCityAuditsByBelongYear(params);
        // 转换审核状态码描述
        auditStatuConvert(list);
        return list;
    }

    @Override
    public PageUtils<CityAuditVo> getCityAuditsListByPage(Map<String, Object> params, int pageNo, int pageSize) {
        String belongYear = (String) params.get("belongYear");
//        String orgName = (String) params.get("orgName");
//        String cityId = (String) params.get("cityId");
        // 未填所属年度，使用当前年度
        if(StringUtils.isBlank(belongYear) || belongYear.equals("0")){
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
            belongYear = dateFormat.format( new Date() );
            params.put("belongYear", belongYear);
        }
//        Map map = new HashMap();
//        map.put("belongYear",belongYear);
//        map.put("orgName",orgName);
        // 根据城市id查询下级区县(从当前用户获取所属cityId)
//        map.put("cityId",cityId);

        PageHelper.offsetPage(pageNo,pageSize);
        List<CityAuditVo> cityAuditVoList = cityAuditMapper.listCityAuditsByBelongYear(params);
        // 转换审核状态码描述
        auditStatuConvert(cityAuditVoList);
        PageInfo<CityAuditVo> pageInfo = new PageInfo<>(cityAuditVoList);
        return PageUtils.getPageUtils(pageInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String insert(CityAudit cityAudit) {

        // 获取组织相关信息
        String loginUserOrganizationInfo = UserUtil.getLoginUserOrganizationInfo();
        SysOrganization sysOrganization = JsonUtils.json2obj(loginUserOrganizationInfo, SysOrganization.class);
        // 获取组织名
        String organizationName = sysOrganization.getOrganizationName();

        // 获取当前机构id
        String organizationId = sysOrganization.getId();

        // 获取当前用户角色列表
        List<String> roleCodes = UserUtil.getLoginUserRoleCodeList();
        String roleCode = RoleCodeUtil.getLoginUserFyemRoleCode(roleCodes);

        String belongYear = cityAudit.getBelongYear();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
        // 未填所属年度，使用当前年度
        if(StringUtils.isBlank(belongYear) || belongYear.equals("0")){
            belongYear = dateFormat.format( new Date() );
        }
        cityAudit.setBelongYear(belongYear);

        cityAudit.setId(IdUtil.getUUId());
        cityAudit.setOrganizationId(organizationId);
        cityAudit.setOrganizationName(organizationName);
        cityAudit.setRoleCode(roleCode);
        boolean result = false;
        try {
            result = this.save(cityAudit);
            if (result == false){
                return "0";
            }else {
                return "1";
            }
        } catch (Exception e){
            log.error("市级审核数据新增报错" + e.getMessage());
            throw new SofnException("新增失败！");
        }
    }

    @Override
    public List<CityReportManagementVo> reportManagement(Map<String, Object> params) {
        params.put("status",AuditStatueEnum.STATUS_APPROVE_CITY.getStatus());
        List<CityReportManagementVo> list = cityAuditMapper.reportManagement(params);
        // 设置应报数量
        int shouldReportCount = 0;
        CityReportManagementVo reportManagementVo = (list != null && list.size() > 0) ? list.get(0) : null;
        if (reportManagementVo != null){
            shouldReportCount = FyemAreaUtil.getListCountByParentId(sysRegionApi);
            reportManagementVo.setShouldReportCount(String.valueOf(shouldReportCount));
        }

        // 转换审核状态码显示和上报情况显示
        reportSituationConvert(list);
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String cityAuditApprove(Map<String, Object> params) {

        CityAudit cityAudit = cityAuditMapper.selectByBelongYearAndCountyId(params);
        // 当该区县处于市级待审核时，允许审核通过
        if (!(cityAudit != null && cityAudit.getStatus().equals(AuditStatueEnum.STATUS_REPORT_CITY.getStatus()))){
            return "不满足通过前提条件";
        }

        String  belongYear = (String) params.get("belongYear");
        String  countyId = (String) params.get("countyId");
        String  roleCode = (String) params.get("roleCode");
        String  cityId = (String) params.get("cityId");
        String  organizationName = (String) params.get("organizationName");
        String  remark = (String) params.get("remark");
        Map mapOfBPR = new HashMap();
        mapOfBPR.put("belongYear",belongYear);
        mapOfBPR.put("cityId",cityId);
        mapOfBPR.put("countyId",countyId);
        mapOfBPR.put("status",AuditStatueEnum.STATUS_APPROVE_CITY.getStatus());
        mapOfBPR.put("organizationName",organizationName);
        mapOfBPR.put("roleCode",roleCode);
        // 重置退回意见
        mapOfBPR.put("remark",remark);
        // 改变数据状态的条件(市级待审核  )
        mapOfBPR.put("statusConditionFirst",AuditStatueEnum.STATUS_REPORT_CITY.getStatus());
        try {
            // 市级审核表、县级上报管理表、水生生物审核、中央财政审核通过，暂时不考虑部、省、市本级和直属单位
            int resultOfCityAudit = cityAuditMapper.updateStatus(mapOfBPR);
            int resultOfCountyAudit = reporManagementMapper.updateStatus(mapOfBPR);
            int resultOfBPR = basicProliferationReleaseMapper.updateStatus(mapOfBPR);
            int resultOfPRS = proliferationReleaseStatisticsMapper.updateStatus(mapOfBPR);

            if (resultOfCityAudit == 1 && resultOfCountyAudit > 0
                    && resultOfBPR > 0 && resultOfPRS > 0){
                return "1";
            } else {
                throw new SofnException("市级审核通过失败！");
            }
        } catch (Exception e){
            log.error("市级审核通过报错:", e);
            throw new SofnException("市级审核通过失败！");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String cityAuditReject(Map<String, Object> params) {

        CityAudit cityAudit = cityAuditMapper.selectByBelongYearAndCountyId(params);
        // 当该区县处于市级待审核、省级已退回时，允许驳回
//        if (!(cityAudit != null
//                && (cityAudit.getStatus().equals(AuditStatueEnum.STATUS_REPORT_CITY.getStatus())
//                || cityAudit.getStatus().equals(AuditStatueEnum.STATUS_RETURN_PROV.getStatus())))){
//            return "不满足退回前提条件";
//        }
        if (cityAudit == null || !org.apache.commons.lang3.StringUtils.equalsAny(cityAudit.getStatus(),
                AuditStatueEnum.STATUS_REPORT_CITY.getStatus(), AuditStatueEnum.STATUS_RETURN_PROV.getStatus(), AuditStatueEnum.STATUS_APPROVE_CITY.getStatus())) {
            return "不满足退回前提条件";
        }

        String  belongYear = (String) params.get("belongYear");
        String  countyId = (String) params.get("countyId");
        String  remark = (String) params.get("remark");
        String  roleCode = (String) params.get("roleCode");
        String  cityId = (String) params.get("cityId");
        String  organizationName = (String) params.get("organizationName");
        Map mapOfBPR = new HashMap();
        mapOfBPR.put("belongYear",belongYear);
        mapOfBPR.put("cityId",cityId);
        mapOfBPR.put("countyId",countyId);
        mapOfBPR.put("status",AuditStatueEnum.STATUS_RETURN_CITY.getStatus());
        mapOfBPR.put("organizationName",organizationName);
        mapOfBPR.put("roleCode",roleCode);
        // 改变数据状态的条件(市级待审核、省级已退回)
        mapOfBPR.put("statusConditionFirst",AuditStatueEnum.STATUS_REPORT_CITY.getStatus());
        mapOfBPR.put("statusConditionSecond",AuditStatueEnum.STATUS_RETURN_PROV.getStatus());
        Map mapOfRemark = new HashMap();
        mapOfRemark.put("belongYear",belongYear);
        mapOfRemark.put("cityId",cityId);
        mapOfRemark.put("countyId",countyId);
        mapOfRemark.put("status",AuditStatueEnum.STATUS_RETURN_CITY.getStatus());
        mapOfRemark.put("organizationName",organizationName);
        mapOfRemark.put("remark",remark);
        mapOfRemark.put("roleCode",roleCode);
        // 改变数据状态的条件(市级待审核、省级已退回)
        mapOfRemark.put("statusConditionFirst",AuditStatueEnum.STATUS_REPORT_CITY.getStatus());
        mapOfRemark.put("statusConditionSecond",AuditStatueEnum.STATUS_RETURN_PROV.getStatus());
        try {
            // 市级审核表、县级上报管理表、水生生物审核、中央财政审核驳回，暂时不考虑部、省、市本级和直属单位
            int resultOfCityAudit = cityAuditMapper.updateStatus(mapOfRemark);
            int resultOfCountyAudit = 0;
            if (RoleLevelEnum.LEVEL_CITY_ADD.getLevel().equals(roleCode)){
                resultOfCountyAudit = reporManagementMapper.updateStatus(mapOfRemark);
            }else {
                resultOfCountyAudit = reporManagementMapper.updateStatus(mapOfRemark);
            }
            int resultOfBPR = basicProliferationReleaseMapper.updateStatus(mapOfBPR);
            int resultOfPRS = proliferationReleaseStatisticsMapper.updateStatus(mapOfBPR);
            if (resultOfCityAudit == 1 && resultOfCountyAudit > 0
                    && resultOfBPR > 0 && resultOfPRS > 0){
                reporManagementService.markReject(params);
                return "1";
            } else {
                throw new SofnException("市级审核驳回失败！");
            }
        } catch (Exception e){
            log.error("市级审核驳回报错:" + e.getMessage());
            throw new SofnException("市级审核驳回失败！");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String cityAuditReport(Map<String, Object> params) {
        String  belongYear = (String) params.get("belongYear");
        String  cityId = (String) params.get("cityId");

        // 允许上报标志
        boolean reportFlag = false;
        // 省退回标志
        boolean returnProvFlag = false;

        // 获取组织相关信息
        String loginUserOrganizationInfo = UserUtil.getLoginUserOrganizationInfo();
        SysOrganization sysOrganization = JsonUtils.json2obj(loginUserOrganizationInfo, SysOrganization.class);
        // 获取组织名
        String organizationName = sysOrganization.getOrganizationName();

        String loginUserId = UserUtil.getLoginUserId();
        // 获取当前机构id
        String organizationId = sysOrganization.getId();

        // 获取当前用户角色列表
        List<String> roleCodes = UserUtil.getLoginUserRoleCodeList();
        String roleCode = RoleCodeUtil.getLoginUserFyemRoleCode(roleCodes);

        // 判断状态是否可以进行市级上报（下级区县上报数 > 0）
        Map map = new HashMap();
        map.put("belongYear",belongYear);
        map.put("cityId",cityId);
        map.put("status",AuditStatueEnum.STATUS_APPROVE_CITY.getStatus());
        int count = cityAuditMapper.countApproveCounty(map);

        // 查询省级审核表是否已存在上报记录
        List<ProvinceAudit> provinceAuditList = provinceAuditMapper.selectListByParams(params);
        ProvinceAudit preProvinceAudit = provinceAuditList.size() > 0 ? provinceAuditList.get(0) : null;
        if (preProvinceAudit != null){
            // 处于省级已退回状态，可以继续市级上报
            if (preProvinceAudit.getStatus().equals(AuditStatueEnum.STATUS_RETURN_PROV.getStatus())){
                reportFlag = true;
                returnProvFlag = true;
            }else {
                reportFlag = false;
                returnProvFlag = false;
            }
        }else {
            if (count == 0){// 没有上报的下级区县
                return "没有可上报的下级区县！";
            }else {
                reportFlag = true;
            }
        }

        if (reportFlag == true){
            // 获取指定cityId市的省id
            Result<List<SysRegionTreeVo>> parentNode = sysRegionApi.getParentNode(cityId);
            List<SysRegionTreeVo> data = parentNode.getData();
            String provinceId = data.size() > 0 ? data.get(0).getRegionCode() : "";

            ProvinceAudit provinceAudit = new ProvinceAudit();
            provinceAudit.setId(IdUtil.getUUId());
            provinceAudit.setBelongYear(belongYear);
            provinceAudit.setProvinceId(provinceId);
            provinceAudit.setCityId(cityId);
            // 省级审核表不需要具体到区县id
            provinceAudit.setCountyId("");
            provinceAudit.setOrganizationId(organizationId);
            provinceAudit.setOrganizationName(organizationName);
            provinceAudit.setCreateTime(new Date());
            provinceAudit.setRoleCode(roleCode);
            provinceAudit.setCreateUserId(loginUserId);
            // 改为 省级待审核 状态
            provinceAudit.setStatus(AuditStatueEnum.STATUS_REPORT_PROV.getStatus());

            Map mapOfBPR = new HashMap();
            mapOfBPR.put("belongYear",belongYear);
            mapOfBPR.put("cityId",cityId);
            mapOfBPR.put("status",AuditStatueEnum.STATUS_REPORT_PROV.getStatus());
            // 改变数据状态的条件(市级已通过、省级已退回)
            mapOfBPR.put("statusConditionFirst",AuditStatueEnum.STATUS_APPROVE_CITY.getStatus());
            mapOfBPR.put("statusConditionSecond",AuditStatueEnum.STATUS_RETURN_PROV.getStatus());
            try {
                // 修改市级审核表的区县记录状态、县级上报管理表记录状态、水生生物的放流记录状态、中央财政记录的状态
                int resultOfCityAudit = cityAuditMapper.updateStatus(mapOfBPR);
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

                if (returnProvFlag == true){// 省退回，市级重新上报
                    if (resultOfProvAudit > 0){
                        reporManagementService.markSubmit(belongYear);
                        return "1";
                    }else {
                        throw new SofnException("市级上报失败！");
                    }
                }else {// 新的市级上报
                    if (resultOfProvAudit == 1 && resultOfCityAudit > 0 && resultOfCountyAudit > 0
                            && resultOfBPR > 0 && resultOfPRS > 0){
                        reporManagementService.markSubmit(belongYear);
                        return "1";
                    } else {
                        throw new SofnException("市级上报失败！");
                    }
                }
            } catch (Exception e){
                log.error("市级上报报错:" + e.getMessage());
                throw new SofnException("市级上报失败！");
            }
        }else {
            return "请等待上级退回后再上报";
        }
    }

    @Override
    public Map<String, Object> view(Map<String, Object> params) {


        // 获取水生生物数据
        List<BasicProliferationReleaseForm> basicProliferationReleaseFormList = basicProliferationReleaseService.getBasicProliferationReleaseList(params);
        // 转换附件名、放流级别名称
        accessoryNameAndReleaseLevelConvert(basicProliferationReleaseFormList);

        // 获取中央财政数据
        ReleaseStatisticsSpeciesVo releaseStatisticsSpeciesVo =
                proliferationReleaseStatisticsService.getReleaseStatisticsSpeciesVo(params);

        Map map = new HashMap();
        map.put("basicProliferationRelease",basicProliferationReleaseFormList);
        map.put("proliferationReleaseStatistics",releaseStatisticsSpeciesVo);
        return map;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String editRemark(CityRemarkVo cityRemarkVo) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", cityRemarkVo.getId());
        CityAudit cityAudit = cityAuditMapper.selectByParams(params);
        if (cityAudit == null){
            throw new SofnException("原数据不存在！");
        }
        if (!cityAudit.getStatus().equals(AuditStatueEnum.STATUS_RETURN_CITY.getStatus())){
            return "0";
        }
        BeanUtils.copyProperties(cityRemarkVo,cityAudit);
        try {
            int result = cityAuditMapper.updateById(cityAudit);
            if (result == 1){
                return "1";
            }else {
                return "0";
            }
        } catch (Exception e){
            log.error("市级审核填写驳回意见报错" + e.getMessage());
            throw new SofnException("填写驳回意见失败！");
        }
    }

    @Override
    public CountyRemarkVo getRemark(Map<String, Object> cityViewParams) {

        CityAudit cityAudit = cityAuditMapper.selectByParams(cityViewParams);
        CountyRemarkVo countyRemarkVo = new CountyRemarkVo();
        if (cityAudit != null){
            BeanUtils.copyProperties(cityAudit, countyRemarkVo);
        }
        return countyRemarkVo;
    }

    @Override
    public PageUtils<CityReportManagementVo> reportManagementByPage(Map<String, Object> cityViewParams, int pageNo, int pageSize) {
        cityViewParams.put("status",AuditStatueEnum.STATUS_APPROVE_CITY.getStatus());
        PageHelper.offsetPage(pageNo,pageSize);
        List<CityReportManagementVo> list = cityAuditMapper.reportManagement(cityViewParams);
        // 设置应报数量(当前登录账户下级机构数量)
        int shouldReportCount = 0;
        CityReportManagementVo cityReportManagementVo = (list != null && list.size() > 0) ? list.get(0) : null;
        if (cityReportManagementVo != null){
            shouldReportCount = FyemAreaUtil.getListCountByParentId(sysRegionApi);
            cityReportManagementVo.setShouldReportCount(String.valueOf(shouldReportCount));
        }
        // 转换审核状态码显示和上报情况显示
        reportSituationConvert(list);
        PageInfo<CityReportManagementVo> pageInfo = new PageInfo<>(list);
        return PageUtils.getPageUtils(pageInfo);
    }


    /**
     * 审核状态码转换页面显示
     * @param list
     * @return
     */
    private void auditStatuConvert(List<CityAuditVo> list){
        for (CityAuditVo cityAuditVo: list ) {
            switch (cityAuditVo.getStatus()){
                case "0": cityAuditVo.setStatus(AuditStatueEnum.STATUS_NOTREPORT.getDescribe());
                            cityAuditVo.setStatusCode("0");break;
                case "1": cityAuditVo.setStatus(AuditStatueEnum.STATUS_REPORT_CITY.getDescribe());
                            cityAuditVo.setStatusCode("1");break;
                case "2": cityAuditVo.setStatus("已驳回");
                            cityAuditVo.setStatusCode("2");break;
                case "3": cityAuditVo.setStatus(AuditStatueEnum.STATUS_APPROVE_CITY.getDescribe());
                            cityAuditVo.setStatusCode("3");break;
                case "4": cityAuditVo.setStatus(AuditStatueEnum.STATUS_REPORT_PROV.getDescribe());
                            cityAuditVo.setStatusCode("4");break;
                case "5": cityAuditVo.setStatus(AuditStatueEnum.STATUS_RETURN_PROV.getDescribe());
                            cityAuditVo.setStatusCode("5");break;
                case "6": cityAuditVo.setStatus(AuditStatueEnum.STATUS_APPROVE_PROV.getDescribe());
                            cityAuditVo.setStatusCode("6");break;
                case "7": cityAuditVo.setStatus(AuditStatueEnum.STATUS_REPORT_MASTER.getDescribe());
                            cityAuditVo.setStatusCode("7");break;
                case "8": cityAuditVo.setStatus(AuditStatueEnum.STATUS_RETURN_MASTER.getDescribe());
                            cityAuditVo.setStatusCode("8");break;
                case "9": cityAuditVo.setStatus(AuditStatueEnum.STATUS_APPROVE_MASTER.getDescribe());
                            cityAuditVo.setStatusCode("9");break;
            }
        }
    }

    /**
     * 上报情况转换页面显示
     * @param list
     */
    private void reportSituationConvert(List<CityReportManagementVo> list){
        for (CityReportManagementVo crmVo : list){
            StringBuilder builder = new StringBuilder().append("上报").append(crmVo.getReportCount()).append("/应报").append(crmVo.getShouldReportCount());
            crmVo.setReportSituation(builder.toString());
            switch (crmVo.getStatus()){
                case "0": crmVo.setStatus(AuditStatueEnum.STATUS_NOTREPORT.getDescribe());
                            crmVo.setStatusCode("0");break;
                case "1": crmVo.setStatus("未上报");
                            crmVo.setStatusCode("1");break;
                case "2": crmVo.setStatus("未上报");
                            crmVo.setStatusCode("2");break;
                case "3": crmVo.setStatus("未上报");
                            crmVo.setStatusCode("3");break;
                case "4": crmVo.setStatus(AuditStatueEnum.STATUS_REPORT_PROV.getDescribe());
                            crmVo.setStatusCode("4");break;
                case "5": crmVo.setStatus("被驳回");
                            crmVo.setStatusCode("5");break;
                case "6": crmVo.setStatus(AuditStatueEnum.STATUS_APPROVE_PROV.getDescribe());
                            crmVo.setStatusCode("6");break;
                case "7": crmVo.setStatus(AuditStatueEnum.STATUS_REPORT_MASTER.getDescribe());
                            crmVo.setStatusCode("7");break;
                case "8": crmVo.setStatus("被驳回");
                            crmVo.setStatusCode("8");break;
                case "9": crmVo.setStatus(AuditStatueEnum.STATUS_APPROVE_MASTER.getDescribe());
                            crmVo.setStatusCode("9");break;
            }
        }
    }

    /**
     * 转换附件名、放流级别名称
     * @param list
     */
    private void accessoryNameAndReleaseLevelConvert(List<BasicProliferationReleaseForm> list){
        for (BasicProliferationReleaseForm form: list ) {
            // 转换附件名
            String accessory = form.getAccessory();
            if (org.apache.commons.lang3.StringUtils.isNotBlank(accessory)) {
                Result<SysFileManageVo> fileResult = sysFileApi.getOne(accessory);
                if (Objects.equals(fileResult.getCode(), Result.CODE) && Objects.nonNull(fileResult.getData())) {
                    form.setAccessoryName(fileResult.getData().getFileName());
                }
            }

            // 转换放流级别名称
            String releaseLevel = form.getReleaseLevel();
            switch (releaseLevel){
                case "0": form.setReleaseLevel(ReleaseLevelEnum.OTHER_LEVEL.getDescribe());break;
                case "1": form.setReleaseLevel(ReleaseLevelEnum.CITY_COUNTY_LEVEL.getDescribe());break;
                case "2": form.setReleaseLevel(ReleaseLevelEnum.PROV_LEVEL.getDescribe());break;
                case "3": form.setReleaseLevel(ReleaseLevelEnum.COUNTRY_LEVEL.getDescribe());break;
            }
        }
    }
}
