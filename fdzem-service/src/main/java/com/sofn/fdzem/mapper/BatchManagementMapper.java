package com.sofn.fdzem.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.fdzem.model.BatchManagement;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BatchManagementMapper extends BaseMapper<BatchManagement> {
    /**
     * 通过id删除该批次
     * @param id
     */
    void removeById(String id);
    /**
     * 更新批次
     * @param batchManagement
     */
    void update(BatchManagement batchManagement);
    /**
     *添加批次
     * @param batchManagement
     */
    void insert1(BatchManagement batchManagement);


    /**
     * 分页查询+条件查询
     * @param params
     * @return
     */
    List<BatchManagement> selectListByQuery(Map params);

    /**
     * 通过id查询批次
     * @param id
     * @return
     */
    BatchManagement view(String id);

    /**
     * 通过所属监测站id获取批次id
     * @param monitoringStationTaskId
     * @return
     */
    List<BatchManagement> list(String monitoringStationTaskId);

    /**
     * 获取所属监测站下的批次总数
     * @param monitoringStationTaskId
     * @return
     */
    Integer total(String monitoringStationTaskId);
}
