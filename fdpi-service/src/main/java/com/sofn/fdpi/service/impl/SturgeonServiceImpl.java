package com.sofn.fdpi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sofn.common.exception.SofnException;
import com.sofn.common.service.BaseService;
import com.sofn.common.utils.BoolUtils;
import com.sofn.common.utils.DateUtils;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.UserUtil;
import com.sofn.fdpi.constants.Constants;
import com.sofn.fdpi.enums.*;
import com.sofn.fdpi.mapper.SturgeonMapper;
import com.sofn.fdpi.model.*;
import com.sofn.fdpi.service.*;
import com.sofn.fdpi.sysapi.bean.BackWorkItemForm;
import com.sofn.fdpi.sysapi.bean.SubmitInstanceVo;
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
import java.util.stream.Collectors;

@Service(value = "sturgeonService")
public class SturgeonServiceImpl extends BaseService<SturgeonMapper, Sturgeon> implements SturgeonService {

    private final String DEF_ID = "fdpi:cites";

    private final String ID_ATTR_NAME = "dataId";

    @Resource
    private SturgeonMapper sturgeonMapper;

    @Resource
    private SturgeonSubService sturgeonSubService;

    @Resource
    private SturgeonSignboardService sturgeonSignboardService;

    @Resource
    private SturgeonSignboardDomesticService ssdService;

    @Resource
    private SignboardPrintService signboardPrintService;

    @Resource
    private SturgeonProcessService sturgeonProcessService;

    @Resource
    @Lazy
    private TbCompService tbCompService;

    @Resource
    private PapersService papersService;

    @Resource
    private PapersSpecService papersSpecService;

    @Resource
    private TbDepartmentService tbDepartmentService;

    @Resource
    private FileUtil fileUtil;

    @Resource
    private WorkService workService;

