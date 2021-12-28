package com.sofn.agzirdd.web.app;

import com.google.common.collect.Maps;
import com.sofn.agzirdd.enums.StatusEnum;
import com.sofn.agzirdd.model.SpeciesInvestigation;

import com.sofn.agzirdd.service.SpeciesInvestigationService;

import com.sofn.agzirdd.sysapi.bean.SysOrganization;
import com.sofn.agzirdd.util.RoleCodeUtil;
import com.sofn.agzirdd.vo.SpeciesInvestigationForm;
import com.sofn.agzirdd.vo.SpeciesInvestigationQueryVo;
import com.sofn.agzirdd.vo.SpeciesInvestigationVo;
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
@Api(value = "外来入侵-物种调查模块App接口", tags = "外来入侵-物种调查模块App接口")
@RestController
@RequestMapping("/speciesInvestigationApp")
public class SpeciesInvestigationAppController extends BaseController {

    /**
     * 物种调查模块-调查基本信息
     */
    @Autowired
    private SpeciesInvestigationService speciesInvestigationService;


    @SofnLog("获取物种调查信息(分页)")
    @ApiOperation(value="获取物种调查信息(分页)")
    @PostMapping("/speciesMonitorByPageListApp")
    public Result<PageUtils<SpeciesInvestigation>> getSpeciesInvestigationByPage(
            @Validated @RequestBody @ApiParam(name = "查询参数对象", value = "传入json格式", required = true) SpeciesInvestigationQueryVo speciesInvestigationQueryVo,
            HttpServletRequest request){
        //根据token获取登录用户Id
        String userId = UserUtil.getLoginUserId(request.getHeader("Authorization"));
        if(StringUtils.isBlank(userId)){
            throw new SofnException("当前登录用户异常");
        }
        Integer pageNo = speciesInvestigationQueryVo.getPageNo();
        Integer pageSize = speciesInvestigationQueryVo.getPageSize();
        //获取查询条件
        Map<String, Object> params = getParamsByLogin(speciesInvestigationQueryVo, userId);
        PageUtils<SpeciesInvestigation> speciesInvestigationByPage =
                speciesInvestigationService.getSpeciesInvestigationByPage(params, pageNo, pageSize);

        return Result.ok(speciesInvestigationByPage);
    }

    @SofnLog("获取物种调查信息(不分页)")
    @ApiOperation(value="获取物种调查信息(不分页)")
    @PostMapping("/speciesInvestigationListApp")
    public Result<List<SpeciesInvestigationForm>> getSpeciesInvestigationListApp(
            @Validated @RequestBody @ApiParam(name = "查询参数对象", value = "传入json格式", required = true) SpeciesInvestigationQueryVo speciesInvestigationQueryVo,
            HttpServletRequest request){
        //根据token获取登录用户Id
        String userId = UserUtil.getLoginUserId(request.getHeader("Authorization"));
        if(StringUtils.isBlank(userId)){
            throw new SofnException("当前登录用户异常");
        }

        //获取查询条件
        Map<String, Object> params = getParamsByLogin(speciesInvestigationQueryVo, userId);
        List<SpeciesInvestigationForm> speciesInvestigationList = speciesInvestigationService.getSpeciesInvestigationList(params);

        return Result.ok(speciesInvestigationList);
    }

