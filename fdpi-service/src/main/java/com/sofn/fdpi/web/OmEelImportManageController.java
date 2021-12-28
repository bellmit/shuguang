package com.sofn.fdpi.web;

import com.sofn.common.model.Result;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.web.BaseController;
import com.sofn.fdpi.service.OmEelImportService;
import com.sofn.fdpi.util.MapUtil;
import com.sofn.fdpi.util.Trace2SourceUtil;
import com.sofn.fdpi.vo.*;
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
import java.util.Date;
import java.util.List;

/**
 * @Description
 * @Author wg
 * @Date 2021/5/12 9:54
 **/
@Api(value = "欧鳗进口相关接口", tags = "欧鳗进口相关接口")
@RestController
@RequestMapping("/omImport")
public class OmEelImportManageController extends BaseController {
    @Resource
    private OmEelImportService omEelImportService;

    @ApiOperation(value = "欧鳗进口新增")
    @RequiresPermissions("ouman:import:add")
    @PostMapping("/add")
    public Result add(@Validated @RequestBody OmEelImportVo omEelImportVo, BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        omEelImportService.add(omEelImportVo);
        return Result.ok();
    }

    @ApiOperation(value = "欧鳗进口修改")
    @RequiresPermissions("ouman:import:update")
    @PostMapping("/update")
    public Result update(@Validated @RequestBody OmEelImportVo omEelImportVo, BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        omEelImportService.updateImport(omEelImportVo);
        return Result.ok();
    }

    @ApiOperation(value = "欧鳗查询详情")
    @RequiresPermissions(value = {"ouman:import:view", "ouman:importAnalyse:view", "ouman:importSource:view"}, logical = Logical.OR)
    @GetMapping("/searchById")
    public Result<OmImportVo> searchById(@ApiParam(value = "主键id", required = true) @RequestParam(value = "id") String id) {
        if (StringUtils.isEmpty(id)) {
            return Result.error("ID不能为空");
        }
        OmImportVo omImportVo = omEelImportService.searchByid(id);
        return Result.ok(omImportVo);
    }

    @ApiOperation(value = "欧鳗进口列表") //企业、省级、部级、欧鳗进口管理和进口比例折算都是这一个接口
    @GetMapping("/eelList")
    public Result<PageUtils<OmImportFromVo>> importEelList(@RequestParam(required = false) @ApiParam(value = "允许进出口证明书号") String credential,
                                                           @RequestParam(required = false) @ApiParam(value = "进口国") String importCountry,
                                                           @RequestParam(required = false) @ApiParam(value = "企业名称") String importMan,
                                                           @RequestParam(required = false, value = "importDate") @ApiParam(value = "进口日期") Date importDate,
                                                           @RequestParam(value = "pageNo") @ApiParam(value = "偏移量") Integer pageNo,
                                                           @RequestParam(value = "pageSize") @ApiParam(value = "当前页条数") Integer pageSize,
                                                           @RequestParam(value = "field", defaultValue = "updateTime") @ApiParam(value = "排序字段,默认是更新时间") String field,
                                                           @RequestParam(defaultValue = "descend") @ApiParam(value = "排序方式默认倒序descend、ascend") String order
    ) {
        String[] keys = {"credential", "importCountry", "importMan", "importDate", "field", "order"};
        Object[] vals = {credential, importCountry, importMan, importDate, field.replaceAll("[A-Z]", "_$0").toLowerCase(), order.equals("descend") ? "desc" : "asc"};
        PageUtils<OmImportFromVo> listImport = omEelImportService.getListImport(MapUtil.getParams(keys, vals), pageNo, pageSize);
        return Result.ok(listImport);
    }

    @ApiOperation(value = "欧鳗进口删除")
    @RequiresPermissions("ouman:import:delete")
    @DeleteMapping("/del")
    public Result delImportEel(@ApiParam(value = "主键id", required = true) @RequestParam(value = "id") String id) {
        Integer integer = omEelImportService.delEelImport(id);
        if (integer == 1) {
            return Result.ok();
        } else {
            return Result.error("删除失败");
        }
    }

    @ApiOperation(value = "进口比例折算导出", produces = "application/octet-stream")
    @RequiresPermissions(value = {"ouman:importRatio:export", "ouman:importAnalyse:export"}, logical = Logical.OR)
    @GetMapping(value = "/export", produces = "application/octet-stream")
    public Result export(@RequestParam(required = false) @ApiParam(value = "允许进出口证明书号") String credential,
                         @RequestParam(required = false) @ApiParam(value = "出口国") String importCountry,
                         @RequestParam(required = false) @ApiParam(value = "企业名称") String importMan,
                         @RequestParam(value = "field", defaultValue = "updateTime") @ApiParam(value = "排序字段,默认是更新时间") String field,
                         @RequestParam(defaultValue = "descend") @ApiParam(value = "排序方式默认倒序descend、ascend") String order,
                         @RequestParam(defaultValue = "1") @ApiParam(value = "导出类型，1是比例折算，2是汇总分析") String exportType,
                         HttpServletResponse response) {
        String[] keys = {"credential", "importCountry", "importMan", "field", "order", "exportType"};
        Object[] vals = {credential, importCountry, importMan, field.replaceAll("[A-Z]", "_$0").toLowerCase(), order.equals("descend") ? "desc" : "asc", exportType};
        omEelImportService.export(MapUtil.getParams(keys, vals), response);
        return Result.ok();
    }

    @ApiOperation(value = "进口企业柱状图数据格式")
    @RequiresPermissions(value = {"ouman:importRatio:export", "ouman:importAnalyse:analyse"}, logical = Logical.OR)
    @GetMapping("/importHistogram")
    public Result<List<OmHistogram>> importHistogram(@RequestParam(required = false) @ApiParam(value = "欧鳗进口企业") String importMan,
                                                     @RequestParam(required = false) @ApiParam(value = "允许进出口证明书号") String credential,
                                                     @RequestParam(required = false) @ApiParam(value = "起始时间") Date startDate,
                                                     @RequestParam(required = false) @ApiParam(value = "结束时间") Date endDate) {
        List<OmHistogram> omHistogram = omEelImportService.getOmHistogram(importMan, credential, startDate, endDate);
        return Result.ok(omHistogram);
    }

    @ApiOperation(value = "根据主键得到进口企业的溯源图")
    @RequiresPermissions("ouman:importSource:source")
    @GetMapping("/getImportTraceSourceById")
    public Result<SourceInfoVo> getImportTraceSourceById(@ApiParam(value = "主键id", required = true) @RequestParam(value = "id") String id) {
        ImportTraceToSourceVo traceSourceById = omEelImportService.getTraceSourceById(id);
        SourceInfoVo sourceInfoVo = Trace2SourceUtil.trace2Source(traceSourceById);
//        Node node = Trace2SourceUtil.source2Node(sourceInfoVo, "0");
        return Result.ok(sourceInfoVo);
    }


}
