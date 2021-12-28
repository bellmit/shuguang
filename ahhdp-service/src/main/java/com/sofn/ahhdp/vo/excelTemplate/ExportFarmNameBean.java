package com.sofn.ahhdp.vo.excelTemplate;

import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.excel.annotation.ExcelImportCheck;
import com.sofn.common.excel.annotation.ExcelSheetInfo;
import lombok.Data;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-04-21 15:11
 */
@Data
@ExcelSheetInfo(title = "保护区场名称变更名单", sheetName = "保护场名称变更名单")
public class ExportFarmNameBean {

    @ExcelField(title = "编号")
    @ExcelImportCheck(isNull = false)
    private String code;

    @ExcelField(title = "原保护场名称")
    @ExcelImportCheck(isNull = false)
    private String oldName;

    @ExcelField(title = "新保护场名称")
    @ExcelImportCheck(isNull = false)
    private String newName;

    @ExcelImportCheck(isNull = false)
    @ExcelField(title = "建设单位")
    private String newCompany;
}
