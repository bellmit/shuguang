package com.sofn.ducss.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.common.utils.PageUtils;
import com.sofn.ducss.model.CollectFlowLog;

import java.util.List;

/**
 * 获取当前年任务
 */
public interface CollectFlowLogService extends IService<CollectFlowLog> {

    PageUtils<CollectFlowLog> getCollectFlowLogByPage(Integer pageNo, Integer pageSize, String year, List<String> areaIds);

    CollectFlowLog lastCollectFlowLog(String year, String areaId);

}
