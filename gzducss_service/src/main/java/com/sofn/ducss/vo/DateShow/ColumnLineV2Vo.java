package com.sofn.ducss.vo.DateShow;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 利用量,利用率柱状图,折线图返回数据VO
 *
 * @author jiangtao
 * @version 1.0
 **/
@Data
@ApiModel(value = "利用量,利用率柱状图,折线图返回数据VO")
@NoArgsConstructor
@AllArgsConstructor
public class ColumnLineV2Vo {

    @ApiModelProperty("作物名称")
    private String name;

    @ApiModelProperty("产生量")
    private BigDecimal theoryResource;

    @ApiModelProperty("可收集量")
    private BigDecimal collectResource;

    @ApiModelProperty("秸秆利用量")
    private BigDecimal strawUtilization;
}
