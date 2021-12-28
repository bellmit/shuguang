package com.sofn.fdpi.service;

import com.alibaba.druid.sql.ast.SQLPartitionValue;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.common.utils.PageUtils;
import com.sofn.fdpi.model.Sturgeon;
import com.sofn.fdpi.vo.SelectVo;
import com.sofn.fdpi.vo.SturgeonForm;
import com.sofn.fdpi.vo.SturgeonProcessVo;
import com.sofn.fdpi.vo.SturgeonVo;

import java.util.List;
import java.util.Map;

public interface SturgeonService extends IService<Sturgeon> {

    /**
     * 新增
     */
    SturgeonVo save(SturgeonForm form);

    /**
     * 删除
     */
    void delete(String id);

    /**
     * 更新
     */
    void update(SturgeonForm form);

    /**
     * 详情
     */
    SturgeonVo get(String id);

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
    PageUtils<SturgeonVo> listPage(Map<String, Object> params, Integer pageNo, Integer pageSize);

    /**
     * 上报
     */
    void report(String id);

    /**
     * 获取证书编号下拉数据
     */
    List<SelectVo> getCredentials();

    /**
     * 列表查询流程
     */
    List<SturgeonProcessVo> listSturgeonProcess(String applyId);

    /**
     * @description 标识申请撤回
     * @date 2021/4/2 10:01
     * @param id
     * @return void
     */
    void cancel(String id);

    /**
     * 根据更改申请流程状态
     */
    void updateApplyStatus();

    List<SelectVo> getCitesList();

    String getApplyType(String id);

    /**
     * 更改申请流程状态
     */
    void updateProcessStatus(String id, String status);

    List<Sturgeon> listApplyByCompId(String compId);
}
