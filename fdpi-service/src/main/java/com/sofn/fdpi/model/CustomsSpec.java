package com.sofn.fdpi.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Size;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-06-29 14:51
 */
@ApiModel("海关物种对象")
@TableName("CUSTOMS_SPEC")
@Data
public class CustomsSpec {
    @ApiModelProperty("主键id")
    private String id;
    @Size(max = 20,message = "货物名称不超过20位")
    @ApiModelProperty("货物名称")
    private String speciesName;
    @Size(max = 10,message = "单位不超过10位")
    @ApiModelProperty("单位")
    private String unit;
    @ApiModelProperty("海关ID")
    private String customsId;
}
