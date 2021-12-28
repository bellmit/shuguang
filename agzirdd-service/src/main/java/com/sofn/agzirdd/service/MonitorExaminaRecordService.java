package com.sofn.agzirdd.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.agzirdd.model.MonitorContent;
import com.sofn.agzirdd.model.MonitorExaminaRecord;

import java.util.List;
import java.util.Map;

/**
 * @Description: 物种监测模块-监测审核记录Service
 * @Author: mcc
 * @Date: 2020\3\10 0010
 */
public interface MonitorExaminaRecordService extends IService<MonitorExaminaRecord> {

    /**
     * 获取满足条件的物种监测模块-监测审核记录List
     * @param params 物种监测表id
     * @return List<MonitorExaminaRecord>
     */
    List<MonitorExaminaRecord> getMonitorExaminaRecordByCondition(Map<String,Object> params);


    /**
     * 新增物种监测模块-监测审核记录
     * @param monitorExaminaRecord monitorExaminaRecord
     */
    void addMonitorExaminaRecord(MonitorExaminaRecord monitorExaminaRecord);

    /**
     * 修改物种监测模块-监测审核记录
     * @param monitorExaminaRecord monitorExaminaRecord
     */
    void updateMonitorExaminaRecord(MonitorExaminaRecord monitorExaminaRecord);

    /**
     * 删除指定speciesMonitorId物种监测模块-监测审核记录
     * @param speciesMonitorId speciesMonitorId
     * @return true or false
     */
    boolean removeMonitorExaminaRecord(String speciesMonitorId);

    /**
     * 保存审核记录
     * @param id
     * @param newStatus
     * @param remark
     */
    void saveByAudit(String id, String newStatus, String remark);
}

