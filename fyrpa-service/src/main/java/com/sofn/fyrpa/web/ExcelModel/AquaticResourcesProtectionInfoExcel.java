package com.sofn.fyrpa.web.ExcelModel;

import com.alibaba.fastjson.annotation.JSONField;
import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.excel.annotation.ExcelSheetInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(value = "aquaticResourcesProtectionInfoExcel", description = "保护区基本信息导出表excle")
@ExcelSheetInfo(title = "汇总统计表")
public class AquaticResourcesProtectionInfoExcel {

    @ExcelField(title = "保护区名称")
    @ApiModelProperty(name = "name",value ="保护区名称" )
    private String name;

    @ExcelField(title = "经度")
    @ApiModelProperty(name = "longitude",value ="经度(东经、西经)" )
    private String longitude;

    @ExcelField(title = "纬度")
    @ApiModelProperty(name = "latitude",value ="纬度(南纬、北纬)" )
    private String latitude;

    @ExcelField(title = "保护区总面积(公顷)")
    @ApiModelProperty(name = "currentProtectionArea",value ="保护区总面积(公顷)" )
    private String  currentProtectionArea;

    @ExcelField(title = "主要保护对象")
    @ApiModelProperty(name = "majorProtectObject",value ="主要保护对象" )
    private String  majorProtectObject;

    @ExcelField(title = "当前级别批准时间")
    @ApiModelProperty(name = "approveTime",value ="当前级别批准时间" )
    @JSONField(format = "yyyy-MM-dd")
    private Date approveTime;

    @ExcelField(title = "当前级别批准文号")
    @ApiModelProperty(name = "approveDocNumber",value ="当前级别批准文号" )
    private String approveDocNumber;

    @ExcelField(title = "管理机构名称")
    @ApiModelProperty(name = "managerOrgName",value ="管理机构名称" )
    private String managerOrgName;

    @ExcelField(title = "填报单位")
    @ApiModelProperty(name = "submitUnit",value ="填报单位" )
    private String submitUnit;

}
