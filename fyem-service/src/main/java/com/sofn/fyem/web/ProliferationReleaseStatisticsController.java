package com.sofn.fyem.web;

import com.google.common.collect.Maps;
import com.sofn.common.exception.SofnException;
import com.sofn.common.log.SofnLog;
import com.sofn.common.model.Result;
import com.sofn.common.model.User;
import com.sofn.common.utils.JsonUtils;
import com.sofn.common.utils.UserUtil;
import com.sofn.common.web.BaseController;
import com.sofn.fyem.model.ProliferationReleaseStatistics;
import com.sofn.fyem.service.ProliferationReleaseStatisticsService;
import com.sofn.fyem.sysapi.SysRegionApi;
import com.sofn.fyem.sysapi.bean.SysOrganization;
import com.sofn.fyem.util.FyemAreaUtil;
import com.sofn.fyem.vo.FyemArea;
import com.sofn.fyem.vo.ReleaseStatisticsForm;
import com.sofn.fyem.vo.ReleaseStatisticsSpeciesVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
@Api(value = "中央财政农业资源及生态保护补助项目增殖放流情况统计表模块接口", tags = "中央财政农业资源及生态保护补助项目增殖放流情况统计表模块接口")
@RestController
@RequestMapping("/releaseStatistics")
public class ProliferationReleaseStatisticsController extends BaseController {


    @Autowired
    private ProliferationReleaseStatisticsService proliferationReleaseStatisticsService;

    @Autowired
    private SysRegionApi sysRegionApi;

    @SofnLog("获取增值流放信息")
    @ApiOperation(value="获取增值流放信息")
    @GetMapping("/releaseStatisticsForm")
    public Result<List<ReleaseStatisticsForm>> releaseStatisticsForm(
            @ApiParam(name = "belongYear",value = "所属年度",required = true)@RequestParam(value = "belongYear")String belongYear,
            HttpServletRequest request){
        //根据token获取登录用户Id
        String userId = UserUtil.getLoginUserId(request.getHeader("Authorization"));
        if(StringUtils.isBlank(userId)){
            throw new SofnException("当前登录用户异常");
        }

        Map<String, Object> params = Maps.newHashMap();
        params.put("belongYear",belongYear);
        //params.put("createUserId",userId);
        User loginUser = UserUtil.getLoginUser();
        if(loginUser == null || loginUser.getOrganizationId().isEmpty()){
            throw new SofnException("获取信息失败，用户信息异常！组织id为空");
        }
        params.put("organizationId",loginUser.getOrganizationId());
        List<ReleaseStatisticsForm> releaseStatisticsFormList = proliferationReleaseStatisticsService.getReleaseStatisticsForm(params);
        return Result.ok(releaseStatisticsFormList);
    }

    @SofnLog("上报上报管理信息")
    @ApiOperation(value="上报物种监测信息App")
    @GetMapping("/submitReleaseStatisticsSpecies")
    public Result<String> submitReporManagement(
            @ApiParam(name = "belongYear",value = "belongYear",required = true)@RequestParam(value = "belongYear")String belongYear,
            @ApiParam(name = "status",value = "状态",required = true)@RequestParam(value = "status")String status){
        Map<String, Object> params = Maps.newHashMap();
        params.put("belongYear",belongYear);
        params.put("status",status);
        int i = proliferationReleaseStatisticsService.updateStatus(params);
        return Result.ok("当前物种监测信息已提交!");

    }

    @SofnLog("获取增值流程信息详情")
    @ApiOperation(value="获取增值流程信息详情")
    @GetMapping("/getReleaseStatisticsSpeciesVo")
    public Result<ReleaseStatisticsSpeciesVo> getReleaseStatisticsSpeciesVo(
            @ApiParam(name = "belongYear",value = "所属年度",required = true)@RequestParam(value = "belongYear")String belongYear){

        Map<String, Object> params = Maps.newHashMap();
//        String loginUserId = UserUtil.getLoginUserId();
        params.put("belongYear",belongYear);
//        params.put("createUserId",loginUserId);
        params.put("organizationId", UserUtil.getLoginUserOrganizationId());
        ReleaseStatisticsSpeciesVo releaseStatisticsSpeciesVo = proliferationReleaseStatisticsService.getReleaseStatisticsSpeciesVo(params);
        return Result.ok(releaseStatisticsSpeciesVo);
    }

