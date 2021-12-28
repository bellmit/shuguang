package com.sofn.pcstd.log;

import com.sofn.common.log.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 日志事件监听
 */
@Component
public class LogListener {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @EventListener
    public void handleLogEvent(Log log) {
        /*SysLog sysLog = SysLog.getSysLog(log);
        sysLogService.save(sysLog);*/
    }

}
