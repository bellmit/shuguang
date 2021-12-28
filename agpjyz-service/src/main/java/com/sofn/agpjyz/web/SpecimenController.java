package com.sofn.agpjyz.web;


import com.sofn.agpjyz.service.SpecimenService;

import com.sofn.agpjyz.vo.*;
import com.sofn.common.model.Result;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-03-11 14:59
 */
@Slf4j
@Api(value = "标本采集接口",tags = "标本采集接口")
@RestController
@RequestMapping("/specimen")
public class SpecimenController extends BaseController {
    @Autowired
    private SpecimenService specimenService;
    @RequiresPermissions("agpjyz:specimen:create")
    @ApiOperation(value = "新增")
    @PostMapping("/save")
    public Result<SpecimenVo> save(
            @Validated @RequestBody SpecimenForm specimenForm, BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        return Result.ok(specimenService.save(specimenForm));
    }
    @RequiresPermissions("agpjyz:specimen:delete")
    @ApiOperation(value = "删除")
    @DeleteMapping("/delete")
    public Result delete(
            @ApiParam(value = "主键id", required = true) @RequestParam("id") String id) {
        return Result.ok(specimenService.del(id));
    }
    @RequiresPermissions("agpjyz:specimen:view")
    @ApiOperation(value = "详情")
    @GetMapping("/get")
    public Result<SourceVo> get(
            @ApiParam(value = "主键id", required = true) @RequestParam("id") String id) {
        return Result.ok(specimenService.getOne(id));
    }

    @RequiresPermissions("agpjyz:specimen:update")
    @ApiOperation(value = "修改")
    @PutMapping("/update")
    public Result update(
            @Validated @RequestBody SpecimenForm specimenForm, BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        return Result.ok(specimenService.update(specimenForm));
    }

    @ApiOperation(value = "上报提交(列表页使用)")
    @PutMapping("/report")
    @RequiresPermissions("agpjyz:specimen:update")
    public Result report(@ApiParam(value = "主键id", required = true) @RequestParam() String id) {
        specimenService.report(id);
        return Result.ok();
    }

    @RequiresPermissions("agpjyz:specimen:export")
   @GetMapping(value = "/export", produces = "application/octet-stream")
    @ApiOperation(value = "导出", produces = "application/octet-stream")
   public void export(
           @ApiParam("中文名id") @RequestParam(required = false) String chineseId,
           @ApiParam("采集人") @RequestParam(required = false) String collectioner,
           @ApiParam("采集号 ") @RequestParam(required = false) String collectionNumber,
             @ApiParam("省") @RequestParam(required = false) String province,
            @ApiParam("市") @RequestParam(required = false) String city,
            @ApiParam("县") @RequestParam(required = false) String county,
           @ApiParam("开始时间(yyyy-MM-dd)") @RequestParam(required = false) String startTime,
          @ApiParam("结束时间(yyyy-MM-dd)") @RequestParam(required = false) String endTime,
          @ApiParam("状态 0已保存 1已撤回 2已上报 3市级退回 4市级通过 5省级退回 6省级通过 7总站退回 8总站通过")
            @RequestParam(required = false) String status, HttpServletResponse response) {
        specimenService.exportByTemplate(getQueryMap(chineseId, province, city, county, collectioner,
              startTime, endTime, status,collectionNumber), response);
   }
    private Map<String, Object> getQueryMap(String chineseId, String province, String city, String county,
                                            String collectioner, String startTime, String endTime, String status,String collectionNumber) {
        Map map = new HashMap<String, Object>(7);
        map.put("chineseId", chineseId);
        map.put("province", province);
        map.put("city", city);
        map.put("county", county);
        map.put("collectioner", collectioner);
        map.put("startTime", startTime);
        map.put("collectionNumber",collectionNumber);
        if (StringUtils.hasText(endTime)) {
            map.put("endTime", endTime + " 23:59:59");
        }
        map.put("status", status);
        return map;
    }


//    @RequiresPermissions("agpjyz:specimen:menu")
    @ApiOperation(value = "分页查询")
    @GetMapping("/listPage")
    public Result<PageUtils<SpecimenVo>> listPage(
            @ApiParam("中文名id）") @RequestParam(required = false) String chineseId,
            @ApiParam("采集人") @RequestParam(required = false) String collectioner,
            @ApiParam("采集号 ") @RequestParam(required = false) String collectionNumber,
            @ApiParam("省") @RequestParam(required = false) String province,
            @ApiParam("市") @RequestParam(required = false) String city,
            @ApiParam("县") @RequestParam(required = false) String county,
            @ApiParam("开始时间(yyyy-MM-dd)") @RequestParam(required = false) String startTime,
            @ApiParam("结束时间(yyyy-MM-dd)") @RequestParam(required = false) String endTime,
            @ApiParam("状态 0已保存 1已撤回 2已上报 3市级退回 4市级通过 5省级退回 6省级通过 7总站退回 8总站通过")
            @RequestParam(required = false) String status,
            @ApiParam(value = "pageNo", required = true) @RequestParam() Integer pageNo,
            @ApiParam(value = "pageSize", required = true) @RequestParam() Integer pageSize) {

        return Result.ok(specimenService.listPage(getQueryMap(chineseId, province, city, county, collectioner,
                startTime, endTime, status,collectionNumber), pageNo, pageSize));
    }
    @RequiresPermissions("agpjyz:specimen:audit")
    @ApiOperation(value = "批量审核(通过)")
    @PutMapping("/auditPass")
    public Result auditPass(
            @Validated @RequestBody AuditForm auditForm, BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        specimenService.auditPass(auditForm.getId(), auditForm.getAuditOpinion());
        return Result.ok();
    }
    @RequiresPermissions("agpjyz:specimen:audit")
    @ApiOperation(value = "批量审核(退回)")
    @PutMapping("/auditReturn")
    public Result auditReturn(
             @Validated @RequestBody AuditForm auditForm, BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        specimenService.auditReturn(auditForm.getId(), auditForm.getAuditOpinion());
        return Result.ok();
    }
    @RequiresPermissions("agpjyz:specimen:cancel")
    @ApiOperation(value = "撤回")
    @PutMapping("/cancel")
    public Result cancel(
            @ApiParam(value = "主键id", required = true) @RequestParam() String id) {
        specimenService.cancel(id);
        return Result.ok();
    }


    @ApiOperation(value = "上次提交")
    @PutMapping("/getLastCommit")
    public Result<SpecimenLastVo> getLastCommit() {

        return Result.ok(specimenService.getLast());
    }

}
