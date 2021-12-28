package com.sofn.fdpi.web;

import com.google.common.collect.Maps;
import com.sofn.common.model.Result;
import com.sofn.common.utils.BoolUtils;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.UserUtil;
import com.sofn.fdpi.constants.Constants;
import com.sofn.fdpi.service.AuditProcessService;
import com.sofn.fdpi.vo.PapersProcessVo;
import com.sofn.fdpi.vo.SignboardProcessVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/papersProcess")
@Api(value = "证书绑定进度相关接口",tags = "证书绑定进度相关接口")
public class AuditProcessController {
    @Autowired
    private AuditProcessService auditProcessService;

    //wuXY
    @RequiresPermissions("fdpi:papersBindingSpeed:query")
    @ApiOperation(value = "证书绑定进度《查询列表》")
    @GetMapping("/list")
    public Result<List<PapersProcessVo>> listForBindingSpeed(@RequestParam(value="year",required = false) @ApiParam(name = "year", value = "年度") String year
            , @RequestParam(value="status",required = false) @ApiParam(name = "status", value = "审核状态:2：上报；3：初审通过；4;初审退回；5：复审通过") String status
            , @RequestParam(value = "pageNo", required = false,defaultValue = "0") @ApiParam(name = "pageNo", value = "索引") Integer pageNo
            , @RequestParam(value = "pageSize", required = false,defaultValue="20") @ApiParam(name = "pageSize", value = "每页条数") Integer pageSize) {

        String startTime="";
        String endTime="";
        if(StringUtils.isNotBlank(year)){
            startTime=year+"-01-01 00:00:00";
            endTime=year+"-12-31 23:59:59";
        }

        Map<String,Object> map= Maps.newHashMap();
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        map.put("status",status);
        map.put("pageNo",pageNo);
        map.put("pageSize",pageSize);
        map.put("compId", UserUtil.getLoginUserOrganizationId());
        PageUtils<PapersProcessVo> papersProcessVoPageUtils  = Constants.WORKFLOW.equals(BoolUtils.Y) ? auditProcessService.listForBindingSpeedByInfo(map)
                : auditProcessService.listForBindingSpeed(map);
        return Result.ok(papersProcessVoPageUtils);
    }
}
