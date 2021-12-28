package com.sofn.flow.service.impl;

import com.cvicse.workflow.api.*;
import  com.cvicse.workflow.xpdl.entity.ext.ActivityParticipantEntity;
import com.cvicse.workflow.api.impl.WfActivityInstanceImpl;
import com.cvicse.workflow.api.query.entity.RunningEntity;
import com.sofn.flow.annotation.GetFlowConnection;
import com.sofn.flow.enums.ActTypeEnum;
import com.sofn.flow.exception.SofnException;
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
 * @author heyongjie
 * @date 2020/5/13 11:45
 */
@Service
public class ProcessWorkItemServiceImpl implements ProcessWorkItemService {

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    AutoParamUtil autoParamUtil;

    @Autowired
    ProcessInstanceServiceImpl processInstanceService;

    @Override
    @GetFlowConnection
    public List<ProcessWorkItemVo> getWorkItemList(WfClient client, String processDefId, String processInstanceId) {
        return getWorkItemListForState(client, processDefId, processInstanceId, Global.RUNNING);
    }

    @GetFlowConnection
    @Override
    public List<ProcessWorkItemVo> getWaitGrabWorkItemList(WfClient client, String processDefId, String processInstanceId) {
        return getWorkItemListForState(client, processDefId, processInstanceId, Global.NOT_STARTED);
    }

    /**
     * 根据状态获取任务列表
     */
    private List<ProcessWorkItemVo> getWorkItemListForState(WfClient client, String processDefId, String processInstanceId, String state) {
        List<WfFilter> wfFilterList = new ArrayList<>();
        // 根据流程实例ID 或者流程定义ID筛选活动
        if (StringUtils.isNotBlank(processInstanceId)) {
            wfFilterList.add(new WfFilter(RunningEntity.ProcessId, WfFilter.EXPRESSION_EQUAL, processInstanceId));
        }

        if (StringUtils.isNotBlank(processDefId)) {
            wfFilterList.add(new WfFilter(RunningEntity.WorkItemProcessDefId, WfFilter.EXPRESSION_EQUAL, processDefId));
        }

        // 根据当前用户筛选流程
        String loginUserName = redisUtil.getLoginUserName();
        if (StringUtils.isBlank(loginUserName)) {
            throw  new SofnException("token过期");
        }

        wfFilterList.add(new WfFilter(RunningEntity.WorkItemPerformer, WfFilter.EXPRESSION_EQUAL, loginUserName));
        wfFilterList.add(new WfFilter(RunningEntity.ActivityCurState, WfFilter.EXPRESSION_EQUAL, state));
        List list = client.listWorkItems(WfFilterUtil.getWfFilterAnd(wfFilterList));

        // 组装成VO
        List<ProcessWorkItemVo> processWorkItemVos = new ArrayList<>();
        Set<String> usernames = new HashSet<>();
        // 获取流程的启动者
        if (!CollectionUtils.isEmpty(list)) {
            for (Object o : list) {
                if (o != null) {
                    WfWorkItem wfWorkItem = (WfWorkItem) o;
                    usernames.add(wfWorkItem.getParticipant());
                    usernames.add(wfWorkItem.getWorkitemStarter());
                    ProcessWorkItemVo processWorkItemVo = new ProcessWorkItemVo().getVo(wfWorkItem);
                    processWorkItemVos.add(processWorkItemVo);
                }
            }
        }
        if (!CollectionUtils.isEmpty(processWorkItemVos)) {
            setProcessStartUser(processWorkItemVos, client, usernames);
        }
        // 设置用户的中文名称
        setNickName(usernames, processWorkItemVos);
        return processWorkItemVos;
    }

