package com.sofn.fdpi.web;

import com.sofn.common.model.Result;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.web.BaseController;
import com.sofn.fdpi.service.OmExportService;
import com.sofn.fdpi.service.OmProcService;
import com.sofn.fdpi.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
 * @Date 2021/6/4 17:21
 **/
@Api(value = "欧鳗加工企业再出口相关接口", tags = "欧鳗加工企业再出口相关接口")
@RestController
@RequestMapping("/omExport")
public class OmReExportController extends BaseController {
    @Resource
    private OmExportService omExportService;
    @Resource
    private OmProcService omProcService;

    @ApiOperation(value = "再出口时得到有效的养殖企业下拉名称")
    @GetMapping("/getBreedSelectVo")
    public Result<SelectVo> getBreedSelectVo() {
        List<SelectVo> breedSelectVo = omExportService.getBreedSelectVo();
        return Result.ok(breedSelectVo);
    }

    @ApiOperation(value = "再出口时根据养殖企业名称得到有效的允许进出口说明书号")
    @GetMapping("/getCredList")
    public Result<SelectVo> getCredList(@RequestParam(value = "breedComp", required = true) @ApiParam(value = "养殖企业的名称", required = true) String breedComp) {
        List<SelectVo> selectVos = omExportService.getCredList(breedComp);
        return Result.ok(selectVos);
    }

    @ApiOperation(value = "再出口时根据养殖企业名称和允许进出口说明书得到欧鳗规格和企业拥有成鳗量")
    @GetMapping("/getReExportSomeInfoVoByCnameAndCred")
    public Result<ReExportSomeInfoVo> getReExportSomeInfoVoByCnameAndCred(@ApiParam(value = "养殖企业名称(主键)", required = true) @RequestParam(value = "breedCompName") String breedCompName,
                                                                          @ApiParam(value = "允许进出口说明书", required = true) @RequestParam(value = "credential") String credential) {
        ReExportSomeInfoVo reExportSomeInfoVo = omProcService.getByCC(breedCompName, credential);
        return Result.ok(reExportSomeInfoVo);
    }

    @ApiOperation(value = "添加一条再出口数据")
    @RequiresPermissions("ouman:export:add")
    @PostMapping("/addReExport")
    public Result addReExport(@Validated @RequestBody OmExportFrom omExportFrom, BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        omExportService.addReExport(omExportFrom);
        return Result.ok();
    }

    @ApiOperation(value = "获得再出口的详情")
    @RequiresPermissions(value = {"ouman:export:view", "ouman:exportAnalyse:view"}, logical = Logical.OR)
    @GetMapping("/searchById")
    public Result<OmExportVo> searchById(@ApiParam(value = "主键id", required = true) @RequestParam(value = "id") String id) {
        OmExportVo omExportVo = omExportService.searchById(id);
        return Result.ok(omExportVo);
    }


    @ApiOperation(value = "再出口数据列表")
    @GetMapping("/getReExportList")
    public Result<PageUtils<OmRePortListTableVo>> delReExport(@ApiParam(value = "来源加工厂", required = false) @RequestParam(value = "sourceProc", required = false) String sourceProc,
                                                              @ApiParam(value = "允许进出口说明书号", required = false) @RequestParam(value = "credential", required = false) String credential,
                                                              @ApiParam(value = "交易日期", required = false) @RequestParam(value = "dealDate", required = false) Date dealDate,
                                                              @RequestParam(value = "pageNo", required = true) @ApiParam(value = "偏移量") Integer pageNo,
                                                              @RequestParam(value = "pageSize", required = true) @ApiParam(value = "每页条数") Integer pageSize,
                                                              @RequestParam(value = "field", defaultValue = "updateTime", required = false) @ApiParam(value = "排序字段,默认是更新时间") String field,
                                                              @RequestParam(defaultValue = "descend", required = false) @ApiParam(value = "排序方式默认倒序descend、ascend") String order) {
        PageUtils<OmRePortListTableVo> reExportList = omExportService.getReExportList(sourceProc, credential, dealDate, pageNo, pageSize, field.replaceAll("[A-Z]", "_$0").toLowerCase(), order.equals("descend") ? "desc" : "asc");
        return Result.ok(reExportList);
    }

    @ApiOperation(value = "再出口导出")
    @RequiresPermissions(value = {"ouman:exportRatio:export", "ouman:exportAnalyse:export"}, logical = Logical.OR)
    @GetMapping("/reportExport")
    public void reportExport(@ApiParam(value = "来源加工厂", required = false) @RequestParam(value = "sourceProc", required = false) String sourceProc,
                             @ApiParam(value = "允许进出口说明书号", required = false) @RequestParam(value = "credential", required = false) String credential,
                             @ApiParam(value = "交易日期", required = false) @RequestParam(value = "dealDate", required = false) Date dealDate,
                             @RequestParam(value = "field", defaultValue = "updateTime", required = false) @ApiParam(value = "排序字段,默认是更新时间") String field,
                             @RequestParam(defaultValue = "descend", required = false) @ApiParam(value = "排序方式默认倒序descend、ascend") String order,
                             @RequestParam(defaultValue = "1", required = false) @ApiParam(value = "导出类型，1是比例折算，2是汇总分析") String exportType,
                             HttpServletResponse response) {
        omExportService.reportExport(sourceProc, credential, dealDate, field, order, response, exportType);
    }

    @ApiOperation(value = "删除一条再出口数据")
    @RequiresPermissions("ouman:export:delete")
    @DeleteMapping("/delReExport")
    public Result delReExport(@ApiParam(value = "主键id", required = true) @RequestParam(value = "id") String id) {
        omExportService.delReExport(id);
        return Result.ok();
    }

    @ApiOperation(value = "修改一条再出口数据")
    @RequiresPermissions("ouman:export:update")
    @PostMapping("/updateReExport")
    public Result updateReExport(@Validated @RequestBody OmExportFrom omExportFrom, BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        omExportService.updateReExport(omExportFrom);
        return Result.ok();
    }

    @ApiOperation(value = "再出口的柱状图数据")
    @RequiresPermissions("ouman:exportAnalyse:analyse")
    @GetMapping("/getReexportHistogram")
    public Result<List<OmHistogram>> getReexportHistogram(@RequestParam(required = false, value = "procComp") @ApiParam(value = "欧鳗加工企业") String procComp,
                                                          @RequestParam(required = false) @ApiParam(value = "允许进出口证明书号") String credential,
                                                          @RequestParam(required = false) @ApiParam(value = "起始时间") Date startDate,
                                                          @RequestParam(required = false) @ApiParam(value = "结束时间") Date endDate) {
        List<OmHistogram> list = omExportService.getReexportHistogram(procComp, credential, startDate, endDate);
        return Result.ok(list);
    }
}
