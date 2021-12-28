package com.sofn.fdpi.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sofn.common.email.EmailService;
import com.sofn.common.exception.SofnException;
import com.sofn.common.model.Result;
import com.sofn.common.utils.IdUtil;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.RegionCacheUtils;
import com.sofn.common.utils.UserUtil;
import com.sofn.fdpi.constants.Constants;
import com.sofn.fdpi.enums.OrganizationLevelEnum;
import com.sofn.fdpi.mapper.FeedbackMapper;
import com.sofn.fdpi.model.Feedback;
import com.sofn.fdpi.model.FileManage;
import com.sofn.fdpi.service.FeedbackService;
import com.sofn.fdpi.service.FileManageService;
import com.sofn.fdpi.sysapi.SysRegionApi;
import com.sofn.fdpi.sysapi.bean.SysFileManageForm;
import com.sofn.fdpi.util.RedisUserUtil;
import com.sofn.fdpi.util.SysOwnOrgUtil;
import com.sofn.fdpi.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Auther: Bay
 * @Date: 2019/12/30 09:12
 */
@Slf4j
@Service
public class FeedbackServiceImpl extends ServiceImpl<FeedbackMapper, Feedback> implements FeedbackService {
    @Autowired
    FeedbackMapper feedbackMapper;
    @Autowired
    SysRegionApi sysRegionApi;
    @Autowired
    FileManageService fileManageService;
    @Autowired(required = false)
    private EmailService emailService;

