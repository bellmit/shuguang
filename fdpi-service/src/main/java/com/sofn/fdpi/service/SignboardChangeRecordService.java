package com.sofn.fdpi.service;

import com.sofn.common.utils.PageUtils;
import com.sofn.fdpi.model.SignboardChangeRecord;
import com.sofn.fdpi.vo.*;

import java.util.Map;

public interface SignboardChangeRecordService {

    /**
     * 新增标识变更记录
     */
    SignboardChangeRecord insertSignboardChangeRecord(SignboardChangeRecordVo signboardChangeRecordVo);

    /**
     * 分页查询
     */
    PageUtils<SignboardChangeRecordVo> listPage(Map<String, Object> params, Integer pageNo, Integer pageSize);

    int delByCompId(String compId);
}
