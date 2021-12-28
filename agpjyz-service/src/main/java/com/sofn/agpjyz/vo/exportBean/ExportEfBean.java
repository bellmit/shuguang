package com.sofn.agpjyz.vo.exportBean;

import com.alibaba.fastjson.annotation.JSONField;
import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.excel.annotation.ExcelSheetInfo;
import lombok.Data;

import java.util.Date;

@Data
@ExcelSheetInfo(title = "环境因子监测信息", sheetName = "环境因子监测信息")
public class ExportEfBean {

    @ExcelField(title = "保护点名称")
    private String protectValue;
    @ExcelField(title = "土壤温度")
    private String soilTemperature;
    @ExcelField(title = "土壤湿度")
    private String soilMoisture;
    @JSONField(format = "yyyy-MM-dd")
    @ExcelField(title = "采集时间")
    private Date collectTime;
    @ExcelField(title = "日常工作状况")
    private String status;
    @ExcelField(title = "操作人")
    private String inputer;
    @ExcelField(title = "操作时间")
    @JSONField(format = "yyyy-MM-dd")
    private Date inputerTime;





}
