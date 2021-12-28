package com.sofn.agzirdd.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.agzirdd.model.PhysiologicalParameters;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


/**
 * @Description: 入侵生物监测点植被生理参数信息Mapper
 * @Author: mcc
 * @Date: 2020\3\5 0005
 */
@Mapper
public interface PhysiologicalParametersMapper extends BaseMapper<PhysiologicalParameters> {


    /**
     * 获取满足条件的入侵生物植被生理参数信息List
     * @param params 监测点名称,叶面积指数,状态,开始时间,结束时间
     * @return List<PhysiologicalParameters>
     */
    List<PhysiologicalParameters> getPhysiologicalParametersByCondition(Map<String,Object> params);

    /**
     * 获取指定id的入侵生物植被生理参数信息
     * @param id id
     * @return PhysiologicalParameters
     */
    PhysiologicalParameters getPhysiologicalParametersById(String id);

    /**
     * 修改入侵生物植被生理参数信息状态
     * @param params params
     */
    void updateStatus(Map<String,Object> params);

}