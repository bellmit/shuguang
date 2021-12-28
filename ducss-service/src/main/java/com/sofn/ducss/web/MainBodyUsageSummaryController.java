package com.sofn.ducss.web;

import com.sofn.common.model.Result;
import com.sofn.common.utils.PageUtils;
import com.sofn.ducss.service.MainBodyUsageSummaryService;
import com.sofn.ducss.util.ExportUtil;
import com.sofn.ducss.vo.MainBodyUsageSummaryVo;
import com.sofn.ducss.vo.StrawUtilizeInfoAndDetailInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * 市场主体规模化利用汇总
 */
@Controller
@RequestMapping("/mainBodyUsageSummary")
@Api(tags = "市场主体规模化利用汇总")
public class MainBodyUsageSummaryController {

    @Autowired
    private MainBodyUsageSummaryService mainBodyUsageSummaryService;


    @GetMapping("/getList")
    @ApiOperation(value = "根据某个县的ID和某一年查询当前县的具体的数据", notes = "权限点(ducss:mainBodyUsageSummary:getList)")
    @ResponseBody
    public Result<PageUtils<List<MainBodyUsageSummaryVo>>> getList(@RequestParam(name = "year") @ApiParam(value = "年份", required = true) String year,
                                                                   @RequestParam(name = "areaId") @ApiParam(value = "区域Id", required = true) String areaId,
                                                                   @RequestParam(name = "orderBy", required = false) @ApiParam(value = "排序， fertilising：肥料化， forage：饲料化，fuel：燃料化，base：基料化，material：原料化， thisCount：本县来源， other：外县来源， countSum：合计") String orderBy,
                                                                   @RequestParam(name = "isDesc", required = false) @ApiParam(value = "是否倒序，Y 倒序") String isDesc,
                                                                   @RequestParam(name = "pageNo") @ApiParam(value = "当前页数", required = true) Integer pageNo,
                                                                   @RequestParam(name = "pageSize") @ApiParam(value = "每页显示条数", required = true) Integer pageSize) {
        PageUtils<List<MainBodyUsageSummaryVo>> listByCounty = mainBodyUsageSummaryService.getListByCounty(year, areaId, orderBy, isDesc, pageNo, pageSize);
        return Result.ok(listByCounty);
    }

    @GetMapping("/getCountByLevel")
    @ApiOperation(value = "根据某一个级别某个区域ID某一年份查询统计数量", notes = "权限点(ducss:mainBodyUsageSummary:getCountByLevel)")
    @ResponseBody
    public Result<MainBodyUsageSummaryVo> getCountByLevel(@RequestParam(name = "year") @ApiParam(value = "年份", required = true) String year,
                                                          @RequestParam(name = "areaId", required = false) @ApiParam(value = "区域Id，除了部级其他都要填一个具体的， 初始化时为当前登录用户所属区划ID， 程序不初始化，因为可能会下钻，就是一级一级的往下查询") String areaId,
                                                          @RequestParam(name = "level", required = false) @ApiParam(value = "级别 2 市级  3 省级   4 部级") String level,
                                                          @RequestParam(name = "orderBy", required = false) @ApiParam(value = "排序， fertilising：肥料化， forage：饲料化，fuel：燃料化，base：基料化，material：原料化， thisCount：本县来源， other：外县来源， countSum：合计") String orderBy,
                                                          @RequestParam(name = "isDesc", required = false) @ApiParam(value = "是否倒序，Y 倒序") String isDesc
    ) {
        //String level, String year, String areaId, String orderBy, String isDesc
//        List<MainBodyUsageSummaryVo> listByLevel = mainBodyUsageSummaryService.getCountByLevel(level, year, areaId);
        List<MainBodyUsageSummaryVo> list = mainBodyUsageSummaryService.getList(level, year, areaId, orderBy, isDesc);
        return Result.ok(list);
    }


    /**
     * 根据主体ID查询主体的填报信息
     *
     * @param utilizeId 填报主体ID
     * @return List<StrawUtilizeInfoAndDetailInfoVo>
     */
    @GetMapping("/getStrawUtilizeInfoAndDetailInfo")
    @ApiOperation(value = "查询某个主体的具体填报信息", notes = "权限点(ducss:mainBodyUsageSummary:getStrawUtilizeInfoAndDetailInfo)")
    @ResponseBody
    public Result<StrawUtilizeInfoAndDetailInfoVo> getStrawUtilizeInfoAndDetailInfo(@RequestParam(name = "utilizeId") @ApiParam(value = "主体ID", required = true) String utilizeId) {
        StrawUtilizeInfoAndDetailInfoVo strawUtilizeInfoAndDetailInfo = mainBodyUsageSummaryService.getStrawUtilizeInfoAndDetailInfo(utilizeId);
        return Result.ok(strawUtilizeInfoAndDetailInfo);
    }


