package com.sofn.fdpi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.sofn.common.enums.CommonEnum;
import com.sofn.common.exception.SofnException;
import com.sofn.common.service.BaseService;
import com.sofn.common.utils.BoolUtils;
import com.sofn.common.utils.DateUtils;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.UserUtil;
import com.sofn.fdpi.constants.Constants;
import com.sofn.fdpi.enums.*;
import com.sofn.fdpi.mapper.SignboardApplyMapper;
import com.sofn.fdpi.model.Signboard;
import com.sofn.fdpi.model.SignboardApply;
import com.sofn.fdpi.service.*;
import com.sofn.fdpi.sysapi.bean.BackWorkItemForm;
import com.sofn.fdpi.sysapi.bean.SubmitInstanceVo;
import com.sofn.fdpi.util.*;
import com.sofn.fdpi.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;

/**
 * 标识申请服务类
 *
 * @Author yumao
 * @Date 2019/12/27 17:30
 **/
@Service(value = "signboardApplyService")
@Slf4j
public class SignboardApplyServiceImpl extends BaseService<SignboardApplyMapper, SignboardApply> implements SignboardApplyService {


    private final String DEF_ID = "fdpi:signboard";

    private final String ID_ATTR_NAME = "dataId";

    @Resource
    private SignboardApplyMapper signboardApplyMapper;
    @Resource
    private SignboardProcessService signboardProcessService;
    @Resource
    private SignboardChangeRecordService signboardChangeRecordService;
    @Resource
    private SignboardApplyListService signboardApplyListService;
    @Resource
    private SignboardService signboardService;
    @Resource
    private SignboardPrintService signboardPrintService;
    @Resource
    private SqlSessionFactory sessionFactory;
    @Resource
    @Lazy
    private TbCompService tbCompService;
    @Resource
    private WorkService workService;
    @Resource
    private SpeService speService;
    @Resource
    private TbDepartmentService tbDepartmentService;


    @Override
    @Transactional
    public SignboardApply insertSignboardApply(SignboardApplyForm signboardApplyForm) {
        //幂等性处理
        RedisUserUtil.validReSubmit("fdpi_insert_SignboardApply");
        String processStatus = signboardApplyForm.getProcessStatus();
        if (!SignboardApplyProcessEnum.UN_REPORT.getKey().equals(processStatus) && !SignboardApplyProcessEnum.REPORT.getKey().equals(processStatus)) {
            throw new SofnException("新增申请,流程状态只能为已上报或未上报");
        }
        OrganizationInfo orgInfo = SysOwnOrgUtil.getOrgInfo();
        if (BoolUtils.Y.equals(orgInfo.getThirdOrg())) {
            throw new SofnException("新增申请只能由第三方机构(企业)保存/提交,当前用户所属机构为非第三方机构");
        }
        SignboardApply entity = new SignboardApply();
        BeanUtils.copyProperties(signboardApplyForm, entity);
        entity.preInsert();
        String applyType = entity.getApplyType();
        //补发 换发 注销 还需要记录具体的标识ID
        if (!SignboardApplyTypeEnum.ALLOTMENT.getKey().equals(applyType)) {
            List<SignboardApplyListForm> applyList = signboardApplyForm.getApplyList();
            if (CollectionUtils.isEmpty(applyList)) {
                throw new SofnException("请选择需要" + SignboardApplyTypeEnum.getVal(applyType) + "的标识编码");
            } else {
                entity.setApplyNum(applyList.size());
                this.recordApplyList(signboardApplyForm.getApplyList(), entity.getId(), applyType, processStatus);
            }
        }
        //如果上报,则添加标识流程-上报
        if (SignboardApplyProcessEnum.REPORT.getKey().equals(processStatus)) {
            entity.setApplyTime(entity.getCreateTime());
            String provinceCode = CodeUtil.getProvinceCode(
                    tbCompService.getCombById(signboardApplyForm.getCompId()).getRegionInCh().split("-")[0]);
            String applyCode = CodeUtil.getApplyCode(provinceCode,
                    CodeTypeEnum.SIGNBOARD_APPLY.getKey(), this.getSequenceNum(provinceCode));
            //验证申请单号是否重复
            RedisUserUtil.validApplyCode(applyCode);
            entity.setApplyCode(applyCode);
            this.recordReportProcess(entity.getId(), applyCode);
            if (Constants.WORKFLOW.equals(BoolUtils.Y)) {
                String[] keys = {"status", "person"};
                Object[] vals = {processStatus, SysOwnOrgUtil.getOrganizationName()};
                workService.startChainProcess(
                        new SubmitInstanceVo(DEF_ID, ID_ATTR_NAME, entity.getId(), MapUtil.getParams(keys, vals)));
            }
        }
        if (!"0000".equals(speService.getSpeBySpeId(entity.getSpeId()).getSpeCode())) {
            entity.setCitesCode("1");
        }
        signboardApplyMapper.insert(entity);
        RedisUserUtil.delRedisKey(entity.getApplyCode());
        return entity;
    }

