package com.sofn.ducss.web;

import com.google.common.collect.Lists;
import com.sofn.common.exception.SofnException;
import com.sofn.common.log.SofnLog;
import com.sofn.common.model.Result;
import com.sofn.common.utils.JsonUtils;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.UserUtil;
import com.sofn.ducss.enums.Constants;
import com.sofn.ducss.enums.RegionLevel;
import com.sofn.ducss.model.CountryTask;
import com.sofn.ducss.service.CountryTaskService;
import com.sofn.ducss.service.SyncSysRegionService;
import com.sofn.ducss.sysapi.bean.SysOrganization;
import com.sofn.ducss.sysapi.bean.SysRegionTreeVo;
import com.sofn.ducss.vo.CountryTaskForm;
import com.sofn.ducss.vo.CountryTaskMinistryVo;
import com.sofn.ducss.vo.CountryTaskVo;
import com.sofn.ducss.vo.TapVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Api(value = "年度任务管理", tags = "县级用户年度任务相关")
@RestController
@RequestMapping("/countryTask")
public class CountryTaskController {

    @Autowired
    private CountryTaskService countryTaskService;

    @Autowired
    private SyncSysRegionService syncSysRegionService;

    @SofnLog("获取当前县级用户所能查看到的年度任务信息(县级)")
    @ApiOperation(value = "获取当前县级用户所能查看到的年度任务信息（县级）")
    @GetMapping("/getCountryTaskList")
    public Result<PageUtils<CountryTask>> getCountryTaskList(@RequestParam(value = "pageNo") @ApiParam(value = "起始行数") Integer pageNo
            , @RequestParam(value = "pageSize") @ApiParam(value = "每页显示数") Integer pageSize
            , @RequestParam(value = "year", required = false) @ApiParam(value = "年度", required = false) String year
            , HttpServletRequest request) {
        //根据token获取登录用户Id
        String userId = UserUtil.getLoginUserId(request.getHeader("Authorization"));
        if (StringUtils.isBlank(userId)) {
            throw new SofnException("当前登录用户异常");
        }
        String userAreaByLogUser = UserUtil.getLoginUserOrganizationInfo();
        if (org.springframework.util.StringUtils.isEmpty(userAreaByLogUser)) {
            throw new SofnException("未获取到登录用户所属机构信息!");
        }
        SysOrganization sysOrganization = JsonUtils.json2obj(userAreaByLogUser, SysOrganization.class);
        String regioncode = sysOrganization.getRegioncode();
        List<String> areaList = JsonUtils.json2List(regioncode, String.class);
        //判断当前用户机构级别是否是县级
        if (!RegionLevel.COUNTY.getCode().equals(sysOrganization.getOrganizationLevel())) {
            throw new SofnException("当前用户不是县级用户!");
        }
        String countyId = areaList.get(2);
        List<String> years = Lists.newArrayList();
        if (StringUtils.isNotEmpty(year)) {
            years = Arrays.asList(year.split(","));
        }
        PageUtils<CountryTask> countryTasks = countryTaskService.getTaskByPage(pageNo, pageSize, countyId, years);

        return Result.ok(countryTasks);
    }

    @SofnLog("获取部级任务列表信息(部级)")
    @ApiOperation(value = "获取部级任务列表信息(部级)")
    @GetMapping("/getMinistryTaskList")
    public Result<PageUtils<CountryTask>> getMinistryTaskList(@RequestParam(value = "pageNo") @ApiParam(value = "起始行数") Integer pageNo
            , @RequestParam(value = "pageSize") @ApiParam(value = "每页显示数") Integer pageSize
            , @RequestParam(value = "year", required = false) @ApiParam(value = "年度", required = false) String year
            , HttpServletRequest request) {
        //根据token获取登录用户Id
        String userId = UserUtil.getLoginUserId(request.getHeader("Authorization"));
        if (StringUtils.isBlank(userId)) {
            throw new SofnException("当前登录用户异常");
        }
        List<String> years = Lists.newArrayList();
        if (StringUtils.isNotEmpty(year)) {
            years = Arrays.asList(year.split(","));
        }
        PageUtils<CountryTask> countryTasks = countryTaskService.getMinistryTaskByPage(pageNo, pageSize, years);
        return Result.ok(countryTasks);
    }

    @SofnLog("编辑县级年度任务信息")
    @ApiOperation(value = "编辑县级年度任务;【仅县级用户有此项功能】")
    @PostMapping("/updateCountryTask")
    public Result updateCountryTask(@Valid @RequestBody @ApiParam(name = "年度任务对象", value = "传入json格式,字段必须传值",
            required = true) CountryTaskVo vo, HttpServletRequest request) {
        String userId = UserUtil.getLoginUserId(request.getHeader("Authorization"));
        if (StringUtils.isBlank(userId)) {
            throw new SofnException("当前登录用户异常");
        }
        CountryTask task = new CountryTask();
        BeanUtils.copyProperties(vo, task);
        task.setCreateUserId(userId);
        return Result.ok(countryTaskService.updateCountryTask(task));
    }

    @SofnLog("根据id获取年度任务信息")
    @ApiOperation(value = "根据id获取年度任务信息")
    @GetMapping("/getCountryTaskById")
    public Result<CountryTaskForm> getCountryTaskById(@ApiParam(name = "年度任务id",
            value = "年度任务id,修改时候把整个对象所有内容一并传回", required = true) String id) {
        return Result.ok(countryTaskService.getCountryTaskFormById(id));
    }

    @SofnLog("删除年度任务信息")
    @ApiOperation(value = "删除年度任务信息")
    @DeleteMapping("/deleteCountryTask")
    public Result deleteCountryTask(String id) {
        return countryTaskService.deleteCountryTaskById(id);
    }

