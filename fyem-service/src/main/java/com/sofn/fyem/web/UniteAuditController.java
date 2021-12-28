package com.sofn.fyem.web;

import com.sofn.common.exception.SofnException;
import com.sofn.common.log.SofnLog;
import com.sofn.common.model.Result;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.UserUtil;
import com.sofn.fyem.enums.RoleLevelEnum;
import com.sofn.fyem.service.CityAuditService;
import com.sofn.fyem.service.MinisterAuditService;
import com.sofn.fyem.service.ProvinceAuditService;
import com.sofn.fyem.service.ReporManagementService;
import com.sofn.fyem.sysapi.SysRegionApi;
import com.sofn.fyem.sysapi.bean.SysOrgVo;
import com.sofn.fyem.util.FyemAreaUtil;
import com.sofn.fyem.util.OrganizationUtil;
import com.sofn.fyem.util.RoleCodeUtil;
import com.sofn.fyem.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: DJH
 * @Date: 2020/5/7 14:50
 */
@Slf4j
@Api(value = "统一审核接口", tags = "统一审核接口")
@RestController
@RequestMapping(value = "/uniteAudit")
public class UniteAuditController {

    @Autowired
    private ReporManagementService reporManagementService;
    @Autowired
    private CityAuditService cityAuditService;
    @Autowired
    private ProvinceAuditService provinceAuditService;
    @Autowired
    private MinisterAuditService ministerAuditService;

    @Autowired
    private SysRegionApi sysRegionApi;

