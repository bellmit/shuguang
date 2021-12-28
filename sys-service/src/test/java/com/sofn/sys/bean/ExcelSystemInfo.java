package com.sofn.sys.bean;

import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.excel.annotation.ExcelSheetInfo;
import lombok.Data;

@Data
@ExcelSheetInfo(sheetName = "生态项目小组开发计划")
public class ExcelSystemInfo {

    @ExcelField(title = "系统名称", column = 0)
    private String appName;

    @ExcelField(title = "APPID", column = 4)
    private String appId;

    @ExcelField(title = "外网地址", column = 10)
    private String viewUrl;

    @ExcelField(title = "内网地址", column = 9)
    private String inViewUrl;

    @ExcelField(title = "是否门户", column = 11)
    private String isMenHu;
}
