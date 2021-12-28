package com.sofn.fdzem.web;

import com.github.pagehelper.PageInfo;
import com.sofn.common.model.Result;
import com.sofn.common.utils.PageUtils;
import com.sofn.fdzem.model.BatchManagement;
import com.sofn.fdzem.service.BatchManagementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Api(tags = "批次管理相关接口" )
@RestController
@RequestMapping("/batchManagement")
public class BatchManagementController {

    @Autowired
    private BatchManagementService batchManagementService;

    @GetMapping("/listPage")
    @RequiresPermissions("fdzem:batchManagement:list")
    @ApiOperation(value = "获取所有任务分页及条件查询")
    public Result<List<BatchManagement>>  getSurveyTasksPage(@RequestParam(required = false,defaultValue = "1") @ApiParam(value = "当前页数") Integer pageNo,
                                                                   @RequestParam(required = false,defaultValue = "10") @ApiParam(value = "每页显示条数") Integer pageSize,
                                                                   @RequestParam(required = false) @ApiParam(value = "监测批次") String name,
                                                                   @RequestParam(required = false) @ApiParam(value = "采样时间-起") String samplingTimeLeft,
                                                                   @RequestParam(required = false) @ApiParam(value = "采样时间-止") String samplingTimeRight,
                                                                   @RequestParam(required = false) @ApiParam(value = "报送时间-起") String submittedTimeLeft,
                                                                   @RequestParam(required = false) @ApiParam(value = "报送时间-止") String submittedTimeRight,
                                                             @RequestParam(required = false) @ApiParam(value = "所属监测站id") String monitoringStationTaskId){
        pageNo = pageNo == null ? 1 : pageNo;
        pageSize = pageSize == null ? 5 : pageSize;
        PageInfo<BatchManagement> listPage = batchManagementService.selectListByQuery(pageNo,pageSize,name,samplingTimeLeft,samplingTimeRight,submittedTimeLeft,submittedTimeRight,monitoringStationTaskId);
        return Result.ok(PageUtils.getPageUtils(listPage));

    }

    @PostMapping("/insert")
    @RequiresPermissions("fdzem:batchManagement:insert")
    @ApiOperation(value = "添加对应任务,需将所属监测站的id放入monitoringStationTaskId字段中")
    public Result<BatchManagement> insertBatchManagement(@RequestBody BatchManagement batchManagement) {
            batchManagementService.insertAll(batchManagement);
            return Result.ok("操作成功");
    }

    @DeleteMapping("/delete")
    @RequiresPermissions("fdzem:batchManagement:delete")
    @ApiOperation(value = "通过id删除批次")
    public Result<BatchManagement> deleteById(String id) {
        try {
            batchManagementService.remove(id);
            return Result.ok("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(e);
        }
    }

    @PutMapping("/update")
    @RequiresPermissions("fdzem:batchManagement:update")
    @ApiOperation(value = "更新对应批次")
    public Result<BatchManagement> updateSurveyTasksById(@RequestBody BatchManagement batchManagement) {
        batchManagementService.update(batchManagement);
        return Result.ok("操作成功");

    }

    @GetMapping("/view")
    @RequiresPermissions("fdzem:batchManagement:view")
    @ApiOperation(value = "详情")
    public Result<BatchManagement> view(String id) {
        try {
            BatchManagement batchManagement = batchManagementService.view(id);
            return new Result<>(200, "获取成功", batchManagement);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(e);
        }
    }
}
