package com.sofn.fdpi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.common.utils.PageUtils;
import com.sofn.fdpi.model.SturgeonPaper;
import com.sofn.fdpi.vo.SturgeonPaperForm;
import com.sofn.fdpi.vo.SturgeonPaperVo;
import com.sofn.fdpi.vo.SturgeonProcessVo;

import java.util.List;
import java.util.Map;

public interface SturgeonPaperService extends IService<SturgeonPaper> {
    /**
     * 新增
     */
    SturgeonPaperVo save(SturgeonPaperForm form);

    /**
     * 删除
     */
    void delete(String id);

    /**
     * 更新
     */
    void update(SturgeonPaperForm form);

    /**
     * 详情
     */
    SturgeonPaperVo get(String id);

    /**
     * 审核通过
     */
    void auditPass(String id);

    /**
     * 审核退回
     */
    void auditReturn(String id, String opinion);

    /**
     * 分页查询
     */
    PageUtils<SturgeonPaperVo> listPage(Map<String, Object> params, Integer pageNo, Integer pageSize);

    /**
     * 上报
     */
    void report(String id);

    /**
     * 填写快递信息
     */
    void express(String id, String express);

    /**
     * 列表查询流程
     */
    List<SturgeonProcessVo> listSturgeonProcess(String applyId);

    /**
     * 撤回
     */
    void cancel(String id);

    String getApplyType(String id);
}
