package com.sofn.fdpi.service.impl;

import com.sofn.common.exception.SofnException;
import com.sofn.common.model.Result;
import com.sofn.common.utils.PageUtils;
import com.sofn.fdpi.service.WorkService;
import com.sofn.fdpi.sysapi.WorkApi;
import com.sofn.fdpi.sysapi.bean.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service(value = "workService")
public class WorkServiceImpl implements WorkService {

    @Resource
    private WorkApi workApi;

    @Override
    public Result<String> startChainProcess(SubmitInstanceVo vo) {
        Result<String> result = workApi.startChainProcess(vo);
        if (!Result.CODE.equals(result.getCode())) {
            throw new SofnException("开始流程失败");
        }
        return result;
    }

    @Override
    public Result<String> completeWorkItem(SubmitInstanceVo vo) {
        Result<String> result = workApi.completeWorkItem(vo);
        if (!Result.CODE.equals(result.getCode())) {
            throw new SofnException("提交工作流程失败");
        }
        return result;
    }

    @Override
    public Result<String> backWorkItem(BackWorkItemForm form) {
        Result<String> result = workApi.backWorkItem(form);
        if (!Result.CODE.equals(result.getCode())) {
            throw new SofnException("回退工作流程失败");
        }
        return result;
    }

    @Override
    public Result<String> removeProDefAttrCache(String defId) {
        Result<String> result = workApi.removeProDefAttrCache(defId);
        return result;
    }

    @Override
    public Result<HisProcInstVo> getProcessInstHisByIdAttr(UpdateInstVo vo) {
        Result<HisProcInstVo> result = workApi.getProcessInstHisByIdAttr(vo);
        if (!Result.CODE.equals(result.getCode())) {
            throw new SofnException("未获取到流程工作实例");
        }
        return result;
    }

    @Override
    public Result<PageUtils<ActivityDataVo>> getActivityAllDataByName(ActivityDataParamsVo activityDataParamsVo) {
        Result<PageUtils<ActivityDataVo>> result = workApi.getActivityAllDataByName(activityDataParamsVo);
        if (!Result.CODE.equals(result.getCode())) {
            throw new SofnException("未获取到流程工作实例");
        }
        return result;
    }
}
