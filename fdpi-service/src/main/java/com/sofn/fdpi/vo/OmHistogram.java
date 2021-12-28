package com.sofn.fdpi.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description 欧鳗柱状图数据vo对象
 * @Author wg
 * @Date 2021/5/23 13:57
 **/
@Data
@ApiModel(value = "欧鳗柱状图数据vo对象")
public class OmHistogram {
    @ApiModelProperty(value = "月份")
    private String month;
    @ApiModelProperty(value = "进口数量")
    private Double quantity;
    @ApiModelProperty(value = "剩余数量,养殖与加工企业时这个值是count次数,再出口时这个值是折算比例")
    private Double remainingQty;
}
