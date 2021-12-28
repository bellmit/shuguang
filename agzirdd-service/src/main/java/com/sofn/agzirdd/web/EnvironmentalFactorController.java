package com.sofn.agzirdd.web;

import com.google.common.collect.Maps;
import com.sofn.agzirdd.excelmodel.EnvironmentalFactorExcel;
import com.sofn.agzirdd.model.EnvironmentalFactor;
import com.sofn.agzirdd.service.EnvironmentalFactorService;
import com.sofn.agzirdd.sysapi.bean.SysOrganization;
import com.sofn.agzirdd.util.ExportUtil;
import com.sofn.agzirdd.vo.EnvironmentalFactorQueryVo;
import com.sofn.common.exception.SofnException;
import com.sofn.common.log.SofnLog;
import com.sofn.common.model.Result;
import com.sofn.common.utils.IdUtil;
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
 * @author Administrator
 */
@Api(value = "外来入侵-生物监测点环境因子信息接口", tags = "外来入侵-生物监测点环境因子信息接口")
@RestController
@RequestMapping("/environmentalFactor")
public class EnvironmentalFactorController extends BaseController {

    @Autowired
    private EnvironmentalFactorService environmentalFactorService;

    @SofnLog("获取生物监测点环境因子信息(分页)")
    @ApiOperation(value="获取生物监测点环境因子信息(分页)")
    @PostMapping("/environmentalFactorByPageList")
    public Result<PageUtils<EnvironmentalFactor>> getEnvironmentalFactorByPage(
            @Validated @RequestBody @ApiParam(name = "查询参数对象", value = "传入json格式", required = true) EnvironmentalFactorQueryVo environmentalFactorQueryVo,
            HttpServletRequest request){
        //根据token获取登录用户Id
        String userId = UserUtil.getLoginUserId(request.getHeader("Authorization"));
        if(StringUtils.isBlank(userId)){
            throw new SofnException("当前登录用户异常");
        }
        Integer pageNo = environmentalFactorQueryVo.getPageNo();
        Integer pageSize = environmentalFactorQueryVo.getPageSize();
        Map<String, Object> params = getParamsByLogin(environmentalFactorQueryVo, userId);

        PageUtils<EnvironmentalFactor> environmentalFactorListByPage = environmentalFactorService.getEnvironmentalFactorListByPage(params, pageNo, pageSize);
        return Result.ok(environmentalFactorListByPage);
    }

    @NotNull
    private Map<String, Object> getParamsByLogin(@ApiParam(name = "查询参数对象", value = "传入json格式", required = true) @RequestBody @Validated EnvironmentalFactorQueryVo environmentalFactorQueryVo, String userId) {
        String loginUserOrganizationInfo = UserUtil.getLoginUserOrganizationInfo();
        SysOrganization sysOrganization = JsonUtils.json2obj(loginUserOrganizationInfo, SysOrganization.class);
        String regionLastCode = sysOrganization.getRegionLastCode();
        //获取当前用户角色列表
        List<String> roleColde = UserUtil.getLoginUserRoleCodeList();

        //放入查询条件
        Map<String, Object> params = Maps.newHashMap();
        params.put("monitorId", environmentalFactorQueryVo.getMonitorId());
        params.put("soilTemperature", environmentalFactorQueryVo.getSoilTemperature());
        params.put("soilHumidity", environmentalFactorQueryVo.getSoilHumidity());
        params.put("beginDate", environmentalFactorQueryVo.getBeginDate());
        params.put("endDate", environmentalFactorQueryVo.getEndDate());
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

    @SofnLog("获取指定id的生物监测点环境因子信息")
    @ApiOperation(value="获取指定id的生物监测点环境因子信息")
    @GetMapping("/getEnvironmentalFactorById")
    public Result<EnvironmentalFactor> getEnvironmentalFactorById(
            @ApiParam(name = "id",value = "生物监测点环境因子信息ID",required = true)@RequestParam(value = "id")String id){

        EnvironmentalFactor environmentalFactorById = environmentalFactorService.getEnvironmentalFactorById(id);
        return Result.ok(environmentalFactorById);
    }

    @SofnLog("新增生物监测点环境因子信息")
    @ApiOperation(value="新增生物监测点环境因子信息")
    @PostMapping("/addEnvironmentalFactor")
    public Result<String> addEnvironmentalFactor(
            @RequestBody @ApiParam(name = "生物监测点环境因子信息对象", value = "传入json格式", required = true)EnvironmentalFactor environmentalFactor,
            HttpServletRequest request){
        String loginUserOrganizationInfo = UserUtil.getLoginUserOrganizationInfo();
        SysOrganization sysOrganization = JsonUtils.json2obj(loginUserOrganizationInfo, SysOrganization.class);
        List<String> regionCode = JsonUtils.json2List(sysOrganization.getRegioncode(), String.class);
        if (regionCode != null) {
            environmentalFactor.setProvinceId(regionCode.get(0));
            environmentalFactor.setCityId(regionCode.get(1));
            environmentalFactor.setCountyId(regionCode.get(2));
        }
        environmentalFactorService.addEnvironmentalFactor(environmentalFactor);
        return Result.ok("新增生物监测点环境因子信息成功");
    }

    @SofnLog("编辑生物监测点环境因子信息")
    @ApiOperation(value="编辑生物监测点环境因子信息")
    @PostMapping("/updateEnvironmentalFactor")
    public Result<String> updateEnvironmentalFactor(
            @Validated  @RequestBody@ApiParam(name = "生物监测点环境因子信息对象", value = "传入json格式", required = true) EnvironmentalFactor environmentalFactor,
            HttpServletRequest request){

        environmentalFactorService.updateEnvironmentalFactor(environmentalFactor);
        return Result.ok("编辑生物监测点环境因子信息成功!");
    }

    @SofnLog("删除生物监测点环境因子信息")
    @ApiOperation(value="删除生物监测点环境因子信息")
    @GetMapping("/removeEnvironmentalFactor")
    public Result<String> removeEnvironmentalFactor(
            @ApiParam(name = "id",value = "id",required = true)@RequestParam(value = "id")String id){

        environmentalFactorService.removeEnvironmentalFactor(id);
        return Result.ok("当前监测点环境因子信息已删除!");
    }

    @SofnLog(value = "生物监测点环境因子信息导出")
    @ApiOperation(value = "生物监测点环境因子信息导出")
    @PostMapping(value = "/exportEnvironmentalFactor", produces = "application/octet-stream")
    public void exportEnvironmentalFactor(
            @Validated @RequestBody @ApiParam(name = "查询参数对象", value = "传入json格式", required = true) EnvironmentalFactorQueryVo environmentalFactorQueryVo,
            HttpServletResponse response){

        //根据token获取登录用户Id
        String userId = UserUtil.getLoginUserId();
        Map<String, Object> params = getParamsByLogin(environmentalFactorQueryVo, userId);
        List<EnvironmentalFactorExcel> environmentalFactorList =
                environmentalFactorService.getEnvironmentalFactorListToExport(params);
        ExportUtil.createExcel(EnvironmentalFactorExcel.class,environmentalFactorList,response,"生物监测点环境因子信息列表.xlsx");
    }
}