    @SofnLog("审核通过与驳回")
    @ApiOperation(value = "审核通过与驳回")
//    @RequiresPermissions(value = "")
    @GetMapping("/uniteAuditApproveAndReject")
    public Result uniteAuditApproveAndReject(@RequestParam(value = "belongYear", required = true)  @ApiParam(name = "belongYear", value = "所属年度,（必传）", required = true) String belongYear,
                                   @RequestParam(value = "provinceId", required = true, defaultValue = "") @ApiParam(name = "provinceId", value = "省id,（必传）,部级审核传入id,其它级别审核传入0,审核部本级、部直属时传入0", required = true) String provinceId,
                                   @RequestParam(value = "cityId", required = true, defaultValue = "")  @ApiParam(name = "cityId", value = "市id,（必传）,省级审核传入id,其它级别审核传入0,审核省本级、省直属时传入0", required = true) String cityId,
                                   @RequestParam(value = "countyId", required = true, defaultValue = "")  @ApiParam(name = "countyId", value = "区县id,（必传）,市级审核传入id,其它级别审核传入0,审核市本级、市直属时传入0", required = true) String countyId,
                                   @RequestParam(value = "organizationName", required = true, defaultValue = "")  @ApiParam(name = "organizationName", value = "组织名,（必传）", required = true) String organizationName,
                                   @RequestParam(value = "operateType", required = true, defaultValue = "")  @ApiParam(name = "operateType", value = "审核操作类型,（必传）,1：退回、2：通过,退回操作传入1,通过操作传入2", required = true) String operateType,
                                   @RequestParam(value = "remark", required = false, defaultValue = "")  @ApiParam(name = "remark", value = "驳回意见,（非必传）,退回时传入驳回意见", required = false) String remark){
        // 获取当前用户角色列表
        List<String> roleCodes = UserUtil.getLoginUserRoleCodeList();
        String roleCode = RoleCodeUtil.getLoginUserFyemRoleCode(roleCodes);
        // 获取角色级别
        List<FyemArea> fyemAreaList = FyemAreaUtil.getUserAreaIdByLogUser(sysRegionApi);
        FyemArea area = fyemAreaList.size() != 0 ? fyemAreaList.get(0): new FyemArea() ;
        String level = area.getLevel();

        if (!reporManagementService.able2Audit(belongYear)) {
            throw new SofnException("存在已上报未退回的数据");
        }

        List<String> organizationIdList = OrganizationUtil.getLogUserOrgAndChildren().stream()
                .map(SysOrgVo::getId).collect(Collectors.toList());

        Map<String, Object> miniParams = new HashMap<>();
        if (level.equals("ministry") && org.apache.commons.lang3.StringUtils.equalsAny(provinceId, "0", "")
                && org.apache.commons.lang3.StringUtils.equalsAny(cityId, "0", "")
                && org.apache.commons.lang3.StringUtils.equalsAny(countyId, "0", "")){
            miniParams.put("belongYear", belongYear);
            miniParams.put("roleCode", RoleLevelEnum.LEVEL_MASTER_ADD.getLevel());
            miniParams.put("organizationIdList", organizationIdList);
            miniParams.put("organizationName", organizationName);
            miniParams.put("remark", remark);
        } else {
            miniParams.put("belongYear", belongYear);
            miniParams.put("organizationIdList", organizationIdList);
            miniParams.put("provinceId", provinceId);
            miniParams.put("remark", remark);
        }

        Map<String, Object> provParams = new HashMap<>();
        if (level.equals("province") && !org.apache.commons.lang3.StringUtils.equalsAny(provinceId, "0", "")
                && org.apache.commons.lang3.StringUtils.equalsAny(cityId, "0", "")
                && org.apache.commons.lang3.StringUtils.equalsAny(countyId, "0", "")){
            provParams.put("belongYear", belongYear);
            provParams.put("provinceId",provinceId);
            provParams.put("roleCode", RoleLevelEnum.LEVEL_PROVINCE_ADD.getLevel());
            provParams.put("organizationIdList", organizationIdList);
            provParams.put("organizationName", organizationName);
            provParams.put("remark",remark);
        } else {
            provParams.put("belongYear", belongYear);
            provParams.put("organizationIdList", organizationIdList);
            provParams.put("cityId",cityId);
            provParams.put("remark",remark);
        }

        Map<String, Object> cityParams = new HashMap<>();
        if (level.equals("city") && org.apache.commons.lang3.StringUtils.equalsAny(provinceId, "0", "")
                && !org.apache.commons.lang3.StringUtils.equalsAny(cityId, "0", "")
                && org.apache.commons.lang3.StringUtils.equalsAny(countyId, "0", "")){
            cityParams.put("belongYear", belongYear);
            cityParams.put("cityId",cityId);
            cityParams.put("roleCode", RoleLevelEnum.LEVEL_CITY_ADD.getLevel());
            cityParams.put("organizationIdList", organizationIdList);
            cityParams.put("organizationName", organizationName);
            cityParams.put("remark",remark);
        } else {
            cityParams.put("belongYear", belongYear);
            cityParams.put("organizationIdList", organizationIdList);
            cityParams.put("countyId",countyId);
            cityParams.put("remark",remark);
        }

        String result = null;
        if (roleCode.equals(RoleLevelEnum.LEVEL_CITY.getLevel())){
            switch (operateType){
                case "1": result = cityAuditService.cityAuditReject(cityParams);break;
                case "2": result = cityAuditService.cityAuditApprove(cityParams);break;
            }
        } else if (roleCode.equals(RoleLevelEnum.LEVEL_PROVINCE.getLevel())){
            switch (operateType){
                case "1": result = provinceAuditService.provinceAuditReject(provParams);break;
                case "2": result = provinceAuditService.provinceAuditApprove(provParams);break;
            }
        } else if (roleCode.equals(RoleLevelEnum.LEVEL_MASTER.getLevel())){
            switch (operateType){
                case "1": result = ministerAuditService.ministerAuditReject(miniParams);break;
                case "2": result = ministerAuditService.ministerAuditApprove(miniParams);break;
            }
        } else {
            return Result.error("当前角色非法");
        }

        if ("1".equals(result)) {
            return Result.ok("操作成功！");
        } else {
            return Result.error("操作失败:"+result);
        }

    }

