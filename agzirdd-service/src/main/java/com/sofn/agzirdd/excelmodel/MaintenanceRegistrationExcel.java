package com.sofn.agzirdd.excelmodel;

import com.alibaba.fastjson.annotation.JSONField;
import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.excel.annotation.ExcelSheetInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Description: 基础设施维护备案记录导出模板
 */
@Data
@ApiModel(value = "MaintenanceRegistrationXlsx", description = "基础设施监控管理")
@ExcelSheetInfo(title = "基础设施维护备案登记", sheetName = "sheet1")
public class MaintenanceRegistrationExcel {
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

    @ExcelField(title = "故障原因")
    @ApiModelProperty(name = "reason", value = "故障原因")
    private String reason;
    @ExcelField(title = "解决方案")
    @ApiModelProperty(name = "solution", value = "解决方案")
    private String solution;

}
