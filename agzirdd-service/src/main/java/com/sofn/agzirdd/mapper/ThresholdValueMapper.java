package com.sofn.agzirdd.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.agzirdd.model.ThresholdValue;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ThresholdValueMapper extends BaseMapper<ThresholdValue> {
    int deleteByPrimaryKey(String id);

    ThresholdValue selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ThresholdValue record);

    int updateByPrimaryKey(ThresholdValue record);

    List<ThresholdValue> getThresholdValueByWtId(String wtId);

    int deleteByWtId(String wtId);
}