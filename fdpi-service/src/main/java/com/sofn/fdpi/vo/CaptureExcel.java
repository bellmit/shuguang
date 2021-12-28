package com.sofn.fdpi.vo;

import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.excel.annotation.ExcelImportCheck;
import com.sofn.common.excel.annotation.ExcelSheetInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-03-09 12:58
 */
@Data
@ExcelSheetInfo(sheetName = "特许猎捕证导入模板")
public class CaptureExcel {
    @ExcelField(title = "序号")
    @ExcelImportCheck(isNull =  false)
    private String id;

    @ExcelField(title = "证书编号")
    @ExcelImportCheck(isNull =  false,max = 30,errMsg = "证书编号不能为空且长度不超过30位")
    private String papersNumber;

    @ExcelImportCheck(isNull =  false,max = 20,errMsg = "猎捕持证单位不能为空且长度不超过20位")
    @ExcelField(title = " 猎捕持证单位（人）")
    private String capUnit;

    @ExcelImportCheck(isNull =  false,max = 20,errMsg = "批准文号不能为空且长度不超过20位")
    @ExcelField(title = "批准文号")
    @ApiModelProperty(value = "批准文号")
    private String appNum;

    @ExcelImportCheck(isNull =  false,max = 50,errMsg = "猎捕事由不能为空且长度不超过50位")
    @ExcelField(title = "猎捕事由")
    private String cause;

    @ExcelImportCheck(isNull =  false, max = 10,errMsg = "猎捕事由不能为空且长度不超过10位" )
    @ExcelField(title = "保护级别",dictType = "level")
    private String proLevel;

    @ExcelImportCheck(isNull =  false,max = 30,errMsg = "物种学名不能为空且长度不超过30位")
    @ExcelField(title = "物种学名（物种名要在本系统中存在）")
    private String speName;

    @ExcelImportCheck(isNull =  false,min = 0,max = 10,errMsg = "数量不超过10位")
    @ExcelField(title = "数量或重量")
    private Integer capNum;

    @ExcelImportCheck(isNull =  false)
    @ExcelField(title = "有效期",dataFormat = "yyyy/MM/dd-yyyy/MM/dd")
    private String term;
    @ExcelImportCheck(isNull =  false,max = 10,errMsg = "猎捕地点（省/直辖市）不能为空且长度不超过10位")
    @ExcelField(title = "猎捕地点（省/直辖市）")
    private String province;
    @ExcelImportCheck(isNull =  false,max = 10,errMsg = "猎捕地点（市）不能为空且长度不超过10位")
    @ExcelField(title = "猎捕地点（市）")
    private String city;
    @ExcelImportCheck(isNull =  false,max = 10,errMsg = "猎捕地点（区）不能为空且长度不超过10位")
    @ExcelField(title = "猎捕地点（区）")
    private String area;
    @ExcelImportCheck(isNull =  false,max = 50,errMsg = "猎捕地点不能为空且长度不超过50位")
    @ExcelField(title = "猎捕地点（具体地点）")
    private String capLocal;

    @ExcelImportCheck(isNull =  false,max = 20,errMsg = "猎捕方式不能为空且长度不超过20位")
    @ExcelField(title = "猎捕方式")
    private String capWay;

    @ExcelImportCheck(isNull =  false,max = 20,errMsg = "发证机关不能为空且长度不超过20位")
    @ExcelField(title = "发证机关")
    private String issueUnit;

    @ExcelImportCheck(isNull =  false)
    @ExcelField(title = "发证日期",dataFormat = "yyyy-MM-dd",align = ExcelField.Align.RIGHT)
    private Date issueDate;
}
