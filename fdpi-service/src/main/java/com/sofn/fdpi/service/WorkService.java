package com.sofn.fdpi.service;


import com.sofn.common.model.Result;
import com.sofn.common.utils.PageUtils;
import com.sofn.fdpi.sysapi.bean.*;

public interface WorkService {
    /**
     * 启动流程
     */
    Result<String> startChainProcess(SubmitInstanceVo vo);

    /**
     * 提交工作项
     */
    Result<String> completeWorkItem(SubmitInstanceVo vo);

    /**
     * 回退
     */
    Result<String> backWorkItem(BackWorkItemForm form);

    /**
     * 删除缓存
     */
    Result<String> removeProDefAttrCache(String defId);

    /**
     * 获取流程实例历史对象[根据业务ID]
     */
    Result<HisProcInstVo> getProcessInstHisByIdAttr(UpdateInstVo vo);

    /**
     * 获取流程活动对象列表[根据名称]
     */
    Result<PageUtils<ActivityDataVo>> getActivityAllDataByName(ActivityDataParamsVo activityDataParamsVo);
}
