package com.sofn.fdpi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.common.utils.PageUtils;
import com.sofn.fdpi.model.SignboardApply;
import com.sofn.fdpi.vo.*;

import java.util.List;
import java.util.Map;

public interface SignboardApplyService extends IService<SignboardApply> {

    SignboardApply insertSignboardApply(SignboardApplyForm signboardApplyForm);

    void updateSignboardApply(SignboardApplyForm signboardApplyForm);

    void deleteLogic(String id);

    SignboardApplyVo getSignboardApply(String id);

    PageUtils<SignboardApplyListVo> getSignboardApplyList(String id, String code, Integer pageNo, Integer pageSize);

    PageUtils<SignboardApplyVo> listPage(Map<String, Object> params, Integer pageNo, Integer pageSize);

    /**
     * 正在配发中的数量
     */
    Integer getApplyNum(String compId, String speId, String signboardType);

    /**
     * 更新流程状态和审核意见
     */
    void updateProcessStatusAndAdvice(String id, String processStatus, String lastAdvice);

    /**
     * 审核标识
     */
    void audit(SignboardProcessForm signboardProcessForm);

    /**
     * 上报
     */
    void report(String id);

    /**
     * 列表查询流程
     */
    List<SignboardProcessVo> listSignboardProcess(String applyId);

    /**
     * 根据参数查询
     */
    List<SignboardApply> listByParams(Map<String, Object> params);

    /**
     * 配发标识
     */
    void allotment(AllotmentForm allotmentForm);

    /**
     * @description 标识申请撤回
     * @date 2021/4/2 16:21
     * @param id
     * @return void
     */
    void cancel(String id);

    int delByCompId(String compId);

    List<SignboardApply> listApplyByCompId(String compId);
}
