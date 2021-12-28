package com.sofn.fyrpa.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sofn.fyrpa.mapper.TargetTwoManagerEpochMapper;
import com.sofn.fyrpa.model.AppraiseAnalyse;
import com.sofn.fyrpa.model.TargetTwoManager;
import com.sofn.fyrpa.model.TargetTwoManagerEpoch;
import com.sofn.fyrpa.service.AppraiseAnalyseService;
import com.sofn.fyrpa.service.TargetTwoManagerEpochService;
import com.sofn.fyrpa.service.TargetTwoManagerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Service
public class TargetTwoManagerEpochServiceImpl extends ServiceImpl<TargetTwoManagerEpochMapper, TargetTwoManagerEpoch> implements TargetTwoManagerEpochService {
    @Lazy
    @Autowired
    private TargetTwoManagerService targetTwoManagerService;

    @Lazy
    @Autowired
    private AppraiseAnalyseService appraiseAnalyseService;

    @Override
    public TargetTwoManagerEpoch getEpochByTime(String targetId, Date date) {
        if (StringUtils.isBlank(targetId)) {
            throw new NullPointerException();
        }
        return this.list(Wrappers.<TargetTwoManagerEpoch>lambdaQuery()
                .eq(TargetTwoManagerEpoch::getTargetId, targetId)
                .le(Objects.nonNull(date), TargetTwoManagerEpoch::getCreateTime, date)
                .orderByDesc(TargetTwoManagerEpoch::getCreateTime)).stream().findFirst().orElse(null);
    }

    @Override
    public boolean fastForwardInUse(final String targetId) {
        TargetTwoManager targetTwoManager = targetTwoManagerService.getById(targetId);
        return Objects.nonNull(targetTwoManager) &&
                appraiseAnalyseService.count(Wrappers.<AppraiseAnalyse>lambdaQuery()
                        .eq(AppraiseAnalyse::getTargetTwoManagerId, targetId)
                        .ge(AppraiseAnalyse::getCreateTime, targetTwoManager.getUpdateTime())) > 0;
    }
}
