package com.sofn.agsjsi.web;

import com.sofn.agsjsi.enums.ProcessStatusEnum;
import com.sofn.agsjsi.service.WetExamplePointCollectService;
import com.sofn.agsjsi.vo.DropDownVo;
import com.sofn.agsjsi.vo.ProcessForm;
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
 * @Description: 农业湿地示范点收集审核管理
 * @Author: WXY
 * @Date: 2020-4-10 15:29:03
 */
@Api(value = "农业湿地示范点收集审核管理相关接口", tags = "农业湿地示范点收集审核管理相关接口")
@Slf4j
@RestController
@RequestMapping("/pointCollectApprove")
public class WetExamplePointCollectApproveController extends BaseController {
    @Autowired
    private WetExamplePointCollectService pointCollectService;

    @ApiOperation(value = "根据条件获取列表（分页）")
    @RequiresPermissions(value={"agsjsi:pointCollectApprove:query"})
    @GetMapping("/listPage")
    public Result<List<WetExamplePointCollectVo>> listPage(@RequestParam(value = "wetName", required = false) @ApiParam(name = "wetName", value = "湿地区名称") String wetName
            , @RequestParam(value = "wetCode", required = false) @ApiParam(name = "wetCode", value = "湿地区编码") String wetCode
            , @RequestParam(value = "secondBasin", required = false) @ApiParam(name = "secondBasin", value = "所属二级流域") String secondBasin
            , @RequestParam(value = "status", required = false) @ApiParam(name = "status", value = "状态：0：已保存；1：已撤回；2：已上报；3：市级退回；4：市级通过；5：省级退回；6：省级通过；7：总站退回；8：总站通过;9：专家批复") String status
            , @RequestParam(value = "startTime", required = false) @ApiParam(name = "startTime", value = "调查开始时间") String startTime
            , @RequestParam(value = "endTime", required = false) @ApiParam(name = "endTime", value = "调查结束时间") String endTime
            , @RequestParam(value = "pageNo", required = false, defaultValue = "0") @ApiParam(name = "pageNo", value = "分页页数") Integer pageNo
            , @RequestParam(value = "pageSize", required = false, defaultValue = "20") @ApiParam(name = "pageSize", value = "每页条数") Integer pageSize) {

        return Result.ok(pointCollectService.listPage(wetName,wetCode,secondBasin,status,startTime,endTime, pageNo, pageSize,"1"));
    }

    @ApiOperation(value = "《详情》")
    @RequiresPermissions("agsjsi:pointCollectApprove:view")
    @GetMapping("/view")
    public Result<WetExamplePointCollectVo> view(@RequestParam(value = "id") @ApiParam(name = "id", value = "农业湿地示范点收集主键", required = true) String id) {

        return Result.ok(pointCollectService.getPointCollect(id,true));
    }

//    @ApiOperation(value = "列表中《上报》")
//    @RequiresPermissions("agsjsi:pointCollectApprove:report")
//    @PutMapping("/report")
//    public Result report(@RequestParam(value = "id") @ApiParam(name = "id", value = "Id主键", required = true) String id) {
//        String success = pointCollectService.updateStatus(id,"1");
//        if(!"1".equals(success)){
//            return Result.error(success);
//        }
//        return Result.ok("上报成功！");
//    }

    @ApiOperation(value = "《撤回》")
    @RequiresPermissions("agsjsi:pointCollectApprove:revoke")
    @PutMapping("/revoke")
    public Result revoke(@RequestParam(value = "id") @ApiParam(name = "id", value = "农业湿地示范点收集表中的Id主键", required = true) String id) {
        String success = pointCollectService.updateStatus(id,"2");
        if(!"1".equals(success)){
            return Result.error(success);
        }
        return Result.ok("撤回成功！");
    }

    @ApiOperation(value = "《审核》")
    @RequiresPermissions("agsjsi:pointCollectApprove:approve")
    @PutMapping("/approve")
    public Result approve(@Validated @RequestBody @ApiParam(name="processForm",value = "审核意见表单json",required = true) ProcessForm processForm
            ,BindingResult result) {

        if(result.hasErrors()){
            return Result.error(getErrMsg(result));
        }

        String success = pointCollectService.approveOrReturn(processForm.getId(),processForm.getAdvice(),true);
        if(!"1".equals(success)){
            return Result.error(success);
        }
        return Result.ok("审核成功！");
    }
    @ApiOperation(value = "《批复》")
    @RequiresPermissions("agsjsi:pointCollectApprove:reply")
    @PutMapping("/reply")
    public Result reply(@Validated @RequestBody @ApiParam(name="processForm",value = "批复意见表单json",required = true) ProcessForm processForm
            ,BindingResult result) {

        if(result.hasErrors()){
            return Result.error(getErrMsg(result));
        }

        String success = pointCollectService.approveOrReturn(processForm.getId(),processForm.getAdvice(),true);
        if(!"1".equals(success)){
            return Result.error(success);
        }
        return Result.ok("批复成功！");
    }

    @ApiOperation(value = "《退回》")
    @RequiresPermissions("agsjsi:pointCollectApprove:back")
    @PutMapping("/back")
    public Result back(@Validated @RequestBody @ApiParam(name="processForm",value = "审核意见表单json",required = true) ProcessForm processForm
            ,BindingResult result) {

        if(result.hasErrors()){
            return Result.error(getErrMsg(result));
        }

        String success = pointCollectService.approveOrReturn(processForm.getId(),processForm.getAdvice(),false);
        if(!"1".equals(success)){
            return Result.error(success);
        }
        return Result.ok("退回成功！");
    }

    @SofnLog("《导出》农业湿地示范点收集审核管理")
    @ApiOperation(value = "导出农业湿地示范点收集审核管理", produces = "application/octet-stream")
    @RequiresPermissions("agsjsi:pointCollectApprove:export")
    @GetMapping("/export")
    public Result export(@RequestParam(value = "wetName", required = false) @ApiParam(name = "wetName", value = "湿地区名称") String wetName
            , @RequestParam(value = "wetCode", required = false) @ApiParam(name = "wetCode", value = "湿地区编码") String wetCode
            , @RequestParam(value = "secondBasin", required = false) @ApiParam(name = "secondBasin", value = "所属二级流域") String secondBasin
            , @RequestParam(value = "status", required = false) @ApiParam(name = "status", value = "状态：0：已保存；1：已撤回；2：已上报；3：市级退回；4：市级通过；5：省级退回；6：省级通过；7：总站退回；8：总站通过;9：专家批复") String status
            , @RequestParam(value = "startTime", required = false) @ApiParam(name = "startTime", value = "调查开始时间") String startTime
            , @RequestParam(value = "endTime", required = false) @ApiParam(name = "endTime", value = "调查结束时间") String endTime
            , HttpServletResponse response) {

        pointCollectService.export(wetName,wetCode,secondBasin,status,startTime,endTime,"1", response);
        return Result.ok("导出成功！");
    }

    @ApiOperation(value = "获取状态下拉列表")
    @GetMapping("/listStatus")
    public Result<List<DropDownVo>> listStatus(){
        return Result.ok(ProcessStatusEnum.listForStatus());
    }

}
