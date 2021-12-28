package com.sofn.agzirdd.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sofn.agzirdd.mapper.MonitorExaminaRecordMapper;
import com.sofn.agzirdd.model.MonitorExaminaRecord;
import com.sofn.agzirdd.service.MonitorExaminaRecordService;
import com.sofn.common.utils.IdUtil;
import com.sofn.common.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description: 物种监测模块-监测审核记录ServiceImpl
 * @Author: mcc
 * @Date: 2020\3\10 0010
 */
@Service
public class MonitorExaminaRecordServiceImpl extends ServiceImpl<MonitorExaminaRecordMapper, MonitorExaminaRecord> implements MonitorExaminaRecordService {

    @Autowired
    private MonitorExaminaRecordMapper monitorExaminaRecordMapper;

    @Override
    public List<MonitorExaminaRecord> getMonitorExaminaRecordByCondition(Map<String, Object> params) {

        return monitorExaminaRecordMapper.getMonitorExaminaRecordByCondition(params);
    }

    @Override
    public void addMonitorExaminaRecord(MonitorExaminaRecord monitorExaminaRecord) {

        this.save(monitorExaminaRecord);
    }

    @Override
    public void updateMonitorExaminaRecord(MonitorExaminaRecord monitorExaminaRecord) {

        this.updateById(monitorExaminaRecord);
    }

    @Override
    public boolean removeMonitorExaminaRecord(String speciesMonitorId) {

        return monitorExaminaRecordMapper.deleteMonitorExaminaRecord(speciesMonitorId);
    }

    @Override
    public void saveByAudit(String smId, String newStatus, String remark) {
        MonitorExaminaRecord record = new MonitorExaminaRecord();
        record.setId(IdUtil.getUUId());
        record.setSpeciesMonitorId(smId);
        record.setStatus(newStatus);
        record.setAuditor(UserUtil.getLoginUserName());
        record.setOpinion(remark);
        record.setCreateTime(new Date());

        this.save(record);
    }
}
