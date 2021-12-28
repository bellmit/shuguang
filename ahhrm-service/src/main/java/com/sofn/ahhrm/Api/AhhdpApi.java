package com.sofn.ahhrm.Api;

import com.sofn.ahhrm.vo.DropDownVo;
import com.sofn.common.config.FeignConfiguration;
import com.sofn.common.model.Result;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@FeignClient(value = "ahhdp-service", configuration = FeignConfiguration.class)
public interface AhhdpApi {



    @ApiOperation(value = "《下拉列表》获取当前省份下的品种名称")
    @GetMapping("/dropDownList/listForName")
    Result<List<DropDownVo>> listForName(@RequestParam(value = "provinceCode", required = true) @ApiParam(name = "provinceCode", value = "省级区域编码", required = true) String provinceCode);
    @GetMapping("/dropDownList/listForAllName")
    Result<List<DropDownVo>> listForAllName();
}
