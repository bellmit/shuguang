package com.sofn.agzirdd.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
public class WelcomeTableVo {

    @ApiModelProperty(value = "年份")
    private String dataYear;

    @ApiModelProperty(value = "值")
    private BigDecimal dataVal;

    @ApiModelProperty(value = "各下级的值")
    private List<WelcomeAreaVo> chilren;

    public WelcomeTableVo(String dataYear){
        this.dataYear=dataYear;
        this.dataVal=BigDecimal.ZERO;
        this.chilren = new ArrayList();
    }
}
