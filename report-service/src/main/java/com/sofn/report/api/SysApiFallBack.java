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
@RequestMapping("/sys/fallback/")
public class SysApiFallBack implements SysApi{

  @Override
  public Result<String> replaceToken() {
    return Result.error("fall back exception");
  }

  @Override
  public Result<SysRegionForm> getSysRegionByName(String regionName) {
    return Result.error("fall back exception");
  }

  @Override
  public Result<String> getFileRealPath(String id) {
    return Result.error("fall back exception");
  }
}
