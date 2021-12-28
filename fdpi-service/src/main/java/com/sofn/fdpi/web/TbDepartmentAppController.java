package com.sofn.fdpi.web;

import com.google.common.collect.Maps;
import com.sofn.common.model.Result;
import com.sofn.common.utils.IdUtil;
import com.sofn.common.web.BaseController;
import com.sofn.fdpi.enums.DepartmentTypeEnum;
import com.sofn.fdpi.service.TbDepartmentService;
import com.sofn.fdpi.util.MapUtil;
import com.sofn.fdpi.vo.SelectVo;
import com.sofn.fdpi.vo.TbDepartmentForm;
import com.sofn.fdpi.vo.TbDepartmentVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@Api(value = "APP_机构相关接口（证书绑定直属机构/证书年审直属机构）",tags = "APP_机构相关接口（证书绑定直属机构/证书年审直属机构）")
@RestController
@RequestMapping("/app/department")
public class TbDepartmentAppController extends BaseController {

    @Autowired
    private TbDepartmentService tbDepartmentService;

    @ApiOperation(value = "证书绑定直属机构-》《分页列表》")
    @RequiresPermissions("fdpi:directlyDept:query")
    @GetMapping("/listForDirect")
    public Result<List<TbDepartmentVo>> listForDirect(@RequestParam(value = "deptPro", required = false) @ApiParam(name = "deptPro", value = "省编码") String deptPro
            , @RequestParam(value = "deptCity", required = false) @ApiParam(name = "deptCity", value = "市编码") String deptCity
            , @RequestParam(value = "deptArea", required = false) @ApiParam(name = "deptArea", value = "区编码") String deptArea
            , @RequestParam(value = "deptName", required = false) @ApiParam(name = "deptName", value = "机构名称") String deptName
            , @RequestParam(value = "pageNo", required = false, defaultValue = "0") @ApiParam(name = "pageNo", value = "分页索引") Integer pageNo
            , @RequestParam(value = "pageSize", required = false, defaultValue = "20") @ApiParam(name = "pageSize", value = "页条数") Integer pageSize) {

        Map<String, Object> map = Maps.newHashMap();
        map.put("deptPro", deptPro);
        map.put("deptCity", deptCity);
        map.put("deptArea", deptArea);
        map.put("deptName", deptName);
        map.put("type", "1");

        return Result.ok(tbDepartmentService.listForPage(map, pageNo, pageSize));
    }

    @ApiOperation(value="证书绑定直属机构->《新增》")
    @RequiresPermissions("fdpi:directlyDept:create")
    @PostMapping("/addForDirect")
    public Result addForDirect(@Validated @RequestBody @ApiParam(name="机构对象",value = "传入json格式",required = true) TbDepartmentForm tbDepartmentForm, BindingResult result){
        if(result.hasErrors()){
            return Result.error(getErrMsg(result));
        }
        tbDepartmentService.addDepartment(tbDepartmentForm, "1");
        return Result.ok("新增成功！");
    }
    @ApiOperation(value="证书绑定直属机构->《获取证书绑定直属机构对象》")
    @GetMapping("/getForDirect")
    public Result<TbDepartmentVo> getForDirect(@RequestParam(value="deptId")@ApiParam(name="deptId",value="部门机构id",required = true) String deptId){
        return Result.ok(tbDepartmentService.getOneById(deptId,"1"));
    }

    @ApiOperation(value="证书绑定直属机构->《修改保存》")
    @PutMapping("/updateForDirect")
    public Result updateForDirect(@Validated @RequestBody @ApiParam(name="机构对象",value = "传入json格式",required = true)TbDepartmentForm tbDepartmentForm, BindingResult result){
        if(result.hasErrors()){
            return Result.error(getErrMsg(result));
        }
        if(StringUtils.isBlank(tbDepartmentForm.getId())){
            return Result.error("请上传ID值!");
        }
        String successResult = tbDepartmentService.updateDepartment(tbDepartmentForm, "1");
        if(!"1".equals(successResult)){
            return  Result.error(successResult);
        }
        return Result.ok("修改成功！");
    }

