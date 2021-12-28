package com.sofn.flow.service;

import com.cvicse.workflow.api.WdModelDefinition;
import com.cvicse.workflow.api.WfClient;
import com.cvicse.workflow.api.WfManagerClient;
import com.sofn.flow.vo.ProcessDefinitionAttrVo;
import com.sofn.flow.vo.ProcessDefinitionVo;
import com.sofn.flow.vo.Result;

import java.util.List;
import java.util.Map;

/**
 * @author heyongjie
 * @date 2020/5/8 17:40
 */
public interface ProcessDefinitionService {

    /**
     * 获取所有已经部署的流程定义
     *
     * @param wfClient              用于切面注入连接
     * @param defId 流程定义ID
     * @return List<ProcessDefinitionVo>
     */
    List<ProcessDefinitionVo> getProcessDefinition(WfManagerClient wfClient, String defId);

    /**
     * 获取流程定义 定义的属性
     * @param processDefinitionId   getProcessDefinitionAttr
     * @return    Result<List<ProcessDefinitionAttrVo>>
     */
    Result<List<ProcessDefinitionAttrVo>> getProcessDefinitionAttr(String processDefinitionId);

    /**
     * 根据流程定义ID获取流程定义详情
     * @param wfClient
     * @param defId
     * @return
     */
    WdModelDefinition getDefinitionByDefId(WfManagerClient wfClient, String defId);

}
