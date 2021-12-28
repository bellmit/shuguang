package com.sofn.web.dgap;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.authorization.annotation.Authorization;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.*;
import com.sofn.core.util.WebUtil;
import com.sofn.model.dgap.Constants;
import com.sofn.model.generator.TDgapLocalPlatFormAppli;
import com.sofn.model.generator.TDgapResource;
import com.sofn.model.generator.TDgapResourceApplication;
import com.sofn.service.dgap.TDgapLocalPlatFormAppliService;
import com.sofn.service.dgap.TDgapResourceApplicationService;
import com.sofn.service.dgap.TDgapResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "地方平台申请管理", description = "地方平台申请管理")
@RequestMapping(value = "/localPlatForm")
public class TDgapLocalPlatFormAppliController extends BaseController {
  @Autowired private TDgapLocalPlatFormAppliService dgapLocalPlatFormAppliService;
  @Autowired private TDgapResourceApplicationService applicationService;
  @Autowired
  private TDgapResourceService resourceService;
  /**
   * @param status
   * @param dateBegin
   * @param dateEnd
   * @param start
   * @param length
   * @return
   */
  @ApiOperation(value = "地方平台对接申请")
  @RequestMapping(value = "/list")
  @SystemControllerLog(description = "(共享系统)分页显示地方平台对接申请", operationType = "分页")
  @Authorization
  public Object list(
      String status,String enableStatus, String dateBegin, String dateEnd, int start, int length, String token) {
    String userName = WebUtil.getCurrentUserName(token);
    PageInfo<Map<String, Object>> list =
        dgapLocalPlatFormAppliService.list(
            status,enableStatus, userName, dateBegin, dateEnd, ((start + 1) / length) + 1, length);
    return setSuccessModelMap(new ModelMap(), list);
  }

  /**
   * 获取登录用户
   *
   * @param token
   * @return
   */
  @ApiOperation(value = "根据登录用户获取用户所属机构信息")
  @SystemControllerLog(description = "(共享系统)根据登录用户获取用户所属机构信息", operationType = "查询")
  @Authorization
  @RequestMapping(value = "/getOrgbyToken")
  public Object getOrgbyToken(@RequestHeader String token) {
    Organization o = dgapLocalPlatFormAppliService.getOrganizationByToken(token);
    ModelMap modelMap = new ModelMap();
    modelMap.addAttribute("data", o);
    return setSuccessModelMap(modelMap);
  }

  @ApiOperation(value = "地方平台修改数据回显根据id查询")
  @RequestMapping(value = "/queryById")
  @SystemControllerLog(description = "(共享系统)根据id查询", operationType = "查询")
  public Object updateList(@RequestBody TDgapLocalPlatFormAppli localPlatFormAppli) {
    TDgapLocalPlatFormAppli tDgap =
        dgapLocalPlatFormAppliService.queryById(localPlatFormAppli.getId());
    return setSuccessModelMap(new ModelMap(), tDgap);
  }

