package com.sofn.fdpi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sofn.common.exception.SofnException;
import com.sofn.common.model.Result;
import com.sofn.common.service.BaseService;
import com.sofn.common.utils.*;
import com.sofn.fdpi.constants.Constants;
import com.sofn.fdpi.enums.*;
import com.sofn.fdpi.mapper.ChangeRecordMapper;
import com.sofn.fdpi.mapper.ChangeRecordProcessMapper;
import com.sofn.fdpi.model.*;
import com.sofn.fdpi.service.ChangeRecordService;
import com.sofn.fdpi.service.PapersYearInspectService;
import com.sofn.fdpi.service.TbCompService;
import com.sofn.fdpi.service.TbDepartmentService;
import com.sofn.fdpi.sysapi.SysRegionApi;
import com.sofn.fdpi.sysapi.WorkApi;
import com.sofn.fdpi.sysapi.bean.*;
import com.sofn.fdpi.util.*;
import com.sofn.fdpi.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Auther: wenjunyun
 * @Date: 2019/12/17 14:44
 */
@Slf4j
@Service
public class ChangeRecordServiceImpl extends BaseService<ChangeRecordMapper, ChangeRecord> implements ChangeRecordService {


    @Autowired(required = false)
    ChangeRecordMapper changeRecordMapper;
    @Autowired(required = false)
    ChangeRecordProcessMapper changeRecordProcessMapper;
    @Autowired(required = false)
    private SysRegionApi sysRegionApi;
    @Autowired(required = false)
    private WorkApi workApi;
    @Autowired
    private PapersYearInspectService papersYearInspectService;
    @Resource
    private TbDepartmentService tbDepartmentService;
    @Autowired
    @Lazy
    private TbCompService tbCompService;
    private static final String DEF_ID = "fdpichange:fdpichange";
    private static final String ID_ATTR_NAME = "dataId";
    private static final String TARGET_ACT_DEFID = "fdpichange_report";

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveChangeRec(ChangeRecord changeRecord, ChangeRecordProcess changeRecordProcess) {
        String redisKey = RedisUserUtil.validReSubmit("fdpi_change_save", 15l);
        if (ChangeStatusEnum.REPORT.getKey().equals(changeRecord.getChangeStatus())) {
            //处理申请单号
            String applyCode = changeRecord.getApplyCode();
            if (applyCode == null || applyCode.isEmpty()) {
                //类型 物种变更 类型为03
                String type = "03";
                //省份
                String provinceName;
                String compId = UserUtil.getLoginUserOrganizationId();
                provinceName = tbCompService.getById(compId).getRegionInCh().split("-")[0];
                //循序号  查询当天的变更申请数量 顺序号为6位
                String changDate = DateUtils.format(new Date(), DateUtils.DATE_PATTERN);
                String startTime = changDate + " 00:00:00";
                String endTime = changDate + " 23:59:59";
                int applyCount = changeRecordMapper.getApplyNum(startTime, endTime);
                applyCode = CodeUtil.getApplyCode(
                        CodeUtil.getProvinceCode(provinceName), type, String.format("%06d", (applyCount + 1)));
                changeRecord.setApplyCode(applyCode);
                changeRecordProcess.setApplyCode(applyCode);
            }
        }

        changeRecordMapper.save(changeRecord);

        SysFileManageForm sf = new SysFileManageForm();
        sf.setFileName(changeRecord.getFileName());
        sf.setIds(changeRecord.getFileId());
        sf.setSystemId("fdpi");
        sf.setRemark("物种变更合同");
        sf.setFileState("Y");
        sysRegionApi.activationFile(sf);

        //        如果是上报加入到流程中去
        if (ChangeStatusEnum.REPORT.getKey().equals(changeRecord.getChangeStatus())) {
            List<CheckVo> changeRecords = changeRecordMapper.checkOnProcess(changeRecord.getChangeCompany(), changeRecord.getSpeciesId());
            if (!CollectionUtils.isEmpty(changeRecords)) {
                throw new SofnException("变更物种正在执行物种转移的流程等待审核完成后再进行申请");
            }
            changeRecordProcessMapper.saveChangeRecordProcess(changeRecordProcess);
            if (Constants.WORKFLOW.equals(BoolUtils.Y)) {
                String[] keys = {"status", "person"};
                Object[] vals = {changeRecord.getChangeStatus(), UserUtil.getLoginUser().getNickname()};
                Result<String> stringResult = workApi.startChainProcess(new SubmitInstanceVo(DEF_ID, ID_ATTR_NAME, changeRecord.getId(),
                        MapUtil.getParams(keys, vals)));
                if (!Result.CODE.equals(stringResult.getCode())) {
                    throw new SofnException("加入流程失败");
                }
            }
        }
        RedisUserUtil.delRedisKey(redisKey);
    }

