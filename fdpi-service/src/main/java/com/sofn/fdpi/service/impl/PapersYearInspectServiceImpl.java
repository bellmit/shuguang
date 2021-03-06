package com.sofn.fdpi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.sofn.common.exception.SofnException;
import com.sofn.common.model.Result;
import com.sofn.common.service.BaseService;
import com.sofn.common.utils.*;
import com.sofn.fdpi.constants.Constants;
import com.sofn.fdpi.enums.*;
import com.sofn.fdpi.mapper.*;
import com.sofn.fdpi.model.*;
import com.sofn.fdpi.service.*;
import com.sofn.fdpi.sysapi.WorkApi;
import com.sofn.fdpi.sysapi.bean.BackWorkItemForm;
import com.sofn.fdpi.sysapi.bean.SubmitInstanceVo;
import com.sofn.fdpi.util.*;
import com.sofn.fdpi.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service("papersYearInspectService")
public class PapersYearInspectServiceImpl extends BaseService<PapersYearInspectMapper, PapersYearInspect> implements PapersYearInspectService {

    @Autowired
    private PapersYearInspectMapper papersYearInspectMapper;

    @Autowired
    private PapersYearInspectRetailService papersYearInspectRetailService;

    @Autowired
    private PapersYearInspectPRetailService papersYearInspectPRetailService;

    @Autowired
    private ChangeRecordMapper changeRecordMapper;

    @Autowired
    private PapersYearInspectProcessMapper papersYearInspectProcessMapper;

    @Resource
    private PlatformTransactionManager platformTransactionManager;

    @Autowired
    private SignboardMapper signboardMapper;

    @Autowired
    private PapersYearInspectHistoryMapper papersYearInspectHistoryMapper;

    @Autowired
    private WorkApi workApi;

    @Autowired
    @Lazy
    private TbCompService tbCompService;

    @Autowired
    private PapersService papersService;

    @Autowired
    private TbDepartmentService tbDepartmentService;

    //??????id
    private final static String defId = "papers_year_inspect:papers_year_inspect";

    //??????????????????
    private final static String idAttrName = "dataId";

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

    /**
     * ??????????????????????????????????????????
     * wuXY
     * 2020-1-3 14:00:45
     *
     * @param map ????????????
     * @return ??????????????????
     */
    @Override
    public PageUtils<PapersYearInspectVo> listForApply(Map<String, Object> map) {
        this.perfectParams(map);
        PageHelper.offsetPage(Integer.parseInt(map.get("pageNo").toString()), Integer.parseInt(map.get("pageSize").toString()));
        List<PapersYearInspectVo> list = papersYearInspectMapper.listByParams(map);
        Set<String> auths = tbDepartmentService.listAllAuth(DepartmentTypeEnum.PAPER_YEAR_INSPECT.getKey());
        list.forEach(vo -> {
            //????????????????????????????????????????????????????????????????????????????????????????????????
            if (map.containsKey("compId") && DefaultAdviceEnum.REPORT_DEFAULT_ADVICE.getCode().equals(vo.getStatus())) {
                vo.setIsShowCancel(true);
            }
            vo.setCanAudit(this.getCanAudit(auths, vo));
        });

        PageInfo<PapersYearInspectVo> pageInfo = new PageInfo<>(list);
        return PageUtils.getPageUtils(pageInfo);
    }

