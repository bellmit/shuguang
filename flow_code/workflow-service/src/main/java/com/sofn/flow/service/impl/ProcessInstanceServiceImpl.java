package com.sofn.flow.service.impl;

import com.cvicse.workflow.api.*;
import com.cvicse.workflow.api.impl.WfActivityInstanceImpl;
import com.cvicse.workflow.api.query.entity.HistoryEntity;
import com.cvicse.workflow.api.query.entity.RunningEntity;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sofn.flow.annotation.GetFlowConnection;
import com.sofn.flow.enums.ExistEnum;
import com.sofn.flow.exception.SofnException;
import com.sofn.flow.service.ActService;
import com.sofn.flow.service.ProcessInstanceService;
import com.sofn.flow.service.ProcessWorkItemService;
import com.sofn.flow.util.*;
import com.sofn.flow.vo.*;
import com.sofn.infroflow.model.SysUser;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 *
 * 流程实例相关
 *
 * @author heyongjie
 * @date 2020/5/8 17:19
 */
@Service
public class ProcessInstanceServiceImpl implements ProcessInstanceService {

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    ProcessWorkItemService processWorkItemService;

    @Autowired
    ActService actService;

    @Override
    @GetFlowConnection
    public Result<String> startProcess(WfClient wfClient, SubmitInstanceVo vo) {
        // @GetFlowConnection  已经处理了事务
        // 该接口可以直接根据流程定义ID将第一个活动提交
        // 判断流程实例是否存在
        String loginUserName = redisUtil.getLoginUserName();
        if (StringUtils.isBlank(loginUserName)) {
            return Result.noLogin();
        }

        // 生成流程实例名称
        /*WdModelDefinition wdModelDefinition = ((ProcessDefinitionServiceImpl)AopContext.currentProxy())
                .getDefinitionByDefId(null, definitionId);
        if (wdModelDefinition == null) {
            throw new SofnException("流程实例不存在");
        }
        String processName = wdModelDefinition.getProcessName()+System.currentTimeMillis();*/

        // 1. 获取流程实例ID
        String instId = createProcessInstance(wfClient, vo.getDefId(), String.valueOf(System.currentTimeMillis()),
                vo.getIdAttrName(), vo.getIdAttrValue());
        if (instId == null) {
            throw new SofnException("流程实例创建失败");
        }

        // 2. 启动流程实例
        Result<String> result1 = startProcessInstance(wfClient, loginUserName, instId);
        if (result1 == null) {
            throw new SofnException("流程实例启动失败");
        }

        if (StringUtils.isBlank(result1.getData())) {
            throw new SofnException("流程实例启动失败");
        }

        // 3 完成活动
        return processWorkItemService.completeWorkItem(wfClient, vo);
    }


    @Override
    @GetFlowConnection
    public String createProcessInstance(WfClient wfClient, String defId, String instName, String idAttrName, String idAttrValue) {
        if (StringUtils.isBlank(defId)) {
            throw new SofnException("流程定义ID不能为空");
        }

        if (StringUtils.isBlank(instName)) {
            throw new SofnException("流程实例名称不能为空");
        }

        if (StringUtils.isBlank(idAttrName)) {
            throw new SofnException("流程属性名称不能为空");
        }

        if (StringUtils.isBlank(idAttrValue)) {
            throw new SofnException("流程属性值不能为空");
        }

        String instId = getInstIdByIdAttr(wfClient, defId, idAttrName, idAttrValue, false);
        if (StringUtils.isBlank(instId)) {
            instId = wfClient.createProcessInstance(defId, instName);
            if (StringUtils.isBlank(instId)) {
                throw new SofnException("流程实例创建失败");
            }

            // 分配属性
            wfClient.assignProcessInstanceAttribute(instId, idAttrName, idAttrValue);
        }

        return instId;
    }

