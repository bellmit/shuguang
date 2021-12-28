package com.sofn.sys.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.netflix.discovery.DiscoveryManager;
import com.sofn.common.annotation.DataImage;
import com.sofn.common.annotation.ImageTran;
import com.sofn.common.excel.ExcelImportUtil;
import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.fileutil.SysFileUtil;
import com.sofn.common.log.SofnLog;
import com.sofn.common.model.BaseData;
import com.sofn.common.model.Dict;
import com.sofn.common.model.Result;
import com.sofn.common.mq.MqData;
import com.sofn.common.mq.MqSendService;
import com.sofn.common.utils.*;
import com.sofn.common.web.BaseController;
import com.sofn.sys.mapper.SysOrgMapper;
import com.sofn.sys.model.SysBaseData;
import com.sofn.sys.model.SysOrg;
import com.sofn.sys.model.SysRegion;
import com.sofn.sys.model.SysSubsystem;
import com.sofn.sys.service.*;
import com.sofn.sys.test.IpPortMaping;
import com.sofn.sys.test.VRegion;
import com.sofn.sys.vo.SysBaseDataTypeVo;
import com.sofn.sys.vo.SysBaseDataVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.*;
import java.util.function.Consumer;

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
    @Autowired
    MqSendService mqSender;
    @Autowired
    SysRegionService sysRegionService;
    @Autowired
    SysOrgService sysOrgService;
    @Autowired
    SysOrgMapper sysOrgMapper;
    @Autowired
    SysUserService sysUserService;
    @Autowired
    SysSubsystemService sysSubsystemService;
    @Autowired
    private SysBaseDataService sysBaseDataService;
    @Autowired
    private SysBaseTypeAndValueService sysBaseTypeAndValueService;

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
        List<String> list = redisHelper.scan("sys_region*");
        List<String> list0 = redisHelper.scan("sys_region_*_510000");
        return Result.ok();
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

    @SofnLog(value = "测试日志注解")
    @GetMapping("/log")
    public Result log(@RequestParam("appId") String appId, @RequestParam("type") String type) {
      return Result.ok(getMap());
    }

    @SofnLog(value = "测试日志注解")
    @PostMapping("/log1")
    public Result log(@RequestBody Map<Object,Object> map) {
      log.error("test",map.toString());
      return Result.ok(getMap());
    }

    private Map<String,Object> getMap(){
      Map<String,Object> map = new HashMap<>();
      map.put("name","张三");
      map.put("age","29");
      map.put("nickname","MR zhang");

      Map<String,Object> subObj = new HashMap<>();
      subObj.put("name","李四");
      subObj.put("age","2");
      subObj.put("nickname","xiaoLi");

      map.put("sun", subObj);

      List<Object> friends = new ArrayList<>();
      Map<String,Object> f1 = new HashMap<>();
      f1.put("name","李四1");
      f1.put("age","25");
      f1.put("nickname","xiaoLi");
      friends.add(f1);

      Map<String,Object> f2 = new HashMap<>();
      f2.put("name","李四2");
      f2.put("age","26");
      f2.put("nickname","xiaoLi");
      friends.add(f2);

      Map<String,Object> f3 = new HashMap<>();
      f3.put("name","李四3");
      f3.put("age","27");
      f3.put("nickname","xiaoLi");
      friends.add(f3);

      map.put("friends", friends);
      return map;
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

    @GetMapping("/mq")
    public Result testMq() {
      MqData data = new MqData();
      data.name = "qqzj";
      data.age = 30;
      data.date = new Date();
      //mqSender.send(MqTopics.SYS_TEST, new MqMessage<MqData>(1, data, new Date()));
      return Result.ok();
    }

    @GetMapping("/login/orginfo")
    public Result orginfo() {
      String json = UserUtil.getLoginUserOrganizationInfo();
      return Result.ok(json,"success");
    }

    @GetMapping("/update/orgcode")
    public Result orgcode() {
      List<SysOrg> orgList = sysOrgService.list();
      orgList.forEach(new Consumer<SysOrg>() {
        @Override
        public void accept(SysOrg sysOrg) {
          String code = null;
          String address = null;
          if (StringUtils.equalsIgnoreCase(sysOrg.getThirdOrg(),BoolUtils.Y)){
            code = sysOrg.getRegionLastCode();
          }
          address = sysOrg.getAddressLastCode();

          if (StringUtils.equalsIgnoreCase(sysOrg.getThirdOrg(),BoolUtils.Y) && StringUtils.isNotBlank(code)){
            SysRegion sysRegion = sysRegionService.getOneByCode(code,0);

            if (sysRegion != null) {
              List<String> codes = sysRegionService.getFormatIdsOrNames(sysRegion.getParentIds(),sysRegion.getRegionCode(),"ID");
              if (!CollectionUtils.isEmpty(codes)) {
                sysOrg.setRegioncode(JsonUtils.obj2json(codes));
              }
            }
          }

          if (StringUtils.isNotBlank(address)){
            SysRegion sysRegion = sysRegionService.getOneByCode(address,0);
            if (sysRegion != null) {
              List<String> codes = sysRegionService.getFormatIdsOrNames(sysRegion.getParentIds(),sysRegion.getRegionCode(),"ID");
              if (!CollectionUtils.isEmpty(codes)) {
                sysOrg.setAddress(JsonUtils.obj2json(codes));
              }
            }
          }

          sysOrgService.updateById(sysOrg);
        }
      });
      return Result.ok();
    }

    @GetMapping("/getRegionCacheForCodes")
    public Result getRegionCacheForCodes(@RequestParam String[] regionNames) {
      return Result.ok(RegionCacheUtils.getRegionCodesByNames(regionNames),"success");
    }

    @GetMapping("/getRegionCacheForNames")
    public Result getRegionCacheForNames(@RequestParam String[] regionCodes) {
      sysRegionService.updateRegionRedisCache();
      return Result.ok(RegionCacheUtils.getRegionNamesByCodes(regionCodes),"success");
    }

  @GetMapping("/getChildrenOrgByIds")
  public Result getChildrenOrgByIds(@RequestParam String[] regionCodes) {
      List<String> orgIds = new ArrayList<>();
      orgIds.add("  abb69c88dd324f2b83cac52f9d7d6f70 ");
      orgIds.add("  39fca2ec9fdb4982bb542f0945e54575    ");
      Set<String> list = sysOrgMapper.getChildrenOrgByIds(orgIds,"sys");
      return Result.ok(list,"success");
  }

  @GetMapping("/checkRegionLevel")
  public Result checkRegionLevel(@RequestParam String regionCode,@RequestParam String level) {
    return Result.ok(sysRegionService.checkRegionLevel(regionCode,level),"success");
  }

  @GetMapping("/getSysFileInfo")
  public Result getSysFileInfo(@RequestParam String regionCode,@RequestParam String level) {
    return Result.ok(SysFileUtil.getSysFileInfo("9999"),"success");
  }

  @GetMapping(value="/getCurrentUserInfo",produces="application/octet-stream")
  @ApiOperation(value = "下载文件", produces = "application/octet-stream")
  public void getCurrentUserInfo(@RequestParam String regionCode, @RequestParam String level, HttpServletResponse response, HttpServletRequest request) throws IOException {
      response.sendRedirect("http://localhost:7999/sys/fileManage/downloadFile/802e1a6bb76e4cf3bdb90f032b1314a0");

//    return "forward:/sys/fileManage/downloadFile/802e1a6bb76e4cf3bdb90f032b1314a0";
  }

  @PostMapping("/testParamError")
  public Result getSysFileInfo(@Validated @RequestBody ParamInfo paramInfo, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return Result.error(getErrMsg(bindingResult));
    }

    return Result.ok(paramInfo,"success");
  }

    @RequestMapping(value = "/offline", method = RequestMethod.DELETE)
    public void offLine(){
      DiscoveryManager.getInstance().shutdownComponent();
    }

  @PostMapping("/testLongToString")
  public Result testLongToString() {
    ParamInfo info = new ParamInfo();
    info.setName("qzj");
    info.setAge(20);
    info.setTime(123456789012345L);

    return Result.ok(info,"success");
  }

  @GetMapping("/updateTrialSystemUrl")
  public Result updateTrialSystemUrl() {
    List<IpPortMaping> list = ExcelImportUtil.getDataByExcel("I:/trial.xlsx", 1, IpPortMaping.class);
    for (IpPortMaping item : list) {
      if (item == null){
        continue;
      }

      String itemTestUrl = String.format("http://%s:%s",item.getTestOutIp(),item.getTestOUtPort());
      String itemTrialUrl = String.format("http://%s:%s",item.getTrialOutIp(),item.getTrialOUtPort());

      SysSubsystem sysSubsystem = new SysSubsystem();
      sysSubsystem.setViewUrl(itemTrialUrl);
      sysSubsystemService.update(sysSubsystem,new UpdateWrapper<SysSubsystem>().eq("VIEW_URL",itemTestUrl));
    }

    return Result.ok("info","success");
  }

  @GetMapping("/importVRegion")
  public void importVRegion() {
    List<VRegion> list = ExcelImportUtil.getDataByExcelAndSheet("I:/xuniquhua.xlsx", 1, VRegion.class, ExcelField.Type.IMPORT,0);
    List<VRegion> list1 = ExcelImportUtil.getDataByExcelAndSheet("I:/xuniquhua.xlsx", 1, VRegion.class, ExcelField.Type.IMPORT,1);
    list.addAll(list1);
    for (VRegion item : list) {
      if (item == null){
        continue;
      }

      //{parentId: "910100", regionName: "中国新疆兵团第一师3团", regionCode: "910102", areaId: "APAC"}
      SysRegion sysRegion = new SysRegion();
      sysRegion.setAreaId("APAC");
      sysRegion.setParentId(item.getParent());
      sysRegion.setRegionCode(item.getCode());
      sysRegion.setRegionName(item.getName());

      sysRegionService.addSysRegion(sysRegion);
    }
  }

  @GetMapping("/importVRegionToBaseData")
  public void importVRegionToBaseData() {
    List<VRegion> list = ExcelImportUtil.getDataByExcelAndSheet("I:/xuniquhua.xlsx", 1, VRegion.class, ExcelField.Type.IMPORT,0);
    List<VRegion> list1 = ExcelImportUtil.getDataByExcelAndSheet("I:/xuniquhua.xlsx", 1, VRegion.class, ExcelField.Type.IMPORT,1);
    list.addAll(list1);    for (VRegion item : list) {
      if (item == null){
        continue;
      }

      //{"typename":"虚拟行政区划","basedataname":"黑龙江农垦","basedatacode":"920000","enable":"Y","remark":"","basedatatypeid":"63c432d331f3da52f4b0c6e167b3d91f"}
      SysBaseDataVo sysBaseDataVo = new SysBaseDataVo();
      SysBaseDataTypeVo sysBaseDataTypeVo = new SysBaseDataTypeVo();
      sysBaseDataTypeVo.setId("63c432d331f3da52f4b0c6e167b3d91f");
      sysBaseDataVo.setSysBaseDataTypeVo(sysBaseDataTypeVo);
      sysBaseDataVo.setBaseDataname(item.getName());
      sysBaseDataVo.setBaseDatacode(item.getCode());
      sysBaseDataVo.setEnable("Y");
      sysBaseDataVo.setRemark("");

      sysBaseDataService.saveBaseDataInfo(sysBaseDataVo);
    }
  }

  //{"ids":"VALUE_63c432d331f3da52f4b0c6e167b3d91f_9da9cb6380425a3f4b5866942dd6c39b,
  // VALUE_63c432d331f3da52f4b0c6e167b3d91f_9724be73f150606a150296724b267629,
  // TYPE_63c432d331f3da52f4b0c6e167b3d91f,
  // VALUE_63c432d331f3da52f4b0c6e167b3d91f_51710877b50764ea20e57199235a9fb5,
  // VALUE_63c432d331f3da52f4b0c6e167b3d91f_58524a8e6b8cf89dd8a8073fda6a473a",
  // "appId":"ducss"}
  @GetMapping("/allocVCodeToSubsystem")
  public void allocVCodeToSubsystem() {
    List<SysBaseData> list = sysBaseDataService.getBaseDataListByType("virtual_region");
    List<String> ids = new ArrayList<>();
    ids.add("TYPE_63c432d331f3da52f4b0c6e167b3d91f");

    for (SysBaseData item : list) {
      if (item == null){
        continue;
      }

      ids.add(String.format("VALUE_%s_%s","63c432d331f3da52f4b0c6e167b3d91f",item.getId()));
    }

    sysBaseTypeAndValueService.saveSubSystemAndBaseDataRelationship(ids,"ducss");
  }

    @Data
    static class ParamInfo{
      @NotBlank(message = "name不能为空")
      private String name;
      @NotNull(message = "age不能为空")
      private Integer age;
      private Long time;
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
