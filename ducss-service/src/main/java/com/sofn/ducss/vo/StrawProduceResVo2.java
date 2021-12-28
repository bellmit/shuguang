package com.sofn.ducss.vo;

import com.sofn.common.excel.annotation.ExcelField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 农作物秸秆产生量汇总数据实体
 */
@Data
@ApiModel(value = "农作物秸秆产生量汇总数据实体2")
@EqualsAndHashCode(callSuper = false)
public class StrawProduceResVo2 {

    @ExcelField(title = "", isShow = false)
    @ApiModelProperty(name = "strawType", value = "秸秆类型")
    private String strawType;

    @ApiModelProperty(name = "strawName", value = "秸秆名称")
    @ExcelField(title = "作物种类")
    private String strawName;

    @ApiModelProperty(name = "theoryResource", value = "产生量")
    @ExcelField(title = "产生量（吨）")
    private BigDecimal theoryResource; // 产生量=粮食产量*草谷比

    @ApiModelProperty(name = "产生量占比(%)=该作物产生量/14种作物产生量合计）*100")
    @ExcelField(title = "产生量占比(%)")
    private BigDecimal theoryResourceRate;

    @ApiModelProperty(name = "collectResource", value = "可收集量")
    @ExcelField(title = "可收集量（吨）")
    private BigDecimal collectResource;// 可收集量=产生量*收集系数

    @ApiModelProperty(name = "可收集量比例（%）=（该作物可收集量/14种作物可收集量合计）*100")
    @ExcelField(title = "可收集量比例（%）")
    private BigDecimal collectResourceRate;

    @ApiModelProperty(name = "grainYield", value = "粮食产量")
    @ExcelField(title = "粮食产量（吨）")
    private BigDecimal grainYield;// 粮食产量

    @ApiModelProperty(name = "seedArea", value = "播种面积")
    @ExcelField(title = "播种面积（亩）")
    private BigDecimal seedArea;//播种面积


    public StrawProduceResVo2() {
        super();
        BigDecimal zero = BigDecimal.ZERO;
        this.theoryResource = zero;
        this.collectResource = zero;
        this.seedArea = zero;
        this.grainYield = zero;
        this.theoryResourceRate = zero;
        this.collectResourceRate = zero;
    }


}