    @Override
    @Transactional
    public SturgeonVo save(SturgeonForm form) {
        String redisKey = RedisUserUtil.validReSubmit("fdpi_save");
        String applyType = Objects.isNull(form.getApplyType()) ? "1" : form.getApplyType();
        //验证证书编号是否重复
        if (!"2".equals(applyType)) {
            this.validCredentials(form.getCredentials());
        }
        String status = form.getStatus();
        if (!SturgeonStatusEnum.KEEP.getKey().equals(status) && !SturgeonStatusEnum.REPORT.getKey().equals(status))
            throw new SofnException("新增状态只能为1保存2上报,请检查");
        Sturgeon entity = new Sturgeon();
        BeanUtils.copyProperties(form, entity);
        entity.preInsert();
        entity.setCompId(UserUtil.getLoginUserOrganizationId());
        entity.setApplyType(applyType);
        if (SturgeonStatusEnum.REPORT.getKey().equals(status)) {
            entity.setApplyTime(entity.getCreateTime());
            String provinceCode = CodeUtil.getProvinceCode(tbCompService.getCombById(UserUtil.getLoginUserOrganizationId()).getRegionInCh().split("-")[0]);
            String codeType = "2".equals(applyType) ?
                    CodeTypeEnum.CITES_APPLY.getKey() : CodeTypeEnum.STURGEON_APPLY.getKey();
            entity.setApplyCode(CodeUtil.getApplyCode(provinceCode, codeType, this.getSequenceNum(provinceCode)));
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

        SturgeonVo sturgeonVo = SturgeonVo.entity2Vo(entity);
        List<SturgeonSubForm> sturgeonSubForms = form.getSturgeonSubForms();
        Integer labelSum = mergeLabelS(SturgeonSubVo.forms2Vos(sturgeonSubForms)).size(); //sturgeonSubForms.size();
        if (!CollectionUtils.isEmpty(sturgeonSubForms)) {
            List<SturgeonSubVo> sturgeonSubVos = Lists.newArrayListWithCapacity(sturgeonSubForms.size());
            for (SturgeonSubForm sturgeonSubForm : sturgeonSubForms) {
                sturgeonSubForm.setSturgeonId(entity.getId());
                labelSum += Integer.parseInt(sturgeonSubForm.getEndNum()) -
                        Integer.parseInt(sturgeonSubForm.getStartNum()) + 1;
                sturgeonSubVos.add(sturgeonSubService.save(sturgeonSubForm, form.getApplyType()));
            }
            sturgeonVo.setSturgeonSubVos(sturgeonSubVos);
        }
        entity.setLabelSum(labelSum);
        sturgeonMapper.insert(entity);
        //激活文件
        this.activationFile("", form.getFileId());
        RedisUserUtil.delRedisKey(redisKey);
        return sturgeonVo;
    }

    private void validCredentials(String credentials) {
        QueryWrapper<Sturgeon> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("CREDENTIALS", credentials).eq("DEL_FLAG", BoolUtils.N);
        if (!CollectionUtils.isEmpty(sturgeonMapper.selectList(queryWrapper))) {
            throw new SofnException("证书编号(" + credentials + ")已提交申请,不可再次申请!");
        }
    }

    /**
     * 激活文件
     */
    private void activationFile(String oldFileId, String newFileId) {
        List<String> oldIds = this.getIds(oldFileId);
        List<String> newIds = this.getIds(newFileId);
        oldIds.removeAll(newIds);
        fileUtil.activationFile(String.join(",", newIds));
        if (!CollectionUtils.isEmpty(oldIds)) {
            fileUtil.batchDeleteFile(String.join(",", oldIds));
        }
    }

    //拼接id
    private List<String> getIds(String fileId) {
        List<String> ids = Lists.newArrayList();
        if (StringUtils.hasText(fileId)) {
            ids.addAll(Arrays.asList(fileId.split(",")));
        }
        return ids;
    }

    @Override
    @Transactional
    public void delete(String id) {
        QueryWrapper<Sturgeon> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ID", id).eq("DEL_FLAG", BoolUtils.N);
        Sturgeon entity = sturgeonMapper.selectOne(queryWrapper);
        if (Objects.isNull(entity)) {
            throw new SofnException("待删除的数据不存在");
        }
        entity.preUpdate();
        entity.setDelFlag(BoolUtils.Y);
        sturgeonMapper.updateById(entity);
        sturgeonSubService.deleteBySturgeonId(id);
    }

    @Override
    @Transactional
    public void update(SturgeonForm form) {
        String redisKey = RedisUserUtil.validReSubmit("fdpi_update", form.getId());
        String status = form.getStatus();
        if (!SturgeonStatusEnum.KEEP.getKey().equals(status) && !SturgeonStatusEnum.REPORT.getKey().equals(status)) {
            throw new SofnException("修改状态只能为1保存2上报,请检查");
        }
        String id = form.getId();
        QueryWrapper<Sturgeon> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ID", id).eq("DEL_FLAG", BoolUtils.N);
        Sturgeon entity = sturgeonMapper.selectOne(queryWrapper);
        if (Objects.isNull(entity)) {
            throw new SofnException("待修改数据不存在!");
        }
        //激活文件
        String oldFileId = entity.getFileId();
        this.activationFile(StringUtils.hasText(oldFileId) ? oldFileId : "", form.getFileId());
        BeanUtils.copyProperties(form, entity);
        entity.preUpdate();
        if (SturgeonStatusEnum.REPORT.getKey().equals(status)) {
            entity.setApplyTime(entity.getCreateTime());
            if (!StringUtils.hasText(entity.getApplyCode())) {
                String provinceCode = CodeUtil.getProvinceCode(tbCompService.getCombById(
                        UserUtil.getLoginUserOrganizationId()).getRegionInCh().split("-")[0]);
                String codeType = "2".equals(entity.getApplyType()) ?
                        CodeTypeEnum.CITES_APPLY.getKey() : CodeTypeEnum.STURGEON_APPLY.getKey();
                entity.setApplyCode(CodeUtil.getApplyCode(provinceCode, codeType, this.getSequenceNum(provinceCode)));
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
        //更新子表
        Integer labelSum = sturgeonSubService.update(id, form.getApplyType(), form.getSturgeonSubForms());
        entity.setLabelSum(labelSum);
        sturgeonMapper.updateById(entity);
        RedisUserUtil.delRedisKey(redisKey);
    }

    private String getSequenceNum(String provinceCode) {
        String maxApplyNum = sturgeonMapper.getTodayMaxApplyNum(
                DateUtils.format(new Date(), "yyyyMMdd") + "0" + provinceCode);
        return StringUtils.hasText(maxApplyNum) ?
                String.format("%06d", (Integer.valueOf(maxApplyNum.substring(13)) + 1)) : null;
    }

    @Override
    public SturgeonVo get(String id) {
        QueryWrapper<Sturgeon> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ID", id).eq("DEL_FLAG", BoolUtils.N);
        SturgeonVo sturgeonVo = SturgeonVo.entity2Vo(sturgeonMapper.selectOne(queryWrapper));
        if (Objects.nonNull(sturgeonVo)) {
            TbCompVo tbCompVo = tbCompService.getCombById(sturgeonVo.getCompId());
            sturgeonVo.setCompName(tbCompVo.getCompName());
            sturgeonVo.setCompType(tbCompVo.getCompType());
            sturgeonVo.setSturgeonSubVos(sturgeonSubService.listBySturgeonId(id));

        }
        return sturgeonVo;
    }

    @Override
    @Transactional
    public void auditPass(String id, String thirdPrint) {
        String redisKey = RedisUserUtil.validReSubmit("fdpi_pass", id);
        String organizationLevel = SysOwnOrgUtil.getOrganizationLevel();
        Sturgeon sturgeon = this.getOneById(id);
        String applyType = StringUtils.hasText(sturgeon.getApplyType()) ? sturgeon.getApplyType() : "1";
        String status = "";
        //申请类型1国外，只有部级通过
        if ("1".equals(applyType)) {
            //生成对应标签
            List<SturgeonSubVo> sturgeonSubVos = sturgeonSubService.listBySturgeonId(id);
            //1-A/B标签   2-C标签
            sturgeonSignboardService.savaBatch(sturgeon, sturgeonSubVos, "1");
            sturgeonSignboardService.savaBatch(sturgeon, this.mergeLabelS(sturgeonSubVos), "2");
            status = SturgeonStatusEnum.PASS.getKey();
        } else {
            //国内分初审通过还是部级通过
            if (Constants.REGION_TYPE_MINISTRY.equals(organizationLevel)) {
                List<SturgeonSubVo> sturgeonSubVos = sturgeonSubService.listBySturgeonId(id);
                //1-A/B标签   2-C标签
                String startCode = ssdService.savaBatch(sturgeon, sturgeonSubVos, thirdPrint);
                status = SturgeonStatusDomesticEnum.PASS.getKey();
                // 推送打印企业
                if (BoolUtils.Y.equals(thirdPrint)) {
                    this.pushThird(sturgeon, startCode);
                }
            } else {
                status = SturgeonStatusDomesticEnum.PASS_DIRECTLY.getKey();
            }
        }

        this.audit(id, status, null);
        if (Constants.WORKFLOW.equals(BoolUtils.Y)) {
            String[] keys = {"status", "person"};
            Object[] vals = {status, SysOwnOrgUtil.getOrganizationName()};
            workService.completeWorkItem(new SubmitInstanceVo(DEF_ID, ID_ATTR_NAME, id, MapUtil.getParams(keys, vals)));
        }
        RedisUserUtil.delRedisKey(redisKey);
    }

    private void pushThird(Sturgeon sturgeon, String startCode) {
        SignboardPrintForm signboardPrintForm = new SignboardPrintForm();
//        signboardPrintForm.setId(printId);
        signboardPrintForm.setType("0000");
        String compId = sturgeon.getCompId();
        TbCompVo tcv = tbCompService.getCombById(compId);
        signboardPrintForm.setCompId(compId);
        signboardPrintForm.setNum(this.getPrintNum(sturgeon.getId()));
        String provinceCode = CodeUtil.getProvinceCode(tcv.getRegionInCh().split("-")[0]);
        signboardPrintForm.setProvinceCode(provinceCode);
        signboardPrintForm.setCompCode(tcv.getCompCode());
        signboardPrintForm.setApplyId(sturgeon.getId());
        signboardPrintForm.setApplyType("2");
        signboardPrintForm.setCodeStart(startCode);
        signboardPrintService.insertSignboardPrint(signboardPrintForm, UserUtil.getLoginUserId());
    }

    private Integer getPrintNum(String signboardId) {
        return ssdService.getPrintNum(signboardId);
    }

    protected static List<SturgeonSubVo> mergeLabelS(List<SturgeonSubVo> sturgeonSubVos) {
        sturgeonSubVos = sturgeonSubVos.stream().
                sorted(Comparator.comparing(SturgeonSubVo::getCaseNum)).collect(Collectors.toList());
        List<SturgeonSubVo> result = Lists.newArrayListWithCapacity(sturgeonSubVos.size());
        Map<String, SturgeonSubVo> map = Maps.newHashMap();
        for (SturgeonSubVo sturgeonSubVo : sturgeonSubVos) {
            Integer caseNum = sturgeonSubVo.getCaseNum();
            String variety = sturgeonSubVo.getVariety();
            if (Objects.isNull(map.get(variety + caseNum))) {
                map.put(variety + caseNum, sturgeonSubVo);
            } else {
                SturgeonSubVo subVo = map.get(variety + caseNum);
                Integer currentStartNum = Integer.valueOf(sturgeonSubVo.getStartNum());
                Integer currentEndNum = Integer.valueOf(sturgeonSubVo.getEndNum());
                if (currentStartNum < Integer.valueOf(subVo.getStartNum())) {
                    subVo.setStartNum(currentStartNum.toString());
                }
                if (currentEndNum > Integer.valueOf(subVo.getEndNum())) {
                    subVo.setEndNum(currentEndNum.toString());
                }
            }
        }
        map.keySet().forEach(key -> result.add(map.get(key)));
        return result;
    }

    @Override
    @Transactional
    public void auditReturn(String id, String opinion) {
        String organizationLevel = SysOwnOrgUtil.getOrganizationLevel();
        Sturgeon sturgeon = this.getOneById(id);
        String applyType = StringUtils.hasText(sturgeon.getApplyType()) ? sturgeon.getApplyType() : "1";
        String status = "";
        //申请类型1国外，只有部级退回
        if ("1".equals(applyType)) {
            status = SturgeonStatusEnum.RETURN.getKey();
        } else {
            //国内分初审退回还是部级退回
            if (Constants.REGION_TYPE_MINISTRY.equals(organizationLevel)) {
                status = SturgeonStatusDomesticEnum.RETURN.getKey();
            } else {
                status = SturgeonStatusDomesticEnum.RETURN_DIRECTLY.getKey();
            }
        }
        this.audit(id, status, opinion);
        if (Constants.WORKFLOW.equals(BoolUtils.Y)) {
            String[] keys = {"status", "opinion", "person"};
            Object[] vals = {status, opinion, SysOwnOrgUtil.getOrganizationName()};
            workService.backWorkItem(BackWorkItemForm.getInstanceForm(
                    DEF_ID, ID_ATTR_NAME, id, "report", MapUtil.getParams(keys, vals)));
        }
    }

    @Override
    public PageUtils<SturgeonVo> listPage(Map<String, Object> params, Integer pageNo, Integer pageSize) {
        this.perfectParams(params);
        PageHelper.offsetPage(pageNo, pageSize);
        List<Sturgeon> sturgeons = sturgeonMapper.listByParams(params);
        if (!CollectionUtils.isEmpty(sturgeons)) {
            Set<String> auths = tbDepartmentService.listAllAuth(DepartmentTypeEnum.SIGNBOARD.getKey());
            PageInfo<Sturgeon> sturgeonPageInfo = new PageInfo<>(sturgeons);
            List<SturgeonVo> sturgeonVos = Lists.newArrayListWithCapacity(sturgeons.size());
            for (Sturgeon sturgeon : sturgeons) {
                SturgeonVo vo = SturgeonVo.entity2Vo(sturgeon);
                //判断当前状态决定是否显示撤回按钮
                if (params.containsKey("compId") && SturgeonStatusEnum.REPORT.getKey().equals(sturgeon.getStatus())) {
                    vo.setIsShowCancel(true);
                }
                if ("2".equals(params.get("applyType"))) {
                    vo.setCanAudit(this.getCanAudit(auths, vo));
                }
                sturgeonVos.add(vo);
            }
            PageInfo<SturgeonVo> sturgeonVoPageInfo = new PageInfo<>(sturgeonVos);
            sturgeonVoPageInfo.setPageSize(pageSize);
            sturgeonVoPageInfo.setTotal(sturgeonPageInfo.getTotal());
            sturgeonVoPageInfo.setPageNum(sturgeonPageInfo.getPageNum());
            return PageUtils.getPageUtils(sturgeonVoPageInfo);
        }
        return PageUtils.getPageUtils(new PageInfo(sturgeons));
    }

    private boolean getCanAudit(Set<String> auths, SturgeonVo vo) {
        String status = vo.getStatus();
        //只有状态在上报或者初审通过情况才能审核
        if (!SturgeonStatusDomesticEnum.REPORT.getKey().equals(status) &&
                !SturgeonStatusDomesticEnum.PASS_DIRECTLY.getKey().equals(status)) {
            return false;
        }
        String organizationLevel = SysOwnOrgUtil.getOrganizationLevel();
        String compDistrict = vo.getCompDistrict();
        String compCity = vo.getCompCity();

        if (Constants.REGION_TYPE_MINISTRY.equals(organizationLevel) &&
                SturgeonStatusDomesticEnum.PASS_DIRECTLY.getKey().equals(status)) {
            return true;
        }else if (!SignboardApplyProcessEnum.REPORT.getKey().equals(status)) {
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
            params.put("organizationLevel", organizationLevel);
            params.put("regionLastCode", orgInfo.getRegionLastCode());
        }

    }

    @Override
    @Transactional
    public void report(String id) {
        Sturgeon entity = this.getOneById(id);
        entity.setStatus(SturgeonStatusEnum.REPORT.getKey());
        entity.setApplyTime(new Date());
        if (!StringUtils.hasText(entity.getApplyCode())) {
            String provinceCode = CodeUtil.getProvinceCode(
                    tbCompService.getCombById(UserUtil.getLoginUserOrganizationId()).getRegionInCh().split("-")[0]);
            String codeType = "2".equals(entity.getApplyType()) ?
                    CodeTypeEnum.CITES_APPLY.getKey() : CodeTypeEnum.STURGEON_APPLY.getKey();
            entity.setApplyCode(CodeUtil.getApplyCode(provinceCode, codeType, this.getSequenceNum(provinceCode)));
        }
        sturgeonMapper.updateById(entity);
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
                    new SubmitInstanceVo(DEF_ID, ID_ATTR_NAME, id, MapUtil.getParams(keys, vals)));
        }
    }

    @Override
    public List<SelectVo> getCredentials() {
        Map<String, Object> params = Maps.newHashMapWithExpectedSize(1);
        List<String> roles = UserUtil.getLoginUserRoleCodeList();
        if (!CollectionUtils.isEmpty(roles) && roles.contains(Constants.COMP_USER_ROLE_CODE)) {
            params.put("compId", UserUtil.getLoginUserOrganizationId());
        }
        return sturgeonMapper.listCredentials(params);
    }

    @Override
    public List<SturgeonProcessVo> listSturgeonProcess(String applyId) {
        SturgeonVo sv = this.get(applyId);
        if (Objects.isNull(sv.getApplyTime())) {
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

    /**
     * @param id
     * @return void
     * @description
     * @date 2021/4/2 9:38
     */
    @Override
    public void cancel(String id) {
        String redisKey = RedisUserUtil.validReSubmit("fdpi_cancel", id);
        //根据主键得到申请标识的详情信息
        QueryWrapper<Sturgeon> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        wrapper.eq("del_flag", BoolUtils.N);
        Sturgeon sturgeon = sturgeonMapper.selectOne(wrapper);
        //非空校验
        if (sturgeon == null) {
            throw new SofnException("没有找到相关数据");
        }
        //判断标识状态,已上报状态才可以撤回
        if (sturgeon.getStatus() != null && !sturgeon.getStatus().equals(SturgeonStatusEnum.REPORT.getKey())) {
            throw new SofnException("只有已上报状态才可以撤回");
        }
        //设置更新时间与更新人
        sturgeon.preUpdate();
        //设置新的标识状态
        if ("2".equals(sturgeon.getApplyType())) {
            sturgeon.setStatus(SturgeonStatusDomesticEnum.CANCEL.getKey());
        } else {
            sturgeon.setStatus(SturgeonStatusEnum.CANCEL.getKey());
        }
        //执行更新
        int i = sturgeonMapper.updateById(sturgeon);
        if (i != 1) {
            throw new SofnException("执行撤回失败");
        }
        //操作工作流
        String[] keys = {"status", "person"};
        Object[] vals = {SturgeonStatusEnum.CANCEL.getKey(), SysOwnOrgUtil.getOrganizationName()};
        if (Constants.WORKFLOW.equals(BoolUtils.Y)) {
            workService.backWorkItem(BackWorkItemForm.getInstanceForm(
                    DEF_ID, ID_ATTR_NAME, id, "report", MapUtil.getParams(keys, vals)));
        }
        //增加流程纪录
        SturgeonProcess sturgeonProcess = new SturgeonProcess();
        sturgeonProcess.setApplyId(id);
        sturgeonProcess.setStatus(sturgeon.getStatus());
        sturgeonProcessService.insertSturgeonProcess(sturgeonProcess);
        RedisUserUtil.delRedisKey(redisKey);
    }

    @Override
    @Transactional
    public void updateApplyStatus() {
        QueryWrapper<Sturgeon> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("del_flag", BoolUtils.N).eq("apply_type", "2").
                eq("status", SturgeonStatusDomesticEnum.PASS.getKey());
        List<String> ids = sturgeonMapper.selectList(queryWrapper).
                stream().map(Sturgeon::getId).collect(Collectors.toList());
        for (String id : ids) {
            List<String> sturgeonSubIds = sturgeonSubService.listBySturgeonId(id).
                    stream().map(SturgeonSubVo::getId).collect(Collectors.toList());
            if (CollectionUtils.isEmpty(ssdService.listBySturgeonSubIds(sturgeonSubIds, BoolUtils.N))) {
                UpdateWrapper<Sturgeon> updateWrapper = new UpdateWrapper();
                updateWrapper.eq("id", id).set("status", SturgeonStatusDomesticEnum.PRINT.getKey());
                sturgeonMapper.update(null, updateWrapper);
                //增加流程纪录
                SturgeonProcess sturgeonProcess = new SturgeonProcess();
                sturgeonProcess.setApplyId(id);
                sturgeonProcess.setStatus(SturgeonStatusDomesticEnum.PRINT.getKey());
                sturgeonProcessService.insertSturgeonProcess(sturgeonProcess);
            }
        }
    }

    @Override
    public List<SelectVo> getCitesList() {
        List<String> speNames = CitesEnum.getSelect().stream().map(SelectVo::getVal).collect(Collectors.toList());
        Map<String, String> map = Maps.newHashMapWithExpectedSize(speNames.size());
        String thirdOrg = SysOwnOrgUtil.getOrgInfo().getThirdOrg();
        if (BoolUtils.Y.equals(thirdOrg)) {
            for (int i = 0; i < speNames.size(); i++) {
                map.put(String.format("%02d", i), speNames.get(i));
            }
        } else {
            String compId = UserUtil.getLoginUserOrganizationId();
            List<PapersVo> papersVos = papersService.getCurrentPapers(compId);
            if (!CollectionUtils.isEmpty(papersVos)) {
                List<PapersSpecVo> papersSpecVos = papersSpecService.listByPapersIds(
                        papersVos.stream().map(PapersVo::getId).collect(Collectors.toList()));
                for (int i = 0; i < speNames.size(); i++) {
                    String speName = speNames.get(i);
                    for (PapersSpecVo psv : papersSpecVos) {
                        if (psv.getSpecName().startsWith(speName)) {
                            map.put(String.format("%02d", i), speName);
                        }
                    }
                }
            }
        }
        if (map.isEmpty()) {
            return Collections.EMPTY_LIST;
        } else {
            return map.entrySet().stream().map(e -> new SelectVo(e.getKey(), e.getValue())).collect(Collectors.toList());
        }
    }

    @Override
    public String getApplyType(String id) {
        QueryWrapper<Sturgeon> queryWrapper = new QueryWrapper();
        queryWrapper.select("apply_type").eq("id", id);
        return sturgeonMapper.selectOne(queryWrapper).getApplyType();
    }

    @Override
    public void updateProcessStatus(String id, String status) {
        UpdateWrapper<Sturgeon> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("status", status).eq("id", id);
        sturgeonMapper.update(null, updateWrapper);
    }

    @Override
    public List<Sturgeon> listApplyByCompId(String compId) {
        QueryWrapper<Sturgeon> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("comp_id", compId);
        return sturgeonMapper.selectList(queryWrapper);
    }

    /**
     * 审核
     */
    private void audit(String id, String status, String opinion) {
        QueryWrapper<Sturgeon> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ID", id).eq("DEL_FLAG", BoolUtils.N);
        Sturgeon sturgeon = sturgeonMapper.selectOne(queryWrapper);
        if (Objects.isNull(sturgeon)) {
            throw new SofnException("待审核的数据不存在");
        }
        sturgeon.preUpdate();
        sturgeon.setStatus(status);

        SturgeonProcess sturgeonProcess = new SturgeonProcess();
        sturgeonProcess.setApplyId(id);
        sturgeonProcess.setStatus(status);

        if (StringUtils.hasText(opinion)) {
            sturgeon.setOpinion(opinion);
            sturgeonProcess.setAdvice(opinion);
        }
        sturgeonMapper.updateById(sturgeon);
        if (Constants.WORKFLOW.equals(BoolUtils.N))
            sturgeonProcessService.insertSturgeonProcess(sturgeonProcess);

    }


}
