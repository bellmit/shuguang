package com.sofn.sys.vo.Excel;

import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.excel.annotation.ExcelImportCheck;
import com.sofn.common.excel.annotation.ExcelSheetInfo;
import lombok.Data;

/**
 * 导入基础数据
 * @author heyongjie
 * @date 2020/9/9 10:21
 */
@Data
@ExcelSheetInfo(title = "基础数据导入模板")
public class ImportBaseDataVo {

    @ExcelField(title = "数据字典类型代码")
    @ExcelImportCheck(isNull =  false,max = 50,min = 1,errMsg = "数据字典类型代码在1-50之间")
    private String typeCode;

    @ExcelField(title = "基础数据名称")
    @ExcelImportCheck(isNull =  false,max = 30,min = 1,errMsg = "数据字典代码在1-30之间")
    private String dataName;

    @ExcelField(title = "基础数据代码")
    @ExcelImportCheck(isNull =  false,max = 30,min = 1,errMsg = "数据字典代码在1-30之间")
    private String dataCode;

    @ExcelField(title = "状态", customDictValue = {"启用", "禁用"})
    @ExcelImportCheck(isNull =  false)
    private String enable;


    @ExcelField(title = "基础数据备注")
    private String remark;

}
