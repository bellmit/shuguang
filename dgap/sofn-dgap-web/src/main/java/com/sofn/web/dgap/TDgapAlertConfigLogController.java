package com.sofn.web.dgap;

import com.github.pagehelper.PageInfo;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.authorization.annotation.Authorization;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.HttpCode;
import com.sofn.service.dgap.TDgapAlertConfigLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** Created by Administrator on 2016/10/13. */
@RestController
@Api(value = "预警记录管理", description = "预警记录管理")
@RequestMapping(value = "/alertLog")
public class TDgapAlertConfigLogController extends BaseController {
  @Autowired private TDgapAlertConfigLogService logService;

  /**
   * 分页列表
   *
   * @param start
   * @param length
   * @return
   */
  @SystemControllerLog(description = "(共享系统)预警记录管理列表", operationType = "分页")
  @ApiOperation(value = "预警记录管理列表")
  @RequestMapping(value = "/list")
  @Authorization
  public Object list(
      String alertConfigId, String targetId, String alertDate,  String solveDate,String solveStatus,int start, int length) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date date1 = null;
    Date date2 = null;
    String solveStatus2=null;
    try {
      if (alertDate != null && !"".equals(alertDate)) {
        date1 = dateFormat.parse(alertDate);
      }
      if (solveDate != null && !"".equals(solveDate)) {
        date2 = dateFormat.parse(solveDate);
      }
    } catch (ParseException p) {
      p.printStackTrace();
    }
    if(solveStatus.equals("0")){
      solveStatus=null;
      solveStatus2="0";
    }

    PageInfo<Map<String, Object>> list =
        logService.list(alertConfigId, targetId, date1, date2,solveStatus,solveStatus2,((start + 1) / length) + 1, length);
    return setSuccessModelMap(new ModelMap(), list);
  }

  @ApiOperation(value = "处理预警记录")
  @RequestMapping(value = "/checkStatus")
  @SystemControllerLog(description = "(共享系统)处理预警记录", operationType = "处理预警记录")
  public Object checkStatus(String id) {
    logService.checkStatus(id.toString());
    return setModelMap(new ModelMap(), HttpCode.OK);
  }
}
