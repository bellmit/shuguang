package com.sofn.ducss.web;


import com.sofn.ducss.enums.CropsEnum;
import com.sofn.ducss.enums.LeavingTypeEnum;
import com.sofn.ducss.enums.ReturnTypeEnum;
import com.sofn.ducss.model.basemodel.Result;
import com.sofn.ducss.service.AggregateService;
import com.sofn.ducss.service.impl.AggregateServiceImpl;
import com.sofn.ducss.util.ExportUtil;
import com.sofn.ducss.util.PageUtils;
import com.sofn.ducss.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Api(value = "数据汇总", tags = "数据汇总模块相关接口")
@RestController
@RequestMapping("/aggregate")
public class AggregateController {
    @Autowired
    private AggregateService aggregateService;

    @ApiOperation(value = "产生量汇总数据")
    @GetMapping("/produceData")
//    @RequiresPermissions("")
    public Result<StrawProduceResVo> findStrawProduceData(AggregateQueryVo queryVo) {
        List<StrawProduceResVo> list = aggregateService.getStrawProduceData(queryVo);
        return Result.ok(list);
    }

    @ApiOperation(value = "利用量汇总数据")
    @GetMapping("/utilzeData")
    public Result<StrawUtilizeResVo> findStrawUtilzeData(AggregateQueryVo queryVo) {
        List<StrawUtilizeResVo> list = aggregateService.getStrawUtilzeData(queryVo);
        return Result.ok(list);
    }

    @ApiOperation(value = "产生量与利用量汇总数据")
    @GetMapping("/produceAndUtilzeData")
    public Result<StrawProduceUtilizeResVo> findStrawProduceAndUtilzeData(AggregateQueryVo queryVo) {
        List<StrawProduceUtilizeResVo> list = aggregateService.findStrawProduceAndUtilzeData(queryVo);
        return Result.ok(list);
    }

    @ApiOperation(value = "导出产生量与利用量汇总数据")
    @GetMapping(value = "/exportStrawUtilize", produces = "application/octet-stream")
    public Result exportStrawUtilize(AggregateQueryVo queryVo, HttpServletResponse response) {
        List<StrawProduceUtilizeResVo> list = aggregateService.findStrawProduceAndUtilzeData(queryVo);
        ExportUtil.createExcel(StrawProduceUtilizeResVo.class, list, response, "产生量与利用量汇总.xlsx");
        return Result.ok("导出成功！");
    }

    @ApiOperation(value = "产生量与利用量汇总数据2")
    @GetMapping("/produceAndUtilzeData2")
    public Result<StrawProduceUtilizeResVo2> findStrawProduceAndUtilzeData2(AggregateQueryVo queryVo) {
        List<StrawProduceUtilizeResVo2> list = aggregateService.findStrawProduceAndUtilzeData2(queryVo);
        return Result.ok(list);
    }

    @ApiOperation(value = "导出产生量与利用量汇总数据2")
    @GetMapping(value = "/exportStrawUtilize2", produces = "application/octet-stream")
    public Result exportStrawUtilize2(AggregateQueryVo queryVo, HttpServletResponse response) {
        List<StrawProduceUtilizeResVo2> list = aggregateService.findStrawProduceAndUtilzeData2(queryVo);
        //导出时保留两位小数
        for (StrawProduceUtilizeResVo2 sp : list) {
            sp.setTheoryResource(sp.getTheoryResource().setScale(2, RoundingMode.HALF_UP));
            sp.setCollectResource(sp.getCollectResource().setScale(2, RoundingMode.HALF_UP));
            sp.setProStrawUtilize(sp.getProStrawUtilize().setScale(2, RoundingMode.HALF_UP));
            sp.setComprehensive(sp.getComprehensive().setScale(2, RoundingMode.HALF_UP));
            sp.setSum(sp.getSum().setScale(2, RoundingMode.HALF_UP));
            sp.setFertilising(sp.getFertilising().setScale(2, RoundingMode.HALF_UP));
            sp.setForage(sp.getForage().setScale(2, RoundingMode.HALF_UP));
            sp.setFuel(sp.getFuel().setScale(2, RoundingMode.HALF_UP));
            sp.setBase(sp.getBase().setScale(2, RoundingMode.HALF_UP));
            sp.setMaterial(sp.getMaterial().setScale(2, RoundingMode.HALF_UP));
            sp.setComprehensiveIndex(sp.getComprehensiveIndex().setScale(2, RoundingMode.HALF_UP));
            sp.setIndustrializationIndex(sp.getIndustrializationIndex().setScale(2, RoundingMode.HALF_UP));
        }
        ExportUtil.createExcel(ExportUtil.filterField(StrawProduceUtilizeResVo2.class, queryVo.getSelectFields()), list, response, "产生量与利用量汇总.xlsx");
        return Result.ok("导出成功！");
    }

