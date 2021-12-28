package com.sofn.fdpi.web;

import com.google.common.collect.Maps;
import com.sofn.common.model.Result;
import com.sofn.common.utils.UserUtil;
import com.sofn.common.web.BaseController;
import com.sofn.fdpi.model.AuditProcess;
import com.sofn.fdpi.model.Papers;
import com.sofn.fdpi.service.PapersService;
import com.sofn.fdpi.service.TbCompService;
import com.sofn.fdpi.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@Api(value = "APP_证书绑定相关接口",tags = "APP_证书绑定相关接口")
@RequestMapping("/app/papersBinding")
@RestController
public class PapersBindingAppController extends BaseController {
    @Autowired
    private PapersService papersService;

    @Autowired
    private TbCompService tbCompService;

    //wuXY
    @RequiresPermissions("fdpi:papersBindingApply:query")
    @ApiOperation(value = "证书绑定申请-》获取《列表》")
    @GetMapping(value = "/listForBinding")
    public Result<List<PapersVo>> listForBinding(
            @RequestParam(value = "papersType", required = false) @ApiParam(name = "papersType", value = "证书类型：1：人工繁育；2：驯养繁殖；3：经营利用") String papersType
            , @RequestParam(value = "papersNumber", required = false) @ApiParam(name = "papersNumber", value = "证书编号") String papersNumber
            , @RequestParam(value = "parStatus", required = false) @ApiParam(name = "parStatus", value = "审核状态：1：绑定未上报；2：上报；3：初审退回；4;初审通过；5：复审退回；6：复审通过") String parStatus
            , @RequestParam(value = "issueSpe", required = false) @ApiParam(name = "issueSpe", value = "物种") String issueSpe
            , @RequestParam(value = "compName", required = false) @ApiParam(name = "compName", value = "企业名称") String compName
            , @RequestParam(value = "pageNo", required = false,defaultValue = "0") @ApiParam(name = "pageNo", value = "索引") Integer pageNo
            , @RequestParam(value = "pageSize", required = false,defaultValue = "20") @ApiParam(name = "pageSize", value = "每页条数") Integer pageSize) {

        Map<String, Object> map = Maps.newHashMap();
        map.put("papersType", papersType);
        map.put("papersNumber", papersNumber);
        map.put("parStatus", parStatus);
        map.put("issueSpe", issueSpe);
        map.put("compName", compName);
        //当前企业的id
        map.put("compId", UserUtil.getLoginUserOrganizationId());

        return Result.ok(papersService.listForBinding(map,pageNo,pageSize));
    }

    //wuXY
    @ApiOperation(value = "证书绑定申请-》新增中《获取当前账号企业的信息》")
    @GetMapping("/getOwnCompInfo")
    public Result<TbCompVo> getOwnCompInfo() {
        return Result.ok(tbCompService.getCombById(UserUtil.getLoginUserOrganizationId()));
    }

    @ApiOperation(value="证书绑定申请-》《新增》根据证书编号获取证书信息")
    @GetMapping("/getPapersById")
    public Result<Papers> getPapersById(@RequestParam(value="papersId")@ApiParam(name="papersId",value="证书表中id，证书下拉列表中的key值",required = true) String papersId){
        if(org.springframework.util.StringUtils.isEmpty(papersId)){
            return Result.error("请选择证书编号列表！");
        }
        return Result.ok(papersService.getPapersById(papersId));
    }

    //wuXY
    @RequiresPermissions(value = {"fdpi:papersBindingApply:update","fdpi:papersBindingApply:view"},logical = Logical.OR)
    @ApiOperation(value = "证书绑定申请-》编辑/查看中根据证书id，获取证书信息(编辑/详情显示数据)")
    @GetMapping("/getPapersInfo")
    public Result<EnterpriseForm> getPapersInfo(@RequestParam("papersId") @ApiParam(name = "papersId", value = "证书id", required = true) String papersId) {
        return Result.ok(papersService.getPapersInfo(papersId));
    }

    //wuXY
    @RequiresPermissions(value = {"fdpi:papersBindingApply:create","fdpi:papersBindingApply:update"},logical = Logical.OR)
    @ApiOperation(value = "证书绑定申请-》新增中《保存/修改》")
    @PostMapping(value = "/saveForBinding")
    public Result saveForBinding(@Validated @RequestBody @ApiParam(name = "papersForm", value = "新增证书绑定json对象", required = true) PapersBindingForm papersBindingForm
            , BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }

