package com.sofn.web.dgap;

import com.github.pagehelper.PageInfo;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.authorization.annotation.Authorization;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.ApiConstants;
import com.sofn.core.constant.ApiMsgConstants;
import com.sofn.core.constant.HttpCode;
import com.sofn.core.util.WebUtil;
import com.sofn.model.generator.TDgapLocalPlatFormAppli;
import com.sofn.service.dgap.TDgapLocalAuditService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "地方平台审核管理", description = "地方平台审核管理")
@RequestMapping(value = "/paltFormAudit")
public class TDgapLocalAuditController extends BaseController {
  @Autowired private TDgapLocalAuditService dgapLocalPlatFormAuditService;

  /**
   * @param platFormName
   * @param platFormLevel
   * @param platFormAreaId
   * @param status
   * @param dateBegin
   * @param dateEnd
   * @param start
   * @param length
   * @param token
   * @return
   */
  @ApiOperation(value = "地方平台对接申请")
  @RequestMapping(value = "/list")
  @SystemControllerLog(description = "(共享系统)分页显示地方平台对接申请", operationType = "分页")
  @Authorization
  public Object list(
      String platFormName, String platFormLevel, String platFormAreaId, String status, String enableStatus,
      String dateBegin, String dateEnd, int start, int length, String token) {
    String userName = WebUtil.getCurrentUserName(token);
    PageInfo<Map<String, Object>> list =
        dgapLocalPlatFormAuditService.list(platFormName, platFormLevel, platFormAreaId,enableStatus,
            status, userName, dateBegin, dateEnd, ((start + 1) / length) + 1, length);
    return setSuccessModelMap(new ModelMap(), list);
  }

  /**
   * 审核地方平台对接申请
   *
   * @param localPlatFormAppli
   * @return
   */
  @ApiOperation(value = "审核地方平台对接申请")
  @RequestMapping(value = "/formAudit")
  @SystemControllerLog(description = "(共享系统)审核地方平台对接申请", operationType = "修改")
  public Object formAudit(
      @RequestBody TDgapLocalPlatFormAppli localPlatFormAppli, @RequestHeader String token) {
    Map<String, Object> map = new HashMap<String, Object>();
    if (localPlatFormAppli.getAuditComments() != null) {
      dgapLocalPlatFormAuditService.formAudit(localPlatFormAppli, token);
      map.put(ApiConstants.CODE, ApiMsgConstants.SUCCESS_CODE);
      map.put(ApiConstants.MSG, ApiMsgConstants.SUCCESS_MSG);
      return setSuccessModelMap(new ModelMap(), HttpCode.OK);
    } else {
      map.put(ApiConstants.CODE, ApiMsgConstants.FAILED_CODE);
      map.put(ApiConstants.MSG, ApiMsgConstants.FAILED_MSG);
      return setFailModelMap(new ModelMap(), HttpCode.BAD_REQUEST);
    }
  }
  /**
   * 启用禁用
   *
   * @param id
   * @return
   */
  @ApiOperation(value = "启用禁用")
  @RequestMapping(value = "/enablePart")
  @SystemControllerLog(description = "(共享系统)启用禁用", operationType = "修改")
  public Object enablePart(String id, String enableStatus) {
    TDgapLocalPlatFormAppli tDgapLocalPlatFormAppli = dgapLocalPlatFormAuditService.queryById(id);
    tDgapLocalPlatFormAppli.setEnableStatus(enableStatus);
    tDgapLocalPlatFormAppli.setUpdateTime(new Date());
    dgapLocalPlatFormAuditService.update(tDgapLocalPlatFormAppli);
    return setModelMap(new ModelMap(), HttpCode.OK);
  }
}
