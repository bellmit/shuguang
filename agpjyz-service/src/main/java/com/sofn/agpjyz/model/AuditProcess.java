package com.sofn.agpjyz.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 审核进程记录
 *
 * @Author yumao
 * @Date 2020/3/10 9:49
 **/
@Data
@TableName("AUDIT_PROCESS")
public class AuditProcess {

    /**
     * 主键
     */
    private String id;
    /**
     * 状态
     */
    private String status;
    /**
     * 审核人
     */
    private String auditor;
    /**
     * 审核人
     */
    private String auditorName;
    /**
     * 审核时间
     */
    private Date auditTime;
    /**
     * 审核数据来源
     */
    private String auditSource;
    /**
     * 来源ID
     */
    private String sourceId;
    /**
     * 审核意见
     */
    private String auditOpinion;
}
