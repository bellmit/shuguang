package com.sofn.agsjdm.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-04-13 15:17
 */
@ApiModel("威胁因素基础信息表单对象")
@Data
public class ThreatFactorForm {
    /**
    * 主键
    */
    @ApiModelProperty("主键")
    private String id;
    /**
     * 湿地区ID
     */
    @NotBlank(message = "湿地区ID不可为空")
    @ApiModelProperty("湿地区ID")
    private String wetlandId;
    /**
     * 基地城市化影响面积
     */
    @Length(min = 0, max = 64, message = "基地城市化影响面积长度不能长度64位")
    @Pattern(regexp = "(^\\d+(\\.\\d+)?$)?", message = "基地城市化影响面积只能是整数或小数")
    @ApiModelProperty("基地城市化影响面积")
    private String cityUrbanization;
    /**
     * 围垦影响面积
     */
    @Pattern(regexp = "(^\\d+(\\.\\d+)?$)?", message = "围垦影响面积只能是整数或小数")
    @Length(min = 0, max = 64, message = "围垦影响面积长度不能超过64位")
    @ApiModelProperty("围垦影响面积")
    private String reclamation;
    /**
     * 沙泥淤积影响面积
     */
    @Length(min = 0, max = 64, message = "沙泥淤积影响面积长度不能超过64位")
    @Pattern(regexp = "(^\\d+(\\.\\d+)?$)?", message = "沙泥淤积影响面积只能整数或小数")
    @ApiModelProperty("沙泥淤积影响面积")
    private String silt;
    /**
     * 污染影响面积
     */
    @NotNull(message = "污染影响面积不能为空")
    @Range(min = 0, message = "污染影响面积需要在0和9223372036854775807之间")
    @ApiModelProperty("污染影响面积")
    private Double pollute;
    /**
     * 捕捞和采集影响面积
     */
    @Length(min = 0, max = 64, message = "捕捞和采集影响面积长度不能超过64位")
    @Pattern(regexp = "(^\\d+(\\.\\d+)?$)?", message = "捕捞和采集影响面积只能是整数或小数")
    @ApiModelProperty("捕捞和采集影响面积")
    private String fishingHarvesting;
    /**
     * 非法狩猎影响面积
     */
    @Length(min = 0, max = 64, message = "非法狩猎影响面积长度不能超过64位")
    @Pattern(regexp = "(^\\d+(\\.\\d+)?$)?", message = "非法狩猎影响面积只能是整数或小数")
    @ApiModelProperty("非法狩猎影响面积")
    private String illegalHuntingue;


    /**
     * 盐碱化面积
     */
    @Length(min = 0, max = 64, message = "盐碱化面积长度不能超过64位")
    @Pattern(regexp = "(^\\d+(\\.\\d+)?$)?", message = "盐碱化面积只能是整数或小数")
    @ApiModelProperty("盐碱化面积")
    private String salinization;
    /**
     * 外来物种入侵影响面积
     */
    @Length(min = 0, max = 64, message = "外来物种入侵影响面积长度不能超过64位")
    @Pattern(regexp = "(^\\d+(\\.\\d+)?$)?", message = "外来物种入侵影响面积只能是整数或小数")
    @ApiModelProperty("外来物种入侵影响面积")
    private String alienSpecies;
    /**
     * 森林过度砍伐影响面积
     */
    @Length(min = 0, max = 64, message = "森林过度砍伐影响面积长度不能超过64位")
    @Pattern(regexp = "(^\\d+(\\.\\d+)?$)?", message = "森林过度砍伐影响面积只能是整数或小数")
    @ApiModelProperty("森林过度砍伐影响面积")
    private String deforestation;
    /**
     * 沙化影响面积
     */
    @Length(min = 0, max = 64, message = "沙化影响面积长度不能超过64位")
    @Pattern(regexp = "(^\\d+(\\.\\d+)?$)?", message = "沙化影响面积只能是整数或小数")
    @ApiModelProperty("沙化影响面积")
    private String desertification;
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
}
