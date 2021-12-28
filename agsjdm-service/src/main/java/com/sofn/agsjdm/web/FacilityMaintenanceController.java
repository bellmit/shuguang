package com.sofn.agsjdm.web;

import com.sofn.agsjdm.model.BasicsCollection;
import com.sofn.agsjdm.model.FacilityMaintenance;
import com.sofn.agsjdm.service.FacilityMaintenanceService;
import com.sofn.common.log.SofnLog;
import com.sofn.common.model.Result;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-04-16 9:25
 */
@Slf4j
@Api(tags = "设施监控管理接口")
@RestController
@RequestMapping("/fm")
public class FacilityMaintenanceController extends BaseController {
    @Autowired
    private FacilityMaintenanceService facilityMaintenanceService;

    @RequiresPermissions("agsjdm:fm:create")
    @SofnLog("新增基础信息收集")
    @ApiOperation(value = "新增基础信息收集")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result insert(@Validated @RequestBody @ApiParam(name = "基础信息收集对象", value = "传入json格式", required = true) FacilityMaintenance bc, BindingResult result){
        //         校验参数
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        facilityMaintenanceService.insert(bc);
        return Result.ok();
    }
    @RequiresPermissions("agsjdm:fm:update")
    @SofnLog("修改基础信息收集")
    @ApiOperation(value = "修改基础信息收集")
    @PutMapping(value = "/update")
    public Result update(@Validated @RequestBody @ApiParam(name = "基础信息收集对象", value = "传入json格式", required = true) FacilityMaintenance bc,BindingResult result){
        //         校验参数
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        facilityMaintenanceService.update(bc);
        return Result.ok();
    }
    @RequiresPermissions("agsjdm:fm:view")
    @SofnLog("获取基础信息详情")
    @ApiOperation(value = "获取基础信息详情")
    @GetMapping(value = "/get")
    public Result<FacilityMaintenance> get(@ApiParam(value = "主键", required = true) @RequestParam(value = "id",required = true) String id){
        return Result.ok(facilityMaintenanceService.get(id));
    }
    @RequiresPermissions("agsjdm:fm:delete")
    @SofnLog("删除基础信息")
    @ApiOperation(value = "删除基础信息")
    @DeleteMapping(value = "/delete")
    public Result delete(@ApiParam(value = "主键", required = true) @RequestParam(value = "id",required = true) String id){
        facilityMaintenanceService.delete(id);
        return Result.ok();
    }

    @ApiOperation(value = "分页查询")
    @GetMapping("/list")
    public Result<PageUtils<FacilityMaintenance>> listPage(
            @ApiParam("湿地区ID") @RequestParam(required = false) String wetlandId,
            @ApiParam("道路情况") @RequestParam(required = false) String road,
            @ApiParam("人员情况") @RequestParam(required = false) String personSit,
            @ApiParam("开始时间(yyyy-MM-dd)") @RequestParam(required = false) String startTime,
            @ApiParam("结束时间(yyyy-MM-dd)") @RequestParam(required = false) String endTime,
            @ApiParam(value = "pageNo", required = true) @RequestParam() Integer pageNo,
            @ApiParam(value = "pageSize", required = true) @RequestParam() Integer pageSize) {

        return Result.ok(facilityMaintenanceService.list(getQueryMap(wetlandId, road,personSit,
                startTime, endTime), pageNo, pageSize));
    }

    @RequiresPermissions("agsjdm:fm:export")
    @GetMapping(value = "/export", produces = "application/octet-stream")
    @ApiOperation(value = "导出", produces = "application/octet-stream")
    public void export(
            @ApiParam("湿地区ID") @RequestParam(required = false) String wetlandId,
            @ApiParam("道路情况") @RequestParam(required = false) String road,
            @ApiParam("人员情况") @RequestParam(required = false) String personSit,
            @ApiParam("开始时间(yyyy-MM-dd)") @RequestParam(required = false) String startTime,
            @ApiParam("结束时间(yyyy-MM-dd)") @RequestParam(required = false) String endTime,
            HttpServletResponse response) {
        facilityMaintenanceService.export(getQueryMap(wetlandId, road,personSit,
                startTime, endTime), response);
    }

    private Map<String, Object> getQueryMap(String wetlandId, String road,String personSit, String startTime, String endTime) {
        Map map = new HashMap<String, Object>(7);
        map.put("wetlandId", wetlandId);
        map.put("road", road);
        map.put("personSit", personSit);
        map.put("startTime", startTime);
        if (StringUtils.hasText(endTime)) {
            map.put("endTime", endTime + " 23:59:59");
        }
        return map;
    }


}
