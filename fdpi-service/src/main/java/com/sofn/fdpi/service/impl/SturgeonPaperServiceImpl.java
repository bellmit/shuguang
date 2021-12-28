package com.sofn.fdpi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.sofn.common.exception.SofnException;
import com.sofn.common.service.BaseService;
import com.sofn.common.utils.BoolUtils;
import com.sofn.common.utils.DateUtils;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.UserUtil;
import com.sofn.fdpi.constants.Constants;
import com.sofn.fdpi.enums.CodeTypeEnum;
import com.sofn.fdpi.enums.SturgeonPaperEnum;
import com.sofn.fdpi.enums.SturgeonStatusEnum;
import com.sofn.fdpi.mapper.SturgeonPaperMapper;
import com.sofn.fdpi.model.SturgeonPaper;
import com.sofn.fdpi.model.SturgeonProcess;
import com.sofn.fdpi.service.SturgeonPaperService;
import com.sofn.fdpi.service.SturgeonProcessService;
import com.sofn.fdpi.service.TbCompService;
import com.sofn.fdpi.service.WorkService;
import com.sofn.fdpi.sysapi.bean.*;
import com.sofn.fdpi.util.*;
import com.sofn.fdpi.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;

@Service(value = "sturgeonPaperService")
public class SturgeonPaperServiceImpl extends BaseService<SturgeonPaperMapper, SturgeonPaper> implements SturgeonPaperService {

    private final String DEF_ID = "fdpi:cites_paper";

    private final String ID_ATTR_NAME = "dataId";

    @Resource
    private SturgeonPaperMapper sturgeonPaperMapper;
    @Resource
    private SturgeonProcessService sturgeonProcessService;
    @Resource
    @Lazy
    private TbCompService tbCompService;
    @Resource
    private WorkService workService;

    @Override
    @Transactional
    public SturgeonPaperVo save(SturgeonPaperForm form) {
        RedisUserUtil.validReSubmit("fdpi_save");
        String status = form.getStatus();
        if (!SturgeonStatusEnum.KEEP.getKey().equals(status) && !SturgeonStatusEnum.REPORT.getKey().equals(status))
            throw new SofnException("新增状态只能为1保存2上报,请检查");
        SturgeonPaper entity = new SturgeonPaper();
        BeanUtils.copyProperties(form, entity);
        entity.preInsert();
        entity.setCompId(UserUtil.getLoginUserOrganizationId());
        if (StringUtils.isEmpty(entity.getApplyType())) {
            entity.setApplyType("1");
        }
        if (SturgeonStatusEnum.REPORT.getKey().equals(status)) {
            entity.setApplyTime(entity.getCreateTime());
            //生成申请编号
            String provinceCode = CodeUtil.getProvinceCode(tbCompService.getCombById(UserUtil.getLoginUserOrganizationId()).getRegionInCh().split("-")[0]);
            String codeType = "2".equals(entity.getApplyType()) ?
                    CodeTypeEnum.CITES_PAPER_APPLY.getKey() : CodeTypeEnum.STURGEON_PAPER_APPLY.getKey();
            String applyCode = CodeUtil.getApplyCode(provinceCode, codeType, this.getSequenceNum(provinceCode));
            //验证申请单号是否重复
            RedisUserUtil.validApplyCode(applyCode);
            entity.setApplyCode(applyCode);
            if (Constants.WORKFLOW.equals(BoolUtils.N)) {
                SturgeonProcess sturgeonProcess = new SturgeonProcess();
                sturgeonProcess.setApplyId(entity.getId());
                sturgeonProcess.setStatus(status);
                sturgeonProcessService.insertSturgeonProcess(sturgeonProcess);
            } else {
                String[] keys = {"status", "person"};
                Object[] vals = {status, SysOwnOrgUtil.getOrganizationName()};
                workService.startChainProcess(
                        new SubmitInstanceVo(DEF_ID, ID_ATTR_NAME, entity.getId(), MapUtil.getParams(keys, vals)));
            }
        }
        SturgeonPaperVo sturgeonPaperVo = SturgeonPaperVo.entity2Vo(entity);
        sturgeonPaperMapper.insert(entity);
        RedisUserUtil.delRedisKey(entity.getApplyCode());
        return sturgeonPaperVo;
    }

