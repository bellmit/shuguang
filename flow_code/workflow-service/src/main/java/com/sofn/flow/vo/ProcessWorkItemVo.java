package com.sofn.flow.vo;

import com.cvicse.workflow.api.WfWorkItem;
import com.sofn.flow.util.DateUtils;
import lombok.Data;

import java.util.List;

/**
 * 工作项VO
 * @author heyongjie
 * @date 2020/5/13 11:01
 */
@Data
public class ProcessWorkItemVo extends AbstractBasicVo<ProcessWorkItemVo, WfWorkItem>{

    /**
     * 流程定义ID
     */
    private String actDefId;

    /**
     * 流程实例ID
     */
    private String actInstanceId;

    /**
     * 工作项完成时间
     */
    private String completeTime;

    /**
     * 工作项描述
     */
    private String description;

    /**
     * 分组ID
     */
    private String groupId;


    /**
     * 工作项机构代码
     */
    private String orgId;

    /**
     * 工作项任务代理原执行人列表
     */
    private List masterUserIds;

    /**
     *  获取参与者
     */
    private String participant;

    /**
     * 工作项上一次状态
     */
    private String previousState;

    /**
     * 活动优先级
     */
    private Integer priority;

    /**
     * 流程定义ID
     */
    private String procDefId;

    /**
     * 流程定义名称
     */
    private String procDefName;

    /**
     * 流程实例ID
     */
    private String procInstanceId;

    /**
     * 流程模板ID
     */
    private String procModelId;


    /**
     * 工作项接收时间
     */
    private String receiveTime;

    /**
     * 上一环节执行人
     */
    private String workItemStarter;

    /**
     * 流程启动用户
     */
    private String startUser;

    /**
     * 工作项状态
     */
    private String state;



    @Override
    public ProcessWorkItemVo getVo(WfWorkItem wfWorkItem) {
        if(wfWorkItem == null){
            return null;
        }
        ProcessWorkItemVo processWorkItemVo = new ProcessWorkItemVo();
        processWorkItemVo.setId(wfWorkItem.getId());
        processWorkItemVo.setName(wfWorkItem.getName());
        processWorkItemVo.setActDefId(wfWorkItem.getActDefId());
        processWorkItemVo.setActInstanceId(wfWorkItem.getActivityInstanceId());
        processWorkItemVo.setCompleteTime(DateUtils.format(wfWorkItem.getCompleteTime()));
        processWorkItemVo.setDescription(wfWorkItem.getDescription());
        processWorkItemVo.setGroupId(wfWorkItem.getGroupId());
        processWorkItemVo.setOrgId(wfWorkItem.getInstCode());
        processWorkItemVo.setMasterUserIds(wfWorkItem.getMasterUserIds());
        processWorkItemVo.setParticipant(wfWorkItem.getParticipant());
        processWorkItemVo.setPreviousState(wfWorkItem.getPreviousState());
        processWorkItemVo.setPriority(wfWorkItem.getPriority());
        processWorkItemVo.setProcDefId(wfWorkItem.getProcDefId());
        processWorkItemVo.setProcInstanceId(wfWorkItem.getProcessInstanceId());
        processWorkItemVo.setProcModelId(wfWorkItem.getProcessModelId());
        processWorkItemVo.setReceiveTime(DateUtils.format(wfWorkItem.getReceiveTime()));
        processWorkItemVo.setWorkItemStarter(wfWorkItem.getWorkitemStarter());
        processWorkItemVo.setState(wfWorkItem.getState());
        return processWorkItemVo;
    }
}
