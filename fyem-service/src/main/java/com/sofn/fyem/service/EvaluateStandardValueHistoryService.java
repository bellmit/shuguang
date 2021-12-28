package com.sofn.fyem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.fyem.model.EvaluateStandardValue;
import com.sofn.fyem.model.EvaluateStandardValueHistory;

import java.util.List;
import java.util.Map;

/**
 * @Description: 流放指标评价分数历史表
 * @Author: mcc
 */
public interface EvaluateStandardValueHistoryService extends IService<EvaluateStandardValueHistory> {

    /**
     * 获取指标评价分数
     * @param param
     * @return
     */
    List<EvaluateStandardValueHistory> getValueHistoryByQuery(Map<String, Object> param);

    /**
     * 新增指标评价分数
     * @param evaluateStandardValueHistory
     */
    void addValueHistory(EvaluateStandardValueHistory evaluateStandardValueHistory);

    /**
     * 修改指标评价分数
     * @param evaluateStandardValueHistory
     */
    void updateValueHistory(EvaluateStandardValueHistory evaluateStandardValueHistory);

    /**
     * 批量新增指标评价分数信息
     * @param valueHistoryList valueHistoryList
     * @return return
     */
    int batchSaveValueHistory(List<EvaluateStandardValueHistory> valueHistoryList);

    /**
     * 批量删除满足条件的指标评价信息
     * @param param param
     * @return int
     */
    int batchDeleteValueHistory(Map<String, Object> param);
}
