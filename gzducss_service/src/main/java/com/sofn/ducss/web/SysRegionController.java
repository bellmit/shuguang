package com.sofn.ducss.web;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.sofn.ducss.enums.LogEnum;
import com.sofn.ducss.enums.SysManageEnum;
import com.sofn.ducss.exception.SofnException;
import com.sofn.ducss.model.SysRegion;
import com.sofn.ducss.model.basemodel.Result;
import com.sofn.ducss.service.*;
import com.sofn.ducss.util.BoolUtils;
import com.sofn.ducss.util.IdUtil;
import com.sofn.ducss.util.LogUtil;
import com.sofn.ducss.util.PageUtils;
import com.sofn.ducss.util.shiro.JWTToken;
import com.sofn.ducss.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 行政区划接口
 *
 * @author heyongjie
 */
@Api(tags = "行政区划接口")
@RestController
@RequestMapping("/sysRegion")
@Slf4j
public class SysRegionController extends BaseController {

    @Autowired
    private SysRegionToTreeService sysRegionToTreeService;

    @Autowired
    private SysRegionService sysRegionService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    SysOrgService sysOrgService;

    @ApiOperation(value = "按条件分页获取信息", notes = "权限码(sys:regionLastCode:index)")
    @GetMapping("/getSysRegionByPage")
    public Result<PageUtils<SysRegionForm>> getSysRegionByPage(@RequestParam(required = false) @ApiParam(value = "行政区划名称") String regionName,
                                                               @RequestParam(required = false) @ApiParam(value = "行政区划代码") String regionCode,
                                                               @RequestParam(required = false) @ApiParam(value = "父ID") String parentId,
                                                               @RequestParam(required = false) @ApiParam(value = "版本年份") Integer versionYear,
                                                               @RequestParam(required = false) @ApiParam(value = "版本代码") Long versionCode,
                                                               @RequestParam(name = "pageNo") @ApiParam(value = "当前页数", required = true) Integer pageNo,
                                                               @RequestParam(name = "pageSize") @ApiParam(value = "每页显示条数", required = true) Integer pageSize) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("regionName", regionName);
        params.put("regionCode", regionCode);
        params.put("parentId", parentId);
        params.put("delFlag", SysManageEnum.DEL_FLAG_N.getCode());
        params.put("versionYear", versionYear);
        params.put("versionCode", versionCode);
        // 获取分页内容
        PageUtils<SysRegionForm> sysRegionPageUtils = sysRegionService.getSysRegionByContionPage(params, pageNo, pageSize);
        return Result.ok(sysRegionPageUtils);
    }

    @ApiOperation(value = "按条件分页获取信息(区域代码批量传入)", notes = "权限码(sys:regionLastCode:index)")
    @PostMapping(value = "/getSysRegionByContionsPage", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<PageUtils<SysRegionForm>> getSysRegionByContionsPage(@RequestBody HashMap<String, String> paramMap) {
        Map<String, Object> params = new HashMap<>();
        params.put("regionName", paramMap.get("regionName"));
        params.put("regionCode", paramMap.get("regionCode"));
        params.put("parentId", paramMap.get("parentId"));
        params.put("delFlag", SysManageEnum.DEL_FLAG_N.getCode());
        params.put("versionYear", paramMap.get("versionYear"));
        params.put("versionCode", paramMap.get("versionCode"));

        Integer pageNo = Integer.parseInt(paramMap.get("pageNo"));
        Integer pageSize = Integer.parseInt(paramMap.get("pageSize"));
        // 获取分页内容
        PageUtils<SysRegionForm> sysRegionPageUtils = sysRegionService.getSysRegionByContionsPage(params, pageNo, pageSize);
        return Result.ok(sysRegionPageUtils);
    }

    @ApiOperation(value = "添加行政区划信息", notes = "权限码(sys:regionLastCode:create)")
    @PostMapping(value = "/saveSysRegion")
    public Result<String> saveSysRegion(@Validated @RequestBody @ApiParam(name = "行政区划对象", value = "传入json格式", required = true)
                                                SysRegionForm sysRegionForm, BindingResult result) {
        // 校验参数
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }

        // 转化为MODEL对象
        SysRegion sysRegion = SysRegionForm.getSysRegion(sysRegionForm);
        sysRegionService.addSysRegion(sysRegion);
        // LogUtil.addLog(LogEnum.LOG_TYPE_REGION_ADD.getCode(), "新增-"  + "-<区划新增>");

        return Result.ok("保存行政区划成功");
    }

    @ApiOperation(value = "修改行政区划信息", notes = "权限码(sys:regionLastCode:update)")
    @PostMapping(value = "/updateSysRegion")
    public Result<String> updateSysRegion(@Validated @RequestBody @ApiParam(name = "行政区划对象", value = "传入json格式", required = true)
                                                  SysRegionForm sysRegionForm, BindingResult result) {
        // 校验参数
        if (result.hasErrors()) {
            return Result.error(getErrMsg(result));
        }
        if (StringUtils.isEmpty(sysRegionForm.getId())) {
            throw new SofnException("Id不能为空");
        }
        // 转化为MODEL对象
        SysRegion sysRegion = SysRegionForm.getSysRegion(sysRegionForm);
        sysRegionService.updateSysRegion(sysRegion);
        // LogUtil.addLog(LogEnum.LOG_TYPE_REGION_EDIT.getCode(), "编辑-"  + "-<区划编辑>");
        return Result.ok("修改行政区划成功");
    }

    @ApiOperation(value = "删除行政区划信息", notes = "权限码(sys:regionLastCode:delete)")
    @PostMapping(value = "/deleteSysRegion/{id}")
    public Result<String> deleteSysRegion(@ApiParam(value = "行政区划ID", required = true) @PathVariable(value = "id") String id) {
        if (StringUtils.isEmpty(id)) {
            return Result.error("ID不能为空");
        }
        sysRegionService.deleteSysRegion(id);
        return Result.ok("删除行政区划成功");
    }

    @ApiOperation(value = "获取行政区划树结构，获取整个行政区划树结构,从顶级100000开始，如果有条件将返回符合条件的上下级关系树",
            notes = "权限码(sys:regionLastCode:getSysRegionTree)")
    @GetMapping(value = "/getSysRegionTree")
    public Result<SysRegionTreeVo> getSysRegionTree(@ApiParam(value = "行政区划名称") @RequestParam(required = false) String keyword,
                                                    @ApiParam(value = "父节点ID") @RequestParam(required = false) String parentId,
                                                    @ApiParam(value = "行政区划代码") @RequestParam(required = false) String regionCode,
                                                    @ApiParam(value = "是否鉴权：Y鉴权（和当前的登录用户相关，例如是成都市的用户，就返回四川省-成都市-List<区>），使用鉴权后筛选无效")
                                                    @RequestParam(value = "isAuth", required = false) String isAuth,
                                                    @ApiParam(value = "行政区划级别(province省city市, 例如鉴权情况下双流县用户如果选择行政区域为province,则能查出整个双流上级到省,整个四川省的区域)") @RequestParam(required = false) String level,
                                                    @ApiParam(value = "如果是第三方登录用户，如果用户对应的机构代理了多个行政机构，那么需要传入一个明确的机构以确定机构级别") @RequestParam(required = false) String orgId,
                                                    @ApiParam(value = "系统APPID") @RequestParam(required = false) String appId,
                                                    @ApiParam(value = "版本年份") @RequestParam(required = false) Integer versionYear,
                                                    @RequestHeader(value = JWTToken.TOKEN, required = false) @ApiParam(required = false) String token
    ) {
        SysRegionTreeVo treeVo;
        versionYear = Calendar.getInstance().get(Calendar.YEAR);
        if (BoolUtils.Y.equals(isAuth)) {
            return Result.ok(sysRegionToTreeService.getSysRegionTreeByLoginUser(token, level, orgId, appId, versionYear));
        }

        if (StringUtils.isNotBlank(keyword) || StringUtils.isNotBlank(parentId) || StringUtils.isNotBlank(regionCode)) {
            treeVo = sysRegionToTreeService.getSysRegionTree(appId, keyword, parentId, regionCode, versionYear);
        } else {
            treeVo = sysRegionToTreeService.getSysRegionTreeByCache(appId, versionYear);
        }
        return Result.ok(treeVo);
    }

    @ApiOperation(value = "根据行政区划代码获取一个", notes = "权限码(sys:regionLastCode:detail)")
    @GetMapping(value = "/getSysRegionOne/{id}")
    public Result<SysRegionForm> getSysRegionOne(@ApiParam(value = "行政区划代码", required = true) @PathVariable(value = "id") String id,
                                                 @ApiParam(value = "版本年份") @RequestParam(value = "versionYear", required = false) Integer versionYear) {
        if (StringUtils.isBlank(id)) {
            return Result.error("ID不能为空");
        }

        Map<String, Object> params = new HashMap<>();
        params.put("regionCode", id);
        params.put("versionCode", sysRegionService.getMaxVersionCodeByYear(versionYear));
        List<SysRegion> regionList = sysRegionService.getSysRegionByContion(params);

        if (!CollectionUtils.isEmpty(regionList)) {
            SysRegion sysRegion = regionList.get(0);
            SysRegionForm sysRegionForm = SysRegionForm.getSysRegionForm(sysRegion);
            sysRegionForm.setAreaName(sysRegionService.getAreaName(sysRegion.getAreaId()));
            return Result.ok(sysRegionForm);
        }

        return Result.error("未找到数据");
    }

    @ApiOperation(value = "根据行政区划名称获取区划", notes = "权限码(sys:regionLastCode:regionbyname)")
    @GetMapping(value = "/getSysRegionByName")
    public Result<SysRegionForm> getSysRegionByName(@ApiParam(value = "行政区划名称", required = true) @RequestParam(value = "regionName") String regionName,
                                                    @ApiParam(value = "版本年份") @RequestParam(value = "versionYear", required = false) Integer versionYear) {
        if (StringUtils.isBlank(regionName)) {
            return Result.error("区划名称不能为空");
        }

        Map<String, Object> params = new HashMap<>();
        params.put("regionName", regionName);
        params.put("versionCode", sysRegionService.getMaxVersionCodeByYear(versionYear));
        List<SysRegion> regionList = sysRegionService.getSysRegionByContion(params);

        if (!CollectionUtils.isEmpty(regionList)) {
            SysRegion sysRegion = regionList.get(0);
            if (sysRegion != null) {
                SysRegionForm sysRegionForm = SysRegionForm.getSysRegionForm(sysRegion);
                sysRegionForm.setAreaName(sysRegionService.getAreaName(sysRegion.getAreaId()));
                return Result.ok(sysRegionForm);
            }
        }

        return Result.error("未找到数据");
    }

    @ApiOperation(value = "根据父ID获取当前ID下面的树结构，只返回一层", notes = "权限码(sys:regionLastCode:getSysRegionTreeById)")
    @GetMapping("/getSysRegionTreeById/{parentId}")
    public Result<List<SysRegionTreeVo>> getSysRegionTreeById(@ApiParam(value = "父ID", required = true) @PathVariable("parentId") String parentId,
                                                              @ApiParam(value = "系统APPID") @RequestParam(required = false) String appId,
                                                              @ApiParam(value = "版本年份") @RequestParam(value = "versionYear", required = false) Integer versionYear) {
        if (StringUtils.isBlank(parentId)) {
            parentId = SysManageEnum.ROOT_LEVEL.getCode();
        }

        List<SysRegionTreeVo> sysRegionTreeVos = sysRegionToTreeService.getSysRegionTreeByIdAndCache(appId, parentId, versionYear);
        return Result.ok(sysRegionTreeVos);
    }

    @ApiOperation(value = "根据IDS批量删除", notes = "权限码(sys:regionLastCode:batchDelete)")
    @PostMapping("/batchDeleteRegion/{ids}")
    public Result<String> batchDeleteRegion(@ApiParam(value = "待删除IDS，ID用英文,号分隔") @PathVariable(value = "ids") String ids) {
        if (StringUtils.isBlank(ids)) {
            return Result.ok();
        }
        List<String> idList = IdUtil.getIdsByStr(ids);
        sysRegionService.batchDeleteSysRegion(idList);
        return Result.ok("批量删除行政区划成功");
    }

    @ApiOperation(value = "根据行政区划编码获取名字", notes = "权限码(sys:regionLastCode:getSysRegionByName)")
    @GetMapping("/getSysRegionName/{id}")
    public Result<String> getSysRegionName(@PathVariable(value = "id") String id,
                                           @ApiParam(value = "版本年份") @RequestParam(value = "versionYear", required = false) Integer versionYear) {
        if (StringUtils.isBlank(id)) {
            return Result.error("ID不能为空");
        }

        Map<String, Object> params = new HashMap<>();
        params.put("regionCode", id);
        params.put("versionCode", sysRegionService.getMaxVersionCodeByYear(versionYear));

        List<SysRegion> regionList = sysRegionService.getSysRegionByContion(params);
        if (!CollectionUtils.isEmpty(regionList)) {
            SysRegion sysRegion = regionList.get(0);
            if (sysRegion != null) {
                return Result.ok(sysRegion.getRegionName(), "成功");
            }
        }

        return Result.ok("成功");
    }

    @ApiOperation(value = "根据父ID获取下级节点列表，多用于列表联动操作", notes = "权限码(sys:regionLastCode:getListByParentId)")
    @GetMapping("/getListByParentId/{parentId}")
    public Result<List<SysRegionForm>> getListByParentId(@ApiParam(value = "父节点ID,如果不传入，默认为100000") @PathVariable("parentId") String parentId,
                                                         @ApiParam(value = "系统APPID") @RequestParam(required = false) String appId,
                                                         @ApiParam(value = "是否鉴权（和当前的登录用户相关，如果是省级用户，" +
                                                                 "那么根据100000查询的时候只会显示当前登录用户的省级节点）")
                                                         @RequestParam(value = "isAuth", required = false) String isAuth,
                                                         @ApiParam(value = "版本年份") @RequestParam(value = "versionYear", required = false) Integer versionYear
    ) {
        if (StringUtils.isBlank(parentId)) {
            parentId = "100000";
        }

        if (StringUtils.isBlank(isAuth)) {
            isAuth = "N";
        }

        List<SysRegionForm> sysRegionForms = sysRegionService.getListByRegionCode(appId, parentId, isAuth, versionYear);
        return Result.ok(sysRegionForms);
    }

    @ApiOperation(value = "获取区域列表", notes = "权限码(sys:regionLastCode:getAreaList)")
    @GetMapping("/getAreaList")
    public Result<List<SysDictVo>> getAreaList() {
        List<SysDictVo> sysDictVos = sysRegionService.getRegionAreaList(SysManageEnum.SYS_AREA_DICT_TYPE_KEY.getCode());
        return Result.ok(sysDictVos);
    }

    @ApiOperation(value = "根据行政区划代码获取当前区划的所有上级区划信息", notes = "sys:regionLastCode:getParentNodeByRegionCode")
    @GetMapping("/getParentNodeByRegionCode/{regionCode}")
    public Result<List<SysRegionTreeVo>> getParentNodeByRegionCode(@ApiParam(value = "行政区划代码") @PathVariable("regionCode") String regionCode,
                                                                   @ApiParam(value = "版本年份") @RequestParam(value = "versionYear", required = false) Integer versionYear) {
        List<SysRegionTreeVo> parentNode = sysRegionToTreeService.getParentNode(regionCode, versionYear);
        return Result.ok(parentNode);
    }

    @ApiOperation(value = "根据行政区划代码获取当前区划的所有上级区划信息", notes = "sys:regionLastCode:getParentNodeByRegionId")
    @GetMapping("/getParentNodeByRegionId/{regionId}")
    public Result<List<SysRegionTreeVo>> getParentNodeByRegionId(@ApiParam(value = "行政区划代码id") @PathVariable("regionId") String regionId,
                                                                 @ApiParam(value = "版本年份") @RequestParam(value = "versionYear", required = false) Integer versionYear) {
        List<SysRegionTreeVo> parentNode = sysRegionToTreeService.getParentNodeByRegionId(regionId, versionYear);
        return Result.ok(parentNode);
    }

    @ApiOperation(value = "根据区划代码获取代码对应的区划名称", notes = "sys:regionLastCode:getRegionNamesByCodes")
    @GetMapping("/getRegionNamesByCodes/{codes}")
    public Result<String> getRegionNamesByCodes(@ApiParam(value = "区划代码，多个用逗号分隔") @PathVariable("codes") String codes,
                                                @ApiParam(value = "版本年份") @RequestParam(value = "versionYear", required = false) Integer versionYear) {
        return Result.ok((Object) sysRegionService.getRegionNames(codes, versionYear));
    }

    @ApiOperation(value = "更新缓存中的区划", notes = "sys:regionLastCode:updateRegionRedisCache")
    @GetMapping("/updateRegionRedisCache")
    public Result<String> updateRegionRedisCache() {
        sysRegionService.updateRegionRedisCache();
        return Result.ok();
    }


    @ApiOperation(value = "根据父ID获取下面的子区划，只返回一层，如果带了条件后台不进行分页并且不止返回一层，任何一个分页参数未传入就不分页"
            , notes = "sys:regionLastCode:getSysRegionFormByParentId")
    @GetMapping("/getSysRegionFormByParentId")
    public Result<PageUtils<SysRegionTreeVo>> getSysRegionFormByParentId(@ApiParam(value = "行政区划名称") @RequestParam(required = false) String keyword,
                                                                         @ApiParam(value = "父节点ID，当没有条件时必传", required = true) @RequestParam String parentId,
                                                                         @ApiParam(value = "行政区划代码") @RequestParam(required = false) String regionCode,
                                                                         @ApiParam(value = "版本年份") @RequestParam(value = "versionYear", required = false) Integer versionYear,
                                                                         @ApiParam(value = "从哪条记录开始") @RequestParam(required = false) Integer pageNo,
                                                                         @ApiParam(value = "每页显示多少条") @RequestParam(required = false) Integer pageSize) {
        Map<String, String> params = Maps.newHashMap();
        params.put("parentId", parentId);
        if (StringUtils.isNotBlank(keyword)) {
            params.put("regionName", keyword);
        }
        if (StringUtils.isNotBlank(regionCode)) {
            params.put("regionCode", regionCode);
        }
        PageUtils<SysRegionTreeVo> sysRegionFormByParentId = sysRegionToTreeService.getSysRegionFormByParentId(params, versionYear, pageNo, pageSize);
        return Result.ok(sysRegionFormByParentId);
    }

    @ApiOperation(value = "获取库中版本年份列表", notes = "sys:regionLastCode:getVersionYearList")
    @GetMapping("/getVersionYearList")
    public Result<List<Integer>> getVersionYearList() {
        return Result.ok(sysRegionService.getVersionYearList());
    }

    @ApiOperation(value = "获取可供选择的版本年份列表，用于新增/编辑中", notes = "sys:regionLastCode:getVersionYearListForAvailable")
    @GetMapping("/getVersionYearListForAvailable")
    public Result<List<Integer>> getVersionYearListForAvailable() {
        return Result.ok(sysRegionService.getVersionYearListForAvailable());
    }

    @ApiOperation(value = "根据区划代码获取代码对应的区划名称，返回Map<String,String>  key为code value为code对应的值", notes = "sys:regionLastCode:getRegionNameMapsByCodes")
    @GetMapping("/getRegionNameMapsByCodes")
    public Result<Map<String, String>> getRegionNameMapsByCodes(@ApiParam(value = "区划代码，多个用逗号分隔") @RequestParam("codes") String codes,
                                                                @ApiParam(value = "版本年份") @RequestParam(value = "versionYear", required = false) Integer versionYear) {
        List<String> idsByStr = IdUtil.getIdsByStr(codes);
        if (CollectionUtils.isEmpty(idsByStr)) {
            return Result.ok(Maps.newHashMap());
        }
        Map<String, String> regionNameMaps = sysRegionService.getRegionNameMaps(Sets.newHashSet(idsByStr), versionYear);
        return Result.ok(regionNameMaps);
    }

    @ApiOperation(value = "农业体系获取支撑平台部级/省级/市级/县级状态,根据用户展示不用状态")
    @GetMapping(value = "/getStatusFromSys")
    public Result<String> getStatusFromSys(@ApiParam(value = "用户名称") @RequestParam("userName") String userName) {
        //支撑平台获取用户信息
        SysUserForm userInfo = sysUserService.getUserInfoByUsername(userName);
        if (null == userInfo) {
            log.error("用户不存在:", userName);
        }
        SysOrganizationForm orgInfo = sysOrgService.getInfoById(userInfo.getOrganizationId());
        if (null == orgInfo && orgInfo.getOrganizationLevel().equals("")) {
            log.error("组织不存在:", orgInfo.getOrganizationName());
        }
        return Result.ok(orgInfo.getOrganizationLevel());
    }

}
