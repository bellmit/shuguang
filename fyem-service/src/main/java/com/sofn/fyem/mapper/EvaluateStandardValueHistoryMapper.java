package com.sofn.fyem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.fyem.model.EvaluateStandardValue;
import com.sofn.fyem.model.EvaluateStandardValueHistory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Description: 流放指标评价分数历史表
 * @Author: mcc
 */
@Mapper
public interface EvaluateStandardValueHistoryMapper extends BaseMapper<EvaluateStandardValueHistory> {

    /**
     * 获取指标评价分数
     * @param param param
     * @return return
     */
    List<EvaluateStandardValueHistory> getValueHistoryByQuery(Map<String, Object> param);


    /**
     * 批量新增指标评价分数信息
     * @param valueHistoryList valueList
     * @return return
     */
    int batchSaveValueHistory(@Param(value = "valueHistoryList") List<EvaluateStandardValueHistory> valueHistoryList);

    /**
     * 批量删除满足条件的指标评价信息
     * @param params params
     * @return int
     */
    int batchDeleteValueHistory(Map<String, Object> params);
}