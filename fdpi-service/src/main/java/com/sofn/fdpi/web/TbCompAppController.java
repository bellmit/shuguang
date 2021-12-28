package com.sofn.fdpi.web;

import com.google.common.collect.Maps;
import com.sofn.common.model.Result;
import com.sofn.common.utils.UserUtil;
import com.sofn.common.web.BaseController;
import com.sofn.fdpi.model.TbComp;
import com.sofn.fdpi.service.PapersYearInspectService;
import com.sofn.fdpi.service.SignboardService;
import com.sofn.fdpi.service.TbCompService;
import com.sofn.fdpi.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@Api(value = "APP_企业相关接口",tags = "APP_企业相关接口")
@RestController
@RequestMapping("/app/comp")
public class TbCompAppController extends BaseController {
    @Autowired
    private TbCompService tbCompService;

    @Autowired
    private PapersYearInspectService papersYearInspectService;
    @Autowired
    private SignboardService signboardService;

    //wuXY
    @ApiOperation(value = "企业查询-》《分页列表》")
    @RequiresPermissions(value = {"fdpi:comp:query","fdpi:compUser:query","fdpi:compInformation:query"},logical = Logical.OR)
    @GetMapping("/listForComp")
    public Result<List<TbCompVo>> listForComp(@RequestParam(value = "provinceCode", required = false) @ApiParam(name = "provinceCode", value = "省编码") String provinceCode
            , @RequestParam(value = "cityCode", required = false) @ApiParam(name = "cityCode", value = "市编码") String cityCode
            , @RequestParam(value = "districtCode", required = false) @ApiParam(name = "districtCode", value = "区编码") String districtCode
            , @RequestParam(value = "compName", required = false) @ApiParam(name = "compName", value = "企业名称") String compName
            , @RequestParam(value = "compFullName", required = false) @ApiParam(name = "compFullName", value = "企业名称全称") String compFullName
            , @RequestParam(value = "pageNo", required = false, defaultValue = "0") @ApiParam(name = "pageNo", value = "分页索引") Integer pageNo
            , @RequestParam(value = "pageSize", required = false, defaultValue = "20") @ApiParam(name = "pageSize", value = "页条数") Integer pageSize) {

        Map<String, Object> map = Maps.newHashMap();
        map.put("provinceCode", provinceCode);
        map.put("cityCode", cityCode);
        map.put("districtCode", districtCode);
        map.put("compName", compName);
        map.put("compFullName", compFullName);

        return Result.ok(tbCompService.listForCompAndYearInspectByPage(map, pageNo, pageSize));

    }

    //wuXY
    @ApiOperation(value = "企业查询-》《详情》")
    @RequiresPermissions(value = {"fdpi:comp:view","fdpi:compUser:view","fdpi:compInfo:view","fdpi:compInformation:view"},logical = Logical.OR)
    @GetMapping("/getById")
    public Result<PapersYearInspectViewVo> getDetailById(
              @RequestParam(value = "compId", required = false) @ApiParam(name = "compId", value = "企业id") String compId
            , @RequestParam(value = "inspectId", required = false) @ApiParam(name = "inspectId", value = "证书年审ID,列表中lastYearInspectId(上次年审id)") String inspectId) {

        return Result.ok(papersYearInspectService.getDetailByCompIdAndInspectId(compId,inspectId));
    }

    //wuXY
    @ApiOperation(value = "企业查询 查看详情中获取《标识列表（分页）》")
    @GetMapping("/listForSignBoard")
    public Result<List<SignboardVoForInspect>> listForSignBoard(
            @RequestParam(value = "compId") @ApiParam(name = "compId", value = "企业id，列表中的id主键",required = true) String compId
            , @RequestParam(value = "inspectId", required = false) @ApiParam(name = "inspectId", value = "证书年审ID") String inspectId
            , @RequestParam(value = "speciesId") @ApiParam(name = "speciesId", value = "物种id",required = true) String speciesId
            , @RequestParam(value = "signboardCode",required = false) @ApiParam(name = "signboardCode", value = "标识编码") String signboardCode
            , @RequestParam(value = "signboardType",required = false) @ApiParam(name = "signboardType", value = "标识类型") String signboardType
            , @RequestParam(value = "pageNo", required = false,defaultValue = "0") @ApiParam(name = "pageNo", value = "索引") Integer pageNo
            , @RequestParam(value = "pageSize", required = false,defaultValue = "20") @ApiParam(name = "pageSize", value = "每页条数") Integer pageSize) {

        return Result.ok(signboardService.listPageForSignboard(compId,inspectId,speciesId,signboardCode,signboardType,pageNo,pageSize));
    }

    //wuXY
    @ApiOperation(value = "企业查询-》《修改企业行政区划》(直属机构才有权限)")
    @PostMapping("/updateCompRegionById")
    public Result updateCompRegionById(
            @RequestBody @ApiParam(name = "compId", value = "企业id") TbCompRegionForm tbCompRegionForm
            ,BindingResult result) {

        if(result.hasErrors()){
            return Result.error(getErrMsg(result));
        }
        String updateResult = tbCompService.updateCompRegionById(tbCompRegionForm);
        if(!"1".equals(updateResult)){
            return Result.error(updateResult);
        }
        return Result.ok("修改成功！");
    }


    //wuXY
    @ApiOperation(value = "企业信息中，获取当前企业信息")
    @GetMapping("/getOwnCompInfo")
    public Result<TbCompVo> getOwnCompInfo() {
        return Result.ok(tbCompService.getCombById(UserUtil.getLoginUserOrganizationId()));
    }

    @RequiresPermissions(value = {"fdpi:compUser:update","fdpi:compInfo:update"},logical = Logical.OR)
    @ApiOperation(value = "企业用户-》企业信息《修改》")
    @PutMapping("/update")
    public Result update(@RequestBody @ApiParam(name = "tbCompForm", value = "企业表单json数据", required = true) TbCompForm tbCompForm
            , BindingResult result) {
        if (result.hasErrors()) {
            Result.error(getErrMsg(result));
        }
        TbComp tbComp = tbCompForm.getTbComp(tbCompForm);
        String updateResult = tbCompService.updateComById(tbComp);
        if (!"1".equals(updateResult)) {
            return Result.error(updateResult);
        }
        return Result.ok("修改成功！");
    }
}