    @SofnLog("新增增值流放信息")
    @ApiOperation(value="新增增值流放信息")
    @PostMapping("/addReleaseStatisticsSpeciesVo")
    public Result<String> addReleaseStatisticsSpeciesVo(
            @Validated @RequestBody @ApiParam(name = "增值流放信息对象", value = "传入json格式", required = true)ReleaseStatisticsSpeciesVo releaseStatisticsSpeciesVo,
            HttpServletRequest request){

        List<String> roleCodeList = UserUtil.getLoginUserRoleCodeList();
        //获取组织相关信息
        String loginUserOrganizationInfo = UserUtil.getLoginUserOrganizationInfo();
        SysOrganization sysOrganization = JsonUtils.json2obj(loginUserOrganizationInfo, SysOrganization.class);
        String organizationLevel;
        List<String> regionCode ;
        //判断当前机构是否为代理机构
        if("N".equals(sysOrganization.getThirdOrg())){
            Result<List<SysOrganization>> fyem = sysRegionApi.getOrgListByAgentOrgId("fyem", sysOrganization.getId(),"");
            SysOrganization fyemOrg = fyem.getData().get(0);
            organizationLevel = fyemOrg.getOrganizationLevel();
            regionCode = JsonUtils.json2List(fyemOrg.getRegioncode(), String.class);
        }else{
            //行政机构
            organizationLevel = sysOrganization.getOrganizationLevel();
            regionCode = JsonUtils.json2List(sysOrganization.getRegioncode(), String.class);
        }
        if(roleCodeList.contains("fyem_master_add")){
            releaseStatisticsSpeciesVo.setRoleCode("fyem_master_add");
        }
        //省级
        if("province".equals(organizationLevel)){
            releaseStatisticsSpeciesVo.setProvinceId(regionCode.get(0));
            releaseStatisticsSpeciesVo.setCityId("");
            releaseStatisticsSpeciesVo.setCountyId("");
            releaseStatisticsSpeciesVo.setRoleCode("fyem_prov_add");
        }
        //市级
        if("city".equals(organizationLevel)){
            releaseStatisticsSpeciesVo.setProvinceId(regionCode.get(0));
            releaseStatisticsSpeciesVo.setCityId(regionCode.get(1));
            releaseStatisticsSpeciesVo.setCountyId("");
            releaseStatisticsSpeciesVo.setRoleCode("fyem_city_add");
        }
        //区县级
        if("county".equals(organizationLevel)){
            releaseStatisticsSpeciesVo.setProvinceId(regionCode.get(0));
            releaseStatisticsSpeciesVo.setCityId(regionCode.get(1));
            releaseStatisticsSpeciesVo.setCountyId(regionCode.get(2));
            releaseStatisticsSpeciesVo.setRoleCode("fyem_county");
        }

        //获取机构区划codeList
        proliferationReleaseStatisticsService.addReleaseStatisticsSpeciesVo(releaseStatisticsSpeciesVo);
        return Result.ok("新增增值流放信息成功");
    }

    @SofnLog("编辑增值放流信息")
    @ApiOperation(value="编辑增值放流信息")
    @PostMapping("/updateReleaseStatisticsSpeciesVo")
    public Result<String> updateReleaseStatisticsSpeciesVo(
            @Validated @RequestBody @ApiParam(name = "增值流放信息对象", value = "传入json格式", required = true)ReleaseStatisticsSpeciesVo releaseStatisticsSpeciesVo,
            HttpServletRequest request){
        List<ProliferationReleaseStatistics> list = proliferationReleaseStatisticsService.getProliferationReleaseStatisticsList(new HashMap<String, Object>() {
            {
                this.put("belongYear", releaseStatisticsSpeciesVo.getBelongYear());
                this.put("provinceId", releaseStatisticsSpeciesVo.getProvinceId());
                this.put("cityId", releaseStatisticsSpeciesVo.getCityId());
                this.put("countyId", releaseStatisticsSpeciesVo.getCountyId());
            }
        });
        ProliferationReleaseStatistics proliferationReleaseStatistics = list.stream().findFirst().orElseThrow(() -> new SofnException("不存在的数据"));
        String status = proliferationReleaseStatistics.getStatus();
        if("0".equals(status) || "2".equals(status) || "5".equals(status) || "8".equals(status)){
            proliferationReleaseStatisticsService.updateReleaseStatisticsSpeciesVo(releaseStatisticsSpeciesVo);
            return Result.ok("编辑增值放流信息成功!");
        }
        return Result.error("当前状态无法进行编辑!");
    }

    @SofnLog("删除增值流放信息")
    @ApiOperation(value="删除增值流放信息")
    @GetMapping("/removeReleaseStatisticsSpecies")
    public Result<String> removeReleaseStatisticsSpecies(
            @ApiParam(name = "belongYear",value = "所属年度",required = true)@RequestParam(value = "belongYear")String belongYear){

        String userId = UserUtil.getLoginUserId();
        if(StringUtils.isBlank(userId)){
            throw new SofnException("当前登录用户异常");
        }
        Map<String,Object> params = Maps.newHashMap();
        params.put("belongYear",belongYear);
        params.put("createUserId",userId);
        proliferationReleaseStatisticsService.removeReleaseStatisticsSpeciesVo(params);
        return Result.ok("当前增值流放信息已删除!");

    }

    private Map<String,Object> getLoginUserArea(Map<String,Object> params){
        //获取当前登录用户所属省市县id
        List<FyemArea> fyemAreaList = FyemAreaUtil.getUserAreaIdByLogUser(sysRegionApi);
        FyemArea area = fyemAreaList.size() != 0 ? fyemAreaList.get(0): new FyemArea() ;
        params.put("provinceId",area.getProvinceId());
        params.put("cityId",area.getCityId());
        params.put("countyId",area.getCountyId());
        return params;
    }
}
