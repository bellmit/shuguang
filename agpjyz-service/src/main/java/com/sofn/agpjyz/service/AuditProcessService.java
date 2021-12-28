package com.sofn.agpjyz.service;

import com.sofn.agpjyz.model.AuditProcess;
import com.sofn.agpjyz.vo.AuditProcessVo;

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
     * 根据资源ID查找
     */
    List<AuditProcessVo> listBySourceId(String sourceId);
}
