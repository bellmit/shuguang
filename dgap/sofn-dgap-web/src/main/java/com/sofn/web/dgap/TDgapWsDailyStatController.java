package com.sofn.web.dgap;

import com.github.pagehelper.PageInfo;
import com.sofn.core.annotation.SystemControllerLog;
import com.sofn.core.authorization.annotation.Authorization;
import com.sofn.core.base.BaseController;
import com.sofn.core.constant.HttpCode;
import com.sofn.model.generator.TDgapWsDailyStat;
import com.sofn.service.dgap.TDgapWsDailyStatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

/** */
@RestController
@Api(value = "/wsDailyStat", description = "WS调用量查询")
@RequestMapping(value = "/wsDailyStat")
public class TDgapWsDailyStatController extends BaseController {
  @Autowired private TDgapWsDailyStatService wsDailyStatService;

  /**
   * @param dailyStat
   * @param start
   * @param length
   * @param startTime
   * @param endTime
   * @return
   */
  @ApiOperation(
    value = "WS调用量查询列表",
    notes = "WS调用量查询列表",
    responseContainer = "List",
    response = PageInfo.class
  )
  @PostMapping(value = "/list")
  //@RequestMapping(value = "/list",method = RequestMethod.POST)
  @SystemControllerLog(description = "(共享系统)分页查询WS调用量的统计信息", operationType = "分页")
  @Authorization
  public Object list(
      TDgapWsDailyStat dailyStat, int start, int length, Date startTime, Date endTime)
      throws Exception {
    if(dailyStat.getBussnessName().length()>60){
      return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);
    }
    if(dailyStat.getCallerUser().length()>60){
      return setModelMap(new ModelMap(), HttpCode.BAD_REQUEST);
    }
    PageInfo<Map<String, Object>> list =
        wsDailyStatService.list(dailyStat,  ((start + 1) / length) + 1, length, startTime, endTime);
    return setSuccessModelMap(new ModelMap(), list);
  }

  /** @param binder */
  @InitBinder
  public void initBinder(final WebDataBinder binder) {
    final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
  }
}