    private String getSequenceNum(String provinceCode) {
        String maxApplyNum = sturgeonPaperMapper.getTodayMaxApplyNum(
                DateUtils.format(new Date(), "yyyyMMdd") + "0" + provinceCode);
        return StringUtils.hasText(maxApplyNum) ?
                String.format("%06d", (Integer.valueOf(maxApplyNum.substring(13)) + 1)) : null;
    }

    @Override
    public void delete(String id) {
        UpdateWrapper<SturgeonPaper> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("ID", id).eq("DEL_FLAG", BoolUtils.N);
        SturgeonPaper entity = new SturgeonPaper();
        entity.preUpdate();
        entity.setDelFlag(BoolUtils.Y);
        sturgeonPaperMapper.update(entity, updateWrapper);
    }

    @Override
    @Transactional
    public void update(SturgeonPaperForm form) {
        String status = form.getStatus();
        if (!SturgeonStatusEnum.KEEP.getKey().equals(status) && !SturgeonStatusEnum.REPORT.getKey().equals(status)) {
            throw new SofnException("修改状态只能为1保存2上报,请检查");
        }
        String id = form.getId();
        QueryWrapper<SturgeonPaper> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ID", id).eq("DEL_FLAG", BoolUtils.N);
        SturgeonPaper entity = sturgeonPaperMapper.selectOne(queryWrapper);
        if (Objects.isNull(entity)) {
            throw new SofnException("待修改数据不存在!");
        }
        BeanUtils.copyProperties(form, entity);
        entity.preUpdate();
        if (SturgeonStatusEnum.REPORT.getKey().equals(status)) {
            if (Objects.isNull(entity.getApplyTime())) {
                entity.setApplyTime(new Date());
                String provinceCode = CodeUtil.getProvinceCode(tbCompService.getCombById(
                        UserUtil.getLoginUserOrganizationId()).getRegionInCh().split("-")[0]);
                String codeType = "2".equals(entity.getApplyType()) ?
                        CodeTypeEnum.CITES_PAPER_APPLY.getKey() : CodeTypeEnum.STURGEON_PAPER_APPLY.getKey();
                String applyCode = CodeUtil.getApplyCode(provinceCode, codeType, this.getSequenceNum(provinceCode));
                //验证申请单号是否重复
                RedisUserUtil.validApplyCode(applyCode);
                entity.setApplyCode(applyCode);
            }
            if (Constants.WORKFLOW.equals(BoolUtils.N)) {
                SturgeonProcess sturgeonProcess = new SturgeonProcess();
                sturgeonProcess.setApplyId(entity.getId());
                sturgeonProcess.setStatus(status);
                sturgeonProcessService.insertSturgeonProcess(sturgeonProcess);
            } else {
                String[] keys = {"status", "person"};
                Object[] vals = {status, SysOwnOrgUtil.getOrganizationName()};
                workService.startChainProcess(
                        new SubmitInstanceVo(DEF_ID, ID_ATTR_NAME, entity.getId(), MapUtil.getParams(keys, vals)));
            }
        }
        sturgeonPaperMapper.updateById(entity);
        RedisUserUtil.delRedisKey(entity.getApplyCode());
    }

    @Override
    public SturgeonPaperVo get(String id) {
        QueryWrapper<SturgeonPaper> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ID", id).eq("DEL_FLAG", BoolUtils.N);
        SturgeonPaperVo sturgeonPaperVo = SturgeonPaperVo.entity2Vo(sturgeonPaperMapper.selectOne(queryWrapper));
        if (Objects.nonNull(sturgeonPaperVo)) {
            sturgeonPaperVo.setCompName(tbCompService.getCombById(sturgeonPaperVo.getCompId()).getCompName());
        }
        return sturgeonPaperVo;
    }

    @Override
    @Transactional
    public void auditPass(String id) {
        this.audit(id, SturgeonStatusEnum.PASS.getKey(), null);
        String[] keys = {"status", "person"};
        Object[] vals = {SturgeonStatusEnum.PASS.getKey(), SysOwnOrgUtil.getOrganizationName()};
        if (Constants.WORKFLOW.equals(BoolUtils.Y))
            workService.completeWorkItem(new SubmitInstanceVo(DEF_ID, ID_ATTR_NAME, id, MapUtil.getParams(keys, vals)));
    }

