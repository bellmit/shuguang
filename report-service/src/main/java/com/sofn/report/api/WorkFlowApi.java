package com.sofn.report.api;

import com.sofn.common.config.FeignConfiguration;
import com.sofn.common.model.Result;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: 工作流api
 * @Author: quzhijie
 * @Date: 2020/12/14 14:20
 **/
@FeignClient(
       value = "workflow-service",
       fallback = WorkFlowApiFallBack.class,
       configuration = FeignConfiguration.class
)
public interface WorkFlowApi {

    @PostMapping(value = "/startChainProcess")
    Result<String> startChainProcess(@ApiParam(name = "启动流程实体", required = true)
                                            @RequestBody SubmitInstanceVo vo);

}
