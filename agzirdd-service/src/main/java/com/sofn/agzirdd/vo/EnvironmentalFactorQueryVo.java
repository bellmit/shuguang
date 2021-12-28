package com.sofn.agzirdd.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * @Description: 外来入侵-生物监测点环境因子信息查询Vo
 * @Author: mcc
 * @Date: 2020\3\10 0010
 */
@ApiModel
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class EnvironmentalFactorQueryVo {

    @ApiModelProperty(name = "monitorId", value = "监测点Id")
    private String monitorId;

    @ApiModelProperty(name = "soilTemperature", value = "土壤温度")
    private String soilTemperature;

    @ApiModelProperty(name = "soilHumidity", value = "土壤湿度")
    private String soilHumidity;

    @ApiModelProperty(name = "beginDate", value = "开始时间")
    private String beginDate;

    @ApiModelProperty(name = "endDate", value = "结束时间")
    private String endDate;

    @ApiModelProperty(name = "pageNo", value = "索引")
    //@NotBlank(message = "页码索引不能为空!")
    private Integer pageNo;

    @ApiModelProperty(name = "pageSize", value = "每页条数")
    //@NotBlank(message = "每页条数不能为空!")
    private Integer pageSize;
}
