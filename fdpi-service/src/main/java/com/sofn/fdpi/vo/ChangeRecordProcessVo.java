/**
 * @Author 文俊云
 * @Date 2020/3/12 17:36
 * @Version 1.0
 */
package com.sofn.fdpi.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Author 文俊云
 * @Date 2020/3/12 17:36
 * @Version 1.0
 */

@Data
public class ChangeRecordProcessVo {

    @ApiModelProperty("物种名称")
    private String speName;
    @ApiModelProperty("变更日期")
    private Date changeDate;
    @ApiModelProperty("变更原因")
    private String changeReason;
    @ApiModelProperty("操作")
    private String content;
    @ApiModelProperty("审核意见")
    private String advice;
    @ApiModelProperty("操作人")
    private String opUserId;
    @ApiModelProperty("操作日期")
    private Date opTime;
    @ApiModelProperty("申请单号")
    private String applyCode;

}
