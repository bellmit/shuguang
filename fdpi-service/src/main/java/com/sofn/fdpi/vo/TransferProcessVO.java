/**
 * @Author 文俊云
 * @Date 2020/3/18 14:05
 * @Version 1.0
 */
package com.sofn.fdpi.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Author 文俊云
 * @Date 2020/3/18 14:05
 * @Version 1.0
 */

@Data
public class TransferProcessVO {
    @ApiModelProperty("物种Id")
    private String speId;
    @ApiModelProperty("物种名称")
    private String speName;
    @ApiModelProperty("增加企业ID")
    private String addCompanyId;
    @ApiModelProperty("增加企业名称")
    private String addCompanyName;
    @ApiModelProperty("减少企业id")
    private String reduceCompanyId;
    @ApiModelProperty("减少企业名称")
    private String reduceCompanyName;
    @ApiModelProperty("物种数目")
    private String speNum;
    @ApiModelProperty("申请时间")
    private Date createTime;
    @ApiModelProperty("操作内容")
    private String opContent;
    @ApiModelProperty("审核意见")
    private String advice;
    @ApiModelProperty("操作人")
    private String opUserName;
    @ApiModelProperty("申请单号")
    private String applyCode;
    @ApiModelProperty("操作时间")
    private Date opTime;
}
