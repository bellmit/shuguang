package com.sofn.fdpi.web;

import com.alibaba.excel.util.BooleanUtils;
import com.google.common.collect.Maps;
import com.sofn.common.model.Result;
import com.sofn.common.utils.BoolUtils;
import com.sofn.common.utils.UserUtil;
import com.sofn.common.web.BaseController;
import com.sofn.fdpi.constants.Constants;
import com.sofn.fdpi.model.PapersYearInspectProcess;
import com.sofn.fdpi.service.PapersYearInspectProcessService;
import com.sofn.fdpi.service.PapersYearInspectService;
import com.sofn.fdpi.service.SignboardService;
import com.sofn.fdpi.util.RedisUserUtil;
import com.sofn.fdpi.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(value="证书年审管理相关接口",tags = "证书年审管理相关接口")
@RestController
@RequestMapping("/papersYearInspect")
public class PapersYearInspectController extends BaseController {
    @Autowired
    private PapersYearInspectService papersYearInspectService;

    @Autowired
    private SignboardService signboardService;
    @Autowired
    private PapersYearInspectProcessService papersYearInspectProcessService;

    //wuXY
    @RequiresPermissions(value = {"fdpi:papersYearInspectApply:query","fdpi:papersYearInspectApprove:query"},logical = Logical.OR)
    @ApiOperation(value = "证书年审管理《查询列表》，申请和审核列表同一个接口")
    @GetMapping("/listForApply")
    public Result<List<PapersYearInspectVo>> listForApply(@RequestParam(value = "year", required = false) @ApiParam(name = "year", value = "年度") String year
            , @RequestParam(value = "status", required = false) @ApiParam(name = "status", value = "审核状态:1:未上报；2：上报；3：初审退回；4：初审通过；5：复审退回；6：复审通过") String status
            , @RequestParam(value = "provinceCode", required = false) @ApiParam(name = "provinceCode",value="省") String provinceCode
            , @RequestParam(value = "cityCode", required = false) @ApiParam(name = "cityCode", value = "市") String cityCode
            , @RequestParam(value = "districtCode", required = false) @ApiParam(name = "districtCode", value = "区") String districtCode
            , @RequestParam(value = "compName", required = false) @ApiParam(name = "compName", value = "公司名称") String compName
            , @RequestParam(value = "pageNo", required = false,defaultValue = "0") @ApiParam(name = "pageNo", value = "索引") Integer pageNo
            , @RequestParam(value = "pageSize", required = false,defaultValue = "20") @ApiParam(name = "pageSize", value = "每页条数") Integer pageSize) {

        Map<String, Object> map = Maps.newHashMap();
        map.put("year", year);
        map.put("status", status);
        map.put("provinceCode", provinceCode);
        map.put("cityCode", cityCode);
        map.put("districtCode", districtCode);
        map.put("compName", compName);
        map.put("pageNo", pageNo);
        map.put("pageSize", pageSize);
        return Result.ok(papersYearInspectService.listForApply(map));
    }

    //wuXY
    @ApiOperation(value = "获取证书年审《编辑/新增/详情》数据")
    @GetMapping("/getById")
    public Result<PapersYearInspectViewVo> getDetailById(@RequestParam(value = "inspectId", required = false) @ApiParam(name = "inspectId", value = "证书年审ID") String inspectId) {
        return Result.ok(papersYearInspectService.getDetailById(UserUtil.getLoginUserOrganizationId(),inspectId));
    }
    //wuXY
    @ApiOperation(value = "证书年审(或者企业信息)中编辑/新增/详情中获取标识列表（分页）")
    @GetMapping("/listForSignBoard")
    public Result<List<SignboardVoForInspect>> listForSignBoard(@RequestParam(value = "inspectId", required = false) @ApiParam(name = "inspectId", value = "证书年审ID,年审新增时不需要传值") String inspectId
            , @RequestParam(value = "speciesId") @ApiParam(name = "speciesId", value = "物种id",required = true) String speciesId
            , @RequestParam(value = "signboardCode",required = false) @ApiParam(name = "signboardCode", value = "标识编码") String signboardCode
            , @RequestParam(value = "signboardType",required = false) @ApiParam(name = "signboardType", value = "标识类型") String signboardType
            , @RequestParam(value = "pageNo", required = false,defaultValue = "0") @ApiParam(name = "pageNo", value = "索引") Integer pageNo
            , @RequestParam(value = "pageSize", required = false,defaultValue = "20") @ApiParam(name = "pageSize", value = "每页条数") Integer pageSize) {

        return Result.ok(signboardService.listPageForSignboard("",inspectId,speciesId,signboardCode,signboardType,pageNo,pageSize));
    }

    //wuXY
    @RequiresPermissions(value = {"fdpi:papersYearInspectApply:create","fdpi:papersYearInspectApply:update"},logical = Logical.OR)
    @ApiOperation(value = "证书年审编辑/新增《保存》,新增/编辑区分主要是对象中inspectId(主表主键)、retailId(明细表主键)的值，有值则编辑，无则新增,新增会返回年审主键inspectId,《上报》使用")
    @PostMapping("/save")
    public Result saveOrUpdate(@Validated @RequestBody @ApiParam(name = "papersYearInspectForm", value = "年审《编辑/新增》json对象", required = true) PapersYearInspectForm papersYearInspectForm
            , BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }

