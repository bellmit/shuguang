/**
 * @Author 文俊云
 * @Date 2019/12/30 10:24
 * @Version 1.0
 */
package com.sofn.fdpi.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Author 文俊云
 * @Date 2019/12/30 10:24
 * @Version 1.0
 */

@Data
public class ChangeRecordProcess {
    @ApiModelProperty("主键")
    private String id;
    @ApiModelProperty("变更记录ID")
    private String changeRecordId;
    @ApiModelProperty("操作人")
    private String opUserId;
    @ApiModelProperty("操作")
    private String content;
    @ApiModelProperty("状态0:保存；1：上报；2：初审退回；3初审通过；4：复审退回；5：复审通过；6结束")
    private String status;
    @ApiModelProperty("建议")
    private String advice;
    @ApiModelProperty("操作时间")
    private Date conTime;
    @ApiModelProperty("申请单号")
    private String applyCode;
}
