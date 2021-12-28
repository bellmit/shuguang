package com.sofn.agzirdd.web;

import com.google.common.collect.Maps;
import com.sofn.agzirdd.excelmodel.MaintenanceRegistrationExcel;
import com.sofn.agzirdd.model.MaintenanceRegistration;
import com.sofn.agzirdd.service.MaintenanceRegistrationService;
import com.sofn.agzirdd.util.ExportUtil;
import com.sofn.agzirdd.vo.MaintenanceRegistrationQueryForm;
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

@Api(value = "基础设施维护备案记录", tags = "基础设施维护备案记录")
@RestController
@RequestMapping("/maintenanceRegistration")
public class MaintenanceRegistrationController {
    @Autowired
    private MaintenanceRegistrationService maintenanceRegistrationService;

    @SofnLog("获取基础设施备案记录信息分页列表")
    @ApiOperation(value="获取基础设施备案记录信息分页列表")
    @PostMapping("/getMaintenancePage")
    public Result<PageUtils<MaintenanceRegistration>> getMaintenancePage(@Validated @RequestBody @ApiParam(name = "查询参数对象", value = "传入json格式", required = true) MaintenanceRegistrationQueryForm registrationQueryForm, HttpServletRequest request){
        //放入查询条件
        Map<String, Object> params = Maps.newHashMap();
        params.put("monitorName",registrationQueryForm.getMonitorName());
        params.put("facilityName",registrationQueryForm.getFacilityName());
        params.put("startTime",registrationQueryForm.getStartTime());
        params.put("endTime",registrationQueryForm.getEndTime());
        PageUtils<MaintenanceRegistration> pageInfo;
        pageInfo = maintenanceRegistrationService.getMaintenanceRegistrationByPage(params,registrationQueryForm.getPageNo(),registrationQueryForm.getPageSize());

        return Result.ok(pageInfo);
    }

    @ApiOperation(value="新增基础设施备案记录信息")
    @PostMapping("/addMaintenanceRegistration")
    public Result<String> addMaintenanceRegistration(
            @Validated @RequestBody @ApiParam(name = "设施备案记录信息对象", value = "传入json格式", required = true) MaintenanceRegistration maintenanceRegistration,
            HttpServletRequest request){
        //根据token获取登录用户Id
        String userId = UserUtil.getLoginUserId(request.getHeader("Authorization"));
        if(StringUtils.isBlank(userId)){
            throw new SofnException("当前登录用户异常");
        }
        maintenanceRegistration.setCreateUserId(userId);
        String userName = UserUtil.getUsernameById(userId);
        maintenanceRegistration.setCreateUserName(userName);

        boolean flag = maintenanceRegistrationService.save(maintenanceRegistration);

        return flag?Result.ok("新增成功"):Result.ok("新增失败,请联系管理员");
    }

    @SofnLog("删除指定id的基础设施备案记录信息")
    @ApiOperation(value="删除指定id的基础设施备案记录信息")
    @DeleteMapping("/deleteById")
    public Result deleteById(
            @ApiParam(name = "id",value = "基础设施备案记录信息ID",required = true)@RequestParam(value = "id")String id) {
        boolean flag =  maintenanceRegistrationService.removeById(id);

        return flag?Result.ok("删除成功"):Result.ok("删除失败,请联系管理员");
    }

    @SofnLog("获取指定id的基础设施备案记录信息")
    @ApiOperation(value="获取指定id的基础设施备案记录信息")
    @GetMapping("/getMaintenanceRegistrationById")
    public Result<MaintenanceRegistration> getMaintenanceRegistrationById(
            @ApiParam(name = "id",value = "基础设施备案记录信息ID",required = true)@RequestParam(value = "id")String id){

        MaintenanceRegistration maintenanceRegistration = maintenanceRegistrationService.getById(id);
        return Result.ok(maintenanceRegistration);
    }

    @SofnLog("编辑基础设施备案记录信息")
    @ApiOperation(value="编辑基础设施备案记录信息")
    @PostMapping("/updateMaintenanceRegistration")
    public Result<String> updateMaintenanceRegistration(
            @Validated @RequestBody @ApiParam(name = "基础设施备案记录对象", value = "传入json格式", required = true) MaintenanceRegistration maintenanceRegistration,
            HttpServletRequest request){
        //根据token获取登录用户Id
        String userId = UserUtil.getLoginUserId();
        if(StringUtils.isBlank(userId)){
            throw new SofnException("当前登录用户异常");
        }

        boolean flag = maintenanceRegistrationService.updateById(maintenanceRegistration);
        return flag?Result.ok("编辑成功"):Result.ok("编辑失败,请联系管理员");
    }


    @SofnLog(value = "基础设施备案记录信息导出")
    @ApiOperation(value = "基础设施备案记录信息导出")
    @GetMapping(value = "/exportMaintenanceRegistration", produces = "application/octet-stream")
    public void exportMaintenanceRegistration(HttpServletResponse response){
//        //放入查询条件
//        Map<String, Object> params = Maps.newHashMap();
//        params.put("monitorName",facilityQueryForm.getMonitorName());
//        params.put("facilityName",facilityQueryForm.getFacilityName());
//        params.put("startTime",facilityQueryForm.getStartTime());
//        params.put("endTime",facilityQueryForm.getEndTime());

        List<MaintenanceRegistrationExcel> maintenanceRegistrationExcelList = maintenanceRegistrationService.getMaintenanceRegistrationLisToExport(Maps.newHashMap());
        ExportUtil.createExcel(MaintenanceRegistrationExcel.class,maintenanceRegistrationExcelList,response,"maintenanceRegistration.xlsx");

    }


}




