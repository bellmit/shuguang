package com.sofn.ahhdp.vo.excelTemplate;

import com.alibaba.fastjson.annotation.JSONField;
import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.excel.annotation.ExcelSheetInfo;
import lombok.Data;

import java.util.Date;

@Data
@ExcelSheetInfo(title = "国家畜禽遗传资源保护区变更记录", sheetName = "国家畜禽遗传资源保护区变更记录")
public class ExportZoneRecordBean {

    @ExcelField(title = "编号")
    private String code;

    @ExcelField(title = "保护区名称")
    private String areaName;

    @ExcelField(title = "新保护区名称")
    private String newName;

    @ExcelField(title = "建设单位")
    private String company;

    @ExcelField(title = "新建设单位")
    private String newCompany;

    @ExcelField(title = "保护区范围")
    private String areaRange;

    @ExcelField(title = "新保护区范围")
    private String newRange;

    @ExcelField(title = "变更人")
    private String operator;

    @ExcelField(title = "变更时间")
    @JSONField(format = "yyyy-MM-dd")
    private Date changeTime;


}