    @SofnLog("上报/撤回")
    @ApiOperation(value = "上报/撤回")
    @PostMapping(value = "/examineCountryTask")
    public Result examineCountryTask(@Valid @RequestBody @ApiParam(name = "年度任务对象", value = "传入json格式,字段必须传值",
            required = true) CountryTask task, HttpServletRequest request) {
        String userId = UserUtil.getLoginUserId(request.getHeader("Authorization"));
        if (StringUtils.isBlank(userId)) {
            throw new SofnException("当前登录用户异常");
        }

        return countryTaskService.examineCountryTask(task, UserUtil.getLoginUserName(), userId);
    }

    @SofnLog("部级新增/编辑年度任务信息")
    @ApiOperation(value = "部级新增/编辑年度任务信息")
    @PostMapping("/addOrUpdateMinistryTask")
    public Result addOrUpdateMinistryTask(@RequestBody @ApiParam(name = "年度任务对象", value = "传入json格式,字段必须传值",
            required = true) @Valid CountryTaskMinistryVo vo, HttpServletRequest request) {
        String userId = UserUtil.getLoginUserId(request.getHeader("Authorization"));
        if (StringUtils.isBlank(userId)) {
            throw new SofnException("当前登录用户异常");
        }
        String userName = UserUtil.getLoginUserName();
        CountryTask task = new CountryTask();
        BeanUtils.copyProperties(vo, task);
        task.setTaskLevel(RegionLevel.MINISTRY.getCode());
        task.setStatus(Constants.ExamineState.SAVE);
        task.setCreateUserId(userId);
        task.setCreateUserName(userName);
        return Result.ok(countryTaskService.addOrUpdateMinistryTask(task));
    }

    @SofnLog("部级下发任务")
    @ApiOperation(value = "部级下发任务")
    @GetMapping("/issueMinistryTask")
    public Result issueMinistryTask(String id, HttpServletRequest request) {
        String userId = UserUtil.getLoginUserId(request.getHeader("Authorization"));
        if (StringUtils.isBlank(userId)) {
            throw new SofnException("当前登录用户异常");
        }
        String userName = UserUtil.getLoginUserName();
        return Result.ok(countryTaskService.issueMinistryTask(id, userId, userName));
    }

    @SofnLog("部级公布数据")
    @ApiOperation(value = "部级公布数据")
    @GetMapping("/publishMinistryTaskInfo")
    public Result publishMinistryTaskInfo(String id, HttpServletRequest request) {
        String userId = UserUtil.getLoginUserId(request.getHeader("Authorization"));
        if (StringUtils.isBlank(userId)) {
            throw new SofnException("当前登录用户异常");
        }
        String userName = UserUtil.getLoginUserName();
        return Result.ok(countryTaskService.publishMinistryTaskInfo(id, userId, userName));
    }

    @SofnLog("部级新增并下发年度任务")
    @ApiOperation(value = "部级新增并下发年度任务")
    @PostMapping("/addAndIssueMinistryTask")
    public Result addAndIssueMinistryTask(@RequestBody @ApiParam(name = "年度任务对象", value = "传入json格式,字段必须传值",
            required = true) @Valid CountryTaskMinistryVo vo, HttpServletRequest request) {
        String userId = UserUtil.getLoginUserId(request.getHeader("Authorization"));
        if (StringUtils.isBlank(userId)) {
            throw new SofnException("当前登录用户异常");
        }
        String userName = UserUtil.getLoginUserName();
        CountryTask task = new CountryTask();
        BeanUtils.copyProperties(vo, task);
        task.setTaskLevel(RegionLevel.MINISTRY.getCode());
        task.setStatus(Constants.ExamineState.SAVE);
        task.setCreateUserId(userId);
        task.setCreateUserName(userName);

        return Result.ok(countryTaskService.addAndIssueMinistryTask(task));
    }

    @SofnLog("公布数据弹框提示")
    @ApiOperation(value = "公布数据弹框提示")
    @GetMapping("/getInfoForTap")
    public Result<TapVo> getInfoForTap(@RequestParam(value = "year", required = true) @ApiParam(value = "年度", required = true) String year
            , HttpServletRequest request) {
        //根据token获取登录用户Id
        String userId = UserUtil.getLoginUserId(request.getHeader("Authorization"));
        if (StringUtils.isBlank(userId)) {
            throw new SofnException("当前登录用户异常");
        }

        String userAreaByLogUser = UserUtil.getLoginUserOrganizationInfo();
        if (org.springframework.util.StringUtils.isEmpty(userAreaByLogUser)) {
            throw new SofnException("未获取到登录用户所属机构信息!");
        }
        SysOrganization sysOrganization = JsonUtils.json2obj(userAreaByLogUser, SysOrganization.class);
        String regioncode = sysOrganization.getRegionLastCode();
        //判断当前用户级别
        if (!RegionLevel.MINISTRY.getCode().equals(sysOrganization.getOrganizationLevel())) {
            throw new SofnException("当前用户不是部级机构用户，请检查账号是否正确");
        }

        if (null == year) {
            year = "";
        }
        TapVo tapVo = countryTaskService.getTapVo(year, regioncode);

        return Result.ok(tapVo);
    }


    @SofnLog("获取已发布的年份数据")
    @ApiOperation(value = "获取已发布的年份数据")
    @GetMapping("/getCountryTaskYearList")
    public Result<List<String>> getCountryTaskYearList() {
        return Result.ok(countryTaskService.getCountryTaskYearList());
    }

    @SofnLog("获取已下发、已发布的年份数据")
    @ApiOperation(value = "获取已下发、已发布的年份数据")
    @GetMapping("/year/list")
    public Result<List<String>> listYear() {
        return Result.ok(countryTaskService.listYear());
    }
}




