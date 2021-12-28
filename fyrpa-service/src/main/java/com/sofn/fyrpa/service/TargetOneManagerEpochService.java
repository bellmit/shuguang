package com.sofn.fyrpa.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.fyrpa.model.TargetOneManagerEpoch;

import java.util.Date;

public interface TargetOneManagerEpochService extends IService<TargetOneManagerEpoch> {
    /**
     * 获取对应时间的历史版本数据
     * @param date
     * @return
     */
    TargetOneManagerEpoch getEpochByTime(String targetId, Date date);

    /**
     * 一级指标最新的数据是否有被使用
     * @return
     */
    boolean fastForwardInUse(String targetId);
}
