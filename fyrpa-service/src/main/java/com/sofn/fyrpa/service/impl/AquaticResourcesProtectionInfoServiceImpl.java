package com.sofn.fyrpa.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sofn.common.exception.SofnException;
import com.sofn.common.model.Result;
import com.sofn.common.model.User;
import com.sofn.common.utils.DateUtils;
import com.sofn.common.utils.JsonUtils;
import com.sofn.fyrpa.enums.CheckEnum;
import com.sofn.fyrpa.enums.DeleteEnum;
import com.sofn.fyrpa.enums.FyrpaEnum;
import com.sofn.fyrpa.mapper.*;
import com.sofn.fyrpa.model.*;
import com.sofn.fyrpa.model.FileInfo;
import com.sofn.fyrpa.service.AquaticResourcesProtectionInfoService;
import com.sofn.fyrpa.sysapi.SysFileApi;
import com.sofn.fyrpa.sysapi.SysRegionApi;
import com.sofn.fyrpa.sysapi.bean.*;
import com.sofn.fyrpa.vo.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class AquaticResourcesProtectionInfoServiceImpl implements AquaticResourcesProtectionInfoService {

    @Autowired
    private AquaticResourcesProtectionInfoMapper aquaticResourcesProtectionInfoMapper;
    @Autowired
    private EnvironmentResourcesMapper environmentResourcesMapper;
    @Autowired
    private ManagerOrgMapper managerOrgMapper;
    @Autowired(required = false)
    private SysFileApi sysFileApi;
    @Autowired
    private FileinfoMapper fileinfoMapper;
    @Autowired
    private InformationAuditMapper informationAuditMapper;
    @Autowired(required = false)
    private SysRegionApi sysRegionApi;

    @Transactional
    @Override
    public Result addResourcesProtection(AquaticResourcesProtectionInfoVo aquaticResourcesProtectionInfoVo, EnvironmentResourcesVo environmentResourcesVo, ManagerOrgVo managerOrgVo, User user) {
         EnvironmentResources environmentResources = new EnvironmentResources();
         environmentResources.setBasinOrSeaArea(environmentResourcesVo.getBasinOrSeaArea());
         environmentResources.setRiverOrMaritimeSpace(environmentResourcesVo.getRiverOrMaritimeSpace());
         environmentResources.setMajorProtectObject(environmentResourcesVo.getMajorProtectObject());
         environmentResources.setLongitude(environmentResourcesVo.getLongitudeRange()+environmentResourcesVo.getLongitudeStart()+"-"+environmentResourcesVo.getLongitudeEnd());
         environmentResources.setLatitude(environmentResourcesVo.getLatitudeRange()+environmentResourcesVo.getLatitudeStart()+"-"+environmentResourcesVo.getLatitudeEnd());
         environmentResources.setStartTime(DateUtils.stringToDate(environmentResourcesVo.getStartTime(),DateUtils.DATE_PATTERN));
         environmentResources.setEndTime(DateUtils.stringToDate(environmentResourcesVo.getEndTime(),DateUtils.DATE_PATTERN));
         environmentResources.setCurrentProtectionArea(environmentResourcesVo.getCurrentProtectionArea());
         environmentResources.setCoreRegionArea(environmentResourcesVo.getCoreRegionArea());
         environmentResources.setExperimentRegionArea(environmentResourcesVo.getExperimentRegionArea());
         environmentResources.setCreateTime(new Date());
         environmentResources.setIsDel(DeleteEnum.DEL_FLAG_N.getKey());
         int result1 = this.environmentResourcesMapper.insert(environmentResources);

         ManagerOrg managerOrg = new ManagerOrg();
         managerOrg.setManagerOrgName(managerOrgVo.getManagerOrgName());
         managerOrg.setOrgFormationDept(managerOrgVo.getOrgFormationDept());
         managerOrg.setDeptApproveNumber(managerOrgVo.getDeptApproveNumber());
         managerOrg.setManagerOrgLevel(managerOrgVo.getManagerOrgLevel());
         managerOrg.setManagerOrgQuality(managerOrgVo.getManagerOrgQuality());
         managerOrg.setJurisdictionRelation(managerOrgVo.getJurisdictionRelation());
         managerOrg.setManagerOrgStaffFormation(managerOrgVo.getManagerOrgStaffFormation());
         managerOrg.setManagerStaffAmout(managerOrgVo.getManagerStaffAmout());
         managerOrg.setTechnologyStaffAmout(managerOrgVo.getTechnologyStaffAmout());
         managerOrg.setIsFunds(managerOrgVo.getIsFunds());
         managerOrg.setFundsSource(managerOrgVo.getFundsSource());
         managerOrg.setFixedFunds(managerOrgVo.getFixedFunds());
         managerOrg.setManagerOrgAddr(managerOrgVo.getManagerOrgAddr());
         managerOrg.setManagerOrgPostCode(managerOrgVo.getManagerOrgPostCode());
         managerOrg.setManagerOrgPhone(managerOrgVo.getManagerOrgPhone());
         managerOrg.setManagerOrgPortraiture(managerOrgVo.getManagerOrgPortraiture());
         managerOrg.setEmail(managerOrgVo.getEmail());
         managerOrg.setCreateTime(new Date());
         managerOrg.setIsDel(DeleteEnum.DEL_FLAG_N.getKey());
         int result2 = this.managerOrgMapper.insert(managerOrg);

         if(result1!=0 && result2!=0){
             AquaticResourcesProtectionInfo aquaticResourcesProtectionInfo = new AquaticResourcesProtectionInfo();
             String[] regionCodeArr = aquaticResourcesProtectionInfoVo.getRegionCode();
             String regionCode = StringUtils.join(regionCodeArr, ",");
             aquaticResourcesProtectionInfo.setRegionCode(regionCode);
             aquaticResourcesProtectionInfo.setName(aquaticResourcesProtectionInfoVo.getName());
             aquaticResourcesProtectionInfo.setDepartmentManager(aquaticResourcesProtectionInfoVo.getDepartmentManager());
             aquaticResourcesProtectionInfo.setApproveTime(DateUtils.stringToDate(aquaticResourcesProtectionInfoVo.getApproveTime(),DateUtils.DATE_PATTERN));
             aquaticResourcesProtectionInfo.setApproveDocNumber(aquaticResourcesProtectionInfoVo.getApproveDocNumber());
             aquaticResourcesProtectionInfo.setIsAdjust(aquaticResourcesProtectionInfoVo.getIsAdjust());
             aquaticResourcesProtectionInfo.setAdjustTime(DateUtils.stringToDate(aquaticResourcesProtectionInfoVo.getAdjustTime(),DateUtils.DATE_PATTERN));
             aquaticResourcesProtectionInfo.setCreateTime(new Date());
             aquaticResourcesProtectionInfo.setIsDel(DeleteEnum.DEL_FLAG_N.getKey());
             aquaticResourcesProtectionInfo.setEnvironmentResourcesId(environmentResources.getId());
             aquaticResourcesProtectionInfo.setManagerOrgId(managerOrg.getId());
             aquaticResourcesProtectionInfo.setStatus(CheckEnum.IS_WSB.getVal());
             aquaticResourcesProtectionInfo.setFileIds(aquaticResourcesProtectionInfoVo.getFileIds());
             aquaticResourcesProtectionInfo.setSubmitTime(aquaticResourcesProtectionInfoVo.getSubmitTime());
             //保存用户id
             aquaticResourcesProtectionInfo.setCreateUserId(user.getId());
             /*//保存区域名称
             String organizationId =(user != null ? user.getOrganizationId() : null);
             String agentCode="";

             Result<List<SysOrganization>> result = sysRegionApi.getOrgAgentByOrgId(FyrpaEnum.fyrpa_id.getCode(),organizationId,agentCode);
             SysOrganization sysOrganization = result.getData().get(0);
             if(sysOrganization!=null){
                 Result<SysOrganizationForm> orgInfoById = sysRegionApi.getOrgInfoById(sysOrganization.getId());
                 SysRegionInfoVo regionInfoVo = orgInfoById.getData().getRegionInfoVo();
                 aquaticResourcesProtectionInfo.setAreaName(regionInfoVo.getProvinceName());
             }*/
             //获取当前用户所在的机构id
             Result<SysOrganizationForm> orgInfoById = sysRegionApi.getOrgInfoById(user != null ? user.getOrganizationId() : null);
             String organizationName= orgInfoById.getData().getOrganizationName();
             aquaticResourcesProtectionInfo.setSubmitUnit(organizationName);
             AquaticResourcesProtectionInfo aquaticResourcesProtectionInfo1 = this.aquaticResourcesProtectionInfoMapper.selectByName(aquaticResourcesProtectionInfoVo.getName(),aquaticResourcesProtectionInfoVo.getSubmitTime());
            if(aquaticResourcesProtectionInfo1!=null && aquaticResourcesProtectionInfo1.getName().equals(aquaticResourcesProtectionInfoVo.getName())&&aquaticResourcesProtectionInfo1.getSubmitTime().equals(aquaticResourcesProtectionInfoVo.getSubmitTime())) {
                return Result.error("不能添加重复数据");
            }else {
                int result4 = this.aquaticResourcesProtectionInfoMapper.insert(aquaticResourcesProtectionInfo);
                SysFileManageForm sysFileManageForm = new SysFileManageForm();
                sysFileManageForm.setSystemId(FyrpaEnum.fyrpa_id.getCode());
                sysFileManageForm.setIds(aquaticResourcesProtectionInfoVo.getFileIds());
                //调用平台激活文件接口
                Result<List<SysFileVo>> listResult = sysFileApi.activationFile(sysFileManageForm);

                if(listResult.getData()==null || listResult.getData().size()==0){
                    throw  new SofnException("激活文件失败");
                }

                //文件信息存入数据库
                FileInfo fileInfo = new FileInfo();
                fileInfo.setFileIds(sysFileManageForm.getIds());
                fileInfo.setSystemId(sysFileManageForm.getSystemId());
                fileInfo.setAquaticResourcesProtectionInfoId(aquaticResourcesProtectionInfo.getId());
                int result3 = this.fileinfoMapper.insert(fileInfo);

                if(result1!=0 && result2!=0 && result3!=0 && result4!=0){
                    return Result.ok("添加成功");
                }
            }
         }
         return Result.error("添加失败");
    }

    @Transactional
    @Override
    public Result addAndSbResourcesProtection(AquaticResourcesProtectionInfoVo aquaticResourcesProtectionInfoVo, EnvironmentResourcesVo environmentResourcesVo, ManagerOrgVo managerOrgVo,User user) {
        EnvironmentResources environmentResources = new EnvironmentResources();
        environmentResources.setBasinOrSeaArea(environmentResourcesVo.getBasinOrSeaArea());
        environmentResources.setRiverOrMaritimeSpace(environmentResourcesVo.getRiverOrMaritimeSpace());
        environmentResources.setMajorProtectObject(environmentResourcesVo.getMajorProtectObject());
        environmentResources.setLongitude(environmentResourcesVo.getLongitudeRange()+environmentResourcesVo.getLongitudeStart()+"-"+environmentResourcesVo.getLongitudeEnd());
        environmentResources.setLatitude(environmentResourcesVo.getLatitudeRange()+environmentResourcesVo.getLatitudeStart()+"-"+environmentResourcesVo.getLatitudeEnd());
        environmentResources.setStartTime(DateUtils.stringToDate(environmentResourcesVo.getStartTime(),DateUtils.DATE_PATTERN));
        environmentResources.setEndTime(DateUtils.stringToDate(environmentResourcesVo.getEndTime(),DateUtils.DATE_PATTERN));
        environmentResources.setCurrentProtectionArea(environmentResourcesVo.getCurrentProtectionArea());
        environmentResources.setCoreRegionArea(environmentResourcesVo.getCoreRegionArea());
        environmentResources.setExperimentRegionArea(environmentResourcesVo.getExperimentRegionArea());
        environmentResources.setCreateTime(new Date());
        environmentResources.setIsDel(DeleteEnum.DEL_FLAG_N.getKey());
        int result1 = this.environmentResourcesMapper.insert(environmentResources);

        ManagerOrg managerOrg = new ManagerOrg();
        managerOrg.setManagerOrgName(managerOrgVo.getManagerOrgName());
        managerOrg.setOrgFormationDept(managerOrgVo.getOrgFormationDept());
        managerOrg.setDeptApproveNumber(managerOrgVo.getDeptApproveNumber());
        managerOrg.setManagerOrgLevel(managerOrgVo.getManagerOrgLevel());
        managerOrg.setManagerOrgQuality(managerOrgVo.getManagerOrgQuality());
        managerOrg.setJurisdictionRelation(managerOrgVo.getJurisdictionRelation());
        managerOrg.setManagerOrgStaffFormation(managerOrgVo.getManagerOrgStaffFormation());
        managerOrg.setManagerStaffAmout(managerOrgVo.getManagerStaffAmout());
        managerOrg.setTechnologyStaffAmout(managerOrgVo.getTechnologyStaffAmout());
        managerOrg.setIsFunds(managerOrgVo.getIsFunds());
        managerOrg.setFundsSource(managerOrgVo.getFundsSource());
        managerOrg.setFixedFunds(managerOrgVo.getFixedFunds());
        managerOrg.setManagerOrgAddr(managerOrgVo.getManagerOrgAddr());
        managerOrg.setManagerOrgPostCode(managerOrgVo.getManagerOrgPostCode());
        managerOrg.setManagerOrgPhone(managerOrgVo.getManagerOrgPhone());
        managerOrg.setManagerOrgPortraiture(managerOrgVo.getManagerOrgPortraiture());
        managerOrg.setEmail(managerOrgVo.getEmail());
        managerOrg.setCreateTime(new Date());
        managerOrg.setIsDel(DeleteEnum.DEL_FLAG_N.getKey());
        int result2 = this.managerOrgMapper.insert(managerOrg);

        if(result1!=0 && result2!=0){
            AquaticResourcesProtectionInfo aquaticResourcesProtectionInfo = new AquaticResourcesProtectionInfo();
            String[] regionCodeArr = aquaticResourcesProtectionInfoVo.getRegionCode();
            String regionCode = StringUtils.join(regionCodeArr, ",");
            aquaticResourcesProtectionInfo.setRegionCode(regionCode);
            aquaticResourcesProtectionInfo.setName(aquaticResourcesProtectionInfoVo.getName());
            aquaticResourcesProtectionInfo.setDepartmentManager(aquaticResourcesProtectionInfoVo.getDepartmentManager());
            aquaticResourcesProtectionInfo.setApproveTime(DateUtils.stringToDate(aquaticResourcesProtectionInfoVo.getApproveTime(),DateUtils.DATE_PATTERN));
            aquaticResourcesProtectionInfo.setApproveDocNumber(aquaticResourcesProtectionInfoVo.getApproveDocNumber());
            aquaticResourcesProtectionInfo.setIsAdjust(aquaticResourcesProtectionInfoVo.getIsAdjust());
            aquaticResourcesProtectionInfo.setAdjustTime(DateUtils.stringToDate(aquaticResourcesProtectionInfoVo.getAdjustTime(),DateUtils.DATE_PATTERN));
            aquaticResourcesProtectionInfo.setCreateTime(new Date());
            aquaticResourcesProtectionInfo.setIsDel(DeleteEnum.DEL_FLAG_N.getKey());
            aquaticResourcesProtectionInfo.setEnvironmentResourcesId(environmentResources.getId());
            aquaticResourcesProtectionInfo.setManagerOrgId(managerOrg.getId());
            aquaticResourcesProtectionInfo.setStatus(CheckEnum.IS_DSP_WJZJ.getVal());
            aquaticResourcesProtectionInfo.setReportTime(Calendar.getInstance().getTime());
            aquaticResourcesProtectionInfo.setFileIds(aquaticResourcesProtectionInfoVo.getFileIds());
            aquaticResourcesProtectionInfo.setSubmitTime(aquaticResourcesProtectionInfoVo.getSubmitTime());
            //保存用户id
            aquaticResourcesProtectionInfo.setCreateUserId(user.getId());
            /*//保存区域名称
            String organizationId =(user != null ? user.getOrganizationId() : null);
            String agentCode="";

            Result<List<SysOrganization>> result = sysRegionApi.getOrgAgentByOrgId(FyrpaEnum.fyrpa_id.getCode(),organizationId,agentCode);
            SysOrganization sysOrganization = result.getData().get(0);
            if(sysOrganization!=null){
                Result<SysOrganizationForm> orgInfoById = sysRegionApi.getOrgInfoById(sysOrganization.getId());
                SysRegionInfoVo regionInfoVo = orgInfoById.getData().getRegionInfoVo();
                aquaticResourcesProtectionInfo.setAreaName(regionInfoVo.getProvinceName());
            }*/
            //获取当前用户所在的机构id
            Result<SysOrganizationForm> orgInfoById = sysRegionApi.getOrgInfoById(user != null ? user.getOrganizationId() : null);
            String organizationName= orgInfoById.getData().getOrganizationName();
            aquaticResourcesProtectionInfo.setSubmitUnit(organizationName);
            AquaticResourcesProtectionInfo aquaticResourcesProtectionInfo1 = this.aquaticResourcesProtectionInfoMapper.selectByName(aquaticResourcesProtectionInfoVo.getName(),aquaticResourcesProtectionInfoVo.getSubmitTime());
            if(aquaticResourcesProtectionInfo1!=null && aquaticResourcesProtectionInfo1.getName().equals(aquaticResourcesProtectionInfoVo.getName())&&aquaticResourcesProtectionInfo1.getSubmitTime().equals(aquaticResourcesProtectionInfoVo.getSubmitTime())) {
                return Result.error("不能添加重复数据");
            }else {
                int result4 = this.aquaticResourcesProtectionInfoMapper.insert(aquaticResourcesProtectionInfo);
                SysFileManageForm sysFileManageForm = new SysFileManageForm();
                sysFileManageForm.setSystemId(FyrpaEnum.fyrpa_id.getCode());
                sysFileManageForm.setIds(aquaticResourcesProtectionInfoVo.getFileIds());
                //调用平台激活文件接口
                Result<List<SysFileVo>> listResult = sysFileApi.activationFile(sysFileManageForm);

                if(listResult.getData()==null || listResult.getData().size()==0){
                    throw  new SofnException("激活文件失败");
                }

                //文件信息存入数据库
                FileInfo fileInfo = new FileInfo();
                fileInfo.setFileIds(sysFileManageForm.getIds());
                fileInfo.setSystemId(sysFileManageForm.getSystemId());
                fileInfo.setAquaticResourcesProtectionInfoId(aquaticResourcesProtectionInfo.getId());
                int result3 = this.fileinfoMapper.insert(fileInfo);

                if(result1!=0 && result2!=0 && result3!=0 && result4!=0){
                    return Result.ok("上报成功");
                }
            }
        }
        return Result.error("上报失败");
    }



    @Transactional
    @Override
    public Result updateResourcesProtection(String id, AquaticResourcesProtectionInfoVo aquaticResourcesProtectionInfoVo, EnvironmentResourcesVo environmentResourcesVo, ManagerOrgVo managerOrgVo,User user) {
        QueryWrapper<AquaticResourcesProtectionInfo>wrapper1 = new QueryWrapper<>();
        wrapper1.eq("id",id);
        wrapper1.eq("is_del",DeleteEnum.DEL_FLAG_N.getKey());
        AquaticResourcesProtectionInfo aquaticResourcesProtectionInfo = this.aquaticResourcesProtectionInfoMapper.selectOne(wrapper1);

        if(aquaticResourcesProtectionInfo!=null ) {
            QueryWrapper<EnvironmentResources> wrapper2 = new QueryWrapper<>();
            wrapper2.eq("id", aquaticResourcesProtectionInfo.getEnvironmentResourcesId());
            wrapper2.eq("is_del", DeleteEnum.DEL_FLAG_N.getKey());
            EnvironmentResources environmentResources = this.environmentResourcesMapper.selectOne(wrapper2);

            QueryWrapper<ManagerOrg> wrapper3 = new QueryWrapper<>();
            wrapper3.eq("id", aquaticResourcesProtectionInfo.getManagerOrgId());
            wrapper3.eq("is_del", DeleteEnum.DEL_FLAG_N.getKey());
            ManagerOrg managerOrg = this.managerOrgMapper.selectOne(wrapper3);

            environmentResources.setBasinOrSeaArea(environmentResourcesVo.getBasinOrSeaArea());
            environmentResources.setRiverOrMaritimeSpace(environmentResourcesVo.getRiverOrMaritimeSpace());
            environmentResources.setMajorProtectObject(environmentResourcesVo.getMajorProtectObject());
            environmentResources.setLongitude(environmentResourcesVo.getLongitudeRange()+environmentResourcesVo.getLongitudeStart()+"-"+environmentResourcesVo.getLongitudeEnd());
            environmentResources.setLatitude(environmentResourcesVo.getLatitudeRange()+environmentResourcesVo.getLatitudeStart()+"-"+environmentResourcesVo.getLatitudeEnd());
            environmentResources.setStartTime(DateUtils.stringToDate(environmentResourcesVo.getStartTime(),DateUtils.DATE_PATTERN));
            environmentResources.setEndTime(DateUtils.stringToDate(environmentResourcesVo.getEndTime(),DateUtils.DATE_PATTERN));                environmentResources.setCurrentProtectionArea(environmentResourcesVo.getCurrentProtectionArea());
            environmentResources.setCoreRegionArea(environmentResourcesVo.getCoreRegionArea());
            environmentResources.setExperimentRegionArea(environmentResourcesVo.getExperimentRegionArea());
            environmentResources.setCreateTime(new Date());
            environmentResources.setIsDel(DeleteEnum.DEL_FLAG_N.getKey());
            int result1 = this.environmentResourcesMapper.updateById(environmentResources);

            managerOrg.setManagerOrgName(managerOrgVo.getManagerOrgName());
            managerOrg.setOrgFormationDept(managerOrgVo.getOrgFormationDept());
            managerOrg.setDeptApproveNumber(managerOrgVo.getDeptApproveNumber());
            managerOrg.setManagerOrgLevel(managerOrgVo.getManagerOrgLevel());
            managerOrg.setManagerOrgQuality(managerOrgVo.getManagerOrgQuality());
            managerOrg.setJurisdictionRelation(managerOrgVo.getJurisdictionRelation());
            managerOrg.setManagerOrgStaffFormation(managerOrgVo.getManagerOrgStaffFormation());
            managerOrg.setManagerStaffAmout(managerOrgVo.getManagerStaffAmout());
            managerOrg.setTechnologyStaffAmout(managerOrgVo.getTechnologyStaffAmout());
            managerOrg.setIsFunds(managerOrgVo.getIsFunds());
            managerOrg.setFundsSource(managerOrgVo.getFundsSource());
            managerOrg.setFixedFunds(managerOrgVo.getFixedFunds());
            managerOrg.setManagerOrgAddr(managerOrgVo.getManagerOrgAddr());
            managerOrg.setManagerOrgPostCode(managerOrgVo.getManagerOrgPostCode());
            managerOrg.setManagerOrgPhone(managerOrgVo.getManagerOrgPhone());
            managerOrg.setManagerOrgPortraiture(managerOrgVo.getManagerOrgPortraiture());
            managerOrg.setEmail(managerOrgVo.getEmail());
            managerOrg.setCreateTime(new Date());
            managerOrg.setIsDel(DeleteEnum.DEL_FLAG_N.getKey());
            int result2 = this.managerOrgMapper.updateById(managerOrg);

            if(result1!=0 && result2!=0) {
                String[] regionCodeArr = aquaticResourcesProtectionInfoVo.getRegionCode();
                String regionCode = StringUtils.join(regionCodeArr, ",");
                aquaticResourcesProtectionInfo.setRegionCode(regionCode);
                aquaticResourcesProtectionInfo.setName(aquaticResourcesProtectionInfoVo.getName());
                aquaticResourcesProtectionInfo.setDepartmentManager(aquaticResourcesProtectionInfoVo.getDepartmentManager());
                aquaticResourcesProtectionInfo.setApproveTime(DateUtils.stringToDate(aquaticResourcesProtectionInfoVo.getApproveTime(), DateUtils.DATE_PATTERN));
                aquaticResourcesProtectionInfo.setApproveDocNumber(aquaticResourcesProtectionInfoVo.getApproveDocNumber());
                aquaticResourcesProtectionInfo.setIsAdjust(aquaticResourcesProtectionInfoVo.getIsAdjust());
                aquaticResourcesProtectionInfo.setAdjustTime(DateUtils.stringToDate(aquaticResourcesProtectionInfoVo.getAdjustTime(), DateUtils.DATE_PATTERN));
                aquaticResourcesProtectionInfo.setCreateTime(new Date());
                aquaticResourcesProtectionInfo.setIsDel(DeleteEnum.DEL_FLAG_N.getKey());
                aquaticResourcesProtectionInfo.setEnvironmentResourcesId(environmentResources.getId());
                aquaticResourcesProtectionInfo.setManagerOrgId(managerOrg.getId());
                aquaticResourcesProtectionInfo.setStatus(CheckEnum.IS_DSP_WJZJ.getVal());
                aquaticResourcesProtectionInfo.setReportTime(Calendar.getInstance().getTime());
                aquaticResourcesProtectionInfo.setFileIds(aquaticResourcesProtectionInfoVo.getFileIds());
                aquaticResourcesProtectionInfo.setSubmitTime(aquaticResourcesProtectionInfoVo.getSubmitTime());
                //获取当前用户所在的机构id
                Result<SysOrganizationForm> orgInfoById = sysRegionApi.getOrgInfoById(user != null ? user.getOrganizationId() : null);
                String organizationName = orgInfoById.getData().getOrganizationName();
                aquaticResourcesProtectionInfo.setSubmitUnit(organizationName);

                   int result4 = this.aquaticResourcesProtectionInfoMapper.updateById(aquaticResourcesProtectionInfo);
                    SysFileManageForm sysFileManageForm = new SysFileManageForm();
                    sysFileManageForm.setSystemId(FyrpaEnum.fyrpa_id.getCode());
                    sysFileManageForm.setIds(aquaticResourcesProtectionInfoVo.getFileIds());
                    //调用平台激活文件接口
                    Result<List<SysFileVo>> listResult = sysFileApi.activationFile(sysFileManageForm);

                    if (listResult.getData() == null || listResult.getData().size() == 0) {
                        throw new SofnException("激活文件失败");
                    }

                    //文件信息存入数据库
                    FileInfo fileInfo = new FileInfo();
                    fileInfo.setFileIds(sysFileManageForm.getIds());
                    fileInfo.setSystemId(sysFileManageForm.getSystemId());
                    fileInfo.setAquaticResourcesProtectionInfoId(aquaticResourcesProtectionInfo.getId());
                    int result3 = this.fileinfoMapper.insert(fileInfo);
                    aquaticResourcesProtectionInfo.setStatus(CheckEnum.IS_WSB.getVal());
                    int result5 = this.aquaticResourcesProtectionInfoMapper.updateById(aquaticResourcesProtectionInfo);
                    if (result3 != 0 && result4 != 0 && result5 != 0) {
                        return Result.ok("上报成功");
                    }
                }
            }

        return null;
    }

    @Transactional
    @Override
    public Result updateAndSbResourcesProtection(String id, AquaticResourcesProtectionInfoVo aquaticResourcesProtectionInfoVo, EnvironmentResourcesVo environmentResourcesVo, ManagerOrgVo managerOrgVo,User user) {
        QueryWrapper<AquaticResourcesProtectionInfo>wrapper1 = new QueryWrapper<>();
        wrapper1.eq("id",id);
        wrapper1.eq("is_del",DeleteEnum.DEL_FLAG_N.getKey());
        AquaticResourcesProtectionInfo aquaticResourcesProtectionInfo = this.aquaticResourcesProtectionInfoMapper.selectOne(wrapper1);

        if(aquaticResourcesProtectionInfo!=null ) {
            QueryWrapper<EnvironmentResources> wrapper2 = new QueryWrapper<>();
            wrapper2.eq("id", aquaticResourcesProtectionInfo.getEnvironmentResourcesId());
            wrapper2.eq("is_del", DeleteEnum.DEL_FLAG_N.getKey());
            EnvironmentResources environmentResources = this.environmentResourcesMapper.selectOne(wrapper2);

            QueryWrapper<ManagerOrg> wrapper3 = new QueryWrapper<>();
            wrapper3.eq("id", aquaticResourcesProtectionInfo.getManagerOrgId());
            wrapper3.eq("is_del", DeleteEnum.DEL_FLAG_N.getKey());
            ManagerOrg managerOrg = this.managerOrgMapper.selectOne(wrapper3);

            environmentResources.setBasinOrSeaArea(environmentResourcesVo.getBasinOrSeaArea());
            environmentResources.setRiverOrMaritimeSpace(environmentResourcesVo.getRiverOrMaritimeSpace());
            environmentResources.setMajorProtectObject(environmentResourcesVo.getMajorProtectObject());
            environmentResources.setLongitude(environmentResourcesVo.getLongitudeRange()+environmentResourcesVo.getLongitudeStart()+"-"+environmentResourcesVo.getLongitudeEnd());
            environmentResources.setLatitude(environmentResourcesVo.getLatitudeRange()+environmentResourcesVo.getLatitudeStart()+"-"+environmentResourcesVo.getLatitudeEnd());
            environmentResources.setStartTime(DateUtils.stringToDate(environmentResourcesVo.getStartTime(),DateUtils.DATE_PATTERN));
            environmentResources.setEndTime(DateUtils.stringToDate(environmentResourcesVo.getEndTime(),DateUtils.DATE_PATTERN));                environmentResources.setCurrentProtectionArea(environmentResourcesVo.getCurrentProtectionArea());
            environmentResources.setCoreRegionArea(environmentResourcesVo.getCoreRegionArea());
            environmentResources.setExperimentRegionArea(environmentResourcesVo.getExperimentRegionArea());
            environmentResources.setCreateTime(new Date());
            environmentResources.setIsDel(DeleteEnum.DEL_FLAG_N.getKey());
            int result1 = this.environmentResourcesMapper.updateById(environmentResources);

            managerOrg.setManagerOrgName(managerOrgVo.getManagerOrgName());
            managerOrg.setOrgFormationDept(managerOrgVo.getOrgFormationDept());
            managerOrg.setDeptApproveNumber(managerOrgVo.getDeptApproveNumber());
            managerOrg.setManagerOrgLevel(managerOrgVo.getManagerOrgLevel());
            managerOrg.setManagerOrgQuality(managerOrgVo.getManagerOrgQuality());
            managerOrg.setJurisdictionRelation(managerOrgVo.getJurisdictionRelation());
            managerOrg.setManagerOrgStaffFormation(managerOrgVo.getManagerOrgStaffFormation());
            managerOrg.setManagerStaffAmout(managerOrgVo.getManagerStaffAmout());
            managerOrg.setTechnologyStaffAmout(managerOrgVo.getTechnologyStaffAmout());
            managerOrg.setIsFunds(managerOrgVo.getIsFunds());
            managerOrg.setFundsSource(managerOrgVo.getFundsSource());
            managerOrg.setFixedFunds(managerOrgVo.getFixedFunds());
            managerOrg.setManagerOrgAddr(managerOrgVo.getManagerOrgAddr());
            managerOrg.setManagerOrgPostCode(managerOrgVo.getManagerOrgPostCode());
            managerOrg.setManagerOrgPhone(managerOrgVo.getManagerOrgPhone());
            managerOrg.setManagerOrgPortraiture(managerOrgVo.getManagerOrgPortraiture());
            managerOrg.setEmail(managerOrgVo.getEmail());
            managerOrg.setCreateTime(new Date());
            managerOrg.setIsDel(DeleteEnum.DEL_FLAG_N.getKey());
            int result2 = this.managerOrgMapper.updateById(managerOrg);

            if(result1!=0 && result2!=0) {
                String[] regionCodeArr = aquaticResourcesProtectionInfoVo.getRegionCode();
                String regionCode = StringUtils.join(regionCodeArr, ",");
                aquaticResourcesProtectionInfo.setRegionCode(regionCode);
                aquaticResourcesProtectionInfo.setName(aquaticResourcesProtectionInfoVo.getName());
                aquaticResourcesProtectionInfo.setDepartmentManager(aquaticResourcesProtectionInfoVo.getDepartmentManager());
                aquaticResourcesProtectionInfo.setApproveTime(DateUtils.stringToDate(aquaticResourcesProtectionInfoVo.getApproveTime(), DateUtils.DATE_PATTERN));
                aquaticResourcesProtectionInfo.setApproveDocNumber(aquaticResourcesProtectionInfoVo.getApproveDocNumber());
                aquaticResourcesProtectionInfo.setIsAdjust(aquaticResourcesProtectionInfoVo.getIsAdjust());
                aquaticResourcesProtectionInfo.setAdjustTime(DateUtils.stringToDate(aquaticResourcesProtectionInfoVo.getAdjustTime(), DateUtils.DATE_PATTERN));
                aquaticResourcesProtectionInfo.setCreateTime(new Date());
                aquaticResourcesProtectionInfo.setIsDel(DeleteEnum.DEL_FLAG_N.getKey());
                aquaticResourcesProtectionInfo.setEnvironmentResourcesId(environmentResources.getId());
                aquaticResourcesProtectionInfo.setManagerOrgId(managerOrg.getId());
                aquaticResourcesProtectionInfo.setStatus(CheckEnum.IS_DSP_WJZJ.getVal());
                aquaticResourcesProtectionInfo.setReportTime(Calendar.getInstance().getTime());
                aquaticResourcesProtectionInfo.setFileIds(aquaticResourcesProtectionInfoVo.getFileIds());
                aquaticResourcesProtectionInfo.setSubmitTime(aquaticResourcesProtectionInfoVo.getSubmitTime());
                //获取当前用户所在的机构id
                Result<SysOrganizationForm> orgInfoById = sysRegionApi.getOrgInfoById(user != null ? user.getOrganizationId() : null);
                String organizationName = orgInfoById.getData().getOrganizationName();
                aquaticResourcesProtectionInfo.setSubmitUnit(organizationName);

                    int result4 = this.aquaticResourcesProtectionInfoMapper.updateById(aquaticResourcesProtectionInfo);
                    SysFileManageForm sysFileManageForm = new SysFileManageForm();
                    sysFileManageForm.setSystemId(FyrpaEnum.fyrpa_id.getCode());
                    sysFileManageForm.setIds(aquaticResourcesProtectionInfoVo.getFileIds());
                    //调用平台激活文件接口
                    Result<List<SysFileVo>> listResult = sysFileApi.activationFile(sysFileManageForm);

                    if (listResult.getData() == null || listResult.getData().size() == 0) {
                        throw new SofnException("激活文件失败");
                    }

                    //文件信息存入数据库
                    FileInfo fileInfo = new FileInfo();
                    fileInfo.setFileIds(sysFileManageForm.getIds());
                    fileInfo.setSystemId(sysFileManageForm.getSystemId());
                    fileInfo.setAquaticResourcesProtectionInfoId(aquaticResourcesProtectionInfo.getId());
                    int result3 = this.fileinfoMapper.insert(fileInfo);
                    aquaticResourcesProtectionInfo.setStatus(CheckEnum.IS_DSP_WJZJ.getVal());
                    aquaticResourcesProtectionInfo.setReportTime(Calendar.getInstance().getTime());
                    int result5 = this.aquaticResourcesProtectionInfoMapper.updateById(aquaticResourcesProtectionInfo);
                    if (result3 != 0 && result4 != 0 && result5 != 0) {
                        return Result.ok("上报成功");
                    }
                }
        }

        return null;
    }

    @Transactional
    @Override
    public Result deleteResourcesProtection(String id) {
            QueryWrapper<AquaticResourcesProtectionInfo>wrapper1 = new QueryWrapper<>();
            wrapper1.eq("id",id);
            wrapper1.eq("is_del",DeleteEnum.DEL_FLAG_N.getKey());
            AquaticResourcesProtectionInfo aquaticResourcesProtectionInfo = this.aquaticResourcesProtectionInfoMapper.selectOne(wrapper1);

        if(aquaticResourcesProtectionInfo!=null ){
            QueryWrapper<EnvironmentResources>wrapper2 = new QueryWrapper<>();
            wrapper2.eq("id",aquaticResourcesProtectionInfo.getEnvironmentResourcesId());
            wrapper2.eq("is_del",DeleteEnum.DEL_FLAG_N.getKey());
            EnvironmentResources  environmentResources = this.environmentResourcesMapper.selectOne(wrapper2);

            QueryWrapper<ManagerOrg>wrapper3 = new QueryWrapper<>();
            wrapper3.eq("id",aquaticResourcesProtectionInfo.getManagerOrgId());
            wrapper3.eq("is_del",DeleteEnum.DEL_FLAG_N.getKey());
            ManagerOrg managerOrg = this.managerOrgMapper.selectOne(wrapper3);

                UpdateWrapper<AquaticResourcesProtectionInfo>updateWrapper1 = new UpdateWrapper<>();
                aquaticResourcesProtectionInfo.setIsDel(DeleteEnum.DEL_FLAG_Y.getKey());
                updateWrapper1.eq("id",aquaticResourcesProtectionInfo.getId());
                int i1 = this.aquaticResourcesProtectionInfoMapper.update(aquaticResourcesProtectionInfo, updateWrapper1);

                UpdateWrapper<EnvironmentResources>updateWrapper2 = new UpdateWrapper<>();
                environmentResources.setIsDel(DeleteEnum.DEL_FLAG_Y.getKey());
                updateWrapper2.eq("id",environmentResources.getId());
                int i2 = this.environmentResourcesMapper.update(environmentResources, updateWrapper2);


                UpdateWrapper<ManagerOrg>updateWrapper3 = new UpdateWrapper<>();
                managerOrg.setIsDel(DeleteEnum.DEL_FLAG_Y.getKey());
                updateWrapper3.eq("id",managerOrg.getId());
                int i3 = this.managerOrgMapper.update(managerOrg, updateWrapper3);

                if(i1!=0&&i2!=0&&i3!=0){
                    return Result.ok("删除成功");
                }
            }

        return Result.error("删除失败");
    }

    @Transactional
    @Override
    public Result sbResourcesProtection(String id) {
        QueryWrapper<AquaticResourcesProtectionInfo>wrapper = new QueryWrapper<>();
        wrapper.eq("id",id);
        wrapper.eq("is_del",DeleteEnum.DEL_FLAG_N.getKey());
        wrapper.in("status",CheckEnum.IS_WSB.getVal(),CheckEnum.IS_Ybh.getVal());
        AquaticResourcesProtectionInfo aquaticResourcesProtectionInfo = this.aquaticResourcesProtectionInfoMapper.selectOne(wrapper);
        if(aquaticResourcesProtectionInfo!=null){
            UpdateWrapper<AquaticResourcesProtectionInfo>updateWrapper = new UpdateWrapper<>();
            aquaticResourcesProtectionInfo.setStatus(CheckEnum.IS_DSP_WJZJ.getVal());
            aquaticResourcesProtectionInfo.setReportTime(Calendar.getInstance().getTime());
            int i = this.aquaticResourcesProtectionInfoMapper.update(aquaticResourcesProtectionInfo, wrapper);
            if(i!=0){
               return  Result.ok("上报成功");
            }
        }

        return Result.error("上报失败");
    }

    @Override
    public Result<IPage<AquaticResourcesProtectionInfoVoList>> selectPageList(Integer pageNo, Integer pageSize, String submitTime, String keyword,User user) {
        Page<AquaticResourcesProtectionInfoVoList> page = new Page<>();
        if(pageNo!=null){
            page.setCurrent(pageNo);
        }

        if(pageSize!=null){
            page.setSize(pageSize);
        }
        List<String> userIds = this.getUserIdsByUserId(user);
        if(userIds == null || userIds.isEmpty()){
            throw new SofnException("组织机构查询异常，获取数据失败！");
        }
        IPage<AquaticResourcesProtectionInfoVoList> aquaticResourcesProtectionInfoVoListIPage = this.aquaticResourcesProtectionInfoMapper.selectListByUserIds(page, submitTime, keyword,userIds);
        return Result.ok(aquaticResourcesProtectionInfoVoListIPage);

    }

    @Override
    public Result selectDetailsById(String id) {
        QueryWrapper<AquaticResourcesProtectionInfo>wrapper1 = new QueryWrapper<>();
        wrapper1.eq("id",id);
        wrapper1.eq("is_del",DeleteEnum.DEL_FLAG_N.getKey());
        AquaticResourcesProtectionInfo aquaticResourcesProtectionInfo = this.aquaticResourcesProtectionInfoMapper.selectOne(wrapper1);


        if(aquaticResourcesProtectionInfo!=null){
            QueryWrapper<EnvironmentResources>wrapper2 = new QueryWrapper<>();
            wrapper2.eq("id",aquaticResourcesProtectionInfo.getEnvironmentResourcesId());
            wrapper2.eq("is_del",DeleteEnum.DEL_FLAG_N.getKey());
            EnvironmentResources  environmentResources = this.environmentResourcesMapper.selectOne(wrapper2);

            QueryWrapper<ManagerOrg>wrapper3 = new QueryWrapper<>();
            wrapper3.eq("id",aquaticResourcesProtectionInfo.getManagerOrgId());
            wrapper3.eq("is_del",DeleteEnum.DEL_FLAG_N.getKey());
            ManagerOrg managerOrg = this.managerOrgMapper.selectOne(wrapper3);

            ListVo listVo = new ListVo();
            AquaticResourcesProtectionInfoVo aquaticResourcesProtectionInfoVo = new AquaticResourcesProtectionInfoVo();
            aquaticResourcesProtectionInfoVo.setApproveTime(DateUtils.format(aquaticResourcesProtectionInfo.getApproveTime()));
            aquaticResourcesProtectionInfoVo.setAdjustTime(DateUtils.format(aquaticResourcesProtectionInfo.getAdjustTime()));
            aquaticResourcesProtectionInfoVo.setRegionCode(aquaticResourcesProtectionInfo.getRegionCode().split(","));

            String fileIds = aquaticResourcesProtectionInfo.getFileIds();
            Result<SysFileManageVo> sysFileApiOne = sysFileApi.getOne(fileIds);
            String fileName = sysFileApiOne.getData().getFileName();
            aquaticResourcesProtectionInfoVo.setFileName(fileName);

            EnvironmentResourcesVo environmentResourcesVo = new EnvironmentResourcesVo();
            environmentResourcesVo.setStartTime(DateUtils.format(environmentResources.getStartTime(),DateUtils.DATE_PATTERN));
            environmentResourcesVo.setEndTime(DateUtils.format(environmentResources.getEndTime(),DateUtils.DATE_PATTERN));
            String latitude = environmentResources.getLatitude();
            String longitude = environmentResources.getLongitude();
            String[] str = latitude.split("-");
            String[] str2 = longitude.split("-");

            environmentResourcesVo.setLatitudeStart(str[0].substring(2));
            environmentResourcesVo.setLatitudeEnd(str[1]);
            environmentResourcesVo.setLatitudeRange(latitude.substring(0,2));

            environmentResourcesVo.setLongitudeStart(str2[0].substring(2));
            environmentResourcesVo.setLongitudeEnd(str2[1]);
            environmentResourcesVo.setLongitudeRange(longitude.substring(0,2));

            ManagerOrgVo managerOrgVo  = new ManagerOrgVo();
            BeanUtils.copyProperties(aquaticResourcesProtectionInfo,aquaticResourcesProtectionInfoVo);
            BeanUtils.copyProperties(environmentResources,environmentResourcesVo);
            BeanUtils.copyProperties(managerOrg,managerOrgVo);
            listVo.setAquaticResourcesProtectionInfoVo(aquaticResourcesProtectionInfoVo);
            listVo.setEnvironmentResourcesVo(environmentResourcesVo);
            listVo.setManagerOrgVo(managerOrgVo);
            return Result.ok(listVo);
        }

        return null;
    }

    @Override
    public Result selectInfoAuditList(String id) {
        QueryWrapper<AquaticResourcesProtectionInfo>wrapper1 = new QueryWrapper<>();
        wrapper1.eq("id",id);
        wrapper1.eq("is_del",DeleteEnum.DEL_FLAG_N.getKey());
        AquaticResourcesProtectionInfo aquaticResourcesProtectionInfo = this.aquaticResourcesProtectionInfoMapper.selectOne(wrapper1);
        if(aquaticResourcesProtectionInfo!=null){
            List<InformationAudit> list = this.informationAuditMapper.selectListData(aquaticResourcesProtectionInfo.getId());
            return Result.ok(list);
        }
        return null;
    }

    /**
     * 根据用户Id获取该用户所属机构下所有用户Id
     * @return
     */
    private List<String> getUserIdsByUserId(User user){
        if(user == null || StringUtils.isEmpty(user.getId())){
            return null;
        }
        if(user.getOrganizationId().isEmpty()){
            throw new SofnException("获取信息失败，用户信息异常！组织id为空");
        }
        Result<List<SysUserForm>> userListByOrgId = sysRegionApi.getUserListByOrgId(user.getOrganizationId(),"N");
        if(userListByOrgId == null || userListByOrgId.getCode() != 200 || userListByOrgId.getData() == null){
            throw new SofnException("获取组织机构信息异常，查询失败！");
        }
        List<SysUserForm> data = userListByOrgId.getData();
        List<String> userIds = new ArrayList<>();
        data.forEach(x->{
            userIds.add(x.getId());
        });
        return userIds;
    }
}
