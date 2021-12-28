package com.sofn.ducss.sysapi;

import com.sofn.common.config.FeignConfiguration;
import com.sofn.common.model.Result;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.shiro.JWTToken;
import com.sofn.ducss.sysapi.bean.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 使用Feign 调用sys服务
 *
 * @author simon
 */
@FeignClient(
        value = "sys-service",
        configuration = FeignConfiguration.class
)
public interface SysApi {

    /**
     * 根据areaId获取
     *
     * @return Result
     */
    @GetMapping("/sysRegion/getParentNodeByRegionCode/{regionCode}")
    Result<List<SysRegionTreeVo>> getParentNode(@ApiParam(value = "行政区划ID", required = true) @PathVariable(value = "regionCode") String regionCode,
                                                @ApiParam(value = "版本年份") @RequestParam(value = "versionYear", required = false) Integer versionYear);

    /**
     * 获取区县信息
     *
     * @return Result
     */
    @GetMapping(value = "/sysRegion/getSysRegionOne/{areaId}")
    Result<SysRegionForm> getSysRegionOne(@ApiParam(value = "行政区划ID", required = true) @PathVariable(value = "areaId") String areaId);

    /**
     * 根据区划id获取省市区名称
     *
     * @return Result<String>
     */
    @ApiOperation(value = "根据ID获取名字", notes = "权限码(sys:regionLastCode:getSysRegionByName)")
    @GetMapping("/sysRegion/getSysRegionName/{id}")
    Result<String> getSysRegionName(@PathVariable(value = "id") String id,
                                    @RequestParam(value = "versionYear", required = false) Integer versionYear);

    /**
     * 获取行政区划树结构
     *
     * @param keyword
     * @param parentId
     * @param regionCode
     * @param isAuth
     * @param token
     * @return
     */
    @GetMapping(value = "/sysRegion/getSysRegionTree")
    Result<SysRegionTreeVo> getSysRegionTree(@ApiParam(value = "行政区划名称") @RequestParam(required = false, value = "keyword") String keyword,
                                             @ApiParam(value = "父节点ID") @RequestParam(required = false, value = "parentId") String parentId,
                                             @ApiParam(value = "行政区划代码") @RequestParam(required = false, value = "regionCode") String regionCode,
                                             @ApiParam(value = "是否鉴权：Y鉴权（和当前的登录用户相关，例如是成都市的用户，就返回四川省-成都市-List<区>），使用鉴权后筛选无效") @RequestParam(required = false, value = "isAuth") String isAuth,
                                             @ApiParam(value = "行政区划级别(province省city市, 例如鉴权情况下双流县用户如果选择行政区域为province,则能查出整个双流上级到省,整个四川省的区域)") @RequestParam(required = false, value = "level") String level,
                                             @ApiParam(value = "系统APPID") @RequestParam(value = "appId", required = false) String appId,
                                             @RequestHeader(value = JWTToken.TOKEN, required = false) @ApiParam(required = false) String token
    );

    /**
     * 根据年度获取行政区划树结构
     *
     * @return
     */
    @GetMapping(value = "/sysRegion/getSysRegionTree")
    Result<SysRegionTreeVo> getSysRegionTreeByYear(@ApiParam(value = "行政区划名称") @RequestParam(required = false, value = "keyword") String keyword,
                                                   @ApiParam(value = "父节点ID") @RequestParam(required = false, value = "parentId") String parentId,
                                                   @ApiParam(value = "行政区划代码") @RequestParam(required = false, value = "regionCode") String regionCode,
                                                   @ApiParam(value = "是否鉴权：Y鉴权（和当前的登录用户相关，例如是成都市的用户，就返回四川省-成都市-List<区>），使用鉴权后筛选无效") @RequestParam(required = false, value = "isAuth") String isAuth,
                                                   @ApiParam(value = "行政区划级别(province省city市, 例如鉴权情况下双流县用户如果选择行政区域为province,则能查出整个双流上级到省,整个四川省的区域)") @RequestParam(required = false, value = "level") String level,
                                                   @ApiParam(value = "系统APPID") @RequestParam(value = "appId", required = false) String appId,
                                                   @ApiParam(value = "版本年份") @RequestParam(name = "versionYear", required = false) Integer versionYear,
                                                   @RequestHeader(value = JWTToken.TOKEN, required = false) @ApiParam(required = false) String token
    );

    @PostMapping("/user/getUserOne")
    Result<SysUserForm> getUserOne(@ApiParam(name = "id", value = "用户ID", required = true) @RequestParam("id") String id);

    @ApiOperation(value = "查询当前类型下的所有字典", notes = "权限码(sys:dict:getDictListByType)")
    //    @RequiresPermissions("sys:dict:getDictListByType")
    @ResponseBody
    @GetMapping(value = "/dict/getDictListByType")
    public Result<List<SysDict>> getDictListByType(@ApiParam(required = true, value = "字典类型值") @RequestParam(value = "typevalue") String typevalue);

    @GetMapping(value = "/sysRegion/getRegionNamesByCodes/{codes}")
    Result<String> getRegionNamesByCodes(@ApiParam(value = "区划代码，多个用逗号分隔", name = "codes", required = true) @PathVariable("codes") String codes,
                                         @ApiParam(value = "版本年份") @RequestParam(value = "versionYear", required = false) Integer versionYear);

