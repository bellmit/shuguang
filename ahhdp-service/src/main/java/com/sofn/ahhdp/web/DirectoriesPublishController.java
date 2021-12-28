package com.sofn.ahhdp.web;


import com.sofn.ahhdp.model.Directories;
import com.sofn.ahhdp.model.DirectoriesRecord;
import com.sofn.ahhdp.service.DirectoriesRecordService;
import com.sofn.ahhdp.service.DirectoriesService;
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
 * @Date: 2020-04-21 11:40
 */
@Slf4j
@Api(tags = "国家畜禽遗传资源保护名录发布模块接口")
@RequestMapping("/dPublish")
@RestController
public class DirectoriesPublishController {
    @Autowired
    private DirectoriesService directoriesService;
    @Autowired
    private DirectoriesRecordService directoriesRecordService;

    @ApiOperation(value = "获取年份")
    @GetMapping("/getYears")
    public Result<List<DropDownVo>> getYears() {
        return Result.ok(directoriesRecordService.getYears());
    }

    @ApiOperation(value = "分页查询变更记录(全部)")
    @GetMapping("/listPageForAll")
    public Result<PageUtils<Directories>> listPageForAll(
            @ApiParam(value = "年份", required = true) @RequestParam() String year,
            @ApiParam(value = "pageNo", required = true) @RequestParam() Integer pageNo,
            @ApiParam(value = "pageSize", required = true) @RequestParam() Integer pageSize) {
        return Result.ok(directoriesService.listByParams(getQueryMap(year, null), pageNo, pageSize));
    }

    @ApiOperation(value = "分页查询变更记录(名称变更)")
    @GetMapping("/listPageForName")
    public Result<PageUtils<DirectoriesRecord>> listPageForName(
            @ApiParam(value = "年份", required = true) @RequestParam() String year,
            @ApiParam(value = "pageNo", required = true) @RequestParam() Integer pageNo,
            @ApiParam(value = "pageSize", required = true) @RequestParam() Integer pageSize) {
        return Result.ok(directoriesRecordService.listByParamsForPublish(getQueryMap(year, "name"), pageNo, pageSize));
    }

    @ApiOperation(value = "分页查询变更记录(所属区域变更)")
    @GetMapping("/listPageForCompany")
    public Result<PageUtils<DirectoriesRecord>> listPageForCompany(
            @ApiParam(value = "年份", required = true) @RequestParam() String year,
            @ApiParam(value = "pageNo", required = true) @RequestParam() Integer pageNo,
            @ApiParam(value = "pageSize", required = true) @RequestParam() Integer pageSize) {
        return Result.ok(directoriesRecordService.listByParamsForPublish(getQueryMap(year, "company"), pageNo, pageSize));
    }

    @ApiOperation(value = "分页查询变更记录(品种变更)")
    @GetMapping("/listPageForRange")
    public Result<PageUtils<DirectoriesRecord>> listPageForRange(
            @ApiParam(value = "年份", required = true) @RequestParam() String year,
            @ApiParam(value = "pageNo", required = true) @RequestParam() Integer pageNo,
            @ApiParam(value = "pageSize", required = true) @RequestParam() Integer pageSize) {
        return Result.ok(directoriesRecordService.listByParamsForPublish(getQueryMap(year, "name"), pageNo, pageSize));
    }

//    @GetMapping(value = "/exportForAll", produces = "application/octet-stream")
//    @ApiOperation(value = "导出新增国家畜禽遗传资源保护名录)", produces = "application/octet-stream")
//    public void exportForAll(
//            @ApiParam(value = "年份", required = true) @RequestParam(required = false) String year,
//            HttpServletResponse response) {
//        directoriesService.export(getQueryMap(year, ""), response);
//    }

//    @GetMapping(value = "/exportForName", produces = "application/octet-stream")
//    @ApiOperation(value = "导出变更记录(名称变更)", produces = "application/octet-stream")
//    public void exportForName(
//            @ApiParam(value = "年份", required = true) @RequestParam(required = false) String year,
//            HttpServletResponse response) {
//        directoriesRecordService.exportForName(getQueryMap(year, "name"), response);
//    }
//
//    @GetMapping(value = "/exportForCompany", produces = "application/octet-stream")
//    @ApiOperation(value = "导出变更记录(单位变更)", produces = "application/octet-stream")
//    public void exportForCompany(
//            @ApiParam(value = "年份", required = true) @RequestParam(required = false) String year,
//            HttpServletResponse response) {
//        directoriesRecordService.exportForCompany(getQueryMap(year, "company"), response);
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