    @Override
    @GetFlowConnection
    public Result<List<ProcessInstanceVo>> getAllProcessInstance(WfClient client, String isHistory) {
        List<WfFilter> wfFilterList = new ArrayList<>();
        if (BooleanUtil.isTrue(isHistory)) {
            // 查询历史
            WfFilter wfFilterAnd = new WfFilter(HistoryEntity.ProcessCreator, WfFilter.EXPRESSION_EQUAL, redisUtil.getLoginUserName());
            wfFilterAnd.setProcessClosed(true);
            wfFilterList.add(wfFilterAnd);
        } else {
            // 不查询历史
            WfFilter wfFilterAnd = new WfFilter(RunningEntity.ProcessCreator, WfFilter.EXPRESSION_EQUAL, redisUtil.getLoginUserName());
            wfFilterList.add(wfFilterAnd);
        }
        // 转为VO
        List list = client.listProcessInstances(WfFilterUtil.getWfFilterAnd(wfFilterList));
        List<ProcessInstanceVo> processInstanceVos = new ArrayList<>();
        Set<String> usernames = new HashSet<>();
        if (!CollectionUtils.isEmpty(list)) {
            for (Object obj : list) {
                if (obj != null) {
                    WfProcessInstance wfProcessInstance = (WfProcessInstance) obj;
                    usernames.add(wfProcessInstance.getCreator());
                    ProcessInstanceVo processInstanceVo = new ProcessInstanceVo().getVo(wfProcessInstance);
                    processInstanceVos.add(processInstanceVo);
                }
            }
        }
        setNickName(usernames, processInstanceVos);
        return Result.ok(processInstanceVos);
    }

    @GetFlowConnection
    @Override
    public List<ProcessInstanceVo> getProcessInstanceByAttr(WfClient wfClient, String defId, String attrName, String attrValue) {
        List<WfFilter> wfFilterList = new ArrayList<>();

        WfFilter wfFilterDef = new WfFilter(RunningEntity.ProcessDefId, WfFilter.EXPRESSION_EQUAL, defId);
        WfFilter wfFilterName = new WfFilter(RunningEntity.ProcessContextName, WfFilter.EXPRESSION_EQUAL, attrName);
        WfFilter wfFilterValue = new WfFilter(RunningEntity.ProcessContextValue, WfFilter.EXPRESSION_EQUAL, attrValue);

        wfFilterList.add(wfFilterDef);
        wfFilterList.add(wfFilterName);
        wfFilterList.add(wfFilterValue);

        List list = wfClient.listProcessInstances(WfFilterUtil.getWfFilterAnd(wfFilterList));

        List<ProcessInstanceVo> processInstanceVos = new ArrayList<>();
        Set<String> usernames = new HashSet<>();
        if (!CollectionUtils.isEmpty(list)) {
            for (Object obj : list) {
                if (obj != null) {
                    WfProcessInstance wfProcessInstance = (WfProcessInstance) obj;
                    usernames.add(wfProcessInstance.getCreator());
                    ProcessInstanceVo processInstanceVo = new ProcessInstanceVo().getVo(wfProcessInstance);
                    processInstanceVos.add(processInstanceVo);
                }
            }
        }
        setNickName(usernames, processInstanceVos);
        return processInstanceVos;
    }

    @Override
    public List<ProcessInstanceVo> getProcessHisInstanceByAttr(WfClient wfClient, String defId, String attrName, String attrValue) {
        List<WfFilter> wfFilterList = new ArrayList<>();

        WfFilter wfFilterDef = new WfFilter(HistoryEntity.ProcessDefId, WfFilter.EXPRESSION_EQUAL, defId);
        WfFilter wfFilterName = new WfFilter(HistoryEntity.ProcessContextName, WfFilter.EXPRESSION_EQUAL, attrName);
        WfFilter wfFilterValue = new WfFilter(HistoryEntity.ProcessContextValue, WfFilter.EXPRESSION_EQUAL, attrValue);

        wfFilterList.add(wfFilterDef);
        wfFilterList.add(wfFilterName);
        wfFilterList.add(wfFilterValue);

        WfFilter wfFilter = WfFilterUtil.getWfFilterAnd(wfFilterList);
        wfFilter.setProcessClosed(true);
        List list = wfClient.listProcessInstances(wfFilter);

        List<ProcessInstanceVo> processInstanceVos = new ArrayList<>();
        Set<String> usernames = new HashSet<>();
        if (!CollectionUtils.isEmpty(list)) {
            for (Object obj : list) {
                if (obj != null) {
                    WfProcessInstance wfProcessInstance = (WfProcessInstance) obj;
                    usernames.add(wfProcessInstance.getCreator());
                    ProcessInstanceVo processInstanceVo = new ProcessInstanceVo().getVo(wfProcessInstance);
                    processInstanceVos.add(processInstanceVo);
                }
            }
        }
        setNickName(usernames, processInstanceVos);
        return processInstanceVos;
    }

