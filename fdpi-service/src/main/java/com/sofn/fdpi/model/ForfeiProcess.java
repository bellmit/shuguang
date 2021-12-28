package com.sofn.fdpi.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sofn.common.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@TableName("FORFEI_PROCESS")
@ApiModel(value = "罚没流程model")
@Data
public class ForfeiProcess extends BaseModel<ForfeiProcess> {
    /**
     * 流程ID
     */
    @ApiModelProperty(value = "流程ID")
    private String id;

    /**
     * 罚没ID
     */
    @ApiModelProperty(value = "罚没ID")
    private String ffId;

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
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(
            pattern = "yyyy-MM-dd",
            timezone = "GMT+8")
    private Date conTime;
}

