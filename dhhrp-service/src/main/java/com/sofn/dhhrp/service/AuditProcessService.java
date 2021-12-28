package com.sofn.dhhrp.service;

import com.sofn.dhhrp.model.AuditProcess;
import com.sofn.dhhrp.vo.AuditProcessVo;

import java.util.List;

/**
 * 审核进程记录服务接口
 */
public interface AuditProcessService {

    /**
     * 新增
     */
    AuditProcess save(AuditProcess entity);

    /**
     * 根据基础信息ID查找
     */
    List<AuditProcessVo> listByBaseId(String baseId);
}
