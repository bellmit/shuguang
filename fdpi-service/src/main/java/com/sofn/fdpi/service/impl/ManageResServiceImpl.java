package com.sofn.fdpi.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.sofn.common.exception.SofnException;
import com.sofn.common.model.Result;
import com.sofn.common.service.BaseService;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.UserUtil;
import com.sofn.fdpi.enums.DefaultAdviceEnum;
import com.sofn.fdpi.enums.ForfeiProcessEnum;
import com.sofn.fdpi.enums.OrganizationLevelEnum;
import com.sofn.fdpi.enums.SignboardApplyProcessEnum;
import com.sofn.fdpi.mapper.ManageResMapper;
import com.sofn.fdpi.model.ManageRes;
import com.sofn.fdpi.service.ManageResService;
import com.sofn.fdpi.service.ResProcessService;
import com.sofn.fdpi.sysapi.SysRegionApi;
import com.sofn.fdpi.util.RedisUserUtil;
import com.sofn.fdpi.util.SysOwnOrgUtil;
import com.sofn.fdpi.vo.ManageResInfoVo;
import com.sofn.fdpi.vo.ResProcessFrom;
import com.sofn.fdpi.vo.SysOrgAndRegionVo;
import com.sofn.fdpi.vo.SysRegionInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Auther: Bay
 * @Date: 2020/1/3 16:15
 */
@Slf4j
@Service
public class ManageResServiceImpl extends BaseService<ManageResMapper, ManageRes> implements ManageResService {
    @Autowired
    ManageResMapper manageResMapper;
    @Autowired
    ResProcessService resPService;
    @Autowired
    SysRegionApi sysRegionApi;

    /**
     * 获取收容信息(分页)
     *
     * @param map
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public PageUtils<ManageRes> getManageResList(Map<String, Object> map, int pageNo, int pageSize) {
        this.qParams(map);
        PageHelper.offsetPage(pageNo, pageSize);
        List<ManageRes> manageResList = manageResMapper.getManageResList(map);
        PageInfo<ManageRes> manageResPageInfo = new PageInfo<>(manageResList);
        PageUtils pageUtils = PageUtils.getPageUtils(manageResPageInfo);
        return pageUtils;
    }

    /**
     * 新增收容信息
     *
     * @param manageResInfoVo
     * @return
     */
    @Override
    @Transactional
    public String saveManageRes(ManageResInfoVo manageResInfoVo) {
        RedisUserUtil.validReSubmit("fdpi_manage_save");
        String organizationId = UserUtil.getLoginUserOrganizationId();
        Result<SysRegionInfoVo> sysRegionInfoByOrgId = sysRegionApi.getSysRegionInfoByOrgId(organizationId);
        String status = String.valueOf(manageResInfoVo.getStatus());
        if (!ForfeiProcessEnum.UN_REPORT.getKey().equals(status) && !ForfeiProcessEnum.FINAL_AUDIT.getKey().equals(status)) {
            throw new SofnException("新增收容 流程状态只能是保存和备案");
        }


        ManageRes manageRes = manageResInfoVo.convertToModel(ManageRes.class);

        manageRes.preInsert();
        manageRes.setOrgId(UserUtil.getLoginUserOrganizationId());
        manageRes.setProvince(sysRegionInfoByOrgId.getData().getProvince());
        manageRes.setCity(sysRegionInfoByOrgId.getData().getCity());
        manageRes.setArea(sysRegionInfoByOrgId.getData().getArea());
        //如果上报添加流程

        int i = manageResMapper.insert(manageRes);
        if (i == 1) {
            return "1";
        }
        return "该收容信息已存在";

    }

    @Override
    public ManageRes getManageResInfo(String id) {
        return this.getById(id);
    }

    /**
     * 更新状态
     *
     * @param id
     * @param status
     */
    @Override
    public void updateStatus(String id, int status) {
        ManageRes manageResInfo = manageResMapper.selectById(id);
        manageResInfo.preUpdate();
        manageResInfo.setStatus(status);
        this.updateById(manageResInfo);
    }

    @Override
    public String deleteManageResInfo(String id) {
        ManageRes manageResInfo = this.getManageResInfo(id);
        if (manageResInfo == null) {
            return "该收容信息不存在";
        }
        HashMap<String, Object> map = Maps.newHashMap();
        map.put("id", id);
        map.put("updateTime", new Date());
        map.put("updateUserId", UserUtil.getLoginUserId());
        int i = manageResMapper.deleteManageResInfo(map);
        if (i == 1) {
            return "1";
        }
        throw new SofnException("删除收容信息失败");
    }

    @Override
    @Transactional
    public String updateManageResInfo(ManageResInfoVo manageResInfoVo) {
        RedisUserUtil.validReSubmit("fdpi_manage_update");
        String status = String.valueOf(manageResInfoVo.getStatus());
        if (!SignboardApplyProcessEnum.UN_REPORT.getKey().equals(status)) {
            throw new SofnException("修改收容救护,流程状态只能是保存");
        }
        ManageRes manageRes = this.getManageResInfo(manageResInfoVo.getId());
        if (manageRes == null) {
            return "该收容信息不存在";
        }
        ManageRes manageResToModel = manageResInfoVo.convertToModel(ManageRes.class);
        manageResToModel.preUpdate();

        boolean b = this.updateById(manageResToModel);
        if (b) {
            return "1";
        }
        throw new SofnException(" 修改收容信息失败");
    }

