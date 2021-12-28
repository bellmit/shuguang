package com.sofn.agzirdd.web;

import com.google.common.collect.Maps;
import com.sofn.agzirdd.enums.StatusEnum;
import com.sofn.agzirdd.excelmodel.BasicInfoExcel;
import com.sofn.agzirdd.model.BasicInfo;
import com.sofn.agzirdd.service.BasicInfoService;
import com.sofn.agzirdd.sysapi.bean.SysOrganization;
import com.sofn.agzirdd.util.ExportUtil;
import com.sofn.agzirdd.vo.BackObjVo;
import com.sofn.agzirdd.vo.BasicInfoQueryVo;
import com.sofn.agzirdd.vo.PassObjVo;
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
 * @Description: 外来入侵-生物监测点基础信息接口
 * @Author: mcc
 * @Date: 2020\3\6 0006
 */
@Api(value = "外来入侵-生物监测点基础信息接口", tags = "外来入侵-生物监测点基础信息接口")
@RestController
@RequestMapping("/basicInfo")
public class BasicInfoController extends BaseController {

    @Autowired
    private BasicInfoService basicInfoService;

    @SofnLog("获取生物监测点基础基本信息(分页)")
    @ApiOperation(value="获取生物监测点基础基本信息(分页)")
    @PostMapping("/basicInfoByPageList")
    public Result<PageUtils<BasicInfo>> getBasicInfoByPage(
            @Validated @RequestBody @ApiParam(name = "查询参数对象", value = "传入json格式", required = true) BasicInfoQueryVo basicInfoQueryVo,
            HttpServletRequest request){
        //根据token获取登录用户Id
        String userId = UserUtil.getLoginUserId(request.getHeader("Authorization"));
        if(StringUtils.isBlank(userId)){
            throw new SofnException("当前登录用户异常");
        }
        Integer pageNo = basicInfoQueryVo.getPageNo();
        Integer pageSize = basicInfoQueryVo.getPageSize();
        //获取当前用户所属机构区划信息
        Map<String, Object> params = getparamsByLogin(basicInfoQueryVo, userId);

        PageUtils<BasicInfo> basicInfoListByPage = basicInfoService.getBasicInfoListByPage(params, pageNo, pageSize);
        return Result.ok(basicInfoListByPage);
    }

    @SofnLog("获取指定id的生物监测点基础基本信息")
    @ApiOperation(value="获取指定id的生物监测点基础基本信息")
    @GetMapping("/getBasicInfoById")
    public Result<BasicInfo> getBasicInfoById(
            @ApiParam(name = "id",value = "生物监测点基本信息ID",required = true)@RequestParam(value = "id")String id){

        BasicInfo basicInfoById = basicInfoService.getBasicInfoById(id);
        return Result.ok(basicInfoById);
    }

    @SofnLog("新增生物监测点基本信息")
    @ApiOperation(value="新增生物监测点基本信息")
    @PostMapping("/addBasicInfo")
    public Result<String> addBasicInfo(
            @RequestBody @ApiParam(name = "生物监测点基本信息对象", value = "传入json格式", required = true)BasicInfo basicInfo,
            HttpServletRequest request){

        basicInfo.setStatus(StatusEnum.STATUS_0.getCode());
        basicInfoService.addBasicInfo(basicInfo);
        return Result.ok("新增生物监测点基本信息成功");
    }

    @SofnLog("新增并上报生物监测点基本信息")
    @ApiOperation(value="新增并上报生物监测点基本信息")
    @PostMapping("/addAndSubmit")
    public Result<String> addAndSubmit(
            @Validated @RequestBody @ApiParam(name = "生物监测点基本信息对象", value = "传入json格式", required = true)BasicInfo basicInfo,
            HttpServletRequest request){

        basicInfo.setStatus(StatusEnum.STATUS_1.getCode());
        basicInfoService.addBasicInfo(basicInfo);
        return Result.ok("新增并上报生物监测点基本信息成功");
    }

