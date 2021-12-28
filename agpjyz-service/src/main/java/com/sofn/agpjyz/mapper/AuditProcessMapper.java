package com.sofn.agpjyz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.agpjyz.model.AuditProcess;
import org.apache.ibatis.annotations.Param;

public interface AuditProcessMapper extends BaseMapper<AuditProcess> {

    void updateAuditOpinion(@Param("id") String id, @Param("auditOpinion") String auditOpinion);
}
