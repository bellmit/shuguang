package com.sofn.agzirdd.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.agzirdd.model.DistributionMap;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface DistributionMapMapper  extends BaseMapper<DistributionMap> {
    int deleteByPrimaryKey(String id);

    DistributionMap selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(DistributionMap record);

    int updateByPrimaryKey(DistributionMap record);

    List<DistributionMap> selectDuringOneYear();

    List<DistributionMap> selectByConditions(Map<String, Object> conditions);

    int removeBySpecInveId(String id);
}