    /**
     * 根据父ID，查询所有下一级子区划列表
     *
     * @param parentId
     * @return
     */
    @GetMapping("/sysRegion/getListByParentId/{parentId}")
    Result<List<SysRegionTreeVo>> getListByParentId(@ApiParam(value = "父节点ID,如果不传入，默认为100000") @PathVariable("parentId") String parentId,
                                                    @ApiParam(value = "系统APPID") @RequestParam(value = "appId", required = false) String appId,
                                                    @ApiParam(value = "是否鉴权（和当前的登录用户相关，如果是省级用户，" +
                                                            "那么根据100000查询的时候只会显示当前登录用户的省级节点）")
                                                    @RequestParam(value = "isAuth", required = false) String isAuth,
                                                    @ApiParam(value = "版本年份") @RequestParam(value = "versionYear", required = false) Integer versionYear
    );


    @GetMapping("/sysRegion/getRegionNameMapsByCodes")
    Result<Map<String, String>> getRegionNameMapsByCodes(@ApiParam(value = "区划代码，多个用逗号分隔") @RequestParam("codes") String codes,
                                                         @ApiParam(value = "版本年份") @RequestParam(value = "versionYear", required = false) Integer versionYear);

    /**
     * 根据父ID，查询所有下一级子区划列表
     *
     * @param parentId
     * @return
     */
    @GetMapping("/sysRegion/getSysRegionTreeById/{parentId}")
    Result<List<SysRegionTreeVo>> getSysRegionTreeById(@ApiParam(value = "父ID", required = true) @PathVariable("parentId") String parentId,
                                                       @ApiParam(value = "系统APPID") @RequestParam(value = "appId", required = false) String appId);

    @ApiOperation(value = "根据父ID获取下面的子区划，只返回一层，如果带了条件后台不进行分页并且不止返回一层，任何一个分页参数未传入就不分页")
    @GetMapping("/sysRegion/getSysRegionFormByParentId")
    Result<PageUtils<SysRegionTreeVo>> getSysRegionFormByParentId(@ApiParam(value = "行政区划名称") @RequestParam(value = "keyword", required = false) String keyword,
                                                                  @ApiParam(value = "父节点ID，当没有条件时必传") @RequestParam(value = "parentId", required = false) String parentId,
                                                                  @ApiParam(value = "行政区划代码") @RequestParam(value = "regionCode", required = false) String regionCode,
                                                                  @ApiParam(value = "从哪条记录开始") @RequestParam(value = "pageNo", required = false) Integer pageNo,
                                                                  @ApiParam(value = "每页显示多少条") @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                                                  @ApiParam(value = "系统APPID") @RequestParam(value = "appId", required = false) String appId);

    @ApiOperation(value = "根据行政区划代码获取当前区划的所有上级区划信息")
    @GetMapping("/sysRegion/getParentNodeByRegionCode/{regionCode}")
    public Result<List<SysRegionTreeVo>> getParentNodeByRegionCode(@ApiParam(value = "行政区划代码") @PathVariable("regionCode") String regionCode);

    @ApiOperation(value = "按条件分页获取信息", notes = "权限码(sys:regionLastCode:index)")
    @GetMapping("/sysRegion/getSysRegionByPage")
    public Result<PageUtils<SysRegionForm>> getSysRegionByPage(@RequestParam(required = false, value = "regionName") @ApiParam(value = "行政区划名称") String regionName,
                                                               @RequestParam(required = false, value = "regionCode") @ApiParam(value = "行政区划代码") String regionCode,
                                                               @RequestParam(required = false, value = "parentId") @ApiParam(value = "父ID") String parentId,
                                                               @RequestParam(required = false, value = "versionYear") @ApiParam(value = "版本年份") Integer versionYear,
                                                               @RequestParam(required = false, value = "versionCode") @ApiParam(value = "版本代码") Long versionCode,
                                                               @RequestParam(name = "pageNo", value = "pageNo") @ApiParam(value = "当前页数", required = true) Integer pageNo,
                                                               @RequestParam(name = "pageSize", value = "pageSize") @ApiParam(value = "每页显示条数", required = true) Integer pageSize);

    /**
     * 根据行政等级,系统id,区域id获取指定用户信息
     *
     * @param regionCodes 区域list
     * @param orgLevel    机构等级
     * @param appId       系统id
     * @return
     */
    @GetMapping({"/user/getUserByOrgInfoAndAppId"})
    @ApiOperation(
            value = "根据机构信息和系统代码获取用户信息",
            notes = "权限码(sys:user:getUserInfoByRegionCodeAndAppId)"
    )
    public Result<List<SysUserForm>> getUserByOrgInfoAndAppId(@ApiParam(name = "区划代码集合", value = "regionCodes") @RequestParam(value = "regionCodes", required = false) List<String> regionCodes, @ApiParam(name = "机构级别", value = "orgLevel") @RequestParam(value = "orgLevel", required = false) String orgLevel, @ApiParam(value = "appId", name = "系统appid", required = true) @RequestParam(value = "appId") String appId);


    @PostMapping(value = "/sysRegion/getSysRegionByContionsPage", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<PageUtils<SysRegionForm>> getSysRegionByContionsPage(@RequestBody HashMap<String, String> paramMap);

    @ApiOperation(value = "获取库中版本年份列表")
    @GetMapping("/sysRegion/getVersionYearList")
    Result<List<Integer>> getVersionYearList();

}
