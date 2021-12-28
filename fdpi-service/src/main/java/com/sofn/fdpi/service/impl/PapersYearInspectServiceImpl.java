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

    //流程id
    private final static String defId = "papers_year_inspect:papers_year_inspect";

    //业务数据名称
    private final static String idAttrName = "dataId";

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

    /**
     * 证书年审申请查询列表（分页）
     * wuXY
     * 2020-1-3 14:00:45
     *
     * @param map 查询条件
     * @return 返回分页列表
     */
    @Override
    public PageUtils<PapersYearInspectVo> listForApply(Map<String, Object> map) {
        this.perfectParams(map);
        PageHelper.offsetPage(Integer.parseInt(map.get("pageNo").toString()), Integer.parseInt(map.get("pageSize").toString()));
        List<PapersYearInspectVo> list = papersYearInspectMapper.listByParams(map);
        Set<String> auths = tbDepartmentService.listAllAuth(DepartmentTypeEnum.PAPER_YEAR_INSPECT.getKey());
        list.forEach(vo -> {
            //如果当前角色是第三方企业，那么只有已上报的数据才可以显示撤回按钮
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
        //只有状态在上报情况才能审核
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
     * 获取证书年审明细信息
     * wuXY
     * 2020-1-3 17:03:10
     *
     * @param inspectId 证书年审id
     * @return 对象
     */
    @Override
    public PapersYearInspectViewVo getDetailById(String compId, String inspectId) {
        Date dateNow = new Date();
        if (StringUtils.isBlank(inspectId)) {
            //新增页面获取数据
            //1、根据当前组织机构，获取企业信息；
            PapersYearInspectViewVo compInfo = papersYearInspectMapper.getCompanyByComId(compId);
            if (compInfo != null) {
                //获取当前企业物种信息；
                List<SpecieVoInPapersYear> specieList = papersYearInspectMapper.listSpeciesByCompIdNew(compId);
                if (!CollectionUtils.isEmpty(specieList)) {
                    compInfo.setSpeciesList(specieList);
                }
                //获取当前企业证书列表；
                List<PapersVoInPapersYear> papersList = papersYearInspectMapper.listPapersInYearByCompId(compId);
                if (!CollectionUtils.isEmpty(papersList)) {
                    compInfo.setPapersList(papersList);
                }
                //获取当前企业年审列表
                Map<String, Object> inspectMap = Maps.newHashMap();
                inspectMap.put("compId", compId);
                inspectMap.put("endTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dateNow));
//                List<PapersYearInspectProcessVo> inspectRetailList = papersYearInspectMapper.listForCondition(inspectMap);
                List<PapersYearInspectHistoryVo> inspectHistoryList = papersYearInspectMapper.listForInspectHistory(inspectMap);
                if (!CollectionUtils.isEmpty(inspectHistoryList)) {
                    compInfo.setInspectList(inspectHistoryList);
                }
                //获取物种变更记录
                List<ChangeRecVoInPapersYearVo> changeRecList = changeRecordMapper.listByConditionForInspect(inspectMap);

                //翻译物种变更类型
                SpeChangeUtil.replaceSpeChangeType(changeRecList);

                if (!CollectionUtils.isEmpty(changeRecList)) {
                    compInfo.setSpeciesChangelist(changeRecList);
                }
                return compInfo;
            }

        } else {
            //编辑or详情
            PapersYearInspect PapersYearInspect = this.getOneById(inspectId);
            if (PapersYearInspect == null) {
                return null;
            }
            //获取当前证书年审的企业相关信息
            PapersYearInspectViewVo compInfo = papersYearInspectMapper.getInspectById(inspectId);
            //获取该年审中物种信息
            List<SpecieVoInPapersYear> specieList = papersYearInspectMapper.listSpeciesByIdNew(inspectId);
            if (!CollectionUtils.isEmpty(specieList)) {
                compInfo.setSpeciesList(specieList);
            }
            //获取该年审中证书信息列表
            List<PapersVoInPapersYear> papersList = papersYearInspectMapper.listPapersInYearById(inspectId);
            if (!CollectionUtils.isEmpty(papersList)) {
                compInfo.setPapersList(papersList);
            }
            //获取该年审中以前年审信息
            Map<String, Object> inspectMap = Maps.newHashMap();
            inspectMap.put("compId", PapersYearInspect.getTbCompId());
            inspectMap.put("endTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(PapersYearInspect.getCreateTime()));

//            List<PapersYearInspectProcessVo> inspectList = papersYearInspectRetailService.listForCondition(inspectMap);
            List<PapersYearInspectHistoryVo> inspectHistoryList = papersYearInspectMapper.listForInspectHistory(inspectMap);
            if (!CollectionUtils.isEmpty(inspectHistoryList)) {
                compInfo.setInspectList(inspectHistoryList);
            }

            //获取该年审中以前物种变更记录
            Map<String, Object> speciesChangeMap = Maps.newHashMap();
            speciesChangeMap.put("inspectId", inspectId);
            speciesChangeMap.put("endTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(PapersYearInspect.getCreateTime()));

            List<ChangeRecVoInPapersYearVo> changeList = changeRecordMapper.listByInspectConditionForInspect(speciesChangeMap);

            //翻译物种变更类型
            SpeChangeUtil.replaceSpeChangeType(changeList);

            if (!CollectionUtils.isEmpty(changeList)) {
                compInfo.setSpeciesChangelist(changeList);
            }
            return compInfo;
        }
        return null;
    }


    /**
     * 《企业查询-详细》获取企业明细信息
     * wuXY
     * 2020-1-3 17:03:10
     *
     * @param inspectId 证书年审id
     * @return 对象
     */
    @Override
    public PapersYearInspectViewVo getDetailByCompIdAndInspectId(String compId, String inspectId) {
        Date dateNow = new Date();
        if (StringUtils.isBlank(inspectId)) {
            //新增页面获取数据
            //1、根据当前组织机构，获取企业信息；
            PapersYearInspectViewVo compInfo = papersYearInspectMapper.getCompanyByComId(compId);
            if (compInfo != null) {
                //获取当前企业物种信息；
                List<SpecieVoInPapersYear> specieList = papersYearInspectMapper.listSpeciesByCompIdNew(compId);
                if (!CollectionUtils.isEmpty(specieList)) {
                    compInfo.setSpeciesList(specieList);
                }
                //获取当前企业证书信息；
                List<PapersVoInPapersYear> papersList = papersYearInspectMapper.listPapersInYearByCompId(compId);
                if (!CollectionUtils.isEmpty(papersList)) {
                    compInfo.setPapersList(papersList);
                }

                //获取当前企业年审列表
                Map<String, Object> inspectMap = Maps.newHashMap();
                inspectMap.put("compId", compId);
                inspectMap.put("endTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dateNow));
//                List<PapersYearInspectProcessVo> inspectRetailList = papersYearInspectRetailService.listForCondition(inspectMap);
                List<PapersYearInspectHistoryVo> inspectHistoryList = papersYearInspectMapper.listForInspectHistory(inspectMap);
                if (!CollectionUtils.isEmpty(inspectHistoryList)) {
                    compInfo.setInspectList(inspectHistoryList);
                }
                //获取物种变更记录
                List<ChangeRecVoInPapersYearVo> changeRecList = changeRecordMapper.listByConditionForInspect(inspectMap);

                //物种变更类型转换
                SpeChangeUtil.replaceSpeChangeType(changeRecList);

                if (!CollectionUtils.isEmpty(changeRecList)) {
                    compInfo.setSpeciesChangelist(changeRecList);
                }
                return compInfo;
            }

        } else {
            //编辑or详情
            PapersYearInspect PapersYearInspect = this.getOneById(inspectId);
            if (PapersYearInspect == null) {
                return null;
            }
            //获取当前证书年审的企业相关信息
            PapersYearInspectViewVo compInfo = papersYearInspectMapper.getInspectById(inspectId);
            //获取该年审中证书物种信息
//            List<SpecieVoInPapersYear> specieList = papersYearInspectMapper.listSpeciesById(inspectId);
            //获取当前企业物种信息；
            List<SpecieVoInPapersYear> specieList = papersYearInspectMapper.listSpeciesByCompIdNew(compId);
            if (!CollectionUtils.isEmpty(specieList)) {
                compInfo.setSpeciesList(specieList);
            }
            //获取当前企业证书信息；
            List<PapersVoInPapersYear> papersList = papersYearInspectMapper.listPapersInYearByCompId(compId);
            if (!CollectionUtils.isEmpty(papersList)) {
                compInfo.setPapersList(papersList);
            }

            //获取该年审中以前年审信息
            Map<String, Object> inspectMap = Maps.newHashMap();
            inspectMap.put("compId", PapersYearInspect.getTbCompId());
            inspectMap.put("endTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(PapersYearInspect.getCreateTime()));

//            List<PapersYearInspectProcessVo> inspectList = papersYearInspectRetailService.listForCondition(inspectMap);
            List<PapersYearInspectHistoryVo> inspectHistoryList = papersYearInspectMapper.listForInspectHistory(inspectMap);
            if (!CollectionUtils.isEmpty(inspectHistoryList)) {
                compInfo.setInspectList(inspectHistoryList);
            }

            //获取该年审中以前物种变更记录
            Map<String, Object> speciesChangeMap = Maps.newHashMap();
            speciesChangeMap.put("inspectId", inspectId);
            speciesChangeMap.put("endTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(PapersYearInspect.getCreateTime()));

            List<ChangeRecVoInPapersYearVo> changeList = changeRecordMapper.listByInspectConditionForInspect(speciesChangeMap);

            //物种变更类型转换
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
     * @description 年审上报取消
     * @date 2021/4/9 9:35
     */
    @Override
    public void cancel(String id) {
        //根据主键得到详情
        QueryWrapper<PapersYearInspect> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        wrapper.eq("del_flag", BoolUtils.N);
        PapersYearInspect papersYearInspect = papersYearInspectMapper.selectOne(wrapper);

        //判断数据库中状态值是否是已上报状态
        if (!DefaultAdviceEnum.REPORT_DEFAULT_ADVICE.getCode().equals(papersYearInspect.getStatus())) {
            throw new SofnException("只有已上报状态才可以申请撤回");
        }
        //设置更新时间与更新人
        papersYearInspect.preUpdate();
        //设置更新后的状态
        papersYearInspect.setStatus(DefaultAdviceEnum.BINDING_CANCEL.getCode());
        //执行更新
        boolean success = papersYearInspect.updateById();
        if (!success) {
            throw new SofnException("执行撤回失败");
        }
        //获取当前组织机构
        Result<SysOrgAndRegionVo> result = SysOwnOrgUtil.getSysApproveLevelForPapersYearApprove();
        if (Result.CODE.equals(result.getCode())) {
            //获取当前组织机构级别成功
            SysOrgAndRegionVo data = result.getData();
            PapersYearInspectProcess papersYearInspectProcess = convertPapersYearInspectProcess(IdUtil.getUUId(), id, DefaultAdviceEnum.BINDING_CANCEL.getCode(),
                    this.getOneById(id).getApplyNum(), DefaultAdviceEnum.BINDING_CANCEL.getMsg(), new Date(), data.getOrgName());
            papersYearInspectProcessMapper.insert(papersYearInspectProcess);
        }

        //操作工作流
        if (Constants.WORKFLOW.equals(BoolUtils.Y)) {
            //操作工作流
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
                return "证书编号(" + result + ")请尽快参加" + vals[0] + "年度年审";
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
     * 新增证书年审
     * wuXY
     * 2020-1-3 17:03:10
     *
     * @param papersYearInspectForm 对象
     * @return 1：成功；other：异常提示
     */
    @Override
    public Result saveOrUpdate(PapersYearInspectForm papersYearInspectForm, boolean isReport) {
        //校验重复提交
        RedisUserUtil.validReSubmit("fdpi_PapersYearInspect_saveOrUpdate");
        Date dateNow = new Date();
        String status = !isReport ? "1" : "2";
        //获取组织机构名称
        SysUserForm sysUserForm = JsonUtils.json2obj(UserUtil.getLoginUserOrganizationInfo(), SysUserForm.class);
        if (StringUtils.isBlank(papersYearInspectForm.getInspectId())) {
            //新增
            String id = IdUtil.getUUId();
            //年审检验重复
            QueryWrapper<PapersYearInspect> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("year", papersYearInspectForm.getYear());
            queryWrapper.eq("TB_COMP_ID", UserUtil.getLoginUserOrganizationId());
            queryWrapper.eq("DEL_FLAG", "N");
            int count = this.count(queryWrapper);
            if (count > 0) {
                return Result.error("该年度已存在！");
            }
            //主表PAPERS_YEAR_INSPECT 数据组装
            PapersYearInspect papersYearInspect = convertPapersYearInspect(id, dateNow, true, status, papersYearInspectForm);
            //明细PAPERS_YEAR_INSPECT_RETAIL 数据组装
            List<PapersYearInspectRetail> retailList = convertInspectRetailList(id, true, papersYearInspectForm);
            //明细中证书表信息，进行组装
            List<PapersYearInspectPRetail> papersList = this.convertInspectPapersRetailList(id, true, papersYearInspectForm);
            TransactionStatus transactionStatus = platformTransactionManager.getTransaction(new DefaultTransactionDefinition());
            //获取标识
            Map<String, Object> signMap = this.convertSignboardParam();
            List<PapersYearInspectHistory> papersYearInspectHistories = signboardMapper.listForInspectHistory(signMap);
            //组装标识列表
            List<PapersYearInspectHistory> papersYearInspectHistoryList = this.convertYearInspectHistories(papersYearInspectHistories, id, dateNow);
            try {
                //保存主表PAPERS_YEAR_INSPECT
                papersYearInspectMapper.insert(papersYearInspect);
                //保存明细PAPERS_YEAR_INSPECT_RETAIL
                papersYearInspectRetailService.saveBatch(retailList);
                //保存明细表中证书信息 PAPERS_YEAR_INSPECT_P_RETAIL。
                papersYearInspectPRetailService.saveBatch(papersList);
                //保存历史标识记录
                if (!CollectionUtils.isEmpty(papersYearInspectHistoryList)) {
                    papersYearInspectHistoryList.forEach(papersYearInspectHistory -> {
                        papersYearInspectHistoryMapper.insert(papersYearInspectHistory);
                    });
                }
                if (isReport) {
                    //上报记录数据组装
                    PapersYearInspectProcess papersYearInspectProcess = convertPapersYearInspectProcess(IdUtil.getUUId(),
                            papersYearInspect.getId(), papersYearInspect.getStatus(), papersYearInspect.getApplyNum(),
                            DefaultAdviceEnum.REPORT_DEFAULT_ADVICE.getMsg(), dateNow, sysUserForm.getOrganizationName());
                    //新增上报记录
                    papersYearInspectProcessMapper.insert(papersYearInspectProcess);
                    //流程组件 - 上报
                    if (Constants.WORKFLOW.equals(BoolUtils.Y)) {
                        SubmitInstanceVo submitInstanceVo = submitInstanceVoProcess(papersYearInspect.getId(), String.valueOf(papersYearInspect.getYear()), papersYearInspect.getStatus(), DefaultAdviceEnum.REPORT_DEFAULT_ADVICE.getMsg(), sysUserForm.getOrganizationName());
                        Result<String> result = workApi.startChainProcess(submitInstanceVo);
                        if (!Result.CODE.equals(result.getCode())) {
                            throw new SofnException("加入流程失败");
                        }
                    }
                }
                platformTransactionManager.commit(transactionStatus);
                return Result.ok((Object) id, "保存成功!");
            } catch (Exception ex) {
                platformTransactionManager.rollback(transactionStatus);
                log.error("证件年审新增保存失败：" + ex.getMessage());
                throw new SofnException("保存失败！");
            }
        } else {
            //修改主表
            PapersYearInspect papersYearInspect = convertPapersYearInspect("", dateNow, false, status, papersYearInspectForm);
            //修改明细
            //List<PapersYearInspectRetail> retailList = convertInspectRetailList(papersYearInspectForm.getInspectId(), false, papersYearInspectForm);
            TransactionStatus transactionUpdateStatus = platformTransactionManager.getTransaction(new DefaultTransactionDefinition());
            try {
                //修改主表
                this.updateById(papersYearInspect);
                //修改明细
                //papersYearInspectRetailService.updateBatchById(retailList);
                if (isReport) {
                    PapersYearInspectProcess papersYearInspectProcess = convertPapersYearInspectProcess(
                            IdUtil.getUUId(), papersYearInspect.getId(), papersYearInspect.getStatus(), papersYearInspect.getApplyNum(),
                            DefaultAdviceEnum.REPORT_DEFAULT_ADVICE.getMsg(), dateNow, sysUserForm.getOrganizationName());
                    //新增上报记录
                    papersYearInspectProcessMapper.insert(papersYearInspectProcess);
                    //流程组件 - 上报
                    if (Constants.WORKFLOW.equals(BoolUtils.Y)) {
                        SubmitInstanceVo submitInstanceVo = submitInstanceVoProcess(papersYearInspect.getId(), String.valueOf(papersYearInspect.getYear()), papersYearInspect.getStatus(), DefaultAdviceEnum.REPORT_DEFAULT_ADVICE.getMsg(), sysUserForm.getOrganizationName());
                        Result<String> result = workApi.startChainProcess(submitInstanceVo);
                        if (!Result.CODE.equals(result.getCode())) {
                            throw new SofnException("加入流程失败");
                        }
                    }
                }
                platformTransactionManager.commit(transactionUpdateStatus);
                return Result.ok("保存成功！");
            } catch (Exception ex) {
                platformTransactionManager.rollback(transactionUpdateStatus);
                log.error("修改年审保存失败：" + ex.getMessage());
                throw new SofnException("保存失败！");
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
     * 列表中上报按钮
     * wuXY
     * 2020-1-6 19:57:14
     *
     * @param inspectId 年审主编id
     * @return 1:成功；其他：异常提示
     */
    @Override
    @Transactional
    public String report(String inspectId) {
        //校验重复提交
        RedisUserUtil.validReSubmit("fdpi_PapersYearInspect_report");
        //修改主表状态
        Date dateNow = new Date();
        PapersYearInspect papersYearInspect = new PapersYearInspect();
        papersYearInspect.setId(inspectId);
        papersYearInspect.setUpdateTime(dateNow);
        papersYearInspect.setUpdateUserId(UserUtil.getLoginUserId());
        String provinceCode = CodeUtil.getProvinceCode(tbCompService.getCombById(this.getOneById(inspectId).getTbCompId()).getRegionInCh().split("-")[0]);
        papersYearInspect.setApplyNum(CodeUtil.getApplyCode(provinceCode,
                CodeTypeEnum.PAPERS_YEAR_INSPECT.getKey(), this.getSequenceNum(provinceCode)));
        papersYearInspect.setStatus("2");

        //获取组织机构名称
        SysUserForm sysUserForm = JsonUtils.json2obj(UserUtil.getLoginUserOrganizationInfo(), SysUserForm.class);

        try {
            //修改主表状态和保存上报操作记录
            boolean isSuccess = this.updateById(papersYearInspect);
            if (isSuccess) {
                //新增上报操作记录
                PapersYearInspectProcess papersYearInspectProcess = convertPapersYearInspectProcess(
                        IdUtil.getUUId(), inspectId, papersYearInspect.getStatus(), papersYearInspect.getApplyNum(),
                        DefaultAdviceEnum.REPORT_DEFAULT_ADVICE.getMsg(), dateNow, sysUserForm.getOrganizationName());
                papersYearInspectProcessMapper.insert(papersYearInspectProcess);
                //流程组件 - 上报
                if (Constants.WORKFLOW.equals(BoolUtils.Y)) {
                    SubmitInstanceVo submitInstanceVo = submitInstanceVoProcess(papersYearInspect.getId(), String.valueOf(papersYearInspect.getYear()), papersYearInspect.getStatus(), DefaultAdviceEnum.REPORT_DEFAULT_ADVICE.getMsg(), sysUserForm.getOrganizationName());
                    Result<String> result = workApi.startChainProcess(submitInstanceVo);
                    if (!Result.CODE.equals(result.getCode())) {
                        throw new SofnException("加入流程失败");
                    }
                }
            }
            return "1";
        } catch (Exception ex) {
            log.error("证书年审上报异常：" + ex.getMessage());
            throw new SofnException("上报失败!");
        }
    }

    /**
     * 列表中删除
     * wuXY
     * 2020-1-6 19:57:14
     *
     * @param inspectId 年审主编id
     * @return 1:成功；其他：异常提示
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
        return "删除失败！";
    }

    /**
     * 证书年审审核
     * wuXY
     * 2020-1-6 19:57:14
     *
     * @param processForm 审核表单
     * @param isApprove   1:审核，0：退回
     * @return 1：成功；其它：提示
     */
    @Override
    public String approveOrBack(ProcessForm processForm, boolean isApprove) {
        //校验重复提交
        RedisUserUtil.validReSubmit("fdpi_PapersYearInspect_approveOrBack", processForm.getId());
        //获取当前组织机构
        Result<SysOrgAndRegionVo> result = SysOwnOrgUtil.getSysApproveLevelForPapersYearApprove();
        if (Result.CODE.equals(result.getCode())) {
            //获取当前组织机构级别成功
            SysOrgAndRegionVo data = result.getData();
            PapersYearInspectProcess papersYearInspectProcess = null;
            Date dateNow = new Date();
            String advice = "";
            String status = "";
            if (ApproveLevelEnum.APPROVE_FIRST_LEVEL.getLevel().equals(data.getApproveLevel())) {
                //直属-初审-审核记录
                if (isApprove) {
                    //审核
                    status = DefaultAdviceEnum.APPROVE_FIRST_DEFAULT_ADVICE.getCode();
                    advice = StringUtils.isBlank(processForm.getAdvice()) ? DefaultAdviceEnum.APPROVE_FIRST_DEFAULT_ADVICE.getMsg() : processForm.getAdvice();
                } else {
                    //退回
                    status = DefaultAdviceEnum.RETURN_FIRST_DEFAULT_ADVICE.getCode();
                    advice = StringUtils.isBlank(processForm.getAdvice()) ? DefaultAdviceEnum.RETURN_FIRST_DEFAULT_ADVICE.getMsg() : processForm.getAdvice();
                }
                papersYearInspectProcess = convertPapersYearInspectProcess(IdUtil.getUUId(), processForm.getId(), status,
                        this.getOneById(processForm.getId()).getApplyNum(), advice, dateNow, data.getOrgName());
            } else if (ApproveLevelEnum.APPROVE_SECOND_LEVEL.getLevel().equals(data.getApproveLevel())) {
                //省级机构 or 直属省级机构-复审
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
                return "该部门用户不能审核/退回";
            }
            //修改年审审核状态数据组装
            PapersYearInspect papersYearInspect = new PapersYearInspect();
            papersYearInspect.setId(processForm.getId());
            papersYearInspect.setStatus(papersYearInspectProcess.getStatus());
            papersYearInspect.setUpdateTime(dateNow);
            papersYearInspect.setUpdateUserId(UserUtil.getLoginUserId());
            //事务
            TransactionStatus transactionStatus = platformTransactionManager.getTransaction(new DefaultTransactionDefinition());
            try {
                //修改年审审核状态
                papersYearInspectMapper.updateById(papersYearInspect);
                platformTransactionManager.commit(transactionStatus);
                //新增审核/退回操作记录；
                papersYearInspectProcessMapper.insert(papersYearInspectProcess);
                //流程组件 - 审核 - 退回
                if (Constants.WORKFLOW.equals(BoolUtils.Y)) {
                    Result<String> stringResult = new Result<>();
                    if (isApprove) {
                        //通过
                        SubmitInstanceVo submitInstanceVo = submitInstanceVoProcess(papersYearInspect.getId(), String.valueOf(papersYearInspect.getYear()), papersYearInspectProcess.getStatus(), papersYearInspectProcess.getAdvice(), papersYearInspectProcess.getPersonName());
                        stringResult = workApi.completeWorkItem(submitInstanceVo);
                    } else {
                        BackWorkItemForm backWorkItemForm = backWorkItemFormProcess(papersYearInspect.getId(), String.valueOf(papersYearInspect.getYear()), papersYearInspectProcess.getStatus(), papersYearInspectProcess.getAdvice(), papersYearInspectProcess.getPersonName());
                        stringResult = workApi.backWorkItem(backWorkItemForm);
                    }
                    if (!Result.CODE.equals(result.getCode())) {
                        throw new SofnException("加入流程失败");
                    }
                }
                return "1";
            } catch (Exception ex) {
                log.error("证书年审审核/退回失败：" + ex.getMessage());
                platformTransactionManager.rollback(transactionStatus);
            }

        }
        return result.getMsg();
    }

    /**
     * 获取审核/退回意见
     * wuXY
     * 2020-1-6 19:57:14
     *
     * @param inspectId 年审id
     * @param status    年审状态
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
     * 对象转换
     *
     * @param id                    主键
     * @param dateNow               当前时间
     * @param papersYearInspectForm 表单
     * @return PapersYearInspect对象
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
            //修改
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
     * 对象转换
     *
     * @param id                    年审主表id
     * @param papersYearInspectForm 表单对象
     * @return PapersYearInspectRetail对象
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
     * 对象转换
     *
     * @param inspectId             年审主表id
     * @param papersYearInspectForm 表单对象
     * @return PapersYearInspectRetail对象
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
     * 证书年审记录表转换
     * wuXY
     * 2020-1-7 11:01:17
     *
     * @param id            主键
     * @param inspectId     证书年审表主键
     * @param status        状态
     * @param advice        意见
     * @param dtNow         时间
     * @param userOrOrgName 人或组织机构名称
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
     * 上报 或 通过
     *
     * @param dataId     证书id
     * @param status     状态
     * @param advice     审核意见
     * @param personName 操作人员
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
     * 退回
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
