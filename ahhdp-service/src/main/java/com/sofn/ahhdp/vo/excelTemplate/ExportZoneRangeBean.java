package com.sofn.ahhdp.vo.excelTemplate;

import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.excel.annotation.ExcelImportCheck;
import com.sofn.common.excel.annotation.ExcelSheetInfo;
import lombok.Data;

@Data
@ExcelSheetInfo(title = "保护范围变更名单", sheetName = "保护范围变更名单")
public class ExportZoneRangeBean {

    @ExcelField(title = "编号")
    @ExcelImportCheck(isNull = false)
    private String code;

    @ExcelField(title = "保护区名称")
    @ExcelImportCheck(isNull = false)
    private String newName;

    @ExcelImportCheck(isNull = false)
    @ExcelField(title = "建设单位")
    private String newCompany;

    @ExcelImportCheck(isNull = false)
    @ExcelField(title = "原保护区范围")
    private String areaRange;

    @ExcelImportCheck(isNull = false)
    @ExcelField(title = "新保护区范围")
    private String newRange;


}
