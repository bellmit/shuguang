package com.sofn.fyem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.fyem.model.ProvinceAudit;
import com.sofn.fyem.vo.*;

import java.util.List;
import java.util.Map;

public interface ProvinceAuditMapper extends BaseMapper<ProvinceAudit> {

    List<ProvinceAuditVo> listProvinceAuditsByBelongYear(Map map);

    ProvinceAudit selectByParams(Map<String, Object> params);
    List<ProvinceAudit> selectListByParams(Map<String, Object> params);

    List<ProvinceReportManagementVo> reportManagement(Map<String, Object> params);

    ProvinceAudit selectByBelongYearAndCityId(Map<String, Object> params);

    int updateStatus(Map mapOfBPR);

    int countApproveCity(Map map);

    List<ProvinceAudit> getInfoByCondition(Map<String,Object> params);
}