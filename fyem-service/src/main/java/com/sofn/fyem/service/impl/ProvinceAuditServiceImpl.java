package com.sofn.fyem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sofn.common.exception.SofnException;
import com.sofn.common.utils.IdUtil;
import com.sofn.common.utils.JsonUtils;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.UserUtil;
import com.sofn.fyem.enums.AuditStatueEnum;
import com.sofn.fyem.enums.RoleLevelEnum;
import com.sofn.fyem.mapper.*;
import com.sofn.fyem.model.MinisterAudit;
import com.sofn.fyem.model.ProvinceAudit;
import com.sofn.fyem.service.ProvinceAuditService;
import com.sofn.fyem.service.ReporManagementService;
import com.sofn.fyem.sysapi.SysRegionApi;
import com.sofn.fyem.sysapi.bean.SysOrganization;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 省级审核接口实现
 * @Author: DJH
 * @Date: 2020/4/27 14:56
 */
@Service(value = "provinceAuditService")
@Slf4j
public class ProvinceAuditServiceImpl extends ServiceImpl<ProvinceAuditMapper, ProvinceAudit> implements ProvinceAuditService {

    @Autowired
    private ProvinceAuditMapper provinceAuditMapper;
    @Autowired
    private CityAuditMapper cityAuditMapper;
    @Autowired
    private MinisterAuditMapper ministerAuditMapper;
    @Autowired
    private ReporManagementMapper reporManagementMapper;
    @Autowired
    private BasicProliferationReleaseMapper basicProliferationReleaseMapper;
    @Autowired
    private ProliferationReleaseStatisticsMapper proliferationReleaseStatisticsMapper;
    @Autowired
    private ReporManagementService reporManagementService;
    @Autowired
    private SysRegionApi sysRegionApi;

    @Override
    public List<ProvinceAuditVo> listProvinceAuditsByBelongYear(Map<String, Object> params) {
        String belongYear = (String) params.get("belongYear");
//        String orgName = (String) params.get("orgName");
//        String provinceId = (String) params.get("provinceId");
        // 未填所属年度，使用当前年度
        if(StringUtils.isBlank(belongYear) || belongYear.equals("0")){
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
            belongYear = dateFormat.format( new Date() );
            params.put("belongYear", belongYear);
        }
//        Map map = new HashMap();
//        map.put("belongYear",belongYear);
//        map.put("orgName",orgName);
        // 根据省id查询下级市(从当前用户获取所属provinceId)
//        map.put("provinceId",provinceId);
        List<ProvinceAuditVo> list = provinceAuditMapper.listProvinceAuditsByBelongYear(params);
        // 转换审核状态码描述
        auditStatuConvert(list);
        return list;
    }

