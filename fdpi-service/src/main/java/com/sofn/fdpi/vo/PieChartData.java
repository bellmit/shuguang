package com.sofn.fdpi.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

/**
 * @Description 欧鳗饼状图数据格式
 * @Author wg
 * @Date 2021/5/26 17:03
 **/
@Data
@ApiModel("欧鳗饼状图数据格式")
public class PieChartData {
    @ApiModelProperty(value = "当次记录的欧鳗吨数")
    private Double num;
    @ApiModelProperty(value = "企业允许驯养繁殖情况")
    private Double tameAllowTon;
    @ApiModelProperty(value = "企业类型:进口？养殖？加工？")
    private String compType;
}
