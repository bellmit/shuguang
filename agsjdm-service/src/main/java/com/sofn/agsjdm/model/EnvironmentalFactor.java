package com.sofn.agsjdm.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * 环境因子监测信息类
 *
 * @Author yumao
 * @Date 2020/3/6 11:13
 **/
@Data
@TableName("ENVIRONMENTAL_FACTOR")
@ApiModel(value = "环境因子监测信息对象")
public class EnvironmentalFactor {

    @ApiModelProperty(value = "主键")
    private String id;

    @NotBlank(message = "湿地区ID不能为空")
    @ApiModelProperty(value = "湿地区ID")
    private String wetlandId;

    @ApiModelProperty(value = "湿地区名称(前端新增/修改不用传)")
    @TableField(exist = false)
    private String wetlandName;

    @Range(min = -273, max = 200, message = "无效的气温录入")
    @ApiModelProperty(value = "气温")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String airTem;

    @Range(min = -100, max = 15000, message = "有效积温范围在-100-15000之间")
    @ApiModelProperty(value = "积温")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String accTem;

    @Range(min = 0, message = "年降水量只能是正整数")
    @ApiModelProperty(value = "年降水")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String annualPre;

    @Range(min = 0, message = "蒸发量只能是正整数")
    @ApiModelProperty(value = "蒸发量")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String evaporation;

    @ApiModelProperty(value = "操作人")
    private String operator;

    @ApiModelProperty(value = "操作时间")
    private Date operatorTime;

    private String province;
    private String city;
    private String county;

}
