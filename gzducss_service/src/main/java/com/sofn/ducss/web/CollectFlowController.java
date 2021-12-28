package com.sofn.ducss.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sofn.ducss.enums.RegionLevel;
import com.sofn.ducss.exception.SofnException;
import com.sofn.ducss.log.SofnLog;
import com.sofn.ducss.model.CollectFlow;
import com.sofn.ducss.model.StrawUtilizeSum;
import com.sofn.ducss.model.SysOrganization;
import com.sofn.ducss.model.basemodel.Result;
import com.sofn.ducss.service.CollectFlowService;
import com.sofn.ducss.util.JsonUtils;
import com.sofn.ducss.util.PageUtils;
import com.sofn.ducss.util.UserUtil;
import com.sofn.ducss.vo.CollectFlowWithTotalVo;
import com.sofn.ducss.vo.StrawProduceResVo;
import com.sofn.ducss.vo.UpdateStatusVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Slf4j
@Api(value = "进度管理", tags = "进度管理")
@RestController
@RequestMapping("/collectFlow")
public class CollectFlowController {

    @Autowired
    private CollectFlowService collectFlowService;

    @SofnLog("数据待上报列表")
    @ApiOperation(value="数据待上报列表")
    @GetMapping("/getCollectFlowLogList")
    public Result<PageUtils<CollectFlow>> getCollectFlowLogList(@RequestParam(value="pageNo") @ApiParam(value="起始行数") Integer pageNo
            , @RequestParam(value = "pageSize") @ApiParam(value="每页显示数") Integer pageSize
            , @RequestParam(value = "year") @ApiParam(value="年度") String year
            , HttpServletRequest request){
        //根据token获取登录用户Id
        String userId = UserUtil.getLoginUserId(request.getHeader("Authorization"));
        if(StringUtils.isBlank(userId)){
            throw new SofnException("当前登录用户异常");
        }

        String areaId = "";

        String userAreaByLogUser = UserUtil.getLoginUserOrganizationInfo();
        if (org.springframework.util.StringUtils.isEmpty(userAreaByLogUser)) {
            throw new SofnException("未获取到登录用户所属机构信息!");
        }
        SysOrganization sysOrganization = JsonUtils.json2obj(userAreaByLogUser, SysOrganization.class);
        String regioncode = sysOrganization.getRegioncode();
        List<String> areaList = JsonUtils.json2List(regioncode, String.class);
        //判断当前用户级别
        if(RegionLevel.CITY.getCode().equals(sysOrganization.getOrganizationLevel())){
            areaId = areaList.get(1);
        }else if(RegionLevel.PROVINCE.getCode().equals(sysOrganization.getOrganizationLevel())){
            areaId = areaList.get(0);
        }else {
            throw new SofnException("请检查用户机构级别是否为市级或者省级");
        }

        if(null == year){
            year = "";
        }
        PageUtils<CollectFlow> collectFlowLogByPage = collectFlowService.getCollectFlowByPage(pageNo,pageSize,year,areaId);

        return Result.ok(collectFlowLogByPage);
    }

    @SofnLog("市级待审核列表")
    @ApiOperation(value="市级待审核列表")
    @GetMapping("/getWaitForExamineDataForCity")
    public Result<CollectFlowWithTotalVo> getWaitForExamineDataForCity(@RequestParam(value = "status",required = false) @ApiParam(value="状态",required = false) String status
            , @RequestParam(value = "year",required = true) @ApiParam(value="年度",required = true) String year
            , HttpServletRequest request){
        //根据token获取登录用户Id
        String userId = UserUtil.getLoginUserId(request.getHeader("Authorization"));
        if(StringUtils.isBlank(userId)){
            throw new SofnException("当前登录用户异常");
        }

        String cityId = "";

        String userAreaByLogUser = UserUtil.getLoginUserOrganizationInfo();
        if (org.springframework.util.StringUtils.isEmpty(userAreaByLogUser)) {
            throw new SofnException("未获取到登录用户所属机构信息!");
        }
        SysOrganization sysOrganization = JsonUtils.json2obj(userAreaByLogUser, SysOrganization.class);
        String regioncode = sysOrganization.getRegioncode();
        List<String> areaList = JsonUtils.json2List(regioncode, String.class);
        //判断当前用户级别
        if(!RegionLevel.CITY.getCode().equals(sysOrganization.getOrganizationLevel())){
            throw new SofnException("当前用户不是市级机构用户，请检查账号是否正确");
        }
        cityId = areaList.get(1);
        if(null == year){
            year = "";
        }
        if(null == status){
            status = "";
        }
        CollectFlowWithTotalVo collectFlowList = collectFlowService.getWaitForExamineDataForCity(year,status,cityId);

        return Result.ok(collectFlowList);
    }

