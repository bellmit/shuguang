/**
 * Copyright (c) 1998-2020 SOFN Corporation
 * All rights reserved.
 * <p>
 * <p>
 * Created On 2020-04-10 11:20
 */
package com.sofn.ducss.model.excelmodel;

import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.excel.annotation.ExcelSheetInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 产生量与还田量数据模板
 *
 * @author Chlf
 * @version 1.0
 **/
@Data
@ApiModel(value = "YieldAndReturnExcelXlsx", description = "产生量与还田量信息")
@ExcelSheetInfo(sheetName = "产生量与直接还田量")
public class YieldAndReturnExcel {

    @ExcelField(title = "秸秆类型")
    @ApiModelProperty(value = "秸秆类型")
    private String strawType;

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
