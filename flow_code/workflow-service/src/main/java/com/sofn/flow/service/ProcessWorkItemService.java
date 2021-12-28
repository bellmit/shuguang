package com.sofn.flow.service;

import com.cvicse.workflow.api.WfClient;
import com.sofn.flow.vo.BackWorkItemForm;
import com.sofn.flow.vo.ProcessWorkItemVo;
import com.sofn.flow.vo.Result;
import com.sofn.flow.vo.SubmitInstanceVo;

import java.util.List;
import java.util.Map;

/**
 * 流程活动项接口
 * @author heyongjie
 * @date 2020/5/13 10:56
 */
public interface ProcessWorkItemService {

    /**
     * 获取当前用户的工作项列表
     * @param client  注入连接
     * @param processInstanceId   流程定义ID
     * @param  processDefId   流程定义ID
     * @return  Result
     */
    List<ProcessWorkItemVo> getWorkItemList(WfClient client,String processDefId,String processInstanceId);

    /**
     * 获取当前用户待抢的工作项列表
     * @param client  注入连接
     * @param processInstanceId   流程定义ID
     * @param  processDefId   流程定义ID
     * @return  Result
     */
    List<ProcessWorkItemVo> getWaitGrabWorkItemList(WfClient client,String processDefId, String processInstanceId);

    /**
     * 完成指定的工作项
     * @param client   连接客户端
     * @param wfWorkItemId    流程ID
     * @param processInstanceId   流程定义ID
     * @param params    启动参数
     * @return Result
     */
    Result<String> completeWorkItem(WfClient client,String processInstanceId,String wfWorkItemId,
                                    Map<String, Object> instParams, Map<String, Object> params, Map<String, Object> nextParams);

    /**
     * 通过属性完成工作项
     * @param client   连接客户端
     * @return Result
     */
    Result<String> completeWorkItem(WfClient client, SubmitInstanceVo vo);

    /**
     * 重新分配用户，当前用户抢占了任务，但当前用户不想执行改任务，将任务重新分配给别人
     * @param client  客户端连接
     * @param workItemId  工作项ID
     * @param sourceUserId  源用户
     * @param targetUserId   重新分配的用户
     * @return  Result<String>
     */
    Result<String> reassignWorkItem(WfClient client,String workItemId,String sourceUserId,String targetUserId);

    /**
     * 获取工作项的属性列表
     * @param client   客户端连接对象
     * @param workItemId   工作项ID
     * @return   Result<Map<String,String>>
     */
    Result<Map<String,Object>> getWorkItemAttr(WfClient client,String workItemId);

    /**
     * 针对抢任务策略，如果是抢任务，需要先获取任务
     * @param client  连接客户对象
     * @param processInstanceId  流程实例
     * @return   Result<String>
     */
    boolean acceptWorkItem(WfClient client, String processInstanceId, Map<String,Object> attrs);

    /**
     * 获取所有可回退列表
     * @param client   客户端连接对象
     * @param actInstanceId  流程实例ID
     * @return  Result<List<ProcessWorkItemVo>>
     */
    Result<List<ProcessWorkItemVo>> getBackWorkItemList(WfClient client,String actInstanceId);

    /**
     * 回退到指定的节点
     * @param client   客户端连接
     * @return  Result<String>
     */
    Result<String> backWorkItem(WfClient client, BackWorkItemForm form);


}