    private Boolean getCanAudit(Set<String> auths, PapersYearInspectVo vo) {
        String parStatus = vo.getStatus();
        //???????????????????????????????????????
        if (!DefaultAdviceEnum.REPORT_DEFAULT_ADVICE.getCode().equals(parStatus)) {
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

    /**
     * ??????????????????????????????
     * wuXY
     * 2020-1-3 17:03:10
     *
     * @param inspectId ????????????id
     * @return ??????
     */
    @Override
    public PapersYearInspectViewVo getDetailById(String compId, String inspectId) {
        Date dateNow = new Date();
        if (StringUtils.isBlank(inspectId)) {
            //????????????????????????
            //1???????????????????????????????????????????????????
            PapersYearInspectViewVo compInfo = papersYearInspectMapper.getCompanyByComId(compId);
            if (compInfo != null) {
                //?????????????????????????????????
                List<SpecieVoInPapersYear> specieList = papersYearInspectMapper.listSpeciesByCompIdNew(compId);
                if (!CollectionUtils.isEmpty(specieList)) {
                    compInfo.setSpeciesList(specieList);
                }
                //?????????????????????????????????
                List<PapersVoInPapersYear> papersList = papersYearInspectMapper.listPapersInYearByCompId(compId);
                if (!CollectionUtils.isEmpty(papersList)) {
                    compInfo.setPapersList(papersList);
                }
                //??????????????????????????????
                Map<String, Object> inspectMap = Maps.newHashMap();
                inspectMap.put("compId", compId);
                inspectMap.put("endTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dateNow));
//                List<PapersYearInspectProcessVo> inspectRetailList = papersYearInspectMapper.listForCondition(inspectMap);
                List<PapersYearInspectHistoryVo> inspectHistoryList = papersYearInspectMapper.listForInspectHistory(inspectMap);
                if (!CollectionUtils.isEmpty(inspectHistoryList)) {
                    compInfo.setInspectList(inspectHistoryList);
                }
                //????????????????????????
                List<ChangeRecVoInPapersYearVo> changeRecList = changeRecordMapper.listByConditionForInspect(inspectMap);

                //????????????????????????
                SpeChangeUtil.replaceSpeChangeType(changeRecList);

                if (!CollectionUtils.isEmpty(changeRecList)) {
                    compInfo.setSpeciesChangelist(changeRecList);
                }
                return compInfo;
            }

        } else {
            //??????or??????
            PapersYearInspect PapersYearInspect = this.getOneById(inspectId);
            if (PapersYearInspect == null) {
                return null;
            }
            //?????????????????????????????????????????????
            PapersYearInspectViewVo compInfo = papersYearInspectMapper.getInspectById(inspectId);
            //??????????????????????????????
            List<SpecieVoInPapersYear> specieList = papersYearInspectMapper.listSpeciesByIdNew(inspectId);
            if (!CollectionUtils.isEmpty(specieList)) {
                compInfo.setSpeciesList(specieList);
            }
            //????????????????????????????????????
            List<PapersVoInPapersYear> papersList = papersYearInspectMapper.listPapersInYearById(inspectId);
            if (!CollectionUtils.isEmpty(papersList)) {
                compInfo.setPapersList(papersList);
            }
            //????????????????????????????????????
            Map<String, Object> inspectMap = Maps.newHashMap();
            inspectMap.put("compId", PapersYearInspect.getTbCompId());
            inspectMap.put("endTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(PapersYearInspect.getCreateTime()));

//            List<PapersYearInspectProcessVo> inspectList = papersYearInspectRetailService.listForCondition(inspectMap);
            List<PapersYearInspectHistoryVo> inspectHistoryList = papersYearInspectMapper.listForInspectHistory(inspectMap);
            if (!CollectionUtils.isEmpty(inspectHistoryList)) {
                compInfo.setInspectList(inspectHistoryList);
            }

            //??????????????????????????????????????????
            Map<String, Object> speciesChangeMap = Maps.newHashMap();
            speciesChangeMap.put("inspectId", inspectId);
            speciesChangeMap.put("endTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(PapersYearInspect.getCreateTime()));

            List<ChangeRecVoInPapersYearVo> changeList = changeRecordMapper.listByInspectConditionForInspect(speciesChangeMap);

            //????????????????????????
            SpeChangeUtil.replaceSpeChangeType(changeList);

            if (!CollectionUtils.isEmpty(changeList)) {
                compInfo.setSpeciesChangelist(changeList);
            }
            return compInfo;
        }
        return null;
    }


    /**
     * ???????????????-?????????????????????????????????
     * wuXY
     * 2020-1-3 17:03:10
     *
     * @param inspectId ????????????id
     * @return ??????
     */
    @Override
    public PapersYearInspectViewVo getDetailByCompIdAndInspectId(String compId, String inspectId) {
        Date dateNow = new Date();
        if (StringUtils.isBlank(inspectId)) {
            //????????????????????????
            //1???????????????????????????????????????????????????
            PapersYearInspectViewVo compInfo = papersYearInspectMapper.getCompanyByComId(compId);
            if (compInfo != null) {
                //?????????????????????????????????
                List<SpecieVoInPapersYear> specieList = papersYearInspectMapper.listSpeciesByCompIdNew(compId);
                if (!CollectionUtils.isEmpty(specieList)) {
                    compInfo.setSpeciesList(specieList);
                }
                //?????????????????????????????????
                List<PapersVoInPapersYear> papersList = papersYearInspectMapper.listPapersInYearByCompId(compId);
                if (!CollectionUtils.isEmpty(papersList)) {
                    compInfo.setPapersList(papersList);
                }

                //??????????????????????????????
                Map<String, Object> inspectMap = Maps.newHashMap();
                inspectMap.put("compId", compId);
                inspectMap.put("endTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dateNow));
//                List<PapersYearInspectProcessVo> inspectRetailList = papersYearInspectRetailService.listForCondition(inspectMap);
                List<PapersYearInspectHistoryVo> inspectHistoryList = papersYearInspectMapper.listForInspectHistory(inspectMap);
                if (!CollectionUtils.isEmpty(inspectHistoryList)) {
                    compInfo.setInspectList(inspectHistoryList);
                }
                //????????????????????????
                List<ChangeRecVoInPapersYearVo> changeRecList = changeRecordMapper.listByConditionForInspect(inspectMap);

                //????????????????????????
                SpeChangeUtil.replaceSpeChangeType(changeRecList);

                if (!CollectionUtils.isEmpty(changeRecList)) {
                    compInfo.setSpeciesChangelist(changeRecList);
                }
                return compInfo;
            }

        } else {
            //??????or??????
            PapersYearInspect PapersYearInspect = this.getOneById(inspectId);
            if (PapersYearInspect == null) {
                return null;
            }
            //?????????????????????????????????????????????
            PapersYearInspectViewVo compInfo = papersYearInspectMapper.getInspectById(inspectId);
            //????????????????????????????????????
//            List<SpecieVoInPapersYear> specieList = papersYearInspectMapper.listSpeciesById(inspectId);
            //?????????????????????????????????
            List<SpecieVoInPapersYear> specieList = papersYearInspectMapper.listSpeciesByCompIdNew(compId);
            if (!CollectionUtils.isEmpty(specieList)) {
                compInfo.setSpeciesList(specieList);
            }
            //?????????????????????????????????
            List<PapersVoInPapersYear> papersList = papersYearInspectMapper.listPapersInYearByCompId(compId);
            if (!CollectionUtils.isEmpty(papersList)) {
                compInfo.setPapersList(papersList);
            }

            //????????????????????????????????????
            Map<String, Object> inspectMap = Maps.newHashMap();
            inspectMap.put("compId", PapersYearInspect.getTbCompId());
            inspectMap.put("endTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(PapersYearInspect.getCreateTime()));

//            List<PapersYearInspectProcessVo> inspectList = papersYearInspectRetailService.listForCondition(inspectMap);
            List<PapersYearInspectHistoryVo> inspectHistoryList = papersYearInspectMapper.listForInspectHistory(inspectMap);
            if (!CollectionUtils.isEmpty(inspectHistoryList)) {
                compInfo.setInspectList(inspectHistoryList);
            }

            //??????????????????????????????????????????
            Map<String, Object> speciesChangeMap = Maps.newHashMap();
            speciesChangeMap.put("inspectId", inspectId);
            speciesChangeMap.put("endTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(PapersYearInspect.getCreateTime()));

            List<ChangeRecVoInPapersYearVo> changeList = changeRecordMapper.listByInspectConditionForInspect(speciesChangeMap);

            //????????????????????????
            SpeChangeUtil.replaceSpeChangeType(changeList);

            if (!CollectionUtils.isEmpty(changeList)) {
                compInfo.setSpeciesChangelist(changeList);
            }
            return compInfo;
        }
        return null;
    }

    /**
     * @param id
     * @return void
     * @author wg
     * @description ??????????????????
     * @date 2021/4/9 9:35
     */
    @Override
    public void cancel(String id) {
        //????????????????????????
        QueryWrapper<PapersYearInspect> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        wrapper.eq("del_flag", BoolUtils.N);
        PapersYearInspect papersYearInspect = papersYearInspectMapper.selectOne(wrapper);

        //???????????????????????????????????????????????????
        if (!DefaultAdviceEnum.REPORT_DEFAULT_ADVICE.getCode().equals(papersYearInspect.getStatus())) {
            throw new SofnException("??????????????????????????????????????????");
        }
        //??????????????????????????????
        papersYearInspect.preUpdate();
        //????????????????????????
        papersYearInspect.setStatus(DefaultAdviceEnum.BINDING_CANCEL.getCode());
        //????????????
        boolean success = papersYearInspect.updateById();
        if (!success) {
            throw new SofnException("??????????????????");
        }
        //????????????????????????
        Result<SysOrgAndRegionVo> result = SysOwnOrgUtil.getSysApproveLevelForPapersYearApprove();
        if (Result.CODE.equals(result.getCode())) {
            //????????????????????????????????????
            SysOrgAndRegionVo data = result.getData();
            PapersYearInspectProcess papersYearInspectProcess = convertPapersYearInspectProcess(IdUtil.getUUId(), id, DefaultAdviceEnum.BINDING_CANCEL.getCode(),
                    this.getOneById(id).getApplyNum(), DefaultAdviceEnum.BINDING_CANCEL.getMsg(), new Date(), data.getOrgName());
            papersYearInspectProcessMapper.insert(papersYearInspectProcess);
        }

        //???????????????
        if (Constants.WORKFLOW.equals(BoolUtils.Y)) {
            //???????????????
            String[] keys = {"status", "person"};
            Object[] vals = {DefaultAdviceEnum.BINDING_CANCEL.getCode(), SysOwnOrgUtil.getOrganizationName()};
            workApi.backWorkItem(BackWorkItemForm.getInstanceForm(
                    defId, idAttrName, id, "papers_year_inspect_report", MapUtil.getParams(keys, vals)));
        }
    }

    @Override
    public List<PapersYearInspect> listYearInspectByCompId(String compId) {
        QueryWrapper<PapersYearInspect> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("tb_comp_id", compId);
        return papersYearInspectMapper.selectList(queryWrapper);
    }

    @Override
    public String promptingInspect() {
        Date now = new Date();
        String nowStr = DateUtils.format(now, "yyyyMMdd");
        Date[] se = this.getStartAndEnd(nowStr);
        if (Objects.nonNull(se[0]) && Objects.nonNull(se[1])) {
            List<Papers> papers = papersService.listEnablePapers();
            String[] keys = {"year", "compId", "pageNo", "pageSize"};
            Object[] vals = {DateUtils.format(se[0], "yyyy"), UserUtil.getLoginUserOrganizationId(), "0", "10"};
            List<PapersYearInspectVo> papersYearInspects = papersYearInspectMapper.list(MapUtil.getParams(keys, vals));
            StringBuilder sb = new StringBuilder();
            for (Papers p : papers) {
                if (CollectionUtils.isEmpty(papersYearInspects)) {
                    sb.append(",").append(p.getPapersNumber());
                } else {
                    String papersYearInspectId = papersYearInspects.get(0).getId();
                    List<PapersVoInPapersYear> pvipys = papersYearInspectMapper.listPapersInYearById(papersYearInspectId);
                    List<String> paperNumbers = pvipys.stream().map(PapersVoInPapersYear::getPapersNumber).collect(Collectors.toList());
                    if (!paperNumbers.contains(p.getPapersNumber())) {
                        sb.append(",").append(p.getPapersNumber());
                    }
                }
            }
            if (StringUtils.isNotBlank(sb.toString())) {
                String result = sb.toString().substring(1);
                return "????????????(" + result + ")???????????????" + vals[0] + "????????????";
            }
        }
        return null;
    }

    public static void main(String[] args) {
        new PapersYearInspectServiceImpl().promptingInspect();
    }

    private Date[] getStartAndEnd(String nowStr) {
        Date[] result = new Date[2];
        String year = nowStr.substring(0, 4);
        String month = nowStr.substring(4, 6);
        if ("11".equals(month) || "12".equals(month)) {
            result[0] = DateUtils.stringToDate(year + "-11-15", DateUtils.DATE_PATTERN);
            result[1] = DateUtils.stringToDate(String.valueOf(Integer.valueOf(year) + 1) + "-02-15", DateUtils.DATE_PATTERN);
        } else if ("01".equals(month) || "02".equals(month)) {
            result[0] = DateUtils.stringToDate(String.valueOf(Integer.valueOf(year) - 1) + "-11-15", DateUtils.DATE_PATTERN);
            result[1] = DateUtils.stringToDate(year + "-02-15", DateUtils.DATE_PATTERN);
        }
        return result;
    }


    /**
     * ??????????????????
     * wuXY
     * 2020-1-3 17:03:10
     *
     * @param papersYearInspectForm ??????
     * @return 1????????????other???????????????
     */
    @Override
    public Result saveOrUpdate(PapersYearInspectForm papersYearInspectForm, boolean isReport) {
        //??????????????????
        RedisUserUtil.validReSubmit("fdpi_PapersYearInspect_saveOrUpdate");
        Date dateNow = new Date();
        String status = !isReport ? "1" : "2";
        //????????????????????????
        SysUserForm sysUserForm = JsonUtils.json2obj(UserUtil.getLoginUserOrganizationInfo(), SysUserForm.class);
        if (StringUtils.isBlank(papersYearInspectForm.getInspectId())) {
            //??????
            String id = IdUtil.getUUId();
            //??????????????????
            QueryWrapper<PapersYearInspect> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("year", papersYearInspectForm.getYear());
            queryWrapper.eq("TB_COMP_ID", UserUtil.getLoginUserOrganizationId());
            queryWrapper.eq("DEL_FLAG", "N");
            int count = this.count(queryWrapper);
            if (count > 0) {
                return Result.error("?????????????????????");
            }
            //??????PAPERS_YEAR_INSPECT ????????????
            PapersYearInspect papersYearInspect = convertPapersYearInspect(id, dateNow, true, status, papersYearInspectForm);
            //??????PAPERS_YEAR_INSPECT_RETAIL ????????????
            List<PapersYearInspectRetail> retailList = convertInspectRetailList(id, true, papersYearInspectForm);
            //???????????????????????????????????????
            List<PapersYearInspectPRetail> papersList = this.convertInspectPapersRetailList(id, true, papersYearInspectForm);
            TransactionStatus transactionStatus = platformTransactionManager.getTransaction(new DefaultTransactionDefinition());
            //????????????
            Map<String, Object> signMap = this.convertSignboardParam();
            List<PapersYearInspectHistory> papersYearInspectHistories = signboardMapper.listForInspectHistory(signMap);
            //??????????????????
            List<PapersYearInspectHistory> papersYearInspectHistoryList = this.convertYearInspectHistories(papersYearInspectHistories, id, dateNow);
            try {
                //????????????PAPERS_YEAR_INSPECT
                papersYearInspectMapper.insert(papersYearInspect);
                //????????????PAPERS_YEAR_INSPECT_RETAIL
                papersYearInspectRetailService.saveBatch(retailList);
                //?????????????????????????????? PAPERS_YEAR_INSPECT_P_RETAIL???
                papersYearInspectPRetailService.saveBatch(papersList);
                //????????????????????????
                if (!CollectionUtils.isEmpty(papersYearInspectHistoryList)) {
                    papersYearInspectHistoryList.forEach(papersYearInspectHistory -> {
                        papersYearInspectHistoryMapper.insert(papersYearInspectHistory);
                    });
                }
                if (isReport) {
                    //????????????????????????
                    PapersYearInspectProcess papersYearInspectProcess = convertPapersYearInspectProcess(IdUtil.getUUId(),
                            papersYearInspect.getId(), papersYearInspect.getStatus(), papersYearInspect.getApplyNum(),
                            DefaultAdviceEnum.REPORT_DEFAULT_ADVICE.getMsg(), dateNow, sysUserForm.getOrganizationName());
                    //??????????????????
                    papersYearInspectProcessMapper.insert(papersYearInspectProcess);
                    //???????????? - ??????
                    if (Constants.WORKFLOW.equals(BoolUtils.Y)) {
                        SubmitInstanceVo submitInstanceVo = submitInstanceVoProcess(papersYearInspect.getId(), String.valueOf(papersYearInspect.getYear()), papersYearInspect.getStatus(), DefaultAdviceEnum.REPORT_DEFAULT_ADVICE.getMsg(), sysUserForm.getOrganizationName());
                        Result<String> result = workApi.startChainProcess(submitInstanceVo);
                        if (!Result.CODE.equals(result.getCode())) {
                            throw new SofnException("??????????????????");
                        }
                    }
                }
                platformTransactionManager.commit(transactionStatus);
                return Result.ok((Object) id, "????????????!");
            } catch (Exception ex) {
                platformTransactionManager.rollback(transactionStatus);
                log.error("?????????????????????????????????" + ex.getMessage());
                throw new SofnException("???????????????");
            }
        } else {
            //????????????
            PapersYearInspect papersYearInspect = convertPapersYearInspect("", dateNow, false, status, papersYearInspectForm);
            //????????????
            //List<PapersYearInspectRetail> retailList = convertInspectRetailList(papersYearInspectForm.getInspectId(), false, papersYearInspectForm);
            TransactionStatus transactionUpdateStatus = platformTransactionManager.getTransaction(new DefaultTransactionDefinition());
            try {
                //????????????
                this.updateById(papersYearInspect);
                //????????????
                //papersYearInspectRetailService.updateBatchById(retailList);
                if (isReport) {
                    PapersYearInspectProcess papersYearInspectProcess = convertPapersYearInspectProcess(
                            IdUtil.getUUId(), papersYearInspect.getId(), papersYearInspect.getStatus(), papersYearInspect.getApplyNum(),
                            DefaultAdviceEnum.REPORT_DEFAULT_ADVICE.getMsg(), dateNow, sysUserForm.getOrganizationName());
                    //??????????????????
                    papersYearInspectProcessMapper.insert(papersYearInspectProcess);
                    //???????????? - ??????
                    if (Constants.WORKFLOW.equals(BoolUtils.Y)) {
                        SubmitInstanceVo submitInstanceVo = submitInstanceVoProcess(papersYearInspect.getId(), String.valueOf(papersYearInspect.getYear()), papersYearInspect.getStatus(), DefaultAdviceEnum.REPORT_DEFAULT_ADVICE.getMsg(), sysUserForm.getOrganizationName());
                        Result<String> result = workApi.startChainProcess(submitInstanceVo);
                        if (!Result.CODE.equals(result.getCode())) {
                            throw new SofnException("??????????????????");
                        }
                    }
                }
                platformTransactionManager.commit(transactionUpdateStatus);
                return Result.ok("???????????????");
            } catch (Exception ex) {
                platformTransactionManager.rollback(transactionUpdateStatus);
                log.error("???????????????????????????" + ex.getMessage());
                throw new SofnException("???????????????");
            }
        }
    }

    private List<PapersYearInspectHistory> convertYearInspectHistories(List<PapersYearInspectHistory> papersYearInspectHistories, String id, Date date) {
        if (CollectionUtils.isEmpty(papersYearInspectHistories)) return null;
        String userId = UserUtil.getLoginUserId();
        papersYearInspectHistories.forEach(papersYearInspectHistory -> {
            papersYearInspectHistory.setId(IdUtil.getUUId());
            papersYearInspectHistory.setYearInspectId(id);
            papersYearInspectHistory.setCreateUserId(userId);
            papersYearInspectHistory.setDelFlag("N");
            papersYearInspectHistory.setCreateTime(date);
        });
        return papersYearInspectHistories;
    }

    private Map<String, Object> convertSignboardParam() {
        Map<String, Object> signMap = Maps.newHashMap();
        signMap.put("compId", UserUtil.getLoginUserOrganizationId());
        signMap.put("withOutStatus", "3");
        return signMap;
    }

    /**
     * ?????????????????????
     * wuXY
     * 2020-1-6 19:57:14
     *
     * @param inspectId ????????????id
     * @return 1:??????????????????????????????
     */
    @Override
    @Transactional
    public String report(String inspectId) {
        //??????????????????
        RedisUserUtil.validReSubmit("fdpi_PapersYearInspect_report");
        //??????????????????
        Date dateNow = new Date();
        PapersYearInspect papersYearInspect = new PapersYearInspect();
        papersYearInspect.setId(inspectId);
        papersYearInspect.setUpdateTime(dateNow);
        papersYearInspect.setUpdateUserId(UserUtil.getLoginUserId());
        String provinceCode = CodeUtil.getProvinceCode(tbCompService.getCombById(this.getOneById(inspectId).getTbCompId()).getRegionInCh().split("-")[0]);
        papersYearInspect.setApplyNum(CodeUtil.getApplyCode(provinceCode,
                CodeTypeEnum.PAPERS_YEAR_INSPECT.getKey(), this.getSequenceNum(provinceCode)));
        papersYearInspect.setStatus("2");

        //????????????????????????
        SysUserForm sysUserForm = JsonUtils.json2obj(UserUtil.getLoginUserOrganizationInfo(), SysUserForm.class);

        try {
            //?????????????????????????????????????????????
            boolean isSuccess = this.updateById(papersYearInspect);
            if (isSuccess) {
                //????????????????????????
                PapersYearInspectProcess papersYearInspectProcess = convertPapersYearInspectProcess(
                        IdUtil.getUUId(), inspectId, papersYearInspect.getStatus(), papersYearInspect.getApplyNum(),
                        DefaultAdviceEnum.REPORT_DEFAULT_ADVICE.getMsg(), dateNow, sysUserForm.getOrganizationName());
                papersYearInspectProcessMapper.insert(papersYearInspectProcess);
                //???????????? - ??????
                if (Constants.WORKFLOW.equals(BoolUtils.Y)) {
                    SubmitInstanceVo submitInstanceVo = submitInstanceVoProcess(papersYearInspect.getId(), String.valueOf(papersYearInspect.getYear()), papersYearInspect.getStatus(), DefaultAdviceEnum.REPORT_DEFAULT_ADVICE.getMsg(), sysUserForm.getOrganizationName());
                    Result<String> result = workApi.startChainProcess(submitInstanceVo);
                    if (!Result.CODE.equals(result.getCode())) {
                        throw new SofnException("??????????????????");
                    }
                }
            }
            return "1";
        } catch (Exception ex) {
            log.error("???????????????????????????" + ex.getMessage());
            throw new SofnException("????????????!");
        }
    }

    /**
     * ???????????????
     * wuXY
     * 2020-1-6 19:57:14
     *
     * @param inspectId ????????????id
     * @return 1:??????????????????????????????
     */
    @Override
    public String deleteInspect(String inspectId) {
        PapersYearInspect papersYearInspect = new PapersYearInspect();
        papersYearInspect.setId(inspectId);
        papersYearInspect.setDelFlag("Y");
        papersYearInspect.setUpdateUserId(UserUtil.getLoginUserId());
        papersYearInspect.setUpdateTime(new Date());
        int changeCount = papersYearInspectMapper.updateById(papersYearInspect);
        if (changeCount > 0) {
            return "1";
        }
        return "???????????????";
    }

    /**
     * ??????????????????
     * wuXY
     * 2020-1-6 19:57:14
     *
     * @param processForm ????????????
     * @param isApprove   1:?????????0?????????
     * @return 1???????????????????????????
     */
    @Override
    public String approveOrBack(ProcessForm processForm, boolean isApprove) {
        //??????????????????
        RedisUserUtil.validReSubmit("fdpi_PapersYearInspect_approveOrBack", processForm.getId());
        //????????????????????????
        Result<SysOrgAndRegionVo> result = SysOwnOrgUtil.getSysApproveLevelForPapersYearApprove();
        if (Result.CODE.equals(result.getCode())) {
            //????????????????????????????????????
            SysOrgAndRegionVo data = result.getData();
            PapersYearInspectProcess papersYearInspectProcess = null;
            Date dateNow = new Date();
            String advice = "";
            String status = "";
            if (ApproveLevelEnum.APPROVE_FIRST_LEVEL.getLevel().equals(data.getApproveLevel())) {
                //??????-??????-????????????
                if (isApprove) {
                    //??????
                    status = DefaultAdviceEnum.APPROVE_FIRST_DEFAULT_ADVICE.getCode();
                    advice = StringUtils.isBlank(processForm.getAdvice()) ? DefaultAdviceEnum.APPROVE_FIRST_DEFAULT_ADVICE.getMsg() : processForm.getAdvice();
                } else {
                    //??????
                    status = DefaultAdviceEnum.RETURN_FIRST_DEFAULT_ADVICE.getCode();
                    advice = StringUtils.isBlank(processForm.getAdvice()) ? DefaultAdviceEnum.RETURN_FIRST_DEFAULT_ADVICE.getMsg() : processForm.getAdvice();
                }
                papersYearInspectProcess = convertPapersYearInspectProcess(IdUtil.getUUId(), processForm.getId(), status,
                        this.getOneById(processForm.getId()).getApplyNum(), advice, dateNow, data.getOrgName());
            } else if (ApproveLevelEnum.APPROVE_SECOND_LEVEL.getLevel().equals(data.getApproveLevel())) {
                //???????????? or ??????????????????-??????
                if (isApprove) {
                    status = DefaultAdviceEnum.APPROVE_SECOND_DEFAULT_ADVICE.getCode();
                    advice = StringUtils.isBlank(processForm.getAdvice()) ? DefaultAdviceEnum.APPROVE_SECOND_DEFAULT_ADVICE.getMsg() : processForm.getAdvice();
                } else {
                    status = DefaultAdviceEnum.RETURN_SECOND_DEFAULT_ADVICE.getCode();
                    advice = StringUtils.isBlank(processForm.getAdvice()) ? DefaultAdviceEnum.RETURN_SECOND_DEFAULT_ADVICE.getMsg() : processForm.getAdvice();
                }
                papersYearInspectProcess = convertPapersYearInspectProcess(IdUtil.getUUId(), processForm.getId(), status,
                        this.getOneById(processForm.getId()).getApplyNum(), advice, dateNow, data.getOrgName());
            } else {
                return "???????????????????????????/??????";
            }
            //????????????????????????????????????
            PapersYearInspect papersYearInspect = new PapersYearInspect();
            papersYearInspect.setId(processForm.getId());
            papersYearInspect.setStatus(papersYearInspectProcess.getStatus());
            papersYearInspect.setUpdateTime(dateNow);
            papersYearInspect.setUpdateUserId(UserUtil.getLoginUserId());
            //??????
            TransactionStatus transactionStatus = platformTransactionManager.getTransaction(new DefaultTransactionDefinition());
            try {
                //????????????????????????
                papersYearInspectMapper.updateById(papersYearInspect);
                platformTransactionManager.commit(transactionStatus);
                //????????????/?????????????????????
                papersYearInspectProcessMapper.insert(papersYearInspectProcess);
                //???????????? - ?????? - ??????
                if (Constants.WORKFLOW.equals(BoolUtils.Y)) {
                    Result<String> stringResult = new Result<>();
                    if (isApprove) {
                        //??????
                        SubmitInstanceVo submitInstanceVo = submitInstanceVoProcess(papersYearInspect.getId(), String.valueOf(papersYearInspect.getYear()), papersYearInspectProcess.getStatus(), papersYearInspectProcess.getAdvice(), papersYearInspectProcess.getPersonName());
                        stringResult = workApi.completeWorkItem(submitInstanceVo);
                    } else {
                        BackWorkItemForm backWorkItemForm = backWorkItemFormProcess(papersYearInspect.getId(), String.valueOf(papersYearInspect.getYear()), papersYearInspectProcess.getStatus(), papersYearInspectProcess.getAdvice(), papersYearInspectProcess.getPersonName());
                        stringResult = workApi.backWorkItem(backWorkItemForm);
                    }
                    if (!Result.CODE.equals(result.getCode())) {
                        throw new SofnException("??????????????????");
                    }
                }
                return "1";
            } catch (Exception ex) {
                log.error("??????????????????/???????????????" + ex.getMessage());
                platformTransactionManager.rollback(transactionStatus);
            }

        }
        return result.getMsg();
    }

    /**
     * ????????????/????????????
     * wuXY
     * 2020-1-6 19:57:14
     *
     * @param inspectId ??????id
     * @param status    ????????????
     * @return PapersYearInspectProcess
     */
    @Override
    public PapersYearInspectProcess getProcessByInspectId(String inspectId, String status) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("inspectId", inspectId);
        map.put("status", status);
        return papersYearInspectProcessMapper.getProcessByInspectId(map);
    }

    @Override
    public PapersYearInspectProcess getProcessByInspectIdInfo(String inspectId, String status) {
        List<Map<String, Object>> changeRecordId = WorkUtil.getProcesslist(defId, idAttrName, inspectId);
        if (StringUtils.isEmpty(status)) return null;

        PapersYearInspectProcess papersYearInspectProcess = new PapersYearInspectProcess();

        changeRecordId.stream().anyMatch(o -> {
            String resultStatus = String.valueOf(o.get("status"));
            if (resultStatus.equals(status)) {
                papersYearInspectProcess.setId(IdUtil.getUUId());
                papersYearInspectProcess.setStatus(status);
                papersYearInspectProcess.setAdvice(String.valueOf(o.get("advice")));
                papersYearInspectProcess.setConTime((Date) o.get("createTime"));
                papersYearInspectProcess.setPersonName(String.valueOf(o.get("personName")));
                return true;
            }
            return false;
        });

        return papersYearInspectProcess;
    }

    /**
     * ????????????
     *
     * @param id                    ??????
     * @param dateNow               ????????????
     * @param papersYearInspectForm ??????
     * @return PapersYearInspect??????
     */
    private PapersYearInspect convertPapersYearInspect(String id, Date dateNow, boolean isAdd, String status, PapersYearInspectForm papersYearInspectForm) {
        PapersYearInspect papersYearInspect = new PapersYearInspect();
        if (isAdd) {
            papersYearInspect.setId(id);
            papersYearInspect.setCreateTime(dateNow);
            papersYearInspect.setCreateUserId(UserUtil.getLoginUserId());
            papersYearInspect.setUpdateTime(papersYearInspect.getCreateTime());
            papersYearInspect.setUpdateUserId(papersYearInspect.getCreateUserId());
        } else {
            //??????
            papersYearInspect.setId(papersYearInspectForm.getInspectId());
            papersYearInspect.setUpdateTime(dateNow);
            papersYearInspect.setUpdateUserId(UserUtil.getLoginUserId());
        }
        papersYearInspect.setTbCompId(papersYearInspectForm.getCompId());
        papersYearInspect.setYear(papersYearInspectForm.getYear());
        papersYearInspect.setStatus(status);
        if ("2".equals(status)) {
            String provinceCode = CodeUtil.getProvinceCode(tbCompService.getCombById(papersYearInspect.getTbCompId()).getRegionInCh().split("-")[0]);
            papersYearInspect.setApplyNum(CodeUtil.getApplyCode(provinceCode,
                    CodeTypeEnum.PAPERS_YEAR_INSPECT.getKey(), this.getSequenceNum(provinceCode)));
        }
        papersYearInspect.setDelFlag("N");

        return papersYearInspect;
    }

    private String getSequenceNum(String provinceCode) {
        String maxApplyNum = papersYearInspectMapper.getTodayMaxApplyNum(
                DateUtils.format(new Date(), "yyyyMMdd") + provinceCode);
        return org.springframework.util.StringUtils.hasText(maxApplyNum) ?
                String.format("%06d", (Integer.valueOf(maxApplyNum.substring(13)) + 1)) : null;
    }

    /**
     * ????????????
     *
     * @param id                    ????????????id
     * @param papersYearInspectForm ????????????
     * @return PapersYearInspectRetail??????
     */
    private List<PapersYearInspectRetail> convertInspectRetailList(String id, boolean isAdd, PapersYearInspectForm papersYearInspectForm) {
        List<PapersYearInspectRetail> retailList = new ArrayList<>();
        Date dtNow = new Date();
        if (papersYearInspectForm.getSpeciesList() != null) {
            for (PapersYearInspectRetailForm form : papersYearInspectForm.getSpeciesList()) {
                PapersYearInspectRetail retail = new PapersYearInspectRetail();
                if (isAdd) {
                    retail.setId(IdUtil.getUUId());
                    retail.setCreateTime(dtNow);
                    retail.setCreateUserId(UserUtil.getLoginUserId());
                    retail.setUpdateTime(dtNow);
                    retail.setUpdateUserId(retail.getCreateUserId());
                    retail.setDelFlag("N");
                } else {
                    retail.setId(form.getInspectRetailId());
                    retail.setUpdateTime(dtNow);
                    retail.setUpdateUserId(UserUtil.getLoginUserId());
                }
//                retail.setPapersId(form.getPapersId());
                retail.setSpeciesId(form.getSpeciesId());
                retail.setPapersYearInspectId(id);
                retail.setIssueNum(form.getSpeciesNumber());
                retail.setSignboardNum(form.getSignboardNumber());
                retailList.add(retail);
            }
        }
        return retailList;
    }

    /**
     * ????????????
     *
     * @param inspectId             ????????????id
     * @param papersYearInspectForm ????????????
     * @return PapersYearInspectRetail??????
     */
    private List<PapersYearInspectPRetail> convertInspectPapersRetailList(String inspectId, boolean isAdd, PapersYearInspectForm papersYearInspectForm) {
        List<PapersYearInspectPRetail> papersRetailList = new ArrayList<>();
        Date dtNow = new Date();
        if (papersYearInspectForm.getPapersList() != null) {
            for (PapersYearInspectPRetailForm form : papersYearInspectForm.getPapersList()) {
                PapersYearInspectPRetail papersRetail = new PapersYearInspectPRetail();
                if (isAdd) {
                    papersRetail.setId(IdUtil.getUUId());
                    papersRetail.setCreateTime(dtNow);
                    papersRetail.setCreateUserId(UserUtil.getLoginUserId());
                    papersRetail.setUpdateTime(dtNow);
                    papersRetail.setUpdateUserId(papersRetail.getCreateUserId());
                    papersRetail.setDelFlag("N");
                    papersRetail.setPapersYearInspectId(inspectId);
                } else {
                    papersRetail.setId(form.getInspectPRetailId());
                    papersRetail.setUpdateTime(dtNow);
                    papersRetail.setUpdateUserId(UserUtil.getLoginUserId());
                }
                papersRetail.setPapersId(form.getPapersId());
                papersRetailList.add(papersRetail);
            }
        }
        return papersRetailList;
    }

    /**
     * ???????????????????????????
     * wuXY
     * 2020-1-7 11:01:17
     *
     * @param id            ??????
     * @param inspectId     ?????????????????????
     * @param status        ??????
     * @param advice        ??????
     * @param dtNow         ??????
     * @param userOrOrgName ????????????????????????
     * @return PapersYearInspectProcess
     */
    private PapersYearInspectProcess convertPapersYearInspectProcess(String id
            , String inspectId
            , String status
            , String applyNum
            , String advice
            , Date dtNow
            , String userOrOrgName) {
        PapersYearInspectProcess papersYearInspectProcess = new PapersYearInspectProcess();
        papersYearInspectProcess.setId(id);
        papersYearInspectProcess.setApplyNum(applyNum);
        papersYearInspectProcess.setPapersYearInspectId(inspectId);
        papersYearInspectProcess.setStatus(status);
        //DefaultAdviceEnum.REPORT_DEFAULT_ADVICE.getMsg()
        papersYearInspectProcess.setAdvice(advice);
        papersYearInspectProcess.setConTime(dtNow);
        papersYearInspectProcess.setPerson(UserUtil.getLoginUserId());
        papersYearInspectProcess.setPersonName(userOrOrgName);
        return papersYearInspectProcess;
    }

    /**
     * ?????? ??? ??????
     *
     * @param dataId     ??????id
     * @param status     ??????
     * @param advice     ????????????
     * @param personName ????????????
     * @return
     */
    private SubmitInstanceVo submitInstanceVoProcess(String dataId, String year, String status, String advice, String personName) {

        SubmitInstanceVo submitInstanceVo = new SubmitInstanceVo();
        submitInstanceVo.setDefId(defId);
        submitInstanceVo.setIdAttrName(idAttrName);
        submitInstanceVo.setIdAttrValue(dataId);
        String[] keys = {"year", "status", "advice", "personName"};
        String[] values = {year, status, advice, personName};
        Map<String, Object> params = MapUtil.getParams(keys, values);
        submitInstanceVo.setParams(params);
        return submitInstanceVo;
    }

    /**
     * ??????
     *
     * @param dataId
     * @param year
     * @param status
     * @param advice
     * @param personName
     * @return
     */
    private BackWorkItemForm backWorkItemFormProcess(String dataId, String year, String status, String advice, String personName) {
        String[] keys = {"year", "status", "advice", "personName"};
        String[] values = {year, status, advice, personName};
        Map<String, Object> params = MapUtil.getParams(keys, values);

        return BackWorkItemForm.getInstanceForm(defId, idAttrName, dataId, "papers_year_inspect_report", params);
    }

}
