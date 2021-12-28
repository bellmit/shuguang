package com.sofn.ducss.model;

import com.sofn.ducss.enums.CheckColorEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 审核信息
 * 这里应该是VO
 */
@Data
@ApiModel
public class CheckInfo {

    /**
     * ID
     */
    private String id;

    /**
     * 年份
     */
    @ApiModelProperty(value = "年度")
    private String year;

    /**
     * 区域ID
     */
    @ApiModelProperty(value = "区域ID")
    private String areaId;

    /**
     * 区域名称
     */
    @ApiModelProperty(value = "区域名称")
    private String areaName;

    /**
     * 审核状态
     */
    @ApiModelProperty(value = "审核状态")
    private String status;

    /**
     * 产生量（万吨）
     */
    @ApiModelProperty(value = "产生量（万吨）原 理论资源量合计（吨）")
    private BigDecimal theoryNumSum;

    /**
     * 产生量（万吨） 颜色标识
     */
    @ApiModelProperty(value = "产生量（万吨） 颜色标识")
    private String theoryNumSumColorState;

    private String theoryNumSumMessage;

    /**
     * 可收集量（万吨）
     */
    @ApiModelProperty(value = "可收集量（万吨）可收集资源量合计（吨）")
    private BigDecimal collectNumSum;

    /**
     * 可收集量（万吨）颜色标识
     */
    @ApiModelProperty(value = "可收集量（万吨）颜色标识")
    private String collectNumSumColorState;

    private String collectNumSumMessage;


    @ApiModelProperty(value = "综合利用量（万吨）")
    private BigDecimal strawUtilizeNumSum;


    @ApiModelProperty(value = " 综合利用量（万吨） 颜色标识")
    private String strawUtilizeNumSumColorState;

    private String strawUtilizeNumSumMessage;

    /**
     * 综合利用率
     */
    @ApiModelProperty(value = "综合利用率")
    private BigDecimal synUtilizeNumSum;

    /**
     * 综合利用率  颜色标识
     */
    @ApiModelProperty(value = "综合利用率  颜色标识")
    private String synUtilizeNumSumColorState;

    private String synUtilizeNumSumMessage;

    /**
     * 直接还田量合计（吨）
     */
    @ApiModelProperty(value = "直接还田量合计（万吨）")
    private BigDecimal directReturnNumSum;

    /**
     * 农户分散利用量合计（吨）  颜色标识
     */
    @ApiModelProperty(value = "农户分散利用量合计（万吨）  颜色标识")
    private String directReturnNumSumColorState;

    private String directReturnNumSumMessage;


    /**
     * 离田利用量
     */
    @ApiModelProperty(value = " 离田利用量")
    private BigDecimal leavingUtilization;

    /**
     * 离田利用量颜色标识
     */
    @ApiModelProperty(value = "离田利用量颜色标识")
    private String leavingUtilizationColorState;

    private String leavingUtilizationMessage;

    /**
     * 抽样分散户数
     */
    @ApiModelProperty(value = "抽样分散户数")
    private Integer scatteredHouseNum;

    /**
     * 抽样分散户数颜色标识
     */
    @ApiModelProperty(value = "抽样分散户数颜色标识")
    private String scatteredHouseNumColorState;

    private String scatteredHouseNumMessage;

    /**
     * 市场主体规模化数量
     */
    @ApiModelProperty(value = "市场主体规模化数量")
    private Integer mainBodyNum;

    /**
     * 市场主体规模化数量颜色标识
     */
    @ApiModelProperty(value = "市场主体规模化数量颜色标识")
    private String mainBodyNumColorState;

    private String mainBodyNumMessage;

    /**
     * 该字段用于计算综合利用量得，不做其他展示
     */
    private BigDecimal strawUtilizationV2;

    /**
     * 该字段用于计算综合利用量得，不做其他展示
     */
    private BigDecimal collectResourceV2;

    /**
     * 清除属性值
     */
    public void clearAttrValue() {
        String message = "此数值正常";
        String code = CheckColorEnum.COLOR_UNCHANGE.getCode();
        BigDecimal value = BigDecimal.ZERO;
        this.setTheoryNumSum(value);
        this.setTheoryNumSumMessage(message);
        this.setTheoryNumSumColorState(code);
        this.setCollectNumSum(value);
        this.setCollectNumSumColorState(code);
        this.setCollectNumSumMessage(message);
        this.setStrawUtilizeNumSum(value);
        this.setStrawUtilizeNumSumMessage(message);
        this.setStrawUtilizeNumSumColorState(code);
        this.setSynUtilizeNumSum(value);
        this.setSynUtilizeNumSumMessage(message);
        this.setSynUtilizeNumSumColorState(code);
        this.setDirectReturnNumSum(value);
        this.setDirectReturnNumSumColorState(code);
        this.setDirectReturnNumSumMessage(message);
        this.setLeavingUtilization(value);
        this.setLeavingUtilizationMessage(message);
        this.setLeavingUtilizationColorState(code);
        this.setScatteredHouseNum(value.intValue());
        this.setScatteredHouseNumMessage(message);
        this.setScatteredHouseNumColorState(code);
        this.setMainBodyNum(value.intValue());
        this.setMainBodyNumMessage(message);
        this.setMainBodyNumColorState(code);
    }
}
