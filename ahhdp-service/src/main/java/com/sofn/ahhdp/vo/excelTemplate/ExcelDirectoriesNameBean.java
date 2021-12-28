package com.sofn.ahhdp.vo.excelTemplate;

import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.excel.annotation.ExcelImportCheck;
import com.sofn.common.excel.annotation.ExcelSheetInfo;
import lombok.Data;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-04-21 15:18
 */
@Data
@ExcelSheetInfo(title = "品种名称变更名单", sheetName = "品种名称变更名单")
public class ExcelDirectoriesNameBean {

    @ExcelField(title = "编号")
    @ExcelImportCheck(isNull = false)
    private String code;

    @ExcelField(title = "原品种名称")
    @ExcelImportCheck(isNull = false)
    private String oldName;

    @ExcelImportCheck(isNull = false)
    @ExcelField(title = "新品种名称")
    private String newName;

    @ExcelImportCheck(isNull = false)
    @ExcelField(title = "品种类别")
    private String category;

    @ExcelImportCheck(isNull = false)
    @ExcelField(title = "所属地区")
    private String oldRegion;
}
