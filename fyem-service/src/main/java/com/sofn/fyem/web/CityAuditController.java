package com.sofn.fyem.web;

import com.sofn.common.log.SofnLog;
import com.sofn.common.model.Result;
import com.sofn.common.utils.PageUtils;
import com.sofn.fyem.model.CityAudit;
import com.sofn.fyem.service.CityAuditService;
import com.sofn.fyem.service.ProvinceAuditService;
import com.sofn.fyem.vo.CityAuditVo;
import com.sofn.fyem.vo.CityRemarkVo;
import com.sofn.fyem.vo.CityReportManagementVo;
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
 * @Date: 2020/4/27 14:42
 */
//@Slf4j
//@Api(value = "市级审核接口", tags = "市级审核接口")
//@RestController
//@RequestMapping(value = "/cityAudit")
@Deprecated
public class CityAuditController {
    @Autowired
    private CityAuditService cityAuditService;

    @Autowired
    private ProvinceAuditService provinceAuditService;


    @SofnLog("市级审核数据展示")
    @ApiOperation(value = "市级审核数据展示")
//    @RequiresPermissions(value = "")
    @GetMapping("/listCityAuditsByBelongYear")
    public Result<List<CityAuditVo>> listCityAuditsByBelongYear(@RequestParam(value = "belongYear", required = false, defaultValue = "0") @ApiParam(name = "belongYear", value = "所属年度,（非必传）", required = false) String belongYear,
                                                                @RequestParam(value = "cityId", required = true, defaultValue = "0") @ApiParam(name = "cityId", value = "市id,（必传）", required = true) String cityId,
                                                                @RequestParam(value = "orgName", required = false, defaultValue = "") @ApiParam(name = "orgName", value = "上报单位,（非必传）", required = false) String orgName){
        Map<String, Object> params = new HashMap();
        params.put("belongYear", belongYear);
        params.put("cityId", cityId);
        params.put("orgName",orgName);
        List<CityAuditVo> list = cityAuditService.listCityAuditsByBelongYear(params);
        return Result.ok(list);
    }

    @SofnLog("市级审核数据展示（分页）")
    @ApiOperation(value = "市级审核数据展示（分页）")
//    @RequiresPermissions(value = "")
    @GetMapping("/getCityAuditsListByPage")
    public Result<List<CityAuditVo>> getCityAuditsListByPage(@RequestParam(value = "belongYear", required = false, defaultValue = "0") @ApiParam(name = "belongYear", value = "所属年度,（非必传）", required = false) String belongYear,
                                                             @RequestParam(value = "cityId", required = true, defaultValue = "0") @ApiParam(name = "cityId", value = "市id,（必传）", required = true) String cityId,
                                                             @RequestParam(value = "orgName", required = false, defaultValue = "") @ApiParam(name = "orgName", value = "上报单位,（非必传）", required = false) String orgName,
                                                             @RequestParam(value = "pageNo", required = true, defaultValue = "0") @ApiParam(name = "pageNo", value = "起始位置,（必传）", required = true) int pageNo,
                                                             @RequestParam(value = "pageSize", required = true, defaultValue = "0") @ApiParam(name = "pageSize", value = "分页大小,（必传）", required = true) int pageSize){
        Map<String, Object> params = new HashMap();
        params.put("belongYear", belongYear);
        params.put("cityId", cityId);
        params.put("orgName",orgName);
        PageUtils<CityAuditVo> list = cityAuditService.getCityAuditsListByPage(params, pageNo, pageSize);
        return Result.ok(list);
    }

    @SofnLog("市级审核数据新增")
    @ApiOperation(value = "市级审核数据新增")
//    @RequiresPermissions(value = "")
    @PostMapping("/insert")
    public Result insert(@Validated @RequestBody @ApiParam(name = "cityAudit", value = "市级审核对象,（必传）", required = true) CityAudit cityAudit){
        String result = cityAuditService.insert(cityAudit);
        if ("1".equals(result)){
            return Result.ok("新增成功！");
        }else {
            return Result.error(result);
        }
    }

    @SofnLog("市级审核数据查看")
    @ApiOperation(value = "市级审核数据查看")
//    @RequiresPermissions(value = "")
    @GetMapping("/view")
    public Result view(@RequestParam(value = "belongYear", required = true, defaultValue = "0")  @ApiParam(name = "belongYear", value = "所属年度,（必传）", required = true) String belongYear,
                       @RequestParam(value = "cityId", required = true, defaultValue = "")  @ApiParam(name = "cityId", value = "市id,（必传）", required = true) String cityId,
                       @RequestParam(value = "countyId", required = true, defaultValue = "")  @ApiParam(name = "countyId", value = "区县id,（必传）", required = true) String countyId){

        Map<String, Object> bprParams = new HashMap();
        bprParams.put("belongYear", belongYear);
        bprParams.put("cityId",cityId);
        bprParams.put("countyId",countyId);
        Map result = cityAuditService.view(bprParams);
        return Result.ok(result);
    }

