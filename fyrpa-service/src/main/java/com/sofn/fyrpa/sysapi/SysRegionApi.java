package com.sofn.fyrpa.sysapi;

import com.sofn.common.config.FeignConfiguration;
import com.sofn.common.model.Result;
import com.sofn.fyrpa.sysapi.bean.*;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 使用Feign 调用sys服务
 6 *
 * @author chenlf
 * @date 2019/9/16 14:2
 */
@FeignClient(
        value = "sys-service",
        configuration = FeignConfiguration.class
)
public interface SysRegionApi {

    /**
     * 获取区县信息
     * @return  Result
     */
    @GetMapping(value = "/sysRegion/getSysRegionOne/{areaId}")
    Result<SysRegionForm> getSysRegionOne(@ApiParam(value = "行政区划ID", required = true) @PathVariable(value = "areaId") String areaId);

    /**
     * 根据areaId获取
     * @return  Result
     */
    @GetMapping("/sysRegion/getParentNodeByRegionCode/{regionCode}")
    Result<List<SysRegionTreeVo>> getParentNode(@ApiParam(value = "行政区划ID", required = true) @PathVariable(value = "regionCode") String regionCode);

    /**
     * 根据userId获取用户角色
     * @return  Result
     */
    @PostMapping("/user/getUserOne")
    Result<SysUserForm> getOne(@ApiParam(name = "id", value = "用户ID", required = true)
                               @RequestParam(required = true, value = "id") String id);

    /**
     * 获取全国各省信息(只返回一层)
     * @return  Result
     */
    @GetMapping(value = "/sysRegion/getSysRegionTreeById/{areaId}")
    Result<List<SysRegionTreeVo>> getSysRegionTreeById(@ApiParam(value = "父级行政区划ID", required = true) @PathVariable(value = "areaId") String areaId);


    /**
     *
     * 按条件获取全国省市区县信息（返回此parentId下所有层级信息）
     * @return  Result
     */
    @GetMapping("/sysRegion/getSysRegionTree")
    Result<List<SysRegionTreeVo>> getSysRegionTree(
            @ApiParam(value = "行政区划名称") @RequestParam(required = false, value = "keyword") String keyword,
            @ApiParam(value = "父节点ID") @RequestParam(required = false, value = "parentId") String parentId,
            @ApiParam(value = "行政区划代码") @RequestParam(required = false, value = "regionCode") String regionCode);

    /**
     * 根据机构级别查询机构信息和代理机构信息
     * @param orgLevel
     * @param appId
     * @param proxyId
     * @param orgId
     * @param regionId
     * @param agentCode
     * @return
     */

    /**
     * 根据组织机构ID获取机构详细信息
     * @param id
     * @return
     */
    @PostMapping("/sysOrganization/getOrgInfoById")
    Result<SysOrganizationForm> getOrgInfoById(
            @ApiParam(value = "机构ID", required = true)
            @NotNull @RequestParam(required = true, value = "id") String id);


    /**
     * 根据行政机构ID查询系统下分配的代理机构
     * @param subsystemId
     * @param orgId
     * @param agentCode
     * @return
     */
    @GetMapping("/sysOrgAgent/getOrgAgentByOrgId")
    Result<List<SysOrganization>> getOrgAgentByOrgId(
            @RequestParam(required = false, value = "subsystemId")
            @ApiParam(name = "subsystemId", value = "系统ID", required = true) String subsystemId,
            @RequestParam(required = false, value = "orgId") @ApiParam(name = "orgId", value = "行政机构ID", required = true) String orgId,
            @ApiParam(name = "agentCode", value = "代理机构标识") @RequestParam(required = false, value = "agentCode") String agentCode);


    /**
     * 根据代理机构ID查询代理的行政机构
     * @param subsystemId
     * @param agentOrgId
     * @param agentCode
     * @return
     */
    @GetMapping("/sysOrgAgent/getOrgListByAgentOrgId")
    Result<List<SysOrganization>> getOrgListByAgentOrgId(
            @RequestParam(required = true, value = "subsystemId")
            @ApiParam(name = "subsystemId", value = "系统ID", required = true) String subsystemId,
            @RequestParam(required = true, value = "agentOrgId") @ApiParam(name = "agentOrgId", value = "代理机构ID", required = true) String agentOrgId,
            @RequestParam(required = false, value = "agentCode") @ApiParam(name = "agentCode", value = "代理机构标识") String agentCode);


    /**
     * 根据系统APPID获取有代理机构的行政机构的列表
     */
    @GetMapping("/sysOrgAgent/getOrgListHasAgent")
    Result<List<SysOrganizationForm>> getOrgListHasAgent(@ApiParam(name = "appId", required = true) @RequestParam(value = "appId") String appId);

   /*
   * 根据行政区划名称获取区划,notes="权限码(sys:regionLastCode:regionbyname)"
   * */
   @GetMapping(value = "/sysRegion/getSysRegionByName")
   public Result<SysRegionForm> getSysRegionByName(@ApiParam(value = "行政区划名称", required = true) @RequestParam(value = "regionName") String regionName);



    /*
    * 根据行政区划代码获取当前机构的所有上级机构信息
    * */
    @GetMapping("/sysRegion/getParentNodeByRegionCode/{regionCode}")
    public Result<List<SysRegionTreeVo>> getParentNodeByRegionCode(@ApiParam(value = "行政区划代码") @PathVariable("regionCode") String regionCode);

    /**
     *
     * @param parentId
     * @return
     */
    @GetMapping("/sysRegion/getListByParentId/{parentId}")
    Result<List<SysRegionForm>> getListByParentId(@PathVariable("parentId") String parentId);


    /**
     * 根据区划代码获取代码对应的区划名称
     * @param codes
     * @return
     */
    @GetMapping("/sysRegion/getRegionNamesByCodes/{codes}")
     Result<String> getRegionNamesByCodes(@ApiParam(value = "区划代码，多个用逗号分隔") @PathVariable("codes") String codes);

    /**
     * 根据组织机构id获取该组织机构下所有用户列表
     * @param orgId
     * @return
     */
    @GetMapping("/user/getUserListByOrgId")
    Result<List<SysUserForm>> getUserListByOrgId(@RequestParam(value = "orgId",required = true)String orgId,
                                                 @RequestParam(value = "proxyUser",required = false) String proxyUser);
}
