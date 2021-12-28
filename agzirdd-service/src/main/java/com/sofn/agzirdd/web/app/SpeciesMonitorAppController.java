package com.sofn.agzirdd.web.app;

import com.google.common.collect.Maps;
import com.sofn.agzirdd.enums.StatusEnum;
import com.sofn.agzirdd.model.SpeciesMonitor;
import com.sofn.agzirdd.service.SpeciesMonitorService;
import com.sofn.agzirdd.sysapi.bean.SysOrganization;
import com.sofn.agzirdd.util.RoleCodeUtil;
import com.sofn.agzirdd.vo.SpeciesMonitorForm;
import com.sofn.agzirdd.vo.SpeciesMonitorQueryVo;
import com.sofn.agzirdd.vo.SpeciesMonitorVo;
import com.sofn.common.exception.SofnException;
import com.sofn.common.log.SofnLog;
import com.sofn.common.model.Result;
import com.sofn.common.utils.JsonUtils;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.UserUtil;
import com.sofn.common.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
@Api(value = "外来入侵-物种监测模块App接口", tags = "外来入侵-物种监测模块App接口")
@RestController
@RequestMapping("/speciesMonitorApp")
public class SpeciesMonitorAppController extends BaseController {

    /**
     * 物种监测 -基本信息
     */
    @Autowired
    private SpeciesMonitorService speciesMonitorService;

    @SofnLog("获取物种监测信息(分页)")
    @ApiOperation(value="获取物种监测信息(分页)")
    @PostMapping("/speciesMonitorByPageListApp")
    public Result<PageUtils<SpeciesMonitor>> speciesMonitorByPageListApp(
            @Validated @RequestBody @ApiParam(name = "查询参数对象", value = "传入json格式", required = true) SpeciesMonitorQueryVo speciesMonitorQueryVo,
            HttpServletRequest request){
        //根据token获取登录用户Id
        String userId = UserUtil.getLoginUserId(request.getHeader("Authorization"));
        if(StringUtils.isBlank(userId)){
            throw new SofnException("当前登录用户异常");
        }
        Integer pageNo = speciesMonitorQueryVo.getPageNo();
        Integer pageSize = speciesMonitorQueryVo.getPageSize();
        //获取查询条件
        Map<String, Object> params = getParamsByLogin(speciesMonitorQueryVo, userId);

        PageUtils<SpeciesMonitor> speciesMonitorByPage = speciesMonitorService.getSpeciesMonitorByPage(params, pageNo, pageSize);
        return Result.ok(speciesMonitorByPage);
    }

    @SofnLog("获取物种监测信息(不分页)")
    @ApiOperation(value="获取物种监测信息(不分页)")
    @PostMapping("/speciesMonitorListApp")
    public Result<List<SpeciesMonitorForm>> speciesMonitorListApp(
            @Validated @RequestBody @ApiParam(name = "查询参数对象", value = "传入json格式", required = true) SpeciesMonitorQueryVo speciesMonitorQueryVo,
            HttpServletRequest request){
        //根据token获取登录用户Id
        String userId = UserUtil.getLoginUserId(request.getHeader("Authorization"));
        if(StringUtils.isBlank(userId)){
            throw new SofnException("当前登录用户异常");
        }
        //获取查询条件
        Map<String, Object> params = getParamsByLogin(speciesMonitorQueryVo, userId);

        List<SpeciesMonitorForm> speciesMonitorList = speciesMonitorService.getSpeciesMonitorList(params);
        return Result.ok(speciesMonitorList);
    }

    /**
     * 获取数据查询条件相关方法
     * @param speciesMonitorQueryVo speciesMonitorQueryVo
     * @param userId userId
     * @return map
     */
    @NotNull
    private Map<String, Object> getParamsByLogin(@ApiParam(name = "查询参数对象", value = "传入json格式") @RequestBody SpeciesMonitorQueryVo speciesMonitorQueryVo, String userId) {
        //获取当前用户所属机构区划信息
        String loginUserOrganizationInfo = UserUtil.getLoginUserOrganizationInfo();
        SysOrganization sysOrganization = JsonUtils.json2obj(loginUserOrganizationInfo, SysOrganization.class);
        //当前用户级别所属区域
        String regionLastCode = sysOrganization.getRegionLastCode();
        //获取当前用户角色列表
        List<String> roleColde = UserUtil.getLoginUserRoleCodeList();
        //放入查询条件
        Map<String, Object> params = Maps.newHashMap();
        params.put("status",speciesMonitorQueryVo.getStatus());
        params.put("beginDate",speciesMonitorQueryVo.getBeginDate());
        params.put("endDate",speciesMonitorQueryVo.getEndDate());
        params.put("inStatus",StatusEnum.getShowStatusByRole(RoleCodeUtil.getLoginUserAgzirddRoleCode()));
        //是否为县级人员
        if (roleColde != null && roleColde.contains("agzirdd_county")) {
            params.put("roleCode", "agzirdd_county");
            //params.put("countyId", regionLastCode);
            params.put("createUserId", userId);
        }
        //市级人员
        if (roleColde != null && roleColde.contains("agzirdd_city")) {
            params.put("roleCode", "agzirdd_county");
            params.put("cityId", regionLastCode);
        }
        //省级人员
        if (roleColde != null && roleColde.contains("agzirdd_province")) {
            params.put("roleCode", "agzirdd_county");
            params.put("provinceId", regionLastCode);
        }
        //是否为专家填报
        if (roleColde != null && roleColde.contains("agzirdd_expert")) {
            params.put("roleCode", "agzirdd_expert");
            params.put("createUserId", userId);
        }
        return params;
    }

