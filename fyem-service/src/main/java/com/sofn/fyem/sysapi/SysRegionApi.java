package com.sofn.fyem.sysapi;

import com.google.common.collect.Maps;
import com.sofn.common.config.FeignConfiguration;
import com.sofn.common.model.Result;
import com.sofn.common.utils.PageUtils;
import com.sofn.fyem.sysapi.bean.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * @Description: 用于获取行政区划名称
 * @Author: DJH
 * @Date: 2020\4\29
 */
@FeignClient(
        value = "sys-service",
        configuration = FeignConfiguration.class
)
public interface SysRegionApi {


    /**
     * 根据ID获取省市区名字
     * @param id
     * @return
     */
    @GetMapping("/sysRegion/getSysRegionName/{id}")
    Result<String> getSysRegionName(@PathVariable(value = "id") String id);

    /**
     * 根据组织机构ID获取机构详细信息
     * @param id
     * @return
     */
    @PostMapping("/sysOrganization/getOrgInfoById")
    Result<SysOrganizationForm> getOrgInfoById(
            @ApiParam(value = "机构ID",required = true)
            @NotNull @RequestParam(value = "id") String id);


    /**
     * 根据行政机构ID查询系统下分配的代理机构
     * @param subsystemId
     * @param orgId
     * @param agentCode
     * @return
     */
    @GetMapping("/sysOrgAgent/getOrgAgentByOrgId")
    Result<List<SysOrganization>> getOrgAgentByOrgId(
            @RequestParam(required = false,value = "subsystemId")
            @ApiParam(name = "subsystemId", value = "系统ID",required =true) String subsystemId,
            @RequestParam(required = false,value = "orgId") @ApiParam(name = "orgId", value = "行政机构ID",required = true) String orgId,
            @ApiParam(name = "agentCode", value = "代理机构标识") @RequestParam(required = false,value = "agentCode") String agentCode);


    /**
     * 根据代理机构ID查询代理的行政机构
     * @param subsystemId
     * @param agentOrgId
     * @param agentCode
     * @return
     */
    @GetMapping("/sysOrgAgent/getOrgListByAgentOrgId")
    Result<List<SysOrganization>> getOrgListByAgentOrgId(
            @RequestParam(required = true,value = "subsystemId")
            @ApiParam(name = "subsystemId", value = "系统ID",required = true) String subsystemId,
            @RequestParam (required = true,value = "agentOrgId") @ApiParam(name = "agentOrgId", value = "代理机构ID",required = true) String agentOrgId,
            @RequestParam(required = false,value = "agentCode") @ApiParam(name = "agentCode", value = "代理机构标识")String agentCode);


    /**
     * 根据系统APPID获取有代理机构的行政机构的列表
     * @param appId aeem
     * @return  Result<SysOrganizationForm>
     */
    @GetMapping("/sysOrgAgent/getOrgListHasAgent")
    Result<List<SysOrganizationForm>> getOrgListHasAgent(@ApiParam(name="appId",required = true) @RequestParam(value = "appId") String appId);

    /**
     * 根据行政区划代码获取当前机构的所有上级机构信息
     * @param regionCode 行政区划ID
     * @return List<SysRegionTreeVo>
     */
    @GetMapping(value = "/sysRegion/getParentNodeByRegionCode/{regionCode}", consumes =
            MediaType.APPLICATION_JSON_VALUE)
    Result<List<SysRegionTreeVo>> getParentNode(@ApiParam(value = "行政区划ID", required = true) @PathVariable(value = "regionCode") String regionCode);


    /**
     * 按条件查询代理机构行政机构信息
     * @param orgLevel
     * @param appId
     * @param proxyId
     * @param orgId
     * @param regionId
     * @param agentCode
     * @return
     */
    @GetMapping("/sysOrgAgent/getOrgAndProOrgInfo")
    Result<List<OrgAndProOrgInfoVo>> getOrgAndProOrgInfo(@ApiParam(name = "organizationLevel",defaultValue = "province", value = "机构级别,ministry:部级，province：省级，city：市级，county：县级，country：乡级")
                                                         @RequestParam(value = "orgLevel",required = false,defaultValue = "province") String orgLevel,
                                                         @ApiParam(name = "appId", value = "子系统ID或者AppId",required = true) @RequestParam(value = "appId") String appId,
                                                         @RequestParam(value = "proxyId",required = false) @ApiParam(name = "proxyId",value = "代理机构ID") String proxyId,
                                                         @RequestParam(value = "orgId",required = false) @ApiParam(name = "orgId",value = "行政机构ID")  String orgId,
                                                         @RequestParam(value = "regionId",required = false) @ApiParam(name = "regionId",value = "区划ID")   String regionId,
                                                         @RequestParam(value = "agentCode",required = false) @ApiParam(name = "agentCode",value = "代理标识")  String agentCode);