  @ApiOperation(value = "地方平台申请新增")
  @RequestMapping(value = "/add")
  @SystemControllerLog(description = "(共享系统)地方平台申请新增", operationType = "新增")
  public Map<String, Object> addResourceDirectory(
      @RequestBody TDgapLocalPlatFormAppli localPlatFormAppli, @RequestHeader String token) {

    Map<String, Object> map = new HashMap<String, Object>();
    if(localPlatFormAppli.getPlatFormName()==null||localPlatFormAppli.getPlatFormName().length()>32){
      map.put(ApiConstants.CODE, ApiMsgConstants.FAILED_CODE);
      map.put(ApiConstants.MSG, ApiMsgConstants.FAILED_MSG);
    }
    if(localPlatFormAppli.getNote()==null||localPlatFormAppli.getNote().length()>200){
      map.put(ApiConstants.CODE, ApiMsgConstants.FAILED_CODE);
      map.put(ApiConstants.MSG, ApiMsgConstants.FAILED_MSG);
    }
    if (localPlatFormAppli.getPlatFormLevel() == null
        || localPlatFormAppli.getPlatFormLevel().equals("")) {
      map.put(ApiConstants.CODE, ApiMsgConstants.FAILED_CODE);
      map.put(ApiConstants.MSG, ApiMsgConstants.FAILED_MSG);
    } else {
      dgapLocalPlatFormAppliService.addLocalPlatFormAppli(localPlatFormAppli, token);
      map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
      map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
    }

    return map;
  }
  /**
   * 修改地方平台对接申请
   *
   * @param localPlatFormAppli
   * @return
   */
  @ApiOperation(value = "修改地方平台对接申请")
  @RequestMapping(value = "/update")
  @SystemControllerLog(description = "(共享系统)修改地方平台对接申请", operationType = "修改")
  public Map<String, Object> updateResourceDir(
      @RequestBody TDgapLocalPlatFormAppli localPlatFormAppli, @RequestHeader String token) {
    Map<String, Object> map = new HashMap<String, Object>();
    if(localPlatFormAppli.getPlatFormName().length()>32){
      map.put(ApiConstants.CODE, ApiMsgConstants.FAILED_CODE);
      map.put(ApiConstants.MSG, ApiMsgConstants.FAILED_MSG);
    }
    if(localPlatFormAppli.getNote().length()>200){
      map.put(ApiConstants.CODE, ApiMsgConstants.FAILED_CODE);
      map.put(ApiConstants.MSG, ApiMsgConstants.FAILED_MSG);
    }
    dgapLocalPlatFormAppliService.addLocalPlatFormAppli(localPlatFormAppli, token);
    map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
    map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
    return map;
  }
  /**
   * 删除申请
   *
   * @param id
   * @return
   */
  @ApiOperation(value = "删除申请")
  @RequestMapping(value = "/delete")
  @SystemControllerLog(description = "(共享系统)删除申请", operationType = "删除")
  public Object deleteLocalPlatFormAppli(String id) {
    dgapLocalPlatFormAppliService.delete(id.toString());
    return setModelMap(new ModelMap(), HttpCode.OK);
  }

  /**
   * 提交申请
   *
   * @return
   */
  @ApiOperation(value = "批量提交申请")
  @RequestMapping(value = "/subApply")
  @SystemControllerLog(description = "(共享系统)批量提交申请", operationType = "修改")
  public Object subApply(String jsonString, @RequestHeader String token) {
    ModelMap modelMap = new ModelMap();
    JSONArray a = JSONArray.parseArray(jsonString);
    //獲取資源目录下的资源
    List<TDgapResource> resources = resourceService.getResourceByDirId(Constants.RESOURCE_DIR_ID_DFZSPTDJ);

    for (Object id : a) {
      TDgapLocalPlatFormAppli localPlatFormAppli =
          dgapLocalPlatFormAppliService.queryById(id.toString());
      if (!localPlatFormAppli.getStatus().equals("NEW")) {
        return setModelMap(modelMap, HttpCode.CONFLICT, "您提交的申请中有记录已提交过");
      }
      localPlatFormAppli.setAppliCationTime(new Date());
      localPlatFormAppli.setStatus("APPLICATED");
      dgapLocalPlatFormAppliService.update(localPlatFormAppli);
       for(TDgapResource resource:resources){
         TDgapResourceApplication tDgapResourceApplication = new TDgapResourceApplication();
         CurrentUser currentUser = WebUtil.getCurrentUser(token);
         tDgapResourceApplication.setUserId(currentUser.getId());
         tDgapResourceApplication.setUserName(currentUser.getUserName());
         tDgapResourceApplication.setStatus("NEW");
         tDgapResourceApplication.setDelFlag("N");
         tDgapResourceApplication.setCreateBy(currentUser.getId());
         tDgapResourceApplication.setUpdateBy(currentUser.getId());
         tDgapResourceApplication.setResourceId(resource.getId());
         tDgapResourceApplication.setEffectiveDate(new Date());
         tDgapResourceApplication.setReason("程序后台自动申请");
         tDgapResourceApplication.setLocalPlatformAppliId(localPlatFormAppli.getId());
         applicationService.add(tDgapResourceApplication);
       }
    }
    return setSuccessModelMap(modelMap);
  }
}
