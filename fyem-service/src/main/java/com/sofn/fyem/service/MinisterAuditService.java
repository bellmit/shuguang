package com.sofn.fyem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.common.utils.PageUtils;
import com.sofn.fyem.model.MinisterAudit;
import com.sofn.fyem.vo.MinisterAuditVo;
import com.sofn.fyem.vo.MinisterRemarkVo;
import com.sofn.fyem.vo.ProvinceAuditVo;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: DJH
 * @Date: 2020/4/27 15:06
 */
public interface MinisterAuditService extends IService<MinisterAudit> {

    /**
     *  部级审核数据展示
     * @param params
     * @return
     */
    List<MinisterAuditVo> listMinisterAuditsByBelongYear(Map<String, Object> params);

    /**
     * 部级审核数据展示（分页）
     * @param params
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageUtils<MinisterAuditVo> getMinisterAuditsListByPage(Map<String, Object> params, int pageNo, int pageSize);

    /**
     *  部级审核数据新增
     * @param ministerAudit
     * @return
     */
    String insert(MinisterAudit ministerAudit);

    /**
     *  部级审核数据查看
     * @param params
     * @return
     */
    List<ProvinceAuditVo> view(Map<String, Object> params);

    /**
     * 部级审核通过
     * @param params
     * @return
     */
    String ministerAuditApprove(Map<String, Object> params);

    /**
     * 部级审核驳回
     * @param params
     * @return
     */
    String ministerAuditReject(Map<String, Object> params);

    /**
     * 获取满足条件的数据
     * @param params
     * @return
     */
    MinisterRemarkVo getRemark(Map<String, Object> params);

    /**
     * 填写驳回意见
     * @param ministerRemarkVo
     * @return
     */
    String editRemark(MinisterRemarkVo ministerRemarkVo);
}