    private void setNickName(Set<String> usernames, List<ProcessInstanceVo> processInstanceVos) {
        if (!CollectionUtils.isEmpty(usernames) && !CollectionUtils.isEmpty(processInstanceVos)) {
            List<SysUser> userInfoByUserNames = UserUtil.getUserInfoByUserNames(usernames);
            if (!CollectionUtils.isEmpty(userInfoByUserNames)) {
                for (ProcessInstanceVo processInstanceVo : processInstanceVos) {
                    processInstanceVo.setCreateUser(UserUtil.getUserNickName(processInstanceVo.getCreateUser(), userInfoByUserNames));
                }
            }
        }
    }

    @Override
    @GetFlowConnection
    public Result<String> setProcessInstanceOrgId(WfClient client, String processInstanceId, String orgId) {
        if (StringUtils.isBlank(processInstanceId)) {
            return Result.error("processInstanceId不能为空");
        }
        if (StringUtils.isBlank(orgId)) {
            return Result.error("orgId不能为空");
        }
        client.resetProcessInstCode(processInstanceId, orgId);
        return Result.ok("重置成功");
    }

    @Override
    @GetFlowConnection
    public Result<String> startProcessInstance(WfClient client, String userName, String processInstanceId) {
        if (StringUtils.isBlank(userName)) {
            return Result.error("userName不能为空");
        }

        if (StringUtils.isBlank(processInstanceId)) {
            return Result.error("processInstanceId不能为空");
        }

        WfProcessInstance processInstance = client.getProcessInstance(processInstanceId);
        if (processInstance == null) {
            return Result.error("processInstanceId不存在");
        }

        //查询当前流程实例是否已经启动，已启动无需再次启动
        boolean isStarted = getInstIsRunning(client, userName, processInstanceId);

        if (!isStarted) {
            // ID在此流程的生命周期内有效
            client.startProcess(userName, processInstanceId);
        }

        return Result.ok((Object) processInstanceId);
    }

    @Override
    @GetFlowConnection
    public Result<String> startProcessInstanceByToken(WfClient client, String token, String processInstanceId) {
        if (StringUtils.isBlank(token)) {
            return Result.error("token不能为空");
        }

        if (StringUtils.isBlank(processInstanceId)) {
            return Result.error("processInstanceId不能为空");
        }

        WfProcessInstance processInstance = client.getProcessInstance(processInstanceId);
        if (processInstance == null) {
            return Result.error("processInstanceId不存在");
        }

        String loginUserId = redisUtil.getLoginUserId(token);
        if (StringUtils.isBlank(loginUserId)) {
            return Result.error("未查询到登录用户");
        }
        // ID在此流程的生命周期内有效
        String processId = client.startProcess(loginUserId, processInstanceId);
        return Result.ok((Object) processId);
    }

    @Override
    @GetFlowConnection
    public boolean stopProcessInstance(WfClient client, String processInstanceId) {
        WfProcessInstance processInstance = client.getProcessInstance(processInstanceId);
        if (processInstance == null) {
            throw new SofnException("流程实例不存在");
        }

        client.terminateProcessInstance(processInstanceId);
        return true;
    }

    @Override
    public boolean stopProcessInstance(WfClient wfClient, String defId, String idAttrName, String idAttrValue) {
        String instId = getInstIdByIdAttr(wfClient, defId, idAttrName, idAttrValue, false);
        if (StringUtils.isBlank(instId)) {
            throw new SofnException("未找到流程实例");
        }
        return stopProcessInstance(wfClient, instId);
    }