    @ApiOperation(value = "市场化主体利用量汇总")
    @GetMapping("/mainUtilizeData")
    public Result<MainUtilizeResVo> findMainUtilizeData(AggregateMainUtilizeQueryVo queryVo) {
        PageUtils<MainUtilizeResVo> list = aggregateService.findMainUtilizeDataPage(queryVo);
        return Result.ok(list);
    }

    @ApiOperation(value = "查看指定主体利用量信息")
    @GetMapping("/showMainUtilize")
    public Result<StrawUtilizeVo> showMainUtilize(
            @RequestParam(value = "mainId") @ApiParam(name = "mainId", value = "mainId") String mainId) {
        StrawUtilizeVo su = aggregateService.findMainUtilizeOneData(mainId);
        return Result.ok(su);
    }

    @ApiOperation(value = "导出市场化主体利用量汇总数据")
    @GetMapping(value = "/exportMainUtilize", produces = "application/octet-stream")
    public Result exportMainUtilize(AggregateMainUtilizeQueryVo queryVo, HttpServletResponse response) {
        List<MainUtilizeResVo> list = aggregateService.findMainUtilizeData(queryVo);
        ExportUtil.createExcel(MainUtilizeResVo.class, list, response, "市场化主体利用量汇总.xlsx");
        return Result.ok("导出成功！");
    }

    //只能查已发布的数据
    @ApiOperation(value = "区域信息汇总")
    @GetMapping("/areaUtilizeData")
    public Result<StrawProduceUtilizeResVo> findAreaUtilizeData(AggregateQueryVo queryVo) {
        List<StrawProduceUtilizeResVo> list = aggregateService.findAreaUtilizeData(queryVo);
        return Result.ok(list);
    }


    @ApiOperation(value = "产生量汇总数据2")
    @GetMapping("/produceData2")
//    @RequiresPermissions("")
    public Result<StrawProduceResVo2> findStrawProduceData2(AggregateQueryVo queryVo) {
        List<StrawProduceResVo2> list = aggregateService.getStrawProduceData2(queryVo);
        return Result.ok(list);
    }

    @ApiOperation(value = "导出产生量汇总数据2")
    @GetMapping(value = "/exportProduceData2", produces = "application/octet-stream")
    public Result exportProduceData2(AggregateQueryVo queryVo, HttpServletResponse response) {
        List<StrawProduceResVo2> list = aggregateService.getStrawProduceData2(queryVo);
        // 导出合并其他细项
        List<String> allNames = CropsEnum.getAllNames();
        List<StrawProduceResVo2> otherList = list.stream().filter(e -> !allNames.contains(e.getStrawType()) && !"sum".equals(e.getStrawType())).collect(Collectors.toList());
        StrawProduceResVo2 otherRes = AggregateServiceImpl.addSumProduce2(otherList);
        otherRes.setStrawName("其他");
        list = list.stream().filter(e -> allNames.contains(e.getStrawType())).collect(Collectors.toList());
        list.add(otherRes);
        //导出时保留两位小数
        for (StrawProduceResVo2 sp : list) {
            sp.setTheoryResource(sp.getTheoryResource().setScale(2, RoundingMode.HALF_UP));
            sp.setTheoryResourceRate(sp.getTheoryResourceRate().setScale(2, RoundingMode.HALF_UP));
            sp.setCollectResource(sp.getCollectResource().setScale(2, RoundingMode.HALF_UP));
            sp.setCollectResourceRate(sp.getCollectResourceRate().setScale(2, RoundingMode.HALF_UP));
            sp.setGrainYield(sp.getGrainYield().setScale(2, RoundingMode.HALF_UP));
            sp.setSeedArea(sp.getSeedArea().setScale(2, RoundingMode.HALF_UP));
        }
        ExportUtil.createExcel(ExportUtil.filterField(StrawProduceResVo2.class, queryVo.getSelectFields()), list, response, "产生量汇总.xlsx");
        return Result.ok("导出成功！");
    }


