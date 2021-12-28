package com.sofn.fyem.excelmodel;

import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.excel.annotation.ExcelSheetInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Description: 放流评价汇总-导出模板
 * @Author: wancheng
 */
@Data
@ApiModel(value = "ReleaseEvaluateAndSummaryXlsx", description = "放流评价汇总数据")
@ExcelSheetInfo(title = "放流评价汇总-导出模板", sheetName = "表一")
public class ReleaseEvaluateAndSummaryExcel {

    @ExcelField(title = "上报年度")
    @ApiModelProperty(value = "上报年度")
    private String belongYear;

    @ExcelField(title = "放流时间")
    @ApiModelProperty(value = "放流时间")
    private Date releaseTime;

    @ExcelField(title = "放流区域")
    @ApiModelProperty(value = "放流区域")
    private String areaName;

    @ExcelField(title = "放流地点")
    @ApiModelProperty(value = "放流地点")
    private String releaseSite;

    @ExcelField(title = "放流效果评价")
    @ApiModelProperty(value = "放流效果评价")
    private Double releaseEvaluate;
}
