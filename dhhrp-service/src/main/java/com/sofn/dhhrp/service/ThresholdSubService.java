package com.sofn.dhhrp.service;

import com.sofn.dhhrp.model.ThresholdSub;

import java.util.List;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-04-27 9:28
 */
public interface ThresholdSubService {
    void save(ThresholdSub thresholdSub);
    void  del(String thresholdId);
    List<ThresholdSub> getBythresholdId(String thresholdId);
}
