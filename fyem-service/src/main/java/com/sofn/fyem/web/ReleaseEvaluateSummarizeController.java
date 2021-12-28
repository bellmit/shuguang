package com.sofn.fyem.web;

import com.google.common.collect.Maps;
import com.sofn.common.exception.SofnException;
import com.sofn.common.log.SofnLog;
import com.sofn.common.model.Result;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.UserUtil;
import com.sofn.common.web.BaseController;
import com.sofn.fyem.excelmodel.BasicProliferationReleaseExcel;
import com.sofn.fyem.excelmodel.ReleaseEvaluateAndSummaryExcel;
import com.sofn.fyem.excelmodel.ReleaseSiteAndChoiceTimeExcel;
import com.sofn.fyem.model.BasicProliferationRelease;
import com.sofn.fyem.service.ReleaseEvaluateSummarizeService;
import com.sofn.fyem.util.ExportUtil;
import com.sofn.fyem.vo.BasicProliferationReleaseVO;
import com.sofn.fyem.vo.EvaluateStandardValueVo;
import com.sofn.fyem.vo.ReleaseEvaluateSummarizeVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 放流评价汇总-接口
 * @author Administrator
 */
@Api(value = "放流评价汇总-接口", tags = "放流评价汇总-接口")
@RestController
@RequestMapping("/releaseEvaluateSummarize")
public class ReleaseEvaluateSummarizeController extends BaseController {


    @Autowired
    private ReleaseEvaluateSummarizeService releaseEvaluateSummarizeService;

    @SofnLog("获取指标评价相关信息(分页)")
    @ApiOperation(value="获取指标评价相关信息(分页)")
    @GetMapping("/releaseEvaluateSummarizeListPage")
    public Result<PageUtils<BasicProliferationReleaseVO>> releaseEvaluateSummarizeListPage(
            @ApiParam(name = "pageNo",value = "索引(必传)")@RequestParam(value = "pageNo")Integer pageNo,
            @ApiParam(name = "pageSize",value = "条数(必传)")@RequestParam(value = "pageSize")Integer pageSize,
            @ApiParam(name = "belongYear",value = "belongYear(必传)")@RequestParam(value = "belongYear")String belongYear,
            @ApiParam(name = "provinceId",value = "省()")@RequestParam(value = "provinceId",required = false)String provinceId,
            @ApiParam(name = "cityId",value = "市()")@RequestParam(value = "cityId",required = false)String cityId,
            @ApiParam(name = "countyId",value = "区县()")@RequestParam(value = "countyId",required = false)String countyId,
            @ApiParam(name = "beginDate",value = "开始时间(非必传)")@RequestParam(value = "beginDate",required = false)String beginDate,
            @ApiParam(name = "endDate",value = "结束(非必传)")@RequestParam(value = "endDate",required = false)String endDate,
            HttpServletRequest request){
        //根据token获取登录用户Id
        String userId = UserUtil.getLoginUserId(request.getHeader("Authorization"));
        if(StringUtils.isBlank(userId)){
            throw new SofnException("当前登录用户异常");
        }

        Map<String, Object> params = Maps.newHashMap();
        List<String> roleColde = UserUtil.getLoginUserRoleCodeList();
        params.put("belongYear",belongYear);
        params.put("beginDate",beginDate);
        params.put("endDate",endDate);
        params.put("provinceId",provinceId);
        params.put("countyId",countyId);
        params.put("cityId",cityId);
        if(roleColde.contains("fyem_county")){
            params.put("createUserId",userId);
        }
        if(roleColde.contains("fyem_city_add") || roleColde.contains("fyem_city")){
            params.put("countyId",countyId);
        }
        if(roleColde.contains("fyem_prov_add") || roleColde.contains("fyem_prov")){
            params.put("cityId",cityId);
            params.put("countyId",countyId);
        }

        PageUtils<BasicProliferationReleaseVO> basicProliferationReleaseList = releaseEvaluateSummarizeService.getBasicProliferationReleaseListByPage(params, pageNo, pageSize);
        return Result.ok(basicProliferationReleaseList);
    }

