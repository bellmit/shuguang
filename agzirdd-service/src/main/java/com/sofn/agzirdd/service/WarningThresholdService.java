package com.sofn.agzirdd.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.agzirdd.excelmodel.WarningThresholdExcel;
import com.sofn.agzirdd.model.WarningThreshold;
import com.sofn.agzirdd.vo.WarningThresholdVo;
import com.sofn.common.model.Result;
import com.sofn.common.model.User;
import com.sofn.common.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 *
 */
public interface WarningThresholdService extends IService<WarningThreshold> {

    /**
     * 根据查询条件查询预警阈值分页列表
     */
    PageUtils<WarningThreshold> getWarningThresholdByPage(Map<String, Object> params, int pageNo, int pageSize);

    /**
     * 新增预警阈值信息
     */
    Result addWarningThreshold(User userInfo, WarningThresholdVo warningThresholdVo);
    /**
     * 根据id获取预警阈值信息
     */
    WarningThresholdVo getWarningThresholdVoById(String id);
    /**
     * 删除预警阈值信息
     */
    Result deleteWarningThreshold(String id);

    /**
     * 修改预警阈值信息
     */
    Result updateWarningThreshold(WarningThresholdVo warningThresholdVo);
    /**
     * 获取预警阈值信息列表
     */
    List<WarningThresholdExcel> getWTEByCondition(Map<String, Object> params);

}
