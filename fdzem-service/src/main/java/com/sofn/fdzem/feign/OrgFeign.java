package com.sofn.fdzem.feign;

import com.sofn.common.model.Result;
import com.sofn.common.utils.PageUtils;
import com.sofn.fdzem.vo.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * @Author:袁彬峰
 * @description：2020.04.03
 * @program:生态环境监测数据子系统
 */
@FeignClient(name = "sys-service", contextId = "org")
public interface OrgFeign {

    @PostMapping("/sysOrganization/getOrgInfoById")
    Result<SysOrganizationForm> getOrgInfoById(@ApiParam(value = "机构ID", required = true) @NotNull @RequestParam("id") String id);


    @PostMapping("/sysOrganization/getOrgInfoByOrgIds")
    Result<List<SysOrgVo>> getOrgInfoByOrgIds(BatchOrgForm batchOrgForm);


    @ApiOperation(value = "根据父级机构查询登录用户直接子机构列表", notes = "权限点:(sys:org:list)")
    @GetMapping("/sysOrganization/listOfUserByPid")
    Result<List<SysOrgVo>> listByParentId(@RequestParam(value = "parentId", required = false) String parentId,
                                          @RequestParam(value = "appId", required = false) String appId,
                                          @RequestParam(value = "pageNo", required = true) Integer pageNo,
                                          @RequestParam(value = "pageSize", required = true) Integer pageSize);


    @GetMapping("/sysOrganization/getInfoByCondition")
    @ApiOperation(value = "根据条件获取组织机构信息，Map信息如下：{ID:机构ID，ORGNAME:机构名称}", notes = "权限点:(sys:organization:getInfoByCondition)")
    Result<List<Map<String, String>>> getInfoByCondition(@RequestParam(name = "ids", required = false) @ApiParam(value = "机构IDS") String ids,
                                                         @RequestParam(name = "organizationName", required = false) @ApiParam(value = "机构名称模糊查询") String orgName,
                                                         @RequestParam(name = "pageNo", required = false) @ApiParam(value = "从哪开始") Integer pageNo,
                                                         @RequestParam(name = "pageSize", required = false) @ApiParam(value = "显示多少条，如果为0或者空不分页") Integer pageSize);

    @PostMapping("/sysOrganization/getOrgInfoById")
    Result<SysOrg> getOrgInfoByOrgId(@ApiParam(value = "机构ID", required = true) @NotNull @RequestParam("id") String id);

    @GetMapping("/sysOrganization/listOfTreeByAppId")
    Result<List<SysOrgVo>> listByParentId(@ApiParam(value = "系统APPID", required = true) @RequestParam(value = "appId") String appId,
                                          @ApiParam(value = "父机构ID") @RequestParam(value = "parentId", required = false) String parentId,
                                          @ApiParam(value = "机构级别") @RequestParam(value = "level", required = false) String level);

    /**
     * 获取全部非行政机构列表
     *
     * @param appId
     * @return
     */
    @PostMapping("/sysOrganization/getThirdOrgByPage")
    Result<PageUtils<SysOrgVo>> getThirdOrgByPage(@ApiParam(value = "系统APPID", required = true) @RequestParam(value = "appId") String appId,
                                                  @RequestParam(name = "pageNo", required = false) @ApiParam(value = "从哪开始") Integer pageNo,
                                                  @RequestParam(name = "pageSize", required = false) @ApiParam(value = "显示多少条，如果为0或者空不分页") Integer pageSize
    );

    /**
     * 根据职能id和系统id获取监测中心
     *
     * @param appId
     * @param functionCode
     * @return
     */
    @GetMapping("/sysOrganization/getOrgInfoByAppIdAndFunctionCode/{appId}/{functionCode}")
    Result<List<SysOrgVo>> getOrgInfoByAppIdAndFunctionCode(@PathVariable("appId") String appId,
                                                            @PathVariable("functionCode") String functionCode);

}
