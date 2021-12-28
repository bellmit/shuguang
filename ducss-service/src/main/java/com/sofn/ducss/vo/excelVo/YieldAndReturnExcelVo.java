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
 * 产生量与还田量数据导入模板
 *
 * @author Chlf
 * @version 1.0
 **/
@Data
@ExcelSheetInfo(title = "产生量与还田量导入数据")
public class YieldAndReturnExcelVo {

    @ExcelField(title = "秸秆类型")
    @ApiModelProperty(value = "秸秆类型")
    private String strawType;

    @ExcelField(title = "粮食产量（吨）", dataFormat="0.00")
    @ApiModelProperty(value = "粮食产量（吨）")
    //@ExcelImportCheck(min = 0, errMsg = "【粮食产量】不能小于0")
    private BigDecimal grainYield = new BigDecimal(0.00);

    @ExcelField(title = "草谷比", dataFormat="0.00")
    @ApiModelProperty(value = "草谷比")
    @ExcelImportCheck(min = 0, max = 6, errMsg = "【草谷比】范围在0到6")
    private BigDecimal grassValleyRatio = new BigDecimal(0.00);

    @ExcelField(title = "可收集系数", dataFormat="0.00")
    @ApiModelProperty(value = "可收集系数")
    @ExcelImportCheck(min = 0, max = 1, errMsg = "【可收集系数】范围在0到1")
    private BigDecimal collectionRatio = new BigDecimal(0.00);

    @ExcelField(title = "播种面积（亩）", dataFormat="0.00")
    @ApiModelProperty(value = "播种面积（亩）")
    private BigDecimal seedArea = new BigDecimal(0.00);

    @ExcelField(title = "还田面积（亩）", dataFormat="0.00")
    @ApiModelProperty(value = "还田面积（亩）")
    private BigDecimal returnArea = new BigDecimal(0.00);

    @ExcelField(title = "秸秆调出量（吨）", dataFormat="0.00")
    @ApiModelProperty(value = "调出量（吨）")
    private BigDecimal exportYield = new BigDecimal(0.00);

}
