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
import com.sofn.fyem.service.MinisterAuditService;
import com.sofn.fyem.service.ReporManagementService;
import com.sofn.fyem.sysapi.bean.SysOrganization;
import com.sofn.fyem.util.RoleCodeUtil;
import com.sofn.fyem.vo.MinisterAuditVo;
import com.sofn.fyem.vo.MinisterRemarkVo;
import com.sofn.fyem.vo.ProvinceAuditVo;
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
 * @Description: 部级审核接口实现
 * @Author: DJH
 * @Date: 2020/4/27 15:06
 */
@Service(value = "ministerAuditService")
@Slf4j
public class MinisterAuditServiceImpl extends ServiceImpl<MinisterAuditMapper, MinisterAudit> implements MinisterAuditService {

    @Autowired
    private MinisterAuditMapper ministerAuditMapper;
    @Autowired
    private ProvinceAuditMapper provinceAuditMapper;
    @Autowired
    private CityAuditMapper cityAuditMapper;
    @Autowired
    private ReporManagementMapper reporManagementMapper;
    @Autowired
    private ReporManagementService reporManagementService;
    @Autowired
    private BasicProliferationReleaseMapper basicProliferationReleaseMapper;
    @Autowired
    private ProliferationReleaseStatisticsMapper proliferationReleaseStatisticsMapper;

    @Override
    public List<MinisterAuditVo> listMinisterAuditsByBelongYear(Map<String, Object> params) {
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
        List<MinisterAuditVo> list = ministerAuditMapper.listMinisterAuditsByBelongYear(params);
        // 转换审核状态码描述
        auditStatuConvert(list);
        return list;
    }

