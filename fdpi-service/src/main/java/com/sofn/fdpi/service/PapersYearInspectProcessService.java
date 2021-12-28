package com.sofn.fdpi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.common.utils.PageUtils;
import com.sofn.fdpi.model.PapersYearInspectProcess;
import com.sofn.fdpi.vo.AuditProcessVo;

import java.util.List;
import java.util.Map;

public interface PapersYearInspectProcessService extends IService<PapersYearInspectProcess> {
    /**
     * 获取证书年审记录列表（分页）
     * wuXY
     * 2020-1-7 16:17:31
     * @param map 查询参数
     * @return PageUtils<PapersYearInspectProcess>
     */
    PageUtils<PapersYearInspectProcess> listByPage(Map<String,Object> map,Integer pageNo,Integer pageSize);

    /**
     * 获取证书年审记录列表（分页）流程租金按
     * wuXY
     * 2020-1-7 16:17:31
     * @param map 查询参数
     * @return PageUtils<PapersYearInspectProcess>
     */
    PageUtils<PapersYearInspectProcess> listByPageInfo(Map<String,Object> map,Integer pageNo,Integer pageSize);

    /**
     * 获取证书年审的流程记录列表
     * @param inspectId 证书年审id
     * @return 记录列表
     */
    List<AuditProcessVo> listForAuditProcessByInspectId(String inspectId);

    /**
     * 获取证书年审的流程记录列表 流程组件
     * @param inspectId 证书年审id
     * @return 记录列表
     */
    List<AuditProcessVo> listForAuditProcessByInspectIdY(String inspectId);

    int delByYearInspectIds(List<String> papersYearInspectIds);
}
