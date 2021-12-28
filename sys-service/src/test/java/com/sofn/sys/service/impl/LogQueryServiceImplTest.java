package com.sofn.sys.service.impl;

import com.sofn.SysServiceApplicationTests;
import com.sofn.sys.service.LogQueryService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author heyongjie
 * @date 2020/9/16 17:38
 */
public class LogQueryServiceImplTest extends SysServiceApplicationTests {

    @Autowired
    private LogQueryService logQueryService;

    @Test
    public void getLogInfo() {
//        String logInfo = logQueryService.getLogInfo("", "登录", "sys", 0, 10);
//        System.out.println(logInfo);
    }



    @Test
    public void getSysLogInfo(){
        logQueryService.getSysLogInfo();

    }
}