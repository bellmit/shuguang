package com.sofn.ducss.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sofn.ducss.enums.LogEnum;
import com.sofn.ducss.enums.SysConstant;
import com.sofn.ducss.enums.SysManageEnum;
import com.sofn.ducss.exception.SofnException;
import com.sofn.ducss.log.SofnLog;
import com.sofn.ducss.model.SysOrg;
import com.sofn.ducss.model.SysOrganization;
import com.sofn.ducss.model.basemodel.Dict;
import com.sofn.ducss.model.basemodel.Result;
import com.sofn.ducss.model.tree.TreeUtil;
import com.sofn.ducss.service.SysOrgService;
import com.sofn.ducss.service.SysSplitUserOrgService;
import com.sofn.ducss.util.*;
import com.sofn.ducss.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
  * @Description: 组织机构管理2.0
  * @Author: quzhijie
  * @Date: 2020/3/19 17:03
  **/
@RestController
@Api(tags = "组织管理接口-NEW")
@RequestMapping("/sysOrganization")
@Slf4j
public class SysOrgController extends BaseController {

  @Autowired
  SysOrgService sysOrgService;

  @Autowired
  private SysSplitUserOrgService sysSplitUserOrgService;

  @ApiOperation(value = "查询系统下所有机构的树形目录",notes = "权限点:(sys:org:list)")
  @GetMapping("/listTreeOfAllByAppId")
  public Result<List<SysOrg>> listTreeOfAllByAppId(@RequestParam(value = "appId") String appId){
    List<SysOrg> list = sysOrgService.listOfAllByAppId(appId);
    if (CollectionUtils.isEmpty(list)){
      return Result.ok();
    }

    TreeUtil<SysOrg> treeUtil = new TreeUtil<>();
    return Result.ok(treeUtil.generate(list));
  }

  @ApiOperation(value = "根据父级机构查询登录用户直接子机构列表",notes = "权限点:(sys:org:list)")
  @GetMapping("/listOfUserByPid")
  public Result<List<SysOrgVo>> listByParentId(@RequestParam(value = "parentId",required = false) String parentId,
                                               @RequestParam(value = "appId",required = false) String appId){
    if (StringUtils.isBlank(parentId)) {
      parentId = SysManageEnum.ROOT_ORG.getCode();
    }

    List<SysOrgVo> list = sysOrgService.listOfUserByParentId(parentId, appId);
    return Result.ok(list);
  }

  @ApiOperation(value = "根据父级机构查询登录用户直接子机构列表（分页）",notes = "权限点:(sys:org:list)")
  @GetMapping("/listOfUserByPidForPaging")
  public Result<PageInfo<SysOrgVo>> listByParentId(@RequestParam(value = "parentId",required = false) String parentId,
                                               @RequestParam(value = "appId",required = false) String appId,
                                               @RequestParam(value = "pageNo",required = true) Integer pageNo,
                                               @RequestParam(value = "pageSize",required = true) Integer pageSize){
    if (StringUtils.isBlank(parentId)) {
      parentId = SysManageEnum.ROOT_ORG.getCode();
    }

    PageInfo<SysOrgVo> pageInfo = sysOrgService.listOfUserByParentIdForPaging(parentId, appId, pageNo, pageSize);
    return Result.ok(pageInfo);
  }

