package com.sofn.agsjdm.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * @Author yumao
 * @Date 2020/4/16 9:15
 **/
@ApiModel("生物监测信息采集对象")
@Data
@TableName("BIOMONITORING")
public class Biomonitoring {

    @ApiModelProperty(value = "主键")
    private String id;

    @NotBlank(message = "湿地区ID不可为空")
    @ApiModelProperty(value = "湿地区ID")
    private String wetlandId;

    @ApiModelProperty(value = "湿地区名称(前端新增/修改不用传)")
    @TableField(exist = false)
    private String wetlandName;

    @NotBlank(message = "生物分类为必填项")
    @ApiModelProperty(value = "生物分类")
    private String biologicalAxonomy;

    @NotBlank(message = "中文名为必填项")
    @ApiModelProperty(value = "中文名")
    private String chineseName;

    @Length(max = 64, message = "拉丁学名长度不能超过64")
    @ApiModelProperty(value = "拉丁名")
    private String latinName;

    @Pattern(regexp = "[一二三]?", message = "保护级别只能为一或二或三")
    @ApiModelProperty(value = "保护级别")
    private String proLevel;

    @Range(min = 0, message = "物种数量只能为正整数")
    @ApiModelProperty(value = "种群数量")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Integer populationSize;

    @Length(max = 64, message = "居留型字符长度不能超过64")
    @ApiModelProperty(value = "居留型")
    private String resident;

    @Length(max = 64, message = "调查方法字符长度不能超过64")
    @ApiModelProperty(value = "调查方法")
    private String investigation;

    @Length(max = 100, message = "备注的字符长度不能超过100")
    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "采集人")
    private String operator;

    @ApiModelProperty(value = "采集时间")
    private Date operatorTime;

    private String province;
    private String city;
    private String county;
}
