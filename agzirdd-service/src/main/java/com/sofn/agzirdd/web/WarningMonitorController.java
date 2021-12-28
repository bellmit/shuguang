package com.sofn.agzirdd.web;

import com.google.common.collect.Maps;
import com.sofn.agzirdd.excelmodel.WarningThresholdExcel;
import com.sofn.agzirdd.model.WarningThreshold;
import com.sofn.agzirdd.service.WarningMonitorService;
import com.sofn.agzirdd.service.WarningThresholdService;
import com.sofn.agzirdd.sysapi.bean.SysOrganization;
import com.sofn.agzirdd.util.ExportUtil;
import com.sofn.agzirdd.vo.WarningMonitorQueryVo;
import com.sofn.agzirdd.vo.WarningMonitorVo;
import com.sofn.agzirdd.vo.WarningThresholdQueryVo;
import com.sofn.agzirdd.vo.WarningThresholdVo;
import com.sofn.common.exception.SofnException;
import com.sofn.common.log.SofnLog;
import com.sofn.common.map.MapViewData;
import com.sofn.common.model.Result;
import com.sofn.common.model.User;
import com.sofn.common.utils.JsonUtils;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.UserUtil;
import com.sofn.common.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Chlf
 */
@Api(value = "外来入侵-预警模块接口", tags = "外来入侵-预警模块接口")
@RestController
@RequestMapping("/warningMonitor")
public class WarningMonitorController extends BaseController {
    //appid
    private static final String APPID = "agzirdd";
    //指标分类
    private static final String TYPE = "indexClassify";

    /**
     * 外来入侵物种监测预警模块总的controller
     */
    @Autowired
    private WarningMonitorService warningMonitorService;
    @Autowired
    private WarningThresholdService warningThresholdService;

    @SofnLog("获取物种监测预警阈值分页")
    @ApiOperation(value="获取物种监测预警阈值分页列表")
    @PostMapping("/getThresholdByPage")
    public Result<PageUtils<WarningThreshold>> getThresholdByPage(
            @Validated @RequestBody @ApiParam(name = "查询参数对象", value = "传入json格式", required = true) WarningThresholdQueryVo warningThresholdQueryVo){
        String userId = UserUtil.getLoginUserId();
        if(StringUtils.isBlank(userId)){
            throw new SofnException("当前登录用户异常");
        }
        //放入查询条件
        Map<String, Object> params = Maps.newHashMap();
        List<String> userRoleList = UserUtil.getLoginUserRoleCodeList();
        for (String roleCode : userRoleList){
            if("agzirdd_terminal".equals(roleCode)){
                params.put("isSubmit","true");
                break;
            }else if("agzirdd_province".equals(roleCode)){
                //获取当前用户所属机构区划信息
                String loginUserOrganizationInfo = UserUtil.getLoginUserOrganizationInfo();
                SysOrganization orgData = JsonUtils.json2obj(loginUserOrganizationInfo, SysOrganization.class);
                //判断当前用户所在机构的类型(Y-行政机构,N-代理机构)
                if(orgData.getThirdOrg().equals("Y")) {
                    String address = orgData.getAddress();
                    List<String> areaList = JsonUtils.json2List(address, String.class);
                    params.put("provinceId",areaList.get(0));   //将provinceId存入查询条件中
                }
                break;
            }
        }

        params.put("classificationId",warningThresholdQueryVo.getClassificationId());
        params.put("speciesId",warningThresholdQueryVo.getSpeciesId());
        Integer pageNo = warningThresholdQueryVo.getPageNo();
        Integer pageSize = warningThresholdQueryVo.getPageSize();
        PageUtils<WarningThreshold> warningThresholdPage = warningThresholdService.getWarningThresholdByPage(params, pageNo, pageSize);
        return Result.ok(warningThresholdPage);
    }

    @SofnLog("新增物种监测预警阈值")
    @ApiOperation(value="新增物种监测预警阈值信息")
    @PostMapping("/addWarningThreshold")
    public Result<String> addWarningThreshold(
            @RequestBody @ApiParam(name = "物种监测预警信息对象", value = "传入json格式", required = true) WarningThresholdVo warningThresholdVo){
        User userInfo = UserUtil.getLoginUser();
        if(null==userInfo){
            throw new SofnException("当前登录用户异常");
        }

        return warningThresholdService.addWarningThreshold(userInfo, warningThresholdVo);
    }

    @SofnLog("根据ID获取物种监测预警阈值")
    @ApiOperation(value="根据ID获取物种监测预警阈值")
    @GetMapping("/getWarningThresholdById")
    public Result<WarningThresholdVo> getWarningThresholdById(
            @ApiParam(name = "id",value = "id",required = true)@RequestParam(value = "id")String id){
        User userInfo = UserUtil.getLoginUser();
        if(null==userInfo){
            throw new SofnException("当前登录用户异常");
        }
        WarningThresholdVo vo = warningThresholdService.getWarningThresholdVoById(id);
        return Result.ok(vo);
    }

    @SofnLog("删除物种监测预警阈值")
    @ApiOperation(value="删除物种监测预警阈值")
    @DeleteMapping("/deleteById")
    public Result deleteById(
            @ApiParam(name = "id",value = "id",required = true)@RequestParam(value = "id")String id){
        User userInfo = UserUtil.getLoginUser();
        if(null==userInfo){
            throw new SofnException("当前登录用户异常");
        }

        return warningThresholdService.deleteWarningThreshold(id);
    }