    @ApiOperation(value="证书绑定直属机构->《删除》")
    @RequiresPermissions("fdpi:directlyDept:delete")
    @DeleteMapping("/delForDirect")
    public Result delForDirect(@RequestParam(value="deptId")@ApiParam(name="deptId",value="直属部门Id",required = true) String deptId){

        String successResult = tbDepartmentService.deleteDepartment(deptId);
        if(!"1".equals(successResult)){
            return  Result.error(successResult);
        }
        return Result.ok("删除成功！");
    }

    @ApiOperation(value = "证书年审直属机构查询-》《分页列表》")
    @RequiresPermissions("fdpi:yearInspectDept:query")
    @GetMapping("/listForYearInspectOrg")
    public Result<List<TbDepartmentVo>> listForYearInspectOrg(@RequestParam(value = "deptPro", required = false) @ApiParam(name = "deptPro", value = "省编码") String deptPro
            , @RequestParam(value = "deptCity", required = false) @ApiParam(name = "deptCity", value = "市编码") String deptCity
            , @RequestParam(value = "deptArea", required = false) @ApiParam(name = "deptArea", value = "区编码") String deptArea
            , @RequestParam(value = "deptName", required = false) @ApiParam(name = "deptName", value = "机构名称") String deptName
            , @RequestParam(value = "pageNo", required = false, defaultValue = "0") @ApiParam(name = "pageNo", value = "分页索引") Integer pageNo
            , @RequestParam(value = "pageSize", required = false, defaultValue = "20") @ApiParam(name = "pageSize", value = "页条数") Integer pageSize) {

        Map<String, Object> map = Maps.newHashMap();
        map.put("deptPro", deptPro);
        map.put("deptCity", deptCity);
        map.put("deptArea", deptArea);
        map.put("deptName", deptName);
        map.put("type", "2");

        return Result.ok(tbDepartmentService.listForPage(map, pageNo, pageSize));
    }

    @ApiOperation(value="证书年审直属机构->《新增》")
    @RequiresPermissions("fdpi:yearInspectDept:create")
    @PostMapping("/addForYearInspectOrg")
    public Result addForYearInspectOrg(@Validated @RequestBody @ApiParam(name="机构对象",value = "传入json格式",required = true)TbDepartmentForm tbDepartmentForm, BindingResult result){
        if(result.hasErrors()){
            return Result.error(getErrMsg(result));
        }
        tbDepartmentService.addDepartment(tbDepartmentForm, "2");
        return Result.ok("新增成功！");
    }
    @ApiOperation(value="证书年审直属机构->《获取证书年审直属机构对象》")
    @GetMapping("/getForYearInspectOrg")
    public Result<TbDepartmentVo> getForYearInspectOrg(@RequestParam(value="deptId")@ApiParam(name="deptId",value="部门机构id",required = true) String deptId){
        return Result.ok(tbDepartmentService.getOneById(deptId,"2"));
    }

    @ApiOperation(value="证书年审直属机构->《修改保存》")
    @PutMapping("/updateForYearInspectOrg")
    public Result updateForYearInspectOrg(@Validated @RequestBody @ApiParam(name="机构对象",value = "传入json格式",required = true)TbDepartmentForm tbDepartmentForm, BindingResult result){
        if(result.hasErrors()){
            return Result.error(getErrMsg(result));
        }
        if(StringUtils.isBlank(tbDepartmentForm.getId())){
            return Result.error("请上传ID值!");
        }
        String successResult = tbDepartmentService.updateDepartment(tbDepartmentForm, "2");
        if(!"1".equals(successResult)){
            return  Result.error(successResult);
        }
        return Result.ok("修改成功！");
    }

