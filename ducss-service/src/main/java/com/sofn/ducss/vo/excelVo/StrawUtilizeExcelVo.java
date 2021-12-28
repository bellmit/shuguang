/**
 * Copyright (c) 1998-2020 SOFN Corporation
 * All rights reserved.
 * <p>
 * <p>
 * Created On 2020-04-10 11:20
 */
package com.sofn.ducss.vo.excelVo;

import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.excel.annotation.ExcelImportCheck;
import com.sofn.common.excel.annotation.ExcelSheetInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 市场主体规模化秸秆利用量导入
 *
 * @author Chlf
 * @version 1.0
 **/
@Data
@ExcelSheetInfo(title = "市场主体规模化秸秆利用量导入数据")
public class StrawUtilizeExcelVo {

    @ExcelField(title = "主体编号")
    @ApiModelProperty(name = "mainNo",value = "主体编号")
    private String mainNo;

    @ExcelField(title = "市场主体名称")
    @ApiModelProperty(name = "mainName", value = "市场主体名称")
    private String mainName;

    @ExcelField(title = "法人名称")
    @ApiModelProperty(name = "corporationName", value = "法人名称")
    private String corporationName;

    @ExcelField(title = "法人电话")
    @ApiModelProperty(name = "mobilePhone", value = "法人电话")
    private String mobilePhone;

    @ExcelField(title = "详细地址")
    @ApiModelProperty(name = "address",value = "详细地址")
    private String address;

    @ExcelField(title = "农作物类型")
    @ApiModelProperty(name = "strawName",value = "农作物类型")
    private String strawName;

    @ExcelField(title = "肥料化")
    @ApiModelProperty(name = "fertilising",value = "肥料化")
    private BigDecimal fertilising;

    @ExcelField(title = "饲料化")
    @ApiModelProperty(name = "forage",value = "饲料化")
    private BigDecimal forage;

    @ExcelField(title = "燃料化")
    @ApiModelProperty(name = "fuel",value = "燃料化")
    private BigDecimal fuel;

    @ExcelField(title = "基料化")
    @ApiModelProperty(name = "base",value = "基料化")
    private BigDecimal base;

    @ExcelField(title = "原料化")
    @ApiModelProperty(name = "material",value = "原料化")
    private BigDecimal material;

    @ExcelField(title = "外县购入")
    private BigDecimal other;

    @ExcelField(title = "本县来源")
    private BigDecimal ownCountry = new BigDecimal(0);

}
