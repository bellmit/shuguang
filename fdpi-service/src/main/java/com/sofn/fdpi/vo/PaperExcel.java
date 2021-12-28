package com.sofn.fdpi.vo;


import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.excel.annotation.ExcelImportCheck;
import com.sofn.common.utils.DateUtils;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-03-12 10:45
 */
@Data
public class PaperExcel {

    @ExcelField(title = "序号")
    @ExcelImportCheck(isNull = false)
    private String id;
    @ExcelField(title = "证书类型")
    @ExcelImportCheck(isNull = false, max = 10, errMsg = "证书类型不能为空")
    private String papersType;

    @ExcelField(title = "证书编号")
    @ExcelImportCheck(isNull = false, max = 30, errMsg = "证书编号不能为空且长度不超过30位")
    private String papersNumber;

    @ExcelField(title = "企业名称")
    @ExcelImportCheck(isNull = false, max = 30, errMsg = "企业名称不能为空且长度不超过30位")
    private String compName;

    @ExcelField(title = "企业地址")
    @ExcelImportCheck(isNull = false, max = 50, errMsg = "企业地址不能为空且长度不超过50位")
    private String compAddress;

    @ExcelField(title = "法人代表")
    @ExcelImportCheck(isNull = false, max = 30, errMsg = "法人代表不能为空且不长度超过30位")
    private String legal;

    @ExcelField(title = "技术负责人")
    @ExcelImportCheck(isNull = false, max = 10, errMsg = "技术负责人不能为空且不长度超过10位")
    private String technicalDirector;


    @ExcelField(title = "人工繁育目的", dictType = "purpose")
    @ExcelImportCheck(isNull = false, max = 50, errMsg = "人工繁育目的不能为空且不长度超过50位")
    private String purpose;

    @ExcelField(title = "经营方式")
    @ExcelImportCheck(isNull = false, max = 100, errMsg = "经营方式不能为空且长度不超过100位")
    private String modeOperation;

    @ExcelField(title = "销售去向")
    @ExcelImportCheck(isNull = false, max = 100, errMsg = "销售去向不能为空且长度不超过100位")
    private String salesDestination;

    @ExcelField(title = "有效日期", dataFormat = "yyyy-MM-dd", align = ExcelField.Align.RIGHT)
    @ExcelImportCheck(isNull = false)
    private Date term;
    @ExcelImportCheck(isNull = false)
    @ExcelField(title = "发证日期", dataFormat = "yyyy-MM-dd", align = ExcelField.Align.RIGHT)
    private Date issueDate;
    @ExcelField(title = "发证机关")
    @ExcelImportCheck(isNull = false, max = 30, errMsg = "发证机关不能为空且长度不超过30位")
    private String issueUnit;
    @ExcelField(title = "物种学名")
    @ExcelImportCheck(isNull = false, max = 30, errMsg = "物种学名不能为空且长度不超过30位")
    private String specName;
    @ExcelImportCheck(isNull = false, max = 100, errMsg = "来源地长度不超过100位")
    @ExcelField(title = "来源地")
    private String origin;
    @ExcelImportCheck(isNull = false, max = 100, errMsg = "方式长度不超过100位")
    @ExcelField(title = "方式")
    private String mode;
    //    @ExcelImportCheck(max = 1000000, errMsg = "数量不超过100万")
    @ExcelField(title = "数量")
    private Integer amount;
    @ExcelField(title = "单位")
    private String unit;

    public static PaperExcel form2Vo(ManPaperExeclFrom from) {
        PaperExcel vo = new PaperExcel();
        BeanUtils.copyProperties(from, vo);
        vo.setAmount(Integer.parseInt(from.getAmount()));
        vo.setTerm(DateUtils.stringToDate(from.getTerm(), "yyyy/MM/dd"));
        vo.setIssueDate(DateUtils.stringToDate(from.getIssueDate(), "yyyy/MM/dd"));
        return vo;
    }

    public static PaperExcel form2Vo(ArtPaperExcelFrom from) {
        PaperExcel vo = new PaperExcel();
        BeanUtils.copyProperties(from, vo);
        vo.setAmount(Integer.parseInt(from.getAmount()));
        vo.setTerm(DateUtils.stringToDate(from.getTerm(), "yyyy/MM/dd"));
        vo.setIssueDate(DateUtils.stringToDate(from.getIssueDate(), "yyyy/MM/dd"));
        return vo;
    }
}
