package com.sofn.fdpi.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sofn.common.model.BaseModel;
import lombok.Data;

import java.util.Date;

/**
 * 标识流程类
 *
 * @Author yumao
 * @Date 2019/12/31 9:10
 **/
@Data
@TableName("SIGNBOARD_PROCESS")
public class SignboardProcess extends BaseModel<SignboardProcess> {

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
     * 操作编码(换、补、注销时需要)
     */
    private String code;
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

    /**
     * 物种名称
     */
    @TableField(exist = false)
    private String speName;

    /**
     * 申请类型
     */
    @TableField(exist = false)
    private String applyType;

    /**
     * 申请时间
     */
    @TableField(exist = false)
    private Date applyTime;

    /**
     * 申请数量
     */
    @TableField(exist = false)
    private Integer applyNum;

    /**
     * 申请单号
     */
    private String applyCode;

}