    @Override
    public PageUtils<ProvinceAuditVo> getProvinceAuditsListByPage(Map<String, Object> params, int pageNo, int pageSize) {
        String belongYear = (String) params.get("belongYear");
//        String orgName = (String) params.get("orgName");
//        String provinceId = (String) params.get("provinceId");
        // 未填所属年度，使用当前年度
        if(StringUtils.isBlank(belongYear) || belongYear.equals("0")){
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
            belongYear = dateFormat.format( new Date() );
            params.put("belongYear", belongYear);
        }
//        Map map = new HashMap();
//        map.put("belongYear",belongYear);
//        map.put("orgName",orgName);
        // 根据省id查询下级市(从当前用户获取所属provinceId)
//        map.put("provinceId",provinceId);

        PageHelper.offsetPage(pageNo,pageSize);
        List<ProvinceAuditVo> list = provinceAuditMapper.listProvinceAuditsByBelongYear(params);
        // 转换审核状态码描述
        auditStatuConvert(list);
        PageInfo<ProvinceAuditVo> pageInfo = new PageInfo<>(list);
        return PageUtils.getPageUtils(pageInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String insert(ProvinceAudit provinceAudit) {
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

        String belongYear = provinceAudit.getBelongYear();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
        // 未填所属年度，使用当前年度
        if(StringUtils.isBlank(belongYear) || belongYear.equals("0")){
            belongYear = dateFormat.format( new Date() );
        }
        provinceAudit.setBelongYear(belongYear);

        provinceAudit.setId(IdUtil.getUUId());
        provinceAudit.setOrganizationId(organizationId);
        provinceAudit.setOrganizationName(organizationName);
        provinceAudit.setRoleCode(roleCode);
        boolean result = false;
        try {
            result = this.save(provinceAudit);
            if (result == false){
                return "0";
            }else {
                return "1";
            }
        } catch (Exception e){
            log.error("省级审核数据新增报错:" + e.getMessage());
            throw new SofnException("新增失败！");
        }
    }

    @Override
    public List<ProvinceReportManagementVo> reportManagement(Map<String, Object> params) {
        params.put("status",AuditStatueEnum.STATUS_APPROVE_PROV.getStatus());
        List<ProvinceReportManagementVo> list = provinceAuditMapper.reportManagement(params);
        // 转换审核状态码显示和上报情况显示
        reportSituationConvert(list);
        return list;
    }

    @Override
    public List<CityAuditVo> view(Map<String, Object> params) {
        List<CityAuditVo> list = cityAuditMapper.listCityAuditsByBelongYear(params);
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String provinceAuditApprove(Map<String, Object> params) {
        ProvinceAudit provinceAudit = provinceAuditMapper.selectByBelongYearAndCityId(params);
        // 当该市处于省级待审核时，允许审核通过
        if (!(provinceAudit != null && provinceAudit.getStatus().equals(AuditStatueEnum.STATUS_REPORT_PROV.getStatus()))){
            return "不满足通过前提条件";
        }

        String  belongYear = (String) params.get("belongYear");
        String  cityId = (String) params.get("cityId");
        String  roleCode = (String) params.get("roleCode");
        String  provinceId = (String) params.get("provinceId");
        String  organizationName = (String) params.get("organizationName");
        String  remark = (String) params.get("remark");
        Map mapOfBPR = new HashMap();
        mapOfBPR.put("belongYear",belongYear);
        mapOfBPR.put("provinceId",provinceId);
        mapOfBPR.put("cityId",cityId);
        mapOfBPR.put("status",AuditStatueEnum.STATUS_APPROVE_PROV.getStatus());
        mapOfBPR.put("organizationName",organizationName);
        mapOfBPR.put("roleCode",roleCode);
        // 重置退回意见
        mapOfBPR.put("remark",remark);
        // 改变数据状态的条件(省级待审核)
        mapOfBPR.put("statusConditionFirst",AuditStatueEnum.STATUS_REPORT_PROV.getStatus());
        try {
            // 省级审核表、市级审核表、水生生物审核、中央财政审核通过，暂时不考虑部、省、市本级和直属单位
            int resultOfProvinceAudit = provinceAuditMapper.updateStatus(mapOfBPR);
            int resultOfCityAudit = cityAuditMapper.updateStatus(mapOfBPR);
            int resultOfCountyAudit = reporManagementMapper.updateStatus(mapOfBPR);
            int resultOfBPR = basicProliferationReleaseMapper.updateStatus(mapOfBPR);
            int resultOfPRS = proliferationReleaseStatisticsMapper.updateStatus(mapOfBPR);
            if (resultOfProvinceAudit == 1 && resultOfCityAudit >= 0 && resultOfCountyAudit > 0
                        && resultOfBPR > 0 && resultOfPRS > 0){
                return "1";
            } else {
                throw new SofnException("省级审核通过失败！");
            }
        } catch (Exception e){
            log.error("省级审核通过报错:" + e.getMessage());
            throw new SofnException("省级审核通过失败！");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String provinceAuditReject(Map<String, Object> params) {
        ProvinceAudit provinceAudit = provinceAuditMapper.selectByBelongYearAndCityId(params);
        // 当该市处于省级待审核、部级已退回时，允许驳回
//        if (!(provinceAudit != null
//                && (provinceAudit.getStatus().equals(AuditStatueEnum.STATUS_REPORT_PROV.getStatus())
//                || provinceAudit.getStatus().equals(AuditStatueEnum.STATUS_RETURN_MASTER.getStatus())))){
//            return "不满足退回前提条件";
//        }
        if (provinceAudit == null || !org.apache.commons.lang3.StringUtils.equalsAny(provinceAudit.getStatus(),
                AuditStatueEnum.STATUS_REPORT_PROV.getStatus(), AuditStatueEnum.STATUS_RETURN_MASTER.getStatus(), AuditStatueEnum.STATUS_APPROVE_PROV.getStatus())) {
            return "不满足退回前提条件";
        }
        String belongYear = (String) params.get("belongYear");
        String cityId = (String) params.get("cityId");
        String remark = (String) params.get("remark");
        String roleCode = (String) params.get("roleCode");
        String provinceId = (String) params.get("provinceId");
        String organizationName = (String) params.get("organizationName");
        Map mapOfBPR = new HashMap();
        mapOfBPR.put("belongYear",belongYear);
        mapOfBPR.put("provinceId",provinceId);
        mapOfBPR.put("cityId",cityId);
        mapOfBPR.put("status",AuditStatueEnum.STATUS_RETURN_PROV.getStatus());
        mapOfBPR.put("organizationName",organizationName);
        mapOfBPR.put("roleCode",roleCode);
        // 改变数据状态的条件(省级待审核、部级已退回)
        mapOfBPR.put("statusConditionFirst",AuditStatueEnum.STATUS_REPORT_PROV.getStatus());
        mapOfBPR.put("statusConditionSecond",AuditStatueEnum.STATUS_RETURN_MASTER.getStatus());
        Map mapOfRemark = new HashMap();
        mapOfRemark.put("belongYear",belongYear);
        mapOfRemark.put("provinceId",provinceId);
        mapOfRemark.put("cityId",cityId);
        mapOfRemark.put("status",AuditStatueEnum.STATUS_RETURN_PROV.getStatus());
        mapOfRemark.put("organizationName",organizationName);
        mapOfRemark.put("remark",remark);
        mapOfRemark.put("roleCode",roleCode);
        // 改变数据状态的条件(省级待审核、部级已退回)
        mapOfRemark.put("statusConditionFirst",AuditStatueEnum.STATUS_REPORT_PROV.getStatus());
        mapOfRemark.put("statusConditionSecond",AuditStatueEnum.STATUS_RETURN_MASTER.getStatus());
        try {
            // 省级审核表、市级审核表、水生生物审核、中央财政审核驳回，暂时不考虑部、省、市本级和直属单位
            int resultOfProvinceAudit = provinceAuditMapper.updateStatus(mapOfRemark);
            int resultOfCityAudit = cityAuditMapper.updateStatus(mapOfBPR);
            int resultOfCountyAudit = 0;
            if (RoleLevelEnum.LEVEL_PROVINCE_ADD.getLevel().equals(roleCode)){
                resultOfCountyAudit = reporManagementMapper.updateStatus(mapOfRemark);
            }else {
                resultOfCountyAudit = reporManagementMapper.updateStatus(mapOfBPR);
            }
            int resultOfBPR = basicProliferationReleaseMapper.updateStatus(mapOfBPR);
            int resultOfPRS = proliferationReleaseStatisticsMapper.updateStatus(mapOfBPR);
            if (resultOfProvinceAudit == 1 && resultOfCityAudit >= 0 && resultOfCountyAudit > 0
                        && resultOfBPR > 0 && resultOfPRS > 0){
                reporManagementService.markReject(params);
                return "1";
            } else {
                throw new SofnException("省级审核驳回失败！");
            }
        } catch (Exception e){
            log.error("省级审核驳回报错:" + e.getMessage());
            throw new SofnException("省级审核驳回失败！");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String provinceAuditReport(Map<String, Object> params) {
        String  belongYear = (String) params.get("belongYear");
        String  provinceId = (String) params.get("provinceId");

        // 允许上报标志
        boolean reportFlag = false;
        // 部退回标志
        boolean returnMiniFlag = false;

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

        // 判断状态是否可以进行省级上报（下级市上报数 > 0）
        Map map = new HashMap();
        map.put("belongYear",belongYear);
        map.put("provinceId",provinceId);
        map.put("status",AuditStatueEnum.STATUS_APPROVE_PROV.getStatus());
        int count = provinceAuditMapper.countApproveCity(map);

        // 查询部级审核表是否已存在上报记录
        List<MinisterAudit> ministerAuditList = ministerAuditMapper.selectListByParams(params);
        MinisterAudit preMinisterAudit = ministerAuditList.size() > 0 ? ministerAuditList.get(0) : null;
        if (preMinisterAudit != null){
            // 处于部级已退回状态，可以继续省级上报
            if (preMinisterAudit.getStatus().equals(AuditStatueEnum.STATUS_RETURN_MASTER.getStatus())){
                reportFlag = true;
                returnMiniFlag = true;
            }
        }else {
            if (count == 0){// 没有上报的下级市
                return "没有可上报的下级市！";
            }else {
                reportFlag = true;
            }
        }

        if (reportFlag){
            MinisterAudit ministerAudit = new MinisterAudit();
            ministerAudit.setId(IdUtil.getUUId());
            ministerAudit.setBelongYear(belongYear);
            ministerAudit.setProvinceId(provinceId);
            // 部级审核表不需要具体到市id、区县id
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
            mapOfBPR.put("provinceId",provinceId);
            mapOfBPR.put("status",AuditStatueEnum.STATUS_REPORT_MASTER.getStatus());
            // 改变数据状态的条件(省级已通过、部级已退回)
            mapOfBPR.put("statusConditionFirst",AuditStatueEnum.STATUS_APPROVE_PROV.getStatus());
            mapOfBPR.put("statusConditionSecond",AuditStatueEnum.STATUS_RETURN_MASTER.getStatus());
            try {
                // 修改省级审核表的市记录状态、市级审核表的区县记录状态、县级上报管理表记录状态、水生生物的放流记录状态、中央财政记录的状态
                int resultOfProvAudit = provinceAuditMapper.updateStatus(mapOfBPR);
                int resultOfCityAudit = cityAuditMapper.updateStatus(mapOfBPR);
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

                if (returnMiniFlag){// 部退回，省级重新上报
                    if (resultOfProvAudit > 0 && resultOfBPR > 0 && resultOfPRS > 0){
                        reporManagementService.markSubmit(belongYear);
                        return "1";
                    }else {
                        throw new SofnException("省级上报失败！");
                    }
                }else {// 新的省级上报
                    if (resultOfMiniAudit == 1 && resultOfProvAudit > 0 && resultOfCityAudit >= 0
                            && resultOfCountyAudit > 0 && resultOfBPR > 0 && resultOfPRS > 0){
                        reporManagementService.markSubmit(belongYear);
                        return "1";
                    } else {
                        throw new SofnException("省级上报失败！");
                    }
                }
            } catch (Exception e){
                log.error("省级上报报错:" + e.getMessage());
                throw new SofnException("省级上报失败！");
            }
        }else {
            return "请等待上级退回后再上报";
        }
    }

    @Override
    public CityRemarkVo getRemark(Map<String, Object> params) {

        ProvinceAudit provinceAudit = provinceAuditMapper.selectByParams(params);
        CityRemarkVo cityRemarkVo = new CityRemarkVo();
        if (provinceAudit != null){
            BeanUtils.copyProperties(provinceAudit, cityRemarkVo);
        }
        return cityRemarkVo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String editRemark(ProvinceRemarkVo provinceRemarkVo) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", provinceRemarkVo.getId());
        ProvinceAudit provinceAudit = provinceAuditMapper.selectByParams(params);
        if (provinceAudit == null){
            throw new SofnException("原数据不存在！");
        }
        if (!provinceAudit.getStatus().equals(AuditStatueEnum.STATUS_RETURN_PROV.getStatus())){
            return "0";
        }
        BeanUtils.copyProperties(provinceRemarkVo,provinceAudit);
        try {
            int result = provinceAuditMapper.updateById(provinceAudit);
            if (result == 1){
                return "1";
            }else {
                return "0";
            }
        } catch (Exception e){
            log.error("省级填写驳回意见报错:" + e.getMessage());
            throw new SofnException("填写驳回意见失败！");
        }
    }

    @Override
    public PageUtils<ProvinceReportManagementVo> reportManagementByPage(Map<String, Object> provViewParams, int pageNo, int pageSize) {
        provViewParams.put("status",AuditStatueEnum.STATUS_APPROVE_PROV.getStatus());

        PageHelper.offsetPage(pageNo,pageSize);
        List<ProvinceReportManagementVo> list = provinceAuditMapper.reportManagement(provViewParams);
        // 设置应报数量(当前登录账户下级机构数量)
        int shouldReportCount = 0;
        ProvinceReportManagementVo provinceReportManagementVo = (list != null && list.size() > 0) ? list.get(0) : null;
        if (provinceReportManagementVo != null){
            shouldReportCount = FyemAreaUtil.getListCountByParentId(sysRegionApi);
            provinceReportManagementVo.setShouldReportCount(String.valueOf(shouldReportCount));
        }
        // 转换审核状态码显示和上报情况显示
        reportSituationConvert(list);
        PageInfo<ProvinceReportManagementVo> pageInfo = new PageInfo<>(list);
        return PageUtils.getPageUtils(pageInfo);
    }

    /**
     * 审核状态码转换页面显示
     * @param list
     * @return
     */
    private void auditStatuConvert(List<ProvinceAuditVo> list){
        for (ProvinceAuditVo provinceAuditVo: list ) {
            switch (provinceAuditVo.getStatus()){
                case "0": provinceAuditVo.setStatus(AuditStatueEnum.STATUS_NOTREPORT.getDescribe());
                            provinceAuditVo.setStatusCode("0");break;
                case "1": provinceAuditVo.setStatus(AuditStatueEnum.STATUS_REPORT_CITY.getDescribe());
                            provinceAuditVo.setStatusCode("1");break;
                case "2": provinceAuditVo.setStatus(AuditStatueEnum.STATUS_RETURN_CITY.getDescribe());
                            provinceAuditVo.setStatusCode("2");break;
                case "3": provinceAuditVo.setStatus(AuditStatueEnum.STATUS_APPROVE_CITY.getDescribe());
                            provinceAuditVo.setStatusCode("3");break;
                case "4": provinceAuditVo.setStatus(AuditStatueEnum.STATUS_REPORT_PROV.getDescribe());
                            provinceAuditVo.setStatusCode("4");break;
                case "5": provinceAuditVo.setStatus("已驳回");
                            provinceAuditVo.setStatusCode("5");break;
                case "6": provinceAuditVo.setStatus(AuditStatueEnum.STATUS_APPROVE_PROV.getDescribe());
                            provinceAuditVo.setStatusCode("6");break;
                case "7": provinceAuditVo.setStatus(AuditStatueEnum.STATUS_REPORT_MASTER.getDescribe());
                            provinceAuditVo.setStatusCode("7");break;
                case "8": provinceAuditVo.setStatus(AuditStatueEnum.STATUS_RETURN_MASTER.getDescribe());
                            provinceAuditVo.setStatusCode("8");break;
                case "9": provinceAuditVo.setStatus(AuditStatueEnum.STATUS_APPROVE_MASTER.getDescribe());
                            provinceAuditVo.setStatusCode("9");break;
            }
        }
    }

    /**
     * 上报情况转换页面显示
     * @param list
     */
    private void reportSituationConvert(List<ProvinceReportManagementVo> list){
        for (ProvinceReportManagementVo proVo : list){
            StringBuilder builder = new StringBuilder().append("上报").append(proVo.getReportCount()).append("/应报").append(proVo.getShouldReportCount());
            proVo.setReportSituation(builder.toString());
            switch (proVo.getStatus()){
                case "0": proVo.setStatus(AuditStatueEnum.STATUS_NOTREPORT.getDescribe());
                            proVo.setStatusCode("0");break;
                case "1": proVo.setStatus("未上报");proVo.setStatusCode("1");break;
                case "2": proVo.setStatus("未上报");proVo.setStatusCode("2");break;
                case "3": proVo.setStatus("未上报");proVo.setStatusCode("3");break;
                case "4": proVo.setStatus("未上报");proVo.setStatusCode("4");break;
                case "5": proVo.setStatus("未上报");proVo.setStatusCode("5");break;
                case "6": proVo.setStatus("未上报");proVo.setStatusCode("6");break;
                case "7": proVo.setStatus(AuditStatueEnum.STATUS_REPORT_MASTER.getDescribe());
                            proVo.setStatusCode("7");break;
                case "8": proVo.setStatus("被驳回");proVo.setStatusCode("8");break;
                case "9": proVo.setStatus(AuditStatueEnum.STATUS_APPROVE_MASTER.getDescribe());
                            proVo.setStatusCode("9");break;
            }
        }
    }
}