    /**
     * 导出市场主体规模化利用汇总信息
     *
     * @param year   年度
     * @param areaId 区域ID  如果传入的是省级ID 就是省级
     */
    @GetMapping(value = "/exportMainBodyUsageInfo", produces = "application/octet-stream")
    @ApiOperation(value = " 导出市场主体规模化利用汇总信息， 县级只导出当前用户县的数据，市级用户导出当前市的所有县"
            , notes = "权限点(ducss:mainBodyUsageSummary:exportMainBodyUsageInfo)"
            , produces = "application/octet-stream")
    public void exportMainBodyUsageInfo(@RequestParam("year") @ApiParam(value = "年份", required = true) String year,
                                        @RequestParam(value = "areaId", required = false) @ApiParam(value = "区域,筛选条件时使用") String areaId,
                                        HttpServletResponse httpServletResponse) throws IOException {
//        ClassPathResource resource = new ClassPathResource("static/市场主体规模化秸秆利用量.xlsx");
        mainBodyUsageSummaryService.exportMainBodyUsageInfo(year, areaId, httpServletResponse);
    }


    @GetMapping(value = "/exportList")
    @ApiOperation(value = " 导出市场主体规模化利用汇总信息， 县级汇总信息"
            , notes = "权限点(ducss:mainBodyUsageSummary:exportMainBodyUsageInfo)")
    public Result<PageUtils<List<MainBodyUsageSummaryVo>>> exportList(@RequestParam(name = "year") @ApiParam(value = "年份", required = true) String year,
                                                                      @RequestParam(name = "areaId") @ApiParam(value = "区域Id", required = true) String areaId,
                                                                      @RequestParam(name = "selectFields", required = true) @ApiParam(value = "需要导出的字段集合") String selectFields,
                                                                      @RequestParam(name = "orderBy", required = false) @ApiParam(value = "排序， fertilising：肥料化， forage：饲料化，fuel：燃料化，base：基料化，material：原料化， thisCount：本县来源， other：外县来源， countSum：合计") String orderBy,
                                                                      @RequestParam(name = "isDesc", required = false) @ApiParam(value = "是否倒序，Y 倒序") String isDesc,
                                                                      HttpServletResponse response) {
        PageUtils<List<MainBodyUsageSummaryVo>> listByCounty = mainBodyUsageSummaryService.getListByCounty(year, areaId, orderBy, isDesc, 0, 99999);
        ExportUtil.createExcel(ExportUtil.filterField(MainBodyUsageSummaryVo.class, selectFields), listByCounty.getList(), response, "市场主体规模化利用汇总.xlsx");
        return Result.ok(listByCounty);
    }


    @GetMapping(value = "/exportCountByLevel",produces = "application/octet-stream")
    @ApiOperation(value = " 导出市场主体规模化利用汇总信息， 市级以上导出汇总信息"
            , notes = "权限点(ducss:mainBodyUsageSummary:exportMainBodyUsageInfo)",produces = "application/octet-stream")
    public Result<MainBodyUsageSummaryVo> exportCountByLevel(@RequestParam(name = "year") @ApiParam(value = "年份", required = true) String year,
                                                             @RequestParam(name = "areaId", required = false) @ApiParam(value = "区域Id，除了部级其他都要填一个具体的， 初始化时为当前登录用户所属区划ID， 程序不初始化，因为可能会下钻，就是一级一级的往下查询") String areaId,
                                                             @RequestParam(name = "level", required = false) @ApiParam(value = "级别 2 市级  3 省级   4 部级") String level,
                                                             @RequestParam(name = "selectFields", required = true) @ApiParam(value = "需要导出的字段集合") String selectFields,
                                                             @RequestParam(name = "orderBy", required = false) @ApiParam(value = "排序， fertilising：肥料化， forage：饲料化，fuel：燃料化，base：基料化，material：原料化， thisCount：本县来源， other：外县来源， countSum：合计") String orderBy,
                                                             @RequestParam(name = "isDesc", required = false) @ApiParam(value = "是否倒序，Y 倒序") String isDesc,
                                                             HttpServletResponse response
    ) {
        List<MainBodyUsageSummaryVo> list = mainBodyUsageSummaryService.getList(level, year, areaId, orderBy, isDesc);
        // 导出时保留两位小数
        transferList(list);
        ExportUtil.createExcel(ExportUtil.filterField(MainBodyUsageSummaryVo.class, selectFields), list, response, "市场主体规模化利用汇总.xlsx");
        return Result.ok(list);
    }

    private void transferList(List<MainBodyUsageSummaryVo> list) {
        for (MainBodyUsageSummaryVo sp : list) {
            sp.setFertilising(transferField(sp.getFertilising(), 2));
            sp.setForage(transferField(sp.getForage(), 2));
            sp.setFuel(transferField(sp.getFuel(), 2));
            sp.setBase(transferField(sp.getBase(), 2));
            sp.setMaterial(transferField(sp.getMaterial(), 2));
            sp.setCount(transferField(sp.getCount(), 2));
            sp.setThisCount(transferField(sp.getThisCount(), 2));
            sp.setOther(transferField(sp.getOther(), 2));
        }
    }

    private String transferField(String field, int length) {
        BigDecimal result = new BigDecimal(field);
        return result.setScale(length, RoundingMode.HALF_UP).toPlainString();
    }


}
