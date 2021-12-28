package com.sofn.ducss.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CollectFlowInfoVo {

    private String year;

    @ApiModelProperty("总数")
    private Integer total;

    @ApiModelProperty("通过")
    private Integer pass;

    @ApiModelProperty("未提交")
    private Integer residue;

    @ApiModelProperty("待审核")
    private Integer toAuditAdnRead;

    @ApiModelProperty("退回")
    private Integer returned;
}