    @SofnLog("获取指定id的物种监测信息App")
    @ApiOperation(value="获取指定id的物种监测信息App")
    @GetMapping("/getSpeciesMonitorAllByIdApp")
    public Result<SpeciesMonitorVo> getSpeciesMonitorAllByIdApp(
            @ApiParam(name = "id",value = "物种监测信息ID",required = true)@RequestParam(value = "id")String id){

        SpeciesMonitorVo speciesMonitorAllById = speciesMonitorService.getSpeciesMonitorAllById(id);
        return Result.ok(speciesMonitorAllById);
    }

    @SofnLog("新增物种监测信息App")
    @ApiOperation(value="新增物种监测信息App")
    @PostMapping("/addSpeciesMonitorApp")
    public Result<String> addSpeciesMonitorApp(
            @RequestBody @ApiParam(name = "物种监测信息对象", value = "传入json格式", required = true)SpeciesMonitorVo speciesMonitorVo,
            HttpServletRequest request){

        getProvinceIdByLogin(speciesMonitorVo);
        //获取当前用户角色列表
        List<String> roleColde = UserUtil.getLoginUserRoleCodeList();
        if (roleColde != null && roleColde.contains("agzirdd_expert")) {
            speciesMonitorVo.setStatus(StatusEnum.STATUS_9.getCode());
        }else{
            speciesMonitorVo.setStatus(StatusEnum.STATUS_0.getCode());
        }
        speciesMonitorService.addSpeciesMonitor(speciesMonitorVo);
        return Result.ok("新增物种监测信息成功");
    }

    @SofnLog("新增并上报生物监测点基本信息App")
    @ApiOperation(value="新增并上报生物监测点基本信息App")
    @PostMapping("/addAndSubmitApp")
    public Result<String> addAndSubmitApp(
            @Validated @RequestBody @ApiParam(name = "物种监测信息对象", value = "传入json格式", required = true)SpeciesMonitorVo speciesMonitorVo,
            HttpServletRequest request){

        //获取当前省id
//        getProvinceIdByLogin(speciesMonitorVo);
        //获取当前用户角色列表
        List<String> roleColde = UserUtil.getLoginUserRoleCodeList();
        if (roleColde != null && roleColde.contains("agzirdd_expert")) {
            speciesMonitorVo.setStatus(StatusEnum.STATUS_10.getCode());
        }else{
            speciesMonitorVo.setStatus(StatusEnum.STATUS_1.getCode());
        }
        speciesMonitorService.addSpeciesMonitor(speciesMonitorVo);
        return Result.ok("新增并上报物种监测信息成功!");
    }

    private void getProvinceIdByLogin(@ApiParam(name = "物种监测信息对象", value = "传入json格式", required = true) @RequestBody @Validated SpeciesMonitorVo speciesMonitorVo) {
        String loginUserOrganizationInfo = UserUtil.getLoginUserOrganizationInfo();
        SysOrganization sysOrganization = JsonUtils.json2obj(loginUserOrganizationInfo, SysOrganization.class);
        List<String> regionCode = JsonUtils.json2List(sysOrganization.getRegioncode(), String.class);
        speciesMonitorVo.setProvinceId(regionCode.get(0));
    }

    @SofnLog("编辑物种监测信息App")
    @ApiOperation(value="编辑物种监测信息App")
    @PostMapping("/updateSpeciesMonitorApp")
    public Result<String> updateSpeciesMonitorApp(
            @Validated  @RequestBody@ApiParam(name = "物种监测信息对象", value = "传入json格式", required = true) SpeciesMonitorVo speciesMonitorVo,
            HttpServletRequest request){

        String status00 = StatusEnum.STATUS_0.getCode();
        String status02 = StatusEnum.STATUS_2.getCode();
        String status04 = StatusEnum.STATUS_4.getCode();
        String status06 = StatusEnum.STATUS_6.getCode();
        String status08 = StatusEnum.STATUS_8.getCode();
        String status09 = StatusEnum.STATUS_9.getCode();
        String status = speciesMonitorVo.getStatus();
        if(status00.equals(status) || status02.equals(status) || status04.equals(status) ||
                status06.equals(status) || status08.equals(status) || status09.equals(status)){
            speciesMonitorService.updateSpeciesMonitor(speciesMonitorVo);
            return Result.ok("编辑物种监测信息成功!");
        }
        return Result.error("当前状态无法进行编辑提交!");
    }

