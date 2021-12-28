package com.sofn.agsjdm.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-04-13 13:23
 */
@ApiModel("基础信息收集表单对象")
@Data
public class BasicsCollectionForm {
    /**
     * 主键
     */
    @ApiModelProperty("主键,（新增不传）")
    private String id;
    /**
     * 湿地区ID
     */
    @NotBlank(message = "湿地区ID不可为空")
    @ApiModelProperty("湿地区ID")
    private String wetlandId;
    /**
     * 目标物种名称
     */
    @Length(min = 0, max = 64, message = "目标物种名称长度不能超过64位")
    @ApiModelProperty("目标物种名称")
    private String specValue;
    /**
     * 数量
     */
    @ApiModelProperty("数量")
    private Double amount;
    /**
     * 生长状况
     */
    @Length(min = 0, max = 64, message = "生长状况长度不能超过64位")
    @ApiModelProperty("生长状况")
    private String grow;
    /**
     * 物种丰富度
     */
    @Length(min = 0, max = 64, message = "物种丰富度长度不能超过64位")
    @ApiModelProperty("物种丰富度")
    private String richness;
    /**
     * 操作人
     */
    @ApiModelProperty("操作人")
    private String operator;
    /**
     * 操作时间
     */
    @ApiModelProperty("操作时间")
    private Date operatorTime;
    /**
     * 伴生物种名称
     */
    @Length(min = 0, max = 64, message = "伴生物种名称不能超过64位")
    @ApiModelProperty("伴生物种名称")
    private String comSpecies;
    /**
     * 伴生物种数量
     */
    @Length(min = 0, max = 64, message = "伴生物种数量不能超过64位")
    @Pattern(regexp = "\\d*", message = "伴生物种数量只能是正整数")
    @ApiModelProperty("伴生物种数量")
    private String speciesNumber;
}
