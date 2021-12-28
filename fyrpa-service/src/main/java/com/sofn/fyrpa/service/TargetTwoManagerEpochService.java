package com.sofn.fyrpa.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.fyrpa.model.TargetTwoManagerEpoch;

import java.util.Date;

public interface TargetTwoManagerEpochService extends IService<TargetTwoManagerEpoch> {
    /**
     * 获取对应时间的历史版本数据
     * @param date
     * @return
     */
    TargetTwoManagerEpoch getEpochByTime(String targetId, Date date);

    /**
     * 二级指标最新的数据是否有被使用
     * @return
     */
    boolean fastForwardInUse(String targetId);
}
