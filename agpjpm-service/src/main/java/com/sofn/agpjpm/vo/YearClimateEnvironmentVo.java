package com.sofn.agpjpm.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Description: 年度气候环境
 * @Author: WXY
 * @Date: 2020-6-3 16:40:39
 */
@Data
@ApiModel("年度气候环境")
public class YearClimateEnvironmentVo implements Serializable {
    @ApiModelProperty("主键")
    private String id;
    //年度
    @ApiModelProperty("年度")
    private Integer year;
    @ApiModelProperty("省编码")
    private String provinceCode;
    @ApiModelProperty("市编码")
    private String cityCode;
    @ApiModelProperty("区编码")
    private String areaCode;
    //行政区划中文
    @ApiModelProperty("地区中文")
    private String regionInCh;
    //平均气温
    @ApiModelProperty("年平均气温")
    private BigDecimal avgTemp;
    //≥10℃年积温
    @ApiModelProperty("≥10℃年积温")
    private BigDecimal annualAccumulatedTemp;
    //年平均降水量(mm)
    @ApiModelProperty("年平均降水量(mm)")
    private BigDecimal avgPrecipitation;
    //年平均日照时数(时)
    @ApiModelProperty("年平均日照时数(时)")
    private BigDecimal avgSunshineHours;
    //年蒸发量(mm)
    @ApiModelProperty("年蒸发量(mm)")
    private BigDecimal annualEvaporation;
}
