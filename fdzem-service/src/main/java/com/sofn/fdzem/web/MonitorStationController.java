package com.sofn.fdzem.web;


import com.sofn.common.exception.SofnException;
import com.sofn.common.log.SofnLog;
import com.sofn.common.model.BaseData;
import com.sofn.common.model.Result;
import com.sofn.common.utils.BaseDataUtils;
import com.sofn.fdzem.feign.UserFeign;
import com.sofn.fdzem.model.MonitorStation;
import com.sofn.fdzem.service.MonitorStationService;
import com.sofn.fdzem.util.JustGetOrganization;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 袁彬峰
 * @since 2020-04-01
 */
@Slf4j
@Api(tags = "监测站信息维护", description = "")
@RestController
@RequestMapping("/monitorStation")
public class MonitorStationController {

    @Autowired
    private MonitorStationService monitorStationService;
    @Autowired
    private UserFeign userFeign;

    @GetMapping("/getMonitorStationInfo")
    @SofnLog("监测站详情")
    @RequiresPermissions("fdzem:monitorStation:view")
    @ApiOperation(value = "再打开新增监测站页面时候获取监测站名称", notes = "再打开新增监测站页面时候获取监测站名称,这里会响应检测单位")
    public Result<Object> getMonitorStationInfo() {
        Object object = monitorStationService.getMonitorStationNameOrDetailInfo();
        return Result.ok(object, "获取监测站信息成功");
    }

    @PostMapping("/saveOrUpdate")
    @SofnLog("保存或修改监测站的信息")
    @RequiresPermissions("fdzem:monitorStation:saveOrUpdate")
    @ApiOperation(value = "第一次为保存，以后为修改", notes = "根据是否传id判断保存或者修改(添加id传0)")
    public Result saveOrUpdate(@RequestBody MonitorStation monitorStation) {
        if (monitorStation.getAddress().isEmpty() || monitorStation.getLatitude().isEmpty() || monitorStation.getLongitude().isEmpty()
                || monitorStation.getWatersType().isEmpty() || monitorStation.getSeaArea().isEmpty() || monitorStation.getWatersName().isEmpty())
            throw new SofnException("输入不能为空");
        if (monitorStation.getId() == 0 || monitorStation.getId() == null) {
            monitorStation.setId(null);
            monitorStation.setIsDistribute("N");
            String organizationId = JustGetOrganization.getOrganizationId(userFeign);
            monitorStation.setOrganizationId(organizationId);
        }
        monitorStationService.saveOrUpdate(monitorStation);
        return Result.ok("修改成功");
    }

    @GetMapping("getWatersType")
    @SofnLog("获取水域类型下拉框")
    @ApiOperation(value = "获取水域类型下拉框", notes = "获取水域类型下拉框")
    public Result<List<BaseData>> getWatersType() {
        List<BaseData> list = BaseDataUtils.getByType("fdzem", "water_type");
        Result<List<BaseData>> listResult = new Result<>(list);
        listResult.setCode(200);
        return listResult;
    }

    @GetMapping("getSeaArea")
    @SofnLog("获取所属海区或流域下拉框")
    @ApiOperation(value = "获取所属海区或流域下拉框", notes = "获取所属海区或流域下拉框")
    public Result<List<BaseData>> getSeaArea() {
        List<BaseData> list = BaseDataUtils.getByType("fdzem", "water_area");
        Result<List<BaseData>> listResult = new Result<>(list);
        listResult.setCode(200);
        return listResult;
    }

    @GetMapping("getWatersName")
    @SofnLog("获取水域名称下拉框")
    @ApiOperation(value = "获取水域名称下拉框", notes = "获取水域名称下拉框")
    public Result<List<BaseData>> getWatersName() {
        List<BaseData> list = BaseDataUtils.getByType("fdzem", "water_name");
        Result<List<BaseData>> listResult = new Result<>(list);
        listResult.setCode(200);
        return listResult;
    }
}

