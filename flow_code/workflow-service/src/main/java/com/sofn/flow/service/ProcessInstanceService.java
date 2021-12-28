package com.sofn.flow.service;

import com.cvicse.workflow.api.WfClient;
import com.sofn.flow.enums.ExistEnum;
import com.sofn.flow.util.PageUtils;
import com.sofn.flow.vo.*;

import java.util.List;
import java.util.Map;

/**
 * @author heyongjie
 * @date 2020/5/8 17:40
 */
public interface ProcessInstanceService {

    /**
     * 根据流程定义启动流程
     * @param wfClient  流程连接
     * @return  Result<String>
     */
    Result<String> startProcess(WfClient wfClient, SubmitInstanceVo vo);


    /**
     * 根据流程定义创建流程实例
     *
     * @param defId            流程定义ID
     * @param processInstanceName 流程实例的名称 如请假申请等...
     * @param wfClient 传入null
     * @return 流程实例的ID
     */
    String createProcessInstance(WfClient wfClient, String defId, String processInstanceName, String idAttrName, String idAttrValue);

    /**
     * 获取所有的流程定义
     *
     * @param wfClient 工作流连接客户端，传入null就好
     * @param isHistory    是否历史
     * @return Result Result<List<ProcessInstanceVo>>
     */
    Result<List<ProcessInstanceVo>> getAllProcessInstance(WfClient wfClient,String isHistory);

    /**
     * 根据属性获取所有的流程定义
     *
     * @param wfClient 工作流连接客户端，传入null就好
     * @param defId 流程定义ID
     * @param attrName 属性名称
     * @param attrValue 属性值
     * @return Result Result<List<ProcessInstanceVo>>
     */
    List<ProcessInstanceVo> getProcessInstanceByAttr(WfClient wfClient, String defId, String attrName, String attrValue);

    /**
     * 根据属性获取所有的历史流程定义
     *
     * @param wfClient 工作流连接客户端，传入null就好
     * @param defId 流程定义ID
     * @param attrName 属性名称
     * @param attrValue 属性值
     * @return Result Result<List<ProcessInstanceVo>>
     */
    List<ProcessInstanceVo> getProcessHisInstanceByAttr(WfClient wfClient, String defId, String attrName, String attrValue);

    /**
     * 给流程设置机构代码
     *
     * @param processInstanceId 流程定义ID
     * @param wfClient          工作流连接客户端，传入null就好
     * @param orgId             设置的机构ID
     * @return Result
     */
    Result<String> setProcessInstanceOrgId(WfClient wfClient, String processInstanceId, String orgId);


    /**
     * 将之前创建流程实例启动，流程的第一个活动将被启动
     *
     * @param userId            登录用户ID
     * @param wfClient          工作流连接客户端，传入null就好
     * @param processInstanceId 之前创建的流程实例
     * @return 流程实例ID  在此流程实例的生命周期有效
     */
    Result<String> startProcessInstance(WfClient wfClient, String userId, String processInstanceId);

    /**
     * 将之前创建流程实例启动
     *
     * @param token             登录用户Token
     * @param wfClient          工作流连接客户端，传入null就好
     * @param processInstanceId 之前创建的流程实例
     * @return Result<String>
     */
    Result<String> startProcessInstanceByToken(WfClient wfClient, String token, String processInstanceId);

    /**
     * 终止流程，将未结束的流程结束掉，各活动的相关数据被拷贝到流程实例对象中
     *
     * @param wfClient          工作流连接客户端，传入null就好
     * @param processInstanceId 流程实例ID
     * @return Result<String>
     */
    boolean stopProcessInstance(WfClient wfClient, String processInstanceId);

    boolean stopProcessInstance(WfClient wfClient, String defId, String idAttrName, String idAttrValue);

    /**
     * 取消流程，各活动的相关数据不会被拷贝到流程实例对象中
     *
     * @param wfClient          工作流连接客户端，传入null就好
     * @param processInstanceId 流程实例ID
     * @return Result<String>
     */
    boolean cancelProcessInstance(WfClient wfClient, String processInstanceId);

    boolean cancelProcessInstance(WfClient wfClient, String defId, String idAttrName, String idAttrValue);

