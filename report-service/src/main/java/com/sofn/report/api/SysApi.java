package com.sofn.report.api;

import com.sofn.common.config.FeignConfiguration;
import com.sofn.common.excel.test.bigdatatest.model.SysRegion;
import com.sofn.common.model.Result;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: 系统支撑平台api
 * @Author: quzhijie
 * @Date: 2020/4/28 14:20
 **/
@FeignClient(
       value = "sys-service",
       fallback = SysApiFallBack.class,
       configuration = FeignConfiguration.class
)
public interface SysApi {

   @GetMapping(value ="/user/replaceToken", consumes = MediaType.APPLICATION_JSON_VALUE)
   Result<String> replaceToken();

  @GetMapping(value = "/sysRegion/getSysRegionByName")
  Result<SysRegionForm> getSysRegionByName(@ApiParam(value = "行政区划名称", required = true) @RequestParam(value = "regionName") String regionName);


  @GetMapping("/fileManage/getFileRealPath/{id}")
  @ResponseBody
  Result<String> getFileRealPath(@PathVariable("id") String id);
}
