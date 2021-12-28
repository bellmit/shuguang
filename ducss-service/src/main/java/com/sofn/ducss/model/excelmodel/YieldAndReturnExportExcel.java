package com.sofn.ducss.model.excelmodel;

import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.excel.annotation.ExcelSheetInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Description: 产生量与直接还田量信息导出模板
 * @Author: chlf
 * @Date: 2020\7\27
 */
@Data
@ApiModel(value = "ProStillXlsx", description = "产生量与直接还田量信息")
@ExcelSheetInfo(sheetName = "产生量与直接还田量信息列表")
public class YieldAndReturnExportExcel {

    @ExcelField(title = "年份")
    @ApiModelProperty(name = "year", value = "年份")
    private String year;

    @ExcelField(title = "区划")
    @ApiModelProperty(name = "countyName", value = "区划")
    private String countyName;

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