    @SofnLog("编辑并上报物种监测信息")
    @ApiOperation(value="编辑并上报物种监测信息")
    @PostMapping("/updateAndSubmitApp")
    public Result<String> updateAndSubmitApp(
            @RequestBody @ApiParam(name = "物种监测信息对象", value = "传入json格式", required = true) SpeciesMonitorVo speciesMonitorVo,
            HttpServletRequest request){

        String status00 = StatusEnum.STATUS_0.getCode();
        String status02 = StatusEnum.STATUS_2.getCode();
        String status04 = StatusEnum.STATUS_4.getCode();
        String status06 = StatusEnum.STATUS_6.getCode();
        String status08 = StatusEnum.STATUS_8.getCode();
        String status09 = StatusEnum.STATUS_9.getCode();
        SpeciesMonitor speciesMonitor = speciesMonitorService.getSpeciesMonitorById(speciesMonitorVo.getId());
        String status = speciesMonitor.getStatus();
        if(status00.equals(status) || status02.equals(status) || status04.equals(status) ||
                status06.equals(status) || status08.equals(status) || status09.equals(status)){
            //获取当前用户角色列表
            List<String> roleColde = UserUtil.getLoginUserRoleCodeList();
            if (roleColde != null && roleColde.contains("agzirdd_expert")) {
                speciesMonitorVo.setStatus(StatusEnum.STATUS_10.getCode());
            }else{
                speciesMonitorVo.setStatus(StatusEnum.STATUS_1.getCode());
            }
            speciesMonitorService.updateSpeciesMonitor(speciesMonitorVo);
            return Result.ok("编辑并上报物种监测信息成功!");
        }
        return Result.error("当前状态无法进行编辑提交!");
    }

    @SofnLog("上报物种监测信息App")
    @ApiOperation(value="上报物种监测信息App")
    @GetMapping("/submitSpeciesMonitorApp")
    public Result<String> submitSpeciesMonitorApp(
            @ApiParam(name = "id",value = "id",required = true)@RequestParam(value = "id")String id){

        String status00 = StatusEnum.STATUS_0.getCode();
        String status09 = StatusEnum.STATUS_9.getCode();
        //获取当前用户角色列表
        List<String> roleColde = UserUtil.getLoginUserRoleCodeList();
        SpeciesMonitor speciesMonitor = speciesMonitorService.getSpeciesMonitorById(id);
        //判断当前数据状态是否为'已保存',专家填报状态
        if(status00.equals(speciesMonitor.getStatus()) || status09.equals(speciesMonitor.getStatus())){
            Map<String, Object> params = Maps.newHashMap();
            params.put("id",id);
            //判断当前用户角色
            if (roleColde != null && roleColde.contains("agzirdd_expert")) {
                params.put("status",StatusEnum.STATUS_10.getCode());
            }else{
                params.put("status",StatusEnum.STATUS_1.getCode());
            }
            speciesMonitorService.updateStatus(params);
            return Result.ok("当前物种监测信息已提交!");
        }
        return Result.error("当前物种监测信息该状态下不可提交!");

    }

    @SofnLog("撤回当前物种监测信息App")
    @ApiOperation(value="撤回当前物种监测信息App")
    @GetMapping("/recallSpeciesMonitorApp")
    public Result<String> recallSpeciesMonitorApp(
            @ApiParam(name = "id",value = "id",required = true)@RequestParam(value = "id")String id){

        String status01 = StatusEnum.STATUS_1.getCode();
        String status10 = StatusEnum.STATUS_10.getCode();

        SpeciesMonitor speciesMonitor = speciesMonitorService.getSpeciesMonitorById(id);
        //判断当前数据状态是否为'已提交'专家提交
        if(status01.equals(speciesMonitor.getStatus()) || status10.equals(speciesMonitor.getStatus())){
            Map<String, Object> params = Maps.newHashMap();
            params.put("id",id);
            params.put("status",StatusEnum.STATUS_2.getCode());
            speciesMonitorService.updateStatus(params);
            return Result.ok("当前物种监测信息已撤回!");
        }
        return Result.error("当前物种监测信息该状态下不可撤回!");
    }

    @SofnLog("删除物种监测信息App")
    @ApiOperation(value="删除物种监测信息App")
    @GetMapping("/removeSpeciesMonitorApp")
    public Result<String> removeSpeciesMonitorApp(
            @ApiParam(name = "id",value = "id",required = true)@RequestParam(value = "id")String id){

        SpeciesMonitor speciesMonitor = speciesMonitorService.getSpeciesMonitorById(id);
        if(speciesMonitor == null){
            return Result.error("当前物种监测信息已不存在,出现错误数据显示!");
        }
        //判断当前数据状态是否为'已保存',已撤回,专家填报
        if(StatusEnum.canRemove(speciesMonitor.getStatus())){
            speciesMonitorService.removeSpeciesMonitor(id);
            return Result.ok("当前物种监测信息已删除!");
        }
        return Result.error("当前物种监测信息该状态下不可删除!");
    }
}
