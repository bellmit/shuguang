package com.sofn.fdpi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.common.utils.PageUtils;
import com.sofn.fdpi.model.AuditProcess;
import com.sofn.fdpi.vo.AuditProcessVo;
import com.sofn.fdpi.vo.PapersProcessVo;

import java.util.List;
import java.util.Map;

public interface AuditProcessService extends IService<AuditProcess> {
    /**
     * 证书绑定进度查询
     * wuXY
     * 2020-1-2 19:01:30
     * @param map 查询参数
     * @return 分页列表
     */
    PageUtils<PapersProcessVo> listForBindingSpeed(Map<String,Object> map);

    /**
     * 证书绑定进度查询 流程组件
     * @return 分页列表
     */
    PageUtils<PapersProcessVo> listForBindingSpeedByInfo(Map<String,Object> map);


    /**
     * 获取证书的审核流程记录列表
     * wuXY
     * 2020-11-5 15:10:51
     * @param papersId 证书id
     * @return
     */
    List<AuditProcessVo> listForAuditProcessByPapersId(String papersId);

    /**
     * 获取证书的审核流程记录列表 流程组件 流程
     * wenxin
     * @param papersId 证书id
     * @return
     */
    List<AuditProcessVo> listForAuditProcessByPapersIdY(String papersId);


    void delByPapersId(String papersId);
}
