package com.sofn.web.dgap;

import com.github.pagehelper.PageInfo;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.authorization.annotation.Authorization;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.CurrentUser;
import com.sofn.core.constant.HttpCode;
import com.sofn.core.util.StringUtils;
import com.sofn.model.generator.TDgapResource;
import com.sofn.model.generator.TDgapResourceApplication;
import com.sofn.service.dgap.OuterApiService;
import com.sofn.service.dgap.TDgapResourceApplicationService;
import com.sofn.service.dgap.TDgapResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DocumentEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/** Created by Administrator on 2016/10/27. */
@RestController
@Api(value = "资源申请", description = "资源申请")
@RequestMapping("/resourceApplication")
public class TDgapResourceApplicationController extends BaseController {
  @Autowired private TDgapResourceApplicationService applicationService;
  @Autowired private OuterApiService outerApiService;
  @Autowired private TDgapResourceService tDgapResourceService;


  @ApiOperation(value = "资源审批列表")
  @RequestMapping(value = "/lists")
  @SystemControllerLog(description = "(共享系统)资源审批列表", operationType = "查询")
  @Authorization
  public Object lists(
      @RequestHeader String token, String resourceType, String userName, String resourceName, int start, int length)
      throws Throwable {
    if(resourceName.length()>60){
      return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);
    }
    if(userName.length()>60){
      return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);
    }
    // 获取当前登录用户信息
    CurrentUser currentUser = outerApiService.getCurrentUser(token);
    // 获取当前登录用户id
    String loginedUserId = currentUser.getId();
    PageInfo<Map<String, Object>> list = null;

    try {
      // 获取当前登录用户的角色id
      String roleIds = "";
      List<String> roles = outerApiService.getRoleByUserId(currentUser.getId());
      for(String role:roles){
        roleIds+=role+"|";
      }
      String[] ids = roleIds.split("\\|");
      StringBuilder sb = new StringBuilder();
      for (String s : ids) {
        sb.append("'").append(s).append("'").append(",");
      }
      sb.setLength(sb.length() - 1);
      if (roleIds != null) {
        list = applicationService.list(resourceType, userName.trim(), loginedUserId,
                    resourceName.trim(), sb.toString(), ((start + 1) / length) + 1, length);
      } else {
      }
    } catch (Exception e) {
      e.printStackTrace();
      if (e instanceof InvocationTargetException) {
        throw ((InvocationTargetException) e).getTargetException();
      }
    }
    return setSuccessModelMap(new ModelMap(), list);
  }

  @ApiOperation(value = "所有资源审批详情列表")
  @RequestMapping(value = "/listDetails")
  @SystemControllerLog(description = "(共享系统)所有资源审批详情列表", operationType = "查询")
  @Authorization
  public Object listDetails(
          @RequestHeader String token, String resourceType, String userName, String resourceName, int start, int length){
    PageInfo<Map<String, Object>> list = applicationService.listDetails(resourceType, userName.trim(),
            resourceName.trim(), ((start + 1) / length) + 1, length);
    return setSuccessModelMap(new ModelMap(), list);
  }

  @ApiOperation(value = "根据id查一条资源详情")
  @RequestMapping("/getDetails")
  @SystemControllerLog(description = "(共享系统)描述信息", operationType = "查询")
  public Object getDetails(@RequestBody TDgapResourceApplication tDgapResourceApplication) {
    Map<String,Object> queryById =
            applicationService.getResourceApplicationDetails(tDgapResourceApplication.getId());
    return setModelMap(new ModelMap(), HttpCode.OK, queryById);
  }


  @ApiOperation(value = "资源申请列表")
  @RequestMapping("/list")
  @SystemControllerLog(description = "(共享系统)资源申请列表", operationType = "查询")
  @Authorization
  public Object list(@RequestHeader String token, String name, String type, int start, int length) {
    if(name.length()>60){
      return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);
    }
    CurrentUser currentUser = outerApiService.getCurrentUser(token);
    String userId = currentUser.getId();
    PageInfo<Map<String, Object>> list =
        applicationService.pageList(userId, name.trim(), type, ((start + 1) / length) + 1, length);
    return setSuccessModelMap(new ModelMap(), list);
  }

  @ApiOperation(value = "资源列表")
  @RequestMapping("/resourceList")
  @SystemControllerLog(description = "(共享系统)资源列表", operationType = "查询")
  public Object getResource() {
    List<TDgapResource> tDgapResources = applicationService.getResource();
    return setSuccessModelMap(new ModelMap(), tDgapResources);
  }

  @InitBinder
  public void initBinder(final WebDataBinder binder) {
    final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
  }

  @SystemControllerLog(description = "(共享系统)审批资源", operationType = "审批")
  @ApiOperation(value = "资源审批通过还是不通过")
  @RequestMapping(value = "/applicationdataUpdate")
  public Object updateResourceDir(
      @RequestHeader String token, @RequestBody TDgapResourceApplication resourceApp) {
    if(resourceApp.getApproval().length()>25){
      return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);
    }
    if (StringUtils.isNotBlank(resourceApp.getId())) {
      // 需要查询出原有数据,再更新最新数据
      TDgapResourceApplication oldResource = applicationService.queryById(resourceApp.getId());
      if (oldResource != null) {
        oldResource.setApproval(resourceApp.getApproval());
        oldResource.setPassOrNot(resourceApp.getPassOrNot());
        if (resourceApp.getPassOrNot().equals("Y")) {
          List<String> strings = applicationService.queryCallerToken();
          String callerToken = null;
          do {
            callerToken = createCallerToken(10);
          } while (strings.contains(callerToken));
          oldResource.setCallerToken(callerToken);
        } else {
          oldResource.setCallerToken("");
        }
        /*接收审批人ID*/
        oldResource.setAuditUserId(outerApiService.getLoginedUserId(token));
        oldResource.setAuditTime(new Date());
        resourceApp = oldResource;
        applicationService.update(resourceApp);
      } else {
        return setModelMap(new ModelMap(), HttpCode.FORBIDDEN);
      }
    }
    return setSuccessModelMap(new ModelMap(), HttpCode.OK);
  }

  @ApiOperation(value = "新增申请")
  @RequestMapping("/add")
  @SystemControllerLog(description = "(共享系统)描述信息", operationType = "添加")
  public Object addResource(
      @RequestBody TDgapResourceApplication tDgapResourceApplication, @RequestHeader String token) {
    if(tDgapResourceApplication.getReason()==null||tDgapResourceApplication.getReason().length()>100){
      return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);
    }
    if(tDgapResourceApplication.getEffectiveDate()==null){
      return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);
    }
    TDgapResourceApplication resourceApplication = null;
    try {
      CurrentUser currentUser = outerApiService.getCurrentUser(token);
      tDgapResourceApplication.setUserId(currentUser.getId());
      tDgapResourceApplication.setUserName(currentUser.getUserName());
      tDgapResourceApplication.setStatus("NEW");
      tDgapResourceApplication.setDelFlag("N");
      tDgapResourceApplication.setCreateBy(currentUser.getId());
      tDgapResourceApplication.setUpdateBy(currentUser.getId());
      resourceApplication = applicationService.addResourceApplication(tDgapResourceApplication);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (resourceApplication == null) {
        return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR, "保存失败");
      } else {
        return setModelMap(new ModelMap(), HttpCode.OK, "保存成功");
      }
    }
  }

  @ApiOperation(value = "根据id查一条资源记录")
  @RequestMapping("/resourceById")
  @SystemControllerLog(description = "(共享系统)描述信息", operationType = "查询")
  public Object getResourceById(@RequestBody TDgapResourceApplication tDgapResourceApplication) {
    Map<String, Object> resourceById =
        applicationService.getResourceById(tDgapResourceApplication.getId());
    String resourceId = resourceById.get("RID").toString();
    TDgapResource tDgapResourceById =
        tDgapResourceService.getResourceDelFlagByResourceId(resourceId);
    String delFlag = tDgapResourceById.getDelFlag();
    TDgapResourceApplication queryById =
        applicationService.queryById(tDgapResourceApplication.getId());
    if (queryById.getPassOrNot() != null) {
      return setModelMap(new ModelMap(), HttpCode.LOCKED);
    } else {
      if (delFlag.equals("N")) {
        List list = new ArrayList();
        Map map = new HashMap();
        map.put("error", "");
        list.add(map);
        list.add(resourceById);
        return setModelMap(new ModelMap(), HttpCode.OK, list);
      } else {
        List list = new ArrayList();
        Map map = new HashMap();
        map.put("error", "资源已删，请重新选择");
        list.add(map);
        list.add(resourceById);
        return setModelMap(new ModelMap(), HttpCode.OK, list);
      }
    }
  }

  @ApiOperation(value = "修改资源申请")
  @RequestMapping("/update")
  @SystemControllerLog(description = "(共享系统)描述信息", operationType = "修改")
  public Object updateApply(
      @RequestBody TDgapResourceApplication tDgapResourceApplication, @RequestHeader String token) {
    if(tDgapResourceApplication.getReason().length()>100){
      return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);
    }
    ModelMap modelMap = new ModelMap();
    CurrentUser currentUser = outerApiService.getCurrentUser(token);
    tDgapResourceApplication.setUserId(currentUser.getId());
    tDgapResourceApplication.setUserName(currentUser.getUserName());
    tDgapResourceApplication.setStatus("NEW");
    tDgapResourceApplication.setDelFlag("N");
    tDgapResourceApplication.setUpdateBy(currentUser.getId());
    TDgapResourceApplication resourceApplication =
        applicationService.queryById(tDgapResourceApplication.getId());
    tDgapResourceApplication.setCreateBy(resourceApplication.getCreateBy());
    tDgapResourceApplication.setCreateTime(resourceApplication.getCreateTime());
    try {
      applicationService.updateResourceApplication(tDgapResourceApplication);
    } catch (Exception e) {
      return setModelMap(modelMap, HttpCode.INTERNAL_SERVER_ERROR, "修改失败");
    }
    if (!tDgapResourceApplication.equals(resourceApplication)
        && applicationService
            .queryById(tDgapResourceApplication.getId())
            .equals(resourceApplication)) {
      return setModelMap(modelMap, HttpCode.INTERNAL_SERVER_ERROR, "修改失败");
    }
    return setModelMap(modelMap, HttpCode.OK, "修改成功");
  }

  @ApiOperation(value = "删除资源申请")
  @RequestMapping("/delete")
  @SystemControllerLog(description = "(共享系统)描述信息", operationType = "删除")
  public Object deleteApply(String id) {
    TDgapResourceApplication resourceApplication = applicationService.queryById(id);
    if (resourceApplication != null) {
      resourceApplication.setDelFlag("Y");
      applicationService.update(resourceApplication);
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
  @ApiOperation(value = "批量删除资源申请")
  @SystemControllerLog(description = "(共享系统)描述信息", operationType = "删除")
  @RequestMapping(value = "/deleteAll", method = RequestMethod.POST)
  public Object delete(String dataInfo) {
    String[] ids = dataInfo.split("\\|");
    ModelMap modelMap = new ModelMap();
    applicationService.batchDelete(ids);
    return setSuccessModelMap(modelMap);
  }

  /**
   * 批量下载
   *
   * @return
   */
  @ApiOperation(value = "批量下载")
  @RequestMapping(value = "/downSurface", method = RequestMethod.POST)
  public void downSurface(@RequestHeader String token,HttpServletResponse response) {
    // 获取当前登录用户信息
    CurrentUser currentUser = outerApiService.getCurrentUser(token);
    // 获取当前登录用户id
    String loginedUserId = currentUser.getId();
    byte[] data=applicationService.batchDown(loginedUserId);//需要下载的开发包
    String fileName="开发包.zip";
    OutputStream out = null;
    try {
      ByteArrayInputStream bais = new ByteArrayInputStream(data);
      POIFSFileSystem poifs = new POIFSFileSystem();
      response.setContentType("application/x-download ");
      response.setHeader("Connection", "close"); // 表示不能用浏览器直接打开
      response.setHeader("Accept-Ranges", "bytes");// 告诉客户端允许断点续传localhost连接下载
      response.setHeader("Content-Disposition",
              "attachment;filename=" + new String(fileName.getBytes("GB2312"), "ISO8859-1"));
      response.setCharacterEncoding("UTF-8");
      DirectoryEntry directory = poifs.getRoot();
      DocumentEntry documentEntry = directory.createDocument("WordDocument", bais);
      out = response.getOutputStream();
      poifs.writeFilesystem(out);
      bais.close();
      out.flush();
      out.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @ApiOperation(value = "获取资源类型")
  @RequestMapping(value = "/getResourceType", method = RequestMethod.POST)
  @SystemControllerLog(description = "(共享系统)描述信息", operationType = "查询")
  public Object getResourceType() {
    List<OuterApiService.ListItem> resourceTypes = outerApiService.getResourceTypes();
    return setSuccessModelMap(new ModelMap(), resourceTypes);
  }

  /**
   * 生成授权码
   *
   * @param length
   * @return
   */
  public static String createCallerToken(int length) {
    String val = "";
    Random random = new Random();
    for (int i = 0; i < length; i++) {
      // 输出字母还是数字
      String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
      // 字符串
      if ("char".equalsIgnoreCase(charOrNum)) {
        // 取得大写字母还是小写字母
        int choice = random.nextInt(2) % 2 == 0 ? 65 : 97;
        val += (char) (choice + random.nextInt(26));
      } else if ("num".equalsIgnoreCase(charOrNum)) {
        val += String.valueOf(random.nextInt(10));
      }
    }
    return val;
  }

  @ApiOperation(value = "禁用审批按钮")
  @RequestMapping(value = "/getApplication")
  public Object getApplication(@RequestBody TDgapResourceApplication resourceApplication) {
    TDgapResourceApplication tDgapResourceApplication =
        applicationService.queryById(resourceApplication.getId());
    if (StringUtils.isBlank(tDgapResourceApplication.getPassOrNot())) {
      return setSuccessModelMap(new ModelMap(), tDgapResourceApplication);
    }
    return setModelMap(new ModelMap(), HttpCode.LOCKED);
  }

  /** 资源申请的审批状态 */
  @ApiOperation(value = "资源申请的审批状态")
  @RequestMapping(value = "/getResourceApplicationPassOrNot")
  @SystemControllerLog(description = "(共享系统)描述信息", operationType = "查询")
  public Object getResourceApplicationPassOrNot() {
    List<OuterApiService.ListItem> list = outerApiService.ResourceApplicationPassOrNot();
    return setSuccessModelMap(new ModelMap(), list);
  }
}