  @ApiOperation(value = "查询登录用户的机构树形目录",notes = "权限点:(sys:org:listtree)")
  @GetMapping("/listTree")
  public Result<List<SysOrgVo>> listTree(@RequestParam(name="appId",required = false) @ApiParam(value = "系统appId，或者系统ID") String appId,
                                          @RequestParam(name="orgName",required = false) @ApiParam(value = "机构名称") String orgName,
                                         @RequestParam(name="regionCode",required = false) @ApiParam(value = "行政区划/所在地区划代码") String regionCode,
                                         @RequestParam(name="orgLevel",required = false) @ApiParam(value = "机构级别，请参考机构级别字典") String orgLevel,
                                         @RequestParam(name="isChildren",required = false) @ApiParam(value = "是否携带子节点，默认为携带子节点，Y为携带，传入N或者其他不携带，注意：子节点没有进行条件筛选！") String isChildren,
                                         @RequestParam(name="isAuth",required = false) @ApiParam(value = "是否鉴权，Y鉴权,默认鉴权，传入N或者其他不鉴权") String isAuth,
                                         @RequestParam(name="thirdOrg",required = false) @ApiParam(value = "机构类别，Y行政机构 N非行政机构") String thirdOrg
                                         ){
    if(!StringUtils.isBlank(appId) && SysManageEnum.SUBSYSTEM_ROOT.getCode().equals(appId)){
      appId = "";
    }
    Map<String,String> params = Maps.newHashMap();
    params.put("appId",appId);
    params.put("isAuth",StringUtils.isBlank(isAuth) ? BoolUtils.Y  : isAuth);
    params.put("orgName",orgName);
    params.put("regionCode",regionCode);
    params.put("thirdOrg",thirdOrg);
    params.put("orgLevel",orgLevel);
    params.put("isChildren",StringUtils.isBlank(isChildren) ? BoolUtils.Y  : isChildren );
    log.info("开始获取树结构");
    List<SysOrgVo> list = sysOrgService.listOfUserTree(params);
    log.info("树结构获取成功");
    return Result.ok(list);
  }

  @ApiOperation(value = "加载组织树",notes = "权限点:(sys:organization:load)")
//    @RequiresPermissions("sys:sysOrganization:load")
  @RequestMapping(value = "/{id}/load", method = RequestMethod.POST)
  public Result<SysOrganizationForm> load(@PathVariable String id) {
    SysOrganizationForm infoById = sysOrgService.getInfoById(id);

    return Result.ok(infoById);
  }


  @ApiOperation(value="查询系统所有的组织结构列表",notes = "权限点:(sys:org:index)")
  @GetMapping("/getAllSysOgrInfo")
  public Result<List<SysOrgVo>> getAllByAppId(@RequestParam(name="appId") @ApiParam(value = "系统appId",required = true) String appId,
                                                @RequestParam(name="orgname",required = false) @ApiParam(value = "组织名称") String orgname,
                                               @RequestParam(name="regionid",required = false) @ApiParam(value = "区划ID") String regionid){
    List<SysOrgVo> treeList = sysOrgService.getAllByAppId(appId,orgname,regionid);
    return Result.ok(treeList);
  }

  @ApiOperation(value = "根据区划获取直属行政机构列表",notes = "权限码：(sys:org:getDirectOrgList)")
  @GetMapping("/getDirectOrgList")
  public Result<List<SysOrgVo>> getDirectOrgList(@RequestParam(value = "regionCode") String regionCode) {
    // 获取分页内容
    List<SysOrgVo> list = sysOrgService.getDirectOrgByRegionCode(regionCode);
    return Result.ok(list);
  }

  @ApiOperation(value = "根据区划分页获取非行政机构列表",notes = "权限码：(sys:org:getOrgByAddressRegionCode)")
  @GetMapping("/getOrgByAddressRegionCode")
  public Result<PageUtils<SysOrg>> getOrgByAddressRegionCode(@RequestParam(value = "regionCode") String regionCode, Integer pageNo, Integer pageSize) {
    // 获取分页内容
    PageUtils<SysOrg> sysOrganizationPageUtils = sysOrgService.getOrgByAddressRegionCode(regionCode,pageNo,pageSize);
    return Result.ok(sysOrganizationPageUtils);
  }

  @GetMapping("/getInfoByCondition")
  @ApiOperation(value = "根据条件获取组织机构信息，Map信息如下：{ID:机构ID，ORGNAME:机构名称}",notes = "权限点:(sys:organization:getInfoByCondition)")
  public Result<List<Map<String,String>>> getInfoByCondition(@RequestParam(name="ids",required = false) @ApiParam(value = "机构IDS") String ids,
                                                             @RequestParam(name="organizationName",required = false) @ApiParam(value = "机构名称模糊查询")  String orgName,
                                                             @RequestParam(name="pageNo",required = false) @ApiParam(value = "从哪开始") Integer pageNo,
                                                             @RequestParam(name="pageSize",required = false) @ApiParam(value = "显示多少条，如果为0或者空不分页")Integer pageSize){
    List<Map<String, String>> orgInfoByIdsAndName = sysOrgService.getOrgInfoByIdsAndName(ids, orgName, pageNo, pageSize);
    return Result.ok(orgInfoByIdsAndName);
  }

