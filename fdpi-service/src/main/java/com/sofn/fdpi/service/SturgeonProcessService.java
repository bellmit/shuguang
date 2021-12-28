package com.sofn.fdpi.service;

import com.sofn.fdpi.model.SturgeonProcess;
import com.sofn.fdpi.vo.SturgeonProcessVo;

import java.util.List;


public interface SturgeonProcessService {

    /**
     * 新增流程
     */
    void insertSturgeonProcess(SturgeonProcess sturgeonProcess);

    /**
     * 列表查询流程
     * type 1 标识申请 2 标识补打申请 3标签纸申请
     */
    List<SturgeonProcessVo> listSturgeonProcess(String applyId, String type);

    int delByApplyIds(List<String> applyIds);
}
