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
public class QueryVo {

    @ApiModelProperty(name = "pageNo", value = "索引")
    private Integer pageNo;

    @ApiModelProperty(name = "pageSize", value = "每页条数")
    private Integer pageSize;

    @ApiModelProperty(name = "year", value = "年度")
    private String year;

    @ApiModelProperty(name = "dateBegin", value = "开始时间")
    private String dateBegin;

    @ApiModelProperty(name = "dateEnd", value = "结束时间")
    private String dateEnd;

}
