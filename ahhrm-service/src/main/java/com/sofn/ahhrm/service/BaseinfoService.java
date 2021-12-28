package com.sofn.ahhrm.service;

import com.sofn.ahhrm.vo.*;
import com.sofn.common.utils.PageUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public interface BaseinfoService {

    /**
     * 新增
     */
    BaseinfoVo save(BaseinfoForm form);


    /**
     * 删除
     */
    void delete(String id);

    /**
     * 更新
     */
    void update(BaseinfoForm form);

    /**
     * 详情
     */
    BaseinfoVo get(String id);

    /**
     * 分页查询
     */
    PageUtils<BaseinfoVo> listPage(Map<String, Object> params, Integer pageNo, Integer pageSize);

    /**
     * 撤回
     */
    void cancel(String id);

    /**
     * 审核通过
     */
    void auditPass(String id, String auditOpinion);

    /**
     * 审核退回
     */
    void auditReturn(String id, String auditOpinion);

    /**
     * 导出
     */
    void export(Map<String, Object> params, HttpServletResponse response);

    /**
     * 获取年份
     */
    List<DropDownVo> getYears();

    /**
     * 监测点名称
     */
    List<DropDownVo> getPointNames();

    /**
     * 获取最后一次填报基础信息
     */
    BaseinfoLastVo getLastCommit();
}
