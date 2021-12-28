package com.sofn.agzirdd.excelmodel;

import com.alibaba.fastjson.annotation.JSONField;
import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.excel.annotation.ExcelSheetInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Description: 外来入侵生物阈值信息导出模板
 * @Author: chlf
 * @Date: 2020\3\31
 */
@Data
@ApiModel(value = "WarningThresholdXlsx", description = "外来入侵物种阈值信息")
@ExcelSheetInfo(title = "外来入侵生物阈值信息列表", sheetName = "sheet1")
public class WarningThresholdExcel {
    @ExcelField(title = "物种名")
    @ApiModelProperty(name = "speciesName", value = "物种名")
    private String speciesName;

    @ExcelField(title = "指标分类")
    @ApiModelProperty(name = "classificationName", value = "指标分类名")
    private String classificationName;

    @ExcelField(title = "推送方式")
    @ApiModelProperty(name = "pushWay", value = "推送方式")
    private String pushWay;

    @ExcelField(title = "值1")
    @ApiModelProperty(name = "value1", value = "条件1的值,小于条件2（若条件2有值）的值,前端需要控制条件1必须有值")
    private Long value1;

    @ExcelField(title = "条件1")
    @ApiModelProperty(name = "condition1", value = "条件1:0-'>',1-'>=',2-'=',3-'!=';前端只需要传数字0~3即可")
    private String condition1;

    @ExcelField(title = "颜色值")
    @ApiModelProperty(name = "color", value = "十六进颜色值，如：#FFB6C1")
    private String color;

    @ExcelField(title = "条件2")
    @ApiModelProperty(name = "condition2", value = "条件2:0-'<',1-'<=',2-'=',3-'!=';前端只需要传数字0~3即可")
    private String condition2;

    @ExcelField(title = "值2")
    @ApiModelProperty(name = "value2", value = "条件2的值,可以为空")
    private Long value2;

    @ExcelField(title = "风险等级")
    @ApiModelProperty(name = "riskLevel", value = "风险等级，风险等级：0-轻；1-中；2-重，接口中只需要传数字")
    private String riskLevel;

    @ExcelField(title = "创建者")
    @ApiModelProperty(name = "createUserName", value = "创建者名")
    private String createUserName;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @ExcelField(title = "创建时间")
    @ApiModelProperty(name = "createTime", value = "创建时间")
    private Date createTime;
}
