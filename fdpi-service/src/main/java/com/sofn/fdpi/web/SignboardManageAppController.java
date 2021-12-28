package com.sofn.fdpi.web;

import com.sofn.common.model.Result;
import com.sofn.common.utils.BoolUtils;
import com.sofn.common.utils.IdUtil;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.web.BaseController;
import com.sofn.fdpi.service.SignboardChangeRecordService;
import com.sofn.fdpi.service.SignboardService;
import com.sofn.fdpi.util.MapUtil;
import com.sofn.fdpi.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Deacription
 * @Author yumao
 * @Date 2019/12/26 17:08
 **/
@Slf4j
@Api(value = "APP_标识管理相关接口", tags = "APP_标识管理相关接口")
@RestController
@RequestMapping(value = "/app/signboardManage")
public class SignboardManageAppController extends BaseController {

    @Autowired
    private SignboardService signboardService;

    @Autowired
    private SignboardChangeRecordService signboardChangeRecordService;


    @ApiOperation(value = "批量激活标识")
    @PutMapping("/activateBatch/{ids}")
    //@SofnLog("批量激活标识")
    public Result<String> activateBatchByIds(
            @ApiParam(value = "待激活IDS，ID用英文,号分隔", required = true) @PathVariable(value = "ids") String ids,
            @ApiParam(value = "标识状态 2-在养 4-销售", required = true) @RequestParam(value = "status") String status) {
        List<String> idList = IdUtil.getIdsByStr(ids);
//        signboardService.activateBatch(idList, status);
        return Result.ok();
    }

    @ApiOperation(value = "批量激活标识")
    @PutMapping("/activateBatch")
    public Result<String> activateBatchByIds(@Validated @RequestBody SignActivForm signActivForm, BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        signboardService.activateBatch(signActivForm);
        return Result.ok();
    }

    @ApiOperation(value = "修改(完善谱系信息)")
    @PutMapping("/update")
    //@SofnLog("修改(完善谱系信息)")
    public Result insertSignboardProcess(@Validated @RequestBody SignboardForm signboardForm, BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        signboardService.updateSignboard(signboardForm);
        return Result.ok();
    }

    @ApiOperation(value = "详情")
    @GetMapping("/get")
//    //@SofnLog("标识详情")
    public Result<SignboardVo> get(
            @ApiParam(name = "id", value = "标识ID", required = true) @RequestParam(value = "id") String id) {
        return Result.ok(signboardService.getSignboard(id));
    }

    @ApiOperation(value = "完善芯片编码")
    @PutMapping(value = "/updateChip")
    public Result updateChip(@ApiParam(value = "主键", required = true) @RequestParam("id") String id,
                             @ApiParam("国外芯片") @RequestParam(value = "chipAbroad", required = false) String chipAbroad,
                             @ApiParam("国内芯片") @RequestParam(value = "chipDomestic", required = false) String chipDomestic) {
        signboardService.updateChip(id, chipAbroad, chipDomestic);
        return Result.ok();
    }

    @ApiOperation(value = "根据标识编码获取详情")
    @GetMapping("/getByCode")
    public Result<SignboardVo> getByCode(
            @ApiParam(name = "code", value = "标识编码", required = true) @RequestParam(value = "code") String code,
            @ApiParam(name = "type", value = "查询类型1二维码2门户") @RequestParam(value = "type", required = false) String type) {
        return Result.ok(signboardService.getSignboardByCode(code, type));
    }

    @ApiOperation(value = "分页查询")
    @GetMapping("/listPage")
    //@SofnLog("分页查询标识")
    public Result<PageUtils<SignboardVo>> listPage(
            @ApiParam("省代码") @RequestParam(required = false) String province,
            @ApiParam("市代码") @RequestParam(required = false) String city,
            @ApiParam("区县代码") @RequestParam(required = false) String district,
            @ApiParam("标识编码(模糊查找,如果为16位,则后端转换为精确查找)") @RequestParam(required = false) String code,
//            @ApiParam("完整标识编码(精确查找全国,后端自动去除其它查询条件)") @RequestParam(required = false) String completeCode,
            @ApiParam("企业名称") @RequestParam(required = false) String compName,
            @ApiParam("申请单号") @RequestParam(required = false) String applyCode,
            @ApiParam("物种名称") @RequestParam(required = false) String speName,
            @ApiParam("物种ID") @RequestParam(required = false) String speId,
            @ApiParam("标识状态(1未激活 2在养 3已注销)") @RequestParam(required = false) String status,
            @ApiParam("谱系信息是否完善(1是 0否)") @RequestParam(required = false) String isPedigree,
            @RequestParam(value = "pageNo") Integer pageNo,
            @RequestParam(value = "pageSize") Integer pageSize) {
        String[] keys = {"status", "code", "isPedigree", "speName", "speId", "compName", "province", "city",
                "district", "applyCode", "delFlag"};
        Object[] vals = {status, code, isPedigree, speName, speId, compName, province, city, district, applyCode, BoolUtils.N};
        return Result.ok(signboardService.listPage(MapUtil.getParams(keys, vals), pageNo, pageSize));
    }