        String isSuccess = papersService.saveForBinding(papersBindingForm, "1");
        if (!"1".equals(isSuccess)) {
            return Result.error(isSuccess);
        }
        return Result.ok("保存成功！");
    }

    //wuXY
    @RequiresPermissions("fdpi:papersBindingApply:report")
    @ApiOperation(value = "证书绑定申请-》新增中《上报》操作")
    @PostMapping(value = "/saveAndReportForBinding")
    public Result saveAndReportForBinding(@Validated @RequestBody @ApiParam(name = "papersForm", value = "新增证书绑定json对象", required = true) PapersBindingForm papersBindingForm
            , BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }

        String isSuccess = papersService.saveForBinding(papersBindingForm, "2");
        if (!"1".equals(isSuccess)) {
            return Result.error(isSuccess);
        }
        return Result.ok("上报成功！");
    }

    //wuXY
    @RequiresPermissions("fdpi:papersBindingApply:report")
    @ApiOperation(value = "证书绑定申请-》列表中《上报》操作")
    @PostMapping(value = "/reportForBinding")
    public Result reportForBinding(@RequestParam("papersId") @ApiParam(name = "papersId", value = "证书id", required = true) String papersId) {
        if (StringUtils.isBlank(papersId)) {
            return Result.error("请传递证书id！");
        }

        String isSuccess = papersService.reportForBinding(papersId, "2");
        if (!"1".equals(isSuccess)) {
            return Result.error(isSuccess);
        }
        return Result.ok("上报成功！");
    }

    //wuXY
    @RequiresPermissions("fdpi:papersBindingApply:delete")
    @ApiOperation(value = "证书绑定申请-》列表中《删除》操作")
    @DeleteMapping(value = "/deleteForBinding")
    public Result deleteForBinding(@RequestParam("papersId") @ApiParam(name = "papersId", value = "证书id", required = true) String papersId) {
        if (StringUtils.isBlank(papersId)) {
            return Result.error("请传递证书id！");
        }
        String isSuccess = papersService.deleteBindingByPapersId(papersId);
        if (!"1".equals(isSuccess)) {
            return Result.error(isSuccess);
        }
        return Result.ok("删除成功！");
    }

    //wuXY 直属、省级
    @RequiresPermissions("fdpi:papersBindingApprove:query")
    @ApiOperation(value = "证书绑定审核-》获取《列表》")
    @GetMapping(value = "/listForBindingApprove")
    public Result<List<PapersVo>> listForBindingApprove(
            @RequestParam(value = "province", required = false) @ApiParam(name = "province", value = "省") String province
            , @RequestParam(value = "city", required = false) @ApiParam(name = "city", value = "市") String city
            , @RequestParam(value = "district", required = false) @ApiParam(name = "district", value = "区") String district
            , @RequestParam(value = "compName", required = false) @ApiParam(name = "compName", value = "企业名称") String compName
            , @RequestParam(value = "papersType", required = false) @ApiParam(name = "papersType", value = "证书类型：1：人工繁育；2：驯养繁殖；3：经营利用") String papersType
            , @RequestParam(value = "parStatus", required = false) @ApiParam(name = "parStatus", value = "审核状态：1：绑定未上报；2：上报；3：初审退回；4:初审通过；5：复审退回；6：复审通过") String parStatus
            , @RequestParam(value = "pageNo", required = false,defaultValue = "0") @ApiParam(name = "pageNo", value = "索引") Integer pageNo
            , @RequestParam(value = "pageSize", required = false,defaultValue = "20") @ApiParam(name = "pageSize", value = "每页条数") Integer pageSize) {

        Map<String, Object> map = Maps.newHashMap();
        map.put("province", province);
        map.put("city", city);
        map.put("district", district);
        map.put("compName", compName);
        map.put("papersType", papersType);
        map.put("parStatus", parStatus);
        map.put("pageNo", pageNo);
        map.put("pageSize", pageSize);

        return Result.ok(papersService.listForBindingApprove(map));
    }

    //wuXY
    @RequiresPermissions("fdpi:papersBindingApprove:approve")
    @ApiOperation(value = "证书绑定-》《审核》")
    @PostMapping(value = "approve")
    public Result approve(@RequestBody @ApiParam(name = "processForm", value = "审核表单json", required = true) ProcessForm processForm) {
        String approveResult = papersService.approveOrBack(processForm, "1");
        if (!"1".equals(approveResult)) {
            return Result.error(approveResult);
        }
        return Result.ok("审核成功！");
    }

    //wuXY
    @RequiresPermissions("fdpi:papersBindingApprove:back")
    @ApiOperation(value = "证书绑定-》列表中《退回》")
    @PostMapping(value = "back")
    public Result goBack(@RequestBody @ApiParam(name = "processForm", value = "退回表单json", required = true) ProcessForm processForm) {
        String approveResult = papersService.approveOrBack(processForm, "0");
        if (!"1".equals(approveResult)) {
            return Result.error(approveResult);
        }
        return Result.ok("退回成功！");
    }

    //wuXY
    @RequiresPermissions("fdpi:papersBindingApprove:backIdea")
    @ApiOperation(value = "证书绑定-》获取意见《审核/退回意见》")
    @PostMapping(value = "getAuditProcessByPapersId")
    public Result<AuditProcess> getAuditProcessByPapersId(@RequestParam("papersId") @ApiParam(name = "papersId", value = "证书id", required = true) String papersId
            , @RequestParam("status") @ApiParam(name = "status", value = "状态", required = true) String status) {
        return Result.ok(papersService.getAuditProcessByPapersId(papersId, status));
    }

}
