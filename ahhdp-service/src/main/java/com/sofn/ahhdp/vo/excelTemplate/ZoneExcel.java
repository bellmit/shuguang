package com.sofn.ahhdp.vo.excelTemplate;

import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.excel.annotation.ExcelImportCheck;
import com.sofn.common.excel.annotation.ExcelSheetInfo;
import lombok.Data;

@Data
@ExcelSheetInfo(sheetName = "国家畜禽遗传资源保护区模板")
public class ZoneExcel {

    @ExcelField(title = "编号")
    @ExcelImportCheck(isNull = false)
    private String code;

    @ExcelField(title = "保护区名称")
    @ExcelImportCheck(isNull = false)
    private String areaName;

    @ExcelImportCheck(isNull = false)
    @ExcelField(title = "建设单位")
    private String company;

    @ExcelImportCheck(isNull = false)
    @ExcelField(title = "保护区范围")
    private String areaRange;
}
