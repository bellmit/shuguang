package com.sofn.agsjsi.web;

import com.sofn.agsjsi.service.WetExamplePointCollectService;
import com.sofn.agsjsi.vo.WetExamplePointCollectForm;
import com.sofn.agsjsi.vo.WetExamplePointCollectVo;
import com.sofn.common.log.SofnLog;
import com.sofn.common.model.Result;
import com.sofn.common.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Description: 农业湿地示范点收集
 * @Author: WXY
 * @Date: 2020-4-10 15:29:03
 */
@Api(value = "农业湿地示范点收集相关接口", tags = "农业湿地示范点收集相关接口")
@Slf4j
@RestController
@RequestMapping("/pointCollect")
public class WetExamplePointCollectController extends BaseController {
    @Autowired
    private WetExamplePointCollectService pointCollectService;

    @ApiOperation(value = "根据条件获取列表（分页）")
    @RequiresPermissions(value={"agsjsi:pointCollect:query"})
    @GetMapping("/listPage")
    public Result<List<WetExamplePointCollectVo>> listPage(@RequestParam(value = "wetName", required = false) @ApiParam(name = "wetName", value = "湿地区名称") String wetName
            , @RequestParam(value = "wetCode", required = false) @ApiParam(name = "wetCode", value = "湿地区编码") String wetCode
            , @RequestParam(value = "secondBasin", required = false) @ApiParam(name = "secondBasin", value = "所属二级流域") String secondBasin
            , @RequestParam(value = "startTime", required = false) @ApiParam(name = "startTime", value = "调查开始时间") String startTime
            , @RequestParam(value = "endTime", required = false) @ApiParam(name = "endTime", value = "调查结束时间") String endTime
            , @RequestParam(value = "pageNo", required = false, defaultValue = "0") @ApiParam(name = "pageNo", value = "分页页数") Integer pageNo
            , @RequestParam(value = "pageSize", required = false, defaultValue = "20") @ApiParam(name = "pageSize", value = "每页条数") Integer pageSize) {

        return Result.ok(pointCollectService.listPage(wetName,wetCode,secondBasin,"",startTime,endTime, pageNo, pageSize,"0"));
    }

    @ApiOperation(value = "根据id，获取农业湿地示范点收集数据")
    @GetMapping("/get")
    public Result<WetExamplePointCollectVo> get(@RequestParam(value = "id") @ApiParam(name = "id", value = "农业湿地示范点收集主键", required = true) String id) {

        return Result.ok(pointCollectService.getPointCollect(id,false));
    }
    @ApiOperation(value = "《详情》")
    @RequiresPermissions("agsjsi:pointCollect:view")
    @GetMapping("/view")
    public Result<WetExamplePointCollectVo> view(@RequestParam(value = "id") @ApiParam(name = "id", value = "农业湿地示范点收集主键", required = true) String id) {

        return Result.ok(pointCollectService.getPointCollect(id,true));
    }

    @ApiOperation(value = "《新增保存》,保存成功后返回物种的Id")
    @RequiresPermissions("agsjsi:pointCollect:create")
    @PostMapping("/save")
    public Result save(@Validated @RequestBody @ApiParam(name = "pointCollectForm", value = "农业湿地示范点收集表单json对象", required = true) WetExamplePointCollectForm pointCollectForm
            , BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        String isSuccess = pointCollectService.saveAndReport(pointCollectForm,false);
        if(!"1".equals(isSuccess)){
            return Result.error(isSuccess);
        }


        return Result.ok("新增成功！");
    }
    @ApiOperation(value = "《新增提交》,保存成功后返回物种的Id")
    @RequiresPermissions("agsjsi:pointCollect:report")
    @PostMapping("/saveAndReport")
    public Result saveAndReport(@Validated @RequestBody @ApiParam(name = "pointCollectForm", value = "农业湿地示范点收集表单json对象", required = true) WetExamplePointCollectForm pointCollectForm
            , BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        String isSuccess = pointCollectService.saveAndReport(pointCollectForm,true);
        if(!"1".equals(isSuccess)){
            return Result.error(isSuccess);
        }


        return Result.ok("提交成功！");
    }

    @ApiOperation(value = "编辑页面中《修改》")
    @RequiresPermissions("agsjsi:pointCollect:update")
    @PutMapping("/update")
    public Result update(@Validated @RequestBody @ApiParam(name = "pointCollectForm", value = "农业湿地示范点收集表单json对象", required = true) WetExamplePointCollectForm pointCollectForm
            , BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        String success = pointCollectService.updateAndReport(pointCollectForm,false);
        if(!"1".equals(success)){
            return Result.error(success);
        }
        return Result.ok("修改成功！");
    }

    @ApiOperation(value = "编辑页面中《提交》")
    @RequiresPermissions("agsjsi:pointCollect:report")
    @PutMapping("/updateAndReport")
    public Result updateAndReport(@Validated @RequestBody @ApiParam(name = "pointCollectForm", value = "农业湿地示范点收集表单json对象", required = true) WetExamplePointCollectForm pointCollectForm
            , BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        String success = pointCollectService.updateAndReport(pointCollectForm,true);
        if(!"1".equals(success)){
            return Result.error(success);
        }
        return Result.ok("提交成功！");
    }

    @ApiOperation(value = "《删除》")
    @RequiresPermissions("agsjsi:pointCollect:delete")
    @DeleteMapping("/delete")
    public Result delete( @RequestParam(value="id") @ApiParam(name = "id", value = "主键id", required = true)String id) {

        String success = pointCollectService.delObj(id);
        if(!"1".equals(success)){
            return Result.error(success);
        }
        return Result.ok("删除成功！");
    }

    @SofnLog("《导出》农业湿地示范点收集")
    @ApiOperation(value = "导出农业湿地示范点收集", produces = "application/octet-stream")
    @RequiresPermissions("agsjsi:pointCollect:export")
    @GetMapping("/export")
    public Result export(@RequestParam(value = "wetName", required = false) @ApiParam(name = "wetName", value = "湿地区名称") String wetName
            , @RequestParam(value = "wetCode", required = false) @ApiParam(name = "wetCode", value = "湿地区编码") String wetCode
            , @RequestParam(value = "secondBasin", required = false) @ApiParam(name = "secondBasin", value = "所属二级流域") String secondBasin
            , @RequestParam(value = "startTime", required = false) @ApiParam(name = "startTime", value = "调查开始时间") String startTime
            , @RequestParam(value = "endTime", required = false) @ApiParam(name = "endTime", value = "调查结束时间") String endTime
            , HttpServletResponse response) {

        pointCollectService.export(wetName,wetCode,secondBasin,"",startTime,endTime,"0", response);
        return Result.ok("导出成功！");
    }
}
