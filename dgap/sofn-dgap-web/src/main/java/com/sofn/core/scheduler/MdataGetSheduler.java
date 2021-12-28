package com.sofn.core.scheduler;

import com.sofn.wsclient.mdata.MdataGetter;
import com.sofn.wsclient.mdata.MdataWsClientConfig;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.apache.cxf.common.i18n.Exception;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * author helong //TODO HELONG
 *
 * @date 2016/10/24
 */
@Component
public class MdataGetSheduler {
  protected Logger logger = LoggerFactory.getLogger(this.getClass());

  @Value("${scheduler.mdata.enable}")
  String enabled = "false";

  @Autowired MdataWsClientConfig config;
  @Autowired MdataGetter getter;
  @Autowired MdataGetter.RowDataProcessor processor;

  public void setProcessor(MdataGetter.RowDataProcessor processor) {
    this.processor = processor;
  }

  // 秒（0~59） 分（0~59） 时（0~23） 天（月0~31） 月（0~11） 天（星期1~7）
  @Scheduled(cron = "0 0 1 * * ?")
  public void getMdata() throws Exception {
    if (enabled != null && enabled.equalsIgnoreCase("true")) {
      logger.info("Scheduler for mdata started!");

      Date startDate = getYesterday();
      Date endDate = getToday();

      getter.getMdataCodeSets(config, startDate, endDate, processor);
      getter.getMdataCodes(config, startDate, endDate, processor);

      logger.info("Scheduler for mdata finished!");
    } else {
      logger.info("Scheduler for mdata disabled!");
    }
  }

  /**
   * 获取今天的凌晨0点
   *
   * @return
   */
  public static Date getToday() {
    Date date = new Date();
    Calendar calendar = new GregorianCalendar();
    calendar.setTime(date);
    calendar.set(Calendar.HOUR, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);
    /*DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String toDay = format.format(calendar.getTime());*/
    Date toDay = calendar.getTime();
    return toDay;
  }

  /**
   * 获取昨天的凌晨0点
   *
   * @return
   */
  public static Date getYesterday() {
    Date yesterDay = new Date();
    yesterDay.setDate(yesterDay.getDate() - 1);
    yesterDay.setHours(0);
    yesterDay.setMinutes(0);
    yesterDay.setSeconds(0);
    /*DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String yesterDay = format.format(date);*/
    return yesterDay;
  }
}
