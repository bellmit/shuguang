package com.sofn.fdpi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.sofn.common.exception.SofnException;
import com.sofn.common.model.Result;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.UserUtil;
import com.sofn.fdpi.constants.Constants;
import com.sofn.fdpi.enums.DefaultAdviceEnum;
import com.sofn.fdpi.enums.ForfeiProcessEnum;
import com.sofn.fdpi.enums.OrganizationLevelEnum;
import com.sofn.fdpi.enums.SignboardApplyProcessEnum;
import com.sofn.fdpi.mapper.ForfeiMapper;
import com.sofn.fdpi.model.FileManage;
import com.sofn.fdpi.model.Forfei;
import com.sofn.fdpi.service.FileManageService;
import com.sofn.fdpi.service.ForfeiProcessService;
import com.sofn.fdpi.service.ForfeiService;
import com.sofn.fdpi.sysapi.SysRegionApi;
import com.sofn.fdpi.sysapi.bean.SysFileManageForm;
import com.sofn.fdpi.util.RedisUserUtil;
import com.sofn.fdpi.util.SysOwnOrgUtil;
import com.sofn.fdpi.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @Description:
 * @Auther: Bay
 * @Date: 2020/1/2 16:41
 */
@Slf4j
@Service
@Transactional
public class ForfeiServiceImpl extends ServiceImpl<ForfeiMapper, Forfei> implements ForfeiService {
    @Autowired
    ForfeiMapper forfeiMapper;
    @Autowired
    SysRegionApi sysRegionApi;
//    @Autowired
//    ForfeiProcessService fProcessService;
    @Autowired
    FileManageService fileManageService;

    /**
     * 获取罚没信息(分页)
     *
     * @param map
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public PageUtils<Forfei> getForfeiListByPage(Map<String, Object> map, int pageNo, int pageSize) {

        this.qParams(map);
        PageHelper.offsetPage(pageNo, pageSize);
        List<Forfei> forFeiList = forfeiMapper.getForFeiList(map);
        for (Forfei f :
                forFeiList) {
            List<FileManage> list = fileManageService.list(f.getId());
            f.setFiles(list);
        }
        PageInfo<Forfei> forfeiPageInfo = new PageInfo<>(forFeiList);
        PageUtils pageUtils = PageUtils.getPageUtils(forfeiPageInfo);
        return pageUtils;
    }

    /**
     * 新增罚没信息
     *
     * @param forfeiInfoVo
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int saveForfeiInfo(ForfeiInfoVo forfeiInfoVo) {
        RedisUserUtil.validReSubmit("fdpi_forfei_save");
        int i = 0;
        String organizationId = UserUtil.getLoginUserOrganizationId();
//        获取当前组织机构的 省市区信息
        Result<SysRegionInfoVo> sysRegionInfoByOrgId = sysRegionApi.getSysRegionInfoByOrgId(organizationId);
        String province = sysRegionInfoByOrgId.getData().getProvince();
        String area = sysRegionInfoByOrgId.getData().getArea();
        String city = sysRegionInfoByOrgId.getData().getCity();
//      获取当前记录的状态
        String status = String.valueOf(forfeiInfoVo.getStatus());
        if (!ForfeiProcessEnum.UN_REPORT.getKey().equals(status) && !ForfeiProcessEnum.FINAL_AUDIT.getKey().equals(status)) {
            throw new SofnException("新增罚没,流程状态只能是保存和备案");
        }
        Forfei forfei = forfeiInfoVo.convertToModel(Forfei.class);
        forfei.preInsert();
        forfeiInfoVo.setId(forfei.getId());
        forfei.setArea(area);
        forfei.setCity(city);
        forfei.setProvince(province);
        forfei.setOrgId(UserUtil.getLoginUserOrganizationId());
        if (forfeiInfoVo.getFiles() != null && forfeiInfoVo.getFiles().size() > 0) {
//                激活文件
            this.updateFile(forfeiInfoVo);
        }
        i = forfeiMapper.insert(forfei);

        return i;
    }

    @Override
    public String deleteForFeiInfo(String id) {
        Map map = Maps.newHashMap();
        map.put("id", id);
        map.put("updateTime", new Date());
        map.put("updateUserId", UserUtil.getLoginUserId());
        int i = forfeiMapper.deleteForFeiInfo(map);
        return "";
    }

    @Override
    public String updateForFeiInfo(ForfeiInfoVo forfeiInfoVo) {
        RedisUserUtil.validReSubmit("fdpi_forfei_update");
        Forfei forfei = this.getById(forfeiInfoVo.getId());

        if (!ForfeiProcessEnum.UN_REPORT.getKey().equals(forfei.getStatus() + "")) {
            throw new SofnException("修改罚没,流程状态只能是未上报");
        }

        if (forfei == null) {
            return "该罚没信息不存在";
        }
        Forfei forfeiVoToModel = forfeiInfoVo.convertToModel(Forfei.class);
        forfeiVoToModel.preUpdate();
        this.updateFile(forfeiInfoVo);
        int isSuccess = forfeiMapper.updateById(forfeiVoToModel);
        if (isSuccess == 1) {
            return "1";
        }
        throw new SofnException("修改罚没信息失败");
    }

    @Override
    public Forfei getForfeiInfo(String id) {
        Forfei byId = getById(id);
        List<FileManage> list = fileManageService.list(id);
        byId.setFiles(list);
        return byId;
    }

    /**
     * 更新状态
     *
     * @param id
     * @param status
     */
    @Override
    public void updateStatus(String id, int status) {
        Forfei ffei = this.getForfeiInfo(id);
        ffei.preUpdate();
        ffei.setStatus(status);
        this.updateById(ffei);
    }

