package com.sofn.web.dgap;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.authorization.annotation.Authorization;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.CurrentUser;
import com.sofn.core.constant.HttpCode;
import com.sofn.core.util.WebUtil;
import com.sofn.model.generator.TDgapAlertConfig;
import com.sofn.model.generator.TDgapCommon;
import com.sofn.model.generator.TDgapResource;
import com.sofn.model.generator.TDgapResourceDirectory;
import com.sofn.service.dgap.OuterApiService;
import com.sofn.service.dgap.TDgapAlertConfigService;
import com.sofn.service.dgap.TDgapCommonService;
import com.sofn.service.dgap.TDgapResourceDirectoryService;
import com.sofn.sys.model.dgap.SysUserBean;
import com.sofn.sys.persistence.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/** Created by Administrator on 2016/10/13. */
@RestController
@Api(value = "告警配置信息管理", description = "告警配置信息管理")
@RequestMapping(value = "/alertConfig")
public class TDgapAlertConfigController extends BaseController {
  @Autowired private TDgapAlertConfigService alertConfigService;
  @Autowired private TDgapResourceDirectoryService directoryService;
  @Autowired private OuterApiService apiService;
  @Autowired private TDgapCommonService commonService;

  /**
   * 分页列表
   *
   * @param start
   * @param length
   * @return
   */
  @ApiOperation(value = "告警配置信息列表")
  @RequestMapping(value = "/list")
  @SystemControllerLog(description = "(共享系统)告警配置信息展示列表", operationType = "分页")
  @Authorization
  public Object list(String alertType, String targetId, int start, int length) {
    PageInfo<Map<String, Object>> list =
        alertConfigService.list(alertType, targetId, ((start + 1) / length) + 1, length);
    System.nanoTime();
    return setSuccessModelMap(new ModelMap(), list);
  }

