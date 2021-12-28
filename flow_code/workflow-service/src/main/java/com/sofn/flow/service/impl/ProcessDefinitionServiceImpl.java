package com.sofn.flow.service.impl;

import com.cvicse.workflow.api.WdModelDefinition;
import com.cvicse.workflow.api.WfFilter;
import com.cvicse.workflow.api.WfManagerClient;
import com.cvicse.workflow.api.WfProcessModel;
import com.cvicse.workflow.api.query.entity.ModelEntity;
import com.sofn.flow.annotation.GetFlowConnection;
import com.sofn.flow.service.ProcessDefinitionService;
import com.sofn.flow.util.AutoParamUtil;
import com.sofn.flow.util.RedisUtil;
import com.sofn.flow.util.XmlUtil;
import com.sofn.flow.vo.ProcessDefinitionAttrVo;
import com.sofn.flow.vo.ProcessDefinitionVo;
import com.sofn.flow.vo.Result;
import com.sofn.flow.vo.SysParamVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 流程定义相关
 *
 * @author heyongjie
 * @date 2020/5/8 14:05
 */
@Service
public class ProcessDefinitionServiceImpl implements ProcessDefinitionService {

    @Autowired
    RedisUtil redisUtil;

    @Override
    @GetFlowConnection
    public List<ProcessDefinitionVo> getProcessDefinition(WfManagerClient wfClient, String defId) {
        // 过滤条件
        WfFilter wfFilter = null;
        if (StringUtils.isNotBlank(defId)) {
            wfFilter = new WfFilter(ModelEntity.DefId, WfFilter.EXPRESSION_LIKE, defId);
        }

        List<ProcessDefinitionVo> defVoList = new ArrayList<>();
//        List procDefs = wfClient.listModelDefines(wfFilter, false);
//        if(!CollectionUtils.isEmpty(procDefs)) {
//            for (Object procDef : procDefs) {
//                WdModelDefinition wdModelDefinition = (WdModelDefinition) procDef;
//                defVoList.add(new ProcessDefinitionVo().getVo(wdModelDefinition));
//            }
//        }
        WfProcessModel[] wfProcessModels = wfClient.listProcesses(wfFilter);
        if (wfProcessModels != null && wfProcessModels.length > 0) {
            for (WfProcessModel wfProcessModel : wfProcessModels) {
                ProcessDefinitionVo processDefinitionVo = new ProcessDefinitionVo();
                processDefinitionVo.setVersion(wfProcessModel.getVersion());
                processDefinitionVo.setPackageId(wfProcessModel.getPackageId());
                processDefinitionVo.setDescription(wfProcessModel.getDescription());
                processDefinitionVo.setName(wfProcessModel.getName());
                processDefinitionVo.setId(wfProcessModel.getId() + "");
                processDefinitionVo.setModelId(wfProcessModel.getId());
                defVoList.add(processDefinitionVo);
            }
        }

        return defVoList;
    }

    @Override
    public Result<List<ProcessDefinitionAttrVo>> getProcessDefinitionAttr(String processDefinitionId) {
        List<ProcessDefinitionAttrVo> definitionAttr = redisUtil.getProcessDefAttr(processDefinitionId);
        if (CollectionUtils.isEmpty(definitionAttr)) {
            definitionAttr = XmlUtil.getDefinitionAttr(processDefinitionId);
            if (!CollectionUtils.isEmpty(definitionAttr)) {
                redisUtil.cacheProcessDefAttr(processDefinitionId, definitionAttr);
            }
        }
        // 如果系统内置了并且不显示的就过滤掉
        List<ProcessDefinitionAttrVo> realDefinitionAttr = new ArrayList<>();
        if (!CollectionUtils.isEmpty(definitionAttr)) {
            for (ProcessDefinitionAttrVo processDefinitionAttrVo : definitionAttr) {
                String attrId = processDefinitionAttrVo.getAttrId();
                if (AutoParamUtil.contains(attrId)) {
                    SysParamVo sysParamVo = AutoParamUtil.getSysParamVo(attrId);
                    if (sysParamVo != null) {
                        if (sysParamVo.isShow()) {
                            realDefinitionAttr.add(processDefinitionAttrVo);
                        }
                    }
                } else {
                    realDefinitionAttr.add(processDefinitionAttrVo);
                }
            }
        }
        return Result.ok(realDefinitionAttr);
    }

    @Override
    @GetFlowConnection
    public WdModelDefinition getDefinitionByDefId(WfManagerClient wfClient, String defId) {
        List procDefs = wfClient.listModelDefines(new WfFilter(ModelEntity.DefId, WfFilter.EXPRESSION_EQUAL, defId), false);
        if (!CollectionUtils.isEmpty(procDefs)) {
            return (WdModelDefinition) procDefs.get(0);
        }

        return null;
    }


}
