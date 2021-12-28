package com.sofn.report.api;

import com.sofn.common.model.Result;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *@Description TODO
 *@Author quzhijie
 *@Date 2020/8/3 10:41
 *@Version 1.0
 **/
@Component
@RequestMapping("/workflow/fallback/")
public class WorkFlowApiFallBack implements WorkFlowApi{

  @Override
  public Result<String> startChainProcess(SubmitInstanceVo vo) {
    return Result.error("fall back exception");
  }
}
