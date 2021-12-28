package com.sofn.agzirdd.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 * @Description: 基础设施维护查询
 */
@ApiModel
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class FacilityQueryForm {

    @ApiModelProperty(name = "monitorName", value = "监测点名称")
    private String monitorName;

    @ApiModelProperty(name = "facilityName", value = "基础设施名称")
    private String facilityName;

    @ApiModelProperty(name = "startTime", value = "开始时间,只需传入日期即可，如：2019-09-09")
    private String startTime;

    @ApiModelProperty(name = "endTime", value = "结束时间,只需传入日期即可，如：2020-02-02")
    private String endTime;

    @ApiModelProperty(name = "pageNo", value = "索引")
    //@NotBlank(message = "页码索引不能为空!")
    private Integer pageNo;

    @ApiModelProperty(name = "pageSize", value = "每页条数")
    //@NotBlank(message = "每页条数不能为空!")
    private Integer pageSize;

}
