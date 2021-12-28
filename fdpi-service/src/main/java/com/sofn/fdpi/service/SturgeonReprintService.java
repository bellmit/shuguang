package com.sofn.fdpi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.common.utils.PageUtils;
import com.sofn.fdpi.model.SturgeonReprint;
import com.sofn.fdpi.vo.SturgeonProcessVo;
import com.sofn.fdpi.vo.SturgeonReprintForm;
import com.sofn.fdpi.vo.SturgeonReprintVo;
import com.sofn.fdpi.vo.SturgeonSignboardVo;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public interface SturgeonReprintService extends IService<SturgeonReprint> {
    /**
     * 新增
     */
    SturgeonReprintVo save(SturgeonReprintForm form);

    /**
     * 删除
     */
    void delete(String id);

    /**
     * 更新
     */
    void update(SturgeonReprintForm form);

    /**
     * 详情
     */
    SturgeonReprintVo get(String id);

    /**
     * 审核通过
     */
    void auditPass(String id, String thirdPrint);

    /**
     * 审核退回
     */
    void auditReturn(String id, String opinion);

    /**
     * 分页查询
     */
    PageUtils<SturgeonReprintVo> listPage(Map<String, Object> params, Integer pageNo, Integer pageSize);

    /**
     * 上报
     */
    void report(String id);

    /**
     * 列表查询流程
     */
    List<SturgeonProcessVo> listSturgeonProcess(String applyId);

    /**
     * @param id 撤回所需要提供的主键
     * @return void
     * @description 标识补打申请撤回
     * @date 2021/4/1 18:30
     */
    void cancel(String id);

    List<SturgeonSignboardVo> listRepring(String compId, List<String> signboardIds);

    void print(String id);

    void export(String id, HttpServletResponse response);

    String getContractSequenceNum(String contractNum);

    String getApplyType(String id);
}