    @SofnLog("上报")
    @ApiOperation(value = "上报")
//    @RequiresPermissions(value = "")
    @GetMapping("/uniteAuditReport")
    public Result uniteAuditReport(@RequestParam(value = "belongYear", required = true, defaultValue = "")  @ApiParam(name = "belongYear", value = "所属年度,（必传）", required = true) String belongYear,
                                  @RequestParam(value = "provinceId", required = true, defaultValue = "") @ApiParam(name = "provinceId", value = "省id,（必传）,省级上报传入id,其它级别审核传入0", required = true) String provinceId,
                                  @RequestParam(value = "cityId", required = true, defaultValue = "")  @ApiParam(name = "cityId", value = "市id,（必传）,市级上报传入id,其它级别审核传入0", required = true) String cityId,
                                   @RequestParam(value = "countyId", required = true, defaultValue = "")  @ApiParam(name = "countyId", value = "区县id,（必传）,区县级上报传入id,其它级别审核传入0", required = true) String countyId){

        // 获取当前用户角色列表
        List<String> roleCodes = UserUtil.getLoginUserRoleCodeList();
        String roleCode = RoleCodeUtil.getLoginUserFyemRoleCode(roleCodes);
        // 判断角色级别

        Map<String, Object> countyAuditParams = new HashMap();
        countyAuditParams.put("belongYear", belongYear);
        countyAuditParams.put("countyId",countyId);

        Map<String, Object> cityAuditParams = new HashMap();
        cityAuditParams.put("belongYear", belongYear);
        cityAuditParams.put("cityId",cityId);

        Map<String, Object> provAuditParams = new HashMap();
        provAuditParams.put("belongYear", belongYear);
        provAuditParams.put("provinceId",provinceId);

        if (!reporManagementService.able2Audit(belongYear)) {
            throw new SofnException("存在已上报未退回的数据");
        }

        String result = "0";
        if (roleCode.equals(RoleLevelEnum.LEVEL_COUNTY.getLevel())){
            result = reporManagementService.updateStatus(countyAuditParams);
        }else if (roleCode.equals(RoleLevelEnum.LEVEL_CITY.getLevel())){
            result = cityAuditService.cityAuditReport(cityAuditParams);
        }else if (roleCode.equals(RoleLevelEnum.LEVEL_PROVINCE.getLevel())){
            result = provinceAuditService.provinceAuditReport(provAuditParams);
        }else {
            return Result.error("当前角色非法");
        }

        if ("1".equals(result)){
            return Result.ok("上报成功！");
        } else {
            return Result.error("上报失败:"+result);
        }
    }

