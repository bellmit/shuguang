package com.sofn.agzirdd.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 批量审核实体
 */
@ApiModel
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class BatchAuditVo {

    @ApiModelProperty(name = "remark", value = "审核意见")
    private String remark;

    @ApiModelProperty(name = "items", value = "审核项数组")
    private List<BatchAuditItemVo> items;
}