    @Override
    @Transactional
    public void auditReturn(String id, String opinion) {
        this.audit(id, SturgeonStatusEnum.RETURN.getKey(), opinion);
        String[] keys = {"status", "opinion", "person"};
        Object[] vals = {SturgeonStatusEnum.RETURN.getKey(), opinion, SysOwnOrgUtil.getOrganizationName()};
        if (Constants.WORKFLOW.equals(BoolUtils.Y))
            workService.backWorkItem(BackWorkItemForm.getInstanceForm(
                    DEF_ID, ID_ATTR_NAME, id, "report", MapUtil.getParams(keys, vals)));
    }

    @Override
    public PageUtils<SturgeonPaperVo> listPage(Map<String, Object> params, Integer pageNo, Integer pageSize) {
        this.perfectParams(params);
        if (Objects.isNull(params.get("applyType"))) {
            params.put("applyType", "1");
        }
        PageHelper.offsetPage(pageNo, pageSize);
        List<SturgeonPaperVo> sturgeonPaperVos = sturgeonPaperMapper.listByParams(params);
        sturgeonPaperVos.forEach(o -> {
            if (Objects.nonNull(params.get("compId")) && SturgeonStatusEnum.REPORT.getKey().equals(o.getStatus())) {
                o.setCanCancel(true);
            } else {
                o.setCanCancel(false);
            }
            o.setStatusName(SturgeonPaperEnum.getVal(o.getStatus()));
        });
        return PageUtils.getPageUtils(new PageInfo(sturgeonPaperVos));
    }

    public static void perfectParams(Map<String, Object> params) {
        List<String> roles = UserUtil.getLoginUserRoleCodeList();
        OrganizationInfo orgInfo = SysOwnOrgUtil.getOrgInfo();
        String organizationLevel = orgInfo.getOrganizationLevel();
        if (CollectionUtils.isEmpty(roles)) {
            throw new SofnException("未获取到该用户角色");
        }
        if (Objects.isNull(params.get("applyType"))) {
            params.put("applyType", "1");
        }
        if (roles.contains(Constants.PRINT_USER_ROLE_CODE)) {
            params.put("isPrint", BoolUtils.Y);
        } else if (roles.contains(Constants.COMP_USER_ROLE_CODE)) {
            params.put("compId", UserUtil.getLoginUserOrganizationId());
        } else if (Constants.REGION_TYPE_MINISTRY.equals(organizationLevel)) {
            params.put("isMinistry" + params.get("applyType"), BoolUtils.Y);
        } else {
            params.put("direclyId", UserUtil.getLoginUserOrganizationId());
        }

    }

    @Override
    @Transactional
    public void report(String id) {
        QueryWrapper<SturgeonPaper> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ID", id).eq("DEL_FLAG", BoolUtils.N);
        SturgeonPaper entity = sturgeonPaperMapper.selectOne(queryWrapper);
        if (Objects.isNull(entity)) {
            throw new SofnException("待上报的数据不存在");
        }
        if (Objects.isNull(entity.getApplyTime())) {
            entity.setApplyTime(new Date());
        }
        if (Objects.isNull(entity.getApplyCode())) {
            String provinceCode = CodeUtil.getProvinceCode(tbCompService.getCombById(
                    UserUtil.getLoginUserOrganizationId()).getRegionInCh().split("-")[0]);
            String codeType = "2".equals(entity.getApplyType()) ?
                    CodeTypeEnum.CITES_PAPER_APPLY.getKey() : CodeTypeEnum.STURGEON_PAPER_APPLY.getKey();
            String applyCode = CodeUtil.getApplyCode(provinceCode, codeType, this.getSequenceNum(provinceCode));
            //验证申请单号是否重复
            RedisUserUtil.validApplyCode(applyCode);
            entity.setApplyCode(applyCode);
        }
        entity.preUpdate();
        entity.setStatus(SturgeonStatusEnum.REPORT.getKey());
        sturgeonPaperMapper.updateById(entity);
        if (Constants.WORKFLOW.equals(BoolUtils.N)) {
            //增加上报流程
            SturgeonProcess sturgeonProcess = new SturgeonProcess();
            sturgeonProcess.setApplyId(id);
            sturgeonProcess.setStatus(SturgeonStatusEnum.REPORT.getKey());
            sturgeonProcessService.insertSturgeonProcess(sturgeonProcess);
        } else {
            String[] keys = {"status", "person"};
            Object[] vals = {SturgeonStatusEnum.REPORT.getKey(), SysOwnOrgUtil.getOrganizationName()};
            workService.startChainProcess(
                    new SubmitInstanceVo(DEF_ID, ID_ATTR_NAME, entity.getId(), MapUtil.getParams(keys, vals)));
        }
        RedisUserUtil.delRedisKey(entity.getApplyCode());
    }