  @ApiOperation(value = "根据组织机构ID获取机构详细信息",notes = "权限点:(sys:organization:info)")
  @PostMapping("/getOrgInfoById")
  public Result<SysOrganizationForm> getOrgInfoById(@ApiParam(value = "机构ID",required = true) @NotNull @RequestParam("id") String id) {
    return Result.ok(sysOrgService.getInfoById(id));
  }

  @GetMapping("/getSysRegionInfoByOrgId/{orgId}")
  @ApiOperation(value = "根据机构ID获取区域信息",notes = "权限点:(sys:organization:getSysRegionInfoByOrgId)")
  public Result<SysRegionInfoVo> getSysRegionInfoByOrgId(@PathVariable("orgId") @ApiParam(value = "机构IDS",required = true)String orgId){
    SysRegionInfoVo sysRegionInfoVo = sysOrgService.getSysRegionInfoByOrgId(orgId);
    return Result.ok(sysRegionInfoVo);
  }

  @GetMapping("/getThirdOrg")
  @ApiOperation(value = "获取全部非行政机构列表",notes = "权限标getThirdOrg示:(sys:organization:getThirdOrg)")
  public Result<List<SysOrgVo>> getThirdOrg(@ApiParam(value = "appId",required = true) @NotNull @RequestParam("appId") String appId){
    return Result.ok(sysOrgService.getAllThridOrgList(appId));
  }

  @ApiOperation(
    value = "分页获取非行政组织机构信息",
    notes = "权限标示:(sys:organization:getThirdOrgByPage)"
  )
  @PostMapping("/getThirdOrgByPage")
  public Result<PageUtils<SysOrganization>> getThirdOrgByPage(
                                                              @ApiParam(value = "appId",required = true) @NotNull @RequestParam("appId") String appId,
                                                              @ApiParam(name = "pageNo",value = "页码",required = true) @RequestParam(name="pageNo",defaultValue = "0") Integer pageNo,
                                                              @ApiParam(name = "pageSize",value = "每页条数",required = true) @RequestParam(name="pageSize",defaultValue = "10",required = false) Integer pageSize) {
    PageHelper.offsetPage(pageNo, pageSize);
    List<SysOrgVo> list = sysOrgService.getAllThridOrgList(appId);
    PageInfo<SysOrgVo> pageInfo = new PageInfo<>(list);
    return Result.ok(PageUtils.getPageUtils(pageInfo));
  }

  @GetMapping("/getProxyOrgByRegionIdAndAppId")
  @ApiOperation(
    value = "根据区划ID和子系统ID查询代理机构",
    notes = "权限标示:(sys:organization:getProxyOrgByRegionIdAndAppId)"
  )
  public Result<List<SysOrganizationTreeVo>> getProxyOrgByRegionIdAndAppId(@RequestParam @ApiParam(value = "区划ID",required = true) String regionId,
                                                                           @RequestParam @ApiParam(value = "子系统ID或者AppId",required = true) String appId,
                                                                           @RequestParam(required = false) @ApiParam(value = "行政机构ID") String orgId){
    List<SysOrganizationTreeVo> proxyOrgByRegionIdAndAppId = sysOrgService.getProxyOrgByRegionIdAndAppId(regionId, appId, orgId);
    return Result.ok(proxyOrgByRegionIdAndAppId);
  }

  @GetMapping("/getFunctionList")
  @ApiOperation(value = "获取机构职能列表",notes = "权限码：(sys:org:getFunctionList)")
  public Result<List<Dict>> getFunctionList(){
    List<Dict> list = DictUtils.getByType(SysManageEnum.FUNCTION_DICT_TYPE.getCode());

    return Result.ok(list);
  }

