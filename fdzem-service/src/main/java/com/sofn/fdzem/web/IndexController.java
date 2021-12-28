package com.sofn.fdzem.web;

import com.sofn.common.log.SofnLog;
import com.sofn.common.model.Result;
import com.sofn.common.utils.PageUtils;
import com.sofn.fdzem.model.Index;
import com.sofn.fdzem.service.IndexService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@Api(tags = "指标管理界面接口", description = "")
@RestController
@RequestMapping("/index")
public class IndexController {
    @Autowired
    private IndexService indexService;

    @GetMapping("/listPage")
    @SofnLog("指标管理界面分页查询")
    @RequiresPermissions("fdzem:index:list")
    @ApiOperation(value = "指标的分页查询", notes = "指标的分页查询")
    public Result<PageUtils<Index>> listPage(@ApiParam(name = "pageNum", value = "当前页码", required = true) @RequestParam(value = "pageNum", required = true) Integer pageNum,
                                     @ApiParam(name = "pageSize", value = "每页显示条数", required = true) @RequestParam(value = "pageSize", required = true) Integer pageSize,
                                     @ApiParam(name = "indexType", value = "指标类型", required = false) @RequestParam(value = "indexType", required = false) String indexType,
                                     @ApiParam(name = "indexName", value = "指标名称", required = false) @RequestParam(value = "indexName", required = false) String indexName,
                                     @ApiParam(name = "startTime", value = "起始添加时间", required = false) @RequestParam(value = "startTime", required = false) String startTime,
                                     @ApiParam(name = "endTime", value = "截至添加时间", required = false) @RequestParam(value = "endTime", required = false) String endTime) {
        return Result.ok(indexService.listPage(pageNum, pageSize, indexType, indexName, startTime, endTime));
    }

    @PostMapping("/insert")
    @SofnLog("指标添加")
    @RequiresPermissions("fdzem:index:insert")
    @ApiOperation(value = "指标添加", notes = "指标添加")
    public Result saveTopIndex(@RequestBody Index index) {
        indexService.saveIndex(index);
        return Result.ok("保存成功");
    }

    @GetMapping("/getById/{id}")
    @SofnLog("通过指标id获取指标数据")
    @RequiresPermissions("fdzem:index:view")
    @ApiOperation(value = "通过指标id获取指标数据", notes = "通过指标id获取指标数据")
    public Result<Index> getById(@PathVariable("id") Long id) {
        Index index = indexService.getById(id);
        return Result.ok(index, "成功获取数据");
    }

    @PutMapping("/update")
    @SofnLog("编辑修改指标数据")
    @RequiresPermissions("fdzem:index:update")
    @ApiOperation(value = "编辑修改指标数据", notes = "编辑修改指标数据，一级指标只能不能改为二级指标，二级指标不能改为一级指标不能修改指标类型，只能修改指标的其他相关属性")
    public Result updateIndex(@RequestBody Index index) {
        indexService.updateIndex(index);
        return Result.ok("修改成功");
    }

    @PutMapping("/updateStatus")
    @SofnLog("编辑修改指标状态")
    @RequiresPermissions("fdzem:index:updateStatus")
    @ApiOperation(value = "编辑修改指标状态", notes = "是否启用")
    @Transactional(rollbackFor = Exception.class)
    public Result updateIndexStatus(@ApiParam(name = "id", value = "id", required = true) @RequestParam(value = "id", required = true) Long id,
                                    @ApiParam(name = "state", value = "指标状态(1是0否)", required = true) @RequestParam(value = "state", required = true) Integer state) {
        indexService.updateIndexStatus(id, state);
        return Result.ok("修改成功");
    }


    @GetMapping("/getTopIndex")
    @SofnLog("获得一级指标的id和name")
    @ApiOperation(value = "获得一级指标的id和name", notes = "获得一级指标的id和name")
    public Result<Index> getTopIndex() {
        List<Index> index = indexService.getTopIndex();
        return Result.ok(index, "成功获取数据");
    }
}
