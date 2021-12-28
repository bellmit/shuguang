package com.sofn.fyem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sofn.common.utils.IdUtil;
import com.sofn.fyem.mapper.EvaluateStandardValueMapper;
import com.sofn.fyem.model.EvaluateStandardValue;
import com.sofn.fyem.service.EvaluateStandardValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Description: 流放指标评价分数表
 * @Author: mcc
 */
@Service
public class EvaluateStandardValueServiceimpl extends ServiceImpl<EvaluateStandardValueMapper, EvaluateStandardValue> implements EvaluateStandardValueService {

    @Autowired
    private EvaluateStandardValueMapper evaluateStandardValueMapper;

    @Override
    public List<EvaluateStandardValue> getEvaluateStandardValueByQuery(Map<String, Object> param) {

        return evaluateStandardValueMapper.getEvaluateStandardValueByQuery(param);
    }

    @Override
    public void addEvaluateStandardValue(EvaluateStandardValue evaluateStandardValue) {

        evaluateStandardValue.setId(IdUtil.getUUId());
        this.save(evaluateStandardValue);
    }

    @Override
    public void updateEvaluateStandardValue(EvaluateStandardValue evaluateStandardValue) {

        this.updateById(evaluateStandardValue);
    }

    @Override
    public int batchSaveEvaluateStandardValue(List<EvaluateStandardValue> valueList) {
        return evaluateStandardValueMapper.batchSaveEvaluateStandardValue(valueList);
    }

    @Override
    public int batchDeleteEvaluateStandardValue(Map<String,Object> param) {
        return evaluateStandardValueMapper.batchDeleteEvaluateStandardValue(param);
    }
}
