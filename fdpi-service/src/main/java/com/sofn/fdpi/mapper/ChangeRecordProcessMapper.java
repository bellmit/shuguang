package com.sofn.fdpi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.fdpi.model.ChangeRecordProcess;
import com.sofn.fdpi.vo.ChangeRecordProcessVo;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Auther: wenjunyun
 * @Date: 2019/12/30 09:40
 */
public interface ChangeRecordProcessMapper extends BaseMapper<ChangeRecordProcess> {
    void saveChangeRecordProcess(ChangeRecordProcess changeRecordProcess);
    void changeCompanySpecies(Map map);
    void insertCompanySpecies(Map map);
    void saveCompanySpeciesProcess(Map map);
    List<ChangeRecordProcessVo> listProcess(Map<String,Object> map);
    List<ChangeRecordProcessVo> getProcess(Map<String,Object> map);
}