    @Override
    @GetFlowConnection
    public boolean cancelProcessInstance(WfClient client, String processInstanceId) {
        WfProcessInstance processInstance = client.getProcessInstance(processInstanceId);
        if (processInstance == null) {
            throw new SofnException("流程实例不存在");
        }
        client.abortProcessInstance(processInstanceId);
        return true;
    }

    @Override
    public boolean cancelProcessInstance(WfClient wfClient, String defId, String idAttrName, String idAttrValue) {
        String instId = getInstIdByIdAttr(wfClient, defId, idAttrName, idAttrValue, false);
        if (StringUtils.isBlank(instId)) {
            throw new SofnException("未找到流程实例");
        }
        return cancelProcessInstance(wfClient, instId);
    }

    @Override
    @GetFlowConnection
    public Result<String> getProcessInstanceState(WfClient client, String processInstanceId) {
        WfProcessInstance processInstance = client.getProcessInstance(processInstanceId);
        if (processInstance == null) {
            return Result.error("processInstanceId不存在");
        }
        return Result.ok((Object) processInstance.getState());
    }

    @Override
    @GetFlowConnection
    public Result<Map<String, Object>> getProcessInstanceAttr(WfClient client, String processInstanceId) {
        WfProcessInstance processInstance = client.getProcessInstance(processInstanceId);
        if (processInstance == null) {
            return Result.error("processInstanceId不存在");
        }
        Map map = client.listProcessInstanceAttributes(processInstanceId, null);
        Map<String, Object> maps = new HashMap<>(10);
        for (Object key : map.keySet()) {
            maps.put(key.toString(), map.get(key));
        }
        return Result.ok(maps);
    }

    @Override
    @GetFlowConnection
    public boolean reviveProcessInstance(WfClient client, String processInstanceId) {
        WfProcessInstance processInstance = client.getProcessInstance(processInstanceId);
        if (processInstance == null) {
            throw new SofnException("流程实例不存在");
        }
        client.reviveProcess(processInstanceId);
        return true;
    }

    @Override
    public boolean reviveProcessInstance(WfClient wfClient, String defId, String idAttrName, String idAttrValue) {
        String instId = getInstIdByIdAttr(wfClient, defId, idAttrName, idAttrValue, false);
        if (StringUtils.isBlank(instId)) {
            throw new SofnException("未找到流程实例");
        }
        return reviveProcessInstance(wfClient, instId);
    }

    @Override
    @GetFlowConnection
    public boolean delProcessInstance(WfClient client, String processInstanceId) {
        WfProcessInstance processInstance = client.getProcessInstance(processInstanceId);
        if (processInstance == null) {
            throw new SofnException("流程实例不存在");
        }
        client.removeProcessInstance(processInstanceId, false, false);
        return true;
    }

    @Override
    public boolean delProcessInstance(WfClient wfClient, String defId, String idAttrName, String idAttrValue) {
        String instId = getInstIdByIdAttr(wfClient, defId, idAttrName, idAttrValue, false);
        if (StringUtils.isBlank(instId)) {
            throw new SofnException("未找到流程实例");
        }
        return delProcessInstance(wfClient, instId);
    }

    @Override
    @GetFlowConnection
    public Result<String> setValues(WfClient wfClient, Map<String, Object> values, String processInstanceId) {
        if (CollectionUtils.isEmpty(values)) {
            return Result.error("没有需要设置的属性");
        }
        WfProcessInstance processInstance = wfClient.getProcessInstance(processInstanceId);
        if (processInstance == null) {
            return Result.error("processInstanceId不存在");
        }
        for (String key : values.keySet()) {
            Object value = values.get(key);
            if (value != null) {
                wfClient.assignProcessInstanceAttribute(processInstanceId, key, value);
            }
        }
        return Result.ok("设置属性成功");
    }

