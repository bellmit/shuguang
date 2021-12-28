package com.sofn.agpjyz.vo.exportBean;

import com.alibaba.fastjson.annotation.JSONField;
import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.excel.annotation.ExcelSheetInfo;
import lombok.Data;

import java.util.Date;

@Data
@ExcelSheetInfo(title = "目标物种基础信息", sheetName = "目标物种基础信息")
public class ExportTsBean {

    @ExcelField(title = "保护点名称")
    private String protectValue;
    @ExcelField(title = "目标物种名称")
    private String specValue;
    @ExcelField(title = "拉丁学名")
    private String latinName;
    @ExcelField(title = "数量(株或个)")
    private Integer amount;
    @ExcelField(title = "生长状况")
    private String grow;
    @ExcelField(title = "物种丰富度")
    private String richness;
    @ExcelField(title = "操作人")
    private String inputer;
    @ExcelField(title = "操作时间")
    @JSONField(format = "yyyy-MM-dd")
    private Date inputerTime;

}
