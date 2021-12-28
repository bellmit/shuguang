package com.sofn.ahhdp.web;

import com.sofn.ahhdp.enums.AuditStatusEnum;
import com.sofn.ahhdp.model.Farm;
import com.sofn.ahhdp.model.FarmRecord;

import com.sofn.ahhdp.service.*;
import com.sofn.common.model.Result;
import com.sofn.common.utils.PageUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-04-21 15:54
 */
@Slf4j
@Api(tags = "国家畜禽遗传资源保护场变更模块接口")
@RequestMapping("/farmChange")
@RestController
public class FarmChangeController {
    @Autowired
    private FarmService farmService;
    @Autowired
    private FarmApplyService farmApplyService;
    @Autowired
    private FarmRecordService farmRecordService;


    @ApiOperation(value = "下载导入模板", produces = "application/octet-stream")
    @GetMapping(value = "/downTemplate", produces = "application/octet-stream")
    public void downDataCountModel(HttpServletResponse response) {
        farmService.downTemplate(response);
    }


    @RequiresPermissions("ahhdp:farmChange:import")
    @ApiOperation(value = "导入国家畜禽遗传资源保护场")
    @PostMapping(value = "importFarm", produces = "multipart/form-data")
    public Result importFarm(@RequestParam @ApiParam("文件导入地址") MultipartFile multipartFile) {
        farmService.importFarm(multipartFile);
        return Result.ok("导入成功");
    }
    @RequiresPermissions("ahhdp:farmChange:apply")
    @ApiOperation(value = "申请变更")
    @PutMapping("/apply")
    public Result apply(
            @ApiParam(value = "主键id", required = true) @RequestParam() String id,
            @ApiParam(value = "保护场名称") @RequestParam(required = false) String newName,
            @ApiParam(value = "建设单位") @RequestParam(required = false) String newCompany) {
        farmApplyService.apply(id, newName, newCompany);
        return Result.ok();
    }
    @RequiresPermissions("ahhdp:farmChange:view")
    @ApiOperation(value = "详情(保护场管理)")
    @GetMapping("/get")
    public Result<FarmRecord> get(
            @ApiParam(value = "主键id", required = true) @RequestParam() String id) {
        return Result.ok(farmApplyService.getForManage(id));
    }
    @RequiresPermissions("ahhdp:farmChange:audit")
    @ApiOperation(value = "审核(通过)")
    @PutMapping("/auditPass")
    public Result auditPass(
            @ApiParam(value = "主键id", required = true) @RequestParam() String id,
            @ApiParam(value = "审核意见", required = true) @RequestParam() String opinion) {
        farmApplyService.auditPass(id, opinion);
        return Result.ok();
    }
    @RequiresPermissions("ahhdp:farmChange:audit")
    @ApiOperation(value = "审核(不通过)")
    @PutMapping("/auditReturn")
    public Result auditReturn(
            @ApiParam(value = "主键id", required = true) @RequestParam() String id,
            @ApiParam(value = "审核意见", required = true) @RequestParam() String opinion) {
        farmApplyService.auditUnpass(id, opinion);
        return Result.ok();
    }

    @ApiOperation(value = "分页查询保护场管理")
    @GetMapping("/listPageForFarm")
    public Result<PageUtils<Farm>> listPageForFarm(
            @ApiParam("保护场名称") @RequestParam(required = false) String areaName,
            @ApiParam("建设单位") @RequestParam(required = false) String company,
            @ApiParam(value = "pageNo", required = true) @RequestParam() Integer pageNo,
            @ApiParam(value = "pageSize", required = true) @RequestParam() Integer pageSize) {
        return Result.ok(farmService.listByParams(getQueryMap(areaName, company, AuditStatusEnum.PASS.getKey(),
                null, null), pageNo, pageSize));
    }
    @RequiresPermissions("ahhdp:farmChange:exportManage")
    @GetMapping(value = "/export", produces = "application/octet-stream")
    @ApiOperation(value = "导出保护场管理", produces = "application/octet-stream")
    public void export(
            @ApiParam("保护场名称") @RequestParam(required = false) String areaName,
            @ApiParam("建设单位") @RequestParam(required = false) String company,
            HttpServletResponse response) {
        farmService.export(getQueryMap(areaName, company, AuditStatusEnum.PASS.getKey(),
                null, null), response);
    }

    @ApiOperation(value = "分页查询变更申请")
    @GetMapping("/listPageForApply")
    public Result<PageUtils<Farm>> listPageForApply(
            @ApiParam("保护场名称") @RequestParam(required = false) String areaName,
            @ApiParam("建设单位") @RequestParam(required = false) String company,
            @ApiParam("开始时间(yyyy-MM-dd)") @RequestParam(required = false) String startTime,
            @ApiParam("结束时间(yyyy-MM-dd)") @RequestParam(required = false) String endTime,
            @ApiParam("审核状态 1通过0不通过") @RequestParam(required = false) String auditStatus,
            @ApiParam(value = "pageNo", required = true) @RequestParam() Integer pageNo,
            @ApiParam(value = "pageSize", required = true) @RequestParam() Integer pageSize) {
        return Result.ok(farmApplyService.listByParams(getQueryMap(areaName, company, auditStatus, startTime, endTime)
                , pageNo, pageSize));
    }

    @ApiOperation(value = "分页查询变更记录")
    @GetMapping("/listPageForRecord")
    public Result<PageUtils<FarmRecord>> listPageForRecord(
            @ApiParam("保护场名称") @RequestParam(required = false) String areaName,
            @ApiParam("建设单位") @RequestParam(required = false) String company,
            @ApiParam("开始时间(yyyy-MM-dd)") @RequestParam(required = false) String startTime,
            @ApiParam("结束时间(yyyy-MM-dd)") @RequestParam(required = false) String endTime,
            @ApiParam(value = "pageNo", required = true) @RequestParam() Integer pageNo,
            @ApiParam(value = "pageSize", required = true) @RequestParam() Integer pageSize) {
        return Result.ok(farmRecordService.listByParams(getQueryMap(areaName, company, null, startTime, endTime), pageNo, pageSize));
    }
    private Map<String, Object> getQueryMap(String areaName, String company,
                                            String auditStatus, String startTime, String endTime) {
        Map map = new HashMap<String, Object>(7);
        map.put("areaName", areaName);
        map.put("company", company);
        map.put("auditStatus", auditStatus);
        map.put("startTime", startTime);
        if (StringUtils.hasText(endTime)) {
            map.put("endTime", endTime + " 23:59:59");
        }
        return map;
    }
}