    /**
     * 设置流程实例的属性
     *
     * @param processWorkItemVos 查询出来的流程活动
     * @param usernames          用户名称
     * @param client             客户端连接对象
     */
    private void setProcessStartUser(List<ProcessWorkItemVo> processWorkItemVos, WfClient client, Set<String> usernames) {
        if (!CollectionUtils.isEmpty(processWorkItemVos) && client != null) {
            List<WfProcessInstance> list = client.listProcessInstances(null);
            if (!CollectionUtils.isEmpty(list)) {
                for (ProcessWorkItemVo processWorkItemVo : processWorkItemVos) {
                    WfProcessInstance wfProcessInstance = null;
                    for (WfProcessInstance wfProcessInstance2 : list) {
                        if (processWorkItemVo.getProcInstanceId().equals(wfProcessInstance2.getId())) {
                            wfProcessInstance = wfProcessInstance2;
                            break;
                        }
                    }
                    if (wfProcessInstance != null) {
                        usernames.add(wfProcessInstance.getCreator());
                        processWorkItemVo.setStartUser(wfProcessInstance.getCreator());
                    }
                }
            }
        }
    }

    /**
     * 设置昵称
     *
     * @param usernames          用户名称
     * @param processWorkItemVos 工作项集合
     */
    private void setNickName(Set<String> usernames, List<ProcessWorkItemVo> processWorkItemVos) {
        if (!CollectionUtils.isEmpty(usernames) && !CollectionUtils.isEmpty(processWorkItemVos)) {
            List<SysUser> userInfoByUserNames = UserUtil.getUserInfoByUserNames(usernames);
            if (!CollectionUtils.isEmpty(userInfoByUserNames)) {
                for (ProcessWorkItemVo processWorkItemVo : processWorkItemVos) {
                    processWorkItemVo.setParticipant(UserUtil.getUserNickName(processWorkItemVo.getParticipant(), userInfoByUserNames));
                    processWorkItemVo.setWorkItemStarter(UserUtil.getUserNickName(processWorkItemVo.getWorkItemStarter(), userInfoByUserNames));
                    processWorkItemVo.setStartUser(UserUtil.getUserNickName(processWorkItemVo.getStartUser(), userInfoByUserNames));
                }
            }
        }
    }

    /**
     * 获取当个ProcessWorkItemVo对象，此方法不会关闭客户端连接对象
     *
     * @param client     客户端连接对象
     * @param workItemId 活动ID
     * @throws SofnException 如果没有查询到或者参数有问题就直接抛出异常
     */
    private void getWorkItemById(WfClient client, String workItemId) throws SofnException {
        if (client == null) {
            throw new SofnException("请传入连接对象");
        }
        if (StringUtils.isBlank(workItemId)) {
            throw new SofnException("workItemId不能为空");
        }
        WfWorkItem workItem = client.getWorkItem(workItemId);
        if (workItem == null) {
            throw new SofnException("没有查询到相关流程活动");
        }
    }

    @Override
    @GetFlowConnection
    public Result<String> completeWorkItem(WfClient client, String processInstanceId, String wfWorkItemId, Map<String, Object> instParams, Map<String, Object> params, Map<String, Object> nextParams) {
        WfWorkItem wfWorkItem = client.getWorkItem(wfWorkItemId);
        if (wfWorkItem == null) {
            throw new SofnException(String.format("活动实例%s不存在", wfWorkItemId));
        }

        WfProcessInstance processInstance = client.getProcessInstance(processInstanceId);
        if (processInstance == null) {
            throw new SofnException(String.format("活动实例%s不存在", wfWorkItemId));
        }

        try {
            // 检查是否含有内置参数 ，如果有内置参数需要添加内置参数
            // 获取流程的参数
            String processDefinitionId = processInstance.getProcessDefinitionId();
            List<ProcessDefinitionAttrVo> definitionAttr = redisUtil.getProcessDefAttr(processDefinitionId);
            if (!CollectionUtils.isEmpty(definitionAttr)) {
                for (ProcessDefinitionAttrVo processDefinitionAttrVo : definitionAttr) {
                    if (AutoParamUtil.contains(processDefinitionAttrVo.getAttrId())) {
                        SysParamVo sysParamVo = AutoParamUtil.getSysParamVo(processDefinitionAttrVo.getAttrId());
                        if (sysParamVo != null && !sysParamVo.isShow()) {
                            // 设置参数
                            if (sysParamVo.getParamName().equals(AutoParamUtil.USER_ROLE_PARAM)) {
                                // 如果是获取用户信息需要二个字段 一个是流程实例ID  一个是活动ID
                                Object value = autoParamUtil.getValueByParam(sysParamVo.getParamName(), processDefinitionId, wfWorkItem.getActDefId());
                                if (value != null && !StringUtils.isBlank(value.toString())) {
                                    params.put(sysParamVo.getParamName(),value.toString());
                                }
                            }else{
                                // TODO  其他参数
                            }
                        }
                    }
                }
            }

            // 分配属性
            assignAttrs(client, processInstanceId, instParams, wfWorkItem.getId(), params, null, null);
            // 设置下一个活动节点的工作项属性
            List nextWorkItemList = client.completeWorkItem(processInstanceId, wfWorkItem.getId());
            // 分配属性
            assignAttrs(client, null, null, null, null, nextWorkItemList, nextParams);

            return Result.ok("活动完成");
        } catch (Exception e) {
            throw new SofnException("完成活动异常，" + e.getMessage());
        }
    }