  @GetMapping("/getOrgInfoByOrgFunction")
  @ApiOperation(value = "根据职能ID和AppId(现在没用)查询机构信息",notes = "权限码：(sys:org:getOrgInfoByOrgFunction)")
  public Result<List<SysOrganization>> getOrgInfoByOrgFunction(@NotBlank @RequestParam @ApiParam(value = "职能ID",required = true)String orgFunction,
                                                               @NotBlank @RequestParam(value = "appId",required = false) @ApiParam(value = "系统AppId",required = false)String appId){
    List<SysOrg> orgInfoByOrgFunction = sysOrgService.getOrgInfoByOrgFunction(orgFunction, appId);
    return Result.ok(orgInfoByOrgFunction);
  }

  @ApiOperation(value = "添加组织树节点",notes = "权限点:(sys:org:save)")
  @PostMapping("/save")
  public Result<String> saveOrg(@Validated @RequestBody SysOrgForm sysOrgForm, BindingResult br){
    if (br.hasErrors()) {
      return Result.error(getErrMsg(br));
    }

    SysOrg sysOrg = new SysOrg();
    BeanUtils.copyProperties(sysOrgForm, sysOrg);
    sysOrgService.saveOrg(sysOrg, sysOrgForm.getAppIds());
    LogUtil.addLog(LogEnum.LOG_TYPE_ORG_DELETE.getCode(), "新增-" +UserUtil.getLoginUserName()+ "-<机构新增>");
    return Result.ok();
  }

  @PostMapping("/createThirdOrganization")
  @ApiOperation(value = "创建非行政机构",notes = "权限点:(sys:organization:createthird)")
  @ResponseBody
  @SofnLog(value = "创建非行政机构", type = "新增")
  public Result<String> createThirdOrganization(@ApiParam @Validated @RequestBody SysThirdOrgForm sysThirdOrgForm, BindingResult result) {
    if (result.hasErrors()) {
      return Result.error(getErrMsg(result));
    }

    // 判断是否存在相同名称的三方机构
    List<SysOrg> list = sysOrgService.list(new QueryWrapper<SysOrg>()
      .eq("ORGANIZATION_NAME", sysThirdOrgForm.getOrganizationName()));

    String orgId = null;
    if (!CollectionUtils.isEmpty(list)){
      if (list.size() > 1) {
        throw new SofnException("存在多条重复名称的机构");
      }

      if (list.get(0) != null && BoolUtils.isFalse(list.get(0).getThirdOrg())){
        orgId = list.get(0).getId();
      }else {
        throw new SofnException("存在相同名称的非三方机构");
      }
    } else {
      // 转化为MODEL对象
      SysOrg sysOrg = new SysOrg();
      BeanUtils.copyProperties(sysThirdOrgForm, sysOrg);
      sysOrg.setAddressLastCode(sysThirdOrgForm.getAddress());
      sysOrg.setThirdOrg(BoolUtils.N);
      sysOrg.setParentId(SysManageEnum.ROOT_ORG.getCode());

      List<String> appIdList = new ArrayList<>();
      appIdList.add(sysThirdOrgForm.getAppId());
      orgId = sysOrgService.saveOrg(sysOrg, appIdList);
    }
    LogUtil.addLog(LogEnum.LOG_TYPE_ORG_DELETE.getCode(), "新增-" +UserUtil.getLoginUserName()+ "-<机构新增>");
    return Result.ok(orgId,"");
  }

   @ApiOperation(value = "删除机构",notes = "权限点:(sys:org:delete)")
   @PostMapping("/delete")
   @SofnLog(value = "删除机构", type = "删除")
   public Result<String> deleteOrg(@NotBlank @RequestParam("id") String id){
     sysSplitUserOrgService.deleteOrg(id);
     LogUtil.addLog(LogEnum.LOG_TYPE_ORG_DELETE.getCode(), "删除-" +UserUtil.getLoginUserName()+ "-<机构删除>");
     return Result.ok();
   }

