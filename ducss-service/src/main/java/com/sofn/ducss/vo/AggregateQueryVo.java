package com.sofn.ducss.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 汇总查询VO
 */
@ApiModel("汇总查询通用实体")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class AggregateQueryVo {
    @ApiModelProperty(name = "year", value = "年份")
    protected String year;

    @ApiModelProperty(name = "areaIds", value = "多选的区域IDs")
    protected String areaIds;

    @ApiModelProperty("查询的字段集合，以逗号隔开")
    private String selectFields;

    @ApiModelProperty(value = "是否数据审核查询")
    private Boolean checkInfoFlag;

    public AggregateQueryVo(String year, String areaIds) {
        this.year = year;
        this.areaIds = areaIds;
    }

}
