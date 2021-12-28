package com.sofn.agzirdd.web;

import com.google.common.collect.Maps;
import com.sofn.agzirdd.enums.StatusEnum;
import com.sofn.agzirdd.excelmodel.PhysiologicalParametersExcel;
import com.sofn.agzirdd.model.PhysiologicalParameters;
import com.sofn.agzirdd.service.PhysiologicalParametersService;
import com.sofn.agzirdd.sysapi.bean.SysOrganization;
import com.sofn.agzirdd.util.ExportUtil;
import com.sofn.agzirdd.vo.BackObjVo;
import com.sofn.agzirdd.vo.PassObjVo;
import com.sofn.agzirdd.vo.PhysiologicalParametersQueryVo;
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
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @Description: 外来入侵-生物监测点植被生理参数信息接口
 * @Author: mcc
 * @Date: 2020\3\9 0009
 */
@Api(value = "外来入侵-生物监测点植被生理参数信息接口", tags = "外来入侵-生物监测点植被生理参数信息接口")
@RestController
@RequestMapping("/physiologicalParameters")
public class PhysiologicalParametersController extends BaseController {

    @Autowired
    private PhysiologicalParametersService physiologicalParametersService;

    @SofnLog("获取生物监测点植被生理参数信息(分页)")
    @ApiOperation(value="获取生物监测点植被生理参数信息(分页)")
    @PostMapping("/physiologicalParametersByPageList")
    public Result<PageUtils<PhysiologicalParameters>> getPhysiologicalParametersByPage(
            @Validated @RequestBody @ApiParam(name = "查询参数对象", value = "传入json格式", required = true) PhysiologicalParametersQueryVo physiologicalParametersQueryVo,
            HttpServletRequest request){
        //根据token获取登录用户Id
        String userId = UserUtil.getLoginUserId(request.getHeader("Authorization"));
        if(StringUtils.isBlank(userId)){
            throw new SofnException("当前登录用户异常");
        }
        Integer pageNo = physiologicalParametersQueryVo.getPageNo();
        Integer pageSize = physiologicalParametersQueryVo.getPageSize();

        Map<String, Object> params = getParamsByLogin(physiologicalParametersQueryVo, userId);

        PageUtils<PhysiologicalParameters> physiologicalParametersListByPage = physiologicalParametersService.getPhysiologicalParametersListByPage(params, pageNo, pageSize);
        return Result.ok(physiologicalParametersListByPage);
    }

    @NotNull
    private Map<String, Object> getParamsByLogin(@ApiParam(name = "查询参数对象", value = "传入json格式", required = true) @RequestBody @Validated PhysiologicalParametersQueryVo physiologicalParametersQueryVo, String userId) {
        String loginUserOrganizationInfo = UserUtil.getLoginUserOrganizationInfo();
        SysOrganization sysOrganization = JsonUtils.json2obj(loginUserOrganizationInfo, SysOrganization.class);
        String regionLastCode = sysOrganization.getRegionLastCode();
        //获取当前用户角色列表
        List<String> roleColde = UserUtil.getLoginUserRoleCodeList();
        //放入查询条件
        Map<String, Object> params = Maps.newHashMap();
        params.put("monitorId", physiologicalParametersQueryVo.getMonitorId());
        params.put("leafAreaIndex", physiologicalParametersQueryVo.getLeafAreaIndex());
        params.put("status", physiologicalParametersQueryVo.getStatus());
        params.put("beginDate", physiologicalParametersQueryVo.getBeginDate());
        params.put("endDate", physiologicalParametersQueryVo.getEndDate());
        //是否为县级人员
        if (roleColde != null && roleColde.contains("agzirdd_county")) {
            //params.put("countyId", regionLastCode);
            params.put("createUserId", userId);
        }
        //市级人员
        if (roleColde != null && roleColde.contains("agzirdd_city")) {
            params.put("cityId", regionLastCode);
        }
        //省级人员
        if (roleColde != null && roleColde.contains("agzirdd_province")) {
            params.put("provinceId", regionLastCode);
        }
        return params;
    }

    @SofnLog("获取指定id的生物监测点植被生理参数信息")
    @ApiOperation(value="获取指定id的生物监测点植被生理参数信息")
    @GetMapping("/getPhysiologicalParametersById")
    public Result<PhysiologicalParameters> getPhysiologicalParametersById(
            @ApiParam(name = "id",value = "id",required = true)@RequestParam(value = "id")String id){

        PhysiologicalParameters physiologicalParametersById = physiologicalParametersService.getPhysiologicalParametersById(id);
        return Result.ok(physiologicalParametersById);
    }

