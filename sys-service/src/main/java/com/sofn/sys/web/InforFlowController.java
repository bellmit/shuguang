package com.sofn.sys.web;

import com.sofn.common.model.Result;
import com.sofn.sys.config.InforFlowConfig;
import com.sofn.sys.service.WorkFlowUrlRegulatorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author heyongjie
 * @date 2020/5/28 16:17
 */
@RestController
@Api(tags = "流程配置相关接口")
@RequestMapping("/inforFlow")
public class InforFlowController {

  @Autowired(required = false)
  private InforFlowConfig inforFlowConfig;
//  @Autowired
//  private WorkFlowUrlRegulatorService workFlowUrlRegulatorService;
//  private static String processModelUrl;

  @GetMapping("/getProcessModelUrl")
  @ApiOperation(value = "获取流程设计器的访问路径", notes = "权限:(sys:inforFlow:getProcessModelUrl)")
  public Result<String> getProcessModelUrl() {
    if (inforFlowConfig == null) {
      return Result.error("请配置sofn.inforFlow.processModelUrl参数！");
    }
    return Result.ok((Object) inforFlowConfig.getProcessModelUrl());
  }
//    processModelUrl = InitWorkFlowUrl.getWorkFlowUrl("processModelUrl");
//    processModelUrl=workFlowUrlRegulatorService.getWorkFlowUrl("processModelUrl");
//    if (processModelUrl == null) {
//      return Result.error("请配置sofn.inforFlow.processModelUrl参数！");
//    }
//    return Result.ok(processModelUrl);
//  }

}
