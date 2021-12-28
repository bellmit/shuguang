package com.sofn.agzirdd.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.agzirdd.model.CollectionPlantSpecimen;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Description: 物种采集模块-植物标本采集
 * @Author: mcc
 * @Date: 2020\3\17 0017
 */
@Mapper
public interface CollectionPlantSpecimenMapper extends BaseMapper<CollectionPlantSpecimen> {

    /**
     * 获取满足条件的植物标本内容数据
     * @param params params
     * @return List
     */
    List<CollectionPlantSpecimen> getCollectionPlantSpecimenByQuery(Map<String,Object> params);

    /**
     * 获取specimenCollectionId-植物标本采集
     * @param specimenCollectionId specimenCollectionId
     * @return InvestigatContent
     */
    CollectionPlantSpecimen getCollectionPlantSpecimenBySpecimenCollectionId(@Param(value="specimenCollectionId")String specimenCollectionId);

    /**
     * 删除specimenCollectionId-植物标本采集
     * @param specimenCollectionId specimenCollectionId
     * @return true or false
     */
    boolean deleteCollectionPlantSpecimen(String specimenCollectionId);
}