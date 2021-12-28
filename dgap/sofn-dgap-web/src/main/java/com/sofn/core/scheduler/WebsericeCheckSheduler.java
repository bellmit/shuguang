package com.sofn.core.scheduler;

import com.sofn.core.config.Constant;
import com.sofn.model.generator.TDgapAlertConfig;
import com.sofn.model.generator.TDgapAlertLog;
import com.sofn.model.generator.TDgapResource;
import com.sofn.service.dgap.TDgapAlertConfigLogService;
import com.sofn.service.dgap.TDgapAlertConfigService;
import com.sofn.service.dgap.TDgapResourceService;
import java.util.Date;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * author tianjie
 *
 * @date 2016/10/24
 */
@Component
public class WebsericeCheckSheduler {

  private static String ACTIVE = "ACTIVE";
  private static String HEARTBEATMETHOD = "heartbeatMessage";
  protected Logger logger = LoggerFactory.getLogger(this.getClass());

  @Value("${scheduler.wshealthcheck.enable}")
  String enabled = "false";

  @Autowired private TDgapAlertConfigService tDgapAlertConfigService;

  @Autowired private TDgapResourceService tDgapResourceService;

  @Autowired private TDgapAlertConfigLogService tDgapAlertConfigLogService;

  @Scheduled(cron = "0 0 2 * * ?")
  public void checkWebservice() throws Exception {
    if (enabled != null && enabled.equalsIgnoreCase("true")) {
      logger.info("Scheduler for ws health check started!");

      healthCheck();

      logger.info("Scheduler for ws health check finished!");
    } else {
      logger.info("Scheduler for ws health check disabled!");
    }
  }

  public void healthCheck() {
    List<TDgapAlertConfig> list = tDgapAlertConfigService.queryAllAlertConfig();

    for (TDgapAlertConfig config : list) {
      healthCheck(config);
    }
  }

  public void healthCheck(String configId) {
    TDgapAlertConfig config = tDgapAlertConfigService.queryById(configId);
    healthCheck(config);
  }

  void healthCheck(TDgapAlertConfig config) {
    String webserviceId = config.getTargetId();
    TDgapResource resource = null;
    resource = tDgapResourceService.getResource(webserviceId);
    if (resource == null) {
      return;
    }
    String url = resource.getWsdlUrl();
    // 调用web Service//输出调用结果 ;
    if (!invokeWs(resource.getWsdlUrl(), config)) {
      TDgapAlertLog log = new TDgapAlertLog();

      /*if (tDgapAlertConfigLogService.checkHasAlert(config.getTargetId())) {
        return;
      }*/

      log.setTargetId(config.getTargetId());
      log.setAlertConfigId(config.getId());
      log.setDescription(config.getDescription());
      log.setAlertDate(new Date());
      log.setCreateTime(new Date());
      log.setUpdateTime(new Date());
      log.setDelFlag("N");
      log.setCreateBy("webservice_check_scheduler");
      log.setUpdateBy("webservice_check_scheduler");
      tDgapAlertConfigLogService.add(log);
      // 调用失败`
    }
  }

  public boolean invokeWs(String wsUrl, TDgapAlertConfig config) {
    boolean flag = false;
    Client client = null;
    try {
      JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
      client = dcf.createClient(wsUrl);
      //   client.getOutInterceptors().add(new ClientLoginInterceptor(default_user_name,
      // default_pwd));
    } catch (Exception e) {
      return flag;
    }

    // url为调用webService的wsdl地址
    QName name = new QName(Constant.NAMESPACE, HEARTBEATMETHOD);
    // namespace是命名空间，methodName是方法名
    // paramvalue为参数值
    int index = 0;
    while (index <= config.getThreshold().longValue()) {

      try {
        Object[] objects = client.invoke(name);
        return true;
      } catch (Exception e) {
        index++;
        e.printStackTrace();
      }
      if (index >= config.getThreshold().longValue()) {
        flag = false;
      } else {
        flag = true;
      }
    }
    return flag;
  }
}
