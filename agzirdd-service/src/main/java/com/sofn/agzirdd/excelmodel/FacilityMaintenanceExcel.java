package com.sofn.agzirdd.excelmodel;

import com.alibaba.fastjson.annotation.JSONField;
import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.excel.annotation.ExcelSheetInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Description: 基础设施维护导出模板
 */
@Data
@ApiModel(value = "FacilityMaintenanceXlsx", description = "基础设施监控管理")
@ExcelSheetInfo(title = "基础设施维护", sheetName = "sheet1")
public class FacilityMaintenanceExcel {
    @ExcelField(title = "监测点名称")
    @ApiModelProperty(name = "monitorName", value = "监测点名称")

    private String monitorName;
    @ExcelField(title = "基础设施名称")
    @ApiModelProperty(name = "facilityName", value = "基础设施名称")
    private String facilityName;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @ExcelField(title = "使用日期")
    @ApiModelProperty(name = "useDate", value = "使用日期")
    private Date useDate;

}
