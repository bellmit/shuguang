package com.sofn.fdpi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.common.model.Result;
import com.sofn.common.utils.PageUtils;
import com.sofn.fdpi.model.PapersYearInspect;
import com.sofn.fdpi.model.PapersYearInspectProcess;
import com.sofn.fdpi.vo.PapersYearInspectForm;
import com.sofn.fdpi.vo.PapersYearInspectViewVo;
import com.sofn.fdpi.vo.PapersYearInspectVo;
import com.sofn.fdpi.vo.ProcessForm;

import java.util.List;
import java.util.Map;

public interface PapersYearInspectService extends IService<PapersYearInspect> {
    /**
     * 证书年审申请查询列表（分页）
     * wuXY
     * 2020-1-3 14:00:45
     * @param map 查询条件
     * @return 返回分页列表
     */
    PageUtils<PapersYearInspectVo> listForApply(Map<String,Object> map);

    /**
     * 获取证书年审明细信息
     * wuXY
     * 2020-1-3 17:03:10
     * @param compId 企业id
     * @param inspectId 证书年审id
     * @return 对象
     */
    PapersYearInspectViewVo getDetailById(String compId,String inspectId);
    /**
     * 新增/修改/保存上报/证书年审
     * wuXY
     * 2020-1-3 17:03:10
     * @param papersYearInspectForm 对象
     * @return 1：成功；other：异常提示
     */
    Result saveOrUpdate(PapersYearInspectForm papersYearInspectForm, boolean isReport);

//    /**
//     * 修改证书年审
//     * wuXY
//     * 2020-1-3 17:03:10
//     * @param papersYearInspectForm 对象
//     * @return 1：成功；other：异常提示
//     */
//    String update(PapersYearInspectForm papersYearInspectForm);

    /**
     * 列表中上报按钮
     * wuXY
     * 2020-1-6 19:57:14
     * @param inspectId 年审主编id
     * @return 1:成功；其他：异常提示
     */
    String report(String inspectId);

    /**
     * 列表中删除
     * wuXY
     * 2020-1-6 19:57:14
     * @param inspectId 年审主编id
     * @return 1:成功；其他：异常提示
     */
    String deleteInspect(String inspectId);

    /**
     * 证书年审审核
     * wuXY
     * 2020-1-6 19:57:14
     * @param processForm 审核表单
     * @param isApprove 1:审核，0：退回
     * @return 1：成功；其它：提示
     */
    String approveOrBack(ProcessForm processForm, boolean isApprove);
    /**
     * 获取审核/退回意见
     * wuXY
     * 2020-1-6 19:57:14
     * @param inspectId 年审id
     * @param status 年审状态
     * @return PapersYearInspectProcess
     */
    PapersYearInspectProcess getProcessByInspectId(String inspectId,String status);

    /**
     * 获取审核/退回意见 从流程组件获取
     * @param inspectId 年审id
     * @param status 年审状态
     * @return PapersYearInspectProcess
     */
    PapersYearInspectProcess getProcessByInspectIdInfo(String inspectId,String status);

    /**
     * 《企业查询-详细》获取企业明细信息
     * wuXY
     * 2020-1-3 17:03:10
     *
     * @param inspectId 证书年审id
     * @return 对象
     */
    PapersYearInspectViewVo getDetailByCompIdAndInspectId(String compId, String inspectId);

    /**
     * 提示年审
     */
    String promptingInspect();

    /**
     * @author wg
     * @description 年审上报撤回
     * @date 2021/4/9 9:35
     * @param id 主键
     * @return void
     */
    void cancel(String id);

    List<PapersYearInspect> listYearInspectByCompId(String compId);
}
