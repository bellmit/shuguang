package com.sofn.agsjdm.vo.exportBean;

import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.excel.annotation.ExcelSheetInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-04-13 15:27
 */
@Data
@ExcelSheetInfo(title = "威胁因素基础信息收集", sheetName = "威胁因素基础信息收集")
public class ThreatFactorExecl {

    /**
     * 湿地区名称
     */
    @ExcelField(title = "湿地区名称")
    private String wetlandName;
    /**
     * 基地城市化影响面积
     */
    @ExcelField(title = "基建和城市化影响面积（hm²）")
    private String cityUrbanization;
    /**
     * 围垦影响面积
     */
    @ExcelField(title = "围垦影响面积（hm²）")
    private String reclamation;
    /**
     * 沙泥淤积影响面积
     */
    @ExcelField(title = "泥沙淤积影响面积（hm²）")
    private String silt;
    /**
     * 污染影响面积
     */
    @ExcelField(title = "污染影响面积（hm²）")
    private Double pollute;
    /**
     * 捕捞和采集影响面积
     */
    @ExcelField(title = "过度捕捞和采集影响面积（hm²）")
    private String fishingHarvesting;
    /**
     * 非法狩猎影响面积
     */
    @ExcelField(title = "非法狩猎影响面积（hm²）")
    private String illegalHuntingue;


    /**
     * 盐碱化面积
     */
    @ExcelField(title = "盐碱化影响面积（hm²）")
    private String salinization;
    /**
     * 外来物种入侵影响面积
     */
    @ExcelField(title = "外来物种入侵影响面积（hm²）")
    private String alienSpecies;
    /**
     * 森林过度砍伐影响面积
     */
    @ExcelField(title = "森林过度采伐影响面积（hm²）")
    private String deforestation;
    /**
     * 沙化影响面积
     */
    @ExcelField(title = "沙化影响面积（hm²）")
    private String desertification;
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
}
