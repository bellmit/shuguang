package com.sofn.fdpi.web;

import com.sofn.common.exception.SofnException;
import com.sofn.common.model.Result;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.web.BaseController;
import com.sofn.fdpi.model.OmBreed;
import com.sofn.fdpi.service.OmBreedService;
import com.sofn.fdpi.service.OmEelImportService;
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
 * @Description 欧鳗养殖企业相关数据的接口
 * @Author wg
 * @Date 2021/5/14 16:24
 **/
@Api(value = "欧鳗养殖企业相关接口", tags = "欧鳗养殖企业相关接口")
@RestController
@RequestMapping("/omBreed")
public class OmBreedController extends BaseController {
    @Resource
    private OmBreedService omBreedService;
    @Resource
    private OmEelImportService omEelImportService;

    @ApiOperation(value = "欧鳗养殖企业新增")
    @RequiresPermissions(value = {"ouman:import2breed:add", "ouman:supplement:add"}, logical = Logical.OR)
    @PostMapping("/add")
    public Result add(@Validated @RequestBody OmBreedVo omBreedVo, BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        omBreedService.add(omBreedVo);
        return Result.ok();
    }

    @ApiOperation(value = "欧鳗养殖企业补录新增")
    @RequiresPermissions(value = {"ouman:import2breed:add", "ouman:supplement:add"}, logical = Logical.OR)
    @PostMapping("/additional")
    public Result additional(@Validated @RequestBody OmBreedAdditionalFrom omBreedAdditionalFrom, BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        OmBreedVo omBreedVo = new OmBreedVo();
        BeanUtils.copyProperties(omBreedAdditionalFrom, omBreedVo);
        omBreedService.add(omBreedVo);
        return Result.ok();
    }


    @ApiOperation(value = "根据出让企业（进口）的名字得到当前企业（养殖）允许进出口说明书号的列表")
    @GetMapping("/getCredentialList")
    public Result<List<CredKV>> getCredentialList(@RequestParam(required = false) @ApiParam(value = "出让企业(进口)") String cellComp) {
        List<CredKV> credentialList = omBreedService.getCredentialList(cellComp);
        return Result.ok(credentialList);
    }


    @ApiOperation(value = "根据进口企业的名字和允许进出口说明书得到一些进口时的详情数据")
    @GetMapping("/getImportInfoByCC")
    public Result<OmImportInfo> getSomeInfoByCC(@RequestParam(required = false) @ApiParam(value = "出让企业(进口)") String cellComp,
                                                @RequestParam(required = false) @ApiParam(value = "允许进出口说明书号") String credential) {
        OmImportInfo omImportInfo = omEelImportService.getInfoByNameAndCred(cellComp, credential);
        return Result.ok(omImportInfo);
    }

    @ApiOperation(value = "欧鳗养殖企业比例折算和汇总分析列表数据")
    @GetMapping("/getList")
    public Result<PageUtils<OmBreedProcTableVo>> getList(
            @RequestParam(required = false) @ApiParam(value = "出让企业") String cellComp,
            @RequestParam(required = false) @ApiParam(value = "受让企业") String transferComp,
            @RequestParam(required = false) @ApiParam(value = "允许进出口说明书号") String credential,
            @RequestParam(required = false) @ApiParam(value = "交易日期") Date dealDate,
            @RequestParam(value = "pageNo") @ApiParam(value = "pageNo") Integer pageNo,
            @RequestParam(value = "pageSize") @ApiParam(value = "pageSize") Integer pageSize,
            @RequestParam(value = "field", defaultValue = "updateTime") @ApiParam(value = "排序字段,默认是更新时间") String field,
            @RequestParam(defaultValue = "descend") @ApiParam(value = "排序方式默认倒序descend、ascend") String order,
            @RequestParam(required = false) @ApiParam(value = "查询类型,1是本系统查询,2是属于数据补录查询") String searchType) {
        String[] keys = {"cellComp", "transferComp", "credential", "dealDate", "orderBy", "sortord", "searchType"};
        Object[] vals = {cellComp, transferComp, credential, dealDate, field.replaceAll("[A-Z]", "_$0").toLowerCase(), order.equals("descend") ? "desc" : "asc", searchType};
        PageUtils<OmBreedProcTableVo> list = omBreedService.getList(MapUtil.getParams(keys, vals), pageNo, pageSize);
        return Result.ok(list);
    }

    @ApiOperation(value = "欧鳗养殖企业补录数据修改")
    @RequiresPermissions(value = {"ouman:import2breed:update", "ouman:supplement:update"}, logical = Logical.OR)
    @PostMapping("/additionalUpdate")
    public Result additionalUpdate(@Validated @RequestBody OmBreedAdditionalFrom omBreedAdditionalFrom, BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        OmBreedVo omBreedVo = new OmBreedVo();
        BeanUtils.copyProperties(omBreedAdditionalFrom, omBreedVo);
        omBreedService.updateOmBreed(omBreedVo);
        return Result.ok();
    }

