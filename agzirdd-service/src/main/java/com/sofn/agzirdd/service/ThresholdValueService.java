package com.sofn.agzirdd.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.agzirdd.model.ThresholdValue;

import java.util.List;

/**
 *
 */
public interface ThresholdValueService extends IService<ThresholdValue> {
    //根据wtid查询
    List<ThresholdValue> selectByWtId(String wtId);

    //根据wtid删除
    boolean deleteByWtId(String wtid);
}
