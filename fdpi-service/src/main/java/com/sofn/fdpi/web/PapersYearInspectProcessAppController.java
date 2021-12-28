package com.sofn.fdpi.web;

import com.google.common.collect.Maps;
import com.sofn.common.model.Result;
import com.sofn.common.utils.BoolUtils;
import com.sofn.common.utils.UserUtil;
import com.sofn.fdpi.constants.Constants;
import com.sofn.fdpi.model.PapersYearInspectProcess;
import com.sofn.fdpi.service.PapersYearInspectProcessService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Api(value="APP_证书年审进度相关接口",tags = "APP_证书年审进度相关接口")
@RestController
@RequestMapping("/app/papersYearProcess")
public class PapersYearInspectProcessAppController {
    @Autowired
    private PapersYearInspectProcessService papersYearInspectProcessService;
    //wuXY
    @RequiresPermissions("fdpi:papersYearInspectSpeed:query")
    @ApiOperation(value = "证书年度进度《查询列表》")
    @GetMapping("/list")
    public Result<List<PapersYearInspectProcess>> list(@RequestParam(value="year",required = false) @ApiParam(name = "year", value = "年度") String year
            , @RequestParam(value="status",required = false) @ApiParam(name = "status", value = "审核状态:2：上报；3：初审退回；4:初审通过；5：复审退回;6：复审通过") String status
            , @RequestParam(value = "pageNo", required = false,defaultValue = "0") @ApiParam(name = "pageNo", value = "索引") Integer pageNo
            , @RequestParam(value = "pageSize", required = false,defaultValue = "20") @ApiParam(name = "pageSize", value = "每页条数") Integer pageSize){

        Map<String,Object> map= Maps.newHashMap();
        map.put("year",year);
        map.put("status",status);
        map.put("compId", UserUtil.getLoginUserOrganizationId());
        return Result.ok(Constants.WORKFLOW.equals(BoolUtils.Y) ? papersYearInspectProcessService.listByPageInfo(map,pageNo,pageSize) : papersYearInspectProcessService.listByPage(map,pageNo,pageSize));
    }
}
