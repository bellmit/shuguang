package com.sofn.agzirdd.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sofn.agzirdd.mapper.CollectionExaminaRecordMapper;
import com.sofn.agzirdd.model.CollectionExaminaRecord;
import com.sofn.agzirdd.service.CollectionExaminaRecordService;
import com.sofn.common.utils.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 * @Description: 物种采集模块-审核记录
 * @Author: mcc
 * @Date: 2020\3\18 0018
 */
@Service
public class CollectionExaminaRecordServiceImpl extends ServiceImpl<CollectionExaminaRecordMapper, CollectionExaminaRecord> implements CollectionExaminaRecordService {

    /**
     * 物种采集模块-审核记录
     */
    @Autowired
    private CollectionExaminaRecordMapper collectionExaminaRecordMapper;

    @Override
    public List<CollectionExaminaRecord> getCollectionExaminaRecordByCondition(Map<String, Object> params) {
        return collectionExaminaRecordMapper.getCollectionExaminaRecordByCondition(params);
    }

    @Override
    public void addCollectionExaminaRecord(CollectionExaminaRecord collectionExaminaRecord) {
        collectionExaminaRecord.setId(IdUtil.getUUId());
        collectionExaminaRecord.setCreateTime(new Date());
        this.save(collectionExaminaRecord);
    }

    @Override
    public void updateCollectionExaminaRecord(CollectionExaminaRecord collectionExaminaRecord) {
        this.updateById(collectionExaminaRecord);
    }

    @Override
    public boolean removeCollectionExaminaRecord(String specimenCollectionId) {
        return collectionExaminaRecordMapper.deleteCollectionExaminaRecord(specimenCollectionId);
    }
}
