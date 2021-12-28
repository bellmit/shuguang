package com.sofn.ahhdp.service;

import com.sofn.ahhdp.model.Directories;
import com.sofn.ahhdp.model.DirectoriesApply;
import com.sofn.ahhdp.model.DirectoriesRecord;
import com.sofn.common.utils.PageUtils;

import java.util.Map;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-04-21 11:36
 */
public interface DirectoriesApplyService {

    /**
     * 新增
     */
    void save(DirectoriesApply entity);

    /**
     * 详情
     */
    DirectoriesApply get(String id);

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
    PageUtils<DirectoriesApply> listByParams(Map<String, Object> params, Integer pageNo, Integer pageSize);

    /**
     *  获取详情
     */
    DirectoriesRecord getForManage(String id);
}
