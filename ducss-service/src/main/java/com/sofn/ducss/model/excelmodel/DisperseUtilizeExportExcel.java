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
 * 农户分散利用量数据模板
 *
 * @author Chlf
 * @version 1.0
 **/
@Data
@ApiModel(value = "DisperseUtilizeExcelXlsx", description = "农户分散利用量信息")
@ExcelSheetInfo(sheetName = "农户分散利用量")
public class DisperseUtilizeExportExcel {
/*
    @ExcelField(title = "区县")
    @ApiModelProperty(name = "countryName",value = "区县")
    private String countryName;*/

    @ExcelField(title = "农户编码")
    @ApiModelProperty(name = "farmerId", value = "农户编码")
    private String farmerNo;

    @ExcelField(title = "年份")
    @ApiModelProperty(name = "year", value = "年份")
    private String year;

    @ExcelField(title = "区划")
    @ApiModelProperty(name = "areaName", value = "区划")
    private String areaName;

    @ExcelField(title = "户主姓名")
    @ApiModelProperty(name = "farmerName", value = "户主姓名")
    private String farmerName;

    @ExcelField(title = "户主电话")
    @ApiModelProperty(name = "farmerPhone", value = "户主电话")
    private String farmerPhone;

    @ExcelField(title = "详细地址")
    @ApiModelProperty(name = "address", value = "详细地址")
    private String address;

    @ExcelField(title = "农作物类型")
    @ApiModelProperty(name = "strawName", value = "农作物类型")
    private String strawName; //秸秆名称

    @ExcelField(title = "播种面积")
    @ApiModelProperty(name = "sownArea", value = "播种面积")
    private BigDecimal sownArea;

    @ExcelField(title = "亩产")
    @ApiModelProperty(name = "yieldPerMu", value = "亩产")
    private BigDecimal yieldPerMu;

    @ExcelField(title = "肥料化")
    @ApiModelProperty(name = "fertilising", value = "肥料化")
    private BigDecimal fertilising;

    @ExcelField(title = "饲料化")
    @ApiModelProperty(name = "forage", value = "饲料化")
    private BigDecimal forage;

    @ExcelField(title = "燃料化")
    @ApiModelProperty(name = "fuel", value = "燃料化")
    private BigDecimal fuel;

    @ExcelField(title = "基料化")
    @ApiModelProperty(name = "base", value = "基料化")
    private BigDecimal base;

    @ExcelField(title = "原料化")
    @ApiModelProperty(name = "material", value = "原料化")
    private BigDecimal material;

    @ExcelField(title = "利用量")
    @ApiModelProperty(name = "reuse", value = "利用量")
    private BigDecimal reuse;

    @ExcelField(title = "用途")
    @ApiModelProperty(name = "application", value = "用途")
    private String application;

}
