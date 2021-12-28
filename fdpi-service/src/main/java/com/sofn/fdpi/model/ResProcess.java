package com.sofn.fdpi.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sofn.common.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
@TableName("RES_PROCESS")
@ApiModel(value = "收容流程model")
@Data
public class ResProcess extends BaseModel<ResProcess> {
    /**
     * 流程ID
     */
    @ApiModelProperty(value = "流程ID")
    private String id;

    /**
     * 收容ID
     */
    @ApiModelProperty(value = "收容ID")
    private String resId;

    /**
     * 操作人
     */
    @ApiModelProperty(value = "操作人")
    private String person;

    /**
     * 操作内容
     */
    @ApiModelProperty(value = "操作内容")
    private String content;

    /**
     * 审核状态
     */
    @ApiModelProperty(value = "审核状态")
    private int status;

    /**
     * 审核意见
     */
    @ApiModelProperty(value = "审核意见")
    private String advice;

    /**
     * 操作时间
     */
    @ApiModelProperty(value = "操作时间")
    private Date conTime;
}

