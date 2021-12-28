package com.sofn.sys.job;

import com.sofn.common.clearlogicaldeletedata.ClearLogicalDeleteData;
import com.sofn.common.clearlogicaldeletedata.properties.ClearLogicalDeleteDataProperties;
import com.sofn.common.fileutil.FastDFSClient;
import com.sofn.sys.model.SysFileManage;
import com.sofn.sys.service.SysFileManageService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 清除数据任务
 * @author heyongjie
 * @date 2019/12/13 9:59
 */
@JobHandler(value="clearDataJobHandler")
@Component
@Slf4j
public class ClearDataJob  extends IJobHandler {

    @Autowired(required = false)
    private ClearLogicalDeleteData clearLogicalDeleteData;

    @Autowired(required = false)
    private ClearLogicalDeleteDataProperties clearLogicalDeleteDataProperties;

    @Autowired
    private SysFileManageService sysFileManageService;

    @Override
    public ReturnT<String> execute(String param) throws Exception {
        if(clearLogicalDeleteData != null){
            // 1. 删除文件
            int datAgo = clearLogicalDeleteDataProperties.getDatAgo();
            List<SysFileManage> deleteFileByDate = sysFileManageService.getDeleteFileByDate(datAgo);
            if(!CollectionUtils.isEmpty(deleteFileByDate)){
                List<String> collect = deleteFileByDate.stream().map(SysFileManage::getFilePath).collect(Collectors.toList());
                FastDFSClient.batchDeleteFile(collect);
                XxlJobLogger.log("文件清理成功，已经将" + datAgo + "天之前已删除的文件删除");
            }
            // 2. 删除剩余表的数据
            clearLogicalDeleteData.clearData();
            // 只有使用XxlJobLogger.log 打印日志，才会在前台的监控界面查询到日志信息
            XxlJobLogger.log("成功删除了"+datAgo+"天之前的逻辑删除数据");
            log.info("成功删除了{}天之前的逻辑删除数据",datAgo);


        }
        return ReturnT.SUCCESS;
    }

}
