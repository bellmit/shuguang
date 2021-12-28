package com.sofn.ducss.vo;

import com.sofn.ducss.excel.annotation.ExcelField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel("zip 导出")
public class YieldAndReturnExportVoExcel {
    @ExcelField(title = "秸秆类型")
    @ApiModelProperty(value = "秸秆类型")
    private String strawName;

    @ExcelField(title = "粮食产量（吨）")
    @ApiModelProperty(value = "粮食产量（吨）")
    private BigDecimal grainYield;

    @ExcelField(title = "草谷比")
    @ApiModelProperty(value = "草谷比")
    private BigDecimal grassValleyRatio;

    @ExcelField(title = "可收集系数")
    @ApiModelProperty(value = "可收集系数")
    private BigDecimal collectionRatio;

    @ExcelField(title = "播种面积（亩）")
    @ApiModelProperty(value = "播种面积（亩）")
    private BigDecimal seedArea;

    @ExcelField(title = "还田面积（亩）")
    @ApiModelProperty(value = "还田面积（亩）")
    private BigDecimal returnArea;

    @ExcelField(title = "秸秆调出量（吨）")
    @ApiModelProperty(value = "调出量（吨）")
    private BigDecimal exportYield;
}
