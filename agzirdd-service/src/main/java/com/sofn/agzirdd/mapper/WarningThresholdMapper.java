package com.sofn.agzirdd.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.agzirdd.excelmodel.WarningThresholdExcel;
import com.sofn.agzirdd.model.WarningThreshold;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface WarningThresholdMapper extends BaseMapper<WarningThreshold> {
    int deleteByPrimaryKey(String id);

    WarningThreshold selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(WarningThreshold record);

    int updateByPrimaryKey(WarningThreshold record);

    List<WarningThreshold> getWarningThresholdByCondition(Map<String,Object> params);

    List<WarningThresholdExcel> getWarningThresholdExcelByCondition(Map<String,Object> params);

}