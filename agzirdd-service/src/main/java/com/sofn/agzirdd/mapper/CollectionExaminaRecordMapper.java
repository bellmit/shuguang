package com.sofn.agzirdd.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.agzirdd.model.CollectionExaminaRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Description: 物种采集模块-审核记录
 * @Author: mcc
 * @Date: 2020\3\17 0017
 */
@Mapper
public interface CollectionExaminaRecordMapper extends BaseMapper<CollectionExaminaRecord> {

    /**
     * 获取物种采集模块-审核记录List
     * @param params 物种调查表id
     * @return List<CollectionExaminaRecord>
     */
    List<CollectionExaminaRecord> getCollectionExaminaRecordByCondition(Map<String,Object> params);


    /**
     * 删除specimenCollectionId-监测审核记录
     * @param specimenCollectionId specimenCollectionId
     * @return true or false
     */
    boolean deleteCollectionExaminaRecord(String specimenCollectionId);
}