package com.sofn.fdzem.service;


import com.github.pagehelper.PageInfo;
import com.sofn.fdzem.model.BatchManagement;

public interface BatchManagementService {
    /**
     * 批次管理列表获取+分页+条件查询
     * @param pageNo
     * @param pageSize
     * @param name
     * @param samplingTimeLeft
     * @param samplingTimeRight
     * @param submittedTimeLeft
     * @param submittedTimeRight
     * @param monitoringStationTaskId
     * @return
     */
    PageInfo<BatchManagement> selectListByQuery(Integer pageNo, Integer pageSize, String name, String samplingTimeLeft,String samplingTimeRight,String submittedTimeLeft,String submittedTimeRight,String monitoringStationTaskId);

    /**
     * 批次添加
     * @param batchManagement
     */
    void insertAll(BatchManagement batchManagement);

    /**
     * 删除
     * @param id
     */
    void remove(String id);

    /**
     * 更新
     * @param batchManagement
     */
    void update(BatchManagement batchManagement);

    /**
     * 获取批次详情
     * @param id
     * @return
     */
    BatchManagement view (String id);

}
