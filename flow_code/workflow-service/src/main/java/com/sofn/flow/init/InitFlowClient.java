package com.sofn.flow.init;

import com.cvicse.workflow.api.WfClientManager;
import com.sofn.flow.service.impl.ProcessInstanceServiceImpl;
import org.apache.log4j.Logger;

/**
 * 初始化工作流客户端
 * @author heyongjie
 * @date 2020/5/7 11:11
 */

public class InitFlowClient  {

    private static Logger logger = Logger.getLogger(ProcessInstanceServiceImpl.class);

    public void initFlowClient() {
        logger.info("FlowClient开始初始化");
        WfClientManager.getInstance().init();
        logger.info("FlowClient初始化成功");
    }
}