    private String getSequenceNum(String provinceCode) {
        String maxApplyNum = signboardApplyMapper.getTodayMaxApplyNum(
                DateUtils.format(new Date(), "yyyyMMdd") + "0" + provinceCode);
        return StringUtils.hasText(maxApplyNum) ?
                String.format("%06d", (Integer.valueOf(maxApplyNum.substring(13)) + 1)) : null;
    }

    @Override
    @Transactional
    public void updateSignboardApply(SignboardApplyForm signboardApplyForm) {
        String processStatus = signboardApplyForm.getProcessStatus();
        if (!SignboardApplyProcessEnum.UN_REPORT.getKey().equals(processStatus) && !SignboardApplyProcessEnum.REPORT.getKey().equals(processStatus)) {
            throw new SofnException("新增申请,流程状态只能为已上报或未上报");
        }
        OrganizationInfo orgInfo = SysOwnOrgUtil.getOrgInfo();
        if (BoolUtils.Y.equals(orgInfo.getThirdOrg())) {
            throw new SofnException("修改申请只能由第三方机构(企业)保存/提交,当前用户所属机构为非第三方机构");
        }
        String applyId = signboardApplyForm.getId();
        SignboardApply entity = signboardApplyMapper.selectById(applyId);
        BeanUtils.copyProperties(signboardApplyForm, entity);
        entity.preUpdate();
        String applyType = entity.getApplyType();
        //补发 换发 注销 还需要记录具体的标识ID
        if (!SignboardApplyTypeEnum.ALLOTMENT.equals(applyType)) {
            //先删除原来数据,再做新增
            signboardApplyListService.deleteByApplyId(applyId);
            List<SignboardApplyListForm> applyList = signboardApplyForm.getApplyList();
            if (!CollectionUtils.isEmpty(applyList)) {
                entity.setApplyNum(applyList.size());
                this.recordApplyList(signboardApplyForm.getApplyList(), applyId, applyType, processStatus);
            }
        }

        //如果上报,则添加标识流程-上报
        if (SignboardApplyProcessEnum.REPORT.getKey().equals(processStatus)) {
            if (Objects.isNull(entity.getApplyTime())) {
                entity.setApplyTime(new Date());
            }
            if (!StringUtils.hasText(entity.getApplyCode())) {
                String provinceCode = CodeUtil.getProvinceCode(tbCompService.getCombById(
                        signboardApplyForm.getCompId()).getRegionInCh().split("-")[0]);
                String applyCode = CodeUtil.getApplyCode(provinceCode,
                        CodeTypeEnum.SIGNBOARD_APPLY.getKey(), this.getSequenceNum(provinceCode));
                //验证申请单号是否重复
                RedisUserUtil.validApplyCode(applyCode);
                entity.setApplyCode(applyCode);
            }
            this.recordReportProcess(applyId, entity.getApplyCode());
            if (Constants.WORKFLOW.equals(BoolUtils.Y)) {
                String[] keys = {"status", "person"};
                Object[] vals = {processStatus, SysOwnOrgUtil.getOrganizationName()};
                workService.startChainProcess(
                        new SubmitInstanceVo(DEF_ID, ID_ATTR_NAME, entity.getId(), MapUtil.getParams(keys, vals)));
            }
        }
        if (!"0000".equals(speService.getSpeBySpeId(entity.getSpeId()).getSpeCode())) {
            entity.setCitesCode("1");
        }
        signboardApplyMapper.updateById(entity);
        RedisUserUtil.delRedisKey(entity.getApplyCode());
    }

