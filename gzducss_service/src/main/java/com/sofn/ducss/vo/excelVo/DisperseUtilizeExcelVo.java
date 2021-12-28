/**
 * Copyright (c) 1998-2020 SOFN Corporation
 * All rights reserved.
 * <p>
 * <p>
 * Created On 2020-04-10 11:20
 */
package com.sofn.ducss.vo.excelVo;

import com.sofn.ducss.excel.annotation.ExcelField;
import com.sofn.ducss.excel.annotation.ExcelImportCheck;
import com.sofn.ducss.excel.annotation.ExcelSheetInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 农户分散利用量导入
 *
 * @author Chlf
 * @version 1.0
 **/
@Data
@ExcelSheetInfo(title = "农户分散利用量导入数据")
public class DisperseUtilizeExcelVo {

    @ExcelField(title = "农户编号")
    @ApiModelProperty(name = "farmerNo",value = "农户编号")
    private String farmerNo;

    @ExcelField(title = "户主姓名")
    @ApiModelProperty(name = "farmerName", value = "户主姓名")
    private String farmerName;

    @ExcelField(title = "户主电话")
    @ApiModelProperty(name = "farmerPhone",value = "户主电话")
    @ExcelImportCheck(min = 7, max = 11, errMsg = "【户主电话】格式有误")
    private String farmerPhone;

    @ExcelField(title = "详细地址")
    @ApiModelProperty(name = "address",value = "详细地址")
    private String address;

    @ExcelField(title = "农作物类型")
    @ApiModelProperty(name = "strawName",value = "农作物类型")
    private String strawName;

    @ExcelField(title = "播种面积（亩）")
    @ApiModelProperty(name = "sownArea",value = "播种面积")
    @ExcelImportCheck(min = 0, max = 999999999999999L, errMsg = "【播种面积】范围在0到999999999999999")
    private BigDecimal sownArea;

    @ExcelField(title = "亩产（公斤/亩）")
    @ApiModelProperty(name = "yieldPerMu",value = "亩产")
    @ExcelImportCheck(min = 0, max = 9999, errMsg = "【亩产】范围在0到9999")
    private BigDecimal yieldPerMu;

    @ExcelField(title = "肥料化利用比例（%）")
    @ApiModelProperty(name = "fertilising",value = "肥料化")
    @ExcelImportCheck(min = 0, max = 100, errMsg = "【肥料化】范围在百分之0到100")
    private BigDecimal fertilising;

    @ExcelField(title = "饲料化利用比例（%）")
    @ApiModelProperty(name = "forage",value = "饲料化")
    @ExcelImportCheck(min = 0, max = 100, errMsg = "【饲料化】范围在百分之0到100")
    private BigDecimal forage;

    @ExcelField(title = "燃料化利用比例（%）")
    @ApiModelProperty(name = "fuel",value = "燃料化")
    @ExcelImportCheck(min = 0, max = 100, errMsg = "【燃料化】范围在百分之0到100")
    private BigDecimal fuel;

    @ExcelField(title = "基料化利用比例（%）")
    @ApiModelProperty(name = "base",value = "基料化")
    @ExcelImportCheck(min = 0, max = 100, errMsg = "【基料化】范围在百分之0到100")
    private BigDecimal base;

    @ExcelField(title = "原料化利用比例（%）")
    @ApiModelProperty(name = "material",value = "原料化")
    @ExcelImportCheck(min = 0, max = 100, errMsg = "【原料化】范围在百分之0到100")
    private BigDecimal material;

    @ExcelField(title = "收集他人秸秆利用量（吨）")
    @ApiModelProperty(name = "reuse",value = "利用量")
    private BigDecimal reuse;

    @ExcelField(title = "用途")
    @ApiModelProperty(name = "application",value = "用途")
    private String application;

}
