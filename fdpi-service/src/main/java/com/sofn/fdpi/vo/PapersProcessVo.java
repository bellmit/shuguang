package com.sofn.fdpi.vo;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
/**
 * @Description:  证书审核流程记录
 * @Author: wuXy
 * @Date: 2020-1-3 16:41:37
 */
@Data
@ApiModel("证书绑定进度对象")
public class PapersProcessVo implements Serializable {
    @ApiModelProperty("主键id")
    private String id;
    @ApiModelProperty("证书id")
    private String papersId;
    @ApiModelProperty("证书编号")
    private String papersNumber;
    @ApiModelProperty("物种")
    private String issueSpe;
    @ApiModelProperty("申请时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date applyTime;
    @ApiModelProperty("审核状态")
    private String parStatusName;
    @ApiModelProperty("审核意见")
    private String advice;
    @ApiModelProperty("操作人")
    private String personName;
    @ApiModelProperty("操作时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date operationTime;
    @ApiModelProperty(  "申请单号")
    private String applyNum;

}
