package com.sofn.fyem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.common.utils.PageUtils;
import com.sofn.fyem.model.ProvinceAudit;
import com.sofn.fyem.vo.*;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: DJH
 * @Date: 2020/4/27 14:56
 */
public interface ProvinceAuditService extends IService<ProvinceAudit> {

    /**
     * 省级审核数据展示
     * @param params
     * @return
     */
    List<ProvinceAuditVo> listProvinceAuditsByBelongYear(Map<String, Object> params);

    /**
     * 省级审核数据展示（分页）
     * @param params
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageUtils<ProvinceAuditVo> getProvinceAuditsListByPage(Map<String, Object> params, int pageNo, int pageSize);

    /**
     * 省级审核数据新增
     * @param provinceAudit
     * @return
     */
    String insert(ProvinceAudit provinceAudit);

    /**
     * 省级上报管理展示
     * @param params
     * @return
     */
    List<ProvinceReportManagementVo> reportManagement(Map<String, Object> params);

    /**
     * 省级上报管理查看
     * @param params
     * @return
     */
    List<CityAuditVo> view(Map<String, Object> params);

    /**
     * 省级审核通过
     * @param params
     * @return
     */
    String provinceAuditApprove(Map<String, Object> params);

    /**
     * 省级审核驳回
     * @param params
     * @return
     */
    String provinceAuditReject(Map<String, Object> params);

    /**
     * 省级上报
     * @param params
     * @return
     */
    String provinceAuditReport(Map<String, Object> params);

    /**
     * 获取满足条件的数据
     * @param params
     * @return
     */
    CityRemarkVo getRemark(Map<String, Object> params);

    /**
     * 填写驳回意见
     * @param provinceRemarkVo
     * @return
     */
    String editRemark(ProvinceRemarkVo provinceRemarkVo);

    /**
     * 省级上报管理展示（分页）
     * @param provViewParams
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageUtils<ProvinceReportManagementVo> reportManagementByPage(Map<String, Object> provViewParams, int pageNo, int pageSize);
}
