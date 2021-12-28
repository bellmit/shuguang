package com.sofn.agsjdm.web;

import com.sofn.agsjdm.model.Biomonitoring;
import com.sofn.agsjdm.service.BiomonitoringService;
import com.sofn.agsjdm.vo.DropDownVo;
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
import java.util.List;
import java.util.Map;

@Slf4j
@Api(tags = "生物监测信息采集模块接口")
@RestController
@RequestMapping("/biomonitoring")
public class BiomonitoringController extends BaseController {

    @Autowired
    private BiomonitoringService service;

    @ApiOperation(value = "新增")
    @PostMapping("/save")
    @RequiresPermissions("agsjdm:biomonitoring:create")
    public Result<Biomonitoring> save(
            @Validated @RequestBody Biomonitoring biomonitoring, BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        return Result.ok(service.save(biomonitoring));
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("/delete")
    @RequiresPermissions("agsjdm:biomonitoring:delete")
    public Result delete(
            @ApiParam(value = "主键id", required = true) @RequestParam() String id) {
        service.delete(id);
        return Result.ok();
    }

    @ApiOperation(value = "详情")
    @GetMapping("/get")
    @RequiresPermissions("agsjdm:biomonitoring:view")
    public Result<Biomonitoring> get(
            @ApiParam(value = "主键id", required = true) @RequestParam() String id) {
        return Result.ok(service.get(id));
    }


    @ApiOperation(value = "获取年份")
    @GetMapping("/getYears")
    public Result<List<DropDownVo>> getYears() {
        return Result.ok(service.getYears());
    }

    @ApiOperation(value = "修改")
    @PutMapping("/update")
    @RequiresPermissions("agsjdm:biomonitoring:update")
    public Result insertSignboardProcess(
            @Validated @RequestBody Biomonitoring biomonitoring, BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        service.update(biomonitoring);
        return Result.ok();
    }

    @ApiOperation(value = "分页查询")
    @GetMapping("/listPage")
    public Result<PageUtils<Biomonitoring>> listPage(
            @ApiParam("湿地区名称Id") @RequestParam(required = false) String wetlandId,
            @ApiParam("生物分类Id") @RequestParam(required = false) String biologicalAxonomy,
            @ApiParam("中文名") @RequestParam(required = false) String chineseName,
            @ApiParam("开始时间(yyyy-MM-dd)") @RequestParam(required = false) String startTime,
            @ApiParam("结束时间(yyyy-MM-dd)") @RequestParam(required = false) String endTime,
            @ApiParam(value = "pageNo", required = true) @RequestParam() Integer pageNo,
            @ApiParam(value = "pageSize", required = true) @RequestParam() Integer pageSize) {

        return Result.ok(service.listPage(
                getQueryMap(wetlandId, biologicalAxonomy, chineseName, startTime, endTime), pageNo, pageSize));
    }

    @GetMapping(value = "/export", produces = "application/octet-stream")
    @ApiOperation(value = "导出", produces = "application/octet-stream")
    @RequiresPermissions("agsjdm:biomonitoring:export")
    public void export(
            @ApiParam("湿地区名称Id") @RequestParam(required = false) String wetlandId,
            @ApiParam("生物分类Id") @RequestParam(required = false) String biologicalAxonomy,
            @ApiParam("中文名") @RequestParam(required = false) String chineseName,
            @ApiParam("开始时间(yyyy-MM-dd)") @RequestParam(required = false) String startTime,
            @ApiParam("结束时间(yyyy-MM-dd)") @RequestParam(required = false) String endTime,
            HttpServletResponse response) {
        service.export(getQueryMap(wetlandId, biologicalAxonomy, chineseName, startTime, endTime), response);
    }

    /**
     * 封装查询参数
     */
    private Map<String, Object> getQueryMap(
            String wetlandId, String biologicalAxonomy, String chineseName, String startTime, String endTime) {
        Map map = new HashMap<String, Object>(5);
        map.put("wetlandId", wetlandId);
        map.put("biologicalAxonomy", biologicalAxonomy);
        map.put("chineseName", chineseName);
        map.put("startTime", startTime);
        if (StringUtils.hasText(endTime)) {
            map.put("endTime", endTime + " 23:59:59");
        }
        return map;
    }


}
