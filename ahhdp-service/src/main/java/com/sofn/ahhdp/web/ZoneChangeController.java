package com.sofn.ahhdp.web;

import com.sofn.ahhdp.enums.AuditStatusEnum;
import com.sofn.ahhdp.model.Zone;
import com.sofn.ahhdp.model.ZoneRecord;
import com.sofn.ahhdp.service.ZoneApplyService;
import com.sofn.ahhdp.service.ZoneRecordService;
import com.sofn.ahhdp.service.ZoneService;
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

@Slf4j
@Api(tags = "国家畜禽遗传资源保护区变更模块接口")
@RequestMapping("/zoneChange")
@RestController
public class ZoneChangeController {

    @Autowired
    private ZoneService zoneService;
    @Autowired
    private ZoneApplyService zoneApplyService;
    @Autowired
    private ZoneRecordService zoneRecordService;

    @ApiOperation(value = "下载导入模板", produces = "application/octet-stream")
    @GetMapping(value = "/downTemplate", produces = "application/octet-stream")
//    @RequiresPermissions("ahhdp:zoneChange:import")
    public void downDataCountModel(HttpServletResponse response) {
        zoneService.downTemplate(response);
    }

    @ApiOperation(value = "导入国家畜禽遗传资源保护区")
    @PostMapping(value = "importZone", produces = "multipart/form-data")
    @RequiresPermissions("ahhdp:zoneChange:import")
    public Result importZone(@RequestParam @ApiParam("文件导入地址") MultipartFile multipartFile) {
        zoneService.importZone(multipartFile);
        return Result.ok("导入成功");
    }

    @ApiOperation(value = "申请变更")
    @PutMapping("/apply")
    @RequiresPermissions("ahhdp:zoneChange:apply")
    public Result apply(
            @ApiParam(value = "主键id", required = true) @RequestParam() String id,
            @ApiParam(value = "保护区名称") @RequestParam(required = false) String newName,
            @ApiParam(value = "建设单位") @RequestParam(required = false) String newCompany,
            @ApiParam(value = "保护区范围") @RequestParam(required = false) String newRange) {
        zoneApplyService.apply(id, newName, newCompany, newRange);
        return Result.ok();
    }

    @ApiOperation(value = "详情(保护区管理)")
    @GetMapping("/get")
    @RequiresPermissions("ahhdp:zoneChange:view")
    public Result<ZoneRecord> get(
            @ApiParam(value = "主键id", required = true) @RequestParam() String id) {
        return Result.ok(zoneApplyService.getForManage(id));
    }

    @ApiOperation(value = "审核(通过)")
    @PutMapping("/auditPass")
    @RequiresPermissions("ahhdp:zoneChange:audit")
    public Result auditPass(
            @ApiParam(value = "主键id", required = true) @RequestParam() String id,
            @ApiParam(value = "审核意见", required = true) @RequestParam() String opinion) {
        zoneApplyService.auditPass(id, opinion);
        return Result.ok();
    }

    @ApiOperation(value = "审核(不通过)")
    @PutMapping("/auditReturn")
    @RequiresPermissions("ahhdp:zoneChange:audit")
    public Result auditReturn(
            @ApiParam(value = "主键id", required = true) @RequestParam() String id,
            @ApiParam(value = "审核意见", required = true) @RequestParam() String opinion) {
        zoneApplyService.auditUnpass(id, opinion);
        return Result.ok();
    }

    @ApiOperation(value = "分页查询保护区管理")
    @GetMapping("/listPageForZone")
    public Result<PageUtils<Zone>> listPageForZone(
            @ApiParam("保护区名称") @RequestParam(required = false) String areaName,
            @ApiParam("建设单位") @RequestParam(required = false) String company,
            @ApiParam(value = "pageNo", required = true) @RequestParam() Integer pageNo,
            @ApiParam(value = "pageSize", required = true) @RequestParam() Integer pageSize) {
        return Result.ok(zoneService.listByParams(getQueryMap(areaName, company, AuditStatusEnum.PASS.getKey(),
                null, null), pageNo, pageSize));
    }

    @GetMapping(value = "/export", produces = "application/octet-stream")
    @ApiOperation(value = "导出保护区管理", produces = "application/octet-stream")
    @RequiresPermissions("ahhdp:zoneChange:exportManage")
    public void exportForManage(
            @ApiParam("保护区名称") @RequestParam(required = false) String areaName,
            @ApiParam("建设单位") @RequestParam(required = false) String company,
            HttpServletResponse response) {
        zoneService.export(getQueryMap(areaName, company, AuditStatusEnum.PASS.getKey(),
                null, null), response);
    }

    @ApiOperation(value = "分页查询变更申请")
    @GetMapping("/listPageForApply")
    public Result<PageUtils<Zone>> listPageForApply(
            @ApiParam("保护区名称") @RequestParam(required = false) String areaName,
            @ApiParam("建设单位") @RequestParam(required = false) String company,
            @ApiParam("开始时间(yyyy-MM-dd)") @RequestParam(required = false) String startTime,
            @ApiParam("结束时间(yyyy-MM-dd)") @RequestParam(required = false) String endTime,
            @ApiParam("审核状态 1通过0不通过") @RequestParam(required = false) String auditStatus,
            @ApiParam(value = "pageNo", required = true) @RequestParam() Integer pageNo,
            @ApiParam(value = "pageSize", required = true) @RequestParam() Integer pageSize) {
        return Result.ok(zoneApplyService.listByParams(getQueryMap(areaName, company, auditStatus, startTime, endTime)
                , pageNo, pageSize));
    }

    @ApiOperation(value = "分页查询变更记录")
    @GetMapping("/listPageForRecord")
    public Result<PageUtils<ZoneRecord>> listPageForRecord(
            @ApiParam("保护区名称") @RequestParam(required = false) String areaName,
            @ApiParam("建设单位") @RequestParam(required = false) String company,
            @ApiParam("开始时间(yyyy-MM-dd)") @RequestParam(required = false) String startTime,
            @ApiParam("结束时间(yyyy-MM-dd)") @RequestParam(required = false) String endTime,
            @ApiParam(value = "pageNo", required = true) @RequestParam() Integer pageNo,
            @ApiParam(value = "pageSize", required = true) @RequestParam() Integer pageSize) {
        return Result.ok(zoneRecordService.listByParams(getQueryMap(
                areaName, company, null, startTime, endTime), pageNo, pageSize));
    }

    @GetMapping(value = "/exportForRecord", produces = "application/octet-stream")
    @ApiOperation(value = "导出变更记录", produces = "application/octet-stream")
    public void exportForRecord(
            @ApiParam("保护区名称") @RequestParam(required = false) String areaName,
            @ApiParam("建设单位") @RequestParam(required = false) String company,
            @ApiParam("开始时间(yyyy-MM-dd)") @RequestParam(required = false) String startTime,
            @ApiParam("结束时间(yyyy-MM-dd)") @RequestParam(required = false) String endTime,
            HttpServletResponse response) {
        zoneRecordService.export(getQueryMap(areaName, company, null, startTime, endTime), response);
    }


    /**
     * 封装查询参数
     */
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
