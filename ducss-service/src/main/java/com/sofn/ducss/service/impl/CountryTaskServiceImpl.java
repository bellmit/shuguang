package com.sofn.ducss.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sofn.common.exception.SofnException;
import com.sofn.common.model.Result;
import com.sofn.common.utils.IdUtil;
import com.sofn.common.utils.JsonUtils;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.UserUtil;
import com.sofn.ducss.enums.AuditStatusEnum;
import com.sofn.ducss.enums.Constants;
import com.sofn.ducss.enums.LogEnum;
import com.sofn.ducss.enums.RegionLevel;
import com.sofn.ducss.mapper.*;
import com.sofn.ducss.model.*;
import com.sofn.ducss.service.*;
import com.sofn.ducss.sysapi.SysApi;
import com.sofn.ducss.sysapi.bean.SysDict;
import com.sofn.ducss.sysapi.bean.SysOrganization;
import com.sofn.ducss.sysapi.bean.SysRegionTreeVo;
import com.sofn.ducss.util.*;
import com.sofn.ducss.util.vo.AreaRegionCode;
import com.sofn.ducss.vo.CountryTaskForm;
import com.sofn.ducss.vo.StrawUtilizeResVo3;
import com.sofn.ducss.vo.TapVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 获取年度任务
 */
@Service
@Slf4j
public class CountryTaskServiceImpl extends ServiceImpl<CountryTaskMapper, CountryTask> implements CountryTaskService {
    @Autowired
    private CountryTaskMapper countryTaskMapper;

    @Autowired
    private ProStillMapper proStillMapper;

    @Autowired
    private CollectFlowMapper collectFlowMapper;

    @Autowired
    private CollectFlowLogMapper collectFlowLogMapper;

    @Autowired
    private StrawProduceMapper strawProduceMapper;

    @Autowired
    private StrawUtilizeSumMapper strawUtilizeSumMapper;

    @Autowired
    private StrawUtilizeSumTotalMapper strawUtilizeSumTotalMapper;

    @Autowired
    private SysApi sysApi;

    @Autowired
    private ProStillDetailMapper proStillDetailMapper;

    @Autowired
    private DisperseUtilizeDetailMapper disperseUtilizeDetailMapper;

    @Autowired
    private StrawUtilizeDetailMapper strawUtilizeDetailMapper;

    @Autowired
    private MessageService messageService;

    @Autowired
    private ProductionUsageSumMapper productionUsageSumMapper;

    @Autowired
    private StrawUsageSumMapper strawUsageSumMapper;

    @Autowired
    private ReturnLeaveSumMapper returnLeaveSumMapper;

    @Autowired
    private SyncSysRegionService syncSysRegionService;

    @Autowired
    private CountyDataAnalysisService countyDataAnalysisService;

    @Autowired
    private CountyAggregateService countyAggregateService;

    @Override
    public PageUtils<CountryTask> getTaskByPage(Integer pageNo, Integer pageSize, String countyId, List<String> years) {
        PageHelper.offsetPage(pageNo, pageSize);
        Map<String, Object> params = Maps.newHashMap();
        params.put("years", years);
        params.put("countyId", countyId);
        List<CountryTask> list = countryTaskMapper.getTaskByPage(params);
        for (CountryTask countryTask : list) {
            String userId = countryTask.getCreateUserId();
            String userName = UserUtil.getUsernameById(userId);
            countryTask.setCreateUserName(userName);
        }
        return PageUtils.getPageUtils(new PageInfo(list));
    }

    @Override
    public PageUtils<CountryTask> getMinistryTaskByPage(Integer pageNo, Integer pageSize, List<String> years) {
        PageHelper.offsetPage(pageNo, pageSize);
        Map<String, Object> params = Maps.newHashMap();
        params.put("years", years);
        params.put("task_level", RegionLevel.MINISTRY.getCode());
        List<CountryTask> list = countryTaskMapper.getMinistryTaskByPage(params);
        for (CountryTask countryTask : list) {
            String userId = countryTask.getCreateUserId();
            String userName = UserUtil.getUsernameById(userId);
            countryTask.setCreateUserName(userName);
        }
        return PageUtils.getPageUtils(new PageInfo(list));
    }

