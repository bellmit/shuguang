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
            //??????????????????
            String applyCode = changeRecord.getApplyCode();
            if (applyCode == null || applyCode.isEmpty()) {
                //?????? ???????????? ?????????03
                String type = "03";
                //??????
                String provinceName;
                String compId = UserUtil.getLoginUserOrganizationId();
                provinceName = tbCompService.getById(compId).getRegionInCh().split("-")[0];
                //?????????  ????????????????????????????????? ????????????6???
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
        sf.setRemark("??????????????????");
        sf.setFileState("Y");
        sysRegionApi.activationFile(sf);

        //        ????????????????????????????????????
        if (ChangeStatusEnum.REPORT.getKey().equals(changeRecord.getChangeStatus())) {
            List<CheckVo> changeRecords = changeRecordMapper.checkOnProcess(changeRecord.getChangeCompany(), changeRecord.getSpeciesId());
            if (!CollectionUtils.isEmpty(changeRecords)) {
                throw new SofnException("?????????????????????????????????????????????????????????????????????????????????");
            }
            changeRecordProcessMapper.saveChangeRecordProcess(changeRecordProcess);
            if (Constants.WORKFLOW.equals(BoolUtils.Y)) {
                String[] keys = {"status", "person"};
                Object[] vals = {changeRecord.getChangeStatus(), UserUtil.getLoginUser().getNickname()};
                Result<String> stringResult = workApi.startChainProcess(new SubmitInstanceVo(DEF_ID, ID_ATTR_NAME, changeRecord.getId(),
                        MapUtil.getParams(keys, vals)));
                if (!Result.CODE.equals(stringResult.getCode())) {
                    throw new SofnException("??????????????????");
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
        //?????????????????????  ---
        //??????????????????
        if (ChangeStatusEnum.REPORT.getKey().equals(changeRecord.getChangeStatus())) {
            String applyCode = changeRecord.getApplyCode();
            if (applyCode == null || applyCode.isEmpty()) {
                //?????? ???????????? ?????????03
                String type = "03";
                //??????
                String provinceName;
                String compId = UserUtil.getLoginUserOrganizationId();
                provinceName = tbCompService.getById(compId).getRegionInCh().split("-")[0];
                //?????????  ????????????????????????????????? ????????????6???
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
        // ????????????id ????????????????????????id ?????????????????????
        if (StringUtils.isNotBlank(fileId) && !fileId.equals(oldFileId)) {
            SysFileManageForm sf = new SysFileManageForm();
            sf.setFileName(changeRecord.getFileName());
            sf.setIds(changeRecord.getFileId());
            sf.setSystemId("fdpi");
            sf.setFileState("Y");
            sf.setRemark("??????????????????");
            sysRegionApi.activationFile(sf);
            //????????????id ????????????????????????id ????????????????????????
            if (StringUtils.isNotBlank(oldFileId)) {
                sysRegionApi.delFile(oldFileId);
            }
        }

        //????????????????????????????????? --------???????????????
        changeRecordProcessMapper.saveChangeRecordProcess(changeRecordProcess);
        //       --------------------------
        //???????????????????????????,?????????????????????????????????????????????????????????????????????.??????,????????????????????????????????????????????????????????????????????????,???????????????
        Map map_companySpeciesProcess = new HashMap();

        //????????????????????????

        ChangeRecordDetailVO changeRecordDetailVO = changeRecordMapper.getChangeRecordDetailById(map);

        //        ????????????????????????????????????
        if (ChangeStatusEnum.REPORT.getKey().equals(changeRecordDetailVO.getChangeStatus())) {
            List<CheckVo> changeRecords = changeRecordMapper.checkOnProcess(changeRecordDetailVO.getCompanyId(), changeRecordDetailVO.getSpeciesId());
            if (!CollectionUtils.isEmpty(changeRecords)) {
                throw new SofnException("?????????????????????????????????????????????????????????????????????????????????");
            }
            if (Constants.WORKFLOW.equals(BoolUtils.Y)) {
                String[] keys = {"status", "person"};
                Object[] vals = {changeRecordDetailVO.getChangeStatus(), UserUtil.getLoginUser().getNickname()};
                Result<String> stringResult = workApi.startChainProcess(new SubmitInstanceVo(DEF_ID, ID_ATTR_NAME, changeRecord.getId(),
                        MapUtil.getParams(keys, vals)));
                if (!Result.CODE.equals(stringResult.getCode())) {
                    throw new SofnException("??????????????????");
                }
            }
        }

        //?????????????????????????????????,??????????????????????????????,--?????????????????????
        if (ChangeStatusEnum.PASS.getKey().equals(changeRecordDetailVO.getChangeStatus())) {
            //1????????????2????????????3????????????4????????????5????????????6?????????;7????????????8????????????9????????????10:?????????11?????????
            //1,3,5,7,9??????????????????????????????????????????;2,4,6,8,10,11????????????????????????????????????
            Map map_companySpecies = new HashMap();
            map_companySpeciesProcess.put("beforeNum", changeRecordDetailVO.getSpeciesNum());
            if (Arrays.asList("1", "3", "5", "7", "9").contains(changeRecordDetailVO.getChangeReason())) {
                map_companySpecies.put("changeNum", changeRecordDetailVO.getChangeNum());
                map_companySpeciesProcess.put("importMark", "??????");
                map_companySpeciesProcess.put("changeNum", changeRecordDetailVO.getChangeNum());
                map_companySpeciesProcess.put("afterNum", changeRecordDetailVO.getSpeciesNum() + changeRecordDetailVO.getChangeNum());
            }
            if (Arrays.asList("2", "4", "6", "8", "10", "11").contains(changeRecordDetailVO.getChangeReason())) {
                if (changeRecordDetailVO.getSpeciesNum() - changeRecordDetailVO.getChangeNum() < 0) {
                    throw new SofnException("??????????????????????????????");
                }
                map_companySpecies.put("changeNum", Integer.parseInt("-" + changeRecordDetailVO.getChangeNum()));
                map_companySpeciesProcess.put("importMark", "??????");
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
//             ------?????????    ?????????????????????????????????  --?????????
            changeRecordProcessMapper.saveCompanySpeciesProcess(map_companySpeciesProcess);
//          -----?????????????????????????????????
            if (Constants.WORKFLOW.equals(BoolUtils.Y)) {
                String[] keys = {"status", "person"};
                Object[] vals = {changeRecordDetailVO.getChangeStatus(), SysOwnOrgUtil.getOrganizationName()};
                Result<String> stringResult = workApi.completeWorkItem(new SubmitInstanceVo(DEF_ID,
                        ID_ATTR_NAME, changeRecord.getId(),
                        MapUtil.getParams(keys, vals)));
                if (!Result.CODE.equals(stringResult.getCode())) {
                    throw new SofnException("??????????????????");
                }
            }
        }


        //          ???????????????????????????????????? ?????????????????????
        if (ChangeStatusEnum.RETURN.getKey().equals(changeRecordDetailVO.getChangeStatus())) {
            if (Constants.WORKFLOW.equals(BoolUtils.Y)) {
                String[] keys = {"status", "person", "opinion"};
                Object[] vals = {changeRecordDetailVO.getChangeStatus(), SysOwnOrgUtil.getOrganizationName(), changeRecordDetailVO.getFirstOpnion()};
                Result<String> stringResult = workApi.backWorkItem(BackWorkItemForm.
                        getInstanceForm(DEF_ID, ID_ATTR_NAME,
                                changeRecord.getId(), TARGET_ACT_DEFID, MapUtil.getParams(keys, vals)));
                if (!Result.CODE.equals(stringResult.getCode())) {
                    throw new SofnException("??????????????????");
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
            //????????????????????????
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
     * ????????????????????????
     */
    private void perfectParams(Map<String, Object> params) {
        OrganizationInfo orgInfo = SysOwnOrgUtil.getOrgInfo();
        //??????????????????????????????????????????
        RedisUserUtil.validLoginUser(orgInfo);
        String id = orgInfo.getId();
        String thirdOrg = orgInfo.getThirdOrg();
        String regionLastCode = orgInfo.getRegionLastCode();
        // ???????????????
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
            //??????????????????????????????????????????????????????????????????????????????,??????false?????????
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
        //??????????????????0:?????????1????????????2??????????????????3???????????????4?????????
        //???????????????????????????????????????
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

        //?????????????????????
        changeRecordMapper.updateChangeRecord(changeRecord);
        //?????????????????????????????????
        changeRecordProcessMapper.saveChangeRecordProcess(changeRecordProcess);
    }

    /**
     * ??????????????????-?????????-?????????????????????
     * wuXY
     * 2020-11-5 14:20:47
     *
     * @param compId    ??????id
     * @param inspectId ??????id
     * @param speciesId ??????id
     * @return ????????????????????????
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
            //????????????????????????
            changeRecList = changeRecordMapper.listByConditionForInspect(speciesMap);
            SpeChangeUtil.replaceSpeChangeType(changeRecList);

        } else {
            PapersYearInspect PapersYearInspect = papersYearInspectService.getById(inspectId);
            if (PapersYearInspect == null) {
                return null;
            }
            //??????????????????????????????????????????
            speciesMap.put("inspectId", inspectId);
            speciesMap.put("endTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").
                    format(PapersYearInspect.getCreateTime()));

            changeRecList = changeRecordMapper.listByInspectConditionForInspect(speciesMap);

//            ??????????????????
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
     * @param id ??????
     * @return void
     * @author wg
     * @description ??????????????????
     * @date 2021/4/9 14:47
     */
    @Override
    public void cancel(String id) {
        //????????????????????????
        QueryWrapper<ChangeRecord> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id).eq("del_flag", BoolUtils.N);
        wrapper.select("id", "change_status", "apply_code");
        ChangeRecord changeRecord = changeRecordMapper.selectOne(wrapper);
        //????????????
        if (changeRecord == null) {
            throw new SofnException("????????????????????????");
        }
        //???????????????????????????????????????
        if (changeRecord.getChangeStatus() != null && !changeRecord.getChangeStatus().equals(ChangeStatusEnum.REPORT.getKey())) {
            throw new SofnException("????????????????????????????????????");
        }
        //????????????????????????????????????
        changeRecord.preUpdate();
        //??????????????????????????????
        changeRecord.setChangeStatus(ChangeStatusEnum.CANCEL.getKey());
        //????????????
        int i = changeRecordMapper.updateById(changeRecord);
        if (i != 1) {
            throw new SofnException("????????????");
        }
        ChangeRecordProcess changeRecordProcess = new ChangeRecordProcess();
        changeRecordProcess.setApplyCode(changeRecord.getApplyCode());
        changeRecordProcess.setId(IdUtil.getUUId());
        changeRecordProcess.setChangeRecordId(id);
        changeRecordProcess.setConTime(new Date());
        changeRecordProcess.setOpUserId(UserUtil.getLoginUser().getNickname());
        changeRecordProcess.setContent("?????????????????????!");
        changeRecordProcess.setAdvice(ChangeStatusEnum.CANCEL.getVal());
        changeRecordProcessMapper.saveChangeRecordProcess(changeRecordProcess);
        if (Constants.WORKFLOW.equals(BoolUtils.Y)) {
            //???????????????
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
//        ??????????????????????????????????????? ????????????????????????????????????????????????
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