  /**
   * 新增告警配置信息
   *
   * @param json
   * @return
   */
  @ApiOperation(value = "新增告警配置信息")
  @RequestMapping(value = "/add")
  @SystemControllerLog(description = "(共享系统)新增告警配置信息", operationType = "新增")
  public Object addAlert(@RequestBody String json, @RequestHeader String token) {
    Object adds = null;
    int count = 0;

    JSONObject jsonObject = JSONArray.parseObject(json);
    TDgapCommon common = jsonObject.getObject("common", TDgapCommon.class);
    if(common.getDescription()==null||common.getDescription().length()>100){
      return  setModelMap(new ModelMap(),HttpCode.BAD_REQUEST);
    }
    if (common == null) {
      return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);
    } else {
      // 验证数据重复
      String targetId = common.getTargetId();
      List<TDgapCommon> commons = commonService.checkRepeat(targetId,"");
      if (commons.size() != 0) {
        for (TDgapCommon tDgapCommon : commons) {
          BigDecimal oldthreshold = tDgapCommon.getThreshold();
          String oldalertType = tDgapCommon.getAlertType();
          String olddescription = tDgapCommon.getDescription();

          if (common.getDescription().equals(olddescription)
              && common.getThreshold().equals(oldthreshold)
              && common.getAlertType().equals(oldalertType)) {
            count = count + 1;
          }
        }
        if (count >= 1) {
          return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);
        } else {
          adds = commonService.adds(json, token);
          if (adds == null) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
          } else {
            return setModelMap(new ModelMap(), HttpCode.OK, "保存成功");
          }
        }
      } else {
        adds = commonService.adds(json, token);
        if (adds == null) {
          return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        } else {
          return setModelMap(new ModelMap(), HttpCode.OK, "保存成功");
        }
      }
    }
  }

  /**
   * 修改告警配置信息
   *
   * @param json
   * @return
   */
  @SystemControllerLog(description = "(共享系统)修改告警配置信息", operationType = "修改")
  @ApiOperation(value = "修改告警配置信息")
  @RequestMapping(value = "/update")
  public Object updateAlert(@RequestBody String json, @RequestHeader String token) {
    Object updates = null;
    int count = 0;

    JSONObject jsonObject = JSONArray.parseObject(json);
    TDgapCommon common = jsonObject.getObject("common", TDgapCommon.class);
    if (common == null) {
      return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);
    } else {
      // 验证数据重复
      String targetId = common.getTargetId();
      List<TDgapCommon> commons = commonService.checkRepeat(targetId,common.getId());
      if (commons.size() != 0) {
        for (TDgapCommon tDgapCommon : commons) {
          BigDecimal oldthreshold = tDgapCommon.getThreshold();
          String oldalertType = tDgapCommon.getAlertType();
          String olddescription = tDgapCommon.getDescription();
          if (common.getDescription().equals(olddescription)
                  && common.getThreshold().equals(oldthreshold)
                  && common.getAlertType().equals(oldalertType)) {
            count = count + 1;
          }
        }
        if (count >= 1) {
          return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);
        } else {
          updates = commonService.updates(json, token);
          if (updates == null) {
            return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
          } else {
            return setModelMap(new ModelMap(), HttpCode.OK, "保存成功");
          }
        }
      } else {
        updates = commonService.updates(json, token);
        if (updates == null) {
          return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
        } else {
          return setModelMap(new ModelMap(), HttpCode.OK, "保存成功");
        }
      }
    }
  }

  /**
   * 删除告警配置信息
   *
   * @param id
   * @return
   */
  @SystemControllerLog(description = "(共享系统)删除告警配置信息", operationType = "删除")
  @ApiOperation(value = "删除告警配置信息")
  @RequestMapping(value = "/delete")
  public Object deleteAlert(String id) {
    TDgapAlertConfig TDgapAlertConfig = alertConfigService.queryById(id);
    if (TDgapAlertConfig != null) {
      TDgapAlertConfig.setDelFlag("Y");
      alertConfigService.update(TDgapAlertConfig);
      return setModelMap(new ModelMap(), HttpCode.OK, "删除成功");
    } else {
      return setModelMap(new ModelMap(), HttpCode.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * 批量删除告警配置信息
   *
   * @return
   */
  @SystemControllerLog(description = "(共享系统)批量删除告警配置信息", operationType = "删除")
  @ApiOperation(value = "批量删除告警配置信息")
  @RequestMapping(value = "/deletes", method = RequestMethod.POST)
  public Object delete(String dataInfo) {
    String[] ids = dataInfo.split("\\|");
    ModelMap modelMap = new ModelMap();
    alertConfigService.batchDelete(ids);
    return setSuccessModelMap(modelMap);
  }

  /**
   * 前台数据回显
   *
   * @param
   * @return
   */
  @SystemControllerLog(description = "(共享系统)数据回显", operationType = "修改")
  @ApiOperation(value = "根据id查询告警配置信息")
  @RequestMapping(value = "/getAlertById", method = RequestMethod.POST)
  public Object getAlertConfigById(@RequestBody TDgapAlertConfig resource) {
    if (resource.getId() != null) {
      List<Map> alertById = alertConfigService.getAlertById(resource.getId());
      return setSuccessModelMap(new ModelMap(), alertById);
    }
    return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);
  }

  @SystemControllerLog(description = "(共享系统)获取web服务接口名称", operationType = "查询")
  @ApiOperation(value = "获取web服务接口名称")
  @RequestMapping(value = "/getWeb", method = RequestMethod.POST)
  public Object webs(@RequestBody TDgapResource resource) {
    List<TDgapResourceDirectory> list = directoryService.selectTypes(resource.getName());
    return setSuccessModelMap(new ModelMap(), list);
  }

  @SystemControllerLog(description = "(共享系统)获取资源目录名称", operationType = "查询")
  @ApiOperation(value = "获取资源目录名称")
  @RequestMapping(value = "/getType", method = RequestMethod.POST)
  public Object getTypes(String dirName) {
    List<TDgapResourceDirectory> types = directoryService.selectAll(dirName);
    return setSuccessModelMap(new ModelMap(), types);
  }

  @SystemControllerLog(description = "(共享系统)获取预警接收人", operationType = "新增")
  @ApiOperation(value = "获取预警接收人")
  @RequestMapping(value = "/getUser", method = RequestMethod.POST)
  public Object getUsers(
      ModelMap modelMap,
      Long draw,
      Long start,
      Long length,
      String status,
      @RequestHeader(value = "token", defaultValue = "") String token)
      throws Throwable {
    status = "1";
    // 获取当前登录用户
    CurrentUser u = WebUtil.getCurrentUser(token);
    // 未登录
    if (u == null) {
      return setModelMap(modelMap, HttpCode.UNAUTHORIZED);
    }
    String userId = u.getId();

    int count = apiService.getUserCount(status, userId);
    Page page = new Page();
    if (draw == 0) {
      draw = 1L;
    }
    if (start == 0) {
      start = 0L;
    }
    if (length == 0) {
      length = 10L;
    }
    page.setDraw(draw);
    page.setStart(start);
    page.setLength(length);
    page.setRecordsTotal(count);
    // 分页计算
    page.doPage();
    List<SysUserBean> beans = null;
    // 如果总数为0就不再查数据，减小数据库压力
    if (count > 0) {
      beans = apiService.getUsersOfSameOrganization(page, status, userId);
    } else {
      beans = new ArrayList<SysUserBean>();
    }
    modelMap.addAttribute("page", page);
    modelMap.addAttribute("data", beans);
    return setSuccessModelMap(modelMap);
  }
}
