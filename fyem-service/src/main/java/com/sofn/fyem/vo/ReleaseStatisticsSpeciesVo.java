package com.sofn.fyem.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description: 增值放流-经济物种
 * @Author: mcc
 */
@ApiModel
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ReleaseStatisticsSpeciesVo {

    @ApiModelProperty(name = "belongYear", value = "增值放流-所属年度")
    private String belongYear;

    @ApiModelProperty(name = "provinceId", value = "省级id")
    private String provinceId;

    @ApiModelProperty(name = "cityId", value = "市级id")
    private String cityId;

    @ApiModelProperty(name = "countyId", value = "区县id")
    private String countyId;

    @ApiModelProperty(name = "status", value = "状态")
    private String status;

    @ApiModelProperty(name = "roleCode", value = "角色码")
    private String roleCode;

    @ApiModelProperty(name = "releaseStatisticsFreshWaterSpeciesVo", value = "增值放流-淡水经济物种")
    private ReleaseStatisticsFreshWaterSpeciesVo releaseStatisticsFreshWaterSpeciesVo;

    @ApiModelProperty(name = "releaseStatisticsSeaWaterSpeciesVo", value = "增值放流-海水经济物种")
    private ReleaseStatisticsSeaWaterSpeciesVo releaseStatisticsSeaWaterSpeciesVo;

    @ApiModelProperty(name = "releaseStatisticsRareSpeciesVo", value = "增值放流-珍稀经济物种")
    private ReleaseStatisticsRareSpeciesVo releaseStatisticsRareSpeciesVo;

    public ReleaseStatisticsSpeciesVo(){}
}