    /**
     * 获取反馈信息列表(分页)
     *
     * @param map
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public PageUtils<Feedback> getFeedbackListByPage(Map<String, Object> map, int pageNo, int pageSize) {
        String sysOrgLevelId = SysOwnOrgUtil.getSysOrgInfoForApproveAndQuery().getData().getSysOrgLevelId();
        Boolean caseOne = (OrganizationLevelEnum.DIRECT_AND_MINISTRY_ORG_LEVEL.getId().equals(sysOrgLevelId) || OrganizationLevelEnum.MINISTRY_ORG_LEVEL.getId().equals(sysOrgLevelId));
        if (!caseOne) {
            Result<SysRegionInfoVo> sysRegionInfoByOrgId = sysRegionApi.getSysRegionInfoByOrgId(UserUtil.getLoginUserOrganizationId());
            SysRegionInfoVo infoVo = sysRegionInfoByOrgId.getData();
            map.put("provincialId", infoVo.getProvince());
        }
        PageHelper.offsetPage(pageNo, pageSize);
        List<Feedback> feedbackList = feedbackMapper.getFeedbackList(map);
        for (Feedback f :
                feedbackList) {
            f.setFiles(fileManageService.list(f.getId()));
            String[] regionNamesByCodes = RegionCacheUtils.getRegionNamesByCodes(f.getProvince(), f.getCity(), f.getArea());
            f.setLocalPlace(Arrays.stream(regionNamesByCodes).collect(Collectors.joining("-")));
        }
        PageInfo<Feedback> spePageInfo = new PageInfo<>(feedbackList);
        PageUtils pageUtils = PageUtils.getPageUtils(spePageInfo);
        return pageUtils;
    }

    /**
     * 获取公众反馈信息
     *
     * @param map
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public PageUtils<Feedback> ListPage(Map<String, Object> map, int pageNo, int pageSize) {
        String sysOrgLevelId = SysOwnOrgUtil.getSysOrgInfoForApproveAndQuery().getData().getSysOrgLevelId();
//        直属县级用户
        Boolean caseOne = (OrganizationLevelEnum.DIRECT_ORG_LEVEL.getId().equals(sysOrgLevelId));
        //       直属市级用户
        Boolean caseTwo = (OrganizationLevelEnum.DIRECT_AND_CITY_ORG_LEVEL.getId().equals(sysOrgLevelId));
//      直属省级用户
        Boolean caseThree = (OrganizationLevelEnum.DIRECT_AND_PROVINCE_ORG_LEVEL.getId().equals(sysOrgLevelId));
        Boolean case4 = (OrganizationLevelEnum.DIRECT_AND_MINISTRY_ORG_LEVEL.getId().equals(sysOrgLevelId));
        Boolean case5 = (OrganizationLevelEnum.MINISTRY_ORG_LEVEL.getId().equals(sysOrgLevelId));
        if (caseOne || caseTwo || caseThree) {
            map.put("direclyId", UserUtil.getLoginUserOrganizationId());
        }
//        非直属部门
        if (!caseOne && !caseTwo && !caseThree && !case4 && !case5) {
            Result<SysRegionInfoVo> sysRegionInfoByOrgId = sysRegionApi.getSysRegionInfoByOrgId(UserUtil.getLoginUserOrganizationId());
            SysRegionInfoVo infoVo = sysRegionInfoByOrgId.getData();
            map.put("provincialId", infoVo.getProvince());
        }
        PageHelper.offsetPage(pageNo, pageSize);
        List<Feedback> feedbackList = feedbackMapper.listPublic(map);
        for (Feedback f :
                feedbackList) {
            f.setFiles(fileManageService.list(f.getId()));
            String[] regionNamesByCodes = RegionCacheUtils.getRegionNamesByCodes(f.getProvince(), f.getCity(), f.getArea());
            f.setLocalPlace(Arrays.stream(regionNamesByCodes).collect(Collectors.joining("-")));
        }
        PageInfo<Feedback> spePageInfo = new PageInfo<>(feedbackList);
        PageUtils pageUtils = PageUtils.getPageUtils(spePageInfo);
        return pageUtils;

    }

    /**
     * 获取详细反馈信息
     *
     * @param id
     * @return
     */
    @Override
    public Feedback getFeedbackInfo(String id) {
        Feedback byId = feedbackMapper.selectById(id);
        StringBuilder st = new StringBuilder();
        if (byId.getProvince() != null) {
            Result<String> sysRegionName1 = sysRegionApi.getSysRegionName(byId.getProvince());
            st.append(sysRegionName1.getData());
            if (byId.getCity() != null) {
                Result<String> sysRegionName2 = sysRegionApi.getSysRegionName(byId.getCity());
                st.append("-" + sysRegionName2.getData());
                if (byId.getArea() != null) {
                    Result<String> sysRegionName3 = sysRegionApi.getSysRegionName(byId.getArea());
                    st.append("-" + sysRegionName3.getData());
                }
            }
        }
        byId.setLocalPlace(st.toString());
        List<FileManage> list = fileManageService.list(id);
        byId.setFiles(list);
        return byId;
    }

