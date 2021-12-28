package com.sofn.agpjpm.web;

import com.sofn.agpjpm.service.MonitorService;
import com.sofn.agpjpm.sysapi.JzbApi;
import com.sofn.agpjpm.vo.*;
import com.sofn.common.model.Result;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "监测表模块接口")
@RestController
@RequestMapping("/monitor")
public class MonitorController extends BaseController {

    @Resource
    private MonitorService monitorService;

    @Resource
    private JzbApi jzbApi;

    @ApiOperation(value = "新增")
    @PostMapping("/save")
    @RequiresPermissions(value = {"agpjpm:monitor:create", "agpjyz:monitor:create"}, logical = Logical.OR)
    public Result<MonitorVo> save(
            @Validated @RequestBody MonitorForm monitorForm, BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        return Result.ok(monitorService.save(monitorForm));
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("/delete")
    @RequiresPermissions(value = {"agpjpm:monitor:delete", "agpjyz:monitor:delete"}, logical = Logical.OR)
    public Result delete(
            @ApiParam(value = "主键id", required = true) @RequestParam() String id) {
        monitorService.delete(id);
        return Result.ok();
    }

    @ApiOperation(value = "详情")
    @GetMapping("/get")
    @RequiresPermissions(value = {"agpjpm:monitor:view", "agpjyz:monitor:view"}, logical = Logical.OR)
    public Result<MonitorVo> get(
            @ApiParam(value = "主键id", required = true) @RequestParam() String id) {
        return Result.ok(monitorService.get(id));
    }

    @ApiOperation(value = "修改")
    @PutMapping("/update")
    @RequiresPermissions(value = {"agpjpm:monitor:update", "agpjyz:monitor:update"}, logical = Logical.OR)
    public Result update(
            @Validated @RequestBody MonitorForm monitorForm, BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        monitorService.update(monitorForm);
        return Result.ok();
    }

    @ApiOperation(value = "分页查询")
    @GetMapping("/listPage")
    public Result<PageUtils<MonitorVo>> listPage(
            @ApiParam("保护点Id") @RequestParam(required = false) String protectId,
            @ApiParam("省") @RequestParam(required = false) String province,
            @ApiParam("市") @RequestParam(required = false) String city,
            @ApiParam("县") @RequestParam(required = false) String county,
            @ApiParam("调查人") @RequestParam(required = false) String surveyor,
            @ApiParam("调查单位") @RequestParam(required = false) String surveyDept,
            @ApiParam("联系电话") @RequestParam(required = false) String tel,
            @ApiParam("开始时间(yyyy-MM-dd)") @RequestParam(required = false) String startTime,
            @ApiParam("结束时间(yyyy-MM-dd)") @RequestParam(required = false) String endTime,
            @ApiParam(value = "pageNo", required = true) @RequestParam() Integer pageNo,
            @ApiParam(value = "pageSize", required = true) @RequestParam() Integer pageSize) {
        return Result.ok(monitorService.listPage(getQueryMap(protectId, province, city, county, tel, surveyor,
                surveyDept, startTime, endTime), pageNo, pageSize));
    }


    @GetMapping(value = "/export", produces = "application/octet-stream")
    @ApiOperation(value = "导出", produces = "application/octet-stream")
    @RequiresPermissions(value = {"agpjpm:monitor:export", "agpjyz:monitor:export"}, logical = Logical.OR)
    public void export(
            @ApiParam("保护点Id") @RequestParam(required = false) String protectId,
            @ApiParam("省") @RequestParam(required = false) String province,
            @ApiParam("市") @RequestParam(required = false) String city,
            @ApiParam("县") @RequestParam(required = false) String county,
            @ApiParam("调查人") @RequestParam(required = false) String surveyor,
            @ApiParam("调查单位") @RequestParam(required = false) String surveyDept,
            @ApiParam("联系电话") @RequestParam(required = false) String tel,
            @ApiParam("开始时间(yyyy-MM-dd)") @RequestParam(required = false) String startTime,
            @ApiParam("结束时间(yyyy-MM-dd)") @RequestParam(required = false) String endTime,
            HttpServletResponse response) {
        monitorService.exportByTemplate(getQueryMap(protectId, province, city, county, tel, surveyor, surveyDept,
                startTime, endTime), response);
    }

    /**
     * 封装查询参数
     */
    private Map<String, Object> getQueryMap(String protectId, String province, String city, String county, String tel,
                                            String surveyor, String surveyDept, String startTime, String endTime) {
        Map map = new HashMap<String, Object>(9);
        map.put("protectId", protectId);
        map.put("province", province);
        map.put("city", city);
        map.put("county", county);
        map.put("surveyor", surveyor);
        map.put("surveyDept", surveyDept);
        map.put("tel", tel);
        map.put("startTime", startTime);
        if (StringUtils.hasText(endTime)) {
            map.put("endTime", endTime + " 23:59:59");
        }
        return map;
    }

    @ApiOperation(value = "《下拉列表》获取保护点名称")
    @GetMapping("/listForProtectPoints")
    public Result<List<DropDownVo>> listForProtectPoints() {
        return jzbApi.listForProtectPoints(null);
    }

    @ApiOperation(value = "《下拉列表》获取农业野生植物物种")
    @GetMapping("/listForSpecies")
    public Result<List<DropDownWithLatinVo>> listForSpecies() {
        return jzbApi.listForSpecies();
    }

    @ApiOperation(value = "根据年度和地区区划获取一条年度气候环境信息")
    @GetMapping("/getYearClimateEnvironment")
    public Result<YearClimateEnvironmentVo> getYearClimateEnvironment(@RequestParam("year") @ApiParam(name = "year", value = "年度", required = true, example = "2020") String year
            , @RequestParam("provinceCode") @ApiParam(name = "provinceCode", value = "省编号", required = true, example = "2020") String provinceCode
            , @RequestParam("cityCode") @ApiParam(name = "cityCode", value = "市编号", required = true, example = "2020") String cityCode
            , @RequestParam("areaCode") @ApiParam(name = "areaCode", value = "区编号", required = true, example = "2020") String areaCode) {
        return jzbApi.getYearClimateEnvironment(year, provinceCode, cityCode, areaCode);
    }
}
