package com.sofn.agzirdd.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 审核通用数据VO
 */
@ApiModel
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class AuditDataVo {
    @ApiModelProperty(name = "id", value = "id")
    private String id;

    @ApiModelProperty(name = "status", value = "状态")
    private String status;

    @ApiModelProperty(name = "remark", value = "审核意见")
    private String remark;

    @ApiModelProperty(value = "是否审核通过",hidden = true)
    private boolean isAccept;
}
