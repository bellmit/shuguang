
package com.sofn.fdpi.web;

import com.sofn.common.model.Result;
import com.sofn.common.web.BaseController;
import com.sofn.fdpi.service.DataTransferCitesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@Slf4j
@Api(value = "数据迁移出口物种(cites)", tags = "数据迁移出口物种(cites)")
@RestController
@RequestMapping("/dataTransferCites")

public class DataTransferCitesController extends BaseController {

    @Resource
    private DataTransferCitesService dataTransferCitesService;

    @ApiOperation(value = "迁移出口物种数据")
    @GetMapping("/handleData")
    public Result handleData(){
        dataTransferCitesService.test();
        return Result.ok();
    }


}

