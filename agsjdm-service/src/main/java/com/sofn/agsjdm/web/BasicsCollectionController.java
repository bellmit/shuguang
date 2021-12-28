package com.sofn.agsjdm.web;

import com.sofn.agsjdm.model.BasicsCollection;
import com.sofn.agsjdm.service.BasicsCollectionService;
import com.sofn.agsjdm.vo.BasicsCollectionForm;
import com.sofn.common.log.SofnLog;
import com.sofn.common.model.Result;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
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
 * @Date: 2020-04-13 13:27
 */
@Slf4j
@Api(tags = "基础信息收集接口")
@RestController
@RequestMapping("/bc")
public class BasicsCollectionController extends BaseController {
    @Autowired
    private BasicsCollectionService basicsCollectionService;
    @RequiresPermissions("asgjdm:bc:create")
    @SofnLog("新增基础信息收集")
    @ApiOperation(value = "新增基础信息收集")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result insert(@Validated @RequestBody @ApiParam(name = "基础信息收集对象", value = "传入json格式", required = true) BasicsCollectionForm bc,BindingResult result){
        //         校验参数
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        basicsCollectionService.insert(bc);
        return Result.ok();
    }
    @RequiresPermissions("asgjdm:bc:update")
    @SofnLog("修改基础信息收集")
    @ApiOperation(value = "修改基础信息收集")
    @PutMapping(value = "/update")
    public Result update(@Validated @RequestBody @ApiParam(name = "基础信息收集对象", value = "传入json格式", required = true) BasicsCollectionForm bc,BindingResult result){
        //         校验参数
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        basicsCollectionService.update(bc);
        return Result.ok();
    }
    @RequiresPermissions("asgjdm:bc:view")
    @SofnLog("获取基础信息详情")
    @ApiOperation(value = "获取基础信息详情")
    @GetMapping(value = "/get")
    public Result<BasicsCollection> get(@ApiParam(value = "主键", required = true) @RequestParam(value = "id",required = true) String id){
        return Result.ok(basicsCollectionService.get(id));
    }
    @RequiresPermissions("asgjdm:bc:delete")
    @SofnLog("删除基础信息")
    @ApiOperation(value = "删除基础信息")
    @DeleteMapping(value = "/delete")
    public Result delete(@ApiParam(value = "主键", required = true) @RequestParam(value = "id",required = true) String id){
        basicsCollectionService.delete(id);
        return Result.ok();
    }

    @ApiOperation(value = "分页查询")
    @GetMapping("/list")
    public Result<PageUtils<BasicsCollection>> listPage(
            @ApiParam("湿地区ID") @RequestParam(required = false) String wetlandId,
            @ApiParam("目标物种名称") @RequestParam(required = false) String specValue,
            @ApiParam("开始时间(yyyy-MM-dd)") @RequestParam(required = false) String startTime,
            @ApiParam("结束时间(yyyy-MM-dd)") @RequestParam(required = false) String endTime,
            @ApiParam(value = "pageNo", required = true) @RequestParam() Integer pageNo,
            @ApiParam(value = "pageSize", required = true) @RequestParam() Integer pageSize) {

        return Result.ok(basicsCollectionService.list(getQueryMap(wetlandId, specValue,
                startTime, endTime), pageNo, pageSize));
    }

    @RequiresPermissions("asgjdm:bc:export")
    @GetMapping(value = "/export", produces = "application/octet-stream")
    @ApiOperation(value = "导出", produces = "application/octet-stream")
    public void export(
            @ApiParam("湿地区ID") @RequestParam(required = false) String wetlandId,
            @ApiParam("目标物种名称") @RequestParam(required = false) String specValue,
            @ApiParam("开始时间(yyyy-MM-dd)") @RequestParam(required = false) String startTime,
            @ApiParam("结束时间(yyyy-MM-dd)") @RequestParam(required = false) String endTime,
            HttpServletResponse response) {
        basicsCollectionService.export(getQueryMap(wetlandId, specValue,
                startTime, endTime), response);
    }

    private Map<String, Object> getQueryMap(String wetlandId, String specValue, String startTime, String endTime) {
        Map map = new HashMap<String, Object>(7);
        map.put("wetlandId", wetlandId);
        map.put("specValue", specValue);
        map.put("startTime", startTime);
        if (StringUtils.hasText(endTime)) {
            map.put("endTime", endTime + " 23:59:59");
        }
        return map;
    }


}


