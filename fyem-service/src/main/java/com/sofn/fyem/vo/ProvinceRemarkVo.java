package com.sofn.fyem.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description: 省级驳回意见Vo
 * @Author: DJH
 * @Date: 2020/5/12 14:20
 */
@ApiModel(value = "ProvinceRemarkVo", description = "省级驳回意见Vo")
@Data
@EqualsAndHashCode(callSuper = false)
public class ProvinceRemarkVo {

    @ApiModelProperty(value = "ID")
    private String id;


    @ApiModelProperty(value = "意见")
    private String remark;
}