    @Override
    public String getInstIdByIdAttr(WfClient client, String defId, String attrName, String attrValue, boolean includeHis) {
        List<ProcessInstanceVo> processList = getProcessInstanceByAttr(client, defId, attrName, attrValue);
        if (CollectionUtils.isEmpty(processList) && includeHis) {
            processList = getProcessHisInstanceByAttr(client, defId, attrName, attrValue);
        }

        if (CollectionUtils.isEmpty(processList)) {
            return null;
        }

        if (processList.size() > 1) {
            throw new SofnException("找到多个流程实例");
        }

        return processList.get(0).getId();
    }

    /**
     * 查询当前流程实例是否已经启动
     *
     * @param client
     * @param userName
     * @param instId
     * @return
     */
    private boolean getInstIsRunning(WfClient client, String userName, String instId) {
        //
        List<WfFilter> wfFilterList = new ArrayList<>();
        WfFilter wfFilterDef = new WfFilter(RunningEntity.ProcessId, WfFilter.EXPRESSION_EQUAL, instId);
        WfFilter wfFilterName = new WfFilter(RunningEntity.WorkItemPerformer, WfFilter.EXPRESSION_EQUAL, userName);
        WfFilter wfFilterValue = new WfFilter(RunningEntity.ProcessCurState, WfFilter.EXPRESSION_EQUAL, Global.RUNNING);

        wfFilterList.add(wfFilterDef);
        wfFilterList.add(wfFilterName);
        wfFilterList.add(wfFilterValue);

        List list = client.listProcessInstances(WfFilterUtil.getWfFilterAnd(wfFilterList));

        return list != null && list.size() > 0;
    }

    @Override
    @GetFlowConnection
    public HisProcInstVo getProcessInstHisByIdAttr(WfClient wfClient, String defId, String idAttrName, String idAttrValue, boolean includeHis) {
        ExistEnum existEnum = getInstExist(wfClient, defId, idAttrName, idAttrValue);
        if (existEnum.isRunning()) {
            return getInstStepByIdAttr(wfClient, defId, idAttrName, idAttrValue);
        }

        if (existEnum.isHistory()) {
            return getFinishInstStepByIdAttr(wfClient, defId, idAttrName, idAttrValue);
        }

        return null;
    }

    @Override
    public List<ProcessVo> getProcessInstAllByAttr(List<String> defIds, String idAttrName, List<String> idAttrValues, List<InstanceParamVo> instanceParamVos, String startTime, String endTime, Integer pageNo, Integer pageSize) {
        List<ProcessVo> list2 = actService.getProcessInstAllByAttr(defIds,idAttrName,idAttrValues,  instanceParamVos, startTime, endTime);
        return list2;
    }

//    @Override
//    public PageUtils<ProcessVo> getProcessInstHisByAttr(String defId, Map<String, Object> attrs, int pageNo, int pageSize) {
//        /*WfProcessDefinition wfProcessDefinition = wfClient.getProcessDefinition(defId);
//        if (wfProcessDefinition == null) {
//            throw new SofnException("未找到流程定义");
//        }*/
//
//        // 查询流程实例参数
//        /*List<WfFilter> filterList = new ArrayList<>();
//        // 查询流程实例历史记录
//        WfFilter defFilter = new WfFilter(RunningEntity.ProcessDefId, WfFilter.EXPRESSION_EQUAL, defId);
//        filterList.add(defFilter);
//        if (!CollectionUtils.isEmpty(attrs)) {
//            for (Map.Entry<String, Object> entry : attrs.entrySet()) {
//                WfFilter filterKey = new WfFilter(RunningEntity.ActivityContextName, WfFilter.EXPRESSION_EQUAL, entry.getKey());
//                WfFilter filterValue = new WfFilter(RunningEntity.ActivityContextValue, WfFilter.EXPRESSION_LIKE, entry.getValue().toString());
//                filterList.add(filterKey);
//                filterList.add(filterValue);
//            }
//        }
//        WfFilter wfFilter = WfFilterUtil.getWfFilterAnd(filterList);
//        ParseResultSet resultSet = new ParseResultSet(pageSize, pageNo, "_T_a_.id desc");
//        resultSet.setUsePagination(true);
//        wfFilter.setParseResultSet(resultSet);
//
//        //查询非运行状态的流程活动实例
//        //wfFilter.setProcessClosed(true);
//
//        List<HisActInstVo> hisActInstVoList = new ArrayList<>();
//        List list = wfClient.listActivityInstances(wfFilter);
//        if (!CollectionUtils.isEmpty(list)) {
//            for (Object obj : list) {
//                HisActInstVo vo = getActInstHis(wfClient, obj);
//                if (vo != null) {
//                    hisActInstVoList.add(vo);
//                }
//            }
//        }*/
//
//        PageHelper.offsetPage(pageNo, pageSize);
////        List<ProcessVo> list = actService.getListByParams(defId, null, attrs);
//        PageInfo<ProcessVo> pageInfo = new PageInfo<>(list);
//        return PageUtils.getPageUtils(pageInfo);
//    }

//    @Override
//    public PageUtils<ProcessVo> getProcessInstAllByAttr(String defId, Integer pageNo, Integer pageSize) {
//        PageHelper.offsetPage(pageNo, pageSize);
//        List<ProcessVo> list = actService.getProcessInstAllByAttr(defId);
//        PageInfo<ProcessVo> pageInfo = new PageInfo<>(list);
//        return PageUtils.getPageUtils(pageInfo);
//    }

