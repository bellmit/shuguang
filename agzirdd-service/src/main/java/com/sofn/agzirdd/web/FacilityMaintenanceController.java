package com.sofn.agzirdd.web;

import com.google.common.collect.Maps;
import com.sofn.agzirdd.excelmodel.FacilityMaintenanceExcel;
import com.sofn.agzirdd.model.FacilityMaintenance;
import com.sofn.agzirdd.service.FacilityMaintenanceService;
import com.sofn.agzirdd.util.ExportUtil;
import com.sofn.agzirdd.vo.FacilityQueryForm;
import com.sofn.common.exception.SofnException;
import com.sofn.common.log.SofnLog;
import com.sofn.common.model.Result;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.UserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Api(value = "基础设施维护", tags = "基础设施维护")
@RestController
@RequestMapping("/facilityMaintenance")
public class FacilityMaintenanceController {
    @Autowired
    private FacilityMaintenanceService facilityMaintenanceService;

    @SofnLog("获取基础设施维护信息分页列表")
    @ApiOperation(value="获取基础设施维护信息分页列表")
    @PostMapping("/getFacilityMaintenancePage")
    public Result<PageUtils<FacilityMaintenance>> getFacilityMaintenancePage(@Validated @RequestBody @ApiParam(name = "查询参数对象", value = "传入json格式", required = true) FacilityQueryForm facilityQueryForm, HttpServletRequest request){
        //放入查询条件
        Map<String, Object> params = Maps.newHashMap();
        params.put("monitorName",facilityQueryForm.getMonitorName());
        params.put("facilityName",facilityQueryForm.getFacilityName());
        params.put("startTime",facilityQueryForm.getStartTime());
        params.put("endTime",facilityQueryForm.getEndTime());
        PageUtils<FacilityMaintenance> pageInfo = facilityMaintenanceService.getFacilityByPage(params,facilityQueryForm.getPageNo(),facilityQueryForm.getPageSize());

        return Result.ok(pageInfo);
    }

    @SofnLog("新增基础设施维护信息")
    @ApiOperation(value="新增基础设施维护信息")
    @PostMapping("/addFacilityMaintenance")
    public Result<String> addFacilityMaintenance(
            @Validated @RequestBody @ApiParam(name = "基础设施维护对象", value = "传入json格式", required = true) FacilityMaintenance facilityMaintenance,
            HttpServletRequest request){
        //根据token获取登录用户Id
        String userId = UserUtil.getLoginUserId();
        if(StringUtils.isBlank(userId)){
            throw new SofnException("当前登录用户异常");
        }
        facilityMaintenance.setCreateUserId(userId);
        String userName = UserUtil.getUsernameById(userId);
        facilityMaintenance.setCreateUserName(userName);

        boolean flag = facilityMaintenanceService.save(facilityMaintenance);

        return flag?Result.ok("新增成功"):Result.ok("新增失败,请联系管理员");
    }


    @SofnLog("删除指定id的基础设施维护信息")
    @ApiOperation(value="删除指定id的基础设施维护信息")
    @GetMapping("/deleteById")
    public Result deleteById(
            @ApiParam(name = "id",value = "监测点设施管理信息ID",required = true)@RequestParam(value = "id")String id) {
        boolean flag =  facilityMaintenanceService.deleteById(id);

        return flag?Result.ok("删除成功"):Result.ok("删除失败,请联系管理员");
    }

    @SofnLog("获取指定id的基础设施维护信息")
    @ApiOperation(value="获取指定id的基础设施维护信息")
    @GetMapping("/getFacilityMaintenanceById")
    public Result<FacilityMaintenance> getFacilityMaintenanceById(
            @ApiParam(name = "id",value = "基础设施维护信息ID",required = true)@RequestParam(value = "id")String id){

        FacilityMaintenance facilityMaintenance = facilityMaintenanceService.getById(id);
        return Result.ok(facilityMaintenance);
    }

    @SofnLog("编辑基础设施维护信息")
    @ApiOperation(value="编辑基础设施维护信息")
    @PostMapping("/updateFacilityMaintenance")
    public Result<String> updateFacilityMaintenance(
            @Validated @RequestBody @ApiParam(name = "基础设施维护对象", value = "传入json格式", required = true) FacilityMaintenance facilityMaintenance,
            HttpServletRequest request){
        //根据token获取登录用户Id
        String userId = UserUtil.getLoginUserId();
        if(StringUtils.isBlank(userId)){
            throw new SofnException("当前登录用户异常");
        }

        boolean flag = facilityMaintenanceService.updateById(facilityMaintenance);
        return flag?Result.ok("编辑成功"):Result.ok("编辑失败,请联系管理员");
    }


    @SofnLog(value = "基础设施维护信息导出")
    @ApiOperation(value = "基础设施维护信息导出")
    @GetMapping(value = "/exportFacilityMaintenance", produces = "application/octet-stream")
    public void exportFacilityMaintenance(HttpServletResponse response){
//        //放入查询条件
//        Map<String, Object> params = Maps.newHashMap();
//        params.put("monitorName",facilityQueryForm.getMonitorName());
//        params.put("facilityName",facilityQueryForm.getFacilityName());
//        params.put("startTime",facilityQueryForm.getStartTime());
//        params.put("endTime",facilityQueryForm.getEndTime());

        List<FacilityMaintenanceExcel> facilityLisExportList = facilityMaintenanceService.getFacilityMaintenanceLisToExport(Maps.newHashMap());
        ExportUtil.createExcel(FacilityMaintenanceExcel.class,facilityLisExportList,response,"facilityMaintenance.xlsx");

    }


}




