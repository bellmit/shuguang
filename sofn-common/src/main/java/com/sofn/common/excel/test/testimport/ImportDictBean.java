package com.sofn.common.excel.test.testimport;

import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.excel.annotation.ExcelSheetInfo;
import lombok.Data;

import java.util.Date;

@Data
@ExcelSheetInfo(title = "字典",sheetName = "字典")
public class ImportDictBean {

    @ExcelField(title = "ID")
    private String id;

    @ExcelField(title = "字典类型")
    private String dicttype;

    @ExcelField(title = "字典名")
    private String dictname;

    @ExcelField(title = "字典码")
    private String dictcode;

    @ExcelField(title = "是否启用")
    private String enable;

    @ExcelField(title = "备注")
    private String remark;

    @ExcelField(title = "创建者")
    private String createBy;

    @ExcelField(title = "创建日期",dataFormat = "yyyy-MM")
    private Date createTime;

    @ExcelField(title = "修改者")
    private String updateBy;

    @ExcelField(title = "修改日期",dataFormat = "yyyy-MM")
    private Date updateTime;
}