    /**
     * 获取历史记录对象
     * @param wfClient
     * @param obj
     * @return
     */
    private HisActInstVo getActInstHis(WfClient wfClient, Object obj) {
        if (obj instanceof WfActivityInstanceImpl) {
            WfActivityInstanceImpl actInst = (WfActivityInstanceImpl) obj;
            HisActInstVo actInstVo = new HisActInstVo();
            actInstVo.setActName(actInst.getName());
            actInstVo.setActInstCompleteTime(actInst.getCompleteTime());
            actInstVo.setActInstReceiveTime(actInst.getReceiveTime());
            actInstVo.setActInstIsBack(actInst.getIsBack());
            actInstVo.setActInstState(actInst.getState());
            actInstVo.setActDefId(actInst.getActDefinitionId());

            List<HisWorkItemVo> workItemVoList = new ArrayList<>();
            actInstVo.setWorkItemList(workItemVoList);

            // 查询活动实例工作项
            WfFilter actInstFilter = new WfFilter(RunningEntity.ActivityId, WfFilter.EXPRESSION_EQUAL, actInst.getId());
            List workItemList = wfClient.listWorkItems(actInstFilter);
            if (!CollectionUtils.isEmpty(workItemList)) {
                for (Object item : workItemList) {
                    if (item == null) {
                        continue;
                    }

                    if (item instanceof WfWorkItem) {
                        WfWorkItem wfWorkItem = (WfWorkItem) item;
                        HisWorkItemVo workItemVo = new HisWorkItemVo();
                        workItemVo.setWorkItemCompleteTime(actInst.getCompleteTime());
                        workItemVo.setWorkItemReceiveTime(actInst.getReceiveTime());
                        workItemVo.setWorkItemIsBack(actInst.getIsBack());
                        workItemVo.setWorkItemState(actInst.getState());
                        workItemVo.setWorkItemCompleteUserName(wfWorkItem.getParticipant());

                        // 查询工作项属性
                        Map params = wfClient.listWorkItemAttributes(wfWorkItem.getId(), null);
                        workItemVo.setWorkItemParams(params);

                        workItemVoList.add(workItemVo);
                    }
                }
            }

            return actInstVo;
        }

        return null;
    }

    @Override
    public ExistEnum getInstExist(WfClient client, String defId, String idAttrName, String idAttrValue) {
        List<ProcessInstanceVo> processList = getProcessInstanceByAttr(client, defId, idAttrName, idAttrValue);
        if (!CollectionUtils.isEmpty(processList)){
            return ExistEnum.RUNNING;
        }

        processList = getProcessHisInstanceByAttr(client, defId, idAttrName, idAttrValue);
        if (!CollectionUtils.isEmpty(processList)){
            return ExistEnum.HISTORY;
        }

        return ExistEnum.NOT_EXIST;
    }

