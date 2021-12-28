package com.sofn.agzirdd.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.agzirdd.model.MonitorContent;
import com.sofn.agzirdd.vo.MonitorContentVo;

/**
 * @Description: 物种监测模块-监测内容Service
 * @Author: mcc
 * @Date: 2020\3\10 0010
 */
public interface MonitorContentService extends IService<MonitorContent> {


    /**
     * 获取指定speciesMonitorId物种监测模块-监测内容
     * @param speciesMonitorId speciesMonitorId
     * @return MonitorContent
     */
    MonitorContent getMonitorContentBySpeciesMonitorId(String speciesMonitorId);

    /**
     * 获取指定speciesMonitorId物种监测模块-监测内容Vo
     * @param speciesMonitorId speciesMonitorId
     * @return MonitorContentVo
     */
    MonitorContentVo getMonitorContentVo(String speciesMonitorId);

    /**
     * 新增入侵物种监测模块-监测内容
     * @param monitorContent monitorContent
     */
    void addMonitorContent(MonitorContent monitorContent);

    /**
     * 修改入侵物种监测模块-监测内容
     * @param monitorContent monitorContent
     */
    void updateMonitorContent(MonitorContent monitorContent);

    /**
     * 删除指定speciesMonitorId物种监测模块-监测内容
     * @param speciesMonitorId speciesMonitorId
     * @return true or false
     */
    boolean removeMonitorContent(String speciesMonitorId);
}
