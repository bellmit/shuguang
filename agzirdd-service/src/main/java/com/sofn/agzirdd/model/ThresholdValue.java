package com.sofn.agzirdd.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
* @Author Chlf
* @Description //预警阈值设置实体类
* @Date 14:08 2020/3/27
* @Param
* @Return
**/
@TableName("THRESHOLD_VALUE")
@Data
public class ThresholdValue extends Model<ThresholdValue> {
    @TableId(type = IdType.UUID)
    @ApiModelProperty(name = "id", value = "id(前端不用传值)", hidden = true)
    private String id;
    @ApiModelProperty(name = "wtId", value = "监测预警阈值表id(前端不需要传值)", hidden = true)
    private String wtId;
    @ApiModelProperty(name = "condition1", value = "条件1:0-'>=',1-'<',2-'=',3-'!=';前端只需要传数字0~3即可")
    private String condition1;
    @ApiModelProperty(name = "value1", value = "条件1的值,小于条件2（若条件2有值）的值,前端需要控制条件1必须有值")
    private BigDecimal value1;
    @ApiModelProperty(name = "condition2", value = "条件2:0-'>=',1-'<',2-'=',3-'!=';前端只需要传数字0~3即可")
    private String condition2;
    @ApiModelProperty(name = "value2", value = "条件2的值,可以为空")
    private BigDecimal value2;
    @ApiModelProperty(name = "riskLevel", value = "风险等级，风险等级：0-轻；1-中；2-重，接口中只需要传数字")
    private String riskLevel;
    @ApiModelProperty(name = "color", value = "十六进颜色值，如：#FFB6C1")
    private String color;

}