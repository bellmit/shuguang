package com.sofn.agzirdd.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * @Description: 物种调查模块-调查基本信息查询条件Vo
 * @author Administrator
 */
@ApiModel
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SpeciesInvestigationQueryVo {

    @ApiModelProperty(name = "formNumber", value = "表格编号")
    private String formNumber;

    @ApiModelProperty(name = "investigator", value = "调查人")
    private String investigator;

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

    @ApiModelProperty(name = "pageNo", value = "索引(分页必传,导出不用)")
    private Integer pageNo;

    @ApiModelProperty(name = "pageSize", value = "每页条数(分页必传,导出不用)")
    private Integer pageSize;

    @ApiModelProperty(name = "speciesId", value = "物种id")
    private String speciesId;
}
