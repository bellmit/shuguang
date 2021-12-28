package com.sofn.sys.web;

import com.sofn.common.log.SofnLog;
import com.sofn.common.model.Result;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.web.BaseController;
import com.sofn.sys.service.AppPackageManageService;
import com.sofn.sys.vo.AppPackageManageForm;
import com.sofn.sys.vo.AppPackageManageVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * App包管理Controller
 *
 * @author heyongjie
 * @date 2019/11/18 16:14
 */
@Api(tags = "App包管理，权限码：（sys:appPackageManage:index）")
@RestController
@RequestMapping("/appPackageManage")
@Slf4j
public class AppPackageManageController extends BaseController {

    @Autowired
    private AppPackageManageService appPackageManageService;


    @GetMapping("/getList")
    @ApiOperation(value = "按条件查询列表", notes = "权限码(sys:appPackageManage:getList)")
    @SofnLog("按条件查询列表")
    public Result<PageUtils<AppPackageManageVo>> getList(@RequestParam(name = "pageNo") @ApiParam(value = "当前页数", required = true) Integer pageNo,
                                                         @RequestParam(name = "pageSize") @ApiParam(value = "每页显示条数", required = true) Integer pageSize,
                                                         @RequestParam(name = "productName", required = false) @ApiParam(value = "产品名称") String productName,
                                                         @RequestParam(name = "platform", required = false) @ApiParam(value = "平台") String platform,
                                                         @RequestParam(name = "state", required = false) @ApiParam(value = "状态") String state) {
        PageUtils pageUtils = appPackageManageService.getList(pageNo, pageSize, productName, platform, state);
        return Result.ok(pageUtils);
    }

    @GetMapping("/getOne/{id}")
    @ApiOperation(value = "获取一个App信息", notes = "权限码(sys:appPackageManage:getOne)")
    @SofnLog("获取一个App信息")
    public Result<AppPackageManageVo> getOne(@PathVariable(value = "id") String id) {
        AppPackageManageVo one = appPackageManageService.getOne(id);
        return Result.ok(one);
    }

    @GetMapping("/getTheLatestVersion")
    @ApiOperation(value = "获取最新版本信息", notes = "权限码(sys:appPackageManage:getTheLatestVersion)")
    public Result<AppPackageManageVo> getTheLatestVersion(@RequestParam(name = "productName") @ApiParam(value = "产品名称",required = true) String productName,
                                                          @RequestParam(name = "platform") @ApiParam(value = "平台",required = true) String platform) {
        AppPackageManageVo theLatestVersion = appPackageManageService.getTheLatestVersion(productName, platform);
        return Result.ok(theLatestVersion);
    }

    @PostMapping("/insert")
    @ApiOperation(value = "添加版本信息", notes = "权限码(sys:appPackageManage:insert)")
    @SofnLog(value = "获取一个App信息", type = "新增")
    public Result<String> insert(@Validated @RequestBody @ApiParam(name = "App包信息", value = "传入json格式", required = true)
                                         AppPackageManageForm appPackageManageForm, BindingResult result) {
        // 校验参数
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        appPackageManageService.insert(appPackageManageForm);
        return Result.ok();
    }

    @PostMapping("/update")
    @ApiOperation(value = "只能更新产品描述和状态", notes = "权限码(sys:appPackageManage:update)")
    @SofnLog(value = "只能更新产品描述和状态", type = "更新")
    public Result<String> update(
            @Validated @RequestBody @ApiParam(name = "包管理对象", value = "传入json格式", required = true)
                    AppPackageManageForm appPackageManageForm, BindingResult result) {
        // 校验参数
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        appPackageManageService.update(appPackageManageForm);
        return Result.ok();
    }

    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除文件信息", notes = "权限码(sys:appPackageManage:delete)")
    @SofnLog(value = "删除文件信息", type = "删除")
    public Result<String> delete(@PathVariable(value = "id") String id) {
        appPackageManageService.delete(id);
        return Result.ok();
    }


}
