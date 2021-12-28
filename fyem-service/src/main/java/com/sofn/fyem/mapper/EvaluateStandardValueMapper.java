package com.sofn.fyem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.fyem.model.EvaluateStandardValue;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Description: 流放指标评价分数表
 * @Author: mcc
 */
@Mapper
public interface EvaluateStandardValueMapper extends BaseMapper<EvaluateStandardValue> {

    /**
     * 获取指标评价分数
     * @param param param
     * @return return
     */
    List<EvaluateStandardValue> getEvaluateStandardValueByQuery(Map<String,Object> param);


    /**
     * 批量新增指标评价分数信息
     * @param valueList valueList
     * @return return
     */
    int batchSaveEvaluateStandardValue(@Param(value = "valueList") List<EvaluateStandardValue> valueList);

    /**
     * 批量删除满足条件的指标评价信息
     * @param params params
     * @return int
     */
    int batchDeleteEvaluateStandardValue(Map<String, Object> params);
}