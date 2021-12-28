package com.sofn.fyem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.fyem.model.MinisterAudit;
import com.sofn.fyem.vo.MinisterAuditVo;

import java.util.List;
import java.util.Map;

public interface MinisterAuditMapper extends BaseMapper<MinisterAudit> {

    MinisterAudit selectByParams(Map<String, Object> params);
    List<MinisterAudit> selectListByParams(Map<String, Object> params);

    List<MinisterAuditVo> listMinisterAuditsByBelongYear(Map map);

    MinisterAudit selectByBelongYearAndProvId(Map<String, Object> params);

    int updateStatus(Map mapOfBPR);

    List<MinisterAudit> getInfoByCondition(Map<String,Object> params);
}