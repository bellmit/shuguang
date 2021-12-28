package com.sofn.agzirdd.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 * @Description: 基础设施维护备案记录查询
 */
@ApiModel
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class MaintenanceRegistrationQueryForm {
    @ApiModelProperty(name = "monitorName", value = "监测点名称")
    private String monitorName;
    @ApiModelProperty(name = "facilityName", value = "基础设施名称")
    private String facilityName;
    @ApiModelProperty(name = "startTime", value = "开始时间")
    private String startTime;
    @ApiModelProperty(name = "endTime", value = "结束时间")
    private String endTime;
    @ApiModelProperty(name = "pageNo", value = "索引")
    //@NotBlank(message = "页码索引不能为空!")
    private Integer pageNo;
    @ApiModelProperty(name = "pageSize", value = "每页条数")
    private Integer pageSize;

}
