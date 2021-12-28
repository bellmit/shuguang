package com.sofn.fdpi.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 标识流程类
 **/
@Data
@TableName("STURGEON_PROCESS")
public class SturgeonProcess {

    /**
     * 主键
     */
    private String id;
    /**
     * 申请ID
     */
    private String applyId;
    /**
     * 操作人
     */
    private String person;
    /**
     * 状态
     */
    private String status;
    /**
     * 审核意见
     */
    private String advice;
    /**
     * 操作时间
     */
    private Date conTime;

}
