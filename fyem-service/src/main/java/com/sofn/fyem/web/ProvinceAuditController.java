package com.sofn.fyem.web;

import com.sofn.common.log.SofnLog;
import com.sofn.common.model.Result;
import com.sofn.common.utils.PageUtils;
import com.sofn.fyem.model.ProvinceAudit;
import com.sofn.fyem.service.MinisterAuditService;
import com.sofn.fyem.service.ProvinceAuditService;
import com.sofn.fyem.vo.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: DJH
 * @Date: 2020/4/27 14:55
 */
//@Slf4j
//@Api(value = "省级审核接口", tags = "省级审核接口")
//@RestController
//@RequestMapping(value = "/provinceAudit")
@Deprecated
public class ProvinceAuditController {
    @Autowired
    private ProvinceAuditService provinceAuditService;
    @Autowired
    private MinisterAuditService ministerAuditService;

    @SofnLog("省级审核数据展示")
    @ApiOperation(value = "省级审核数据展示")
//    @RequiresPermissions(value = "")
    @GetMapping("/listProvinceAuditsByBelongYear")
    public Result<List<ProvinceAuditVo>> listProvinceAuditsByBelongYear(@RequestParam(value = "belongYear", required = true, defaultValue = "0") @ApiParam(name = "belongYear", value = "所属年度,（必传）", required = true) String belongYear,
                                                                        @RequestParam(value = "provinceId", required = true, defaultValue = "") @ApiParam(name = "provinceId", value = "省id,（必传）", required = true) String provinceId,
                                                                        @RequestParam(value = "orgName", required = false, defaultValue = "") @ApiParam(name = "orgName", value = "上报单位,（非必传）", required = false) String orgName){
        Map<String, Object> params = new HashMap();
        params.put("belongYear", belongYear);
        params.put("provinceId", provinceId);
        params.put("orgName",orgName);
        List<ProvinceAuditVo> list = provinceAuditService.listProvinceAuditsByBelongYear(params);
        return Result.ok(list);
    }

    @SofnLog("省级审核数据展示（分页）")
    @ApiOperation(value = "省级审核数据展示（分页）")
//    @RequiresPermissions(value = "")
    @GetMapping("/getProvinceAuditsListByPage")
    public Result<List<ProvinceAuditVo>> getProvinceAuditsListByPage(@RequestParam(value = "belongYear", required = true, defaultValue = "0") @ApiParam(name = "belongYear", value = "所属年度,（必传）", required = true) String belongYear,
                                                                     @RequestParam(value = "provinceId", required = true, defaultValue = "") @ApiParam(name = "provinceId", value = "省id,（必传）", required = true) String provinceId,
                                                                     @RequestParam(value = "orgName", required = false, defaultValue = "") @ApiParam(name = "orgName", value = "上报单位,（非必传）", required = false) String orgName,
                                                                     @RequestParam(value = "pageNo", required = true, defaultValue = "0") @ApiParam(name = "pageNo", value = "起始位置,（必传）", required = true) int pageNo,
                                                                     @RequestParam(value = "pageSize", required = true, defaultValue = "0") @ApiParam(name = "pageSize", value = "分页大小,（必传）", required = true) int pageSize){
        Map<String, Object> params = new HashMap();
        params.put("belongYear", belongYear);
        params.put("provinceId", provinceId);
        params.put("orgName",orgName);
        PageUtils<ProvinceAuditVo> list = provinceAuditService.getProvinceAuditsListByPage(params, pageNo, pageSize);
        return Result.ok(list);
    }

    @SofnLog("省级审核数据新增")
    @ApiOperation(value = "省级审核数据新增")
//    @RequiresPermissions(value = "")
    @PostMapping("/insert")
    public Result insert(@Validated @RequestBody @ApiParam(name = "provinceAudit", value = "省级审核对象,（必传）", required = true) ProvinceAudit provinceAudit){
        String result = provinceAuditService.insert(provinceAudit);
        if ("1".equals(result)){
            return Result.ok("新增成功！");
        }else {
            return Result.error(result);
        }
    }

    @SofnLog("省级审核数据查看")
    @ApiOperation(value = "省级审核数据查看")
//    @RequiresPermissions(value = "")
    @GetMapping("/view")
    public Result view(@RequestParam(value = "belongYear", required = true, defaultValue = "0")  @ApiParam(name = "belongYear", value = "所属年度,（必传）", required = true) String belongYear,
                       @RequestParam(value = "cityId", required = true, defaultValue = "")  @ApiParam(name = "cityId", value = "市id,（必传）", required = true) String cityId,
                       @RequestParam(value = "roleCode", required = false, defaultValue = "")  @ApiParam(name = "roleCode", value = "角色code,（非必传）", required = false) String roleCode){

        Map<String, Object> params = new HashMap();
        params.put("belongYear", belongYear);
        params.put("cityId",cityId);
        params.put("roleCode",roleCode);
        List<CityAuditVo> list = provinceAuditService.view(params);
        return Result.ok(list);
    }

