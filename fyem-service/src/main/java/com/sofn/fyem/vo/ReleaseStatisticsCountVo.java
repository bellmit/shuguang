package com.sofn.fyem.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

/**
 * @Description: 增值放流-统计vo
 * @Author: mcc
 */
@ApiModel
@Data
@EqualsAndHashCode(callSuper = false)
public class ReleaseStatisticsCountVo implements Comparable<ReleaseStatisticsCountVo> {

    @ApiModelProperty(name = "areaName", value = "区域名称")
    private String areaName;

    @ApiModelProperty(name = "provinceId", value = "省id")
    private String provinceId;

    @ApiModelProperty(name = "cityId", value = "市id")
    private String cityId;

    @ApiModelProperty(name = "countyId", value = "区县id")
    private String countyId;

    @ApiModelProperty(name = "speciesType", value = "物种类型")
    private String speciesType;

    @ApiModelProperty(name = "freshWaterPlanReleaseCapital", value = "淡水计划放流资金")
    private Double freshWaterPlanReleaseCapital;

    @ApiModelProperty(name = "freshWaterPlanReleaseAmount", value = "淡水计划放流数量")
    private Double freshWaterPlanReleaseAmount;

    @ApiModelProperty(name = "freshWaterPracticalReleaseCapital", value = "淡水实际放流资金")
    private Double freshWaterPracticalReleaseCapital;

    @ApiModelProperty(name = "freshWaterPracticalReleaseAmount", value = "淡水实际放流数量")
    private Double freshWaterPracticalReleaseAmount;

    @ApiModelProperty(name = "freshWaterYearReleaseCapital", value = "淡水上一年度放流资金")
    private Double freshWaterYearReleaseCapital;

    @ApiModelProperty(name = "yearReleaseAmount", value = "淡水上一年度放流数量")
    private Double freshWaterYearReleaseAmount;

    /********************************淡水end**************************/

    /********************************海水began**************************/

    @ApiModelProperty(name = "seaWaterPlanReleaseCapital", value = "海水计划放流资金")
    private Double seaWaterPlanReleaseCapital;

    @ApiModelProperty(name = "seaWaterPlanReleaseAmount", value = "海水计划放流数量")
    private Double seaWaterPlanReleaseAmount;

    @ApiModelProperty(name = "seaWaterPracticalReleaseCapital", value = "海水实际放流资金")
    private Double seaWaterPracticalReleaseCapital;

    @ApiModelProperty(name = "seaWaterPracticalReleaseAmount", value = "海水实际放流数量")
    private Double seaWaterPracticalReleaseAmount;

    @ApiModelProperty(name = "seaWaterYearReleaseCapital", value = "海水上一年度放流资金")
    private Double seaWaterYearReleaseCapital;

    @ApiModelProperty(name = "seaWaterYearReleaseAmount", value = "海水上一年度放流数量")
    private Double seaWaterYearReleaseAmount;

    /********************************海水end**************************/

    /********************************珍稀began**************************/

    @ApiModelProperty(name = "rarePlanReleaseCapital", value = "珍稀计划放流资金")
    private Double rarePlanReleaseCapital;

    @ApiModelProperty(name = "rarePlanReleaseAmount", value = "珍稀计划放流数量")
    private Double rarePlanReleaseAmount;

    @ApiModelProperty(name = "rarePracticalReleaseCapital", value = "珍稀实际放流资金")
    private Double rarePracticalReleaseCapital;

    @ApiModelProperty(name = "rarePracticalReleaseAmount", value = "珍稀实际放流数量")
    private Double rarePracticalReleaseAmount;

    @ApiModelProperty(name = "rareYearReleaseCapital", value = "珍稀上一年度放流资金")
    private Double rareYearReleaseCapital;

    @ApiModelProperty(name = "rareYearReleaseAmount", value = "珍稀上一年度放流数量")
    private Double rareYearReleaseAmount;

    /********************************珍稀end**************************/

    /********************************合计began**************************/
    @ApiModelProperty(name = "sumPlanReleaseCapital", value = "合计计划放流资金")
    private Double sumPlanReleaseCapital;

    @ApiModelProperty(name = "sumPlanReleaseAmount", value = "合计计划放流数量")
    private Double sumPlanReleaseAmount;

    @ApiModelProperty(name = "sumPracticalReleaseCapital", value = "合计实际放流资金")
    private Double sumPracticalReleaseCapital;

    @ApiModelProperty(name = "sumPracticalReleaseAmount", value = "合计实际放流数量")
    private Double sumPracticalReleaseAmount;

    @ApiModelProperty(name = "sumYearReleaseCapital", value = "合计上一年度放流资金")
    private Double sumYearReleaseCapital;

    @ApiModelProperty(name = "sumYearReleaseAmount", value = "合计上一年度放流数量")
    private Double sumYearReleaseAmount;

    /********************************合计end**************************/

    @Override
    public int compareTo(@NotNull ReleaseStatisticsCountVo o) {
        int ret = StringUtils.compare(this.getProvinceId(), o.getProvinceId());
        if (ret == 0) {
            ret = StringUtils.compare(this.getCityId(), o.getCityId());
        }
        if (ret == 0) {
            ret = StringUtils.compare(this.getCountyId(), o.getCountyId());
        }
        return ret;
    }
}