    @SofnLog("编辑物种监测预警阈值")
    @ApiOperation(value="编辑物种监测预警阈值")
    @PostMapping("/updateWarningThreshold")
    public Result updateWarningThreshold(
            @RequestBody @ApiParam(name = "修改后的物种监测预警信息对象，页面所有值都传入，不管是否修改过", value = "传入json格式", required = true) WarningThresholdVo warningThresholdVo){
        User userInfo = UserUtil.getLoginUser();
        if(null==userInfo){
            throw new SofnException("当前登录用户异常");
        }

        return warningThresholdService.updateWarningThreshold(warningThresholdVo);
    }

    @SofnLog("入侵物种信息【预警图】")
    @ApiOperation(value="获取入侵物种信息【预警图】")
    @PostMapping("/getWarningMapInfo")
    public Result<List<WarningMonitorVo>> getWarningMapInfo(
            @Validated @RequestBody @ApiParam(name = "查询参数对象", value = "传入json格式", required = true) WarningMonitorQueryVo warningMonitorQueryVo){
        //放入查询条件
        Map<String, String> params = Maps.newHashMap();
        params.put("classificationId",warningMonitorQueryVo.getClassificationId());
        params.put("speciesName",warningMonitorQueryVo.getSpeciesName());
        params.put("belongYear",warningMonitorQueryVo.getBelongYear());

        List<WarningMonitorVo> list = warningMonitorService.getWarningMonitorVoList(params);
        return Result.ok(list);
    }

    @SofnLog("导出预警阈值列表")
    @ApiOperation(value="导出预警阈值列表")
    @GetMapping(value = "/exportWarningThreshold", produces = "application/octet-stream")
    public void exportWarningThreshold(HttpServletResponse response){
        //获取当前用户所属机构区划信息
        String loginUserOrganizationInfo = UserUtil.getLoginUserOrganizationInfo();
        SysOrganization sysOrganization = JsonUtils.json2obj(loginUserOrganizationInfo, SysOrganization.class);
        Map<String, Object> params = new HashMap();
        params.put("provinceId",sysOrganization.getRegionLastCode());

        List<WarningThresholdExcel> warningThresholdExcelList = warningThresholdService.getWTEByCondition(params);
        ExportUtil.createExcel(WarningThresholdExcel.class,warningThresholdExcelList,response,"外来入侵物种预警阈值.xlsx");
    }

    @SofnLog("入侵物种信息【预警图】(新)")
    @ApiOperation(value="(新)入侵物种信息【预警图】")
    @PostMapping("/getWarningMapInfoNew")
    public Result<MapViewData> getWarningMapInfoNew(@RequestParam(value = "index") @ApiParam(name="index", value="指标,固定值；如：发生面积-AREA,新发物种-NEWSPECIES,造成经济损失-ZCJJSS",
            allowableValues = "AREA,NEWSPECIES,ZCJJSS", required = true) String index,
                                             @RequestParam(value = "adLevel") @ApiParam(name="adLevel", value="行政级别,传值ad_county：国家 ad_province:省级；ad_city:市级；",allowableValues = "ad_county,ad_province,ad_city", required = true) String adLevel,
                                             @RequestParam(value = "adCode") @ApiParam(name="adCode", value="行政区域代码或行政区域名称；adLevel为国家时传100000；adLevel为省市级时候传相应省市的6位行政编码", required = true) String adCode,
                                             @RequestParam(value = "year",required = false) @ApiParam(name="year", value="年度查询条件,比如：2020;不传默认当前年") String year,
                                             @RequestParam(value = "date",required = false) @ApiParam(name="date", value="日期查询条件,比如：2020-09-01;不传则按当前年查询，与年同时有值则以日期查询") String date,
                                             @RequestParam(value = "speciesName",required = false) @ApiParam(name="speciesName", value="物种名称") String speciesName

    ){
        Map<String,String> conditions = new HashMap<>();
        if(StringUtils.isNotBlank(date)){
            conditions.put("createTime", String.format(date,"yyyy-MM-dd"));
        }else {
            conditions.put("belongYear", StringUtils.isEmpty(year)? String.valueOf(Calendar.getInstance().get(Calendar.YEAR)) : year);
        }
        conditions.put("speciesName", speciesName);

        return Result.ok(warningMonitorService.getMapViewData(index, adLevel, adCode, conditions));
    }

    @SofnLog("获取入侵物种信息有数据的日期")
    @ApiOperation(value="获取有入侵物种信息数据的日期列表")
    @PostMapping("/getMonitorTimes")
    public Result<List<String>> getMonitorTimes(@RequestParam(value = "index") @ApiParam(name="index", value="指标,固定值；如：发生面积-AREA,新发物种-NEWSPECIES,造成经济损失-ZCJJSS",
            allowableValues = "AREA,NEWSPECIES,ZCJJSS", required = true) String index,
            @RequestParam(value = "speciesName",required = false) @ApiParam(name="speciesName", value="物种名称") String speciesName){

        List<String> list = warningMonitorService.getMonitorTimeList(index, StringUtils.isEmpty(speciesName)?null:speciesName);
        return Result.ok(list);
    }
}
