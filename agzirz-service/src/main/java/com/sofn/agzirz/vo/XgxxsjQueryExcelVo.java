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
public class XgxxsjQueryExcelVo {

    @ApiModelProperty(name = "cnName", value = "中文名")
    private String cnName;

    @ApiModelProperty(name = "schoName", value = "科名")
    private String schoName;

    @ApiModelProperty(name = "haveMeasure", value = "是否有防控措施(1:是  0:否)")
    private String haveMeasure;

    @ApiModelProperty(name = "dateBegin", value = "开始时间")
    private String dateBegin;

    @ApiModelProperty(name = "dateEnd", value = "结束时间")
    private String dateEnd;

}
