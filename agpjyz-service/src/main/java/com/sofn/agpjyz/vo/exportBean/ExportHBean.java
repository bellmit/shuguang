package com.sofn.agpjyz.vo.exportBean;

import com.alibaba.fastjson.annotation.JSONField;
import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.excel.annotation.ExcelSheetInfo;
import lombok.Data;

import java.util.Date;

@Data
@ExcelSheetInfo(title = "伴生植物基础信息收集", sheetName = "伴生植物基础信息收集")
public class ExportHBean {

    @ExcelField(title = "保护点名称")
    private String protectValue;
    @ExcelField(title = "伴生植物名称")
    private String associated;
    @ExcelField(title = "数量(株或个)")
    private String amount;
    @ExcelField(title = "生长状况")
    private String growth;
    @ExcelField(title = "物种丰富度")
    private String richness;
    @ExcelField(title = "操作人")
    private String inputer;
    @ExcelField(title = "操作时间")
    @JSONField(format = "yyyy-MM-dd")
    private Date inputerTime;

}
