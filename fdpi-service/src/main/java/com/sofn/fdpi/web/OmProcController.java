package com.sofn.fdpi.web;

import com.sofn.common.model.Result;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.web.BaseController;
import com.sofn.fdpi.service.OmProcService;
import com.sofn.fdpi.util.MapUtil;
import com.sofn.fdpi.util.Trace2SourceUtil;
import com.sofn.fdpi.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * @Description 欧鳗加工企业接口
 * @Author wg
 * @Date 2021/5/20 9:35
 **/
@Api(value = "欧鳗加工企业相关接口", tags = "欧鳗加工企业相关接口")
@RestController
@RequestMapping("/omProc")
public class OmProcController extends BaseController {
    @Resource
    private OmProcService omProcService;

    @ApiOperation(value = "欧鳗加工企业新增")
    @RequiresPermissions(value = {"ouman:breed2proc:add", "ouman:supplement:add"}, logical = Logical.OR)
    @PostMapping("/add")
    public Result add(@Validated @RequestBody OmProcVo omProcVo, BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        omProcService.add(omProcVo);
        return Result.ok();
    }

    @ApiOperation(value = "欧鳗加工企业补录新增")
    @RequiresPermissions(value = {"ouman:breed2proc:add", "ouman:supplement:add"}, logical = Logical.OR)
    @PostMapping("/additional")
    public Result additional(@Validated @RequestBody OmBreedAdditionalFrom omBreedAdditionalFrom, BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        OmProcVo omProcVo = new OmProcVo();
        BeanUtils.copyProperties(omBreedAdditionalFrom, omProcVo);
        omProcService.add(omProcVo);
        return Result.ok();
    }


    @ApiOperation(value = "欧鳗加工企业比例折算和汇总分析列表数据")
    @GetMapping("/getList")
    public Result<PageUtils<OmBreedProcTableVo>> getList(
            @RequestParam(required = false) @ApiParam(value = "出让企业") String cellComp,
            @RequestParam(required = false) @ApiParam(value = "受让企业") String transferComp,
            @RequestParam(required = false) @ApiParam(value = "交易日期") Date dealDate,
            @RequestParam(value = "pageNo") @ApiParam(value = "pageNo") Integer pageNo,
            @RequestParam(value = "pageSize") @ApiParam(value = "pageSize") Integer pageSize,
            @RequestParam(value = "field", defaultValue = "updateTime") @ApiParam(value = "排序字段,默认是更新时间") String field,
            @RequestParam(defaultValue = "descend") @ApiParam(value = "排序方式默认倒序descend、ascend") String order,
            @RequestParam(defaultValue = "1") @ApiParam(value = "查询类型,1是本系统查询,2是属于数据补录查询", required = true) String searchType) {
        String[] keys = {"cellComp", "transferComp", "dealDate", "orderBy", "sortord", "searchType"};
        Object[] vals = {cellComp, transferComp, dealDate, field.replaceAll("[A-Z]", "_$0").toLowerCase(), order.equals("descend") ? "desc" : "asc", searchType};
        PageUtils<OmBreedProcTableVo> list = omProcService.getList(MapUtil.getParams(keys, vals), pageNo, pageSize);
        return Result.ok(list);
    }

    @ApiOperation(value = "欧鳗加工企业数据导出", produces = "application/octet-stream")
    @GetMapping(value = "/procExport", produces = "application/octet-stream")
    public void export(
            @RequestParam(required = false) @ApiParam(value = "出让企业") String cellComp,
            @RequestParam(required = false) @ApiParam(value = "受让企业") String transferComp,
            @RequestParam(required = false) @ApiParam(value = "交易日期") Date dealDate,
            @RequestParam(value = "field", defaultValue = "updateTime") @ApiParam(value = "排序字段,默认是更新时间") String field,
            @RequestParam(defaultValue = "descend") @ApiParam(value = "排序方式默认倒序descend、ascend") String order,
            @RequestParam(defaultValue = "1") @ApiParam(value = "导出类型,1是折算比例,2是属于汇总", required = true) String exportType,
            HttpServletResponse response) {
        String[] keys = {"cellComp", "transferComp", "dealDate", "orderBy", "sortord", "exportType"};
        Object[] vals = {cellComp, transferComp, dealDate, field.replaceAll("[A-Z]", "_$0").toLowerCase(), order.equals("descend") ? "desc" : "asc", exportType};
        omProcService.exportProcList(MapUtil.getParams(keys, vals), response);
    }

    @ApiOperation(value = "欧鳗加工企业数据修改")
    @RequiresPermissions(value = {"ouman:breed2proc:update", "ouman:supplement:update"}, logical = Logical.OR)
    @PostMapping("/update")
    public Result update(@Validated @RequestBody OmProcVo omProcVo, BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        omProcService.updateOmProc(omProcVo);
        return Result.ok();
    }

