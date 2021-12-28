package com.sofn.agzirdd.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description: 物种采集模块-标本采集查询条件Vo
 * @Author: mcc
 * @Date: 2020\3\19 0019
 */
@ApiModel
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SpecimenCollectionQueryVo {

    @ApiModelProperty(name = "gatherer", value = "物种id")
    private String speciesId;

    @ApiModelProperty(name = "gatherer", value = "采集人")
    private String gatherer;

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

    @ApiModelProperty(name = "type", value = "标本类型：0-植物；1-动物；2-微生物(必传)")
    private String type;

    @ApiModelProperty(name = "pageNo", value = "索引(分页必传,导出不用)")
    private Integer pageNo;

    @ApiModelProperty(name = "pageSize", value = "每页条数(分页必传,导出不用)")
    private Integer pageSize;

}