    @SofnLog("省级待审核列表")
    @ApiOperation(value="省级待审核列表")
    @GetMapping("/getWaitForExamineDataForProvince")
    public Result<CollectFlowWithTotalVo> getWaitForExamineDataForProvince(@RequestParam(value = "status",required = false) @ApiParam(value="状态",required = false) String status
            , @RequestParam(value = "year",required = true) @ApiParam(value="年度",required = true) String year
            , HttpServletRequest request){
        //根据token获取登录用户Id
        String userId = UserUtil.getLoginUserId(request.getHeader("Authorization"));
        if(StringUtils.isBlank(userId)){
            throw new SofnException("当前登录用户异常");
        }

        String provinceId = "";

        String userAreaByLogUser = UserUtil.getLoginUserOrganizationInfo();
        if (org.springframework.util.StringUtils.isEmpty(userAreaByLogUser)) {
            throw new SofnException("未获取到登录用户所属机构信息!");
        }
        SysOrganization sysOrganization = JsonUtils.json2obj(userAreaByLogUser, SysOrganization.class);
        String regioncode = sysOrganization.getRegioncode();
        List<String> areaList = JsonUtils.json2List(regioncode, String.class);
        //判断当前用户级别
        if(!RegionLevel.PROVINCE.getCode().equals(sysOrganization.getOrganizationLevel())){
            throw new SofnException("当前用户不是省级机构用户，请检查账号是否正确");
        }
        provinceId = areaList.get(0);
        if(null == year){
            year = "";
        }
        if(null == status){
            status = "";
        }
        CollectFlowWithTotalVo collectFlowList = collectFlowService.getWaitForExamineDataForProvince(year,status,provinceId);

        return Result.ok(collectFlowList);
    }

    @SofnLog("部级待审核列表")
    @ApiOperation(value="部级待审核列表")
    @GetMapping("/getWaitForExamineDataForMinistry")
    public Result<CollectFlowWithTotalVo> getWaitForExamineDataForMinistry(@RequestParam(value = "status",required = false) @ApiParam(value="状态",required = false) String status
            , @RequestParam(value = "year",required = true) @ApiParam(value="年度",required = true) String year
            , HttpServletRequest request){
        //根据token获取登录用户Id
        String userId = UserUtil.getLoginUserId(request.getHeader("Authorization"));
        if(StringUtils.isBlank(userId)){
            throw new SofnException("当前登录用户异常");
        }

        String userAreaByLogUser = UserUtil.getLoginUserOrganizationInfo();
        if (org.springframework.util.StringUtils.isEmpty(userAreaByLogUser)) {
            throw new SofnException("未获取到登录用户所属机构信息!");
        }
        SysOrganization sysOrganization = JsonUtils.json2obj(userAreaByLogUser, SysOrganization.class);
        String regioncode = sysOrganization.getRegionLastCode();
        //判断当前用户级别
        if(!RegionLevel.MINISTRY.getCode().equals(sysOrganization.getOrganizationLevel())){
            throw new SofnException("当前用户不是部级机构用户，请检查账号是否正确");
        }

        if(null == year){
            year = "";
        }
        if(null == status){
            status = "";
        }
        CollectFlowWithTotalVo collectFlowList = collectFlowService.getWaitForExamineDataForMinistry(year,status,regioncode);

        return Result.ok(collectFlowList);
    }

    @SofnLog("市级状态修改：上报，已读，退回，撤回，通过")
    @ApiOperation(value="市级状态修改：上报，已读，退回，撤回，通过")
    @GetMapping("/updateStatusForCity")
    public Result updateStatusForCity(UpdateStatusVo queryVo
            , HttpServletRequest request){
        //根据token获取登录用户Id
        String userId = UserUtil.getLoginUserId(request.getHeader("Authorization"));
        String userName = UserUtil.getLoginUserName();
        if(StringUtils.isBlank(userId)){
            throw new SofnException("当前登录用户异常");
        }

        String cityId = "";

        String userAreaByLogUser = UserUtil.getLoginUserOrganizationInfo();
        if (org.springframework.util.StringUtils.isEmpty(userAreaByLogUser)) {
            throw new SofnException("未获取到登录用户所属机构信息!");
        }
        SysOrganization sysOrganization = JsonUtils.json2obj(userAreaByLogUser, SysOrganization.class);
        String regioncode = sysOrganization.getRegioncode();
        List<String> areaList = JsonUtils.json2List(regioncode, String.class);
        //判断当前用户级别
        if(!RegionLevel.CITY.getCode().equals(sysOrganization.getOrganizationLevel())){
            throw new SofnException("当前用户不是市级机构用户，请检查账号是否正确");
        }
        cityId = areaList.get(1);
        //modify 2020.11.10 chlf
        queryVo.setProvinceId(areaList.get(0));
        queryVo.setCityId(cityId);

        String message = collectFlowService.updateStatusForCity(queryVo,cityId,RegionLevel.CITY.getCode(),userId,userName);

        return Result.ok(message);
    }

