package com.sofn.core.scheduler;

import com.sofn.service.dgap.TDgapEtlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/** Created by wangt on 2017/5/31. */
@Component
public class AetlSheduler {
  private Logger logger = LoggerFactory.getLogger(AetlSheduler.class);
  @Autowired TDgapEtlService tDgapEtlService;

  // 秒（0~59） 分（0~59） 时（0~23） 天（月0~31） 月（0~11） 天（星期1~7）
  @Scheduled(cron = "0 43 09 * * ?")
  public boolean excuteTrans() {
    logger.info("调度开始了+++++++++++");
    boolean trans = tDgapEtlService.excuteTrans();
    return trans;
  }
}