    @SofnLog("获取指标评价相关信息(不分页)")
    @ApiOperation(value="获取指标评价相关信息(不分页)")
    @GetMapping("/releaseEvaluateSummarizeList")
    public Result<List<BasicProliferationReleaseVO>> releaseEvaluateSummarizeList(
            @ApiParam(name = "belongYear",value = "belongYear(必传)")@RequestParam(value = "belongYear")String belongYear,
            @ApiParam(name = "provinceId",value = "省()")@RequestParam(value = "provinceId",required = false)String provinceId,
            @ApiParam(name = "cityId",value = "市()")@RequestParam(value = "cityId",required = false)String cityId,
            @ApiParam(name = "countyId",value = "区县()")@RequestParam(value = "countyId",required = false)String countyId,
            @ApiParam(name = "beginDate",value = "开始时间(非必传)")@RequestParam(value = "beginDate",required = false) String beginDate,
            @ApiParam(name = "endDate",value = "结束(非必传)")@RequestParam(value = "endDate",required = false)String endDate,
            HttpServletRequest request){
        //根据token获取登录用户Id
        String userId = UserUtil.getLoginUserId(request.getHeader("Authorization"));
        if(StringUtils.isBlank(userId)){
            throw new SofnException("当前登录用户异常");
        }

        Map<String, Object> params = Maps.newHashMap();
        List<String> roleColde = UserUtil.getLoginUserRoleCodeList();
        params.put("belongYear",belongYear);
        params.put("beginDate",beginDate);
        params.put("endDate",endDate);
        params.put("provinceId",provinceId);
        params.put("countyId",countyId);
        params.put("cityId",cityId);
        if(roleColde.contains("fyem_county")){
            params.put("createUserId",userId);
        }
        if(roleColde.contains("fyem_city_add") || roleColde.contains("fyem_city")){
            params.put("countyId",countyId);
        }
        if(roleColde.contains("fyem_prov_add") || roleColde.contains("fyem_prov")){
            params.put("cityId",cityId);
            params.put("countyId",countyId);
        }
        List<BasicProliferationReleaseVO> basicProliferationReleaseList = releaseEvaluateSummarizeService.getBasicProliferationReleaseListByQuery(params);
        return Result.ok(basicProliferationReleaseList);
    }

    @SofnLog("指标评价汇总结构")
    @ApiOperation(value="指标评价汇总结构")
    @GetMapping("/getReleaseEvaluateSummarizeType")
    public Result<ReleaseEvaluateSummarizeVo> getReleaseEvaluateSummarizeType(
            @ApiParam(name = "id",value = "id(必传)")@RequestParam(value = "id")String id,
            HttpServletRequest request){

        ReleaseEvaluateSummarizeVo releaseEvaluateSummarizeVo =
                releaseEvaluateSummarizeService.getReleaseEvaluateSummarizeType(id);
        return Result.ok(releaseEvaluateSummarizeVo);
    }

    @SofnLog("获取指标评价汇总分数")
    @ApiOperation(value="获取指标评价汇总分数")
    @GetMapping("/getReleaseEvaluateSummarize")
    public Result<ReleaseEvaluateSummarizeVo> getReleaseEvaluateSummarize(
            @ApiParam(name = "id",value = "id(必传)")@RequestParam(value = "id")String id,
            @ApiParam(name = "belongYear",value = "belongYear(必传)")@RequestParam(value = "belongYear")String belongYear,
            HttpServletRequest request){

        ReleaseEvaluateSummarizeVo releaseEvaluateSummarizeVo =
                releaseEvaluateSummarizeService.getReleaseEvaluateSummarizeVo(id,belongYear);
        return Result.ok(releaseEvaluateSummarizeVo);
    }

    @SofnLog("获取评价分数详情")
    @ApiOperation(value="获取评价分数详情")
    @GetMapping("/getReleaseEvaluateSummarizeHistory")
    public Result<ReleaseEvaluateSummarizeVo> getReleaseEvaluateSummarizeHistory(
            @ApiParam(name = "id",value = "id(必传)")@RequestParam(value = "id")String id,
            @ApiParam(name = "belongYear",value = "belongYear(必传)")@RequestParam(value = "belongYear")String belongYear,
            HttpServletRequest request){

        ReleaseEvaluateSummarizeVo releaseEvaluateSummarizeVo =
                releaseEvaluateSummarizeService.getReleaseEvaluateSummarizeHistory(id,belongYear);
        return Result.ok(releaseEvaluateSummarizeVo);
    }

