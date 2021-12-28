package com.sofn.ducss.service;

import com.sofn.common.utils.PageUtils;
import com.sofn.ducss.model.DucssOperateLog;

public interface DucssOperateLogService {

    /**
     * 保存操作日志
     *
     * @param operateType   操作类型
     * @param operateDetail 操作详情
     */
    void save(String operateType, String operateDetail);

    /**
     * 查询操作记录
     *
     * @param startDate     开始事件
     * @param endDate       截止事件
     * @param operateType   操作类型
     * @param operateDetail 操作详情
     * @param areaId        区域ID
     * @param pageNo        从哪条记录开始
     * @param pageSize      每页显示多少条
     * @return PageUtils<DucssOperateLog>
     */
    PageUtils<DucssOperateLog> getLogList(String startDate,
                                          String endDate,
                                          String operateType,
                                          String operateDetail,
                                          String areaId,
                                          Integer pageNo,
                                          Integer pageSize
    );

}