    @SofnLog("省级上报管理展示")
    @ApiOperation(value = "省级上报管理展示")
//    @RequiresPermissions(value = "")
    @GetMapping("/reportManagement")
    public Result reportManagement(@RequestParam(value = "belongYear", required = false, defaultValue = "0")  @ApiParam(name = "belongYear", value = "所属年度,（非必传）", required = false) String belongYear,
                                   @RequestParam(value = "provinceId", required = true, defaultValue = "")  @ApiParam(name = "provinceId", value = "省id,（必传）", required = true) String provinceId,
                                   @RequestParam(value = "roleCode", required = false, defaultValue = "")  @ApiParam(name = "roleCode", value = "角色code,（非必传）", required = false) String roleCode){
        Map<String, Object> params = new HashMap();
        params.put("belongYear", belongYear);
        params.put("provinceId", provinceId);
        params.put("roleCode", roleCode);
        List<ProvinceReportManagementVo> list = provinceAuditService.reportManagement(params);
        return Result.ok(list);
    }

    @SofnLog("获取驳回意见")
    @ApiOperation(value = "获取驳回意见")
//    @RequiresPermissions(value = "")
    @GetMapping("/getRemark")
    public Result getRemark(@RequestParam(value = "belongYear", required = true, defaultValue = "0")  @ApiParam(name = "belongYear", value = "所属年度,（必传）", required = true) String belongYear,
                            @RequestParam(value = "provinceId", required = true, defaultValue = "")  @ApiParam(name = "provinceId", value = "省id,（必传）", required = true) String provinceId){
        Map<String, Object> params = new HashMap();
        params.put("belongYear", belongYear);
        params.put("provinceId", provinceId);

        MinisterRemarkVo ministerRemarkVo = ministerAuditService.getRemark(params);
        return Result.ok(ministerRemarkVo);
    }

    @SofnLog("省级审核通过")
    @ApiOperation(value = "省级审核通过")
//    @RequiresPermissions(value = "")
    @GetMapping("/provinceAuditApprove")
    public Result provinceAuditApprove(@RequestParam(value = "belongYear", required = true, defaultValue = "0")  @ApiParam(name = "belongYear", value = "所属年度,（必传）", required = true) String belongYear,
                                   @RequestParam(value = "cityId", required = true, defaultValue = "")  @ApiParam(name = "cityId", value = "市id,（必传）", required = true) String cityId){
        Map<String, Object> params = new HashMap();
        params.put("belongYear", belongYear);
        params.put("cityId", cityId);
        String result = provinceAuditService.provinceAuditApprove(params);
        if ("1".equals(result)){
            return Result.ok("通过成功！");
        } else {
            return Result.error(result);
        }
    }

    @SofnLog("省级审核驳回")
    @ApiOperation(value = "省级审核驳回")
//    @RequiresPermissions(value = "")
    @GetMapping("/provinceAuditReject")
    public Result provinceAuditReject(@RequestParam(value = "belongYear", required = true, defaultValue = "0")  @ApiParam(name = "belongYear", value = "所属年度,（必传）", required = true) String belongYear,
                                  @RequestParam(value = "cityId", required = true, defaultValue = "")  @ApiParam(name = "cityId", value = "市id,（必传）", required = true) String cityId){
        Map<String, Object> params = new HashMap();
        params.put("belongYear", belongYear);
        params.put("cityId",cityId);
        String result = provinceAuditService.provinceAuditReject(params);
        if ("1".equals(result)){
            return Result.ok("驳回成功！");
        } else {
            return Result.error(result);
        }
    }

    @SofnLog("填写驳回意见")
    @ApiOperation(value = "填写驳回意见")
//    @RequiresPermissions(value = "")
    @PutMapping("/editRemark")
    public Result editRemark(@Validated @RequestBody @ApiParam(name = "provinceRemarkVo", value = "驳回意见,（必传）", required = true) ProvinceRemarkVo provinceRemarkVo){
        String result = provinceAuditService.editRemark(provinceRemarkVo);
        if ("1".equals(result)){
            return Result.ok("填写成功！");
        }else {
            return Result.error(result);
        }
    }

    @SofnLog("省级上报")
    @ApiOperation(value = "省级上报")
//    @RequiresPermissions(value = "")
    @GetMapping("/provinceAuditReport")
    public Result provinceAuditReport(@RequestParam(value = "belongYear", required = true, defaultValue = "0")  @ApiParam(name = "belongYear", value = "所属年度,（必传）", required = true) String belongYear,
                                  @RequestParam(value = "provinceId", required = true, defaultValue = "")  @ApiParam(name = "provinceId", value = "省id,（必传）", required = true) String provinceId){
        Map<String, Object> params = new HashMap();
        params.put("belongYear", belongYear);
        params.put("provinceId",provinceId);
        String result = provinceAuditService.provinceAuditReport(params);
        if ("1".equals(result)){
            return Result.ok("上报成功！");
        } else {
            return Result.error(result);
        }
    }

}
