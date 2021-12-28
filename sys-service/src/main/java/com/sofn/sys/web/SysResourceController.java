package com.sofn.sys.web;

import com.sofn.common.excel.ExcelExportUtil;
import com.sofn.common.excel.ExcelImportUtil;
import com.sofn.common.excel.properties.ExcelPropertiesUtils;
import com.sofn.common.fileutil.FileDownloadUtils;
import com.sofn.common.log.SofnLog;
import com.sofn.common.model.Result;
import com.sofn.common.utils.IdUtil;
import com.sofn.common.web.BaseController;
import com.sofn.sys.model.SysResource;
import com.sofn.sys.service.SysResourceService;
import com.sofn.sys.vo.SysResourceBatchSaveVo;
import com.sofn.sys.vo.SysResourceForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

/**
 * @author cjbi
 */

@Api(tags = "菜单接口")
@RestController
@RequestMapping("/sysResource")
@Slf4j
public class SysResourceController extends BaseController {

    @Autowired
    private SysResourceService resourceService;


    @SofnLog(value = "添加菜单信息",type = "新增")
    @ApiOperation(value = "添加菜单信息", notes = "权限码:(sys:resource:create)")
    @PostMapping("/createResource")
    public Result<String> createResource(@Validated @RequestBody @ApiParam(name = "菜单对象",
            value = "传入json格式", required = true) SysResourceForm resourceForm, BindingResult result) {
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        // 转化为MODEL对象r
        SysResource resource = SysResourceForm.getSysResource(resourceForm);
        resourceService.createResource(resource);
        return Result.ok();
    }

    @SofnLog(value = "修改菜单信息",type = "修改")
    @ApiOperation(value = "修改菜单信息", notes = "权限码:(sys:resource:update)")
    @PostMapping("/updateResource")
    public Result<String> update(@Validated @RequestBody @ApiParam(name = "菜单对象",
            value = "传入json格式", required = true) SysResourceForm sysResourceForm, BindingResult result) {

        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        // 转化为MODEL对象
        SysResource resource = SysResourceForm.getSysResource(sysResourceForm);

        resourceService.updateResource(resource);
        return Result.ok();
    }

    @SofnLog(value = "删除菜单信息",type = "删除")
    @ApiOperation(value = "删除菜单信息", notes = "权限码(sys:resource:delete)")
    @PostMapping("/deleteResource")
    public Result<String> delete(@ApiParam(name = "id", value = "菜单ID", required = true) @RequestParam(value = "id") String id) {
        if (id == null || "".equals(id)) {
            return Result.error("ID不能为空");
        }
        resourceService.deleteResource(id);
        return Result.ok();
    }

    @SofnLog("获取一个菜单信息")
    @ApiOperation(value = "根据ID获取一个", notes = "权限码(sys:resource:detail)")
    @PostMapping(value = "/getResourceOne")
    public Result<SysResourceForm> getSysResourceOne(@ApiParam(name = "id", value = "菜单ID", required = true)
                                                     @RequestParam(value = "id") String id) {
        SysResource sysResource = resourceService.getById(id);
        SysResource sysResourceParent = resourceService.getById(sysResource.getParentId());
        SysResourceForm sysResourceForm = new SysResourceForm(sysResource);
        sysResourceForm.setParentName(sysResourceParent.getResourceName());
        return Result.ok(sysResourceForm);
    }

    @ApiOperation(value = "根据APPID,resourceName获取当前用户所属的所有菜单",notes = "权限码：（sys:resource:getAllResourceByAppId）")
    @PostMapping(value = "/getAllResourceByAppId")
    public Result<List<SysResourceForm>> getAllResourceByAppId(@ApiParam(name = "appId", value = "AppID", required = true) @RequestParam(value = "appId") String appId,
                                                               @RequestParam(required = false) @ApiParam(value = "菜单名称") String resourceName) {
        List<SysResourceForm> sysResourceList = resourceService.getAllResourceByAppId(appId, resourceName);
        return Result.ok(sysResourceList);
    }

    @ApiOperation(value = "根据APPID,resourceName获取所有菜单，不做权限限制，并生成树",notes = "权限码：（sys:resource:getAllResourceByAppIdWithOutPermission）")
    @PostMapping(value = "/getAllResourceByAppIdWithOutPermission")
    public Result<List<SysResourceForm>> getAllResourceByAppIdWithOutPermission(@ApiParam(name = "appId", value = "AppID", required = true) @RequestParam(value = "appId") String appId,
                                                                                @RequestParam(required = false) @ApiParam(value = "菜单名称") String resourceName) {
        List<SysResourceForm> sysResourceList = resourceService.getAllResourceByAppIdWithOutPermission(appId, resourceName);
        return Result.ok(sysResourceList);
    }

