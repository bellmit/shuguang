package com.sofn.agpjyz.web;

import com.google.common.collect.Maps;
import com.sofn.agpjyz.enums.ProcessEnum;
import com.sofn.agpjyz.service.SourceService;
import com.sofn.agpjyz.vo.*;
import com.sofn.common.model.Result;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "资源调查模块接口")
@RestController
@RequestMapping("/source")
public class SourceController extends BaseController {

    @Autowired
    private SourceService sourceService;

    @ApiOperation(value = "新增")
    @PostMapping("/save")
    @RequiresPermissions("agpjyz:source:create")
    public Result<SourceVo> save(
            @Validated @RequestBody SourceForm sourceForm, BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        return Result.ok(sourceService.save(sourceForm));
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("/delete")
    @RequiresPermissions("agpjyz:source:delete")
    public Result delete(
            @ApiParam(value = "主键id", required = true) @RequestParam() String id) {
        sourceService.delete(id);
        return Result.ok();
    }

    @ApiOperation(value = "详情")
    @GetMapping("/get")
    @RequiresPermissions("agpjyz:source:view")
    public Result<SourceVo> get(
            @ApiParam(value = "主键id", required = true) @RequestParam() String id) {
        return Result.ok(sourceService.get(id));
    }

    @ApiOperation(value = "获取最后一次填报基础信息")
    @GetMapping("/getLastCommit")
    public Result<SourceLastVo> getLastCommit() {
        return Result.ok(sourceService.getLastCommit());
    }

    @ApiOperation(value = "修改")
    @PutMapping("/update")
    @RequiresPermissions("agpjyz:source:update")
    public Result update(
            @Validated @RequestBody SourceForm sourceForm, BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        sourceService.update(sourceForm);
        return Result.ok();
    }

    @ApiOperation(value = "上报提交(列表页使用)")
    @PutMapping("/report")
    @RequiresPermissions("agpjyz:source:update")
    public Result report(@ApiParam(value = "主键id", required = true) @RequestParam() String id) {
        sourceService.report(id);
        return Result.ok();
    }

    @ApiOperation(value = "根据当前用户获取对应状态列表")
    @GetMapping("/getStatus")
    public Result<List<SelectVo>> getStatus() {
        return Result.ok(ProcessEnum.getStatus());
    }

    @ApiOperation(value = "撤回")
    @PutMapping("/cancel")
    @RequiresPermissions("agpjyz:source:cancel")
    public Result cancel(
            @ApiParam(value = "主键id", required = true) @RequestParam() String id) {
        sourceService.cancel(id);
        return Result.ok();
    }

    @ApiOperation(value = "批量审核(通过)")
    @PutMapping("/auditPass")
    @RequiresPermissions("agpjyz:source:audit")
    public Result auditPass(
            @Validated @RequestBody AuditForm auditForm, BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        sourceService.auditPass(auditForm.getId(), auditForm.getAuditOpinion());
        return Result.ok();
    }

    @ApiOperation(value = "批量审核(退回)")
    @PutMapping("/auditReturn")
    @RequiresPermissions("agpjyz:source:audit")
    public Result auditReturn(
            @Validated @RequestBody AuditForm auditForm, BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        sourceService.auditReturn(auditForm.getId(), auditForm.getAuditOpinion());
        return Result.ok();
    }

    @ApiOperation(value = "分页查询")
    @GetMapping("/listPage")
//    @RequiresPermissions("agpjyz:source:menu")
    public Result<PageUtils<SourceVo>> listPage(
            @ApiParam("物种ID") @RequestParam(required = false) String specId,
            @ApiParam("省") @RequestParam(required = false) String province,
            @ApiParam("市") @RequestParam(required = false) String city,
            @ApiParam("县") @RequestParam(required = false) String county,
            @ApiParam("生境类型ID") @RequestParam(required = false) String habitatId,
            @ApiParam("调查人") @RequestParam(required = false) String investigator,
            @ApiParam("开始时间(yyyy-MM-dd)") @RequestParam(required = false) String startTime,
            @ApiParam("结束时间(yyyy-MM-dd)") @RequestParam(required = false) String endTime,
            @ApiParam("状态 0已保存 1已撤回 2已上报 3市级退回 4市级通过 5省级退回 6省级通过 7总站退回 8总站通过")
            @RequestParam(required = false) String status,
            @ApiParam(value = "pageNo", required = true) @RequestParam() Integer pageNo,
            @ApiParam(value = "pageSize", required = true) @RequestParam() Integer pageSize) {

        return Result.ok(sourceService.listPage(getQueryMap(specId, province, city, county, habitatId, investigator,
                startTime, endTime, status), pageNo, pageSize));
    }

    @GetMapping(value = "/export", produces = "application/octet-stream")
    @ApiOperation(value = "导出", produces = "application/octet-stream")
    @RequiresPermissions("agpjyz:source:export")
    public void export(
            @ApiParam("物种Id") @RequestParam(required = false) String specId,
            @ApiParam("省") @RequestParam(required = false) String province,
            @ApiParam("市") @RequestParam(required = false) String city,
            @ApiParam("县") @RequestParam(required = false) String county,
            @ApiParam("生境类型ID") @RequestParam(required = false) String habitatId,
            @ApiParam("调查人") @RequestParam(required = false) String investigator,
            @ApiParam("开始时间(yyyy-MM-dd)") @RequestParam(required = false) String startTime,
            @ApiParam("结束时间(yyyy-MM-dd)") @RequestParam(required = false) String endTime,
            @ApiParam("状态 0已保存 1已撤回 2已上报 3市级退回 4市级通过 5省级退回 6省级通过 7总站退回 8总站通过")
            @RequestParam(required = false) String status, HttpServletResponse response) {
        sourceService.exportByTemplate(getQueryMap(specId, province, city, county, habitatId, investigator,
                startTime, endTime, status), response);
    }

    /**
     * 封装查询参数
     */
    private Map<String, Object> getQueryMap(String specId, String province, String city, String county, String habitatId,
                                            String investigator, String startTime, String endTime, String status) {
        Map map = Maps.newHashMapWithExpectedSize(9);
        map.put("specId", specId);
        map.put("province", province);
        map.put("city", city);
        map.put("county", county);
        map.put("investigator", investigator);
        map.put("startTime", startTime);
        map.put("habitatId", habitatId);
        if (StringUtils.hasText(endTime)) {
            map.put("endTime", endTime + " 23:59:59");
        }
        map.put("status", status);
        return map;
    }
}
