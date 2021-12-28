package com.sofn.agpjpm.web;

import com.google.common.collect.Maps;
import com.sofn.agpjpm.service.SurveyService;
import com.sofn.agpjpm.util.FileUtil;
import com.sofn.agpjpm.vo.MonitorVo;
import com.sofn.agpjpm.vo.ServeyListVo;
import com.sofn.agpjpm.vo.SurveyForm;
import com.sofn.agpjpm.vo.SurveyVo;
import com.sofn.common.model.Result;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.web.BaseController;
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

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-06-10 10:34
 */
@Api(tags = "植物调查模块接口")
@RestController
@RequestMapping("/sc")
public class SurveyController extends BaseController {
    @Autowired
    private SurveyService surveyService;

    @RequiresPermissions(value = {"agpjpm:survey:create","agpjyz:survey:create"},logical = Logical.OR)
    @ApiOperation(value = "新增")
    @PostMapping("/save")
    public Result save(@Validated @RequestBody SurveyForm surveyForm, BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        surveyService.save(surveyForm);
        return Result.ok();
    }
    @RequiresPermissions(value = {"agpjpm:survey:view","agpjyz:survey:view"},logical = Logical.OR)
    @ApiOperation(value = "详情")
    @GetMapping("/get")
    public Result<SurveyVo> get(
            @ApiParam(value = "主键id", required = true) @RequestParam() String id) {
        return Result.ok(surveyService.get(id));
    }
    @RequiresPermissions(value = {"agpjpm:survey:update","agpjyz:survey:update"},logical = Logical.OR)
    @ApiOperation(value = "修改")
    @PutMapping("/update")
    public Result update(
            @Validated @RequestBody SurveyForm surveyForm, BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        surveyService.update(surveyForm);
        return Result.ok();
    }
    @RequiresPermissions(value = {"agpjpm:survey:delete","agpjyz:survey:delete"},logical = Logical.OR)
    @ApiOperation(value = "删除")
    @DeleteMapping(value = "/delete")
    public Result delete(@ApiParam(value = "主键", required = true) @RequestParam(value = "id",required = true) String id){
        surveyService.del(id);
        return Result.ok();
    }

    @ApiOperation(value = "分页查询")
    @GetMapping("/listPage")
    public Result<PageUtils<ServeyListVo>> listPage(
            @ApiParam("省") @RequestParam(required = false) String province,
            @ApiParam("市") @RequestParam(required = false) String city,
            @ApiParam("县") @RequestParam(required = false) String county,
            @ApiParam("生态类型id") @RequestParam(required = false) String habitatId,
            @ApiParam("土壤类型id") @RequestParam(required = false) String soilId,
            @ApiParam("地型id") @RequestParam(required = false) String landformId,
            @ApiParam("气候类型id") @RequestParam(required = false) String climaticId,
            @ApiParam("调查人") @RequestParam(required = false) String surveyor,
            @ApiParam("开始时间(yyyy-MM-dd)") @RequestParam(required = false) String startTime,
            @ApiParam("结束时间(yyyy-MM-dd)") @RequestParam(required = false) String endTime,
            @ApiParam(value = "pageNo", required = true) @RequestParam() Integer pageNo,
            @ApiParam(value = "pageSize", required = true) @RequestParam() Integer pageSize) {

        return Result.ok(surveyService.listPage(getQueryMap(province, city, county, surveyor,
                startTime, endTime,habitatId,soilId,landformId,climaticId), pageNo, pageSize));
    }

    @GetMapping(value = "/export", produces = "application/octet-stream")
    @ApiOperation(value = "导出", produces = "application/octet-stream")
    @RequiresPermissions(value = {"agpjpm:survey:export","agpjyz:survey:export"},logical = Logical.OR)
    public void export(
            @ApiParam("省") @RequestParam(required = false) String province,
            @ApiParam("市") @RequestParam(required = false) String city,
            @ApiParam("县") @RequestParam(required = false) String county,
            @ApiParam("生态类型id") @RequestParam(required = false) String habitatId,
            @ApiParam("土壤类型id") @RequestParam(required = false) String soilId,
            @ApiParam("地型id") @RequestParam(required = false) String landformId,
            @ApiParam("气候类型id") @RequestParam(required = false) String climaticId,
            @ApiParam("调查人") @RequestParam(required = false) String surveyor,
            @ApiParam("开始时间(yyyy-MM-dd)") @RequestParam(required = false) String startTime,
            @ApiParam("结束时间(yyyy-MM-dd)") @RequestParam(required = false) String endTime,
            HttpServletResponse response) {
        surveyService.exportByTemplate(getQueryMap(province, city, county, surveyor,
                startTime, endTime,habitatId,soilId,landformId,climaticId), response);
    }
        /**
         * 封装查询参数
         */
        private Map<String, Object> getQueryMap(String province, String city, String county,
                String surveyor, String startTime, String endTime,String habitatId,String soilId,String landformId,String climaticId) {
            Map map = Maps.newHashMap();
            map.put("province", province);
            map.put("city", city);
            map.put("county", county);
            map.put("surveyor", surveyor);
            map.put("startTime", startTime);
            map.put("habitatId", habitatId);
            map.put("soilId", soilId);
            map.put("landformId", landformId);
            map.put("climaticId", climaticId);
            if (StringUtils.hasText(endTime)) {
                map.put("endTime", endTime + " 23:59:59");
            }
            return map;
        }



    }

