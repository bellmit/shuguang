package com.sofn.agpjyz.vo.exportBean;

import com.alibaba.fastjson.annotation.JSONField;
import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.excel.annotation.ExcelSheetInfo;
import lombok.Data;

import java.util.Date;

@Data
@ExcelSheetInfo(title = "基础设施备案登记", sheetName = "基础设施备案登记")
public class ExportFfBean {

    @ExcelField(title = "保护点名称")
    private String protectValue;
    @ExcelField(title= "基础设施名称")
    private String facilities;
    @JSONField(format = "yyyy-MM-dd")
    @ExcelField(title = "使用日期")
    private Date useDate;
    @ExcelField(title = "操作人")
    private String inputer;
    @ExcelField(title = "操作时间")
    @JSONField(format = "yyyy-MM-dd")
    private Date inputerTime;





}
