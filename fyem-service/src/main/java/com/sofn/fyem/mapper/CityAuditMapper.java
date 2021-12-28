package com.sofn.fyem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.fyem.model.CityAudit;
import com.sofn.fyem.vo.CityAuditVo;
import com.sofn.fyem.vo.CityReportManagementVo;

import java.util.List;
import java.util.Map;

public interface CityAuditMapper extends BaseMapper<CityAudit> {

    List<CityAuditVo> listCityAuditsByBelongYear(Map map);

    List<CityReportManagementVo> reportManagement(Map map);

    int updateStatus(Map mapOfBPR);

    CityAudit selectByBelongYearAndCountyId(Map map);

    CityAudit selectByBelongYearAndCityId(Map map);

    int countApproveCounty(Map map);

    String selectProvinceIdByBelongYearAndCityId(Map map);

    CityAudit selectByParams(Map<String, Object> params);

    int insertNotExists(CityAudit cityAudit);

    List<CityAudit> getInfoByCondition(Map<String,Object> params);
}