    /**
     * 根据父ID获取当前ID下面的树结构，只返回一层
     */
    @GetMapping("/sysRegion/getSysRegionTreeById/{parentId}")
    Result<List<SysRegionTreeVo>> getSysRegionTreeById(@ApiParam(value = "父ID", required = true) @PathVariable("parentId") String parentId);

    /**
     * 根据父级机构查询登录用户直接子机构列表
     * @param parentId
     * @param appId
     * @return
     */
    @GetMapping("/sysOrganization/listOfUserByPid")
    Result<List<SysOrgVo>> listByParentId(@RequestParam(value = "parentId",required = false) String parentId,
                                                 @RequestParam(value = "appId",required = false) String appId);

    /**
     * 根据区划获取直属行政机构列表
     * @param regionCode
     * @return
     */
    @GetMapping("/sysOrganization/getDirectOrgList")
    Result<List<SysOrgVo>> getDirectOrgList(@RequestParam(value = "regionCode") String regionCode);

    /**
     * 根据区划分页获取非行政机构列表
     * @param regionCode
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GetMapping("/sysOrganization/getOrgByAddressRegionCode")
    Result<PageUtils<SysOrg>> getOrgByAddressRegionCode(@RequestParam(value = "regionCode") String regionCode,
                                                        @RequestParam(value = "pageNo") Integer pageNo,
                                                        @RequestParam(value = "pageSize")Integer pageSize);

    /**
     * 查询登录用户的机构树形目录
     * @param appId
     * @param orgName
     * @param regionCode
     * @param orgLevel
     * @param isChildren
     * @param isAuth
     * @param thirdOrg
     * @return
     */
    @GetMapping("/sysOrganization/listTree")
    Result<List<SysOrgVo>> listTree(@RequestParam(name="appId",required = false) @ApiParam(value = "系统appId，或者系统ID") String appId,
                                           @RequestParam(name="orgName",required = false) @ApiParam(value = "机构名称") String orgName,
                                           @RequestParam(name="regionCode",required = false) @ApiParam(value = "行政区划/所在地区划代码") String regionCode,
                                           @RequestParam(name="orgLevel",required = false) @ApiParam(value = "机构级别，请参考机构级别字典") String orgLevel,
                                           @RequestParam(name="isChildren",required = false) @ApiParam(value = "是否携带子节点，默认为携带子节点，Y为携带，传入N或者其他不携带，注意：子节点没有进行条件筛选！") String isChildren,
                                           @RequestParam(name="isAuth",required = false) @ApiParam(value = "是否鉴权，Y鉴权,默认鉴权，传入N或者其他不鉴权") String isAuth,
                                           @RequestParam(name="thirdOrg",required = false) @ApiParam(value = "机构类别，Y行政机构 N非行政机构") String thirdOrg);

    /**
     * 根据父ID获取下级节点列表，多用于列表联动操作
     * @param parentId
     * @param appId
     * @param isAuth
     * @return
     */
    @GetMapping("/sysRegion/getListByParentId/{parentId}")
    Result<List<SysRegionForm>> getListByParentId(@ApiParam(value = "父节点ID,如果不传入，默认为100000") @PathVariable("parentId") String parentId,
                                                         @ApiParam(value = "系统APPID") @RequestParam(value = "appId", required = false) String appId,
                                                         @ApiParam(value = "是否鉴权（和当前的登录用户相关，如果是省级用户，" +
                                                                 "那么根据100000查询的时候只会显示当前登录用户的省级节点）")
                                                         @RequestParam(value = "isAuth",required = false) String isAuth);

    /**
     * 根据ID获取一个区划
     * @param id
     * @return
     */
    @GetMapping(value = "/sysRegion/getSysRegionOne/{id}")
    Result<SysRegionForm> getSysRegionOne(@ApiParam(value = "行政区划ID", required = true) @PathVariable(value = "id") String id);

}
