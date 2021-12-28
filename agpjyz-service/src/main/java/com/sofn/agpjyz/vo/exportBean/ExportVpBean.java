package com.sofn.agpjyz.vo.exportBean;

import com.alibaba.fastjson.annotation.JSONField;
import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.excel.annotation.ExcelSheetInfo;
import lombok.Data;

import java.util.Date;

@Data
@ExcelSheetInfo(title = "植被生理参数监测信息采集", sheetName = "植被生理参数监测信息采集")
public class ExportVpBean {

    @ExcelField(title = "保护点名称")
    private String protectValue;
    @ExcelField(title = "植被盖度")
    private String coverDegree;
    @ExcelField(title = "冠层")
    private String canopy;
    @ExcelField(title = "叶面积")
    private String leafArea;
    @ExcelField(title = "操作人")
    private String inputer;
    @ExcelField(title = "操作时间")
    @JSONField(format = "yyyy-MM-dd")
    private Date inputerTime;

}
