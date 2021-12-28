package com.sofn.agpjyz.vo.exportBean;

import com.alibaba.fastjson.annotation.JSONField;
import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.excel.annotation.ExcelSheetInfo;
import lombok.Data;

import java.util.Date;

@Data
@ExcelSheetInfo(title = "威胁因素基础信息", sheetName = "威胁因素基础信息")
public class ExportTfBean {

    @ExcelField(title = "保护点名称")
    private String protectValue;
    @ExcelField(title = "采挖受损面积")
    private String excavation;
    @ExcelField(title = "放牧受损面积")
    private String graze;
    @ExcelField(title = "偷牧受损面积")
    private String stealing;
    @ExcelField(title = "砍伐受损面积")
    private String felling;
    @ExcelField(title = "火烧受损面积")
    private String destroyFire;
    @ExcelField(title = "废渣数量及描述")
    private String wasteResidue;
    @ExcelField(title = "道路数量及描述")
    private String roads;
    @ExcelField(title = "厂矿数量及描述")
    private String factoriesMines;
    @ExcelField(title = "建筑数量及描述")
    private String builds;
    @ExcelField(title = "设施数量及描述")
    private String facilities;
    @ExcelField(title = "操作人")
    private String inputer;
    @ExcelField(title = "操作时间")
    @JSONField(format = "yyyy-MM-dd")
    private Date inputerTime;

}