    @SofnLog("查看详情")
    @ApiOperation(value = "查看详情")
//    @RequiresPermissions(value = "")
    @GetMapping("/uniteAuditView")
    public Result uniteAuditView(@RequestParam(value = "belongYear", required = true, defaultValue = "")  @ApiParam(name = "belongYear", value = "所属年度,（必传）", required = true) String belongYear,
                                   @RequestParam(value = "provinceId", required = true, defaultValue = "") @ApiParam(name = "provinceId", value = "省id,（必传）,部级查看详情传入id,其它级别传入0", required = true) String provinceId,
                                   @RequestParam(value = "cityId", required = true, defaultValue = "")  @ApiParam(name = "cityId", value = "市id,（必传）,省级查看详情传入id,其它级别传入0", required = true) String cityId,
                                   @RequestParam(value = "countyId", required = true, defaultValue = "")  @ApiParam(name = "countyId", value = "区县id,（必传）,市级查看详情传入id,其它级别传入0", required = true) String countyId,
                                 @RequestParam(value = "organizationName", required = true, defaultValue = "")  @ApiParam(name = "organizationName", value = "组织名", required = true) String organizationName,
                                 @RequestParam(value = "viewType", required = true, defaultValue = "")  @ApiParam(name = "viewType", value = "查看操作级别,（必传）,1：市级、2：省级、3：部级,进行不同级别查看需要传入不同查看级别", required = true) String viewType){

        // 获取当前用户角色列表
        List<String> roleCodes = UserUtil.getLoginUserRoleCodeList();
        String roleCode = RoleCodeUtil.getLoginUserFyemRoleCode(roleCodes);

        Map<String, Object> cityViewParams = new HashMap();
        Map<String, Object> provViewParams = new HashMap();
        Map<String, Object> ministerViewParams = new HashMap();

        Map<String, Object> result = new HashMap<>();
        if (roleCode.equals(RoleLevelEnum.LEVEL_CITY.getLevel())){
            if ("0".equals(provinceId) && !"0".equals(cityId) && "0".equals(countyId)){
                // 查看市本级、直属具体数据
                cityViewParams.put("belongYear", belongYear);
                cityViewParams.put("cityId", cityId);
                cityViewParams.put("roleCode", RoleLevelEnum.LEVEL_CITY_ADD.getLevel());
                cityViewParams.put("organizationName", organizationName);
                result = cityAuditService.view(cityViewParams);
            }else  {
                // 查看市下各个县 具体数据
                cityViewParams.put("belongYear", belongYear);
                cityViewParams.put("countyId", countyId);
                result = cityAuditService.view(cityViewParams);
            }
        }else if (roleCode.equals(RoleLevelEnum.LEVEL_PROVINCE.getLevel())){
            if ("1".equals(viewType)){
                if ("0".equals(provinceId) && !"0".equals(cityId) && "0".equals(countyId)){
                    // 查看市本级、直属具体数据
                    provViewParams.put("belongYear", belongYear);
                    provViewParams.put("cityId", cityId);
                    provViewParams.put("roleCode", RoleLevelEnum.LEVEL_CITY_ADD.getLevel());
                    provViewParams.put("organizationName", organizationName);
                    result = cityAuditService.view(provViewParams);
                }else  {
                    // 查看市下各个县 具体数据
                    provViewParams.put("belongYear", belongYear);
                    provViewParams.put("countyId", countyId);
                    result = cityAuditService.view(provViewParams);
                }
            }else if ("2".equals(viewType)){
                if (!"0".equals(provinceId) && "0".equals(cityId) && "0".equals(countyId)){
                    // 查看省本级、直属具体数据
                    provViewParams.put("belongYear", belongYear);
                    provViewParams.put("provinceId", provinceId);
                    provViewParams.put("roleCode", RoleLevelEnum.LEVEL_PROVINCE_ADD.getLevel());
                    provViewParams.put("organizationName", organizationName);
                    result = cityAuditService.view(provViewParams);
                }else {
                    // 查看省下各个市
                    provViewParams.put("belongYear", belongYear);
                    provViewParams.put("cityId", cityId);
                    List<CityAuditVo> cityAuditVos = provinceAuditService.view(provViewParams);
                    result.put("cityAuditVos", cityAuditVos);
                }

            }
        }else if (roleCode.equals(RoleLevelEnum.LEVEL_MASTER.getLevel())){
            if ("1".equals(viewType)){
                if ("0".equals(provinceId) && !"0".equals(cityId) && "0".equals(countyId)){
                    // 查看市本级、直属具体数据
                    ministerViewParams.put("belongYear", belongYear);
                    ministerViewParams.put("cityId", cityId);
                    ministerViewParams.put("roleCode", RoleLevelEnum.LEVEL_CITY_ADD.getLevel());
                    ministerViewParams.put("organizationName", organizationName);
                    result = cityAuditService.view(ministerViewParams);
                }else {
                    // 查看市下各个县 具体数据
                    ministerViewParams.put("belongYear", belongYear);
                    ministerViewParams.put("countyId", countyId);
                    result = cityAuditService.view(ministerViewParams);
                }
            }else if ("2".equals(viewType)){
                if (!"0".equals(provinceId) && "0".equals(cityId) && "0".equals(countyId)){
                    // 查看省本级、直属具体数据
                    ministerViewParams.put("belongYear", belongYear);
                    ministerViewParams.put("provinceId", provinceId);
                    ministerViewParams.put("roleCode", RoleLevelEnum.LEVEL_PROVINCE_ADD.getLevel());
                    ministerViewParams.put("organizationName", organizationName);
                    result = cityAuditService.view(ministerViewParams);
                }else {
                    // 查看省下各个市
                    ministerViewParams.put("belongYear", belongYear);
                    ministerViewParams.put("cityId", cityId);
                    List<CityAuditVo> cityAuditVos = provinceAuditService.view(ministerViewParams);
                    result.put("cityAuditVos", cityAuditVos);
                }

            }else if ("3".equals(viewType)){
                if ("0".equals(provinceId) && "0".equals(cityId) && "0".equals(countyId)){
                    // 查看部本级、直属具体数据
                    ministerViewParams.put("belongYear", belongYear);
                    ministerViewParams.put("roleCode", RoleLevelEnum.LEVEL_MASTER_ADD.getLevel());
                    ministerViewParams.put("organizationName", organizationName);
                    result = cityAuditService.view(ministerViewParams);
                }else {
                    // 查看部下各个省
                    ministerViewParams.put("belongYear", belongYear);
                    ministerViewParams.put("provinceId", provinceId);
                    List<ProvinceAuditVo> provinceAuditVoList = ministerAuditService.view(ministerViewParams);
                    result.put("provinceAudits", provinceAuditVoList);
                }
            }
        }else {
            return Result.error("当前角色非法");
        }

        return Result.ok(result);
    }

