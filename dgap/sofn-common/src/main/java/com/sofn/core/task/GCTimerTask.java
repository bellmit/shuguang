package com.sofn.core.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author lijiang
 * @create 2018-08-23 15:03
 * @desc 垃圾回收定时任务
 **/
@Component
public class GCTimerTask {
    private static final Logger LOGGER = LoggerFactory.getLogger(GCTimerTask.class);

    /**
     * 每天23：59执行一次垃圾回收
     */
    @Scheduled(cron = "0 59 23 * * ?")
    public void run() {
        System.gc();
        LOGGER.info("********* 垃圾回收任务已执行！********");
    }
}
