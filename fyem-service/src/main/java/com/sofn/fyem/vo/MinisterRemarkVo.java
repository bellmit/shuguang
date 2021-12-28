package com.sofn.fyem.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description: 部级驳回意见Vo
 * @Author: DJH
 * @Date: 2020/5/12 14:20
 */
@ApiModel(value = "MinisterRemarkVo", description = "部级驳回意见Vo")
@Data
@EqualsAndHashCode(callSuper = false)
public class MinisterRemarkVo {

    @ApiModelProperty(name = "id", value = "ID")
    private String id;

    @ApiModelProperty(name = "remark", value = "意见")
    private String remark;
}