        return papersYearInspectService.saveOrUpdate(papersYearInspectForm, false);
    }
    //wuXY
    @RequiresPermissions("fdpi:papersYearInspectApply:report")
    @ApiOperation(value = "证书年审编辑/新增中《上报》，新增/编辑区分主要是对象中inspectId(主表主键)、retailId(明细表主键)的值，这里是页面直接操作《上报》未点击《保存》，则调用saveAndReport接口，如果先《保存》再点击《上报》，则上报调用report接口")
    @PostMapping("/saveAndReport")
    public Result saveAndReport(@Validated @RequestBody @ApiParam(name = "papersYearInspectForm", value = "年审《编辑/新增》json对象", required = true) PapersYearInspectForm papersYearInspectForm
            , BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        return papersYearInspectService.saveOrUpdate(papersYearInspectForm, true);
    }
    //wuXY
    @RequiresPermissions("fdpi:papersYearInspectApply:report")
    @ApiOperation(value = "证书年审列表中《上报》")
    @PutMapping("/report")
    public Result report(@RequestParam(value = "inspectId") @ApiParam(name = "inspectId", value = "证书审核ID") String inspectId) {
        String isSuccess = papersYearInspectService.report(inspectId);
        if (!"1".equals(isSuccess)) {
            return Result.error(isSuccess);
        }

        return Result.ok("上报成功！");
    }

    /**
     * @author wg
     * @description 证书年审上报撤回
     * @date 2021/4/9 9:31
     * @param id
     * @return com.sofn.common.model.Result
     */
    @ApiOperation(value = "证书年审上报撤回")
    @PutMapping("/cancel")
    public Result cancel(
            @ApiParam(value = "主键id", required = true) @RequestParam() String id) {
        papersYearInspectService.cancel(id);
        return Result.ok();
    }

    //wuXY
    @RequiresPermissions("fdpi:papersYearInspectApply:delete")
    @ApiOperation(value = "证书年审列表中《删除》")
    @DeleteMapping("/delete")
    public Result delete(@RequestParam(value = "inspectId") @ApiParam(name = "inspectId", value = "证书审核ID") String inspectId) {
        String isSuccess = papersYearInspectService.deleteInspect(inspectId);
        if (!"1".equals(isSuccess)) {
            return Result.error(isSuccess);
        }
        return Result.ok("删除成功！");
    }

    //wuXY
    @RequiresPermissions("fdpi:papersYearInspectApprove:approve")
    @ApiOperation(value = "证书年审《审核》")
    @PostMapping(value = "approve")
    public Result approve(@RequestBody @ApiParam(name = "processForm", value = "审核表单json", required = true) ProcessForm processForm) {
        String approveResult = papersYearInspectService.approveOrBack(processForm, true);
        if (!"1".equals(approveResult)) {
            return Result.error(approveResult);
        }
        return Result.ok("审核成功！");
    }

    //wuXY
    @RequiresPermissions("fdpi:papersYearInspectApprove:back")
    @ApiOperation(value = "证书年审《退回》")
    @PostMapping(value = "back")
    public Result goBack(@RequestBody @ApiParam(name = "processForm", value = "退回表单json", required = true) ProcessForm processForm) {
        String approveResult = papersYearInspectService.approveOrBack(processForm, false);
        if (!"1".equals(approveResult)) {
            return Result.error(approveResult);
        }
        //校验重复提交
        RedisUserUtil.validReSubmit("papersYearInspect/" + "back");

        return Result.ok("退回成功！");
    }

    //wuXY
    @RequiresPermissions(value = {"fdpi:papersYearInspectApprove:backIdea","fdpi:papersYearInspectApply:backIdea"},logical =Logical.OR)
    @ApiOperation(value = "证书年审获取意见《审核/退回意见》")
    @GetMapping(value = "getAuditProcessByPapersId")
    public Result<PapersYearInspectProcess> getProcessByInspectId(@RequestParam("inspectId") @ApiParam(name = "inspectId", value = "证书年审id", required = true) String inspectId
            , @RequestParam("status") @ApiParam(name = "status", value = "状态", required = true) String status) {

         PapersYearInspectProcess papersYearInspectProcess = Constants.WORKFLOW.equals(BoolUtils.Y) ? papersYearInspectService.getProcessByInspectIdInfo(inspectId, status)
                : papersYearInspectService.getProcessByInspectId(inspectId, status);
        return Result.ok(papersYearInspectProcess);
    }

    //wuXY
    @ApiOperation(value = "v3.0证书年审->详情《操作流程列表》")
    @PostMapping(value = "listForAuditProcessByPapersId")
    public Result<List<AuditProcessVo>> listForAuditProcessByPapersId(@RequestParam("inspectId") @ApiParam(name = "inspectId", value = "证书年审id", required = true) String inspectId){
        List<AuditProcessVo> auditProcessVos =  Constants.WORKFLOW.equals(BoolUtils.Y) ? papersYearInspectProcessService.listForAuditProcessByInspectIdY(inspectId) :
                papersYearInspectProcessService.listForAuditProcessByInspectId(inspectId);
        return Result.ok(auditProcessVos);
    }

    @ApiOperation(value = "每年11月15日到次年2月15日提示用户需要提交年审申请")
    @GetMapping("/promptingInspect")
    public Result<List<PapersProcessVo>> promptingInspect() {
        String result = papersYearInspectService.promptingInspect();
        return StringUtils.hasText(result) ? Result.ok(true, result) : Result.ok(false);
    }
}
