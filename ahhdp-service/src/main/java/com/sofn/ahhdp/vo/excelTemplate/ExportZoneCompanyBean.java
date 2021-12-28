package com.sofn.ahhdp.vo.excelTemplate;

import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.excel.annotation.ExcelImportCheck;
import com.sofn.common.excel.annotation.ExcelSheetInfo;
import lombok.Data;

@Data
@ExcelSheetInfo(title = "建设单位变更名单", sheetName = "建设单位变更名单")
public class ExportZoneCompanyBean {

    @ExcelField(title = "编号")
    @ExcelImportCheck(isNull = false)
    private String code;

    @ExcelField(title = "保护区名称")
    @ExcelImportCheck(isNull = false)
    private String newName;

    @ExcelImportCheck(isNull = false)
    @ExcelField(title = "原建设单位")
    private String company;

    @ExcelImportCheck(isNull = false)
    @ExcelField(title = "新建设单位")
    private String newCompany;

    @ExcelImportCheck(isNull = false)
    @ExcelField(title = "保护区范围")
    private String newRange;


}
