package com.sofn.agpjyz.vo.exportBean;

import com.alibaba.fastjson.annotation.JSONField;
import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.excel.annotation.ExcelSheetInfo;
import lombok.Data;

import java.util.Date;

@Data
@ExcelSheetInfo(title = "基础设施维护情况备案登记", sheetName = "基础设施维护情况备案登记")
public class ExportFmBean {

    @ExcelField(title = "保护点名称")
    private String protectValue;
    @ExcelField(title = "基础设施名称")
    private String facilitiesId;
    @JSONField(format = "yyyy-MM-dd")
    @ExcelField(title = "使用日期")
    private Date useDate;
    @ExcelField(title = "故障原因")
    private String fault;
    @ExcelField(title = "解决方案")
    private String solve;
    @ExcelField(title = "维修人")
    private String repairMan;
    @ExcelField(title = "维修时间")
    @JSONField(format = "yyyy-MM-dd")
    private Date repairTime;


}
