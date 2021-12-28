package com.sofn.ahhdp.service;

import com.sofn.ahhdp.model.Zone;
import com.sofn.ahhdp.model.ZoneApply;
import com.sofn.ahhdp.model.ZoneRecord;
import com.sofn.common.utils.PageUtils;

import java.util.Map;

public interface ZoneApplyService {

    /**
     * 新增
     */
    void save(ZoneApply entity);

    /**
     * 详情
     */
    ZoneApply get(String id);

    /**
     * 申请变更
     */
    void apply(String id, String newName, String newCompany, String newRange);

    /**
     * 审核通过
     */
    void auditPass(String id, String opinion);

    /**
     * 审核不通过
     */
    void auditUnpass(String id, String opinion);

    /**
     * 分页查询变更申请
     */
    PageUtils<Zone> listByParams(Map<String, Object> params, Integer pageNo, Integer pageSize);

    /**
     *  获取详情
     */
    ZoneRecord getForManage(String id);
}
