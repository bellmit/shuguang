package com.sofn.agpjyz.web;

import com.sofn.agpjyz.model.FacilitiesFiling;
import com.sofn.agpjyz.service.FacilitiesFilingService;
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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "基础设施备案登记接口")
@RestController
@RequestMapping("/ff")
public class FacilitiesFilingController extends BaseController {

    @Autowired
    private FacilitiesFilingService ffService;

    @ApiOperation(value = "新增")
    @PostMapping("/save")
    @RequiresPermissions("agpjyz:ff:create")
    public Result<FacilitiesFiling> save(
            @Validated @RequestBody FacilitiesFiling facilitiesFiling, BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        return Result.ok(ffService.save(facilitiesFiling));
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("/delete")
    @RequiresPermissions("agpjyz:ff:delete")
    public Result delete(
            @ApiParam(value = "主键id", required = true) @RequestParam() String id) {
        ffService.delete(id);
        return Result.ok();
    }

    @ApiOperation(value = "详情")
    @GetMapping("/get")
    @RequiresPermissions("agpjyz:ff:view")
    public Result<FacilitiesFiling> get(
            @ApiParam(value = "主键id", required = true) @RequestParam() String id) {
        return Result.ok(ffService.get(id));
    }

    @ApiOperation(value = "修改")
    @PutMapping("/update")
    @RequiresPermissions("agpjyz:ff:update")
    public Result insertSignboardProcess(
            @Validated @RequestBody FacilitiesFiling facilitiesFiling, BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        ffService.update(facilitiesFiling);
        return Result.ok();
    }

    @ApiOperation(value = "分页查询")
    @GetMapping("/listPage")
//    @RequiresPermissions("agpjyz:ff:menu")
    public Result<PageUtils<FacilitiesFiling>> listPage(
            @ApiParam("保护点ID") @RequestParam(required = false) String protectId,
            @ApiParam("基础设施名称") @RequestParam(required = false) String facilities,
            @ApiParam("开始时间(yyyy-MM-dd)") @RequestParam(required = false) String startTime,
            @ApiParam("结束时间(yyyy-MM-dd)") @RequestParam(required = false) String endTime,
            @ApiParam(value = "pageNo", required = true) @RequestParam() Integer pageNo,
            @ApiParam(value = "pageSize", required = true) @RequestParam() Integer pageSize) {

        return Result.ok(ffService.listPage(getQueryMap(protectId, facilities, startTime, endTime), pageNo, pageSize));
    }

    @ApiOperation(value = "基础设施维护情况备案登记(新增/编辑选基础设施名称下拉接口)")
    @GetMapping("/list")
    public Result<List<FacilitiesFiling>> list() {
        return Result.ok(ffService.list(Collections.EMPTY_MAP));
    }

    @GetMapping(value = "/export", produces = "application/octet-stream")
    @ApiOperation(value = "导出", produces = "application/octet-stream")
    @RequiresPermissions("agpjyz:ff:export")
    public void export(
            @ApiParam("保护点ID") @RequestParam(required = false) String protectId,
            @ApiParam("基础设施名称") @RequestParam(required = false) String facilities,
            @ApiParam("开始时间(yyyy-MM-dd)") @RequestParam(required = false) String startTime,
            @ApiParam("结束时间(yyyy-MM-dd)") @RequestParam(required = false) String endTime,
            HttpServletResponse response) {
        ffService.export(getQueryMap(protectId, facilities, startTime, endTime), response);
    }

    /**
     * 封装查询参数
     */
    private Map<String, Object> getQueryMap(String protectId, String facilities, String startTime, String endTime) {
        Map map = new HashMap<String, Object>(4);
        map.put("protectId", protectId);
        map.put("facilities", facilities);
        map.put("startTime", startTime);
        if (StringUtils.hasText(endTime)) {
            map.put("endTime", endTime + " 23:59:59");
        }
        return map;
    }
}
