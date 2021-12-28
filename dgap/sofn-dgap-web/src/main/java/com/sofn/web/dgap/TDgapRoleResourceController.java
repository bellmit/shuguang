package com.sofn.web.dgap;

import com.github.pagehelper.PageInfo;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.authorization.annotation.Authorization;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.CurrentUser;
import com.sofn.core.constant.HttpCode;
//import com.sofn.core.constant.move.SysRole;
import com.sofn.core.util.StringUtils;
import com.sofn.core.util.WebUtil;
import com.sofn.sys.model.SysRole;
import com.sofn.model.generator.TDgapResource;
import com.sofn.model.generator.TDgapRoleResource;
import com.sofn.service.dgap.OuterApiService;
import com.sofn.service.dgap.TDgapResourceService;
import com.sofn.service.dgap.TDgapRoleResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** Created by Administrator on 2016/10/24. */
@RestController
@Api(value = "资源权限设置", description = "资源权限设置")
@RequestMapping(value = "/resourceRole")
public class TDgapRoleResourceController extends BaseController {
  @Autowired private TDgapRoleResourceService tDgapRoleResourceService;
  @Autowired private TDgapResourceService tDgapResourceService;
  @Autowired private OuterApiService outerApiService;

  /**
   * 分页列表
   *
   * @param
   * @param start
   * @param length
   * @return
   */
  @ApiOperation(value = "资源权限列表")
  @RequestMapping(value = "/list")
  @SystemControllerLog(description = "(共享系统)分页展示资源的权限设置列表", operationType = "分页")
  @Authorization
  public Object list(
      @RequestHeader String token,String resourceDirectoryName,String resourceName,
      String roleId,int start,int length) {

    CurrentUser u = outerApiService.getCurrentUser(token);
    String userId = u.getId();

    List<SysRole> roleList = outerApiService.getRolesOfSameDepartment(userId);

    StringBuilder sb = new StringBuilder();
    for (SysRole Sy : roleList) {
      sb.append("'");
      sb.append(Sy.getId());
      sb.append("'");
      sb.append(",");
    }
    sb.setLength(sb.length() - 1);
    if (resourceName != null && !resourceName.equals("")) {
      resourceName = resourceName.trim();
    }
    PageInfo<Map<String, Object>> list =
        tDgapRoleResourceService.list(
            resourceDirectoryName,
            resourceName,
            roleId,
            sb.toString(),
            ((start + 1) / length) + 1,
            length);

    return setSuccessModelMap(new ModelMap(), list);
  }

  /*资源管理级联资源目录*/
  @ApiOperation(value = "按名字级联查询资源目录列表")
  @RequestMapping(value = "/relist")
  @SystemControllerLog(description = "(共享系统)按名字查询资源列表", operationType = "查询")
  public Object lists(String name) {
    List<TDgapResource> list = tDgapResourceService.getResourceNameList(name);
    return setSuccessModelMap(new ModelMap(), list);
  }

  @ApiOperation(value = "资源权限设置表不分页作用在下拉列表里面")
  @RequestMapping(value = "/listredir")
  @SystemControllerLog(description = "(共享系统)资源权限设置表不分页作用在下拉列表里面", operationType = "查询")
  public Object redirListAll() {
    List<Map<String, Object>> list = tDgapRoleResourceService.listAll();
    return setSuccessModelMap(new ModelMap(), list);
  }

  /**
   * 获取当前用户下的所在机构下的所有角色
   *
   * @param
   * @return
   */
  @ApiOperation(value = "角色列表")
  @RequestMapping(value = "/rolelist")
  @SystemControllerLog(description = "(共享系统)获取当前用户下的所有角色", operationType = "查询")
  public Object listRoles(
      @RequestHeader String token, ModelMap modelMap, String roleName, String status) {

    status = "1";
    CurrentUser currentUser = WebUtil.getCurrentUser(token);
    if (currentUser == null) {
      return setModelMap(modelMap, HttpCode.UNAUTHORIZED);
    }
    String userId = currentUser.getId();
    List<SysRole> roles = outerApiService.getRolesOfSameDepartment(userId);
    modelMap.addAttribute("data", roles);
    return setSuccessModelMap(modelMap);
  }

