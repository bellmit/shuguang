package com.sofn.agzirdd.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.agzirdd.model.CollectionExaminaRecord;

import java.util.List;
import java.util.Map;
/**
 * @Description: 物种采集模块-审核记录
 * @Author: mcc
 * @Date: 2020\3\18 0018
 */
public interface CollectionExaminaRecordService  extends IService<CollectionExaminaRecord> {

    /**
     * 获取满足条件的物种采集模块-审核记录
     * @param params 物种调查表id
     * @return List<CollectionExaminaRecord>
     */
    List<CollectionExaminaRecord> getCollectionExaminaRecordByCondition(Map<String,Object> params);


    /**
     * 新增物种采集模块-审核记录
     * @param collectionExaminaRecord collectionExaminaRecord
     */
    void addCollectionExaminaRecord(CollectionExaminaRecord collectionExaminaRecord);

    /**
     * 修改物种采集模块-审核记录
     * @param collectionExaminaRecord collectionExaminaRecord
     */
    void updateCollectionExaminaRecord(CollectionExaminaRecord collectionExaminaRecord);

    /**
     * 删除指定speciesInvestgatId物种采集模块-审核记录
     * @param specimenCollectionId specimenCollectionId
     * @return true or false
     */
    boolean removeCollectionExaminaRecord(String specimenCollectionId);
}
