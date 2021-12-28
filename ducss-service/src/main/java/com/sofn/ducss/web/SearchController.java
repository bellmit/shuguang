package com.sofn.ducss.web;

import com.sofn.common.log.SofnLog;
import com.sofn.common.model.Result;
import com.sofn.ducss.service.DataAnalysisService;
import com.sofn.ducss.service.SearchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author huanghao
 * @version 1.0
 * @date 2020/10/9  17:01
 * @description
 **/
@RestController
@Api(tags = "ES-数据分析接口", value = "ES-数据分析页面的表现层")
@Slf4j
@RequestMapping("/sku_search")
public class SearchController {
    //    @Autowired
//    private EsManagerService esManagerService;
    @Autowired
    private SearchService searchService;

    @Autowired
    private DataAnalysisService dataAnalysisService;

//    /**
//     * 全文检索
//     *
//     * @return
//     */
//    @PostMapping
//    @ApiOperation(value = "数据分析列表全文检索")
//    @SofnLog("数据分析列表全文检索")
//    public Result search(@RequestBody HashMap<String, String> paramMap) throws Exception {
//        //特殊符号处理
//        handlerSearchMap(paramMap);
//        return Result.ok(searchService.search(paramMap));
//    }

    @PostMapping
    @ApiOperation(value = "数据分析列表全文检索2")
    @SofnLog("数据分析列表全文检索2")
    public Result search2(@RequestBody HashMap<String, String> paramMap) throws Exception {
        //特殊符号处理
        // handlerSearchMap(paramMap);
        //return Result.ok(searchService.search2(paramMap));
        return dataAnalysisService.getDataList(paramMap);
    }

    //对搜索入参带有特殊符号进行处理
    public void handlerSearchMap(Map<String, String> searchMap) {
        if (null != searchMap) {
            Set<Map.Entry<String, String>> entries = searchMap.entrySet();
            for (Map.Entry<String, String> entry : entries) {
                if (entry.getKey().startsWith("spec_")) {
                    searchMap.put(entry.getKey(), entry.getValue().replace("+", "%2B"));
                }
            }
        }
    }

    @GetMapping("real")
    @ApiOperation(value = "处理线上数据 area 综合利用率")
    public Result real() {
        dataAnalysisService.real();
        return Result.ok();
    }

    @GetMapping("real2")
    @ApiOperation(value = "处理线上数据 city 综合利用率")
    public Result real2() {
        dataAnalysisService.real2();
        return Result.ok();
    }

    @GetMapping("real3")
    @ApiOperation(value = "处理线上数据 province 综合利用率")
    public Result real3() {
        dataAnalysisService.real3();
        return Result.ok();
    }


    @PostMapping("real4")
    @ApiOperation(value = "real4")
    public Result real4(@RequestBody HashMap<String, List<String>> paramMap) {
        dataAnalysisService.real4(paramMap.get("list"));
        return Result.ok();
    }


}