    @GetFlowConnection
    @Override
    public Result<String> completeWorkItem(WfClient client, SubmitInstanceVo vo) {
        String instId = processInstanceService.getInstIdByIdAttr(client, vo.getDefId(), vo.getIdAttrName(), vo.getIdAttrValue(), false);
        if (StringUtils.isBlank(instId)) {
            throw new SofnException("未找到流程实例");
        }

        // 未接收任务先接收任务
        List<ProcessWorkItemVo> waitWorkItemList = getWaitGrabWorkItemList(client, vo.getDefId(), instId);
        if (!CollectionUtils.isEmpty(waitWorkItemList)) {
            if (!acceptWorkItem(client, instId, null)) {
                throw new SofnException("接收任务失败");
            }
        }

        List<ProcessWorkItemVo> workItemList = getWorkItemList(client, vo.getDefId(), instId);
        if (CollectionUtils.isEmpty(workItemList) || workItemList.get(0) == null) {
            throw new SofnException("待完成任务不存在");
        }

        // 业务主键参数加入到属性中
        if (vo.getInstParams() == null) {
            vo.setInstParams(new HashMap<>());
        }
        vo.getInstParams().put(vo.getIdAttrName(), vo.getIdAttrValue());

        return completeWorkItem(client, instId, workItemList.get(0).getId(), vo.getInstParams(),vo.getParams(),vo.getNextParams());
    }

    @Override
    @GetFlowConnection
    public Result<String> reassignWorkItem(WfClient client, String workItemId, String sourceUserId, String targetUserId) {
        try {
            getWorkItemById(client, workItemId);
            client.reassignWorkItem(sourceUserId, targetUserId, workItemId);
            return Result.ok("重置成功");
        } catch (SofnException e) {
            return Result.error(e.getMsg());
        }
    }

    @Override
    @GetFlowConnection
    public Result<Map<String, Object>> getWorkItemAttr(WfClient client, String workItemId) {
        try {
            getWorkItemById(client, workItemId);
            Map map = client.listWorkItemAttributes(workItemId, null);
            Map<String, Object> params = new HashMap<>(10);
            if (!CollectionUtils.isEmpty(map)) {
                for (Object key : map.keySet()) {
                    if (key != null) {
                        String realKey = key.toString();
                        params.put(realKey, map.get(key));
                    }
                }
            }
            return Result.ok(params);
        } catch (SofnException e) {
            return Result.error(e.getMsg());
        }
    }

