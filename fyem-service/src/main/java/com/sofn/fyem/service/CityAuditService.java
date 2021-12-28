package com.sofn.fyem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.common.utils.PageUtils;
import com.sofn.fyem.model.CityAudit;
import com.sofn.fyem.vo.CityAuditVo;
import com.sofn.fyem.vo.CityRemarkVo;
import com.sofn.fyem.vo.CityReportManagementVo;
import com.sofn.fyem.vo.CountyRemarkVo;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: DJH
 * @Date: 2020/4/27 14:44
 */
public interface CityAuditService extends IService<CityAudit> {

    /**
     * 市级审核数据展示
     * @param params
     * @return
     */
    List<CityAuditVo> listCityAuditsByBelongYear(Map<String, Object> params);

    /**
     * 市级审核数据展示（分页）
     * @param params
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageUtils<CityAuditVo> getCityAuditsListByPage(Map<String, Object> params, int pageNo, int pageSize);

    /**
     * 市级审核数据新增
     * @param cityAudit
     * @return
     */
    String insert(CityAudit cityAudit);

    /**
     * 市级上报管理展示
     * @param params
     * @return
     */
    List<CityReportManagementVo> reportManagement(Map<String, Object> params);

    /**
     * 市级审核通过
     * @param params
     * @return
     */
    String cityAuditApprove(Map<String, Object> params);

    /**
     * 市级审核驳回
     * @param params
     * @return
     */
    String cityAuditReject(Map<String, Object> params);

    /**
     * 市级上报
     * @param params
     * @return
     */
    String cityAuditReport(Map<String, Object> params);

    /**
     * 市级审核数据查看
     * @param params
     * @return
     */
    Map<String, Object> view(Map<String, Object> params);

    /**
     * 填写驳回意见
     * @param cityRemarkVo
     * @return
     */
    String editRemark(CityRemarkVo cityRemarkVo);

    /**
     * 查看驳回意见
     * @param cityViewParams
     * @return
     */
    CountyRemarkVo getRemark(Map<String, Object> cityViewParams);

    /**
     * 市级上报管理展示(分页)
     * @param cityViewParams
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageUtils<CityReportManagementVo> reportManagementByPage(Map<String, Object> cityViewParams, int pageNo, int pageSize);
}
