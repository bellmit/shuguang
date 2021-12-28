package com.sofn.agzirdd.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description: 通过实体vo
 * @Author: mcc
 * @Date: 2020\3\6 0006
 */
@ApiModel
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PassObjVo {

    @ApiModelProperty(name = "id", value = "id")
    private String id;

    @ApiModelProperty(name = "status", value = "状态")
    private String status;

    @ApiModelProperty(name = "remark", value = "审核意见")
    private String remark;
}
