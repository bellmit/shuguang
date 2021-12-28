package com.sofn.gatewayservice.api;

import com.sofn.common.config.FeignConfiguration;
import com.sofn.common.fileutil.SysFileManageVo;
import com.sofn.common.model.Result;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

 /**
  * @Description: 信息资源目录api
  * @Author: quzhijie
  * @Date: 2020/4/28 14:20
  **/
@FeignClient(
        value = "share-service",
        configuration = FeignConfiguration.class
)
public interface ShareApi {

    @GetMapping(value ="/tokenVerify/user", consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<String> tokenVerify(@RequestParam("userTokens") String userTokens, @RequestParam("applyUrl")String applyUrl);

}
