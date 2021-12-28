package com.sofn.fyem.web;

import com.sofn.common.log.SofnLog;
import com.sofn.common.model.Result;
import com.sofn.common.utils.PageUtils;
import com.sofn.fyem.model.MinisterAudit;
import com.sofn.fyem.service.MinisterAuditService;
import com.sofn.fyem.vo.MinisterAuditVo;
import com.sofn.fyem.vo.MinisterRemarkVo;
import com.sofn.fyem.vo.ProvinceAuditVo;
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
 * @Date: 2020/4/27 15:05
 */
//@Slf4j
//@Api(value = "部级审核接口", tags = "部级审核接口")
//@RestController
//@RequestMapping(value = "/ministerAudit")
@Deprecated
public class MinisterAuditController {

    @Autowired
    private MinisterAuditService ministerAuditService;

    @SofnLog("部级审核数据展示")
    @ApiOperation(value = "部级审核数据展示")
//    @RequiresPermissions(value = "")
    @GetMapping("/listMinisterAuditsByBelongYear")
    public Result<List<MinisterAuditVo>> listMinisterAuditsByBelongYear(@RequestParam(value = "belongYear", required = false, defaultValue = "0") @ApiParam(name = "belongYear", value = "所属年度,（非必传）", required = false) String belongYear,
                                                                        @RequestParam(value = "orgName", required = false, defaultValue = "") @ApiParam(name = "orgName", value = "上报单位,（非必传）", required = false) String orgName){
        Map<String, Object> params = new HashMap();
        params.put("belongYear", belongYear);
        params.put("orgName",orgName);
        List<MinisterAuditVo> list = ministerAuditService.listMinisterAuditsByBelongYear(params);
        return Result.ok(list);
    }

    @SofnLog("部级审核数据展示（分页）")
    @ApiOperation(value = "部级审核数据展示（分页）")
//    @RequiresPermissions(value = "")
    @GetMapping("/getMinisterAuditsListByPage")
    public Result<List<MinisterAuditVo>> getMinisterAuditsListByPage(@RequestParam(value = "belongYear", required = false, defaultValue = "0") @ApiParam(name = "belongYear", value = "所属年度,（非必传）", required = false) String belongYear,
                                                                     @RequestParam(value = "orgName", required = false, defaultValue = "") @ApiParam(name = "orgName", value = "上报单位,（非必传）", required = false) String orgName,
                                                                     @RequestParam(value = "pageNo", required = true, defaultValue = "0") @ApiParam(name = "pageNo", value = "起始位置,（必传）", required = true) int pageNo,
                                                                     @RequestParam(value = "pageSize", required = true, defaultValue = "0") @ApiParam(name = "pageSize", value = "分页大小,（必传）", required = true) int pageSize){
        Map<String, Object> params = new HashMap();
        params.put("belongYear", belongYear);
        params.put("orgName",orgName);
        PageUtils<MinisterAuditVo> list = ministerAuditService.getMinisterAuditsListByPage(params, pageNo, pageSize);
        return Result.ok(list);
    }

    @SofnLog("部级审核数据新增")
    @ApiOperation(value = "部级审核数据新增")
//    @RequiresPermissions(value = "")
    @PostMapping("/insert")
    public Result insert(@Validated @RequestBody @ApiParam(name = "ministerAudit", value = "部级审核对象,（必传）", required = true) MinisterAudit ministerAudit){
        String result = ministerAuditService.insert(ministerAudit);
        if ("1".equals(result)){
            return Result.ok("新增成功！");
        }else {
            return Result.error(result);
        }
    }

    @SofnLog("部级审核数据查看")
    @ApiOperation(value = "部级审核数据查看")
//    @RequiresPermissions(value = "")
    @GetMapping("/view")
    public Result view(@RequestParam(value = "belongYear", required = true, defaultValue = "0")  @ApiParam(name = "belongYear", value = "所属年度,（必传）", required = true) String belongYear,
                       @RequestParam(value = "provinceId", required = true, defaultValue = "")  @ApiParam(name = "provinceId", value = "省id,（必传）", required = true) String provinceId,
                       @RequestParam(value = "roleCode", required = false, defaultValue = "")  @ApiParam(name = "roleCode", value = "角色code,（非必传）", required = false) String roleCode){

        Map<String, Object> params = new HashMap();
        params.put("belongYear", belongYear);
        params.put("provinceId",provinceId);
        params.put("roleCode",roleCode);
        List<ProvinceAuditVo> list = ministerAuditService.view(params);
        return Result.ok(list);
    }

    @SofnLog("部级审核通过")
    @ApiOperation(value = "部级审核通过")
//    @RequiresPermissions(value = "")
    @GetMapping("/ministerAuditApprove")
    public Result ministerAuditApprove(@RequestParam(value = "belongYear", required = true, defaultValue = "0")  @ApiParam(name = "belongYear", value = "所属年度,（必传）", required = true) String belongYear,
                                       @RequestParam(value = "provinceId", required = true, defaultValue = "")  @ApiParam(name = "provinceId", value = "省id,（必传）", required = true) String provinceId){
        Map<String, Object> params = new HashMap();
        params.put("belongYear", belongYear);
        params.put("provinceId", provinceId);
        String result = ministerAuditService.ministerAuditApprove(params);
        if ("1".equals(result)){
            return Result.ok("通过成功！");
        } else {
            return Result.error(result);
        }
    }

    @SofnLog("部级审核驳回")
    @ApiOperation(value = "部级审核驳回")
//    @RequiresPermissions(value = "")
    @GetMapping("/ministerAuditReject")
    public Result ministerAuditReject(@RequestParam(value = "belongYear", required = true, defaultValue = "0")  @ApiParam(name = "belongYear", value = "所属年度,（必传）", required = true) String belongYear,
                                      @RequestParam(value = "provinceId", required = true, defaultValue = "")  @ApiParam(name = "provinceId", value = "省id,（必传）", required = true) String provinceId){
        Map<String, Object> params = new HashMap();
        params.put("belongYear", belongYear);
        params.put("provinceId",provinceId);
        String result = ministerAuditService.ministerAuditReject(params);
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
    public Result editRemark(@Validated @RequestBody @ApiParam(name = "ministerRemarkVo", value = "驳回意见,（必传）", required = true) MinisterRemarkVo ministerRemarkVo){
        String result = ministerAuditService.editRemark(ministerRemarkVo);
        if ("1".equals(result)){
            return Result.ok("填写成功！");
        }else {
            return Result.error(result);
        }
    }

}
