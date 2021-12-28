package com.sofn.ducss.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 获取前端查询对象表单vo
 */
@ApiModel
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class QueryOneVo {

    @ApiModelProperty(name = "year", value = "年度")
    private String year;

    @ApiModelProperty(name = "countyId", value = "县Id")
    private String countyId;

}
