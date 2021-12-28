package com.sofn.fyem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sofn.common.utils.IdUtil;
import com.sofn.fyem.mapper.EvaluateStandardValueHistoryMapper;
import com.sofn.fyem.model.EvaluateStandardValue;
import com.sofn.fyem.model.EvaluateStandardValueHistory;
import com.sofn.fyem.service.EvaluateStandardValueHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Description: 流放指标评价分数表
 * @Author: mcc
 */
@Service
public class EvaluateStandardValueHistoryServiceimpl extends ServiceImpl<EvaluateStandardValueHistoryMapper, EvaluateStandardValueHistory> implements EvaluateStandardValueHistoryService {

    @Autowired
    private EvaluateStandardValueHistoryMapper valueHistoryMapper;

    @Override
    public List<EvaluateStandardValueHistory> getValueHistoryByQuery(Map<String, Object> param) {

        return valueHistoryMapper.getValueHistoryByQuery(param);
    }

    @Override
    public void addValueHistory(EvaluateStandardValueHistory evaluateStandardValueHistory) {

        evaluateStandardValueHistory.setId(IdUtil.getUUId());
        this.save(evaluateStandardValueHistory);
    }

    @Override
    public void updateValueHistory(EvaluateStandardValueHistory evaluateStandardValueHistory) {

        this.updateById(evaluateStandardValueHistory);
    }

    @Override
    public int batchSaveValueHistory(List<EvaluateStandardValueHistory> valueHistoryList) {
        return valueHistoryMapper.batchSaveValueHistory(valueHistoryList);
    }

    @Override
    public int batchDeleteValueHistory(Map<String,Object> param) {
        return valueHistoryMapper.batchDeleteValueHistory(param);
    }
}
