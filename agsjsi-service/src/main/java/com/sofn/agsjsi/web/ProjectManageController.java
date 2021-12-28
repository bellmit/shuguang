package com.sofn.agsjsi.web;

import com.github.pagehelper.PageInfo;
import com.sofn.agsjsi.model.ProjectManage;
import com.sofn.agsjsi.service.ProjectManageService;
import com.sofn.agsjsi.vo.DropDownVo;
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

import java.util.List;


@Slf4j
@Api(value = "计划管理", tags = "计划管理相关接口")
@RestController
@RequestMapping(value = "/projectManage")
public class ProjectManageController extends BaseController {
    @Autowired
    private ProjectManageService projectManageService;

    @ApiOperation(value = "获取计划管理列表")
    @RequiresPermissions("agsjsi:projectManage:query")
    @GetMapping("/list")
    public Result<List<ProjectManage>> getProjectManagesByQuery(@RequestParam(required = false,defaultValue = "0") @ApiParam(value = "当前页数") Integer pageNo,
                                                         @RequestParam(required = false,defaultValue = "10") @ApiParam(value = "每页显示条数") Integer pageSize,
                                                         @RequestParam(required = false) @ApiParam(value = "计划名字") String name,
                                                         @RequestParam(required = false) @ApiParam(value = "所属机构id") String orgId,
                                                         @RequestParam(required = false) @ApiParam(value = "状态") String status,
                                                         @RequestParam(required = false) @ApiParam(value = "查询时间-开始") String surveyTimeLeft,
                                                         @RequestParam(required = false) @ApiParam(value = "查询时间-结束") String surveyTimeRight){
//        pageNo = pageNo == null ? 1 : pageNo/10+1;
//        pageSize = pageSize == null ? 5 : pageSize;

        return Result.ok(projectManageService.getAllByQuery(pageNo,pageSize,name, orgId,status, surveyTimeLeft, surveyTimeRight));
    }

    @ApiOperation(value = "添加计划管理信息")
    @RequiresPermissions("agsjsi:projectManage:create")
    @PostMapping("/create")
    public Result<ProjectManage> saveProjectManage(@RequestBody @Validated ProjectManage projectManage, BindingResult result){
            try {
                if(result.hasErrors()){
                    return Result.error(getErrMsg(result));
                }
                //作添加
                projectManageService.insertProjectManage(projectManage);
            } catch (Exception e) {
                return Result.error(e.getMessage());
            }
        return Result.ok("操作成功");
    }
    @ApiOperation(value = "更新计划管理信息")
    @RequiresPermissions("agsjsi:projectManage:update")
    @PutMapping("/update")
    public Result<ProjectManage> updateProjectManage(@Validated@RequestBody  ProjectManage projectManage, BindingResult result){
        try {
            if(result.hasErrors()){
                return Result.error(getErrMsg(result));
            }
            if(StringUtils.isEmpty(projectManage.getId())){
                return Result.error("请上传id！");
            }
            //作更新
            projectManageService.updateProjectManage(projectManage);
        } catch (Exception e) {
            return Result.error(e);
        }
        return Result.ok("操作成功");
    }

    @ApiOperation(value = "通过id删除计划管理信息")
    @RequiresPermissions("agsjsi:projectManage:delete")
    @DeleteMapping("/delete")
    public Result<ProjectManage> deleteProjectManage(String id){
        try {
            projectManageService.deleteProjectManage(id);
        } catch (Exception e) {
            Result.error(e);
        }
        return Result.ok("删除成功");
    }

    @ApiOperation(value = "修改为状态为已下发")
    @RequiresPermissions("agsjsi:projectManage:down")
    @PutMapping("/down")
    public Result<ProjectManage> updateProjectManageTwo(String id){
        try {
            projectManageService.updateProjectManageTwo(id);
        } catch (Exception e) {
            Result.error(e);
        }
        return Result.ok("下发成功");
    }

    @SofnLog("《下拉列表》获取所属下级机构")
    @ApiOperation(value = "《下拉列表》获取所属下级机构")
    @RequiresPermissions("agsjsi:projectManage:query")
    @GetMapping("/listForSelect")
    public Result<List<DropDownVo>> listForSelect() {
        List<DropDownVo> dropDownList = projectManageService.listForDropDown();
        return Result.ok(dropDownList);
    }

    @ApiOperation(value = "获取计划管理详情")
    @RequiresPermissions("agsjsi:projectManage:view")
    @GetMapping("/view")
    public Result<ProjectManage> getProjectManageOne(String id){
        ProjectManage projectManage = projectManageService.getProjectManageOne(id);
        Result<ProjectManage> result = new Result<>(projectManage);
        result.setCode(200);
        return result;
    }
}