    @ApiOperation(value = "农作物秸秆利用量汇总数据2")
    @GetMapping("/utilzeData2")
    public Result<StrawUtilizeResVo3> findStrawUtilzeData2(AggregateQueryVo queryVo) {
        //获取数据
        List<StrawUtilizeResVo3> list = aggregateService.getStrawUtilzeData2(queryVo);
        return Result.ok(list);
    }

    @ApiOperation(value = "导出利用量汇总数据2")
    @GetMapping(value = "/exportUtilzeData2", produces = "application/octet-stream")
    public Result exportUtilzeData2(AggregateQueryVo queryVo, HttpServletResponse response) {
        List<StrawUtilizeResVo3> list = aggregateService.getStrawUtilzeData2(queryVo);
        // 导出合并其他细项
        List<String> allNames = CropsEnum.getAllNames();
        List<StrawUtilizeResVo3> otherList = list.stream().filter(e -> !allNames.contains(e.getStrawType()) && !"sum".equals(e.getStrawType())).collect(Collectors.toList());
        StrawUtilizeResVo3 otherRes = AggregateServiceImpl.addSumUtilize2(otherList);
        otherRes.setStrawName("其他");
        list = list.stream().filter(e -> allNames.contains(e.getStrawType())).collect(Collectors.toList());
        list.add(otherRes);
        //导出时保留两位小数
        for (StrawUtilizeResVo3 sp : list) {
            sp.setStrawUsage(sp.getStrawUsage().setScale(2, RoundingMode.HALF_UP));
            sp.setComprehensiveRate(sp.getComprehensiveRate().setScale(2, RoundingMode.HALF_UP));
            sp.setAllTotal(sp.getAllTotal().setScale(2, RoundingMode.HALF_UP));
            sp.setFertilizer(sp.getFertilizer().setScale(2, RoundingMode.HALF_UP));
            sp.setFuel(sp.getFuel().setScale(2, RoundingMode.HALF_UP));
            sp.setBasic(sp.getBasic().setScale(2, RoundingMode.HALF_UP));
            sp.setRawMaterial(sp.getRawMaterial().setScale(2, RoundingMode.HALF_UP));
            sp.setFeed(sp.getFeed().setScale(2, RoundingMode.HALF_UP));
            sp.setOther(sp.getOther().setScale(2, RoundingMode.HALF_UP));
            sp.setYieldExport(sp.getYieldExport().setScale(2, RoundingMode.HALF_UP));
            sp.setComprehensiveIndex(sp.getComprehensiveIndex().setScale(2, RoundingMode.HALF_UP));
            sp.setIndustrializationIndex(sp.getIndustrializationIndex().setScale(2, RoundingMode.HALF_UP));
        }
        ExportUtil.createExcel(ExportUtil.filterField(StrawUtilizeResVo3.class, queryVo.getSelectFields()), list, response, "利用量汇总.xlsx");
        return Result.ok("导出成功！");
    }

    @ApiOperation(value = "农作物秸秆还田离田汇总")
    @GetMapping("/ReturnLeaveSumData")
    public Result<ReturnLeaveSumVo> findReturnLeaveSumData(AggregateQueryVo queryVo) {
        List<ReturnLeaveSumVo> list = aggregateService.findReturnLeaveSumData(queryVo);
        return Result.ok(list);
    }

