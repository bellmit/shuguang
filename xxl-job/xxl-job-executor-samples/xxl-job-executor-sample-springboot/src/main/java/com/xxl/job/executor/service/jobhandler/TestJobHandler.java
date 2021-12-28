package com.xxl.job.executor.service.jobhandler;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;
import org.springframework.stereotype.Component;

/**
 * @author heyongjie
 * @date 2019/12/11 10:15
 */
@JobHandler(value="testJobHandler")
@Component
public class TestJobHandler extends IJobHandler {
    @Override
    public ReturnT<String> execute(String param) throws Exception {
        XxlJobLogger.log(param);
        System.out.println("Hello XXL-JOB");
        return ReturnT.SUCCESS;
    }
}
