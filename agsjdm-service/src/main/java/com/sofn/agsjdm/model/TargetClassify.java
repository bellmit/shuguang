package com.sofn.agsjdm.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;


/**
 * @Author yumao
 * @Date 2020/4/14 9:47
 **/
@ApiModel("指标分类对象")
@Data
@TableName("TARGET_CLASSIFY")
public class TargetClassify {

    @ApiModelProperty(value = "主键")
    private String id;

    @NotBlank(message = "指标分类值不可为空")
    @ApiModelProperty(value = "指标分类值")
    private String targetVal;

}