    @Override
    @Transactional
    public void insertManageResProcess(ResProcessFrom from) {
        // 新增流程
        resPService.insertResProcess(from);
        int status = from.getStatus();
        if (!DefaultAdviceEnum.REPORT_DEFAULT_ADVICE.getCode().equals(String.valueOf(status))) {
            //更新状态
            this.updateStatus(from.getResId(), status);
        }
    }

    @Override
    public void pass(ResProcessFrom resProcessFrom) {

        Result<SysOrgAndRegionVo> result = SysOwnOrgUtil.getSysOrgInfoForApproveAndQuery();
        String orgLevel = result.getData().getSysOrgLevelId();
        if (orgLevel.equals(OrganizationLevelEnum.PROVINCE_ORG_LEVEL.getId()) || orgLevel.equals(OrganizationLevelEnum.DIRECT_AND_PROVINCE_ORG_LEVEL.getId())) {
            resProcessFrom.setStatus(4);
        } else {
            resProcessFrom.setStatus(6);
        }
        resPService.insertResProcess(resProcessFrom);
        int status = resProcessFrom.getStatus();
        if (!DefaultAdviceEnum.REPORT_DEFAULT_ADVICE.getCode().equals(String.valueOf(status))) {
            //更新状态
            this.updateStatus(resProcessFrom.getResId(), status);
        }
    }

    @Override
    public void back(ResProcessFrom resProcessFrom) {
        Result<SysOrgAndRegionVo> result = SysOwnOrgUtil.getSysOrgInfoForApproveAndQuery();
        String orgLevel = result.getData().getSysOrgLevelId();
        if (orgLevel.equals(OrganizationLevelEnum.PROVINCE_ORG_LEVEL.getId()) || orgLevel.equals(OrganizationLevelEnum.DIRECT_AND_PROVINCE_ORG_LEVEL.getId())) {
            resProcessFrom.setStatus(3);
        } else {
            resProcessFrom.setStatus(5);
        }
        int status = resProcessFrom.getStatus();
        resPService.insertResProcess(resProcessFrom);

        if (!DefaultAdviceEnum.REPORT_DEFAULT_ADVICE.getCode().equals(String.valueOf(status))) {
            //更新状态
            this.updateStatus(resProcessFrom.getResId(), status);
        }
    }

    private void qParams(Map<String, Object> params) {
        //获取用户级别
        Result<SysOrgAndRegionVo> result = null;
        try {
            result = SysOwnOrgUtil.getSysOrgInfoForApproveAndQuery();
        } catch (Exception e) {
            throw new SofnException("获取用户的级别失败");
        }
        String orgLevel = result.getData().getSysOrgLevelId();
        //获取用户区域信息
        String organizationId = UserUtil.getLoginUserOrganizationId();
        Result<SysRegionInfoVo> sysRegionInfoByOrgId = sysRegionApi.getSysRegionInfoByOrgId(organizationId);
        //省级
        String orgId = UserUtil.getLoginUserOrganizationId();
        if (orgLevel.equals(OrganizationLevelEnum.PROVINCE_ORG_LEVEL.getId()) || orgLevel.equals(OrganizationLevelEnum.DIRECT_AND_PROVINCE_ORG_LEVEL.getId())) {
            String province = sysRegionInfoByOrgId.getData().getProvince();

            //                只是省级直属或省级
            params.put("orgId", orgId);
            params.put("provinceOrg", province);
//            }

        } else if (orgLevel.equals(OrganizationLevelEnum.DISTRICT_ORG_LEVEL.getId()) || orgLevel.equals(OrganizationLevelEnum.DIRECT_ORG_LEVEL.getId())) {
            String area = sysRegionInfoByOrgId.getData().getArea();

            //  区级直属或区级
            params.put("orgId", orgId);
            params.put("areaOrg", area);
//            }

        } else if (orgLevel.equals(OrganizationLevelEnum.CITY_ORG_LEVEL.getId()) || orgLevel.equals(OrganizationLevelEnum.DIRECT_AND_CITY_ORG_LEVEL.getId())) {

            String city = sysRegionInfoByOrgId.getData().getCity();

//                市级直属或市级
            params.put("cityOrg", city);
            params.put("orgId", orgId);
//            }
        } else {
//            部级
            params.put("Min", "1");
        }


    }

    //记录上报信息
    private void recReport(String rid) {
        ResProcessFrom from = new ResProcessFrom();
        //收容id
        from.setResId(rid);
        from.setStatus(Integer.parseInt(DefaultAdviceEnum.REPORT_DEFAULT_ADVICE.getCode()));
        this.insertManageResProcess(from);
    }

}
