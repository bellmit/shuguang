package com.sofn.ducss.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.ducss.model.CollectFlowLog;

import java.util.List;
import java.util.Map;

public interface CollectFlowLogMapper  extends BaseMapper<CollectFlowLog> {

    List<CollectFlowLog> getCollectFlowLogList(Map<String, Object> params);

    void insertFlowLog(CollectFlowLog ducCollectFlowLog);

    List<CollectFlowLog> selectCollectFlowLogPage(Map<String, Object> params);
}