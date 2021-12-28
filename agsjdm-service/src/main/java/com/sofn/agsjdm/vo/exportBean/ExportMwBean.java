package com.sofn.agsjdm.vo.exportBean;

import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.excel.annotation.ExcelSheetInfo;
import lombok.Data;


@Data
@ExcelSheetInfo(title = "监测预警模型构建与管理信息", sheetName = "监测预警模型构建与管理信息")
public class ExportMwBean {

    @ExcelField(title = "检测类型")
    private String testType;
    @ExcelField(title = "湿地区名称")
    private String wetlandName;
    @ExcelField(title = "中文名")
    private String chineseName;
    @ExcelField(title = "指标分类名称")
    private String indexValue;


}