    @Override
    @Transactional
    public void insertForfeiProcess(ForfeiProcessFrom from) {

//        fProcessService.insertForfeiProcess(from);
        int status = from.getStatus();
        if (!DefaultAdviceEnum.REPORT_DEFAULT_ADVICE.getCode().equals(String.valueOf(status))) {
            this.updateStatus(from.getFfId(), status);
        }
    }

//    @Override
//    public void pass(ForfeiProcessFrom from) {
//        Result<SysOrgAndRegionVo> result = SysOwnOrgUtil.getSysOrgInfoForApproveAndQuery();
//        String orgLevel = result.getData().getSysOrgLevelId();
//        if (orgLevel.equals(OrganizationLevelEnum.PROVINCE_ORG_LEVEL.getId()) || orgLevel.equals(OrganizationLevelEnum.DIRECT_AND_PROVINCE_ORG_LEVEL.getId())) {
//            from.setStatus(4);
//        } else {
//            from.setStatus(6);
//        }
//        int status = from.getStatus();
////        fProcessService.insertForfeiProcess(from);
//        if (!DefaultAdviceEnum.REPORT_DEFAULT_ADVICE.getCode().equals(String.valueOf(status))) {
//            this.updateStatus(from.getFfId(), status);
//        }
//
//    }
//
//    @Override
//    public void back(ForfeiProcessFrom from) {
//        Result<SysOrgAndRegionVo> result = SysOwnOrgUtil.getSysOrgInfoForApproveAndQuery();
//        String orgLevel = result.getData().getSysOrgLevelId();
//        if (orgLevel.equals(OrganizationLevelEnum.PROVINCE_ORG_LEVEL.getId()) || orgLevel.equals(OrganizationLevelEnum.DIRECT_AND_PROVINCE_ORG_LEVEL.getId())) {
//            from.setStatus(3);
//        } else {
//            from.setStatus(5);
//        }
//        int status = from.getStatus();
////        fProcessService.insertForfeiProcess(from);
//        if (!DefaultAdviceEnum.REPORT_DEFAULT_ADVICE.getCode().equals(String.valueOf(status))) {
//            this.updateStatus(from.getFfId(), status);
//        }
//    }