    @SofnLog("展示待审信息")
    @ApiOperation(value = "展示待审信息")
//    @RequiresPermissions(value = "")
    @GetMapping("/uniteAuditViewReport")
    public Result uniteAuditViewReport(@RequestParam(value = "belongYear", required = true, defaultValue = "")  @ApiParam(name = "belongYear", value = "所属年度,（必传）,部级展示只传入年份", required = true) String belongYear,
                                       @RequestParam(value = "orgName", required = false, defaultValue = "") @ApiParam(name = "orgName", value = "上报单位,（非必传）", required = false) String orgName,
                                       @RequestParam(value = "pageNo", required = true, defaultValue = "0") @ApiParam(name = "pageNo", value = "起始位置,（必传）", required = true) int pageNo,
                                       @RequestParam(value = "pageSize", required = true, defaultValue = "0") @ApiParam(name = "pageSize", value = "分页大小,（必传）", required = true) int pageSize,
                                       HttpServletRequest request){
        //根据token获取登录用户Id
        String userId = UserUtil.getLoginUserId(request.getHeader("Authorization"));
        if(StringUtils.isBlank(userId)){
            throw new SofnException("当前登录用户异常");
        }

        List<FyemArea> fyemAreaList = FyemAreaUtil.getUserAreaIdByLogUser(sysRegionApi);
        FyemArea area = fyemAreaList.size() != 0 ? fyemAreaList.get(0): new FyemArea() ;
        String provinceId = area.getProvinceId();
        String cityId = area.getCityId();

        // 获取当前用户角色列表
        List<String> roleCodes = UserUtil.getLoginUserRoleCodeList();
        String roleCode = RoleCodeUtil.getLoginUserFyemRoleCode(roleCodes);

        List<String> organizationIdList = OrganizationUtil.getLogUserOrgAndChildren().stream()
                .map(SysOrgVo::getId).collect(Collectors.toList());

//        Map<String, Object> countyViewParams = new HashMap<>();
//        countyViewParams.put("belongYear",belongYear);
//        countyViewParams.put("createUserId",userId);
//        countyViewParams.put("organizationIdList", organizationIdList);

        Map<String, Object> cityViewParams = new HashMap<>();
        cityViewParams.put("belongYear", belongYear);
        cityViewParams.put("cityId",cityId);
        cityViewParams.put("orgName",orgName);
        cityViewParams.put("organizationIdList", organizationIdList);

        Map<String, Object> provViewParams = new HashMap<>();
        provViewParams.put("belongYear", belongYear);
        provViewParams.put("provinceId",provinceId);
        provViewParams.put("orgName",orgName);
        provViewParams.put("organizationIdList", organizationIdList);

        Map<String, Object> ministerViewParams = new HashMap<>();
        ministerViewParams.put("belongYear", belongYear);
        ministerViewParams.put("orgName", orgName);
        ministerViewParams.put("organizationIdList", organizationIdList);

        if (roleCode.equals(RoleLevelEnum.LEVEL_CITY.getLevel())){
            PageUtils<CityAuditVo> cityAuditVoList = cityAuditService.getCityAuditsListByPage(cityViewParams, pageNo, pageSize);
            return Result.ok(cityAuditVoList);
        }else if (roleCode.equals(RoleLevelEnum.LEVEL_PROVINCE.getLevel())){
            PageUtils<ProvinceAuditVo> provinceAuditVoList = provinceAuditService.getProvinceAuditsListByPage(provViewParams, pageNo, pageSize);
            return Result.ok(provinceAuditVoList);
        }else if (roleCode.equals(RoleLevelEnum.LEVEL_MASTER.getLevel())){
            PageUtils<MinisterAuditVo> ministerAuditVoList = ministerAuditService.getMinisterAuditsListByPage(ministerViewParams, pageNo, pageSize);
            return Result.ok(ministerAuditVoList);
        }

        return Result.error("当前角色非法");
    }

