package com.sofn.agzirdd.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sofn.agzirdd.mapper.ThresholdValueMapper;
import com.sofn.agzirdd.model.ThresholdValue;
import com.sofn.agzirdd.service.ThresholdValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThresholdValueServiceImpl extends ServiceImpl<ThresholdValueMapper, ThresholdValue> implements ThresholdValueService {
    @Autowired
    private ThresholdValueMapper thresholdValueMapper;

    @Override
    public List<ThresholdValue> selectByWtId(String wtId) {
        return thresholdValueMapper.getThresholdValueByWtId(wtId);
    }

    @Override
    public boolean deleteByWtId(String wtId) {
        int count = thresholdValueMapper.deleteByWtId(wtId);
        return count>0;
    }
}
