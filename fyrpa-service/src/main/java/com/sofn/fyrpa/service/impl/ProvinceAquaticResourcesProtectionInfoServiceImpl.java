package com.sofn.fyrpa.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sofn.common.model.Result;
import com.sofn.common.model.User;
import com.sofn.common.utils.DateUtils;
import com.sofn.fyrpa.enums.CheckEnum;
import com.sofn.fyrpa.enums.DeleteEnum;
import com.sofn.fyrpa.mapper.AquaticResourcesProtectionInfoMapper;
import com.sofn.fyrpa.mapper.EnvironmentResourcesMapper;
import com.sofn.fyrpa.mapper.InformationAuditMapper;
import com.sofn.fyrpa.mapper.ManagerOrgMapper;
import com.sofn.fyrpa.model.AquaticResourcesProtectionInfo;
import com.sofn.fyrpa.model.EnvironmentResources;
import com.sofn.fyrpa.model.InformationAudit;
import com.sofn.fyrpa.model.ManagerOrg;
import com.sofn.fyrpa.service.ProvinceAquaticResourcesProtectionInfoService;
import com.sofn.fyrpa.sysapi.SysFileApi;
import com.sofn.fyrpa.sysapi.SysRegionApi;
import com.sofn.fyrpa.sysapi.bean.SysFileManageVo;
import com.sofn.fyrpa.sysapi.bean.SysOrganizationForm;
import com.sofn.fyrpa.sysapi.bean.SysRegionInfoVo;
import com.sofn.fyrpa.vo.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

@Service
public class ProvinceAquaticResourcesProtectionInfoServiceImpl implements ProvinceAquaticResourcesProtectionInfoService{
    @Autowired
    private AquaticResourcesProtectionInfoMapper aquaticResourcesProtectionInfoMapper;

    @Autowired
    private InformationAuditMapper informationAuditMapper;

    @Autowired
    private EnvironmentResourcesMapper environmentResourcesMapper;

    @Autowired
    private ManagerOrgMapper managerOrgMapper;

    @Autowired(required = false)
    private SysFileApi sysFileApi;

    @Autowired(required = false)
    private SysRegionApi sysRegionApi;

    @Transactional
    @Override
    public Result isNotPass(InformationAuditVo informationAuditVo,String id) {
        QueryWrapper<AquaticResourcesProtectionInfo> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("is_del", DeleteEnum.DEL_FLAG_N.getKey());
//        wrapper1.in("status", CheckEnum.IS_DSP_WJZJ.getVal(),CheckEnum.IS_DSP_YGZJ.getVal());
        wrapper1.eq("status", CheckEnum.IS_DSP_WJZJ.getVal());
        wrapper1.eq("id",id);
        AquaticResourcesProtectionInfo aquaticResourcesProtectionInfo = aquaticResourcesProtectionInfoMapper.selectOne(wrapper1);
        if(aquaticResourcesProtectionInfo!=null){
            InformationAudit informationAudit = new InformationAudit();
            informationAudit.setAuditMind(informationAuditVo.getAuditMind());
            informationAudit.setChecker(informationAuditVo.getChecker());
            informationAudit.setAuditUnit(informationAuditVo.getAuditUnit());
            informationAudit.setAuditTime(new Date());
            informationAudit.setCreateTime(new Date());
            informationAudit.setIsDel(DeleteEnum.DEL_FLAG_N.getKey());
            informationAudit.setProtectionInfoId(id);

            aquaticResourcesProtectionInfo.setStatus(CheckEnum.IS_Ybh.getVal());
            int i1 = this.aquaticResourcesProtectionInfoMapper.updateById(aquaticResourcesProtectionInfo);
            int i2 = this.informationAuditMapper.insert(informationAudit);
            if(i1!=0 && i2!=0){
                 return Result.ok("驳回成功");
            }
        }

        return Result.error("驳回失败");
    }