    /**
     * 获取流程实例状态
     *
     * @param wfClient          工作流连接客户端，传入null就好
     * @param processInstanceId 流程实例ID
     * @return Result<String>
     */
    Result<String> getProcessInstanceState(WfClient wfClient, String processInstanceId);

    /**
     * 获取流程实例的属性  可动态生成表单用于提交流程
     *
     * @param wfClient          工作流连接客户端，传入null就好
     * @param processInstanceId 流程定义ID
     * @return 流程属性
     */
    Result<Map<String, Object>> getProcessInstanceAttr(WfClient wfClient, String processInstanceId);

    /**
     * 复活流程实例,将正常结束、终止和取消的流程实例恢复为流程结束前一样的状态
     *
     * @param wfClient          工作流连接客户端，传入null就好
     * @param processInstanceId 流程定义
     * @return Result<String>
     */
    boolean reviveProcessInstance(WfClient wfClient, String processInstanceId);

    boolean reviveProcessInstance(WfClient wfClient, String defId, String idAttrName, String idAttrValue);

    /**
     * 刪除流程实例
     *
     * @param wfClient          工作流连接客户端，传入null就好
     * @param processInstanceId processInstanceId
     * @return  Result<String>
     */
    boolean delProcessInstance(WfClient wfClient, String processInstanceId);

    boolean delProcessInstance(WfClient wfClient, String defId, String idAttrName, String idAttrValue);

    /**
     * 给流程是设置值
     *
     * @param wfClient          连接客户端
     * @param values            需要设置的值
     * @param processInstanceId 流程定义
     * @return Result<String>
     */
    Result<String> setValues(WfClient wfClient, Map<String, Object> values, String processInstanceId);

    /**
     * 根据业务属性ID获取流程实例ID
     * @param client
     * @param defId
     * @param attrName
     * @param attrValue
     * @return
     */
    String getInstIdByIdAttr(WfClient client, String defId, String attrName, String attrValue, boolean includeHis);

    /**
     * 获取流程实例历史对象
     * @param wfClient
     * @param defId
     * @param idAttrName
     * @param idAttrValue
     * @return
     */
    HisProcInstVo getProcessInstHisByIdAttr(WfClient wfClient, String defId, String idAttrName, String idAttrValue, boolean includeHis);

//    /**
//     * 获取流程实例历史对象
//     * @param defId
//     * @param attrs
//     * @return
//     */
//    PageUtils<Act> getProcessInstHisByAttr(String defId, Map<String,Object> attrs, int pageNo, int pageSize);

    /**
     * 获取流程实例全部对象
     * @param defIds
     * @param idAttrValues
     * @param idAttrName
     * @param pageNo
     * @param pageSize
     * @return
     */
    List<ProcessVo> getProcessInstAllByAttr(List<String> defIds, String idAttrName, List<String> idAttrValues,
                                                 List<InstanceParamVo> instanceParamVos,
                                                 String startTime,
                                                 String endTime, Integer pageNo, Integer pageSize);
    /**
     * 获取流程实例是否存在
     * @param defId
     * @param idAttrName
     * @param idAttrValue
     * @return
     */
    ExistEnum getInstExist(WfClient client, String defId, String idAttrName, String idAttrValue);

    /**
     * 根据业务ID获取运行中流程实例历史步骤
     * @param wfClient
     * @param defId
     * @param idAttrName
     * @param idAttrValue
     * @return
     */
    HisProcInstVo getInstStepByIdAttr(WfClient wfClient, String defId, String idAttrName, String idAttrValue);

    /**
     * 根据业务ID获取已结束流程实例历史步骤
     * @param wfClient
     * @param defId
     * @param idAttrName
     * @param idAttrValue
     * @return
     */
    HisProcInstVo getFinishInstStepByIdAttr(WfClient wfClient, String defId, String idAttrName, String idAttrValue);


    /**
     * 获取流程活动对象列表[根据名称]
     * @param defId 参数
     * @param activityMaker
     * @param instanceParamVos
     * @param pageNo 页
     * @param pageSize 条数
     * @return PageUtils<ActivityDataVo>
     */
    PageUtils<ActivityDataVo> getActivityAllDataByName(String defId, String idAttrName, List<String> idAttrValues, String activityMaker, List<InstanceParamTwoVo> instanceParamVos, String startTime, String endTime, Integer pageNo, Integer pageSize);
}
