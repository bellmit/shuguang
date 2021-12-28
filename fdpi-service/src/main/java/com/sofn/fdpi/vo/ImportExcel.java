package com.sofn.fdpi.vo;

import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.excel.annotation.ExcelImportCheck;
import com.sofn.common.excel.annotation.ExcelSheetInfo;
import lombok.Data;


import java.util.Date;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-03-09 16:35
 */
@Data
@ExcelSheetInfo(sheetName = "进出口信息导入模板",title = "备注导入进出口信息是多物种时（请新建一行数据进出口信息与之前的进出口信息相同，物种信息（后5列信息从物种学名开始）不同即可）")
public class ImportExcel {
    @ExcelField(title = "序号")
    @ExcelImportCheck(isNull =  false)
    private  String id;
    /**
     * 进出口审核编号
     */
    @ExcelImportCheck(isNull =  false,max = 30,errMsg = "审批表编号不能为空且长度不超过30位")
    @ExcelField(title = "审批表编号")
    private String impAuform;
    /**
     * 进口单位
     */
    @ExcelImportCheck(max = 20,errMsg = "进口单位长度不超过20位")
    @ExcelField(title = "进口单位")
    private String impComp;
    /**
     * 出口、再出口单位
     */
    @ExcelImportCheck(max = 20,errMsg = "出口、再出口单位长度不超过20位")
    @ExcelField(title = "出口、再出口单位")
    private String exComp;
    /**
     * 有效日期
     */

    @ExcelImportCheck(isNull =  false)
    @ExcelField(title = "有效期至",dataFormat = "yyyy-MM-dd",align = ExcelField.Align.RIGHT)
    private Date validityTime;
    /**
     *   签证机关
     */
    @ExcelImportCheck(isNull =  false,max = 20,errMsg = "签证机关不能为空且长度不超过20位")
    @ExcelField(title = "签证机关")
    private String visaAuth;
    /**
     * 签发日期
     */

    @ExcelImportCheck(isNull =  false)
    @ExcelField(title = "签证日期",dataFormat = "yyyy-MM-dd",align = ExcelField.Align.RIGHT)
    private Date issueDate;

    @ExcelImportCheck(isNull =  false,max = 10,errMsg = "物种学名不能为空且长度不超过10位")
    @ExcelField(title = "物种学名")
    private String speName;
    @ExcelField(title = "保护级别")
    @ExcelImportCheck(isNull =  false,max = 10,errMsg = "保护级别长度不超过10位")
    private String proLevel;
    @ExcelField(title = "数量或重量")
    @ExcelImportCheck(isNull =  false,max = 10,errMsg = "数量或重量长度不超过2位且不能为空")
    private String amount;

    @ExcelField(title = "来源地或目的地")
    @ExcelImportCheck(isNull =  false,max = 20,errMsg = "来源地或目的地长度不超过20位且不能为空")
    private String source;
    @ExcelField(title = "进出口岸")
    @ExcelImportCheck(isNull =  false,max = 20,errMsg = "进出口岸长度不超过20位且不能为空")
    private String port;
}