  @ApiOperation(value = "根据机构区划和机构级别获取区划下下级级别的机构",notes = "权限码：(sys:org:getOrgByRegionAndLevel)")
  @GetMapping("/getOrgByRegionAndLevel")
  public Result<List<SysOrg>> getOrgByRegionAndLevel(@RequestParam("appId") @ApiParam(value = "子系统AppId",required = true) String appId,
                                                     @RequestParam("region")  @ApiParam(value = "行政区划",required = true) String region,
                                                     @RequestParam("level") @ApiParam(value = "机构级别",required = true) String level){
    List<SysOrg> list = sysOrgService.getOrgByRegionAndLevel(appId, region, SysConstant.getNextOrgLevelCode(level));
    return Result.ok(list);
  }

  @ApiOperation(value = "检查组织机构名称是否重复,返回值 Y:重复 N:不重复",notes = "权限点:(sys:org:checkOrgNameIsExits)")
  @GetMapping("/checkOrgNameIsExits")
  public Result<String> checkOrgNameIsExits(@RequestParam(name="id",required = false) @ApiParam(value = "组织机构ID，用于校验编辑时的名称是否重复") String id,
                                         @RequestParam(name="orgName") @ApiParam(value = "机构名称") String orgName){
    return Result.ok(sysOrgService.checkOrgNameIsExist(orgName,id)?BoolUtils.Y:BoolUtils.N,"");
  }

  @ApiOperation(value = "获取用户能够访问的机构列表，没有做成树结构",notes = "权限点:(sys:org:getAllOrgByLoginUser)")
  @GetMapping("/getAllOrgByLoginUser")
  public Result<List<SysOrgVo>> getAllOrgByLoginUser(@RequestParam(value = "appId",required = false) @ApiParam(value = "系统AppId") String appId){
    if(StringUtils.isBlank(appId)){
      return Result.ok(sysOrgService.getAllOrgListAndLoginUserId(Lists.newArrayList(),true,true));
    }
    return Result.ok(sysOrgService.getAllOrgListAndLoginUserId(Lists.newArrayList(appId),true,true));
  }

  @ApiOperation(value = "根据机构id批量获取机构详情列表",notes = "权限点:(sys:org:getOrgInfoByOrgIds)")
  @PostMapping("/getOrgInfoByOrgIds")
  public Result<List<SysOrgVo>> getOrgInfoByOrgIds(@ApiParam @Validated @RequestBody BatchOrgForm batchOrgForm, BindingResult result){
    if (result.hasErrors()) {
      return Result.error(getErrMsg(result));
    }
    return Result.ok(sysOrgService.listByAppIdAndIds(batchOrgForm.getAppId(),batchOrgForm.getIds()));
  }

  @ApiOperation(value = "查询系统下机构树",notes = "权限点:(sys:org:list)")
  @GetMapping("/listOfTreeByAppId")
  public Result<List<SysOrgVo>> listByParentId(@ApiParam(value = "系统APPID",required = true) @RequestParam(value = "appId") String appId,
                                               @ApiParam(value = "父机构ID") @RequestParam(value = "parentId",required = false) String parentId,
                                               @ApiParam(value = "机构级别") @RequestParam(value = "level",required = false) String level){
    if (StringUtils.isBlank(parentId)) {
      parentId = SysManageEnum.ROOT_ORG.getCode();
    }

    List<SysOrgVo> list = sysOrgService.listTreeByAppId(appId, parentId, level);
    TreeUtil<SysOrgVo> treeUtil = new TreeUtil<>();
    return Result.ok(treeUtil.generate(list));
  }

  @ApiOperation(value = "查询系统下机构列表(非树形)",notes = "权限点:(sys:org:list)")
  @GetMapping("/listByAppId")
  public Result<List<SysOrgVo>> listByAppId(@ApiParam(value = "系统APPID",required = true) @RequestParam(value = "appId") String appId,
                                               @ApiParam(value = "父机构ID") @RequestParam(value = "parentId",required = false) String parentId){
    if (StringUtils.isBlank(parentId)) {
      parentId = SysManageEnum.ROOT_ORG.getCode();
    }

    List<SysOrgVo> list = sysOrgService.listByAppId(appId, parentId);
    return Result.ok(list);
  }