    /**
     * 新增反馈信息
     *
     * @param feedbackInfoVo
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int saveFeedback(FeedbackInfoVo feedbackInfoVo) {
        RedisUserUtil.validReSubmit("fdpi_feedback_save");
        int i = 0;
        Result<SysRegionInfoVo> sysRegionInfoByOrgId = sysRegionApi.getSysRegionInfoByOrgId(UserUtil.getLoginUserOrganizationId());
        String sysOrgProvince = sysRegionInfoByOrgId.getData().getProvince();
        feedbackInfoVo.setId(IdUtil.getUUId());
        Feedback feedback = feedbackInfoVo.convertToModel(Feedback.class);
        feedback.setDelFlag("N");
        feedback.setFfFrom("2");
        feedback.setCreateUserId(UserUtil.getLoginUserId());
        feedback.setCreateTime(new Date());
        feedback.setProvinceId(sysOrgProvince);
        if (feedbackInfoVo.getFiles() != null && feedbackInfoVo.getFiles().size() > 0) {
            this.updateFile(feedbackInfoVo);
        }
        i = feedbackMapper.insert(feedback);

        return i;
    }

    /**
     * 公众
     *
     * @param feedbackInfoVo
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int savePublic(FeedbackInfoVo feedbackInfoVo) {
        RedisUserUtil.validReSubmit("fdpi_public_save");
        String direclyId = feedbackMapper.getDireclyId(feedbackInfoVo.getFfUnit().trim());
        if (direclyId == null) {
            throw new SofnException("没有找到违法单位的直属部门");
        }
        feedbackInfoVo.setId(IdUtil.getUUId());
        Feedback feedback = feedbackInfoVo.convertToModel(Feedback.class);
        feedback.setDelFlag("N");
        feedback.setStatus("0");
        feedback.setFfFrom("1");
        feedback.setDireclyId(direclyId);
        feedback.setCreateTime(new Date());
        if (feedbackInfoVo.getFiles() != null && feedbackInfoVo.getFiles().size() > 0) {
            this.updateFile(feedbackInfoVo);
        }
        int i = feedbackMapper.insert(feedback);
        return i;
    }

    @Override
    public int del(String id) {
        return feedbackMapper.del(id);
    }

    @Transactional
    @Override
    public int update(FeedbackInfoVo feedbackInfoVo) {
        RedisUserUtil.validReSubmit("fdpi_feedback_update");
        fileManageService.del(feedbackInfoVo.getId());
        Feedback feedback = new Feedback();
        BeanUtils.copyProperties(feedbackInfoVo, feedback);
        feedbackMapper.updateById(feedback);
        updateFile(feedbackInfoVo);
        return 0;
    }

    @Override
    public void advice(Map map) {

        feedbackMapper.advice(map);
        Feedback byId = feedbackMapper.selectById((String) map.get("id"));
        if (byId != null) {
//            发邮件
            String email = byId.getEmail();
            String advice = byId.getAdvice();
            try {
                emailService.sendHtmlMail(email, "反馈信息", advice);
            } catch (Exception e) {
                throw new SofnException("邮件发送失败!");
            }
        }
    }

    private void updateFile(FeedbackInfoVo form) {
        String yId = form.getId();
        //先根据源ID查出所有文件,并获取文件ID列表
        List<FileManageVo> fLists = fileManageService.listBySourceId(yId);
        List<String> fileIds = null;
        if (fLists != null) {
            fileIds = new ArrayList<>(fLists.size());
            for (FileManageVo vo : fLists) {
                fileIds.add(vo.getId());
            }
        }

        List<FileManageForm> files = form.getFiles();
        if (!CollectionUtils.isEmpty(files)) {
            StringBuilder stringIds = new StringBuilder();
            for (FileManageForm fileManageForm : files) {
                String fileId = fileManageForm.getId();
                if (fileId != null) {
                    stringIds.append("," + fileId);
                    fileManageService.insertFileMange(fileManageForm, "", yId);
                } else {
                    if (fileIds.contains(fileId)) {
                        fileIds.remove(fileId);
                    } else {
                        stringIds.append("," + fileId);
                        fileManageService.insertFileMange(fileManageForm, "", yId);
                    }
                }
            }
            //激活系统文件
            if (stringIds.length() > 0) {
                SysFileManageForm sfmf = new SysFileManageForm();
                sfmf.setIds(stringIds.substring(1));
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

        if (!CollectionUtils.isEmpty(fileIds)) {
            // 本系统文件删除
            fileManageService.deleteBatchIds(fileIds);
            //支撑平台文件删除
            StringBuilder sysDelIds = new StringBuilder();
            for (String sysDelId : fileIds) {
                sysDelIds.append("," + sysDelId);
            }
            try {
                Result<String> result = sysRegionApi.batchDeleteFile(sysDelIds.substring(1));
                if (!Result.CODE.equals(result.getCode())) {
                    throw new SofnException("平台删除文件失败!");
                }
            } catch (Exception e) {
                throw new SofnException("支撑平台连接失败!");
            }
        }
    }
}
