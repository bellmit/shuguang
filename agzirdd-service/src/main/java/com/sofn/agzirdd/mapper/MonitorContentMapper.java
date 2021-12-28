package com.sofn.agzirdd.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.agzirdd.model.MonitorContent;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description: 物种监测模块-监测内容Mapper
 * @Author: mcc
 * @Date: 2020\3\10 0010
 */
@Mapper
public interface MonitorContentMapper extends BaseMapper<MonitorContent> {

    /**
     * 获取指定speciesMonitorId物种监测模块-监测内容
     * @param speciesMonitorId speciesMonitorId
     * @return MonitorContent
     */
    MonitorContent getMonitorContentBySpeciesMonitorId(String speciesMonitorId);

    /**
     * 删除指定speciesMonitorId物种监测模块-监测内容
     * @param speciesMonitorId speciesMonitorId
     * @return true or false
     */
    boolean deleteMonitorContent(String speciesMonitorId);
}