    @SofnLog("市级上报管理展示")
    @ApiOperation(value = "市级上报管理展示")
//    @RequiresPermissions(value = "")
    @GetMapping("/reportManagement")
    public Result reportManagement(@RequestParam(value = "belongYear", required = false, defaultValue = "0")  @ApiParam(name = "belongYear", value = "所属年度,（非必传）", required = false) String belongYear,
                                   @RequestParam(value = "cityId", required = true, defaultValue = "")  @ApiParam(name = "cityId", value = "市id,（必传）", required = true) String cityId,
                                   @RequestParam(value = "roleCode", required = false, defaultValue = "")  @ApiParam(name = "roleCode", value = "角色code,（非必传）", required = false) String roleCode){
        Map<String, Object> params = new HashMap();
        params.put("belongYear", belongYear);
        params.put("cityId", cityId);
        params.put("roleCode", roleCode);
        List<CityReportManagementVo> list = cityAuditService.reportManagement(params);
        return Result.ok(list);
    }

    @SofnLog("获取驳回意见")
    @ApiOperation(value = "获取驳回意见")
//    @RequiresPermissions(value = "")
    @GetMapping("/getRemark")
    public Result getRemark(@RequestParam(value = "belongYear", required = true, defaultValue = "0")  @ApiParam(name = "belongYear", value = "所属年度,（必传）", required = true) String belongYear,
                            @RequestParam(value = "cityId", required = true, defaultValue = "")  @ApiParam(name = "cityId", value = "市id,（必传）", required = true) String cityId){
        Map<String, Object> params = new HashMap();
        params.put("belongYear", belongYear);
        params.put("cityId", cityId);

        CityRemarkVo cityRemarkVo = provinceAuditService.getRemark(params);
        return Result.ok(cityRemarkVo);
    }

    @SofnLog("市级审核通过")
    @ApiOperation(value = "市级审核通过")
//    @RequiresPermissions(value = "")
    @GetMapping("/cityAuditApprove")
    public Result cityAuditApprove(@RequestParam(value = "belongYear", required = true, defaultValue = "0")  @ApiParam(name = "belongYear", value = "所属年度,（必传）", required = true) String belongYear,
                                   @RequestParam(value = "countyId", required = true, defaultValue = "")  @ApiParam(name = "countyId", value = "区县id,（必传）", required = true) String countyId){
        Map<String, Object> params = new HashMap();
        params.put("belongYear", belongYear);
        params.put("countyId", countyId);
        String result = cityAuditService.cityAuditApprove(params);
        if ("1".equals(result)){
            return Result.ok("通过成功！");
        } else {
            return Result.error(result);
        }
    }

    @SofnLog("市级审核驳回")
    @ApiOperation(value = "市级审核驳回")
//    @RequiresPermissions(value = "")
    @GetMapping("/cityAuditReject")
    public Result cityAuditReject(@RequestParam(value = "belongYear", required = true, defaultValue = "0")  @ApiParam(name = "belongYear", value = "所属年度,（非必传）", required = true) String belongYear,
                                   @RequestParam(value = "countyId", required = true, defaultValue = "")  @ApiParam(name = "countyId", value = "区县id,（非必传）", required = true) String countyId){
        Map<String, Object> params = new HashMap();
        params.put("belongYear", belongYear);
        params.put("countyId",countyId);
        String result = cityAuditService.cityAuditReject(params);
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
    public Result editRemark(@Validated @RequestBody @ApiParam(name = "cityRemarkVo", value = "驳回意见,（必传）", required = true) CityRemarkVo cityRemarkVo){
        String result = cityAuditService.editRemark(cityRemarkVo);
        if ("1".equals(result)){
            return Result.ok("填写成功！");
        }else {
            return Result.error(result);
        }
    }

    @SofnLog("市级上报")
    @ApiOperation(value = "市级上报")
//    @RequiresPermissions(value = "")
    @GetMapping("/cityAuditReport")
    public Result cityAuditReport(@RequestParam(value = "belongYear", required = true, defaultValue = "0")  @ApiParam(name = "belongYear", value = "所属年度,（必传）", required = true) String belongYear,
                                   @RequestParam(value = "cityId", required = true, defaultValue = "")  @ApiParam(name = "cityId", value = "市id,（必传）", required = true) String cityId){
        Map<String, Object> params = new HashMap();
        params.put("belongYear", belongYear);
        params.put("cityId",cityId);
        String result = cityAuditService.cityAuditReport(params);
        if ("1".equals(result)){
            return Result.ok("上报成功！");
        } else {
            return Result.error(result);
        }
    }
}
