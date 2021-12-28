package com.sofn.fdpi.vo;

import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.excel.annotation.ExcelImportCheck;
import lombok.Data;


/**
 * @Description:
 * @author: xiaobo
 * @Date: 2020-12-18 11:27
 */
@Data
public class ArtPaperExcelFrom {
    @ExcelField(title = "序号", align = ExcelField.Align.RIGHT)
    @ExcelImportCheck(isNull = false)
    private String id;
    @ExcelField(title = "证书类型", dictType = "papersType")
    @ExcelImportCheck(isNull = false, errMsg = "证书类型必选")
    private String papersType;

    @ExcelField(title = "证书编号")
    @ExcelImportCheck(isNull = false, max = 50, errMsg = "证书编号不能为空且长度不超过50")
    private String papersNumber;

    @ExcelField(title = "企业名称")
    @ExcelImportCheck(isNull = false, max = 50, errMsg = "企业名称不能为空且长度不超过50")
    private String compName;

    @ExcelField(title = "企业地址")
    @ExcelImportCheck(isNull = false, max = 50, errMsg = "企业地址不能为空且长度不超过50位")
    private String compAddress;

    @ExcelField(title = "法人代表")
    @ExcelImportCheck(isNull = false, max = 50, errMsg = "法人代表不能为空且不长度超过50位")
    private String legal;

    @ExcelField(title = "技术负责人")
    @ExcelImportCheck(isNull = false, max = 100, errMsg = "技术负责人不能为空且不长度超过100位")
    private String technicalDirector;

    @ExcelField(title = "人工繁育目的", dictType = "purpose")
    @ExcelImportCheck(isNull = false, errMsg = "人工繁育目的必选")
    private String purpose;


    @ExcelField(title = "有效日期", dataFormat = "yyyy-MM-dd")
    @ExcelImportCheck(isNull = false)
    private String term;

    @ExcelField(title = "发证机关")
    @ExcelImportCheck(isNull = false, max = 50, errMsg = "发证机关不能为空且长度不超过50位")
    private String issueUnit;

    @ExcelField(title = "发证日期", dataFormat = "yyyy-MM-dd", align = ExcelField.Align.RIGHT)
    private String issueDate;

    @ExcelField(title = "物种学名")
    @ExcelImportCheck(isNull = false, max = 50, errMsg = "物种学名不能为空且长度不超过50位")
    private String specName;

    @ExcelImportCheck(isNull = false, max = 100, errMsg = "来源地长度不超过100位")
    @ExcelField(title = "来源地")
    private String origin;

    @ExcelImportCheck(isNull = false, max = 100, errMsg = "方式长度不超过100位")
    @ExcelField(title = "方式")
    private String mode;


    @ExcelField(title = "数量")
    private String amount;

    @ExcelField(title = "单位")
    private String unit;
}