    @ApiOperation(value = "欧鳗加工企业补录数据修改")
    @RequiresPermissions(value = {"ouman:breed2proc:update", "ouman:supplement:update"}, logical = Logical.OR)
    @PostMapping("/additionalUpdate")
    public Result additionalUpdate(@Validated @RequestBody OmBreedAdditionalFrom omBreedAdditionalFrom, BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        OmProcVo omProcVo = new OmProcVo();
        BeanUtils.copyProperties(omBreedAdditionalFrom, omProcVo);
        omProcService.updateOmProc(omProcVo);
        return Result.ok();
    }


    @ApiOperation(value = "欧鳗加工企业数据删除")
    @RequiresPermissions(value = {"ouman:breed2proc:delete", "ouman:supplement:delete"}, logical = Logical.OR)
    @DeleteMapping("/delById")
    public Result del(@ApiParam(value = "主键id", required = true) @RequestParam(value = "id") String id) {
        omProcService.delByid(id);
        return Result.ok();
    }

    @ApiOperation(value = "欧鳗加工企业根据出让企业的名称得到养殖企业的允许进出口说明书号列表")
    @GetMapping("/getBreedCredList")
    public Result<List<CredKV>> getBreedCredList(@ApiParam(value = "出让企业（养殖）", required = true) @RequestParam(value = "breedComp") String breedComp) {
        List<CredKV> breedCredList = omProcService.getBreedCredList(breedComp);
        return Result.ok(breedCredList);
    }

    @ApiOperation(value = "欧鳗加工企业溯源格式接口")
    @RequiresPermissions("ouman:procSource:source")
    @GetMapping("/getProcTraceSourceById")
    public Result<SourceInfoVo> getImportTraceSourceById(@ApiParam(value = "主键id", required = true) @RequestParam(value = "id") String id) {
        ImportTraceToSourceVo breedTraceSourceById = omProcService.getBreedTraceSourceById(id);
        SourceInfoVo sourceInfoVo = Trace2SourceUtil.trace2Source(breedTraceSourceById);
//        Node node = Trace2SourceUtil.source2Node(sourceInfoVo, "2");
        return Result.ok(sourceInfoVo);
    }

    @ApiOperation(value = "欧鳗加工企业详情")
    @RequiresPermissions(value = {"ouman:breed2proc:view", "ouman:supplement:view", "ouman:procSource:view"}, logical = Logical.OR)
    @GetMapping("/searchById")
    public Result<OmBreedReVo> searchByid(@ApiParam(value = "主键id", required = true) @RequestParam(value = "id") String id) {
        OmBreedReVo byId = omProcService.getById(id);
        return Result.ok(byId);
    }

//    @ApiOperation(value = "欧鳗加工企业得到当前企业的剩余鳗鱼总吨数")
//    @GetMapping("/getResidueSum")
//    public Result<Double> getResidueSum() {
//        Double residue = omProcResidueService.getResidue();
//        return Result.ok(residue);
//    }


    @ApiOperation(value = "根据养殖企业名称和允许进出口说明书的得到该数据进口时的详情")
    @GetMapping("/getImportInfoByBnameAndCred")
    public Result<OmImportInfo> getImportInfoByBnameAndCred(@ApiParam(value = "养殖企业名称", required = true) @RequestParam(value = "breedCompName") String breedCompName,
                                                            @ApiParam(value = "允许进出口说明书", required = true) @RequestParam(value = "credential") String credential) {
        OmImportInfo importInfoByCnameAndCred = omProcService.getImportInfoByCnameAndCred(breedCompName, credential);
        return Result.ok(importInfoByCnameAndCred);
    }

    @ApiOperation(value = "加工企业进口时得到有效的养殖场列表")
    @GetMapping("/getBreedList")
    public Result<List<SelectVo>> getBreedList() {
        List<SelectVo> breedList = omProcService.getBreedList();
        return Result.ok(breedList);
    }

    @ApiOperation(value = "加工企业柱状图数据格式")
    @GetMapping("/procHistogram")
    public Result<List<OmHistogram>> procHistogram(@RequestParam(required = false) @ApiParam(value = "欧鳗加工企业") String omProcComp,
                                                   @RequestParam(required = false) @ApiParam(value = "允许进出口证明书号") String credential,
                                                   @RequestParam(required = false) @ApiParam(value = "起始时间") Date startDate,
                                                   @RequestParam(required = false) @ApiParam(value = "结束时间") Date endDate) {
        List<OmHistogram> omHistogram = omProcService.getOmHistogram(omProcComp, credential, startDate, endDate);
        return Result.ok(omHistogram);
    }
}
