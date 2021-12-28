package com.sofn.common.workflowapi;

import com.sofn.common.config.FeignConfiguration;
import com.sofn.common.model.Result;
import com.sofn.common.workflowapi.bean.BackFlowVo;
import com.sofn.common.workflowapi.bean.SubmitFlowVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 使用Feign workflow
 */
@FeignClient(
        value = "workflow-service",
        configuration = FeignConfiguration.class
)
public interface WorkFlowApi {

    /**
     * 启动某个流程，并且传入相应的关联参数，以及流程定义ID
     * @return  Result
     * @param submitFlowVo
     */
    @PostMapping(value = "/instance/startChainProcess", consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<String> startChainProcess(@RequestBody SubmitFlowVo submitFlowVo);

    /**
     * 提交到下一步，此接口会自动在INFORFLOW里面接收任务，然后提交任务
     * @return  Result
     * @param submitFlowVo
     */
    @PostMapping(value = "/workItem/completeWorkItem", consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<String> completeWorkItem(@RequestBody SubmitFlowVo submitFlowVo);

    /**
     * 回退到某一步
     * @return  Result
     */
    @PostMapping(value = "/workItem/backWorkItem", consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<String> backWorkItem(@RequestBody BackFlowVo backFlowVo);

    /**
     * 恢复流程实例，用于部级二次审核，先重启流程，再跟回退接口配合使用
     * @return  Result
     * @param submitFlowVo
     */
    @PostMapping(value = "/instance/reviveProcessInstanceByAttr", consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<String> reviveProcessInstanceByAttr(@RequestBody SubmitFlowVo submitFlowVo);

}
