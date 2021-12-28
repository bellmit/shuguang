package com.sofn.agzirdd.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;



/**
 * @Description: 外来入侵-生物监测点基本信息查询Vo
 * @Author: mcc
 * @Date: 2020\3\5 0005
 */
@ApiModel
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class BasicInfoQueryVo {

    @ApiModelProperty(name = "monitorName", value = "监测点名称")
    private String monitorName;

    @ApiModelProperty(name = "provinceId", value = "省级id")
    private String provinceId;

    @ApiModelProperty(name = "cityId", value = "市级id")
    private String cityId;

    @ApiModelProperty(name = "countyId", value = "区县id")
    private String countyId;

    @ApiModelProperty(name = "status", value = "状态")
    private String status;

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
