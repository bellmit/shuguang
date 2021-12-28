package com.sofn.ahhdp.vo.excelTemplate;

import com.alibaba.fastjson.annotation.JSONField;
import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.excel.annotation.ExcelImportCheck;
import com.sofn.common.excel.annotation.ExcelSheetInfo;
import lombok.Data;

import java.util.Date;

@Data
@ExcelSheetInfo(title = "保护区名称变更名单", sheetName = "保护区名称变更名单")
public class ExportZoneNameBean {

    @ExcelField(title = "编号")
    @ExcelImportCheck(isNull = false)
    private String code;

    @ExcelField(title = "原保护区名称")
    @ExcelImportCheck(isNull = false)
    private String areaName;

    @ExcelField(title = "新保护区名称")
    @ExcelImportCheck(isNull = false)
    private String newName;

    @ExcelImportCheck(isNull = false)
    @ExcelField(title = "建设单位")
    private String newCompany;

    @ExcelImportCheck(isNull = false)
    @ExcelField(title = "保护区范围")
    private String newRange;





}
