package com.sofn.ahhdp.web;

import com.sofn.ahhdp.enums.AuditStatusEnum;
import com.sofn.ahhdp.model.Zone;
import com.sofn.ahhdp.model.ZoneRecord;
import com.sofn.ahhdp.service.ZoneApplyService;
import com.sofn.ahhdp.service.ZoneRecordService;
import com.sofn.ahhdp.service.ZoneService;
import com.sofn.ahhdp.vo.DropDownVo;
import com.sofn.common.model.Result;
import com.sofn.common.utils.PageUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Api(tags = "国家畜禽遗传资源保护区发布模块接口")
@RequestMapping("/zonePublish")
@RestController
public class ZonePublishController {

    @Autowired
    private ZoneRecordService zoneRecordService;
    @Autowired
    private ZoneService zoneService;

    @ApiOperation(value = "获取年份")
    @GetMapping("/getYears")
    public Result<List<DropDownVo>> getYears() {
        return Result.ok(zoneRecordService.getYears());
    }

    @ApiOperation(value = "分页查询变更记录(全部)")
    @GetMapping("/listPageForAll")
    public Result<PageUtils<Zone>> listPageForAll(
            @ApiParam(value = "年份", required = true) @RequestParam() String year,
            @ApiParam(value = "pageNo", required = true) @RequestParam() Integer pageNo,
            @ApiParam(value = "pageSize", required = true) @RequestParam() Integer pageSize) {
        return Result.ok(zoneService.listByParams(getQueryMap(year, null), pageNo, pageSize));
    }

    @ApiOperation(value = "分页查询变更记录(名称变更)")
    @GetMapping("/listPageForName")
    public Result<PageUtils<ZoneRecord>> listPageForName(
            @ApiParam(value = "年份", required = true) @RequestParam() String year,
            @ApiParam(value = "pageNo", required = true) @RequestParam() Integer pageNo,
            @ApiParam(value = "pageSize", required = true) @RequestParam() Integer pageSize) {
        return Result.ok(zoneRecordService.listByParamsForPublish(getQueryMap(year, "name"), pageNo, pageSize));
    }

    @ApiOperation(value = "分页查询变更记录(单位变更)")
    @GetMapping("/listPageForCompany")
    public Result<PageUtils<ZoneRecord>> listPageForCompany(
            @ApiParam(value = "年份", required = true) @RequestParam() String year,
            @ApiParam(value = "pageNo", required = true) @RequestParam() Integer pageNo,
            @ApiParam(value = "pageSize", required = true) @RequestParam() Integer pageSize) {
        return Result.ok(zoneRecordService.listByParamsForPublish(getQueryMap(year, "company"), pageNo, pageSize));
    }

    @ApiOperation(value = "分页查询变更记录(范围变更)")
    @GetMapping("/listPageForRange")
    public Result<PageUtils<ZoneRecord>> listPageForRange(
            @ApiParam(value = "年份", required = true) @RequestParam() String year,
            @ApiParam(value = "pageNo", required = true) @RequestParam() Integer pageNo,
            @ApiParam(value = "pageSize", required = true) @RequestParam() Integer pageSize) {
        return Result.ok(zoneRecordService.listByParamsForPublish(getQueryMap(year, "range"), pageNo, pageSize));
    }

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