    @Override
    public List<SpeciesSelect> listSpeciesSelect(Map map) {
        List<SpeciesSelect> speciesSelects = changeRecordMapper.listSpeciesSelect(map);
        if ("1".equals(map.get("type"))) {
            speciesSelects.forEach(o -> {
                Integer reduceNum = this.getReportNum((String) map.get("companyId"), o.getSpeciesId());
                if (reduceNum == null) {
                    reduceNum = 0;
                }
                Integer num = Integer.parseInt(o.getSpeciesNum()) - reduceNum;
                o.setOperableNum(num.toString());
            });
        } else if ("2".equals(map.get("type"))) {
            speciesSelects.forEach(o -> {
                Integer reduceNum = this.getReportNumForTrans((String) map.get("companyId"), o.getSpeciesId());
                if (reduceNum == null) {
                    reduceNum = 0;
                }
                Integer num = Integer.parseInt(o.getSpeciesNum()) - reduceNum;
                o.setOperableNum(num.toString());
            });
        } else {

        }
        return speciesSelects;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateChangeRecord(ChangeRecord changeRecord, ChangeRecordProcess changeRecordProcess) {
        String redisKey = RedisUserUtil.validReSubmit("fdpi_change_update", changeRecord.getId());
        Map map = new HashMap();
        map.put("id", changeRecord.getId());
        ChangeRecordDetailVO old = changeRecordMapper.getChangeRecordDetailById(map);
        changeRecordProcess.setApplyCode(old.getApplyCode());
        //保存变更记录表  ---
        //处理申请单号
        if (ChangeStatusEnum.REPORT.getKey().equals(changeRecord.getChangeStatus())) {
            String applyCode = changeRecord.getApplyCode();
            if (applyCode == null || applyCode.isEmpty()) {
                //类型 物种变更 类型为03
                String type = "03";
                //省份
                String provinceName;
                String compId = UserUtil.getLoginUserOrganizationId();
                provinceName = tbCompService.getById(compId).getRegionInCh().split("-")[0];
                //循序号  查询当天的变更申请数量 顺序号为6位
                String changDate = DateUtils.format(new Date(), DateUtils.DATE_PATTERN);
                String startTime = changDate + " 00:00:00";
                String endTime = changDate + " 23:59:59";
                int applyCount = changeRecordMapper.getApplyNum(startTime, endTime);
                applyCode = CodeUtil.getApplyCode(CodeUtil.getProvinceCode(provinceName),
                        CodeTypeEnum.SPE_CHANGE.getKey(), String.format("%06d", (applyCount + 1)));
                changeRecord.setApplyCode(applyCode);
                changeRecordProcess.setApplyCode(applyCode);
            }
        }
        changeRecordMapper.updateChangeRecord(changeRecord);

        String fileId = changeRecord.getFileId();
        String oldFileId = old.getFileId();
        // 有新文件id 并且不等于旧文件id 则需要激活文件
        if (StringUtils.isNotBlank(fileId) && !fileId.equals(oldFileId)) {
            SysFileManageForm sf = new SysFileManageForm();
            sf.setFileName(changeRecord.getFileName());
            sf.setIds(changeRecord.getFileId());
            sf.setSystemId("fdpi");
            sf.setFileState("Y");
            sf.setRemark("物种变更合同");
            sysRegionApi.activationFile(sf);
            //有旧文件id 并且不等于新文件id 则需要删除旧文件
            if (StringUtils.isNotBlank(oldFileId)) {
                sysRegionApi.delFile(oldFileId);
            }
        }

        //保存变更记录操作记录表 --------此处要修改
        changeRecordProcessMapper.saveChangeRecordProcess(changeRecordProcess);
        //       --------------------------
        //修改企业物种库存表,并且根据变更原因进行增加或者减少处理数目的正负.同时,根据变更原因来进行企业物种库存操作情况表数据处理,也进行保存
        Map map_companySpeciesProcess = new HashMap();

        //查询以前库存情况

        ChangeRecordDetailVO changeRecordDetailVO = changeRecordMapper.getChangeRecordDetailById(map);

        //        如果是上报加入到流程中去
        if (ChangeStatusEnum.REPORT.getKey().equals(changeRecordDetailVO.getChangeStatus())) {
            List<CheckVo> changeRecords = changeRecordMapper.checkOnProcess(changeRecordDetailVO.getCompanyId(), changeRecordDetailVO.getSpeciesId());
            if (!CollectionUtils.isEmpty(changeRecords)) {
                throw new SofnException("变更物种正在执行物种转移的流程等待审核完成后再进行申请");
            }
            if (Constants.WORKFLOW.equals(BoolUtils.Y)) {
                String[] keys = {"status", "person"};
                Object[] vals = {changeRecordDetailVO.getChangeStatus(), UserUtil.getLoginUser().getNickname()};
                Result<String> stringResult = workApi.startChainProcess(new SubmitInstanceVo(DEF_ID, ID_ATTR_NAME, changeRecord.getId(),
                        MapUtil.getParams(keys, vals)));
                if (!Result.CODE.equals(stringResult.getCode())) {
                    throw new SofnException("加入流程失败");
                }
            }
        }

        //如果本次操作是复审通过,则进行数目的变化操作,--并进行提交流程
        if (ChangeStatusEnum.PASS.getKey().equals(changeRecordDetailVO.getChangeStatus())) {
            //1：进口；2：出口；3：自繁；4：死亡；5：救护；6：放生;7，购买，8：销售；9：借入；10:借出；11：捐赠
            //1,3,5,7,9需要增加该企业对应物种的库存;2,4,6,8,10,11需要减少该企业对应的物种
            Map map_companySpecies = new HashMap();
            map_companySpeciesProcess.put("beforeNum", changeRecordDetailVO.getSpeciesNum());
            if (Arrays.asList("1", "3", "5", "7", "9").contains(changeRecordDetailVO.getChangeReason())) {
                map_companySpecies.put("changeNum", changeRecordDetailVO.getChangeNum());
                map_companySpeciesProcess.put("importMark", "入库");
                map_companySpeciesProcess.put("changeNum", changeRecordDetailVO.getChangeNum());
                map_companySpeciesProcess.put("afterNum", changeRecordDetailVO.getSpeciesNum() + changeRecordDetailVO.getChangeNum());
            }
            if (Arrays.asList("2", "4", "6", "8", "10", "11").contains(changeRecordDetailVO.getChangeReason())) {
                if (changeRecordDetailVO.getSpeciesNum() - changeRecordDetailVO.getChangeNum() < 0) {
                    throw new SofnException("库存不足，操作失败！");
                }
                map_companySpecies.put("changeNum", Integer.parseInt("-" + changeRecordDetailVO.getChangeNum()));
                map_companySpeciesProcess.put("importMark", "出库");
                map_companySpeciesProcess.put("changeNum", Integer.parseInt("-" + changeRecordDetailVO.getChangeNum()));
                map_companySpeciesProcess.put("afterNum", changeRecordDetailVO.getSpeciesNum() - changeRecordDetailVO.getChangeNum());
            }
            map_companySpecies.put("changeDate", new Date());
            map_companySpecies.put("createUserId", changeRecordDetailVO.getCreateUserId());
            map_companySpecies.put("companyId", changeRecordDetailVO.getCompanyId());
            map_companySpecies.put("speciesId", changeRecordDetailVO.getSpeciesId());
            changeRecordProcessMapper.changeCompanySpecies(map_companySpecies);

            map_companySpeciesProcess.put("id", IdUtil.getUUId());
            map_companySpeciesProcess.put("companyId", changeRecordDetailVO.getCompanyId());
            map_companySpeciesProcess.put("speciesId", changeRecordDetailVO.getSpeciesId());
            map_companySpeciesProcess.put("speNum", changeRecord.getId());
            map_companySpeciesProcess.put("billType", changeRecordDetailVO.getChangeReason());
            map_companySpeciesProcess.put("changeDate", new Date());
            map_companySpeciesProcess.put("lastChangeUserId", changeRecordDetailVO.getCreateUserId());
//             ------此方法    物种变更的操作流程记录  --要修改
            changeRecordProcessMapper.saveCompanySpeciesProcess(map_companySpeciesProcess);
//          -----直属审核通过提交工作项
            if (Constants.WORKFLOW.equals(BoolUtils.Y)) {
                String[] keys = {"status", "person"};
                Object[] vals = {changeRecordDetailVO.getChangeStatus(), SysOwnOrgUtil.getOrganizationName()};
                Result<String> stringResult = workApi.completeWorkItem(new SubmitInstanceVo(DEF_ID,
                        ID_ATTR_NAME, changeRecord.getId(),
                        MapUtil.getParams(keys, vals)));
                if (!Result.CODE.equals(stringResult.getCode())) {
                    throw new SofnException("加入流程失败");
                }
            }
        }


        //          如果状态为审核不通过那么 回退到上一节点
        if (ChangeStatusEnum.RETURN.getKey().equals(changeRecordDetailVO.getChangeStatus())) {
            if (Constants.WORKFLOW.equals(BoolUtils.Y)) {
                String[] keys = {"status", "person", "opinion"};
                Object[] vals = {changeRecordDetailVO.getChangeStatus(), SysOwnOrgUtil.getOrganizationName(), changeRecordDetailVO.getFirstOpnion()};
                Result<String> stringResult = workApi.backWorkItem(BackWorkItemForm.
                        getInstanceForm(DEF_ID, ID_ATTR_NAME,
                                changeRecord.getId(), TARGET_ACT_DEFID, MapUtil.getParams(keys, vals)));
                if (!Result.CODE.equals(stringResult.getCode())) {
                    throw new SofnException("加入流程失败");
                }
            }
        }
        RedisUserUtil.delRedisKey(redisKey);
    }

    @Override
    public List<ChangeType> listChangeType() {
        return changeRecordMapper.listChangeType();
    }

    @Override
    public ChangeRecordCompanyVO getCompanyByIdOrName(Map map) {
        List<ChangeRecordCompanyVO> list = changeRecordMapper.getCompanyByIdOrName(map);

        if (list == null || list.size() == 0) {
            return null;
        } else {
            //替换企业类型字典
            Map<String, String> compTypeMap = sysRegionApi.getDictListByType("fdpi_comp_type").getData().
                    stream().collect(Collectors.toMap(SysDict::getDictcode, SysDict::getDictname));
            ChangeRecordCompanyVO returnVo = list.get(0);
            String compType = returnVo.getCompType();
            if (compType != null && !compType.isEmpty()) {
                String typeName = compTypeMap.get(compType);
                returnVo.setCompType(typeName);
            }
            return returnVo;
        }
    }

    @Override
    public ChangeRecordDetailVO getChangeRecordDetailById(Map map) {
        return changeRecordMapper.getChangeRecordDetailById(map);
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
        }
    }

