package com.sofn.pcstd.web;

import com.sofn.common.annotation.DataImage;
import com.sofn.common.annotation.ImageTran;
import com.sofn.common.model.BaseData;
import com.sofn.common.model.Dict;
import com.sofn.common.model.Result;
import com.sofn.common.utils.*;
import com.sofn.common.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Api(value = "测试")
@RestController
@RequestMapping("/test")
public class TestController extends BaseController {

    @Autowired
    RedisUtils redisUtils;
    @Autowired
    RedisHelper redisHelper;
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

    @PostMapping("/resultInfo")
    public Result<NullInfo> resultInfo() {
        NullInfo info = new NullInfo();
        info.setName("qzj");
        info.setNickName("");
        return Result.ok(info);
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

    @GetMapping("/getCurrentUserId")
    public Result getCurrentUserId(@RequestParam("token") String token) {
        return Result.ok(UserUtil.getLoginUserId(token));
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

    @PostMapping("/getBaseDataList")
    public Result getBaseDataList(@RequestParam("appId") String appId, @RequestParam("type") String type) {
        List<BaseData> BaseDataList = BaseDataUtils.getByType(appId, type);
        return Result.ok(BaseDataList);
    }

    @PostMapping("/delBaseDataTest")
    public Result delBaseDataTest() {
        Map<Object,Object> map = redisHelper.hScan("abcdef", "*_data");
        return Result.ok(map);
    }

    public String getPort(){
        return environment.getProperty("local.server.port");
    }

    @ImageTran
    @PostMapping("/getTestInfo")
    public Result getTestInfo() {
        TestInfo info = new TestInfo();
        info.setAge(20);
        info.setName("quzhijie");
        info.setNickname("qzj");
        info.setHeadImage("a81365398ca74d718d5b1b25bcc08971");

        TestInfo info1 = new TestInfo();
        info1.setAge(24);
        info1.setName("heyongjie");
        info1.setNickname("hyj");
        info1.setHeadImage("11111111111111111");

        TestInfo info2 = new TestInfo();
        info2.setAge(25);
        info2.setName("zhouqingchun");
        info2.setNickname("zcq");
        info2.setHeadImage("222222222222222");

        List<TestInfo> list = new ArrayList<>();
        list.add(info1);
        list.add(info2);
        info.setTestInfo(list);

        List<String> list1 = new ArrayList<>();
        list1.add("33333333333333");
        list1.add("444444444444444");
        info.setStrList(list1);

        return Result.ok(info);
    }

    @GetMapping("/testLogger")
    public Result testLogger() {
        log.trace("this is a trace log.");
        log.debug("this is a debug log.");
        log.info("this is a info log.");
        log.warn("this is a warn log.");
        log.error("this is a error log.");
        return Result.ok();
    }

    @GetMapping("/testLogger1")
    public Result testLogger1() {
        NullInfo info = null;
        info.getAge();
        return Result.ok();
    }

    @Data
    class NullInfo{
        private String name;
        private String nickName;
        private Integer age;
        private List<String> data;
        private String phone;
        private Boolean isBoy;
    }

    @Data
    public static class TestInfo {
        private String name;
        private String nickname;
        private int age;
        @DataImage
        private String headImage;
        private List<TestInfo> testInfo;
        @DataImage(isList = true)
        private List<String> strList;
    }

}
