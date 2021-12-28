package com.sofn.agsjdm.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @Author yumao
 * @Date 2020/4/14 9:47
 **/
@ApiModel("水质监测信息采集对象")
@Data
@TableName("WATER_QUALITY")
public class WaterQuality {

    @ApiModelProperty(value = "主键")
    private String id;

    @NotBlank(message = "湿地区ID不可为空")
    @ApiModelProperty(value = "湿地区ID")
    private String wetlandId;

    @ApiModelProperty(value = "湿地区名称(前端新增/修改不用传)")
    @TableField(exist = false)
    private String wetlandName;

    @Length(min = 0, max = 64)
    @NotBlank(message = "水源补给状况至少选择一个")
    @ApiModelProperty(value = "水源补给状况")
    private String waterSupply;

    @Length(min = 0, max = 64)
    @NotBlank(message = "流出状况为必选项")
    @ApiModelProperty(value = "流出状况")
    private String flowOut;

    @Length(min = 0, max = 64)
    @NotBlank(message = "积水状况为必选项")
    @ApiModelProperty(value = "积水状况")
    private String accWater;

    @Range(min = 0, message = "枯水位数值无效")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "枯水位")
    private Double lowWater;

    @Range(min = 0, message = "平水位数值无效")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "平水位")
    private Double flatWater;

    @Range(min = 0, message = "丰水位数值无效")
    @ApiModelProperty(value = "丰水位")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Double abundantWater;

    @Range(min = 0, message = "蓄水量数值无效")
    @ApiModelProperty(value = "蓄水量")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Double waterDemand;

    @Range(min = 0, message = "最大水深数值无效")
    @ApiModelProperty(value = "最大水深")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Double maxDepth;

    @Range(min = 0, message = "平均水深数值无效")
    @ApiModelProperty(value = "平均水深")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Double avgDepth;

    @ApiModelProperty(value = "监测人")
    private String operator;

    @ApiModelProperty(value = "监测时间")
    private Date operatorTime;

    private String province;
    private String city;
    private String county;

}