    /**
     * 记录上报流程
     */
    private void recordReportProcess(String applyId, String applyCode) {
        SignboardProcessForm signboardProcessForm = new SignboardProcessForm();
        signboardProcessForm.setApplyCode(applyCode);
        signboardProcessForm.setApplyId(applyId);
        signboardProcessForm.setStatus(SignboardApplyProcessEnum.REPORT.getKey());
        this.insertSignboardProcess(signboardProcessForm);
    }

    /**
     * 补发 换发 注销 还需要记录具体的标识ID
     */
    private void recordApplyList(
            List<SignboardApplyListForm> applyList, String applyId, String applyType, String processStatus) {
        List<String> signboardIds = new ArrayList<>(applyList.size());
        for (SignboardApplyListForm signboardApplyListForm : applyList) {
            //标识状态验证
            String status = signboardApplyListForm.getStatus();
            if (SignboardStatusEnum.UN_ACTIVATE.getKey().equals(status)
                    && SignboardApplyTypeEnum.RENEWAL.getKey().equals(applyType)) {
                throw new SofnException("存在未激活标识编码,不能进行换发操作!");
            } else if (SignboardStatusEnum.FEED.getKey().equals(status) &&
                    SignboardApplyTypeEnum.REPLACEMENT.getKey().equals(applyType)) {
                throw new SofnException("存在在养标识编码,不能进行补发操作!");
            } else if (SignboardStatusEnum.CANCELLATION.getKey().equals(status)) {
                throw new SofnException("存在已注销标识编码,不能进行操作!");
            } else if (SignboardStatusEnum.SALE.getKey().equals(status)) {
                throw new SofnException("存在销售标识编码,不能进行操作!");
            }
            String signboardId = signboardApplyListForm.getSignboardId();
            if (SignboardApplyProcessEnum.REPORT.getKey().equals(processStatus)) {
                validAppyingCode(signboardId);
            }
            if (signboardIds.contains(signboardId)) {
                SignboardVo signboardVo = signboardService.getSignboard(signboardId);
                String codeMsg = "";
                if (!Objects.isNull(signboardVo)) {
                    codeMsg = "(" + signboardVo.getCode() + ")";
                }
                throw new SofnException("存在重复的标识编码" + codeMsg + ",不可以提交");
            } else {
                signboardIds.add(signboardId);
            }
            signboardApplyListForm.setApplyId(applyId);
            signboardApplyListService.insertSignboardApplyList(signboardApplyListForm);
        }
    }

    //验证该标识是否正在申请流程中
    private void validAppyingCode(String signboardId) {
        String code = signboardApplyMapper.validAppyingCode(signboardId);
        if (StringUtils.hasText(code)) {
            throw new SofnException("标识编码(" + code + ")正在申请流程中，不能再次申请");
        }
    }

    @Override
    public void deleteLogic(String id) {
        SignboardApply signboardApply = signboardApplyMapper.selectById(id);
        signboardApply.preUpdate();
        signboardApply.setDelFlag(CommonEnum.DEL_FLAG_Y.getCode());
        signboardApplyMapper.updateById(signboardApply);
    }

    @Override
    public SignboardApplyVo getSignboardApply(String id) {
        SignboardApply signboardApply = signboardApplyMapper.getSignboardApply(id);
        if (Objects.isNull(signboardApply)) {
            return null;
        } else {
            if (CommonEnum.DEL_FLAG_Y.getCode().equals(signboardApply.getDelFlag())) {
                return null;
            }
            SignboardApplyVo saVo = SignboardApplyVo.entity2Vo(signboardApply);
            if (!SignboardApplyTypeEnum.ALLOTMENT.getKey().equals(saVo.getApplyType())) {
                saVo.setApplyList(signboardApplyListService.listByApplyId(id));
            }
            return saVo;
        }
    }

    @Override
    public PageUtils<SignboardApplyListVo> getSignboardApplyList(String id, String code, Integer pageNo, Integer pageSize) {
        Map map = new HashMap<String, Object>(2);
        map.put("applyId", id);
        map.put("code", code);
        return signboardApplyListService.listPage(map, pageNo, pageSize);
    }