    @Override
    public HisProcInstVo getInstStepByIdAttr(WfClient wfClient, String defId, String idAttrName, String idAttrValue) {
        String instId = getInstIdByIdAttr(wfClient, defId, idAttrName, idAttrValue, false);
        if (StringUtils.isBlank(instId)) {
            throw new SofnException("未找到流程实例");
        }

        HisProcInstVo instVo = new HisProcInstVo();
        instVo.setDefId(defId);
        instVo.setIdAttrName(idAttrName);
        instVo.setIdAttrValue(idAttrValue);

        // 查询流程实例参数
        Map instParams = wfClient.listProcessInstanceAttributes(instId, null);
        instVo.setInstParams(instParams);

        // 查询流程实例历史记录
        WfFilter wfFilter = new WfFilter(RunningEntity.ActivityProcessId, WfFilter.EXPRESSION_EQUAL, instId);
        List list = wfClient.listActivityInstances(wfFilter);

        // 查询工作项属性
        if (!CollectionUtils.isEmpty(list)) {
            List<HisActInstVo> actInstVoList = new ArrayList<>();
            instVo.setActInstList(actInstVoList);
            for (Object obj : list) {
                if (obj instanceof WfActivityInstanceImpl) {
                    WfActivityInstanceImpl actInst = (WfActivityInstanceImpl) obj;
                    HisActInstVo actInstVo = new HisActInstVo();
                    actInstVo.setActName(actInst.getName());
                    actInstVo.setActInstCompleteTime(actInst.getCompleteTime());
                    actInstVo.setActInstReceiveTime(actInst.getReceiveTime());
                    actInstVo.setActInstIsBack(actInst.getIsBack());
                    actInstVo.setActInstState(actInst.getState());
                    actInstVo.setActDefId(actInst.getActDefinitionId());

                    List<HisWorkItemVo> workItemVoList = new ArrayList<>();
                    actInstVo.setWorkItemList(workItemVoList);

                    // 查询活动实例工作项
                    WfFilter actInstFilter = new WfFilter(RunningEntity.ActivityId, WfFilter.EXPRESSION_EQUAL, actInst.getId());
                    List workItemList = wfClient.listWorkItems(actInstFilter);
                    if (!CollectionUtils.isEmpty(workItemList)) {
                        for (Object item : workItemList) {
                            if (item == null) {
                                continue;
                            }

                            if (item instanceof WfWorkItem) {
                                WfWorkItem wfWorkItem = (WfWorkItem) item;
                                HisWorkItemVo workItemVo = new HisWorkItemVo();
                                workItemVo.setWorkItemCompleteTime(actInst.getCompleteTime());
                                workItemVo.setWorkItemReceiveTime(actInst.getReceiveTime());
                                workItemVo.setWorkItemIsBack(actInst.getIsBack());
                                workItemVo.setWorkItemState(actInst.getState());
                                workItemVo.setWorkItemCompleteUserName(wfWorkItem.getParticipant());

                                // 查询工作项属性
                                Map params = wfClient.listWorkItemAttributes(wfWorkItem.getId(), null);
                                workItemVo.setWorkItemParams(params);

                                workItemVoList.add(workItemVo);
                            }
                        }
                    }
                    actInstVoList.add(actInstVo);
                }
            }
        }

        return instVo;
    }