    @ApiOperation(value = "分页查询标识变更记录")
    @GetMapping("/listPageChangeRecord")
    //@SofnLog("分页查询标识变更记录")
    public Result<PageUtils<SignboardChangeRecordVo>> listPage(
            @ApiParam(name = "signboardId", value = "标识ID", required = true) @RequestParam(value = "signboardId") String signboardId,
            @RequestParam(value = "pageNo") Integer pageNo,
            @RequestParam(value = "pageSize") Integer pageSize) {
        Map map = new HashMap<String, Object>(1);
        map.put("signboardId", signboardId);
        return Result.ok(signboardChangeRecordService.listPage(map, pageNo, pageSize));
    }

    @ApiOperation(value = "标识申请换发、补发时,通过标识编码查看具体标识信息")
    @GetMapping("/getListInfo")
    //@SofnLog("查找换发、补发需要的标识信息")
    public Result<List<SignboardApplyListVo>> getListInfo(
            @ApiParam("企业ID(如果不传,后端则取当前用户单位ID)") @RequestParam(required = false) String compId,
            @ApiParam("企业ID(如果不传,后端则取当前用户单位ID)") @RequestParam(required = false) String queryType,
            @ApiParam("物种ID") @RequestParam(required = false) String speId) {
        return Result.ok(signboardService.getListInfo(compId, speId, queryType));
    }

    @ApiOperation(value = "标识申请换发、补发时,通过标识编码查看具体标识信息(分页)")
    @GetMapping("/getListInfoPage")
    //@SofnLog("查找换发、补发需要的标识信息")
    public Result<PageUtils<SignboardApplyListVo>> getListInfoPage(
            @ApiParam("企业ID(如果不传,后端则取当前用户单位ID)") @RequestParam(required = false) String compId,
            @ApiParam("物种ID") @RequestParam(required = false) String speId,
            @RequestParam(value = "pageNo") Integer pageNo,
            @RequestParam(value = "pageSize") Integer pageSize) {
        return Result.ok(signboardService.getListInfoPage(compId, speId, pageNo, pageSize));
    }

    @ApiOperation(value = "标识申请换发、补发时,查看物种利用类型列表和物种来源列表")
    @GetMapping("/getSpeSourceAndUtilizeType")
    //@SofnLog("标识申请换发、补发时,查看物种利用类型列表和物种来源列表")
    public Result<Map<String, List<SelectVo>>> getSpeSourceAndUtilizeTypeAnd(
            @ApiParam(name = "speId", value = "物种ID", required = true) @RequestParam(value = "speId") String speId,
            @ApiParam(name = "compId", value = "企业ID", required = true) @RequestParam(value = "compId") String compId) {
        return Result.ok(signboardService.getSpeSourceAndUtilizeType(speId, compId));
    }

    @ApiOperation(value = "标识申请换发、补发时,查看物种存量和已配发标识数")
    @GetMapping("/getStockAndAllotted")
    public Result<Map<String, Integer>> getStockAndAllotted(
            @ApiParam(name = "speId", value = "物种ID", required = true) @RequestParam(value = "speId") String speId,
            @ApiParam(name = "signboardType", value = "标识类型") @RequestParam(value = "signboardType", required = false) String signboardType,
            @ApiParam(name = "compId", value = "企业ID(如果不传,后端则取当前用户单位ID)", required = true) @RequestParam(value = "compId") String compId) {
        return Result.ok(signboardService.getStockAndAllotted(speId, compId, signboardType));
    }
}
