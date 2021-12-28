package com.sofn.agzirdd.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.agzirdd.model.CollectionMicrobeSpecimen;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Description: 物种采集模块-微生物标本
 * @Author: mcc
 * @Date: 2020\3\17 0017
 */
@Mapper
public interface CollectionMicrobeSpecimenMapper extends BaseMapper<CollectionMicrobeSpecimen> {

    /**
     * 获取满足条件的微生物标本内容数据
     * @param params params
     * @return List
     */
    List<CollectionMicrobeSpecimen> getCollectionMicrobeSpecimenByQuery(Map<String,Object> params);

    /**
     * 获取specimenCollectionId-微生物标本
     * @param specimenCollectionId specimenCollectionId
     * @return InvestigatContent
     */
    CollectionMicrobeSpecimen getCollectionMicrobeSpecimenBySpecimenCollectionId(@Param(value="specimenCollectionId")String specimenCollectionId);

    /**
     * 删除specimenCollectionId-微生物标本
     * @param specimenCollectionId specimenCollectionId
     * @return true or false
     */
    boolean deleteCollectionMicrobeSpecimen(String specimenCollectionId);
}