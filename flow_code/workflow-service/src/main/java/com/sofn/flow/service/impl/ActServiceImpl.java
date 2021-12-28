package com.sofn.flow.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sofn.flow.mapper.ActMapper;
import com.sofn.flow.model.Act;
import com.sofn.flow.service.ActService;
import com.sofn.flow.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ActServiceImpl extends ServiceImpl<ActMapper, Act> implements ActService {

    @Autowired
    ActMapper actMapper;

    @Override
    public List<Act> getListByParams(String defId, String instId, Map<String, Object> attrs) {
        return actMapper.getListByParams(defId, instId, attrs);
    }

    @Override
    public List<ProcessVo> getProcessInstAllByAttr(List<String> defIds,   String idAttrName, List<String> idAttrValues, List<InstanceParamVo> instanceParamVos, String startTime, String endTime) {

        return actMapper.getProcessInstAllByAttr(defIds, idAttrName,idAttrValues,instanceParamVos, startTime, endTime);
    }

    @Override
    public List<ProcessVo> getProcessInstAllByAttrByPage(List<String> defIds, String idAttrName, List<String> idAttrValues, List<InstanceParamVo> instanceParamVos, String startTime, String endTime) {
        return actMapper.getProcessInstAllByAttrByPage(defIds, idAttrName, idAttrValues, instanceParamVos, startTime, endTime);
    }

    @Override
    public List<ActivityDataVo> getActivityAllDataByName(String defId, String idAttrName, List<String> idAttrValues, String activityMaker, List<InstanceParamTwoVo> instanceParamVos, String startTime, String endTime) {
        return actMapper.getActivityAllDataByName(defId,idAttrName,idAttrValues,activityMaker,instanceParamVos,startTime,endTime);
    }

    @Override
    public List<ActContextVo> getAllFieldAndValue(String activityId, List<InstanceParamTwoVo> instanceParamVos) {
        return actMapper.getAllFieldAndValue(activityId,instanceParamVos);
    }
}