    private void updateFile(ForfeiInfoVo form) {
        String id = form.getId();
        //先根据源ID查出所有文件,并获取文件ID列表
        List<FileManageVo> fmvList = fileManageService.listBySourceId(id);
        List<String> oldFileIds = null;
        if (!CollectionUtils.isEmpty(fmvList)) {
            oldFileIds = new ArrayList<>(fmvList.size());
            for (FileManageVo vo : fmvList) {
                oldFileIds.add(vo.getId());
            }
        }

        List<FileManageForm> files = form.getFiles();
        if (!CollectionUtils.isEmpty(files)) {
            StringBuilder ids = new StringBuilder();
            for (FileManageForm fileManageForm : files) {
                String fileId = fileManageForm.getId();
                if (CollectionUtils.isEmpty(oldFileIds)) {
                    ids.append("," + fileId);
                    fileManageService.insertFileMange(fileManageForm, "", id);
                } else {
                    if (oldFileIds.contains(fileId)) {
                        oldFileIds.remove(fileId);
                    } else {
                        ids.append("," + fileId);
                        fileManageService.insertFileMange(fileManageForm, "", id);
                    }
                }
            }
            //激活系统文件
            if (ids.length() > 0) {
                SysFileManageForm sfmf = new SysFileManageForm();
                sfmf.setIds(ids.substring(1));
                sfmf.setInterfaceNum("hidden");
                sfmf.setSystemId(Constants.SYSTEM_ID);
                try {
                    Result<List<SysFileVo>> result = sysRegionApi.activationFile(sfmf);
                    if (result.getData().size() < 1) {
                        throw new SofnException("激活文件失败!请检查文件是否上传成功");
                    }
                } catch (Exception e) {
                    throw new SofnException("激活文件失败!请检查文件是否上传成功");
                }
            }
        }

        if (!CollectionUtils.isEmpty(oldFileIds)) {
            // 本系统文件删除
            fileManageService.deleteBatchIds(oldFileIds);
            //支撑平台文件删除
            StringBuilder sysDelIds = new StringBuilder();
            for (String sysDelId : oldFileIds) {
                sysDelIds.append("," + sysDelId);
            }
            try {
                Result<String> result = sysRegionApi.batchDeleteFile(sysDelIds.substring(1));
                if (!Result.CODE.equals(result.getCode())) {
                    throw new SofnException("支撑平台文件删除文件失败!");
                }
            } catch (Exception e) {
                throw new SofnException("支撑平台连接失败!");
            }
        }
    }

    //后续添加    记录上报
    private void recordReport(String fid) {
        ForfeiProcessFrom from = new ForfeiProcessFrom();
        from.setFfId(fid);
        from.setStatus(Integer.parseInt(DefaultAdviceEnum.REPORT_DEFAULT_ADVICE.getCode()));
        this.insertForfeiProcess(from);
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
            //             省级直属 省级
            params.put("orgId", orgId);
            params.put("provinceOrg", province);
//            }

        } else if (orgLevel.equals(OrganizationLevelEnum.DISTRICT_ORG_LEVEL.getId()) || orgLevel.equals(OrganizationLevelEnum.DIRECT_ORG_LEVEL.getId())) {
            String area = sysRegionInfoByOrgId.getData().getArea();
            //  区级直属 区级
            params.put("orgId", orgId);
            params.put("areaOrg", area);
//        }

        } else if (orgLevel.equals(OrganizationLevelEnum.CITY_ORG_LEVEL.getId()) || orgLevel.equals(OrganizationLevelEnum.DIRECT_AND_CITY_ORG_LEVEL.getId())) {
            String city = sysRegionInfoByOrgId.getData().getCity();
//                市级直属 市级
            params.put("cityOrg", city);
            params.put("orgId", orgId);
//            }
        } else {
//            部级
            params.put("Min", "1");
        }


    }
}
