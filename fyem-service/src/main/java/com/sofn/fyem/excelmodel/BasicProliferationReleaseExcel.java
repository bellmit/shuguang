package com.sofn.fyem.excelmodel;

import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.excel.annotation.ExcelSheetInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Description: 水生生物增殖放流基础数据导出模板
 * @Author: mcc
 */
@Data
@ApiModel(value = "BasicProliferationReleaseXlsx", description = "水生生物增殖放流基础数据")
@ExcelSheetInfo(title = "放流地点/时间选择-导出模板", sheetName = "表一")
public class BasicProliferationReleaseExcel {

    @ExcelField(title = "所属年度")
    @ApiModelProperty(value = "所属年度")
    private String belongYear;

    @ExcelField(title = "放流地址")
    @ApiModelProperty(value = "放流地址")
    private String areaName;

    @ExcelField(title = "流放地点")
    @ApiModelProperty(value = "流放地点")
    private String releaseSite;

    @ExcelField(title = "经度")
    @ApiModelProperty(value = "经度")
    private String longitude;

    @ExcelField(title = "纬度")
    @ApiModelProperty(value = "纬度")
    private String latitude;

    @ExcelField(title = "放流效果评价")
    @ApiModelProperty(value = "放流效果评价")
    private Double releaseEvaluate;

    @ExcelField(title = "放流时间")
    @ApiModelProperty(value = "放流时间")
    private Date releaseTime;

}
