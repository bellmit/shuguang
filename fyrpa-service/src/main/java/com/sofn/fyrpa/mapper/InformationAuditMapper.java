package com.sofn.fyrpa.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.fyrpa.mapper.InformationAuditMapper;
import com.sofn.fyrpa.model.InformationAudit;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InformationAuditMapper extends BaseMapper<InformationAudit> {

    /**
     * 审批意见查询
     * @param protectionInfoId
     * @return
     */
    List<InformationAudit> selectListData(@Param("protectionInfoId")String protectionInfoId);

    /**
     * 通过id查询已驳回信息
     * @param protectionInfoId
     * @return
     */
    List<InformationAudit> selectListDataById(@Param("protectionInfoId")String protectionInfoId);
}