    @SofnLog("新增生物监测点植被生理参数信息")
    @ApiOperation(value="新增生物监测点植被生理参数信息")
    @PostMapping("/addPhysiologicalParameters")
    public Result<String> addPhysiologicalParameters(
            @RequestBody @ApiParam(name = "生物监测点植被生理参数信息对象", value = "传入json格式", required = true)PhysiologicalParameters physiologicalParameters){

        String loginUserOrganizationInfo = UserUtil.getLoginUserOrganizationInfo();
        SysOrganization sysOrganization = JsonUtils.json2obj(loginUserOrganizationInfo, SysOrganization.class);
        List<String> regionCode = JsonUtils.json2List(sysOrganization.getRegioncode(), String.class);
        if (regionCode != null) {
            physiologicalParameters.setProvinceId(regionCode.get(0));
            physiologicalParameters.setCityId(regionCode.get(1));
            physiologicalParameters.setCountyId(regionCode.get(2));
        }
        physiologicalParameters.setStatus(StatusEnum.STATUS_0.getCode());
        physiologicalParametersService.addPhysiologicalParameters(physiologicalParameters);
        return Result.ok("新增生物监测点植被生理参数信息成功");
    }

    @SofnLog("新增并上报生物监测点植被生理参数信息")
    @ApiOperation(value="新增并上报生物监测点植被生理参数信息")
    @PostMapping("/addAndSubmit")
    public Result<String> addAndSubmit(
            @RequestBody @ApiParam(name = "生物监测点植被生理参数信息对象", value = "传入json格式", required = true)PhysiologicalParameters physiologicalParameters){

        physiologicalParameters.setStatus(StatusEnum.STATUS_1.getCode());
        physiologicalParametersService.addPhysiologicalParameters(physiologicalParameters);
        return Result.ok("新增并上报生物监测点植被生理参数信息成功");
    }

    @SofnLog("编辑生物监测点植被生理参数信息")
    @ApiOperation(value="编辑生物监测点植被生理参数信息")
    @PostMapping("/updatePhysiologicalParameters")
    public Result<String> updatePhysiologicalParameters(
            @RequestBody @ApiParam(name = "生物监测点植被生理参数信息对象", value = "传入json格式", required = true)PhysiologicalParameters physiologicalParameters){

        String status00 = StatusEnum.STATUS_0.getCode();
        String status02 = StatusEnum.STATUS_2.getCode();
        String status04 = StatusEnum.STATUS_4.getCode();
        String status06 = StatusEnum.STATUS_6.getCode();
        String status08 = StatusEnum.STATUS_8.getCode();
        String status = physiologicalParameters.getStatus();
        if(status00.equals(status) || status02.equals(status) || status04.equals(status) ||
                status06.equals(status) || status08.equals(status)){

            physiologicalParametersService.updatePhysiologicalParameters(physiologicalParameters);
            return Result.ok("编辑生物监测点植被生理参数信息成功!");
        }
        return Result.error("当前状态无法进行编辑!");
    }

    @SofnLog("编辑并上报生物监测点植被生理参数信息")
    @ApiOperation(value="编辑并上报生物监测点植被生理参数信息")
    @PostMapping("/updateAndSubmit")
    public Result<String> updateAndSubmit(
            @RequestBody @ApiParam(name = "生物监测点植被生理参数信息对象", value = "传入json格式", required = true)PhysiologicalParameters physiologicalParameters){

        String status00 = StatusEnum.STATUS_0.getCode();
        String status02 = StatusEnum.STATUS_2.getCode();
        String status04 = StatusEnum.STATUS_4.getCode();
        String status06 = StatusEnum.STATUS_6.getCode();
        String status08 = StatusEnum.STATUS_8.getCode();
        String status = physiologicalParameters.getStatus();
        if(status00.equals(status) || status02.equals(status) || status04.equals(status) ||
                status06.equals(status) || status08.equals(status)){
            physiologicalParameters.setStatus(StatusEnum.STATUS_1.getCode());
            physiologicalParametersService.updatePhysiologicalParameters(physiologicalParameters);
            return Result.ok("编辑并上报生物监测点植被生理参数信息成功!");
        }
        return Result.error("当前状态无法进行编辑提交!");
    }

