package com.sofn.ducss.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@ApiModel(value="市场主体利用量汇总查询实体",description = "排序规则，1正序2倒序，不传不排序")
@Data
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AggregateMainUtilizeQueryVo extends com.sofn.ducss.vo.AggregateQueryVo {

    public AggregateMainUtilizeQueryVo(String year, String areaIds) {
        super(year, areaIds);
    }

    @ApiModelProperty(name = "fertilisingSort", value = "肥料化排序")
    private Integer fertilisingSort;

    @ApiModelProperty(name = "forageSort", value = "饲料化排序")
    private Integer forageSort;

    @ApiModelProperty(name = "fuelSort", value = "燃料化排序")
    private Integer fuelSort;

    @ApiModelProperty(name = "baseSort", value = "基料化排序")
    private Integer baseSort;

    @ApiModelProperty(name = "materialSort", value = "原料化排序")
    private Integer materialSort;

    @ApiModelProperty(name = "ownSourceSort", value = "本县来源排序")
    private Integer ownSourceSort;

    @ApiModelProperty(name = "otherSourceSort", value = "外县来源排序")
    private Integer otherSourceSort;

    @ApiModelProperty(name = "totalSort", value = "总利用量排序")
    private Integer totalSort;

    @ApiModelProperty(name = "pageNo", value = "页码")
    private Integer pageNo;

    @ApiModelProperty(name = "pageSize", value = "页大小")
    private Integer pageSize;
}