    @SofnLog("展示上报管理信息")
    @ApiOperation(value = "展示上报管理信息")
//    @RequiresPermissions(value = "")
    @GetMapping("/uniteAuditViewReporManagement")
    public Result uniteAuditViewReporManagement(@RequestParam(value = "belongYear", required = false, defaultValue = "")  @ApiParam(name = "belongYear", value = "所属年度,（非必传）", required = false) String belongYear,
                                       @RequestParam(value = "pageNo", required = true, defaultValue = "0") @ApiParam(name = "pageNo", value = "起始位置,（必传）", required = true) int pageNo,
                                       @RequestParam(value = "pageSize", required = true, defaultValue = "0") @ApiParam(name = "pageSize", value = "分页大小,（必传）", required = true) int pageSize,
                                       HttpServletRequest request){
        //根据token获取登录用户Id
        String userId = UserUtil.getLoginUserId(request.getHeader("Authorization"));
        if(StringUtils.isBlank(userId)){
            throw new SofnException("当前登录用户异常");
        }

        List<FyemArea> fyemAreaList = FyemAreaUtil.getUserAreaIdByLogUser(sysRegionApi);
        FyemArea area = fyemAreaList.size() != 0 ? fyemAreaList.get(0): new FyemArea();
        String provinceId = area.getProvinceId();
        String cityId = area.getCityId();

        // 获取当前用户角色列表
        List<String> roleCodes = UserUtil.getLoginUserRoleCodeList();
        String roleCode = RoleCodeUtil.getLoginUserFyemRoleCode(roleCodes);

        Map<String, Object> cityViewParams = new HashMap<>();
        cityViewParams.put("belongYear", belongYear);
        cityViewParams.put("cityId",cityId);

        Map<String, Object> provViewParams = new HashMap<>();
        provViewParams.put("belongYear", belongYear);
        provViewParams.put("provinceId",provinceId);

        Map<String, Object> ministerViewParams = new HashMap<>();
        ministerViewParams.put("belongYear", belongYear);

        if (roleCode.equals(RoleLevelEnum.LEVEL_CITY.getLevel())){
            PageUtils<CityReportManagementVo> cityReportManagementVoList =
                    cityAuditService.reportManagementByPage(cityViewParams, pageNo, pageSize);
            return Result.ok(cityReportManagementVoList);
        }else if (roleCode.equals(RoleLevelEnum.LEVEL_PROVINCE.getLevel())){
            PageUtils<ProvinceReportManagementVo> provinceReportManagementVoList =
                    provinceAuditService.reportManagementByPage(provViewParams, pageNo, pageSize);
            return Result.ok(provinceReportManagementVoList);
        }

        return Result.error("当前角色非法");
    }

