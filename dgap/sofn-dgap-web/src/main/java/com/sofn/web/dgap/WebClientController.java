package com.sofn.web.dgap;

import com.alibaba.fastjson.JSONObject;
import com.sofn.core.authorization.annotation.Authorization;
import com.sofn.core.base.BaseController;
import com.sofn.core.scheduler.WebsericeCheckSheduler;
import com.sofn.core.util.DateUtil;
//import com.sofn.dgap.scheduler.FilesSyncInSheduler;
import com.sofn.service.dgap.TDgapWsDailyStatService;
import java.util.Date;

import org.apache.cxf.common.i18n.Exception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** */
@RestController
@RequestMapping(value = "/wc")
public class WebClientController extends BaseController {

  @Autowired private WebsericeCheckSheduler wsChecker;

  @Autowired private TDgapWsDailyStatService wsDailyStatService;

//  @Autowired
//  private FilesSyncInSheduler filesSyncInSheduler;


  @GetMapping(value = "/dailyStat")
  public Object dailyStat(String day) throws Exception {
    logger.debug("day=" + day);
    Date date = DateUtil.stringToDate(day);
    logger.debug("Doing daily stat: date=" + date);
    int c = wsDailyStatService.stateDailyLog(date);
    return setSuccessModelMap(new ModelMap(), c);
  }

  @GetMapping(value = "/wsHealthCheck")
  public Object wsHealthCheck() throws Exception {
    logger.debug("Doing web service health check");
    wsChecker.healthCheck();
    return setSuccessModelMap(new ModelMap(), true);
  }

  /**
   *   该方法是将正式数据库中的TTS_SCLTXXCJ_REGISTER_V2复制到 仓库数据库
   * @param sourceDataSource
   * @param destDataSource
   * @return
   */
  @RequestMapping(value = "/copyData")
  public Object copyTTSEntities(String sourceDataSource, String destDataSource) {
    boolean isSuccess= wsDailyStatService.copyTTSEntities(sourceDataSource,destDataSource);
    if(isSuccess==true){
      return setSuccessModelMap(new ModelMap(), true);
    }else{
      return setFailModelMap(new ModelMap(), false);
    }
  }


  @RequestMapping(value = "/spybdjstatus", produces = "application/json;charset=utf-8")
  public Object spybdjstatus() {
      JSONObject jo = new JSONObject();
      //jo.put("");
      return jo.toJSONString();
  }

}