  @DeleteMapping("/batchDelete")
  @ApiOperation(value = "批量删除机构",notes = "权限点:(sys:org:batchDelete)")
  @SofnLog(value = "批量删除机构", type = "删除")
  public Result<String> batchDelete(@ApiParam(value = "待删除ID，用ID隔开") @RequestParam("ids") String ids){
    if(StringUtils.isBlank(ids)){
      return Result.ok("删除成功");
    }
    List<String> idsByStr = IdUtil.getIdsByStr(ids);
    sysSplitUserOrgService.batchDelete(idsByStr);
    LogUtil.addLog(LogEnum.LOG_TYPE_ORG_DELETE.getCode(), "删除-" +UserUtil.getLoginUserName()+ "-<机构删除>");
    return Result.ok("删除成功");
  }


  @GetMapping("/getOrgInfoByAppIdAndFunctionCode/{appId}/{functionCode}")
  @ApiOperation(value = "根据系统代码和职能代码获取机构信息",notes = "权限点:(sys:org:batchDelete)")
  public Result<List<SysOrgVo>> getOrgInfoByAppIdAndFunctionCode(@PathVariable(value = "appId")  @ApiParam(value = "系统代码") String appId,
                                                                 @PathVariable("functionCode")  @ApiParam(value = "机构职能代码，如：nongye...") String functionCode){
    List<SysOrgVo> orgInfoByAppIdAndFunctionCode = sysOrgService.getOrgInfoByAppIdAndFunctionCode(appId, functionCode);
    return Result.ok(orgInfoByAppIdAndFunctionCode);
  }

  @GetMapping("/getOrgLocation")
  @ApiOperation(value = "根据机构ID获取机构的经纬度",notes = "权限点:(sys:org:getOrgLocation)")
  public Result<Map<String,String>> getOrgLocation(@ApiParam(value = "机构ID",required = true) @NotNull @RequestParam("id") String id){
    return Result.ok(sysOrgService.getOrgLocation(id));
  }

    @GetMapping(value = "/getOrgListByRegionCodeAndAppId")
    @ApiOperation(value = "根据区划代码和系统代码获取机构列表", notes = "权限码(sys:user:getUserInfoByRegionCodeAndAppId)")
    @SofnLog(value = "根据区划代码和系统代码获取机构列表")
    public Result<List<SysUserForm>> getOrgListByRegionCodeAndAppId(@ApiParam(value = "区划代码集合", required = true) @RequestParam List<String> regionCodes,
                                                                     @ApiParam(value = "机构级别") @RequestParam(required = false) String orgLevel,
                                                                     @ApiParam(value = "系统AppId", required = true) @RequestParam String appId){
        List<SysOrg> list = sysOrgService.getOrgIdByRegionCodeAndAppId(appId, regionCodes, orgLevel);
        return Result.ok(list);
    }

  @ApiOperation(value="查询系统所有的组织结构列表",notes = "权限点:(sys:org:index)")
  @GetMapping("/getAllSysOgrList")
  //@RequiresPermissions("sys:org:index")
  public Result<List<SysOrgVo>> getAllSysOgrList(@RequestParam(name="appId") @ApiParam(value = "系统appId") String appId,
                                                 @RequestParam(name="dictCode") @ApiParam(value = "组织名称") String dictCode,
                                                 @RequestParam(name="regionCode") @ApiParam(value = "区划ID") List<String> regionCode){
    List<SysOrgVo> treeList = sysOrgService.getAllSysOgrList(appId,dictCode,regionCode);
    return Result.ok(treeList);
  }

  @ApiOperation(value="获取区域下使用系统的用户",notes = "权限点:(sys:org:index)")
  @GetMapping("/getUserUsingApp")
  public Result<List<SysUserForm>> getUserUsingApp(@RequestParam(name="appId") @ApiParam(value = "系统appId") String appId,
                                                   @RequestParam(name="regionCode") @ApiParam(value = "区划ID") List<String> regionCode){
    List<SysUserForm> userList = sysOrgService.getUserUsingApp(appId,regionCode);
    return Result.ok(userList);
  }

}
