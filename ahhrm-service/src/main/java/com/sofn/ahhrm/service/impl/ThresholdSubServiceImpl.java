package com.sofn.ahhrm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sofn.ahhrm.mapper.ThresholdSubMapper;
import com.sofn.ahhrm.model.ThresholdSub;
import com.sofn.ahhrm.service.ThresholdSubService;
import com.sofn.common.utils.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-04-27 9:28
 */
@Service("thresholdSubService")
public class ThresholdSubServiceImpl implements ThresholdSubService {
       @Autowired
       private ThresholdSubMapper thresholdSubMapper;
    @Override
    public void save(ThresholdSub thresholdSub) {
        thresholdSub.setId(IdUtil.getUUId());
        thresholdSubMapper.insert(thresholdSub);
    }

    @Override
    public void del(String thresholdId) {
        thresholdSubMapper.del(thresholdId);
    }

    @Override
    public List<ThresholdSub> getBythresholdId(String thresholdId) {
        QueryWrapper<ThresholdSub> wrapper = new QueryWrapper<>();
        wrapper.eq("threshold_id", thresholdId);
        return thresholdSubMapper.selectList(wrapper);
    }
}
