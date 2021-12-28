package com.sofn.fdpi.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sofn.common.model.Result;
import com.sofn.common.utils.BoolUtils;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.web.BaseController;
import com.sofn.fdpi.enums.OMCompProcessEnum;
import com.sofn.fdpi.enums.OmCompType;
import com.sofn.fdpi.model.OmComp;
import com.sofn.fdpi.model.OmProc;
import com.sofn.fdpi.service.*;
import com.sofn.fdpi.util.MapUtil;
import com.sofn.fdpi.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Slf4j
@Api(value = "欧鳗企业相关接口", tags = "欧鳗企业相关接口")
@RestController
@RequestMapping("/omComp")
public class OmCompController extends BaseController {

    @Resource
    private OmCompService omCompService;
    @Resource
    private OmBreedService omBreedService;
    @Resource
    private OmEelImportService omEelImportService;
    @Resource
    private OmProcService omProcService;

    @ApiOperation(value = "欧鳗企业详情")
    @GetMapping("/getOmComp")
    @RequiresPermissions(value = {"ouman:compAudit:view", "ouman:compFilingSelf:view"}, logical = Logical.OR)
    public Result<OmCompVO> getOmComp(
            @RequestParam(value = "id", required = false) @ApiParam(value = "企业自身查询不需要id") String id) {
        return Result.ok(StringUtils.hasText(id) ? omCompService.getOmCompById(id) : omCompService.getOmComp());
    }

    @ApiOperation(value = "申请提交")
    @PostMapping("/apply")
    @RequiresPermissions("ouman:compFilingSelf:apply")
    public Result<SturgeonVo> apply(@Validated @RequestBody OmCompForm omCompForm, BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        omCompService.apply(omCompForm);
        return Result.ok();
    }

    @ApiOperation(value = "审核(通过)")
    @PutMapping("/pass")
    @RequiresPermissions("ouman:compAudit:audit")
    public Result auditPass(@ApiParam(value = "主键id", required = true) @RequestParam() String id) {
        omCompService.audit(id, OMCompProcessEnum.PASS.getKey());
        return Result.ok();
    }

