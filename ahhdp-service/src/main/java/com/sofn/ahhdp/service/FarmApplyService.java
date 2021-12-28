package com.sofn.ahhdp.service;

import com.sofn.ahhdp.model.Farm;
import com.sofn.ahhdp.model.FarmApply;
import com.sofn.ahhdp.model.FarmRecord;
import com.sofn.common.utils.PageUtils;

import java.util.Map;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-04-21 15:23
 */
public interface FarmApplyService {
    /**
     * 新增
     */
    void save(FarmApply entity);

    /**
     * 详情
     */
    FarmApply get(String id);

    /**
     * 申请变更
     */
    void apply(String id, String newName, String newCompany);

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
    PageUtils<Farm> listByParams(Map<String, Object> params, Integer pageNo, Integer pageSize);

    /**
     *  获取详情
     */
    FarmRecord getForManage(String id);
}
