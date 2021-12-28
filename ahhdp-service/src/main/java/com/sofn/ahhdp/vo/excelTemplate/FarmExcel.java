package com.sofn.ahhdp.vo.excelTemplate;

import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.excel.annotation.ExcelImportCheck;
import com.sofn.common.excel.annotation.ExcelSheetInfo;
import lombok.Data;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-04-21 14:04
 */
@Data
@ExcelSheetInfo(sheetName = "国家畜禽遗传资源保护场模板")
public class FarmExcel {
    @ExcelField(title = "编号")
    @ExcelImportCheck(isNull = false)
    private String code;

    @ExcelField(title = "保护场名称")
    @ExcelImportCheck(isNull = false)
    private String oldName;

    @ExcelImportCheck(isNull = false)
    @ExcelField(title = "建设单位")
    private String oldCompany;
}
