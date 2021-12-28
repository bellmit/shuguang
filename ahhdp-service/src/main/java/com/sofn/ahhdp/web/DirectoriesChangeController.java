package com.sofn.ahhdp.web;

import com.sofn.ahhdp.enums.AuditStatusEnum;
import com.sofn.ahhdp.model.Directories;
import com.sofn.ahhdp.model.DirectoriesApply;
import com.sofn.ahhdp.model.DirectoriesRecord;
import com.sofn.ahhdp.service.DirectoriesApplyService;
import com.sofn.ahhdp.service.DirectoriesRecordService;
import com.sofn.ahhdp.service.DirectoriesService;
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
 * @Date: 2020-04-21 11:39
 */
@Slf4j
@Api(tags = "国家畜禽遗传资源保护名录变更模块接口")
@RequestMapping("/dChange")
@RestController

public class DirectoriesChangeController {
    @Autowired
    private DirectoriesService directoriesService;
    @Autowired
    private DirectoriesApplyService directoriesApplyService;
    @Autowired
    private DirectoriesRecordService directoriesRecordService;


    @ApiOperation(value = "下载导入模板", produces = "application/octet-stream")
    @GetMapping(value = "/downTemplate", produces = "application/octet-stream")
    public void downDataCountModel(HttpServletResponse response) {
        directoriesService.downTemplate(response);
    }

    @RequiresPermissions("ahhdp:directoriesChange:import")
    @ApiOperation(value = "导入国家畜禽遗传资源保护名录")
    @PostMapping(value = "importDirectories", produces = "multipart/form-data")
    public Result importDirectories(@RequestParam @ApiParam("文件导入地址") MultipartFile multipartFile) {
        directoriesService.importDirectories(multipartFile);
        return Result.ok("导入成功");
    }

    @RequiresPermissions("ahhdp:directoriesChange:apply")
    @ApiOperation(value = "申请变更")
    @PutMapping("/apply")
    public Result apply(
            @ApiParam(value = "主键id", required = true) @RequestParam() String id,
            @ApiParam(value = "新品种名称") @RequestParam(required = false) String newName,
            @ApiParam(value = "新所属地区（注意所属地区输入省级行政区划且用英文,隔开）") @RequestParam(required = false) String newCompany) {
        directoriesApplyService.apply(id, newName, newCompany);
        return Result.ok();
    }
    @RequiresPermissions("ahhdp:directoriesChange:view")
    @ApiOperation(value = "详情(保护名录管理)")
    @GetMapping("/get")
    public Result<DirectoriesRecord> get(
            @ApiParam(value = "主键id", required = true) @RequestParam() String id) {
        return Result.ok(directoriesApplyService.getForManage(id));
    }
    @RequiresPermissions("ahhdp:directoriesChange:audit")
    @ApiOperation(value = "审核(通过)")
    @PutMapping("/auditPass")
    public Result auditPass(
            @ApiParam(value = "主键id", required = true) @RequestParam() String id,
            @ApiParam(value = "审核意见", required = true) @RequestParam() String opinion) {
        directoriesApplyService.auditPass(id, opinion);
        return Result.ok();
    }
    @RequiresPermissions("ahhdp:directoriesChange:audit")
    @ApiOperation(value = "审核(不通过)")
    @PutMapping("/auditReturn")
    public Result auditReturn(
            @ApiParam(value = "主键id", required = true) @RequestParam() String id,
            @ApiParam(value = "审核意见", required = true) @RequestParam() String opinion) {
        directoriesApplyService.auditUnpass(id, opinion);
        return Result.ok();
    }

    @ApiOperation(value = "分页查询保护名录管理")
    @GetMapping("/listPageForDirectories")
    public Result<PageUtils<Directories>> listPageForDirectories(
            @ApiParam("新品种名称") @RequestParam(required = false) String areaName,
            @ApiParam("新所属地区") @RequestParam(required = false) String company,
            @ApiParam("品种类别") @RequestParam(required = false) String category,
            @ApiParam(value = "pageNo", required = true) @RequestParam() Integer pageNo,
            @ApiParam(value = "pageSize", required = true) @RequestParam() Integer pageSize) {
        return Result.ok(directoriesService.listByParams(getQueryMap(areaName, company, category,AuditStatusEnum.PASS.getKey(),
                null, null), pageNo, pageSize));
    }
    @RequiresPermissions("ahhdp:directoriesChange:exportManage")
    @GetMapping(value = "/export", produces = "application/octet-stream")
    @ApiOperation(value = "导出保护名录管理", produces = "application/octet-stream")
    public void export(
            @ApiParam("新品种名称") @RequestParam(required = false) String areaName,
            @ApiParam("新所属地区") @RequestParam(required = false) String company,
            @ApiParam("品种类别") @RequestParam(required = false) String category,
            HttpServletResponse response) {
        directoriesService.export(getQueryMap(areaName, company,category,AuditStatusEnum.PASS.getKey(),
                null, null), response);
    }

    @ApiOperation(value = "分页查询变更申请")
    @GetMapping("/listPageForApply")
    public Result<PageUtils<DirectoriesApply>> listPageForApply(
            @ApiParam("新品种名称") @RequestParam(required = false) String areaName,
            @ApiParam("新所属地区") @RequestParam(required = false) String company,
            @ApiParam("品种类别") @RequestParam(required = false) String category,
            @ApiParam("开始时间(yyyy-MM-dd)") @RequestParam(required = false) String startTime,
            @ApiParam("结束时间(yyyy-MM-dd)") @RequestParam(required = false) String endTime,
            @ApiParam("审核状态 1通过0不通过") @RequestParam(required = false) String auditStatus,
            @ApiParam(value = "pageNo", required = true) @RequestParam() Integer pageNo,
            @ApiParam(value = "pageSize", required = true) @RequestParam() Integer pageSize) {
        return Result.ok(directoriesApplyService.listByParams(getQueryMap(areaName, company,category, auditStatus, startTime, endTime)
                , pageNo, pageSize));
    }

    @ApiOperation(value = "分页查询变更记录")
    @GetMapping("/listPageForRecord")
    public Result<PageUtils<DirectoriesRecord>> listPageForRecord(
            @ApiParam("新品种名称") @RequestParam(required = false) String areaName,
            @ApiParam("新所属地区") @RequestParam(required = false) String company,
            @ApiParam("品种类别") @RequestParam(required = false) String category,
            @ApiParam("开始时间(yyyy-MM-dd)") @RequestParam(required = false) String startTime,
            @ApiParam("结束时间(yyyy-MM-dd)") @RequestParam(required = false) String endTime,
            @ApiParam(value = "pageNo", required = true) @RequestParam() Integer pageNo,
            @ApiParam(value = "pageSize", required = true) @RequestParam() Integer pageSize) {
        return Result.ok(directoriesRecordService.listByParams(getQueryMap(areaName, company, category,null, startTime, endTime), pageNo, pageSize));
    }
    private Map<String, Object> getQueryMap(String areaName, String company,String category,
                                            String auditStatus, String startTime, String endTime) {
        Map map = new HashMap<String, Object>(7);
        map.put("areaName", areaName);
        map.put("company", company);
        map.put("category", category);
        map.put("auditStatus", auditStatus);
        map.put("startTime", startTime);
        if (StringUtils.hasText(endTime)) {
            map.put("endTime", endTime + " 23:59:59");
        }
        return map;
    }
}
