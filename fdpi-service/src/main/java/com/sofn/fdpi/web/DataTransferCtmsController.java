
package com.sofn.fdpi.web;

import com.sofn.common.model.Result;
import com.sofn.common.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@Api(value = "数据迁移养殖加工企业(ctms)", tags = "数据迁移养殖加工企业(ctms)")
@RestController
@RequestMapping("/dataTransferCtms")

public class DataTransferCtmsController extends BaseController {
    @ApiOperation(value = "hello")
    @RequiresPermissions("test:hello")
    @PostMapping("/hello")
    public Result hello() {
        return Result.ok("hello,world!");
    }
}

