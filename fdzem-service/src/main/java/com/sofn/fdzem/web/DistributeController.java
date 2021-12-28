package com.sofn.fdzem.web;

import com.sofn.common.log.SofnLog;
import com.sofn.common.model.Result;
import com.sofn.common.utils.PageUtils;
import com.sofn.fdzem.model.Distribute;
import com.sofn.fdzem.model.Index;
import com.sofn.fdzem.service.DistributeService;
import com.sofn.fdzem.vo.DistributeVo;
import com.sofn.fdzem.vo.MonitroIdVo;
import com.sofn.fdzem.vo.SysOrgVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Api(tags = "区域监测中心管理", description = "")
@RestController
@RequestMapping("/distribute")
public class DistributeController {
    @Autowired
    DistributeService distributeService;

    @GetMapping("listPage")
    @SofnLog("区域监测管理分页查询")
    @RequiresPermissions("fdzem:distribute:list")
    @ApiOperation(value = "区域监测管理分页查询", notes = "区域监测管理分页查询")
    public Result<PageUtils<SysOrgVo>> listPage(@ApiParam(name = "pageNum", value = "当前页码", required = true) @RequestParam(value = "pageNum", required = true) Integer pageNum,
                                                @ApiParam(name = "pageSize", value = "每页显示条数", required = true) @RequestParam(value = "pageSize", required = true) Integer pageSize) {
        return Result.ok(distributeService.listPage(pageNum, pageSize));
    }

    @GetMapping("/getById/{id}")
    @SofnLog("查看监测站分配")
    @RequiresPermissions("fdzem:distribute:view")
    @ApiOperation(value = "查看监测站分配", notes = "查看监测站分配")
    @ResponseBody
    public Result<DistributeVo> getById(@PathVariable("id") String id) {
        DistributeVo distributeVo = distributeService.getById(id);
        return Result.ok(distributeVo, "成功获取数据");
    }

    @PutMapping("/update")
    @SofnLog("保存监测站分配数据")
    //@RequiresPermissions("fdzem:distribute:update")
    @ApiOperation(value = "保存监测站分配数据", notes = "保存监测站分配数据")
    public Result updateIndex(@RequestBody MonitroIdVo monitroIdVo) {
        distributeService.updateDistribute(monitroIdVo.getId(), monitroIdVo.getMonitroIds());
        return Result.ok("修改成功");
    }

    /*@PostMapping("/insert")
    @SofnLog("新增监测中心")
    //@RequiresPermissions("fdzem:distribute:insert")
    @ApiOperation(value = "新增监测中心", notes = "新增监测中心")
    public Result saveTopIndex(@RequestBody Distribute distribute) {
        distributeService.insert(distribute);
        return Result.ok("保存成功");
    }*/
}