    @SofnLog("编辑生物监测点基本信息")
    @ApiOperation(value="编辑生物监测点基本信息")
    @PostMapping("/updateBasicInfo")
    public Result<String> updateBasicInfo(
            @Validated  @RequestBody@ApiParam(name = "生物监测点基本信息对象", value = "传入json格式", required = true) BasicInfo basicInfo,
            HttpServletRequest request){

        String status00 = StatusEnum.STATUS_0.getCode();
        String status02 = StatusEnum.STATUS_2.getCode();
        String status04 = StatusEnum.STATUS_4.getCode();
        String status06 = StatusEnum.STATUS_6.getCode();
        String status08 = StatusEnum.STATUS_8.getCode();
        String status = basicInfo.getStatus();
        if(status00.equals(status) || status02.equals(status) || status04.equals(status) ||
                status06.equals(status) || status08.equals(status)){

            basicInfoService.updateBasicInfo(basicInfo);
            return Result.ok("编辑生物监测点基本信息成功!");
        }
        return Result.error("当前状态无法进行编辑!");
    }

    @SofnLog("编辑并上报生物监测点基本信息")
    @ApiOperation(value="编辑并上报生物监测点基本信息")
    @PostMapping("/updateAndSubmit")
    public Result<String> updateAndSubmit(
            @RequestBody @ApiParam(name = "生物监测点基本信息对象", value = "传入json格式", required = true) BasicInfo basicInfo,
            HttpServletRequest request){

        String status00 = StatusEnum.STATUS_0.getCode();
        String status02 = StatusEnum.STATUS_2.getCode();
        String status04 = StatusEnum.STATUS_4.getCode();
        String status06 = StatusEnum.STATUS_6.getCode();
        String status08 = StatusEnum.STATUS_8.getCode();
        String status = basicInfo.getStatus();
        if(status00.equals(status) || status02.equals(status) || status04.equals(status) ||
                status06.equals(status) || status08.equals(status)) {
            basicInfo.setStatus(StatusEnum.STATUS_1.getCode());
            basicInfoService.updateBasicInfo(basicInfo);
            return Result.ok("编辑并上报生物监测点基本信息成功!");
        }
        return Result.error("当前状态无法进行编辑提交!");
    }

    @SofnLog("驳回生物监测点基本信息")
    @ApiOperation(value="驳回生物监测点基本信息")
    @PostMapping("/backBasicInfo")
    public Result<String> backBasicInfo(
            @RequestBody @ApiParam(name = "审核退回对象", value = "传入json格式",required = true) BackObjVo backObjVo){

        Map<String, Object> params = Maps.newHashMap();
        params.put("id",backObjVo.getId());
        params.put("status",backObjVo.getStatus());
        params.put("remark",backObjVo.getRemark());
        basicInfoService.updateStatus(params);
        return Result.ok("当前生物监测点基本信息已驳回!");
    }

    @SofnLog("确认通过生物监测点基本信息")
    @ApiOperation(value="确认通过生物监测点基本信息")
    @PostMapping("/passBasicInfo")
    public Result<String> passBasicInfo(
            @RequestBody @ApiParam(name = "审核通过对象", value = "传入json格式",required = true) PassObjVo passObjVo){

        Map<String, Object> params = Maps.newHashMap();
        params.put("id",passObjVo.getId());
        params.put("status",passObjVo.getStatus());
        params.put("remark",passObjVo.getRemark());
        basicInfoService.updateStatus(params);
        return Result.ok("已确认通过生物监测点基本信息!");
    }

