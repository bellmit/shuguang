package com.sofn.agsjdm.api;

import com.sofn.agsjdm.vo.DropDownVo;
import com.sofn.agsjdm.vo.WetExamplePointCollectVo;
import com.sofn.common.config.FeignConfiguration;
import com.sofn.common.model.Result;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "agsjsi-service", configuration = FeignConfiguration.class)
public interface JsiApi {


//    @ApiOperation(value = "《下拉列表》获取农业湿地示范点收集列表")
//    @GetMapping("/dropDownManage/listForPointCollect")
//    Result<List<DropDownVo>> listForPointCollect(@RequestParam(value = "lastRegionCode") String lastRegionCode);
//    @ApiOperation(value = "根据id，获取农业湿地示范点收集数据")
//    @GetMapping("/pointCollect/get")
//    Result<WetExamplePointCollectVo> get(@RequestParam(value = "id") @ApiParam(name = "id", value = "农业湿地示范点收集主键", required = true) String id);
}