    @Override
    @GetFlowConnection
    public boolean acceptWorkItem(WfClient client, String instId, Map<String,Object> attrs) {
        // 1. 按条件筛选活动列表
        List<WfFilter> wfFilterList = new ArrayList<>();

        // 流程定义
        if (StringUtils.isNotBlank(instId)) {
            WfProcessInstance processInstance = client.getProcessInstance(instId);
            if (processInstance == null) {
                throw new SofnException("流程实例ID不存在");
            }
            wfFilterList.add(new WfFilter(RunningEntity.ProcessId, WfFilter.EXPRESSION_EQUAL, instId));
        }

        // 流程状态为未启动的才能进行任务抢占
        wfFilterList.add(new WfFilter(RunningEntity.ActivityCurState, WfFilter.EXPRESSION_EQUAL, Global.NOT_STARTED));
        // 根据当前用户筛选流程
        String loginUserName = redisUtil.getLoginUserName();
        if (StringUtils.isBlank(loginUserName)) {
            throw new SofnException("未获取到用户信息");
        }
        wfFilterList.add(new WfFilter(RunningEntity.WorkItemPerformer, WfFilter.EXPRESSION_EQUAL, loginUserName));

        List list = client.listWorkItems(WfFilterUtil.getWfFilterAnd(wfFilterList));
        if (CollectionUtils.isEmpty(list)) {
            throw new SofnException("当前用户没有可以抢的任务");
        }

        // 抢占所有当前用户可以抢的
        for (Object w : list) {
            if (w != null) {
                WfWorkItem wfWorkItem = (WfWorkItem) w;
                if (StringUtils.isBlank(instId)) {
                    client.acceptWorkItem(wfWorkItem.getProcessInstanceId(), wfWorkItem.getId());
                } else {
                    client.acceptWorkItem(instId, wfWorkItem.getId());
                }

                // 设置当前工作项属性
                if (!CollectionUtils.isEmpty(attrs)) {
                    for (String key : attrs.keySet()) {
                        Object value = attrs.get(key);
                        if(value != null && !StringUtils.isBlank(value.toString())){
                            client.assignWorkItemAttribute(wfWorkItem.getId(), key, attrs.get(key));
                        }
                    }
                }
            }
        }

        return true;
    }

    @Override
    @GetFlowConnection
    public Result<List<ProcessWorkItemVo>> getBackWorkItemList(WfClient client, String actInstanceId) {
        if (StringUtils.isBlank(actInstanceId)) {
            return Result.error("actInstanceId不能为空");
        }
        List list = client.listBackableActivities(actInstanceId);
        if (CollectionUtils.isEmpty(list)) {
            return Result.error("该流程没有可以回退的节点");
        }
        List<ProcessWorkItemVo> processWorkItemVos = new ArrayList<>();
        Set<String> usernames = new HashSet<>();
        for (Object o : list) {
            if (o != null) {
                WfActivityInstanceImpl wfWorkItem = (WfActivityInstanceImpl) o;
                if("START".equals(wfWorkItem.getActDefinitionId())){
                    continue;
                }
                ProcessWorkItemVo processWorkItemVo = new ProcessWorkItemVo();
                processWorkItemVo.setActDefId(wfWorkItem.getActDefinitionId());
                processWorkItemVo.setName(wfWorkItem.getName());
                processWorkItemVo.setProcInstanceId(wfWorkItem.getProcessInstanceId());
                processWorkItemVo.setId(wfWorkItem.getId());
                processWorkItemVo.setState(wfWorkItem.getState());
                processWorkItemVo.setStartUser(wfWorkItem.getActivityStarter());
                processWorkItemVo.setActInstanceId(wfWorkItem.getProcessInstanceId());
                processWorkItemVo.setWorkItemStarter(wfWorkItem.getActor());
                processWorkItemVo.setReceiveTime(DateUtils.format(wfWorkItem.getReceiveTime()));
                processWorkItemVo.setCompleteTime(DateUtils.format(wfWorkItem.getCompleteTime()));
                usernames.add(wfWorkItem.getActivityStarter());
                usernames.add(wfWorkItem.getActor());
                processWorkItemVos.add(processWorkItemVo);
            }
        }
        setNickName(usernames, processWorkItemVos);
        return Result.ok(processWorkItemVos);
    }

