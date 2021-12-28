package com.sofn.flow.vo;

import com.cvicse.workflow.api.WdModelDefinition;
import com.cvicse.workflow.api.WfProcessDefinition;
import lombok.Data;

/**
 * 流程定义VO   部署后就会有
 * @author heyongjie
 * @date 2020/5/8 13:56
 */
@Data
public class ProcessDefinitionVo extends AbstractBasicVo<ProcessDefinitionVo,WfProcessDefinition> {

    /**
     * 流程定义id
     */
    private Long modelId;

    /**
     * 流程描述
     */
    private String description;

    /**
     * 流程版本
     */
    private int version;

    /**
     * 流程所属包ID
     */
    private String packageId;

    public ProcessDefinitionVo getVo(WdModelDefinition wdModelDefinition) {
        if(wdModelDefinition == null){
            return null;
        }

        String key = String.format("%s:%s",wdModelDefinition.getXpdlPackage(),wdModelDefinition.getProcessID());
        ProcessDefinitionVo processDefinitionVo = new ProcessDefinitionVo();
        processDefinitionVo.setId(key);
        processDefinitionVo.setName(wdModelDefinition.getProcessName());
        processDefinitionVo.setDescription(wdModelDefinition.getDescription());
        processDefinitionVo.setPackageId(wdModelDefinition.getXpdlPackage());
        processDefinitionVo.setModelId(wdModelDefinition.getModelID());
        return processDefinitionVo;
    }


    @Override
    public ProcessDefinitionVo getVo(WfProcessDefinition wfProcessDefinition) {
        // 获取流程定义信息
        if(wfProcessDefinition == null){
            return null;
        }
        ProcessDefinitionVo processDefinitionVo = new ProcessDefinitionVo();
        processDefinitionVo.setId(wfProcessDefinition.getId());
        processDefinitionVo.setName(wfProcessDefinition.getName());
        processDefinitionVo.setDescription(wfProcessDefinition.getDescription());
        processDefinitionVo.setPackageId(wfProcessDefinition.getPackageId());
        processDefinitionVo.setVersion(wfProcessDefinition.getVersion());
        return processDefinitionVo;
    }


}
