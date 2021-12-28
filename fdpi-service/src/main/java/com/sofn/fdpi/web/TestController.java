package com.sofn.fdpi.web;

import com.sofn.common.model.Dict;
import com.sofn.common.model.Result;
import com.sofn.common.utils.DictUtils;
import com.sofn.common.utils.RedisUtils;
import com.sofn.common.utils.UserUtil;
import com.sofn.common.web.BaseController;
import com.sofn.fdpi.service.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Api(value = "测试")
@RestController
@RequestMapping("/test")
public class TestController extends BaseController {

    @Autowired
    RedisUtils redisUtils;
    @Autowired
    Environment environment;

    @ApiOperation(value = "hello")
    @RequiresPermissions("test:hello")
    @PostMapping("/hello")
    public Result hello() {
        return Result.ok("hello,world!");
    }

    @ApiOperation(value = "hi")
    @RequiresPermissions("test:hi")
    @PostMapping("/hi")
    public Result hi() {
        return Result.ok("hi,world!");
    }

    @ApiOperation(value = "error")
    @PostMapping("/error")
    public void error() {
        boolean is = 1/0>0;
    }


    @ApiOperation(value = "redis")
    @PostMapping("/redis")
    public Result redis() {
        redisUtils.set("hello","world");
        return Result.ok(redisUtils.get("hello"));
    }

    @PostMapping("/notneed")
    public Result notNeedPermission() {
        DictUtils.getByType("sex");
        DictUtils.getByTypeAndKey("sex","man");
        return Result.ok("不需要权限可访问");
    }

    @PostMapping("/getUserInfo")
    public Result getUserInfo() {
        log.info("getUserInfo, port:"+getPort());
        return Result.ok(UserUtil.getLoginUser());
    }

    @PostMapping("/getDictList")
    public Result getDictList(@RequestParam("type") String type) {
        List<Dict> dictList = DictUtils.getByType(type);
        return Result.ok(dictList);
    }

    @PostMapping("/getDict")
    public Result getDict(@RequestParam("type") String type, @RequestParam("key") String key) {
        Dict dict = DictUtils.getByTypeAndKey(type,key);
        return Result.ok(dict);
    }

    public String getPort(){
        return environment.getProperty("local.server.port");
    }

    @Resource
    TestService testService;

    @PostMapping(value = "/upload", produces = "multipart/form-data")
    @ApiOperation(value = "检查数据")
    public Result upload(@RequestParam @ApiParam("文件导入地址（暂只支持txt）") MultipartFile multipartFile,
                         @RequestParam() @ApiParam(value = "打印ID", required = true) String id) {
        testService.checkData(multipartFile, id);
        return Result.ok();
    }
}