    @Override
    public PageUtils<SignboardApplyVo> listPage(Map<String, Object> params, Integer pageNo, Integer pageSize) {
        this.perfectParams(params);
        PageHelper.offsetPage(pageNo, pageSize);
        List<SignboardApply> list = signboardApplyMapper.listByParams(params);
        PageInfo<SignboardApply> saPageInfo = new PageInfo<>(list);
        List<SignboardApplyVo> listVo = new ArrayList<>(list.size());
        if (!CollectionUtils.isEmpty(list)) {
            Set<String> auths = tbDepartmentService.listAllAuth(DepartmentTypeEnum.SIGNBOARD.getKey());
            for (SignboardApply entity : list) {
                SignboardApplyVo sav = SignboardApplyVo.entity2Vo(entity);
                String status = entity.getProcessStatus();
                if (Constants.REGION_TYPE_MINISTRY.equals(params.get("organizationLevel")) &&
                        SignboardApplyProcessEnum.FINAL_AUDIT.getKey().equals(status)) {
                    sav.setCanAllotment(sav.getApplyNum().equals(sav.getAllotmentNum()));
                } else {
                    sav.setCanAllotment(false);
                }
                if (!SignboardApplyProcessEnum.ALLOTMENT.getKey().equals(status)) {
                    sav.setAllotmentNum(0);
                }
                //如果当前请求角色是第三方企业，则判断是否显示撤回按钮,默认false不显示
                if (params.containsKey("compId")) {
                    //如果是已上报状态才显示
                    if (SignboardApplyProcessEnum.REPORT.getKey().equals(status)) {
                        sav.setShowCancel(true);
                    }
                }
                sav.setCanAudit(this.getCanAudit(auths, sav));
                listVo.add(sav);
            }
            PageInfo<SignboardApplyVo> savPageInfo = new PageInfo<>(listVo);
            savPageInfo.setTotal(saPageInfo.getTotal());
            savPageInfo.setPageSize(pageSize);
            savPageInfo.setPageNum(saPageInfo.getPageNum());
            return PageUtils.getPageUtils(savPageInfo);
        }
        return PageUtils.getPageUtils(new PageInfo<>(list));
    }

