package com.sofn.fyem.vo;

import com.sofn.common.model.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description: 市级上报管理Vo
 * @Author: DJH
 * @Date: 2020/4/28 14:34
 */
@ApiModel(value = "CityReportManagementVo", description = "市级上报管理Vo")
@Data
@EqualsAndHashCode(callSuper = false)
public class CityReportManagementVo extends BaseVo<CityReportManagementVo> {

    @ApiModelProperty(name = "provinceId", value = "省id")
    private String provinceId;

    @ApiModelProperty(name = "cityId", value = "市id")
    private String cityId;

    @ApiModelProperty(name = "countyId", value = "区县id")
    private String countyId;

    @ApiModelProperty(name = "belongYear", value = "上报年度")
    private String belongYear;

    @ApiModelProperty(name = "reportCount", value = "上报数")
    private String reportCount;

    @ApiModelProperty(name = "shouldReportCount", value = "应报数")
    private String shouldReportCount;

    @ApiModelProperty(name = "reportSituation", value = "上报情况")
    private String reportSituation;

    @ApiModelProperty(name = "status", value = "待审状态")
    private String status;

    @ApiModelProperty(name = "statusCode", value = "状态码code")
    private String statusCode;
}