    @SofnLog("省级状态修改：上报，已读，退回，撤回，通过")
    @ApiOperation(value="省级状态修改：上报，已读，退回，撤回，通过")
    @GetMapping("/updateStatusForProvince")
    public Result updateStatusForProvince(UpdateStatusVo queryVo
            , HttpServletRequest request){
        //根据token获取登录用户Id
        String userId = UserUtil.getLoginUserId(request.getHeader("Authorization"));
        String userName = UserUtil.getLoginUserName();
        if(StringUtils.isBlank(userId)){
            throw new SofnException("当前登录用户异常");
        }

        String provinveId = "";

        String userAreaByLogUser = UserUtil.getLoginUserOrganizationInfo();
        if (org.springframework.util.StringUtils.isEmpty(userAreaByLogUser)) {
            throw new SofnException("未获取到登录用户所属机构信息!");
        }
        SysOrganization sysOrganization = JsonUtils.json2obj(userAreaByLogUser, SysOrganization.class);
        String regioncode = sysOrganization.getRegioncode();
        List<String> areaList = JsonUtils.json2List(regioncode, String.class);
        //判断当前用户级别
        if(!RegionLevel.PROVINCE.getCode().equals(sysOrganization.getOrganizationLevel())){
            throw new SofnException("当前用户不是省级机构用户，请检查账号是否正确");
        }
        provinveId = areaList.get(0);
        //modify 2020.11.10 chlf
        queryVo.setProvinceId(provinveId);
        queryVo.setCityId(areaList.get(0));

        String message = collectFlowService.updateStatusForCity(queryVo,provinveId,RegionLevel.PROVINCE.getCode(),userId,userName);

        return Result.ok(message);
    }

    @SofnLog("部级状态修改：上报，已读，退回，撤回，通过")
    @ApiOperation(value="部级状态修改：上报，已读，退回，撤回，通过")
    @GetMapping("/updateStatusForMinistry")
    public Result updateStatusForMinistry(UpdateStatusVo queryVo
            , HttpServletRequest request){
        //根据token获取登录用户Id
        String userId = UserUtil.getLoginUserId(request.getHeader("Authorization"));
        String userName = UserUtil.getLoginUserName();
        if(StringUtils.isBlank(userId)){
            throw new SofnException("当前登录用户异常");
        }


        String userAreaByLogUser = UserUtil.getLoginUserOrganizationInfo();
        if (org.springframework.util.StringUtils.isEmpty(userAreaByLogUser)) {
            throw new SofnException("未获取到登录用户所属机构信息!");
        }
        SysOrganization sysOrganization = JsonUtils.json2obj(userAreaByLogUser, SysOrganization.class);
        String regioncode = sysOrganization.getRegionLastCode();
        //判断当前用户级别
        if(!RegionLevel.MINISTRY.getCode().equals(sysOrganization.getOrganizationLevel())){
            throw new SofnException("当前用户不是部级机构用户，请检查账号是否正确");
        }
        //modify 2020.11.10 chlf
        queryVo.setProvinceId(regioncode);
        queryVo.setCityId(regioncode);

        String message = collectFlowService.updateStatusForCity(queryVo,regioncode,RegionLevel.MINISTRY.getCode(),userId,userName);

        return Result.ok(message);
    }

    @SofnLog("秸秆产生量")
    @ApiOperation(value="秸秆产生量")
    @GetMapping("/findStrawProduceData")
    public Result<List<StrawProduceResVo>> findStrawProduceData(@RequestParam(value = "areaId",required = true) @ApiParam(value="区划ID",required = true) String areaId
            , @RequestParam(value = "year",required = true) @ApiParam(value="年度",required = true) String year
            , HttpServletRequest request)
            throws JsonProcessingException, IllegalAccessException, InvocationTargetException {
        List<StrawProduceResVo> strawProduceResVos =  collectFlowService.findStrawProduceData(areaId, year);
        return Result.ok(strawProduceResVos);
    }

    @SofnLog("秸秆利用量")
    @ApiOperation(value="秸秆利用量")
    @GetMapping("/findStrawUtilzeData")
    public Result<List<StrawUtilizeSum>> findStrawUtilzeData(@RequestParam(value = "areaId",required = true) @ApiParam(value="区划ID",required = true) String areaId
            , @RequestParam(value = "year",required = true) @ApiParam(value="年度",required = true) String year
            , HttpServletRequest request)
            throws JsonProcessingException {
        List<StrawUtilizeSum> strawUtilizeSums =  collectFlowService.findStrawUtilzeData(areaId, year);
        return Result.ok(strawUtilizeSums);
    }


}