    @ApiOperation(value = "审核(不通过)")
    @PutMapping("/unpass")
    @RequiresPermissions("ouman:compAudit:audit")
    public Result auditReturn(@ApiParam(value = "主键id", required = true) @RequestParam() String id) {
        omCompService.audit(id, OMCompProcessEnum.UN_PASS.getKey());
        return Result.ok();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("/delete")
    @RequiresPermissions("ouman:compAudit:delete")
    public Result delete(@ApiParam(value = "主键id", required = true) @RequestParam(value = "id") String id) {
        if (StringUtils.isEmpty(id)) {
            return Result.error("ID不能为空");
        }
        omCompService.delete(id);
        return Result.ok();
    }


    @ApiOperation(value = "分页查询备案企业审核")
    @GetMapping("/listPageAudit")
    public Result<PageUtils<OmCompVO>> listPageAudit(
            @RequestParam(required = false) @ApiParam(value = "区域编码") String regionCode,
            @RequestParam(required = false) @ApiParam(value = "单位名称") String compName,
            @RequestParam(required = false) @ApiParam(value = "排序字段") String orderBy,
            @RequestParam(required = false) @ApiParam(value = "是否升序,默认倒序") Boolean isAsc,
            @RequestParam(value = "pageNo") Integer pageNo,
            @RequestParam(value = "pageSize") Integer pageSize) {
        String[] keys = {"regionCode", "compName", "orderBy", "isAsc", "status"};
        Object[] vals = {regionCode, compName, orderBy, isAsc, OMCompProcessEnum.APPLY.getKey()};
        return Result.ok(omCompService.listPage(MapUtil.getParams(keys, vals), pageNo, pageSize));
    }

    @ApiOperation(value = "分页查询备案情况查看")
    @GetMapping("/listPageView")
    public Result<PageUtils<OmCompVO>> listPageView(
            @RequestParam(required = false) @ApiParam(value = "单位名称") String compName,
            @RequestParam(required = false) @ApiParam(value = "提交时间") String applyDate,
            @RequestParam(required = false) @ApiParam(value = "排序字段") String orderBy,
            @RequestParam(required = false) @ApiParam(value = "是否升序,默认倒序") Boolean isAsc,
            @RequestParam(value = "pageNo") Integer pageNo,
            @RequestParam(value = "pageSize") Integer pageSize) {
        String[] keys = {"compName", "applyDate", "orderBy", "isAsc"};
        Object[] vals = {compName, applyDate, orderBy, isAsc};
        return Result.ok(omCompService.listPage(MapUtil.getParams(keys, vals), pageNo, pageSize));
    }

    @ApiOperation(value = "分页查询已审核企业列表")
    @GetMapping("/listPageAudited")
    public Result<PageUtils<OmCompVO>> listPageAudited(
            @RequestParam(required = false) @ApiParam(value = "区域编码") String regionCode,
            @RequestParam(required = false) @ApiParam(value = "单位名称") String compName,
            @RequestParam(required = false) @ApiParam(value = "排序字段") String orderBy,
            @RequestParam(required = false) @ApiParam(value = "是否升序,默认倒序") Boolean isAsc,
            @RequestParam(value = "pageNo") Integer pageNo,
            @RequestParam(value = "pageSize") Integer pageSize) {
        String[] keys = {"regionCode", "compName", "orderBy", "isAsc", "statuss"};
        Object[] vals = {regionCode, compName, orderBy, isAsc,
                Arrays.asList(OMCompProcessEnum.UN_PASS.getKey(), OMCompProcessEnum.PASS.getKey())};
        return Result.ok(omCompService.listPage(MapUtil.getParams(keys, vals), pageNo, pageSize));
    }

    @ApiOperation(value = "分页查询企业查询统计")
    @GetMapping("/listPageCount")
    public Result<PageUtils<OmCompVO>> listPageCount(
            @RequestParam(required = false) @ApiParam(value = "区域编码") String regionCode,
            @RequestParam(required = false) @ApiParam(value = "企业类型") String compType,
            @RequestParam(required = false) @ApiParam(value = "排序字段") String orderBy,
            @RequestParam(required = false) @ApiParam(value = "是否升序,默认倒序") Boolean isAsc,
            @RequestParam(value = "pageNo") Integer pageNo,
            @RequestParam(value = "pageSize") Integer pageSize) {
        String[] keys = {"regionCode", "compType", "orderBy", "isAsc", "status"};
        Object[] vals = {regionCode, compType, orderBy, isAsc, OMCompProcessEnum.PASS.getKey()};
        return Result.ok(omCompService.listPage(MapUtil.getParams(keys, vals), pageNo, pageSize));
    }

    @ApiOperation(value = "导出企业查询统计", produces = "application/octet-stream")
    @GetMapping(value = "/exportCount", produces = "application/octet-stream")
    @RequiresPermissions("ouman:compCount:export")
    public void export(
            @RequestParam(required = false) @ApiParam(value = "区域编码") String regionCode,
            @RequestParam(required = false) @ApiParam(value = "企业类型") String compType,
            HttpServletResponse response) {
        String[] keys = {"regionCode", "compType", "status"};
        Object[] vals = {regionCode, compType, OMCompProcessEnum.PASS.getKey()};
        omCompService.exportCount(MapUtil.getParams(keys, vals), response);
    }

    @ApiOperation(value = "企业名称下拉接口")
    @GetMapping("/listComp")
    public Result<List<SelectVo>> listComp(@RequestParam(value = "compType", required = false, defaultValue = "1,2,3") @ApiParam(value = "企业类型,1进口，2养殖，3加工", defaultValue = "1,2,3") ArrayList<Integer> compTypes) {
        return Result.ok(omCompService.listComp(compTypes));
    }

    @ApiOperation(value = "得到当前企业准许驯养繁殖吨数")
    @GetMapping("/getTameAllowTon")
    public Result<Double> getOmComp() {
        OmCompVO omComp = omCompService.getOmComp();
        return Result.ok(omComp.getTameAllowTon());
    }

//    @ApiOperation(value = "根据企业名字和允许进出口说明书号得到饼状图数据")
//    @GetMapping("/getPieChartData")
//    public Result<PieChartData> getPieChartData(@RequestParam(required = true, value = "compName") @ApiParam(value = "欧鳗企业主键") String compName,
//                                                @RequestParam(required = true, value = "credential") @ApiParam(value = "数据的主键") String dataId,
//                                                @RequestParam(required = true, value = "dataType") @ApiParam(value = "数据的类型") String dataType) {
//        PieChartData pieChartData = omCompService.getPieChartData(compName, dataId,dataType);
//        return Result.ok(pieChartData);
//    }

    @ApiOperation(value = "省级、部级用户的配额统计分析列表数据")
    @RequiresPermissions("ouman:quotaManage:analyse")
    @GetMapping("/getQuotaList")
    public Result<PageUtils<QuotaListVo>> getQuotaList(@RequestParam(required = false, value = "compType", defaultValue = "1") @ApiParam(value = "欧鳗类型,欧鳗进口企业传1/欧鳗养殖企业传2/欧鳗加工企业传3", required = true, defaultValue = "1") String compType,
                                                       @RequestParam(required = false, value = "compName") @ApiParam(value = "欧鳗企业(主键)") String compName,
                                                       @RequestParam(required = false, value = "credential") @ApiParam(value = "允许进出口说明书号") String credential,
                                                       @RequestParam(required = false, value = "dealDate") @ApiParam(value = "交易日期") Date dealDate,
                                                       @RequestParam(value = "pageNo") @ApiParam(value = "偏移量") Integer pageNo,
                                                       @RequestParam(value = "pageSize") @ApiParam(value = "每页条数") Integer pageSize,
                                                       @RequestParam(value = "field", defaultValue = "dealDate") @ApiParam(value = "排序字段,默认是交易日期dealDate，其他还支持进口数量importNum(折算比例)、剩余数量residueNum(剩余折算)") String field,
                                                       @RequestParam(defaultValue = "descend") @ApiParam(value = "排序方式默认倒序descend、ascend、默认倒序") String order) {
        String[] keys = {"compType", "compName", "credential", "dealDate", "pageNo", "pageSize", "field", "order"};
        Object[] vals = {OmCompType.getCompTypeByCode(Integer.valueOf(compType)), compName, credential, dealDate, pageNo, pageSize, field, order.equals("descend") ? "desc" : "asc"};
        PageUtils<QuotaListVo> pieChartData = omCompService.getQuotaList(MapUtil.getParams(keys, vals));
        return Result.ok(pieChartData);
    }

    @ApiOperation(value = "省级、部级用户的交易比例折算接口")
    @RequiresPermissions("ouman:transactionRatio:export")
    @GetMapping("/getConvertList")
    public Result<PageUtils<OmBreedProcTableVo>> getConvertList(
            @RequestParam(required = false, value = "cellComp") @ApiParam(value = "出让企业") String cellComp,
            @RequestParam(required = false, value = "transferComp") @ApiParam(value = "受让企业") String transferComp,
            @RequestParam(required = false, value = "credential") @ApiParam(value = "允许进出口说明书号") String credential,
            @RequestParam(required = false, value = "dealDate") @ApiParam(value = "交易日期") Date dealDate,
            @RequestParam(value = "pageNo") @ApiParam(value = "偏移量") Integer pageNo,
            @RequestParam(value = "pageSize") @ApiParam(value = "每页条数") Integer pageSize,
            @RequestParam(value = "field", defaultValue = "dealDate") @ApiParam(value = "排序字段,默认是交易日期dealDate") String field,
            @RequestParam(defaultValue = "descend") @ApiParam(value = "排序方式默认倒序descend、ascend、默认倒序") String order,
            @RequestParam(defaultValue = "1") @ApiParam(value = "交易类型;1是本系统企业，2是数据补录") String searchType) {
        String[] keys = {"cellComp", "transferComp", "credential", "dealDate", "pageNo", "pageSize", "field", "order", "searchType"};
        Object[] vals = {cellComp, transferComp, credential, dealDate, pageNo, pageSize, field, order.equals("descend") ? "desc" : "asc", searchType};
        PageUtils<OmBreedProcTableVo> pageUtils = omCompService.getConvertList(MapUtil.getParams(keys, vals));
        return Result.ok(pageUtils);
    }

    @ApiOperation(value = "省级、部级用户的交易比例折算和汇总分析导出接口")
    @RequiresPermissions(value = {"ouman:transactionAnalyse:export", "ouman:transactionAnalyse:export"}, logical = Logical.OR)
    @GetMapping("/exportAll")
    public void exportAll(
            @RequestParam(required = false, value = "cellComp") @ApiParam(value = "出让企业") String cellComp,
            @RequestParam(required = false, value = "transferComp") @ApiParam(value = "受让企业") String transferComp,
            @RequestParam(required = false, value = "credential") @ApiParam(value = "允许进出口说明书号") String credential,
            @RequestParam(required = false, value = "dealDate") @ApiParam(value = "交易日期") Date dealDate,
            @RequestParam(value = "field", defaultValue = "dealDate") @ApiParam(value = "排序字段,默认是交易日期dealDate") String field,
            @RequestParam(defaultValue = "descend") @ApiParam(value = "排序方式默认倒序descend、ascend、默认倒序") String order,
            @RequestParam(defaultValue = "1") @ApiParam(value = "交易类型;1是本系统企业，2是数据补录") String dealType,
            @RequestParam(defaultValue = "1") @ApiParam(value = "导出类型;1是比例折算，2是汇总分析") String exportType,
            HttpServletResponse httpServletResponse) {
        String[] keys = {"cellComp", "transferComp", "credential", "dealDate", "field", "order", "dealType", "exportType"};
        Object[] vals = {cellComp, transferComp, credential, dealDate, field, order.equals("descend") ? "desc" : "asc", dealType, exportType};
        omCompService.exportAll(MapUtil.getParams(keys, vals), httpServletResponse);
    }

    @ApiOperation(value = "欧鳗企业柱状图数据格式")
    @RequiresPermissions("ouman:transactionAnalyse:analyse")
    @GetMapping("/omHistogram")
    public Result<List<OmHistogram>> breedHistogram(@RequestParam(required = true) @ApiParam(value = "欧鳗企业主键") String omCompId,
                                                    @RequestParam(required = false) @ApiParam(value = "允许进出口证明书号") String credential,
                                                    @RequestParam(required = false) @ApiParam(value = "起始时间") Date startDate,
                                                    @RequestParam(required = false) @ApiParam(value = "结束时间") Date endDate) {
        //判断企业的类型
        QueryWrapper<OmComp> wrapper = new QueryWrapper<>();
        wrapper.eq("del_flag", BoolUtils.N)
                .eq("id", omCompId)
                .select("comp_type");
        OmComp one = omCompService.getOne(wrapper);
        List<OmHistogram> omHistogram = null;
        //根据企业的类型去做不同的处理
        switch (one.getCompType()) {
            case "欧鳗进口企业":
                omHistogram = omEelImportService.getOmHistogram(omCompId, credential, startDate, endDate);
                break;
            case "欧鳗养殖企业":
                omHistogram = omBreedService.getBreedHistogram(omCompId, credential, startDate, endDate);
                break;
            case "欧鳗加工企业":
                omHistogram = omProcService.getOmHistogram(omCompId, credential, startDate, endDate);
                break;
        }
        return Result.ok(omHistogram);
    }


    @ApiOperation(value = "省级部级用户查找一条数据")
    @RequiresPermissions("ouman:transactionAnalyse:view")
    @GetMapping("/searchBreedOrProcOne")
    public Result<OmBreedReVo> searchBreedOrProcOne(@RequestParam(value = "dataType", required = true) @ApiParam(value = "该数据的类型,2是养殖，3是加工", required = true) String dataType,
                                                    @RequestParam(value = "id", required = true) @ApiParam(value = "数据的主键", required = true) String id) {
        OmBreedReVo omBreedReVo = null;
        //先判断数据的类型
        switch (dataType) {
            case "2":
                omBreedReVo = omBreedService.searchByid(id);
                break;
            case "3":
                omBreedReVo = omProcService.getById(id);
                break;
        }
        return Result.ok(omBreedReVo);
    }
}