    @SofnLog("驳回生物监测点植被生理参数信息")
    @ApiOperation(value="驳回生物监测点植被生理参数信息")
    @PostMapping("/backPhysiologicalParameters")
    public Result<String> backPhysiologicalParameters(
            @RequestBody @ApiParam(name = "审核退回对象", value = "传入json格式",required = true) BackObjVo backObjVo){

        Map<String, Object> params = Maps.newHashMap();
        params.put("id",backObjVo.getId());
        params.put("status",backObjVo.getStatus());
        params.put("remark",backObjVo.getRemark());
        physiologicalParametersService.updateStatus(params);
        return Result.ok("当前生物监测点植被生理参数信息已驳回!");
    }

    @SofnLog("确认通过生物监测点植被生理参数信息")
    @ApiOperation(value="确认通过生物监测点植被生理参数信息")
    @PostMapping("/passPhysiologicalParameters")
    public Result<String> passPhysiologicalParameters(
            @RequestBody @ApiParam(name = "审核通过对象", value = "传入json格式",required = true) PassObjVo passObjVo){

        Map<String, Object> params = Maps.newHashMap();
        params.put("id",passObjVo.getId());
        params.put("status",passObjVo.getStatus());
        params.put("remark",passObjVo.getRemark());
        physiologicalParametersService.updateStatus(params);
        return Result.ok("已确认通过生物监测点植被生理参数信息!");
    }

    @SofnLog("删除生物监测点植被生理参数信息")
    @ApiOperation(value="删除生物监测点植被生理参数信息")
    @GetMapping("/removePhysiologicalParameters")
    public Result<String> removePhysiologicalParameters(
            @ApiParam(name = "id",value = "id",required = true)@RequestParam(value = "id")String id){

        String status0 = StatusEnum.STATUS_0.getCode();
        String status2 = StatusEnum.STATUS_2.getCode();

        PhysiologicalParameters physiologicalParameters = physiologicalParametersService.getPhysiologicalParametersById(id);
        //判断当前数据状态是否为'已保存'状态
        if(status0.equals(physiologicalParameters.getStatus()) || status2.equals(physiologicalParameters.getStatus())){
            physiologicalParametersService.removePhysiologicalParameters(id);
            return Result.ok("当前监测点植被生理参数信息已删除!");
        }
        return Result.error("当前监测点植被生理参数信息该状态下不可删除!");
    }

    @SofnLog("撤回当前监测点植被生理参数信息")
    @ApiOperation(value="撤回当前监测点植被生理参数信息")
    @GetMapping("/recallPhysiologicalParameters")
    public Result<String> recallPhysiologicalParameters(
            @ApiParam(name = "id",value = "id",required = true)@RequestParam(value = "id")String id){

        PhysiologicalParameters physiologicalParameters = physiologicalParametersService.getPhysiologicalParametersById(id);
        //判断当前数据状态是否为'已提交'状态
        if(StatusEnum.STATUS_1.getCode().equals(physiologicalParameters.getStatus())){
            Map<String, Object> params = Maps.newHashMap();
            params.put("id",id);
            params.put("status",StatusEnum.STATUS_2.getCode());
            physiologicalParametersService.updateStatus(params);
            return Result.ok("当前监测点植被生理参数信息已撤回!");
        }
        return Result.error("当前监测点植被生理参数信息该状态下不可撤回!");
    }

    @SofnLog(value = "生物监测点植被生理参数信息导出")
    @ApiOperation(value = "生物监测点植被生理参数信息导出")
    @PostMapping(value = "/exportPhysiologicalParameters", produces = "application/octet-stream")
    public void exportPhysiologicalParameters(
            @Validated @RequestBody @ApiParam(name = "查询参数对象", value = "传入json格式", required = true) PhysiologicalParametersQueryVo physiologicalParametersQueryVo,
             HttpServletRequest request,HttpServletResponse response){

        //根据token获取登录用户Id
        String userId = UserUtil.getLoginUserId(request.getHeader("Authorization"));

        Map<String, Object> params = getParamsByLogin(physiologicalParametersQueryVo, userId);

        List<PhysiologicalParametersExcel> physiologicalParametersListToExport = physiologicalParametersService.getPhysiologicalParametersListToExport(params);
        ExportUtil.createExcel(PhysiologicalParametersExcel.class,physiologicalParametersListToExport,response,
                "生物监测点植被生理参数信息列表.xlsx");
    }
}