    @Override
    public PageUtils<ChangeRecordDetailVO> listChangeRecordDetailVO(Map map, Integer pageNo, Integer pageSize) {
        this.perfectParams(map);
        PageHelper.offsetPage(pageNo, pageSize);
        List<ChangeRecordDetailVO> list = changeRecordMapper.listChangeRecordDetail2(map);
        Set<String> auths = tbDepartmentService.listAllAuth(DepartmentTypeEnum.SIGNBOARD.getKey());
        for (ChangeRecordDetailVO vo : list) {
            vo.setCanAudit(this.getCanAudit(auths, vo));
            //如果当前请求角色是第三方企业，则判断是否显示撤回按钮,默认false不显示
            if (map.containsKey("compId")) {
                if (ChangeStatusEnum.REPORT.getKey().equals(vo.getChangeStatus())) {
                    vo.setIsShowCancel(true);
                }
            }
        }
        PageInfo<ChangeRecordDetailVO> listPageInfo = new PageInfo<ChangeRecordDetailVO>(list);
        listPageInfo.setList(list);
        return PageUtils.getPageUtils(listPageInfo);
    }

    private boolean getCanAudit(Set<String> auths, ChangeRecordDetailVO vo) {
        String changeStatus = vo.getChangeStatus();
        //变更申请状态0:保存；1：上报；2：初审退回；3初审通过；4、撤回
        //只有状态在上报情况才能审核
        if (!"1".equals(changeStatus)) {
            return false;
        }
        String organizationLevel = SysOwnOrgUtil.getOrganizationLevel();
        String compDistrict = vo.getCompDistrict();
        String compCity = vo.getCompCity();

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
    public PageUtils<ChangeRecordProcessVo> listProcess(Map<String, Object> map, Integer pageNo, Integer pageSize) {
        PageHelper.offsetPage(pageNo, pageSize);
        List<ChangeRecordProcessVo> list = changeRecordProcessMapper.listProcess(map);
        PageInfo<ChangeRecordProcessVo> listPageInfo = new PageInfo<ChangeRecordProcessVo>(list);
        listPageInfo.setList(list);
        return PageUtils.getPageUtils(listPageInfo);
    }

    @Override
    public PageUtils<ChangeRecordProcessVo> listProcessByassembly(Map<String, Object> map, Integer pageNo, Integer pageSize) {
        List<ChangeRecord> assemblyId = changeRecordMapper.getAssemblyId(map);
        Map<String, ChangeRecord> maps = assemblyId.stream().collect(Collectors.toMap(ChangeRecord::getId, a -> a, (k1, k2) -> k1));
        List<String> dataIds = assemblyId.stream().map(ChangeRecord::getId).collect(Collectors.toList());
        ActivityDataParamsVo activityDataParamsVo = ActivityDataParamsVo.getInstance(DEF_ID, ID_ATTR_NAME,
                dataIds, map, pageNo, pageSize);
        PageUtils<ActivityDataVo> activityDataVoPageUtils = WorkUtil.getPageUtilsByParams(activityDataParamsVo);
        List<ActivityDataVo> activityDataVos = activityDataVoPageUtils.getList();
        List<ChangeRecordProcessVo> changeRecordProcessVos = this.activityDataVos2ChangeRecordProcessVo(activityDataVos, maps);
        PageInfo<ChangeRecordProcessVo> changeRecordProcessVosPageInfo = new PageInfo<>(changeRecordProcessVos);
        changeRecordProcessVosPageInfo.setTotal(activityDataVoPageUtils.getTotalCount());
        changeRecordProcessVosPageInfo.setPageSize(activityDataVoPageUtils.getPageSize());
        changeRecordProcessVosPageInfo.setPageNum(activityDataVoPageUtils.getCurrPage());
        return PageUtils.getPageUtils(changeRecordProcessVosPageInfo);
    }

    private List<ChangeRecordProcessVo> activityDataVos2ChangeRecordProcessVo(
            List<ActivityDataVo> activityDataVos, Map<String, ChangeRecord> map) {
        List<ChangeRecordProcessVo> changeRecordProcessVos = Collections.EMPTY_LIST;
        if (!CollectionUtils.isEmpty(activityDataVos)) {
            changeRecordProcessVos = Lists.newArrayListWithCapacity(activityDataVos.size());
            for (ActivityDataVo activityDataVo : activityDataVos) {
                ChangeRecordProcessVo changerecordprocessvo = new ChangeRecordProcessVo();
                ChangeRecord transfervo = map.get(activityDataVo.getUnitValue());
                changerecordprocessvo.setChangeDate(transfervo.getChangeDate());
                changerecordprocessvo.setSpeName(transfervo.getSpeciesId());
                changerecordprocessvo.setChangeReason(transfervo.getChangeReason());
                changerecordprocessvo.setApplyCode(transfervo.getApplyCode());
                List<ActContextVo> actContextVos = activityDataVo.getActContextVos();
                for (ActContextVo actContextVo : actContextVos) {
                    String dataFieldId = actContextVo.getDataFieldId();
                    String value = actContextVo.getValue();
                    if (org.springframework.util.StringUtils.hasText(value)) {
                        if ("status".equals(dataFieldId)) {
                            changerecordprocessvo.setContent(ChangeStatusEnum.getVal(value));
                        } else if ("person".equals(dataFieldId)) {
                            changerecordprocessvo.setOpUserId(value);
                        } else if ("opinion".equals(dataFieldId)) {
                            changerecordprocessvo.setAdvice(value);
                        }
                    }
                }
                changerecordprocessvo.setOpTime(
                        DateUtils.stringToDate(activityDataVo.getActivityCompleteTime(), DateUtils.DATE_TIME_PATTERN));
                changeRecordProcessVos.add(changerecordprocessvo);

            }
        }
        return changeRecordProcessVos;
    }

    @Override
    public List<ChangeRecordProcessVo> getProcess(Map<String, Object> map) {
        List<ChangeRecordProcessVo> list = changeRecordProcessMapper.getProcess(map);
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteChangeRecord(ChangeRecord changeRecord, ChangeRecordProcess changeRecordProcess) {

        //保存变更记录表
        changeRecordMapper.updateChangeRecord(changeRecord);
        //保存变更记录操作记录表
        changeRecordProcessMapper.saveChangeRecordProcess(changeRecordProcess);
    }

    /**
     * 企业查询列表-》详情-》物种变更列表
     * wuXY
     * 2020-11-5 14:20:47
     *
     * @param compId    企业id
     * @param inspectId 年审id
     * @param speciesId 物种id
     * @return 物种变更分页列表
     */
    @Override
    public PageUtils<List<ChangeRecVoInPapersYearVo>> listPageForChangeRecBySpecies(String compId, String inspectId, String speciesId, Integer pageNo, Integer pageSize) {
        Date dateNow = new Date();
        Map<String, Object> speciesMap = Maps.newHashMap();
        List<ChangeRecVoInPapersYearVo> changeRecList = null;
        PageHelper.offsetPage(pageNo, pageSize);
        speciesMap.put("speciesId", speciesId);
        if (StringUtils.isBlank(inspectId)) {
            speciesMap.put("compId", compId);
            speciesMap.put("endTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dateNow));
            //获取物种变更记录
            changeRecList = changeRecordMapper.listByConditionForInspect(speciesMap);
            SpeChangeUtil.replaceSpeChangeType(changeRecList);

        } else {
            PapersYearInspect PapersYearInspect = papersYearInspectService.getById(inspectId);
            if (PapersYearInspect == null) {
                return null;
            }
            //获取该年审中以前物种变更记录
            speciesMap.put("inspectId", inspectId);
            speciesMap.put("endTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").
                    format(PapersYearInspect.getCreateTime()));

            changeRecList = changeRecordMapper.listByInspectConditionForInspect(speciesMap);

//            变更原因映射
            Map<String, String> mapReason = sysRegionApi.getDictListByType("fdpi_changeType").getData().
                    stream().collect(Collectors.toMap(SysDict::getDictcode, SysDict::getDictname));
            Map<String, String> collect = ComeStockFlowEnum.getSelect().
                    stream().collect(Collectors.toMap(SelectVo::getKey, SelectVo::getVal));
            mapReason.putAll(collect);
            changeRecList.forEach(o -> {
                o.setChangeReason(mapReason.get(o.getChangeReason()));
            });

        }
        PageInfo<ChangeRecVoInPapersYearVo> pageInfo = new PageInfo<>(changeRecList);

        return PageUtils.getPageUtils(pageInfo);
    }

    @Override
    public Integer getReportNum(String compId, String speId) {
        return changeRecordMapper.getReportNum(compId, speId);
    }

    @Override
    public Integer getReportNumForTrans(String compId, String speId) {
        return changeRecordMapper.getReportTranNum(compId, speId);
    }

    /**
     * @param id 主键
     * @return void
     * @author wg
     * @description 物种变更撤回
     * @date 2021/4/9 14:47
     */
    @Override
    public void cancel(String id) {
        //根据主键查询详情
        QueryWrapper<ChangeRecord> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id).eq("del_flag", BoolUtils.N);
        wrapper.select("id", "change_status", "apply_code");
        ChangeRecord changeRecord = changeRecordMapper.selectOne(wrapper);
        //非空校验
        if (changeRecord == null) {
            throw new SofnException("没有找到相关数据");
        }
        //如果当前是已上报才可以撤回
        if (changeRecord.getChangeStatus() != null && !changeRecord.getChangeStatus().equals(ChangeStatusEnum.REPORT.getKey())) {
            throw new SofnException("只有已上报状态才可以撤回");
        }
        //设置当前操作人、操作时间
        changeRecord.preUpdate();
        //设置当前标签纸的状态
        changeRecord.setChangeStatus(ChangeStatusEnum.CANCEL.getKey());
        //执行更新
        int i = changeRecordMapper.updateById(changeRecord);
        if (i != 1) {
            throw new SofnException("撤回失败");
        }
        ChangeRecordProcess changeRecordProcess = new ChangeRecordProcess();
        changeRecordProcess.setApplyCode(changeRecord.getApplyCode());
        changeRecordProcess.setId(IdUtil.getUUId());
        changeRecordProcess.setChangeRecordId(id);
        changeRecordProcess.setConTime(new Date());
        changeRecordProcess.setOpUserId(UserUtil.getLoginUser().getNickname());
        changeRecordProcess.setContent("用户上报后撤回!");
        changeRecordProcess.setAdvice(ChangeStatusEnum.CANCEL.getVal());
        changeRecordProcessMapper.saveChangeRecordProcess(changeRecordProcess);
        if (Constants.WORKFLOW.equals(BoolUtils.Y)) {
            //操作工作流
            String[] keys = {"status", "person"};
            Object[] vals = {ChangeStatusEnum.CANCEL.getKey(), SysOwnOrgUtil.getOrganizationName()};
            workApi.backWorkItem(BackWorkItemForm.getInstanceForm(
                    DEF_ID, ID_ATTR_NAME, id, TARGET_ACT_DEFID, MapUtil.getParams(keys, vals)));
        }
    }

    @Override
    public List<ChangeRecord> listRecordByCompId(String compId) {
        QueryWrapper<ChangeRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("change_company", compId);
        queryWrapper.select("id", "species_id", "change_company");
        return changeRecordMapper.selectList(queryWrapper);
    }

    @Override
    public List<ChangeRecordProcessVo> getProcessByassembly(Map<String, Object> map) {
//        查询该条记录是否进入了流程 不是返回空是的话就在流程中查出来
        String id = (String) map.get("changeRecordId");
        ChangeRecord changeRecord = changeRecordMapper.getChangeRecordbyId(id);

        List<ChangeRecordProcessVo> list = new ArrayList<>();
        if (changeRecord != null) {
            List<Map<String, Object>> changeRecordId = WorkUtil.getProcesslist(DEF_ID, ID_ATTR_NAME, (String) map.get("changeRecordId"));
            changeRecordId.forEach(o -> {
                ChangeRecordProcessVo ch = new ChangeRecordProcessVo();
                ch.setOpUserId((String) o.get("person"));
                ch.setOpTime((Date) o.get("createTime"));
                ch.setContent(ChangeStatusEnum.RETURN.getKey().equals(o.get("status")) ?
                        (String) o.get("opinion") : ChangeStatusEnum.getVal((String) o.get("status")));
                list.add(ch);
            });

        }
//

        return list;
    }

}