    @Override
    @GetFlowConnection
    public Result<String> backWorkItem(WfClient client, BackWorkItemForm form) {
        String instId = processInstanceService.getInstIdByIdAttr(client, form.getDefId(), form.getIdAttrName(), form.getIdAttrValue(), false);
        if (StringUtils.isBlank(instId)) {
            throw new SofnException("未找到流程实例");
        }

        // 获取待接收列表
        List<ProcessWorkItemVo> waitWorkItemList = getWaitGrabWorkItemList(client, form.getDefId(), instId);

        // 先接收任务
        if (BooleanUtil.isTrue(form.getAcceptBack()) && !CollectionUtils.isEmpty(waitWorkItemList)) {
            acceptWorkItem(client, instId, form.getParams());
        }

        String currentActInstId = getActIdByActDefIdAndInstId(client, instId, null);
        String targetActInstId = getActIdByActDefIdAndInstId(client, instId, form.getTargetActDefId());

        // 回退
        List targetWorkItemList = client.backActivityInstance(currentActInstId, targetActInstId, BooleanUtil.isTrue(form.getBackToCommit()));

        // 设置属性
        assignAttrs(client, instId, form.getInstParams(), null, null, targetWorkItemList, form.getNextParams());

        return Result.ok("回退成功");
    }

    private List getWorkItemIdByActId(WfClient client, String instId, String actDefId) throws SofnException {
        if (client == null) {
            throw new SofnException("请传入连接对象");
        }

        if (StringUtils.isBlank(instId)) {
            throw new SofnException("instId不能为空");
        }

        if (StringUtils.isBlank(actDefId)) {
            throw new SofnException("actDefId不能为空");
        }

        List<WfFilter> wfFilterList = new ArrayList<>();
        // 根据流程实例ID筛选活动
        if (StringUtils.isNotBlank(instId)) {
            wfFilterList.add(new WfFilter(RunningEntity.ProcessId, WfFilter.EXPRESSION_EQUAL, instId));
        }

        wfFilterList.add(new WfFilter(RunningEntity.ActivityDefId, WfFilter.EXPRESSION_EQUAL, actDefId));
        List list = client.listWorkItems(WfFilterUtil.getWfFilterAnd(wfFilterList));
        if (CollectionUtils.isEmpty(list)) {
            throw new SofnException("未找到活动工作项");
        }

        return list;
    }

    private String getActIdByActDefIdAndInstId(WfClient client, String instId, String actDefId) throws SofnException {
        if (client == null) {
            throw new SofnException("请传入连接对象");
        }

        if (StringUtils.isBlank(instId)) {
            throw new SofnException("instId不能为空");
        }

        List<WfFilter> wfFilterList = new ArrayList<>();

        // 获取当前活动接口实例ID
        WfFilter stateNotStarted = new WfFilter(RunningEntity.ActivityCurState, WfFilter.EXPRESSION_EQUAL, Global.NOT_STARTED);
        WfFilter stateRunning = new WfFilter(RunningEntity.ActivityCurState, WfFilter.EXPRESSION_EQUAL, Global.RUNNING);
        WfFilter orState = new WfFilter(stateNotStarted, WfFilter.JOIN_OR, stateRunning);
        wfFilterList.add(orState);

        // 根据流程实例ID筛选活动
        wfFilterList.add(new WfFilter(RunningEntity.ProcessId, WfFilter.EXPRESSION_EQUAL, instId));

        // 查询当前活动实例ID
        List currentActList = client.listActivityInstances(WfFilterUtil.getWfFilterAnd(wfFilterList));
        if (CollectionUtils.isEmpty(currentActList)) {
            throw new SofnException("当前活动实例不存在");
        }
        String currentActInstId = ((WfActivityInstance)currentActList.get(0)).getId();
        if (StringUtils.isBlank(actDefId)) {
            return currentActInstId;
        }

        // 查询当前活动实例可回退到的目标活动实例ID
        String targetActInstId = getBackableActInstId(client, currentActInstId, actDefId);
        if (StringUtils.isBlank(targetActInstId)) {
            throw new SofnException("无可回退的活动");
        }

        return targetActInstId;
    }

    /**
     * 根据当前活动实例ID和目标活动定义ID获取目标活动下的可回退活动实例ID
     */
    private String getBackableActInstId(WfClient wfClient, String currentActInstId, String targetActDefId) {
        List backableList = wfClient.listBackableActivities(currentActInstId);
        if (!CollectionUtils.isEmpty(backableList)) {
            for (Object obj : backableList) {
                if (obj == null) {
                    continue;
                }

                WfActivityInstance actInstance = (WfActivityInstance)obj;
                if (StringUtils.equals(actInstance.getActDefinitionId(), targetActDefId)){
                    return actInstance.getId();
                }
            }
        }

        return null;
    }