    @Override
    public List<CountryTask> getTaskByCondition(Map<String, Object> param) {
        return countryTaskMapper.getCountryTaskByCondition(param);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String addOrUpdateMinistryTask(CountryTask task) {
        if (task.getExpectNum() == null || task.getExpectNum().intValue() <= 0) {
            throw new SofnException("操作失败，预计填报数应为大于0的正整数!");
        }
        task.setCreateDate(new Date());

        if (task.getId().equals("")) {
            // 看看当年的任务是否生成
            Map<String, Object> params = Maps.newHashMap();
            params.put("year", task.getYear());
            params.put("taskLevel", task.getTaskLevel());
            List<CountryTask> tasks = countryTaskMapper.getCountryTaskByCondition(params);
            if (tasks.size() <= 0) {
                task.setId(IdUtil.getUUId());
                task.setCreateDate(new Date());
                LogUtil.addLog(LogEnum.LOG_TYPE_TASK_EDIT.getCode(), "编辑-" + task.getYear() + "年填报任务");
                return countryTaskMapper.insertMinistryTask(task) ? "任务创建成功" : "任务创建失败";
            } else {
                throw new SofnException("添加失败，当年任务已经生成！");
            }
        } else {
            CountryTask taskOld = countryTaskMapper.selectByPrimaryKey(task.getId());
            if (null == taskOld) {
                throw new SofnException("部级任务基础数据异常，请检查数据库");
            }
            if (!taskOld.getYear().equals(task.getYear())) {
                Map<String, Object> params = Maps.newHashMap();
                params.put("year", task.getYear());
                params.put("taskLevel", task.getTaskLevel());
                List<CountryTask> tasks = countryTaskMapper.getCountryTaskByCondition(params);
                if (tasks.size() > 0) {
                    throw new SofnException("修改失败，欲修改年度任务已经生成!");
                }
            }
            countryTaskMapper.updateMinistryByCondition(task);
            LogUtil.addLog(LogEnum.LOG_TYPE_TASK_EDIT.getCode(), "编辑-" + task.getYear() + "年填报任务");
            return "编辑成功!";
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String addAndIssueMinistryTask(CountryTask task) {
        if (task.getExpectNum() == null || task.getExpectNum().intValue() <= 0) {
            throw new SofnException("操作失败，预计填报数应为大于0的正整数!");
        }
        task.setCreateDate(new Date());

        // 看看当年的任务是否生成
        Map<String, Object> params = Maps.newHashMap();
        params.put("year", task.getYear());
        params.put("taskLevel", task.getTaskLevel());
        List<CountryTask> tasks = countryTaskMapper.getCountryTaskByCondition(params);
        if (tasks.size() <= 0) {
            // 查询有哪些县级生成了任务
            Map<String, Object> countryQueryMap = Maps.newHashMap();
            countryQueryMap.put("year", task.getYear());
            countryQueryMap.put("taskLevel", Constants.ExamineState.ISSUE.toString());
            List<CountryTask> countryTaskList = countryTaskMapper.getCountryTaskByCondition(countryQueryMap);
            Map<String, CountryTask> countryTaskMap = countryTaskList.stream().collect(Collectors.toMap(CountryTask::getAreaId, Function.identity(), (key1, key2) -> key2));

            task.setId(IdUtil.getUUId());
            task.setCreateDate(new Date());
            countryTaskMapper.insertMinistryTask(task);
            LogUtil.addLog(LogEnum.LOG_TYPE_TASK_EDIT.getCode(), "编辑-" + task.getYear() + "年填报任务");
            List<CountryTask> list = new ArrayList<>();
            Result<SysRegionTreeVo> treeVoResult = sysApi.getSysRegionTree(null, null, null, null, null, Constants.APPID, null);
            if (treeVoResult != null && treeVoResult.getData() != null) {
                SysRegionTreeVo root = treeVoResult.getData();
                for (SysRegionTreeVo proTree : root.getChildren()) {
                    String provinceId = proTree.getRegionCode();
                    for (SysRegionTreeVo cityTree : proTree.getChildren()) {
                        String cityId = cityTree.getRegionCode();
                        for (SysRegionTreeVo county : cityTree.getChildren()) {
                            String countyId = county.getRegionCode();
                            if (countryTaskMap.get(countyId) != null) {
                                continue;
                            }
                            CountryTask countryTask = new CountryTask();
                            countryTask.setId(IdUtil.getUUId());
                            countryTask.setYear(task.getYear());
                            countryTask.setProvinceId(provinceId);
                            countryTask.setCityId(cityId);
                            countryTask.setAreaId(countyId);
                            countryTask.setExpectNum(task.getExpectNum());
                            countryTask.setStatus(Constants.ExamineState.SAVE);
                            countryTask.setIsReport((byte) 0);
                            countryTask.setTaskLevel(RegionLevel.COUNTY.getCode());
                            countryTask.setCreateUserId(task.getCreateUserId());
                            countryTask.setCreateUserName(task.getCreateUserName());
                            countryTask.setCreateDate(new Date());
                            list.add(countryTask);
                        }
                    }
                }
            }
            this.saveBatch(list, 500);

            Map<String, Object> params2 = Maps.newHashMap();
            params2.put("id", task.getId());
            params2.put("status", Constants.ExamineState.ISSUE);

            countryTaskMapper.updateStatus(params2);
            LogUtil.addLog(LogEnum.LOG_TYPE_TASK_DISTRIBUTION.getCode(), "下发-" + task.getYear() + "年度填报任务");
            return "下发成功!";
        } else {
            throw new SofnException("添加失败，当年任务已经生成！");
        }
    }

    @Override
    public String updateCountryTask(CountryTask task) {
        if (task.getExpectNum() == null || task.getExpectNum().intValue() <= 0) {
            throw new SofnException("操作失败，预计填报数应为大于0的正整数!");
        }
        countryTaskMapper.updateByCondition(task);
        return "编辑成功!";
    }

    @Override
    public CountryTaskForm getCountryTaskFormById(String id) {
        CountryTaskForm form = new CountryTaskForm();
        CountryTask task = countryTaskMapper.selectByPrimaryKey(id);
        BeanUtils.copyProperties(task, form);
        //TODO  查询实际填报数量
        form.setFactNum(0);

        return form;
    }

    @Override
    public Result deleteCountryTaskById(String id) {
        CountryTask task = countryTaskMapper.selectByPrimaryKey(id);
        if (null == task) {
            throw new SofnException("被操作的年度任务不存在");
        }
        if (task.getStatus() != Constants.ExamineState.SAVE) {
            throw new SofnException("只有未下发状态的任务可以删除！");
        }
        return this.removeById(id) ? Result.ok("删除成功") : Result.error("删除失败，请联系管理员");
    }

    /**
     * 处理县级审批流程
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result examineCountryTask(CountryTask task, String userName, String userId) {
        Byte status = task.getStatus();
        // 把前台选中的task传过来通过status来判断下一步做什么
        // 如果status是未上报，已撤回，已退回的状态那么下一步对应的操作是上报
        // 如果status是已上报，下一步操作是撤回
        Map<String, Object> params = Maps.newHashMap();
        params.put("year", task.getYear());
        params.put("areaId", task.getAreaId());
        countryTaskMapper.updateTaskFactMainNum(params);

        //判断部级任务是否已经公布数据，公布了数据不允许再进行任何操作
        Map<String, Object> params3 = Maps.newHashMap();
        params3.put("year", task.getYear());
        params3.put("taskLevel", RegionLevel.MINISTRY.getCode());
        List<CountryTask> tasks2 = countryTaskMapper.getCountryTaskByCondition(params3);
        if (tasks2.size() > 0) {
            if (tasks2.get(0).getStatus() == Constants.ExamineState.PUBLISH) {
                throw new SofnException("部级已公布该年度数据，无法进行操作!");
            }
        } else {
            throw new SofnException("未查询到部级任务!");
        }
        List<CountryTask> tasks = countryTaskMapper.getTasks(params);
        if (tasks.size() == 1) {
            CountryTask oldTask = tasks.get(0);
            if (oldTask.getStatus() == status) {
                if (status == Constants.ExamineState.SAVE || status == Constants.ExamineState.WITHDRAWN
                        || status == Constants.ExamineState.RETURNED) {
                    // 上报
                    // 查看生产量与直接还田量这张表是否填写
                    // 1、查看是否有上报的的flow有的话删除
                    // 2、修改task状态为已上报
                    // 3、汇总信息生成flow记录

                    //判断是否实际填报数大于预计填报数
                    Integer expectNum = task.getExpectNum();//预计填报数
                    Integer factNum = task.getFactNum();//实际填报数
                    if (factNum < expectNum) {
                        throw new SofnException("实际填报数小于预计填报数不允许上报操作！");
                    }
                    // 判断已退回的重新上报 必须上传修改说明附件
/*                    if (Constants.ExamineState.RETURNED.equals(status) && CollectionUtils.isEmpty(task.getFiles())) {
                        throw new SofnException("被退回重新上报需上传文件说明！");
                    }*/

                    // 县级上报 检查所有作物的综合利用率是否大于100%
                    StringBuilder sb = new StringBuilder();
                    List<StrawUtilizeResVo3> utilizeList = countyAggregateService.getCountyStrawUtilize2(task.getAreaId(), task.getYear());
                    for (StrawUtilizeResVo3 utilize : utilizeList) {
                        if (utilize.getCollectResource().compareTo(BigDecimal.ZERO) > 0 &&
                                utilize.getStrawUsage().divide(utilize.getCollectResource(), 10, BigDecimal.ROUND_HALF_UP).compareTo(BigDecimal.ONE) > 0) {
                            sb.append(utilize.getStrawName()).append("作物综合利用率超过100%;");
                        }
                    }
                    if (sb.length() > 0) {
                        throw new SofnException(sb.toString());
                    }

                    Map<String, Object> params2 = Maps.newHashMap();
                    params2.put("year", task.getYear());
                    params2.put("countyId", task.getAreaId());
                    List<ProStill> list = proStillMapper.getProStillByPage(params2);
                    if (list.size() >= 1) {// 生产量与直接还田量已经填写
                        collectFlowMapper.deleteCollectflow(params2);
                        countryTaskMapper.updateTaskStatus(task.getYear(), task.getAreaId(), Constants.ExamineState.REPORTED);

                        //防重提交，删除旧数据
                        // 删除秸秆产生量汇总表
                        StrawUtilizeSum strawUtilizeSum = new StrawUtilizeSum();
                        strawUtilizeSum.setAreaId(task.getAreaId());
                        strawUtilizeSum.setYear(task.getYear());
                        strawUtilizeSumMapper.deleteStrawUtilizeSum(strawUtilizeSum);
                        //删除新秸秆利用量汇总表 -zy1
                        strawUsageSumMapper.deleteStrawUtilizeSum(strawUtilizeSum);

                        // 删除秸秆利用量合计表
                        strawUtilizeSumTotalMapper.deleteStrawUtilizeSum(strawUtilizeSum);

                        //删除新产生量与利用量汇总表 -zy1
                        productionUsageSumMapper.deleteByYearandArea(strawUtilizeSum);

                        //删除还田离田情况汇总 -zy1
                        returnLeaveSumMapper.deleteStrawUtilizeSum(strawUtilizeSum);


                        //此处，会新增很多汇总数据
                        CollectFlow thisDucCollectFlow = createFlowByTask2(task);
                        collectFlowMapper.insertOrUpdate(thisDucCollectFlow); //单表汇总
                        status = Constants.ExamineState.REPORTED;
                        //xx县xxxx年度数据已上报
                        //获取县名
                        addReportMessage(task, AuditStatusEnum.REPORTED.getCode());

                        //插入中间表数据
                        countyDataAnalysisService.insertCountyDataAnalysis(task.getYear(), task.getAreaId());
                    } else {
                        throw new SofnException("请先填写生产量与直接还田量再执行上报操作！");
                    }
                } else if (status == Constants.ExamineState.REPORTED) {
                    // 退回操作
                    // 判断是否上传修改说明附件
                    if (StringUtils.isNotEmpty(task.getFiles())) {
                        throw new SofnException("请上传附件说明！");
                    }
                    Map<String, Object> params2 = Maps.newHashMap();
                    params2.put("year", task.getYear());
                    params2.put("countyId", task.getAreaId());
                    collectFlowMapper.deleteCollectflow(params2);
                    countryTaskMapper.updateTaskStatus(task.getYear(), task.getAreaId(),
                            Constants.ExamineState.WITHDRAWN);

                    // 删除汇总表
                    StrawProduce ducStrawProduce = new StrawProduce();
                    ducStrawProduce.setAreaId(task.getAreaId());
                    ducStrawProduce.setYear(task.getYear());
                    strawProduceMapper.deleteStrawProduce(ducStrawProduce);

                    // 删除秸秆产生量汇总表
                    StrawUtilizeSum strawUtilizeSum = new StrawUtilizeSum();
                    strawUtilizeSum.setAreaId(task.getAreaId());
                    strawUtilizeSum.setYear(task.getYear());
                    strawUtilizeSumMapper.deleteStrawUtilizeSum(strawUtilizeSum);

                    //删除新的秸秆利用汇总 -zy1
                    strawUsageSumMapper.deleteStrawUtilizeSum(strawUtilizeSum);

                    // 删除秸秆利用量合计表
                    strawUtilizeSumTotalMapper.deleteStrawUtilizeSum(strawUtilizeSum);
                    //删除新的产生汇总 -zy1
                    productionUsageSumMapper.deleteByYearandArea(strawUtilizeSum);
                    //删除还田离田汇总 -zy1
                    returnLeaveSumMapper.deleteStrawUtilizeSum(strawUtilizeSum);

                    // 2021-3-30 发现BUG 一直未删除中间表数据
                    countyDataAnalysisService.deleCountyDataAnalysis(task.getYear(), task.getAreaId());

                    status = Constants.ExamineState.WITHDRAWN;
                    addReportMessage(task, AuditStatusEnum.WITHDRAWN.getCode());
                }

                // 加入日志
                CollectFlowLog ducCollectFlowLog = new CollectFlowLog();
                ducCollectFlowLog.setId(IdUtil.getUUId());
                ducCollectFlowLog.setAreaId(task.getAreaId());
                ducCollectFlowLog.setYear(task.getYear());
                ducCollectFlowLog.setOperation(status + "");
                ducCollectFlowLog.setCreateTime(new Date());
                ducCollectFlowLog.setCreateUserName(userName);
                ducCollectFlowLog.setCreateUserId(userId);
                // 激活文件
                if (StringUtils.isNotEmpty(task.getFiles())) {
                    if (!FileUtil.activationFiles(task.getFiles())) {
                        throw new SofnException("上传文件异常");
                    }
                    ducCollectFlowLog.setFiles(task.getFiles());
                }
                //北京无法新增,暂时隐藏
                collectFlowLogMapper.insertFlowLog(ducCollectFlowLog);
                // 上报
                // 获取区域
                String areaId = task.getAreaId();
                String areaName = SysRegionUtil.getFullRegionNameByLastCode(areaId, task.getYear());
                LogUtil.addLog(LogEnum.LOG_TYPE_REPORTING.getCode(), "上报-" + task.getYear() + "年-" + areaName + "数据");


            } else {
                throw new SofnException("任务状态已经发生变化请刷新！");
            }
        } else {
            throw new SofnException("年度填报任务条数出现问题请检查！");
        }
        return Result.ok("操作成功！");
    }

    private void addReportMessage(CountryTask task, String status) {
        //xx县xxxx年度数据已撤回
        //获取县名
        String regionName = SysRegionUtil.getRegionNameByRegionCode(task.getAreaId(), task.getYear());
        //消息内容
        String text = "";
        //传来是保存,新增上报消息
        if (AuditStatusEnum.REPORTED.getCode().equals(status)) {
            text = regionName + "" + task.getYear() + "年度数据已上报";
            //新增上报消息
            messageService.addMessageByProcess(String.valueOf(Constants.ExamineState.REPORTED), null, text, task.getAreaId());
        }
        //新增撤回消息
        if (AuditStatusEnum.WITHDRAWN.getCode().equals(status)) {
            text = regionName + "" + task.getYear() + "年度数据已撤回";
            //或撤回消息消息
            messageService.addMessageByProcess(String.valueOf(Constants.ExamineState.WITHDRAWN), null, text, task.getAreaId());
        }
    }

    /*private CollectFlow createFlowByTask(CountryTask task) {
        // 计算汇总写入汇总表
        List<ProStillDetail> list = proStillDetailMapper.getProStillDetailListByAreaId(task.getAreaId(),
                task.getYear());
        BigDecimal totalCollectResource = new BigDecimal(0);
        BigDecimal totalTheoryResource = new BigDecimal(0);
        for (ProStillDetail deatil : list) {
            deatil.calculateResource();
            totalCollectResource = totalCollectResource.add(deatil.getCollectResource());
            totalTheoryResource = totalTheoryResource.add(deatil.getTheoryResource());
            StrawProduce ducStrawProduce = new StrawProduce();
            ducStrawProduce.setId(IdUtil.getUUId());
            ducStrawProduce.setAreaId(task.getAreaId());
            ducStrawProduce.setCollectResource(deatil.getCollectResource());
            ducStrawProduce.setStrawType(deatil.getStrawType());
            ducStrawProduce.setTheoryResource(deatil.getTheoryResource());
            ducStrawProduce.setYear(task.getYear());
            ///插入秸秆产生量汇总表
            strawProduceMapper.insertStrawProduce(ducStrawProduce);
        }

        List<StrawUtilizeSum> proList = selectDucStrawUtilizeSum(task.getAreaId(), task.getYear());
        BigDecimal mainNum = new BigDecimal(0);
        BigDecimal farmerSplitNum = new BigDecimal(0);// 农户分散利用量
        BigDecimal directReturnNum = new BigDecimal(0);// 直接还田量
        BigDecimal strawUtilizeNum = new BigDecimal(0);// 秸秆利用量
        BigDecimal otherTotal = new BigDecimal(0);// 收购外县量
        BigDecimal exportTotal = new BigDecimal(0);// 调出量

        if (null == proList) {
            throw new SofnException("请先填写分散利用量再执行上报操作！");
        }
        for (StrawUtilizeSum strawUtilizeSum : proList) {
            strawUtilizeSum.setId(IdUtil.getUUId());
            strawUtilizeSum.calculateNum();
            mainNum = mainNum.add(strawUtilizeSum.getMainTotal());
            farmerSplitNum = farmerSplitNum.add(strawUtilizeSum.getDisperseTotal());
            directReturnNum = directReturnNum.add(strawUtilizeSum.getProStillField());
            strawUtilizeNum = strawUtilizeNum.add(strawUtilizeSum.getProStrawUtilize());
            otherTotal = otherTotal.add(strawUtilizeSum.getMainTotalOther());
            exportTotal = exportTotal.add(strawUtilizeSum.getYieldAllExport());
        }
        // 插入秸秆利用量汇总表
        strawUtilizeSumMapper.insertBatchStrawUtilizeSum(proList);
        //合计是根据利用公式单独计算的所以需要单独存表不影响其他合计计算
        strawUtilizeSumTotalMapper.insertStrawUtilizeSumTotal(setCountrySum(proList, task));

        CollectFlow thisDucCollectFlow = new CollectFlow();
        thisDucCollectFlow.setId(IdUtil.getUUId());
        thisDucCollectFlow.setLevel((byte) 3);
        thisDucCollectFlow.setCreateDate(new Date());
        thisDucCollectFlow.setCreateUser(task.getCreateUserName());
        thisDucCollectFlow.setCreateUserId(task.getCreateUserId());
        thisDucCollectFlow.setStatus(Constants.ExamineState.REPORTED);
        thisDucCollectFlow.setAreaId(task.getAreaId());
        thisDucCollectFlow.setYear(task.getYear());
        thisDucCollectFlow.setIsreport((byte) 0);
        thisDucCollectFlow.setTheoryNum(totalTheoryResource);
        thisDucCollectFlow.setCollectNum(totalCollectResource);
        thisDucCollectFlow.setMainNum(mainNum);
        thisDucCollectFlow.setFarmerSplitNum(farmerSplitNum);
        thisDucCollectFlow.setDirectReturnNum(directReturnNum);
        strawUtilizeNum = strawUtilizeNum.subtract(otherTotal);
        thisDucCollectFlow.setStrawUtilizeNum(strawUtilizeNum);
        thisDucCollectFlow.setBuyOtherNum(otherTotal);
        thisDucCollectFlow.setExportNum(exportTotal);

        // 计算综合利用率
        if ((thisDucCollectFlow.getStrawUtilizeNum().compareTo(new BigDecimal(0)) > 0 || thisDucCollectFlow.getExportNum().compareTo(new BigDecimal(0)) > 0)
                && thisDucCollectFlow.getCollectNum().compareTo(new BigDecimal(0)) > 0) {
            //2019.4.17 算法修改，（本县秸秆利用量-收购外县的秸秆总量）+调出秸秆量/可收集量
            BigDecimal suu = thisDucCollectFlow.getStrawUtilizeNum()
                    .subtract(otherTotal)
                    .add(exportTotal)
                    .multiply(new BigDecimal(100))
                    .divide(thisDucCollectFlow.getCollectNum(), 10, RoundingMode.HALF_UP);

            thisDucCollectFlow.setSynUtilizeNum(suu);
        } else {
            thisDucCollectFlow.setSynUtilizeNum(new BigDecimal(0));
        }
        return thisDucCollectFlow;
    }*/

    private CollectFlow createFlowByTask2(CountryTask task) {
        // 计算汇总写入汇总表
        //查询产生量与直接还田量基础表
        List<ProStillDetail> list = proStillDetailMapper.getProStillDetailListByAreaId(task.getAreaId(),
                task.getYear());
        BigDecimal totalCollectResource = new BigDecimal(0);
        BigDecimal totalTheoryResource = new BigDecimal(0);
        for (ProStillDetail deatil : list) {
            deatil.calculateResource();
            totalCollectResource = totalCollectResource.add(deatil.getCollectResource());
            totalTheoryResource = totalTheoryResource.add(deatil.getTheoryResource());
            StrawProduce ducStrawProduce = new StrawProduce();
            ducStrawProduce.setId(IdUtil.getUUId());
            ducStrawProduce.setAreaId(task.getAreaId());
            ducStrawProduce.setCollectResource(deatil.getCollectResource());
            ducStrawProduce.setStrawType(deatil.getStrawType());
            ducStrawProduce.setTheoryResource(deatil.getTheoryResource());
            ducStrawProduce.setYear(task.getYear());
            ducStrawProduce.setSeedArea(deatil.getSeedArea());
            ducStrawProduce.setGrainYield(deatil.getGrainYield());
            ///插入秸秆产生量汇总表
            strawProduceMapper.insertStrawProduce(ducStrawProduce);
        }

        List<StrawUtilizeSum> proList = selectDucStrawUtilizeSum(task.getAreaId(), task.getYear());
        BigDecimal mainNum = new BigDecimal(0);
        BigDecimal farmerSplitNum = new BigDecimal(0);// 农户分散利用量
        BigDecimal directReturnNum = new BigDecimal(0);// 直接还田量
        BigDecimal strawUtilizeNum = new BigDecimal(0);// 秸秆利用量
        BigDecimal otherTotal = new BigDecimal(0);// 收购外县量
        BigDecimal exportTotal = new BigDecimal(0);// 调出量

        // 该字段用于计算综合利用量得，不做其他展示
        BigDecimal strawUtilizationV2 = BigDecimal.ZERO;
        BigDecimal collectResourceV2 = BigDecimal.ZERO;

        if (null == proList) {
            throw new SofnException("请先填写分散利用量再执行上报操作！");
        }
        for (StrawUtilizeSum strawUtilizeSum : proList) {
            strawUtilizeSum.setId(IdUtil.getUUId());
            //todo 感觉多算了一步
            strawUtilizeSum.calculateNum();
            mainNum = mainNum.add(strawUtilizeSum.getMainTotal());
            farmerSplitNum = farmerSplitNum.add(strawUtilizeSum.getDisperseTotal());
            directReturnNum = directReturnNum.add(strawUtilizeSum.getProStillField());
            strawUtilizeNum = strawUtilizeNum.add(strawUtilizeSum.getProStrawUtilize());
            otherTotal = otherTotal.add(strawUtilizeSum.getMainTotalOther());
            exportTotal = exportTotal.add(strawUtilizeSum.getYieldAllExport());
            strawUtilizeSum.setCollectResourceV2(strawUtilizeSum.getCollectResource());
            strawUtilizeSum.setStrawUtilizationV2(strawUtilizeSum.getProStrawUtilize());
            if (strawUtilizeSum.getProStrawUtilize().compareTo(BigDecimal.ZERO) > 0 && strawUtilizeSum.getCollectResource().compareTo(BigDecimal.ZERO) > 0) {
                strawUtilizationV2 = strawUtilizationV2.add(strawUtilizeSum.getProStrawUtilize());
                collectResourceV2 = collectResourceV2.add(strawUtilizeSum.getCollectResource());
            }
        }
        // 插入秸秆利用量汇总表
        strawUtilizeSumMapper.insertBatchStrawUtilizeSum(proList);

        //插入秸秆利用量汇总表新  2020-11-5 zy1
        strawUsageSumMapper.insertBatchStrawUsageSum(SumUtil.strawUsageSum(proList));

        //插入还田离田汇总 zy1
        returnLeaveSumMapper.insertBatchReturnLeaveSum(SumUtil.getReturnLeaveSum(proList));

        //合计是根据利用公式单独计算的所以需要单独存表不影响其他合计计算
        StrawUtilizeSum strawUtilizeSum = setCountrySum(proList, task);
        strawUtilizeSumTotalMapper.insertStrawUtilizeSumTotal(strawUtilizeSum);

        //按区域汇总。插入秸秆产生量与利用量新汇总表-2020-11-5 zy1
        productionUsageSumMapper.insertProductionUsageSum(SumUtil.getProductionUsageSum(strawUtilizeSum));


        CollectFlow thisDucCollectFlow = new CollectFlow();
        thisDucCollectFlow.setId(IdUtil.getUUId());
        thisDucCollectFlow.setLevel((byte) 3);
        thisDucCollectFlow.setCreateDate(new Date());
        thisDucCollectFlow.setCreateUser(task.getCreateUserName());
        thisDucCollectFlow.setCreateUserId(task.getCreateUserId());
        thisDucCollectFlow.setStatus(Constants.ExamineState.REPORTED);
        //--------2020.11.10 modify By chlf-------------//
        thisDucCollectFlow.setCityId(task.getCityId());
        thisDucCollectFlow.setProvinceId(task.getProvinceId());
        //--------------------end-----------------------//
        thisDucCollectFlow.setAreaId(task.getAreaId());
        thisDucCollectFlow.setYear(task.getYear());
        thisDucCollectFlow.setIsreport((byte) 0);
        thisDucCollectFlow.setTheoryNum(totalTheoryResource);
        thisDucCollectFlow.setCollectNum(totalCollectResource);
        thisDucCollectFlow.setMainNum(mainNum);
        thisDucCollectFlow.setFarmerSplitNum(farmerSplitNum);
        thisDucCollectFlow.setDirectReturnNum(directReturnNum);
        thisDucCollectFlow.setStrawUtilizeNum(strawUtilizeNum);
        thisDucCollectFlow.setBuyOtherNum(otherTotal);
        thisDucCollectFlow.setExportNum(exportTotal);
        thisDucCollectFlow.setCollectResourceV2(collectResourceV2);
        thisDucCollectFlow.setStrawUtilizationV2(strawUtilizationV2);

        // 计算综合利用率
        thisDucCollectFlow.setSynUtilizeNum(StrawCalculatorUtil2.calculationComprehensiveRote(strawUtilizeNum, totalCollectResource));
/*        if ((thisDucCollectFlow.getStrawUtilizeNum().compareTo(new BigDecimal(0)) > 0 || thisDucCollectFlow.getExportNum().compareTo(new BigDecimal(0)) > 0)
                && thisDucCollectFlow.getCollectNum().compareTo(new BigDecimal(0)) > 0) {
//            //2019.4.17 算法修改，（本县秸秆利用量-收购外县的秸秆总量）+调出秸秆量/可收集量
//            BigDecimal suu = thisDucCollectFlow.getStrawUtilizeNum()
//                    .subtract(otherTotal)
//                    .add(exportTotal)
//                    .multiply(new BigDecimal(100))
//                    .divide(thisDucCollectFlow.getCollectNum(), 10, RoundingMode.HALF_UP)
//                    ;

            thisDucCollectFlow.setSynUtilizeNum(StrawCalculatorUtil2.calculationComprehensiveRote(strawUtilizeNum, totalCollectResource));
        } else {
            thisDucCollectFlow.setSynUtilizeNum(new BigDecimal(0));
        }*/

        // 优炫数据库无法设置0e-10  需要处理一下！
        thisDucCollectFlow.setZeroValue();
        return thisDucCollectFlow;
    }


    private StrawUtilizeSum setCountrySum(List<StrawUtilizeSum> sumList, CountryTask task) {
        if (sumList == null || sumList.isEmpty()) {
            return null;
        }
        BigDecimal fertilising = new BigDecimal(0); // 肥料化
        BigDecimal forage = new BigDecimal(0);// 饲料化
        BigDecimal fuel = new BigDecimal(0);// 燃料化
        BigDecimal base = new BigDecimal(0);// 基料化
        BigDecimal material = new BigDecimal(0);// 原料化
        BigDecimal yieldAllNum = new BigDecimal(0); // 秸秆产量
        BigDecimal collectResource = new BigDecimal(0); // 可收集量

        BigDecimal mainFertilising = new BigDecimal(0); // 肥料化
        BigDecimal mainForage = new BigDecimal(0);// 饲料化
        BigDecimal mainFuel = new BigDecimal(0);// 燃料化
        BigDecimal mainBase = new BigDecimal(0);// 基料化
        BigDecimal mainMaterial = new BigDecimal(0);// 原料化
        BigDecimal mainTotalOther = new BigDecimal(0);// 其他县收购
        BigDecimal proStillField = new BigDecimal(0);// 直接还田量

        BigDecimal yieldAllExport = new BigDecimal(0);//调出量

        BigDecimal theoryResource = new BigDecimal(0); // 产生量

        BigDecimal disperseFertilising = new BigDecimal(0); // 分散 肥料化
        BigDecimal disperseForage = new BigDecimal(0); // 分散 饲料化
        BigDecimal disperseFuel = new BigDecimal(0); // 分散 燃料化
        BigDecimal disperseBase = new BigDecimal(0); // 分散 基料化
        BigDecimal disperseMaterial = new BigDecimal(0); // 分散 原料化

        BigDecimal collectResourceV2 = BigDecimal.ZERO; // 可收集量（只用来计算秸秆利用率）
        BigDecimal strawUtilizationV2 = BigDecimal.ZERO;


        StrawUtilizeSum sum = new StrawUtilizeSum();
        if (sumList != null) {
            for (StrawUtilizeSum item : sumList) {
                fertilising = item.getFertilising().add(fertilising);
                forage = item.getForage().add(forage);
                fuel = item.getFuel().add(fuel);
                base = item.getBase().add(base);
                material = item.getMaterial().add(material);
                yieldAllNum = item.getYieldAllNum().add(yieldAllNum);
                collectResource = item.getCollectResource().add(collectResource);

                mainFertilising = item.getMainFertilising().add(mainFertilising);
                mainForage = item.getMainForage().add(mainForage);
                mainFuel = item.getMainFuel().add(mainFuel);
                mainBase = item.getMainBase().add(mainBase);
                mainMaterial = item.getMainMaterial().add(mainMaterial);
                mainTotalOther = item.getMainTotalOther().add(mainTotalOther);
                proStillField = item.getProStillField().add(proStillField);
                theoryResource = item.getTheoryResource().add(theoryResource);

                yieldAllExport = yieldAllExport.add(item.getYieldAllExport());
                disperseFertilising = disperseFertilising.add(item.getDisperseFertilising());
                disperseForage = disperseForage.add(item.getDisperseForage());
                disperseFuel = disperseFuel.add(item.getDisperseFuel());
                disperseBase = disperseBase.add(item.getDisperseBase());
                disperseMaterial = disperseMaterial.add(item.getDisperseMaterial());

                // 只用于计算综合利用率
                BigDecimal mainTotal = item.getMainFertilising().add(item.getMainForage()).add(item.getMainFuel()).add(item.getMainBase()).add(item.getMainMaterial());
                BigDecimal disperseTotal = item.getDisperseFertilising().add(item.getDisperseForage()).add(item.getDisperseFuel()).add(item.getDisperseBase()).add(item.getDisperseMaterial());
                BigDecimal strawUtilization = StrawCalculatorUtil2.calculationStrawUtilize(mainTotal, disperseTotal, item.getProStillField(), item.getYieldAllExport(), item.getMainTotalOther());
                if (item.getCollectResource().compareTo(BigDecimal.ZERO) > 0 && strawUtilization.compareTo(BigDecimal.ZERO) > 0) {
                    collectResourceV2 = collectResourceV2.add(item.getCollectResource());
                    strawUtilizationV2 = strawUtilizationV2.add(strawUtilization);
                }
            }
        }
        sum.setDisperseFertilising(disperseFertilising);
        sum.setDisperseForage(disperseForage);
        sum.setDisperseFuel(disperseFuel);
        sum.setDisperseBase(disperseBase);
        sum.setDisperseMaterial(disperseMaterial);

        sum.setStrawName("合计");
        sum.setCollectResource(collectResource);
//  	if (yieldAllNum.compareTo(new BigDecimal(0)) > 0) {
//  	    sum.setDisperseFertilising(
//  		    fertilising.multiply(collectResource).divide(yieldAllNum, 10, RoundingMode.HALF_UP));
//  	    sum.setDisperseForage(forage.multiply(collectResource).divide(yieldAllNum, 10, RoundingMode.HALF_UP));
//  	    sum.setDisperseFuel(fuel.multiply(collectResource).divide(yieldAllNum, 10, RoundingMode.HALF_UP));
//  	    sum.setDisperseMaterial(material.multiply(collectResource).divide(yieldAllNum, 10, RoundingMode.HALF_UP));
//  	    sum.setDisperseBase(base.multiply(collectResource).divide(yieldAllNum, 10, RoundingMode.HALF_UP));
//  	}
        sum.setYear(task.getYear());
        sum.setAreaId(task.getAreaId());
        sum.setMainBase(mainBase);
        sum.setMainFertilising(mainFertilising);
        sum.setMainForage(mainForage);
        sum.setMainFuel(mainFuel);
        sum.setMainMaterial(mainMaterial);
        sum.setMainTotalOther(mainTotalOther);
        sum.setBase(mainBase.add(sum.getDisperseBase()));
        sum.setFertilising(mainFertilising.add(disperseFertilising));
        sum.setForage(mainForage.add(sum.getDisperseForage()));
        sum.setFuel(mainFuel.add(sum.getDisperseFuel()));
        sum.setMaterial(mainMaterial.add(sum.getDisperseMaterial()));
        sum.setReturnResource(proStillField);
        sum.setYieldAllExport(yieldAllExport);

        sum.setProStillField(proStillField);
        sum.setCollectResource(collectResource);
        sum.setTheoryResource(theoryResource);
        sum.setOrderNo(10000);
        sum.setCollectResourceV2(collectResourceV2);
        sum.setStrawUtilizationV2(strawUtilizationV2);

        sum.calculateNumNoDisperse();
        // 重新算秸秆利用率
        sum.setComprehensive(StrawCalculatorUtil2.calculationComprehensiveRote(sum.getStrawUtilizationV2(), sum.getCollectResourceV2()));
        sum.setId(IdUtil.getUUId());

        return sum;
    }

    @Override
    public List<StrawUtilizeSum> selectDucStrawUtilizeSum(String areaId, String year) {
        return getProList(areaId, year);
    }

    @Override
    public List<StrawUtilizeSum> selectDucStrawUtilizeSumStatus(String areaId, String year, List<String> status) {
        return getProListStatus(areaId, year, status);
    }

    private List<StrawUtilizeSum> getProList(String areaId, String year) {

        // 秸秆类型
        List<SysDictionary> dictList = new ArrayList<>();

        List<SysDict> list2 = sysApi.getDictListByType(Constants.DictionaryType.STRAW_TYPE).getData();
        for (SysDict sysDict : list2) {
            SysDictionary sysDictionary = new SysDictionary();
            sysDictionary.setDictKey(sysDict.getDictcode());
            sysDictionary.setDictValue(sysDict.getDictname());
            dictList.add(sysDictionary);
        }

        Map<String, SysDictionary> dictMap = new HashMap<String, SysDictionary>();

        for (SysDictionary dict : dictList) {
            dictMap.put(dict.getDictKey(), dict);
        }

        List<StrawUtilizeSum> proList = new ArrayList<StrawUtilizeSum>(dictList.size());
        // 查询秸秆生产量与直接还田量
        List<ProStillDetail> list = proStillDetailMapper.getProStillDetailListByAreaId(areaId, year);
        if (list.isEmpty()) {
            return null;
        }

        // 查询分散利用量
        List<DisperseUtilizeDetail> disList = disperseUtilizeDetailMapper.selectDetailByAreaId(areaId, year);

        Map<String, DisperseUtilizeDetail> detailMap = new HashMap<String, DisperseUtilizeDetail>();
        for (DisperseUtilizeDetail detail : disList) {
            detailMap.put(detail.getStrawType(), detail);
        }

        // 市场主体规模利用量
        List<StrawUtilizeDetail> delList = strawUtilizeDetailMapper.selectDetailSumByAreaId(areaId, year);

        if (disList.isEmpty() && delList.isEmpty()) {
            return null;
        }

        Map<String, StrawUtilizeDetail> utilizelMap = new HashMap<String, StrawUtilizeDetail>();
        for (StrawUtilizeDetail detail : delList) {
            utilizelMap.put(detail.getStrawType(), detail);
        }

        for (ProStillDetail deatil : list) {
            // 计算资源量
            deatil.calculateResource();
            StrawUtilizeSum sum = new StrawUtilizeSum();
            // 赋值秸秆生产量与直接还田量
            sum.setDucProStillDeatil(deatil);
            sum.setYear(year);
            sum.setAreaId(areaId);

            if (dictMap.containsKey(deatil.getStrawType())) {
                sum.setStrawName(dictMap.get(deatil.getStrawType()).getDictValue());
            }

            if (detailMap.containsKey(deatil.getStrawType())) {
                // 赋值分散利用量
                sum.setDucDisperseUtilizeDetail(detailMap.get(deatil.getStrawType()));
            }

            if (utilizelMap.containsKey(deatil.getStrawType())) {
                // 市场主体明细
                sum.setDucStrawUtilizeDetail(utilizelMap.get(deatil.getStrawType()));
            }
            sum.calculateNum();
            proList.add(sum);
        }
        return proList;
    }

    private List<StrawUtilizeSum> getProListStatus(String areaId, String year, List<String> status) {

        // 秸秆类型
        List<SysDictionary> dictList = new ArrayList<>();

        List<SysDict> list2 = sysApi.getDictListByType(Constants.DictionaryType.STRAW_TYPE).getData();
        for (SysDict sysDict : list2) {
            SysDictionary sysDictionary = new SysDictionary();
            sysDictionary.setDictKey(sysDict.getDictcode());
            sysDictionary.setDictValue(sysDict.getDictname());
            dictList.add(sysDictionary);
        }

        Map<String, SysDictionary> dictMap = new HashMap<String, SysDictionary>();

        for (SysDictionary dict : dictList) {
            dictMap.put(dict.getDictKey(), dict);
        }

        List<StrawUtilizeSum> proList = new ArrayList<StrawUtilizeSum>(dictList.size());
        // 查询秸秆生产量与直接还田量
        List<ProStillDetail> list = proStillDetailMapper.getProStillDetailListByAreaId2(areaId, year, status);
        if (list.isEmpty()) {
            return null;
        }

        // 查询分散利用量
        List<DisperseUtilizeDetail> disList = disperseUtilizeDetailMapper.selectDetailByAreaIdStatus(areaId, year, status);

        Map<String, DisperseUtilizeDetail> detailMap = new HashMap<String, DisperseUtilizeDetail>();
        for (DisperseUtilizeDetail detail : disList) {
            detailMap.put(detail.getStrawType(), detail);
        }

        // 市场主体规模利用量
        List<StrawUtilizeDetail> delList = strawUtilizeDetailMapper.selectDetailSumByAreaIdStatus(areaId, year, status);

        if (disList.isEmpty() && delList.isEmpty()) {
            return null;
        }

        Map<String, StrawUtilizeDetail> utilizelMap = new HashMap<String, StrawUtilizeDetail>();
        for (StrawUtilizeDetail detail : delList) {
            utilizelMap.put(detail.getStrawType(), detail);
        }

        for (ProStillDetail deatil : list) {
            // 计算资源量
            deatil.calculateResource();
            StrawUtilizeSum sum = new StrawUtilizeSum();
            // 赋值秸秆生产量与直接还田量
            sum.setDucProStillDeatil(deatil);
            sum.setYear(year);
            sum.setAreaId(areaId);

            if (dictMap.containsKey(deatil.getStrawType())) {
                sum.setStrawName(dictMap.get(deatil.getStrawType()).getDictValue());
            }

            if (detailMap.containsKey(deatil.getStrawType())) {
                // 赋值分散利用量
                sum.setDucDisperseUtilizeDetail(detailMap.get(deatil.getStrawType()));
            }

            if (utilizelMap.containsKey(deatil.getStrawType())) {
                // 市场主体明细
                sum.setDucStrawUtilizeDetail(utilizelMap.get(deatil.getStrawType()));
            }
            sum.calculateNum();
            proList.add(sum);
        }
        return proList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String issueMinistryTask(String id, String userId, String userName) {
        CountryTask task = countryTaskMapper.selectByPrimaryKey(id);
        if (task.getStatus() != Constants.ExamineState.SAVE) {
            throw new SofnException("当前任务不是未下发状态的部级任务，无法下发！");
        }
        String yewr = task.getYear();
        Integer expectNum = task.getExpectNum();
        List<CountryTask> list = new ArrayList<>();
        /*
        List<DucssRegionCopySys> regionInfo = syncSysRegionService.getAreaIdByLevel(RegionLevel.COUNTY.getCode(), task.getYear());
        if (CollectionUtils.isEmpty(regionInfo)) {
            throw new SofnException("未获取到区县信息");
        }
        for (DucssRegionCopySys item : regionInfo) {
            CountryTask countryTask = new CountryTask();
            countryTask.setId(IdUtil.getUUId());
            String parentIds = item.getParentIds();
            if (StringUtils.isBlank(parentIds)) {
                throw new SofnException("当前区划【" + item.getRegionName() + "】没有父节点信息");
            }
            String[] parentArr = parentIds.split("-");
            countryTask.setYear(yewr);
            countryTask.setProvinceId(parentArr[1]);
            countryTask.setCityId(parentArr[2]);
            countryTask.setAreaId(item.getRegionCode());
            countryTask.setExpectNum(expectNum);
            countryTask.setStatus(Constants.ExamineState.SAVE);
            countryTask.setIsReport((byte) 0);
            countryTask.setTaskLevel(RegionLevel.COUNTY.getCode());
            countryTask.setCreateUserId(userId);
            countryTask.setCreateUserName(userName);
            countryTask.setCreateDate(new Date());
            list.add(countryTask);
        }
        */
        // 查询已下发任务的县
        Map<String, Object> countryQueryMap = Maps.newHashMap();
        countryQueryMap.put("year", task.getYear());
        countryQueryMap.put("taskLevel", Constants.ExamineState.ISSUE.toString());
        List<CountryTask> countryTaskList = countryTaskMapper.getCountryTaskByCondition(countryQueryMap);
        Map<String, CountryTask> countryTaskMap = countryTaskList.stream().collect(Collectors.toMap(CountryTask::getAreaId, Function.identity(), (key1, key2) -> key2));

        Result<SysRegionTreeVo> treeVoResult = sysApi.getSysRegionTree(null, null, null, null, null, Constants.APPID, null);
        if (treeVoResult != null && treeVoResult.getData() != null) {
            SysRegionTreeVo root = treeVoResult.getData();
            for (SysRegionTreeVo proTree : root.getChildren()) {
                String provinceId = proTree.getRegionCode();
                for (SysRegionTreeVo cityTree : proTree.getChildren()) {
                    String cityId = cityTree.getRegionCode();
                    for (SysRegionTreeVo county : cityTree.getChildren()) {
                        String countyId = county.getRegionCode();
                        if (countryTaskMap.get(countyId) != null) {
                            continue;
                        }
                        CountryTask countryTask = new CountryTask();
                        countryTask.setId(IdUtil.getUUId());
                        countryTask.setYear(yewr);
                        countryTask.setProvinceId(provinceId);
                        countryTask.setCityId(cityId);
                        countryTask.setAreaId(countyId);
                        countryTask.setExpectNum(expectNum);
                        countryTask.setStatus(Constants.ExamineState.SAVE);
                        countryTask.setIsReport((byte) 0);
                        countryTask.setTaskLevel(RegionLevel.COUNTY.getCode());
                        countryTask.setCreateUserId(userId);
                        countryTask.setCreateUserName(userName);
                        countryTask.setCreateDate(new Date());
                        list.add(countryTask);
                    }
                }
            }
        } else {
            return "未查询到对应行政区划！";
        }
        this.saveBatch(list, 500);
        Map<String, Object> params = Maps.newHashMap();
        params.put("id", id);
        params.put("status", Constants.ExamineState.ISSUE);
        countryTaskMapper.updateStatus(params);
        LogUtil.addLog(LogEnum.LOG_TYPE_TASK_DISTRIBUTION.getCode(), "下发-" + task.getYear() + "年填报任务");
        return "下发成功!";
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String publishMinistryTaskInfo(String id, String userId, String userName) {
        CountryTask task = countryTaskMapper.selectByPrimaryKey(id);
        if (task.getStatus() == Constants.ExamineState.PUBLISH) {
            throw new SofnException("当前任务已公布，请勿重复公布数据！");
        }
        if (task.getStatus() != Constants.ExamineState.ISSUE) {
            throw new SofnException("当前任务不是已下发状态的部级任务，无法公布数据！");
        }

        Map<String, Object> params = Maps.newHashMap();
        params.put("id", id);
        params.put("status", Constants.ExamineState.PUBLISH);
        countryTaskMapper.updateStatus(params);
        LogUtil.addLog(LogEnum.LOG_TYPE_RELEASE_DATA.getCode(), "公布-" + task.getYear() + "年填报任务");
        return "公布数据成功!";
    }

//    @Override
//    public void insertOrUpdateSuperTaskNum(String year, String areaId) {
//        CountryTask queryDct = new CountryTask();
//        queryDct.setAreaId(areaId);
//        queryDct.setYear(year);
//
//        CountryTask dct = countryTaskMapper.selectOneByBean(queryDct);
//
//
//        if (dct == null) { //新增, 只初始化最简单的数据，下一步更新实时数据
//            dct = new CountryTask();
//            dct.setYear(queryDct.getYear());
//            dct.setAreaId(queryDct.getAreaId());
//            dct.setFactNum(0);
//            dct.setExpectNum(0);
//            dct.setMainNum(0);
//            dct.setStatus((byte)0);
//            dct.setCreateDate(new Date());
//            ducCountryTaskDao.insertSelective(dct);
//        }
//        //更新汇总数据
//        ducCountryTaskDao.updateTaskNumWithSub(dct.getId(),dct.getAreaId(),dct.getYear());
//    }

    @Override
    public TapVo getTapVo(String year, String regioncode) {
        TapVo tapVo = new TapVo();
        Result<List<SysRegionTreeVo>> listByParentId = sysApi.getListByParentId(regioncode, Constants.APPID, "", null);
        List<SysRegionTreeVo> sysRegionTreeVoList = listByParentId.getData();

        int regionReportNum = 0;
        for (SysRegionTreeVo sysRegionTreeVo : sysRegionTreeVoList) {
            String areaId = sysRegionTreeVo.getRegionCode();

            Map<String, Object> params = Maps.newHashMap();
            params.put("year", year);
            params.put("status", Constants.ExamineState.PASSED);
            params.put("areaId", areaId);
            CollectFlow collectFlow = collectFlowMapper.getForAreaId(params);
            if (null != collectFlow) {
                regionReportNum++;
            }
        }

        tapVo.setRegionTotalNum(sysRegionTreeVoList.size());
        tapVo.setRegionPassNum(regionReportNum);
        return tapVo;
    }

    @Override
    public List<String> getCountryTaskYearList() {
        //根据token获取登录用户Id
        String loginToken = UserUtil.getLoginToken();
        if (org.apache.commons.lang.StringUtils.isBlank(loginToken)) {
            throw new SofnException("当前登录用户异常");
        }
        String userAreaByLogUser = UserUtil.getLoginUserOrganizationInfo();
        if (org.springframework.util.StringUtils.isEmpty(userAreaByLogUser)) {
            throw new SofnException("未获取到登录用户所属机构信息!");
        }
        SysOrganization sysOrganization = JsonUtils.json2obj(userAreaByLogUser, SysOrganization.class);
        //判断当前用户级别
        if (!RegionLevel.MINISTRY.getCode().equals(sysOrganization.getOrganizationLevel()) && !RegionLevel.PROVINCE.getCode().equals(sysOrganization.getOrganizationLevel())) {
            throw new SofnException("当前用户不是部级用户或省级用户！");
        }
        List<String> list = countryTaskMapper.getCountryTaskYearList(Lists.newArrayList(Constants.ExamineState.PUBLISH.toString()), RegionLevel.MINISTRY.getCode());
        if (list != null) {
            return list;
        }
        return new ArrayList<>();
    }

    @Override
    public List<String> listYear() {
        //根据token获取登录用户Id
        String loginToken = UserUtil.getLoginToken();
        if (org.apache.commons.lang.StringUtils.isBlank(loginToken)) {
            throw new SofnException("当前登录用户异常");
        }
        String userAreaByLogUser = UserUtil.getLoginUserOrganizationInfo();
        if (org.springframework.util.StringUtils.isEmpty(userAreaByLogUser)) {
            throw new SofnException("未获取到登录用户所属机构信息!");
        }
        List<String> list = countryTaskMapper.getCountryTaskYearList(Lists.newArrayList(Constants.ExamineState.ISSUE.toString(), Constants.ExamineState.PUBLISH.toString()), RegionLevel.MINISTRY.getCode());
        if (CollectionUtils.isNotEmpty(list)) {
            // 特殊处理
            AreaRegionCode currentRegionCode = SysRegionUtil.getRegionCode();
//            if ("330000".equals(currentRegionCode.getProvinceId())
//                    || "360000".equals(currentRegionCode.getProvinceId())
//                    || "130000".equals(currentRegionCode.getProvinceId())
//                    || "510000".equals(currentRegionCode.getProvinceId())
//                    || "340000".equals(currentRegionCode.getProvinceId())) {
//                if (!list.contains("2099")) {
//                    list.add("2099");
//                }
//            }
//            if ("330000".equals(currentRegionCode.getProvinceId())
//                    || "360000".equals(currentRegionCode.getProvinceId())
//               ) {
//                if (!list.contains("2021")) {
//                    list.add("2021");
//                }
//            }
            return list;
        }
        return new ArrayList<>();
    }

    @Override
    public List<CountryTask> listByAreaIdsAndYears(List<String> areaIds, List<String> years) {
        return countryTaskMapper.listByAreaIdsAndYears(areaIds, years);
    }
}
