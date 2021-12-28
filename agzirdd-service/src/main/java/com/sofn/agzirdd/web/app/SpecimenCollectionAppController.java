package com.sofn.agzirdd.web.app;

import com.google.common.collect.Maps;
import com.sofn.agzirdd.enums.StatusEnum;
import com.sofn.agzirdd.enums.TypeEnum;
import com.sofn.agzirdd.model.SpecimenCollection;
import com.sofn.agzirdd.service.SpecimenCollectionService;
import com.sofn.agzirdd.sysapi.bean.SysOrganization;
import com.sofn.agzirdd.util.RoleCodeUtil;
import com.sofn.agzirdd.vo.SpecimenCollectionForm;
import com.sofn.agzirdd.vo.SpecimenCollectionQueryVo;
import com.sofn.agzirdd.vo.SpecimenCollectionVo;
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
@Api(value = "外来入侵-物种采集模块App接口", tags = "外来入侵-物种采集模块App接口")
@RestController
@RequestMapping("/specimenCollectionApp")
public class SpecimenCollectionAppController extends BaseController {

    /**
     * 物种采集模块-标本采集基本信息
     */
    @Autowired
    private SpecimenCollectionService specimenCollectionService;

    @SofnLog("获取物种标本采集信息(分页)App")
    @ApiOperation(value="获取物种标本采集信息(分页)App")
    @PostMapping("/specimenCollectionByPageListApp")
    public Result<PageUtils<SpecimenCollection>> specimenCollectionByPageListApp(
            @Validated @RequestBody @ApiParam(name = "查询参数对象", value = "传入json格式", required = true) SpecimenCollectionQueryVo specimenCollectionQueryVo,
            HttpServletRequest request){
        //根据token获取登录用户Id
        String userId = UserUtil.getLoginUserId(request.getHeader("Authorization"));
        if(StringUtils.isBlank(userId)){
            throw new SofnException("当前登录用户异常");
        }
        Integer pageNo = specimenCollectionQueryVo.getPageNo();
        Integer pageSize = specimenCollectionQueryVo.getPageSize();
        //放入查询条件
        Map<String, Object> params = getParamsByLogin(specimenCollectionQueryVo, userId);
        PageUtils<SpecimenCollection> specimenCollectionByPage = specimenCollectionService.getSpecimenCollectionByPage(params, pageNo, pageSize);
        return Result.ok(specimenCollectionByPage);
    }

    @SofnLog("获取物种标本采集信息(不分页)")
    @ApiOperation(value="获取物种标本采集信息(不分页)")
    @PostMapping("/specimenCollectionListApp")
    public Result<List<SpecimenCollectionForm>> specimenCollectionListApp(
            @Validated @RequestBody @ApiParam(name = "查询参数对象", value = "传入json格式", required = true) SpecimenCollectionQueryVo specimenCollectionQueryVo,
            HttpServletRequest request){
        //根据token获取登录用户Id
        String userId = UserUtil.getLoginUserId(request.getHeader("Authorization"));
        if(StringUtils.isBlank(userId)){
            throw new SofnException("当前登录用户异常");
        }

        //放入查询条件
        Map<String, Object> params = getParamsByLogin(specimenCollectionQueryVo, userId);
        List<SpecimenCollectionForm> specimenCollectionList = specimenCollectionService.getSpecimenCollectionList(params);
        return Result.ok(specimenCollectionList);
    }