    /**
     * 分配工作项属性
     */
    private void assignAttrs(WfClient client, String instId, Map<String,Object> instParams, String curWorkItemId, Map<String,Object> params,
                             List nextWorkItemList, Map<String,Object> nextParams) {
        // 设置实例属性
        if (StringUtils.isNotBlank(instId) && !CollectionUtils.isEmpty(instParams)) {
            for (String key : instParams.keySet()) {
                Object value = instParams.get(key);
                if(value != null && !StringUtils.isBlank(value.toString())){
                    client.assignProcessInstanceAttribute(instId, key, instParams.get(key));
                }
            }
        }

        // 设置当前工作项属性
        if (StringUtils.isNotBlank(curWorkItemId) && !CollectionUtils.isEmpty(params)) {
            for (String key : params.keySet()) {
                Object value = params.get(key);
                if(value != null && !StringUtils.isBlank(value.toString())){
                    client.assignWorkItemAttribute(curWorkItemId, key, params.get(key));
                }
            }
        }

        // 设置下一个活动的工作项属性
        if (!CollectionUtils.isEmpty(nextWorkItemList) && !CollectionUtils.isEmpty(nextParams)) {
            for (Object obj : nextWorkItemList) {
                if (obj instanceof WfWorkItem) {
                    WfWorkItem workItem = (WfWorkItem) obj;
                    if (!CollectionUtils.isEmpty(nextParams)) {
                        for (String key : nextParams.keySet()) {
                            Object value = nextParams.get(key);
                            if(value != null && !StringUtils.isBlank(value.toString())){
                                client.assignWorkItemAttribute(workItem.getId(), key, nextParams.get(key));
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 获取流程实例当前活动
     * @param client
     * @param defId
     * @param instId
     * @return
     */
    public List getCurrentAct(WfClient client, String defId, String instId) {
        List<WfFilter> wfFilterList = new ArrayList<>();
        // 根据流程实例ID 或者流程定义ID筛选活动
        if (StringUtils.isNotBlank(instId)) {
            wfFilterList.add(new WfFilter(RunningEntity.ProcessId, WfFilter.EXPRESSION_EQUAL, instId));
        }

        if (StringUtils.isNotBlank(defId)) {
            wfFilterList.add(new WfFilter(RunningEntity.ActivityProcDefId, WfFilter.EXPRESSION_EQUAL, defId));
        }

        // 当前activity状态包括RUNNING or NOT_STARTED
        List<WfFilter> actFilterList = new ArrayList<>();
        actFilterList.add(new WfFilter(RunningEntity.ActivityCurState, WfFilter.EXPRESSION_EQUAL, Global.RUNNING));
        actFilterList.add(new WfFilter(RunningEntity.ActivityCurState, WfFilter.EXPRESSION_EQUAL, Global.NOT_STARTED));
        wfFilterList.add(WfFilterUtil.getWfFilterOr(actFilterList));
        List list = client.listActivityInstances(WfFilterUtil.getWfFilterAnd(wfFilterList));

        return list;
    }

    private ActTypeEnum getActType(WfClient client, String defId, String instId) {
        List list = getCurrentAct(client, defId, instId);
        if (list != null) {
            for (Object obj : list) {
                if (obj instanceof WfActivityInstance) {
                    WfActivityInstance actInst = (WfActivityInstance) obj;
                    WfActivityDefinition actDef = client.getActivityDefinition(actInst.getProcessModelId(),actInst.getActDefinitionId());
                    String ass = "";
                    Set set = actDef.getActivityParticipantSet().getActivityParticipants();
                    Iterator it = set.iterator();
                    while(it.hasNext()) {
                        ActivityParticipantEntity actPartEnt = (ActivityParticipantEntity) it.next();
                        ass= actPartEnt.getAssignPolicy();
                    }

                    return ActTypeEnum.getByCode(ass);
                }
            }
        }

        return null;
    }
}
