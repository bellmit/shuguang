package com.sofn.ahhdp.web;

import com.sofn.ahhdp.model.Farm;
import com.sofn.ahhdp.model.FarmRecord;
import com.sofn.ahhdp.service.FarmRecordService;
import com.sofn.ahhdp.service.FarmService;
import com.sofn.ahhdp.vo.DropDownVo;
import com.sofn.common.model.Result;
import com.sofn.common.utils.PageUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-04-21 15:54
 */
@Slf4j
@Api(tags = "国家畜禽遗传资源保护场发布模块接口")
@RequestMapping("/farmPublish")
@RestController
public class FarmPublishController {
    @Autowired
    private FarmRecordService farmRecordService;
    @Autowired
    private FarmService farmService;

    @ApiOperation(value = "获取年份")
    @GetMapping("/getYears")
    public Result<List<DropDownVo>> getYears() {
        return Result.ok(farmRecordService.getYears());
    }

    @ApiOperation(value = "分页查询变更记录(全部)")
    @GetMapping("/listPageForAll")
    public Result<PageUtils<Farm>> listPageForAll(
            @ApiParam(value = "年份", required = true) @RequestParam() String year,
            @ApiParam(value = "pageNo", required = true) @RequestParam() Integer pageNo,
            @ApiParam(value = "pageSize", required = true) @RequestParam() Integer pageSize) {
        return Result.ok(farmService.listByParams(getQueryMap(year, null), pageNo, pageSize));
    }

    @ApiOperation(value = "分页查询变更记录(名称变更)")
    @GetMapping("/listPageForName")
    public Result<PageUtils<FarmRecord>> listPageForName(
            @ApiParam(value = "年份", required = true) @RequestParam() String year,
            @ApiParam(value = "pageNo", required = true) @RequestParam() Integer pageNo,
            @ApiParam(value = "pageSize", required = true) @RequestParam() Integer pageSize) {
        return Result.ok(farmRecordService.listByParamsForPublish(getQueryMap(year, "name"), pageNo, pageSize));
    }

    @ApiOperation(value = "分页查询变更记录(单位变更)")
    @GetMapping("/listPageForCompany")
    public Result<PageUtils<FarmRecord>> listPageForCompany(
            @ApiParam(value = "年份", required = true) @RequestParam() String year,
            @ApiParam(value = "pageNo", required = true) @RequestParam() Integer pageNo,
            @ApiParam(value = "pageSize", required = true) @RequestParam() Integer pageSize) {
        return Result.ok(farmRecordService.listByParamsForPublish(getQueryMap(year, "company"), pageNo, pageSize));
    }

    @ApiOperation(value = "分页查询变更记录(所属区域变更)")
    @GetMapping("/listPageForRange")
    public Result<PageUtils<FarmRecord>> listPageForRange(
            @ApiParam(value = "年份", required = true) @RequestParam() String year,
            @ApiParam(value = "pageNo", required = true) @RequestParam() Integer pageNo,
            @ApiParam(value = "pageSize", required = true) @RequestParam() Integer pageSize) {
        return Result.ok(farmRecordService.listByParamsForPublish(getQueryMap(year, "range"), pageNo, pageSize));
    }

//    @GetMapping(value = "/exportForAll", produces = "application/octet-stream")
//    @ApiOperation(value = "导出新增国家畜禽遗传资源保护场)", produces = "application/octet-stream")
//    public void exportForAll(
//            @ApiParam(value = "年份", required = true) @RequestParam(required = false) String year,
//            HttpServletResponse response) {
//        farmService.export(getQueryMap(year, ""), response);
//    }
//
//    @GetMapping(value = "/exportForName", produces = "application/octet-stream")
//    @ApiOperation(value = "导出变更记录(名称变更)", produces = "application/octet-stream")
//    public void exportForName(
//            @ApiParam(value = "年份", required = true) @RequestParam(required = false) String year,
//            HttpServletResponse response) {
//        farmRecordService.exportForName(getQueryMap(year, "name"), response);
//    }
//
//    @GetMapping(value = "/exportForCompany", produces = "application/octet-stream")
//    @ApiOperation(value = "导出变更记录(单位变更)", produces = "application/octet-stream")
//    public void exportForCompany(
//            @ApiParam(value = "年份", required = true) @RequestParam(required = false) String year,
//            HttpServletResponse response) {
//        farmRecordService.exportForCompany(getQueryMap(year, "company"), response);
//    }



    /**
     * 封装查询参数
     */
    private Map<String, Object> getQueryMap(String year, String type) {
        Map map = new HashMap<String, Object>(1);
        map.put("year", year);
        map.put("type", type);
        return map;
    }
}
