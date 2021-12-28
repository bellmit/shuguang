package com.sofn.agsjdm.vo.exportBean;

import com.alibaba.fastjson.annotation.JSONField;
import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.excel.annotation.ExcelSheetInfo;
import lombok.Data;

import java.util.Date;

@Data
@ExcelSheetInfo(title = "生物监测信息采集", sheetName = "生物监测信息采集")
public class ExportBBean {

    @ExcelField(title = "湿地区名称")
    private String wetlandName;
    @ExcelField(title = "生物分类")
    private String biologicalAxonomy;
    @ExcelField(title = "中文名")
    private String chineseName;
    @ExcelField(title = "拉丁名")
    private String latinName;
    @ExcelField(title = "保护级别")
    private String proLevel;
    @ExcelField(title = "种群数量")
    private Integer populationSize;
    @ExcelField(title = "居留型")
    private String resident;
    @ExcelField(title = "调查方法")
    private String investigation;
    @ExcelField(title = "备注")
    private String remarks;
    @ExcelField(title = "监测人")
    private String operator;
    @ExcelField(title = "监测时间")
    @JSONField(format = "yyyy-MM-dd")
    private Date operatorTime;


}
