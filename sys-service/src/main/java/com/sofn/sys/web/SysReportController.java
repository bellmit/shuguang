package com.sofn.sys.web;

import com.google.common.collect.Maps;
import com.sofn.common.exception.SofnException;
import com.sofn.common.model.Result;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.web.BaseController;
import com.sofn.sys.service.SysReportService;
import com.sofn.sys.vo.SysReportVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author heyongjie
 * @date 2020/5/26 17:45
 */
@RestController
@Api(tags = "报表相关接口")
@RequestMapping("/sysReport")
public class SysReportController extends BaseController {

    @Autowired
    private SysReportService sysReportService;


    @GetMapping("/getSysReportByCondition")
    @ApiOperation(value = "按条件分页获取报表信息", notes = "权限(sys:sysReport:getSysReportByCondition)")
    public Result<PageUtils<SysReportVo>> getSysReportByCondition(String reportName, String appId, String reportType, Integer pageNo, Integer pageSize) {
        Map<String, String> params = Maps.newHashMap();
        params.put("reportName", reportName);
        params.put("appId", appId);
        params.put("reportType", reportType);
        PageUtils<SysReportVo> sysReportByCondition = sysReportService.getSysReportByCondition(params, pageNo, pageSize);
        return Result.ok(sysReportByCondition);

    }

    /**
     * 更新报表信息
     *
     * @param sysReport 更改后的信息
     */
        @PostMapping("/updateSysReport")
        @ApiOperation(value = "更新报表信息", notes = "权限(sys:sysReport:updateSysReport)")
        public Result updateSysReport(@Validated @RequestBody @ApiParam(name = "报表对象", value = "传入json格式", required = true) SysReportVo sysReport, BindingResult result) {
            if (result.hasErrors()) {
                return Result.error(getErrMsg(result));
            }
            if (StringUtils.isBlank(sysReport.getId())) {
                throw new SofnException("请传入待更新ID！");
            }
            sysReportService.updateSysReport(sysReport);
            return Result.ok("更新成功！");

    }

    /**
     * 保存报表信息
     *
     * @param sysReport 待保存报表信息
     */
    @PostMapping("/insertSysReport")
    @ApiOperation(value = "更新报表信息", notes = "权限(sys:sysReport:updateSysReport)")
    public Result<String> insertSysReport(@Validated @RequestBody @ApiParam(name = "报表对象", value = "传入json格式", required = true) SysReportVo sysReport, BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        sysReport.setId("");
        sysReportService.insertSysReport(sysReport);
        return Result.ok("保存成功！");
    }


    @DeleteMapping("/delSysReport/{id}")
    @ApiOperation(value = "删除报表信息", notes = "权限(sys:sysReport:delSysReport)")
    public Result<String> delSysReport(@PathVariable("id") String id) {
        sysReportService.delSysReport(id);
        return Result.ok("删除成功！");
    }

    @GetMapping("/getUrl/{reportName}/{reportType}")
    @ApiOperation(value = "获取报表访问路径", notes = "权限(sys:sysReport:getUrl)")
    public Result<String> getUrl(@PathVariable("reportName") @ApiParam(value = "报表名称，不带后缀", required = true) String reportName,
                                 @PathVariable("reportType") @ApiParam(value = "报表类型，1:普通报表 2:决策报表", required = true) String reportType) {
        String url = sysReportService.getUrl(reportName, reportType);
        return Result.ok((Object) url);
    }

    @GetMapping("/getOne/{id}")
    @ApiOperation(value = "根据报表ID获取报表信息", notes = "权限(sys:sysReport:getOne)")
    public Result<SysReportVo> getOne(@PathVariable("id") @ApiParam(value = "报表ID", required = true) String id){
        SysReportVo one = sysReportService.getOne(id);
        return Result.ok(one);
    }

}
