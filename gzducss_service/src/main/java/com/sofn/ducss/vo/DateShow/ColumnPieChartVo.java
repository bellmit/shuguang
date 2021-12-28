package com.sofn.ducss.vo.DateShow;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 柱状图饼状图数据Vo
 *
 * @author jiangtao
 * @date 2020/11/6
 */
@Data
@ApiModel(value = "利用量,利用率柱状图,折线图返回数据VO")
@NoArgsConstructor
@AllArgsConstructor
public class ColumnPieChartVo {


    @ApiModelProperty(value = "柱状横坐标,饼状外圈值")
    private String name;

    @ApiModelProperty(value = "对应的数值")
    private BigDecimal value;
}