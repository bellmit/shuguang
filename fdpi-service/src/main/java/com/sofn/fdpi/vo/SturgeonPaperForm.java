package com.sofn.fdpi.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

@Data
@ApiModel(value = "鲟鱼子酱标签纸表单对象")
public class SturgeonPaperForm {

    @ApiModelProperty(value = "主键，修改时必填")
    private String id;
    @DecimalMax(value = "1000000000",message = "A数量必须小于10亿")
    @DecimalMin(value = "0", message = "A数量必须大于0")
    @ApiModelProperty(value = "A规格", example = "2000")
    private Integer paperA;
    @ApiModelProperty(value = "A单价", example = "9.76")
    private Double priceA;
    @DecimalMax(value = "1000000000",message = "B数量必须小于10亿")
    @DecimalMin(value = "0", message = "B数量必须大于0")
    @ApiModelProperty(value = "B规格", example = "3000")
    private Integer paperB;
    @ApiModelProperty(value = "B单价", example = "7.26")
    private Double priceB;
    @DecimalMax(value = "1000000000",message = "箱贴必须小于10亿")
    @DecimalMin(value = "0", message = "箱贴必须大于0")
    @ApiModelProperty(value = "箱贴", example = "1000")
    private Integer paperS;
    @ApiModelProperty(value = "箱贴单价", example = "3.3")
    private Double priceS;
    @ApiModelProperty(value = "总金额", example = "40023.00")
    private BigDecimal total;
    @ApiModelProperty(value = "状态", example = "1")
    private String status;
    @ApiModelProperty(value = "申请类型1国外2国内")
    private String applyType;


}
