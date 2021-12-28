package com.sofn.core.scheduler;

import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.core.util.RedisUtil;
import com.sofn.core.util.UUIDUtil;
import com.sofn.model.generator.TDgapWsDailyStat;
import com.sofn.provider.dgap.TDgapWsDailyStatProvider;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import de.ruedigermoeller.serialization.annotations.Serialize;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * author tianjie
 *
 * @date 2016/10/25
 */
@Component
public class WebserviceLogStatics {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @DubboReference
    private TDgapWsDailyStatProvider tDgapWsDailyStatProvider;

    @Value("${scheduler.dailystat.enable}")
    String enabled = "false";

    @Scheduled(cron = "0 0 3 * * ?")
    public void statisticErrlog() {
        if (enabled != null && enabled.equalsIgnoreCase("true")) {
            logger.info("Scheduler for dailystat started!");
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DAY_OF_MONTH, -1);
            tDgapWsDailyStatProvider.stateDailyLog(cal.getTime());
            logger.info("Scheduler for dailystat finished!");
        } else {
            logger.info("Scheduler for dailystat disabled!");
        }
    }

    @Scheduled(cron = "0 0 1 * * ?")
    public void newStatisticErrlog() {
        if (enabled != null && enabled.equalsIgnoreCase("true")) {
            logger.info("Scheduler for newdailystat started!");
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DAY_OF_MONTH, -1);

            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//设置日期格式

            String nowTime = df.format(cal.getTime());

            Set<Serializable> keysSet = RedisUtil.getKeysSet(nowTime);

            for (Serializable key : keysSet) {
                String[] value = RedisUtil.get((String) key).toString().split(",");

                String[] keys = ((String) key).split(",");

                TDgapWsDailyStat tDgapWsDailyStat = new TDgapWsDailyStat();
                tDgapWsDailyStat.setWebServiceId(keys[3]);
                try {
                    tDgapWsDailyStat.setStatDate(df.parse(df.format(cal.getTime())));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                tDgapWsDailyStat.setCallerUser(keys[1]);
                tDgapWsDailyStat.setBussnessName(keys[2]);
                tDgapWsDailyStat.setWebServiceName(keys[4]);
                tDgapWsDailyStat.setResourceDir(keys[5]);

                int avgTimecost = Integer.parseInt(value[2]) / (Integer.parseInt(value[0]) + Integer.parseInt(value[1]));
                tDgapWsDailyStat.setSuccessTimes(new BigDecimal(value[0]));
                tDgapWsDailyStat.setFailTimes(new BigDecimal(value[1]));
                tDgapWsDailyStat.setAvgTimecost(new BigDecimal(avgTimecost));
                String uid = UUIDUtil.getUUID();
                tDgapWsDailyStat.setId(uid == null ? "" : uid);


                tDgapWsDailyStat.setUpdateBy("0");
                tDgapWsDailyStat.setRemark("0");
                try {
                    tDgapWsDailyStat.setUpdateTime(df.parse(df.format(cal.getTime())));
                    tDgapWsDailyStat.setCreateTime(df.parse(df.format(cal.getTime())));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                tDgapWsDailyStat.setCreateBy("0");
                tDgapWsDailyStat.setDelFlag("N");
                tDgapWsDailyStatProvider.setWebServiceLog(tDgapWsDailyStat);

            }


//      tDgapWsDailyStatProvider.stateDailyLog(cal.getTime());
//            logger.info("Scheduler for newdailystat finished!");
//        }
//        else {
//            logger.info("Scheduler for newdailystat disabled!");
        }
    }

}