    @Override
    public PageUtils<MinisterAuditVo> getMinisterAuditsListByPage(Map<String, Object> params, int pageNo, int pageSize) {
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

        PageHelper.offsetPage(pageNo,pageSize);
        List<MinisterAuditVo> list = ministerAuditMapper.listMinisterAuditsByBelongYear(params);
        // 转换审核状态码描述
        auditStatuConvert(list);
        PageInfo<MinisterAuditVo> pageInfo = new PageInfo<>(list);
        return PageUtils.getPageUtils(pageInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String insert(MinisterAudit ministerAudit) {

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

        String belongYear = ministerAudit.getBelongYear();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
        // 未填所属年度，使用当前年度
        if(StringUtils.isBlank(belongYear) || belongYear.equals("0")){
            belongYear = dateFormat.format( new Date() );
        }
        ministerAudit.setBelongYear(belongYear);

        ministerAudit.setId(IdUtil.getUUId());
        ministerAudit.setOrganizationId(organizationId);
        ministerAudit.setOrganizationName(organizationName);
        ministerAudit.setRoleCode(roleCode);
        boolean result = false;
        try {
            result = this.save(ministerAudit);
            if (result == false){
                return "0";
            }else {
                return "1";
            }
        } catch (Exception e){
            log.error("部级审核数据新增报错" + e.getMessage());
            throw new SofnException("新增失败！");
        }
    }

    @Override
    public List<ProvinceAuditVo> view(Map<String, Object> params) {
        List<ProvinceAuditVo> list = provinceAuditMapper.listProvinceAuditsByBelongYear(params);
        return list;
    }

    @Override
    public String ministerAuditApprove(Map<String, Object> params) {
        MinisterAudit ministerAudit = ministerAuditMapper.selectByBelongYearAndProvId(params);
        // 仅当该省处于部级待审核时，允许审核通过
        if (!(ministerAudit != null && ministerAudit.getStatus().equals(AuditStatueEnum.STATUS_REPORT_MASTER.getStatus()))){
            return "不满足通过前提条件";
        }

        String  belongYear = (String) params.get("belongYear");
        String  provinceId = (String) params.get("provinceId");
        String  roleCode = (String) params.get("roleCode");
        String  organizationName = (String) params.get("organizationName");
        String  remark = (String) params.get("remark");
        Map<String, Object> mapOfBPR = new HashMap<>();
        mapOfBPR.put("belongYear",belongYear);
        mapOfBPR.put("provinceId",provinceId);
        mapOfBPR.put("status",AuditStatueEnum.STATUS_APPROVE_MASTER.getStatus());
        mapOfBPR.put("organizationName",organizationName);
        mapOfBPR.put("roleCode",roleCode);
        // 重置退回意见
        mapOfBPR.put("remark",remark);
        // 改变数据状态的条件(部级待审核)
        mapOfBPR.put("statusConditionFirst",AuditStatueEnum.STATUS_REPORT_MASTER.getStatus());
        try {
            // 部级审核表、省级审核表、市级审核表、水生生物审核、中央财政审核通过，暂时不考虑部、省、市本级和直属单位
            int resultOfMinisterAudit = ministerAuditMapper.updateStatus(mapOfBPR);
            int resultOfProvinceAudit = provinceAuditMapper.updateStatus(mapOfBPR);
            int resultOfCityAudit = cityAuditMapper.updateStatus(mapOfBPR);
            int resultOfCountyAudit = reporManagementMapper.updateStatus(mapOfBPR);
            int resultOfBPR = basicProliferationReleaseMapper.updateStatus(mapOfBPR);
            int resultOfPRS = proliferationReleaseStatisticsMapper.updateStatus(mapOfBPR);
            if (resultOfMinisterAudit == 1 && resultOfProvinceAudit >= 0 && resultOfCityAudit >= 0
                    && resultOfCountyAudit > 0 && resultOfBPR > 0 && resultOfPRS > 0){
                return "1";
            } else {
                throw new SofnException("部级审核通过失败！");
            }
        } catch (Exception e){
            log.error("部级审核通过报错:" + e.getMessage());
            throw new SofnException("部级审核通过失败！");
        }
    }

    @Override
    public String ministerAuditReject(Map<String, Object> params) {
        MinisterAudit ministerAudit = ministerAuditMapper.selectByBelongYearAndProvId(params);
        // 仅当该省处于部级待审核时，允许驳回
//        if (!(ministerAudit != null
//                && ministerAudit.getStatus().equals(AuditStatueEnum.STATUS_REPORT_MASTER.getStatus()))){
//            return "不满足退回前提条件";
//        }
        if (ministerAudit == null || !org.apache.commons.lang3.StringUtils.equalsAny(ministerAudit.getStatus(),
                AuditStatueEnum.STATUS_APPROVE_MASTER.getStatus(), AuditStatueEnum.STATUS_REPORT_MASTER.getStatus())) {
            return "不满足退回前提条件";
        }

        String belongYear = (String) params.get("belongYear");
        String provinceId = (String) params.get("provinceId");
        String remark = (String) params.get("remark");
        String roleCode = (String) params.get("roleCode");
        String organizationName = (String) params.get("organizationName");
        Map<String, Object> mapOfBPR = new HashMap<>();
        mapOfBPR.put("belongYear",belongYear);
        mapOfBPR.put("provinceId",provinceId);
        mapOfBPR.put("status",AuditStatueEnum.STATUS_RETURN_MASTER.getStatus());
        mapOfBPR.put("organizationName",organizationName);
        mapOfBPR.put("roleCode",roleCode);
        // 改变数据状态的条件(部级待审核/部级已通过)
        mapOfBPR.put("statusConditionFirst",AuditStatueEnum.STATUS_REPORT_MASTER.getStatus());
        mapOfBPR.put("statusConditionSecond",AuditStatueEnum.STATUS_APPROVE_MASTER.getStatus());
        Map<String, Object> mapOfRemark = new HashMap<>();
        mapOfRemark.put("belongYear",belongYear);
        mapOfRemark.put("provinceId",provinceId);
        mapOfRemark.put("status",AuditStatueEnum.STATUS_RETURN_MASTER.getStatus());
        mapOfRemark.put("organizationName",organizationName);
        mapOfRemark.put("remark",remark);
        mapOfRemark.put("roleCode",roleCode);
        // 改变数据状态的条件(部级待审核/部级已通过)
        mapOfRemark.put("statusConditionFirst",AuditStatueEnum.STATUS_REPORT_MASTER.getStatus());
        mapOfRemark.put("statusConditionSecond",AuditStatueEnum.STATUS_APPROVE_MASTER.getStatus());
        try {
            // 部级审核表、省级审核表、市级审核表、水生生物审核、中央财政审核驳回，暂时不考虑部、省、市本级和直属单位
            int resultOfMinisterAudit = ministerAuditMapper.updateStatus(mapOfRemark);
            int resultOfProvinceAudit = provinceAuditMapper.updateStatus(mapOfBPR);
            int resultOfCityAudit = cityAuditMapper.updateStatus(mapOfBPR);
            int resultOfCountyAudit = 0;
            if (RoleLevelEnum.LEVEL_MASTER_ADD.getLevel().equals(roleCode)){
                resultOfCountyAudit = reporManagementMapper.updateStatus(mapOfRemark);
            }else {
                resultOfCountyAudit = reporManagementMapper.updateStatus(mapOfBPR);
            }
            int resultOfBPR = basicProliferationReleaseMapper.updateStatus(mapOfBPR);
            int resultOfPRS = proliferationReleaseStatisticsMapper.updateStatus(mapOfBPR);
            if (resultOfMinisterAudit == 1 && resultOfProvinceAudit >= 0 && resultOfCityAudit >= 0
                    && resultOfCountyAudit > 0 && resultOfBPR > 0 && resultOfPRS > 0){
                reporManagementService.markReject(params);
                return "1";
            } else {
                throw new SofnException("部级审核驳回失败！");
            }
        } catch (Exception e){
            log.error("部级审核驳回报错:" + e.getMessage());
            throw new SofnException("部级审核驳回失败！");
        }
    }

    @Override
    public MinisterRemarkVo getRemark(Map<String, Object> params) {

        MinisterAudit ministerAudit = ministerAuditMapper.selectByParams(params);
        MinisterRemarkVo ministerRemarkVo = new MinisterRemarkVo();
        if (ministerAudit != null){
            BeanUtils.copyProperties(ministerAudit, ministerRemarkVo);
        }
        return ministerRemarkVo;
    }

    @Override
    public String editRemark(MinisterRemarkVo ministerRemarkVo) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", ministerRemarkVo.getId());
        MinisterAudit ministerAudit = ministerAuditMapper.selectByParams(params);
        if (ministerAudit == null){
            throw new SofnException("原数据不存在！");
        }
        // 非部级退回状态，不允许填写驳回意见
        if (!ministerAudit.getStatus().equals(AuditStatueEnum.STATUS_RETURN_MASTER.getStatus())){
            return "0";
        }
        BeanUtils.copyProperties(ministerRemarkVo,ministerAudit);
        try {
            int result = ministerAuditMapper.updateById(ministerAudit);
            if (result == 1){
                return "1";
            }else {
                return "0";
            }
        } catch (Exception e){
            log.error("部级审核填写驳回意见报错" + e.getMessage());
            throw new SofnException("填写驳回意见失败！");
        }
    }

    /**
     * 审核状态码转换页面显示
     * @param list
     * @return
     */
    private void auditStatuConvert(List<MinisterAuditVo> list){
        for (MinisterAuditVo ministerAuditVo: list ) {
            switch (ministerAuditVo.getStatus()){
                case "0": ministerAuditVo.setStatus(AuditStatueEnum.STATUS_NOTREPORT.getDescribe());
                            ministerAuditVo.setStatusCode("0");break;
                case "1": ministerAuditVo.setStatus(AuditStatueEnum.STATUS_REPORT_CITY.getDescribe());
                            ministerAuditVo.setStatusCode("1");break;
                case "2": ministerAuditVo.setStatus(AuditStatueEnum.STATUS_RETURN_CITY.getDescribe());
                            ministerAuditVo.setStatusCode("2");break;
                case "3": ministerAuditVo.setStatus(AuditStatueEnum.STATUS_APPROVE_CITY.getDescribe());
                            ministerAuditVo.setStatusCode("3");break;
                case "4": ministerAuditVo.setStatus(AuditStatueEnum.STATUS_REPORT_PROV.getDescribe());
                            ministerAuditVo.setStatusCode("4");break;
                case "5": ministerAuditVo.setStatus(AuditStatueEnum.STATUS_RETURN_PROV.getDescribe());
                            ministerAuditVo.setStatusCode("5");break;
                case "6": ministerAuditVo.setStatus(AuditStatueEnum.STATUS_APPROVE_PROV.getDescribe());
                            ministerAuditVo.setStatusCode("6");break;
                case "7": ministerAuditVo.setStatus(AuditStatueEnum.STATUS_REPORT_MASTER.getDescribe());
                            ministerAuditVo.setStatusCode("7");break;
                case "8": ministerAuditVo.setStatus("已驳回");
                            ministerAuditVo.setStatusCode("8");break;
                case "9": ministerAuditVo.setStatus(AuditStatueEnum.STATUS_APPROVE_MASTER.getDescribe());
                            ministerAuditVo.setStatusCode("9");break;
            }
        }
    }

}
