package com.sofn.fyrpa.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sofn.fyrpa.mapper.TargetOneManagerEpochMapper;
import com.sofn.fyrpa.mapper.TargetOneManagerMapper;
import com.sofn.fyrpa.model.AppraiseAnalyse;
import com.sofn.fyrpa.model.TargetOneManager;
import com.sofn.fyrpa.model.TargetOneManagerEpoch;
import com.sofn.fyrpa.service.AppraiseAnalyseService;
import com.sofn.fyrpa.service.TargetOneManagerEpochService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Service
public class TargetOneManagerEpochServiceImpl extends ServiceImpl<TargetOneManagerEpochMapper, TargetOneManagerEpoch> implements TargetOneManagerEpochService {
    @Lazy
    @Autowired
    private AppraiseAnalyseService appraiseAnalyseService;

    @Lazy
    @Autowired
    private TargetOneManagerMapper targetOneManagerMapper;

    @Override
    public TargetOneManagerEpoch getEpochByTime(String targetId, Date date) {
        if (StringUtils.isBlank(targetId)) {
            throw new NullPointerException();
        }
        return this.list(Wrappers.<TargetOneManagerEpoch>lambdaQuery()
                .eq(TargetOneManagerEpoch::getTargetId, targetId)
                .le(Objects.nonNull(date), TargetOneManagerEpoch::getCreateTime, date)
                .orderByDesc(TargetOneManagerEpoch::getCreateTime)).stream().findFirst().orElse(null);
    }

    @Override
    public boolean fastForwardInUse(String targetId) {
        TargetOneManager targetOneManager = targetOneManagerMapper.selectById(targetId);

        return Objects.nonNull(targetId) &&
                appraiseAnalyseService.count(Wrappers.<AppraiseAnalyse>lambdaQuery()
                    .eq(AppraiseAnalyse::getTargetOneManagerId, targetId)
                    .ge(AppraiseAnalyse::getCreateTime, targetOneManager.getUpdateTime())) > 0;
    }
}
