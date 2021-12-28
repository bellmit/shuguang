package com.sofn.agsjdm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sofn.agsjdm.mapper.ThresholdMapper;
import com.sofn.agsjdm.model.Threshold;
import com.sofn.agsjdm.service.ThresholdService;
import com.sofn.common.utils.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-04-16 10:34
 */
/**
 * 阀值服务务类
 **/
@Service(value = "thresholdServer")
public class ThresholdServiceImpl implements ThresholdService {

    @Autowired
    private ThresholdMapper thresholdMapper;

    @Override
    public Threshold save(Threshold entity) {
        entity.setId(IdUtil.getUUId());
        thresholdMapper.insert(entity);
        return entity;
    }

    @Override
    public void deleteByWarningId(String warningId) {
        QueryWrapper<Threshold> wrapper = new QueryWrapper<>();
        wrapper.eq("WARNING_ID", warningId);
        thresholdMapper.delete(wrapper);
    }

    @Override
    public List<Threshold> listByWarningId(String warningId) {
        QueryWrapper<Threshold> wrapper = new QueryWrapper<>();
        wrapper.eq("WARNING_ID", warningId);
        return thresholdMapper.selectList(wrapper);
    }
}
