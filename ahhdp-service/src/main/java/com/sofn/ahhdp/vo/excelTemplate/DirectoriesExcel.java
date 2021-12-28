package com.sofn.ahhdp.vo.excelTemplate;

import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.excel.annotation.ExcelImportCheck;
import com.sofn.common.excel.annotation.ExcelSheetInfo;
import lombok.Data;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-04-21 14:08
 */
@Data
@ExcelSheetInfo(sheetName = "国家畜禽遗传资源保护名录模板")
public class DirectoriesExcel {

    @ExcelField(title = "编号")
    @ExcelImportCheck(isNull = false)
    private String  code;
    @ExcelField(title = "品种名称")
    @ExcelImportCheck(isNull = false)
    private String   oldName;
    @ExcelField(title = "品种类别", dictType = "variety_type")
    @ExcelImportCheck(isNull = false)
    private String  category;
    @ExcelField(title = "所属地区")
    @ExcelImportCheck(isNull = false)
    private String  oldRegion;
}
