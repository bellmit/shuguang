package com.sofn.sys.job;

import com.sofn.sys.service.SysFileManageService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 删除未激活文件JOB
 * @author heyongjie
 * @date 2019/12/13 16:31
 */
@JobHandler(value="deleteNotActiveJob")
@Component
@Slf4j
public class DeleteNotActiveJob   extends IJobHandler {

    @Autowired
    private SysFileManageService sysFileManageService;

    @Override
    public ReturnT<String> execute(String param) throws Exception {
        long deleteCount = sysFileManageService.deleteNotActivationFile(30);
        XxlJobLogger.log("删除未激活文件成功，删除了" + deleteCount + "条数据");
        log.info("删除未激活文件成功，删除了{}条数据",deleteCount);
        return ReturnT.SUCCESS;
    }
}
