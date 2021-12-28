package com.sofn.agsjdm.vo.exportBean;

import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.excel.annotation.ExcelSheetInfo;
import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-04-13 14:38
 */

@Data
@ExcelSheetInfo(title = "基础信息收集", sheetName = "基础信息收集")
public class BasicsCollectionExecl {


    /**
     * 湿地区名称
     */
    @ExcelField(title = "湿地区名称")
    private String wetlandName;
    /**
     * 目标物种名称
     */
    @ExcelField(title = "目标物种名称")
    private String specValue;
    /**
     * 数量
     */
    @ExcelField(title = "数量（个或株）")
    private Double amount;
    /**
     * 生长状况
     */
    @ExcelField(title = "生长状况")
    private String grow;
    /**
     * 物种丰富度
     */
    @ExcelField(title = "物种丰富度")
    private String richness;

    @ExcelField(title = "伴生物种名称")

    private String comSpecies;
    /**
     * 伴生物种数量
     */
    @ExcelField(title = "伴生物种数量")
    private String speciesNumber;
    /**
     * 操作人
     */
    @ExcelField(title = "检测人")

    private String operator;
    /**
     * 操作时间
     */
    @ExcelField(title = "检测时间")
    private Date operatorTime;
    /**
     * 伴生物种名称
     */

}