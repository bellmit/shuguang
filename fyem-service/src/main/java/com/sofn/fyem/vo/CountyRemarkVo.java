package com.sofn.fyem.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description: 县级驳回意见Vo
 * @Author: DJH
 * @Date: 2020/5/13 14:33
 */
@ApiModel(value = "CountyRemarkVo", description = "县级驳回意见Vo")
@Data
@EqualsAndHashCode(callSuper = false)
public class CountyRemarkVo {

    @ApiModelProperty(name = "id", value = "ID")
    private String id;

    @ApiModelProperty(name = "remark", value = "驳回意见")
    private String remark;

}
