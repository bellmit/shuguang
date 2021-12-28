package com.sofn.flow.service.impl;

import com.cvicse.workflow.api.*;
import com.cvicse.workflow.api.query.entity.RunningEntity;
import com.sofn.flow.annotation.GetFlowConnection;
import com.sofn.flow.service.ActiveControlService;
import com.sofn.flow.util.WfFilterUtil;
import com.sofn.flow.vo.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author heyongjie
 * @date 2020/5/11 11:08
 */
@Service
public class ActiveControlServiceImpl implements ActiveControlService {


    @Override
    @GetFlowConnection
    public Result<String> changeProcessInstanceState(WfClient client, String instanceId, String state) {
        WfProcessInstance processInstance = client.getProcessInstance(instanceId);
        if(processInstance == null){
            return Result.error("processInstanceId不存在");
        }

        client.changeProcessInstanceState(instanceId,state);
        return Result.ok("更新成功");
    }

    /**
     * 获取启动的流程实例节点，如果提交当前节点后，再次查询会获得下一个节点
     * @param client   客户端连接对象
     * @param processInstanceId  流程实例ID
     * @return   流程实例节点ID
     */
    private String getActivityId(WfClient client, String processInstanceId){
        if(client == null){
            return "";
        }
        List<WfFilter> wfFilterList = new ArrayList<>();
        wfFilterList.add(new WfFilter(RunningEntity.ProcessId,WfFilter.EXPRESSION_EQUAL,processInstanceId));
        wfFilterList.add(new WfFilter(RunningEntity.ActivityCurState,WfFilter.EXPRESSION_EQUAL, Global.RUNNING));
        List list = client.listActivityInstances(WfFilterUtil.getWfFilterAnd(wfFilterList));
        if(CollectionUtils.isEmpty(list)){
            return "";
        }
        for (Object o : list) {
            WfActivityInstance wfActivityInstance = (WfActivityInstance) o;
            if("开始".equals(wfActivityInstance.getName())){
                continue;
            }
            return wfActivityInstance.getId();
        }
        return null;
    }

    @Override
    @GetFlowConnection
    public Result<String> submitProcess(WfClient client, String processInstanceId, Map<String,Object> params) {
        if(StringUtils.isBlank(processInstanceId)){
            return Result.error("processInstanceId不能为空");
        }
        WfProcessInstance processInstance = client.getProcessInstance(processInstanceId);
        if(processInstance == null){
            return Result.error("processInstanceId不存在");
        }
        // 提交流程
        String activityId = getActivityId(client, processInstanceId);
        if(StringUtils.isBlank(activityId)){
            return Result.error("没查询到活动节点信息");
        }
//        client.completeWorkItem()
        client.commitActivityInstance(processInstanceId,activityId,null);
        return Result.ok("流程提交成功");
    }

    @Override
    public Result<List> getBackNodeList(WfClient client, String instanceId) {
        List list = client.listBackableActivities(instanceId);
        return Result.ok(list);
    }


}