    @ApiOperation(value = "欧鳗养殖企业数据修改")
    @RequiresPermissions(value = {"ouman:import2breed:update", "ouman:supplement:update"}, logical = Logical.OR)
    @PostMapping("/update")
    public Result update(@Validated @RequestBody OmBreedVo omBreedVo, BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        omBreedService.updateOmBreed(omBreedVo);
        return Result.ok();
    }


    @ApiOperation(value = "欧鳗养殖企业数据删除")
    @RequiresPermissions(value = {"ouman:import2breed:delete", "ouman:supplement:delete"}, logical = Logical.OR)
    @DeleteMapping("/delByid")
    public Result del(@ApiParam(value = "主键id", required = true) @RequestParam(value = "id") String id) {
        omBreedService.delByid(id);
        return Result.ok();
    }

    @ApiOperation(value = "欧鳗养殖企业查询一条数据")
    @RequiresPermissions(value = {"ouman:import2breed:view", "ouman:supplement:view", "ouman:breedSource:view"}, logical = Logical.OR)
    @GetMapping("/searchByid")
    public Result<OmBreedReVo> searchByid(@ApiParam(value = "主键id", required = true) @RequestParam(value = "id") String id) {
        OmBreedReVo omBreedReVo = omBreedService.searchByid(id);
        return Result.ok(omBreedReVo);
    }

    @ApiOperation(value = "欧鳗养殖企业数据导出", produces = "application/octet-stream")
    @GetMapping(value = "/export", produces = "application/octet-stream")
    public void export(@RequestParam(required = false) @ApiParam(value = "出让企业") String cellComp,
                       @RequestParam(required = false) @ApiParam(value = "受让企业") String transferComp,
                       @RequestParam(required = false) @ApiParam(value = "交易日期") Date dealDate,
                       @RequestParam(value = "field", defaultValue = "updateTime") @ApiParam(value = "排序字段,默认是更新时间") String field,
                       @RequestParam(defaultValue = "descend") @ApiParam(value = "排序方式默认倒序descend、ascend") String order,
                       @RequestParam(required = true) @ApiParam(value = "导出类型,1为比例折算，2为汇总分析", required = true) String exportType,
                       @RequestParam(required = false, defaultValue = "1") @ApiParam(value = "查询类型,1为本系统用户，2为数据补录", required = true) String searchType,
                       HttpServletResponse response) {
        String[] keys = {"cellComp", "transferComp", "dealDate", "orderBy", "sortord", "exportType", "searchType"};
        Object[] vals = {cellComp, transferComp, dealDate, field.replaceAll("[A-Z]", "_$0").toLowerCase(), order.equals("descend") ? "desc" : "asc", exportType, searchType};
        omBreedService.export(MapUtil.getParams(keys, vals), response);
    }

    @ApiOperation(value = "养殖企业柱状图数据格式")
    @RequiresPermissions("ouman:breedSource:source")
    @GetMapping("/breedHistogram")
    public Result<List<OmHistogram>> breedHistogram(@RequestParam(required = false) @ApiParam(value = "欧鳗养殖企业") String companyId,
                                                    @RequestParam(required = false) @ApiParam(value = "允许进出口证明书号") String credential,
                                                    @RequestParam(required = false) @ApiParam(value = "起始时间") Date startDate,
                                                    @RequestParam(required = false) @ApiParam(value = "结束时间") Date endDate) {
        List<OmHistogram> breedHistogram = omBreedService.getBreedHistogram(companyId, credential, startDate, endDate);
        return Result.ok(breedHistogram);
    }

    @ApiOperation(value = "养殖企业进口时得到有效的进口企业列表")
    @GetMapping("/getImportList")
    public Result<List<SelectVo>> getBreedList() {
        List<SelectVo> breedList = omEelImportService.getImportList();
        return Result.ok(breedList);
    }

    @ApiOperation(value = "养殖企业溯源图")
    @GetMapping("/getBreedTraceSourceById")
    public Result<SourceInfoVo> getBreedTraceSourceById(@ApiParam(value = "主键id", required = true) @RequestParam(value = "id") String id) {
        ImportTraceToSourceVo breedTraceSourceById = omBreedService.getBreedTraceSourceById(id);
        //将ImportTraceToSourceVo转成SourceInfoVo格式
        SourceInfoVo sourceInfoVo = Trace2SourceUtil.trace2Source(breedTraceSourceById);
//        Node node = Trace2SourceUtil.source2Node(sourceInfoVo, "1");
        return Result.ok(sourceInfoVo);
    }
}