    @Override
    public Result isPass(InformationAuditVo informationAuditVo, String id) {
        QueryWrapper<AquaticResourcesProtectionInfo> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("is_del", DeleteEnum.DEL_FLAG_N.getKey());
//        wrapper1.in("status", CheckEnum.IS_DSP_WJZJ.getVal(),CheckEnum.IS_DSP_YGZJ.getVal());
        wrapper1.eq("status", CheckEnum.IS_DSP_WJZJ.getVal());
        wrapper1.eq("id",id);
        AquaticResourcesProtectionInfo aquaticResourcesProtectionInfo = aquaticResourcesProtectionInfoMapper.selectOne(wrapper1);
        if(aquaticResourcesProtectionInfo!=null){
            InformationAudit informationAudit = new InformationAudit();
            informationAudit.setAuditMind(informationAuditVo.getAuditMind());
            informationAudit.setChecker(informationAuditVo.getChecker());
            informationAudit.setAuditUnit(informationAuditVo.getAuditUnit());
            informationAudit.setAuditTime(new Date());
            informationAudit.setCreateTime(new Date());
            informationAudit.setIsDel(DeleteEnum.DEL_FLAG_N.getKey());
            informationAudit.setProtectionInfoId(id);
//            if(aquaticResourcesProtectionInfo.getStatus().equals(CheckEnum.IS_DSP_WJZJ.getVal())){
//                aquaticResourcesProtectionInfo.setStatus(CheckEnum.IS_ZJSP.getVal());
//                int i1 = this.aquaticResourcesProtectionInfoMapper.updateById(aquaticResourcesProtectionInfo);
//                int i2 = this.informationAuditMapper.insert(informationAudit);
//                if(i1!=0 && i2!=0){
//                    return Result.ok("省级审核成功");
//                }
//            }
//
//            if(aquaticResourcesProtectionInfo.getStatus().equals(CheckEnum.IS_DSP_YGZJ.getVal())){
//                aquaticResourcesProtectionInfo.setStatus(CheckEnum.IS_YTG.getVal());
//                int i1 = this.aquaticResourcesProtectionInfoMapper.updateById(aquaticResourcesProtectionInfo);
//                int i2 = this.informationAuditMapper.insert(informationAudit);
//                if(i1!=0 && i2!=0){
//                    return Result.ok("省级审核成功");
//                }
//            }

            aquaticResourcesProtectionInfo.setStatus(CheckEnum.IS_ZJSP.getVal());
            Assert.isTrue(this.aquaticResourcesProtectionInfoMapper.updateById(aquaticResourcesProtectionInfo) == 1, () -> "更新审核状态失败");
            Assert.isTrue(this.informationAuditMapper.insert(informationAudit) == 1, () -> "添加审核信息失败");
            return Result.ok("省级审核成功");
        }
        return Result.error("省级审核失败");
    }

    @Override
    public Result<IPage<AquaticResourcesProtectionInfoVoList>> selectPageList(Integer pageNo, Integer pageSize, String submitTime, String keyword, String[] regionCodeArr, User user) {
        Page<AquaticResourcesProtectionInfoVoList> page = new Page<>();
        if(pageNo!=null){
            page.setCurrent(pageNo);
        }

        if(pageSize!=null){
            page.setSize(pageSize);
        }


        //获取当前用户所在的机构id
        Result<SysOrganizationForm> orgInfoById = sysRegionApi.getOrgInfoById(user != null ? user.getOrganizationId() : null);
        //获取省市区列表信息
        SysRegionInfoVo sysRegionInfoVo = orgInfoById.getData().getRegionInfoVo();

        String regionCode = StringUtils.join(regionCodeArr, ",");
        IPage<AquaticResourcesProtectionInfoVoList> aquaticResourcesProtectionInfoVoListIPage = this.aquaticResourcesProtectionInfoMapper.selectList2(page, submitTime, keyword,regionCode,sysRegionInfoVo.getProvince());


        return Result.ok(aquaticResourcesProtectionInfoVoListIPage);
    }

    @Override
    public Result<ListVo> selectDetailsById(String id) {
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

            ManagerOrgVo managerOrgVo  = new ManagerOrgVo();
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

    @Override
    public Result<IPage<AquaticResourcesProtectionInfoVoList>> selectListIsPass(Integer pageNo, Integer pageSize, String submitTime, String keyword, String[] regionCodeArr,User user) {
        Page<AquaticResourcesProtectionInfoVoList> page = new Page<>();
        if(pageNo!=null){
            page.setCurrent(pageNo);
        }

        if(pageSize!=null){
            page.setSize(pageSize);
        }

        String regionCode = StringUtils.join(regionCodeArr, ",");
        //获取当前用户所在的机构id
        Result<SysOrganizationForm> orgInfoById = sysRegionApi.getOrgInfoById(user != null ? user.getOrganizationId() : null);
        //获取省市区列表信息
        SysRegionInfoVo sysRegionInfoVo = orgInfoById.getData().getRegionInfoVo();

        IPage<AquaticResourcesProtectionInfoVoList> aquaticResourcesProtectionInfoVoListIPage = this.aquaticResourcesProtectionInfoMapper.selectListIsPass(page, submitTime, keyword,regionCode,sysRegionInfoVo.getProvinceName());
        return Result.ok(aquaticResourcesProtectionInfoVoListIPage);
    }

    @Override
    public Result<String> report(String id, User user) {
        LambdaUpdateWrapper<AquaticResourcesProtectionInfo> wrapper1 = new LambdaUpdateWrapper<AquaticResourcesProtectionInfo>()
                .eq(AquaticResourcesProtectionInfo::getId, id)
                .eq(AquaticResourcesProtectionInfo::getIsDel, DeleteEnum.DEL_FLAG_N.getKey())
                .eq(AquaticResourcesProtectionInfo::getStatus, CheckEnum.IS_DSB.getVal())
                .set(AquaticResourcesProtectionInfo::getStatus, CheckEnum.IS_YTG.getVal());
        return this.aquaticResourcesProtectionInfoMapper.update(null, wrapper1) == 1 ? Result.ok("上报成功") : Result.error("上报失败");
    }
}