    /**
     * 获取查询条件
     * @param speciesInvestigationQueryVo speciesInvestigationQueryVo
     * @param userId userId
     * @return map
     */
    @NotNull
    private Map<String, Object> getParamsByLogin(@ApiParam(name = "查询参数对象", value = "传入json格式") @RequestBody SpeciesInvestigationQueryVo speciesInvestigationQueryVo, String userId) {
        //获取当前用户所属机构区划信息
        String loginUserOrganizationInfo = UserUtil.getLoginUserOrganizationInfo();
        SysOrganization sysOrganization = JsonUtils.json2obj(loginUserOrganizationInfo, SysOrganization.class);
        //当前用户级别所属区域
        String regionLastCode = sysOrganization.getRegionLastCode();
        //获取当前用户角色列表
        List<String> roleColde = UserUtil.getLoginUserRoleCodeList();
        //放入查询条件
        Map<String, Object> params = Maps.newHashMap();
        params.put("status",speciesInvestigationQueryVo.getStatus());
        params.put("beginDate",speciesInvestigationQueryVo.getBeginDate());
        params.put("endDate",speciesInvestigationQueryVo.getEndDate());
        params.put("inStatus",StatusEnum.getShowStatusByRole(RoleCodeUtil.getLoginUserAgzirddRoleCode()));
        //是否为县级人员
        if (roleColde != null && roleColde.contains("agzirdd_county")) {
            //params.put("roleCode", "agzirdd_county");
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

    @SofnLog("获取指定id的物种调查信息")
    @ApiOperation(value="获取指定id的物种调查信息")
    @GetMapping("/getSpeciesInvestigationAllByIdApp")
    public Result<SpeciesInvestigationVo> getSpeciesInvestigationAllById(
            @ApiParam(name = "id",value = "物种调查信息ID",required = true)@RequestParam(value = "id")String id){

        SpeciesInvestigationVo speciesInvestigationAllById =
                speciesInvestigationService.getSpeciesInvestigationAllById(id);
        return Result.ok(speciesInvestigationAllById);
    }

    @SofnLog("新增物种调查信息App")
    @ApiOperation(value="新增物种调查信息App")
    @PostMapping("/addSpeciesInvestigationApp")
    public Result<String> addSpeciesInvestigationApp(
            @Validated @RequestBody @ApiParam(name = "物种调查信息对象", value = "传入json格式", required = true)SpeciesInvestigationVo speciesInvestigationVo,
            HttpServletRequest request){

//        String loginUserOrganizationInfo = UserUtil.getLoginUserOrganizationInfo();
//        SysOrganization sysOrganization = JsonUtils.json2obj(loginUserOrganizationInfo, SysOrganization.class);
//        List<String> regionCode = JsonUtils.json2List(sysOrganization.getRegioncode(), String.class);
//        speciesInvestigationVo.setProvinceId(regionCode.get(0));

        //获取当前用户角色列表
        List<String> roleColde = UserUtil.getLoginUserRoleCodeList();
        if (roleColde != null && roleColde.contains("agzirdd_expert")) {
            speciesInvestigationVo.setStatus(StatusEnum.STATUS_9.getCode());
            speciesInvestigationVo.setRoleCode("agzirdd_expert");
        }else{
            speciesInvestigationVo.setStatus(StatusEnum.STATUS_0.getCode());
            speciesInvestigationVo.setRoleCode("agzirdd_county");
        }
        speciesInvestigationService.addSpeciesInvestigation(speciesInvestigationVo);
        return Result.ok("新增物种调查信息成功");
    }

    @SofnLog("新增并上报生物监测点基本信息App")
    @ApiOperation(value="新增并上报生物监测点基本信息App")
    @PostMapping("/addAndSubmitApp")
    public Result<String> addAndSubmitApp(
            @Validated @RequestBody @ApiParam(name = "物种调查信息对象", value = "传入json格式", required = true)SpeciesInvestigationVo speciesInvestigationVo){

//        String loginUserOrganizationInfo = UserUtil.getLoginUserOrganizationInfo();
//        SysOrganization sysOrganization = JsonUtils.json2obj(loginUserOrganizationInfo, SysOrganization.class);
//        List<String> regionCode = JsonUtils.json2List(sysOrganization.getRegioncode(), String.class);
//        speciesInvestigationVo.setProvinceId(regionCode.get(0));

        //获取当前用户角色列表
        //增加一种情形；县级用户新增有外来入侵生物
        // 的调查数据，则直接类似于专家数据直接上报总部审核
        //获取当前用户角色列表
        List<String> roleColde = UserUtil.getLoginUserRoleCodeList();
        if (roleColde != null && roleColde.contains("agzirdd_expert")) {
            speciesInvestigationVo.setStatus(StatusEnum.STATUS_10.getCode());
            speciesInvestigationVo.setRoleCode("agzirdd_expert");
        }else{
            speciesInvestigationVo.setStatus(StatusEnum.STATUS_1.getCode());
            speciesInvestigationVo.setRoleCode("agzirdd_county");

        }
        speciesInvestigationService.addSpeciesInvestigation(speciesInvestigationVo);
        return Result.ok("新增并上报物种调查信息成功!");
    }


    @SofnLog("编辑物种调查信息App")
    @ApiOperation(value="编辑物种调查信息App")
    @PostMapping("/updateSpeciesInvestigationApp")
    public Result<String> updateSpeciesInvestigationApp(
            @Validated  @RequestBody@ApiParam(name = "物种调查信息对象", value = "传入json格式", required = true) SpeciesInvestigationVo speciesInvestigationVo,
            HttpServletRequest request){

        String status00 = StatusEnum.STATUS_0.getCode();
        String status02 = StatusEnum.STATUS_2.getCode();
        String status04 = StatusEnum.STATUS_4.getCode();
        String status06 = StatusEnum.STATUS_6.getCode();
        String status08 = StatusEnum.STATUS_8.getCode();
        String status09 = StatusEnum.STATUS_9.getCode();
        String status = speciesInvestigationVo.getStatus();
        if(status00.equals(status) || status02.equals(status) || status04.equals(status) ||
                status06.equals(status) || status08.equals(status) || status09.equals(status)){


            speciesInvestigationService.updateSpeciesInvestigation(speciesInvestigationVo);
            return  Result.ok("编辑物种调查信息成功!");
        }
        return Result.error("当前状态无法进行编辑!");
    }

    @SofnLog("编辑并上报物种调查信息App")
    @ApiOperation(value="编辑并上报物种调查信息App")
    @PostMapping("/updateAndSubmitApp")
    public Result<String> updateAndSubmitApp(
            @Validated @RequestBody @ApiParam(name = "物种调查信息对象", value = "传入json格式", required = true) SpeciesInvestigationVo speciesInvestigationVo,
            HttpServletRequest request){

        String status00 = StatusEnum.STATUS_0.getCode();
        String status02 = StatusEnum.STATUS_2.getCode();
        String status04 = StatusEnum.STATUS_4.getCode();
        String status06 = StatusEnum.STATUS_6.getCode();
        String status08 = StatusEnum.STATUS_8.getCode();
        String status09 = StatusEnum.STATUS_9.getCode();
        String status = speciesInvestigationVo.getStatus();
        if(status00.equals(status) || status02.equals(status) || status04.equals(status) ||
                status06.equals(status) || status08.equals(status) || status09.equals(status)){

            //获取当前用户角色列表
            List<String> roleColde = UserUtil.getLoginUserRoleCodeList();
            if (roleColde != null && roleColde.contains("agzirdd_expert")) {
                speciesInvestigationVo.setStatus(StatusEnum.STATUS_10.getCode());
            }else{

                speciesInvestigationVo.setStatus(StatusEnum.STATUS_1.getCode());
            }
            speciesInvestigationService.updateSpeciesInvestigation(speciesInvestigationVo);
            return  Result.ok("编辑并上报物种调查信息成功!");
        }
        return Result.error("当前状态无法进行编辑提交!");
    }

    @SofnLog("上报物种调查信息App")
    @ApiOperation(value="上报物种调查信息App")
    @GetMapping("/submitSpeciesInvestigationApp")
    public Result<String> submitSpeciesInvestigationApp(
            @ApiParam(name = "id",value = "id",required = true)@RequestParam(value = "id")String id){

        String status00 = StatusEnum.STATUS_0.getCode();
        String status09 = StatusEnum.STATUS_9.getCode();
        //获取当前用户角色列表
        List<String> roleColde = UserUtil.getLoginUserRoleCodeList();
        SpeciesInvestigation speciesInvestigation = speciesInvestigationService.getSpeciesInvestigationById(id);
        //判断当前数据状态是否为'已保存',专家填报状态
        if(status00.equals(speciesInvestigation.getStatus()) || status09.equals(speciesInvestigation.getStatus())){
            Map<String, Object> params = Maps.newHashMap();
            params.put("id",id);
            //判断当前用户角色
            if (roleColde != null && roleColde.contains("agzirdd_expert")) {
                params.put("status",StatusEnum.STATUS_10.getCode());
            }else{
                params.put("status",StatusEnum.STATUS_1.getCode());
            }
            speciesInvestigationService.updateStatus(params);
            return Result.ok("当前物种监测信息已提交!");
        }
        return Result.error("当前物种监测信息该状态下不可提交!");
    }

    @SofnLog("撤回当前物种调查信息")
    @ApiOperation(value="撤回当前物种调查信息")
    @GetMapping("/recallSpeciesInvestigationApp")
    public Result<String> recallSpeciesInvestigation(
            @ApiParam(name = "id",value = "id",required = true)@RequestParam(value = "id")String id){

        String status01 = StatusEnum.STATUS_1.getCode();
        String status10 = StatusEnum.STATUS_10.getCode();

        SpeciesInvestigation speciesInvestigation = speciesInvestigationService.getSpeciesInvestigationById(id);
        //判断当前数据状态是否为'已提交'状态
        if(status01.equals(speciesInvestigation.getStatus()) || status10.equals(speciesInvestigation.getStatus())){
            Map<String, Object> params = Maps.newHashMap();
            params.put("id",id);
            params.put("status",StatusEnum.STATUS_2.getCode());
            speciesInvestigationService.updateStatus(params);
            return Result.ok("当前物种调查信息已撤回!");
        }
        return Result.error("当前物种调查信息该状态下不可撤回!");
    }


    @SofnLog("删除物种调查信息App")
    @ApiOperation(value="删除物种调查信息App")
    @GetMapping("/removeSpeciesInvestigationApp")
    public Result<String> removeSpeciesInvestigationApp(
            @ApiParam(name = "id",value = "id",required = true)@RequestParam(value = "id")String id){

        SpeciesInvestigation speciesInvestigation = speciesInvestigationService.getSpeciesInvestigationById(id);
        if(speciesInvestigation == null){
            return Result.error("当前物种调查信息已不存在,出现错误数据显示!");
        }
        //判断当前数据状态是否为'已保存'已撤回,专家填报
        if(StatusEnum.canRemove(speciesInvestigation.getStatus())){
            speciesInvestigationService.removeSpeciesInvestigation(id);
            return Result.ok("当前物种调查信息已删除!");
        }
        return Result.error("当前物种调查信息该状态下不可删除!");
    }
}
