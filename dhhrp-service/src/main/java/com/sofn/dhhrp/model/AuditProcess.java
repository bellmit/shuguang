package com.sofn.dhhrp.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 审核进程记录
 **/
@Data
@TableName("audit_process")
public class AuditProcess {

    /**
     * 主键
     */
    private String id;
    /**
     * 基础信息ID
     */
    private String baseId;
    /**
     * 状态
     */
    private String status;
    /**
     * 审核人
     */
    private String auditor;
    /**
     * 审核人名称
     */
    private String auditorName;
    /**
     * 审核时间
     */
    private Date auditTime;
    /**
     * 审核意见
     */
    private String auditOpinion;

}
