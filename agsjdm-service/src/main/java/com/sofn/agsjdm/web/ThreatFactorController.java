package com.sofn.agsjdm.web;

import com.sofn.agsjdm.model.ThreatFactor;
import com.sofn.agsjdm.service.ThreatFactorService;
import com.sofn.agsjdm.vo.ThreatFactorForm;
import com.sofn.common.log.SofnLog;
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
 * @Date: 2020-04-13 15:31
 */
@Slf4j
@Api(tags = "威胁因素基础信息收集接口")
@RestController
@RequestMapping("/tf")
public class ThreatFactorController extends BaseController {
    @Autowired
    private ThreatFactorService threatFactorService;
    @RequiresPermissions("agsjdm:tf:create")
    @SofnLog("新增威胁因素基础信息收集")
    @ApiOperation(value = "新增威胁因素基础信息收集")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result insert(@Validated @RequestBody @ApiParam(name = "威胁因素基础信息收集对象", value = "传入json格式", required = true) ThreatFactorForm bc, BindingResult result){
        //         校验参数
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        threatFactorService.insert(bc);
        return Result.ok();
    }
    @RequiresPermissions("agsjdm:tf:update")
    @SofnLog("修改威胁因素基础信息收集")
    @ApiOperation(value = "修改威胁因素基础信息收集")
    @PutMapping(value = "/update")
    public Result update(@Validated @RequestBody @ApiParam(name = "威胁因素基础信息收集对象", value = "传入json格式", required = true) ThreatFactorForm bc,BindingResult result){
        //         校验参数
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        threatFactorService.update(bc);
        return Result.ok();
    }
    @RequiresPermissions("agsjdm:tf:view")
    @SofnLog("获取威胁因素基础信息详情")
    @ApiOperation(value = "获取威胁因素基础信息详情")
    @GetMapping(value = "/get")
    public Result<ThreatFactor> get(@ApiParam(value = "主键", required = true) @RequestParam(value = "id",required = true) String id){
        return Result.ok(threatFactorService.get(id));
    }
    @RequiresPermissions("agsjdm:tf:delete")
    @SofnLog("删除威胁因素基础信息")
    @ApiOperation(value = "删除威胁因素基础信息")
    @DeleteMapping(value = "/delete")
    public Result delete(@ApiParam(value = "主键", required = true) @RequestParam(value = "id",required = true) String id){
        threatFactorService.delete(id);
        return Result.ok();
    }

    @ApiOperation(value = "分页查询")
    @GetMapping("/list")
    public Result<PageUtils<ThreatFactor>> listPage(
            @ApiParam("湿地区ID") @RequestParam(required = false) String wetlandId,
            @ApiParam("围垦影响面积") @RequestParam(required = false) String reclamation,
            @ApiParam("沙泥淤积影响面积") @RequestParam(required = false) String silt,
            @ApiParam("开始时间(yyyy-MM-dd)") @RequestParam(required = false) String startTime,
            @ApiParam("结束时间(yyyy-MM-dd)") @RequestParam(required = false) String endTime,
            @ApiParam(value = "pageNo", required = true) @RequestParam() Integer pageNo,
            @ApiParam(value = "pageSize", required = true) @RequestParam() Integer pageSize) {

        return Result.ok(threatFactorService.list(getQueryMap(wetlandId, reclamation,silt,
                startTime, endTime), pageNo, pageSize));
    }

    @RequiresPermissions("agsjdm:tf:export")
    @GetMapping(value = "/export", produces = "application/octet-stream")
    @ApiOperation(value = "导出", produces = "application/octet-stream")
    public void export(
            @ApiParam("湿地区ID") @RequestParam(required = false) String wetlandId,
            @ApiParam("围垦影响面积") @RequestParam(required = false) String reclamation,
            @ApiParam("沙泥淤积影响面积") @RequestParam(required = false) String silt,
            @ApiParam("开始时间(yyyy-MM-dd)") @RequestParam(required = false) String startTime,
            @ApiParam("结束时间(yyyy-MM-dd)") @RequestParam(required = false) String endTime,
            HttpServletResponse response) {
        threatFactorService.export(getQueryMap(wetlandId, reclamation,silt,
                startTime, endTime), response);
    }

    private Map<String, Object> getQueryMap(String wetlandId, String reclamation,String silt, String startTime, String endTime) {
        Map map = new HashMap<String, Object>(7);
        map.put("wetlandId", wetlandId);
        map.put("reclamation", reclamation);
        map.put("silt", silt);
        map.put("startTime", startTime);
        if (StringUtils.hasText(endTime)) {
            map.put("endTime", endTime + " 23:59:59");
        }
        return map;
    }
}
