package com.sofn.agzirdd.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.agzirdd.model.EnvironmentalFactor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Description: 入侵生物监测点环境因子信息Mapper
 * @Author: mcc
 * @Date: 22020\3\10 0010
 */
@Mapper
public interface EnvironmentalFactorMapper extends BaseMapper<EnvironmentalFactor> {

    /**
     * 获取满足条件的入侵生物监测点环境因子信息List
     * @param params 监测点名称(id),土壤温度,土壤湿度,开始时间,结束时间
     * @return List<EnvironmentalFactor>
     */
    List<EnvironmentalFactor> getEnvironmentalFactorByCondition(Map<String,Object> params);

    /**
     * 获取指定id的入侵生物监测点环境因子信息
     * @param id id
     * @return EnvironmentalFactor
     */
    EnvironmentalFactor getEnvironmentalFactorById(String id);

    /**
     * 修改监测点基本信息状态
     * @param params params
     */
    void updateStatus(Map<String,Object> params);

}