    /*@SofnLog("新增指标评价汇总分数")
    @ApiOperation(value="新增指标评价汇总分数")
    @PostMapping("/addReleaseEvaluateSummarize")
    public Result<String> addReleaseEvaluateSummarize(
            @RequestBody @ApiParam(name = "评价指标分数信息", value = "传入json格式", required = false) EvaluateStandardValueVo evaluateStandardValueVo,
            HttpServletRequest request){

        releaseEvaluateSummarizeService.addReleaseEvaluateSummarizeVo(evaluateStandardValueVo);
        return Result.ok("新增评价汇总分数成功!");
    }*/

    @SofnLog("评价指标评价汇总分数")
    @ApiOperation(value="评价指标评价汇总分数")
    @PostMapping("/updateReleaseEvaluateSummarize")
    public Result<String> updateReleaseEvaluateSummarize(
            @RequestBody @ApiParam(name = "评价指标分数信息", value = "传入json格式", required = false) EvaluateStandardValueVo evaluateStandardValueVo,
            HttpServletRequest request){

        releaseEvaluateSummarizeService.updateReleaseEvaluateSummarizeVo(evaluateStandardValueVo);
        return Result.ok("修改评价汇总分数成功");
    }


    @SofnLog("导出指标评价信息")
    @ApiOperation(value="导出指标评价信息")
    @GetMapping(value = "/exportReleaseEvaluateSummarize", produces = "application/octet-stream")
    public void exportReleaseEvaluateSummarize(
            @ApiParam(name = "belongYear",value = "belongYear(必传)")@RequestParam(value = "belongYear")String belongYear,
            @ApiParam(name = "provinceId",value = "省()")@RequestParam(value = "provinceId",required = false)String provinceId,
            @ApiParam(name = "cityId",value = "市()")@RequestParam(value = "cityId",required = false)String cityId,
            @ApiParam(name = "countyId",value = "区县()")@RequestParam(value = "countyId",required = false)String countyId,
            @ApiParam(name = "beginDate",value = "开始时间(非必传)")@RequestParam(value = "beginDate",required = false) String beginDate,
            @ApiParam(name = "endDate",value = "结束(非必传)")@RequestParam(value = "endDate",required = false)String endDate,
            HttpServletRequest request, HttpServletResponse response){
        //根据token获取登录用户Id
        String userId = UserUtil.getLoginUserId(request.getHeader("Authorization"));
        if(StringUtils.isBlank(userId)){
            throw new SofnException("当前登录用户异常");
        }

        Map<String, Object> params = Maps.newHashMap();
        List<String> roleColde = UserUtil.getLoginUserRoleCodeList();
        params.put("belongYear",belongYear);
        params.put("beginDate",beginDate);
        params.put("endDate",endDate);
        params.put("provinceId",provinceId);
        params.put("countyId",countyId);
        params.put("cityId",cityId);
        if(roleColde.contains("fyem_county")){
            params.put("createUserId",userId);
        }
        if(roleColde.contains("fyem_city_add") || roleColde.contains("fyem_city")){
            params.put("countyId",countyId);
        }
        if(roleColde.contains("fyem_prov_add") || roleColde.contains("fyem_prov")){
            params.put("cityId",cityId);
            params.put("countyId",countyId);
        }
        List<BasicProliferationReleaseExcel> basicProliferationReleaseExcel = releaseEvaluateSummarizeService.getBasicProliferationReleaseExcel(params);
        List<ReleaseEvaluateAndSummaryExcel> releaseEvaluateAndSummaryExcels = new ArrayList<>();
        basicProliferationReleaseExcel.forEach(x->{
            ReleaseEvaluateAndSummaryExcel releaseEvaluateAndSummaryExcel = new ReleaseEvaluateAndSummaryExcel();
            BeanUtils.copyProperties(x,releaseEvaluateAndSummaryExcel);
            releaseEvaluateAndSummaryExcels.add(releaseEvaluateAndSummaryExcel);
        });
        ExportUtil.createExcel(ReleaseEvaluateAndSummaryExcel.class,releaseEvaluateAndSummaryExcels,response,
                "放流评价汇总表.xlsx");
    }
}