    @SofnLog("查看驳回意见")
    @ApiOperation(value = "查看驳回意见")
//    @RequiresPermissions(value = "")
    @GetMapping("/uniteAuditViewRemark")
    public Result uniteAuditViewRemark(@RequestParam(value = "belongYear", required = true, defaultValue = "")  @ApiParam(name = "belongYear", value = "所属年度,（必传）", required = true) String belongYear,
                                 @RequestParam(value = "provinceId", required = true, defaultValue = "") @ApiParam(name = "provinceId", value = "省id,（必传）,省级查看驳回意见传入id,其它级别传入0", required = true) String provinceId,
                                 @RequestParam(value = "cityId", required = true, defaultValue = "")  @ApiParam(name = "cityId", value = "市id,（必传）,市级查看驳回意见传入id,其它级别传入0", required = true) String cityId,
                                 @RequestParam(value = "countyId", required = true, defaultValue = "")  @ApiParam(name = "countyId", value = "区县id,（必传）,区县级查看驳回意见传入id,其它级别传入0", required = true) String countyId){

        // 获取当前用户角色列表
        List<String> roleCodes = UserUtil.getLoginUserRoleCodeList();
        String roleCode = RoleCodeUtil.getLoginUserFyemRoleCode(roleCodes);
        // 判断角色级别

        Map<String, Object> countyViewParams = new HashMap();
        countyViewParams.put("belongYear", belongYear);
        countyViewParams.put("countyId",countyId);

        Map<String, Object> cityViewParams = new HashMap();
        cityViewParams.put("belongYear", belongYear);
        cityViewParams.put("cityId",cityId);

        Map<String, Object> provViewParams = new HashMap();
        provViewParams.put("belongYear", belongYear);
        provViewParams.put("provinceId",provinceId);

        if (roleCode.equals(RoleLevelEnum.LEVEL_COUNTY.getLevel())){
            CountyRemarkVo countyRemarkVo = cityAuditService.getRemark(countyViewParams);
            return Result.ok(countyRemarkVo);
        }else if (roleCode.equals(RoleLevelEnum.LEVEL_CITY.getLevel())){
            CityRemarkVo cityRemarkVo = provinceAuditService.getRemark(cityViewParams);
            return Result.ok(cityRemarkVo);
        }else if (roleCode.equals(RoleLevelEnum.LEVEL_PROVINCE.getLevel())){
            MinisterRemarkVo ministerRemarkVo = ministerAuditService.getRemark(provViewParams);
            return Result.ok(ministerRemarkVo);
        }
        return Result.error("当前角色非法");
    }

//    @SofnLog("填写驳回意见")
//    @ApiOperation(value = "填写驳回意见")
//    @RequiresPermissions(value = "")
//    @PutMapping("/uniteAuditEditRemark")
    @Deprecated
    public Result uniteAuditEditRemark(@Validated @RequestBody @ApiParam(name = "remarkVo", value = "驳回意见对象,（必传）,传入id和驳回意见", required = true) ProvinceRemarkVo remarkVo){

        // 获取当前用户角色列表
        List<String> roleCodes = UserUtil.getLoginUserRoleCodeList();
        String roleCode = RoleCodeUtil.getLoginUserFyemRoleCode(roleCodes);
        // 判断角色级别

        MinisterRemarkVo ministerRemarkVo = new MinisterRemarkVo();
        ProvinceRemarkVo provinceRemarkVo = new ProvinceRemarkVo();
        CityRemarkVo cityRemarkVo = new CityRemarkVo();
        BeanUtils.copyProperties(remarkVo, ministerRemarkVo);
        BeanUtils.copyProperties(remarkVo, provinceRemarkVo);
        BeanUtils.copyProperties(remarkVo, cityRemarkVo);

        String result = "0";
        if (roleCode.equals(RoleLevelEnum.LEVEL_CITY.getLevel())){
            result = cityAuditService.editRemark(cityRemarkVo);
        }else if (roleCode.equals(RoleLevelEnum.LEVEL_PROVINCE.getLevel())){
            result = provinceAuditService.editRemark(provinceRemarkVo);
        }else if (roleCode.equals(RoleLevelEnum.LEVEL_MASTER.getLevel())){
            result = ministerAuditService.editRemark(ministerRemarkVo);
        }

        if ("1".equals(result)){
            return Result.ok("填写成功！");
        }else {
            return Result.error(result);
        }
    }
}