    @ApiOperation(value="证书年审直属机构->《删除》")
    @RequiresPermissions("fdpi:yearInspectDept:delete")
    @DeleteMapping("/delForYearInspectOrg")
    public Result delForYearInspectOrg(@RequestParam(value="deptId")@ApiParam(name="deptId",value="直属部门Id",required = true) String deptId){

        String successResult = tbDepartmentService.deleteDepartment(deptId);
        if(!"1".equals(successResult)){
            return  Result.error(successResult);
        }
        return Result.ok("删除成功！");
    }
    @ApiOperation(value="直属机构和证书年审直属机构数据存入redis中,不需要对接，后期废除")
    @GetMapping("/convertDataToRedis")
    public Result convertDataToRedis(){

        tbDepartmentService.convertDataToRedis();
        return Result.ok("执行成功！");
    }

    @ApiOperation(value = "是否拥有权限")
    @GetMapping("/isAuth")
    public Result<Boolean> isAuth(@RequestParam() @ApiParam(required = true, value = "权限类型0备案1变更2年审3打印") String type) {
        return Result.ok(tbDepartmentService.isAuth(type));
    }

    @ApiOperation(value = "权限管理分页查询")
    @GetMapping("/listforPage")
    public Result<List<TbDepartmentVo>> listforPage(
            @RequestParam(required = false) @ApiParam(value = "功能权限类型,可多选，多type用英文','隔开,接口（/department/getAuthType）") String types,
//            @RequestParam(value = "deptPro", required = false) @ApiParam(name = "deptPro", value = "省编码") String deptPro,
            @RequestParam(value = "deptCity", required = false) @ApiParam(name = "deptCity", value = "市编码") String deptCity,
            @RequestParam(value = "deptArea", required = false) @ApiParam(name = "deptArea", value = "区编码") String deptArea,
            @RequestParam(value = "deptName", required = false) @ApiParam(name = "deptName", value = "机构名称") String deptName,
            @RequestParam() @ApiParam(required = true, defaultValue = "0", name = "pageNo", value = "分页索引") Integer pageNo,
            @RequestParam() @ApiParam(required = true, defaultValue = "10", name = "pageSize", value = "页条数") Integer pageSize) {

        String[] keys = {"types", "deptCity", "deptArea", "deptName"};
        Object[] vals = {IdUtil.getIdsByStr(types), deptCity, deptArea, deptName};
        return Result.ok(tbDepartmentService.listForPage(MapUtil.getParams(keys, vals), pageNo, pageSize));
    }

    @ApiOperation(value = "新增")
    @PostMapping("/add")
    public Result add(@Validated @RequestBody @ApiParam(name = "机构对象", value = "传入json格式", required = true) TbDepartmentForm tbDepartmentForm, BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        tbDepartmentService.addDepartment(tbDepartmentForm, tbDepartmentForm.getType());
        return Result.ok("新增成功！");
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("/delete")
    public Result delete(@RequestParam(value = "deptId") @ApiParam(name = "deptId", value = "直属部门Id", required = true) String deptId) {
        String successResult = tbDepartmentService.deleteDepartment(deptId);
        if (!"1".equals(successResult)) {
            return Result.error(successResult);
        }
        return Result.ok("删除成功！");
    }

    @ApiOperation(value = "修改")
    @PutMapping("/update")
    public Result update(@Validated @RequestBody @ApiParam(name = "机构对象", value = "传入json格式", required = true) TbDepartmentForm tbDepartmentForm, BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        if (StringUtils.isBlank(tbDepartmentForm.getId())) {
            return Result.error("请上传ID值!");
        }
        String successResult = tbDepartmentService.updateDepartment(tbDepartmentForm, tbDepartmentForm.getType());
        if (!"1".equals(successResult)) {
            return Result.error(successResult);
        }
        return Result.ok("修改成功！");
    }

    @ApiOperation(value = "详情")
    @GetMapping("/getOne")
    public Result<TbDepartmentVo> getOne(@RequestParam(value = "deptId") @ApiParam(name = "deptId", value = "部门机构id", required = true) String deptId) {
        return Result.ok(tbDepartmentService.getOneById(deptId, null));
    }

    @ApiOperation(value = "功能权限类型列表")
    @GetMapping("/getAuthType")
    public Result<List<SelectVo>> getAuthType() {
        return Result.ok(DepartmentTypeEnum.getSelect());
    }
}