    @ApiOperation(value = "导出还田离田情况汇总数据2")
    @GetMapping(value = "/exportReturnLeaveSumData", produces = "application/octet-stream")
    public Result exportReturnLeaveSumData(AggregateQueryVo queryVo, HttpServletResponse response) {
        List<ReturnLeaveSumVo> list = aggregateService.findReturnLeaveSumData(queryVo);
        // 导出合并其他细项
        List<String> allNames = CropsEnum.getAllNames();
        List<ReturnLeaveSumVo> otherList = list.stream().filter(e -> !allNames.contains(e.getStrawType()) && !"sum".equals(e.getStrawType())).collect(Collectors.toList());
        ReturnLeaveSumVo otherRes = AggregateServiceImpl.addReturnLeaveList(otherList);
        otherRes.setStrawName("其他");
        list = list.stream().filter(e -> allNames.contains(e.getStrawType())).collect(Collectors.toList());
        list.add(otherRes);
        //导出时保留两位小数
        for (ReturnLeaveSumVo sp : list) {
            sp.setProStillField(sp.getProStillField().setScale(2, RoundingMode.HALF_UP));
            sp.setReturnRatio(sp.getReturnRatio().setScale(2, RoundingMode.HALF_UP));
            sp.setAllTotal(sp.getAllTotal().setScale(2, RoundingMode.HALF_UP));
            sp.setDisperseTotal(sp.getDisperseTotal().setScale(2, RoundingMode.HALF_UP));
            sp.setMainTotal(sp.getMainTotal().setScale(2, RoundingMode.HALF_UP));
        }
        ExportUtil.createExcel(ExportUtil.filterField(ReturnLeaveSumVo.class, queryVo.getSelectFields()), list, response, "还田离田情况汇总.xlsx");
        return Result.ok("导出成功！");
    }


    //大数据提供一个接口
    @ApiOperation(value = "大数据秸秆指标调用接口")
    @PostMapping("/bigDataIndexSum")
    public Result<StrawBigDataVo> bigDataIndexSum(@RequestParam(name = "year") @ApiParam(value = "年份") String year,
                                                  @RequestParam(name = "regionCode") @ApiParam(value = "区域编码") String regionCode) {
        AggregateQueryVo queryVo = new AggregateQueryVo(year, regionCode);
        StrawBigDataVo list = aggregateService.bigDataIndexSum(queryVo);
        return Result.ok(list);
    }


    @ApiOperation(value = "秸秆综合利用情况汇总")
    @GetMapping("/straw/usage")
    public Result<List<StrawUsageVo>> findStrawUsage(AggregateQueryVo queryVo) {
        List<StrawUsageVo> strawUsage = aggregateService.findStrawUsage(queryVo);
        return Result.ok(strawUsage);
    }

    @ApiOperation(value = "导出秸秆综合利用情况汇总")
    @GetMapping(value = "/straw/usage/export", produces = "application/octet-stream")
    public Result exportStrawUsage(AggregateQueryVo queryVo, HttpServletResponse response) {
        List<StrawUsageVo> list = aggregateService.findStrawUsage(queryVo);
        // 导出合并其他细项
        List<String> allNames = CropsEnum.getAllNames();
        List<StrawUsageVo> otherList = list.stream().filter(e -> !allNames.contains(e.getStrawType()) && !"sum".equals(e.getStrawType())).collect(Collectors.toList());
        StrawUsageVo otherRes = AggregateServiceImpl.getStrawUsageTotal(otherList);
        otherRes.setStrawName("其他");
        list = list.stream().filter(e -> allNames.contains(e.getStrawType())).collect(Collectors.toList());
        list.add(otherRes);
        for (StrawUsageVo vo : list) {
            //导出时保留两位小数
            vo.setSeedArea(vo.getSeedArea().setScale(2, RoundingMode.HALF_UP));
            vo.setTheoryResource(vo.getTheoryResource().setScale(2, RoundingMode.HALF_UP));
            vo.setReturnResource(vo.getReturnResource().setScale(2, RoundingMode.HALF_UP));
            vo.setLeaveNumber(vo.getLeaveNumber().setScale(2, RoundingMode.HALF_UP));
            vo.setTransportAmount(vo.getTransportAmount().setScale(2, RoundingMode.HALF_UP));
            vo.setTotolRate((vo.getTotolRate().setScale(2, RoundingMode.HALF_UP)));
            // 还田方式
            vo.setReturnType(ReturnTypeEnum.getTypes(Arrays.asList(vo.getReturnType().split(","))));
            // 离田利用方式
            vo.setLeavingType(LeavingTypeEnum.getTypes(Arrays.asList(vo.getLeavingType().split(","))));
        }
        ExportUtil.createExcel(ExportUtil.filterField(StrawUsageVo.class, queryVo.getSelectFields()), list, response, "秸秆综合利用情况汇总.xlsx");
        return Result.ok("导出成功！");
    }

}