    @ApiOperation(value = "根据subsystemID获取当前用户所属的所有菜单",notes = "权限码：（sys:resource:getAllResourceBySubsystemId）")
    @PostMapping(value = "/getAllResourceBySubsystemId")
    public Result<List<SysResourceForm>> getAllResourceBySubsystemId(@ApiParam(name = "subsystemId", required = true)
                                                                     @RequestParam(value = "subsystemId") String subsystemId) {
        List<SysResourceForm> sysResourceList = resourceService.getAllResourceBySubsystemId(subsystemId);

        return Result.ok(sysResourceList);
    }
//通过资源name查询resourceList

    @ApiOperation(value = "根据角色ID获取权限菜单",notes = "权限码：（sys:resource:getAllResourceByRoleId）")
    @PostMapping(value = "/getAllResourceByRoleId")
    public Result<List<SysResourceForm>> getAllResourceByRoleId(@ApiParam(name = "id", value = "角色ID", required = true) @RequestParam(value = "id") String id) {
        List<SysResourceForm> sysResourceList = resourceService.getAllResourceByRoleId(id);
        if (sysResourceList == null) {
            return Result.ok("该角色没有权限");
        }
        return Result.ok(sysResourceList);
    }

//    @ApiOperation(value = "获取当前用户权限的子系统菜单树结构")
//    @GetMapping(value = "/getSysResourceTree")
//    public Result<List<SysSubsystemForm>> getSysResourceTree() {
//        List<SysSubsystemForm> sysSubsystemFormList = resourceService.getAllRoleResource();
//        return Result.ok(sysSubsystemFormList);
//    }
//
//    @ApiOperation(value = "批量导出菜单", notes = "权限码(sys:resource:export)", produces = "application/octet-stream")
//    @PostMapping(value = "/exportSysResource", produces = "application/octet-stream")
//    public void exportSysResource(@RequestParam(required = false) @ApiParam(value = "菜单名称") String resourceName,
//                                  HttpServletResponse hsr) {
//        List<SysResourceForm> resourceFormtreeList = resourceService.getSysResourceTreeList(resourceName);
//        List<ImportSysResourceVo> isrList = new ArrayList<>();
//        for (SysResourceForm srf : resourceFormtreeList
//        ) {
//            ImportSysResourceVo isr = new ImportSysResourceVo();
//            BeanUtils.copyProperties(srf, isr);
//            isrList.add(isr);
//        }
//        ExcelExportUtil.createExcel(hsr, ImportSysResourceVo.class, isrList);
//    }

    @ApiOperation(value = "批量导入菜单", notes = "权限码(sys:resource:import)")
    @PostMapping(value = "/importSysResource")
    public Result<String> importSysResource(@PathVariable("file") MultipartFile file) {
        List<SysResourceBatchSaveVo> sysResourceBatchSaveVos = ExcelImportUtil.getDataByExcel(file, SysResourceBatchSaveVo.class);
        resourceService.importResource(sysResourceBatchSaveVos);

        return Result.ok("导入成功");
    }



    @GetMapping(value = "/getImportTemplate", produces = "application/octet-stream")
    @ApiOperation(value = "下载菜单模板", notes = "权限码(sys:resource:getImportTemplate)", produces = "application/octet-stream")
    public void getImportTemplate(HttpServletResponse response) {
        // 直接创建模板并返回
        String filePath = ExcelPropertiesUtils.getExcelFilePath();
        filePath += "/资源导入模板.xlsx";
        File file = new File(filePath);
        if (file.exists()) {
            log.info("磁盘中存在用户导入模板文件，不在重新生成");
        } else {
            ExcelExportUtil.createTemplate(filePath, SysResourceBatchSaveVo.class);
        }
        FileDownloadUtils.downloadFile(filePath, response);
    }

    @DeleteMapping(value = "/batchDelete")
    @ApiOperation(value = "批量删除菜单", notes = "权限码(sys:resource:batchDelete)")
    @SofnLog(value = "批量删除菜单", type = "删除")
    public Result<String> batchDelete(@RequestParam("ids") String ids){
        if(StringUtils.isBlank(ids)){
            return Result.error("请选择要删除的数据");
        }
        List<String> idsByStr = IdUtil.getIdsByStr(ids);
        resourceService.batchDelete(idsByStr);
        return Result.ok("删除成功");
    }


}
