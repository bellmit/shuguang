package com.sofn.agzirdd.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.agzirdd.model.MonitorExaminaRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Description: 物种监测模块-监测审核记录Mapper
 * @Author: mcc
 * @Date: 2020\3\10 0010
 */
@Mapper
public interface MonitorExaminaRecordMapper extends BaseMapper<MonitorExaminaRecord> {


    /**
     * 获取满足条件的物种监测模块-监测审核记录List
     * @param params 物种监测表id
     * @return List<MonitorExaminaRecord>
     */
    List<MonitorExaminaRecord> getMonitorExaminaRecordByCondition(Map<String,Object> params);


    /**
     * 删除指定speciesMonitorId物种监测模块-监测审核记录
     * @param speciesMonitorId speciesMonitorId
     * @return true or false
     */
    boolean deleteMonitorExaminaRecord(String speciesMonitorId);
}