    @SofnLog("删除生物监测点基本信息")
    @ApiOperation(value="删除生物监测点基本信息")
    @GetMapping("/removeBasicInfo")
    public Result<String> removeBasicInfo(
            @ApiParam(name = "id",value = "id",required = true)@RequestParam(value = "id")String id){

        String status0 = StatusEnum.STATUS_0.getCode();
        String status2 = StatusEnum.STATUS_2.getCode();

        BasicInfo basicInfo = basicInfoService.getBasicInfoById(id);
        //判断当前数据状态是否为'已保存'状态
        if(status0.equals(basicInfo.getStatus()) || status2.equals(basicInfo.getStatus())){
            basicInfoService.removeBasicInfo(id);
            return Result.ok("当前监测点基本信息已删除!");
        }
        return Result.error("当前生物监测点基本信息该状态下不可删除!");
    }

    @SofnLog("撤回当前监测点基本信息")
    @ApiOperation(value="撤回当前监测点基本信息")
    @GetMapping("/recallBasicInfo")
    public Result<String> recallBasicInfo(
            @ApiParam(name = "id",value = "id",required = true)@RequestParam(value = "id")String id){

        BasicInfo basicInfo = basicInfoService.getBasicInfoById(id);
        //判断当前数据状态是否为'已提交'状态
        if(StatusEnum.STATUS_1.getCode().equals(basicInfo.getStatus())){
            Map<String, Object> params = Maps.newHashMap();
            params.put("id",id);
            params.put("status",StatusEnum.STATUS_2.getCode());
            basicInfoService.updateStatus(params);
            return Result.ok("当前生物监测点基本信息已撤回!");
        }
        return Result.error("当前生物监测点基本信息该状态下不可撤回!");
    }

    @SofnLog(value = "生物监测点基础信息导出")
    @ApiOperation(value = "生物监测点基础信息导出")
    @PostMapping(value = "/exportBasicInfo", produces = "application/octet-stream")
    public void exportBasicInfo(@Validated @RequestBody @ApiParam(name = "查询参数对象", value = "传入json格式", required = true) BasicInfoQueryVo basicInfoQueryVo,
                                HttpServletRequest request,HttpServletResponse response){

        //根据token获取登录用户Id
        String userId = UserUtil.getLoginUserId(request.getHeader("Authorization"));
        Map<String, Object> params = getparamsByLogin(basicInfoQueryVo, userId);

        List<BasicInfoExcel> basicInfoLisExportList = basicInfoService.getBasicInfoLisToExport(params);
        ExportUtil.createExcel(BasicInfoExcel.class,basicInfoLisExportList,response,"生物监测点基础信息列表.xlsx");

    }

    @NotNull
    private Map<String, Object> getparamsByLogin(@ApiParam(name = "查询参数对象", value = "传入json格式", required = true) @RequestBody @Validated BasicInfoQueryVo basicInfoQueryVo, String userId) {
        //获取当前用户所属机构区划信息
        String loginUserOrganizationInfo = UserUtil.getLoginUserOrganizationInfo();
        SysOrganization sysOrganization = JsonUtils.json2obj(loginUserOrganizationInfo, SysOrganization.class);
        //当前用户级别所属区域
        String regionLastCode = sysOrganization.getRegionLastCode();
        //获取当前用户角色列表
        List<String> roleColde = UserUtil.getLoginUserRoleCodeList();
        //放入查询条件
        Map<String, Object> params = Maps.newHashMap();
        params.put("monitorName", basicInfoQueryVo.getMonitorName());
        if (StringUtils.isNotBlank(basicInfoQueryVo.getProvinceId())) {
            params.put("provinceId", basicInfoQueryVo.getProvinceId());
        }
        if (StringUtils.isNotBlank(basicInfoQueryVo.getCityId())) {
            params.put("cityId", basicInfoQueryVo.getCityId());
        }
        if (StringUtils.isNotBlank(basicInfoQueryVo.getCountyId())) {
            params.put("countyId", basicInfoQueryVo.getCountyId());
        }
        params.put("status", basicInfoQueryVo.getStatus());
        params.put("beginDate", basicInfoQueryVo.getBeginDate());
        params.put("endDate", basicInfoQueryVo.getEndDate());
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

}
