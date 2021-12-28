package com.sofn.agpjyz.web;

import com.sofn.agpjyz.model.Halophytes;
import com.sofn.agpjyz.service.HalophytesService;
import com.sofn.agpjyz.vo.HalophytesVo;
import com.sofn.agpjyz.vo.TargetSpeciesVo;
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
 * @Date: 2020-03-02 17:17
 */
@Slf4j
@Api(value = "伴生植物基础信息接口",tags = "伴生植物基础信息接口")
@RestController
@RequestMapping("/halophytes")
public class HalophytesController extends BaseController {
    @Autowired
    private HalophytesService halophytesService;
    @RequiresPermissions("agpjyz:halophytes:create")
    @SofnLog("伴生植物基础信息")
    @ApiOperation(value = "伴生植物基础信息")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result addhalophytes(@Validated @RequestBody @ApiParam(name = "伴生植物基础信息对象", value = "传入json格式", required = true)
                                        HalophytesVo halophytesVo,BindingResult result) {
//         校验参数
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        int i = halophytesService.insertHalophytes(halophytesVo);
        if (i == 1) {
            return Result.ok("新增成功");
        } else {
            return Result.ok("不能重读插入记录");
        }

    }
    @RequiresPermissions("agpjyz:halophytes:delete")
    @SofnLog("删除伴生植物基础信息")
    @ApiOperation(value = "删除伴生植物基础信息")
    @DeleteMapping(value = "/delete")
    public Result delhalophytes(@ApiParam(value = "伴生植物id",required = true)@RequestParam("id") String id){
        return Result.ok(halophytesService.delHalophytes(id));
    }
    @RequiresPermissions("agpjyz:halophytes:update")
    @SofnLog("修改伴生植物基础信息")
    @ApiOperation(value = "修改伴生植物基础信息")
    @PutMapping(value = "/update")
    public Result updateHal(@Validated@RequestBody @ApiParam(name = "证书参数对象", value = "传入json格式",
            required = true) Halophytes halophytes,BindingResult result){
        //         校验参数
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        return Result.ok(halophytesService.updateHalophytes(halophytes));
    }
//    @RequiresPermissions("agpjyz:halophytes:menu")
    @SofnLog("条件获取伴生植物基础信息(分页)")
    @ApiOperation(value = "条件获取伴生植物基础信息(分页)")
    @GetMapping(value = "/listPage")
    public Result<PageUtils<Halophytes>> getCaptureList(@ApiParam(name = "protectId",value = "保护点id",required = false)@RequestParam(value = "protectId",required = false)String protectId,
                                                        @ApiParam(name = "associated",value = "伴生植物名称",required = false)@RequestParam(value = "associated",required = false)String associated,
                                                        @ApiParam(name = "selectDateS",value = "查询时间开始",required = false)@RequestParam(value = "selectDateS",required = false)String selectDateS,
                                                        @ApiParam(name = "selectDateE",value = "查询时间结束",required = false)@RequestParam(value = "selectDateE",required = false)String selectDateE,
                                                        @ApiParam(name = "pageNo",required = true)@RequestParam("pageNo")int pageNo,
                                                        @ApiParam(name = "pageSize",required = true)@RequestParam("pageSize")int pageSize) {

        Map map= new HashMap<String,Object>(4);
        map.put("protectId",protectId);
        map.put("associated",associated);
        map.put("selectDateS",selectDateS);
        map.put("selectDateE",selectDateE);
        return  Result.ok(halophytesService.getHalophytes(getQueryMap(protectId, associated, selectDateS, selectDateE),pageNo,pageSize));
    }
    @RequiresPermissions("agpjyz:halophytes:view")
    @ApiOperation(value = "详情")
    @GetMapping("/get")
    public Result<Halophytes> get(
            @ApiParam(value = "主键id", required = true) @RequestParam() String id) {
        return Result.ok(halophytesService.get(id));
    }
    @RequiresPermissions("agpjyz:halophytes:export")
    @GetMapping(value = "/export", produces = "application/octet-stream")
    @ApiOperation(value = "导出", produces = "application/octet-stream")
    public void export(
            @ApiParam(name = "protectId",value = "保护点id",required = false)@RequestParam(value = "protectId",required = false)String protectId,
            @ApiParam(name = "associated",value = "伴生植物名称",required = false)@RequestParam(value = "associated",required = false)String associated,
            @ApiParam(name = "selectDateS",value = "查询时间开始",required = false)@RequestParam(value = "selectDateS",required = false)String selectDateS,
            @ApiParam(name = "selectDateE",value = "查询时间结束",required = false)@RequestParam(value = "selectDateE",required = false)String selectDateE,
            HttpServletResponse response) {
        halophytesService.export(getQueryMap(protectId, associated, selectDateS, selectDateE), response);
    }

    /**
     * 封装查询参数
     */
    private Map<String, Object> getQueryMap(String protectId, String associated, String selectDateS, String selectDateE) {
        Map map = new HashMap<String, Object>(4);
        map.put("protectId", protectId);
        map.put("associated", associated);
        if (StringUtils.hasText(selectDateS)) {
            map.put("selectDateS", selectDateS + " 00:00:00");
        }
        if (StringUtils.hasText(selectDateE)) {
            map.put("selectDateE", selectDateE + " 23:59:59");
        }
        return map;
    }
}
