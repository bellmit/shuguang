package com.sofn.agzirdd.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sofn.agzirdd.mapper.InvestigatExaminaRecordMapper;
import com.sofn.agzirdd.model.InvestigatExaminaRecord;
import com.sofn.agzirdd.model.MonitorExaminaRecord;
import com.sofn.agzirdd.service.InvestigatExaminaRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Description: 物种调查模块-审核信息
 * @Author: mcc
 * @Date: 2020\3\13 0013
 */
@Service
public class InvestigatExaminaRecordServiceImpl extends ServiceImpl<InvestigatExaminaRecordMapper, InvestigatExaminaRecord> implements InvestigatExaminaRecordService {

    @Autowired
    private InvestigatExaminaRecordMapper investigatExaminaRecordMapper;

    @Override
    public List<InvestigatExaminaRecord> getInvestigatExaminaRecordByCondition(Map<String, Object> params) {
        return investigatExaminaRecordMapper.getInvestigatExaminaRecordByCondition(params);
    }

    @Override
    public void addInvestigatExaminaRecord(InvestigatExaminaRecord investigatExaminaRecord) {
        this.save(investigatExaminaRecord);
    }

    @Override
    public void updateInvestigatExaminaRecord(InvestigatExaminaRecord investigatExaminaRecord) {
        this.updateById(investigatExaminaRecord);
    }

    @Override
    public boolean removeInvestigatExaminaRecord(String speciesInvestgatId) {
        return investigatExaminaRecordMapper.deleteInvestigatExaminaRecord(speciesInvestgatId);
    }
}
