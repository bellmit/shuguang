package com.sofn.agsjdm.vo.exportBean;

import com.alibaba.fastjson.annotation.JSONField;
import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.excel.annotation.ExcelSheetInfo;
import lombok.Data;

import java.util.Date;

@Data
@ExcelSheetInfo(title = "环境因子监测信息采集", sheetName = "环境因子监测信息采集")
public class ExportEfBean {

    @ExcelField(title = "湿地区名称")
    private String wetlandName;
    @ExcelField(title = "气温（℃）")
    private String airTem;
    @ExcelField(title = "积温（℃）")
    private String accTem;
    @ExcelField(title = "年降水（mm）")
    private String annualPre;
    @ExcelField(title = "蒸发量（mm）")
    private String evaporation;
    @ExcelField(title = "监测人")
    private String operator;
    @ExcelField(title = "监测时间")
    @JSONField(format = "yyyy-MM-dd")
    private Date operatorTime;


}
