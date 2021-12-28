package com.sofn.agzirdd.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 批量审核单项记录实体
 */
@ApiModel
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class BatchAuditItemVo {
    @ApiModelProperty(name = "id", value = "要审核数据的id")
    private String id;

    @ApiModelProperty(name = "status", value = "审核后状态")
    private String status;
}
