package com.sofn.flow.service;

import com.cvicse.workflow.api.WfClient;
import com.sofn.flow.vo.Result;

import java.util.List;
import java.util.Map;

/**
 * 活动控制相关接口
 * @author heyongjie
 * @date 2020/5/11 10:56
 */
public interface ActiveControlService {


    /**
     * 改变流程实例的状态
     * @param client    流程实例的客户端
     * @param instanceId  流程实例的ID
     * @param state  流程实例的新状态  suspended   Global.XXX
     * @return  Result
     */
    Result<String> changeProcessInstanceState(WfClient client,String instanceId, String state);

    /**
     * 提交活动，如果将流程设置为自动提交就不需要调用这个方法
     * 如果活动的完成策略为自动，活动的提交由工作流引擎自动执行，只有在完成策略为手动的时候，需要编写程序进行提交
     * @param client  WfClient 传入null
     * @param params   流程实例提交参数
     * @param processInstanceId   流程实例ID
     * @return  Result
     */
    Result<String> submitProcess(WfClient client, String processInstanceId,Map<String,Object> params);

    /**
     * 获取所有可回退节点集合
     * @param client  WfClient 传入null
     * @param instanceId   流程定义ID
     * @return Result<List>
     */
    Result<List> getBackNodeList(WfClient client, String instanceId);

}
