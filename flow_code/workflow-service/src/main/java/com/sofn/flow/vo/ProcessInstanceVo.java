package com.sofn.flow.vo;

import com.cvicse.workflow.api.WfProcessInstance;
import com.sofn.flow.util.DateUtils;
import lombok.Data;

/**
 * 流程实例vo
 * @author heyongjie
 * @date 2020/5/9 11:23
 */
@Data
public class ProcessInstanceVo extends AbstractBasicVo<ProcessInstanceVo, WfProcessInstance> {

    /**
     * 获取流程实例的机构ID，可动态设置
     */
    private String orgId;

    /**
     * 获取流程定义的ID
     */
    private String processDefinitionId;

    /**
     * 流程定义名称
     */
    private String processDefName;

    /**
     * 流程实例的创建人
     */
    private String createUser;

    /**
     * 描述
     */
    private String description;

    /**
     * 流程的创建时间
     */
    private String createTime;

    /**
     * 实例创建时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String completeTime;

    /**
     * 最后操作时间
     */
    private String lastOperateTime;

    /**
     * 上一次的状态
     */
    private String state;

    @Override
    public ProcessInstanceVo getVo(WfProcessInstance wfProcessInstance) {
        // 获取流程实例VO
        if(wfProcessInstance == null){
            return null;
        }
        ProcessInstanceVo processInstanceVo = new ProcessInstanceVo();
        processInstanceVo.setId(wfProcessInstance.getId());
        processInstanceVo.setName(wfProcessInstance.getName());
        processInstanceVo.setOrgId(wfProcessInstance.getInstCode());
        processInstanceVo.setProcessDefinitionId(wfProcessInstance.getProcessDefinitionId());
        processInstanceVo.setCreateUser(wfProcessInstance.getCreator());
        processInstanceVo.setDescription(wfProcessInstance.getDescription());
        processInstanceVo.setCreateTime(DateUtils.format(wfProcessInstance.getCreateTime()));
        processInstanceVo.setStartTime(DateUtils.format(wfProcessInstance.getStartTime()));
        processInstanceVo.setCompleteTime(DateUtils.format(wfProcessInstance.getCompleteTime()));
        processInstanceVo.setState(wfProcessInstance.getState());
        return processInstanceVo;
    }
}
