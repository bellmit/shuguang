package com.sofn.fdpi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.fdpi.model.PapersYearInspectProcess;
import com.sofn.fdpi.vo.AuditProcessVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 证书年审表操作记录表
 */
public interface PapersYearInspectProcessMapper extends BaseMapper<PapersYearInspectProcess> {
    //获取证书年审审核意见信息
    PapersYearInspectProcess getProcessByInspectId(Map<String,Object> map);
    //证书年审审核意见列表
    List<PapersYearInspectProcess> listByCondition(Map<String,Object> map);

    List<AuditProcessVo> listForAuditProcessByInspectId(@Param("inspectId") String inspectId);

    List<PapersYearInspectProcess> listByConditionByInfo(Map<String, Object> map);
}
