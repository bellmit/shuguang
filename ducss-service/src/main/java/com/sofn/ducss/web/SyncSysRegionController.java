package com.sofn.ducss.web;

import com.sofn.common.model.Result;
import com.sofn.ducss.service.SyncSysRegionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/syncSysRegion")
@Api(tags = "同步支撑平台区划数据")
public class SyncSysRegionController {

    @Autowired
    private SyncSysRegionService syncSysRegionService;

/*    @GetMapping("/syncSysRegion")
    @ApiOperation(value = "同步支撑平台行政区划数据")
    public Result<String> syncSysRegion(String year){

        syncSysRegionService.syncSysRegion(year);
        return Result.ok("同步成功");

    }*/

}