    @Override
    public void express(String id, String express) {
        QueryWrapper<SturgeonPaper> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ID", id).eq("DEL_FLAG", BoolUtils.N);
        SturgeonPaper entity = sturgeonPaperMapper.selectOne(queryWrapper);
        if (Objects.isNull(entity)) {
            throw new SofnException("待填写快递信息的数据不存在");
        }
        String status = entity.getStatus();
        if (!SturgeonStatusEnum.PASS.getKey().equals(status) && !SturgeonPaperEnum.PRINT.getKey().equals(status)) {
            throw new SofnException("只能在已通过状态填写快递信息");
        }
        if ("2".equals(entity.getApplyType()) && SturgeonPaperEnum.PASS.getKey().equals(status)) {
            SturgeonProcess sturgeonProcess = new SturgeonProcess();
            sturgeonProcess.setApplyId(id);
            sturgeonProcess.setStatus(SturgeonPaperEnum.PRINT.getKey());
            sturgeonProcessService.insertSturgeonProcess(sturgeonProcess);
        }
        entity.preUpdate();
        entity.setStatus(SturgeonPaperEnum.PRINT.getKey());
        entity.setExpress(express);
        sturgeonPaperMapper.updateById(entity);
    }

    @Override
    public List<SturgeonProcessVo> listSturgeonProcess(String applyId) {
        SturgeonPaperVo spv = this.get(applyId);
        if (Objects.isNull(spv.getApplyTime())) {
            return Collections.EMPTY_LIST;
        }
        List<Map<String, Object>> list = WorkUtil.getProcesslist(DEF_ID, ID_ATTR_NAME, applyId);
        List<SturgeonProcessVo> sturgeonProcessVos = Lists.newArrayListWithCapacity(list.size());
        for (Map map : list) {
            SturgeonProcessVo vo = SturgeonProcessVo.map2Vo(map);
            vo.setAdvice(Objects.isNull(map.get("opinion")) ? null : map.get("opinion").toString());
            sturgeonProcessVos.add(vo);
        }
        return sturgeonProcessVos;
    }

    @Override
    @Transactional
    public void cancel(String id) {
        QueryWrapper<SturgeonPaper> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id).eq("del_flag", BoolUtils.N);
        SturgeonPaper sp = sturgeonPaperMapper.selectOne(wrapper);
        if (!SturgeonStatusEnum.REPORT.getKey().equals(sp.getStatus()))
            throw new SofnException("只有已上报状态才可以撤回!");
        sp.preUpdate();
        sp.setStatus(SturgeonStatusEnum.CANCEL.getKey());
        sturgeonPaperMapper.updateById(sp);
        String[] keys = {"status", "person"};
        Object[] vals = {SturgeonStatusEnum.CANCEL.getKey(), SysOwnOrgUtil.getOrganizationName()};
        if (Constants.WORKFLOW.equals(BoolUtils.Y))
            workService.backWorkItem(BackWorkItemForm.getInstanceForm(
                    DEF_ID, ID_ATTR_NAME, id, "report", MapUtil.getParams(keys, vals)));

    }

    @Override
    public String getApplyType(String id) {
        QueryWrapper<SturgeonPaper> queryWrapper = new QueryWrapper();
        queryWrapper.select("apply_type").eq("id", id);
        return sturgeonPaperMapper.selectOne(queryWrapper).getApplyType();
    }

    /**
     * 审核
     */
    private void audit(String id, String status, String opinion) {
        UpdateWrapper<SturgeonPaper> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("ID", id).eq("DEL_FLAG", BoolUtils.N);
        SturgeonPaper entity = new SturgeonPaper();
        entity.preUpdate();
        entity.setStatus(status);
        SturgeonProcess sturgeonProcess = new SturgeonProcess();
        sturgeonProcess.setApplyId(id);
        sturgeonProcess.setStatus(status);
        if (StringUtils.hasText(opinion)) {
            entity.setOpinion(opinion);
            sturgeonProcess.setAdvice(opinion);
        }
        sturgeonPaperMapper.update(entity, updateWrapper);
        if (Constants.WORKFLOW.equals(BoolUtils.N))
            sturgeonProcessService.insertSturgeonProcess(sturgeonProcess);

    }
}