    /**
     * 获取查询条件
     * @param specimenCollectionQueryVo specimenCollectionQueryVo
     * @param userId userId
     * @return map
     */
    @NotNull
    private Map<String, Object> getParamsByLogin(@ApiParam(name = "查询参数对象", value = "传入json格式", required = true) @RequestBody @Validated SpecimenCollectionQueryVo specimenCollectionQueryVo, String userId) {
        //获取当前用户所属机构区划信息
        String loginUserOrganizationInfo = UserUtil.getLoginUserOrganizationInfo();
        SysOrganization sysOrganization = JsonUtils.json2obj(loginUserOrganizationInfo, SysOrganization.class);
        //当前用户级别所属区域
        String regionLastCode = sysOrganization.getRegionLastCode();
        //获取当前用户角色列表
        List<String> roleColde = UserUtil.getLoginUserRoleCodeList();
        //放入查询条件
        Map<String, Object> params = Maps.newHashMap();
        params.put("gatherer", specimenCollectionQueryVo.getGatherer());
        if (StringUtils.isNotBlank(specimenCollectionQueryVo.getProvinceId())) {
            params.put("provinceId", specimenCollectionQueryVo.getProvinceId());
        }
        if (StringUtils.isNotBlank(specimenCollectionQueryVo.getCityId())) {
            params.put("cityId", specimenCollectionQueryVo.getCityId());
        }
        if (StringUtils.isNotBlank(specimenCollectionQueryVo.getCountyId())) {
            params.put("countyId", specimenCollectionQueryVo.getCountyId());
        }
        params.put("status", specimenCollectionQueryVo.getStatus());
        params.put("beginDate", specimenCollectionQueryVo.getBeginDate());
        params.put("endDate", specimenCollectionQueryVo.getEndDate());
        params.put("inStatus",StatusEnum.getShowStatusByRole(RoleCodeUtil.getLoginUserAgzirddRoleCode()));
        String type = specimenCollectionQueryVo.getType();
        String speciesId = specimenCollectionQueryVo.getSpeciesId();
        //判断当前类型->植物,动物,微生物
        if(TypeEnum.PLANT.getCode().equals(type)){
            params.put("plantSpeciesId", speciesId);
        }else if(TypeEnum.ANIMAL.getCode().equals(type)){
            params.put("animalSpeciesId", speciesId);
        }else if(TypeEnum.MICROBE.getCode().equals(type)){
            params.put("microbeSpeciesId", speciesId);
        }
        params.put("type", type);
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

    @SofnLog("获取指定id的物种标本采集信息App")
    @ApiOperation(value="获取指定id的物种标本采集信息App")
    @GetMapping("/getSpecimenCollectionAllByIdApp")
    public Result<SpecimenCollectionVo> getSpecimenCollectionAllById(
            @ApiParam(name = "id",value = "物种采集信息ID",required = true)@RequestParam(value = "id")String id){

        SpecimenCollectionVo specimenCollectionAllById = specimenCollectionService.getSpecimenCollectionAllById(id);
        return Result.ok(specimenCollectionAllById);
    }

    @SofnLog("新增物种标本采集信息App")
    @ApiOperation(value="新增物种标本采集信息App")
    @PostMapping("/addSpecimenCollectionApp")
    public Result<String> addSpecimenCollectionApp(
            @RequestBody @ApiParam(name = "物种标本采集信息对象", value = "传入json格式", required = true)SpecimenCollectionVo specimenCollectionVo,
            HttpServletRequest request){

//        String loginUserOrganizationInfo = UserUtil.getLoginUserOrganizationInfo();
//        SysOrganization sysOrganization = JsonUtils.json2obj(loginUserOrganizationInfo, SysOrganization.class);
//        List<String> regionCode = JsonUtils.json2List(sysOrganization.getRegioncode(), String.class);
//        specimenCollectionVo.setProvinceId(regionCode.get(0));

        //获取当前用户角色列表
        List<String> roleColde = UserUtil.getLoginUserRoleCodeList();
        if (roleColde != null && roleColde.contains("agzirdd_expert")) {
            specimenCollectionVo.setStatus(StatusEnum.STATUS_9.getCode());
        }else{
            specimenCollectionVo.setStatus(StatusEnum.STATUS_0.getCode());
        }
        specimenCollectionService.addSpecimenCollection(specimenCollectionVo);
        return Result.ok("新增物种标本采集信息成功");
    }

    @SofnLog("新增并上报生物监测点基本信息App")
    @ApiOperation(value="新增并上报生物监测点基本信息App")
    @PostMapping("/addAndSubmitApp")
    public Result<String> addAndSubmitApp(
            @Validated @RequestBody @ApiParam(name = "物种标本采集信息对象", value = "传入json格式", required = true)SpecimenCollectionVo specimenCollectionVo,
            HttpServletRequest request){

//        String loginUserOrganizationInfo = UserUtil.getLoginUserOrganizationInfo();
//        SysOrganization sysOrganization = JsonUtils.json2obj(loginUserOrganizationInfo, SysOrganization.class);
//        List<String> regionCode = JsonUtils.json2List(sysOrganization.getRegioncode(), String.class);
//        specimenCollectionVo.setProvinceId(regionCode.get(0));

        //获取当前用户角色列表
        List<String> roleColde = UserUtil.getLoginUserRoleCodeList();
        if (roleColde != null && roleColde.contains("agzirdd_expert")) {
            specimenCollectionVo.setStatus(StatusEnum.STATUS_10.getCode());
        }else{
            specimenCollectionVo.setStatus(StatusEnum.STATUS_1.getCode());
        }
        specimenCollectionService.addSpecimenCollection(specimenCollectionVo);
        return Result.ok("新增并上报物种标本采集信息成功!");
    }

    @SofnLog("编辑物种标本采集信息App")
    @ApiOperation(value="编辑物种标本采集信息App")
    @PostMapping("/updateSpecimenCollectionApp")
    public Result<String> updateSpecimenCollectionApp(
            @Validated  @RequestBody@ApiParam(name = "物种标本采集信息对象", value = "传入json格式", required = true) SpecimenCollectionVo specimenCollectionVo,
            HttpServletRequest request){

        String status00 = StatusEnum.STATUS_0.getCode();
        String status02 = StatusEnum.STATUS_2.getCode();
        String status04 = StatusEnum.STATUS_4.getCode();
        String status06 = StatusEnum.STATUS_6.getCode();
        String status08 = StatusEnum.STATUS_8.getCode();
        String status09 = StatusEnum.STATUS_9.getCode();
        String status = specimenCollectionVo.getStatus();
        if(status00.equals(status) || status02.equals(status) || status04.equals(status) ||
                status06.equals(status) || status08.equals(status) || status09.equals(status)){
            specimenCollectionService.updateSpecimenCollection(specimenCollectionVo);
            return Result.ok("编辑物种标本采集信息成功!");
        }
        return Result.error("当前状态无法进行编辑!");

    }

    @SofnLog("编辑并上报物种标本采集信息App")
    @ApiOperation(value="编辑并上报物种标本采集信息App")
    @PostMapping("/updateAndSubmitApp")
    public Result<String> updateAndSubmitApp(
            @RequestBody @ApiParam(name = "物种标本采集信息对象", value = "传入json格式", required = true) SpecimenCollectionVo specimenCollectionVo,
            HttpServletRequest request){

        String status00 = StatusEnum.STATUS_0.getCode();
        String status02 = StatusEnum.STATUS_2.getCode();
        String status04 = StatusEnum.STATUS_4.getCode();
        String status06 = StatusEnum.STATUS_6.getCode();
        String status08 = StatusEnum.STATUS_8.getCode();
        String status09 = StatusEnum.STATUS_9.getCode();
        SpecimenCollection specimenCollection = specimenCollectionService.getSpecimenCollectionById(specimenCollectionVo.getId());
        String status = specimenCollection.getStatus();
        if(status00.equals(status) || status02.equals(status) || status04.equals(status) ||
                status06.equals(status) || status08.equals(status) || status09.equals(status)){

            //获取当前用户角色列表
            List<String> roleColde = UserUtil.getLoginUserRoleCodeList();
            if (roleColde != null && roleColde.contains("agzirdd_expert")) {
                specimenCollectionVo.setStatus(StatusEnum.STATUS_10.getCode());
            }else{
                specimenCollectionVo.setStatus(StatusEnum.STATUS_1.getCode());
            }
            specimenCollectionService.updateSpecimenCollection(specimenCollectionVo);
            return Result.ok("编辑并上报物种标本采集信息成功!");
        }
        return Result.error("当前状态无法进行编辑提交!");
    }

    @SofnLog("上报物种采集信息App")
    @ApiOperation(value="上报物种采集信息App")
    @GetMapping("/submitSpecimenCollectionApp")
    public Result<String> submitSpecimenCollectionApp(
            @ApiParam(name = "id",value = "id",required = true)@RequestParam(value = "id")String id){

        String status00 = StatusEnum.STATUS_0.getCode();
        String status09 = StatusEnum.STATUS_9.getCode();
        //获取当前用户角色列表
        List<String> roleColde = UserUtil.getLoginUserRoleCodeList();
        SpecimenCollection specimenCollection = specimenCollectionService.getSpecimenCollectionById(id);
        //判断当前数据状态是否为'已保存',专家填报状态
        if(status00.equals(specimenCollection.getStatus()) || status09.equals(specimenCollection.getStatus())){
            Map<String, Object> params = Maps.newHashMap();
            params.put("id",id);
            //判断当前用户角色
            if (roleColde != null && roleColde.contains("agzirdd_expert")) {
                params.put("status",StatusEnum.STATUS_10.getCode());
            }else{
                params.put("status",StatusEnum.STATUS_1.getCode());
            }
            specimenCollectionService.updateStatus(params);
            return Result.ok("当前物种监测信息已提交!");
        }
        return Result.error("当前物种监测信息该状态下不可提交!");

    }

    @SofnLog("撤回当前物种标本采集信息App")
    @ApiOperation(value="撤回当前物种标本采集信息App")
    @GetMapping("/recallSpecimenCollectionApp")
    public Result<String> recallSpecimenCollection(
            @ApiParam(name = "id",value = "id",required = true)@RequestParam(value = "id")String id){

        String status01 = StatusEnum.STATUS_1.getCode();
        String status10 = StatusEnum.STATUS_10.getCode();

        SpecimenCollection specimenCollection = specimenCollectionService.getSpecimenCollectionById(id);
        //判断当前数据状态是否为'已提交'状态
        if(status01.equals(specimenCollection.getStatus()) || status10.equals(specimenCollection.getStatus())){
            Map<String, Object> params = Maps.newHashMap();
            params.put("id",id);
            params.put("status",StatusEnum.STATUS_2.getCode());
            specimenCollectionService.updateStatus(params);
            return Result.ok("当前物种标本采集信息已撤回!");
        }
        return Result.error("当前物种标本采集信息该状态下不可撤回!");
    }

    @SofnLog("删除物种标本采集信息App")
    @ApiOperation(value="删除物种标本采集信息App")
    @GetMapping("/removeSpecimenCollectionApp")
    public Result<String> removeSpecimenCollectionApp(
            @ApiParam(name = "id",value = "id",required = true)@RequestParam(value = "id")String id){

        SpecimenCollection specimenCollection = specimenCollectionService.getSpecimenCollectionById(id);
        if(specimenCollection == null){
            return Result.error("当前物种标本采集信息已不存在,出现错误数据显示!");
        }
        //判断当前数据状态是否为'已保存'状态
        if(StatusEnum.canRemove(specimenCollection.getStatus())){
            specimenCollectionService.removeSpecimenCollection(id);
            return Result.ok("当前物种标本采集信息已删除!");
        }
        return Result.error("当前物种标本采集信息该状态下不可删除!");
    }
}
