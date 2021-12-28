package com.sofn.fyem.web;

import com.google.common.collect.Maps;
import com.sofn.common.exception.SofnException;
import com.sofn.common.log.SofnLog;
import com.sofn.common.model.Result;
import com.sofn.common.model.User;
import com.sofn.common.utils.UserUtil;
import com.sofn.common.web.BaseController;
import com.sofn.fyem.model.ReporManagement;
import com.sofn.fyem.service.ReporManagementService;
import com.sofn.fyem.sysapi.SysRegionApi;
import com.sofn.fyem.util.FyemAreaUtil;
import com.sofn.fyem.vo.FyemArea;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
@Api(value = "上报管理模块接口", tags = "上报管理模块接口")
@RestController
@RequestMapping("/reporManagement")
public class ReporManagementController extends BaseController {

    @Autowired
    private ReporManagementService reporManagementService;
    @Autowired
    private SysRegionApi sysRegionApi;

    @SofnLog("获取上报管理信息")
    @ApiOperation(value="获取上报管理信息")
    @GetMapping("/reporManagementList")
    public Result<List<ReporManagement>> reporManagementList(
            @ApiParam(name = "belongYear",value = "所属年度",required = true)@RequestParam(value = "belongYear", required = true)String belongYear,
            @ApiParam(name = "status",value = "待审状态",required = false)@RequestParam(value = "status", required = false)String status,
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
        params.put("organizationId", loginUser.getOrganizationId());
        params.put("status",status);
        List<ReporManagement> reporManagementList = reporManagementService.getReporManagementListByQuery(params);
        return Result.ok(reporManagementList);
    }

    @SofnLog("获取上报管理信息详情")
    @ApiOperation(value="获取上报管理信息详情")
    @GetMapping("/getReporManagement")
    public Result<ReporManagement> getReporManagement(
            @ApiParam(name = "id",value = "id",required = true)@RequestParam(value = "id")String id){

        ReporManagement reporManagement = reporManagementService.getReporManagementById(id);
        return Result.ok(reporManagement);
    }

    @SofnLog("上报上报管理信息")
    @ApiOperation(value="上报上报管理信息")
    @GetMapping("/submitReporManagement")
    public Result<String> submitReporManagement(
            @RequestParam(value = "belongYear", required = true, defaultValue = "0")  @ApiParam(name = "belongYear", value = "所属年度,（必传）", required = true) String belongYear,
            @RequestParam(value = "provinceId", required = true, defaultValue = "") @ApiParam(name = "provinceId", value = "省id,（必传）,省级填报员上报传入id,其它级别传入0", required = true) String provinceId,
            @RequestParam(value = "cityId", required = true, defaultValue = "")  @ApiParam(name = "cityId", value = "市id,（必传）,市级填报员上报传入id,其它级别传入0", required = true) String cityId,
            @RequestParam(value = "countyId", required = true, defaultValue = "")  @ApiParam(name = "countyId", value = "区县id,（必传）,县级填报员上报传入id,其它级别传入0", required = true) String countyId){

        Map<String, Object> params = Maps.newHashMap();
        params.put("belongYear",belongYear);
        params.put("provinceId",provinceId);
        params.put("cityId",cityId);
        params.put("countyId",countyId);
        if (!reporManagementService.able2Audit(belongYear)) {
            throw new SofnException("存在已上报未退回的数据");
        }
        String result = reporManagementService.updateStatus(params);
        if ("1".equals(result)){
            return Result.ok("上报成功!");
        } else {
            return Result.error(result);
        }
    }

    @SofnLog("删除上报管理信息")
    @ApiOperation(value="删除上报管理信息")
    @GetMapping("/removeReporManagement")
    public Result<String> removeReporManagement(
            @ApiParam(name = "id",value = "id",required = true)@RequestParam(value = "id")String id){

        reporManagementService.removeReporManagement(id);
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