    @Override
    public HisProcInstVo getFinishInstStepByIdAttr(WfClient wfClient, String defId, String idAttrName, String idAttrValue) {
        String instId = getInstIdByIdAttr(wfClient, defId, idAttrName, idAttrValue, true);
        if (StringUtils.isBlank(instId)) {
            throw new SofnException("未找到流程实例");
        }

        HisProcInstVo instVo = new HisProcInstVo();
        instVo.setDefId(defId);
        instVo.setIdAttrName(idAttrName);
        instVo.setIdAttrValue(idAttrValue);

        // 查询流程实例参数
        WfFilter instFilter = new WfFilter();
        instFilter.setProcessClosed(true);
        Map instParams = wfClient.listProcessInstanceAttributes(instId, instFilter);
        instVo.setInstParams(instParams);

        // 查询非运行状态的流程活动实例
        WfFilter actFilter = new WfFilter(HistoryEntity.ActivityProcessId, WfFilter.EXPRESSION_EQUAL, instId);
        actFilter.setProcessClosed(true);
        List list = wfClient.listActivityInstances(actFilter);

        if (!CollectionUtils.isEmpty(list)) {
            List<HisActInstVo> actInstVoList = new ArrayList<>();
            instVo.setActInstList(actInstVoList);
            for (Object obj : list) {
                if (obj instanceof WfActivityInstanceImpl) {
                    WfActivityInstanceImpl actInst = (WfActivityInstanceImpl) obj;
                    HisActInstVo actInstVo = new HisActInstVo();
                    actInstVo.setActName(actInst.getName());
                    actInstVo.setActInstCompleteTime(actInst.getCompleteTime());
                    actInstVo.setActInstReceiveTime(actInst.getReceiveTime());
                    actInstVo.setActInstIsBack(actInst.getIsBack());
                    actInstVo.setActInstState(actInst.getState());
                    actInstVo.setActDefId(actInst.getActDefinitionId());

                    List<HisWorkItemVo> workItemVoList = new ArrayList<>();
                    actInstVo.setWorkItemList(workItemVoList);

                    // 查询活动实例工作项
                    WfFilter actInstFilter = new WfFilter(HistoryEntity.ActivityId, WfFilter.EXPRESSION_EQUAL, actInst.getId());
                    actInstFilter.setProcessClosed(true);
                    List workItemList = wfClient.listWorkItems(actInstFilter);
                    if (!CollectionUtils.isEmpty(workItemList)) {
                        for (Object item : workItemList) {
                            if (item == null) {
                                continue;
                            }

                            if (item instanceof WfWorkItem) {
                                WfWorkItem wfWorkItem = (WfWorkItem) item;
                                HisWorkItemVo workItemVo = new HisWorkItemVo();
                                workItemVo.setWorkItemCompleteTime(actInst.getCompleteTime());
                                workItemVo.setWorkItemReceiveTime(actInst.getReceiveTime());
                                workItemVo.setWorkItemIsBack(actInst.getIsBack());
                                workItemVo.setWorkItemState(actInst.getState());
                                workItemVo.setWorkItemCompleteUserName(wfWorkItem.getParticipant());

                                // 查询工作项属性
                                WfFilter wiFilter = new WfFilter();
                                wiFilter.setProcessClosed(true);
                                Map params = wfClient.listWorkItemAttributes(wfWorkItem.getId(), wiFilter);
                                workItemVo.setWorkItemParams(params);

                                workItemVoList.add(workItemVo);
                            }
                        }
                    }
                    actInstVoList.add(actInstVo);
                }
            }
        }

        return instVo;
    }

    /**
     * 获取流程活动对象列表[根据名称]
     * @param defId 参数
     * @param activityMaker
     * @param instanceParamVos
     * @param pageNo 页
     * @param pageSize 条数
     * @return PageUtils<ActivityDataVo>
     */
    @Override
    public PageUtils<ActivityDataVo> getActivityAllDataByName(String defId, String idAttrName, List<String> idAttrValues, String activityMaker, List<InstanceParamTwoVo> instanceParamVos, String startTime, String endTime, Integer pageNo, Integer pageSize) {
        PageHelper.offsetPage(pageNo, pageSize);

        List<ActivityDataVo> list = actService.getActivityAllDataByName(defId,idAttrName,idAttrValues,activityMaker,instanceParamVos,startTime,endTime);
        list.forEach((e)->{
            List<ActContextVo> actContextVos = actService.getAllFieldAndValue(e.getActivityId(),instanceParamVos);
            e.setActContextVos(actContextVos);
        });
        PageInfo<ActivityDataVo> pageInfo = new PageInfo<>(list);

        return PageUtils.getPageUtils(pageInfo);
    }

}
