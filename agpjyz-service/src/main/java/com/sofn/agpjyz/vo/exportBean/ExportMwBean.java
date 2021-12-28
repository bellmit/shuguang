package com.sofn.agpjyz.vo.exportBean;

import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.excel.annotation.ExcelSheetInfo;
import lombok.Data;


@Data
@ExcelSheetInfo(title = "监测预警模型构建与管理信息", sheetName = "监测预警模型构建与管理信息")
public class ExportMwBean {

    @ExcelField(title = "重点保护植物名称")
    private String plantValue;
    @ExcelField(title = "监测点名称")
    private String protectValue;
    @ExcelField(title = "指标分类名称")
    private String indexValue;


}
