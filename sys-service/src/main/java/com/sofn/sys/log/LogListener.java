package com.sofn.sys.log;

import com.sofn.common.log.Log;
import com.sofn.sys.model.SysLog;
import com.sofn.sys.service.SysLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 日志事件监听
 */
@Component
public class LogListener {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SysLogService sysLogService;

    @EventListener
    public void handleLogEvent(Log log) {
        SysLog sysLog = SysLog.getSysLog(log);
        sysLogService.save(sysLog);
    }

}
