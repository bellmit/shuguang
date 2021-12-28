package com.sofn.fdpi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.common.utils.PageUtils;
import com.sofn.fdpi.model.SignboardProcess;
import com.sofn.fdpi.vo.SignboardProcessForm;
import com.sofn.fdpi.vo.SignboardProcessVo;

import java.util.List;
import java.util.Map;


public interface SignboardProcessService extends IService<SignboardProcess> {

    /**
     * 新增流程
     */
    SignboardProcess insertSignboardProcess(SignboardProcessForm form);

    /**
     * 分页查询流程
     */
    PageUtils<SignboardProcessVo> listPage(Map<String, Object> params, Integer pageNo, Integer pageSize);

    /**
     * 列表查询流程
     */
    List<SignboardProcessVo> listSignboardProcess(Map<String, Object> params);

    /**
     * 列表查询流程
     */
    List<SignboardProcessVo> listSignboardProcess(String applyId);

    void delByApplyIdAndStatus(String applyId, String status);

    int delByApplyIds(List<String> applyIds);
}
