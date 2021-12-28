package com.sofn.agzirdd.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.agzirdd.model.SpecimenCollection;
import com.sofn.agzirdd.vo.SpecimenCollectionForm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Description: 物种采集模块-标本采集基本信息
 * @Author: mcc
 * @Date: 2020\3\17 0017
 */
@Mapper
public interface SpecimenCollectionMapper extends BaseMapper<SpecimenCollection> {

    /**
     * 获取物种采集模块-标本采集基本信息List
     * @param params 采集人,省id,市id,县id,状态,开始时间,结束时间
     * @return List<SpecimenCollection>
     */
    List<SpecimenCollection> getSpecimenCollectionByCondition(Map<String,Object> params);

    /**
     * 获取物种采集模块-标本采集基本信息List
     * @param params 采集人,省id,市id,县id,状态,开始时间,结束时间
     * @return List<SpecimenCollection>
     */
    List<SpecimenCollection> getSpecimenCollectionByParams(Map<String,Object> params);

    /**
     * 获取物种采集模块-标本采集基本信息FormList
     * @param params 采集人,省id,市id,县id,状态,开始时间,结束时间
     * @return List<SpecimenCollectionForm>
     */
    List<SpecimenCollectionForm> getSpecimenCollectionForm(Map<String,Object> params);

    /**
     * 获取指定id的物种采集模块-标本采集基本信息
     * @param id id
     * @return SpecimenCollection
     */
    SpecimenCollection getSpecimenCollectionById(@Param(value="id")String id);

    /**
     * 修改物种采集模块-标本采集基本信息状态
     * @param params params
     */
    void updateStatus(Map<String,Object> params);
}