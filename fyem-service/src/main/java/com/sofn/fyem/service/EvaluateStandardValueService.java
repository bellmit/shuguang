package com.sofn.fyem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.fyem.model.EvaluateStandardValue;

import java.util.List;
import java.util.Map;

/**
 * @Description: 流放指标评价分数表
 * @Author: mcc
 */
public interface EvaluateStandardValueService extends IService<EvaluateStandardValue> {

    /**
     * 获取指标评价分数
     * @param param
     * @return
     */
    List<EvaluateStandardValue> getEvaluateStandardValueByQuery(Map<String,Object> param);

    /**
     * 新增指标评价分数
     * @param evaluateStandardValue
     */
    void addEvaluateStandardValue(EvaluateStandardValue evaluateStandardValue);

    /**
     * 修改指标评价分数
     * @param evaluateStandardValue
     */
    void updateEvaluateStandardValue(EvaluateStandardValue evaluateStandardValue);

    /**
     * 批量新增指标评价分数信息
     * @param valueList valueList
     * @return return
     */
    int batchSaveEvaluateStandardValue(List<EvaluateStandardValue> valueList);

    /**
     * 批量删除满足条件的指标评价信息
     * @param param param
     * @return int
     */
    int batchDeleteEvaluateStandardValue(Map<String,Object> param);
}