  @ApiOperation(value = "资源权限设置新增")
  @RequestMapping(value = "/add")
  @SystemControllerLog(description = "(共享系统)新增资源权限设置", operationType = "新增")
  public Object addResourceRole(@RequestBody TDgapRoleResource roleResource) throws Exception {
    TDgapRoleResource add = null;
    int count = 0;
    // 验证数据是否重复
    String roleId = roleResource.getRoleId();
    String resourceId = roleResource.getResourceId();
    List<TDgapRoleResource> list = tDgapRoleResourceService.checkRepetition(roleId);
    if (list.size() != 0) {
      for (TDgapRoleResource resource : list) {
        String resourceResourceId = resource.getResourceId();

        if (resourceId.equals(resourceResourceId)) {
          count = count + 1;
        }
      }
      if (count >= 1) {
        return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);
      } else {
        add = tDgapRoleResourceService.add(roleResource);
        return setSuccessModelMap(new ModelMap(), add);
      }
    } else {
      add = tDgapRoleResourceService.add(roleResource);
      return setSuccessModelMap(new ModelMap(), add);
    }
  }

  @ApiOperation(value = "根据id查一条资源权限")
  @RequestMapping("/findById")
  @SystemControllerLog(description = "(共享系统)根据id查一条资源权限", operationType = "查询")
  public Object getResourceById(@RequestBody TDgapRoleResource roleResource) {
    TDgapRoleResource tDgapRoleResource = tDgapRoleResourceService.queryById(roleResource.getId());
    if (tDgapRoleResource != null) {
      return setSuccessModelMap(new ModelMap(), tDgapRoleResource);
    } else {
      return setModelMap(new ModelMap(), HttpCode.NOT_FOUND);
    }
  }

  /**
   * 修改资源权限设置
   *
   * @param resourceDir
   * @return
   */
  @ApiOperation(value = "修改资源权限设置")
  @RequestMapping(value = "/update")
  @SystemControllerLog(description = "(共享系统)修改资源权限设置", operationType = "修改")
  public Object updateResourceRole(@RequestBody TDgapRoleResource resourceDir) {
    if (StringUtils.isNotBlank(resourceDir.getId())) {

      String roleId = resourceDir.getRoleId();
      String resourceId = resourceDir.getResourceId();
      int count = 0;
      List<TDgapRoleResource> list = tDgapRoleResourceService.checkRepetition(roleId);
      if (list.size() != 0) {
        for (TDgapRoleResource resource : list) {
          String resourceResourceId = resource.getResourceId();
          if (resourceId.equals(resourceResourceId)) {
            count = count + 1;
          }
        }
        if (count >= 1) {
          return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);
        }
      }

      // 需要查询出原有数据,再更新最新数据
      TDgapRoleResource oldResource = tDgapRoleResourceService.queryById(resourceDir.getId());
      if (oldResource != null) {
        oldResource.setRoleId(resourceDir.getRoleId());
        oldResource.setResourceId(resourceDir.getResourceId());
        tDgapRoleResourceService.update(oldResource);
      } else {
        return setModelMap(new ModelMap(), HttpCode.FORBIDDEN);
      }
    }
    return setSuccessModelMap(new ModelMap(), HttpCode.OK);
  }

  /**
   * 删除资源权限设置
   *
   * @param id
   * @return
   */
  @ApiOperation(value = "删除资源权限设置")
  @RequestMapping(value = "/delete")
  @SystemControllerLog(description = "(共享系统)删除资源权限设置", operationType = "删除")
  public Object deleteResourceDir(String id) {
    TDgapRoleResource tDgapRoleResource = tDgapRoleResourceService.queryById(id);
    if (tDgapRoleResource != null) {
      tDgapRoleResource.setDelFlag("Y");
      tDgapRoleResourceService.update(tDgapRoleResource);
      return setModelMap(new ModelMap(), HttpCode.OK);
    } else {
      return setModelMap(new ModelMap(), HttpCode.FORBIDDEN);
    }
  }

  /**
   * 批量删除资源
   *
   * @return
   */
  @ApiOperation(value = "批量删除资源权限设置")
  @RequestMapping(value = "/deletes")
  @SystemControllerLog(description = "(共享系统)批量删除资源权限设置", operationType = "删除")
  public Object deleteResourceRoles(String dataInfo) {
    String[] jsonStr = dataInfo.split("\\|");
    ModelMap modelMap = new ModelMap();
    for (int i = 0; i < jsonStr.length; i++) {
      logger.debug(jsonStr[i] + "  ");
      String oStr = jsonStr[i].toString();
      if (oStr == null || oStr.equals("")) {
        continue;
      }
      TDgapRoleResource tDgapResourceRole = tDgapRoleResourceService.queryById(oStr);
      tDgapResourceRole.setDelFlag("Y");
      tDgapRoleResourceService.update(tDgapResourceRole);
    }
    return setSuccessModelMap(modelMap);
  }
}
