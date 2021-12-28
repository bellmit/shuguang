package com.sofn.common.excel.test.testimport;

import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.excel.annotation.ExcelSheetInfo;
import lombok.Data;

import java.util.Date;

@Data
@ExcelSheetInfo(title = "基础数据",sheetName = "基础数据")
public class ImportBaseDataBean {

    @ExcelField(title = "ID")
    private String id;

    @ExcelField(title = "基础数据类型")
    private String baseDatatype;

    @ExcelField(title = "基础数据名称")
    private String baseDataname;

    @ExcelField(title = "基础数据值")
    private String baseDatacode;

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
