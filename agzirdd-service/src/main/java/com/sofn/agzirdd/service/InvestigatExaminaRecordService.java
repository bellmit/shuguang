package com.sofn.agzirdd.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.agzirdd.model.InvestigatExaminaRecord;

import java.util.List;
import java.util.Map;

/**
 * @Description: 物种调查模块-审核记录
 * @Author: mcc
 * @Date: 2020\3\12 0012
 */
public interface InvestigatExaminaRecordService extends IService<InvestigatExaminaRecord> {

    /**
     * 获取满足条件的物种调查模块-审核记录
     * @param params 物种调查表id
     * @return List<InvestigatExaminaRecord>
     */
    List<InvestigatExaminaRecord> getInvestigatExaminaRecordByCondition(Map<String,Object> params);


    /**
     * 新增物种调查模块-审核记录
     * @param investigatExaminaRecord investigatExaminaRecord
     */
    void addInvestigatExaminaRecord(InvestigatExaminaRecord investigatExaminaRecord);

    /**
     * 修改物种调查模块-审核记录
     * @param investigatExaminaRecord investigatExaminaRecord
     */
    void updateInvestigatExaminaRecord(InvestigatExaminaRecord investigatExaminaRecord);

    /**
     * 删除指定 speciesInvestgatId 物种调查模块-审核记录
     * @param speciesInvestgatId speciesInvestgatId
     * @return true or false
     */
    boolean removeInvestigatExaminaRecord(String speciesInvestgatId);

}
