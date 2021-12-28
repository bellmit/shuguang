package com.sofn.fdpi.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;

/**
 * @Description
 * @Author wg
 * @Date 2021/6/5 12:48
 **/
@ApiModel("再出口时的一些信息")
@Data
public class ReExportSomeInfoVo {
    @ApiModelProperty("规格")
    private Integer size;
    @ApiModelProperty("欧鳗量")
    private Double sum;
    @ApiModelProperty("折算后可交易量")
    private Double obversion;
}
