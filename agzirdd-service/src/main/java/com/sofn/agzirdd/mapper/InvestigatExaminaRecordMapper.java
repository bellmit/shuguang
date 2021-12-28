package com.sofn.agzirdd.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.agzirdd.model.InvestigatExaminaRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Description: 物种调查模块-审核记录mapper
 * @Author: mcc
 * @Date: 2020\3\12 0012
 */
@Mapper
public interface InvestigatExaminaRecordMapper extends BaseMapper<InvestigatExaminaRecord> {

    /**
     * 获取满足条件的物种调查模块-监测审核记录List
     * @param params 物种监测表id
     * @return List<InvestigatExaminaRecord>
     */
    List<InvestigatExaminaRecord> getInvestigatExaminaRecordByCondition(Map<String,Object> params);


    /**
     * speciesInvestgatId-监测审核记录
     * @param speciesInvestgatId speciesInvestgatId
     * @return true or false
     */
    boolean deleteInvestigatExaminaRecord(String speciesInvestgatId);

}