    private boolean getCanAudit(Set<String> auths, SignboardApplyVo sav) {
        String processStatus = sav.getProcessStatus();
        //只有状态在上报或者初审通过情况才能审核
        if (!SignboardApplyProcessEnum.REPORT.getKey().equals(processStatus) &&
                !SignboardApplyProcessEnum.AUDIT.getKey().equals(processStatus)) {
            return false;
        }
        String organizationLevel = SysOwnOrgUtil.getOrganizationLevel();
        String compDistrict = sav.getCompDistrict();
        String compCity = sav.getCompCity();

        if (Constants.REGION_TYPE_MINISTRY.equals(organizationLevel) &&
                SignboardApplyProcessEnum.AUDIT.getKey().equals(processStatus)) {
            return true;
        } else if (!SignboardApplyProcessEnum.REPORT.getKey().equals(processStatus)) {
            return false;
        }
        if (CollectionUtils.isEmpty(auths)) {
            return false;
        } else if (Constants.REGION_TYPE_PROVINCE.equals(organizationLevel)) {
            if (auths.contains(compDistrict) || auths.contains(compCity)) {
                return false;
            }
        } else if (Constants.REGION_TYPE_CITY.equals(organizationLevel)) {
            if (auths.contains(compDistrict)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Integer getApplyNum(String compId, String speId, String signboardType) {
        List<SignboardApply> list = signboardApplyMapper.countApplyNum(compId, speId, signboardType);
        if (!CollectionUtils.isEmpty(list)) {
            Integer res = 0;
            for (SignboardApply signboardApply : list) {
                res += signboardApply.getApplyNum();
                res -= signboardApply.getAllotmentNum();
            }
            return res;
        }
        return 0;
    }

    /**
     * 完善权限查询参数
     */
    private void perfectParams(Map<String, Object> params) {
        OrganizationInfo orgInfo = SysOwnOrgUtil.getOrgInfo();
        //验证用户机构配置是否满足要求
        RedisUserUtil.validLoginUser(orgInfo);
        String id = orgInfo.getId();
        String thirdOrg = orgInfo.getThirdOrg();
        String regionLastCode = orgInfo.getRegionLastCode();
        // 第三方机构
        if (BoolUtils.N.equals(thirdOrg)) {
            params.put("compId", id);
        } else {
            String organizationLevel = orgInfo.getOrganizationLevel();
            params.put("organizationLevel", organizationLevel);
            params.put("regionLastCode", regionLastCode);
//            //如果当前机构用户级别是市区县,查询参数需要增加直属机构ID
//            if (Constants.REGION_TYPE_PROVINCE.equals(organizationLevel) ||
//                    Constants.REGION_TYPE_COUNTY.equals(organizationLevel) ||
//                    Constants.REGION_TYPE_CITY.equals(organizationLevel)) {
//                params.put("direclyId", id);
//            }
//            //当前机构用户级别是省,查询参数需要增加省级区域代码
//            if (Constants.REGION_TYPE_PROVINCE.equals(organizationLevel)) {
//                int length = regionLastCode.length();
//                String provinceCode = length > 2 ? regionLastCode.substring(0, 2) + "0000" : regionLastCode + "0000";
//                params.put("provinceCode", provinceCode);
//            }
//            //如果当前机构用户级别是市区县,查询参数需要增加直属机构ID
//            else if (Constants.REGION_TYPE_PROVINCE.equals(organizationLevel)||
//                    Constants.REGION_TYPE_COUNTY.equals(organizationLevel) ||
//                    Constants.REGION_TYPE_CITY.equals(organizationLevel)) {
//                params.put("direclyId", id);
//            }
        }

    }

    @Override
    public void updateProcessStatusAndAdvice(String id, String processStatus, String lastAdvice) {
        UpdateWrapper<SignboardApply> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("ID", id);
        SignboardApply sa = new SignboardApply();
        sa.preUpdate();
        sa.setProcessStatus(processStatus);
        sa.setLastAdvice(lastAdvice);
        signboardApplyMapper.update(sa, updateWrapper);
    }

    @Override
    @Transactional
    public void audit(SignboardProcessForm signboardProcessForm) {
        RedisUserUtil.validReSubmit("fdpi_audit", signboardProcessForm.getApplyId());
        signboardProcessForm.setApplyCode(this.getById(signboardProcessForm.getApplyId()).getApplyCode());
        this.insertSignboardProcess(signboardProcessForm);
        String applyId = signboardProcessForm.getApplyId();
        String processStatus = signboardProcessForm.getStatus();
        String advice = signboardProcessForm.getAdvice();
        //更新标识流程状态(标识上报本身保存了流程状态,在这儿不需要重复操作)
        if (!SignboardApplyProcessEnum.REPORT.getKey().equals(processStatus)) {
            this.updateProcessStatusAndAdvice(applyId, processStatus, advice);
            //终审通过还需具体对标识操作
            if (SignboardApplyProcessEnum.FINAL_AUDIT.getKey().equals(processStatus)) {
                this.handleFinalProcess(applyId, signboardProcessForm.getAdvice());
            }
        }
        if (Constants.WORKFLOW.equals(BoolUtils.Y)) {
            //添加数据到流程组件
            String applyStatus = signboardProcessForm.getStatus();
            if (SignboardApplyProcessEnum.AUDIT.getKey().equals(applyStatus) ||
                    SignboardApplyProcessEnum.FINAL_AUDIT.getKey().equals(applyStatus)) {
                String[] keys = {"status", "person"};
                Object[] vals = {applyStatus, SysOwnOrgUtil.getOrganizationName()};
                workService.completeWorkItem(
                        new SubmitInstanceVo(DEF_ID, ID_ATTR_NAME, applyId, MapUtil.getParams(keys, vals)));
            } else if (SignboardApplyProcessEnum.AUDIT_RETURN.getKey().equals(applyStatus) ||
                    SignboardApplyProcessEnum.FINAL_AUDIT_RETURN.getKey().equals(applyStatus)) {
                String[] keys = {"status", "opinion", "person"};
                Object[] vals = {applyStatus, advice, SysOwnOrgUtil.getOrganizationName()};
                workService.backWorkItem(BackWorkItemForm.getInstanceForm(
                        DEF_ID, ID_ATTR_NAME, applyId, "report", MapUtil.getParams(keys, vals)));
            }
        }
    }

    @Transactional
    public void insertSignboardProcess(SignboardProcessForm signboardProcessForm) {
        signboardProcessService.insertSignboardProcess(signboardProcessForm);
    }

    @Override
    @Transactional
    public void report(String id) {
        QueryWrapper<SignboardApply> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ID", id).eq("DEL_FLAG", BoolUtils.N);
        SignboardApply entity = signboardApplyMapper.selectOne(queryWrapper);
        if (Objects.isNull(entity.getApplyTime())) {
            entity.setApplyTime(new Date());
        }
        if (StringUtils.isEmpty(entity.getApplyCode())) {
            String provinceCode = CodeUtil.getProvinceCode(tbCompService.getCombById(
                    entity.getCompId()).getRegionInCh().split("-")[0]);
            String applyCode = CodeUtil.getApplyCode(provinceCode,
                    CodeTypeEnum.SIGNBOARD_APPLY.getKey(), this.getSequenceNum(provinceCode));
            RedisUserUtil.validApplyCode(applyCode);
            entity.setApplyCode(applyCode);
        }
        entity.preUpdate();
        entity.setProcessStatus(SignboardApplyProcessEnum.REPORT.getKey());
        signboardApplyMapper.updateById(entity);
        this.recordReportProcess(id, entity.getApplyCode());
        if (Constants.WORKFLOW.equals(BoolUtils.Y)) {
            String[] keys = {"status", "person"};
            Object[] vals = {SignboardApplyProcessEnum.REPORT.getKey(), SysOwnOrgUtil.getOrganizationName()};
            workService.startChainProcess(
                    new SubmitInstanceVo(DEF_ID, ID_ATTR_NAME, id, MapUtil.getParams(keys, vals)));
        }
        RedisUserUtil.delRedisKey(entity.getApplyCode());
    }

    @Override
    public List<SignboardProcessVo> listSignboardProcess(String applyId) {
        SignboardApply sa = signboardApplyMapper.getSignboardApply(applyId);
        if (Objects.isNull(sa.getApplyTime())) {
            return Collections.EMPTY_LIST;
        }
        List<Map<String, Object>> list = WorkUtil.getProcesslist(DEF_ID, ID_ATTR_NAME, applyId);
        List<SignboardProcessVo> signboardProcessVos = Lists.newArrayListWithCapacity(list.size());
        for (Map map : list) {
            SignboardProcessVo vo = SignboardProcessVo.map2Vo(map);
            vo.setAdvice(Objects.isNull(map.get("opinion")) ? null : map.get("opinion").toString());
            signboardProcessVos.add(vo);
        }
        return signboardProcessVos;
    }

    @Override
    public List<SignboardApply> listByParams(Map<String, Object> params) {
        return signboardApplyMapper.listByParams(params);
    }

    /**
     * 终审通过,根据申请类型具体操作
     */
    private void handleFinalProcess(String applyId, String remark) {
        SignboardApply sa = signboardApplyMapper.selectById(applyId);
        String appleType = sa.getApplyType();
        if (SignboardApplyTypeEnum.ALLOTMENT.getKey().equals(appleType)) {
            //配发标识
            //新需求修改为审核和配发同步
            new Thread(() -> {
                try {
                    this.insertSignboard(sa);
                } catch (Exception e) {
                    RedisUserUtil.hdel(Constants.FDPI_INSERT_SIGNBOARD, sa.getSpeId());
                    signboardProcessService.
                            delByApplyIdAndStatus(applyId, SignboardApplyProcessEnum.FINAL_AUDIT.getKey());
                    this.updateProcessStatusAndAdvice(applyId, SignboardApplyProcessEnum.AUDIT.getKey(), "生成数据异常");
                }
            }).start();
        } else if (SignboardApplyTypeEnum.RENEWAL.getKey().equals(appleType)) {
            //换发标识
            this.changeSignboard(signboardApplyListService.listByApplyId(applyId), appleType, remark);
        } else if (SignboardApplyTypeEnum.REPLACEMENT.getKey().equals(appleType)) {
            //补发标识
            this.reIssue(signboardApplyListService.listByApplyId(applyId), appleType, remark);
        } else if (SignboardApplyTypeEnum.CANCELLATION.getKey().equals(appleType)) {
            //注销标识
            this.cancelSignboard(signboardApplyListService.listByApplyId(applyId), appleType, remark);
        }
    }

    /**
     * 记录标识变更
     */
    private void signboardChangeRecord(Signboard entity, String appleType, String remark) {
        SignboardChangeRecordVo scrVo = new SignboardChangeRecordVo();
        scrVo.setSpeId(entity.getSpeId());
        scrVo.setCompId(entity.getCompId());
        scrVo.setCode(entity.getCode());
        String status = "";
        if (SignboardApplyTypeEnum.ALLOTMENT.getKey().equals(appleType)) {
            status = SignboardChangeStatusEnum.DISTRIBUTE.getKey();
        } else if (SignboardApplyTypeEnum.RENEWAL.getKey().equals(appleType)) {
            status = SignboardChangeStatusEnum.RENEWAL.getKey();
        } else if (SignboardApplyTypeEnum.REPLACEMENT.getKey().equals(appleType)) {
            status = SignboardChangeStatusEnum.REPLACEMENT.getKey();
        } else if (SignboardApplyTypeEnum.CANCELLATION.getKey().equals(appleType)) {
            status = SignboardChangeStatusEnum.CANCELLATION.getKey();
        }
        scrVo.setStatus(status);
        // TODO 备注信息暂不知道填什么
        remark = "";
        scrVo.setRemark(remark);
        scrVo.setSignboardId(entity.getId());
        signboardChangeRecordService.insertSignboardChangeRecord(scrVo);
    }

    /**
     * 换发标识
     */
    private void changeSignboard(List<SignboardApplyListVo> signboardApplyListVos, String appleType, String remark) {
        if (!CollectionUtils.isEmpty(signboardApplyListVos)) {
            SqlSession session = sessionFactory.openSession(ExecutorType.BATCH);
            for (SignboardApplyListVo vo : signboardApplyListVos) {
                Signboard entity = signboardService.changeSignboard(vo);
                this.signboardChangeRecord(entity, appleType, remark);
            }
            session.commit();
            session.close();
        }
    }

    /**
     * 补发标识
     */
    private void reIssue(List<SignboardApplyListVo> signboardApplyListVos, String appleType, String remark) {
        if (!CollectionUtils.isEmpty(signboardApplyListVos)) {
            SqlSession session = sessionFactory.openSession(ExecutorType.BATCH);
            for (SignboardApplyListVo vo : signboardApplyListVos) {
                Signboard entity = signboardService.reIssue(vo.getSignboardId());
                this.signboardChangeRecord(entity, appleType, remark);
            }
            session.commit();
            session.close();
        }
    }

    /**
     * 新增标识
     */
    private void insertSignboard(SignboardApply signboardApply) {
        String createUserId = UserUtil.getLoginUserId();
        Boolean flag = true;
        Long expire = 7200L;
        String speId = signboardApply.getSpeId();
        String compId = signboardApply.getCompId();
        while (flag) {
            Object obj = RedisUserUtil.hget(Constants.FDPI_INSERT_SIGNBOARD, speId);
            System.out.println("申请单号(" + signboardApply.getApplyCode() + ")读到缓存数据为---------------------" + obj);
            if (Objects.isNull(obj)) {
                RedisUserUtil.hset(Constants.FDPI_INSERT_SIGNBOARD, speId, compId, expire);
                signboardService.insertSignboardBatch(signboardApply, createUserId);
                RedisUserUtil.hdel(Constants.FDPI_INSERT_SIGNBOARD, speId);
                flag = false;
            } else {
                try {
                    Thread.sleep(20000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    /**
     * 配发标识
     */
    @Override
    @Transactional
    public void allotment(AllotmentForm allotmentForm) {
        String applyId = allotmentForm.getId();
        String redisKey = RedisUserUtil.validReSubmit("fdpi_allotment", 1800L, applyId);
        SignboardApply signboardApply = this.getById(applyId);
        List<String> codes = allotmentForm.getCodes();
        Integer allotmentedNum = 0;
        List<SignboardPrintVo> spvs = signboardPrintService.listByApplyId(applyId);
        if (!CollectionUtils.isEmpty(spvs)) {
            for (SignboardPrintVo spv : spvs) {
                if (PrintStatusEnum.ALREADY_PRINTED.getKey().equals(spv.getStatus())) {
                    signboardService.updateDelFlagByPringId(spv.getId(), BoolUtils.N);
                    signboardApplyListService.updateDelFlagByPringId(spv.getId());
                }
            }
        }
        signboardApply.setProcessStatus(SignboardApplyProcessEnum.ALLOTMENT.getKey());
        signboardApplyMapper.updateById(signboardApply);
        SignboardProcessForm signboardProcessForm = new SignboardProcessForm();
        signboardProcessForm.setApplyCode(signboardApply.getApplyCode());
        signboardProcessForm.setApplyId(applyId);
        signboardProcessForm.setStatus(SignboardApplyProcessEnum.ALLOTMENT.getKey());
        this.insertSignboardProcess(signboardProcessForm);
        if (Constants.WORKFLOW.equals(BoolUtils.Y)) {
            //添加数据到流程组件
            Boolean flag = false;
            if (CollectionUtils.isEmpty(codes)) {
                flag = true;
            } else {
                //已配发的标识数量
                if (signboardApply.getApplyNum().equals(codes.size() + allotmentedNum))
                    flag = true;
            }
            if (flag) {
                String[] keys = {"status", "person"};
                Object[] vals = {SignboardApplyProcessEnum.ALLOTMENT.getKey(), SysOwnOrgUtil.getOrganizationName()};
                workService.completeWorkItem(
                        new SubmitInstanceVo(DEF_ID, ID_ATTR_NAME, applyId, MapUtil.getParams(keys, vals)));
            }
        }
        RedisUserUtil.delRedisKey(redisKey);
    }

    /**
     * @param id
     * @return void
     * @description 标识申请撤回
     * @date 2021/4/2 16:22
     */
    @Override
    public void cancel(String id) {
        RedisUserUtil.validReSubmit("fdpi_cancel", id);
        //查询当前标识申请信息
        QueryWrapper<SignboardApply> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id).eq("del_flag", BoolUtils.N);
        SignboardApply signboardApply = signboardApplyMapper.selectOne(wrapper);
        //非空校验
        if (signboardApply == null) {
            throw new SofnException("没有找到相关数据");
        }
        //如果当前是已上报才可以撤回
        if (signboardApply.getProcessStatus() != null && !signboardApply.getProcessStatus().trim().equals(SignboardApplyProcessEnum.REPORT.getKey())) {
            throw new SofnException("只有已上报状态才可以撤回");
        }
        //设置当前操作人、操作时间
        signboardApply.preUpdate();
        //设置更新后的状态
        signboardApply.setProcessStatus(SignboardApplyProcessEnum.CANCEL.getKey());
        //执行更新
        int i = signboardApplyMapper.updateById(signboardApply);
        if (i != 1) {
            throw new SofnException("执行撤回失败");
        }
        SignboardProcessForm signboardProcessForm = new SignboardProcessForm();
        signboardProcessForm.setApplyCode(signboardApply.getApplyCode());
        signboardProcessForm.setApplyId(id);
        signboardProcessForm.setStatus(SignboardApplyProcessEnum.CANCEL.getKey());
        this.insertSignboardProcess(signboardProcessForm);
        if (Constants.WORKFLOW.equals(BoolUtils.Y)) {
            //操作工作流
            String[] keys = {"status", "person"};
            Object[] vals = {SignboardApplyProcessEnum.CANCEL.getKey(), SysOwnOrgUtil.getOrganizationName()};
            workService.backWorkItem(BackWorkItemForm.getInstanceForm(
                    DEF_ID, ID_ATTR_NAME, id, "report", MapUtil.getParams(keys, vals)));
        }
    }

    @Override
    public int delByCompId(String compId) {
        QueryWrapper<SignboardApply> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("comp_id", compId);
        return signboardApplyMapper.delete(queryWrapper);
    }

    @Override
    public List<SignboardApply> listApplyByCompId(String compId) {
        QueryWrapper<SignboardApply> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("comp_id", compId);
        return signboardApplyMapper.selectList(queryWrapper);
    }

    /**
     * 注销标识
     */
    private void cancelSignboard(List<SignboardApplyListVo> signboardApplyListVos, String appleType, String remark) {
        if (!CollectionUtils.isEmpty(signboardApplyListVos)) {
            SqlSession session = sessionFactory.openSession(ExecutorType.BATCH);
            for (SignboardApplyListVo vo : signboardApplyListVos) {
                Signboard entity = signboardService.updateStatus(
                        vo.getSignboardId(), SignboardStatusEnum.CANCELLATION.getKey());
                this.signboardChangeRecord(entity, appleType, remark);
            }
            session.commit();
            session.close();
        }
    }

}
