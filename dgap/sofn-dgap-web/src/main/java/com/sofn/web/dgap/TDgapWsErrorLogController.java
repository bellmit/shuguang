package com.sofn.web.dgap;

import com.github.pagehelper.PageInfo;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.authorization.annotation.Authorization;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.HttpCode;
import com.sofn.service.dgap.OuterApiService;
import com.sofn.service.dgap.TDgapWsErrorLogMapperService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** */
@RestController
@Api(value = "/wsErrorStat", description = "WS调用出错统计")
@RequestMapping(value = "/wsErrorStat")
public class TDgapWsErrorLogController extends BaseController {
  @Autowired private TDgapWsErrorLogMapperService mapperService;
  @Autowired private OuterApiService outerApiService;

  /**
   * @param
   * @param
   * @param start
   * @param length
   * @param startTime
   * @param endTime
   * @return
   */
  @ApiOperation(
    value = "WS调用出错列表",
    notes = "获取WS调用出错信息的列表",
    responseContainer = "List",
    response = PageInfo.class
  )
  @PostMapping(value = "/list")
  @SystemControllerLog(description = "(共享系统)分页查询WS调用出错信息", operationType = "分页")
  @Authorization
  public Object list(@ApiParam(required = true) String resourceDirectoryId,
      String webServiceName, Date startTime, Date endTime, String methodName, String callerUser, String errorType,
      int start, int length) throws Exception {
    if(webServiceName.length()>50){
      return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);
    }
    if(methodName.length()>20){
      return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);
    }
    if(callerUser.length()>20){
      return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);
    }
    if (webServiceName != null) webServiceName = webServiceName.trim();
    if (methodName != null) methodName = methodName.trim();
    if (callerUser != null) callerUser = callerUser.trim();

    PageInfo<List<Map<String, Object>>> list =
        mapperService.list(resourceDirectoryId, webServiceName, startTime, endTime, methodName,
            callerUser, errorType, ((start + 1) / length) + 1, length);

    return setSuccessModelMap(new ModelMap(), list);
  }

  @ApiOperation(
    value = "WS调用出错类型列表",
    notes = "WS调用出错类型列表",
    responseContainer = "List",
    response = OuterApiService.ListItem.class
  )
  @PostMapping(value = "/listWsErrorTypes")
  @SystemControllerLog(description = "(共享系统)查询WS调用出错类型列表", operationType = "查询")
  public Object listWsErrorTypes() {
    List<OuterApiService.ListItem> list = outerApiService.getWsErrorTypes();
    return setSuccessModelMap(new ModelMap(), list);
  }

  /** @param binder */
  @InitBinder
  public void initBinder(final WebDataBinder binder) {
    final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
  }
}
