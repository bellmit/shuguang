package com.sofn.agzirdd.excelmodel;

import com.alibaba.fastjson.annotation.JSONField;
import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.excel.annotation.ExcelSheetInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Description: 外来入侵生物基础信息导出模板
 * @Author: mcc
 * @Date: 2020\3\6 0006
 */
@Data
@ApiModel(value = "BasicInfoXlsx", description = "外来入侵生物基础信息")
@ExcelSheetInfo(title = "外来入侵生物基础信息列表", sheetName = "表一")
public class BasicInfoExcel {


    @ExcelField(title = "监测点名称")
    @ApiModelProperty(name = "monitorName", value = "监测点名称")
    private String monitorName;

    @ExcelField(title = "所在地区")
    @ApiModelProperty(name = "areaName", value = "地区名，省市区全称组合")
    private String areaName;

    @ExcelField(title = "经度")
    @ApiModelProperty(name = "longitude", value = "经度")
    private String longitude;

    @ExcelField(title = "纬度")
    @ApiModelProperty(name = "latitude", value = "纬度")
    private String latitude;

    @ExcelField(title = "负责单位")
    @ApiModelProperty(name = "responsibleDepartment", value = "负责单位")
    private String responsibleDepartment;

    @ExcelField(title = "负责人")
    @ApiModelProperty(name = "leadingCadre", value = "负责人")
    private String leadingCadre;

    @ExcelField(title = "负责人电话")
    @ApiModelProperty(name = "telephone", value = "负责人电话")
    private String telephone;

    @ExcelField(title = "主管领导")
    @ApiModelProperty(name = "executive", value = "主管领导")
    private String executive;

    @ExcelField(title = "主管领导电话")
    @ApiModelProperty(name = "exePhone", value = "主管领导电话")
    private String exePhone;

    @ExcelField(title = "状态")
    @ApiModelProperty(name = "status", value = "状态")
    private String status;

    @ExcelField(title = "操作人")
    @ApiModelProperty(name = "createUserName", value = "创建者姓名")
    private String createUserName;

    @ExcelField(title = "操作时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(name = "createTime", value = "创建时间")
    private Date createTime;
}
