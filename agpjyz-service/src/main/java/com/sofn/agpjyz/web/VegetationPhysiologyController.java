package com.sofn.agpjyz.web;

import com.sofn.agpjyz.model.VegetationPhysiology;
import com.sofn.agpjyz.service.VegetationPhysiologyService;
import com.sofn.agpjyz.vo.TargetSpeciesVo;
import com.sofn.agpjyz.vo.VegetationPhysiologyVo;
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
 * @Date: 2020-03-02 17:14
 */
@Slf4j
@Api(value = "植被生理",tags = "植被生理接口")
@RestController
@RequestMapping("/vegetationPhysiology")
public class VegetationPhysiologyController extends BaseController {
    @Autowired
    private VegetationPhysiologyService vegetationPhysiologyService;
    @RequiresPermissions("agpjyz:vegetationPhysiology:create")
    @SofnLog("新增植被生理基础信息")
    @ApiOperation(value = "新增植被生理基础信息")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result addvegetationPhysiology(@Validated @RequestBody @ApiParam(name = "植被生理基础信息对象", value = "传入json格式", required = true)
                                                  VegetationPhysiologyVo vegetationPhysiologyVo, BindingResult result) {
//         校验参数
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        int i = vegetationPhysiologyService.insertVegetationPhysiology(vegetationPhysiologyVo);
        if (i == 1) {
            return Result.ok("新增成功");
        } else {
            return Result.ok("不能重读插入记录");
        }

    }
    @RequiresPermissions("agpjyz:vegetationPhysiology:delete")
    @SofnLog("删除植被生理基础信息")
    @ApiOperation(value = "删除植被生理基础信息")
    @DeleteMapping(value = "/delete")
    public Result delvegetationPhysiology(@ApiParam(value = "植被生理id",required = true)@RequestParam("id") String id){
        return Result.ok(vegetationPhysiologyService.delVegetationPhysiology(id));
    }
    @RequiresPermissions("agpjyz:vegetationPhysiology:update")
    @SofnLog("修改植被生理基础信息")
    @ApiOperation(value = "修改植被生理基础信息")
    @PutMapping(value = "/update")
    public Result updateHal(@Validated@RequestBody @ApiParam(name = "证书参数对象", value = "传入json格式",
            required = true) VegetationPhysiology vegetationPhysiology,BindingResult result){
        //         校验参数
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        return Result.ok(vegetationPhysiologyService.updateVegetationPhysiology(vegetationPhysiology));
    }
//    @RequiresPermissions("agpjyz:vegetationPhysiology:menu")
    @SofnLog("条件获取植被生理基础信息(分页)")
    @ApiOperation(value = "条件获取植被生理基础信息(分页)")
    @GetMapping(value = "/listPage")
    public Result<PageUtils<VegetationPhysiology>> getCaptureList(@ApiParam(name = "protectId",value = "保护点id",required = false)@RequestParam(value = "protectId",required = false)String protectId,
                                                                  @ApiParam(name = "coverDegree",value = "植被生理名称",required = false)@RequestParam(value = "coverDegree",required = false)String coverDegree,
                                                                  @ApiParam(name = "selectDateS",value = "查询时间开始",required = false)@RequestParam(value = "selectDateS",required = false)String selectDateS,
                                                                  @ApiParam(name = "selectDateE",value = "查询时间结束",required = false)@RequestParam(value = "selectDateE",required = false)String selectDateE,
                                                                  @ApiParam(name = "pageNo",required = true)@RequestParam("pageNo")int pageNo,
                                                                  @ApiParam(name = "pageSize",required = true)@RequestParam("pageSize")int pageSize) {



        return  Result.ok(vegetationPhysiologyService.getVegetationPhysiology(getQueryMap(protectId, coverDegree, selectDateS, selectDateE),pageNo,pageSize));
    }
    @RequiresPermissions("agpjyz:vegetationPhysiology:view")
    @ApiOperation(value = "详情")
    @GetMapping("/get")
    public Result<VegetationPhysiology> get(
            @ApiParam(value = "主键id", required = true) @RequestParam("id") String id) {
        return Result.ok(vegetationPhysiologyService.get(id));
    }

    @RequiresPermissions("agpjyz:vegetationPhysiology:export")
    @GetMapping(value = "/export", produces = "application/octet-stream")
    @ApiOperation(value = "导出", produces = "application/octet-stream")
    public void export(
            @ApiParam(name = "protectId",value = "保护点id",required = false)@RequestParam(value = "protectId",required = false)String protectId,
            @ApiParam(name = "coverDegree",value = "植被生理名称",required = false)@RequestParam(value = "coverDegree",required = false)String coverDegree,
            @ApiParam(name = "selectDateS",value = "查询时间开始",required = false)@RequestParam(value = "selectDateS",required = false)String selectDateS,
            @ApiParam(name = "selectDateE",value = "查询时间结束",required = false)@RequestParam(value = "selectDateE",required = false)String selectDateE,
            HttpServletResponse response) {
        vegetationPhysiologyService.export(getQueryMap(protectId, coverDegree, selectDateS, selectDateE), response);
    }
    /**
     * 封装查询参数
     */
    private Map<String, Object> getQueryMap(String protectId, String coverDegree, String selectDateS, String selectDateE) {
        Map map = new HashMap<String, Object>(4);
        map.put("protectId", protectId);
        map.put("coverDegree", coverDegree);
        if (StringUtils.hasText(selectDateS)) {
            map.put("selectDateS", selectDateS + " 00:00:00");
        }
        if (StringUtils.hasText(selectDateE)) {
            map.put("selectDateE", selectDateE + " 23:59:59");
        }
        return map;
    }
}
