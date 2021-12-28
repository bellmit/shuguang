package com.sofn.fdpi.vo;

import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.excel.annotation.ExcelImportCheck;
import com.sofn.common.excel.annotation.ExcelSheetInfo;
import lombok.Data;

/**
 * @Description:
 * @author: xiaobo
 * @Date: 2020-12-18 11:30
 */
@Data
public class ManPaperExeclFrom {
    @ExcelField(title = "序号")
    @ExcelImportCheck(isNull = false)
    private String id;

    @ExcelField(title = "证书类型", dictType = "papersType")
    @ExcelImportCheck(isNull = false, errMsg = "证书类型必选")
    private String papersType;

    @ExcelField(title = "证书编号")
    @ExcelImportCheck(isNull = false, max = 50, errMsg = "证书编号不能为空且长度不超过50位")
    private String papersNumber;

    @ExcelField(title = "企业名称")
    @ExcelImportCheck(isNull = false, max = 50, errMsg = "企业名称不能为空且长度不超过50位")
    private String compName;

    @ExcelField(title = "企业地址")
    @ExcelImportCheck(isNull = false, max = 50, errMsg = "企业地址不能为空且长度不超过50位")
    private String compAddress;

    @ExcelField(title = "法人代表")
    @ExcelImportCheck(isNull = false, max = 50, errMsg = "法人代表不能为空且不长度超过50位")
    private String legal;

    @ExcelField(title = "经营方式")
    @ExcelImportCheck(isNull = false, max = 50, errMsg = "经营方式不能为空且长度不超过50位")
    private String modeOperation;

    @ExcelField(title = "销售去向")
    @ExcelImportCheck(isNull = false, max = 50, errMsg = "销售去向不能为空且长度不超过50位")
    private String salesDestination;

    @ExcelField(title = "有效日期", dataFormat = "yyyy-MM-dd", align = ExcelField.Align.RIGHT)
    @ExcelImportCheck(isNull = false)
    private String term;
    @ExcelImportCheck(isNull = false)
    @ExcelField(title = "发证日期", dataFormat = "yyyy-MM-dd", align = ExcelField.Align.RIGHT)
    private String issueDate;
    @ExcelField(title = "发证机关")
    @ExcelImportCheck(isNull = false, max = 50, errMsg = "发证机关不能为空且长度不超过50位")
    private String issueUnit;
    @ExcelField(title = "物种学名")
    @ExcelImportCheck(isNull = false, max = 50, errMsg = "物种学名不能为空且长度不超过50位")
    private String specName;

    @ExcelField(title = "来源地")
    @ExcelImportCheck(isNull = false, max = 100, errMsg = "来源地长度不超过100位")
    private String origin;
    @ExcelImportCheck(isNull = false, max = 100, errMsg = "方式长度不超过100位")
    @ExcelField(title = "方式")
    private String mode;

    @ExcelField(title = "数量")
    @ExcelImportCheck(max = 10, errMsg = "数量长度不超过10位")
    private String amount;

    @ExcelField(title = "单位")
    private String unit;
}
