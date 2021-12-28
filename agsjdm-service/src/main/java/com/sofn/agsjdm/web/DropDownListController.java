package com.sofn.agsjdm.web;


import com.sofn.agsjdm.service.InformationManagementService;
import com.sofn.agsjdm.util.RegionUtil;
import com.sofn.agsjdm.vo.DropDownVo;
import com.sofn.common.log.SofnLog;
import com.sofn.common.model.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Api(value = "下拉列表管理相关接口", tags = "下拉列表管理相关接口")
@Slf4j
@RestController
@RequestMapping("/dropDownList")
public class DropDownListController {

    @Resource
    private InformationManagementService informationManagementService;

    @SofnLog("《下拉列表》获取农业湿地示范点收集列表")
    @ApiOperation(value = "《下拉列表》获取农业湿地示范点收集列表")
    @GetMapping("/listForPointCollect")
    public Result<List<DropDownVo>> listForPointCollect() {
        return Result.ok(informationManagementService.listForPointCollect(RegionUtil.getRegionLastCode()));
    }
}
