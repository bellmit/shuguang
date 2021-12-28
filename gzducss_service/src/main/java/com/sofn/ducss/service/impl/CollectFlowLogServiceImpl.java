package com.sofn.ducss.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.sofn.ducss.enums.Constants;
import com.sofn.ducss.mapper.CollectFlowLogMapper;
import com.sofn.ducss.model.CollectFlowLog;
import com.sofn.ducss.service.CollectFlowLogService;
import com.sofn.ducss.util.PageUtils;
import com.sofn.ducss.util.UserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 获取年度任务
 */
@Service
@Slf4j
public class CollectFlowLogServiceImpl extends ServiceImpl<CollectFlowLogMapper,CollectFlowLog> implements CollectFlowLogService {
    @Autowired
    private CollectFlowLogMapper collectFlowLogMapper;

    @Override
    public PageUtils<CollectFlowLog> getCollectFlowLogByPage(Integer pageNo, Integer pageSize,String year,String countyId){
        PageHelper.offsetPage(pageNo,pageSize);

        Map<String, Object> params = Maps.newHashMap();
        params.put("year", year);
        params.put("countyId", countyId);
        List<CollectFlowLog> list = collectFlowLogMapper.getCollectFlowLogList(params);
        for(CollectFlowLog collectFlowLog : list){
            String userId = collectFlowLog.getCreateUserId();
            String userName = UserUtil.getUsernameById(userId);
            collectFlowLog.setCreateUserName(userName);
        }
        return PageUtils.getPageUtils(new PageInfo(list));
    }

    @Override
    public List<CollectFlowLog> findCollectFlowLog(String year,String areaId) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("year", year);
        params.put("areaId", areaId);
        params.put("status", Constants.ExamineState.RETURNED.toString());
        return collectFlowLogMapper.selectCollectFlowLogPage(params);
    }

}
