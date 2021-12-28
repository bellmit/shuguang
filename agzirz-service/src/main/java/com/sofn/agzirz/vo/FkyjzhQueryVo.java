package com.sofn.agzirz.vo;

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
public class FkyjzhQueryVo {

    @ApiModelProperty(name = "pageNo", value = "索引")
    private Integer pageNo;

    @ApiModelProperty(name = "pageSize", value = "每页条数")
    private Integer pageSize;

    @ApiModelProperty(name = "eventCause", value = "事件原因")
    private String eventCause;

    @ApiModelProperty(name = "importance", value = "重要程度")
    private String importance;

    @ApiModelProperty(name = "dateBegin", value = "开始时间")
    private String dateBegin;

    @ApiModelProperty(name = "dateEnd", value = "结束时间")
    private String dateEnd;

}
