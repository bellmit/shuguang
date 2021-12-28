package com.sofn.ducss.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.ducss.model.CollectFlowLog;
import com.sofn.ducss.util.PageUtils;

import java.util.List;

/**
 * 获取当前年任务
 */
public interface CollectFlowLogService extends IService<CollectFlowLog> {

    PageUtils<CollectFlowLog> getCollectFlowLogByPage(Integer pageNo, Integer pageSize,String year,String countyId);

    List<CollectFlowLog> findCollectFlowLog(String year,String areaId);

}
