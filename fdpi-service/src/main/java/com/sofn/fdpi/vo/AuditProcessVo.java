package com.sofn.fdpi.vo;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
@ApiModel("证书变更审核流程操作记录对象")
public class AuditProcessVo implements Serializable {
    @ApiModelProperty("审核状态")
    private String parStatus;
    @ApiModelProperty("审核状态/操作类型中文名")
    private String parStatusName;
    @ApiModelProperty("审核意见/内容")
    private String advice;
    @ApiModelProperty("操作人")
    private String personName;
    @ApiModelProperty("操作时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date operationTime;
}
