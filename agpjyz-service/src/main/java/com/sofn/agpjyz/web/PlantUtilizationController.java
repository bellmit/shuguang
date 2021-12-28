package com.sofn.agpjyz.web;


import com.google.common.collect.Maps;
import com.sofn.agpjyz.service.PlantUtilizationService;
import com.sofn.agpjyz.vo.PlantUtilizationForm;
import com.sofn.agpjyz.vo.PlantUtilizationVo;
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
import java.util.Map;

@Api(tags = "植物利用模块接口")
@RestController
@RequestMapping("/pu")
public class PlantUtilizationController extends BaseController {


    @Autowired
    private PlantUtilizationService puService;

    @ApiOperation(value = "新增")
    @PostMapping("/save")
    @RequiresPermissions("agpjyz:pu:create")
    public Result<PlantUtilizationVo> save(
            @Validated @RequestBody PlantUtilizationForm plantUtilizationForm, BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        return Result.ok(puService.save(plantUtilizationForm));
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("/delete")
    @RequiresPermissions("agpjyz:pu:delete")
    public Result delete(
            @ApiParam(value = "主键id", required = true) @RequestParam() String id) {
        puService.delete(id);
        return Result.ok();
    }

    @ApiOperation(value = "详情")
    @GetMapping("/get")
    @RequiresPermissions("agpjyz:pu:view")
    public Result<PlantUtilizationVo> get(
            @ApiParam(value = "主键id", required = true) @RequestParam() String id) {
        return Result.ok(puService.get(id));
    }

    @ApiOperation(value = "修改")
    @PutMapping("/update")
    @RequiresPermissions("agpjyz:pu:update")
    public Result update(
            @Validated @RequestBody PlantUtilizationForm plantUtilizationForm, BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        puService.update(plantUtilizationForm);
        return Result.ok();
    }

    @ApiOperation(value = "分页查询")
    @GetMapping("/listPage")
//    @RequiresPermissions("agpjyz:pu:menu")
    public Result<PageUtils<PlantUtilizationVo>> listPage(
            @ApiParam("物种ID") @RequestParam(required = false) String specId,
            @ApiParam("产业利用ID") @RequestParam(required = false) String industrialId,
            @ApiParam("上报人") @RequestParam(required = false) String reportPerson,
            @ApiParam("省") @RequestParam(required = false) String province,
            @ApiParam("市") @RequestParam(required = false) String city,
            @ApiParam("县") @RequestParam(required = false) String county,
            @ApiParam("用途ID") @RequestParam(required = false) String purpose,
            @ApiParam("利用单位名称") @RequestParam(required = false) String utilizationUnit,
            @ApiParam("开始时间(yyyy-MM-dd)") @RequestParam(required = false) String startTime,
            @ApiParam("结束时间(yyyy-MM-dd)") @RequestParam(required = false) String endTime,
            @ApiParam(value = "pageNo", required = true) @RequestParam() Integer pageNo,
            @ApiParam(value = "pageSize", required = true) @RequestParam() Integer pageSize) {

        return Result.ok(puService.listPage(getQueryMap(province, city, county, industrialId, specId, utilizationUnit,
                purpose, reportPerson, startTime, endTime), pageNo, pageSize));
    }

    @GetMapping(value = "/export", produces = "application/octet-stream")
    @ApiOperation(value = "导出", produces = "application/octet-stream")
    @RequiresPermissions("agpjyz:pu:export")
    public void export(
            @ApiParam("物种ID") @RequestParam(required = false) String specId,
            @ApiParam("产业利用ID") @RequestParam(required = false) String industrialId,
            @ApiParam("上报人") @RequestParam(required = false) String reportPerson,
            @ApiParam("省") @RequestParam(required = false) String province,
            @ApiParam("市") @RequestParam(required = false) String city,
            @ApiParam("县") @RequestParam(required = false) String county,
            @ApiParam("用途ID") @RequestParam(required = false) String purpose,
            @ApiParam("利用单位名称") @RequestParam(required = false) String utilizationUnit,
            @ApiParam("开始时间(yyyy-MM-dd)") @RequestParam(required = false) String startTime,
            @ApiParam("结束时间(yyyy-MM-dd)") @RequestParam(required = false) String endTime,
            HttpServletResponse response) {
        puService.export(getQueryMap(province, city, county, industrialId, specId, utilizationUnit, purpose,
                reportPerson, startTime, endTime), response);
    }

    /**
     * 封装查询参数
     */
    private Map<String, Object> getQueryMap(String province, String city, String county, String industrialId,
                                            String specId, String utilizationUnit, String purpose, String reportPerson,
                                            String startTime, String endTime) {
        Map map = Maps.newHashMapWithExpectedSize(10);
        map.put("specId", specId);
        map.put("industrialId", industrialId);
        map.put("reportPerson", reportPerson);
        map.put("startTime", startTime);
        map.put("utilizationUnit", utilizationUnit);
        map.put("purpose", purpose);
        if (StringUtils.hasText(endTime)) {
            map.put("endTime", endTime + " 23:59:59");
        }
        map.put("province", province);
        map.put("city", city);
        map.put("county", county);
        return map;
    }
}
