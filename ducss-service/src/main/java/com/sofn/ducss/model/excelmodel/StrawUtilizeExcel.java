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
 * 市场主体规模化秸秆利用量数据模板
 *
 * @author Chlf
 * @version 1.0
 **/
@Data
@ApiModel(value = "strawUtilizeExcelXlsx", description = "市场主体规模化秸秆利用量")
@ExcelSheetInfo(sheetName = "市场主体规模化秸秆利用量")
public class StrawUtilizeExcel {

    @ExcelField(title = "市场主体编号")
    @ApiModelProperty(value = "市场主体编号")
    private String mainNo;

    @ExcelField(title = "年度")
    @ApiModelProperty(value = "年度")
    private String year;

    @ExcelField(title = "区划")
    @ApiModelProperty(value = "区划")
    private String areaName;

    @ExcelField(title = "市场主体名称")
    @ApiModelProperty(value = "市场主体名称")
    private String mainName;

    @ExcelField(title = "法人名称")
    @ApiModelProperty(value = "法人名称")
    private String corporationName;

    @ExcelField(title = "法人电话")
    @ApiModelProperty(value = "法人电话")
    private String mobilePhone;

    @ExcelField(title = "地址")
    @ApiModelProperty(value = "地址")
    private String address;

    @ExcelField(title = "农作物类型")
    @ApiModelProperty(value = "农作物类型")
    private String strawName;

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

    @ExcelField(title = "外县购入")
    @ApiModelProperty(name = "other", value = "外县购入")
    private BigDecimal other;

    @ExcelField(title = "本县来源")
    @ApiModelProperty(name = "ownCountry", value = "本县来源")
    private BigDecimal ownCountry = new BigDecimal(0);

}
